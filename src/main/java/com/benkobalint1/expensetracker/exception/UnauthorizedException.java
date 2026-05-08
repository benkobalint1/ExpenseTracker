package com.benkobalint1.expensetracker.exception;

/**
 * @author benkobalint1
 **/
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
