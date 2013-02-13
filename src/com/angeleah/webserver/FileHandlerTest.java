package com.angeleah.webserver;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import java.util.logging.Logger;

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
    public void itShouldBeAbleToHandleAFileRequestProperly() {
        RequestStore requestStore = new RequestStore();
        requestStore.setDirectory("com/angeleah/webserver/TestDirectory/");
        String body = "<p>This Page is awesome</p>";
        fileHandler.handle(requestStore, "awesomePage.html");
        assertEquals("200", requestStore.getCode());
        assertEquals("OK",requestStore.getStatus());
        byte[] b1 = requestStore.getBody();
        byte[] b2 = body.getBytes();
        System.out.println(b1);
        System.out.println(b2);
        assertEquals(true, FileByteArrayCompare(b1, b2));
    }

    public boolean FileByteArrayCompare(byte[] b1, byte[] b2){
        for (int i=0; i< b1.length; i++) {
            if (b1[i] != b2[i]) {
                return false;
            }
        }
        return true;
    }
}
