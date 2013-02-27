package com.angeleah.webserver;
import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import java.net.*;
import java.io.*;

///**
// * Created with IntelliJ IDEA.
// * User: angeleah
// * Date: 2/1/13
// * Time: 10:13 AM
// * To change this template use File | Settings | File Templates.
// */
public class ConnectionTest {

    @Test
    public void itShouldBeAbleToParseAPort() throws ArgsException {
        String[] args = new String[]{"ang/server/cool", "-p", "5000", "-d", "what!"};
        assertEquals(5000, Connection.parsePort(args));
    }

    @Test
    public void itShouldBeAbleToParseADirectory() throws ArgsException {
        String[] args = new String[]{"ang/server/cool.jar", "-p", "5000", "-d", "com/angeleah/webserver/TestDirectory"};
        assertEquals("com/angeleah/webserver/TestDirectory", Connection.parseDirectory(args));
    }
}
