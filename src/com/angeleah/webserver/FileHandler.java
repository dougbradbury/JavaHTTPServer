package com.angeleah.webserver;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/12/13
 * Time: 3:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileHandler implements RequestHandler {

    public RequestStore handle(RequestStore requestStore) {
        if (requestStore.getMethod().equals("GET")) {
            BinaryReader reader = new BinaryReader();
            byte[] body = reader.read(requestStore.getDirectory(), requestStore.getRequestUri());
            requestStore.setBody(body);
            requestStore.setContentLength(body.length);
            requestStore.setOk();
        } else {
            requestStore.set405();
        }
        return requestStore;
    }
}