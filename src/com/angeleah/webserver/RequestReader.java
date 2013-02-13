package com.angeleah.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/11/13
 * Time: 11:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class RequestReader {

    public RequestReader(Reader inputStream) {
        try {
            BufferedReader in = new BufferedReader(inputStream);
            Integer contentLength = 0;
            ArrayList<String> headers = readHeaders(in);
            String content = readBody(in);
            parseHeaders(headers);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    ArrayList<String> readHeaders(BufferedReader in) throws IOException {
//        StringBuilder unparsedHeader = new StringBuilder();
        ArrayList<String> unparsedHeader = new ArrayList<String>();
        String line = in.readLine();
        while (line != null && !line.equals("")) {
            unparsedHeader.add(line);
        }
        return unparsedHeader;
    };

    private String readBody(BufferedReader in) {
               return null ;
    };

    private ArrayList<String> parseHeaders(ArrayList<String> headers) {
        return null;
    };

    public RequestStore parseContent(String content) {
                    return null;
    };
}
//Can I process them individually as they come in? into a map?
