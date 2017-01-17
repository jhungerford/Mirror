package dev.mirror.resources;

import com.codahale.metrics.MetricRegistry;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import dev.mirror.services.RadarService;
import dev.mirror.services.TestWeatherFetcher;
import dev.mirror.services.WeatherFetcher;
import dev.mirror.services.WeatherService;
import io.dropwizard.testing.junit.ResourceTestRule;
import io.dropwizard.views.ViewMessageBodyWriter;
import io.dropwizard.views.ViewRenderer;
import io.dropwizard.views.freemarker.FreemarkerViewRenderer;
import org.eclipse.jetty.http.HttpStatus;
import org.joda.time.DateTimeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import java.io.File;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class MirrorResourceIntTest {

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addProvider(new ViewMessageBodyWriter(new MetricRegistry(), Collections.<ViewRenderer>singleton(new FreemarkerViewRenderer())))
            .addResource(new MirrorResource(new WeatherService(new TestWeatherFetcher()), new RadarService("FTG")))
            .build();

    private Response response;

    @Before
    public void setUp() {
        response = null;
    }

    @After
    public void tearDown() {
        if (response != null) {
            response.close();
        }
    }

    @Test
    public void rootResourceExists() {
        response = resources.client().target("/").request().get();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK_200);
        assertThat(response.getLength()).isGreaterThan(0);
    }

    @Test
    public void indexHtmlExists() {
        response = resources.client().target("/index.html").request().get();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK_200);
        assertThat(response.getLength()).isGreaterThan(0);
    }

    @Test
    public void currentTimeFormatMorning() {
        DateTimeUtils.setCurrentMillisFixed(1462022645219L); // 7:24 4/30/16

        Document document = fetchMirrorDocument();

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

        Document document = fetchMirrorDocument();

        Elements timeElements = document.getElementsByClass("clock-time");
        assertThat(timeElements.size()).isEqualTo(1);
        assertThat(timeElements.first().text()).isEqualTo("13:06");

        Elements dateElements = document.getElementsByClass("clock-date");
        assertThat(dateElements.size()).isEqualTo(1);
        assertThat(dateElements.first().text()).isEqualTo("Sunday, April 3, 2016");
    }

    @Test
    public void weatherExists() throws Exception {
        Document document = fetchMirrorDocument();

        Elements weatherCurrentElements = document.getElementsByClass("weather-current");
        assertThat(weatherCurrentElements).hasSize(1);
        assertThat(weatherCurrentElements.first().text()).isEqualTo("62.01°");
    }

    private Document fetchMirrorDocument() {
        response = resources.client().target("/").request().get();

        String htmlStr = response.readEntity(String.class);
        return Jsoup.parse(htmlStr);
    }
}