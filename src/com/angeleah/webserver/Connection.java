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

    public static void main(String args[]) {
        try {
//            int port = Integer.parseInt(args[0]);
            ServerSocket serverSocket = new ServerSocket(5000);
            for (;;) {
                Socket client = serverSocket.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                Conductor conductor = new Conductor(in);
                PrintWriter out = new PrintWriter(client.getOutputStream());
                // need to use a buffered writer when writing the byte array

                out.write(conductor.handleRequest());

                out.close();
                in.close();
                client.close();
            }
        }
        catch (Exception e) {
            System.err.println(e);
            System.err.println("Usage: java HttpMirror <port>");
        }
    }
}




