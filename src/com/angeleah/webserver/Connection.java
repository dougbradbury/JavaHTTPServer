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

        String directory = "/Users/angeleah/Development/8th_light_apprenticeship/javaserver/src/com/angeleah/webserver/cob_spec/public/";  //parseDirectory(args);

        try {
//            int port = Integer.parseInt(args[0]);
            ServerSocket serverSocket = new ServerSocket(5000);
            for (;;) {
                Socket client = serverSocket.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                Conductor conductor = new Conductor(in, directory);
//                BufferedWriter out = new BufferedWriter((new OutputStreamWriter(client.getOutputStream())));
//                OutputStream out = new DataOutputStream(client.getOutputStream());
                DataOutputStream out= new DataOutputStream(client.getOutputStream());
                byte[] response = conductor.conductTheProcess();
                int length = response.length;
                out.write(response, 0, length);

//                String response = conductor.conductTheProcess();
//                out.write(response);

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

//    public static String parseDirectory(String[] args) {
//
//        return null;
//    }
}
//(defn parse-directory [commands]
//        (let [matches (re-find #"-d \S+" (vector-to-string commands))]
//        (first(rest (str/split matches #" ")))))




