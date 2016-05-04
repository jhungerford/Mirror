package dev.mirror;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import dev.mirror.config.MirrorConfiguration;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class MirrorApplicationIntTest {

    @ClassRule
    public static final DropwizardAppRule<MirrorConfiguration> rule =
            new DropwizardAppRule<>(MirrorApplication.class, ResourceHelpers.resourceFilePath("test-config.yml"));

    private Client client;

    @Before
    public void setUp() {
        this.client = new JerseyClientBuilder().build();
    }

    @After
    public void tearDown() {
        this.client.close();
    }

    @Test
    public void environmentVariablesSet() {
        assertThat(System.getenv("MIRROR_FORECASTIO_API_KEY")).isNotNull();
        assertThat(System.getenv("MIRROR_LATITUDE")).isNotNull();
        assertThat(System.getenv("MIRROR_LONGITUDE")).isNotNull();
    }

    @Test
    public void appIsHealthy() {
        Response response = client.target(String.format("http://localhost:%d/healthcheck", rule.getAdminPort()))
                .request().get();

        DocumentContext healthJson = JsonPath.parse(response.readEntity(String.class));
        assertThat(healthJson.read("$.app.healthy", Boolean.class)).isTrue();
        assertThat(healthJson.read("$.deadlocks.healthy", Boolean.class)).isTrue();
    }

    @Test
    public void indexHtmlContainsData() {
        Response response = client.target(String.format("http://localhost:%d/index.html", rule.getLocalPort()))
                .request().get();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getHeaderString("Content-Type")).isEqualTo("text/html");

        response.close();
    }

    @Test
    public void cssAssetsExist() {
        Response response = client.target(String.format("http://localhost:%d/css/mirror.css", rule.getLocalPort()))
                .request().get();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getHeaderString("Content-Type")).contains("text/css");
        assertThat(response.getLength()).isGreaterThan(0);
    }

    @Test
    public void fontAssetsExist() {
        Response response = client.target(String.format("http://localhost:%d/font/weathericons-regular-webfont.ttf", rule.getLocalPort()))
                .request().get();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.hasEntity()).isTrue();
    }
}
