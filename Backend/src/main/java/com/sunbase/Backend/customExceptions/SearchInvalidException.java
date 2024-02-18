package com.sunbase.Backend.customExceptions;

public class SearchInvalidException extends RuntimeException{
    public SearchInvalidException(String message) {
        super(message);
    }
}
