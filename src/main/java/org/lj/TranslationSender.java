package org.lj;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TranslationSender {

    private String translation;

    @Inject
    public TranslationSender(String translationIn) {
        translation = translationIn;
    }

    @Outgoing("messaging-demo")                        
    public String send() {               
        return translation;
    }
}
