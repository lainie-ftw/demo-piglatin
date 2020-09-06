package org.lj;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

public class TranslatorResource {

    @Inject @Channel("messaging-demo") Emitter<String> translationEmitter;

    @Consumes(MediaType.TEXT_PLAIN)
    public void addTranslation(String translation) {
       translationEmitter.send(translation);
    }
}
