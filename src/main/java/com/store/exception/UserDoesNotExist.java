package com.store.exception;

import com.store.database.model.User;

public class UserDoesNotExist extends DoesNotExistException {

    private String username="";

    public UserDoesNotExist(String username) {
        super();
        this.username = username;
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
        System.err.println("User " + username + " doesn't exist");
    }
}
