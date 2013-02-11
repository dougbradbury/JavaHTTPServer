//package com.angeleah.webserver;
//import static com.sun.activation.registries.LogSupport.log;
//import static junit.framework.Assert.assertEquals;
//import static junit.framework.Assert.assertNotNull;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.File;
//
///**
// * Created with IntelliJ IDEA.
// * User: angeleah
// * Date: 2/8/13
// * Time: 10:17 AM
// * To change this template use File | Settings | File Templates.
// */
//public class CharacterWriterTest {
//
//    public CharacterWriter  characterWriter;
//
//    @Before
//    public void setup() {
//        characterWriter = new CharacterWriter();
//    };
//
//    @Test
//    public void itShouldBeAbleToWriteCharacterDataToAFile(){
//          String file = "test.html";
//          String data = "<div id=\"logo\" >\n" +
//                  "\t\t<a href=\"http://angeleah.com/blog/index.html\">Angeleah.com</a>\n" +
//                  "\t</div>";
//          characterWriter.writeCharacterData(file, data);
//          assertEquals(data.length(), new File(file).length());
//    }
//
//}
