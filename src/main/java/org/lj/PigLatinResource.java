package org.lj;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;

@Path("/piglatin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PigLatinResource {

    private PigLatin pigLatin = new PigLatin();

    public PigLatinResource() {
    }

    @GET
    public PigLatin translate(PigLatin input) {
        pigLatin.translateToPigLatin(input.translatedText);
        return pigLatin.translatedText;
    }
}
