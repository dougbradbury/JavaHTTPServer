//package com.angeleah.webserver;
//import java.net.*;
//import java.io.*;
//
///**
//* Created with IntelliJ IDEA.
//* User: angeleah
//* Date: 2/1/13
//* Time: 10:12 AM
//* To change this template use File | Settings | File Templates.
//*/
//public class Connection {
//
//    public static void connect(String[] headers) throws IOException {
//
//        ServerSocket serverSocket = null;
//        PrintWriter out = null;
//        BufferedReader in = null;
//        Socket clientSocket = null;
//
//        try {
//            serverSocket = new ServerSocket(5000);
//        }
//        catch (IOException e) {
//            System.out.println("Could not listen on port 5000");
//            System.exit(-1);
//        };
//
//        try {
//            clientSocket = serverSocket.accept();
//          //  httpServer.serve(clientSocket);
//        }
//        catch (IOException e) {
//            System.out.println("Accept failed 5000");
//            System.exit(-1);
//        };
//    }
//}
//
//
//
//
