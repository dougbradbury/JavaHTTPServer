package com.angeleah.webserver;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.Assert.assertEquals;


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

    @Test
    public void itShouldBeAbleToRouteToIndex() {
        RequestStore requestStore = new RequestStore();
        requestStore.setDirectory("com/angeleah/webserver/TestDirectory");
        requestStore.setRequestUri("/");
        Router router = new Router();
        router.routeRequest(requestStore);
        String body = "<!DOCTYPE html>\n<title>Web Server</title>\n<body>\n<a href=\"/anotherTestFile.html\">anotherTestFile.html</a>\n<a href=\"/awesomePage.html\">awesomePage.html</a>\n<a href=\"/file1\">file1</a>\n<a href=\"/helloWorld.html\">helloWorld.html</a>\n<a href=\"/imageTest.jpeg\">imageTest.jpeg</a>\n</body>\n</html>";
        byte[] b1 = body.getBytes();
        byte[] b2 = requestStore.getBody();
        assert(FileByteArrayCompare(b1, b2));
    }

    @Test
    public void itShouldBeAbleToRouteToParamsHandler(){
        RequestStore requestStore = new RequestStore();
        requestStore.setRequestUri("/some-script-url?variable_1=123459876&variable_2=some_value");
        HashMap params = new HashMap();
        params.put("variable_1", "123459876");
        params.put("variable_2", "some_value");
        requestStore.setParams(params);
        Router router = new Router();
        router.routeRequest(requestStore);
        String body = "<!DOCTYPE html>\n<title>Web Server</title>\n<body>\n<p>variable_2 = some_value</p>\n<p>variable_1 = 123459876</p>\n</body>\n</html>";
        byte[] b1 = body.getBytes();
        byte[] b2 = requestStore.getBody();
        assert(FileByteArrayCompare(b1, b2));
    }

    @Test
    public void itShouldBeAbleToHandleAFile() {
        RequestStore requestStore = new RequestStore();
        requestStore.setDirectory("com/angeleah/webserver/TestDirectory");
        requestStore.setRequestUri("/awesomePage.html");
        Router router = new Router();
        router.routeRequest(requestStore);
        String body =  "<p>This Page is awesome</p>";
        byte[] b1 = body.getBytes();
        byte[] b2 = requestStore.getBody();
        assert(FileByteArrayCompare(b1, b2));
    }

    @Test
    public void itShouldBeAbleToHandleAForm() {
        RequestStore requestStore = new RequestStore();
        requestStore.setRequestUri("/form");
        String content = "my = data value1 = hello";
        requestStore.setRequestBody(content);
        String body = "<!DOCTYPE html>\n<title>Web Server</title>\n<body>\n<p>my = data value1 = hello</p>\n</body>\n</html>";
        Router router = new Router();
        router.routeRequest(requestStore);
        byte[] b1 = body.getBytes();
        byte[] b2 = requestStore.getBody();
        assert(FileByteArrayCompare(b1, b2));
    }

    @Test
    public void itShouldBeABleToNotFindingSomething(){
        RequestStore requestStore = new RequestStore();
        requestStore.setDirectory("com/angeleah/webserver/TestDirectory");
        requestStore.setRequestUri("/coolawesomesweetness");
        Router router = new Router();
        router.routeRequest(requestStore);
        String body = "<h1>Not Found</h1>";
        byte[] b1 = body.getBytes();
        byte[] b2 = requestStore.getBody();
        assert(FileByteArrayCompare(b1, b2));
    }

//    class TestHandler implements RequestHandler {
//
//        @Override
//        public RequestStore handle(RequestStore requestStore) {
//            //return dummy response
//            return null;  //To change body of implemented methods use File | Settings | File Templates.
//        }
//    }
//    @Test
//    public void itShouldBeAbleToRegisterARoute() {
//        Router router = new Router();
//        router.register("/foo", new TestHandler());
//
//        RequestStore request = new RequestStore();
//        request.setRequestUri("/foo");
//        RequestStore response = router.route(request);

        //check response

 //   }

}
