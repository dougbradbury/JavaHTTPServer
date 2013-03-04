package com.angeleah.webserver;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/16/13
 * Time: 12:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class RouterTest {

    public boolean FileByteArrayCompare(byte[] b1, byte[] b2){
        for (int i=0; i< b1.length; i++) {
            if (b1[i] != b2[i]) {
                return false;
            }
        }
        return true;
    }

    class TestHandler implements RequestHandler {

        public boolean wasHandled = false;

        @Override
        public RequestStore handle(RequestStore requestStore) {
            wasHandled = true;
            return requestStore;
        }
    }

    @Test
    public void itShouldBeAbleToRegisterARoute() {
        RequestStore requestStore = new RequestStore();
        requestStore.setRequestUri("/");
        Router router = new Router();
        TestHandler handler = new TestHandler();
        router.register("/", handler);
        router.routeRequest(requestStore);
        assertTrue(handler.wasHandled);
    }

    @Test
    public void itShouldBeAbleToHandleARouteThatPointsToAFile() {
        RequestStore requestStore = new RequestStore();
        requestStore.setDirectory("com/angeleah/webserver/TestDirectory");
        requestStore.setRequestUri("/awesomePage.html");
        requestStore.setMethod("GET");
        Router router = new Router();
        router.routeRequest(requestStore);
        String body =  "<p>This Page is awesome</p>";
        byte[] b1 = body.getBytes();
        byte[] b2 = requestStore.getBody();
        assert(FileByteArrayCompare(b1, b2));
    }

    @Test
    public void itShouldHandleARouteThatIsNotFound(){
        RequestStore requestStore = new RequestStore();
        requestStore.setRequestUri("/coolawesomesweetness");
        requestStore.setDirectory("com/angeleah/webserver/TestDirectory");
        Router router = new Router();
        router.routeRequest(requestStore);
        String body ="<!DOCTYPE html>\n<title>Web Server</title>\n<body>\n<h1>Not Found</h1>\n</body>\n</html>";
        byte[] b1 = body.getBytes();
        byte[] b2 = requestStore.getBody();
        assert(FileByteArrayCompare(b1, b2));
    }
}
