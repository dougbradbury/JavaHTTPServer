package com.angeleah.webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/27/13
 * Time: 3:17 PM
 * To change this template use File | Settings | File Templates.
 */
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
            e.printStackTrace();
        }
    }

    private void processRequest() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        Conductor conductor = new Conductor(in, directory);
        DataOutputStream out= new DataOutputStream(client.getOutputStream());

        byte[] response = conductor.conductTheProcess();
        int length = response.length;
        out.write(response, 0, length);

        out.close();
        in.close();
        client.close();
    }
}
