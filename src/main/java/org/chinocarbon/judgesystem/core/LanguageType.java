package org.chinocarbon.judgesystem.core;

/**
 * @author ChinoCarbon
 * @since 2022/4/29-8:10 PM
 */
public enum LanguageType
{
    C(1),
    CPP(2),
    Java(3);

    private final int type;

    LanguageType(int type)
    {
        this.type = type;
    }

    public static LanguageType getType(int type)
    {
        if(type == 1) return C;
        if(type == 2) return CPP;
        if(type == 3) return Java;
        return null;
    }
}
