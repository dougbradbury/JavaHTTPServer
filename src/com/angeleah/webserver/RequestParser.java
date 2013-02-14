package com.angeleah.webserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/13/13
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestParser {

    public RequestStore requestStore;

    public RequestParser(Reader inputStream, RequestStore requestStore) {
        this.requestStore = requestStore;

    }

    public void processRequest(Reader inputStream) throws IOException {
        ArrayList<String> requestHeaders = readHeaders(inputStream);
        String initialRequestLIne = requestHeaders.remove(0);
        handleInitialRequestLine(initialRequestLIne);
        splitHeaders(requestHeaders);
//        ? set header values in requeststore?
//        getBody(); if no content length return null. That way I can say if body is !null
    }

    public ArrayList<String> readHeaders(Reader inputStream)throws IOException {
        BufferedReader in = new BufferedReader(inputStream);
        ArrayList<String> data = new ArrayList<String>();
        String line = in.readLine();

        while ((line != null) && (!line.equals(""))) {
            data.add(line);
            line = in.readLine();
        }
        return data;
    }

    public RequestStore handleInitialRequestLine(String line) {
        String[] parts = line.split(" ");
        requestStore.setMethod(parts[0]);
        requestStore.setRequestUri(parts[1]);
        requestStore.setProtocolVersion(parts[2]);
        return requestStore;
    }

    private void splitHeaders(ArrayList<String> requestHeaders) {

    }
}
