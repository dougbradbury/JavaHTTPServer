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

public class TypeExtractorTest {



    public TypeExtractor typeExtractor;


    @Before
    public void setUp() {
        typeExtractor = new TypeExtractor();
    };

    @Test
     public void separateExtensionFromPathIfExtensionExists(){
        String path = new String ("image.gif");
        assertEquals("gif", typeExtractor.separateExtensionFromPath(path));
    };

    @Test
    public void returnTheFileNameIfThereIsNoExtension(){
        String path = new String ("file1");
        assertEquals("file1", typeExtractor.separateExtensionFromPath(path));
    };

    @Test
    public void extractTheTypeIfThereIsOne(){

    };
}