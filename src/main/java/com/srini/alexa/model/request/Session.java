package com.srini.alexa.model.request;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Session {
	private Application application;

    private String sessionId;

    private Attributes attributes;

    private User user;

    public Application getApplication ()
    {
        return application;
    }

    public void setApplication (Application application)
    {
        this.application = application;
    }

    
    public String getSessionId ()
    {
        return sessionId;
    }

    public void setSessionId (String sessionId)
    {
        this.sessionId = sessionId;
    }

    public Attributes getAttributes ()
    {
        return attributes;
    }

    public void setAttributes (Attributes attributes)
    {
        this.attributes = attributes;
    }

    public User getUser ()
    {
        return user;
    }

    public void setUser (User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [application = "+application+", sessionId = "+sessionId+", attributes = "+attributes+", user = "+user+"]";
    }
}
