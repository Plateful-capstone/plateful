//package com.team5.plateful.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class KeyContoller {
//
//    @Autowired
//    private Keys keys;
//
//    @GetMapping(value="/keys.js", produces = "application/javascript")
//    public String getKeys() {
//        return String.format("""
//                 const TEST_KEY = "%s";
//                 const FILESTACK_KEY = "%s";
//                 """,);
//    }
//}
