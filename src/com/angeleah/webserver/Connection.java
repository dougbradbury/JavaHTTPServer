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

    public static void main(String[] args) {

        System.out.println(args);

        String directory = "/Users/angeleah/Development/8th_light_apprenticeship/javaserver/src/com/angeleah/webserver/cob_spec/public/";

        try {

//            String directory = parseDirectory(args);
            ServerSocket serverSocket = new ServerSocket(5000);
            for (;;)  {
                Socket client = serverSocket.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                Conductor conductor = new Conductor(in, directory);
                DataOutputStream out= new DataOutputStream(client.getOutputStream());
              //  out.writeChars(directory);

                byte[] response = conductor.conductTheProcess();
                int length = response.length;
                out.write(response, 0, length);

                out.close();
                in.close();
                client.close();
            }
        }
        catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
            System.err.println("Usage: java HttpMirror <port>");
        }
    }

    public static int parsePort(String[] args) {
        ArrayList<String> argsList = getArgs(args);
        int index = argsList.indexOf("-p");
        String port = argsList.get(index + 1);
        return Integer.parseInt(port);
    }

    public static String parseDirectory(String[] args) {
        ArrayList<String> argsList = getArgs(args);
        int index = argsList.indexOf("-d");
        String directory = argsList.get(index + 1);
        return directory;
    }

    private static  ArrayList<String> getArgs(String[] args) {
        ArrayList<String> argsContents = new ArrayList<String>();
        for (String name : args) {
            argsContents.add(name);
        }
        return argsContents;
    }
}


//public class Connection {
//
//    public Connection(Socket client, String directory) {
//    }
//
//    public void connect(Socket client, String directory) throws IOException {
//
//        try {
//                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//                Conductor conductor = new Conductor(in, directory);
//                DataOutputStream out= new DataOutputStream(client.getOutputStream());
//
//                byte[] response = conductor.conductTheProcess();
//                int length = response.length;
//                out.write(response, 0, length);
//
//                out.close();
//                in.close();
//                client.close();
//        }
//        catch (Exception e) {
//            System.err.println(e);
//            e.printStackTrace();
//            System.err.println("Usage: java HttpMirror <port>");
//        }
//
//    }
//}

