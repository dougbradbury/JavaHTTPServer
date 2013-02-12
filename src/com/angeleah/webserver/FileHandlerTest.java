package com.angeleah.webserver;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/12/13
 * Time: 3:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileHandlerTest {

    public FileHandler fileHandler;

    @Before
    public void SetUp() {
        fileHandler = new FileHandler();
    }

    @Test
    public void itShouldBeAbleToHandleAnImageRequestProperly() {
        RequestStore requestStore = new RequestStore();
        requestStore.setDirectory("com/angeleah/webserver/TestDirectory/");
        String body = "<p>This Page is awesome</p>";
        fileHandler.handle(requestStore, "awesomePage.html");
        assertEquals("200", requestStore.getCode());
        assertEquals("OK",requestStore.getStatus());
//        assertEquals(body.getBytes(), requestStore.getBody());
        //        need to iterate over these two individually and compare the chars.

    }
}
