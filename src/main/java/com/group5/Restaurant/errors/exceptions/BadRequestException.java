package com.group5.Restaurant.errors.exceptions;

public class BadRequestException extends Exception{
    public BadRequestException(String message){
        super(message);
    }
}