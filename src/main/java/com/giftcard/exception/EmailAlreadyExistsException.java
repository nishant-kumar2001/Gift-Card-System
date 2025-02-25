// File: src/main/java/com/giftcard/exceptions/EmailAlreadyExistsException.java
package com.giftcard.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}