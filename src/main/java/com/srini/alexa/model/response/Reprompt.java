package com.srini.alexa.model.response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Reprompt
{
    private OutputSpeech outputSpeech;

    public OutputSpeech getOutputSpeech ()
    {
        return outputSpeech;
    }

    public void setOutputSpeech (OutputSpeech outputSpeech)
    {
        this.outputSpeech = outputSpeech;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [outputSpeech = "+outputSpeech+"]";
    }
}