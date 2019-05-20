package com.shoppinglist.exception;

public class NoInvitationFoundException extends RuntimeException {
    public NoInvitationFoundException() {
        super("No invitation found!");
    }

    public NoInvitationFoundException(String msg) {
        super(msg);
    }
}
