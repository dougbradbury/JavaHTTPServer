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
    private byte[] body;
    private String directory;
    private String code;
    private String status;
    private String location;
    private String mimeType;
    public HashMap params;


    public void setMethod(String methodValue) {
        method = methodValue;
    }

    public String getMethod() {
        return method;
    }

    public void setRequestUri(String UriValue) {
        requestUri = UriValue;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setProtocolVersion(String version) {
        protocolVersion = version;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setHeader(String key, String value) {
        headers.put(key, value);
    }

    public String header(String key) {
        return headers.get(key);
    }

    public void setBody(byte[] bodyInBytes) {
        body = bodyInBytes;
    }

    public byte[] getBody() {
        return body;
    }

    public void setDirectory(String dir) {
        directory = dir;
    }

    public String getDirectory() {
        return directory;
    }

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setOk() {
        code = "200";
        status = "OK";
    }

    public void setRedirect() {
        code = "302";
        status = "redirect";
        location  = "http://localhost:5000/";
    }

    public void set404() {
        code = "404";
        status = "not found";
    }

    public void setMimeType(String type) {
        mimeType = type;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setParams(HashMap paramsMap) {
        params = paramsMap;
    }

    public HashMap getParams() {
        return params;
    }
}
