package com.shoppinglist.exception;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException() {
        super("Token expired!");
    }
}
