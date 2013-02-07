package com.angeleah.webserver;
import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 1/30/13
 * Time: 11:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class HeaderParserTest {

    public HeaderParser headerParser;

    @Before
    public void setUp() {
        headerParser = new HeaderParser();
    };

    @Test
    public void shouldBeAbleToParseTheMethod() {
        String firstLine = new String("GET /form HTTP/1.1");
        RequestStore request = headerParser.handleInitialRequestLine(firstLine);
        assertEquals("GET", request.method());
    };

    public void shouldBeAbleToParseTheRequesturi() {
        String firstLine = new String("GET /form HTTP/1.1");
        RequestStore request = headerParser.handleInitialRequestLine(firstLine);
        assertEquals("/form", request.header("requestUri"));
    };

    public void shouldBeAbleToParseTheprotocolversion() {
        String firstLine = new String("GET /form HTTP/1.1");
        RequestStore request = headerParser.handleInitialRequestLine(firstLine);
        assertEquals("HTTP/1.1", request.header("protocolVersion"));
    };

//    public void itShouldReceiveTheRequest() {
//
//    };

//    @Test
//    public void parseTheLinesAfterTheInitialLine() {
//        Map<String, String> pairs = new HashMap<String, String>();
//        pairs.put("method","GET");
//        pairs.put("protocolVersion","HTTP 1.1");
//        pairs.put("requestUri","/form");
//        String incoming = new String("GET HTTP");
//        //assertEquals(pairs, headerParser.parse());
//    };

}
//I need to take in the values, read all of them, (into an array?)  Then grab part 0 and pop it off of the front and pass it to handle initial request line.
//  Then I need to go through the rest of them and pair up the key and value.(strip whitespace and then split on colon.-> into an array  then [0] and [1].
// Key and value.