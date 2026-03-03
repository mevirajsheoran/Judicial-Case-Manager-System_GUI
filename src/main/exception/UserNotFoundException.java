package main.exception;

/**
 * Custom exception thrown when a user is not found in the system.
 */
public class UserNotFoundException extends Exception {

    // Default constructor
    public UserNotFoundException() {
        super("User not found.");
    }

    // Constructor with custom message
    public UserNotFoundException(String message) {
        super(message);
    }

    // Constructor with cause
    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    // Constructor with custom message and cause
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
