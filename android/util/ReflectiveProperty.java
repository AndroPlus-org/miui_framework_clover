// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import java.lang.reflect.*;

// Referenced classes of package android.util:
//            Property, NoSuchPropertyException

class ReflectiveProperty extends Property
{

    public ReflectiveProperty(Class class1, Class class2, String s)
    {
        Object obj;
        String s1;
        super(class2, s);
        char c = Character.toUpperCase(s.charAt(0));
        obj = s.substring(1);
        obj = (new StringBuilder()).append(c).append(((String) (obj))).toString();
        s1 = (new StringBuilder()).append("get").append(((String) (obj))).toString();
        mGetter = class1.getMethod(s1, (Class[])null);
_L2:
        s = mGetter.getReturnType();
        if(!typesMatch(class2, s))
            throw new NoSuchPropertyException((new StringBuilder()).append("Underlying type (").append(s).append(") ").append("does not match Property type (").append(class2).append(")").toString());
        break; /* Loop/switch isn't completed */
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
        String s2 = (new StringBuilder()).append("is").append(((String) (obj))).toString();
        try
        {
            mGetter = class1.getMethod(s2, (Class[])null);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            try
            {
                mField = class1.getField(s);
                class1 = mField.getType();
                if(!typesMatch(class2, class1))
                {
                    obj = JVM INSTR new #83  <Class NoSuchPropertyException>;
                    StringBuilder stringbuilder = JVM INSTR new #47  <Class StringBuilder>;
                    stringbuilder.StringBuilder();
                    ((NoSuchPropertyException) (obj)).NoSuchPropertyException(stringbuilder.append("Underlying type (").append(class1).append(") ").append("does not match Property type (").append(class2).append(")").toString());
                    throw obj;
                }
            }
            // Misplaced declaration of an exception variable
            catch(Class class1)
            {
                throw new NoSuchPropertyException((new StringBuilder()).append("No accessor method or field found for property with name ").append(s).toString());
            }
            return;
        }
        if(true) goto _L2; else goto _L1
_L1:
        class2 = (new StringBuilder()).append("set").append(((String) (obj))).toString();
        mSetter = class1.getMethod(class2, new Class[] {
            s
        });
_L4:
        return;
        class1;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private boolean typesMatch(Class class1, Class class2)
    {
        boolean flag;
        flag = true;
        if(class2 == class1)
            break MISSING_BLOCK_LABEL_153;
        if(!class2.isPrimitive()) goto _L2; else goto _L1
_L1:
        if(class2 != Float.TYPE || class1 != java/lang/Float) goto _L4; else goto _L3
_L3:
        boolean flag1 = flag;
_L5:
        return flag1;
_L4:
        if(class2 == Integer.TYPE)
        {
            flag1 = flag;
            if(class1 == java/lang/Integer)
                continue; /* Loop/switch isn't completed */
        }
        if(class2 == Boolean.TYPE)
        {
            flag1 = flag;
            if(class1 == java/lang/Boolean)
                continue; /* Loop/switch isn't completed */
        }
        if(class2 == Long.TYPE)
        {
            flag1 = flag;
            if(class1 == java/lang/Long)
                continue; /* Loop/switch isn't completed */
        }
        if(class2 == Double.TYPE)
        {
            flag1 = flag;
            if(class1 == java/lang/Double)
                continue; /* Loop/switch isn't completed */
        }
        if(class2 == Short.TYPE)
        {
            flag1 = flag;
            if(class1 == java/lang/Short)
                continue; /* Loop/switch isn't completed */
        }
        if(class2 == Byte.TYPE)
        {
            flag1 = flag;
            if(class1 == java/lang/Byte)
                continue; /* Loop/switch isn't completed */
        }
        if(class2 == Character.TYPE)
        {
            flag1 = flag;
            if(class1 == java/lang/Character)
                continue; /* Loop/switch isn't completed */
        }
        flag1 = false;
        if(true) goto _L5; else goto _L2
_L2:
        return false;
        return true;
    }

    public Object get(Object obj)
    {
        if(mGetter != null)
        {
            try
            {
                obj = mGetter.invoke(obj, (Object[])null);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                throw new AssertionError();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                throw new RuntimeException(((InvocationTargetException) (obj)).getCause());
            }
            return obj;
        }
        if(mField != null)
        {
            try
            {
                obj = mField.get(obj);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                throw new AssertionError();
            }
            return obj;
        } else
        {
            throw new AssertionError();
        }
    }

    public boolean isReadOnly()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mSetter == null)
        {
            flag1 = flag;
            if(mField == null)
                flag1 = true;
        }
        return flag1;
    }

    public void set(Object obj, Object obj1)
    {
        if(mSetter == null)
            break MISSING_BLOCK_LABEL_47;
        mSetter.invoke(obj, new Object[] {
            obj1
        });
_L1:
        return;
        obj;
        throw new RuntimeException(((InvocationTargetException) (obj)).getCause());
        obj;
        throw new AssertionError();
        if(mField != null)
            try
            {
                mField.set(obj, obj1);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                throw new AssertionError();
            }
        else
            throw new UnsupportedOperationException((new StringBuilder()).append("Property ").append(getName()).append(" is read-only").toString());
          goto _L1
    }

    private static final String PREFIX_GET = "get";
    private static final String PREFIX_IS = "is";
    private static final String PREFIX_SET = "set";
    private Field mField;
    private Method mGetter;
    private Method mSetter;
}
