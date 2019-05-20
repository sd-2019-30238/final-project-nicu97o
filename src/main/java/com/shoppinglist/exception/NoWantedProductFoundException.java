package com.shoppinglist.exception;

public class NoWantedProductFoundException extends RuntimeException {
    public NoWantedProductFoundException() {
        super("No wanted product found!");
    }

    public NoWantedProductFoundException(String msg) {
        super(msg);
    }
}
