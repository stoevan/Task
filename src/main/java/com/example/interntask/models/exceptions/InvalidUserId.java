package com.example.interntask.models.exceptions;

public class InvalidUserId extends RuntimeException {
    public InvalidUserId()
    {
        super("Invalid user");
    }
}
