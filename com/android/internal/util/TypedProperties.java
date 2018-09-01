// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypedProperties extends HashMap
{
    public static class ParseException extends IllegalArgumentException
    {

        ParseException(StreamTokenizer streamtokenizer, String s)
        {
            super((new StringBuilder()).append("expected ").append(s).append(", saw ").append(streamtokenizer.toString()).toString());
        }
    }

    public static class TypeException extends IllegalArgumentException
    {

        TypeException(String s, Object obj, String s1)
        {
            super((new StringBuilder()).append(s).append(" has type ").append(obj.getClass().getName()).append(", not ").append(s1).toString());
        }
    }


    public TypedProperties()
    {
    }

    static StreamTokenizer initTokenizer(Reader reader)
    {
        reader = new StreamTokenizer(reader);
        reader.resetSyntax();
        reader.wordChars(48, 57);
        reader.wordChars(65, 90);
        reader.wordChars(97, 122);
        reader.wordChars(95, 95);
        reader.wordChars(36, 36);
        reader.wordChars(46, 46);
        reader.wordChars(45, 45);
        reader.wordChars(43, 43);
        reader.ordinaryChar(61);
        reader.whitespaceChars(32, 32);
        reader.whitespaceChars(9, 9);
        reader.whitespaceChars(10, 10);
        reader.whitespaceChars(13, 13);
        reader.quoteChar(34);
        reader.slashStarComments(true);
        reader.slashSlashComments(true);
        return reader;
    }

    static int interpretType(String s)
    {
        if("unset".equals(s))
            return 120;
        if("boolean".equals(s))
            return 90;
        if("byte".equals(s))
            return 329;
        if("short".equals(s))
            return 585;
        if("int".equals(s))
            return 1097;
        if("long".equals(s))
            return 2121;
        if("float".equals(s))
            return 1094;
        if("double".equals(s))
            return 2118;
        return !"String".equals(s) ? -1 : 29516;
    }

    static void parse(Reader reader, Map map)
        throws ParseException, IOException
    {
        StreamTokenizer streamtokenizer = initTokenizer(reader);
        Pattern pattern = Pattern.compile("([a-zA-Z_$][0-9a-zA-Z_$]*\\.)*[a-zA-Z_$][0-9a-zA-Z_$]*");
        do
        {
            int i = streamtokenizer.nextToken();
            if(i == -1)
                return;
            if(i != -3)
                throw new ParseException(streamtokenizer, "type name");
            i = interpretType(streamtokenizer.sval);
            if(i == -1)
                throw new ParseException(streamtokenizer, "valid type name");
            streamtokenizer.sval = null;
            if(i == 120 && streamtokenizer.nextToken() != 40)
                throw new ParseException(streamtokenizer, "'('");
            if(streamtokenizer.nextToken() != -3)
                throw new ParseException(streamtokenizer, "property name");
            String s = streamtokenizer.sval;
            if(!pattern.matcher(s).matches())
                throw new ParseException(streamtokenizer, "valid property name");
            streamtokenizer.sval = null;
            if(i == 120)
            {
                if(streamtokenizer.nextToken() != 41)
                    throw new ParseException(streamtokenizer, "')'");
                map.remove(s);
            } else
            {
                if(streamtokenizer.nextToken() != 61)
                    throw new ParseException(streamtokenizer, "'='");
                Object obj = parseValue(streamtokenizer, i);
                reader = ((Reader) (map.remove(s)));
                if(reader != null && obj.getClass() != reader.getClass())
                    throw new ParseException(streamtokenizer, "(property previously declared as a different type)");
                map.put(s, obj);
            }
        } while(streamtokenizer.nextToken() == 59);
        throw new ParseException(streamtokenizer, "';'");
    }

    static Object parseValue(StreamTokenizer streamtokenizer, int i)
        throws IOException
    {
        int j = streamtokenizer.nextToken();
        if(i == 90)
        {
            if(j != -3)
                throw new ParseException(streamtokenizer, "boolean constant");
            if("true".equals(streamtokenizer.sval))
                return Boolean.TRUE;
            if("false".equals(streamtokenizer.sval))
                return Boolean.FALSE;
            else
                throw new ParseException(streamtokenizer, "boolean constant");
        }
        if((i & 0xff) == 73)
        {
            if(j != -3)
                throw new ParseException(streamtokenizer, "integer constant");
            long l;
            try
            {
                l = Long.decode(streamtokenizer.sval).longValue();
            }
            catch(NumberFormatException numberformatexception)
            {
                throw new ParseException(streamtokenizer, "integer constant");
            }
            i = i >> 8 & 0xff;
            switch(i)
            {
            case 3: // '\003'
            case 5: // '\005'
            case 6: // '\006'
            case 7: // '\007'
            default:
                throw new IllegalStateException((new StringBuilder()).append("Internal error; unexpected integer type width ").append(i).toString());

            case 1: // '\001'
                if(l < -128L || l > 127L)
                    throw new ParseException(streamtokenizer, "8-bit integer constant");
                else
                    return new Byte((byte)(int)l);

            case 2: // '\002'
                if(l < -32768L || l > 32767L)
                    throw new ParseException(streamtokenizer, "16-bit integer constant");
                else
                    return new Short((short)(int)l);

            case 4: // '\004'
                if(l < 0xffffffff80000000L || l > 0x7fffffffL)
                    throw new ParseException(streamtokenizer, "32-bit integer constant");
                else
                    return new Integer((int)l);

            case 8: // '\b'
                break;
            }
            if(l < 0x8000000000000000L || l > 0x7fffffffffffffffL)
                throw new ParseException(streamtokenizer, "64-bit integer constant");
            else
                return new Long(l);
        }
        if((i & 0xff) == 70)
        {
            if(j != -3)
                throw new ParseException(streamtokenizer, "float constant");
            double d;
            try
            {
                d = Double.parseDouble(streamtokenizer.sval);
            }
            catch(NumberFormatException numberformatexception1)
            {
                throw new ParseException(streamtokenizer, "float constant");
            }
            if((i >> 8 & 0xff) == 4)
            {
                double d1 = Math.abs(d);
                if(d1 != 0.0D && Double.isInfinite(d) ^ true && Double.isNaN(d) ^ true && (d1 < 1.4012984643248171E-045D || d1 > 3.4028234663852886E+038D))
                    throw new ParseException(streamtokenizer, "32-bit float constant");
                else
                    return new Float((float)d);
            } else
            {
                return new Double(d);
            }
        }
        if(i == 29516)
        {
            if(j == 34)
                return streamtokenizer.sval;
            if(j == -3 && "null".equals(streamtokenizer.sval))
                return NULL_STRING;
            else
                throw new ParseException(streamtokenizer, "double-quoted string or 'null'");
        } else
        {
            throw new IllegalStateException((new StringBuilder()).append("Internal error; unknown type ").append(i).toString());
        }
    }

    public Object get(Object obj)
    {
        obj = super.get(obj);
        if(obj == NULL_STRING)
            return null;
        else
            return obj;
    }

    public boolean getBoolean(String s)
    {
        return getBoolean(s, false);
    }

    public boolean getBoolean(String s, boolean flag)
    {
        Object obj = super.get(s);
        if(obj == null)
            return flag;
        if(obj instanceof Boolean)
            return ((Boolean)obj).booleanValue();
        else
            throw new TypeException(s, obj, "boolean");
    }

    public byte getByte(String s)
    {
        return getByte(s, (byte)0);
    }

    public byte getByte(String s, byte byte0)
    {
        Object obj = super.get(s);
        if(obj == null)
            return byte0;
        if(obj instanceof Byte)
            return ((Byte)obj).byteValue();
        else
            throw new TypeException(s, obj, "byte");
    }

    public double getDouble(String s)
    {
        return getDouble(s, 0.0D);
    }

    public double getDouble(String s, double d)
    {
        Object obj = super.get(s);
        if(obj == null)
            return d;
        if(obj instanceof Double)
            return ((Double)obj).doubleValue();
        else
            throw new TypeException(s, obj, "double");
    }

    public float getFloat(String s)
    {
        return getFloat(s, 0.0F);
    }

    public float getFloat(String s, float f)
    {
        Object obj = super.get(s);
        if(obj == null)
            return f;
        if(obj instanceof Float)
            return ((Float)obj).floatValue();
        else
            throw new TypeException(s, obj, "float");
    }

    public int getInt(String s)
    {
        return getInt(s, 0);
    }

    public int getInt(String s, int i)
    {
        Object obj = super.get(s);
        if(obj == null)
            return i;
        if(obj instanceof Integer)
            return ((Integer)obj).intValue();
        else
            throw new TypeException(s, obj, "int");
    }

    public long getLong(String s)
    {
        return getLong(s, 0L);
    }

    public long getLong(String s, long l)
    {
        Object obj = super.get(s);
        if(obj == null)
            return l;
        if(obj instanceof Long)
            return ((Long)obj).longValue();
        else
            throw new TypeException(s, obj, "long");
    }

    public short getShort(String s)
    {
        return getShort(s, (short)0);
    }

    public short getShort(String s, short word0)
    {
        Object obj = super.get(s);
        if(obj == null)
            return word0;
        if(obj instanceof Short)
            return ((Short)obj).shortValue();
        else
            throw new TypeException(s, obj, "short");
    }

    public String getString(String s)
    {
        return getString(s, "");
    }

    public String getString(String s, String s1)
    {
        Object obj = super.get(s);
        if(obj == null)
            return s1;
        if(obj == NULL_STRING)
            return null;
        if(obj instanceof String)
            return (String)obj;
        else
            throw new TypeException(s, obj, "string");
    }

    public int getStringInfo(String s)
    {
        s = ((String) (super.get(s)));
        if(s == null)
            return -1;
        if(s == NULL_STRING)
            return 0;
        return !(s instanceof String) ? -2 : 1;
    }

    public void load(Reader reader)
        throws IOException
    {
        parse(reader, this);
    }

    static final String NULL_STRING = new String("<TypedProperties:NULL_STRING>");
    public static final int STRING_NOT_SET = -1;
    public static final int STRING_NULL = 0;
    public static final int STRING_SET = 1;
    public static final int STRING_TYPE_MISMATCH = -2;
    static final int TYPE_BOOLEAN = 90;
    static final int TYPE_BYTE = 329;
    static final int TYPE_DOUBLE = 2118;
    static final int TYPE_ERROR = -1;
    static final int TYPE_FLOAT = 1094;
    static final int TYPE_INT = 1097;
    static final int TYPE_LONG = 2121;
    static final int TYPE_SHORT = 585;
    static final int TYPE_STRING = 29516;
    static final int TYPE_UNSET = 120;

}
