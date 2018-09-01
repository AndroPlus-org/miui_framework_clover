// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;

public class KeyValueMap extends HashMap
{

    public KeyValueMap()
    {
    }

    public static transient KeyValueMap fromKeyValues(Object aobj[])
    {
        KeyValueMap keyvaluemap = new KeyValueMap();
        keyvaluemap.setKeyValues(aobj);
        return keyvaluemap;
    }

    public float getFloat(String s)
    {
        s = ((String) (get(s)));
        if(s != null)
            s = (Float)s;
        else
            s = null;
        return s.floatValue();
    }

    public int getInt(String s)
    {
        s = ((String) (get(s)));
        if(s != null)
            s = (Integer)s;
        else
            s = null;
        return s.intValue();
    }

    public String getString(String s)
    {
        s = ((String) (get(s)));
        if(s != null)
            s = (String)s;
        else
            s = null;
        return s;
    }

    public transient void setKeyValues(Object aobj[])
    {
        if(aobj.length % 2 != 0)
            throw new RuntimeException("Key-Value arguments passed into setKeyValues must be an alternating list of keys and values!");
        for(int i = 0; i < aobj.length; i += 2)
        {
            if(!(aobj[i] instanceof String))
                throw new RuntimeException((new StringBuilder()).append("Key-value argument ").append(i).append(" must be a key of type ").append("String, but found an object of type ").append(aobj[i].getClass()).append("!").toString());
            put((String)aobj[i], aobj[i + 1]);
        }

    }

    public String toString()
    {
        StringWriter stringwriter = new StringWriter();
        Iterator iterator = entrySet().iterator();
        while(iterator.hasNext()) 
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            Object obj = entry.getValue();
            if(obj instanceof String)
                obj = (new StringBuilder()).append("\"").append(obj).append("\"").toString();
            else
                obj = obj.toString();
            stringwriter.write((new StringBuilder()).append((String)entry.getKey()).append(" = ").append(((String) (obj))).append(";\n").toString());
        }
        return stringwriter.toString();
    }
}
