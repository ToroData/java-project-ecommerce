package edu.uoc.pac4.exception;

public class ProductException extends Exception {
    public static final String ERR_EMPTY_NAME = "[ERROR] Product name cannot be empty or null";
    public static final String ERR_INVALID_PRICE = "[ERROR] Price must be greater than zero";

    public ProductException() {
        super();
    }

    public ProductException(String message) {
        super(message);
    }

    public String getMessage() {
        return super.getMessage();
    }
}
