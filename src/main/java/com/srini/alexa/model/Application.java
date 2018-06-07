package com.srini.alexa.model;

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
