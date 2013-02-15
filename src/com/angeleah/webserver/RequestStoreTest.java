package com.angeleah.webserver;
import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 1/31/13
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
import static junit.framework.Assert.assertEquals;

public class RequestStoreTest {

    public RequestStore requestStore;

    @Before
    public void setUp() {
        requestStore = new RequestStore();
    };

    @Test
    public void methodShouldReturnMethod() {
        requestStore.setMethod("GET");
        assertEquals("GET", requestStore.getMethod());
    }

    @Test
    public void shouldHaveHeaders() {
        requestStore.setHeader("Content-Type", "text/html");
        assertEquals("text/html", requestStore.getHeaders("Content-Type"));
    }

    @Test
    public void shouldBeAbleToSetMultipleHeaders() {
        requestStore.setHeader("Host", "localhost");
        assertEquals("localhost", requestStore.getHeaders("Host"));
    }

    @Test
    public void shouldHandleMultipleHeaderKeys() {
        requestStore.setHeader("Content-Type", "text/html");
        requestStore.setHeader("Host", "localhost");

        assertEquals("localhost", requestStore.getHeaders("Host"));
        assertEquals("text/html", requestStore.getHeaders("Content-Type"));
    }

    @Test
    public void shouldHaveBody() {
        byte[] body = new byte[]{1,2,3};
        requestStore.setBody(body);
        assertEquals(body[0], requestStore.getBody()[0]);
        assertEquals(body[1], requestStore.getBody()[1]);
        assertEquals(body[2], requestStore.getBody()[2]);
    }



}
