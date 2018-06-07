package com.srini.alexa.model.response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Image
{
    private String largeImageUrl;

    private String smallImageUrl;

    public String getLargeImageUrl ()
    {
        return largeImageUrl;
    }

    public void setLargeImageUrl (String largeImageUrl)
    {
        this.largeImageUrl = largeImageUrl;
    }

    public String getSmallImageUrl ()
    {
        return smallImageUrl;
    }

    public void setSmallImageUrl (String smallImageUrl)
    {
        this.smallImageUrl = smallImageUrl;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [largeImageUrl = "+largeImageUrl+", smallImageUrl = "+smallImageUrl+"]";
    }
}