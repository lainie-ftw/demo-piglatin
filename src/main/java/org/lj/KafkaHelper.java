package org.lj;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import javax.inject.Inject;

@ApplicationScoped
public class KafkaHelper {
   
   @Inject
  @Channel("messaging-demo") 
  Emitter<SlackMessage> kafkaSender;
   
  public void KafkaHelper() {
  }

  public void sendToKafka(SlackMessage message) {
    kafkaSender.send(message);
  }
}
