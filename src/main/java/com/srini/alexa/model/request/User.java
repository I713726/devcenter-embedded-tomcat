package com.srini.alexa.model.request;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	private String userId;

    public String getUserId ()
    {
        return userId;
    }

    public void setUserId (String userId)
    {
        this.userId = userId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [userId = "+userId+"]";
    }
}
