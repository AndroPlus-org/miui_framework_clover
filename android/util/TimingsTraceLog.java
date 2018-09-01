// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.os.*;
import java.util.ArrayDeque;
import java.util.Deque;

// Referenced classes of package android.util:
//            Slog, Pair

public class TimingsTraceLog
{

    public TimingsTraceLog(String s, long l)
    {
        ArrayDeque arraydeque;
        if(DEBUG_BOOT_TIME)
            arraydeque = new ArrayDeque();
        else
            arraydeque = null;
        mStartTimes = arraydeque;
        mTag = s;
        mTraceTag = l;
    }

    public void logDuration(String s, long l)
    {
        Slog.d(mTag, (new StringBuilder()).append(s).append(" took to complete: ").append(l).append("ms").toString());
    }

    public void traceBegin(String s)
    {
        Trace.traceBegin(mTraceTag, s);
        if(DEBUG_BOOT_TIME)
            mStartTimes.push(Pair.create(s, Long.valueOf(SystemClock.elapsedRealtime())));
    }

    public void traceEnd()
    {
        Trace.traceEnd(mTraceTag);
        if(!DEBUG_BOOT_TIME)
            return;
        if(mStartTimes.peek() == null)
        {
            Slog.w(mTag, "traceEnd called more times than traceBegin");
            return;
        } else
        {
            Pair pair = (Pair)mStartTimes.pop();
            logDuration((String)pair.first, SystemClock.elapsedRealtime() - ((Long)pair.second).longValue());
            return;
        }
    }

    private static final boolean DEBUG_BOOT_TIME;
    private final Deque mStartTimes;
    private final String mTag;
    private long mTraceTag;

    static 
    {
        DEBUG_BOOT_TIME = Build.IS_USER ^ true;
    }
}
