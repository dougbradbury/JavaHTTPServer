package com.angeleah.webserver;

import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: angeleah
 * Date: 2/12/13
 * Time: 10:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class FormHandlerTest {

    public FormHandler formHandler;

    @Before
    public void SetUp() {
        formHandler = new FormHandler();
    }

    @Test
    public itSHouldBeAbleToHandleAFormCorrectly() {
        RequestStore requestStore = new RequestStore();
        requestStore.setParams
    }
}
