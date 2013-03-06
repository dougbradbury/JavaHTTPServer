package com.angeleah.webserver;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 1/31/13
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */

//Can the public interface be reduced?  Move more methods to private and test through the public interface
public class RequestStore {
    private String method = null;
    private String requestUri = null;
    private String protocolVersion = null;
    private HashMap<String, String> headers = new HashMap<String, String>();
    private byte[] body = null;
    private String directory = null;
    private String code = null;
    private String status = null;
    private String location = null;
    private String mimeType = null;
    public Integer requestContentLength = null; //make private - encapsulation
    public Integer contentLength = null; //make private - encapsulation
    public HashMap<String,String> params = new HashMap<String,String>(); //make private - encapsulation
    private String requestBody = null;


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

    public String getHeaders(String key) {
        return headers.get(key);
    }

    public HashMap<String,String> getAllHeaders(){
        return headers;
    }

    public void setRequestContentLength(int reqContLength) {
        requestContentLength =  reqContLength;
    }

    public Integer getRequestContentLength() {
        return requestContentLength;
    }

    public void setContentLength(int size) {
        contentLength =  size;
    }

    public Integer getContentLength() {
        return contentLength;
    }

    public void setBody(byte[] bodyInBytes) {
        body = bodyInBytes;
    }

    public byte[] getBody() {
        return body;
    }

    public void setDirectory(String dir) { //move elsewhere - ISP / cohesion
        directory = dir;
    }

    public String getDirectory() { //move elsewhere - ISP / cohesion
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
        location  = "http://localhost:5000/"; //hard coded host and port is bad for business, set location based on request location
    }

    public void set404() {
        code = "404";
        status = "not found";
    }

    public void set405() {
        code = "405";
        status = "Method Not Allowed";
    }

    public void setMimeType(String type) {
        mimeType = type;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setParams(HashMap<String,String> paramsMap) {
        params = paramsMap;
    }

    public HashMap getParams() {
        return params;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getRequestBody() {
        return requestBody;
    }
}
