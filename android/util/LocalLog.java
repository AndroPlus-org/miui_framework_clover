// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;

public final class LocalLog
{
    public static class ReadOnlyLocalLog
    {

        public void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
        {
            mLog.dump(filedescriptor, printwriter, as);
        }

        public void reverseDump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
        {
            mLog.reverseDump(filedescriptor, printwriter, as);
        }

        private final LocalLog mLog;

        ReadOnlyLocalLog(LocalLog locallog)
        {
            mLog = locallog;
        }
    }


    public LocalLog(int i)
    {
        mMaxLines = Math.max(0, i);
        mLog = new ArrayDeque(mMaxLines);
    }

    private void append(String s)
    {
        this;
        JVM INSTR monitorenter ;
        for(; mLog.size() >= mMaxLines; mLog.remove());
        break MISSING_BLOCK_LABEL_36;
        s;
        throw s;
        mLog.add(s);
        this;
        JVM INSTR monitorexit ;
    }

    public void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        this;
        JVM INSTR monitorenter ;
        for(filedescriptor = mLog.iterator(); filedescriptor.hasNext(); printwriter.println((String)filedescriptor.next()));
        break MISSING_BLOCK_LABEL_42;
        filedescriptor;
        throw filedescriptor;
        this;
        JVM INSTR monitorexit ;
    }

    public void log(String s)
    {
        if(mMaxLines <= 0)
        {
            return;
        } else
        {
            append(String.format("%s - %s", new Object[] {
                LocalDateTime.now(), s
            }));
            return;
        }
    }

    public ReadOnlyLocalLog readOnlyLocalLog()
    {
        return new ReadOnlyLocalLog(this);
    }

    public void reverseDump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        this;
        JVM INSTR monitorenter ;
        for(filedescriptor = mLog.descendingIterator(); filedescriptor.hasNext(); printwriter.println((String)filedescriptor.next()));
        break MISSING_BLOCK_LABEL_42;
        filedescriptor;
        throw filedescriptor;
        this;
        JVM INSTR monitorexit ;
    }

    private final Deque mLog;
    private final int mMaxLines;
}
