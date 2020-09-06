package org.lj;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

public class TranslatorResource {
    private static final Logger LOG = Logger.getLogger(TranslatorResource.class);

    @Inject @Channel("messaging-demo") Emitter<String> translationEmitter;

//    @Consumes(MediaType.TEXT_PLAIN)
    public void addTranslation(String translation) {
       LOG.info("incoming translated text is " + translation);
       translationEmitter.send(translation);
    }
}
