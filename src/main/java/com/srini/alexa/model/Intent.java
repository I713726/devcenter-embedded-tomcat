package com.srini.alexa.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Intent {
	private String confirmationStatus;

    private String name;

    public String getConfirmationStatus ()
    {
        return confirmationStatus;
    }

    public void setConfirmationStatus (String confirmationStatus)
    {
        this.confirmationStatus = confirmationStatus;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [confirmationStatus = "+confirmationStatus+", name = "+name+"]";
    }
}
