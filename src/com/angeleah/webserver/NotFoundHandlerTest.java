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
//        assertEquals(body.getBytes(),requestStore.getBody());

//        need to iterate over these two individually and compare the chars.
    }


}
