package com.angeleah.webserver;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 1/21/13
 * Time: 2:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class HtmlBodyBuilder {

    public HtmlBodyBuilder() {
    }

    public String createHtmlBodyWithDirectoryContents(ArrayList<String> content) {
        return createHtmlHeaderTags()+createDirectoryLinks(content)+"</body>";
    }

    public String createHtmlBodyWithParamsContent(Map<String, String> content) {
        return createHtmlHeaderTags()+ CreateParamsParagraphs(content)+"</body>";
    }

    public String createHtmlHeaderTags() {
        StringBuilder headerTagString = new StringBuilder();
          headerTagString.append("<!DOCTYPE html>\n");
          headerTagString.append("<title>Web Server</title>\n");
          headerTagString.append("<body>\n");
     return  headerTagString.toString();
    }

    public String createDirectoryLinks(ArrayList<String> links) {
        StringBuilder linkString =  new StringBuilder();
        for (String link : links) {
           linkString.append("<a href=\"/");
           linkString.append(link);
           linkString.append("\">");
           linkString.append(link);
           linkString.append("</a>\n");
        }
    return linkString.toString();
    }

    public String CreateParamsParagraphs(Map<String, String> params) {
        StringBuilder paramsString = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            paramsString.append("<p>");
            paramsString.append(entry.getKey());
            paramsString.append(" = ");
            paramsString.append(entry.getValue());
            paramsString.append("</p>\n");
        }
    return paramsString.toString();
    }

}