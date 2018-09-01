// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.StrictMode;
import android.system.OsConstants;
import android.util.Slog;
import java.io.*;
import java.util.Arrays;
import libcore.io.Libcore;
import libcore.io.Os;

public class KernelCpuSpeedReader
{

    public KernelCpuSpeedReader(int i, int j)
    {
        mProcFile = String.format("/sys/devices/system/cpu/cpu%d/cpufreq/stats/time_in_state", new Object[] {
            Integer.valueOf(i)
        });
        mLastSpeedTimesMs = new long[j];
        mDeltaSpeedTimesMs = new long[j];
        mJiffyMillis = 1000L / Libcore.os.sysconf(OsConstants._SC_CLK_TCK);
    }

    public long[] readDelta()
    {
        android.os.StrictMode.ThreadPolicy threadpolicy;
        Object obj;
        Object obj1;
        Object obj2;
        String s;
        threadpolicy = StrictMode.allowThreadDiskReads();
        obj = null;
        obj1 = null;
        obj2 = null;
        s = null;
        Object obj3;
        obj3 = JVM INSTR new #76  <Class BufferedReader>;
        FileReader filereader = JVM INSTR new #78  <Class FileReader>;
        filereader.FileReader(mProcFile);
        ((BufferedReader) (obj3)).BufferedReader(filereader);
        Object obj4;
        obj4 = JVM INSTR new #86  <Class android.text.TextUtils$SimpleStringSplitter>;
        ((android.text.TextUtils.SimpleStringSplitter) (obj4)).android.text.TextUtils.SimpleStringSplitter(' ');
        int i = 0;
_L20:
        if(i >= mLastSpeedTimesMs.length) goto _L2; else goto _L1
_L1:
        s = ((BufferedReader) (obj3)).readLine();
        if(s == null) goto _L2; else goto _L3
_L3:
        long l;
        ((android.text.TextUtils.SimpleStringSplitter) (obj4)).setString(s);
        ((android.text.TextUtils.SimpleStringSplitter) (obj4)).next();
        l = Long.parseLong(((android.text.TextUtils.SimpleStringSplitter) (obj4)).next()) * mJiffyMillis;
        if(l >= mLastSpeedTimesMs[i])
            break MISSING_BLOCK_LABEL_142;
        mDeltaSpeedTimesMs[i] = l;
_L4:
        mLastSpeedTimesMs[i] = l;
        i++;
        continue; /* Loop/switch isn't completed */
        mDeltaSpeedTimesMs[i] = l - mLastSpeedTimesMs[i];
          goto _L4
        obj4;
_L18:
        throw obj4;
        obj;
        obj1 = obj4;
        obj4 = obj;
_L16:
        obj = obj1;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_188;
        ((BufferedReader) (obj3)).close();
        obj = obj1;
_L11:
        if(obj == null) goto _L6; else goto _L5
_L5:
        try
        {
            throw obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj3) { }
_L9:
        obj4 = JVM INSTR new #110 <Class StringBuilder>;
        ((StringBuilder) (obj4)).StringBuilder();
        Slog.e("KernelCpuSpeedReader", ((StringBuilder) (obj4)).append("Failed to read cpu-freq: ").append(((IOException) (obj3)).getMessage()).toString());
        Arrays.fill(mDeltaSpeedTimesMs, 0L);
        StrictMode.setThreadPolicy(threadpolicy);
_L13:
        return mDeltaSpeedTimesMs;
_L2:
        obj4 = obj;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_263;
        ((BufferedReader) (obj3)).close();
        obj4 = obj;
_L10:
        if(obj4 == null) goto _L8; else goto _L7
_L7:
        try
        {
            throw obj4;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj3) { }
          goto _L9
        obj4;
          goto _L10
        obj3;
label0:
        {
            if(obj1 != null)
                break label0;
            obj = obj3;
        }
          goto _L11
        obj = obj1;
        if(obj1 == obj3) goto _L11; else goto _L12
_L12:
        ((Throwable) (obj1)).addSuppressed(((Throwable) (obj3)));
        obj = obj1;
          goto _L11
        obj3;
_L14:
        StrictMode.setThreadPolicy(threadpolicy);
        throw obj3;
_L6:
        throw obj4;
_L8:
        StrictMode.setThreadPolicy(threadpolicy);
          goto _L13
        obj3;
          goto _L14
        obj4;
        obj3 = obj2;
        continue; /* Loop/switch isn't completed */
        obj4;
        if(true) goto _L16; else goto _L15
_L15:
        obj4;
        obj3 = s;
        if(true) goto _L18; else goto _L17
_L17:
        if(true) goto _L20; else goto _L19
_L19:
    }

    private static final String TAG = "KernelCpuSpeedReader";
    private final long mDeltaSpeedTimesMs[];
    private final long mJiffyMillis;
    private final long mLastSpeedTimesMs[];
    private final String mProcFile;
}
