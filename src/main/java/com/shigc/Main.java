package com.shigc;

import com.shigc.function.Manager;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws IOException {
        System.getProperties().put("org.apache.commons.logging.simplelog.defaultlog","fatal");
        Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        Logger.getLogger("org.apache.http").setLevel(Level.OFF);
        Manager manager = new Manager();
        manager.start();
    }
}
