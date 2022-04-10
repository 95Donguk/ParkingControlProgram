package com.nhnacademy.pakingcontrolprogram.exception;

public class DontHaveMoneyException extends RuntimeException {
    public DontHaveMoneyException(String message) {
        super(message);
    }
}
