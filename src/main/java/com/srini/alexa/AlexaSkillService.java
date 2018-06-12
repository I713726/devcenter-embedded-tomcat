package com.srini.alexa;
 
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;

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

import com.srini.alexa.model.UserData;
 
@Path("/aiskill")
public class AlexaSkillService {
 
	
	 private static HashMap<String, UserData> userDataMap;

	    

	public AlexaSkillService(){
		initializeUserData();
	}
	
	@POST
    @Path("/alexaskill")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String alexaSkill(String alexaRequest) throws Exception {
		
		JsonReader reader = Json.createReader(new StringReader(alexaRequest));
        
        JsonObject alexaRequestObject = reader.readObject();
        
        JsonObject session_jsondata = alexaRequestObject.getJsonObject("session");
        //JsonObject context_jsondata = alexaRequestObject.getJsonObject("context");
        JsonObject request_jsondata = alexaRequestObject.getJsonObject("request");
        
        if ("LaunchRequest".equalsIgnoreCase(request_jsondata.getString("type")) ) {
            //log('In side LaunchRequest :\n', req.body.request.type);
    		//var dataRow = readData(inputId);
    		//console.log('datRow :', dataRow );
    		//console.log('Excel First Name :\n', dataRow.FirstName);
    		
        	JsonObject sessionJson = Json.createObjectBuilder().build();
        	
        	return buildResponse("SSML", "<speak>Welcome to Voya 401k service, to get started please say the four digit PIN you setup to enabling the skill? </speak>", 
        			"<speak>to get started please say the four digit PIN you setup to enabling the skill?</speak>", "false", sessionJson);
   		
        } else if ("SessionEndedRequest".equalsIgnoreCase(request_jsondata.getString("type"))) {
    		//console.log('In side SessionEndedRequest :\n', req.body.request.type);
            if ("ERROR".equalsIgnoreCase(request_jsondata.getString("reason"))) {
               // log.error("Alexa ended the session due to an error");
            }
            /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
             Per Alexa docs, we shouldn't send ANY response here... weird, I know.
             * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

         } else if ("IntentRequest".equalsIgnoreCase(request_jsondata.getString("type"))) {
        	//get Intent Json object
        	JsonObject intent_jsondata = request_jsondata.getJsonObject("intent");
        	//get slots Json object
        	JsonObject slots_jsondata = intent_jsondata.getJsonObject("slots");
        	//get session attributes json object
        	JsonObject attribute_jsondata = session_jsondata.getJsonObject("attributes");
        	
        	if ( "VoyaPINIntent".equalsIgnoreCase(intent_jsondata.getString("name")) && (slots_jsondata != null) && (slots_jsondata.getJsonObject("pin") != null) 
        			&& ((slots_jsondata.getJsonObject("pin")).getString("value") != "" )) {
        		UserData userData = getUserData((slots_jsondata.getJsonObject("pin")).getString("value"));
        		
        		if (userData != null) {
        			JsonObject sessionJson = Json.createObjectBuilder().add("voayPin", userData.getUserId()).build();
        			return buildResponse("SSML", "<speak>Hi "+userData.getFirstName()+",!! how can I help you with your " +userData.getPlanName()+ " today</speak>", 
                			"<speak>You can say, things like tell me how my account is doing? </speak>", "true", sessionJson);
        		} else {
        			JsonObject sessionJson = Json.createObjectBuilder().build();
        			return buildResponse("SSML", "<speak>Invalid PIN or No Account setup!</speak>", 
                			"", "true", sessionJson);
        		}
        	} else if ( attribute_jsondata!= null && attribute_jsondata.getString("voayPin") != "" ) {
        		UserData userData = getUserData(attribute_jsondata.getString("voayPin"));
        		//need to continue for all questions and rest of conditions
        	}
        	 
        	
			
        	 
         } else {
        	JsonObject sessionJson = Json.createObjectBuilder().build();
        	return buildResponse("SSML", "<speakInvalid PIN or No Account setup!</speak>", "", "true", sessionJson);
        }
        
        return null;
        
    }
	
	@POST
    @Path("/googlehomeskill")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String googleHomeSkill(String alexaRequest) throws Exception {
		
		JsonReader reader = Json.createReader(new StringReader(alexaRequest));
        
        JsonObject alexaRequestObject = reader.readObject();
        
        JsonObject session_jsondata = alexaRequestObject.getJsonObject("session");
        //JsonObject context_jsondata = alexaRequestObject.getJsonObject("context");
        JsonObject request_jsondata = alexaRequestObject.getJsonObject("request");
        
        if ("LaunchRequest".equalsIgnoreCase(request_jsondata.getString("type")) ) {
            //log('In side LaunchRequest :\n', req.body.request.type);
    		//var dataRow = readData(inputId);
    		//console.log('datRow :', dataRow );
    		//console.log('Excel First Name :\n', dataRow.FirstName);
    		
        	JsonObject sessionJson = Json.createObjectBuilder().build();
        	
        	return buildResponse("SSML", "<speak>Welcome to Voya 401k service, to get started please say the four digit PIN you setup to enabling the skill? </speak>", 
        			"<speak>to get started please say the four digit PIN you setup to enabling the skill?</speak>", "false", sessionJson);
   		
        } else if ("SessionEndedRequest".equalsIgnoreCase(request_jsondata.getString("type"))) {
    		//console.log('In side SessionEndedRequest :\n', req.body.request.type);
            if ("ERROR".equalsIgnoreCase(request_jsondata.getString("reason"))) {
               // log.error("Alexa ended the session due to an error");
            }
            /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
             Per Alexa docs, we shouldn't send ANY response here... weird, I know.
             * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

         } else if ("IntentRequest".equalsIgnoreCase(request_jsondata.getString("type"))) {
        	//get Intent Json object
        	JsonObject intent_jsondata = request_jsondata.getJsonObject("intent");
        	//get slots Json object
        	JsonObject slots_jsondata = intent_jsondata.getJsonObject("slots");
        	//get session attributes json object
        	JsonObject attribute_jsondata = session_jsondata.getJsonObject("attributes");
        	
        	if ( "VoyaPINIntent".equalsIgnoreCase(intent_jsondata.getString("name")) && (slots_jsondata != null) && (slots_jsondata.getJsonObject("pin") != null) 
        			&& ((slots_jsondata.getJsonObject("pin")).getString("value") != "" )) {
        		UserData userData = getUserData((slots_jsondata.getJsonObject("pin")).getString("value"));
        		
        		if (userData != null) {
        			JsonObject sessionJson = Json.createObjectBuilder().add("voayPin", userData.getUserId()).build();
        			return buildResponse("SSML", "<speak>Hi "+userData.getFirstName()+",!! how can I help you with your " +userData.getPlanName()+ " today</speak>", 
                			"<speak>You can say, things like tell me how my account is doing? </speak>", "true", sessionJson);
        		} else {
        			JsonObject sessionJson = Json.createObjectBuilder().build();
        			return buildResponse("SSML", "<speak>Invalid PIN or No Account setup!</speak>", 
                			"", "true", sessionJson);
        		}
        	}
        	 
        	
			
        	 
         } else {
        	JsonObject sessionJson = Json.createObjectBuilder().build();
        	return buildResponse("SSML", "<speakInvalid PIN or No Account setup!</speak>", "", "true", sessionJson);
        }
        
        return null;
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
	
	private UserData getUserData(String userId) {
		if (userDataMap != null) {
			return userDataMap.get(userId);
		} else {
			return null;
		}
	}
	
	
	 private void initializeUserData() {
	        if (userDataMap == null) {
	        	userDataMap = new HashMap<String, UserData>();
	        	userDataMap.put("1111", new UserData("1111","Srini","Kunkalaguntla","Voya 401(K) Savings Plan","$55000","2%","3%","44"));
	        	userDataMap.put("2222", new UserData("2222","Tom","Armstrong","Voya 401(K) Savings Plan ","$55000","3%","3%","50"));
	        	userDataMap.put("3333", new UserData("3333","Sandesh","Parulekar","Voya 401(K) Savings Plan ","$55000","35","3%","46"));
	        	userDataMap.put("4444", new UserData("4444","Ruchi","Gupta","Voya 401(K) Savings Plan ","$55000","4%","3%","40"));
	        	
	        }
	    }
 
}