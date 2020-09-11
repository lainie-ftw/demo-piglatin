package org.lj;

import org.eclipse.microprofile.reactive.messaging.Outgoing;
import io.smallrye.reactive.messaging.annotations.Broadcast;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.jboss.logging.Logger;
import java.math.BigInteger; 
import java.util.Random;

import javax.ws.rs.core.MediaType;

@Path("/piglatin")
public class PigLatinResource {

    private PigLatin pigLatin;
    private static final Logger LOG = Logger.getLogger(PigLatinResource.class);

    public PigLatinResource() {
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PigLatin translate(PigLatin input) {
        pigLatin = new PigLatin(input.inputText);
        pigLatin.translateToPigLatin();
        // stall a bit
        //int i;
        //Random random = new Random();
        BigInteger fact=BigInteger.valueOf(1); 
        //int number = random.nextInt(100) + 100;//It is the number to calculate factorial. 
        //for(i=1;i<=number;i++){
        //    fact = fact.multiply(BigInteger.valueOf(i));
        //}
        LOG.info(pigLatin.inputText + " translated to " + pigLatin.outputText + " (" + fact + ")");
        send();
        return pigLatin;
    }
    
    @Outgoing("my-topic")
    @Broadcast
    public PigLatin send() {
        return pigLatin;
    }
}
