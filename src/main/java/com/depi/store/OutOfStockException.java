package com.depi.store;

/**
 * Thrown by Item.sell() when there are no available copies left to sell.
 * Unchecked, since running out of stock is an expected business condition
 * the caller (Cart.checkout) is meant to catch and handle gracefully.
 */
public class OutOfStockException extends RuntimeException {

    public OutOfStockException(String message) {
        super(message);
    }
}
