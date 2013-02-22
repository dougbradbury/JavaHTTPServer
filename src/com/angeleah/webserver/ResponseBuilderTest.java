package com.angeleah.webserver;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
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
    public Date date;

    @Before
    public void setUp() {
        requestStore = new RequestStore();
        date = new  Date();
        responseBuilder = new ResponseBuilder(requestStore, date);
    }

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

    public boolean FileByteArrayCompare(byte[] b1, byte[] b2){
        for (int i=0; i< b1.length; i++) {
            if (b1[i] != b2[i]) {
                return false;
            }
        }
        return true;
    }
//        these can not be properly tested without my router to handle the requests and set the body format properly.
//    @Test
//    public void itShouldBeAbleToBuildAResponse() throws IOException {
//        testSetUp("POST /form HTTP/1.1\nHost: www.Superawesome.com\nContent-Length: 24\n\r\nmy = data value1 = hello\n");
//        requestStore.setOk();
//        String stringResponse = "HTTP/1.1 200 OK\r\n";
//        String stringResponse2 = "cool";
//        byte[] b1 = stringResponse.getBytes();
//
//        byte[] b2 = responseBuilder.buildResponseHeaders(date);
//        assert(FileByteArrayCompare(b1, b2));
//
//
//    }

    @Test
    public void itShouldBeAbleToBuildTheHeaders() throws IOException {
        testSetUp("GET / HTTP/1.1\nHost: www.Superawesome.com\n\r\n");
        requestStore.setOk();
        String stringResponse = "HTTP/1.1 200 OK\r\n";
        byte[] b1 = stringResponse.getBytes();
        byte[] b2 = responseBuilder.buildResponseHeaders(date);
        assert(FileByteArrayCompare(b1, b2));
    }

    @Test
        public void itShouldBeAbleToBuildTheBody() throws IOException {
        String body = "my = data value1 = hello";
        requestStore.setBody(body.getBytes());
        requestStore.setOk();
        byte[] b1 = requestStore.getBody();
        byte[] b2 = responseBuilder.buildResponseBody();
        assert(FileByteArrayCompare(b1, b2));
    }

    @Test
    public void itShouldIBeAbleToConstructTheInitialResponseLine() throws IOException {
        testSetUp("GET / HTTP/1.1\nHost: www.Superawesome.com\n\r\n");
        requestStore.setOk();
        assertEquals("HTTP/1.1 200 OK\r\n",responseBuilder.buildInitialResponseLine());
    }

//    @Test
//    public void itShouldBeAbleToConstructTheDateHeader(){
//        Date date = new Date(2013, 2, 19, 13, 18, 13);
//        assertEquals("Date: Tue, 19 Feb 2013 13:18:13 CST\r\n", responseBuilder.buildDateResponseLine(date));
//    }

    @Test
    public void itShouldBeAbleToConstructTheLocationHeader(){
        requestStore.setLocation("http://localhost:5000/");
        assertEquals("Location: http://localhost:5000/\r\n", responseBuilder.buildLocationResponseLine());
    }

    @Test
    public void itShouldBeAbleToConstructTheContentType(){
        requestStore.setMimeType("text/html");
        assertEquals("Content-Type: text/html\r\n", responseBuilder.buildContentTypeResponseLine());
    }

    @Test
    public void itSHouldBeAbleToGetTheContentLength(){
        requestStore.setContentLength(24);
        assertEquals("Content-Length: 24\r\n", responseBuilder.buildContentLengthResponseLine());
    }
}
