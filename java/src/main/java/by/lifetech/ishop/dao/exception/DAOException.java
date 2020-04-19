package by.lifetech.ishop.dao.exception;

public class DAOException extends Exception {
    public DAOException(String message, Exception e)  {
        super(message, e);
    }
}
