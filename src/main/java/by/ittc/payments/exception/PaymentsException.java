package by.ittc.payments.exception;

public class PaymentsException extends Exception {

    private static final long serialVersionUID = 1309627591377281280L;

    public PaymentsException() {
        super();
    }

    public PaymentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentsException(String message) {
        super(message);
    }

    public PaymentsException(Throwable cause) {
        super(cause);
    }
}
