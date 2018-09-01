// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.WeakHashMap;
import org.apache.miui.commons.lang3.ClassUtils;
import org.apache.miui.commons.lang3.reflect.MemberUtils;

public class ReflectionUtils
{
    public static class ClassNotFoundError extends Error
    {

        private static final long serialVersionUID = 0xf1234492b6e762fcL;

        public ClassNotFoundError(String s, Throwable throwable)
        {
            super(s, throwable);
        }

        public ClassNotFoundError(Throwable throwable)
        {
            super(throwable);
        }
    }

    public static class InvocationTargetError extends Error
    {

        private static final long serialVersionUID = 0xf1234492b6e762fcL;

        public InvocationTargetError(String s, Throwable throwable)
        {
            super(s, throwable);
        }

        public InvocationTargetError(Throwable throwable)
        {
            super(throwable);
        }
    }


    public ReflectionUtils()
    {
    }

    public static transient Object callMethod(Object obj, String s, Object aobj[])
    {
        try
        {
            obj = findMethodBestMatch(obj.getClass(), s, aobj).invoke(obj, aobj);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new IllegalAccessError(((IllegalAccessException) (obj)).getMessage());
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new InvocationTargetError(((InvocationTargetException) (obj)).getCause());
        }
        return obj;
    }

    public static transient Object callStaticMethod(Class class1, String s, Object aobj[])
    {
        try
        {
            class1 = ((Class) (findMethodBestMatch(class1, s, aobj).invoke(null, aobj)));
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            throw new IllegalAccessError(class1.getMessage());
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            throw class1;
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            throw new InvocationTargetError(class1.getCause());
        }
        return class1;
    }

    public static Class findClass(String s, ClassLoader classloader)
    {
        ClassLoader classloader1 = classloader;
        if(classloader == null)
            classloader1 = BOOTCLASSLOADER;
        try
        {
            s = ClassUtils.getClass(classloader1, s, false);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new ClassNotFoundError(s);
        }
        return s;
    }

    public static transient Constructor findConstructorBestMatch(Class class1, Class aclass[])
    {
        int i = 0;
        Object obj = new StringBuilder(class1.getName());
        ((StringBuilder) (obj)).append(getParametersString(aclass));
        ((StringBuilder) (obj)).append("#bestmatch");
        String s = ((StringBuilder) (obj)).toString();
        if(constructorCache.containsKey(s))
        {
            class1 = (Constructor)constructorCache.get(s);
            if(class1 == null)
                throw new NoSuchMethodError(s);
            else
                return class1;
        }
        try
        {
            obj = findConstructorExact(class1, aclass);
            constructorCache.put(s, obj);
        }
        catch(NoSuchMethodError nosuchmethoderror)
        {
            Class class2 = null;
            Constructor aconstructor[] = class1.getDeclaredConstructors();
            for(int j = aconstructor.length; i < j;)
            {
label0:
                {
                    Constructor constructor = aconstructor[i];
                    class1 = class2;
                    if(!ClassUtils.isAssignable(aclass, constructor.getParameterTypes(), true))
                        break label0;
                    if(class2 != null)
                    {
                        class1 = class2;
                        if(MemberUtils.compareParameterTypes(constructor.getParameterTypes(), class2.getParameterTypes(), aclass) >= 0)
                            break label0;
                    }
                    class1 = constructor;
                }
                i++;
                class2 = class1;
            }

            if(class2 != null)
            {
                class2.setAccessible(true);
                constructorCache.put(s, class2);
                return class2;
            } else
            {
                class1 = new NoSuchMethodError(s);
                constructorCache.put(s, null);
                throw class1;
            }
        }
        return ((Constructor) (obj));
    }

    public static transient Constructor findConstructorBestMatch(Class class1, Object aobj[])
    {
        return findConstructorBestMatch(class1, getParameterTypes(aobj));
    }

    public static transient Constructor findConstructorExact(Class class1, Class aclass[])
    {
        Object obj = new StringBuilder(class1.getName());
        ((StringBuilder) (obj)).append(getParametersString(aclass));
        ((StringBuilder) (obj)).append("#exact");
        obj = ((StringBuilder) (obj)).toString();
        if(constructorCache.containsKey(obj))
        {
            class1 = (Constructor)constructorCache.get(obj);
            if(class1 == null)
                throw new NoSuchMethodError(((String) (obj)));
            else
                return class1;
        }
        try
        {
            class1 = class1.getDeclaredConstructor(aclass);
            class1.setAccessible(true);
            constructorCache.put(obj, class1);
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            constructorCache.put(obj, null);
            throw new NoSuchMethodError(((String) (obj)));
        }
        return class1;
    }

    public static Field findField(Class class1, String s)
    {
        Object obj = new StringBuilder(class1.getName());
        ((StringBuilder) (obj)).append('#');
        ((StringBuilder) (obj)).append(s);
        obj = ((StringBuilder) (obj)).toString();
        if(fieldCache.containsKey(obj))
        {
            class1 = (Field)fieldCache.get(obj);
            if(class1 == null)
                throw new NoSuchFieldError(((String) (obj)));
            else
                return class1;
        }
        try
        {
            class1 = findFieldRecursiveImpl(class1, s);
            class1.setAccessible(true);
            fieldCache.put(obj, class1);
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            fieldCache.put(obj, null);
            throw new NoSuchFieldError(((String) (obj)));
        }
        return class1;
    }

    private static Field findFieldRecursiveImpl(Class class1, String s)
        throws NoSuchFieldException
    {
        Field field = class1.getDeclaredField(s);
        return field;
        NoSuchFieldException nosuchfieldexception;
        nosuchfieldexception;
_L2:
        class1 = class1.getSuperclass();
        if(class1 == null || class1.equals(java/lang/Object))
            throw nosuchfieldexception;
        Field field1 = class1.getDeclaredField(s);
        return field1;
        NoSuchFieldException nosuchfieldexception1;
        nosuchfieldexception1;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static transient Method findMethodBestMatch(Class class1, String s, Class aclass[])
    {
        int i = 0;
        Object obj = new StringBuilder(class1.getName());
        ((StringBuilder) (obj)).append('#');
        ((StringBuilder) (obj)).append(s);
        ((StringBuilder) (obj)).append(getParametersString(aclass));
        ((StringBuilder) (obj)).append("#bestmatch");
        String s1 = ((StringBuilder) (obj)).toString();
        if(methodCache.containsKey(s1))
        {
            class1 = (Method)methodCache.get(s1);
            if(class1 == null)
                throw new NoSuchMethodError(s1);
            else
                return class1;
        }
        try
        {
            obj = findMethodExact(class1, s, aclass);
            methodCache.put(s1, obj);
        }
        catch(NoSuchMethodError nosuchmethoderror)
        {
            Object obj1 = null;
            Method amethod[] = class1.getDeclaredMethods();
            int j = amethod.length;
            for(class1 = ((Class) (obj1)); i < j; class1 = ((Class) (obj1)))
            {
label0:
                {
                    Method method = amethod[i];
                    obj1 = class1;
                    if(!method.getName().equals(s))
                        break label0;
                    obj1 = class1;
                    if(!ClassUtils.isAssignable(aclass, method.getParameterTypes(), true))
                        break label0;
                    if(class1 != null)
                    {
                        obj1 = class1;
                        if(MemberUtils.compareParameterTypes(method.getParameterTypes(), class1.getParameterTypes(), aclass) >= 0)
                            break label0;
                    }
                    obj1 = method;
                }
                i++;
            }

            if(class1 != null)
            {
                class1.setAccessible(true);
                methodCache.put(s1, class1);
                return class1;
            } else
            {
                class1 = new NoSuchMethodError(s1);
                methodCache.put(s1, null);
                throw class1;
            }
        }
        return ((Method) (obj));
    }

    public static transient Method findMethodBestMatch(Class class1, String s, Object aobj[])
    {
        return findMethodBestMatch(class1, s, getParameterTypes(aobj));
    }

    public static transient Method findMethodExact(Class class1, String s, Class aclass[])
    {
        Object obj = new StringBuilder(class1.getName());
        ((StringBuilder) (obj)).append('#');
        ((StringBuilder) (obj)).append(s);
        ((StringBuilder) (obj)).append(getParametersString(aclass));
        ((StringBuilder) (obj)).append("#exact");
        obj = ((StringBuilder) (obj)).toString();
        if(methodCache.containsKey(obj))
        {
            class1 = (Method)methodCache.get(obj);
            if(class1 == null)
                throw new NoSuchMethodError(((String) (obj)));
            else
                return class1;
        }
        try
        {
            class1 = class1.getDeclaredMethod(s, aclass);
            class1.setAccessible(true);
            methodCache.put(obj, class1);
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            methodCache.put(obj, null);
            throw new NoSuchMethodError(((String) (obj)));
        }
        return class1;
    }

    public static transient Method findMethodExact(Class class1, String s, Object aobj[])
    {
        Class aclass[] = null;
        int i = aobj.length - 1;
        while(i >= 0) 
        {
            Object obj = aobj[i];
            if(obj == null)
                throw new ClassNotFoundError("parameter type must not be null", null);
            Class aclass1[] = aclass;
            if(aclass == null)
                aclass1 = new Class[i + 1];
            if(obj instanceof Class)
                aclass1[i] = (Class)obj;
            else
            if(obj instanceof String)
                aclass1[i] = findClass((String)obj, class1.getClassLoader());
            else
                throw new ClassNotFoundError("parameter type must either be specified as Class or String", null);
            i--;
            aclass = aclass1;
        }
        aobj = aclass;
        if(aclass == null)
            aobj = new Class[0];
        return findMethodExact(class1, s, ((Class []) (aobj)));
    }

    public static Object getAdditionalInstanceField(Object obj, String s)
    {
        if(obj == null)
            throw new NullPointerException("object must not be null");
        if(s == null)
            throw new NullPointerException("key must not be null");
        WeakHashMap weakhashmap = additionalFields;
        weakhashmap;
        JVM INSTR monitorenter ;
        obj = (HashMap)additionalFields.get(obj);
        if(obj != null)
            break MISSING_BLOCK_LABEL_54;
        weakhashmap;
        JVM INSTR monitorexit ;
        return null;
        weakhashmap;
        JVM INSTR monitorexit ;
        obj;
        JVM INSTR monitorenter ;
        s = ((String) (((HashMap) (obj)).get(s)));
        obj;
        JVM INSTR monitorexit ;
        return s;
        obj;
        throw obj;
        s;
        throw s;
    }

    public static Object getAdditionalStaticField(Class class1, String s)
    {
        return getAdditionalInstanceField(class1, s);
    }

    public static Object getAdditionalStaticField(Object obj, String s)
    {
        return getAdditionalInstanceField(obj.getClass(), s);
    }

    public static transient Class[] getClassesAsArray(Class aclass[])
    {
        return aclass;
    }

    public static Object getObjectField(Object obj, String s)
    {
        try
        {
            obj = findField(obj.getClass(), s).get(obj);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new IllegalAccessError(((IllegalAccessException) (obj)).getMessage());
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw obj;
        }
        return obj;
    }

    public static transient Class[] getParameterTypes(Object aobj[])
    {
        Class aclass[] = new Class[aobj.length];
        int i = 0;
        while(i < aobj.length) 
        {
            Class class1;
            if(aobj[i] != null)
                class1 = aobj[i].getClass();
            else
                class1 = null;
            aclass[i] = class1;
            i++;
        }
        return aclass;
    }

    private static transient String getParametersString(Class aclass[])
    {
        StringBuilder stringbuilder = new StringBuilder("(");
        boolean flag = true;
        int i = 0;
        int j = aclass.length;
        while(i < j) 
        {
            Class class1 = aclass[i];
            if(flag)
                flag = false;
            else
                stringbuilder.append(",");
            if(class1 != null)
                stringbuilder.append(class1.getCanonicalName());
            else
                stringbuilder.append("null");
            i++;
        }
        stringbuilder.append(")");
        return stringbuilder.toString();
    }

    public static Object getStaticObjectField(Class class1, String s)
    {
        try
        {
            class1 = ((Class) (findField(class1, s).get(null)));
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            throw new IllegalAccessError(class1.getMessage());
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            throw class1;
        }
        return class1;
    }

    public static Object getSurroundingThis(Object obj)
    {
        return getObjectField(obj, "this$0");
    }

    public static transient Object newInstance(Class class1, Object aobj[])
    {
        try
        {
            class1 = ((Class) (findConstructorBestMatch(class1, aobj).newInstance(aobj)));
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            throw new IllegalAccessError(class1.getMessage());
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            throw class1;
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            throw new InvocationTargetError(class1.getCause());
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            throw new InstantiationError(class1.getMessage());
        }
        return class1;
    }

    public static Object removeAdditionalInstanceField(Object obj, String s)
    {
        if(obj == null)
            throw new NullPointerException("object must not be null");
        if(s == null)
            throw new NullPointerException("key must not be null");
        WeakHashMap weakhashmap = additionalFields;
        weakhashmap;
        JVM INSTR monitorenter ;
        obj = (HashMap)additionalFields.get(obj);
        if(obj != null)
            break MISSING_BLOCK_LABEL_54;
        weakhashmap;
        JVM INSTR monitorexit ;
        return null;
        weakhashmap;
        JVM INSTR monitorexit ;
        obj;
        JVM INSTR monitorenter ;
        s = ((String) (((HashMap) (obj)).remove(s)));
        obj;
        JVM INSTR monitorexit ;
        return s;
        obj;
        throw obj;
        s;
        throw s;
    }

    public static Object removeAdditionalStaticField(Class class1, String s)
    {
        return removeAdditionalInstanceField(class1, s);
    }

    public static Object removeAdditionalStaticField(Object obj, String s)
    {
        return removeAdditionalInstanceField(obj.getClass(), s);
    }

    public static Object setAdditionalInstanceField(Object obj, String s, Object obj1)
    {
        if(obj == null)
            throw new NullPointerException("object must not be null");
        if(s == null)
            throw new NullPointerException("key must not be null");
        WeakHashMap weakhashmap = additionalFields;
        weakhashmap;
        JVM INSTR monitorenter ;
        HashMap hashmap = (HashMap)additionalFields.get(obj);
        HashMap hashmap1;
        hashmap1 = hashmap;
        if(hashmap != null)
            break MISSING_BLOCK_LABEL_76;
        hashmap1 = JVM INSTR new #35  <Class HashMap>;
        hashmap1.HashMap();
        additionalFields.put(obj, hashmap1);
        weakhashmap;
        JVM INSTR monitorexit ;
        hashmap1;
        JVM INSTR monitorenter ;
        obj = hashmap1.put(s, obj1);
        hashmap1;
        JVM INSTR monitorexit ;
        return obj;
        obj;
        throw obj;
        obj;
        throw obj;
    }

    public static Object setAdditionalStaticField(Class class1, String s, Object obj)
    {
        return setAdditionalInstanceField(class1, s, obj);
    }

    public static Object setAdditionalStaticField(Object obj, String s, Object obj1)
    {
        return setAdditionalInstanceField(obj.getClass(), s, obj1);
    }

    public static void setObjectField(Object obj, String s, Object obj1)
    {
        try
        {
            findField(obj.getClass(), s).set(obj, obj1);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new IllegalAccessError(((IllegalAccessException) (obj)).getMessage());
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw obj;
        }
    }

    public static void setStaticObjectField(Class class1, String s, Object obj)
    {
        try
        {
            findField(class1, s).set(null, obj);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            throw new IllegalAccessError(class1.getMessage());
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            throw class1;
        }
    }

    public static final ClassLoader BOOTCLASSLOADER = ClassLoader.getSystemClassLoader();
    private static final WeakHashMap additionalFields = new WeakHashMap();
    private static final HashMap constructorCache = new HashMap();
    private static final HashMap fieldCache = new HashMap();
    private static final HashMap methodCache = new HashMap();

}
