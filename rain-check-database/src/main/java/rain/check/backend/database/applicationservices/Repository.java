package rain.check.backend.database.applicationservices;

import jakarta.ws.rs.NotFoundException;

import java.util.Objects;

/**
 * Common methods for database Repository services.
 */
public interface Repository {

    /**
     * If a given is null, throws a {@link NotFoundException}.
     * @param obj - Object to be evaluated.
     * @param message - Message associated with the exception that might be thrown
     * @return the input object if not null.
     * @param <T> - Object class
     */
    default  <T> Object returnObjectOrThrowNotFoundException(final T obj,
                                                           final String message) {
        if (Objects.isNull(obj))
            throw new NotFoundException(message);
        return obj;
    }

}
