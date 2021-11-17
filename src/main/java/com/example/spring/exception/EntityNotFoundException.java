package com.example.spring.exception;

import lombok.Getter;

public class EntityNotFoundException  extends RuntimeException{

    @Getter
    private ErrorCodes errorCode;
    public EntityNotFoundException(String message){
        super(message);
    }
    public EntityNotFoundException(String message,Throwable cause){
        super(message,cause);
    }
    public EntityNotFoundException(String message,Throwable cause,ErrorCodes errorCodes){
        super(message,cause);
        this.errorCode =errorCodes;
    }
    public EntityNotFoundException(String message,ErrorCodes errorCodes){
        super(message);
        this.errorCode =errorCodes;

    }
}
