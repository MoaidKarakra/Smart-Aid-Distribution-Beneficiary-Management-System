package exceptions;

/**
 * Exception thrown when attempting to register a duplicate beneficiary or aid item
 */
public class DuplicateRegistrationException extends Exception {
    public DuplicateRegistrationException(String message) {
        super(message);
    }
}
