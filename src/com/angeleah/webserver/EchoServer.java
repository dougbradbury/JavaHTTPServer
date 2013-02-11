package com.angeleah.webserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/8/13
 * Time: 10:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class EchoServer {

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

