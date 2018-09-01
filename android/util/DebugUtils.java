// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import java.io.PrintWriter;
import java.lang.reflect.*;
import java.util.Locale;

public class DebugUtils
{

    public DebugUtils()
    {
    }

    public static void buildShortClassTag(Object obj, StringBuilder stringbuilder)
    {
        if(obj != null) goto _L2; else goto _L1
_L1:
        stringbuilder.append("null");
_L4:
        return;
_L2:
        String s1;
label0:
        {
            String s = obj.getClass().getSimpleName();
            if(s != null)
            {
                s1 = s;
                if(!s.isEmpty())
                    break label0;
            }
            s = obj.getClass().getName();
            int i = s.lastIndexOf('.');
            s1 = s;
            if(i > 0)
                s1 = s.substring(i + 1);
        }
        stringbuilder.append(s1);
        stringbuilder.append('{');
        stringbuilder.append(Integer.toHexString(System.identityHashCode(obj)));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static String flagsToString(Class class1, String s, int i)
    {
        int j;
        StringBuilder stringbuilder;
        int k;
        j = 0;
        stringbuilder = new StringBuilder();
        class1 = class1.getDeclaredFields();
        k = class1.length;
_L2:
        Field field;
        int j1;
        if(j >= k)
            break; /* Loop/switch isn't completed */
        field = class1[j];
        int l = field.getModifiers();
        j1 = i;
        if(!Modifier.isStatic(l))
            break MISSING_BLOCK_LABEL_147;
        j1 = i;
        if(!Modifier.isFinal(l))
            break MISSING_BLOCK_LABEL_147;
        j1 = i;
        if(!field.getType().equals(Integer.TYPE))
            break MISSING_BLOCK_LABEL_147;
        j1 = i;
        if(!field.getName().startsWith(s))
            break MISSING_BLOCK_LABEL_147;
        j1 = i;
        int i1 = field.getInt(null);
        j1 = i;
        if((i & i1) == 0)
            break MISSING_BLOCK_LABEL_147;
        i &= i1;
        j1 = i;
        stringbuilder.append(field.getName().substring(s.length())).append('|');
        j1 = i;
_L3:
        j++;
        i = j1;
        if(true) goto _L2; else goto _L1
_L1:
        if(i != 0 || stringbuilder.length() == 0)
            stringbuilder.append(Integer.toHexString(i));
        else
            stringbuilder.deleteCharAt(stringbuilder.length() - 1);
        return stringbuilder.toString();
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
          goto _L3
    }

    public static boolean isObjectSelected(Object obj)
    {
        boolean flag;
        boolean flag1;
        Object obj1;
        boolean flag2;
        flag = false;
        flag1 = false;
        obj1 = System.getenv("ANDROID_OBJECT_FILTER");
        flag2 = flag;
        if(obj1 == null) goto _L2; else goto _L1
_L1:
        flag2 = flag;
        if(((String) (obj1)).length() <= 0) goto _L2; else goto _L3
_L3:
        String as[];
        as = ((String) (obj1)).split("@");
        flag2 = flag;
        if(!obj.getClass().getSimpleName().matches(as[0])) goto _L2; else goto _L4
_L4:
        int i = 1;
_L12:
        flag2 = flag1;
        if(i >= as.length) goto _L2; else goto _L5
_L5:
        String as1[];
        Class class1;
        as1 = as[i].split("=");
        class1 = obj.getClass();
        obj1 = class1;
        Object obj3;
        do
        {
            obj3 = JVM INSTR new #15  <Class StringBuilder>;
            ((StringBuilder) (obj3)).StringBuilder();
            obj3 = ((Class) (obj1)).getDeclaredMethod(((StringBuilder) (obj3)).append("get").append(as1[0].substring(0, 1).toUpperCase(Locale.ROOT)).append(as1[0].substring(1)).toString(), (Class[])null);
            obj1 = class1.getSuperclass();
        } while(obj1 != null && obj3 == null);
        flag2 = flag1;
        if(obj3 == null) goto _L7; else goto _L6
_L6:
        obj1 = ((Method) (obj3)).invoke(obj, (Object[])null);
        if(obj1 == null) goto _L9; else goto _L8
_L8:
        obj1 = obj1.toString();
_L10:
        flag2 = flag1 | ((String) (obj1)).matches(as1[1]);
_L7:
        i++;
        flag1 = flag2;
        continue; /* Loop/switch isn't completed */
_L9:
        obj1 = "null";
          goto _L10
        Object obj2;
        obj2;
        ((InvocationTargetException) (obj2)).printStackTrace();
        flag2 = flag1;
          goto _L7
        obj2;
        ((IllegalAccessException) (obj2)).printStackTrace();
        flag2 = flag1;
          goto _L7
        obj2;
        ((NoSuchMethodException) (obj2)).printStackTrace();
        flag2 = flag1;
          goto _L7
_L2:
        return flag2;
        if(true) goto _L12; else goto _L11
_L11:
    }

    public static void printSizeValue(PrintWriter printwriter, long l)
    {
        float f = l;
        String s = "";
        float f1 = f;
        if(f > 900F)
        {
            s = "KB";
            f1 = f / 1024F;
        }
        f = f1;
        if(f1 > 900F)
        {
            s = "MB";
            f = f1 / 1024F;
        }
        f1 = f;
        if(f > 900F)
        {
            s = "GB";
            f1 = f / 1024F;
        }
        f = f1;
        if(f1 > 900F)
        {
            s = "TB";
            f = f1 / 1024F;
        }
        f1 = f;
        String s1 = s;
        if(f > 900F)
        {
            s1 = "PB";
            f1 = f / 1024F;
        }
        if(f1 < 1.0F)
            s = String.format("%.2f", new Object[] {
                Float.valueOf(f1)
            });
        else
        if(f1 < 10F)
            s = String.format("%.1f", new Object[] {
                Float.valueOf(f1)
            });
        else
        if(f1 < 100F)
            s = String.format("%.0f", new Object[] {
                Float.valueOf(f1)
            });
        else
            s = String.format("%.0f", new Object[] {
                Float.valueOf(f1)
            });
        printwriter.print(s);
        printwriter.print(s1);
    }

    public static String sizeValueToString(long l, StringBuilder stringbuilder)
    {
        StringBuilder stringbuilder1 = stringbuilder;
        if(stringbuilder == null)
            stringbuilder1 = new StringBuilder(32);
        float f = l;
        stringbuilder = "";
        float f1 = f;
        if(f > 900F)
        {
            stringbuilder = "KB";
            f1 = f / 1024F;
        }
        f = f1;
        if(f1 > 900F)
        {
            stringbuilder = "MB";
            f = f1 / 1024F;
        }
        f1 = f;
        if(f > 900F)
        {
            stringbuilder = "GB";
            f1 = f / 1024F;
        }
        f = f1;
        if(f1 > 900F)
        {
            stringbuilder = "TB";
            f = f1 / 1024F;
        }
        f1 = f;
        Object obj = stringbuilder;
        if(f > 900F)
        {
            obj = "PB";
            f1 = f / 1024F;
        }
        if(f1 < 1.0F)
            stringbuilder = String.format("%.2f", new Object[] {
                Float.valueOf(f1)
            });
        else
        if(f1 < 10F)
            stringbuilder = String.format("%.1f", new Object[] {
                Float.valueOf(f1)
            });
        else
        if(f1 < 100F)
            stringbuilder = String.format("%.0f", new Object[] {
                Float.valueOf(f1)
            });
        else
            stringbuilder = String.format("%.0f", new Object[] {
                Float.valueOf(f1)
            });
        stringbuilder1.append(stringbuilder);
        stringbuilder1.append(((String) (obj)));
        return stringbuilder1.toString();
    }

    public static String valueToString(Class class1, String s, int i)
    {
        int j;
        int k;
        class1 = class1.getDeclaredFields();
        j = 0;
        k = class1.length;
_L3:
        if(j >= k) goto _L2; else goto _L1
_L1:
        Object obj;
        obj = class1[j];
        int l = ((Field) (obj)).getModifiers();
        if(!Modifier.isStatic(l) || !Modifier.isFinal(l) || !((Field) (obj)).getType().equals(Integer.TYPE) || !((Field) (obj)).getName().startsWith(s))
            continue; /* Loop/switch isn't completed */
        if(i != ((Field) (obj)).getInt(null))
            continue; /* Loop/switch isn't completed */
        obj = ((Field) (obj)).getName().substring(s.length());
        return ((String) (obj));
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
        j++;
          goto _L3
_L2:
        return Integer.toString(i);
    }
}
