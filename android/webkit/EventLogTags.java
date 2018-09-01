// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.util.EventLog;

public class EventLogTags
{

    private EventLogTags()
    {
    }

    public static void writeBrowserDoubleTapDuration(int i, long l)
    {
        EventLog.writeEvent(0x111d6, new Object[] {
            Integer.valueOf(i), Long.valueOf(l)
        });
    }

    public static void writeBrowserSnapCenter()
    {
        EventLog.writeEvent(0x11206, new Object[0]);
    }

    public static void writeBrowserZoomLevelChange(int i, int j, long l)
    {
        EventLog.writeEvent(0x111d5, new Object[] {
            Integer.valueOf(i), Integer.valueOf(j), Long.valueOf(l)
        });
    }

    public static void writeExpDetAttemptToCallObjectGetclass(String s)
    {
        EventLog.writeEvent(0x11207, s);
    }

    public static final int BROWSER_DOUBLE_TAP_DURATION = 0x111d6;
    public static final int BROWSER_SNAP_CENTER = 0x11206;
    public static final int BROWSER_ZOOM_LEVEL_CHANGE = 0x111d5;
    public static final int EXP_DET_ATTEMPT_TO_CALL_OBJECT_GETCLASS = 0x11207;
}
