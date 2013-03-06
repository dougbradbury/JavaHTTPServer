package com.angeleah.webserver;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;


/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/12/13
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotFoundHandlerTest {

    public NotFoundHandler notFoundHandler;

    @Before
    public void SetUp() {
        notFoundHandler = new NotFoundHandler();
    }

    //How could this test not depend also on the body builder doing the right thing?
    //If your html template for not found changes, should this test need to be modified?
    @Test
    public void itShouldbeAbleToHandleA404Properly() {
        RequestStore requestStore = new RequestStore();
//        requestStore.setDirectory("com/angeleah/webserver/TestDirectory/");
        String body ="<!DOCTYPE html>\n<title>Web Server</title>\n<body>\n<h1>Not Found</h1>\n</body>\n</html>";
        notFoundHandler.handle(requestStore);
        byte[] b1 = requestStore.getBody();
        byte[] b2 = body.getBytes();
        assertEquals(true, FileByteArrayCompare(b1, b2));
    }

    public boolean FileByteArrayCompare(byte[] b1, byte[] b2){ //DRY
        for (int i=0; i< b1.length; i++) {
            if (b1[i] != b2[i]) {
                return false;
            }
        }
        return true;
    }
}
