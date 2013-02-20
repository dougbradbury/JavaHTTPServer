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
    public void itShouldBeAbleToHandleQueryStringsCorrectly() {
        RequestStore requestStore = new RequestStore();
        HtmlBodyBuilder builder = new HtmlBodyBuilder();
        HashMap params = new HashMap();
        params.put("variable_1", "123459876");
        params.put("variable_2", "some_value");
        requestStore.setParams(params);
        String body = builder.createHtmlBodyWithParamsContent(requestStore.getParams());
        paramsHandler.handle(requestStore);
        assertEquals(body, "<!DOCTYPE html>\n<title>Web Server</title>\n<body>\n<p>variable_2 = some_value</p>\n<p>variable_1 = 123459876</p>\n</body>\n</html>");
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
