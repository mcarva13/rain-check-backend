package rain.check.backend.app.interfaceadapters.exceptions;

import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;


@Provider
public class ExceptionMapper implements jakarta.ws.rs.ext.ExceptionMapper<Exception> {

    @Inject
    Logger logger;

    @Override
    public Response toResponse(Exception exception) {
        return mapExceptionToResponse(exception);
    }

    private Response mapExceptionToResponse(Exception exception) {
        // Response from WebApplicationException as they are
        if (exception instanceof WebApplicationException) {
            // Overwrite error message
            Response originalErrorResponse = ((WebApplicationException) exception).getResponse();
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
            logger.fatalf(exception, "[ERROR]");
            return Response.serverError().entity("Internal Server Error. Please try later.").build();
        }
    }
}