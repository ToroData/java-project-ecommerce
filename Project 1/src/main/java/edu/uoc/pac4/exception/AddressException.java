package edu.uoc.pac4.exception;

/**
 * Exception thrown when there is an error related to an address.
 *
 * @Author: Ricard Santiago Raigada García
 * @Date: 05/20/2023
 * @Version: 1.0
 */

public class AddressException extends Exception {
    public static final String ERR_STREET_NUMBER = "[ERROR] Street number must be greater than zero";
    public static final String ERR_INVALID_ZIPCODE = "[ERROR] The zipcode is not alphanumerical";

    /**
     * Constructs a new AddressException object with no detail message.
     */
    public AddressException() {
        super();
    }

    /**
     * Constructs a new AddressException object with the specified detail message.
     *
     * @param message the detail message.
     */
    public AddressException(String message) {
        super(message);
    }

    /**
     * Returns the detail message string of this exception.
     *
     * @return the detail message string.
     */
    public String getMessage() {
        return super.getMessage();
    }
}
