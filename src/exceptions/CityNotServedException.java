package exceptions;

/**
 * Exception thrown when attempting to register a beneficiary from a city not in the served list.
 */
public class CityNotServedException extends Exception {
    
    /**
     * Constructs a new CityNotServedException with the specified message.
     * 
     * @param message The detail message
     */
    public CityNotServedException(String message) {
        super(message);
    }
}
