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

    public RequestParser(Reader inputStream, RequestStore requestStore) {
        this.requestStore = requestStore;

    }

    public void processRequest(Reader inputStream) throws IOException {
        ArrayList<String> requestHeaders = readHeaders(inputStream);
        parseInitialRequestLine(requestHeaders.remove(0));
        checkForQueryStringParams();
//        parseHeadersIntoKeyValuePairs(requestHeaders);
//        ? set header values in requeststore?
//         if no content length
//              set params to null. That way I can say if body is !null
//        else readBody()
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

//    public RequestStore readRequestBody(Reader inputStream) throws IOException {
//        BufferedReader in = new BufferedReader(inputStream);
//        int c;
//        StringBuilder response= new StringBuilder();
//        while ((c = in.read()) != "") {
//            response.append( (char)c ) ;
//        }
//        String result = response.toString();
//        boolean blank = true;
//          while (blank) {
//             String line = in.readLine();
//             if ((line != "") && (line != "\r") && (line != "\n")) {
//                 blank = false;
//             }
//         }


//    }

    public RequestStore readBody(Reader inputStream) throws IOException {
        StringBuilder params = new StringBuilder();
        params.append(trimBlankLines(inputStream));
        System.out.println(params);
        int offsetLength = params.length();
        int remainingBodyLength = (requestStore.getContentLength() - offsetLength);
        BufferedReader in = new BufferedReader(inputStream);
        char[] chars = new char[remainingBodyLength];
        in.read(chars, 0, remainingBodyLength);
        params.append(String.valueOf(chars));

        byte[] bodyInBytes = params.toString().getBytes();
        requestStore.setBody(bodyInBytes);
        return requestStore;
    }

     public String trimBlankLines(Reader inputStream) throws IOException {
         BufferedReader in = new BufferedReader(inputStream);
         String characters = new String();
         boolean blank = true;
          while (blank) {
             String line = in.readLine();
             if ((line != "") && (line != "\r") && (line != "\n")) {
                 blank = false;
             }
         }
         return characters;
     }
}

