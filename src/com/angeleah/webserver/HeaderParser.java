package com.angeleah.webserver;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 1/30/13
 * Time: 11:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class HeaderParser {
    RequestStore requestStore = new RequestStore();

//    do not forget about handling the cr/lf that will be in evey line.  it needs to be stripped
//    when reading can help determining things.
    public RequestStore handleInitialRequestLine(String line) {
        String[] parts = line.split(" ");
        requestStore.setMethod(parts[0]);
        requestStore.setRequestUri(parts[1]);
        requestStore.setProtocolVersion(parts[2]);
        return requestStore;
    };



//    need to read the first line and then hand it off to be handled.  read the rest including the body.
}
