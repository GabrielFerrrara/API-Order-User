package com.serasa.order.domain.exception;

public class OrderInvalidException extends RuntimeException {
    public OrderInvalidException(String message) {
        super(message);
    }
}