package com.shoppinglist.exception;

public class UserAlreadyAMemberException extends RuntimeException {
    public UserAlreadyAMemberException() {
        super("User is already a member!");
    }

    public UserAlreadyAMemberException(String msg) {
        super(msg);
    }
}
