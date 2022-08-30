package com.errornotes.ErrorNotesApi.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseMessage {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object object) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("message", message);
        map.put("data", object);

        return new ResponseEntity<Object>(map, status);

    }

}
