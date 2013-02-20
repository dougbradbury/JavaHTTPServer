package com.angeleah.webserver;
import org.junit.Before;
import org.junit.Test;


/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 1/24/13
 * Time: 12:05 PM
 * To change this template use File | Settings | File Templates.
 */

import static junit.framework.Assert.assertEquals;

public class MimeTypeExtractorTest {

    public MimeTypeExtractor mimeTypeExtractor;

    @Before
    public void setUp() {
        mimeTypeExtractor = new MimeTypeExtractor();
    }

    @Test
     public void separateExtensionFromPathIfExtensionExists(){
        assertEquals("image/gif", mimeTypeExtractor.extractType("image.gif"));
    }

    @Test
    public void returnTheFileNameIfThereIsNoExtension(){
        assertEquals("text/html", mimeTypeExtractor.extractType("file1"));
    }
}