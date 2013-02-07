package com.angeleah.webserver;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 1/30/13
 * Time: 9:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class ResultCode {

    public Map<String, String> get(String type) {
        Map<String, String> result = new HashMap<String, String>();
        if (type.equals("200")){
            result.put("code", "200");
            result.put("status", "OK");
        } else if (type.equals("302")) {
            result.put("code", "302");
            result.put("status", "Found");
            result.put("location", "http://localhost:5000/");
        } else if (type.equals("404")) {
            result.put("code", "404");
            result.put("status", "Not Found");
        }
        return (HashMap) result;
    };

}
