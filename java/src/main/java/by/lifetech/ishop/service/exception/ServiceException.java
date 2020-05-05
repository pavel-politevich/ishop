package by.lifetech.ishop.service.exception;

public class ServiceException extends Exception {
    public ServiceException(String message, Exception e)  {
        super(message, e);
    }

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
