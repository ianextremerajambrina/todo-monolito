package com.ian.todo.exception;

public class ItemNotFound extends Exception {

    public final String message;

    public ItemNotFound(String message) {
        this.message = message;
    }

}
