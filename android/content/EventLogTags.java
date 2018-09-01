// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.util.EventLog;

public class EventLogTags
{

    private EventLogTags()
    {
    }

    public static void writeBinderSample(String s, int i, int j, String s1, int k)
    {
        EventLog.writeEvent(52004, new Object[] {
            s, Integer.valueOf(i), Integer.valueOf(j), s1, Integer.valueOf(k)
        });
    }

    public static void writeContentQuerySample(String s, String s1, String s2, String s3, int i, String s4, int j)
    {
        EventLog.writeEvent(52002, new Object[] {
            s, s1, s2, s3, Integer.valueOf(i), s4, Integer.valueOf(j)
        });
    }

    public static void writeContentUpdateSample(String s, String s1, String s2, int i, String s3, int j)
    {
        EventLog.writeEvent(52003, new Object[] {
            s, s1, s2, Integer.valueOf(i), s3, Integer.valueOf(j)
        });
    }

    public static final int BINDER_SAMPLE = 52004;
    public static final int CONTENT_QUERY_SAMPLE = 52002;
    public static final int CONTENT_UPDATE_SAMPLE = 52003;
}
