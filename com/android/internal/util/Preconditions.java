// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.text.TextUtils;
import java.util.Collection;
import java.util.Iterator;

public class Preconditions
{

    public Preconditions()
    {
    }

    public static void checkArgument(boolean flag)
    {
        if(!flag)
            throw new IllegalArgumentException();
        else
            return;
    }

    public static void checkArgument(boolean flag, Object obj)
    {
        if(!flag)
            throw new IllegalArgumentException(String.valueOf(obj));
        else
            return;
    }

    public static transient void checkArgument(boolean flag, String s, Object aobj[])
    {
        if(!flag)
            throw new IllegalArgumentException(String.format(s, aobj));
        else
            return;
    }

    public static float checkArgumentFinite(float f, String s)
    {
        if(Float.isNaN(f))
            throw new IllegalArgumentException((new StringBuilder()).append(s).append(" must not be NaN").toString());
        if(Float.isInfinite(f))
            throw new IllegalArgumentException((new StringBuilder()).append(s).append(" must not be infinite").toString());
        else
            return f;
    }

    public static float checkArgumentInRange(float f, float f1, float f2, String s)
    {
        if(Float.isNaN(f))
            throw new IllegalArgumentException((new StringBuilder()).append(s).append(" must not be NaN").toString());
        if(f < f1)
            throw new IllegalArgumentException(String.format("%s is out of range of [%f, %f] (too low)", new Object[] {
                s, Float.valueOf(f1), Float.valueOf(f2)
            }));
        if(f > f2)
            throw new IllegalArgumentException(String.format("%s is out of range of [%f, %f] (too high)", new Object[] {
                s, Float.valueOf(f1), Float.valueOf(f2)
            }));
        else
            return f;
    }

    public static int checkArgumentInRange(int i, int j, int k, String s)
    {
        if(i < j)
            throw new IllegalArgumentException(String.format("%s is out of range of [%d, %d] (too low)", new Object[] {
                s, Integer.valueOf(j), Integer.valueOf(k)
            }));
        if(i > k)
            throw new IllegalArgumentException(String.format("%s is out of range of [%d, %d] (too high)", new Object[] {
                s, Integer.valueOf(j), Integer.valueOf(k)
            }));
        else
            return i;
    }

    public static long checkArgumentInRange(long l, long l1, long l2, String s)
    {
        if(l < l1)
            throw new IllegalArgumentException(String.format("%s is out of range of [%d, %d] (too low)", new Object[] {
                s, Long.valueOf(l1), Long.valueOf(l2)
            }));
        if(l > l2)
            throw new IllegalArgumentException(String.format("%s is out of range of [%d, %d] (too high)", new Object[] {
                s, Long.valueOf(l1), Long.valueOf(l2)
            }));
        else
            return l;
    }

    public static int checkArgumentNonnegative(int i)
    {
        if(i < 0)
            throw new IllegalArgumentException();
        else
            return i;
    }

    public static int checkArgumentNonnegative(int i, String s)
    {
        if(i < 0)
            throw new IllegalArgumentException(s);
        else
            return i;
    }

    public static long checkArgumentNonnegative(long l)
    {
        if(l < 0L)
            throw new IllegalArgumentException();
        else
            return l;
    }

    public static long checkArgumentNonnegative(long l, String s)
    {
        if(l < 0L)
            throw new IllegalArgumentException(s);
        else
            return l;
    }

    public static int checkArgumentPositive(int i, String s)
    {
        if(i <= 0)
            throw new IllegalArgumentException(s);
        else
            return i;
    }

    public static float[] checkArrayElementsInRange(float af[], float f, float f1, String s)
    {
        checkNotNull(af, (new StringBuilder()).append(s).append(" must not be null").toString());
        for(int i = 0; i < af.length; i++)
        {
            float f2 = af[i];
            if(Float.isNaN(f2))
                throw new IllegalArgumentException((new StringBuilder()).append(s).append("[").append(i).append("] must not be NaN").toString());
            if(f2 < f)
                throw new IllegalArgumentException(String.format("%s[%d] is out of range of [%f, %f] (too low)", new Object[] {
                    s, Integer.valueOf(i), Float.valueOf(f), Float.valueOf(f1)
                }));
            if(f2 > f1)
                throw new IllegalArgumentException(String.format("%s[%d] is out of range of [%f, %f] (too high)", new Object[] {
                    s, Integer.valueOf(i), Float.valueOf(f), Float.valueOf(f1)
                }));
        }

        return af;
    }

    public static Object[] checkArrayElementsNotNull(Object aobj[], String s)
    {
        if(aobj == null)
            throw new NullPointerException((new StringBuilder()).append(s).append(" must not be null").toString());
        for(int i = 0; i < aobj.length; i++)
            if(aobj[i] == null)
                throw new NullPointerException(String.format("%s[%d] must not be null", new Object[] {
                    s, Integer.valueOf(i)
                }));

        return aobj;
    }

    public static Collection checkCollectionElementsNotNull(Collection collection, String s)
    {
        if(collection == null)
            throw new NullPointerException((new StringBuilder()).append(s).append(" must not be null").toString());
        long l = 0L;
        for(Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            if(iterator.next() == null)
                throw new NullPointerException(String.format("%s[%d] must not be null", new Object[] {
                    s, Long.valueOf(l)
                }));
            l++;
        }

        return collection;
    }

    public static Collection checkCollectionNotEmpty(Collection collection, String s)
    {
        if(collection == null)
            throw new NullPointerException((new StringBuilder()).append(s).append(" must not be null").toString());
        if(collection.isEmpty())
            throw new IllegalArgumentException((new StringBuilder()).append(s).append(" is empty").toString());
        else
            return collection;
    }

    public static int checkFlagsArgument(int i, int j)
    {
        if((i & j) != i)
            throw new IllegalArgumentException((new StringBuilder()).append("Requested flags 0x").append(Integer.toHexString(i)).append(", but only 0x").append(Integer.toHexString(j)).append(" are allowed").toString());
        else
            return i;
    }

    public static Object checkNotNull(Object obj)
    {
        if(obj == null)
            throw new NullPointerException();
        else
            return obj;
    }

    public static Object checkNotNull(Object obj, Object obj1)
    {
        if(obj == null)
            throw new NullPointerException(String.valueOf(obj1));
        else
            return obj;
    }

    public static transient Object checkNotNull(Object obj, String s, Object aobj[])
    {
        if(obj == null)
            throw new NullPointerException(String.format(s, aobj));
        else
            return obj;
    }

    public static void checkState(boolean flag)
    {
        checkState(flag, null);
    }

    public static void checkState(boolean flag, String s)
    {
        if(!flag)
            throw new IllegalStateException(s);
        else
            return;
    }

    public static CharSequence checkStringNotEmpty(CharSequence charsequence)
    {
        if(TextUtils.isEmpty(charsequence))
            throw new IllegalArgumentException();
        else
            return charsequence;
    }

    public static CharSequence checkStringNotEmpty(CharSequence charsequence, Object obj)
    {
        if(TextUtils.isEmpty(charsequence))
            throw new IllegalArgumentException(String.valueOf(obj));
        else
            return charsequence;
    }
}
