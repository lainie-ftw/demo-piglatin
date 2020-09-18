package org.lj;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import io.smallrye.reactive.messaging.annotations.Outgoing;

import org.jboss.logging.Logger;

public class PigLatinHandler {

    public PigLatin pigLatin;
    private static final Logger LOG = Logger.getLogger(PigLatinResource.class);

    public PigLatinHandler() {
    }
    
    public PigLatinHandler(String inputText) {
        pigLatin = new PigLatin(inputText);
    }

    @Outgoing("slack")
    @Broadcast
    public PigLatin translate() {
        pigLatin.translateToPigLatin();
        LOG.info(pigLatin.inputText + " translated to " + pigLatin.outputText);
        return pigLatin;
     }
}
