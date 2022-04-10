package com.nhnacademy.pakingcontrolprogram.exception;

public class AlreadyParkedSpaceException extends IllegalArgumentException {
    public AlreadyParkedSpaceException(String message) {
        super(message);
    }
}
