package Exceptions;

/**
 * Exception to be thrown when an action is being done on a keyword that does not exist in the memory
 */

public class KeywordNotFoundException extends RuntimeException {
    public KeywordNotFoundException() {
        super();
    }

    public KeywordNotFoundException(String message) {
        super(message);
    }
}
