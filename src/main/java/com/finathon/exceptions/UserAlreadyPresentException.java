package com.finathon.exceptions;

public class UserAlreadyPresentException extends Exception{
    public UserAlreadyPresentException(String message){
        super(message);
    }
}
