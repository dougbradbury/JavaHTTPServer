package com.angeleah.webserver;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 1/22/13
 * Time: 11:49 AM
 * To change this template use File | Settings | File Templates.
 */
public interface RequestHandler {

    public RequestStore handle(RequestStore requestStore);
}
