package com.angeleah.webserver;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/12/13
 * Time: 10:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class ParamsHandler implements RequestHandler {

    public RequestStore handle(RequestStore requestStore) {
        HtmlBodyBuilder builder = new HtmlBodyBuilder();
        HashMap<String,String> params = requestStore.getParams();
        String body = builder.createHtmlBodyWithParamsContent(params);
        requestStore.setBody(body.getBytes());
        requestStore.setContentLength(body.length());
        requestStore.setOk();
        return requestStore;
    }

}
