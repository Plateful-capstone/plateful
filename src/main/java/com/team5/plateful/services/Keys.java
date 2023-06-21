package com.team5.plateful.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Keys {

    @Value("${spoonacular.key}")
    private String spoonacularKey;

    @Value("${filestack.key}")
    private String filestackKey;

    public String getSpoonacularKey() {
        return spoonacularKey;
    }

    public void setSpoonacularKey(String spoonacularKey) {
        this.spoonacularKey = spoonacularKey;
    }

    public String getFilestackKey() {
        return filestackKey;
    }

    public void setFilestackKey(String filestackKey) {
        this.filestackKey = filestackKey;
    }
}
