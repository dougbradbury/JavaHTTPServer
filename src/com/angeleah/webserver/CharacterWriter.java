//package com.angeleah.webserver;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//
///**
// * Created with IntelliJ IDEA.
// * User: angeleah
// * Date: 2/8/13
// * Time: 10:17 AM
// * To change this template use File | Settings | File Templates.
// */
//public class CharacterWriter {
//
//    public void writeCharacterData(String fileName, String data) {
//        FileWriter output = null;
//        try {
//            output = new FileWriter(fileName);
//            BufferedWriter writer = new BufferedWriter(output);
//            writer.write(data);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } finally {
//            if (output != null) {
//                try {
//                    output.close();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//    }
//}
