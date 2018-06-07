package com.srini.alexa.model.response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AlexaResponse {
	
	private String version;
	
	private SessionAttributes sessionAttributes;
	
	private Response response;

    public Response getResponse ()
    {
        return response;
    }

    public void setResponse (Response response)
    {
        this.response = response;
    }

    public SessionAttributes getSessionAttributes ()
    {
        return sessionAttributes;
    }

    public void setSessionAttributes (SessionAttributes sessionAttributes)
    {
        this.sessionAttributes = sessionAttributes;
    }

    public String getVersion ()
    {
        return version;
    }

    public void setVersion (String version)
    {
        this.version = version;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [response = "+response+", sessionAttributes = "+sessionAttributes+", version = "+version+"]";
    }
}
