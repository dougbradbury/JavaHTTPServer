package com.angeleah.webserver;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/1/13
 * Time: 10:12 AM
 * To change this template use File | Settings | File Templates.
 */

public class Connection {

    public static void main(String[] args) throws IOException {

        try {
            int port = parsePort(args);
            String directory = parseDirectory(args);
            ServerSocket serverSocket = new ServerSocket(port);
            ArrayList<Thread> threads = new ArrayList<Thread>();

            for (;;)  {
                Socket client = serverSocket.accept();
                HttpRequest request = new HttpRequest(client, directory);
                Thread thread = new Thread(request);
                threads.add(thread);
                thread.start();
            }
        }
        catch (ArgsException e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

        public static int parsePort(String[] args) throws ArgsException {
            ArrayList<String> argsList = getArgs(args);
            int index = argsList.indexOf("-p");
            if (index == -1) throw new ArgsException();
            String port = argsList.get(index + 1);
            if (argsList.indexOf(port) > argsList.size()) throw new ArgsException();
            return Integer.parseInt(port);
        }

    public static String parseDirectory(String[] args) throws ArgsException{
        ArrayList<String> argsList = getArgs(args);
        int index = argsList.indexOf("-d");
        if (index == -1) throw new ArgsException();
        String directory = argsList.get(index + 1);
        return directory;
    }

    private static ArrayList<String> getArgs(String[] args) {
        ArrayList<String> argsContents = new ArrayList<String>();
        for (String name : args) {
            argsContents.add(name);
        }
        return argsContents;
    }
}