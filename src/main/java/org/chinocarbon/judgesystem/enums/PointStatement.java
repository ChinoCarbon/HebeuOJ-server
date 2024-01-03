package org.chinocarbon.judgesystem.enums;

/**
 * @author ChinoCarbon
 * @since 2022/4/30-7:25 PM
 */
public enum PointStatement
{
    ACCEPTED(0),
    WRONG_ANSWER(1),
    TIME_LIMIT_EXCEEDED(2),
    MEMORY_LIMIT_EXCEEDED(3),
    RUNTIME_ERROR(4);

    private final int type;

    PointStatement(int type)
    {
        this.type = type;
    }

    public int getType()
    {
        return type;
    }

    public static PointStatement getStatementByString(String s)
    {
        switch (s)
        {
            case "ACCEPTED" -> {return ACCEPTED;}
            case "WRONG_ANSWER" -> {return WRONG_ANSWER;}
            case "TLE" -> {return TIME_LIMIT_EXCEEDED;}
            case "RUNTIME_ERROR" -> {return RUNTIME_ERROR;}
        }
        return null;
    }
}
