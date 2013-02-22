package com.angeleah.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/13/13
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */

//ideas for a test to add would be that host is present for http1.1 or else it returns 400 bad request.
//    one or more blank lines can be parsed from the body.


public class Conductor {

    public BufferedReader in;
    public RequestStore requestStore;
    public String directory;

    public Conductor(BufferedReader in, String directory) {
        this.in = in;
        this.directory = directory;
    }

//    public byte[] conductTheProcess() throws IOException {
//          String line = in.readLine();
////        String response =  "HTTP/1.0 200 OK\r\nDate: Fri, 31 Dec 1999 23:59:59 GMT\r\nContent-Type: text/html\r\nContent-Length: 1\r\n\r\nA\r\n";
//        String response = line + "\n\r\n<h1>A</h1>";
////        return response.getBytes();
//        return response.getBytes();
//    }

//    public String conductTheProcess() throws IOException {
//        return in.readLine();
//    }

    public byte[] conductTheProcess() throws IOException {
        RequestStore requestStore = new RequestStore();
        requestStore.setDirectory(directory);
        parseRequest(in, requestStore);
        routeRequest(requestStore);
        Date date = new Date();
        byte[] response = buildRequest(requestStore, date);
        return response;
    }

    public void parseRequest(BufferedReader input, RequestStore store) throws IOException {
        RequestParser parser = new RequestParser(input, store);
        parser.processRequest(input);
    }

    public void routeRequest(RequestStore requestStore) {
        Router router = new Router();
        router.routeRequest(requestStore);
    }

      public byte[] buildRequest(RequestStore store, Date date) {
        ResponseBuilder responseBuilder = new ResponseBuilder(store, date);
        return responseBuilder.buildResponse(date);
      }
}
