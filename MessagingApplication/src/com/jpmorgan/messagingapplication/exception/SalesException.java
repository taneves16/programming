package com.jpmorgan.messagingapplication.exception;

public class SalesException extends Exception{

    
    private final static long serialVersionUID = 1L;

    public SalesException() {
        super();
    }

    public SalesException(String message) {
        super(message);
    }

    public SalesException(String message, Throwable e) {
        super(message, e);
    }

}