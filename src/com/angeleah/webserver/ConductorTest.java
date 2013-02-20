package com.angeleah.webserver;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/13/13
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConductorTest {

    @Test
    public void itShouldBeAbleToConductTheProcess() {
        StringReader request = new StringReader("POST /form HTTP/1.1\nHost: www.Superawesome.com\nContent-Length: 24\n\r\nmy = data value1 = hello\n");
        BufferedReader bufferedReader = new BufferedReader(request);
        Conductor conductor = new Conductor(bufferedReader);
    }
}
