package com.jpmorgan.messagingapplication.exception;

public class MessageTypeException extends Exception{

	private final static long serialVersionUID = -5747106992680571497L;

    public MessageTypeException() {
        super();
    }

    public MessageTypeException(String message) {
        super(message);
    }

    public MessageTypeException(String message, Throwable e) {
        super(message, e);
    }

}
