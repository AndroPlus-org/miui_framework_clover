// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.util.ArraySet;
import dalvik.system.VMRuntime;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;
import libcore.util.EmptyArray;

public class ArrayUtils
{

    private ArrayUtils()
    {
    }

    public static ArraySet add(ArraySet arrayset, Object obj)
    {
        ArraySet arrayset1 = arrayset;
        if(arrayset == null)
            arrayset1 = new ArraySet();
        arrayset1.add(obj);
        return arrayset1;
    }

    public static ArrayList add(ArrayList arraylist, Object obj)
    {
        ArrayList arraylist1 = arraylist;
        if(arraylist == null)
            arraylist1 = new ArrayList();
        arraylist1.add(obj);
        return arraylist1;
    }

    public static Object[] appendElement(Class class1, Object aobj[], Object obj)
    {
        return appendElement(class1, aobj, obj, false);
    }

    public static Object[] appendElement(Class class1, Object aobj[], Object obj, boolean flag)
    {
        int i;
        if(aobj != null)
        {
            if(!flag && contains(aobj, obj))
                return aobj;
            i = aobj.length;
            class1 = ((Class) ((Object[])Array.newInstance(class1, i + 1)));
            System.arraycopy(((Object) (aobj)), 0, class1, 0, i);
        } else
        {
            i = 0;
            class1 = ((Class) ((Object[])Array.newInstance(class1, 1)));
        }
        class1[i] = obj;
        return class1;
    }

    public static int[] appendInt(int ai[], int i)
    {
        return appendInt(ai, i, false);
    }

    public static int[] appendInt(int ai[], int i, boolean flag)
    {
        if(ai == null)
            return (new int[] {
                i
            });
        int j = ai.length;
        if(!flag)
        {
            for(int k = 0; k < j; k++)
                if(ai[k] == i)
                    return ai;

        }
        int ai1[] = new int[j + 1];
        System.arraycopy(ai, 0, ai1, 0, j);
        ai1[j] = i;
        return ai1;
    }

    public static long[] appendLong(long al[], long l)
    {
        if(al == null)
            return (new long[] {
                l
            });
        int i = al.length;
        for(int j = 0; j < i; j++)
            if(al[j] == l)
                return al;

        long al1[] = new long[i + 1];
        System.arraycopy(al, 0, al1, 0, i);
        al1[i] = l;
        return al1;
    }

    public static ArraySet cloneOrNull(ArraySet arrayset)
    {
        ArraySet arrayset1 = null;
        if(arrayset != null)
            arrayset1 = new ArraySet(arrayset);
        return arrayset1;
    }

    public static long[] cloneOrNull(long al[])
    {
        long al1[] = null;
        if(al != null)
            al1 = (long[])al.clone();
        return al1;
    }

    public static boolean contains(Collection collection, Object obj)
    {
        boolean flag;
        if(collection != null)
            flag = collection.contains(obj);
        else
            flag = false;
        return flag;
    }

    public static boolean contains(char ac[], char c)
    {
        if(ac == null)
            return false;
        int i = ac.length;
        for(int j = 0; j < i; j++)
            if(ac[j] == c)
                return true;

        return false;
    }

    public static boolean contains(int ai[], int i)
    {
        if(ai == null)
            return false;
        int j = ai.length;
        for(int k = 0; k < j; k++)
            if(ai[k] == i)
                return true;

        return false;
    }

    public static boolean contains(long al[], long l)
    {
        if(al == null)
            return false;
        int i = al.length;
        for(int j = 0; j < i; j++)
            if(al[j] == l)
                return true;

        return false;
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

    public static boolean containsAll(char ac[], char ac1[])
    {
        if(ac1 == null)
            return true;
        int i = ac1.length;
        for(int j = 0; j < i; j++)
            if(!contains(ac, ac1[j]))
                return false;

        return true;
    }

    public static boolean containsAll(Object aobj[], Object aobj1[])
    {
        if(aobj1 == null)
            return true;
        int i = aobj1.length;
        for(int j = 0; j < i; j++)
            if(!contains(aobj, aobj1[j]))
                return false;

        return true;
    }

    public static boolean containsAny(Object aobj[], Object aobj1[])
    {
        if(aobj1 == null)
            return false;
        int i = aobj1.length;
        for(int j = 0; j < i; j++)
            if(contains(aobj, aobj1[j]))
                return true;

        return false;
    }

    public static int[] convertToIntArray(List list)
    {
        int ai[] = new int[list.size()];
        for(int i = 0; i < list.size(); i++)
            ai[i] = ((Integer)list.get(i)).intValue();

        return ai;
    }

    public static String[] defeatNullable(String as[])
    {
        if(as == null)
            as = EmptyArray.STRING;
        return as;
    }

    public static Object[] emptyArray(Class class1)
    {
        Object obj1;
label0:
        {
            if(class1 == java/lang/Object)
                return EmptyArray.OBJECT;
            int i = (class1.hashCode() & 0x7fffffff) % 73;
            Object obj = sCache[i];
            if(obj != null)
            {
                obj1 = obj;
                if(obj.getClass().getComponentType() == class1)
                    break label0;
            }
            obj1 = Array.newInstance(class1, 0);
            sCache[i] = obj1;
        }
        return (Object[])obj1;
    }

    public static boolean equals(byte abyte0[], byte abyte1[], int i)
    {
        if(i < 0)
            throw new IllegalArgumentException();
        if(abyte0 == abyte1)
            return true;
        while(abyte0 == null || abyte1 == null || abyte0.length < i || abyte1.length < i) 
            return false;
        for(int j = 0; j < i; j++)
            if(abyte0[j] != abyte1[j])
                return false;

        return true;
    }

    public static int indexOf(Object aobj[], Object obj)
    {
        if(aobj == null)
            return -1;
        for(int i = 0; i < aobj.length; i++)
            if(Objects.equals(aobj[i], obj))
                return i;

        return -1;
    }

    public static boolean isEmpty(Collection collection)
    {
        boolean flag;
        if(collection != null)
            flag = collection.isEmpty();
        else
            flag = true;
        return flag;
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

    public static Object[] newUnpaddedArray(Class class1, int i)
    {
        return (Object[])VMRuntime.getRuntime().newUnpaddedArray(class1, i);
    }

    public static boolean[] newUnpaddedBooleanArray(int i)
    {
        return (boolean[])VMRuntime.getRuntime().newUnpaddedArray(Boolean.TYPE, i);
    }

    public static byte[] newUnpaddedByteArray(int i)
    {
        return (byte[])VMRuntime.getRuntime().newUnpaddedArray(Byte.TYPE, i);
    }

    public static char[] newUnpaddedCharArray(int i)
    {
        return (char[])VMRuntime.getRuntime().newUnpaddedArray(Character.TYPE, i);
    }

    public static float[] newUnpaddedFloatArray(int i)
    {
        return (float[])VMRuntime.getRuntime().newUnpaddedArray(Float.TYPE, i);
    }

    public static int[] newUnpaddedIntArray(int i)
    {
        return (int[])VMRuntime.getRuntime().newUnpaddedArray(Integer.TYPE, i);
    }

    public static long[] newUnpaddedLongArray(int i)
    {
        return (long[])VMRuntime.getRuntime().newUnpaddedArray(Long.TYPE, i);
    }

    public static Object[] newUnpaddedObjectArray(int i)
    {
        return (Object[])VMRuntime.getRuntime().newUnpaddedArray(java/lang/Object, i);
    }

    public static boolean referenceEquals(ArrayList arraylist, ArrayList arraylist1)
    {
        if(arraylist == arraylist1)
            return true;
        int i = arraylist.size();
        for(int j = arraylist1.size(); arraylist == null || arraylist1 == null || i != j;)
            return false;

        boolean flag = false;
        int k = 0;
        while(k < i && flag ^ true) 
        {
            boolean flag1;
            if(arraylist.get(k) != arraylist1.get(k))
                flag1 = true;
            else
                flag1 = false;
            flag |= flag1;
            k++;
        }
        return flag ^ true;
    }

    public static ArraySet remove(ArraySet arrayset, Object obj)
    {
        if(arrayset == null)
            return null;
        arrayset.remove(obj);
        if(arrayset.isEmpty())
            return null;
        else
            return arrayset;
    }

    public static ArrayList remove(ArrayList arraylist, Object obj)
    {
        if(arraylist == null)
            return null;
        arraylist.remove(obj);
        if(arraylist.isEmpty())
            return null;
        else
            return arraylist;
    }

    public static Object[] removeElement(Class class1, Object aobj[], Object obj)
    {
        if(aobj != null)
        {
            if(!contains(aobj, obj))
                return aobj;
            int i = aobj.length;
            for(int j = 0; j < i; j++)
                if(Objects.equals(aobj[j], obj))
                    if(i == 1)
                    {
                        return null;
                    } else
                    {
                        class1 = ((Class) ((Object[])Array.newInstance(class1, i - 1)));
                        System.arraycopy(((Object) (aobj)), 0, class1, 0, j);
                        System.arraycopy(((Object) (aobj)), j + 1, class1, j, i - j - 1);
                        return class1;
                    }

        }
        return aobj;
    }

    public static int[] removeInt(int ai[], int i)
    {
        if(ai == null)
            return null;
        int j = ai.length;
        for(int k = 0; k < j; k++)
            if(ai[k] == i)
            {
                int ai1[] = new int[j - 1];
                if(k > 0)
                    System.arraycopy(ai, 0, ai1, 0, k);
                if(k < j - 1)
                    System.arraycopy(ai, k + 1, ai1, k, j - k - 1);
                return ai1;
            }

        return ai;
    }

    public static long[] removeLong(long al[], long l)
    {
        if(al == null)
            return null;
        int i = al.length;
        for(int j = 0; j < i; j++)
            if(al[j] == l)
            {
                long al1[] = new long[i - 1];
                if(j > 0)
                    System.arraycopy(al, 0, al1, 0, j);
                if(j < i - 1)
                    System.arraycopy(al, j + 1, al1, j, i - j - 1);
                return al1;
            }

        return al;
    }

    public static String[] removeString(String as[], String s)
    {
        if(as == null)
            return null;
        int i = as.length;
        for(int j = 0; j < i; j++)
            if(Objects.equals(as[j], s))
            {
                s = new String[i - 1];
                if(j > 0)
                    System.arraycopy(as, 0, s, 0, j);
                if(j < i - 1)
                    System.arraycopy(as, j + 1, s, j, i - j - 1);
                return s;
            }

        return as;
    }

    public static int size(Object aobj[])
    {
        int i;
        if(aobj == null)
            i = 0;
        else
            i = aobj.length;
        return i;
    }

    public static long total(long al[])
    {
        long l = 0L;
        long l1 = l;
        if(al != null)
        {
            int i = 0;
            int j = al.length;
            do
            {
                l1 = l;
                if(i >= j)
                    break;
                l += al[i];
                i++;
            } while(true);
        }
        return l1;
    }

    public static Object[] trimToSize(Object aobj[], int i)
    {
        if(aobj == null || i == 0)
            return null;
        if(aobj.length == i)
            return aobj;
        else
            return Arrays.copyOf(aobj, i);
    }

    public static int unstableRemoveIf(ArrayList arraylist, Predicate predicate)
    {
        if(arraylist == null)
            return 0;
        int i = arraylist.size();
        int j = 0;
        int k = i - 1;
        int l;
label0:
        do
        {
label1:
            {
                l = j;
                if(j <= k)
                {
                    do
                    {
                        l = k;
                        if(j >= i)
                            break;
                        l = k;
                        if(!(predicate.test(arraylist.get(j)) ^ true))
                            break;
                        j++;
                    } while(true);
                    for(; l > j && predicate.test(arraylist.get(l)); l--);
                    if(j < l)
                        break label1;
                    l = j;
                }
                for(j = i - 1; j >= l; j--)
                    arraylist.remove(j);

                break label0;
            }
            Collections.swap(arraylist, j, l);
            j++;
            k = l - 1;
        } while(true);
        return i - l;
    }

    private static final int CACHE_SIZE = 73;
    private static Object sCache[] = new Object[73];

}
