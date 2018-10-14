package com.trafficanalysics.apisusi.modal;

public class Data
{
    private String skill_link;


    private String text;


    private String skill;

    private String query;

    private String timezoneOffset;

    private String language;

    public String getSkill_link ()
    {
        return skill_link;
    }

    public void setSkill_link (String skill_link)
    {
        this.skill_link = skill_link;
    }

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public String getSkill ()
    {
        return skill;
    }

    public void setSkill (String skill)
    {
        this.skill = skill;
    }

    public String getQuery ()
    {
        return query;
    }

    public void setQuery (String query)
    {
        this.query = query;
    }

    public String getTimezoneOffset ()
    {
        return timezoneOffset;
    }

    public void setTimezoneOffset (String timezoneOffset)
    {
        this.timezoneOffset = timezoneOffset;
    }

    public String getLanguage ()
    {
        return language;
    }

    public void setLanguage (String language)
    {
        this.language = language;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [skill_link = "+skill_link+", 1 = "+1+", text = "+text+", 0 = "+0+", skill = "+skill+", query = "+query+", timezoneOffset = "+timezoneOffset+", language = "+language+"]";
    }
}
