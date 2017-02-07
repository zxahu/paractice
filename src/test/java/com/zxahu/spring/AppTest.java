package com.zxahu.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest extends SpringTestNGSupport {

    @Autowired
    private App app;

    @Test
    public void test() {
        app.doSth();
    }
}
