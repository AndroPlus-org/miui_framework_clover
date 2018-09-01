// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.framework.protobuf.nano;

import java.lang.reflect.*;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package com.android.framework.protobuf.nano:
//            MessageNano

public final class MessageNanoPrinter
{

    private MessageNanoPrinter()
    {
    }

    private static void appendQuotedBytes(byte abyte0[], StringBuffer stringbuffer)
    {
        if(abyte0 == null)
        {
            stringbuffer.append("\"\"");
            return;
        }
        stringbuffer.append('"');
        int i = 0;
        while(i < abyte0.length) 
        {
            int j = abyte0[i] & 0xff;
            if(j == 92 || j == 34)
                stringbuffer.append('\\').append((char)j);
            else
            if(j >= 32 && j < 127)
                stringbuffer.append((char)j);
            else
                stringbuffer.append(String.format("\\%03o", new Object[] {
                    Integer.valueOf(j)
                }));
            i++;
        }
        stringbuffer.append('"');
    }

    private static String deCamelCaseify(String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        int i = 0;
        while(i < s.length()) 
        {
            char c = s.charAt(i);
            if(i == 0)
                stringbuffer.append(Character.toLowerCase(c));
            else
            if(Character.isUpperCase(c))
                stringbuffer.append('_').append(Character.toLowerCase(c));
            else
                stringbuffer.append(c);
            i++;
        }
        return stringbuffer.toString();
    }

    private static String escapeString(String s)
    {
        int i = s.length();
        StringBuilder stringbuilder = new StringBuilder(i);
        int j = 0;
        while(j < i) 
        {
            char c = s.charAt(j);
            if(c >= ' ' && c <= '~' && c != '"' && c != '\'')
                stringbuilder.append(c);
            else
                stringbuilder.append(String.format("\\u%04x", new Object[] {
                    Integer.valueOf(c)
                }));
            j++;
        }
        return stringbuilder.toString();
    }

    public static String print(MessageNano messagenano)
    {
        if(messagenano == null)
            return "";
        StringBuffer stringbuffer = new StringBuffer();
        try
        {
            StringBuffer stringbuffer1 = JVM INSTR new #22  <Class StringBuffer>;
            stringbuffer1.StringBuffer();
            print(null, messagenano, stringbuffer1, stringbuffer);
        }
        // Misplaced declaration of an exception variable
        catch(MessageNano messagenano)
        {
            return (new StringBuilder()).append("Error printing proto: ").append(messagenano.getMessage()).toString();
        }
        // Misplaced declaration of an exception variable
        catch(MessageNano messagenano)
        {
            return (new StringBuilder()).append("Error printing proto: ").append(messagenano.getMessage()).toString();
        }
        return stringbuffer.toString();
    }

    private static void print(String s, Object obj, StringBuffer stringbuffer, StringBuffer stringbuffer1)
        throws IllegalAccessException, InvocationTargetException
    {
        if(obj != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(!(obj instanceof MessageNano)) goto _L4; else goto _L3
_L3:
        int i;
        Class class1;
        int j;
        int k1;
        Method amethod[];
        i = stringbuffer.length();
        if(s != null)
        {
            stringbuffer1.append(stringbuffer).append(deCamelCaseify(s)).append(" <\n");
            stringbuffer.append("  ");
        }
        class1 = obj.getClass();
        Field afield[] = class1.getFields();
        j = 0;
        int l = afield.length;
        do
        {
            if(j >= l)
                break;
            Object obj1 = afield[j];
            int i1 = ((Field) (obj1)).getModifiers();
            String s2 = ((Field) (obj1)).getName();
            if(!"cachedSize".equals(s2) && (i1 & 1) == 1 && (i1 & 8) != 8 && s2.startsWith("_") ^ true && s2.endsWith("_") ^ true)
            {
                Class class2 = ((Field) (obj1)).getType();
                obj1 = ((Field) (obj1)).get(obj);
                if(class2.isArray())
                {
                    if(class2.getComponentType() == Byte.TYPE)
                    {
                        print(s2, obj1, stringbuffer, stringbuffer1);
                    } else
                    {
                        int j1;
                        int l1;
                        if(obj1 == null)
                            j1 = 0;
                        else
                            j1 = Array.getLength(obj1);
                        l1 = 0;
                        while(l1 < j1) 
                        {
                            print(s2, Array.get(obj1, l1), stringbuffer, stringbuffer1);
                            l1++;
                        }
                    }
                } else
                {
                    print(s2, obj1, stringbuffer, stringbuffer1);
                }
            }
            j++;
        } while(true);
        amethod = class1.getMethods();
        k1 = amethod.length;
        j = 0;
_L10:
        if(j >= k1) goto _L6; else goto _L5
_L5:
        String s1 = amethod[j].getName();
        if(!s1.startsWith("set")) goto _L8; else goto _L7
_L7:
        s1 = s1.substring(3);
        Object obj2;
        obj2 = JVM INSTR new #71  <Class StringBuilder>;
        ((StringBuilder) (obj2)).StringBuilder();
        obj2 = class1.getMethod(((StringBuilder) (obj2)).append("has").append(s1).toString(), new Class[0]);
        if(((Boolean)((Method) (obj2)).invoke(obj, new Object[0])).booleanValue()) goto _L9; else goto _L8
_L8:
        j++;
          goto _L10
_L9:
        obj2 = JVM INSTR new #71  <Class StringBuilder>;
        ((StringBuilder) (obj2)).StringBuilder();
        obj2 = class1.getMethod(((StringBuilder) (obj2)).append("get").append(s1).toString(), new Class[0]);
        print(s1, ((Method) (obj2)).invoke(obj, new Object[0]), stringbuffer, stringbuffer1);
          goto _L8
_L6:
        if(s != null)
        {
            stringbuffer.setLength(i);
            stringbuffer1.append(stringbuffer).append(">\n");
        }
          goto _L1
_L4:
        if(obj instanceof Map)
        {
            obj = (Map)obj;
            s = deCamelCaseify(s);
            Iterator iterator = ((Map) (obj)).entrySet().iterator();
            while(iterator.hasNext()) 
            {
                obj = (java.util.Map.Entry)iterator.next();
                stringbuffer1.append(stringbuffer).append(s).append(" <\n");
                int k = stringbuffer.length();
                stringbuffer.append("  ");
                print("key", ((java.util.Map.Entry) (obj)).getKey(), stringbuffer, stringbuffer1);
                print("value", ((java.util.Map.Entry) (obj)).getValue(), stringbuffer, stringbuffer1);
                stringbuffer.setLength(k);
                stringbuffer1.append(stringbuffer).append(">\n");
            }
        } else
        {
            s = deCamelCaseify(s);
            stringbuffer1.append(stringbuffer).append(s).append(": ");
            if(obj instanceof String)
            {
                s = sanitizeString((String)obj);
                stringbuffer1.append("\"").append(s).append("\"");
            } else
            if(obj instanceof byte[])
                appendQuotedBytes((byte[])obj, stringbuffer1);
            else
                stringbuffer1.append(obj);
            stringbuffer1.append("\n");
        }
          goto _L1
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
          goto _L8
        nosuchmethodexception;
          goto _L8
    }

    private static String sanitizeString(String s)
    {
        String s1 = s;
        if(!s.startsWith("http"))
        {
            s1 = s;
            if(s.length() > 200)
                s1 = (new StringBuilder()).append(s.substring(0, 200)).append("[...]").toString();
        }
        return escapeString(s1);
    }

    private static final String INDENT = "  ";
    private static final int MAX_STRING_LEN = 200;
}
