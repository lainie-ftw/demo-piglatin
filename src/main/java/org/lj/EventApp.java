package org.lj;

import org.jboss.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/events")
public class EventApp extends HttpServlet{
	
//  private static final long serialVersionUID = 1L;
  private static final Logger LOG = Logger.getLogger(EventApp.class);
  
  @Inject
  @Channel("slack")
  Emitter<PigLatin> slackEmitter;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
	  String inputText = request.getParameter("text");
	  String userID = request.getParameter("user_name");
	  String sourceChannel = request.getParameter("channel_name");
		  
	  PigLatin pigLatin = new PigLatin(inputText, userID, sourceChannel);
	  pigLatin.translateToPigLatin();
	  slackEmitter.send(pigLatin);
	 
	  PrintWriter writer = response.getWriter();   
	  writer.print(pigLatin.outputText);
	  writer.close();
  }
}
