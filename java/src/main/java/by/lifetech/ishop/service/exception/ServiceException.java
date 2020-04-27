package by.lifetech.ishop.service.exception;

public class ServiceException extends Exception {
    public ServiceException(String message, Exception e)  {
        super(message, e);
    }
}
