package org.lj;

import com.slack.api.bolt.App;
import com.slack.api.bolt.servlet.SlackAppServlet;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import javax.inject.Inject;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/slack/events")
public class SlackApp extends SlackAppServlet {
  private static final long serialVersionUID = 1L;

  @Inject @Channel("messaging-demo") static Emitter<String> translationEmitter;
  
  public SlackApp() throws IOException { super(initSlackApp()); }
  public SlackApp(App app) { super(app); }

  private static App initSlackApp() throws IOException {
    App app = new App();
    app.command("/piglatin", (req, ctx) -> {
      //Translate the input text
      String textToTranslate = req.getPayload().getText();
      String translatedText = translateToPigLatin(textToTranslate);
      
       //Send result to Kafka
       translationEmitter.send(translatedText);
            
      //Send response back to Slack from app
    //  ctx.respond(textToTranslate + " in Pig Latin is " + translatedText + "! :tada:");
      //Tell Slack we got the message.
      return ctx.ack("got it");
    });
    return app;
  }
  
  //Pig Latin logic borrowed from here: http://pages.cs.wisc.edu/~ltorrey/cs302/examples/PigLatinTranslator.java
  private static String translateToPigLatin(String textToTranslate) {
    String translatedText = "";
    int i = 0;
    while (i < textToTranslate.length()) {
      // Take care of punctuation and spaces
      while (i < textToTranslate.length() && !isLetter(textToTranslate.charAt(i))) {
        translatedText = translatedText + textToTranslate.charAt(i);
        i++;
      }

      // If there aren't any words left, stop.
      if (i >= textToTranslate.length()) break;

      // Otherwise we're at the beginning of a word.
      int begin = i;
      while (i<textToTranslate.length() && isLetter(textToTranslate.charAt(i))) {
        i++;
      }

      // Now we're at the end of a word, so translate it.
      int end = i;
      translatedText = translatedText + pigWord(textToTranslate.substring(begin, end));
  }
  return translatedText;
}

  /**
   * Method to test whether a character is a letter or not.
   * @param c The character to test
   * @return True if it's a letter
   */
  private static boolean isLetter(char c) {
    return ( (c >='A' && c <='Z') || (c >='a' && c <='z') );
  }

  /**
   * Method to translate one word into pig latin.
   * @param word The word in english
   * @return The pig latin version
   */
  private static String pigWord(String word) {
    int split = firstVowel(word);
    return word.substring(split)+"-"+word.substring(0, split)+"ay";
  }

  /**
   * Method to find the index of the first vowel in a word.
   * @param word The word to search
   * @return The index of the first vowel
   */
  private static int firstVowel(String word) {
    word = word.toLowerCase();
    for (int i=0; i<word.length(); i++)
      if (word.charAt(i)=='a' || word.charAt(i)=='e' ||
          word.charAt(i)=='i' || word.charAt(i)=='o' ||
          word.charAt(i)=='u')
        return i;
    return 0;
  }
}
