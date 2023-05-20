package edu.uoc.pac4;

/**
 * Exception class for user-related errors.
 *
 * UserException class extends the Exception to customize the class and provides constants
 *
 * @Author: Ricard Santiago Raigada García
 * @Date: 05/20/2023
 * @Version: 1.0
 */
public class UserException extends Exception {
    /**
     * Error message indicating that the email is not in a valid format.
     */
    public static final String ERR_INVALID_EMAIL = "[ERROR] The email is not in a valid format";
    /**
     * Error message indicating that the user must be at least 16 years old.
     */
    public static final String ERR_MIN_AGE = "[ERROR] The user must be at least 16 years old";
    /**
     * Error message indicating that the user cannot be subscribed as premium if he/she is in debt.
     */
    public static final String ERR_PREMIUM_SUBSCRIPTION = "[ERROR] The user cannot be subscribed as premium if he/she is in debt";

    /**
     * Error message indicating that the added debt value must be greater than zero.
     */
    public static final String ERR_ADD_INVALID_DEBT_VALUE = "[ERROR] The added debt value must be greater than zero";

    /**
     * Constructs a new UserException object with no specific message.
     */
    public UserException() {
        super();
    }

    /**
     * Constructs a new UserException object with the specified message.
     *
     * @param message the detail message.
     */
    public UserException(String message) {
        super(message);
    }

    /**
     * Returns the detail message string of this exception.
     *
     * @return the detail message string of this exception.
     */
    public String getMessage() {
        return super.getMessage();
    }
}
