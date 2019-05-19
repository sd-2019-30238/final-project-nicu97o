package com.shoppinglist.exception;

public class NoClubFoundException extends RuntimeException {
    public NoClubFoundException() {
        super("No club found!");
    }

    public NoClubFoundException(String msg) {
        super(msg);
    }
}
