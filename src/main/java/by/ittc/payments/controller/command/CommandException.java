package by.ittc.payments.controller.command;

import by.ittc.payments.exception.PaymentsException;

public class CommandException extends PaymentsException {

    private static final long serialVersionUID = 6783024691851759489L;

    public CommandException() {
        super();
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }

}
