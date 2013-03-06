package com.angeleah.webserver;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/12/13
 * Time: 4:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class RedirectHandler implements RequestHandler{

    public RequestStore handle(RequestStore requestStore) {
        requestStore.setRedirect(); //the details of what a redirect are perhaps belong here instead of the request store - OCP / SRP
        return requestStore;
    }
}
