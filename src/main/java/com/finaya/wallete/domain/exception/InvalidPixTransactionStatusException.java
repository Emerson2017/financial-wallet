package com.finaya.wallete.domain.exception;

public class InvalidPixTransactionStatusException extends RuntimeException {
    public InvalidPixTransactionStatusException(String message) {
        super(message);
    }
}
