package Exceptions;

public class InvalidDiceException extends Exception {
    public InvalidDiceException(String message) {
        super(message);
    }

    public InvalidDiceException() {
    }
}
