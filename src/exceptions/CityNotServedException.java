package exceptions;

/**
 * Exception thrown when a city is not in the served areas
 */
public class CityNotServedException extends Exception {
    public CityNotServedException(String message) {
        super(message);
    }
}
