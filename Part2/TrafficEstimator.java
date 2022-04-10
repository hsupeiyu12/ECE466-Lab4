import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class TrafficEstimator {

    private static ConcurrentHashMap<Integer,Timestamp> map = new ConcurrentHashMap<Integer, Timestamp>();

    public static void mapPut(Integer key, Timestamp value){
        map.put(key, value);
    }

    public static Timestamp mapGet(Integer key){
        return map.get(key);
    }

    public static void main(String[] args) throws IOException {
        // BlackBox is started at localhost with default port 4444
        // also initialize the sink port for the TrafficSink to receive packets later
        
        // Default:
        String receiver = "localhost";
        InetAddress addr = InetAddress.getByName(receiver);
        int boxPort = 5990;
        int estimatorPort = 9939;
        
        // read the user input for given parameters L N r
        int L = 100000;
        int N = 5000;
        int r = 50;
        
        if (args.length > 3){
            String hostname = args[0];
            addr = InetAddress.getByName(hostname);
            boxPort = Integer.parseInt(args[1]);
            estimatorPort = 4445;
        }
        if (args.length >= 3){
            // read the user input for given parameters N L r
            N = Integer.parseInt(args[2]);
            L = Integer.parseInt(args[3]);
            r = Integer.parseInt(args[4]);
        }
        
        

        // start the traffic generator to send packets to blackbox
        new TrafficGenerator(addr, boxPort, N, L, r, estimatorPort).start();

        // start the traffic sink to receive packets from blackbox and compute timestamps
        new TrafficSink(estimatorPort,N, L, r).start();
    }
}
