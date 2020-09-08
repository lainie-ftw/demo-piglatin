package org.lj;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import javax.inject.Inject;

public class KafkaHelper {
  
  @Inject 
  @Channel("messaging-demo") 
  Emitter<SlackMessage> kafkaSender;

  public void sendToKafka(SlackMessage message) {
    kafkaSender.send(message);
  }
}
