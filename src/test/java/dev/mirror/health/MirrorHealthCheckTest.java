package dev.mirror.health;

import dev.mirror.resources.MirrorResource;
import dev.mirror.views.MirrorView;
import org.junit.After;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MirrorHealthCheckTest {

    private MirrorResource mockMirrorResource = mock(MirrorResource.class);
    private MirrorHealthCheck healthCheck = new MirrorHealthCheck(mockMirrorResource);

    @After
    public void tearDown() {
        reset(mockMirrorResource);
    }

    @Test
    public void healthyWhenIndexExists() throws Exception {
        when(mockMirrorResource.buildMirrorView()).thenReturn(MirrorView.builder().build());
        assertThat(healthCheck.check().isHealthy()).isTrue();
    }

    @Test
    public void unhealthyWhenIndexDoesNotExist() throws Exception {
        when(mockMirrorResource.buildMirrorView()).thenReturn(null);
        assertThat(healthCheck.check().isHealthy()).isFalse();
    }

    @Test
    public void unhealthyWhenIndexThrowsAnException() throws Exception {
        when(mockMirrorResource.buildMirrorView()).thenThrow(new IllegalStateException("Test exception"));
        assertThat(healthCheck.check().isHealthy()).isFalse();
    }

}