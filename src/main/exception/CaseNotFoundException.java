package main.exception;

/**
 * Custom exception thrown when a case with a specific ID is not found in the database.
 */
public class CaseNotFoundException extends Exception {

    // Default constructor
    public CaseNotFoundException() {
        super("Case not found.");
    }

    // Constructor with custom message
    public CaseNotFoundException(String message) {
        super(message);
    }

    // Constructor with cause
    public CaseNotFoundException(Throwable cause) {
        super(cause);
    }

    // Constructor with custom message and cause
    public CaseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
