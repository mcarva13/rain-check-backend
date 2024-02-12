package rain.check.backend.database.applicationservices;

import jakarta.ws.rs.NotFoundException;

import java.util.Objects;

public interface Repository {

    default  <T> Object returnObjectOrThrowNotFoundException(final T obj,
                                                           final String message) {
        if (Objects.isNull(obj))
            throw new NotFoundException(message);
        return obj;
    }

}
