package com.angeleah.webserver;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class HtmlBodyBuilderTest {

    public HtmlBodyBuilder htmlBodyBuilder;

    @Before
    public void setUp() {
        htmlBodyBuilder = new HtmlBodyBuilder();
    }

    @Test
    public void CreateHtmlBodyWithDirectoryContents() {
        String[] content = new String[] {"file1", "file2", "image.gif"};
        assertEquals("<!DOCTYPE html>\n<title>Web Server</title>\n<body>\n<a href=\"/file1\">file1</a>\n<a href=\"/file2\">file2</a>\n<a href=\"/image.gif\">image.gif</a>\n</body>",
                htmlBodyBuilder.CreateHtmlBodyWithDirectoryContents(content));
    };

    @Test
    public void CreateHtmlBodyWithParamsContent() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("value1", "123456abcd");
        params.put("value2", "hello");
        assertEquals("<!DOCTYPE html>\n<title>Web Server</title>\n<body>\n<p>value1 = 123456abcd</p>\n<p>value2 = hello</p>\n</body>",
                htmlBodyBuilder.CreateHtmlBodyWithParamsContent(params));

    };

}