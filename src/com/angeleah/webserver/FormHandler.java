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
        String content = requestStore.getRequestBody();
        String body = builder.createHtmlBodyWithRequestContent(content);
        requestStore.setBody(body.getBytes());
        requestStore.setOk();
        return requestStore;
    }
}
