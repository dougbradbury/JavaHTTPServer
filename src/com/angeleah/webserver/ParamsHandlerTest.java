package com.angeleah.webserver;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/12/13
 * Time: 10:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class ParamsHandlerTest {

    public ParamsHandler paramsHandler;

    @Before
    public void SetUp() {
        paramsHandler = new ParamsHandler();
    }

    @Test
    public void itShouldBeAbleToHandleAFormCorrectly() {
        RequestStore requestStore = new RequestStore();
        HtmlBodyBuilder builder = new HtmlBodyBuilder();
        HashMap params = new HashMap();
        params.put("thing1", "12345");
        params.put("thing2", "987");
        params.put("name", "hello");
        System.out.println(params);
        String body = builder.createHtmlBodyWithParamsContent(params);
        requestStore.setParams(params);
        paramsHandler.handle(requestStore);
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
