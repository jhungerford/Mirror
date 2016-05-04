package dev.mirror.resources;

import dev.mirror.services.TestWeatherFetcher;
import dev.mirror.services.WeatherService;
import dev.mirror.views.MirrorView;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

public class MirrorResourceTest {

    private WeatherService testWeatherService = new WeatherService(new TestWeatherFetcher());

    private MirrorResource mirrorResource = new MirrorResource(testWeatherService);

    @After
    public void cleanUp() {
        DateTimeUtils.setCurrentMillisSystem();
    }

    @Test
    public void viewContainsCurrentTime() throws IOException {
        long time = 1462022645319L;
        DateTimeUtils.setCurrentMillisFixed(time);

        MirrorView view = mirrorResource.buildMirrorView();

        assertThat(view.getTime()).isEqualTo(new DateTime(time));
    }

    @Test
    public void timeChangesInView() throws IOException {
        MirrorView firstView = mirrorResource.buildMirrorView();
        DateTimeUtils.setCurrentMillisOffset(20000);
        MirrorView secondView = mirrorResource.buildMirrorView();

        assertThat(firstView.getTime()).isNotEqualTo(secondView.getTime());
        assertThat(firstView.getTime().isBefore(secondView.getTime()));
    }
}
