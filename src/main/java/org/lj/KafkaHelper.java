package org.lj;

import org.jboss.logging.Logger;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import javax.inject.Inject;

public class KafkaHelper {
  
  @Inject 
  @Channel("messaging-demo") 
  static Emitter<SlackMessage> kafkaSender;

  public static void sendToKafka(SlackMessage message) {
    kafkaSender.send(message);
  }
}
