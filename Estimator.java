import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

// usage:
// java Estimator blackboxIPaddress sendingSocket receivingSocket
public class Estimator {
    public static void main(String[] args) {
        String receiver = "localhost";
        int sendingSocket = 4444;
        int receivingSocket = 4445;
        if (args.length >= 1) {
            receiver = args[0];
        }
        if (args.length >= 2) {
            sendingSocket = Integer.parseInt(args[1]);
        }
        if (args.length >= 3) {
            receivingSocket = Integer.parseInt(args[2]);
        }
        
        try {
            InetAddress addr = InetAddress.getByName(receiver);
            DatagramSocket socket = new DatagramSocket();
            Sink sink = new Sink(receivingSocket);
		    int size = 16;
		    
		    new Thread(sink).start();
		    
		    for (int i = 1; i <= 4; i++) {
		        byte[] buf  = new byte[size];
		        System.arraycopy(toByteArray(receivingSocket), 2, buf, 0, 2);
		        System.arraycopy(toByteArray(i), 0, buf, 2, 4);
		        System.arraycopy(toByteArrayLong(System.nanoTime() / 1000), 0, buf, 6, 8);
		        
		        DatagramPacket packet = new DatagramPacket(buf, buf.length, addr, sendingSocket);
		        socket.send(packet);
		        long startTime = System.nanoTime();
		        while (System.nanoTime() - startTime < 1000);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Converts an integer to a byte array.
     * @param value an integer
     * @return a byte array representing the integer
    */
    public static byte[] toByteArray(int value) {
        byte[] Result = new byte[4];
        Result[3] = (byte) ((value >>> (8*0)) & 0xFF);
        Result[2] = (byte) ((value >>> (8*1)) & 0xFF);
        Result[1] = (byte) ((value >>> (8*2)) & 0xFF);
        Result[0] = (byte) ((value >>> (8*3)) & 0xFF);
        return Result;
    }
    
    public static byte[] toByteArrayLong(long value) {
        byte[] Result = new byte[8];
        Result[7] = (byte) ((value >>> (8*0)) & 0xFF);
        Result[6] = (byte) ((value >>> (8*1)) & 0xFF);
        Result[5] = (byte) ((value >>> (8*2)) & 0xFF);
        Result[4] = (byte) ((value >>> (8*3)) & 0xFF);
        Result[3] = (byte) ((value >>> (8*4)) & 0xFF);
        Result[2] = (byte) ((value >>> (8*5)) & 0xFF);
        Result[1] = (byte) ((value >>> (8*6)) & 0xFF);
        Result[0] = (byte) ((value >>> (8*7)) & 0xFF);
        return Result;
    }
}
