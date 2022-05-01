package se.xmut.trahrs.exception;

public class InvalidArgException extends Exception{
    public InvalidArgException() {}
    public InvalidArgException(String message) {
        super(message);
    }
}
