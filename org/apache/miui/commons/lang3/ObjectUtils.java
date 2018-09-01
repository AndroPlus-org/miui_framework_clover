// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3;

import java.io.Serializable;
import java.lang.reflect.*;
import java.util.*;
import org.apache.miui.commons.lang3.exception.CloneFailedException;
import org.apache.miui.commons.lang3.mutable.MutableInt;

// Referenced classes of package org.apache.miui.commons.lang3:
//            Validate, ArrayUtils

public class ObjectUtils
{
    public static class Null
        implements Serializable
    {

        private Object readResolve()
        {
            return ObjectUtils.NULL;
        }

        private static final long serialVersionUID = 0x626e04ed40667ec5L;

        Null()
        {
        }
    }


    public ObjectUtils()
    {
    }

    public static Object clone(Object obj)
    {
        if(!(obj instanceof Cloneable)) goto _L2; else goto _L1
_L1:
        if(!obj.getClass().isArray()) goto _L4; else goto _L3
_L3:
        Object obj1 = obj.getClass().getComponentType();
        if(((Class) (obj1)).isPrimitive()) goto _L6; else goto _L5
_L5:
        obj1 = ((Object[])obj).clone();
_L7:
        return obj1;
_L6:
        int i = Array.getLength(obj);
        Object obj2 = Array.newInstance(((Class) (obj1)), i);
        do
        {
            int j = i - 1;
            obj1 = obj2;
            if(i <= 0)
                continue; /* Loop/switch isn't completed */
            Array.set(obj2, j, Array.get(obj, j));
            i = j;
        } while(true);
_L4:
        try
        {
            obj1 = obj.getClass().getMethod("clone", new Class[0]).invoke(obj, new Object[0]);
        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            throw new CloneFailedException((new StringBuilder()).append("Cloneable type ").append(obj.getClass().getName()).append(" has no clone method").toString(), nosuchmethodexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            throw new CloneFailedException((new StringBuilder()).append("Cannot clone Cloneable type ").append(obj.getClass().getName()).toString(), illegalaccessexception);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            throw new CloneFailedException((new StringBuilder()).append("Exception cloning Cloneable type ").append(obj.getClass().getName()).toString(), invocationtargetexception.getCause());
        }
        if(true) goto _L7; else goto _L2
_L2:
        return null;
    }

    public static Object cloneIfPossible(Object obj)
    {
        Object obj1 = clone(obj);
        if(obj1 != null)
            obj = obj1;
        return obj;
    }

    public static int compare(Comparable comparable, Comparable comparable1)
    {
        return compare(comparable, comparable1, false);
    }

    public static int compare(Comparable comparable, Comparable comparable1, boolean flag)
    {
        int i = 1;
        byte byte1 = -1;
        if(comparable == comparable1)
            return 0;
        if(comparable == null)
        {
            if(!flag)
                i = -1;
            return i;
        }
        if(comparable1 == null)
        {
            byte byte0;
            if(flag)
                byte0 = byte1;
            else
                byte0 = 1;
            return byte0;
        } else
        {
            return comparable.compareTo(comparable1);
        }
    }

    public static Object defaultIfNull(Object obj, Object obj1)
    {
        if(obj == null)
            obj = obj1;
        return obj;
    }

    public static boolean equals(Object obj, Object obj1)
    {
        if(obj == obj1)
            return true;
        if(obj == null || obj1 == null)
            return false;
        else
            return obj.equals(obj1);
    }

    public static transient Object firstNonNull(Object aobj[])
    {
        if(aobj != null)
        {
            int i = 0;
            for(int j = aobj.length; i < j; i++)
            {
                Object obj = aobj[i];
                if(obj != null)
                    return obj;
            }

        }
        return null;
    }

    public static int hashCode(Object obj)
    {
        int i;
        if(obj == null)
            i = 0;
        else
            i = obj.hashCode();
        return i;
    }

    public static transient int hashCodeMulti(Object aobj[])
    {
        int i = 1;
        int j = 1;
        if(aobj != null)
        {
            int k = 0;
            int l = aobj.length;
            do
            {
                i = j;
                if(k >= l)
                    break;
                j = j * 31 + hashCode(aobj[k]);
                k++;
            } while(true);
        }
        return i;
    }

    public static String identityToString(Object obj)
    {
        if(obj == null)
        {
            return null;
        } else
        {
            StringBuffer stringbuffer = new StringBuffer();
            identityToString(stringbuffer, obj);
            return stringbuffer.toString();
        }
    }

    public static void identityToString(StringBuffer stringbuffer, Object obj)
    {
        if(obj == null)
        {
            throw new NullPointerException("Cannot get the toString of a null identity");
        } else
        {
            stringbuffer.append(obj.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(obj)));
            return;
        }
    }

    public static transient Comparable max(Comparable acomparable[])
    {
        Comparable comparable = null;
        Comparable comparable1 = null;
        if(acomparable != null)
        {
            int i = acomparable.length;
            int j = 0;
            do
            {
                comparable = comparable1;
                if(j >= i)
                    break;
                Comparable comparable2 = acomparable[j];
                comparable = comparable1;
                if(compare(comparable2, comparable1, false) > 0)
                    comparable = comparable2;
                j++;
                comparable1 = comparable;
            } while(true);
        }
        return comparable;
    }

    public static transient Comparable median(Comparable acomparable[])
    {
        Validate.notEmpty(acomparable);
        Validate.noNullElements(acomparable);
        TreeSet treeset = new TreeSet();
        Collections.addAll(treeset, acomparable);
        return (Comparable)treeset.toArray()[(treeset.size() - 1) / 2];
    }

    public static transient Object median(Comparator comparator, Object aobj[])
    {
        Validate.notEmpty(aobj, "null/empty items", new Object[0]);
        Validate.noNullElements(aobj);
        Validate.notNull(comparator, "null comparator", new Object[0]);
        comparator = new TreeSet(comparator);
        Collections.addAll(comparator, aobj);
        return comparator.toArray()[(comparator.size() - 1) / 2];
    }

    public static transient Comparable min(Comparable acomparable[])
    {
        int i = 0;
        Comparable comparable = null;
        Comparable comparable1 = null;
        if(acomparable != null)
        {
            int j = acomparable.length;
            do
            {
                comparable = comparable1;
                if(i >= j)
                    break;
                Comparable comparable2 = acomparable[i];
                comparable = comparable1;
                if(compare(comparable2, comparable1, true) < 0)
                    comparable = comparable2;
                i++;
                comparable1 = comparable;
            } while(true);
        }
        return comparable;
    }

    public static transient Object mode(Object aobj[])
    {
        if(ArrayUtils.isNotEmpty(aobj))
        {
            Object obj = new HashMap(aobj.length);
            int i = 0;
            int j = aobj.length;
            while(i < j) 
            {
                Object obj1 = aobj[i];
                MutableInt mutableint = (MutableInt)((HashMap) (obj)).get(obj1);
                if(mutableint == null)
                    ((HashMap) (obj)).put(obj1, new MutableInt(1));
                else
                    mutableint.increment();
                i++;
            }
            aobj = null;
            i = 0;
            obj = ((HashMap) (obj)).entrySet().iterator();
            do
            {
                if(!((Iterator) (obj)).hasNext())
                    break;
                java.util.Map.Entry entry = (java.util.Map.Entry)((Iterator) (obj)).next();
                int k = ((MutableInt)entry.getValue()).intValue();
                if(k == i)
                    aobj = null;
                else
                if(k > i)
                {
                    i = k;
                    aobj = ((Object []) (entry.getKey()));
                }
            } while(true);
            return ((Object) (aobj));
        } else
        {
            return null;
        }
    }

    public static boolean notEqual(Object obj, Object obj1)
    {
        boolean flag;
        if(!equals(obj, obj1))
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static String toString(Object obj)
    {
        if(obj == null)
            obj = "";
        else
            obj = obj.toString();
        return ((String) (obj));
    }

    public static String toString(Object obj, String s)
    {
        if(obj != null)
            s = obj.toString();
        return s;
    }

    public static final Null NULL = new Null();

}
