package com.wallissoftware.alice.client;

import com.google.gwt.junit.client.GWTTestCase;

public class SandboxGwtTest extends GWTTestCase {
    @Override
    public String getModuleName() {
        return "com.wallissoftware.alice.Alice";
    }

    public void testSandbox() {
        assertTrue(true);
    }
}