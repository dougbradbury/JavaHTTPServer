package com.angeleah.webserver;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/16/13
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormHandlerTest {

    public FormHandler formHandler;

    @Before
    public void SetUp() {
        formHandler = new FormHandler();
    }

    @Test
    public void itShouldBeAbleToHandleAFormCorrectly() {
        RequestStore requestStore = new RequestStore();
        HtmlBodyBuilder builder = new HtmlBodyBuilder();
        String content = "my = data value1 = hello";
        String body = builder.createHtmlBodyWithRequestContent(content);
        requestStore.setRequestBody(content);
        formHandler.handle(requestStore);
        byte[] b1 = requestStore.getBody();
        byte[] b2 = body.getBytes();
        assertEquals("200", requestStore.getCode());
        assertEquals("OK", requestStore.getStatus());
        assertEquals(true, paramsCompare(b1, b2));
    }

    public boolean paramsCompare(byte[] b1, byte[] b2){
        for (int i=0; i< b1.length; i++) {
            if (b1[i] != b2[i]) {
                return false;
            }
        }
        return true;
    }

}
