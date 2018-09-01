// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;


// Referenced classes of package com.android.internal.util:
//            ArrayUtils

public final class GrowingArrayUtils
{

    private GrowingArrayUtils()
    {
    }

    public static float[] append(float af[], int i, float f)
    {
        if(!_2D_assertionsDisabled && i > af.length)
            throw new AssertionError();
        float af1[] = af;
        if(i + 1 > af.length)
        {
            af1 = ArrayUtils.newUnpaddedFloatArray(growSize(i));
            System.arraycopy(af, 0, af1, 0, i);
        }
        af1[i] = f;
        return af1;
    }

    public static int[] append(int ai[], int i, int j)
    {
        if(!_2D_assertionsDisabled && i > ai.length)
            throw new AssertionError();
        int ai1[] = ai;
        if(i + 1 > ai.length)
        {
            ai1 = ArrayUtils.newUnpaddedIntArray(growSize(i));
            System.arraycopy(ai, 0, ai1, 0, i);
        }
        ai1[i] = j;
        return ai1;
    }

    public static long[] append(long al[], int i, long l)
    {
        if(!_2D_assertionsDisabled && i > al.length)
            throw new AssertionError();
        long al1[] = al;
        if(i + 1 > al.length)
        {
            al1 = ArrayUtils.newUnpaddedLongArray(growSize(i));
            System.arraycopy(al, 0, al1, 0, i);
        }
        al1[i] = l;
        return al1;
    }

    public static Object[] append(Object aobj[], int i, Object obj)
    {
        if(!_2D_assertionsDisabled && i > aobj.length)
            throw new AssertionError();
        Object aobj1[] = aobj;
        if(i + 1 > aobj.length)
        {
            aobj1 = ArrayUtils.newUnpaddedArray(((Object) (aobj)).getClass().getComponentType(), growSize(i));
            System.arraycopy(((Object) (aobj)), 0, ((Object) (aobj1)), 0, i);
        }
        aobj1[i] = obj;
        return aobj1;
    }

    public static boolean[] append(boolean aflag[], int i, boolean flag)
    {
        if(!_2D_assertionsDisabled && i > aflag.length)
            throw new AssertionError();
        boolean aflag1[] = aflag;
        if(i + 1 > aflag.length)
        {
            aflag1 = ArrayUtils.newUnpaddedBooleanArray(growSize(i));
            System.arraycopy(aflag, 0, aflag1, 0, i);
        }
        aflag1[i] = flag;
        return aflag1;
    }

    public static int growSize(int i)
    {
        if(i <= 4)
            i = 8;
        else
            i *= 2;
        return i;
    }

    public static int[] insert(int ai[], int i, int j, int k)
    {
        if(!_2D_assertionsDisabled && i > ai.length)
            throw new AssertionError();
        if(i + 1 <= ai.length)
        {
            System.arraycopy(ai, j, ai, j + 1, i - j);
            ai[j] = k;
            return ai;
        } else
        {
            int ai1[] = ArrayUtils.newUnpaddedIntArray(growSize(i));
            System.arraycopy(ai, 0, ai1, 0, j);
            ai1[j] = k;
            System.arraycopy(ai, j, ai1, j + 1, ai.length - j);
            return ai1;
        }
    }

    public static long[] insert(long al[], int i, int j, long l)
    {
        if(!_2D_assertionsDisabled && i > al.length)
            throw new AssertionError();
        if(i + 1 <= al.length)
        {
            System.arraycopy(al, j, al, j + 1, i - j);
            al[j] = l;
            return al;
        } else
        {
            long al1[] = ArrayUtils.newUnpaddedLongArray(growSize(i));
            System.arraycopy(al, 0, al1, 0, j);
            al1[j] = l;
            System.arraycopy(al, j, al1, j + 1, al.length - j);
            return al1;
        }
    }

    public static Object[] insert(Object aobj[], int i, int j, Object obj)
    {
        if(!_2D_assertionsDisabled && i > aobj.length)
            throw new AssertionError();
        if(i + 1 <= aobj.length)
        {
            System.arraycopy(((Object) (aobj)), j, ((Object) (aobj)), j + 1, i - j);
            aobj[j] = obj;
            return aobj;
        } else
        {
            Object aobj1[] = ArrayUtils.newUnpaddedArray(((Object) (aobj)).getClass().getComponentType(), growSize(i));
            System.arraycopy(((Object) (aobj)), 0, ((Object) (aobj1)), 0, j);
            aobj1[j] = obj;
            System.arraycopy(((Object) (aobj)), j, ((Object) (aobj1)), j + 1, aobj.length - j);
            return aobj1;
        }
    }

    public static boolean[] insert(boolean aflag[], int i, int j, boolean flag)
    {
        if(!_2D_assertionsDisabled && i > aflag.length)
            throw new AssertionError();
        if(i + 1 <= aflag.length)
        {
            System.arraycopy(aflag, j, aflag, j + 1, i - j);
            aflag[j] = flag;
            return aflag;
        } else
        {
            boolean aflag1[] = ArrayUtils.newUnpaddedBooleanArray(growSize(i));
            System.arraycopy(aflag, 0, aflag1, 0, j);
            aflag1[j] = flag;
            System.arraycopy(aflag, j, aflag1, j + 1, aflag.length - j);
            return aflag1;
        }
    }

    static final boolean _2D_assertionsDisabled = com/android/internal/util/GrowingArrayUtils.desiredAssertionStatus() ^ true;

}
