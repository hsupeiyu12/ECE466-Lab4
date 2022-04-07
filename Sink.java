import java.io.*;
import java.net.*;
import java.util.*;

// usage:
// java Sink socketNumber
public class Sink implements Runnable {
    private final int MAX_PACKETS = 1000;
    private int socketNumber = 4444;
    private long sendTimes[] = new long[MAX_PACKETS];
    private long endTimes[] = new long[MAX_PACKETS];
    private int seqNos[] = new int[MAX_PACKETS];
    
    public Sink(int socketNumber) {
        this.socketNumber = socketNumber;
    }
    
    public void run() {
        DatagramSocket socket = null; 
        try {
            socket = new DatagramSocket(socketNumber);
            socket.setSoTimeout(3000);
        } catch (Exception e) {
			e.printStackTrace();
		}	
        byte[] buf = new byte[2000];
        DatagramPacket p = new DatagramPacket(buf, buf.length);
        
        PrintStream pout = null;
        FileOutputStream fout =  null;
        try {
            fout = new FileOutputStream("sink.out");
        } catch (Exception e) {
			e.printStackTrace();
		}
		pout = new PrintStream (fout);
		
		long elapsed = 0;
		boolean firstPacket = true;
		long startTime = 0;
        
        try {
            int i = 0;
            while (i < MAX_PACKETS) {
                socket.receive(p);
                endTimes[i] = System.nanoTime() / 1000;
                sendTimes[i] = fromByteArray(p.getData(), 6, 8);
                seqNos[i] = (int)fromByteArray(p.getData(), 2, 4);
                if (startTime == 0) {
                    startTime = sendTimes[i];
                }
                i++;
            }
        } catch (Exception e) {
            System.out.println("End");
            
        }
        
        for (int i = 0; i < seqNos.length; i++) {
            if (seqNos[i] == 0) {
                break;
            }
            pout.println(seqNos[i] + " " + (sendTimes[i] - startTime) + " " + (endTimes[i] - startTime));
            
        }
        pout.close();
    }
    
    /**
     * Converts a byte array to an integer.
     * @param value a byte array
     * @param start start position in the byte array
     * @param length number of bytes to consider
     * @return the integer value
     */
    public static long fromByteArray(byte [] value, int start, int length) {
        long Return = 0;
        for (int i=start; i< start+length; i++) {
            Return = (Return << 8) + (value[i] & 0xff);
        }
        return Return;
    }
}

