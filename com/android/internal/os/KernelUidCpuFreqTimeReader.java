// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.SystemClock;
import android.util.Slog;
import android.util.SparseArray;
import java.io.*;

public class KernelUidCpuFreqTimeReader
{
    public static interface Callback
    {

        public abstract void onCpuFreqs(long al[]);

        public abstract void onUidCpuFreqTime(int i, long al[]);
    }


    public KernelUidCpuFreqTimeReader()
    {
        mLastUidCpuFreqTimeMs = new SparseArray();
    }

    private void readCpuFreqs(String s, Callback callback)
    {
        if(mCpuFreqs == null)
        {
            s = s.split(" ");
            mCpuFreqsCount = s.length - 1;
            mCpuFreqs = new long[mCpuFreqsCount];
            for(int i = 0; i < mCpuFreqsCount; i++)
                mCpuFreqs[i] = Long.parseLong(s[i + 1], 10);

        }
        if(callback != null)
            callback.onCpuFreqs(mCpuFreqs);
    }

    private void readTimesForUid(int i, String s, Callback callback)
    {
        long al[] = (long[])mLastUidCpuFreqTimeMs.get(i);
        long al1[] = al;
        if(al == null)
        {
            al1 = new long[mCpuFreqsCount];
            mLastUidCpuFreqTimeMs.put(i, al1);
        }
        String as[] = s.split(" ");
        int j = as.length;
        if(j != al1.length)
        {
            Slog.e("KernelUidCpuFreqTimeReader", (new StringBuilder()).append("No. of readings don't match cpu freqs, readings: ").append(j).append(" cpuFreqsCount: ").append(al1.length).toString());
            return;
        }
        al = new long[j];
        s = new long[j];
        boolean flag = false;
        int k = 0;
        while(k < j) 
        {
            long l = Long.parseLong(as[k], 10) * 10L;
            al[k] = l - al1[k];
            if(al[k] < 0L || l < 0L)
                return;
            s[k] = l;
            if(flag || al[k] > 0L)
                flag = true;
            else
                flag = false;
            k++;
        }
        if(flag)
        {
            System.arraycopy(s, 0, al1, 0, j);
            if(callback != null)
                callback.onUidCpuFreqTime(i, al);
        }
    }

    public void readDelta(Callback callback)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        obj = null;
        obj1 = null;
        if(!mProcFileAvailable && mReadErrorCounter >= 5)
            return;
        obj2 = null;
        obj3 = null;
        Object obj5;
        obj5 = JVM INSTR new #124 <Class BufferedReader>;
        FileReader filereader = JVM INSTR new #126 <Class FileReader>;
        filereader.FileReader("/proc/uid_time_in_state");
        ((BufferedReader) (obj5)).BufferedReader(filereader);
        mNowTimeMs = SystemClock.elapsedRealtime();
        readDelta(((BufferedReader) (obj5)), callback);
        mLastTimeReadMs = mNowTimeMs;
        mProcFileAvailable = true;
        callback = obj1;
        if(obj5 == null)
            break MISSING_BLOCK_LABEL_91;
        ((BufferedReader) (obj5)).close();
        callback = obj1;
_L3:
        if(callback == null) goto _L2; else goto _L1
_L1:
        try
        {
            throw callback;
        }
        // Misplaced declaration of an exception variable
        catch(Callback callback) { }
_L4:
        mReadErrorCounter = mReadErrorCounter + 1;
        Slog.e("KernelUidCpuFreqTimeReader", (new StringBuilder()).append("Failed to read /proc/uid_time_in_state: ").append(callback).toString());
_L2:
        return;
        callback;
          goto _L3
        obj5;
        callback = ((Callback) (obj3));
_L8:
        throw obj5;
        obj;
        obj3 = obj5;
        obj5 = obj;
_L7:
        obj = obj3;
        if(callback == null)
            break MISSING_BLOCK_LABEL_171;
        callback.close();
        obj = obj3;
_L5:
        if(obj == null)
            break MISSING_BLOCK_LABEL_213;
        try
        {
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch(Callback callback) { }
          goto _L4
        callback;
label0:
        {
            if(obj3 != null)
                break label0;
            obj = callback;
        }
          goto _L5
        obj = obj3;
        if(obj3 == callback) goto _L5; else goto _L6
_L6:
        ((Throwable) (obj3)).addSuppressed(callback);
        obj = obj3;
          goto _L5
        throw obj5;
        obj5;
        callback = obj2;
        obj3 = obj;
          goto _L7
        Object obj4;
        obj4;
        callback = ((Callback) (obj5));
        obj5 = obj4;
        obj4 = obj;
          goto _L7
        obj4;
        callback = ((Callback) (obj5));
        obj5 = obj4;
          goto _L8
    }

    public void readDelta(BufferedReader bufferedreader, Callback callback)
        throws IOException
    {
        String s = bufferedreader.readLine();
        if(s == null)
            return;
        readCpuFreqs(s, callback);
        do
        {
            String s1 = bufferedreader.readLine();
            if(s1 != null)
            {
                int i = s1.indexOf(' ');
                readTimesForUid(Integer.parseInt(s1.substring(0, i - 1), 10), s1.substring(i + 1, s1.length()), callback);
            } else
            {
                return;
            }
        } while(true);
    }

    public void removeUid(int i)
    {
        mLastUidCpuFreqTimeMs.delete(i);
    }

    public void removeUidsInRange(int i, int j)
    {
        if(j < i)
        {
            return;
        } else
        {
            mLastUidCpuFreqTimeMs.put(i, null);
            mLastUidCpuFreqTimeMs.put(j, null);
            i = mLastUidCpuFreqTimeMs.indexOfKey(i);
            j = mLastUidCpuFreqTimeMs.indexOfKey(j);
            mLastUidCpuFreqTimeMs.removeAtRange(i, (j - i) + 1);
            return;
        }
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "KernelUidCpuFreqTimeReader";
    private static final int TOTAL_READ_ERROR_COUNT = 5;
    private static final String UID_TIMES_PROC_FILE = "/proc/uid_time_in_state";
    private long mCpuFreqs[];
    private int mCpuFreqsCount;
    private long mLastTimeReadMs;
    private SparseArray mLastUidCpuFreqTimeMs;
    private long mNowTimeMs;
    private boolean mProcFileAvailable;
    private int mReadErrorCounter;
}
