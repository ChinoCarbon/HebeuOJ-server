package org.chinocarbon.judgesystem.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ChinoCarbon
 * @since 2022/4/30-7:41 PM
 */
public class Result implements Serializable
{

    private List<PointMessage> messages;
    private boolean CE;
    private Judgement judgement;
    private StringBuilder compileMessage;

    @Override
    public String toString()
    {
        return "Result{" +
                "messages=" + messages +
                ", CE=" + CE +
                ", judgement=" + judgement +
                ", compileMessage=" + compileMessage +
                '}';
    }

    public Judgement getJudgement()
    {
        return judgement;
    }

    public void setJudgement(Judgement judgement)
    {
        this.judgement = judgement;
    }

    public Result(Judgement judgement)
    {
        messages = new ArrayList<>();
        compileMessage = new StringBuilder();
        this.judgement = judgement;
    }

    public boolean isCE()
    {
        return CE;
    }

    public void setCE(boolean CE)
    {
        this.CE = CE;
    }

    public Result(boolean CE, StringBuilder compileMessage)
    {
        this.CE = CE;
        this.compileMessage = compileMessage;
    }

    public List<PointMessage> getMessages()
    {
        return messages;
    }

    public void setMessages(List<PointMessage> messages)
    {
        this.messages = messages;
    }

    public StringBuilder getCompileMessage()
    {
        return compileMessage;
    }

    public void setCompileMessage(StringBuilder compileMessage)
    {
        this.compileMessage = compileMessage;
    }

    public void addAMessage(PointMessage pointMessage)
    {
        messages.add(pointMessage);
    }
}
