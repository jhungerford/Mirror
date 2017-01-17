package dev.mirror.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mirror.services.RadarService;
import io.dropwizard.setup.Environment;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @see <a href="http://www.srh.noaa.gov/jetstream/doppler/ridge_download.html">NWS RIDGE Radar</a>
 */
public class RadarFactory {

    @NotEmpty
    private String radarSiteId;

    @JsonProperty
    public String getRadarSiteId() {
        return radarSiteId;
    }

    @JsonProperty
    public void setRadarSiteId(String radarSiteId) {
        this.radarSiteId = radarSiteId;
    }

    public RadarService build(Environment environment) {
        return new RadarService(radarSiteId);
    }
}
