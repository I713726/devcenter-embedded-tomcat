package com.srini.alexa;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.srini.alexa.model.AlexaRequest;
import com.srini.alexa.model.Product;
import com.srini.alexa.model.Status;
import com.srini.alexa.model.response.AlexaResponse;
import com.srini.alexa.model.response.Card;
import com.srini.alexa.model.response.Directives;
import com.srini.alexa.model.response.OutputSpeech;
import com.srini.alexa.model.response.Reprompt;
import com.srini.alexa.model.response.Response;
import com.srini.alexa.model.response.SessionAttributes;
 
@Path("/alexaskill")
public class AlexaSkillService {
 
	
	public AlexaSkillService(){
		
	}
	
	@GET
	@Path("/{param}")
	public String getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say : " + msg;
 
		return output;
 
	}
	
	@POST
    @Path("/alexarequest")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AlexaResponse insert(AlexaRequest alexaRequest) {
		String output = "Alexa say : " + alexaRequest.getRequest().getType();
		SessionAttributes sessionAttributes = new SessionAttributes();
		sessionAttributes.setKey("1");
		Response response = new Response();
		OutputSpeech outputSpeech = new OutputSpeech();
		outputSpeech.setType("PlainText");
		outputSpeech.setText("Plain text string to speak");
		outputSpeech.setSsml("<speak>SSML text string to speak</speak>");
		response.setOutputSpeech(outputSpeech);
		Reprompt reprompt = new Reprompt();
		OutputSpeech RepromptOutputSpeech = new OutputSpeech();
		RepromptOutputSpeech.setType("PlainText");
		RepromptOutputSpeech.setText("Plain text string to speak");
		RepromptOutputSpeech.setSsml("<speak>SSML text string to speak</speak>");
		reprompt.setOutputSpeech(RepromptOutputSpeech);
		response.setReprompt(reprompt);
		String shouldEndSession = "false";
		response.setShouldEndSession(shouldEndSession);
		response.setCard(new Card());
		return buildResponse(sessionAttributes, response);
    }
	
	@POST
    @Path("/insert")
	
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Status insert(Product product) {
        return new Status("SUCCESS", "Inserted " + product.getName());
    }
	
	private AlexaResponse buildResponse(SessionAttributes sessionAttributes, Response response) {
		AlexaResponse alexaResponse = new AlexaResponse();
		alexaResponse.setVersion("1.0");
		alexaResponse.setSessionAttributes(sessionAttributes);
		alexaResponse.setResponse(response);
		return alexaResponse;
	}
 
}