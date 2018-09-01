// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.SystemClock;
import android.util.*;
import java.io.*;

public class KernelUidCpuTimeReader
{
    public static interface Callback
    {

        public abstract void onUidCpuTime(int i, long l, long l1);
    }


    public KernelUidCpuTimeReader()
    {
        mLastUserTimeUs = new SparseLongArray();
        mLastSystemTimeUs = new SparseLongArray();
        mLastTimeReadUs = 0L;
    }

    private void removeUidsFromKernelModule(int i, int j)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        obj = null;
        obj1 = null;
        Slog.d("KernelUidCpuTimeReader", (new StringBuilder()).append("Removing uids ").append(i).append("-").append(j).toString());
        obj2 = null;
        obj3 = null;
        Object obj4;
        obj4 = JVM INSTR new #68  <Class FileWriter>;
        ((FileWriter) (obj4)).FileWriter("/proc/uid_cputime/remove_uid_range");
        StringBuilder stringbuilder = JVM INSTR new #44  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        ((FileWriter) (obj4)).write(stringbuilder.append(i).append("-").append(j).toString());
        ((FileWriter) (obj4)).flush();
        Throwable throwable;
        throwable = obj1;
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_113;
        ((FileWriter) (obj4)).close();
        throwable = obj1;
_L3:
        if(throwable == null) goto _L2; else goto _L1
_L1:
        try
        {
            throw throwable;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj4) { }
_L4:
        Slog.e("KernelUidCpuTimeReader", (new StringBuilder()).append("failed to remove uids ").append(i).append(" - ").append(j).append(" from uid_cputime module").toString(), ((Throwable) (obj4)));
_L2:
        return;
        throwable;
          goto _L3
        Object obj5;
        obj5;
        obj4 = obj3;
_L8:
        throw obj5;
        Exception exception;
        exception;
        obj = obj5;
        obj5 = exception;
_L7:
        exception = ((Exception) (obj));
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_207;
        ((FileWriter) (obj4)).close();
        exception = ((Exception) (obj));
_L5:
        if(exception == null)
            break MISSING_BLOCK_LABEL_254;
        try
        {
            throw exception;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj4) { }
          goto _L4
        obj4;
label0:
        {
            if(obj != null)
                break label0;
            exception = ((Exception) (obj4));
        }
          goto _L5
        exception = ((Exception) (obj));
        if(obj == obj4) goto _L5; else goto _L6
_L6:
        ((Throwable) (obj)).addSuppressed(((Throwable) (obj4)));
        exception = ((Exception) (obj));
          goto _L5
        throw obj5;
        obj5;
        obj4 = obj2;
          goto _L7
        obj5;
          goto _L7
        obj5;
          goto _L8
    }

    public void readDelta(Callback callback)
    {
        long l;
        Object obj;
        Object obj1;
        Object obj2;
        android.text.TextUtils.SimpleStringSplitter simplestringsplitter;
        l = SystemClock.elapsedRealtime() * 1000L;
        obj = null;
        obj1 = null;
        obj2 = null;
        simplestringsplitter = null;
        Object obj3;
        obj3 = JVM INSTR new #106 <Class BufferedReader>;
        FileReader filereader = JVM INSTR new #108 <Class FileReader>;
        filereader.FileReader("/proc/uid_cputime/show_uid_stat");
        ((BufferedReader) (obj3)).BufferedReader(filereader);
        simplestringsplitter = JVM INSTR new #114 <Class android.text.TextUtils$SimpleStringSplitter>;
        simplestringsplitter.android.text.TextUtils.SimpleStringSplitter(' ');
_L3:
        obj2 = ((BufferedReader) (obj3)).readLine();
        if(obj2 == null) goto _L2; else goto _L1
_L1:
        int i;
        long l1;
        long l2;
        simplestringsplitter.setString(((String) (obj2)));
        obj2 = simplestringsplitter.next();
        i = Integer.parseInt(((String) (obj2)).substring(0, ((String) (obj2)).length() - 1), 10);
        l1 = Long.parseLong(simplestringsplitter.next(), 10);
        l2 = Long.parseLong(simplestringsplitter.next(), 10);
        if(callback == null)
            break MISSING_BLOCK_LABEL_444;
        if(mLastTimeReadUs == 0L)
            break MISSING_BLOCK_LABEL_444;
        long l3;
        long l4;
        l3 = l1;
        l4 = l2;
        int j = mLastUserTimeUs.indexOfKey(i);
        if(j < 0)
            break MISSING_BLOCK_LABEL_418;
        long l5;
        long l6;
        long l7;
        l5 = l1 - mLastUserTimeUs.valueAt(j);
        l6 = l2 - mLastSystemTimeUs.valueAt(j);
        l7 = mLastTimeReadUs;
        if(l5 >= 0L)
        {
            l3 = l5;
            l4 = l6;
            if(l6 >= 0L)
                break MISSING_BLOCK_LABEL_418;
        }
        obj2 = JVM INSTR new #44  <Class StringBuilder>;
        ((StringBuilder) (obj2)).StringBuilder("Malformed cpu data for UID=");
        ((StringBuilder) (obj2)).append(i).append("!\n");
        ((StringBuilder) (obj2)).append("Time between reads: ");
        TimeUtils.formatDuration((l - l7) / 1000L, ((StringBuilder) (obj2)));
        ((StringBuilder) (obj2)).append("\n");
        ((StringBuilder) (obj2)).append("Previous times: u=");
        TimeUtils.formatDuration(mLastUserTimeUs.valueAt(j) / 1000L, ((StringBuilder) (obj2)));
        ((StringBuilder) (obj2)).append(" s=");
        TimeUtils.formatDuration(mLastSystemTimeUs.valueAt(j) / 1000L, ((StringBuilder) (obj2)));
        ((StringBuilder) (obj2)).append("\nCurrent times: u=");
        TimeUtils.formatDuration(l1 / 1000L, ((StringBuilder) (obj2)));
        ((StringBuilder) (obj2)).append(" s=");
        TimeUtils.formatDuration(l2 / 1000L, ((StringBuilder) (obj2)));
        ((StringBuilder) (obj2)).append("\nDelta: u=");
        TimeUtils.formatDuration(l5 / 1000L, ((StringBuilder) (obj2)));
        ((StringBuilder) (obj2)).append(" s=");
        TimeUtils.formatDuration(l6 / 1000L, ((StringBuilder) (obj2)));
        Slog.e("KernelUidCpuTimeReader", ((StringBuilder) (obj2)).toString());
        l3 = 0L;
        l4 = 0L;
        if(l3 == 0L && l4 == 0L)
            break MISSING_BLOCK_LABEL_444;
        callback.onUidCpuTime(i, l3, l4);
        mLastUserTimeUs.put(i, l1);
        mLastSystemTimeUs.put(i, l2);
          goto _L3
        obj;
        callback = ((Callback) (obj3));
        obj3 = obj;
_L14:
        throw obj3;
        obj1;
        obj = callback;
        callback = ((Callback) (obj1));
_L12:
        obj1 = obj3;
        if(obj == null)
            break MISSING_BLOCK_LABEL_507;
        ((BufferedReader) (obj)).close();
        obj1 = obj3;
_L10:
        if(obj1 == null)
            break; /* Loop/switch isn't completed */
        try
        {
            throw obj1;
        }
        // Misplaced declaration of an exception variable
        catch(Callback callback) { }
_L8:
        Slog.e("KernelUidCpuTimeReader", (new StringBuilder()).append("Failed to read uid_cputime: ").append(callback.getMessage()).toString());
_L7:
        mLastTimeReadUs = l;
        return;
_L2:
        callback = ((Callback) (obj));
        if(obj3 == null) goto _L5; else goto _L4
_L4:
        ((BufferedReader) (obj3)).close();
        callback = ((Callback) (obj));
_L5:
        if(callback == null) goto _L7; else goto _L6
_L6:
        try
        {
            throw callback;
        }
        // Misplaced declaration of an exception variable
        catch(Callback callback) { }
          goto _L8
        callback;
          goto _L5
        obj;
        if(obj3 == null)
        {
            obj1 = obj;
            continue; /* Loop/switch isn't completed */
        }
        obj1 = obj3;
        if(obj3 == obj)
            continue; /* Loop/switch isn't completed */
        ((Throwable) (obj3)).addSuppressed(((Throwable) (obj)));
        obj1 = obj3;
        if(true) goto _L10; else goto _L9
_L9:
        throw callback;
        callback;
        obj = obj2;
        obj3 = obj1;
        continue; /* Loop/switch isn't completed */
        callback;
        obj = obj3;
        obj3 = obj1;
        if(true) goto _L12; else goto _L11
_L11:
        obj3;
        callback = simplestringsplitter;
        if(true) goto _L14; else goto _L13
_L13:
    }

    public void removeUid(int i)
    {
        int j = mLastSystemTimeUs.indexOfKey(i);
        if(j >= 0)
        {
            mLastSystemTimeUs.removeAt(j);
            mLastUserTimeUs.removeAt(j);
        }
        removeUidsFromKernelModule(i, i);
    }

    public void removeUidsInRange(int i, int j)
    {
        if(j < i)
        {
            return;
        } else
        {
            mLastSystemTimeUs.put(i, 0L);
            mLastUserTimeUs.put(i, 0L);
            mLastSystemTimeUs.put(j, 0L);
            mLastUserTimeUs.put(j, 0L);
            int k = mLastSystemTimeUs.indexOfKey(i);
            int l = mLastSystemTimeUs.indexOfKey(j);
            mLastSystemTimeUs.removeAtRange(k, (l - k) + 1);
            mLastUserTimeUs.removeAtRange(k, (l - k) + 1);
            removeUidsFromKernelModule(i, j);
            return;
        }
    }

    private static final String TAG = "KernelUidCpuTimeReader";
    private static final String sProcFile = "/proc/uid_cputime/show_uid_stat";
    private static final String sRemoveUidProcFile = "/proc/uid_cputime/remove_uid_range";
    private SparseLongArray mLastSystemTimeUs;
    private long mLastTimeReadUs;
    private SparseLongArray mLastUserTimeUs;
}
