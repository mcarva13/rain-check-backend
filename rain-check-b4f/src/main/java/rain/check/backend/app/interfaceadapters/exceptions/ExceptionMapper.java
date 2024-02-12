package rain.check.backend.app.interfaceadapters.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

/**
 * Rain-Check B4F exception mapper.
 */
@Provider
@Slf4j
public class ExceptionMapper implements jakarta.ws.rs.ext.ExceptionMapper<Exception> {

    @Override
    public Response toResponse(final Exception exception) {
        return mapExceptionToResponse(exception);
    }

    private Response mapExceptionToResponse(final Exception exception) {
        if (exception instanceof WebApplicationException) {
            // Overwrite error message
            final Response originalErrorResponse = ((WebApplicationException) exception).getResponse();
            return Response.fromResponse(originalErrorResponse)
                    .entity(exception.getMessage())
                    .build();
        }
        // Special mappings
        else if (exception instanceof IllegalArgumentException) {
            return Response.status(400).entity(exception.getMessage()).build();
        }
        // 500 (Internal Server Error) for all other
        else {
            LOGGER.info("[ERROR]", exception);
            return Response.serverError().entity("Internal Server Error. Please try later.").build();
        }
    }
}