package com.example.interntask.models.exceptions;

public class InvalidTaskId extends RuntimeException{
    public  InvalidTaskId()
    {
        super("Invalid task id");
    }
}
