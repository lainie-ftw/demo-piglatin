package org.lj;

import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
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

    @Inject
    @Channel("slack")
    Emitter<PigLatin> slackEmitter;

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
        slackEmitter.send(pigLatin);
        return pigLatin;
    }
    
    // @Outgoing("slack")
    // @Broadcast
    // public PigLatin send() {
    //     return pigLatin;
    // }
}
