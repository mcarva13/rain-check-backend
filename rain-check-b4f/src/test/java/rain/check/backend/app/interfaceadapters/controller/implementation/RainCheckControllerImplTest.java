package rain.check.backend.app.interfaceadapters.controller.implementation;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rain.check.backend.app.applicationservices.RainCheckService;
import rain.check.backend.app.domain.entities.Weather;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RainCheckControllerImplTest {

    @InjectMocks
    private RainCheckControllerImpl rainCheckController;

    @Mock
    private RainCheckService rainCheckService;

    @Test
    void generateWeatherReportTest() {
        // Arrange
        final Weather mockServiceResponse = mock(Weather.class);

        when(rainCheckService.checkWeatherReport(anyString())).thenReturn(mockServiceResponse);

        // Act
        final Response actualResponse = rainCheckController.generateWeatherReport("someID");

        // Assert
        assertThat(actualResponse.getStatus(), equalTo(200));
        assertThat(actualResponse.getEntity(), equalTo(mockServiceResponse));
    }

}