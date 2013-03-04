package com.angeleah.webserver;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/16/13
 * Time: 12:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormHandler implements RequestHandler{

    public RequestStore handle(RequestStore requestStore) {
        HtmlBodyBuilder builder = new HtmlBodyBuilder();
        StringBuilder body = new StringBuilder();
        if ((requestStore.getParams() != null) && (requestStore.getRequestBody() != null))  {
            body.append(builder.createHtmlBodyWithParamsAndBodyContent(requestStore.getParams(), requestStore.getRequestBody()));
        } else if ((requestStore.getParams() != null) && (requestStore.getRequestBody() == null)) {
            body.append(builder.createHtmlBodyWithParamsContent(requestStore.getParams()));
        } else if ((requestStore.getParams() == null) && (requestStore.getRequestBody() != null)){
            body.append(builder.createHtmlBodyWithRequestContent(requestStore.getRequestBody()));
        }
        requestStore.setBody(body.toString().getBytes());
        requestStore.setContentLength(body.length());
        requestStore.setOk();
        return requestStore;
    }

}




