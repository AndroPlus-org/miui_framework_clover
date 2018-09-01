// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.util.Log;
import java.lang.reflect.*;
import java.util.HashMap;
import org.apache.miui.commons.lang3.ClassUtils;
import org.apache.miui.commons.lang3.reflect.MemberUtils;

// Referenced classes of package miui.util:
//            ObjectReference

public class ReflectionUtils
{

    public ReflectionUtils()
    {
    }

    public static transient Object callMethod(Object obj, String s, Class class1, Object aobj[])
        throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        return checkMethodReturnValue(findMethodBestMatch(obj.getClass(), s, aobj).invoke(obj, aobj), class1);
    }

    public static transient Object callStaticMethod(Class class1, String s, Class class2, Object aobj[])
        throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        class1 = findMethodBestMatch(class1, s, aobj);
        try
        {
            class1 = ((Class) (checkMethodReturnValue(class1.invoke(null, aobj), class2)));
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            throw new IllegalArgumentException(class1);
        }
        return class1;
    }

    private static Object checkFieldValue(Object obj, Class class1)
        throws IllegalArgumentException
    {
        if(class1 == java/lang/Void)
            throw new IllegalArgumentException("fieldClazz");
        if(obj == null)
            return null;
        if(class1 == null)
            return obj;
        if(ClassUtils.isAssignable(obj.getClass(), class1, true))
            return obj;
        else
            throw new IllegalArgumentException("fieldClazz");
    }

    private static Object checkMethodReturnValue(Object obj, Class class1)
        throws IllegalArgumentException
    {
        if(obj == null)
            return null;
        if(class1 == null)
            return obj;
        if(class1 == java/lang/Void)
            return null;
        if(ClassUtils.isAssignable(obj.getClass(), class1, true))
            return obj;
        else
            throw new IllegalArgumentException("returnValueClazz");
    }

    public static Class findClass(String s, ClassLoader classloader)
        throws ClassNotFoundException
    {
        ClassLoader classloader1 = classloader;
        if(classloader == null)
            classloader1 = BOOTCLASSLOADER;
        return ClassUtils.getClass(classloader1, s, false);
    }

    public static transient Constructor findConstructorBestMatch(Class class1, Class aclass[])
        throws NoSuchMethodException
    {
        String s;
        StringBuilder stringbuilder = new StringBuilder(class1.getName());
        stringbuilder.append(getParametersString(aclass));
        stringbuilder.append("#bestmatch");
        s = stringbuilder.toString();
        HashMap hashmap = constructorCache;
        hashmap;
        JVM INSTR monitorenter ;
        if(!constructorCache.containsKey(s))
            break MISSING_BLOCK_LABEL_84;
        class1 = (Constructor)constructorCache.get(s);
        if(class1 != null)
            break MISSING_BLOCK_LABEL_80;
        class1 = JVM INSTR new #44  <Class NoSuchMethodException>;
        class1.NoSuchMethodException(s);
        throw class1;
        class1;
        hashmap;
        JVM INSTR monitorexit ;
        throw class1;
        hashmap;
        JVM INSTR monitorexit ;
        return class1;
        hashmap;
        JVM INSTR monitorexit ;
        Constructor constructor = findConstructorExact(class1, aclass);
        hashmap = constructorCache;
        hashmap;
        JVM INSTR monitorenter ;
        constructorCache.put(s, constructor);
        hashmap;
        JVM INSTR monitorexit ;
        return constructor;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
        Object obj = null;
        Constructor aconstructor[] = class1.getDeclaredConstructors();
        int i = aconstructor.length;
        int j = 0;
        for(class1 = ((Class) (obj)); j < i; class1 = ((Class) (obj)))
        {
label0:
            {
                Constructor constructor1 = aconstructor[j];
                obj = class1;
                if(!ClassUtils.isAssignable(aclass, constructor1.getParameterTypes(), true))
                    break label0;
                if(class1 != null)
                {
                    obj = class1;
                    if(MemberUtils.compareParameterTypes(constructor1.getParameterTypes(), class1.getParameterTypes(), aclass) >= 0)
                        break label0;
                }
                obj = constructor1;
            }
            j++;
        }

        if(class1 == null)
            break MISSING_BLOCK_LABEL_235;
        class1.setAccessible(true);
        aclass = constructorCache;
        aclass;
        JVM INSTR monitorenter ;
        constructorCache.put(s, class1);
        aclass;
        JVM INSTR monitorexit ;
        return class1;
        class1;
        throw class1;
        aclass = new NoSuchMethodException(s);
        class1 = constructorCache;
        class1;
        JVM INSTR monitorenter ;
        constructorCache.put(s, null);
        class1;
        JVM INSTR monitorexit ;
        throw aclass;
        aclass;
        throw aclass;
    }

    public static transient Constructor findConstructorBestMatch(Class class1, Object aobj[])
        throws NoSuchMethodException
    {
        return findConstructorBestMatch(class1, getParameterTypes(aobj));
    }

    public static transient Constructor findConstructorExact(Class class1, Class aclass[])
        throws NoSuchMethodException
    {
        Object obj;
        obj = new StringBuilder(class1.getName());
        ((StringBuilder) (obj)).append(getParametersString(aclass));
        ((StringBuilder) (obj)).append("#exact");
        obj = ((StringBuilder) (obj)).toString();
        HashMap hashmap = constructorCache;
        hashmap;
        JVM INSTR monitorenter ;
        if(!constructorCache.containsKey(obj))
            break MISSING_BLOCK_LABEL_84;
        class1 = (Constructor)constructorCache.get(obj);
        if(class1 != null)
            break MISSING_BLOCK_LABEL_80;
        class1 = JVM INSTR new #44  <Class NoSuchMethodException>;
        class1.NoSuchMethodException(((String) (obj)));
        throw class1;
        class1;
        hashmap;
        JVM INSTR monitorexit ;
        throw class1;
        hashmap;
        JVM INSTR monitorexit ;
        return class1;
        hashmap;
        JVM INSTR monitorexit ;
        aclass = class1.getDeclaredConstructor(aclass);
        aclass.setAccessible(true);
        class1 = constructorCache;
        class1;
        JVM INSTR monitorenter ;
        constructorCache.put(obj, aclass);
        class1;
        JVM INSTR monitorexit ;
        return aclass;
        aclass;
        class1;
        JVM INSTR monitorexit ;
        throw aclass;
        aclass;
        class1 = constructorCache;
        class1;
        JVM INSTR monitorenter ;
        constructorCache.put(obj, null);
        class1;
        JVM INSTR monitorexit ;
        throw aclass;
        aclass;
        throw aclass;
    }

    public static Field findField(Class class1, String s)
        throws NoSuchFieldException
    {
        Object obj;
        obj = new StringBuilder(class1.getName());
        ((StringBuilder) (obj)).append('#');
        ((StringBuilder) (obj)).append(s);
        obj = ((StringBuilder) (obj)).toString();
        HashMap hashmap = fieldCache;
        hashmap;
        JVM INSTR monitorenter ;
        if(!fieldCache.containsKey(obj))
            break MISSING_BLOCK_LABEL_81;
        class1 = (Field)fieldCache.get(obj);
        if(class1 != null)
            break MISSING_BLOCK_LABEL_77;
        class1 = JVM INSTR new #193 <Class NoSuchFieldException>;
        class1.NoSuchFieldException(((String) (obj)));
        throw class1;
        class1;
        hashmap;
        JVM INSTR monitorexit ;
        throw class1;
        hashmap;
        JVM INSTR monitorexit ;
        return class1;
        hashmap;
        JVM INSTR monitorexit ;
        s = findFieldRecursiveImpl(class1, s);
        s.setAccessible(true);
        class1 = fieldCache;
        class1;
        JVM INSTR monitorenter ;
        fieldCache.put(obj, s);
        class1;
        JVM INSTR monitorexit ;
        return s;
        s;
        class1;
        JVM INSTR monitorexit ;
        throw s;
        s;
        class1 = fieldCache;
        class1;
        JVM INSTR monitorenter ;
        fieldCache.put(obj, null);
        class1;
        JVM INSTR monitorexit ;
        throw s;
        s;
        throw s;
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
        throws NoSuchMethodException
    {
        String s1;
        StringBuilder stringbuilder = new StringBuilder(class1.getName());
        stringbuilder.append('#');
        stringbuilder.append(s);
        stringbuilder.append(getParametersString(aclass));
        stringbuilder.append("#bestmatch");
        s1 = stringbuilder.toString();
        HashMap hashmap = methodCache;
        hashmap;
        JVM INSTR monitorenter ;
        if(!methodCache.containsKey(s1))
            break MISSING_BLOCK_LABEL_101;
        class1 = (Method)methodCache.get(s1);
        if(class1 != null)
            break MISSING_BLOCK_LABEL_97;
        class1 = JVM INSTR new #44  <Class NoSuchMethodException>;
        class1.NoSuchMethodException(s1);
        throw class1;
        class1;
        hashmap;
        JVM INSTR monitorexit ;
        throw class1;
        hashmap;
        JVM INSTR monitorexit ;
        return class1;
        hashmap;
        JVM INSTR monitorexit ;
        Method method = findMethodExact(class1, s, aclass);
        hashmap = methodCache;
        hashmap;
        JVM INSTR monitorenter ;
        methodCache.put(s1, method);
        hashmap;
        JVM INSTR monitorexit ;
        return method;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
        Object obj = null;
        Method amethod[] = class1.getDeclaredMethods();
        int i = amethod.length;
        int j = 0;
        for(class1 = ((Class) (obj)); j < i; class1 = ((Class) (obj)))
        {
label0:
            {
                Method method1 = amethod[j];
                obj = class1;
                if(!method1.getName().equals(s))
                    break label0;
                obj = class1;
                if(!ClassUtils.isAssignable(aclass, method1.getParameterTypes(), true))
                    break label0;
                if(class1 != null)
                {
                    obj = class1;
                    if(MemberUtils.compareParameterTypes(method1.getParameterTypes(), class1.getParameterTypes(), aclass) >= 0)
                        break label0;
                }
                obj = method1;
            }
            j++;
        }

        if(class1 == null)
            break MISSING_BLOCK_LABEL_269;
        class1.setAccessible(true);
        s = methodCache;
        s;
        JVM INSTR monitorenter ;
        methodCache.put(s1, class1);
        s;
        JVM INSTR monitorexit ;
        return class1;
        class1;
        throw class1;
        s = new NoSuchMethodException(s1);
        class1 = methodCache;
        class1;
        JVM INSTR monitorenter ;
        methodCache.put(s1, null);
        class1;
        JVM INSTR monitorexit ;
        throw s;
        s;
        throw s;
    }

    public static transient Method findMethodBestMatch(Class class1, String s, Object aobj[])
        throws NoSuchMethodException
    {
        return findMethodBestMatch(class1, s, getParameterTypes(aobj));
    }

    public static transient Method findMethodExact(Class class1, String s, Class aclass[])
        throws NoSuchMethodException
    {
        Object obj;
        obj = new StringBuilder(class1.getName());
        ((StringBuilder) (obj)).append('#');
        ((StringBuilder) (obj)).append(s);
        ((StringBuilder) (obj)).append(getParametersString(aclass));
        ((StringBuilder) (obj)).append("#exact");
        obj = ((StringBuilder) (obj)).toString();
        HashMap hashmap = methodCache;
        hashmap;
        JVM INSTR monitorenter ;
        if(!methodCache.containsKey(obj))
            break MISSING_BLOCK_LABEL_101;
        class1 = (Method)methodCache.get(obj);
        if(class1 != null)
            break MISSING_BLOCK_LABEL_96;
        class1 = JVM INSTR new #44  <Class NoSuchMethodException>;
        class1.NoSuchMethodException(((String) (obj)));
        throw class1;
        class1;
        hashmap;
        JVM INSTR monitorexit ;
        throw class1;
        hashmap;
        JVM INSTR monitorexit ;
        return class1;
        hashmap;
        JVM INSTR monitorexit ;
        s = class1.getDeclaredMethod(s, aclass);
        s.setAccessible(true);
        class1 = methodCache;
        class1;
        JVM INSTR monitorenter ;
        methodCache.put(obj, s);
        class1;
        JVM INSTR monitorexit ;
        return s;
        s;
        class1;
        JVM INSTR monitorexit ;
        throw s;
        s;
        class1 = methodCache;
        class1;
        JVM INSTR monitorenter ;
        methodCache.put(obj, null);
        class1;
        JVM INSTR monitorexit ;
        throw s;
        s;
        throw s;
    }

    public static transient Method findMethodExact(Class class1, String s, Object aobj[])
        throws ClassNotFoundException, NoSuchMethodException
    {
        Class aclass[] = null;
        int i = aobj.length - 1;
        while(i >= 0) 
        {
            Object obj = aobj[i];
            if(obj == null)
                throw new NullPointerException("parameter type must not be null");
            Class aclass1[] = aclass;
            if(aclass == null)
                aclass1 = new Class[i + 1];
            if(obj instanceof Class)
                aclass1[i] = (Class)obj;
            else
            if(obj instanceof String)
                aclass1[i] = findClass((String)obj, class1.getClassLoader());
            else
                throw new IllegalArgumentException("parameter type must either be specified as Class or String", null);
            i--;
            aclass = aclass1;
        }
        aobj = aclass;
        if(aclass == null)
            aobj = new Class[0];
        return findMethodExact(class1, s, ((Class []) (aobj)));
    }

    public static transient Class[] getClassesAsArray(Class aclass[])
    {
        return aclass;
    }

    public static Object getObjectField(Object obj, String s, Class class1)
        throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException
    {
        return checkFieldValue(findField(obj.getClass(), s).get(obj), class1);
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

    public static Object getStaticObjectField(Class class1, String s, Class class2)
        throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException
    {
        class1 = findField(class1, s);
        try
        {
            class1 = ((Class) (checkFieldValue(class1.get(null), class2)));
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            throw new IllegalArgumentException(class1);
        }
        return class1;
    }

    public static Object getSurroundingThis(Object obj, Class class1)
        throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException
    {
        return getObjectField(obj, "this$0", class1);
    }

    public static transient Object newInstance(Class class1, Object aobj[])
        throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        return findConstructorBestMatch(class1, aobj).newInstance(aobj);
    }

    public static void setObjectField(Object obj, String s, Object obj1)
        throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException
    {
        findField(obj.getClass(), s).set(obj, obj1);
    }

    public static void setStaticObjectField(Class class1, String s, Object obj)
        throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException
    {
        class1 = findField(class1, s);
        try
        {
            class1.set(null, obj);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
        }
        throw new IllegalArgumentException(class1);
    }

    public static transient ObjectReference tryCallMethod(Object obj, String s, Class class1, Object aobj[])
    {
        try
        {
            obj = new ObjectReference(callMethod(obj, s, class1, aobj));
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.w("ReflectionUtils", "", ((Throwable) (obj)));
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.w("ReflectionUtils", "", ((Throwable) (obj)));
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.w("ReflectionUtils", "", ((Throwable) (obj)));
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.w("ReflectionUtils", "", ((Throwable) (obj)));
            return null;
        }
        return ((ObjectReference) (obj));
    }

    public static transient ObjectReference tryCallStaticMethod(Class class1, String s, Class class2, Object aobj[])
    {
        try
        {
            class1 = new ObjectReference(callStaticMethod(class1, s, class2, aobj));
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        return class1;
    }

    public static Class tryFindClass(String s, ClassLoader classloader)
    {
        try
        {
            s = findClass(s, classloader);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.w("ReflectionUtils", "", s);
            return null;
        }
        return s;
    }

    public static transient Constructor tryFindConstructorBestMatch(Class class1, Class aclass[])
    {
        try
        {
            class1 = findConstructorBestMatch(class1, aclass);
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        return class1;
    }

    public static transient Constructor tryFindConstructorBestMatch(Class class1, Object aobj[])
    {
        try
        {
            class1 = findConstructorBestMatch(class1, aobj);
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        return class1;
    }

    public static transient Constructor tryFindConstructorExact(Class class1, Class aclass[])
    {
        try
        {
            class1 = findConstructorExact(class1, aclass);
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        return class1;
    }

    public static Field tryFindField(Class class1, String s)
    {
        try
        {
            class1 = findField(class1, s);
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        return class1;
    }

    public static transient Method tryFindMethodBestMatch(Class class1, String s, Class aclass[])
    {
        try
        {
            class1 = findMethodBestMatch(class1, s, aclass);
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        return class1;
    }

    public static transient Method tryFindMethodBestMatch(Class class1, String s, Object aobj[])
    {
        try
        {
            class1 = findMethodBestMatch(class1, s, aobj);
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        return class1;
    }

    public static transient Method tryFindMethodExact(Class class1, String s, Class aclass[])
    {
        try
        {
            class1 = findMethodExact(class1, s, aclass);
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        return class1;
    }

    public static transient Method tryFindMethodExact(Class class1, String s, Object aobj[])
    {
        try
        {
            class1 = findMethodExact(class1, s, aobj);
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        return class1;
    }

    public static ObjectReference tryGetObjectField(Object obj, String s, Class class1)
    {
        try
        {
            obj = new ObjectReference(getObjectField(obj, s, class1));
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.w("ReflectionUtils", "", ((Throwable) (obj)));
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.w("ReflectionUtils", "", ((Throwable) (obj)));
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.w("ReflectionUtils", "", ((Throwable) (obj)));
            return null;
        }
        return ((ObjectReference) (obj));
    }

    public static ObjectReference tryGetStaticObjectField(Class class1, String s, Class class2)
    {
        try
        {
            class1 = new ObjectReference(getStaticObjectField(class1, s, class2));
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        return class1;
    }

    public static ObjectReference tryGetSurroundingThis(Object obj, Class class1)
    {
        try
        {
            obj = new ObjectReference(getSurroundingThis(obj, class1));
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.w("ReflectionUtils", "", ((Throwable) (obj)));
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.w("ReflectionUtils", "", ((Throwable) (obj)));
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.w("ReflectionUtils", "", ((Throwable) (obj)));
            return null;
        }
        return ((ObjectReference) (obj));
    }

    public static transient Object tryNewInstance(Class class1, Object aobj[])
    {
        try
        {
            class1 = ((Class) (newInstance(class1, aobj)));
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            Log.w("ReflectionUtils", "", class1);
            return null;
        }
        return class1;
    }

    public static void trySetObjectField(Object obj, String s, Object obj1)
    {
        setObjectField(obj, s, obj1);
_L1:
        return;
        obj;
        Log.w("ReflectionUtils", "", ((Throwable) (obj)));
          goto _L1
        obj;
        Log.w("ReflectionUtils", "", ((Throwable) (obj)));
          goto _L1
        obj;
        Log.w("ReflectionUtils", "", ((Throwable) (obj)));
          goto _L1
    }

    public static void trySetStaticObjectField(Class class1, String s, Object obj)
    {
        setStaticObjectField(class1, s, obj);
_L1:
        return;
        class1;
        Log.w("ReflectionUtils", "", class1);
          goto _L1
        class1;
        Log.w("ReflectionUtils", "", class1);
          goto _L1
        class1;
        Log.w("ReflectionUtils", "", class1);
          goto _L1
    }

    public static final ClassLoader BOOTCLASSLOADER = ClassLoader.getSystemClassLoader();
    private static final String TAG = "ReflectionUtils";
    private static final HashMap constructorCache = new HashMap();
    private static final HashMap fieldCache = new HashMap();
    private static final HashMap methodCache = new HashMap();

}
