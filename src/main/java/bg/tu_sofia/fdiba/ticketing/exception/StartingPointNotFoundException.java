package bg.tu_sofia.fdiba.ticketing.exception;

import java.io.Serial;

/*
 * Exception thrown when a starting point cannot be found.
 */

public class StartingPointNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public StartingPointNotFoundException(String message) {}

}
