// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.tuple;

import java.io.Serializable;
import org.apache.miui.commons.lang3.ObjectUtils;
import org.apache.miui.commons.lang3.builder.CompareToBuilder;

// Referenced classes of package org.apache.miui.commons.lang3.tuple:
//            ImmutablePair

public abstract class Pair
    implements java.util.Map.Entry, Comparable, Serializable
{

    public Pair()
    {
    }

    public static Pair of(Object obj, Object obj1)
    {
        return new ImmutablePair(obj, obj1);
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((Pair)obj);
    }

    public int compareTo(Pair pair)
    {
        return (new CompareToBuilder()).append(getLeft(), pair.getLeft()).append(getRight(), pair.getRight()).toComparison();
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == this)
            return true;
        if(obj instanceof java.util.Map.Entry)
        {
            obj = (java.util.Map.Entry)obj;
            if(ObjectUtils.equals(getKey(), ((java.util.Map.Entry) (obj)).getKey()))
                flag = ObjectUtils.equals(getValue(), ((java.util.Map.Entry) (obj)).getValue());
            return flag;
        } else
        {
            return false;
        }
    }

    public final Object getKey()
    {
        return getLeft();
    }

    public abstract Object getLeft();

    public abstract Object getRight();

    public Object getValue()
    {
        return getRight();
    }

    public int hashCode()
    {
        int i = 0;
        int j;
        if(getKey() == null)
            j = 0;
        else
            j = getKey().hashCode();
        if(getValue() != null)
            i = getValue().hashCode();
        return j ^ i;
    }

    public String toString()
    {
        return (new StringBuilder()).append('(').append(getLeft()).append(',').append(getRight()).append(')').toString();
    }

    public String toString(String s)
    {
        return String.format(s, new Object[] {
            getLeft(), getRight()
        });
    }

    private static final long serialVersionUID = 0x44c3687a6deaffd1L;
}
