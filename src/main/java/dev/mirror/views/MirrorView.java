package dev.mirror.views;

import com.google.common.base.Charsets;
import io.dropwizard.views.View;

public class MirrorView extends View {
    public MirrorView() {
        super("mirror.ftl", Charsets.UTF_8);
    }
}
