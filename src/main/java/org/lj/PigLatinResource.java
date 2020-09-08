package org.lj;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/piglatin")
@Produces("text/plain")
@Consumes("text/plain")
public class PigLatin {

    private String translatedText;

    public PigLatinResource() {
    }

    @GET
    public String translate(String text) {
        PigLatin pigLatin = new PigLatin();
        translatedText = pigLatin.translateToPigLatin(inputText);
        return translatedText;
    }
}
