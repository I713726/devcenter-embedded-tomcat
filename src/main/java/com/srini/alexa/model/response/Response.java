package com.srini.alexa.model.response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response
{
    private Reprompt reprompt;

    private Directives[] directives;

    private OutputSpeech outputSpeech;

    private Card card;

    private String shouldEndSession;

    public Reprompt getReprompt ()
    {
        return reprompt;
    }

    public void setReprompt (Reprompt reprompt)
    {
        this.reprompt = reprompt;
    }

    public Directives[] getDirectives ()
    {
        return directives;
    }

    public void setDirectives (Directives[] directives)
    {
        this.directives = directives;
    }

    public OutputSpeech getOutputSpeech ()
    {
        return outputSpeech;
    }

    public void setOutputSpeech (OutputSpeech outputSpeech)
    {
        this.outputSpeech = outputSpeech;
    }

    public Card getCard ()
    {
        return card;
    }

    public void setCard (Card card)
    {
        this.card = card;
    }

    public String getShouldEndSession ()
    {
        return shouldEndSession;
    }

    public void setShouldEndSession (String shouldEndSession)
    {
        this.shouldEndSession = shouldEndSession;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [reprompt = "+reprompt+", directives = "+directives+", outputSpeech = "+outputSpeech+", card = "+card+", shouldEndSession = "+shouldEndSession+"]";
    }
}
			
			
