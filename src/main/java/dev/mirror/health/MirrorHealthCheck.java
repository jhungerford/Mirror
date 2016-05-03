package dev.mirror.health;

import com.codahale.metrics.health.HealthCheck;
import dev.mirror.resources.MirrorResource;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class MirrorHealthCheck extends HealthCheck {
    private static final Logger log = getLogger(MirrorHealthCheck.class);

    private final MirrorResource mirrorResource;

    public MirrorHealthCheck(MirrorResource mirrorResource) {
        this.mirrorResource = mirrorResource;
    }

    @Override
    protected Result check() throws Exception {
        try {
            if (mirrorResource.buildMirrorView() == null) {
                return Result.unhealthy("Mirror view is null");
            } else {
                return Result.healthy();
            }
        } catch (Exception e) {
            log.error("Error checking mirror health", e);
            return Result.unhealthy(e);
        }
    }
}
