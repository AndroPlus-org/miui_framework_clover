// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.reflect;

import java.lang.reflect.*;
import org.apache.miui.commons.lang3.ArrayUtils;
import org.apache.miui.commons.lang3.ClassUtils;

// Referenced classes of package org.apache.miui.commons.lang3.reflect:
//            MemberUtils

public class MethodUtils
{

    public MethodUtils()
    {
    }

    public static transient Method getAccessibleMethod(Class class1, String s, Class aclass[])
    {
        try
        {
            class1 = getAccessibleMethod(class1.getMethod(s, aclass));
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            return null;
        }
        return class1;
    }

    public static Method getAccessibleMethod(Method method)
    {
        if(!MemberUtils.isAccessible(method))
            return null;
        Class class1 = method.getDeclaringClass();
        if(Modifier.isPublic(class1.getModifiers()))
            return method;
        String s = method.getName();
        Class aclass[] = method.getParameterTypes();
        Method method1 = getAccessibleMethodFromInterfaceNest(class1, s, aclass);
        method = method1;
        if(method1 == null)
            method = getAccessibleMethodFromSuperclass(class1, s, aclass);
        return method;
    }

    private static transient Method getAccessibleMethodFromInterfaceNest(Class class1, String s, Class aclass[])
    {
        Class class2;
        Object obj = null;
        class2 = class1;
        class1 = obj;
_L10:
        if(class2 == null) goto _L2; else goto _L1
_L1:
        Class aclass1[];
        int i;
        aclass1 = class2.getInterfaces();
        i = 0;
_L7:
        Object obj1 = class1;
        if(i >= aclass1.length) goto _L4; else goto _L3
_L3:
        if(Modifier.isPublic(aclass1[i].getModifiers())) goto _L6; else goto _L5
_L5:
        i++;
          goto _L7
_L6:
        obj1 = aclass1[i].getDeclaredMethod(s, aclass);
        class1 = ((Class) (obj1));
_L11:
        if(class1 == null) goto _L9; else goto _L8
_L8:
        obj1 = class1;
_L4:
        class2 = class2.getSuperclass();
        class1 = ((Class) (obj1));
          goto _L10
_L9:
        obj1 = getAccessibleMethodFromInterfaceNest(aclass1[i], s, aclass);
        class1 = ((Class) (obj1));
        if(obj1 == null) goto _L5; else goto _L4
_L2:
        return class1;
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
          goto _L11
    }

    private static transient Method getAccessibleMethodFromSuperclass(Class class1, String s, Class aclass[])
    {
        for(class1 = class1.getSuperclass(); class1 != null; class1 = class1.getSuperclass())
            if(Modifier.isPublic(class1.getModifiers()))
            {
                try
                {
                    class1 = class1.getMethod(s, aclass);
                }
                // Misplaced declaration of an exception variable
                catch(Class class1)
                {
                    return null;
                }
                return class1;
            }

        return null;
    }

    public static transient Method getMatchingAccessibleMethod(Class class1, String s, Class aclass[])
    {
        int i = 0;
        Method method;
        try
        {
            method = class1.getMethod(s, aclass);
            MemberUtils.setAccessibleWorkaround(method);
        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            Object obj = null;
            Method amethod[] = class1.getMethods();
            int j = amethod.length;
            for(class1 = ((Class) (obj)); i < j; class1 = ((Class) (obj)))
            {
label0:
                {
                    Method method1 = amethod[i];
                    obj = class1;
                    if(!method1.getName().equals(s))
                        break label0;
                    obj = class1;
                    if(!ClassUtils.isAssignable(aclass, method1.getParameterTypes(), true))
                        break label0;
                    method1 = getAccessibleMethod(method1);
                    obj = class1;
                    if(method1 == null)
                        break label0;
                    if(class1 != null)
                    {
                        obj = class1;
                        if(MemberUtils.compareParameterTypes(method1.getParameterTypes(), class1.getParameterTypes(), aclass) >= 0)
                            break label0;
                    }
                    obj = method1;
                }
                i++;
            }

            if(class1 != null)
                MemberUtils.setAccessibleWorkaround(class1);
            return class1;
        }
        return method;
    }

    public static transient Object invokeExactMethod(Object obj, String s, Object aobj[])
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        Object aobj1[] = aobj;
        if(aobj == null)
            aobj1 = ArrayUtils.EMPTY_OBJECT_ARRAY;
        int i = aobj1.length;
        aobj = new Class[i];
        for(int j = 0; j < i; j++)
            aobj[j] = aobj1[j].getClass();

        return invokeExactMethod(obj, s, aobj1, ((Class []) (aobj)));
    }

    public static Object invokeExactMethod(Object obj, String s, Object aobj[], Class aclass[])
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        Object aobj1[] = aobj;
        if(aobj == null)
            aobj1 = ArrayUtils.EMPTY_OBJECT_ARRAY;
        aobj = aclass;
        if(aclass == null)
            aobj = ArrayUtils.EMPTY_CLASS_ARRAY;
        aobj = getAccessibleMethod(obj.getClass(), s, ((Class []) (aobj)));
        if(aobj == null)
            throw new NoSuchMethodException((new StringBuilder()).append("No such accessible method: ").append(s).append("() on object: ").append(obj.getClass().getName()).toString());
        else
            return ((Method) (aobj)).invoke(obj, aobj1);
    }

    public static transient Object invokeExactStaticMethod(Class class1, String s, Object aobj[])
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        Object aobj1[] = aobj;
        if(aobj == null)
            aobj1 = ArrayUtils.EMPTY_OBJECT_ARRAY;
        int i = aobj1.length;
        aobj = new Class[i];
        for(int j = 0; j < i; j++)
            aobj[j] = aobj1[j].getClass();

        return invokeExactStaticMethod(class1, s, aobj1, ((Class []) (aobj)));
    }

    public static Object invokeExactStaticMethod(Class class1, String s, Object aobj[], Class aclass[])
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        Object aobj1[] = aobj;
        if(aobj == null)
            aobj1 = ArrayUtils.EMPTY_OBJECT_ARRAY;
        aobj = aclass;
        if(aclass == null)
            aobj = ArrayUtils.EMPTY_CLASS_ARRAY;
        aobj = getAccessibleMethod(class1, s, ((Class []) (aobj)));
        if(aobj == null)
            throw new NoSuchMethodException((new StringBuilder()).append("No such accessible method: ").append(s).append("() on class: ").append(class1.getName()).toString());
        else
            return ((Method) (aobj)).invoke(null, aobj1);
    }

    public static transient Object invokeMethod(Object obj, String s, Object aobj[])
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        Object aobj1[] = aobj;
        if(aobj == null)
            aobj1 = ArrayUtils.EMPTY_OBJECT_ARRAY;
        int i = aobj1.length;
        aobj = new Class[i];
        for(int j = 0; j < i; j++)
            aobj[j] = aobj1[j].getClass();

        return invokeMethod(obj, s, aobj1, ((Class []) (aobj)));
    }

    public static Object invokeMethod(Object obj, String s, Object aobj[], Class aclass[])
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        Class aclass1[] = aclass;
        if(aclass == null)
            aclass1 = ArrayUtils.EMPTY_CLASS_ARRAY;
        aclass = ((Class []) (aobj));
        if(aobj == null)
            aclass = ((Class []) (ArrayUtils.EMPTY_OBJECT_ARRAY));
        aobj = getMatchingAccessibleMethod(obj.getClass(), s, aclass1);
        if(aobj == null)
            throw new NoSuchMethodException((new StringBuilder()).append("No such accessible method: ").append(s).append("() on object: ").append(obj.getClass().getName()).toString());
        else
            return ((Method) (aobj)).invoke(obj, aclass);
    }

    public static transient Object invokeStaticMethod(Class class1, String s, Object aobj[])
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        Object aobj1[] = aobj;
        if(aobj == null)
            aobj1 = ArrayUtils.EMPTY_OBJECT_ARRAY;
        int i = aobj1.length;
        aobj = new Class[i];
        for(int j = 0; j < i; j++)
            aobj[j] = aobj1[j].getClass();

        return invokeStaticMethod(class1, s, aobj1, ((Class []) (aobj)));
    }

    public static Object invokeStaticMethod(Class class1, String s, Object aobj[], Class aclass[])
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        Class aclass1[] = aclass;
        if(aclass == null)
            aclass1 = ArrayUtils.EMPTY_CLASS_ARRAY;
        aclass = ((Class []) (aobj));
        if(aobj == null)
            aclass = ((Class []) (ArrayUtils.EMPTY_OBJECT_ARRAY));
        aobj = getMatchingAccessibleMethod(class1, s, aclass1);
        if(aobj == null)
            throw new NoSuchMethodException((new StringBuilder()).append("No such accessible method: ").append(s).append("() on class: ").append(class1.getName()).toString());
        else
            return ((Method) (aobj)).invoke(null, aclass);
    }
}
