package org.lj;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TranslatorResource {
    private static final Logger LOG = Logger.getLogger(TranslatorResource.class);

    @Inject 
    @Channel("messaging-demo") 
    private Emitter<String> translationEmitter;

    public void addTranslation(String translation) {
       LOG.info("incoming translated text is " + translation);
       translationEmitter.send(translation);
    }
}
