// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.StrictMode;
import android.os.SystemClock;
import android.util.LongSparseLongArray;
import android.util.Slog;
import java.io.*;

public class KernelMemoryBandwidthStats
{

    public KernelMemoryBandwidthStats()
    {
        mStatsDoNotExist = false;
    }

    public LongSparseLongArray getBandwidthEntries()
    {
        return mBandwidthEntries;
    }

    public void parseStats(BufferedReader bufferedreader)
        throws IOException
    {
        android.text.TextUtils.SimpleStringSplitter simplestringsplitter = new android.text.TextUtils.SimpleStringSplitter(' ');
        mBandwidthEntries.clear();
        do
        {
            String s = bufferedreader.readLine();
            if(s != null)
            {
                simplestringsplitter.setString(s);
                simplestringsplitter.next();
                int i = 0;
                do
                {
                    int j = mBandwidthEntries.indexOfKey(i);
                    if(j >= 0)
                        mBandwidthEntries.put(i, mBandwidthEntries.valueAt(j) + Long.parseLong(simplestringsplitter.next()) / 0xf4240L);
                    else
                        mBandwidthEntries.put(i, Long.parseLong(simplestringsplitter.next()) / 0xf4240L);
                    i++;
                } while(simplestringsplitter.hasNext());
            } else
            {
                return;
            }
        } while(true);
    }

    public void updateStats()
    {
        Object obj;
        Object obj1;
        long l;
        android.os.StrictMode.ThreadPolicy threadpolicy;
        Object obj2;
        Object obj4;
        obj = null;
        obj1 = null;
        if(mStatsDoNotExist)
            return;
        l = SystemClock.uptimeMillis();
        threadpolicy = StrictMode.allowThreadDiskReads();
        obj2 = null;
        obj4 = null;
        Object obj5;
        obj5 = JVM INSTR new #45  <Class BufferedReader>;
        FileReader filereader = JVM INSTR new #100 <Class FileReader>;
        filereader.FileReader("/sys/kernel/memory_state_time/show_stat");
        ((BufferedReader) (obj5)).BufferedReader(filereader);
        parseStats(((BufferedReader) (obj5)));
        obj4 = obj1;
        if(obj5 == null)
            break MISSING_BLOCK_LABEL_73;
        ((BufferedReader) (obj5)).close();
        obj4 = obj1;
_L3:
        if(obj4 == null) goto _L2; else goto _L1
_L1:
        throw obj4;
        obj5;
_L6:
        Slog.w("KernelMemoryBandwidthStats", "No kernel memory bandwidth stats available");
        mBandwidthEntries.clear();
        mStatsDoNotExist = true;
        StrictMode.setThreadPolicy(threadpolicy);
_L9:
        l = SystemClock.uptimeMillis() - l;
        if(l > 100L)
            Slog.w("KernelMemoryBandwidthStats", (new StringBuilder()).append("Reading memory bandwidth file took ").append(l).append("ms").toString());
        return;
        obj4;
          goto _L3
        obj5;
_L13:
        throw obj5;
        obj;
        obj2 = obj5;
        obj5 = obj;
_L12:
        obj = obj2;
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_187;
        ((BufferedReader) (obj4)).close();
        obj = obj2;
_L7:
        if(obj == null) goto _L5; else goto _L4
_L4:
        throw obj;
        obj5;
          goto _L6
        obj4;
label0:
        {
            if(obj2 != null)
                break label0;
            obj = obj4;
        }
          goto _L7
        obj = obj2;
        if(obj2 == obj4) goto _L7; else goto _L8
_L8:
        ((Throwable) (obj2)).addSuppressed(((Throwable) (obj4)));
        obj = obj2;
          goto _L7
        obj5;
_L11:
        obj4 = JVM INSTR new #126 <Class StringBuilder>;
        ((StringBuilder) (obj4)).StringBuilder();
        Slog.e("KernelMemoryBandwidthStats", ((StringBuilder) (obj4)).append("Failed to read memory bandwidth: ").append(((IOException) (obj5)).getMessage()).toString());
        mBandwidthEntries.clear();
        StrictMode.setThreadPolicy(threadpolicy);
          goto _L9
_L5:
        throw obj5;
        obj5;
_L10:
        StrictMode.setThreadPolicy(threadpolicy);
        throw obj5;
_L2:
        StrictMode.setThreadPolicy(threadpolicy);
          goto _L9
        obj5;
          goto _L10
        obj5;
          goto _L11
        obj5;
        obj4 = obj2;
        obj2 = obj;
          goto _L12
        Object obj3;
        obj3;
        obj4 = obj5;
        obj5 = obj3;
        obj3 = obj;
          goto _L12
        obj3;
        obj4 = obj5;
        obj5 = obj3;
          goto _L13
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "KernelMemoryBandwidthStats";
    private static final String mSysfsFile = "/sys/kernel/memory_state_time/show_stat";
    protected final LongSparseLongArray mBandwidthEntries = new LongSparseLongArray();
    private boolean mStatsDoNotExist;
}
