package dev.mirror.views;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class Radar {

    public final ImmutableList<String> layerUrls;

    private Radar(Builder builder) {
        this.layerUrls = ImmutableList.copyOf(builder.layerUrls);
    }

    public static Builder builder(String radarSiteId) {
        return new Builder(radarSiteId);
    }

    public static class Builder {
        private static final String TOPO_URL = "http://radar.weather.gov/ridge/Overlays/Topo/Short/%s_Topo_Short.jpg";
        private static final String COUNTY_URL = "http://radar.weather.gov/ridge/Overlays/County/Short/%s_County_Short.gif";

        private final String radarSiteId;
        private final List<String> layerUrls;

        private Builder(String radarSiteId) {
            this.radarSiteId = radarSiteId.toUpperCase();
            this.layerUrls = new ArrayList<>();
        }

        public Builder withTopo() {
            return this.withLayer(TOPO_URL);
        }

        public Builder withCounty() {
            return this.withLayer(COUNTY_URL);
        }

        private Builder withLayer(String formatString) {
            layerUrls.add(String.format(formatString, radarSiteId));
            return this;
        }

        public Radar build() {
            return new Radar(this);
        }
    }
}
