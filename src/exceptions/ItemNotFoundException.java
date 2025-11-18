package exceptions;

/**
 * Exception thrown when an aid item is not found in the system.
 */
public class ItemNotFoundException extends Exception {
    
    /**
     * Constructs a new ItemNotFoundException with the specified message.
     * 
     * @param message The detail message
     */
    public ItemNotFoundException(String message) {
        super(message);
    }
}
