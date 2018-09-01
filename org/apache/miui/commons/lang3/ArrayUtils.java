// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3;

import java.lang.reflect.Array;
import java.util.*;
import org.apache.miui.commons.lang3.builder.EqualsBuilder;
import org.apache.miui.commons.lang3.builder.HashCodeBuilder;
import org.apache.miui.commons.lang3.builder.ToStringBuilder;
import org.apache.miui.commons.lang3.builder.ToStringStyle;
import org.apache.miui.commons.lang3.mutable.MutableInt;

public class ArrayUtils
{

    public ArrayUtils()
    {
    }

    private static Object add(Object obj, int i, Object obj1, Class class1)
    {
        if(obj == null)
            if(i != 0)
            {
                throw new IndexOutOfBoundsException((new StringBuilder()).append("Index: ").append(i).append(", Length: 0").toString());
            } else
            {
                obj = Array.newInstance(class1, 1);
                Array.set(obj, 0, obj1);
                return obj;
            }
        int j = Array.getLength(obj);
        if(i > j || i < 0)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("Index: ").append(i).append(", Length: ").append(j).toString());
        class1 = ((Class) (Array.newInstance(class1, j + 1)));
        System.arraycopy(obj, 0, class1, 0, i);
        Array.set(class1, i, obj1);
        if(i < j)
            System.arraycopy(obj, i, class1, i + 1, j - i);
        return class1;
    }

    public static byte[] add(byte abyte0[], byte byte0)
    {
        abyte0 = (byte[])copyArrayGrow1(abyte0, Byte.TYPE);
        abyte0[abyte0.length - 1] = byte0;
        return abyte0;
    }

    public static byte[] add(byte abyte0[], int i, byte byte0)
    {
        return (byte[])add(abyte0, i, Byte.valueOf(byte0), Byte.TYPE);
    }

    public static char[] add(char ac[], char c)
    {
        ac = (char[])copyArrayGrow1(ac, Character.TYPE);
        ac[ac.length - 1] = c;
        return ac;
    }

    public static char[] add(char ac[], int i, char c)
    {
        return (char[])add(ac, i, Character.valueOf(c), Character.TYPE);
    }

    public static double[] add(double ad[], double d)
    {
        ad = (double[])copyArrayGrow1(ad, Double.TYPE);
        ad[ad.length - 1] = d;
        return ad;
    }

    public static double[] add(double ad[], int i, double d)
    {
        return (double[])add(ad, i, Double.valueOf(d), Double.TYPE);
    }

    public static float[] add(float af[], float f)
    {
        af = (float[])copyArrayGrow1(af, Float.TYPE);
        af[af.length - 1] = f;
        return af;
    }

    public static float[] add(float af[], int i, float f)
    {
        return (float[])add(af, i, Float.valueOf(f), Float.TYPE);
    }

    public static int[] add(int ai[], int i)
    {
        ai = (int[])copyArrayGrow1(ai, Integer.TYPE);
        ai[ai.length - 1] = i;
        return ai;
    }

    public static int[] add(int ai[], int i, int j)
    {
        return (int[])add(ai, i, Integer.valueOf(j), Integer.TYPE);
    }

    public static long[] add(long al[], int i, long l)
    {
        return (long[])add(al, i, Long.valueOf(l), Long.TYPE);
    }

    public static long[] add(long al[], long l)
    {
        al = (long[])copyArrayGrow1(al, Long.TYPE);
        al[al.length - 1] = l;
        return al;
    }

    public static Object[] add(Object aobj[], int i, Object obj)
    {
        Class class1;
        if(aobj != null)
            class1 = ((Object) (aobj)).getClass().getComponentType();
        else
        if(obj != null)
            class1 = obj.getClass();
        else
            throw new IllegalArgumentException("Array and element cannot both be null");
        return (Object[])add(((Object) (aobj)), i, obj, class1);
    }

    public static Object[] add(Object aobj[], Object obj)
    {
        Class class1;
        if(aobj != null)
            class1 = ((Object) (aobj)).getClass();
        else
        if(obj != null)
            class1 = obj.getClass();
        else
            throw new IllegalArgumentException("Arguments cannot both be null");
        aobj = (Object[])copyArrayGrow1(((Object) (aobj)), class1);
        aobj[aobj.length - 1] = obj;
        return aobj;
    }

    public static short[] add(short aword0[], int i, short word0)
    {
        return (short[])add(aword0, i, Short.valueOf(word0), Short.TYPE);
    }

    public static short[] add(short aword0[], short word0)
    {
        aword0 = (short[])copyArrayGrow1(aword0, Short.TYPE);
        aword0[aword0.length - 1] = word0;
        return aword0;
    }

    public static boolean[] add(boolean aflag[], int i, boolean flag)
    {
        return (boolean[])add(aflag, i, Boolean.valueOf(flag), Boolean.TYPE);
    }

    public static boolean[] add(boolean aflag[], boolean flag)
    {
        aflag = (boolean[])copyArrayGrow1(aflag, Boolean.TYPE);
        aflag[aflag.length - 1] = flag;
        return aflag;
    }

    public static transient byte[] addAll(byte abyte0[], byte abyte1[])
    {
        if(abyte0 == null)
            return clone(abyte1);
        if(abyte1 == null)
        {
            return clone(abyte0);
        } else
        {
            byte abyte2[] = new byte[abyte0.length + abyte1.length];
            System.arraycopy(abyte0, 0, abyte2, 0, abyte0.length);
            System.arraycopy(abyte1, 0, abyte2, abyte0.length, abyte1.length);
            return abyte2;
        }
    }

    public static transient char[] addAll(char ac[], char ac1[])
    {
        if(ac == null)
            return clone(ac1);
        if(ac1 == null)
        {
            return clone(ac);
        } else
        {
            char ac2[] = new char[ac.length + ac1.length];
            System.arraycopy(ac, 0, ac2, 0, ac.length);
            System.arraycopy(ac1, 0, ac2, ac.length, ac1.length);
            return ac2;
        }
    }

    public static transient double[] addAll(double ad[], double ad1[])
    {
        if(ad == null)
            return clone(ad1);
        if(ad1 == null)
        {
            return clone(ad);
        } else
        {
            double ad2[] = new double[ad.length + ad1.length];
            System.arraycopy(ad, 0, ad2, 0, ad.length);
            System.arraycopy(ad1, 0, ad2, ad.length, ad1.length);
            return ad2;
        }
    }

    public static transient float[] addAll(float af[], float af1[])
    {
        if(af == null)
            return clone(af1);
        if(af1 == null)
        {
            return clone(af);
        } else
        {
            float af2[] = new float[af.length + af1.length];
            System.arraycopy(af, 0, af2, 0, af.length);
            System.arraycopy(af1, 0, af2, af.length, af1.length);
            return af2;
        }
    }

    public static transient int[] addAll(int ai[], int ai1[])
    {
        if(ai == null)
            return clone(ai1);
        if(ai1 == null)
        {
            return clone(ai);
        } else
        {
            int ai2[] = new int[ai.length + ai1.length];
            System.arraycopy(ai, 0, ai2, 0, ai.length);
            System.arraycopy(ai1, 0, ai2, ai.length, ai1.length);
            return ai2;
        }
    }

    public static transient long[] addAll(long al[], long al1[])
    {
        if(al == null)
            return clone(al1);
        if(al1 == null)
        {
            return clone(al);
        } else
        {
            long al2[] = new long[al.length + al1.length];
            System.arraycopy(al, 0, al2, 0, al.length);
            System.arraycopy(al1, 0, al2, al.length, al1.length);
            return al2;
        }
    }

    public static transient Object[] addAll(Object aobj[], Object aobj1[])
    {
        if(aobj == null)
            return clone(aobj1);
        if(aobj1 == null)
            return clone(aobj);
        Class class1 = ((Object) (aobj)).getClass().getComponentType();
        Object aobj2[] = (Object[])Array.newInstance(class1, aobj.length + aobj1.length);
        System.arraycopy(((Object) (aobj)), 0, ((Object) (aobj2)), 0, aobj.length);
        try
        {
            System.arraycopy(((Object) (aobj1)), 0, ((Object) (aobj2)), aobj.length, aobj1.length);
        }
        // Misplaced declaration of an exception variable
        catch(Object aobj[])
        {
            aobj1 = ((Object) (aobj1)).getClass().getComponentType();
            if(!class1.isAssignableFrom(((Class) (aobj1))))
                throw new IllegalArgumentException((new StringBuilder()).append("Cannot store ").append(((Class) (aobj1)).getName()).append(" in an array of ").append(class1.getName()).toString(), ((Throwable) (aobj)));
            else
                throw aobj;
        }
        return aobj2;
    }

    public static transient short[] addAll(short aword0[], short aword1[])
    {
        if(aword0 == null)
            return clone(aword1);
        if(aword1 == null)
        {
            return clone(aword0);
        } else
        {
            short aword2[] = new short[aword0.length + aword1.length];
            System.arraycopy(aword0, 0, aword2, 0, aword0.length);
            System.arraycopy(aword1, 0, aword2, aword0.length, aword1.length);
            return aword2;
        }
    }

    public static transient boolean[] addAll(boolean aflag[], boolean aflag1[])
    {
        if(aflag == null)
            return clone(aflag1);
        if(aflag1 == null)
        {
            return clone(aflag);
        } else
        {
            boolean aflag2[] = new boolean[aflag.length + aflag1.length];
            System.arraycopy(aflag, 0, aflag2, 0, aflag.length);
            System.arraycopy(aflag1, 0, aflag2, aflag.length, aflag1.length);
            return aflag2;
        }
    }

    public static byte[] clone(byte abyte0[])
    {
        if(abyte0 == null)
            return null;
        else
            return (byte[])abyte0.clone();
    }

    public static char[] clone(char ac[])
    {
        if(ac == null)
            return null;
        else
            return (char[])ac.clone();
    }

    public static double[] clone(double ad[])
    {
        if(ad == null)
            return null;
        else
            return (double[])ad.clone();
    }

    public static float[] clone(float af[])
    {
        if(af == null)
            return null;
        else
            return (float[])af.clone();
    }

    public static int[] clone(int ai[])
    {
        if(ai == null)
            return null;
        else
            return (int[])ai.clone();
    }

    public static long[] clone(long al[])
    {
        if(al == null)
            return null;
        else
            return (long[])al.clone();
    }

    public static Object[] clone(Object aobj[])
    {
        if(aobj == null)
            return null;
        else
            return (Object[])aobj.clone();
    }

    public static short[] clone(short aword0[])
    {
        if(aword0 == null)
            return null;
        else
            return (short[])aword0.clone();
    }

    public static boolean[] clone(boolean aflag[])
    {
        if(aflag == null)
            return null;
        else
            return (boolean[])aflag.clone();
    }

    public static boolean contains(byte abyte0[], byte byte0)
    {
        boolean flag;
        if(indexOf(abyte0, byte0) != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean contains(char ac[], char c)
    {
        boolean flag;
        if(indexOf(ac, c) != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean contains(double ad[], double d)
    {
        boolean flag;
        if(indexOf(ad, d) != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean contains(double ad[], double d, double d1)
    {
        boolean flag = false;
        if(indexOf(ad, d, 0, d1) != -1)
            flag = true;
        return flag;
    }

    public static boolean contains(float af[], float f)
    {
        boolean flag;
        if(indexOf(af, f) != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean contains(int ai[], int i)
    {
        boolean flag;
        if(indexOf(ai, i) != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean contains(long al[], long l)
    {
        boolean flag;
        if(indexOf(al, l) != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean contains(Object aobj[], Object obj)
    {
        boolean flag;
        if(indexOf(aobj, obj) != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean contains(short aword0[], short word0)
    {
        boolean flag;
        if(indexOf(aword0, word0) != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean contains(boolean aflag[], boolean flag)
    {
        if(indexOf(aflag, flag) != -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static Object copyArrayGrow1(Object obj, Class class1)
    {
        if(obj != null)
        {
            int i = Array.getLength(obj);
            class1 = ((Class) (Array.newInstance(obj.getClass().getComponentType(), i + 1)));
            System.arraycopy(obj, 0, class1, 0, i);
            return class1;
        } else
        {
            return Array.newInstance(class1, 1);
        }
    }

    private static int[] extractIndices(HashSet hashset)
    {
        int ai[] = new int[hashset.size()];
        int i = 0;
        for(hashset = hashset.iterator(); hashset.hasNext();)
        {
            ai[i] = ((Integer)hashset.next()).intValue();
            i++;
        }

        return ai;
    }

    public static int getLength(Object obj)
    {
        if(obj == null)
            return 0;
        else
            return Array.getLength(obj);
    }

    public static int hashCode(Object obj)
    {
        return (new HashCodeBuilder()).append(obj).toHashCode();
    }

    public static int indexOf(byte abyte0[], byte byte0)
    {
        return indexOf(abyte0, byte0, 0);
    }

    public static int indexOf(byte abyte0[], byte byte0, int i)
    {
        if(abyte0 == null)
            return -1;
        int j = i;
        if(i < 0)
            j = 0;
        for(i = j; i < abyte0.length; i++)
            if(byte0 == abyte0[i])
                return i;

        return -1;
    }

    public static int indexOf(char ac[], char c)
    {
        return indexOf(ac, c, 0);
    }

    public static int indexOf(char ac[], char c, int i)
    {
        if(ac == null)
            return -1;
        int j = i;
        if(i < 0)
            j = 0;
        for(; j < ac.length; j++)
            if(c == ac[j])
                return j;

        return -1;
    }

    public static int indexOf(double ad[], double d)
    {
        return indexOf(ad, d, 0);
    }

    public static int indexOf(double ad[], double d, double d1)
    {
        return indexOf(ad, d, 0, d1);
    }

    public static int indexOf(double ad[], double d, int i)
    {
        if(isEmpty(ad))
            return -1;
        int j = i;
        if(i < 0)
            j = 0;
        for(; j < ad.length; j++)
            if(d == ad[j])
                return j;

        return -1;
    }

    public static int indexOf(double ad[], double d, int i, double d1)
    {
        if(isEmpty(ad))
            return -1;
        int j = i;
        if(i < 0)
            j = 0;
        for(; j < ad.length; j++)
            if(ad[j] >= d - d1 && ad[j] <= d + d1)
                return j;

        return -1;
    }

    public static int indexOf(float af[], float f)
    {
        return indexOf(af, f, 0);
    }

    public static int indexOf(float af[], float f, int i)
    {
        if(isEmpty(af))
            return -1;
        int j = i;
        if(i < 0)
            j = 0;
        for(i = j; i < af.length; i++)
            if(f == af[i])
                return i;

        return -1;
    }

    public static int indexOf(int ai[], int i)
    {
        return indexOf(ai, i, 0);
    }

    public static int indexOf(int ai[], int i, int j)
    {
        if(ai == null)
            return -1;
        int k = j;
        if(j < 0)
            k = 0;
        for(; k < ai.length; k++)
            if(i == ai[k])
                return k;

        return -1;
    }

    public static int indexOf(long al[], long l)
    {
        return indexOf(al, l, 0);
    }

    public static int indexOf(long al[], long l, int i)
    {
        if(al == null)
            return -1;
        int j = i;
        if(i < 0)
            j = 0;
        for(i = j; i < al.length; i++)
            if(l == al[i])
                return i;

        return -1;
    }

    public static int indexOf(Object aobj[], Object obj)
    {
        return indexOf(aobj, obj, 0);
    }

    public static int indexOf(Object aobj[], Object obj, int i)
    {
        if(aobj == null)
            return -1;
        int j = i;
        if(i < 0)
            j = 0;
        if(obj == null)
            for(i = j; i < aobj.length; i++)
                if(aobj[i] == null)
                    return i;

        else
        if(((Object) (aobj)).getClass().getComponentType().isInstance(obj))
            for(i = j; i < aobj.length; i++)
                if(obj.equals(aobj[i]))
                    return i;

        return -1;
    }

    public static int indexOf(short aword0[], short word0)
    {
        return indexOf(aword0, word0, 0);
    }

    public static int indexOf(short aword0[], short word0, int i)
    {
        if(aword0 == null)
            return -1;
        int j = i;
        if(i < 0)
            j = 0;
        for(; j < aword0.length; j++)
            if(word0 == aword0[j])
                return j;

        return -1;
    }

    public static int indexOf(boolean aflag[], boolean flag)
    {
        return indexOf(aflag, flag, 0);
    }

    public static int indexOf(boolean aflag[], boolean flag, int i)
    {
        if(isEmpty(aflag))
            return -1;
        int j = i;
        if(i < 0)
            j = 0;
        for(; j < aflag.length; j++)
            if(flag == aflag[j])
                return j;

        return -1;
    }

    public static boolean isEmpty(byte abyte0[])
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(abyte0 != null)
            if(abyte0.length == 0)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static boolean isEmpty(char ac[])
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(ac != null)
            if(ac.length == 0)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static boolean isEmpty(double ad[])
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(ad != null)
            if(ad.length == 0)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static boolean isEmpty(float af[])
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(af != null)
            if(af.length == 0)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static boolean isEmpty(int ai[])
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(ai != null)
            if(ai.length == 0)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static boolean isEmpty(long al[])
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(al != null)
            if(al.length == 0)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static boolean isEmpty(Object aobj[])
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(aobj != null)
            if(aobj.length == 0)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static boolean isEmpty(short aword0[])
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(aword0 != null)
            if(aword0.length == 0)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static boolean isEmpty(boolean aflag[])
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(aflag != null)
            if(aflag.length == 0)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static boolean isEquals(Object obj, Object obj1)
    {
        return (new EqualsBuilder()).append(obj, obj1).isEquals();
    }

    public static boolean isNotEmpty(byte abyte0[])
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(abyte0 != null)
        {
            flag1 = flag;
            if(abyte0.length != 0)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isNotEmpty(char ac[])
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(ac != null)
        {
            flag1 = flag;
            if(ac.length != 0)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isNotEmpty(double ad[])
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(ad != null)
        {
            flag1 = flag;
            if(ad.length != 0)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isNotEmpty(float af[])
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(af != null)
        {
            flag1 = flag;
            if(af.length != 0)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isNotEmpty(int ai[])
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(ai != null)
        {
            flag1 = flag;
            if(ai.length != 0)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isNotEmpty(long al[])
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(al != null)
        {
            flag1 = flag;
            if(al.length != 0)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isNotEmpty(Object aobj[])
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(aobj != null)
        {
            flag1 = flag;
            if(aobj.length != 0)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isNotEmpty(short aword0[])
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(aword0 != null)
        {
            flag1 = flag;
            if(aword0.length != 0)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isNotEmpty(boolean aflag[])
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(aflag != null)
        {
            flag1 = flag;
            if(aflag.length != 0)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isSameLength(byte abyte0[], byte abyte1[])
    {
        while(abyte0 == null && abyte1 != null && abyte1.length > 0 || abyte1 == null && abyte0 != null && abyte0.length > 0 || abyte0 != null && abyte1 != null && abyte0.length != abyte1.length) 
            return false;
        return true;
    }

    public static boolean isSameLength(char ac[], char ac1[])
    {
        while(ac == null && ac1 != null && ac1.length > 0 || ac1 == null && ac != null && ac.length > 0 || ac != null && ac1 != null && ac.length != ac1.length) 
            return false;
        return true;
    }

    public static boolean isSameLength(double ad[], double ad1[])
    {
        while(ad == null && ad1 != null && ad1.length > 0 || ad1 == null && ad != null && ad.length > 0 || ad != null && ad1 != null && ad.length != ad1.length) 
            return false;
        return true;
    }

    public static boolean isSameLength(float af[], float af1[])
    {
        while(af == null && af1 != null && af1.length > 0 || af1 == null && af != null && af.length > 0 || af != null && af1 != null && af.length != af1.length) 
            return false;
        return true;
    }

    public static boolean isSameLength(int ai[], int ai1[])
    {
        while(ai == null && ai1 != null && ai1.length > 0 || ai1 == null && ai != null && ai.length > 0 || ai != null && ai1 != null && ai.length != ai1.length) 
            return false;
        return true;
    }

    public static boolean isSameLength(long al[], long al1[])
    {
        while(al == null && al1 != null && al1.length > 0 || al1 == null && al != null && al.length > 0 || al != null && al1 != null && al.length != al1.length) 
            return false;
        return true;
    }

    public static boolean isSameLength(Object aobj[], Object aobj1[])
    {
        while(aobj == null && aobj1 != null && aobj1.length > 0 || aobj1 == null && aobj != null && aobj.length > 0 || aobj != null && aobj1 != null && aobj.length != aobj1.length) 
            return false;
        return true;
    }

    public static boolean isSameLength(short aword0[], short aword1[])
    {
        while(aword0 == null && aword1 != null && aword1.length > 0 || aword1 == null && aword0 != null && aword0.length > 0 || aword0 != null && aword1 != null && aword0.length != aword1.length) 
            return false;
        return true;
    }

    public static boolean isSameLength(boolean aflag[], boolean aflag1[])
    {
        while(aflag == null && aflag1 != null && aflag1.length > 0 || aflag1 == null && aflag != null && aflag.length > 0 || aflag != null && aflag1 != null && aflag.length != aflag1.length) 
            return false;
        return true;
    }

    public static boolean isSameType(Object obj, Object obj1)
    {
        if(obj == null || obj1 == null)
            throw new IllegalArgumentException("The Array must not be null");
        else
            return obj.getClass().getName().equals(obj1.getClass().getName());
    }

    public static int lastIndexOf(byte abyte0[], byte byte0)
    {
        return lastIndexOf(abyte0, byte0, 0x7fffffff);
    }

    public static int lastIndexOf(byte abyte0[], byte byte0, int i)
    {
        if(abyte0 == null)
            return -1;
        if(i < 0)
            return -1;
        int j = i;
        if(i >= abyte0.length)
            j = abyte0.length - 1;
        for(i = j; i >= 0; i--)
            if(byte0 == abyte0[i])
                return i;

        return -1;
    }

    public static int lastIndexOf(char ac[], char c)
    {
        return lastIndexOf(ac, c, 0x7fffffff);
    }

    public static int lastIndexOf(char ac[], char c, int i)
    {
        if(ac == null)
            return -1;
        if(i < 0)
            return -1;
        int j = i;
        if(i >= ac.length)
            j = ac.length - 1;
        for(; j >= 0; j--)
            if(c == ac[j])
                return j;

        return -1;
    }

    public static int lastIndexOf(double ad[], double d)
    {
        return lastIndexOf(ad, d, 0x7fffffff);
    }

    public static int lastIndexOf(double ad[], double d, double d1)
    {
        return lastIndexOf(ad, d, 0x7fffffff, d1);
    }

    public static int lastIndexOf(double ad[], double d, int i)
    {
        if(isEmpty(ad))
            return -1;
        if(i < 0)
            return -1;
        int j = i;
        if(i >= ad.length)
            j = ad.length - 1;
        for(; j >= 0; j--)
            if(d == ad[j])
                return j;

        return -1;
    }

    public static int lastIndexOf(double ad[], double d, int i, double d1)
    {
        if(isEmpty(ad))
            return -1;
        if(i < 0)
            return -1;
        int j = i;
        if(i >= ad.length)
            j = ad.length - 1;
        for(i = j; i >= 0; i--)
            if(ad[i] >= d - d1 && ad[i] <= d + d1)
                return i;

        return -1;
    }

    public static int lastIndexOf(float af[], float f)
    {
        return lastIndexOf(af, f, 0x7fffffff);
    }

    public static int lastIndexOf(float af[], float f, int i)
    {
        if(isEmpty(af))
            return -1;
        if(i < 0)
            return -1;
        int j = i;
        if(i >= af.length)
            j = af.length - 1;
        for(i = j; i >= 0; i--)
            if(f == af[i])
                return i;

        return -1;
    }

    public static int lastIndexOf(int ai[], int i)
    {
        return lastIndexOf(ai, i, 0x7fffffff);
    }

    public static int lastIndexOf(int ai[], int i, int j)
    {
        if(ai == null)
            return -1;
        if(j < 0)
            return -1;
        int k = j;
        if(j >= ai.length)
            k = ai.length - 1;
        for(j = k; j >= 0; j--)
            if(i == ai[j])
                return j;

        return -1;
    }

    public static int lastIndexOf(long al[], long l)
    {
        return lastIndexOf(al, l, 0x7fffffff);
    }

    public static int lastIndexOf(long al[], long l, int i)
    {
        if(al == null)
            return -1;
        if(i < 0)
            return -1;
        int j = i;
        if(i >= al.length)
            j = al.length - 1;
        for(; j >= 0; j--)
            if(l == al[j])
                return j;

        return -1;
    }

    public static int lastIndexOf(Object aobj[], Object obj)
    {
        return lastIndexOf(aobj, obj, 0x7fffffff);
    }

    public static int lastIndexOf(Object aobj[], Object obj, int i)
    {
        if(aobj == null)
            return -1;
        if(i < 0)
            return -1;
        int j = i;
        if(i >= aobj.length)
            j = aobj.length - 1;
        if(obj == null)
            for(; j >= 0; j--)
                if(aobj[j] == null)
                    return j;

        else
        if(((Object) (aobj)).getClass().getComponentType().isInstance(obj))
            for(; j >= 0; j--)
                if(obj.equals(aobj[j]))
                    return j;

        return -1;
    }

    public static int lastIndexOf(short aword0[], short word0)
    {
        return lastIndexOf(aword0, word0, 0x7fffffff);
    }

    public static int lastIndexOf(short aword0[], short word0, int i)
    {
        if(aword0 == null)
            return -1;
        if(i < 0)
            return -1;
        int j = i;
        if(i >= aword0.length)
            j = aword0.length - 1;
        for(; j >= 0; j--)
            if(word0 == aword0[j])
                return j;

        return -1;
    }

    public static int lastIndexOf(boolean aflag[], boolean flag)
    {
        return lastIndexOf(aflag, flag, 0x7fffffff);
    }

    public static int lastIndexOf(boolean aflag[], boolean flag, int i)
    {
        if(isEmpty(aflag))
            return -1;
        if(i < 0)
            return -1;
        int j = i;
        if(i >= aflag.length)
            j = aflag.length - 1;
        for(; j >= 0; j--)
            if(flag == aflag[j])
                return j;

        return -1;
    }

    public static byte[] nullToEmpty(byte abyte0[])
    {
        if(abyte0 == null || abyte0.length == 0)
            return EMPTY_BYTE_ARRAY;
        else
            return abyte0;
    }

    public static char[] nullToEmpty(char ac[])
    {
        if(ac == null || ac.length == 0)
            return EMPTY_CHAR_ARRAY;
        else
            return ac;
    }

    public static double[] nullToEmpty(double ad[])
    {
        if(ad == null || ad.length == 0)
            return EMPTY_DOUBLE_ARRAY;
        else
            return ad;
    }

    public static float[] nullToEmpty(float af[])
    {
        if(af == null || af.length == 0)
            return EMPTY_FLOAT_ARRAY;
        else
            return af;
    }

    public static int[] nullToEmpty(int ai[])
    {
        if(ai == null || ai.length == 0)
            return EMPTY_INT_ARRAY;
        else
            return ai;
    }

    public static long[] nullToEmpty(long al[])
    {
        if(al == null || al.length == 0)
            return EMPTY_LONG_ARRAY;
        else
            return al;
    }

    public static Boolean[] nullToEmpty(Boolean aboolean[])
    {
        if(aboolean == null || aboolean.length == 0)
            return EMPTY_BOOLEAN_OBJECT_ARRAY;
        else
            return aboolean;
    }

    public static Byte[] nullToEmpty(Byte abyte[])
    {
        if(abyte == null || abyte.length == 0)
            return EMPTY_BYTE_OBJECT_ARRAY;
        else
            return abyte;
    }

    public static Character[] nullToEmpty(Character acharacter[])
    {
        if(acharacter == null || acharacter.length == 0)
            return EMPTY_CHARACTER_OBJECT_ARRAY;
        else
            return acharacter;
    }

    public static Double[] nullToEmpty(Double adouble[])
    {
        if(adouble == null || adouble.length == 0)
            return EMPTY_DOUBLE_OBJECT_ARRAY;
        else
            return adouble;
    }

    public static Float[] nullToEmpty(Float afloat[])
    {
        if(afloat == null || afloat.length == 0)
            return EMPTY_FLOAT_OBJECT_ARRAY;
        else
            return afloat;
    }

    public static Integer[] nullToEmpty(Integer ainteger[])
    {
        if(ainteger == null || ainteger.length == 0)
            return EMPTY_INTEGER_OBJECT_ARRAY;
        else
            return ainteger;
    }

    public static Long[] nullToEmpty(Long along[])
    {
        if(along == null || along.length == 0)
            return EMPTY_LONG_OBJECT_ARRAY;
        else
            return along;
    }

    public static Object[] nullToEmpty(Object aobj[])
    {
        if(aobj == null || aobj.length == 0)
            return EMPTY_OBJECT_ARRAY;
        else
            return aobj;
    }

    public static Short[] nullToEmpty(Short ashort[])
    {
        if(ashort == null || ashort.length == 0)
            return EMPTY_SHORT_OBJECT_ARRAY;
        else
            return ashort;
    }

    public static String[] nullToEmpty(String as[])
    {
        if(as == null || as.length == 0)
            return EMPTY_STRING_ARRAY;
        else
            return as;
    }

    public static short[] nullToEmpty(short aword0[])
    {
        if(aword0 == null || aword0.length == 0)
            return EMPTY_SHORT_ARRAY;
        else
            return aword0;
    }

    public static boolean[] nullToEmpty(boolean aflag[])
    {
        if(aflag == null || aflag.length == 0)
            return EMPTY_BOOLEAN_ARRAY;
        else
            return aflag;
    }

    private static Object remove(Object obj, int i)
    {
        int j = getLength(obj);
        if(i < 0 || i >= j)
            throw new IndexOutOfBoundsException((new StringBuilder()).append("Index: ").append(i).append(", Length: ").append(j).toString());
        Object obj1 = Array.newInstance(obj.getClass().getComponentType(), j - 1);
        System.arraycopy(obj, 0, obj1, 0, i);
        if(i < j - 1)
            System.arraycopy(obj, i + 1, obj1, i, j - i - 1);
        return obj1;
    }

    public static byte[] remove(byte abyte0[], int i)
    {
        return (byte[])remove(abyte0, i);
    }

    public static char[] remove(char ac[], int i)
    {
        return (char[])remove(ac, i);
    }

    public static double[] remove(double ad[], int i)
    {
        return (double[])remove(ad, i);
    }

    public static float[] remove(float af[], int i)
    {
        return (float[])remove(af, i);
    }

    public static int[] remove(int ai[], int i)
    {
        return (int[])remove(ai, i);
    }

    public static long[] remove(long al[], int i)
    {
        return (long[])remove(al, i);
    }

    public static Object[] remove(Object aobj[], int i)
    {
        return (Object[])remove(((Object) (aobj)), i);
    }

    public static short[] remove(short aword0[], int i)
    {
        return (short[])remove(aword0, i);
    }

    public static boolean[] remove(boolean aflag[], int i)
    {
        return (boolean[])remove(aflag, i);
    }

    private static transient Object removeAll(Object obj, int ai[])
    {
        int i = getLength(obj);
        int j = 0;
        int k = 0;
        if(isNotEmpty(ai))
        {
            Arrays.sort(ai);
            j = ai.length;
            int i1 = i;
            do
            {
                int l1 = j - 1;
                j = k;
                if(l1 < 0)
                    break;
                int j2 = ai[l1];
                if(j2 < 0 || j2 >= i)
                    throw new IndexOutOfBoundsException((new StringBuilder()).append("Index: ").append(j2).append(", Length: ").append(i).toString());
                j = l1;
                if(j2 < i1)
                {
                    k++;
                    i1 = j2;
                    j = l1;
                }
            } while(true);
        }
        Object obj1 = Array.newInstance(obj.getClass().getComponentType(), i - j);
        if(j < i)
        {
            int j1 = i;
            i -= j;
            int l = ai.length - 1;
            j = j1;
            while(l >= 0) 
            {
                int i2 = ai[l];
                int k1 = i;
                if(j - i2 > 1)
                {
                    j = j - i2 - 1;
                    k1 = i - j;
                    System.arraycopy(obj, i2 + 1, obj1, k1, j);
                }
                j = i2;
                l--;
                i = k1;
            }
            if(j > 0)
                System.arraycopy(obj, 0, obj1, 0, j);
        }
        return obj1;
    }

    public static transient byte[] removeAll(byte abyte0[], int ai[])
    {
        return (byte[])removeAll(abyte0, clone(ai));
    }

    public static transient char[] removeAll(char ac[], int ai[])
    {
        return (char[])removeAll(ac, clone(ai));
    }

    public static transient double[] removeAll(double ad[], int ai[])
    {
        return (double[])removeAll(ad, clone(ai));
    }

    public static transient float[] removeAll(float af[], int ai[])
    {
        return (float[])removeAll(af, clone(ai));
    }

    public static transient int[] removeAll(int ai[], int ai1[])
    {
        return (int[])removeAll(ai, clone(ai1));
    }

    public static transient long[] removeAll(long al[], int ai[])
    {
        return (long[])removeAll(al, clone(ai));
    }

    public static transient Object[] removeAll(Object aobj[], int ai[])
    {
        return (Object[])removeAll(((Object) (aobj)), clone(ai));
    }

    public static transient short[] removeAll(short aword0[], int ai[])
    {
        return (short[])removeAll(aword0, clone(ai));
    }

    public static transient boolean[] removeAll(boolean aflag[], int ai[])
    {
        return (boolean[])removeAll(aflag, clone(ai));
    }

    public static byte[] removeElement(byte abyte0[], byte byte0)
    {
        int i = indexOf(abyte0, byte0);
        if(i == -1)
            return clone(abyte0);
        else
            return remove(abyte0, i);
    }

    public static char[] removeElement(char ac[], char c)
    {
        int i = indexOf(ac, c);
        if(i == -1)
            return clone(ac);
        else
            return remove(ac, i);
    }

    public static double[] removeElement(double ad[], double d)
    {
        int i = indexOf(ad, d);
        if(i == -1)
            return clone(ad);
        else
            return remove(ad, i);
    }

    public static float[] removeElement(float af[], float f)
    {
        int i = indexOf(af, f);
        if(i == -1)
            return clone(af);
        else
            return remove(af, i);
    }

    public static int[] removeElement(int ai[], int i)
    {
        i = indexOf(ai, i);
        if(i == -1)
            return clone(ai);
        else
            return remove(ai, i);
    }

    public static long[] removeElement(long al[], long l)
    {
        int i = indexOf(al, l);
        if(i == -1)
            return clone(al);
        else
            return remove(al, i);
    }

    public static Object[] removeElement(Object aobj[], Object obj)
    {
        int i = indexOf(aobj, obj);
        if(i == -1)
            return clone(aobj);
        else
            return remove(aobj, i);
    }

    public static short[] removeElement(short aword0[], short word0)
    {
        int i = indexOf(aword0, word0);
        if(i == -1)
            return clone(aword0);
        else
            return remove(aword0, i);
    }

    public static boolean[] removeElement(boolean aflag[], boolean flag)
    {
        int i = indexOf(aflag, flag);
        if(i == -1)
            return clone(aflag);
        else
            return remove(aflag, i);
    }

    public static transient byte[] removeElements(byte abyte0[], byte abyte1[])
    {
        Object obj;
        if(isEmpty(abyte0) || isEmpty(abyte1))
            return clone(abyte0);
        obj = new HashMap(abyte1.length);
        int i = 0;
        int k = abyte1.length;
        while(i < k) 
        {
            Byte byte1 = Byte.valueOf(abyte1[i]);
            MutableInt mutableint = (MutableInt)((HashMap) (obj)).get(byte1);
            if(mutableint == null)
                ((HashMap) (obj)).put(byte1, new MutableInt(1));
            else
                mutableint.increment();
            i++;
        }
        abyte1 = new HashSet();
        obj = ((HashMap) (obj)).entrySet().iterator();
_L2:
        int j;
        int l;
        Byte byte2;
        int i1;
        if(!((Iterator) (obj)).hasNext())
            break MISSING_BLOCK_LABEL_207;
        java.util.Map.Entry entry = (java.util.Map.Entry)((Iterator) (obj)).next();
        byte2 = (Byte)entry.getKey();
        l = 0;
        j = 0;
        i1 = ((MutableInt)entry.getValue()).intValue();
_L4:
        if(j >= i1) goto _L2; else goto _L1
_L1:
        l = indexOf(abyte0, byte2.byteValue(), l);
        if(l < 0) goto _L2; else goto _L3
_L3:
        abyte1.add(Integer.valueOf(l));
        j++;
        l++;
          goto _L4
        return removeAll(abyte0, extractIndices(abyte1));
    }

    public static transient char[] removeElements(char ac[], char ac1[])
    {
        Object obj;
        if(isEmpty(ac) || isEmpty(ac1))
            return clone(ac);
        obj = new HashMap(ac1.length);
        int i = 0;
        int k = ac1.length;
        while(i < k) 
        {
            Character character = Character.valueOf(ac1[i]);
            MutableInt mutableint = (MutableInt)((HashMap) (obj)).get(character);
            if(mutableint == null)
                ((HashMap) (obj)).put(character, new MutableInt(1));
            else
                mutableint.increment();
            i++;
        }
        ac1 = new HashSet();
        obj = ((HashMap) (obj)).entrySet().iterator();
_L2:
        int j;
        int l;
        Character character1;
        int i1;
        if(!((Iterator) (obj)).hasNext())
            break MISSING_BLOCK_LABEL_207;
        java.util.Map.Entry entry = (java.util.Map.Entry)((Iterator) (obj)).next();
        character1 = (Character)entry.getKey();
        l = 0;
        j = 0;
        i1 = ((MutableInt)entry.getValue()).intValue();
_L4:
        if(j >= i1) goto _L2; else goto _L1
_L1:
        l = indexOf(ac, character1.charValue(), l);
        if(l < 0) goto _L2; else goto _L3
_L3:
        ac1.add(Integer.valueOf(l));
        j++;
        l++;
          goto _L4
        return removeAll(ac, extractIndices(ac1));
    }

    public static transient double[] removeElements(double ad[], double ad1[])
    {
        Iterator iterator;
        if(isEmpty(ad) || isEmpty(ad1))
            return clone(ad);
        HashMap hashmap = new HashMap(ad1.length);
        int i = 0;
        int k = ad1.length;
        while(i < k) 
        {
            Double double2 = Double.valueOf(ad1[i]);
            MutableInt mutableint = (MutableInt)hashmap.get(double2);
            if(mutableint == null)
                hashmap.put(double2, new MutableInt(1));
            else
                mutableint.increment();
            i++;
        }
        ad1 = new HashSet();
        iterator = hashmap.entrySet().iterator();
_L2:
        Double double1;
        int j;
        int l;
        int i1;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_208;
        java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
        double1 = (Double)entry.getKey();
        l = 0;
        j = 0;
        i1 = ((MutableInt)entry.getValue()).intValue();
_L4:
        if(j >= i1) goto _L2; else goto _L1
_L1:
        l = indexOf(ad, double1.doubleValue(), l);
        if(l < 0) goto _L2; else goto _L3
_L3:
        ad1.add(Integer.valueOf(l));
        j++;
        l++;
          goto _L4
        return removeAll(ad, extractIndices(ad1));
    }

    public static transient float[] removeElements(float af[], float af1[])
    {
        Iterator iterator;
        if(isEmpty(af) || isEmpty(af1))
            return clone(af);
        HashMap hashmap = new HashMap(af1.length);
        int i = 0;
        int k = af1.length;
        while(i < k) 
        {
            Float float2 = Float.valueOf(af1[i]);
            MutableInt mutableint = (MutableInt)hashmap.get(float2);
            if(mutableint == null)
                hashmap.put(float2, new MutableInt(1));
            else
                mutableint.increment();
            i++;
        }
        af1 = new HashSet();
        iterator = hashmap.entrySet().iterator();
_L2:
        Float float1;
        int j;
        int l;
        int i1;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_208;
        java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
        float1 = (Float)entry.getKey();
        l = 0;
        j = 0;
        i1 = ((MutableInt)entry.getValue()).intValue();
_L4:
        if(j >= i1) goto _L2; else goto _L1
_L1:
        l = indexOf(af, float1.floatValue(), l);
        if(l < 0) goto _L2; else goto _L3
_L3:
        af1.add(Integer.valueOf(l));
        j++;
        l++;
          goto _L4
        return removeAll(af, extractIndices(af1));
    }

    public static transient int[] removeElements(int ai[], int ai1[])
    {
        Iterator iterator;
        if(isEmpty(ai) || isEmpty(ai1))
            return clone(ai);
        HashMap hashmap = new HashMap(ai1.length);
        int i = 0;
        int k = ai1.length;
        while(i < k) 
        {
            Integer integer = Integer.valueOf(ai1[i]);
            MutableInt mutableint = (MutableInt)hashmap.get(integer);
            if(mutableint == null)
                hashmap.put(integer, new MutableInt(1));
            else
                mutableint.increment();
            i++;
        }
        ai1 = new HashSet();
        iterator = hashmap.entrySet().iterator();
_L2:
        int j;
        int l;
        Integer integer1;
        int i1;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_207;
        java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
        integer1 = (Integer)entry.getKey();
        l = 0;
        j = 0;
        i1 = ((MutableInt)entry.getValue()).intValue();
_L4:
        if(j >= i1) goto _L2; else goto _L1
_L1:
        l = indexOf(ai, integer1.intValue(), l);
        if(l < 0) goto _L2; else goto _L3
_L3:
        ai1.add(Integer.valueOf(l));
        j++;
        l++;
          goto _L4
        return removeAll(ai, extractIndices(ai1));
    }

    public static transient long[] removeElements(long al[], long al1[])
    {
        Object obj;
        if(isEmpty(al) || isEmpty(al1))
            return clone(al);
        obj = new HashMap(al1.length);
        int i = 0;
        int k = al1.length;
        while(i < k) 
        {
            Long long1 = Long.valueOf(al1[i]);
            MutableInt mutableint = (MutableInt)((HashMap) (obj)).get(long1);
            if(mutableint == null)
                ((HashMap) (obj)).put(long1, new MutableInt(1));
            else
                mutableint.increment();
            i++;
        }
        al1 = new HashSet();
        obj = ((HashMap) (obj)).entrySet().iterator();
_L2:
        int j;
        int l;
        Long long2;
        int i1;
        if(!((Iterator) (obj)).hasNext())
            break MISSING_BLOCK_LABEL_207;
        java.util.Map.Entry entry = (java.util.Map.Entry)((Iterator) (obj)).next();
        long2 = (Long)entry.getKey();
        l = 0;
        j = 0;
        i1 = ((MutableInt)entry.getValue()).intValue();
_L4:
        if(j >= i1) goto _L2; else goto _L1
_L1:
        l = indexOf(al, long2.longValue(), l);
        if(l < 0) goto _L2; else goto _L3
_L3:
        al1.add(Integer.valueOf(l));
        j++;
        l++;
          goto _L4
        return removeAll(al, extractIndices(al1));
    }

    public static transient Object[] removeElements(Object aobj[], Object aobj1[])
    {
        Object obj;
        int i = 0;
        if(isEmpty(aobj) || isEmpty(aobj1))
            return clone(aobj);
        obj = new HashMap(aobj1.length);
        int k = aobj1.length;
        while(i < k) 
        {
            Object obj1 = aobj1[i];
            MutableInt mutableint = (MutableInt)((HashMap) (obj)).get(obj1);
            if(mutableint == null)
                ((HashMap) (obj)).put(obj1, new MutableInt(1));
            else
                mutableint.increment();
            i++;
        }
        aobj1 = new HashSet();
        obj = ((HashMap) (obj)).entrySet().iterator();
_L2:
        int j;
        int l;
        Object obj2;
        int i1;
        if(!((Iterator) (obj)).hasNext())
            break MISSING_BLOCK_LABEL_198;
        java.util.Map.Entry entry = (java.util.Map.Entry)((Iterator) (obj)).next();
        obj2 = entry.getKey();
        l = 0;
        j = 0;
        i1 = ((MutableInt)entry.getValue()).intValue();
_L4:
        if(j >= i1) goto _L2; else goto _L1
_L1:
        l = indexOf(aobj, obj2, l);
        if(l < 0) goto _L2; else goto _L3
_L3:
        ((HashSet) (aobj1)).add(Integer.valueOf(l));
        j++;
        l++;
          goto _L4
        return removeAll(aobj, extractIndices(((HashSet) (aobj1))));
    }

    public static transient short[] removeElements(short aword0[], short aword1[])
    {
        Iterator iterator;
        if(isEmpty(aword0) || isEmpty(aword1))
            return clone(aword0);
        HashMap hashmap = new HashMap(aword1.length);
        int i = 0;
        int k = aword1.length;
        while(i < k) 
        {
            Short short2 = Short.valueOf(aword1[i]);
            MutableInt mutableint = (MutableInt)hashmap.get(short2);
            if(mutableint == null)
                hashmap.put(short2, new MutableInt(1));
            else
                mutableint.increment();
            i++;
        }
        aword1 = new HashSet();
        iterator = hashmap.entrySet().iterator();
_L2:
        Short short1;
        int j;
        int l;
        int i1;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_208;
        java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
        short1 = (Short)entry.getKey();
        l = 0;
        j = 0;
        i1 = ((MutableInt)entry.getValue()).intValue();
_L4:
        if(j >= i1) goto _L2; else goto _L1
_L1:
        l = indexOf(aword0, short1.shortValue(), l);
        if(l < 0) goto _L2; else goto _L3
_L3:
        aword1.add(Integer.valueOf(l));
        j++;
        l++;
          goto _L4
        return removeAll(aword0, extractIndices(aword1));
    }

    public static transient boolean[] removeElements(boolean aflag[], boolean aflag1[])
    {
        Iterator iterator;
        if(isEmpty(aflag) || isEmpty(aflag1))
            return clone(aflag);
        HashMap hashmap = new HashMap(aflag1.length);
        int i = 0;
        int k = aflag1.length;
        while(i < k) 
        {
            Boolean boolean2 = Boolean.valueOf(aflag1[i]);
            MutableInt mutableint = (MutableInt)hashmap.get(boolean2);
            if(mutableint == null)
                hashmap.put(boolean2, new MutableInt(1));
            else
                mutableint.increment();
            i++;
        }
        aflag1 = new HashSet();
        iterator = hashmap.entrySet().iterator();
_L2:
        Boolean boolean1;
        int j;
        int l;
        int i1;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_208;
        java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
        boolean1 = (Boolean)entry.getKey();
        l = 0;
        j = 0;
        i1 = ((MutableInt)entry.getValue()).intValue();
_L4:
        if(j >= i1) goto _L2; else goto _L1
_L1:
        l = indexOf(aflag, boolean1.booleanValue(), l);
        if(l < 0) goto _L2; else goto _L3
_L3:
        aflag1.add(Integer.valueOf(l));
        j++;
        l++;
          goto _L4
        return removeAll(aflag, extractIndices(aflag1));
    }

    public static void reverse(byte abyte0[])
    {
        if(abyte0 == null)
            return;
        int i = 0;
        for(int j = abyte0.length - 1; j > i; i++)
        {
            int k = abyte0[j];
            abyte0[j] = abyte0[i];
            abyte0[i] = (byte)k;
            j--;
        }

    }

    public static void reverse(char ac[])
    {
        if(ac == null)
            return;
        int i = 0;
        for(int j = ac.length - 1; j > i; i++)
        {
            int k = ac[j];
            ac[j] = ac[i];
            ac[i] = (char)k;
            j--;
        }

    }

    public static void reverse(double ad[])
    {
        if(ad == null)
            return;
        int i = 0;
        for(int j = ad.length - 1; j > i; i++)
        {
            double d = ad[j];
            ad[j] = ad[i];
            ad[i] = d;
            j--;
        }

    }

    public static void reverse(float af[])
    {
        if(af == null)
            return;
        int i = 0;
        for(int j = af.length - 1; j > i; i++)
        {
            float f = af[j];
            af[j] = af[i];
            af[i] = f;
            j--;
        }

    }

    public static void reverse(int ai[])
    {
        if(ai == null)
            return;
        int i = 0;
        for(int j = ai.length - 1; j > i; i++)
        {
            int k = ai[j];
            ai[j] = ai[i];
            ai[i] = k;
            j--;
        }

    }

    public static void reverse(long al[])
    {
        if(al == null)
            return;
        int i = 0;
        for(int j = al.length - 1; j > i; i++)
        {
            long l = al[j];
            al[j] = al[i];
            al[i] = l;
            j--;
        }

    }

    public static void reverse(Object aobj[])
    {
        if(aobj == null)
            return;
        int i = 0;
        for(int j = aobj.length - 1; j > i; i++)
        {
            Object obj = aobj[j];
            aobj[j] = aobj[i];
            aobj[i] = obj;
            j--;
        }

    }

    public static void reverse(short aword0[])
    {
        if(aword0 == null)
            return;
        int i = 0;
        for(int j = aword0.length - 1; j > i; i++)
        {
            int k = aword0[j];
            aword0[j] = aword0[i];
            aword0[i] = (short)k;
            j--;
        }

    }

    public static void reverse(boolean aflag[])
    {
        if(aflag == null)
            return;
        int i = 0;
        for(int j = aflag.length - 1; j > i; i++)
        {
            boolean flag = aflag[j];
            aflag[j] = aflag[i];
            aflag[i] = flag;
            j--;
        }

    }

    public static byte[] subarray(byte abyte0[], int i, int j)
    {
        if(abyte0 == null)
            return null;
        int k = i;
        if(i < 0)
            k = 0;
        i = j;
        if(j > abyte0.length)
            i = abyte0.length;
        i -= k;
        if(i <= 0)
        {
            return EMPTY_BYTE_ARRAY;
        } else
        {
            byte abyte1[] = new byte[i];
            System.arraycopy(abyte0, k, abyte1, 0, i);
            return abyte1;
        }
    }

    public static char[] subarray(char ac[], int i, int j)
    {
        if(ac == null)
            return null;
        int k = i;
        if(i < 0)
            k = 0;
        i = j;
        if(j > ac.length)
            i = ac.length;
        i -= k;
        if(i <= 0)
        {
            return EMPTY_CHAR_ARRAY;
        } else
        {
            char ac1[] = new char[i];
            System.arraycopy(ac, k, ac1, 0, i);
            return ac1;
        }
    }

    public static double[] subarray(double ad[], int i, int j)
    {
        if(ad == null)
            return null;
        int k = i;
        if(i < 0)
            k = 0;
        i = j;
        if(j > ad.length)
            i = ad.length;
        i -= k;
        if(i <= 0)
        {
            return EMPTY_DOUBLE_ARRAY;
        } else
        {
            double ad1[] = new double[i];
            System.arraycopy(ad, k, ad1, 0, i);
            return ad1;
        }
    }

    public static float[] subarray(float af[], int i, int j)
    {
        if(af == null)
            return null;
        int k = i;
        if(i < 0)
            k = 0;
        i = j;
        if(j > af.length)
            i = af.length;
        i -= k;
        if(i <= 0)
        {
            return EMPTY_FLOAT_ARRAY;
        } else
        {
            float af1[] = new float[i];
            System.arraycopy(af, k, af1, 0, i);
            return af1;
        }
    }

    public static int[] subarray(int ai[], int i, int j)
    {
        if(ai == null)
            return null;
        int k = i;
        if(i < 0)
            k = 0;
        i = j;
        if(j > ai.length)
            i = ai.length;
        i -= k;
        if(i <= 0)
        {
            return EMPTY_INT_ARRAY;
        } else
        {
            int ai1[] = new int[i];
            System.arraycopy(ai, k, ai1, 0, i);
            return ai1;
        }
    }

    public static long[] subarray(long al[], int i, int j)
    {
        if(al == null)
            return null;
        int k = i;
        if(i < 0)
            k = 0;
        i = j;
        if(j > al.length)
            i = al.length;
        i -= k;
        if(i <= 0)
        {
            return EMPTY_LONG_ARRAY;
        } else
        {
            long al1[] = new long[i];
            System.arraycopy(al, k, al1, 0, i);
            return al1;
        }
    }

    public static Object[] subarray(Object aobj[], int i, int j)
    {
        if(aobj == null)
            return null;
        int k = i;
        if(i < 0)
            k = 0;
        i = j;
        if(j > aobj.length)
            i = aobj.length;
        i -= k;
        Class class1 = ((Object) (aobj)).getClass().getComponentType();
        if(i <= 0)
        {
            return (Object[])Array.newInstance(class1, 0);
        } else
        {
            Object aobj1[] = (Object[])Array.newInstance(class1, i);
            System.arraycopy(((Object) (aobj)), k, ((Object) (aobj1)), 0, i);
            return aobj1;
        }
    }

    public static short[] subarray(short aword0[], int i, int j)
    {
        if(aword0 == null)
            return null;
        int k = i;
        if(i < 0)
            k = 0;
        i = j;
        if(j > aword0.length)
            i = aword0.length;
        i -= k;
        if(i <= 0)
        {
            return EMPTY_SHORT_ARRAY;
        } else
        {
            short aword1[] = new short[i];
            System.arraycopy(aword0, k, aword1, 0, i);
            return aword1;
        }
    }

    public static boolean[] subarray(boolean aflag[], int i, int j)
    {
        if(aflag == null)
            return null;
        int k = i;
        if(i < 0)
            k = 0;
        i = j;
        if(j > aflag.length)
            i = aflag.length;
        i -= k;
        if(i <= 0)
        {
            return EMPTY_BOOLEAN_ARRAY;
        } else
        {
            boolean aflag1[] = new boolean[i];
            System.arraycopy(aflag, k, aflag1, 0, i);
            return aflag1;
        }
    }

    public static transient Object[] toArray(Object aobj[])
    {
        return aobj;
    }

    public static Map toMap(Object aobj[])
    {
        if(aobj == null)
            return null;
        HashMap hashmap = new HashMap((int)((double)aobj.length * 1.5D));
        int i = 0;
        while(i < aobj.length) 
        {
            Object obj = aobj[i];
            if(obj instanceof java.util.Map.Entry)
            {
                obj = (java.util.Map.Entry)obj;
                hashmap.put(((java.util.Map.Entry) (obj)).getKey(), ((java.util.Map.Entry) (obj)).getValue());
            } else
            if(obj instanceof Object[])
            {
                Object aobj1[] = (Object[])obj;
                if(aobj1.length < 2)
                    throw new IllegalArgumentException((new StringBuilder()).append("Array element ").append(i).append(", '").append(obj).append("', has a length less than 2").toString());
                hashmap.put(aobj1[0], aobj1[1]);
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Array element ").append(i).append(", '").append(obj).append("', is neither of type Map.Entry nor an Array").toString());
            }
            i++;
        }
        return hashmap;
    }

    public static Boolean[] toObject(boolean aflag[])
    {
        if(aflag == null)
            return null;
        if(aflag.length == 0)
            return EMPTY_BOOLEAN_OBJECT_ARRAY;
        Boolean aboolean[] = new Boolean[aflag.length];
        int i = 0;
        while(i < aflag.length) 
        {
            Boolean boolean1;
            if(aflag[i])
                boolean1 = Boolean.TRUE;
            else
                boolean1 = Boolean.FALSE;
            aboolean[i] = boolean1;
            i++;
        }
        return aboolean;
    }

    public static Byte[] toObject(byte abyte0[])
    {
        if(abyte0 == null)
            return null;
        if(abyte0.length == 0)
            return EMPTY_BYTE_OBJECT_ARRAY;
        Byte abyte[] = new Byte[abyte0.length];
        for(int i = 0; i < abyte0.length; i++)
            abyte[i] = Byte.valueOf(abyte0[i]);

        return abyte;
    }

    public static Character[] toObject(char ac[])
    {
        if(ac == null)
            return null;
        if(ac.length == 0)
            return EMPTY_CHARACTER_OBJECT_ARRAY;
        Character acharacter[] = new Character[ac.length];
        for(int i = 0; i < ac.length; i++)
            acharacter[i] = Character.valueOf(ac[i]);

        return acharacter;
    }

    public static Double[] toObject(double ad[])
    {
        if(ad == null)
            return null;
        if(ad.length == 0)
            return EMPTY_DOUBLE_OBJECT_ARRAY;
        Double adouble[] = new Double[ad.length];
        for(int i = 0; i < ad.length; i++)
            adouble[i] = Double.valueOf(ad[i]);

        return adouble;
    }

    public static Float[] toObject(float af[])
    {
        if(af == null)
            return null;
        if(af.length == 0)
            return EMPTY_FLOAT_OBJECT_ARRAY;
        Float afloat[] = new Float[af.length];
        for(int i = 0; i < af.length; i++)
            afloat[i] = Float.valueOf(af[i]);

        return afloat;
    }

    public static Integer[] toObject(int ai[])
    {
        if(ai == null)
            return null;
        if(ai.length == 0)
            return EMPTY_INTEGER_OBJECT_ARRAY;
        Integer ainteger[] = new Integer[ai.length];
        for(int i = 0; i < ai.length; i++)
            ainteger[i] = Integer.valueOf(ai[i]);

        return ainteger;
    }

    public static Long[] toObject(long al[])
    {
        if(al == null)
            return null;
        if(al.length == 0)
            return EMPTY_LONG_OBJECT_ARRAY;
        Long along[] = new Long[al.length];
        for(int i = 0; i < al.length; i++)
            along[i] = Long.valueOf(al[i]);

        return along;
    }

    public static Short[] toObject(short aword0[])
    {
        if(aword0 == null)
            return null;
        if(aword0.length == 0)
            return EMPTY_SHORT_OBJECT_ARRAY;
        Short ashort[] = new Short[aword0.length];
        for(int i = 0; i < aword0.length; i++)
            ashort[i] = Short.valueOf(aword0[i]);

        return ashort;
    }

    public static byte[] toPrimitive(Byte abyte[])
    {
        if(abyte == null)
            return null;
        if(abyte.length == 0)
            return EMPTY_BYTE_ARRAY;
        byte abyte0[] = new byte[abyte.length];
        for(int i = 0; i < abyte.length; i++)
            abyte0[i] = abyte[i].byteValue();

        return abyte0;
    }

    public static byte[] toPrimitive(Byte abyte[], byte byte0)
    {
        if(abyte == null)
            return null;
        if(abyte.length == 0)
            return EMPTY_BYTE_ARRAY;
        byte abyte0[] = new byte[abyte.length];
        int i = 0;
        while(i < abyte.length) 
        {
            Byte byte1 = abyte[i];
            int j;
            if(byte1 == null)
                j = byte0;
            else
                j = byte1.byteValue();
            abyte0[i] = (byte)j;
            i++;
        }
        return abyte0;
    }

    public static char[] toPrimitive(Character acharacter[])
    {
        if(acharacter == null)
            return null;
        if(acharacter.length == 0)
            return EMPTY_CHAR_ARRAY;
        char ac[] = new char[acharacter.length];
        for(int i = 0; i < acharacter.length; i++)
            ac[i] = acharacter[i].charValue();

        return ac;
    }

    public static char[] toPrimitive(Character acharacter[], char c)
    {
        if(acharacter == null)
            return null;
        if(acharacter.length == 0)
            return EMPTY_CHAR_ARRAY;
        char ac[] = new char[acharacter.length];
        int i = 0;
        while(i < acharacter.length) 
        {
            Character character = acharacter[i];
            int j;
            if(character == null)
                j = c;
            else
                j = character.charValue();
            ac[i] = (char)j;
            i++;
        }
        return ac;
    }

    public static double[] toPrimitive(Double adouble[])
    {
        if(adouble == null)
            return null;
        if(adouble.length == 0)
            return EMPTY_DOUBLE_ARRAY;
        double ad[] = new double[adouble.length];
        for(int i = 0; i < adouble.length; i++)
            ad[i] = adouble[i].doubleValue();

        return ad;
    }

    public static double[] toPrimitive(Double adouble[], double d)
    {
        if(adouble == null)
            return null;
        if(adouble.length == 0)
            return EMPTY_DOUBLE_ARRAY;
        double ad[] = new double[adouble.length];
        int i = 0;
        while(i < adouble.length) 
        {
            Double double1 = adouble[i];
            double d1;
            if(double1 == null)
                d1 = d;
            else
                d1 = double1.doubleValue();
            ad[i] = d1;
            i++;
        }
        return ad;
    }

    public static float[] toPrimitive(Float afloat[])
    {
        if(afloat == null)
            return null;
        if(afloat.length == 0)
            return EMPTY_FLOAT_ARRAY;
        float af[] = new float[afloat.length];
        for(int i = 0; i < afloat.length; i++)
            af[i] = afloat[i].floatValue();

        return af;
    }

    public static float[] toPrimitive(Float afloat[], float f)
    {
        if(afloat == null)
            return null;
        if(afloat.length == 0)
            return EMPTY_FLOAT_ARRAY;
        float af[] = new float[afloat.length];
        int i = 0;
        while(i < afloat.length) 
        {
            Float float1 = afloat[i];
            float f1;
            if(float1 == null)
                f1 = f;
            else
                f1 = float1.floatValue();
            af[i] = f1;
            i++;
        }
        return af;
    }

    public static int[] toPrimitive(Integer ainteger[])
    {
        if(ainteger == null)
            return null;
        if(ainteger.length == 0)
            return EMPTY_INT_ARRAY;
        int ai[] = new int[ainteger.length];
        for(int i = 0; i < ainteger.length; i++)
            ai[i] = ainteger[i].intValue();

        return ai;
    }

    public static int[] toPrimitive(Integer ainteger[], int i)
    {
        if(ainteger == null)
            return null;
        if(ainteger.length == 0)
            return EMPTY_INT_ARRAY;
        int ai[] = new int[ainteger.length];
        int j = 0;
        while(j < ainteger.length) 
        {
            Integer integer = ainteger[j];
            int k;
            if(integer == null)
                k = i;
            else
                k = integer.intValue();
            ai[j] = k;
            j++;
        }
        return ai;
    }

    public static long[] toPrimitive(Long along[])
    {
        if(along == null)
            return null;
        if(along.length == 0)
            return EMPTY_LONG_ARRAY;
        long al[] = new long[along.length];
        for(int i = 0; i < along.length; i++)
            al[i] = along[i].longValue();

        return al;
    }

    public static long[] toPrimitive(Long along[], long l)
    {
        if(along == null)
            return null;
        if(along.length == 0)
            return EMPTY_LONG_ARRAY;
        long al[] = new long[along.length];
        int i = 0;
        while(i < along.length) 
        {
            Long long1 = along[i];
            long l1;
            if(long1 == null)
                l1 = l;
            else
                l1 = long1.longValue();
            al[i] = l1;
            i++;
        }
        return al;
    }

    public static short[] toPrimitive(Short ashort[])
    {
        if(ashort == null)
            return null;
        if(ashort.length == 0)
            return EMPTY_SHORT_ARRAY;
        short aword0[] = new short[ashort.length];
        for(int i = 0; i < ashort.length; i++)
            aword0[i] = ashort[i].shortValue();

        return aword0;
    }

    public static short[] toPrimitive(Short ashort[], short word0)
    {
        if(ashort == null)
            return null;
        if(ashort.length == 0)
            return EMPTY_SHORT_ARRAY;
        short aword0[] = new short[ashort.length];
        int i = 0;
        while(i < ashort.length) 
        {
            Short short1 = ashort[i];
            int j;
            if(short1 == null)
                j = word0;
            else
                j = short1.shortValue();
            aword0[i] = (short)j;
            i++;
        }
        return aword0;
    }

    public static boolean[] toPrimitive(Boolean aboolean[])
    {
        if(aboolean == null)
            return null;
        if(aboolean.length == 0)
            return EMPTY_BOOLEAN_ARRAY;
        boolean aflag[] = new boolean[aboolean.length];
        for(int i = 0; i < aboolean.length; i++)
            aflag[i] = aboolean[i].booleanValue();

        return aflag;
    }

    public static boolean[] toPrimitive(Boolean aboolean[], boolean flag)
    {
        if(aboolean == null)
            return null;
        if(aboolean.length == 0)
            return EMPTY_BOOLEAN_ARRAY;
        boolean aflag[] = new boolean[aboolean.length];
        int i = 0;
        while(i < aboolean.length) 
        {
            Boolean boolean1 = aboolean[i];
            boolean flag1;
            if(boolean1 == null)
                flag1 = flag;
            else
                flag1 = boolean1.booleanValue();
            aflag[i] = flag1;
            i++;
        }
        return aflag;
    }

    public static String toString(Object obj)
    {
        return toString(obj, "{}");
    }

    public static String toString(Object obj, String s)
    {
        if(obj == null)
            return s;
        else
            return (new ToStringBuilder(obj, ToStringStyle.SIMPLE_STYLE)).append(obj).toString();
    }

    public static final boolean EMPTY_BOOLEAN_ARRAY[] = new boolean[0];
    public static final Boolean EMPTY_BOOLEAN_OBJECT_ARRAY[] = new Boolean[0];
    public static final byte EMPTY_BYTE_ARRAY[] = new byte[0];
    public static final Byte EMPTY_BYTE_OBJECT_ARRAY[] = new Byte[0];
    public static final Character EMPTY_CHARACTER_OBJECT_ARRAY[] = new Character[0];
    public static final char EMPTY_CHAR_ARRAY[] = new char[0];
    public static final Class EMPTY_CLASS_ARRAY[] = new Class[0];
    public static final double EMPTY_DOUBLE_ARRAY[] = new double[0];
    public static final Double EMPTY_DOUBLE_OBJECT_ARRAY[] = new Double[0];
    public static final float EMPTY_FLOAT_ARRAY[] = new float[0];
    public static final Float EMPTY_FLOAT_OBJECT_ARRAY[] = new Float[0];
    public static final Integer EMPTY_INTEGER_OBJECT_ARRAY[] = new Integer[0];
    public static final int EMPTY_INT_ARRAY[] = new int[0];
    public static final long EMPTY_LONG_ARRAY[] = new long[0];
    public static final Long EMPTY_LONG_OBJECT_ARRAY[] = new Long[0];
    public static final Object EMPTY_OBJECT_ARRAY[] = new Object[0];
    public static final short EMPTY_SHORT_ARRAY[] = new short[0];
    public static final Short EMPTY_SHORT_OBJECT_ARRAY[] = new Short[0];
    public static final String EMPTY_STRING_ARRAY[] = new String[0];
    public static final int INDEX_NOT_FOUND = -1;

}
