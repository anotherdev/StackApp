package com.anotherdev.stackapp.api.stackexchange;

public class StackError {

    String error_id;
    String error_name;
    String error_message;


    public String getId() {
        return error_id;
    }

    public String getName() {
        return error_name;
    }

    public String getMessage() {
        return error_message;
    }
}
