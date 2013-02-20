package com.angeleah.webserver;

import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/16/13
 * Time: 12:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class RouterTest {

    public Router router;

    @Before
    public void SetUp() {
        router = new Router();
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

//    @Test
//    public itShouldBeAbletoRouteToIndex() {
//
//    }
}
