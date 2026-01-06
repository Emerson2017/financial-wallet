package com.finaya.wallete.infrastructure.web.exception;

import com.finaya.wallete.domain.exception.EntityNotFoundException;
import com.finaya.wallete.domain.exception.InsufficientBalanceException;
import com.finaya.wallete.domain.exception.InvalidPixTransactionStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ApiErrorResponse> handleInsufficientBalance(
            InsufficientBalanceException ex) {

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(new ApiErrorResponse(
                        "INSUFFICIENT_BALANCE",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(InvalidPixTransactionStatusException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidPixTransactionStatus(
            InvalidPixTransactionStatusException ex) {

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(new ApiErrorResponse(
                        "INVALID_PIX_TRANSACTION_STATUS",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleEntityNotFound(
            EntityNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiErrorResponse(
                        "ENTITY_NOT_FOUND",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneralError(Exception ex) {
        logger.error("Internal Server Error: ", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiErrorResponse("INTERNAL_SERVER_ERROR",
                        "Internal Server Error."));
    }
}
