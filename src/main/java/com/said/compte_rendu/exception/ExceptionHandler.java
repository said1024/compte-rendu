package com.said.compte_rendu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler({AccountNotFoundException.class})
    public ResponseEntity<ApiError> accountNotFound(AccountNotFoundException accountNotFoundException){
        return new ResponseEntity<>(new ApiError(400,accountNotFoundException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({EmailAlreadyInUseException.class})
    public ResponseEntity<ApiError> emailAlreadyInUse(EmailAlreadyInUseException emailAlreadyInUseException){
        return new ResponseEntity<>(new ApiError(400,emailAlreadyInUseException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({InvalidEmailOrPasswordException.class})
    public ResponseEntity<ApiError> invalidEmailOrPassword(InvalidEmailOrPasswordException invalidEmailOrPasswordException){
        return new ResponseEntity<>(new ApiError(400,invalidEmailOrPasswordException.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
