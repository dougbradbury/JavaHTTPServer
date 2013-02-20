package com.angeleah.webserver;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 1/24/13
 * Time: 10:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class MimeTypeExtractor {

    public String extractType(String pathExtension){
        String mimeType;
        if (pathExtension.equals(".txt")) {
            mimeType = "text/html";
        } else if (pathExtension.contains(".gif")) {
            mimeType = "image/gif";
        } else if (pathExtension.contains(".jpeg")) {
            mimeType = "image/jpeg";
        }else if (pathExtension.contains(".jpe")) {
            mimeType = "image/jpeg";
        }else if (pathExtension.contains(".jpg")) {
            mimeType = "image/jpeg" ;
        }else if (pathExtension.contains("Jpeg")) {
            mimeType = "image/jpeg";
        }else if (pathExtension.contains(".png")) {
            mimeType = "image/png";
        } else {
            mimeType = "text/html";
        }
        return mimeType;
    }
}
