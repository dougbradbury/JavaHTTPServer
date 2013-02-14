package com.angeleah.webserver;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/13/13
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestParserTest {

//    @Test
//    public void testStringReader() throws IOException {
//        StringReader in = new StringReader("HTTP/1.1 GET / \n");
//        RequestParser requestReader = new RequestParser(in);
//        requestReader.readHeaders(in);
//    }

    @Test
    public void itShouldBeAbleToReadTheHeaders() throws IOException {
        StringReader in = new StringReader("GET / HTTP/1.1\nHost : http://Superawesome.com");
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        ArrayList<String> headers = new ArrayList<String>();
        headers.add("GET / HTTP/1.1");
        headers.add("Host : http://Superawesome.com");
        assertEquals(headers,requestParser.readHeaders(in));
    }

    @Test
    public void itShouldBeAbleToParseTheInitialRequestLine() throws IOException {
        StringReader in = new StringReader("GET / HTTP/1.1\nHost : http://Superawesome.com");
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        ArrayList<String> headers = requestParser.readHeaders(in);
        String initialRequestLine = headers.remove(0);
        requestParser.parseInitialRequestLine(initialRequestLine);
        assertEquals("HTTP/1.1", requestStore.getProtocolVersion());
        assertEquals("GET", requestStore.getMethod());
        assertEquals("/", requestStore.getRequestUri());
    }

//    @Test
//    public void itShouldBeAbleToParseTheHeadersIntoKeyValuePairs() {
//        StringReader in = new StringReader("GET / HTTP/1.1\nHost : http://Superawesome.com");
//        RequestStore requestStore = new RequestStore();
//        RequestParser requestParser = new RequestParser(in, requestStore);
//        ArrayList<String> headers = requestParser.readHeaders(in);
//        String initialRequestLine = headers.remove(0);
//        requestParser.parseHeadersIntoKeyValuePairs()
//    }

    @Test
    public void itShouldBeAbleToDetermineIfTheLineDoesNotContainTheContentLength() {
        StringReader in = new StringReader("GET / HTTP/1.1\nHost : http://Superawesome.com\n");
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        String line = "Host : http://Superawesome.com";
        assertFalse(requestParser.lineContainsContentLength(line));
    }

    @Test
    public void itShouldBeAbleToDetermineIfTheLineDoesContainTheContentLength() {
        StringReader in = new StringReader("GET / HTTP/1.1\nHost : http://Superawesome.com");
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        String line = "Content-Length: text/html";
        assertTrue(requestParser.lineContainsContentLength(line));
    }

    @Test
    public void itShouldBeAbleToSetTheCOntentLength() throws IOException {
        StringReader in = new StringReader("GET / HTTP/1.1\nHost : http://Superawesome.com\nContent-Length: 45");
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        requestParser.readHeaders(in);
        Integer length = 45;
        assertEquals(length, requestStore.getContentLength());
    }
}