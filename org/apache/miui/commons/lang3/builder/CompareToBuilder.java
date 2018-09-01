// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.builder;

import java.lang.reflect.*;
import java.util.Collection;
import java.util.Comparator;
import org.apache.miui.commons.lang3.ArrayUtils;

// Referenced classes of package org.apache.miui.commons.lang3.builder:
//            Builder, ReflectionToStringBuilder

public class CompareToBuilder
    implements Builder
{

    public CompareToBuilder()
    {
        comparison = 0;
    }

    private static void reflectionAppend(Object obj, Object obj1, Class class1, CompareToBuilder comparetobuilder, boolean flag, String as[])
    {
        Field afield[] = class1.getDeclaredFields();
        AccessibleObject.setAccessible(afield, true);
        int i = 0;
        while(i < afield.length && comparetobuilder.comparison == 0) 
        {
            class1 = afield[i];
            if(!ArrayUtils.contains(as, class1.getName()) && class1.getName().indexOf('$') == -1 && (flag || Modifier.isTransient(class1.getModifiers()) ^ true) && Modifier.isStatic(class1.getModifiers()) ^ true)
                try
                {
                    comparetobuilder.append(class1.get(obj), class1.get(obj1));
                }
                // Misplaced declaration of an exception variable
                catch(Object obj)
                {
                    throw new InternalError("Unexpected IllegalAccessException");
                }
            i++;
        }
    }

    public static int reflectionCompare(Object obj, Object obj1)
    {
        return reflectionCompare(obj, obj1, false, null, new String[0]);
    }

    public static int reflectionCompare(Object obj, Object obj1, Collection collection)
    {
        return reflectionCompare(obj, obj1, ReflectionToStringBuilder.toNoNullStringArray(collection));
    }

    public static int reflectionCompare(Object obj, Object obj1, boolean flag)
    {
        return reflectionCompare(obj, obj1, flag, null, new String[0]);
    }

    public static transient int reflectionCompare(Object obj, Object obj1, boolean flag, Class class1, String as[])
    {
        if(obj == obj1)
            return 0;
        if(obj == null || obj1 == null)
            throw new NullPointerException();
        Class class2 = obj.getClass();
        if(!class2.isInstance(obj1))
            throw new ClassCastException();
        CompareToBuilder comparetobuilder = new CompareToBuilder();
        reflectionAppend(obj, obj1, class2, comparetobuilder, flag, as);
        for(; class2.getSuperclass() != null && class2 != class1; reflectionAppend(obj, obj1, class2, comparetobuilder, flag, as))
            class2 = class2.getSuperclass();

        return comparetobuilder.toComparison();
    }

    public static transient int reflectionCompare(Object obj, Object obj1, String as[])
    {
        return reflectionCompare(obj, obj1, false, null, as);
    }

    public CompareToBuilder append(byte byte0, byte byte1)
    {
        int i;
        i = 0;
        if(comparison != 0)
            return this;
        if(byte0 >= byte1) goto _L2; else goto _L1
_L1:
        i = -1;
_L4:
        comparison = i;
        return this;
_L2:
        if(byte0 > byte1)
            i = 1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public CompareToBuilder append(char c, char c1)
    {
        int i;
        i = 0;
        if(comparison != 0)
            return this;
        if(c >= c1) goto _L2; else goto _L1
_L1:
        i = -1;
_L4:
        comparison = i;
        return this;
_L2:
        if(c > c1)
            i = 1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public CompareToBuilder append(double d, double d1)
    {
        if(comparison != 0)
        {
            return this;
        } else
        {
            comparison = Double.compare(d, d1);
            return this;
        }
    }

    public CompareToBuilder append(float f, float f1)
    {
        if(comparison != 0)
        {
            return this;
        } else
        {
            comparison = Float.compare(f, f1);
            return this;
        }
    }

    public CompareToBuilder append(int i, int j)
    {
        int k;
        k = 0;
        if(comparison != 0)
            return this;
        if(i >= j) goto _L2; else goto _L1
_L1:
        k = -1;
_L4:
        comparison = k;
        return this;
_L2:
        if(i > j)
            k = 1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public CompareToBuilder append(long l, long l1)
    {
        int i;
        i = 0;
        if(comparison != 0)
            return this;
        if(l >= l1) goto _L2; else goto _L1
_L1:
        i = -1;
_L4:
        comparison = i;
        return this;
_L2:
        if(l > l1)
            i = 1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public CompareToBuilder append(Object obj, Object obj1)
    {
        return append(obj, obj1, null);
    }

    public CompareToBuilder append(Object obj, Object obj1, Comparator comparator)
    {
        if(comparison != 0)
            return this;
        if(obj == obj1)
            return this;
        if(obj == null)
        {
            comparison = -1;
            return this;
        }
        if(obj1 == null)
        {
            comparison = 1;
            return this;
        }
        if(obj.getClass().isArray())
        {
            if(obj instanceof long[])
                append((long[])obj, (long[])obj1);
            else
            if(obj instanceof int[])
                append((int[])obj, (int[])obj1);
            else
            if(obj instanceof short[])
                append((short[])obj, (short[])obj1);
            else
            if(obj instanceof char[])
                append((char[])obj, (char[])obj1);
            else
            if(obj instanceof byte[])
                append((byte[])obj, (byte[])obj1);
            else
            if(obj instanceof double[])
                append((double[])obj, (double[])obj1);
            else
            if(obj instanceof float[])
                append((float[])obj, (float[])obj1);
            else
            if(obj instanceof boolean[])
                append((boolean[])obj, (boolean[])obj1);
            else
                append((Object[])obj, (Object[])obj1, comparator);
        } else
        if(comparator == null)
            comparison = ((Comparable)obj).compareTo(obj1);
        else
            comparison = comparator.compare(obj, obj1);
        return this;
    }

    public CompareToBuilder append(short word0, short word1)
    {
        int i;
        i = 0;
        if(comparison != 0)
            return this;
        if(word0 >= word1) goto _L2; else goto _L1
_L1:
        i = -1;
_L4:
        comparison = i;
        return this;
_L2:
        if(word0 > word1)
            i = 1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public CompareToBuilder append(boolean flag, boolean flag1)
    {
        if(comparison != 0)
            return this;
        if(flag == flag1)
            return this;
        if(!flag)
            comparison = -1;
        else
            comparison = 1;
        return this;
    }

    public CompareToBuilder append(byte abyte0[], byte abyte1[])
    {
        int i = -1;
        if(comparison != 0)
            return this;
        if(abyte0 == abyte1)
            return this;
        if(abyte0 == null)
        {
            comparison = -1;
            return this;
        }
        if(abyte1 == null)
        {
            comparison = 1;
            return this;
        }
        if(abyte0.length != abyte1.length)
        {
            if(abyte0.length >= abyte1.length)
                i = 1;
            comparison = i;
            return this;
        }
        for(int j = 0; j < abyte0.length && comparison == 0; j++)
            append(abyte0[j], abyte1[j]);

        return this;
    }

    public CompareToBuilder append(char ac[], char ac1[])
    {
        int i = -1;
        if(comparison != 0)
            return this;
        if(ac == ac1)
            return this;
        if(ac == null)
        {
            comparison = -1;
            return this;
        }
        if(ac1 == null)
        {
            comparison = 1;
            return this;
        }
        if(ac.length != ac1.length)
        {
            if(ac.length >= ac1.length)
                i = 1;
            comparison = i;
            return this;
        }
        for(int j = 0; j < ac.length && comparison == 0; j++)
            append(ac[j], ac1[j]);

        return this;
    }

    public CompareToBuilder append(double ad[], double ad1[])
    {
        int i = -1;
        if(comparison != 0)
            return this;
        if(ad == ad1)
            return this;
        if(ad == null)
        {
            comparison = -1;
            return this;
        }
        if(ad1 == null)
        {
            comparison = 1;
            return this;
        }
        if(ad.length != ad1.length)
        {
            if(ad.length >= ad1.length)
                i = 1;
            comparison = i;
            return this;
        }
        for(int j = 0; j < ad.length && comparison == 0; j++)
            append(ad[j], ad1[j]);

        return this;
    }

    public CompareToBuilder append(float af[], float af1[])
    {
        int i = -1;
        if(comparison != 0)
            return this;
        if(af == af1)
            return this;
        if(af == null)
        {
            comparison = -1;
            return this;
        }
        if(af1 == null)
        {
            comparison = 1;
            return this;
        }
        if(af.length != af1.length)
        {
            if(af.length >= af1.length)
                i = 1;
            comparison = i;
            return this;
        }
        for(int j = 0; j < af.length && comparison == 0; j++)
            append(af[j], af1[j]);

        return this;
    }

    public CompareToBuilder append(int ai[], int ai1[])
    {
        int i = -1;
        if(comparison != 0)
            return this;
        if(ai == ai1)
            return this;
        if(ai == null)
        {
            comparison = -1;
            return this;
        }
        if(ai1 == null)
        {
            comparison = 1;
            return this;
        }
        if(ai.length != ai1.length)
        {
            if(ai.length >= ai1.length)
                i = 1;
            comparison = i;
            return this;
        }
        for(int j = 0; j < ai.length && comparison == 0; j++)
            append(ai[j], ai1[j]);

        return this;
    }

    public CompareToBuilder append(long al[], long al1[])
    {
        int i = -1;
        if(comparison != 0)
            return this;
        if(al == al1)
            return this;
        if(al == null)
        {
            comparison = -1;
            return this;
        }
        if(al1 == null)
        {
            comparison = 1;
            return this;
        }
        if(al.length != al1.length)
        {
            if(al.length >= al1.length)
                i = 1;
            comparison = i;
            return this;
        }
        for(int j = 0; j < al.length && comparison == 0; j++)
            append(al[j], al1[j]);

        return this;
    }

    public CompareToBuilder append(Object aobj[], Object aobj1[])
    {
        return append(aobj, aobj1, null);
    }

    public CompareToBuilder append(Object aobj[], Object aobj1[], Comparator comparator)
    {
        int i = -1;
        if(comparison != 0)
            return this;
        if(aobj == aobj1)
            return this;
        if(aobj == null)
        {
            comparison = -1;
            return this;
        }
        if(aobj1 == null)
        {
            comparison = 1;
            return this;
        }
        if(aobj.length != aobj1.length)
        {
            if(aobj.length >= aobj1.length)
                i = 1;
            comparison = i;
            return this;
        }
        for(int j = 0; j < aobj.length && comparison == 0; j++)
            append(aobj[j], aobj1[j], comparator);

        return this;
    }

    public CompareToBuilder append(short aword0[], short aword1[])
    {
        int i = -1;
        if(comparison != 0)
            return this;
        if(aword0 == aword1)
            return this;
        if(aword0 == null)
        {
            comparison = -1;
            return this;
        }
        if(aword1 == null)
        {
            comparison = 1;
            return this;
        }
        if(aword0.length != aword1.length)
        {
            if(aword0.length >= aword1.length)
                i = 1;
            comparison = i;
            return this;
        }
        for(int j = 0; j < aword0.length && comparison == 0; j++)
            append(aword0[j], aword1[j]);

        return this;
    }

    public CompareToBuilder append(boolean aflag[], boolean aflag1[])
    {
        int i = -1;
        if(comparison != 0)
            return this;
        if(aflag == aflag1)
            return this;
        if(aflag == null)
        {
            comparison = -1;
            return this;
        }
        if(aflag1 == null)
        {
            comparison = 1;
            return this;
        }
        if(aflag.length != aflag1.length)
        {
            if(aflag.length >= aflag1.length)
                i = 1;
            comparison = i;
            return this;
        }
        for(int j = 0; j < aflag.length && comparison == 0; j++)
            append(aflag[j], aflag1[j]);

        return this;
    }

    public CompareToBuilder appendSuper(int i)
    {
        if(comparison != 0)
        {
            return this;
        } else
        {
            comparison = i;
            return this;
        }
    }

    public Integer build()
    {
        return Integer.valueOf(toComparison());
    }

    public volatile Object build()
    {
        return build();
    }

    public int toComparison()
    {
        return comparison;
    }

    private int comparison;
}
