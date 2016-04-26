package dev.mirror;

import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class MirrorMain {
    private static final Logger log = getLogger(MirrorMain.class);

    public static void main(String[] args) {
        try {
            new MirrorApplication().run(args);
        } catch (Exception e) {
            log.error("Error running mirror", e);
        }
    }
}
