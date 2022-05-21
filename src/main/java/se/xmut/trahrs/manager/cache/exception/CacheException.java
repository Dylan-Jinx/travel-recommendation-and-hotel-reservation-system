package se.xmut.trahrs.manager.cache.exception;

public class CacheException extends RuntimeException {
    public CacheException() {
        super();
    }

    public CacheException(String message) {
        super(message);
    }

    public CacheException(Throwable cause) {
        super(cause);
    }
}
