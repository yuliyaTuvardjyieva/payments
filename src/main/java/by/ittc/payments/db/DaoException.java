package by.ittc.payments.db;

import by.ittc.payments.exception.PaymentsException;

public class DaoException extends PaymentsException {

    private static final long serialVersionUID = -7090459758976267740L;

    public DaoException() {
        super();
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

}
