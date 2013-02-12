package com.angeleah.webserver;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.System.out;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/5/13
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class BinaryReader {

    byte[] read(String directory, String fileName) {
        File file = new File(directory.concat(fileName));
        byte[] result = null;
        try {
            InputStream input = new BufferedInputStream(new FileInputStream(file));
            result = readAndClose(input);
        }
        catch (FileNotFoundException ex) {
            log(ex);
        }
        return  result;
    }

    byte[] readAndClose(InputStream aInput){
        byte[] bucket = new byte[32*1024];
        ByteArrayOutputStream result = null;
        try  {
            try {
                result = new ByteArrayOutputStream(bucket.length);
                int bytesRead = 0;
                while(bytesRead != -1){
                    bytesRead = aInput.read(bucket);
                    if(bytesRead > 0){
                        result.write(bucket, 0, bytesRead);
                    }
                }
            }
            finally {
                aInput.close();
            }
        }
        catch (IOException ex){
            log(ex);
        }
        return result.toByteArray();
    };

    private static void log(Object aThing){
        System.out.println(String.valueOf(aThing));
    };
}
