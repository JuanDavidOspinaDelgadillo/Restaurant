package com.group5.Restaurant.exceptions;

public class InternalServerErrorException extends Exception{
    public InternalServerErrorException(String message){
        super(message);
    }
}