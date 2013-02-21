package com.angeleah.webserver;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/16/13
 * Time: 12:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class Router {

    public Router() {
    }
//    public void register(String path, RequestHandler handler) {
//
//    }

    public void routeRequest(RequestStore requestStore) {
        String path = requestStore.getRequestUri();
        RequestHandler handler = getRequestHandlerForPath(path);
        handler.handle(requestStore);
    }

    private RequestHandler getRequestHandlerForPath(String path) {
        if (path.equals("/")) {
            return new IndexHandler();
        } else if (path.contains("?")) {
            return new ParamsHandler();
        } else if (pathIsAFile(path)) {
            return new FileHandler();
        } else if (path.equals("/form")) {
            return new FormHandler();
        } else {
            return new NotFoundHandler();
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
