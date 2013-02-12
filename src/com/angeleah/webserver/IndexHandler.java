package com.angeleah.webserver;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

/**
* Created with IntelliJ IDEA.
* User: angeleah
* Date: 1/22/13
* Time: 11:54 AM
* To change this template use File | Settings | File Templates.
*/
public class IndexHandler {

    public RequestStore indexHandler(RequestStore requestStore) {
//        RequestStore requestStore = requestStore;
        return requestStore;
    }


    public ArrayList<String> readDirectory(String dir) {
        ArrayList<String> directoryContents = new ArrayList<String>();
        File directory = new File(dir);
        String[] contents = directory.list();
        for (String name : contents) {
            directoryContents.add(name);
        }
        return directoryContents;
    }

//    public String buildIndexContent(ArrayList<String> directoryContents) {
//        HtmlBodyBuilder bodyBuilder = new HtmlBodyBuilder();
//    }
}
