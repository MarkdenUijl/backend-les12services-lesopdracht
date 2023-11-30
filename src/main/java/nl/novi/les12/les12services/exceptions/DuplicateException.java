package nl.novi.les12.les12services.exceptions;

public class DuplicateException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public DuplicateException() {
        super();
    }
    public DuplicateException(String message) {
        super(message);
    }
}
