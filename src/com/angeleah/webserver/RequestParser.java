package com.angeleah.webserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.net.URLDecoder;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/13/13
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestParser {

    public RequestStore requestStore;

    public RequestParser(BufferedReader in, RequestStore requestStore) {
        this.requestStore = requestStore;
    }

    public RequestStore processRequest(BufferedReader in) throws IOException {
        ArrayList<String> requestHeaders = readHeaders(in);
        parseInitialRequestLine(requestHeaders.remove(0));
        checkForQueryStringParams();
        parseHeadersIntoKeyValuePairs(requestHeaders);
        if (requestStore.getRequestContentLength() != null) {
            String bodyContent = checkForBlankLines(in) + readRequestBody(in, (requestStore.getRequestContentLength() - 1));
            requestStore.setRequestBody(bodyContent);
        }
        return requestStore;
    }

    public ArrayList<String> readHeaders(BufferedReader in)throws IOException {
        ArrayList<String> data = new ArrayList<String>();
        String line = in.readLine();

        while ((line != null) && (!line.equals(""))) {
            if (lineContainsContentLength(line)) {
                setRequestContentLength(line);
                line = in.readLine();
            } else {
                data.add(line);
                line = in.readLine();
            }
        }
        return data;
    }

    public void checkForQueryStringParams() {
        String route = requestStore.getRequestUri();
        if (route.contains("\\?")) {
            String splitRoute[] =  route.split("\\?");
            requestStore.setRequestUri(splitRoute[0]);
            splitRouteAtAmpersand(splitRoute[1]);
        }
    }

    public void splitRouteAtAmpersand(String route) {
        HashMap params = new HashMap();
        String queryStringParams[] = route.split("\\&");
        int i;
        for(i=0; i < queryStringParams.length; i++) {
        String pairs[] = queryStringParams[i].split("=");
             params.put(pairs[0], pairs[1]);
        }
        requestStore.setParams(params);
    }

    public boolean lineContainsContentLength(String line) {
        return line.contains("-Length: ");
    }

    public void setRequestContentLength(String line) {
        String[] parts = line.split(": ");
        Integer length = Integer.parseInt(parts[1]);
        requestStore.setRequestContentLength(length);
    }

    public RequestStore parseInitialRequestLine(String line) throws UnsupportedEncodingException {
        String[] parts = line.split(" ");
        requestStore.setMethod(parts[0]);
        requestStore.setRequestUri(decodeRequestUri(parts[1]));
        requestStore.setProtocolVersion(parts[2]);
        return requestStore;
    }

    public String decodeRequestUri(String uri)throws UnsupportedEncodingException {
       return URLDecoder.decode(uri, "UTF-8");
    }

    public void parseHeadersIntoKeyValuePairs(ArrayList<String> headers) {
        for (String line : headers) {
            String[] parts = line.split(":");
            requestStore.setHeader(parts[0], parts[1].trim());
        }
    }

    public String readRequestBody(BufferedReader in, int length) throws IOException {
        StringBuilder bodyContent = new StringBuilder();
        char[] chars = new char[length];
        in.read(chars, 0, length);
        bodyContent.append(chars);
        return bodyContent.toString();
    }

    public String checkForBlankLines(BufferedReader in) throws IOException {
        StringBuilder bodyCharacters = new StringBuilder();
        boolean blank = true;
        while (blank) {
            StringBuilder c = new StringBuilder();
            int character = in.read();
            c.append((char) character) ;
            if ((!c.toString().equals("\r")) && (!c.toString().equals("\n")) && (!c.toString().equals("")) && (!c.toString().equals(" "))) {
                bodyCharacters.append(c.toString());
                blank = false;
            }
        }
       return bodyCharacters.toString();
    }
}