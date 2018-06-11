package com.srini.alexa;
 
import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.srini.alexa.model.request.Product;
import com.srini.alexa.model.request.Status;
import com.srini.alexa.model.response.AlexaResponse;
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
 
		return output + json;
 
	}
	
	@POST
    @Path("/alexarequest")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String alexaIn(String alexaRequest) throws Exception {
		parseJsonRequestString(alexaRequest);
//		String output = "Alexa say : " + alexaRequest.getRequest().getType();
//		SessionAttributes sessionAttributes = new SessionAttributes();
//		sessionAttributes.setKey("1");
//		Response response = new Response();
//		OutputSpeech outputSpeech = new OutputSpeech();
//		outputSpeech.setType("PlainText");
//		outputSpeech.setText("Plain text string to speak");
//		outputSpeech.setSsml("<speak>SSML text string to speak</speak>");
//		response.setOutputSpeech(outputSpeech);
//		Reprompt reprompt = new Reprompt();
//		OutputSpeech RepromptOutputSpeech = new OutputSpeech();
//		RepromptOutputSpeech.setType("PlainText");
//		RepromptOutputSpeech.setText("Plain text string to speak");
//		RepromptOutputSpeech.setSsml("<speak>SSML text string to speak</speak>");
//		reprompt.setOutputSpeech(RepromptOutputSpeech);
//		response.setReprompt(reprompt);
//		String shouldEndSession = "false";
//		response.setShouldEndSession(shouldEndSession);
//		response.setCard(new Card());
		return buildResponse();
    }
	
	private void parseJsonRequestString(String jsonRequest) throws Exception {
		//Parse the JSON data present in the string format
		JSONParser parse = new JSONParser();
		//Type caste the parsed json data in json object
		JSONObject jobj = (JSONObject)parse.parse(jsonRequest);
		//Store the JSON object in JSON array as objects (For level 1 array element i.e Results)
		JSONArray jsonarr_1 = (JSONArray) jobj.get("session");
		//Get data for Results array
		for(int i=0;i<jsonarr_1.size();i++)
		{
		  //Store the JSON objects in an array
		  //Get the index of the JSON object and print the values as per the index
		  JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
		  //Store the JSON object in JSON array as objects (For level 2 array element i.e Address Components)
		  JSONArray jsonarr_2 = (JSONArray) jsonobj_1.get("address_components");
		  System.out.println("Elements under results array");
		  System.out.println("\nPlace id: " +jsonobj_1.get("place_id"));
		  System.out.println("Types: " +jsonobj_1.get("types"));
		  //Get data for the Address Components array
		  System.out.println("Elements under address_components array");
		  System.out.println("The long names, short names and types are:");
		  for(int j=0;j<jsonarr_2.size();j++)
		  {
		     //Same just store the JSON objects in an array
		     //Get the index of the JSON objects and print the values as per the index
		     JSONObject jsonobj_2 = (JSONObject) jsonarr_2.get(j);
		     //Store the data as String objects
		     String str_data1 = (String) jsonobj_2.get("long_name");
		     System.out.println(str_data1);
		     String str_data2 = (String) jsonobj_2.get("short_name");
		     System.out.println(str_data2);
		     System.out.println(jsonobj_2.get("types"));
		     System.out.println("\n");
		  }
		}
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
	
	private String buildResponse() {
		String json = Json.createObjectBuilder()
	            .add("key1", "value1")
	            .add("key2", "value2")
	            .build()
	            .toString();
		return json;
	}
	
	String json = Json.createObjectBuilder()
            .add("key1", "value1")
            .add("key2", "value2")
            .build()
            .toString();
	
 
}