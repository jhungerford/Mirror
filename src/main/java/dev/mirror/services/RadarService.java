package dev.mirror.services;

import dev.mirror.views.Radar;

/**
 * Given a radar site id, builds a {@link Radar} which is a stack of transparent gifs layered on top of each other.
 *
 * @see <a href="http://www.srh.noaa.gov/jetstream/doppler/ridge_download.html">URL Descriptions</a>
 */
public class RadarService {

    private final String radarSiteId;

    public RadarService(String radarSiteId) {
        this.radarSiteId = radarSiteId;
    }

    public Radar getRadar() {
        return Radar.builder(radarSiteId)
                .withTopo()
                .withCounty()
//                .withHighways()
//                .withCities()
//                .withReflectivity()
//                .withLegend()
                .build();
    }
}
