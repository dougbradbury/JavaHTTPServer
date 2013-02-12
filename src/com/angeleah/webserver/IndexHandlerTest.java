package com.angeleah.webserver;
import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
* Created with IntelliJ IDEA.
* User: angeleah
* Date: 2/8/13
* Time: 3:03 PM
* To change this template use File | Settings | File Templates.
*/
public class IndexHandlerTest {

    public IndexHandler indexHandler;

    @Before
    public void SetUp() {
        indexHandler = new IndexHandler();
    };

    @Test
    public void IndexHandlerShouldBeAbleToReadADirectory() {
        String directory = "com/angeleah/webserver/TestDirectory";
        ArrayList<String> dirContents = new ArrayList<String>();
        dirContents.add("anotherTestFile.html");
        dirContents.add("awesomePage.html");
        dirContents.add("file1");
        dirContents.add("helloWorld.html");
        dirContents.add("imageTest.jpeg");
        assertEquals(dirContents, indexHandler.readDirectory(directory));
    };

//    @Test
//    public void itShouldReturnTheProperHtmlMarkup() {
//        String directory = "com/angeleah/webserver/TestDirectory";
//        ArrayList<String> dirContents = new ArrayList<String>();
//        dirContents.add("anotherTestFile.html");
//        dirContents.add("awesomePage.html");
//        dirContents.add("file1");
//        dirContents.add("helloWorld.html");
//        dirContents.add("imageTest.jpeg");
//        assertEquals(html, indexHandler.buildIndexContent());
//    };
}
