package dev.mirror.resources;

import dev.mirror.services.WeatherService;
import dev.mirror.views.MirrorView;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

public class MirrorResourceTest {

    private WeatherService mockWeatherService = Mockito.mock(WeatherService.class);

    private MirrorResource mirrorResource = new MirrorResource(mockWeatherService);

    @After
    public void cleanUp() {
        DateTimeUtils.setCurrentMillisSystem();
    }

    @Test
    public void viewContainsCurrentTime() {
        long time = 1462022645319L;
        DateTimeUtils.setCurrentMillisFixed(time);

        MirrorView view = mirrorResource.buildMirrorView();

        assertThat(view.getTime()).isEqualTo(new DateTime(time));
    }

    @Test
    public void timeChangesInView() {
        MirrorView firstView = mirrorResource.buildMirrorView();
        DateTimeUtils.setCurrentMillisOffset(20000);
        MirrorView secondView = mirrorResource.buildMirrorView();

        assertThat(firstView.getTime()).isNotEqualTo(secondView.getTime());
        assertThat(firstView.getTime().isBefore(secondView.getTime()));
    }
}
