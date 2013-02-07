package com.angeleah.webserver;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 1/31/13
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class RequestStore {
    private String method;
    private String requestUri;
    private String protocolVersion;
    private HashMap<String, String> headers = new HashMap<String, String>();
    private Byte[] body;


    public void setMethod(String methodValue) {
        method = methodValue;
    };

    public String method() {
        return method;
    };

    public void setRequestUri(String UriValue) {
        requestUri = UriValue;
    };

    public String requestUri() {
        return requestUri;
    };

    public void setprotocolVersion(String version) {
        protocolVersion = version;
    };

    public String ProtocolVersion() {
        return protocolVersion;
    };

    public void setHeader(String key, String value) {
        headers.put(key, value);
    };

    public String header(String key) {
        return headers.get(key);
    };

    public void setBody(Byte[] body) {
        this.body = body;
    };

    public Byte[] getBody() {
        return body;
    };
}
