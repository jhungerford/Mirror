package dev.mirror.resources;

import com.codahale.metrics.MetricRegistry;
import io.dropwizard.testing.junit.ResourceTestRule;
import io.dropwizard.views.ViewMessageBodyWriter;
import io.dropwizard.views.ViewRenderer;
import io.dropwizard.views.freemarker.FreemarkerViewRenderer;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.Response;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class MirrorResourceTest {

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addProvider(new ViewMessageBodyWriter(new MetricRegistry(), Collections.<ViewRenderer>singleton(new FreemarkerViewRenderer())))
            .addResource(new MirrorResource())
            .build();

    @Test
    public void rootResourceExists() {
        Response response = resources.client().target("/").request().get();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK_200);
        assertThat(response.getLength()).isGreaterThan(0);
    }

    @Test
    public void indexHtmlExists() {
        Response response = resources.client().target("/index.html").request().get();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK_200);
        assertThat(response.getLength()).isGreaterThan(0);
    }
}