package com.shoppinglist.exception;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException() {
        super("Token not found!");
    }

    public TokenNotFoundException(String msg) {
        super(msg);
    }
}
