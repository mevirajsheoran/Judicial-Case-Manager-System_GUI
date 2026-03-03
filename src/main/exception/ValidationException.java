package main.exception;

/**
 * Custom exception thrown when validation of data fails.
 */
public class ValidationException extends Exception {

    // Default constructor
    public ValidationException() {
        super("Validation failed.");
    }

    // Constructor with custom message
    public ValidationException(String message) {
        super(message);
    }

    // Constructor with cause
    public ValidationException(Throwable cause) {
        super(cause);
    }

    // Constructor with custom message and cause
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
