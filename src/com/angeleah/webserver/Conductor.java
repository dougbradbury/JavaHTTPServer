package com.angeleah.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/13/13
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class Conductor {

    public BufferedReader in;

    public Conductor(BufferedReader in) {
        this.in = in;
    }

    public String handleRequest() throws IOException {
        RequestStore requestStore = new RequestStore();
        RequestParser parser = new RequestParser(in, requestStore);
//        ResponseBuilder responseBuilder = new ResponseBuilder();
//        RequestStore dataToBuildResponse = parser.processRequest(in);
        String response = "HTTP/1.0 200 OK\r\nDate: Fri, 31 Dec 1999 23:59:59 GMT\r\nContent-Type: text/html\r\nContent-Length: 1\r\n\r\nA\r\n";
//        byte[] response = responseBuilder.buildResponse(dataToBuildResponse);
//        return requestStore;
        return response;
    }


}
