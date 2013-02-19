package com.angeleah.webserver;


/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/18/13
 * Time: 11:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseBuilder {

//timestamp the response
//    public byte[] buildResponse(RequestStore dataToBuildResponse) {
//        return null;
//    }
public RequestStore requestStore;

    public ResponseBuilder(RequestStore requestStore) {
        this.requestStore = requestStore;
    }

//    public byte[] buildResponse() {
//        byte[] headerResponse = buildResponseHeaders();
//        byte[] bodyResponse = buildResponseBody();
//        byte[] completeResponse = new byte[headerResponse.length + bodyResponse.length];
//        return completeResponse;
//    }
//
//    public byte[] buildResponseHeaders(){
//        StringBuilder headerResponse = new StringBuilder();
//        headerResponse.append(constructInitialResponseLine());
//        return (headerResponse.toString().getBytes());
//    }
//
//    public byte[] buildResponseBody() {
//      return requestStore.getBody();
//    }

    public String constructInitialResponseLine() {
        StringBuilder initialResponseLine = new StringBuilder();
        initialResponseLine.append(requestStore.getProtocolVersion());
        initialResponseLine.append(" ");
        initialResponseLine.append(requestStore.getCode());
        initialResponseLine.append(" ");
        initialResponseLine.append(requestStore.getStatus());
        initialResponseLine.append("\r\n");
        return initialResponseLine.toString();
    }
}
