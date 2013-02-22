package com.angeleah.webserver;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/12/13
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotFoundHandler implements RequestHandler{

    public RequestStore handle(RequestStore requestStore) {
          HtmlBodyBuilder htmlBodyBuilder = new HtmlBodyBuilder();
          String body = htmlBodyBuilder.createHtmlNotFoundBody();
          requestStore.setBody(body.getBytes());
          requestStore.setContentLength(body.length());
          requestStore.set404();
          return requestStore;
    }
}

//          BinaryReader reader = new BinaryReader();
//          byte[] body = reader.read(requestStore.getDirectory(), "/404.html");
