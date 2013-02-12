package com.angeleah.webserver;
import java.net.*;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/1/13
 * Time: 10:12 AM
 * To change this template use File | Settings | File Templates.
 */


public class Connection {

//    public static void connect(String[] headers) throws IOException {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                out.println("Hello...");
                out.println("Enter QUIT to exit...");
                out.println("Type anything you want and I will echo it.");
                out.println("Well, almost.");
                out.println("See what happens if you ask me the meaning of life.");
                out.flush();
                while (true) {
                    String str = in.readLine();
                    if (str == null) {
                        break;
                    } else {
                        if (str.trim().equals("What is the meaning of life?")) {
                            out.println("42");
                        } else if (str.trim().equals("QUIT")) {
                            out.println("Goodbye");
                            break;
                        } else {
                            out.println("Echo: " + str);
                            out.flush();
                        }
                    }
                }
                 clientSocket.close();
            }
        } catch (Exception ex) {}

    }
}
//}
//   pass the input stream to the request parser.
//out.println("Echo: " + str);
//out.flush();
//if (str.trim().equals("QUIT"))
//        out.println("Goodbye");
//break;
//} else if (str.trim().equals("What is the meaning of life?")) {
//        out.println("42");
//
//        ServerSocket serverSocket = null;
//        PrintWriter out = null;
//        BufferedReader in = null;
//        Socket clientSocket = null;
//
//        try {
//            serverSocket = new ServerSocket(5000);
//            System.out.println("listening on port 5000");
//        }
//        catch (IOException e) {
//            System.out.println("Could not listen on port 5000");
//            System.exit(-1);
//        };
//
//        try {
//              clientSocket = serverSocket.accept();

//              InputStream  sockIn = clientSocket.getInputStream();
//              OutputStream sockOut = clientSocket.getOutputStream();
//
//
//        }
//        catch (IOException e) {
//            System.out.println("Accept failed 5000");
//            System.exit(-1);
//        };
//    }
//}




