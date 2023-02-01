package exceptions;

public class ServiceException extends Exception {

    public ServiceException(String message, DAOException e) {
        super(message, e);
    }

    public ServiceException(String message) {
        super(message);
    }
}
