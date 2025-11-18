package exceptions;

/**
 * Exception thrown when a requested item is not found
 */
public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
