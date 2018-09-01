// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HidlSupport
{

    public HidlSupport()
    {
    }

    public static boolean deepEquals(Object obj, Object obj1)
    {
        boolean flag = false;
        if(obj == obj1)
            return true;
        if(obj == null || obj1 == null)
            return false;
        Class class1 = obj.getClass();
        Class class2 = obj1.getClass();
        if(class1 != class2)
            return false;
        if(class1.isArray())
        {
            class1 = class1.getComponentType();
            if(class1 != class2.getComponentType())
                return false;
            if(class1.isPrimitive())
                return Objects.deepEquals(obj, obj1);
            obj = ((Object) ((Object[])obj));
            obj1 = ((Object) ((Object[])obj1));
            if(obj.length == obj1.length)
                flag = IntStream.range(0, obj.length).allMatch(new _.Lambda.G_Gcg0ia_B_NRvJUIh_Nis__dWA._cls2(obj, obj1));
            return flag;
        }
        if(obj instanceof List)
        {
            List list = (List)obj;
            obj = (List)obj1;
            if(list.size() != ((List) (obj)).size())
            {
                return false;
            } else
            {
                obj1 = list.iterator();
                return ((List) (obj)).stream().allMatch(new _.Lambda.G_Gcg0ia_B_NRvJUIh_Nis__dWA._cls1(obj1));
            }
        } else
        {
            throwErrorIfUnsupportedType(obj);
            return obj.equals(obj1);
        }
    }

    public static int deepHashCode(Object obj)
    {
        if(obj == null)
            return 0;
        Class class1 = obj.getClass();
        if(class1.isArray())
            if(class1.getComponentType().isPrimitive())
                return primitiveArrayHashCode(obj);
            else
                return Arrays.hashCode(Arrays.stream((Object[])obj).mapToInt(_.Lambda.G_Gcg0ia_B_NRvJUIh_Nis__dWA.$INST$0).toArray());
        if(obj instanceof List)
        {
            return Arrays.hashCode(((List)obj).stream().mapToInt(_.Lambda.G_Gcg0ia_B_NRvJUIh_Nis__dWA.$INST$1).toArray());
        } else
        {
            throwErrorIfUnsupportedType(obj);
            return obj.hashCode();
        }
    }

    static boolean lambda$_2D_android_os_HidlSupport_2349(Object aobj[], Object aobj1[], int i)
    {
        return deepEquals(aobj[i], aobj1[i]);
    }

    static boolean lambda$_2D_android_os_HidlSupport_2772(Iterator iterator, Object obj)
    {
        return deepEquals(iterator.next(), obj);
    }

    static int lambda$_2D_android_os_HidlSupport_3440(Object obj)
    {
        return deepHashCode(obj);
    }

    static int lambda$_2D_android_os_HidlSupport_3646(Object obj)
    {
        return deepHashCode(obj);
    }

    private static int primitiveArrayHashCode(Object obj)
    {
        Class class1 = obj.getClass().getComponentType();
        if(class1 == Boolean.TYPE)
            return Arrays.hashCode((boolean[])obj);
        if(class1 == Byte.TYPE)
            return Arrays.hashCode((byte[])obj);
        if(class1 == Character.TYPE)
            return Arrays.hashCode((char[])obj);
        if(class1 == Double.TYPE)
            return Arrays.hashCode((double[])obj);
        if(class1 == Float.TYPE)
            return Arrays.hashCode((float[])obj);
        if(class1 == Integer.TYPE)
            return Arrays.hashCode((int[])obj);
        if(class1 == Long.TYPE)
            return Arrays.hashCode((long[])obj);
        if(class1 == Short.TYPE)
            return Arrays.hashCode((short[])obj);
        else
            throw new UnsupportedOperationException();
    }

    private static void throwErrorIfUnsupportedType(Object obj)
    {
        if((obj instanceof Collection) && (obj instanceof List) ^ true)
            throw new UnsupportedOperationException((new StringBuilder()).append("Cannot check equality on collections other than lists: ").append(obj.getClass().getName()).toString());
        if(obj instanceof Map)
            throw new UnsupportedOperationException("Cannot check equality on maps");
        else
            return;
    }
}
