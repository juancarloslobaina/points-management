package es.qabit.pm.service.exceptions;

import java.io.Serial;

public class InsufficientBalanceExceptions extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InsufficientBalanceExceptions() {
        super("Insufficient balance to complete the transaction.");
    }
}
