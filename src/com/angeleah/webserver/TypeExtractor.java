package com.angeleah.webserver;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 1/24/13
 * Time: 10:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class TypeExtractor {

    public String extractType(String path){
        String mimeType;
        String pathExtension;
        pathExtension = separateExtensionFromPath(path);
        if (pathExtension.equals(".txt")) {
            mimeType = "text/html";
        } else if (pathExtension.equals(".gif")) {
            mimeType = "image/gif";
        } else if (pathExtension.equals(".jpeg")) {
            mimeType = "image/jpeg";
        }else if (pathExtension.equals(".jpe")) {
            mimeType = "image/jpeg";
        }else if (pathExtension.equals(".jpg")) {
            mimeType = "image/jpeg" ;
        }else if (pathExtension.equals("Jpeg")) {
            mimeType = "image/jpeg";
        }else if (pathExtension.equals(".png")) {
            mimeType = "image/png";
        } else {
            mimeType = "text/html";
        }
        return mimeType;
    }


    public String separateExtensionFromPath (String path) {
        String[] parts = path.split("\\.");
        String extension;
        if ( parts.length>1) {
            extension = parts[1];
        } else {
            extension = path;
        }
        return extension;
    };
}
