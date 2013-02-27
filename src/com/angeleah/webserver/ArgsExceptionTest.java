package com.angeleah.webserver;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/27/13
 * Time: 3:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArgsExceptionTest {

//    test it  catch just that exception and make sure it happens

    @Test(expected = ArgsException.class)
    public void itShouldThrowAnExceptionWhenNotPassedADirectory() throws ArgsException {
        String[] args = new String[]{"java -jar ang/server/cool", "-p", "5000"};
            Connection.parseDirectory(args);
    }
}
