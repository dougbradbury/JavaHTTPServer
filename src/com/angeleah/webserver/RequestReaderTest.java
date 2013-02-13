package com.angeleah.webserver;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;


import java.io.*;

/**
* Created with IntelliJ IDEA.
* User: angeleah
* Date: 2/11/13
* Time: 11:10 AM
* To change this template use File | Settings | File Templates.
*/
//public class RequestReaderTest {
//
//    @Test
//    public void testStringReader() throws IOException {
//        StringReader in = new StringReader("HTTP/1.1 GET / \n");
//        RequestReader requestReader = new RequestReader(in);
//        requestReader.
//    }
//
//
//
//    @Test
//    public void testReader() throws IOException {
//    StringReader stream = new StringReader("HTTP/1.1 GET / \n");
//    RequestReader requestReader = new RequestReader(stream);
//    }

//        InputStreamReader stream = new InputStreamReader(in);
//        RequestReader requestReader = new RequestReader(stream);
//        BufferedReader bstream = new BufferedReader(requestReader);
//        assertEquals("HTTP/1.1 GET / ", bstream.readLine());

//InputStreamReader stream = new InputStreamReader(in);
//    BufferedReader bstream = new BufferedReader(in);
//    RequestReader requestReader = new RequestReader(bstream);
//    assertEquals("HTTP/1.1 GET / ", requestReader.readLine());

//       Original
//        InputStreamReader stream = new InputStreamReader(in);
//        BufferedReader bstream = new BufferedReader(stream);
//        assertEquals("HTTP/1.1 GET / ", bstream.readLine());
//        RequestReader requestReader = new RequestReader(stream);
//
//
//}
