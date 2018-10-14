package com.trafficanalysics.apisusi.modal;

public class Session
{
    private Identity identity;

    public Identity getIdentity ()
    {
        return identity;
    }

    public void setIdentity (Identity identity)
    {
        this.identity = identity;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [identity = "+identity+"]";
    }
}
