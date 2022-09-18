package com.shop.helper;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("This use does not exist in the database");
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
