package com.shoppinglist.exception;

public class NoProductConstraintFoundException extends RuntimeException {
    public NoProductConstraintFoundException() {
        super("No product constraint found!");
    }

    public NoProductConstraintFoundException(String msg) {
        super(msg);
    }
}
