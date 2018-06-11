package com.srini.alexa;
 
import java.io.StringReader;
import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.srini.alexa.model.request.Product;
import com.srini.alexa.model.request.Status;
 
@Path("/aiskill")
public class AlexaSkillService {
 
	
	public AlexaSkillService(){
		
	}
	
	@POST
    @Path("/alexaskill")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String alexaSkill(String alexaRequest) throws Exception {
		
		JsonReader reader = Json.createReader(new StringReader(alexaRequest));
        
        JsonObject alexaRequestObject = reader.readObject();
        
        JsonObject session_jsondata = alexaRequestObject.getJsonObject("session");
        JsonObject context_jsondata = alexaRequestObject.getJsonObject("context");
        JsonObject request_jsondata = alexaRequestObject.getJsonObject("request");
        
        if ("LaunchRequest".equalsIgnoreCase(request_jsondata.getString("type")) ) {
            //console.log('In side LaunchRequest :\n', req.body.request.type);
    		//var dataRow = readData(inputId);
    		//console.log('datRow :', dataRow );
    		//console.log('Excel First Name :\n', dataRow.FirstName);
    		
        	JsonObject sessionJson = Json.createObjectBuilder().add("questionNo", "0").build();
        	
        	return buildResponse("SSML", "<speak>Welcome to Voya 401k service, to get started please say the four digit PIN you setup to enabling the skill? </speak>", 
        			"<speak>to get started please say the four digit PIN you setup to enabling the skill?</speak>", "true", sessionJson);
   		
        } else {
        	JsonObject sessionJson = Json.createObjectBuilder().build();
        	return buildResponse("SSML", "<speakInvalid PIN or No Account setup!</speak>", "", "true", sessionJson);
        }
        
    }
	
	@POST
    @Path("/googlehomeskill")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String googleHomeSkill(String alexaRequest) throws Exception {
		
		JsonReader reader = Json.createReader(new StringReader(alexaRequest));
        
        JsonObject alexaRequestObject = reader.readObject();
        
        JsonObject session_jsondata = alexaRequestObject.getJsonObject("session");
        JsonObject context_jsondata = alexaRequestObject.getJsonObject("context");
        JsonObject request_jsondata = alexaRequestObject.getJsonObject("request");
        
        if ("LaunchRequest".equalsIgnoreCase(request_jsondata.getString("type")) ) {
            //console.log('In side LaunchRequest :\n', req.body.request.type);
    		//var dataRow = readData(inputId);
    		//console.log('datRow :', dataRow );
    		//console.log('Excel First Name :\n', dataRow.FirstName);
    		
        	JsonObject sessionJson = Json.createObjectBuilder().add("questionNo", "0").build();
        	
        	return buildResponse("SSML", "<speak>Welcome to Voya 401k service, to get started please say the four digit PIN you setup to enabling the skill? </speak>", 
        			"<speak>to get started please say the four digit PIN you setup to enabling the skill?</speak>", "true", sessionJson);
   		
        } else {
        	JsonObject sessionJson = Json.createObjectBuilder().build();
        	return buildResponse("SSML", "<speakInvalid PIN or No Account setup!</speak>", "", "true", sessionJson);
        }
        
    }
	
	private String buildResponse(String outputSpeechType, String speech, String repromptspeech, String shouldEndSession, JsonObject sessionJson) {
		
		JsonObject alexaJsonResponseObj = Json.createObjectBuilder()
                .add("version", "1.0")
                .add("sessionAttributes", sessionJson)
                .add("response", Json.createObjectBuilder()
                					.add("shouldEndSession", shouldEndSession)
                					.add("outputSpeech", Json.createObjectBuilder()
                											.add("type", outputSpeechType)
                											.add("ssml", speech)
                											.build()
                						)
                					.add("reprompt", Json.createObjectBuilder()
											.add("outputSpeech", Json.createObjectBuilder()
        											.add("type", outputSpeechType)
        											.add("ssml", speech)
        											.build()
        										)
                						)
                	)
                .build();
         
        StringWriter stringWriter = new StringWriter();
        JsonWriter writer = Json.createWriter(stringWriter);
        writer.writeObject(alexaJsonResponseObj);
        writer.close();
        return stringWriter.getBuffer().toString();
		
	}
	
//	private AlexaResponse buildResponse(SessionAttributes sessionAttributes, Response response) {
//		AlexaResponse alexaResponse = new AlexaResponse();
//		alexaResponse.setVersion("1.0");
//		alexaResponse.setSessionAttributes(sessionAttributes);
//		alexaResponse.setResponse(response);
//		return alexaResponse;
//	}
//	
//	private String buildResponse() {
//		String json = Json.createObjectBuilder()
//	            .add("key1", "value1")
//	            .add("key2", "value2")
//	            .build()
//	            .toString();
//		return json;
//	}
	
	String json = Json.createObjectBuilder()
            .add("key1", "value1")
            .add("key2", "value2")
            .build()
            .toString();
	
	@GET
	@Path("/{param}")
	public String getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say : " + msg;
 
		return output + json;
 
	}
	
 
}