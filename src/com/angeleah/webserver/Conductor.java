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

//ideas for a test to add would be that host is present for http1.1 or else it returns 400 bad request.
//    one or more blank lines can be parsed from the body.


public class Conductor {

    public BufferedReader in;

    public Conductor(BufferedReader in) {
        this.in = in;
    }

    public String handleRequest() throws IOException {
        RequestStore requestStore = new RequestStore();
//        RequestParser parser = new RequestParser(in, requestStore);
//        RequestStore parsedData = parser.processRequest(in);
        //        MimeTypeExtractor typeExtractor = new MimeTypeExtractor();
//                  Router router = new Router();
//                RequestStore dataToBuildResponse = router.route(parsedData);

        //        ResponseBuilder responseBuilder = new ResponseBuilder(date);
//         byte[] response = responseBuilder.buildResponse(dataToBuildResponse);

        //then send the data to the router to be handled.(pass request store(and a mime type extractor? and return request store.
        String response = "HTTP/1.0 200 OK\r\nDate: Fri, 31 Dec 1999 23:59:59 GMT\r\nContent-Type: text/html\r\nContent-Length: 1\r\n\r\nA\r\n";
        return response;
    }
}
