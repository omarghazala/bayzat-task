package com.bayzdelivery.generator;

import java.util.UUID;

public class StringGenerator {

    public static String generateUniqueString(){
        return "REG-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
