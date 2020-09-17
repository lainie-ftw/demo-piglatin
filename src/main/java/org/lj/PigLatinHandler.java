package org.lj;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import io.smallrye.reactive.messaging.annotations.Outgoing;

import org.jboss.logging.Logger;

public class PigLatinHandler {

    private PigLatin pigLatin;
    private static final Logger LOG = Logger.getLogger(PigLatinResource.class);

    public PigLatinHandler() {
    }

    @Outgoing("slack")
    @Broadcast
    public PigLatin translate() {
        pigLatin = new PigLatin(input.inputText);
        pigLatin.translateToPigLatin();
        LOG.info(pigLatin.inputText + " translated to " + pigLatin.outputText);
        return pigLatin;
     }
}
