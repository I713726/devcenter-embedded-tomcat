package com.srini.alexa.model.response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OutputSpeech
{
    private String text;

    private String ssml;

    private String type;

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public String getSsml ()
    {
        return ssml;
    }

    public void setSsml (String ssml)
    {
        this.ssml = ssml;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [text = "+text+", ssml = "+ssml+", type = "+type+"]";
    }
}
			
			
