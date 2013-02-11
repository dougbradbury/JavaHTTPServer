package com.angeleah.webserver;
import static com.sun.activation.registries.LogSupport.log;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/5/13
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class BinaryReaderTest {

    public BinaryReader reader;

    @Before
    public void SetUp() {
        reader = new BinaryReader();
    };

    @Test
    public void itShouldBeAbleToReturnTheSizeOfABinaryFile() {
        String file = "binary.dat";
        byte[] data = reader.readBinaryFile(file);
        assertEquals(4, data.length);
        assertEquals(1, data[0]);
    };

    @Test
    public void itShouldBeAbleToReadARegularTextFile() {
        String file = "textTest.txt";
        byte[] data = reader.readBinaryFile(file);
        String fileContents = new String(data);
        assertEquals("Hello", fileContents);
    };
}
