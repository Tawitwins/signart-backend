///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package TchatPackage;
//
//import com.corundumstudio.socketio.AckRequest;
//import com.corundumstudio.socketio.Configuration;
//import com.corundumstudio.socketio.SocketIOClient;
//import com.corundumstudio.socketio.SocketIOServer;
//import com.corundumstudio.socketio.listener.DataListener;
//import java.io.UnsupportedEncodingException;
////import java.net.Socket;
//import java.net.URISyntaxException;
//import static org.bouncycastle.crypto.tls.ConnectionEnd.server;
//
///**
// *
// * @author SNMBENGUEO
// */
//public class serverChat {
////    // config  com.corundumstudio.socketio.Configuration; 
////    Configuration config = new Configuration(); 
////    config.setHostname("127.0.0.1"); 
////    config.setPort(10101);
////    
////    // creation du serveur 
////    server = new SocketIOServer(config);
////
////    server.addConnectListener(this);
////    server.addDisconnectListener(this);
////    server.addEventListener("query", AddressQuery.class, this);
//    //static private Socket socket;
//    static final int PORT = 8080;
//    static SocketIOServer server;
//    public static void main(String[] args) throws InterruptedException {
//        Thread ts = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    server();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        Thread tc = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                client();
//            }
//
//            private void client() {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });
//        ts.start();
//        tc.start();
//    }
//
//    public static void server() throws InterruptedException, UnsupportedEncodingException {
//        Configuration config = new Configuration();
//        config.setHostname("localhost");
//        config.setOrigin("http://localhost:8080");
//        config.setPort(PORT);
//        server = new SocketIOServer(config);
//        server.addEventListener("SignartConnexion", String.class, new DataListener<String>() {
//            @Override
//            public void onData(SocketIOClient client, String data, AckRequest ackRequest) {
//                client.sendEvent("toClient", "message from server");
//                System.out.println("il se passe un truc la on a recu des bails"+data);
//            }
//        });
//        server.start();
//        System.out.println("le serveur de tchat de freish a été lancé sur le port ");
//        Thread.sleep(Integer.MAX_VALUE);
//        server.stop();
//    }
//
//// public static void client() throws URISyntaxException, InterruptedException {
////        IO.Options opt = new IO.Options();
////        opt.port = PORT;
////        socket = IO.socket("http://localhost", opt);
////        socket.on("toClient", new Emitter.Listener() {
////            @Override
////            public void call(Object... args) {
////                System.out.println(args);
////            }
////        });
////        while(!socket.connected()) {
////            socket.connect();
////            Thread.sleep(100);
////            System.out.println(socket.connected());
////        }
////        socket.emit("toServer", "test data");//NEVER
////     }
//
//
//// creation du serveur server = new SocketIOServer(config);
//
//}
