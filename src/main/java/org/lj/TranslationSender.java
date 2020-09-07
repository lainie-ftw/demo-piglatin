package org.lj;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TranslationSender {

    @Outgoing("messaging-demo")                        
    public String send() {               
        return "tester message";
    }
}
