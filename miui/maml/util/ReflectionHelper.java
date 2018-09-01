// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util;

import android.text.TextUtils;
import java.util.HashMap;

public class ReflectionHelper
{

    public ReflectionHelper()
    {
    }

    public static Object getEnumConstant(String s, String s1)
    {
        if(TextUtils.isEmpty(s1) || TextUtils.isEmpty(s1))
            return null;
        s = Enum.valueOf(Class.forName(s), s1);
        return s;
        s;
_L2:
        return null;
        s;
        continue; /* Loop/switch isn't completed */
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static Class strTypeToClass(String s)
    {
        try
        {
            s = strTypeToClassThrows(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        return s;
    }

    private static Class strTypeToClassThrows(String s)
        throws ClassNotFoundException
    {
        if(PRIMITIVE_TYPE.containsKey(s))
            return (Class)PRIMITIVE_TYPE.get(s);
        if(!s.contains("."))
            s = (new StringBuilder()).append("java.lang.").append(s).toString();
        return Class.forName(s);
    }

    public static Class[] strTypesToClass(String as[])
        throws ClassNotFoundException
    {
        if(as == null)
            return null;
        Class aclass[] = new Class[as.length];
        for(int i = 0; i < as.length; i++)
            aclass[i] = strTypeToClassThrows(as[i]);

        return aclass;
    }

    static HashMap PRIMITIVE_TYPE;

    static 
    {
        PRIMITIVE_TYPE = new HashMap();
        PRIMITIVE_TYPE.put("byte", Byte.TYPE);
        PRIMITIVE_TYPE.put("short", Short.TYPE);
        PRIMITIVE_TYPE.put("int", Integer.TYPE);
        PRIMITIVE_TYPE.put("long", Long.TYPE);
        PRIMITIVE_TYPE.put("char", Character.TYPE);
        PRIMITIVE_TYPE.put("boolean", Boolean.TYPE);
        PRIMITIVE_TYPE.put("float", Float.TYPE);
        PRIMITIVE_TYPE.put("double", Double.TYPE);
        PRIMITIVE_TYPE.put("byte[]", [B);
        PRIMITIVE_TYPE.put("short[]", [S);
        PRIMITIVE_TYPE.put("int[]", [I);
        PRIMITIVE_TYPE.put("long[]", [J);
        PRIMITIVE_TYPE.put("char[]", [C);
        PRIMITIVE_TYPE.put("boolean[]", [Z);
        PRIMITIVE_TYPE.put("float[]", [F);
        PRIMITIVE_TYPE.put("double[]", [D);
    }
}
