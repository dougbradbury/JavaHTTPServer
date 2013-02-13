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

    @Test
    public void itShouldbeAbleToHandleA404Properly() {
        RequestStore requestStore = new RequestStore();
        requestStore.setDirectory("com/angeleah/webserver/TestDirectory/");
        String body = "<h1>NotFound</h1>";
        notFoundHandler.handle(requestStore);
        byte[] b1 = requestStore.getBody();
        byte[] b2 = body.getBytes();
        System.out.println(b1);
        System.out.println(b2);
        assertEquals(true, byteArrayCompare(b1, b2));
    }

    public boolean byteArrayCompare(byte[] b1, byte[] b2){
        for (int i=0; i< b1.length; i++) {
            if (b1[i] != b2[i]) {
                return false;
            }
        }
        return true;
    }
}
