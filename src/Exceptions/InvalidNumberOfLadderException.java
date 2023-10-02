package Exceptions;

public class InvalidNumberOfLadderException extends Exception {
    public InvalidNumberOfLadderException() {
    }

    public InvalidNumberOfLadderException(String message) {
        super(message);
    }

    public InvalidNumberOfLadderException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidNumberOfLadderException(Throwable cause) {
        super(cause);
    }

    public InvalidNumberOfLadderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
