package org.lj;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TranslationSender {

    @Inject
    private String translation;

    public TranslationSender(String translationIn) {
        translation = translationIn;
    }

    @Outgoing("messaging-demo")                        
    public String send() {               
        return translation;
    }
}
