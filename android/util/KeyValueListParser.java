// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import java.util.Iterator;

// Referenced classes of package android.util:
//            ArrayMap

public class KeyValueListParser
{

    public KeyValueListParser(char c)
    {
        mSplitter = new android.text.TextUtils.SimpleStringSplitter(c);
    }

    public boolean getBoolean(String s, boolean flag)
    {
        s = (String)mValues.get(s);
        if(s == null)
            break MISSING_BLOCK_LABEL_24;
        boolean flag1 = Boolean.parseBoolean(s);
        return flag1;
        s;
        return flag;
    }

    public float getFloat(String s, float f)
    {
        s = (String)mValues.get(s);
        if(s == null)
            break MISSING_BLOCK_LABEL_24;
        float f1 = Float.parseFloat(s);
        return f1;
        s;
        return f;
    }

    public int getInt(String s, int i)
    {
        s = (String)mValues.get(s);
        if(s == null)
            break MISSING_BLOCK_LABEL_24;
        int j = Integer.parseInt(s);
        return j;
        s;
        return i;
    }

    public long getLong(String s, long l)
    {
        s = (String)mValues.get(s);
        if(s == null)
            break MISSING_BLOCK_LABEL_26;
        long l1 = Long.parseLong(s);
        return l1;
        s;
        return l;
    }

    public String getString(String s, String s1)
    {
        s = (String)mValues.get(s);
        if(s != null)
            return s;
        else
            return s1;
    }

    public void setString(String s)
        throws IllegalArgumentException
    {
        mValues.clear();
        if(s != null)
        {
            mSplitter.setString(s);
            String s1;
            int i;
            for(Iterator iterator = mSplitter.iterator(); iterator.hasNext(); mValues.put(s1.substring(0, i).trim(), s1.substring(i + 1).trim()))
            {
                s1 = (String)iterator.next();
                i = s1.indexOf('=');
                if(i < 0)
                {
                    mValues.clear();
                    throw new IllegalArgumentException((new StringBuilder()).append("'").append(s1).append("' in '").append(s).append("' is not a valid key-value pair").toString());
                }
            }

        }
    }

    private final android.text.TextUtils.StringSplitter mSplitter;
    private final ArrayMap mValues = new ArrayMap();
}
