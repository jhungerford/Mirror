package dev.mirror.api;

import dev.mirror.model.Weather;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test that runs a mirror server connected to mock data.
 */
public class MirrorResourceTest {

    @ClassRule
    public static final ResourceTestRule server = ResourceTestRule.builder()
            .addResource(new MirrorResource())
            .build();

    @Test
    public void weatherReturnsData() {
        Response response = server.client()
                .target("/v1/mirror/weather")
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.readEntity(Weather.class)).isNotNull();
    }
}
