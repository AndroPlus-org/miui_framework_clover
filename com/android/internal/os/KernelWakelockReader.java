// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.Process;
import android.os.SystemClock;
import android.util.Slog;
import java.io.*;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.android.internal.os:
//            KernelWakelockStats

public class KernelWakelockReader
{

    public KernelWakelockReader()
    {
    }

    public KernelWakelockStats parseProcWakelocks(byte abyte0[], int i, boolean flag, KernelWakelockStats kernelwakelockstats)
    {
        int l;
        int j;
        for(j = 0; j < i && abyte0[j] != 10 && abyte0[j] != 0; j++);
        l = j + 1;
        int k = l;
        this;
        JVM INSTR monitorenter ;
        sKernelWakelockUpdateVersion++;
_L15:
        if(l < i)
        {
            for(l = k; l < i && abyte0[l] != 10 && abyte0[l] != 0; l++);
            if(l <= i - 1)
                break MISSING_BLOCK_LABEL_149;
        }
        abyte0 = kernelwakelockstats.values().iterator();
        do
        {
            if(!abyte0.hasNext())
                break;
            if(((KernelWakelockStats.Entry)abyte0.next()).mVersion != sKernelWakelockUpdateVersion)
                abyte0.remove();
        } while(true);
        break MISSING_BLOCK_LABEL_484;
        abyte0;
        throw abyte0;
        String as[];
        Object obj;
        as = mProcWakelocksName;
        obj = mProcWakelocksData;
        for(int i1 = k; i1 < l; i1++)
            if((abyte0[i1] & 0x80) != 0)
                abyte0[i1] = (byte)63;

        if(!flag) goto _L2; else goto _L1
_L1:
        Object obj1 = WAKEUP_SOURCES_FORMAT;
_L10:
        boolean flag1 = Process.parseProcLine(abyte0, k, l, ((int []) (obj1)), as, ((long []) (obj)), null);
        int j1;
        obj1 = as[0];
        j1 = (int)obj[1];
        if(!flag) goto _L4; else goto _L3
_L3:
        long l1 = obj[2] * 1000L;
_L11:
        if(!flag1) goto _L6; else goto _L5
_L5:
        if(((String) (obj1)).length() <= 0) goto _L6; else goto _L7
_L7:
        if(kernelwakelockstats.containsKey(obj1)) goto _L9; else goto _L8
_L8:
        obj = JVM INSTR new #70  <Class KernelWakelockStats$Entry>;
        ((KernelWakelockStats.Entry) (obj)).KernelWakelockStats.Entry(j1, l1, sKernelWakelockUpdateVersion);
        kernelwakelockstats.put(obj1, obj);
_L12:
        k = l + 1;
        continue; /* Loop/switch isn't completed */
_L2:
        obj1 = PROC_WAKELOCKS_FORMAT;
          goto _L10
_L4:
        l1 = (obj[2] + 500L) / 1000L;
          goto _L11
_L9:
label0:
        {
            obj1 = (KernelWakelockStats.Entry)kernelwakelockstats.get(obj1);
            if(((KernelWakelockStats.Entry) (obj1)).mVersion != sKernelWakelockUpdateVersion)
                break label0;
            obj1.mCount = ((KernelWakelockStats.Entry) (obj1)).mCount + j1;
            obj1.mTotalTime = ((KernelWakelockStats.Entry) (obj1)).mTotalTime + l1;
        }
          goto _L12
        obj1.mCount = j1;
        obj1.mTotalTime = l1;
        obj1.mVersion = sKernelWakelockUpdateVersion;
          goto _L12
_L6:
        if(flag1) goto _L12; else goto _L13
_L13:
        Object obj2 = JVM INSTR new #114 <Class StringBuilder>;
        ((StringBuilder) (obj2)).StringBuilder();
        StringBuilder stringbuilder = ((StringBuilder) (obj2)).append("Failed to parse proc line: ");
        obj2 = JVM INSTR new #38  <Class String>;
        ((String) (obj2)).String(abyte0, k, l - k);
        Slog.wtf("KernelWakelockReader", stringbuilder.append(((String) (obj2))).toString());
          goto _L12
        Exception exception;
        exception;
        Slog.wtf("KernelWakelockReader", "Failed to parse proc line!");
          goto _L12
        kernelwakelockstats.kernelWakelockVersion = sKernelWakelockUpdateVersion;
        this;
        JVM INSTR monitorexit ;
        return kernelwakelockstats;
        if(true) goto _L15; else goto _L14
_L14:
    }

    public final KernelWakelockStats readKernelWakelockStats(KernelWakelockStats kernelwakelockstats)
    {
        byte abyte0[];
        long l;
        abyte0 = new byte[32768];
        l = SystemClock.uptimeMillis();
        FileInputStream fileinputstream;
        fileinputstream = JVM INSTR new #154 <Class FileInputStream>;
        fileinputstream.FileInputStream("/proc/wakelocks");
        boolean flag = false;
_L3:
        int k;
        FileNotFoundException filenotfoundexception;
        int i;
        int j;
        try
        {
            i = fileinputstream.read(abyte0);
            fileinputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch(KernelWakelockStats kernelwakelockstats)
        {
            Slog.wtf("KernelWakelockReader", "failed to read kernel wakelocks", kernelwakelockstats);
            return null;
        }
        l = SystemClock.uptimeMillis() - l;
        if(l > 100L)
            Slog.w("KernelWakelockReader", (new StringBuilder()).append("Reading wakelock stats took ").append(l).append("ms").toString());
        j = i;
        if(i <= 0) goto _L2; else goto _L1
_L1:
        if(i >= abyte0.length)
            Slog.wtf("KernelWakelockReader", (new StringBuilder()).append("Kernel wake locks exceeded buffer size ").append(abyte0.length).toString());
        k = 0;
_L4:
        j = i;
        if(k < i)
        {
            if(abyte0[k] != 0)
                break MISSING_BLOCK_LABEL_201;
            j = k;
        }
_L2:
        return parseProcWakelocks(abyte0, j, flag, kernelwakelockstats);
        filenotfoundexception;
        filenotfoundexception = new FileInputStream("/d/wakeup_sources");
        flag = true;
          goto _L3
        kernelwakelockstats;
        Slog.wtf("KernelWakelockReader", "neither /proc/wakelocks nor /d/wakeup_sources exists");
        return null;
        k++;
          goto _L4
    }

    private static final int PROC_WAKELOCKS_FORMAT[] = {
        5129, 8201, 9, 9, 9, 8201
    };
    private static final String TAG = "KernelWakelockReader";
    private static final int WAKEUP_SOURCES_FORMAT[] = {
        4105, 8457, 265, 265, 265, 265, 8457
    };
    private static int sKernelWakelockUpdateVersion = 0;
    private static final String sWakelockFile = "/proc/wakelocks";
    private static final String sWakeupSourceFile = "/d/wakeup_sources";
    private final long mProcWakelocksData[] = new long[3];
    private final String mProcWakelocksName[] = new String[3];

    static 
    {
        sKernelWakelockUpdateVersion = 0;
    }
}
