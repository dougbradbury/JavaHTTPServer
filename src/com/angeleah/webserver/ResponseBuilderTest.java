package com.angeleah.webserver;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static junit.framework.Assert.assertEquals;


/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/18/13
 * Time: 11:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseBuilderTest {

    public ResponseBuilder responseBuilder;
    public RequestStore requestStore;

    @Before
    public void setUp() {
        requestStore = new RequestStore();
        responseBuilder = new ResponseBuilder(requestStore);

    }

//    @Test
//    public void itShouldBeAbleToBuildAResponse() {
//
//    }
    public RequestStore testSetUp(String testRequest) throws IOException {
        StringReader request = new StringReader(testRequest);
        BufferedReader in = new BufferedReader(request);
        RequestParser requestParser = new RequestParser(in, requestStore);
        try {
            requestParser.processRequest(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestStore;
    }

    @Test
    public void itShouldIncludeTheProtocolVersion() throws IOException {
        testSetUp("GET / HTTP/1.1\nHost: www.Superawesome.com\nContent-Length: 24\n\r\nmy = data value1 = hello\n");
        requestStore.setOk();
        assertEquals("HTTP/1.1 200 OK\r\n",responseBuilder.constructInitialResponseLine());
    }
}
