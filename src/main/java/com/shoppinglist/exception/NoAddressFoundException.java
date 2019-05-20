package com.shoppinglist.exception;

public class NoAddressFoundException extends RuntimeException {
    public NoAddressFoundException() {
        super("No address found!");
    }

    public NoAddressFoundException(String msg) {
        super(msg);
    }
}
