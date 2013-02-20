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
public class IndexHandler implements RequestHandler {

    public RequestStore handle(RequestStore requestStore) {
        HtmlBodyBuilder htmlBodyBuilder = new HtmlBodyBuilder();
        ArrayList<String> dirContents = readDirectory(requestStore.getDirectory());
        String body = htmlBodyBuilder.createHtmlBodyWithDirectoryContents(dirContents);
        requestStore.setBody(body.getBytes());
        requestStore.setOk();
        return requestStore;
    }

    public ArrayList<String> readDirectory(String dir) {
        ArrayList<String> directoryContents = new ArrayList<String>();
        File directory = new File(dir);
        String[] contents = directory.list();
        for (String name : contents) {
            directoryContents.add(name);
        }
        remove404FromTheDirectoryContents(directoryContents);
        return directoryContents;
    }

    public ArrayList<String> remove404FromTheDirectoryContents(ArrayList<String> contents) {
        if (contents.contains("404.html")) {
            contents.remove("404.html");
        }
        return contents;
    }

//    public byte[] convertBodyToByteArray(String body) {
//        byte[] bodyByteArray = body.getBytes();
//        return bodyByteArray;
//    }
}
