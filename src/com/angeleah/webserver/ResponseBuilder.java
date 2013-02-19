package com.angeleah.webserver;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/18/13
 * Time: 11:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseBuilder {

    public RequestStore requestStore;
    public Date date;

    public ResponseBuilder(RequestStore requestStore, Date date) {
        this.requestStore = requestStore;
        this.date = date;
    }

    public String formatDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
        String formattedDate = (dateFormat.format(date));
        return formattedDate;
    }

//    public byte[] buildResponse(Date date) {
//        byte[] headerResponse = buildResponseHeaders();
//    if the body is not null, get the
//            byte[] bodyResponse = buildResponseBody();
//        byte[] completeResponse = new byte[headerResponse.length + bodyResponse.length];
//        return completeResponse;
//    }
//
    public byte[] buildResponseHeaders(Date date){
        StringBuilder headerResponse = new StringBuilder();
        headerResponse.append(buildInitialResponseLine());
        headerResponse.append(buildDateResponseLine(date));
        if (requestStore.getCode().equals("302")) {
            headerResponse.append(buildLocationResponseLine());
        } else if (requestStore.getRequestContentLength() != null) {
            headerResponse.append(buildContentTypeResponseLine());
            headerResponse.append(buildContentLengthResponseLine());
        }
        headerResponse.append("\r\n");
        return (headerResponse.toString().getBytes());
    }

    public byte[] buildResponseBody() {
      return requestStore.getBody();
    }

    public String buildInitialResponseLine() {
        StringBuilder initialResponseLine = new StringBuilder();
        initialResponseLine.append(requestStore.getProtocolVersion());
        initialResponseLine.append(" ");
        initialResponseLine.append(requestStore.getCode());
        initialResponseLine.append(" ");
        initialResponseLine.append(requestStore.getStatus());
        initialResponseLine.append("\r\n");
        return initialResponseLine.toString();
    }

    public String buildLine(String key, String value) {
        StringBuilder line = new StringBuilder();
        line.append(key);
        line.append(":");
        line.append(" ");
        line.append(value);
        line.append("\r\n");
        return line.toString();
    }

    public String buildDateResponseLine(Date date){
       return buildLine("Date", formatDate(date));
    }

    public String buildContentTypeResponseLine() {
        return buildLine("Content-Type", requestStore.getMimeType());
    }

    public String buildContentLengthResponseLine(){
        return buildLine("Content-Length", requestStore.getContentLength().toString());
    }

    public String buildLocationResponseLine() {
        return buildLine("Location", requestStore.getLocation());
    }

//    public byte[] buildBodyResponse(){
//
//    }


}
