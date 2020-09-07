package org.lj;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

public class TranslatorResource {
    private static final Logger LOG = Logger.getLogger(TranslatorResource.class);

    @Inject 
    @Channel("messaging-demo") 
    Emitter<TranslatorResource> emitter;
    
    public String text;

    public void addTranslation() {
       LOG.info("translated text is " + text);
       emitter.send(this);
    }
}
