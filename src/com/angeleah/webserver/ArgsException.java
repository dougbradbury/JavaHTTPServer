package com.angeleah.webserver;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/27/13
 * Time: 2:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArgsException extends Exception {

    public ArgsException() {
        super("You must provide a port and a directory argument. ex: java -jar pathToJarfile/jarfile.jar -p 5000 -d directoryPath");
    }
}
