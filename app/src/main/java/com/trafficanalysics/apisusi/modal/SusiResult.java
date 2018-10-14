package com.trafficanalysics.apisusi.modal;

public class SusiResult
{
    private String query_date;

    private String count;

    private Session session;

    private String query;

    private Answers[] answers;

    private String language;

    private String answer_time;

    private String client_id;

    private String answer_date;

    public String getQuery_date ()
    {
        return query_date;
    }

    public void setQuery_date (String query_date)
    {
        this.query_date = query_date;
    }

    public String getCount ()
    {
        return count;
    }

    public void setCount (String count)
    {
        this.count = count;
    }

    public Session getSession ()
    {
        return session;
    }

    public void setSession (Session session)
    {
        this.session = session;
    }

    public String getQuery ()
    {
        return query;
    }

    public void setQuery (String query)
    {
        this.query = query;
    }

    public Answers[] getAnswers ()
    {
        return answers;
    }

    public void setAnswers (Answers[] answers)
    {
        this.answers = answers;
    }

    public String getLanguage ()
    {
        return language;
    }

    public void setLanguage (String language)
    {
        this.language = language;
    }

    public String getAnswer_time ()
    {
        return answer_time;
    }

    public void setAnswer_time (String answer_time)
    {
        this.answer_time = answer_time;
    }

    public String getClient_id ()
    {
        return client_id;
    }

    public void setClient_id (String client_id)
    {
        this.client_id = client_id;
    }

    public String getAnswer_date ()
    {
        return answer_date;
    }

    public void setAnswer_date (String answer_date)
    {
        this.answer_date = answer_date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [query_date = "+query_date+", count = "+count+", session = "+session+", query = "+query+", answers = "+answers+", language = "+language+", answer_time = "+answer_time+", client_id = "+client_id+", answer_date = "+answer_date+"]";
    }
}
