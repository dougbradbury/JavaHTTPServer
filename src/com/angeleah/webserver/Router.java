package com.angeleah.webserver;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/16/13
 * Time: 12:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class Router {

    public HashMap<String, Object> routes = new HashMap<String, Object>();

    public Router() {
    }

    public void register(String path, RequestHandler handler) {
        routes.put(path, handler);
    }

    public void routeRequest(RequestStore requestStore) {
        String path = requestStore.getRequestUri();
        RequestHandler handler = getRequestHandlerForPath(requestStore.getDirectory(), path);
        handler.handle(requestStore);
    }

    private RequestHandler getRequestHandlerForPath(String directory, String path) {
        if (routes.containsKey(path)) {
            return (RequestHandler) routes.get(path);
        } else if (pathIsAFile(directory, path)) {
            return new FileHandler();
        } else {
            return new NotFoundHandler();
        }
    }

    public boolean pathIsAFile(String directory , String path) {
        if (directory == null) {
            return false;
        }
        ArrayList<String> directoryContents =  readDirectory(directory);
        if (directoryContents.contains(path)) {
            return true;
        }
        return false;
    }

    public ArrayList<String> readDirectory(String dir) {
        ArrayList<String> directoryContents = new ArrayList<String>();
        File directory = new File(dir);
        String[] contents = directory.list();
        for (String name : contents) {
            directoryContents.add("/" + name);
        }
        return directoryContents;
    }




//        if (path.equals("/")) {
//            return new IndexHandler();
//        } else if (path.equals("/some-script-url?variable_1=123459876&variable_2=some_value")) {
//            return new ParamsHandler();
//        } else if (path.equals("/form?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F")) {
//            return new ParamsHandler();
//        } else if (path.equals("/redirect")) {
//            return new RedirectHandler();
//
//        } else if (path.equals("/form")) {
//            return new FormHandler();
//        } else {
//            return new NotFoundHandler();
//        }
//    }


}

