package com.angeleah.webserver;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;
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
}