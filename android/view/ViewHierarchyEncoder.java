// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ViewHierarchyEncoder
{

    public ViewHierarchyEncoder(ByteArrayOutputStream bytearrayoutputstream)
    {
        mPropertyId = (short)1;
        mCharset = Charset.forName("utf-8");
        mStream = new DataOutputStream(bytearrayoutputstream);
    }

    private short createPropertyIndex(String s)
    {
        Short short1 = (Short)mPropertyNames.get(s);
        Short short2 = short1;
        if(short1 == null)
        {
            int i = mPropertyId;
            mPropertyId = (short)(i + 1);
            short2 = Short.valueOf((short)i);
            mPropertyNames.put(s, short2);
        }
        return short2.shortValue();
    }

    private void endPropertyMap()
    {
        writeShort((short)0);
    }

    private void startPropertyMap()
    {
        mStream.write(77);
_L2:
        return;
        IOException ioexception;
        ioexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void writeBoolean(boolean flag)
    {
        DataOutputStream dataoutputstream;
        mStream.write(90);
        dataoutputstream = mStream;
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        dataoutputstream.write(i);
_L2:
        return;
        IOException ioexception;
        ioexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void writeFloat(float f)
    {
        mStream.write(70);
        mStream.writeFloat(f);
_L2:
        return;
        IOException ioexception;
        ioexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void writeInt(int i)
    {
        mStream.write(73);
        mStream.writeInt(i);
_L2:
        return;
        IOException ioexception;
        ioexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void writeShort(short word0)
    {
        mStream.write(83);
        mStream.writeShort(word0);
_L2:
        return;
        IOException ioexception;
        ioexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void writeString(String s)
    {
        String s1;
        s1 = s;
        if(s == null)
            s1 = "";
        mStream.write(82);
        s = s1.getBytes(mCharset);
        short word0 = (short)Math.min(s.length, 32767);
        mStream.writeShort(word0);
        mStream.write(s, 0, word0);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void addProperty(String s, float f)
    {
        writeShort(createPropertyIndex(s));
        writeFloat(f);
    }

    public void addProperty(String s, int i)
    {
        writeShort(createPropertyIndex(s));
        writeInt(i);
    }

    public void addProperty(String s, String s1)
    {
        writeShort(createPropertyIndex(s));
        writeString(s1);
    }

    public void addProperty(String s, short word0)
    {
        writeShort(createPropertyIndex(s));
        writeShort(word0);
    }

    public void addProperty(String s, boolean flag)
    {
        writeShort(createPropertyIndex(s));
        writeBoolean(flag);
    }

    public void addPropertyKey(String s)
    {
        writeShort(createPropertyIndex(s));
    }

    public void beginObject(Object obj)
    {
        startPropertyMap();
        addProperty("meta:__name__", obj.getClass().getName());
        addProperty("meta:__hash__", obj.hashCode());
    }

    public void endObject()
    {
        endPropertyMap();
    }

    public void endStream()
    {
        startPropertyMap();
        addProperty("__name__", "propertyIndex");
        java.util.Map.Entry entry;
        for(Iterator iterator = mPropertyNames.entrySet().iterator(); iterator.hasNext(); writeString((String)entry.getKey()))
        {
            entry = (java.util.Map.Entry)iterator.next();
            writeShort(((Short)entry.getValue()).shortValue());
        }

        endPropertyMap();
    }

    private static final byte SIG_BOOLEAN = 90;
    private static final byte SIG_BYTE = 66;
    private static final byte SIG_DOUBLE = 68;
    private static final short SIG_END_MAP = 0;
    private static final byte SIG_FLOAT = 70;
    private static final byte SIG_INT = 73;
    private static final byte SIG_LONG = 74;
    private static final byte SIG_MAP = 77;
    private static final byte SIG_SHORT = 83;
    private static final byte SIG_STRING = 82;
    private Charset mCharset;
    private short mPropertyId;
    private final Map mPropertyNames = new HashMap(200);
    private final DataOutputStream mStream;
}
