package com.srini.alexa;
 
import java.io.StringReader;
import java.util.HashMap;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
    public String alexaSkill(@Context HttpHeaders httpHeaders, String alexaRequest) throws Exception {
		
		/** get list of all header parameters from request **/
		Set<String> headerKeys = httpHeaders.getRequestHeaders().keySet();
		for(String header:headerKeys){
			System.out.println(header+":"+httpHeaders.getRequestHeader(header).get(0));
		}
		
		JSONParser parse = new JSONParser();
		//Type caste the parsed json data in json object
		JSONObject jobj = (JSONObject)parse.parse(alexaRequest);
		//System.out.println("Elements input json jobj"+jobj);
		//Store the JSON object in JSON array as objects (For level 1 array element i.e Results)
		JSONObject session_jsondata = (JSONObject) jobj.get("session");
		JSONObject context_jsondata = (JSONObject) jobj.get("context");
		JSONObject request_jsondata = (JSONObject) jobj.get("request");
    	
    	if ("LaunchRequest".equalsIgnoreCase((String)request_jsondata.get("type")) ) {
            //log('In side LaunchRequest :\n', req.body.request.type);
    		//var dataRow = readData(inputId);
    		//console.log('datRow :', dataRow );
    		//console.log('Excel First Name :\n', dataRow.FirstName);
    		
    		JSONObject sessionJson = new JSONObject();
        	
        	return buildResponse("SSML", "<speak>Welcome to Voya 401k service, to get started please say the four digit PIN you setup to enabling the skill? </speak>", 
        			"<speak>to get started please say the four digit PIN you setup to enabling the skill?</speak>", "false", sessionJson);
   		
        } else if ("SessionEndedRequest".equalsIgnoreCase((String)request_jsondata.get("type"))) {
    		//console.log('In side SessionEndedRequest :\n', req.body.request.type);
            if ("ERROR".equalsIgnoreCase((String)request_jsondata.get("reason"))) {
               // log.error("Alexa ended the session due to an error");
            }
            /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
             Per Alexa docs, we shouldn't send ANY response here... weird, I know.
             * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

         } else if ("IntentRequest".equalsIgnoreCase((String)request_jsondata.get("type"))) {
        	//get Intent Json object
        	 JSONObject intent_jsondata = (JSONObject)request_jsondata.get("intent");
        	//get slots Json object
        	 JSONObject slots_jsondata = (JSONObject)intent_jsondata.get("slots");
        	//get session attributes json object
        	 JSONObject attribute_jsondata = (JSONObject)session_jsondata.get("attributes");
        	
        	if ( "VoyaPINIntent".equalsIgnoreCase((String)intent_jsondata.get("name")) && (slots_jsondata != null) && (slots_jsondata.get("pin") != null) ) {
        		UserData userData = getUserData((String)((JSONObject)slots_jsondata.get("pin")).get("value"));
        		
        		if (userData != null) {
        			JSONObject sessionJson = new JSONObject();
        			sessionJson.put("voayPin", userData.getUserId());
        			return buildResponse("SSML", "<speak>Hi "+userData.getFirstName()+",!! how can I help you with your " +userData.getPlanName()+ " today</speak>", 
                			"<speak>You can say, things like tell me how my account is doing? </speak>", "true", sessionJson);
        		} else {
        			JSONObject sessionJson = new JSONObject();
        			return buildResponse("SSML", "<speak>Invalid PIN or No Account setup!</speak>", 
                			"", "true", sessionJson);
        		}
        	} else if ( attribute_jsondata!= null && (String)attribute_jsondata.get("voayPin") != "" ) {
        		UserData userData = getUserData((String)attribute_jsondata.get("voayPin"));
        		//need to continue for all questions and rest of conditions
        	}
        	 
        	
			
        	 
         } else {
        	 JSONObject sessionJson = new JSONObject();
        	return buildResponse("SSML", "<speakInvalid PIN or No Account setup!</speak>", "", "true", sessionJson);
        }
        
        return null;
        
    }
	
	@POST
    @Path("/googlehomeskill")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String googleHomeSkill(@Context HttpHeaders httpHeaders, String alexaRequest ) throws Exception {
		
		
		/** get list of all header parameters from request **/
		Set<String> headerKeys = httpHeaders.getRequestHeaders().keySet();
		for(String header:headerKeys){
			System.out.println(header+":"+httpHeaders.getRequestHeader(header).get(0));
		}
		
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
    		
        	JSONObject sessionJson = new JSONObject();
        	
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
        			JSONObject sessionJson = new JSONObject();
                	sessionJson.put("voayPin", userData.getUserId());
        			return buildResponse("SSML", "<speak>Hi "+userData.getFirstName()+",!! how can I help you with your " +userData.getPlanName()+ " today</speak>", 
                			"<speak>You can say, things like tell me how my account is doing? </speak>", "true", sessionJson);
        		} else {
                	JSONObject sessionJson = new JSONObject();
        			return buildResponse("SSML", "<speak>Invalid PIN or No Account setup!</speak>", 
                			"", "true", sessionJson);
        		}
        	}
        	 
        	
			
        	 
         } else {
        	JSONObject sessionJson = new JSONObject();
        	return buildResponse("SSML", "<speakInvalid PIN or No Account setup!</speak>", "", "true", sessionJson);
        }
        
        return null;
    }
	
	private String buildResponse(String outputSpeechType, String speech, String repromptspeech, String shouldEndSession, JSONObject sessionJson) {
		
		JSONObject alexaJsonResponseObj = new JSONObject();
		alexaJsonResponseObj.put("version", "1.0");
		alexaJsonResponseObj.put("sessionAttributes", sessionJson);
		
		JSONObject responseObj = new JSONObject();
		responseObj.put("shouldEndSession", shouldEndSession);
		
			JSONObject outputSpeechObj = new JSONObject();
			outputSpeechObj.put("type", outputSpeechType);
			outputSpeechObj.put("ssml", speech);
		
			responseObj.put("outputSpeech", outputSpeechObj);
			
			JSONObject repromptObj = new JSONObject();
			
			JSONObject repromptoutputSpeechObj = new JSONObject();
			repromptoutputSpeechObj.put("type", outputSpeechType);
			repromptoutputSpeechObj.put("ssml", speech);
			
			repromptObj.put("outputSpeech", repromptoutputSpeechObj);
			
			responseObj.put("outputSpeech", outputSpeechObj);
			responseObj.put("reprompt", repromptObj);
		
		alexaJsonResponseObj.put("response", responseObj);
		
        return alexaJsonResponseObj.toJSONString();
		
		
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