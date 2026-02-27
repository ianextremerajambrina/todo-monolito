package com.ian.todo.exception;

public class InsertItemError extends RuntimeException {
    public InsertItemError(String message) {
        super(message);
    }
}
