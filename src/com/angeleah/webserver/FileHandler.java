package com.angeleah.webserver;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/12/13
 * Time: 3:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileHandler {

    public RequestStore handle(RequestStore requestStore, String file) {
        BinaryReader reader = new BinaryReader();
        byte[] body = reader.read(requestStore.getDirectory(), file);
        requestStore.setBody(body);
        requestStore.setOk();
        return requestStore;
    }
}