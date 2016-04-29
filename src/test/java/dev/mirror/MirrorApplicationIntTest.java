package dev.mirror;

import dev.mirror.config.MirrorConfiguration;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class MirrorApplicationIntTest {

    @ClassRule
    public static final DropwizardAppRule<MirrorConfiguration> rule =
            new DropwizardAppRule<>(MirrorApplication.class, ResourceHelpers.resourceFilePath("test-config.yml"));

    @Test
    public void indexHtmlContainsData() {
        Client client = new JerseyClientBuilder().build();

        Response response = client.target(String.format("http://localhost:%d/index.html", rule.getLocalPort()))
                .request().get();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getHeaderString("Content-Type")).isEqualTo("text/html");
    }
}
