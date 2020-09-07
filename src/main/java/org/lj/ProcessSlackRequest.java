package org.lj;

import org.jboss.logging.Logger;

com.slack.api.bolt.request.builtin.SlashCommandRequest;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import javax.inject.Inject;

public class ProcessSlackRequest {
  
  @Inject 
  @Channel("messaging-demo") 
  static Emitter<SlackMessage> kafkaSender;
  
  public void process(SlashCommandRequest req) {
      PigLatin pigLatin = new PigLatin();
      String textToTranslate = req.getPayload().getText();
      SlackMessage message = new SlackMessage();
      message.text = pigLatin.translateToPigLatin(textToTranslate);
      LOG.info(textToTranslate + " translated to " + message.text);
      
      //Send result to Kafka
      kafkaSender.send(message);
  }
}
