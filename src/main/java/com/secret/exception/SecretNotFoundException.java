package com.secret.exception;

@SuppressWarnings("serial")
public class SecretNotFoundException extends RuntimeException {

    public SecretNotFoundException(String message) {
        super(message);
    }
}