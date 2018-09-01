// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3;


public final class JavaVersion extends Enum
{

    private JavaVersion(String s, int i, float f, String s1)
    {
        super(s, i);
        value = f;
        name = s1;
    }

    static JavaVersion get(String s)
    {
        if("0.9".equals(s))
            return JAVA_0_9;
        if("1.1".equals(s))
            return JAVA_1_1;
        if("1.2".equals(s))
            return JAVA_1_2;
        if("1.3".equals(s))
            return JAVA_1_3;
        if("1.4".equals(s))
            return JAVA_1_4;
        if("1.5".equals(s))
            return JAVA_1_5;
        if("1.6".equals(s))
            return JAVA_1_6;
        if("1.7".equals(s))
            return JAVA_1_7;
        if("1.8".equals(s))
            return JAVA_1_8;
        else
            return null;
    }

    static JavaVersion getJavaVersion(String s)
    {
        return get(s);
    }

    public static JavaVersion valueOf(String s)
    {
        return (JavaVersion)Enum.valueOf(org/apache/miui/commons/lang3/JavaVersion, s);
    }

    public static JavaVersion[] values()
    {
        return $VALUES;
    }

    public boolean atLeast(JavaVersion javaversion)
    {
        boolean flag;
        if(value >= javaversion.value)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public String toString()
    {
        return name;
    }

    private static final JavaVersion $VALUES[];
    public static final JavaVersion JAVA_0_9;
    public static final JavaVersion JAVA_1_1;
    public static final JavaVersion JAVA_1_2;
    public static final JavaVersion JAVA_1_3;
    public static final JavaVersion JAVA_1_4;
    public static final JavaVersion JAVA_1_5;
    public static final JavaVersion JAVA_1_6;
    public static final JavaVersion JAVA_1_7;
    public static final JavaVersion JAVA_1_8;
    private String name;
    private float value;

    static 
    {
        JAVA_0_9 = new JavaVersion("JAVA_0_9", 0, 1.5F, "0.9");
        JAVA_1_1 = new JavaVersion("JAVA_1_1", 1, 1.1F, "1.1");
        JAVA_1_2 = new JavaVersion("JAVA_1_2", 2, 1.2F, "1.2");
        JAVA_1_3 = new JavaVersion("JAVA_1_3", 3, 1.3F, "1.3");
        JAVA_1_4 = new JavaVersion("JAVA_1_4", 4, 1.4F, "1.4");
        JAVA_1_5 = new JavaVersion("JAVA_1_5", 5, 1.5F, "1.5");
        JAVA_1_6 = new JavaVersion("JAVA_1_6", 6, 1.6F, "1.6");
        JAVA_1_7 = new JavaVersion("JAVA_1_7", 7, 1.7F, "1.7");
        JAVA_1_8 = new JavaVersion("JAVA_1_8", 8, 1.8F, "1.8");
        $VALUES = (new JavaVersion[] {
            JAVA_0_9, JAVA_1_1, JAVA_1_2, JAVA_1_3, JAVA_1_4, JAVA_1_5, JAVA_1_6, JAVA_1_7, JAVA_1_8
        });
    }
}
