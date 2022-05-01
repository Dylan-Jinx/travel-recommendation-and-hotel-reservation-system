package se.xmut.trahrs.exception;

public class CreateTempFileFailedException extends Exception {
    public CreateTempFileFailedException() {}
    public CreateTempFileFailedException(String message) {
        super(message);
    }
}
