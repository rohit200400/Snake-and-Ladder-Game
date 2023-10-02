package Exceptions;

public class DuplicatePlayerNameException extends Exception {
    public DuplicatePlayerNameException() {
    }

    public DuplicatePlayerNameException(String message) {
        super(message);
    }
}
