package es.qabit.pm.service.exceptions;

import java.io.Serial;

public class UserNotFoundExceptions extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserNotFoundExceptions(Long userId) {
        super(String.format("User not found %d", userId));
    }
}
