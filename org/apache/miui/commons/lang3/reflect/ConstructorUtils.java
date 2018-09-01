// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.reflect;

import java.lang.reflect.*;
import org.apache.miui.commons.lang3.ArrayUtils;
import org.apache.miui.commons.lang3.ClassUtils;

// Referenced classes of package org.apache.miui.commons.lang3.reflect:
//            MemberUtils

public class ConstructorUtils
{

    public ConstructorUtils()
    {
    }

    public static transient Constructor getAccessibleConstructor(Class class1, Class aclass[])
    {
        try
        {
            class1 = getAccessibleConstructor(class1.getConstructor(aclass));
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            return null;
        }
        return class1;
    }

    public static Constructor getAccessibleConstructor(Constructor constructor)
    {
        if(!MemberUtils.isAccessible(constructor) || !Modifier.isPublic(constructor.getDeclaringClass().getModifiers()))
            constructor = null;
        return constructor;
    }

    public static transient Constructor getMatchingAccessibleConstructor(Class class1, Class aclass[])
    {
        int i = 0;
        Constructor constructor;
        try
        {
            constructor = class1.getConstructor(aclass);
            MemberUtils.setAccessibleWorkaround(constructor);
        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            Object obj = null;
            Constructor aconstructor[] = class1.getConstructors();
            int j = aconstructor.length;
            for(class1 = ((Class) (obj)); i < j; class1 = ((Class) (obj)))
            {
label0:
                {
                    Constructor constructor1 = aconstructor[i];
                    obj = class1;
                    if(!ClassUtils.isAssignable(aclass, constructor1.getParameterTypes(), true))
                        break label0;
                    constructor1 = getAccessibleConstructor(constructor1);
                    obj = class1;
                    if(constructor1 == null)
                        break label0;
                    MemberUtils.setAccessibleWorkaround(constructor1);
                    if(class1 != null)
                    {
                        obj = class1;
                        if(MemberUtils.compareParameterTypes(constructor1.getParameterTypes(), class1.getParameterTypes(), aclass) >= 0)
                            break label0;
                    }
                    obj = constructor1;
                }
                i++;
            }

            return class1;
        }
        return constructor;
    }

    public static transient Object invokeConstructor(Class class1, Object aobj[])
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
    {
        Object aobj1[] = aobj;
        if(aobj == null)
            aobj1 = ArrayUtils.EMPTY_OBJECT_ARRAY;
        aobj = new Class[aobj1.length];
        for(int i = 0; i < aobj1.length; i++)
            aobj[i] = aobj1[i].getClass();

        return invokeConstructor(class1, aobj1, ((Class []) (aobj)));
    }

    public static Object invokeConstructor(Class class1, Object aobj[], Class aclass[])
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
    {
        Class aclass1[] = aclass;
        if(aclass == null)
            aclass1 = ArrayUtils.EMPTY_CLASS_ARRAY;
        aclass = ((Class []) (aobj));
        if(aobj == null)
            aclass = ((Class []) (ArrayUtils.EMPTY_OBJECT_ARRAY));
        aobj = getMatchingAccessibleConstructor(class1, aclass1);
        if(aobj == null)
            throw new NoSuchMethodException((new StringBuilder()).append("No such accessible constructor on object: ").append(class1.getName()).toString());
        else
            return ((Constructor) (aobj)).newInstance(aclass);
    }

    public static transient Object invokeExactConstructor(Class class1, Object aobj[])
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
    {
        Object aobj1[] = aobj;
        if(aobj == null)
            aobj1 = ArrayUtils.EMPTY_OBJECT_ARRAY;
        int i = aobj1.length;
        aobj = new Class[i];
        for(int j = 0; j < i; j++)
            aobj[j] = aobj1[j].getClass();

        return invokeExactConstructor(class1, aobj1, ((Class []) (aobj)));
    }

    public static Object invokeExactConstructor(Class class1, Object aobj[], Class aclass[])
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
    {
        Object aobj1[] = aobj;
        if(aobj == null)
            aobj1 = ArrayUtils.EMPTY_OBJECT_ARRAY;
        aobj = aclass;
        if(aclass == null)
            aobj = ArrayUtils.EMPTY_CLASS_ARRAY;
        aobj = getAccessibleConstructor(class1, ((Class []) (aobj)));
        if(aobj == null)
            throw new NoSuchMethodException((new StringBuilder()).append("No such accessible constructor on object: ").append(class1.getName()).toString());
        else
            return ((Constructor) (aobj)).newInstance(aobj1);
    }
}
