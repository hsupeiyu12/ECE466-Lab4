import java.io.*;
import java.net.*;
import java.util.*;

// usage:
// java Sink socketNumber
public class Sink implements Runnable {
    private int socketNumber = 4444;
    
    public Sink(int socketNumber) {
        this.socketNumber = socketNumber;
    }
    
    public void run() {
        DatagramSocket socket = null; 
        try {
            socket = new DatagramSocket(socketNumber);
            socket.setSoTimeout(5000);
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
            while (true) {
                socket.receive(p);
                long endTime = System.nanoTime();
                
                if (firstPacket) {
                    firstPacket = false;
                } else {
                    elapsed += endTime - startTime;
                }
                
                // start here to account for I/O delay
                startTime = System.nanoTime();
                pout.println(elapsed / 1000 + " " + p.getLength());
            }
        } catch (Exception e) {
            System.out.println("End");
        }
        
        pout.close();
    }
}

