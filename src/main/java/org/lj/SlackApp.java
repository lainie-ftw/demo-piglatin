package org.lj;

import org.jboss.logging.Logger;

import com.slack.api.bolt.App;
import com.slack.api.bolt.servlet.SlackAppServlet;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/slack/events")
public class SlackApp extends SlackAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final Logger LOG = Logger.getLogger(SlackApp.class);
  
  public SlackApp() throws IOException { super(initSlackApp()); }
  public SlackApp(App app) { super(app); }

  private static App initSlackApp() throws IOException {
    App app = new App();
    app.command("/piglatin", (req, ctx) -> {
      
      //Translate the input text
      PigLatin pigLatin = new PigLatin();
      String textToTranslate = req.getPayload().getText();
      String translatedText = pigLatin.translateToPigLatin(textToTranslate);
      LOG.info(textToTranslate + " translated to " + translatedText);
      
       //Send result to Kafka
      TranslatorResource translator = new TranslatorResource();
      translator.text = translatedText;
      translator.addTranslation();
      
      //Send response back to Slack from app
    //  ctx.respond(textToTranslate + " in Pig Latin is " + translatedText + "! :tada:");
      //Tell Slack we got the message.
      return ctx.ack(translatedText);
    });
    return app;
  }
}
