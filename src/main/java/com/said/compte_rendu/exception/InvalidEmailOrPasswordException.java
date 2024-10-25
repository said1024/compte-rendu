package com.said.compte_rendu.exception;

public class InvalidEmailOrPasswordException extends RuntimeException {
    public InvalidEmailOrPasswordException(String message){
        super(message);
    }
}
