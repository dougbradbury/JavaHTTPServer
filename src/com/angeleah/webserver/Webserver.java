//package com.angeleah.webserver;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//
///**
// * Created with IntelliJ IDEA.
// * User: angeleah
// * Date: 2/25/13
// * Time: 1:48 PM
// * To change this template use File | Settings | File Templates.
// */
//public class Webserver {
//
//        public static void main(String args[]) throws IOException {
//            int port = 5000;
//
//            String directory = "/Users/angeleah/Development/8th_light_apprenticeship/javaserver/src/com/angeleah/webserver/cob_spec/public/";
//
//            try {
//                ServerSocket serverSocket = new ServerSocket(port);
//                try {
//                    while (true) {
//                        Socket client = serverSocket.accept();
//                        new Thread(new Connection(client, directory).start());
//
//                    }
//                }
//                catch (IOException e) {
//                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                }
//                finally {
//                serverSocket.close();
//                }
//        }   catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//}
