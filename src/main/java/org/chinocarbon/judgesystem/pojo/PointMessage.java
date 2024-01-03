package org.chinocarbon.judgesystem.pojo;

import org.chinocarbon.judgesystem.enums.PointStatement;

import java.io.Serializable;

/**
 * @author ChinoCarbon
 * @since 2022/4/29-8:18 PM
 */
public class PointMessage implements Serializable
{
    private int id;
    private final PointStatement statement;
    private final long timeConsumed;
    private final long memoryConsumed;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "PointMessage{" +
                "id=" + id +
                ", statement=" + statement +
                ", timeConsumed=" + timeConsumed +
                ", memoryConsumed=" + memoryConsumed +
                '}';
    }

    public PointMessage(int id, PointStatement statement, long timeConsumed, long memoryConsumed)
    {
        this.id = id;
        this.statement = statement;
        this.timeConsumed = timeConsumed;
        this.memoryConsumed = memoryConsumed;
    }

    public PointStatement getStatement()
    {
        return statement;
    }

    public long getTimeConsumed()
    {
        return timeConsumed;
    }

    public long getMemoryConsumed()
    {
        return memoryConsumed;
    }

}
