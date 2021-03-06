package com.srini.alexa.model.request;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Request {
	private String timestamp;

    private String requestId;

    private String locale;

    private String dialogState;

    private String type;

    private Intent intent;

    public String getTimestamp ()
    {
        return timestamp;
    }

    public void setTimestamp (String timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getRequestId ()
    {
        return requestId;
    }

    public void setRequestId (String requestId)
    {
        this.requestId = requestId;
    }

    public String getLocale ()
    {
        return locale;
    }

    public void setLocale (String locale)
    {
        this.locale = locale;
    }

    public String getDialogState ()
    {
        return dialogState;
    }

    public void setDialogState (String dialogState)
    {
        this.dialogState = dialogState;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public Intent getIntent ()
    {
        return intent;
    }

    public void setIntent (Intent intent)
    {
        this.intent = intent;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [timestamp = "+timestamp+", requestId = "+requestId+", locale = "+locale+", dialogState = "+dialogState+", type = "+type+", intent = "+intent+"]";
    }
}
