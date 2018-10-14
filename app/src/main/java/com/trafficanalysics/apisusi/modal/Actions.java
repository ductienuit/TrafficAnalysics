package com.trafficanalysics.apisusi.modal;

public class Actions
{
    private String expression;

    private String type;

    public String getExpression ()
    {
        return expression;
    }

    public void setExpression (String expression)
    {
        this.expression = expression;
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
        return "ClassPojo [expression = "+expression+", type = "+type+"]";
    }
}
