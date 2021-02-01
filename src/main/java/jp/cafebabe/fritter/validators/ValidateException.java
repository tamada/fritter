package jp.cafebabe.fritter.validators;

public class ValidateException extends Exception {
    public ValidateException(Throwable throwable) {
        super(throwable);
    }

    public ValidateException(String message) {
        super(message);
    }
}
