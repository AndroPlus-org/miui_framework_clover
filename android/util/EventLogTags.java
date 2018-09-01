// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import java.io.BufferedReader;
import java.io.IOException;

public class EventLogTags
{
    public static class Description
    {

        public final String mName;
        public final int mTag;

        Description(int i, String s)
        {
            mTag = i;
            mName = s;
        }
    }


    public EventLogTags()
        throws IOException
    {
    }

    public EventLogTags(BufferedReader bufferedreader)
        throws IOException
    {
    }

    public Description get(int i)
    {
        return null;
    }

    public Description get(String s)
    {
        return null;
    }
}
