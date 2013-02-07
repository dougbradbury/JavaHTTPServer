package com.angeleah.webserver;

import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 1/30/13
 * Time: 9:04 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.HashMap;
import java.util.Map;


public class ResultCodeTest {

    public ResultCode resultCode;

    @Before
    public void setUp() {
        resultCode = new ResultCode();
    };

    @Test
    public void okShouldReturn200Ok() {
        String statusIndicator = new String("200");
        Map<String, String> pairs = new HashMap<String, String>();
        pairs.put("code", "200");
        pairs.put("status", "OK");
        assertEquals( pairs, resultCode.get(statusIndicator));
    };

    @Test
    public void notFoundShouldReturn404NotFound() {
        String statusIndicator = new String("404");
        Map<String, String> pairs = new HashMap<String, String>();
        pairs.put("code", "404");
        pairs.put("status", "Not Found");
        assertEquals( pairs, resultCode.get(statusIndicator));
    };

    @Test
    public void redirectShouldReturn302Found() {
        String statusIndicator = new String("302");
        Map<String, String> pairs = new HashMap<String, String>();
        pairs.put("code", "302");
        pairs.put("status", "Found");
        pairs.put("location", "http://localhost:5000/");
        assertEquals( pairs, resultCode.get(statusIndicator));
    };


}