package dev.mirror.resources;

import dev.mirror.views.MirrorView;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MirrorResourceTest {

    private MirrorResource mirrorResource = new MirrorResource();

    @After
    public void cleanUp() {
        DateTimeUtils.setCurrentMillisSystem();
    }

    @Test
    public void viewContainsCurrentTime() {
        long time = 1462022645319L;
        DateTimeUtils.setCurrentMillisFixed(time);

        MirrorView view = mirrorResource.index();

        assertThat(view.getTime()).isEqualTo(new DateTime(time));
    }

    @Test
    public void timeChangesInView() {
        MirrorView firstView = mirrorResource.index();
        DateTimeUtils.setCurrentMillisOffset(20000);
        MirrorView secondView = mirrorResource.index();

        assertThat(firstView.getTime()).isNotEqualTo(secondView.getTime());
        assertThat(firstView.getTime().isBefore(secondView.getTime()));
    }
}
