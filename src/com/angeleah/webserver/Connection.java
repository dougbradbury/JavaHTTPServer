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

            for (;;)  {
                Socket client = serverSocket.accept();
                HttpRequest request = new HttpRequest(client, directory);
                Thread thread = new Thread(request);
                thread.start();
            }
        }
        catch (Exception e) {
            System.err.println("You must provide a port and a directory argument. ex: java -jar pathToJarfile/jarfile.jar -p 5000 -d directoryPath");
        }
    }

        public static int parsePort(String[] args) {
            ArrayList<String> argsList = getArgs(args);
            int index = argsList.indexOf("-p");         // -1                 make my own exception,(extend exception class)  test it  catch just that exception and make sure it happens
            String port = argsList.get(index + 1);           //check that port is less than args length
            return Integer.parseInt(port);
        }

    public static String parseDirectory(String[] args) {
        ArrayList<String> argsList = getArgs(args);
        int index = argsList.indexOf("-d");
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

final class HttpRequest implements Runnable {

    private final Socket client;
    private final String directory;

    public HttpRequest(Socket client, String directory){
        this.client = client;
        this.directory = directory;
    }

    public void run() {
        try {
            processRequest();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void processRequest() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        Conductor conductor = new Conductor(in, directory);
        DataOutputStream out= new DataOutputStream(client.getOutputStream());

        byte[] response = conductor.conductTheProcess();        // conn close header  and  try loop
        int length = response.length;
        out.write(response, 0, length);

        out.close();
        in.close();
        client.close();

    }
}

// ****** Working socket implementation ******
//package com.angeleah.webserver;
//import java.net.*;
//import java.io.*;
//import java.util.ArrayList;
//
///**
// * Created with IntelliJ IDEA.
// * User: angeleah
// * Date: 2/1/13
// * Time: 10:12 AM
// * To change this template use File | Settings | File Templates.
// */
//
//public class Connection {
//
//    public static void main(String[] args) {
//
//        System.out.println(args);
//
//        try {
//            int port = parsePort(args);
//            String directory = parseDirectory(args);
//            System.out.println(directory);
//            ServerSocket serverSocket = new ServerSocket(port);
//            for (;;) {
//                Socket client = serverSocket.accept();
//
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
//            }
//        }
//        catch (Exception e) {
//            System.err.println(e);
//            e.printStackTrace();
//            System.err.println("Usage: java HttpMirror <port>");
//        }
//    }
//
//    public static int parsePort(String[] args) {
//        ArrayList<String> argsList = getArgs(args);
//        int index = argsList.indexOf("-p");
//        String port = argsList.get(index + 1);
//        return Integer.parseInt(port);
//    }
//
//    public static String parseDirectory(String[] args) {
//        ArrayList<String> argsList = getArgs(args);
//        int index = argsList.indexOf("-d");
//        String directory = argsList.get(index + 1);
//        return directory;
//    }
//
//    private static ArrayList<String> getArgs(String[] args) {
//        ArrayList<String> argsContents = new ArrayList<String>();
//        for (String name : args) {
//            argsContents.add(name);
//        }
//        return argsContents;
//    }
//}