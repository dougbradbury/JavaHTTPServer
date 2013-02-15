package com.angeleah.webserver;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/13/13
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestParserTest {

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

    @Test
    public void itShouldBeAbleToReturnARequestUriWhenItIsNotEncoded() throws UnsupportedEncodingException {
       StringReader in = new StringReader("GET / HTTP/1.1\nHost : http://Superawesome.com\n");
       RequestStore requestStore = new RequestStore();
       RequestParser requestParser = new RequestParser(in, requestStore);
       String uri = "/form";
       assertEquals("/form", requestParser.decodeRequestUri(uri));
    }

    @Test
    public void itShouldBeAbleToReturnARequestUriWhenItIsEncoded() throws UnsupportedEncodingException {
        StringReader in = new StringReader("GET / HTTP/1.1\nHost : http://Superawesome.com\n");
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        String uri = "%2Fform";
        assertEquals("/form", requestParser.decodeRequestUri(uri));
    }

    @Test
    public void itShouldBeAbleToSplitTheParamsAtTheAmpersand(){
        StringReader in = new StringReader("GET / HTTP/1.1\nHost : http://Superawesome.com\n");
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        String queryStringParams = "my=data&cool=sweet";
        requestParser.splitRouteAtAmpersand(queryStringParams);
        HashMap expected = new HashMap();
        expected.put("cool", "sweet" );
        expected.put("my","data");
        assertEquals(expected,requestStore.getParams());
    }

    @Test
    public void itShouldBeAbleToParseTheHeadersIntoKeyValuePairs() throws IOException {
        StringReader in = new StringReader("GET / HTTP/1.1\nHost: www.Superawesome.com");
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        ArrayList<String> headers = requestParser.readHeaders(in);
        String initialRequestLine = headers.remove(0);
        requestParser.parseHeadersIntoKeyValuePairs(headers);
        HashMap testHeaders = new HashMap();
        testHeaders.put("Host", "www.Superawesome.com");
        assertEquals(testHeaders, requestStore.getAllHeaders());
    }

    @Test
    public void itShouldBeAbleToDetermineIfTheLineDoesNotContainTheContentLength() {
        StringReader in = new StringReader("GET / HTTP/1.1\nHost : www.Superawesome.com\n");
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        String line = "Host : http://Superawesome.com";
        assertFalse(requestParser.lineContainsContentLength(line));
    }

    @Test
    public void itShouldBeAbleToDetermineIfTheLineDoesContainTheContentLength() {
        StringReader in = new StringReader("GET / HTTP/1.1\nHost: www.Superawesome.com");
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        String line = "Content-Length: text/html";
        assertTrue(requestParser.lineContainsContentLength(line));
    }

    @Test
    public void itShouldBeAbleToSetTheContentLength() throws IOException {
        StringReader in = new StringReader("GET / HTTP/1.1\nHost: www.Superawesome.com\nContent-Length: 45");
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        requestParser.readHeaders(in);
        Integer length = 45;
        assertEquals(length, requestStore.getContentLength());
    }

    @Test
    public void itShouldParseTheHeadersIntoKeyValuePairs() throws IOException {
        StringReader in = new StringReader("GET / HTTP/1.1\nHost: www.Superawesome.com\nContent-Length: 45");
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        ArrayList<String> requestHeaders = requestParser.readHeaders(in);
        requestParser.parseInitialRequestLine(requestHeaders.remove(0));
        requestParser.parseHeadersIntoKeyValuePairs(requestHeaders);
        assertEquals("www.Superawesome.com", requestStore.getHeaders("Host"));
    }

//    @Test
//    public void itShouldBeAbleToGetTheBodyIfThereIsOne() throws IOException {
//        StringReader in = new StringReader("GET / HTTP/1.1\nHost: www.Superawesome.com\nContent-Length: 24\r\n\r\nmy = data value1 = hello\n");
//        RequestStore requestStore = new RequestStore();
//        RequestParser requestParser = new RequestParser(in, requestStore);
//        ArrayList<String> requestHeaders = requestParser.readHeaders(in);
//        requestParser.parseInitialRequestLine(requestHeaders.remove(0));
//        requestParser.readBody(in);
//        String body = "my = data value1 = hello";
//        assertEquals(body.getBytes(), requestStore.getBody());
//    }
}