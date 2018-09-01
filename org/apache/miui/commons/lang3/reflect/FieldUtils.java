// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import org.apache.miui.commons.lang3.ClassUtils;

// Referenced classes of package org.apache.miui.commons.lang3.reflect:
//            MemberUtils

public class FieldUtils
{

    public FieldUtils()
    {
    }

    public static Field getDeclaredField(Class class1, String s)
    {
        return getDeclaredField(class1, s, false);
    }

    public static Field getDeclaredField(Class class1, String s, boolean flag)
    {
        if(class1 == null)
            throw new IllegalArgumentException("The class must not be null");
        if(s == null)
            throw new IllegalArgumentException("The field name must not be null");
        class1 = class1.getDeclaredField(s);
        if(!MemberUtils.isAccessible(class1))
        {
            if(!flag)
                break MISSING_BLOCK_LABEL_52;
            try
            {
                class1.setAccessible(true);
            }
            // Misplaced declaration of an exception variable
            catch(Class class1)
            {
                return null;
            }
        }
        return class1;
        return null;
    }

    public static Field getField(Class class1, String s)
    {
        class1 = getField(class1, s, false);
        MemberUtils.setAccessibleWorkaround(class1);
        return class1;
    }

    public static Field getField(Class class1, String s, boolean flag)
    {
        Object obj;
        if(class1 == null)
            throw new IllegalArgumentException("The class must not be null");
        if(s == null)
            throw new IllegalArgumentException("The field name must not be null");
        obj = class1;
_L3:
        if(obj == null) goto _L2; else goto _L1
_L1:
        Object obj1;
        obj1 = ((Class) (obj)).getDeclaredField(s);
        if(Modifier.isPublic(((Field) (obj1)).getModifiers()))
            break MISSING_BLOCK_LABEL_62;
        if(!flag)
            continue; /* Loop/switch isn't completed */
        ((Field) (obj1)).setAccessible(true);
        return ((Field) (obj1));
        NoSuchFieldException nosuchfieldexception;
        nosuchfieldexception;
        obj = ((Class) (obj)).getSuperclass();
          goto _L3
_L2:
        Iterator iterator;
        obj = null;
        iterator = ClassUtils.getAllInterfaces(class1).iterator();
_L5:
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        nosuchfieldexception = (Class)iterator.next();
        nosuchfieldexception = nosuchfieldexception.getField(s);
        if(obj != null)
            try
            {
                IllegalArgumentException illegalargumentexception = JVM INSTR new #20  <Class IllegalArgumentException>;
                nosuchfieldexception = JVM INSTR new #92  <Class StringBuilder>;
                nosuchfieldexception.StringBuilder();
                illegalargumentexception.IllegalArgumentException(nosuchfieldexception.append("Reference to field ").append(s).append(" is ambiguous relative to ").append(class1).append("; a matching field exists on two or more implemented interfaces.").toString());
                throw illegalargumentexception;
            }
            // Misplaced declaration of an exception variable
            catch(NoSuchFieldException nosuchfieldexception) { }
        else
            obj = nosuchfieldexception;
        if(true) goto _L5; else goto _L4
_L4:
        return ((Field) (obj));
    }

    public static Object readDeclaredField(Object obj, String s)
        throws IllegalAccessException
    {
        return readDeclaredField(obj, s, false);
    }

    public static Object readDeclaredField(Object obj, String s, boolean flag)
        throws IllegalAccessException
    {
        if(obj == null)
            throw new IllegalArgumentException("target object must not be null");
        Class class1 = obj.getClass();
        Field field = getDeclaredField(class1, s, flag);
        if(field == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Cannot locate declared field ").append(class1.getName()).append(".").append(s).toString());
        else
            return readField(field, obj);
    }

    public static Object readDeclaredStaticField(Class class1, String s)
        throws IllegalAccessException
    {
        return readDeclaredStaticField(class1, s, false);
    }

    public static Object readDeclaredStaticField(Class class1, String s, boolean flag)
        throws IllegalAccessException
    {
        Field field = getDeclaredField(class1, s, flag);
        if(field == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Cannot locate declared field ").append(class1.getName()).append(".").append(s).toString());
        else
            return readStaticField(field, false);
    }

    public static Object readField(Object obj, String s)
        throws IllegalAccessException
    {
        return readField(obj, s, false);
    }

    public static Object readField(Object obj, String s, boolean flag)
        throws IllegalAccessException
    {
        if(obj == null)
            throw new IllegalArgumentException("target object must not be null");
        Class class1 = obj.getClass();
        Field field = getField(class1, s, flag);
        if(field == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Cannot locate field ").append(s).append(" on ").append(class1).toString());
        else
            return readField(field, obj);
    }

    public static Object readField(Field field, Object obj)
        throws IllegalAccessException
    {
        return readField(field, obj, false);
    }

    public static Object readField(Field field, Object obj, boolean flag)
        throws IllegalAccessException
    {
        if(field == null)
            throw new IllegalArgumentException("The field must not be null");
        if(flag && field.isAccessible() ^ true)
            field.setAccessible(true);
        else
            MemberUtils.setAccessibleWorkaround(field);
        return field.get(obj);
    }

    public static Object readStaticField(Class class1, String s)
        throws IllegalAccessException
    {
        return readStaticField(class1, s, false);
    }

    public static Object readStaticField(Class class1, String s, boolean flag)
        throws IllegalAccessException
    {
        Field field = getField(class1, s, flag);
        if(field == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Cannot locate field ").append(s).append(" on ").append(class1).toString());
        else
            return readStaticField(field, false);
    }

    public static Object readStaticField(Field field)
        throws IllegalAccessException
    {
        return readStaticField(field, false);
    }

    public static Object readStaticField(Field field, boolean flag)
        throws IllegalAccessException
    {
        if(field == null)
            throw new IllegalArgumentException("The field must not be null");
        if(!Modifier.isStatic(field.getModifiers()))
            throw new IllegalArgumentException((new StringBuilder()).append("The field '").append(field.getName()).append("' is not static").toString());
        else
            return readField(field, (Object)null, flag);
    }

    public static void writeDeclaredField(Object obj, String s, Object obj1)
        throws IllegalAccessException
    {
        writeDeclaredField(obj, s, obj1, false);
    }

    public static void writeDeclaredField(Object obj, String s, Object obj1, boolean flag)
        throws IllegalAccessException
    {
        if(obj == null)
            throw new IllegalArgumentException("target object must not be null");
        Class class1 = obj.getClass();
        Field field = getDeclaredField(class1, s, flag);
        if(field == null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Cannot locate declared field ").append(class1.getName()).append(".").append(s).toString());
        } else
        {
            writeField(field, obj, obj1);
            return;
        }
    }

    public static void writeDeclaredStaticField(Class class1, String s, Object obj)
        throws IllegalAccessException
    {
        writeDeclaredStaticField(class1, s, obj, false);
    }

    public static void writeDeclaredStaticField(Class class1, String s, Object obj, boolean flag)
        throws IllegalAccessException
    {
        Field field = getDeclaredField(class1, s, flag);
        if(field == null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Cannot locate declared field ").append(class1.getName()).append(".").append(s).toString());
        } else
        {
            writeField(field, (Object)null, obj);
            return;
        }
    }

    public static void writeField(Object obj, String s, Object obj1)
        throws IllegalAccessException
    {
        writeField(obj, s, obj1, false);
    }

    public static void writeField(Object obj, String s, Object obj1, boolean flag)
        throws IllegalAccessException
    {
        if(obj == null)
            throw new IllegalArgumentException("target object must not be null");
        Class class1 = obj.getClass();
        Field field = getField(class1, s, flag);
        if(field == null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Cannot locate declared field ").append(class1.getName()).append(".").append(s).toString());
        } else
        {
            writeField(field, obj, obj1);
            return;
        }
    }

    public static void writeField(Field field, Object obj, Object obj1)
        throws IllegalAccessException
    {
        writeField(field, obj, obj1, false);
    }

    public static void writeField(Field field, Object obj, Object obj1, boolean flag)
        throws IllegalAccessException
    {
        if(field == null)
            throw new IllegalArgumentException("The field must not be null");
        if(flag && field.isAccessible() ^ true)
            field.setAccessible(true);
        else
            MemberUtils.setAccessibleWorkaround(field);
        field.set(obj, obj1);
    }

    public static void writeStaticField(Class class1, String s, Object obj)
        throws IllegalAccessException
    {
        writeStaticField(class1, s, obj, false);
    }

    public static void writeStaticField(Class class1, String s, Object obj, boolean flag)
        throws IllegalAccessException
    {
        Field field = getField(class1, s, flag);
        if(field == null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Cannot locate field ").append(s).append(" on ").append(class1).toString());
        } else
        {
            writeStaticField(field, obj);
            return;
        }
    }

    public static void writeStaticField(Field field, Object obj)
        throws IllegalAccessException
    {
        writeStaticField(field, obj, false);
    }

    public static void writeStaticField(Field field, Object obj, boolean flag)
        throws IllegalAccessException
    {
        if(field == null)
            throw new IllegalArgumentException("The field must not be null");
        if(!Modifier.isStatic(field.getModifiers()))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("The field '").append(field.getName()).append("' is not static").toString());
        } else
        {
            writeField(field, (Object)null, obj, flag);
            return;
        }
    }
}
