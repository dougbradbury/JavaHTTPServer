package com.angeleah.webserver;

import java.util.ArrayList;
import java.util.HashMap;
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
        return createHtmlHeaderTags() + createDirectoryLinks(content) + createClosingTags();
    }

    public String createHtmlBodyWithParamsContent(Map<String, String> content) {
        return createHtmlHeaderTags() + createParagraphsToContainParams(content) + createClosingTags();
    }

    public String createHtmlBodyWithRequestContent(String content) {
        return createHtmlHeaderTags() + createParagraphToContainRequestBody(content) + createClosingTags();
    }

    public String createHtmlNotFoundBody() {
        return createHtmlHeaderTags() + buildNotFound() + createClosingTags();
    }

    public String createHtmlBodyWithParamsAndBodyContent(HashMap paramsContent, String bodyContent){
        return createHtmlHeaderTags() + createParagraphsToContainParams(paramsContent) + createParagraphToContainRequestBody(bodyContent) + createClosingTags();
    }



    public String createHtmlHeaderTags() {
        StringBuilder headerTagString = new StringBuilder();
          headerTagString.append("<!DOCTYPE html>\n");
          headerTagString.append("<title>Web Server</title>\n");
          headerTagString.append("<body>\n");
     return  headerTagString.toString();
    }

    public String createDirectoryLinks(ArrayList<String> links) {
        StringBuilder linkTagString =  new StringBuilder();
        for (String link : links) {
           linkTagString.append("<a href=\"");
           linkTagString.append("/");
           linkTagString.append(link);
           linkTagString.append("\">");
           linkTagString.append(link);
           linkTagString.append("</a>\n");
        }
    return linkTagString.toString();
    }

    public String createParagraphsToContainParams(Map<String, String> params) {
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

    public String createParagraphToContainRequestBody(String requestContent) {
        StringBuilder bodyContent = new StringBuilder();
            bodyContent.append("<p>");
            bodyContent.append(requestContent);
            bodyContent.append("</p>\n");
        return bodyContent.toString();
    }

    public String buildNotFound() {
        StringBuilder bodyContent = new StringBuilder();
        bodyContent.append("<h1>");
        bodyContent.append("Not Found");
        bodyContent.append("</h1>\n");
        return bodyContent.toString();
    }

    public String createClosingTags() {
        StringBuilder closingTags = new StringBuilder();
        closingTags.append("</body>\n");
        closingTags.append("</html>");
        return closingTags.toString();
    }
}