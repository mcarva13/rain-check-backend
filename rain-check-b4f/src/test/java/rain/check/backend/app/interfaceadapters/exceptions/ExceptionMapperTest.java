package rain.check.backend.app.interfaceadapters.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
class ExceptionMapperTest {

    @InjectMocks
    private ExceptionMapper exceptionMapper;

    @Test
    void toResponseWebApplicationException() {
        // Arrange
        final WebApplicationException exception = new WebApplicationException();

        // Act
        final Response actual = exceptionMapper.toResponse(exception);

        // Assert
        assertThat(actual.getStatus(), equalTo(500));
        assertThat(actual.getEntity(), equalTo(exception.getMessage()));
    }

    @Test
    void toResponseIllegalArgumentException() {
        // Arrange
        final IllegalArgumentException exception = new IllegalArgumentException();

        // Act
        final Response actual = exceptionMapper.toResponse(exception);

        // Assert
        assertThat(actual.getStatus(), equalTo(400));
        assertThat(actual.getEntity(), equalTo(exception.getMessage()));
    }

    @Test
    void toResponseOtherException() {
        // Arrange
        final RuntimeException exception = new RuntimeException();

        // Act
        final Response actual = exceptionMapper.toResponse(exception);

        // Assert
        assertThat(actual.getStatus(), equalTo(500));
        assertThat(actual.getEntity(), equalTo("Internal Server Error. Please try later."));
    }

}