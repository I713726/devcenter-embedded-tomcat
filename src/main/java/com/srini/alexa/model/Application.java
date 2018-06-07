package com.srini.alexa.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Application {
	 private String applicationId;

	    public String getApplicationId ()
	    {
	        return applicationId;
	    }

	    public void setApplicationId (String applicationId)
	    {
	        this.applicationId = applicationId;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [applicationId = "+applicationId+"]";
	    }
}
