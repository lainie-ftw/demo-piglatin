package org.lj;

import org.jboss.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import javax.inject.Inject;

import java.io.InputStream;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/events")
public class EventApp extends HttpServlet{
  private static final long serialVersionUID = 1L;
  
  private static final Logger LOG = Logger.getLogger(SlackApp.class);
  
  @Inject
  @Channel("slack")
  Emitter<PigLatin> slackEmitter;

  @Override
  protected void doPost(HttpServletRequest request, 
  HttpServletResponse response)
	      throws ServletException, IOException {
      //    InputStream  is = request.getInputStream();
      //    StringBuilder sb = new StringBuilder();

      //    int i;
      //    char c;
      //    while((i = is.read())!=-1) {
         
            // converts integer to character
       //     c = (char)i;
       //     sb.append(c);
            
            // prints character
           // System.out.print(c);
       //  }
       //   LOG.info(sb.toString());
          PrintWriter writer = response.getWriter();    
	  
	 // JsonObject json = new JsonObject(request.getParameterMap());
	  
	  String inputText = request.getParameter("text");

          PigLatin pigLatin = new PigLatin(inputText);
          pigLatin.translateToPigLatin();
          slackEmitter.send(pigLatin);
	  writer.print(pigLatin.outputText);
          writer.close();
  }
}
