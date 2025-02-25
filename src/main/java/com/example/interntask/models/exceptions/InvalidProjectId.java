package com.example.interntask.models.exceptions;

public class InvalidProjectId extends RuntimeException{
    public InvalidProjectId()
    {
        super("Invalid project id");
    }
}
