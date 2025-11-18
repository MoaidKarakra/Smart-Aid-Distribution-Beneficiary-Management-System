package exceptions;

/**
 * Exception thrown when attempting to register a beneficiary with a duplicate ID.
 */
public class DuplicateRegistrationException extends Exception {
    
    /**
     * Constructs a new DuplicateRegistrationException with the specified message.
     * 
     * @param message The detail message
     */
    public DuplicateRegistrationException(String message) {
        super(message);
    }
}
