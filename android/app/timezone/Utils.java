// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.timezone;


final class Utils
{

    private Utils()
    {
    }

    static Object validateConditionalNull(boolean flag, String s, Object obj)
    {
        if(flag)
            return validateNotNull(s, obj);
        else
            return validateNull(s, obj);
    }

    static Object validateNotNull(String s, Object obj)
    {
        if(obj == null)
            throw new NullPointerException((new StringBuilder()).append(s).append(" == null").toString());
        else
            return obj;
    }

    static Object validateNull(String s, Object obj)
    {
        if(obj != null)
            throw new IllegalArgumentException((new StringBuilder()).append(s).append(" != null").toString());
        else
            return null;
    }

    static String validateRulesVersion(String s, String s1)
    {
        validateNotNull(s, s1);
        if(s1.isEmpty())
            throw new IllegalArgumentException((new StringBuilder()).append(s).append(" must not be empty").toString());
        else
            return s1;
    }

    static int validateVersion(String s, int i)
    {
        if(i < 0 || i > 999)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid ").append(s).append(" version=").append(i).toString());
        else
            return i;
    }
}
