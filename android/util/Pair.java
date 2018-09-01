// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import java.util.Objects;

public class Pair
{

    public Pair(Object obj, Object obj1)
    {
        first = obj;
        second = obj1;
    }

    public static Pair create(Object obj, Object obj1)
    {
        return new Pair(obj, obj1);
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof Pair))
            return false;
        obj = (Pair)obj;
        if(Objects.equals(((Pair) (obj)).first, first))
            flag = Objects.equals(((Pair) (obj)).second, second);
        return flag;
    }

    public int hashCode()
    {
        int i = 0;
        int j;
        if(first == null)
            j = 0;
        else
            j = first.hashCode();
        if(second != null)
            i = second.hashCode();
        return j ^ i;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Pair{").append(String.valueOf(first)).append(" ").append(String.valueOf(second)).append("}").toString();
    }

    public final Object first;
    public final Object second;
}
