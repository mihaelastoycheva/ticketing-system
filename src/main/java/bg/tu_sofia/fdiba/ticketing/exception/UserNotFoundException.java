package bg.tu_sofia.fdiba.ticketing.exception;

import lombok.NoArgsConstructor;

import java.io.Serial;
/*
 * Exception thrown when a user cannot be found.
 */
@NoArgsConstructor
public class UserNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
}
