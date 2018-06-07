package com.srini.alexa;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.srini.alexa.model.AlexaRequest;
 
@Path("/alexaskill")
public class AlexaSkillService {
 
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say : " + msg;
 
		return Response.status(200).entity(output).build();
 
	}
	
	@POST
    @Path("/alexarequest")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insert(AlexaRequest alexaRequest) {
        return "SUCCESS" + alexaRequest.getVersion();
    }
 
}