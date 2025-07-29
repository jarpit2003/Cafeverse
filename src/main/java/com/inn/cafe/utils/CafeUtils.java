package com.inn.cafe.utils;

import org.springframework.http.HttpStatus;         // ✅ Required import
import org.springframework.http.ResponseEntity;

public class CafeUtils {

    private CafeUtils() {
        // Utility class constructor
    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        String json = "{\"message\":\"S: " + responseMessage + "\"}";
        return new ResponseEntity<>(json, httpStatus);  // ✅ Correct JSON and response
    }
}
