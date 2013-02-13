package com.angeleah.webserver;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/12/13
 * Time: 4:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class RedirectHandlerTest {

    public RedirectHandler redirectHandler;

    @Before
    public void SetUp() {
        redirectHandler = new RedirectHandler();
    }

    @Test
    public void itShouldbeAbleToHandleARedirectProperly() {
        RequestStore requestStore = new RequestStore();
        redirectHandler.handle(requestStore);
        assertEquals("redirect",requestStore.getStatus());
        assertEquals("302",requestStore.getCode());
//        location should be root(host + /) ?
    }
}
