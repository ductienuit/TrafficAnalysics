package com.trafficanalysics.apisusi.modal;

public class Identity
{
    private String anonymous;

    private String name;

    private String type;

    public String getAnonymous ()
    {
        return anonymous;
    }

    public void setAnonymous (String anonymous)
    {
        this.anonymous = anonymous;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
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
        return "ClassPojo [anonymous = "+anonymous+", name = "+name+", type = "+type+"]";
    }
}
