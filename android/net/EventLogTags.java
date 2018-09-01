// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.util.EventLog;

public class EventLogTags
{

    private EventLogTags()
    {
    }

    public static void writeNtpFailure(String s, String s1)
    {
        EventLog.writeEvent(50081, new Object[] {
            s, s1
        });
    }

    public static void writeNtpSuccess(String s, long l, long l1)
    {
        EventLog.writeEvent(50080, new Object[] {
            s, Long.valueOf(l), Long.valueOf(l1)
        });
    }

    public static final int NTP_FAILURE = 50081;
    public static final int NTP_SUCCESS = 50080;
}
