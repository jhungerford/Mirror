package dev.mirror.resources;

import com.codahale.metrics.MetricRegistry;
import io.dropwizard.testing.junit.ResourceTestRule;
import io.dropwizard.views.ViewMessageBodyWriter;
import io.dropwizard.views.ViewRenderer;
import io.dropwizard.views.freemarker.FreemarkerViewRenderer;
import org.eclipse.jetty.http.HttpStatus;
import org.joda.time.DateTimeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.Response;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class MirrorResourceIntTest {

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

    @Test
    public void currentTimeFormatMorning() {
        DateTimeUtils.setCurrentMillisFixed(1462022645219L); // 7:24 4/30/16

        Response response = resources.client().target("/").request().get();

        String htmlStr = response.readEntity(String.class);
        Document document = Jsoup.parse(htmlStr);

        Elements timeElements = document.getElementsByClass("clock-time");
        assertThat(timeElements.size()).isEqualTo(1);
        assertThat(timeElements.first().text()).isEqualTo("7:24");

        Elements dateElements = document.getElementsByClass("clock-date");
        assertThat(dateElements.size()).isEqualTo(1);
        assertThat(dateElements.first().text()).isEqualTo("Saturday, April 30, 2016");
    }

    @Test
    public void currentTimeFormatAfternoon() {
        DateTimeUtils.setCurrentMillisFixed(1459710364000L); // 13:06 4/3/16

        Response response = resources.client().target("/").request().get();

        String htmlStr = response.readEntity(String.class);
        Document document = Jsoup.parse(htmlStr);

        Elements timeElements = document.getElementsByClass("clock-time");
        assertThat(timeElements.size()).isEqualTo(1);
        assertThat(timeElements.first().text()).isEqualTo("13:06");

        Elements dateElements = document.getElementsByClass("clock-date");
        assertThat(dateElements.size()).isEqualTo(1);
        assertThat(dateElements.first().text()).isEqualTo("Sunday, April 3, 2016");
    }
}