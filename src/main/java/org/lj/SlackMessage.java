package org.lj;

public class SlackMessage { 
    public String text;
    public String userID;
    public String sourceChannel;
    
    public SlackMessage() {
        text = "";
        userID = "";
        sourceChannel = "";
    }
    
    public setSlackMessage(String inText, String inUserID, String inSourceChannel) {
        text = inText;
        userID = inUserID;
        sourceChannel = inSourceChannel;
    }
}
