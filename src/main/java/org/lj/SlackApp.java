package org.lj;

import com.slack.api.bolt.App;
import com.slack.api.bolt.servlet.SlackAppServlet;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/slack/events")
public class SlackApp extends SlackAppServlet {
  private static final long serialVersionUID = 1L;
  public SlackApp() throws IOException { super(initSlackApp()); }
  public SlackApp(App app) { super(app); }

  private static App initSlackApp() throws IOException {
    App app = new App();
    app.command("/piglatin", (req, ctx) -> {
      //Translate the input text
      String textToTranslate = req.getPayload().getText();
      String translatedText = translateToPigLatin(textToTranslate);
      
      //Post result to Kafka
      
      //Send response back to Slack from app
      return ctx.ack("The (fake) translated text of this command that was sent is: " + translatedText);
    });
    return app;
  }
  
  private static String translateToPigLatin(String textToTranslate) {
    return "ake-fay ig-pay atin-lay";
  }
}
