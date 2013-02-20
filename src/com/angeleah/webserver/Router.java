package com.angeleah.webserver;

import java.io.BufferedReader;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/16/13
 * Time: 12:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class Router {

    public RequestStore requestStore;

    public Router(RequestStore requestStore) {
        this.requestStore = requestStore;
    }
//    public void register(String path, RequestHandler handler) {
//
//    }

    public void routeRequest() {
        String path = requestStore.getRequestUri();
        if (path.equals("/")) {
            IndexHandler indexHandler = new IndexHandler();
            indexHandler.handle(requestStore);
        } else if (path.contains("?")) {
            ParamsHandler paramsHandler = new ParamsHandler();
            paramsHandler.handle(requestStore);
        } else if (pathIsAFile(path)) {
            FileHandler fileHandler = new FileHandler();
            fileHandler.handle(requestStore);
        } else if (path.equals("/form")) {
             FormHandler formHandler = new FormHandler();
             formHandler.handle(requestStore);
        } else {
            NotFoundHandler notFoundHandler = new NotFoundHandler();
            notFoundHandler.handle(requestStore);
        }
    }

    public boolean pathIsAFile(String path) {
        if ((path.equals("file1")) || (path.equals("file2")) || (path.contains("txt")) | (path.contains("html")) || (path.contains("jpeg")) || (path.contains("png")) || (path.contains("gif"))) {
            return true;
        } else {
            return false;
        }
    }
}
