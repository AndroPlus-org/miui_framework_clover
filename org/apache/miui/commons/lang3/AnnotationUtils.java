// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.miui.commons.lang3.builder.ToStringBuilder;
import org.apache.miui.commons.lang3.builder.ToStringStyle;

// Referenced classes of package org.apache.miui.commons.lang3:
//            Validate, ClassUtils

public class AnnotationUtils
{

    public AnnotationUtils()
    {
    }

    private static boolean annotationArrayMemberEquals(Annotation aannotation[], Annotation aannotation1[])
    {
        if(aannotation.length != aannotation1.length)
            return false;
        for(int i = 0; i < aannotation.length; i++)
            if(!equals(aannotation[i], aannotation1[i]))
                return false;

        return true;
    }

    private static boolean arrayMemberEquals(Class class1, Object obj, Object obj1)
    {
        if(class1.isAnnotation())
            return annotationArrayMemberEquals((Annotation[])obj, (Annotation[])obj1);
        if(class1.equals(Byte.TYPE))
            return Arrays.equals((byte[])obj, (byte[])obj1);
        if(class1.equals(Short.TYPE))
            return Arrays.equals((short[])obj, (short[])obj1);
        if(class1.equals(Integer.TYPE))
            return Arrays.equals((int[])obj, (int[])obj1);
        if(class1.equals(Character.TYPE))
            return Arrays.equals((char[])obj, (char[])obj1);
        if(class1.equals(Long.TYPE))
            return Arrays.equals((long[])obj, (long[])obj1);
        if(class1.equals(Float.TYPE))
            return Arrays.equals((float[])obj, (float[])obj1);
        if(class1.equals(Double.TYPE))
            return Arrays.equals((double[])obj, (double[])obj1);
        if(class1.equals(Boolean.TYPE))
            return Arrays.equals((boolean[])obj, (boolean[])obj1);
        else
            return Arrays.equals((Object[])obj, (Object[])obj1);
    }

    private static int arrayMemberHash(Class class1, Object obj)
    {
        if(class1.equals(Byte.TYPE))
            return Arrays.hashCode((byte[])obj);
        if(class1.equals(Short.TYPE))
            return Arrays.hashCode((short[])obj);
        if(class1.equals(Integer.TYPE))
            return Arrays.hashCode((int[])obj);
        if(class1.equals(Character.TYPE))
            return Arrays.hashCode((char[])obj);
        if(class1.equals(Long.TYPE))
            return Arrays.hashCode((long[])obj);
        if(class1.equals(Float.TYPE))
            return Arrays.hashCode((float[])obj);
        if(class1.equals(Double.TYPE))
            return Arrays.hashCode((double[])obj);
        if(class1.equals(Boolean.TYPE))
            return Arrays.hashCode((boolean[])obj);
        else
            return Arrays.hashCode((Object[])obj);
    }

    public static boolean equals(Annotation annotation, Annotation annotation1)
    {
        if(annotation == annotation1)
            return true;
        if(annotation == null || annotation1 == null)
            return false;
        Object obj = annotation.annotationType();
        Class class1 = annotation1.annotationType();
        Validate.notNull(obj, "Annotation %s with null annotationType()", new Object[] {
            annotation
        });
        Validate.notNull(class1, "Annotation %s with null annotationType()", new Object[] {
            annotation1
        });
        if(!((Class) (obj)).equals(class1))
            return false;
        Method amethod[];
        int i;
        int j;
        Method method;
        Object obj1;
        boolean flag;
        try
        {
            amethod = ((Class) (obj)).getDeclaredMethods();
            i = amethod.length;
        }
        // Misplaced declaration of an exception variable
        catch(Annotation annotation)
        {
            return false;
        }
        // Misplaced declaration of an exception variable
        catch(Annotation annotation)
        {
            return false;
        }
        j = 0;
        if(j >= i) goto _L2; else goto _L1
_L1:
        method = amethod[j];
        if(method.getParameterTypes().length != 0 || !isValidAnnotationMemberType(method.getReturnType()))
            continue; /* Loop/switch isn't completed */
        obj1 = method.invoke(annotation, new Object[0]);
        obj = method.invoke(annotation1, new Object[0]);
        flag = memberEquals(method.getReturnType(), obj1, obj);
        if(!flag)
            return false;
        j++;
        break MISSING_BLOCK_LABEL_83;
_L2:
        return true;
    }

    public static int hashCode(Annotation annotation)
    {
        int i;
        int j;
        Method amethod[];
        int k;
        i = 0;
        j = 0;
        amethod = annotation.annotationType().getDeclaredMethods();
        k = amethod.length;
_L2:
        if(i >= k)
            break; /* Loop/switch isn't completed */
        Method method = amethod[i];
        Object obj;
        int l;
        try
        {
            obj = method.invoke(annotation, new Object[0]);
        }
        // Misplaced declaration of an exception variable
        catch(Annotation annotation)
        {
            throw annotation;
        }
        // Misplaced declaration of an exception variable
        catch(Annotation annotation)
        {
            throw new RuntimeException(annotation);
        }
        if(obj != null)
            break MISSING_BLOCK_LABEL_73;
        annotation = JVM INSTR new #194 <Class IllegalStateException>;
        annotation.IllegalStateException(String.format("Annotation method %s returned null", new Object[] {
            method
        }));
        throw annotation;
        l = hashMember(method.getName(), obj);
        j += l;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        return j;
    }

    private static int hashMember(String s, Object obj)
    {
        int i = s.hashCode() * 127;
        if(obj.getClass().isArray())
            return arrayMemberHash(obj.getClass().getComponentType(), obj) ^ i;
        if(obj instanceof Annotation)
            return hashCode((Annotation)obj) ^ i;
        else
            return obj.hashCode() ^ i;
    }

    public static boolean isValidAnnotationMemberType(Class class1)
    {
        if(class1 == null)
            return false;
        Class class2 = class1;
        if(class1.isArray())
            class2 = class1.getComponentType();
        boolean flag;
        if(!class2.isPrimitive() && !class2.isEnum() && !class2.isAnnotation() && !java/lang/String.equals(class2))
            flag = java/lang/Class.equals(class2);
        else
            flag = true;
        return flag;
    }

    private static boolean memberEquals(Class class1, Object obj, Object obj1)
    {
        if(obj == obj1)
            return true;
        if(obj == null || obj1 == null)
            return false;
        if(class1.isArray())
            return arrayMemberEquals(class1.getComponentType(), obj, obj1);
        if(class1.isAnnotation())
            return equals((Annotation)obj, (Annotation)obj1);
        else
            return obj.equals(obj1);
    }

    public static String toString(Annotation annotation)
    {
        int i = 0;
        ToStringBuilder tostringbuilder = new ToStringBuilder(annotation, TO_STRING_STYLE);
        Method amethod[] = annotation.annotationType().getDeclaredMethods();
        int j = amethod.length;
        while(i < j) 
        {
            Method method = amethod[i];
            if(method.getParameterTypes().length <= 0)
                try
                {
                    tostringbuilder.append(method.getName(), method.invoke(annotation, new Object[0]));
                }
                // Misplaced declaration of an exception variable
                catch(Annotation annotation)
                {
                    throw annotation;
                }
                // Misplaced declaration of an exception variable
                catch(Annotation annotation)
                {
                    throw new RuntimeException(annotation);
                }
            i++;
        }
        return tostringbuilder.build();
    }

    private static final ToStringStyle TO_STRING_STYLE = new ToStringStyle() {

        protected void appendDetail(StringBuffer stringbuffer, String s, Object obj)
        {
            Object obj1 = obj;
            if(obj instanceof Annotation)
                obj1 = AnnotationUtils.toString((Annotation)obj);
            super.appendDetail(stringbuffer, s, obj1);
        }

        protected String getShortClassName(Class class1)
        {
            Object obj = null;
            Iterator iterator = ClassUtils.getAllInterfaces(class1).iterator();
            do
            {
                class1 = obj;
                if(!iterator.hasNext())
                    break;
                class1 = (Class)iterator.next();
            } while(!java/lang/annotation/Annotation.isAssignableFrom(class1));
            if(class1 == null)
                class1 = "";
            else
                class1 = class1.getName();
            return (new StringBuilder(class1)).insert(0, '@').toString();
        }

        private static final long serialVersionUID = 1L;

            
            {
                setDefaultFullDetail(true);
                setArrayContentDetail(true);
                setUseClassName(true);
                setUseShortClassName(true);
                setUseIdentityHashCode(false);
                setContentStart("(");
                setContentEnd(")");
                setFieldSeparator(", ");
                setArrayStart("[");
                setArrayEnd("]");
            }
    }
;

}
