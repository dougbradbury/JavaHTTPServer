package com.angeleah.webserver;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.io.BufferedReader;
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
    public void itShouldBeAbleToParseARequest() throws IOException {
        StringReader request = new StringReader("POST /form HTTP/1.1\nHost: www.Superawesome.com\nContent-Length: 24\n\r\nmy = data value1 = hello\n");
        BufferedReader in = new BufferedReader(request);
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        Integer length = 24;
        requestParser.processRequest(in);
        assertEquals("POST", requestStore.getMethod());
        assertEquals("/form", requestStore.getRequestUri());
        assertEquals("HTTP/1.1", requestStore.getProtocolVersion());
        assertEquals(length, requestStore.getRequestContentLength());
       assertEquals("my = data value1 = hello", requestStore.getRequestBody());
    }

    @Test
    public void itShouldBeAbleToReadTheHeaders() throws IOException {
        StringReader request = new StringReader("GET / HTTP/1.1\nHost: www.Superawesome.com");
        BufferedReader in = new BufferedReader(request);
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        ArrayList<String> headers = new ArrayList<String>();
        headers.add("GET / HTTP/1.1");
        headers.add("Host: www.Superawesome.com");
        assertEquals(headers,requestParser.readHeaders(in));
    }

    @Test
    public void itShouldBeAbleToParseTheInitialRequestLine() throws IOException {
        StringReader request = new StringReader("GET / HTTP/1.1\nHost: www.Superawesome.com");
        BufferedReader in = new BufferedReader(request);
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
       StringReader request = new StringReader("GET / HTTP/1.1\nHost: www.Superawesome.com\n");
       BufferedReader in = new BufferedReader(request);
       RequestStore requestStore = new RequestStore();
       RequestParser requestParser = new RequestParser(in, requestStore);
       String uri = "/form";
       assertEquals("/form", requestParser.decodeRequestUri(uri));
    }

    @Test
    public void itShouldBeAbleToReturnARequestUriWhenItIsEncoded() throws UnsupportedEncodingException {
        StringReader request = new StringReader("GET / HTTP/1.1\nHost: www.Superawesome.com\n");
        BufferedReader in = new BufferedReader(request);
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        String uri = "/form?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F";
        assertEquals("/form?variable_1=Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?", requestParser.decodeRequestUri(uri));
    }

    @Test
    public void itShouldBeAbleToSplitTheParamsAtTheAmpersand(){
        StringReader request = new StringReader("GET / HTTP/1.1\nHost: www.Superawesome.com\n");
        BufferedReader in = new BufferedReader(request);
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
        StringReader request = new StringReader("GET / HTTP/1.1\nHost: www.Superawesome.com");
        BufferedReader in = new BufferedReader(request);
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        ArrayList<String> headers = requestParser.readHeaders(in);
        headers.remove(0);
        requestParser.parseHeadersIntoKeyValuePairs(headers);
        HashMap testHeaders = new HashMap();
        testHeaders.put("Host", "www.Superawesome.com");
        assertEquals(testHeaders, requestStore.getAllHeaders());
    }

    @Test
    public void itShouldBeAbleToDetermineIfTheLineDoesNotContainTheContentLength() {
        StringReader request = new StringReader("GET / HTTP/1.1\nHost : www.Superawesome.com\n");
        BufferedReader in = new BufferedReader(request);
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        String line = "Host: http://Superawesome.com";
        assertFalse(requestParser.lineContainsContentLength(line));
    }

    @Test
    public void itShouldBeAbleToDetermineIfTheLineDoesContainTheContentLength() {
        StringReader request = new StringReader("GET / HTTP/1.1\nHost: www.Superawesome.com");
        BufferedReader in = new BufferedReader(request);
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        String line = "Content-Length: text/html";
        assertTrue(requestParser.lineContainsContentLength(line));
    }

    @Test
    public void itShouldBeAbleToSetTheContentLength() throws IOException {
        StringReader request = new StringReader("GET / HTTP/1.1\nHost: www.Superawesome.com\nContent-Length: 45");
        BufferedReader in = new BufferedReader(request);
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        requestParser.readHeaders(in);
        Integer length = 45;
        assertEquals(length, requestStore.getRequestContentLength());
    }

    @Test
    public void itShouldParseTheHeadersIntoKeyValuePairs() throws IOException {
        StringReader request = new StringReader("GET / HTTP/1.1\nHost: www.Superawesome.com\nContent-Length: 45");
        BufferedReader in = new BufferedReader(request);
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        ArrayList<String> requestHeaders = requestParser.readHeaders(in);
        requestParser.parseInitialRequestLine(requestHeaders.remove(0));
        requestParser.parseHeadersIntoKeyValuePairs(requestHeaders);
        assertEquals("www.Superawesome.com", requestStore.getHeaders("Host"));
    }

    @Test
    public void itShouldBeAbleToSetTheMimeType() throws IOException {
        StringReader request = new StringReader("GET /image.gif HTTP/1.1\nHost: www.Superawesome.com\nContent-Length: 45");
        BufferedReader in = new BufferedReader(request);
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        requestParser.processRequest(in);
        assertEquals("image/gif", requestStore.getMimeType());
    }

    @Test public void itShouldBeAbleToReturnTheFirstCharacterAfterBlankLines() throws IOException {
        StringReader request = new StringReader("\r\n\r\nmy = data value1 = hello\n");
        BufferedReader in = new BufferedReader(request);
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        assertEquals("m",requestParser.checkForBlankLines(in));
    }


    @Test
    public void itShouldBeAbleToGetTheBodyFromTheRequestIfThereIsOne() throws IOException {
        StringReader request = new StringReader("my = data value1 = hello\n");
        BufferedReader in = new BufferedReader(request);
        RequestStore requestStore = new RequestStore();
        RequestParser requestParser = new RequestParser(in, requestStore);
        Integer length = 24;
        requestStore.setRequestContentLength(length);
        String body = "my = data value1 = hello";
        assertEquals(body, requestParser.readRequestBody(in, length));
    }
}