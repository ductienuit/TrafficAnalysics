package com.trafficanalysics.apisusi.modal;


public class Answers
{
    private String[] skills;

    private Data[] data;

    private String persona;

    private Actions[] actions;

    private MetaData MetaData;

    public String[] getSkills ()
    {
        return skills;
    }

    public void setSkills (String[] skills)
    {
        this.skills = skills;
    }

    public Data[] getData ()
    {
        return data;
    }

    public void setData (Data[] data)
    {
        this.data = data;
    }

    public String getPersona ()
    {
        return persona;
    }

    public void setPersona (String persona)
    {
        this.persona = persona;
    }

    public Actions[] getActions ()
    {
        return actions;
    }

    public void setActions (Actions[] actions)
    {
        this.actions = actions;
    }

    public MetaData getMetaData ()
    {
        return MetaData;
    }

    public void setMetaData (MetaData MetaData)
    {
        this.MetaData = MetaData;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [skills = "+skills+", data = "+data+", persona = "+persona+", actions = "+actions+", MetaData = "+MetaData+"]";
    }
}
