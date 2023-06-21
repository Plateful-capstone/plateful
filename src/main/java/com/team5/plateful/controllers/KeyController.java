package com.team5.plateful.controllers;

import com.team5.plateful.services.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeyController {

    @Autowired
    private Keys keys;

    @GetMapping(value="/keys.js", produces = "application/javascript")
    public String getKeys() {
        return String.format("""
                const keys = {
                    filestackAPIKey : "%s",
                    spoonacularAPIKey : "%s"
                }
                 """, keys.getFilestackKey(), keys.getSpoonacularKey()
        );
    }
}
