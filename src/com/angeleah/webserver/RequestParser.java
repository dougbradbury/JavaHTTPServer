package com.angeleah.webserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

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
        parseInitialRequestLine(requestHeaders.remove(0));
//        splitHeaders(requestHeaders);
//        ? set header values in requeststore?
//        getBody(); if no content length return null. That way I can say if body is !null
    }

    public ArrayList<String> readHeaders(Reader inputStream)throws IOException {
        BufferedReader in = new BufferedReader(inputStream);
        ArrayList<String> data = new ArrayList<String>();
        String line = in.readLine();

        while ((line != null) && (!line.equals(""))) {
            if (lineContainsContentLength(line)) {
                setContentLength(line);
                line = in.readLine();
            } else {
                data.add(line);
                line = in.readLine();
            }
        }
        return data;
    }

    public boolean lineContainsContentLength(String line) {
        return line.contains("-Length: ");
    }

    public void setContentLength(String line) {
        String[] parts = line.split(": ");
        Integer length = Integer.parseInt(parts[1]);
        requestStore.setContentLength(length);
    }

    public RequestStore parseInitialRequestLine(String line) {
        String[] parts = line.split(" ");
        requestStore.setMethod(parts[0]);
        requestStore.setRequestUri(parts[1]);
        requestStore.setProtocolVersion(parts[2]);
        return requestStore;
    }

    public void splitHeaders(ArrayList<String> headers) {
         return;
    }
}

//    public void parseHeadersIntoKeyValuePairs(ArrayList<String> requestHeaders) {
//        requestHeaders.
////                requestheaders each do split on the space : space key [0], value [1]
////
////        figure out what I should set directly in the request store.
//
//    }
//
//    public String readBody(String s) throws IOException {
//        int length = 0;
//        try {
//                } catch(NumberFormatException e) {}
//            char[] chars = new char[length];
//            in.read(chars, 0, length);
//            return s + new String(chars);
//        }
//}
