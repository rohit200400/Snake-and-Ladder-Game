package Exceptions;

public class InvalidNumberOfSnakesException extends Exception {
    public InvalidNumberOfSnakesException() {
    }

    public InvalidNumberOfSnakesException(String message) {
        super(message);
    }
}
