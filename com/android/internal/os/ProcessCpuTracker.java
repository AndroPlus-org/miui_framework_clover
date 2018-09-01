// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.*;
import android.system.OsConstants;
import android.util.Slog;
import com.android.internal.util.FastPrintWriter;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import libcore.io.*;

public class ProcessCpuTracker
{
    public static interface FilterStats
    {

        public abstract boolean needed(Stats stats);
    }

    public static class Stats
    {

        public boolean active;
        public boolean added;
        public String baseName;
        public long base_majfaults;
        public long base_minfaults;
        public long base_stime;
        public long base_uptime;
        public long base_utime;
        public BatteryStatsImpl.Uid.Proc batteryStats;
        final String cmdlineFile;
        public boolean interesting;
        public String name;
        public int nameWidth;
        public final int pid;
        public int rel_majfaults;
        public int rel_minfaults;
        public int rel_stime;
        public long rel_uptime;
        public int rel_utime;
        public boolean removed;
        final String statFile;
        final ArrayList threadStats;
        final String threadsDir;
        public final int uid;
        public long vsize;
        public boolean working;
        final ArrayList workingThreads;

        Stats(int i, int j, boolean flag)
        {
            pid = i;
            if(j < 0)
            {
                File file = new File("/proc", Integer.toString(pid));
                statFile = (new File(file, "stat")).toString();
                cmdlineFile = (new File(file, "cmdline")).toString();
                threadsDir = (new File(file, "task")).toString();
                if(flag)
                {
                    threadStats = new ArrayList();
                    workingThreads = new ArrayList();
                } else
                {
                    threadStats = null;
                    workingThreads = null;
                }
            } else
            {
                statFile = (new File(new File(new File(new File("/proc", Integer.toString(j)), "task"), Integer.toString(pid)), "stat")).toString();
                cmdlineFile = null;
                threadsDir = null;
                threadStats = null;
                workingThreads = null;
            }
            uid = FileUtils.getUid(statFile.toString());
        }
    }


    public ProcessCpuTracker(boolean flag)
    {
        mLoad1 = 0.0F;
        mLoad5 = 0.0F;
        mLoad15 = 0.0F;
        mFirst = true;
        mBuffer = new byte[4096];
        mIncludeThreads = flag;
        mJiffyMillis = 1000L / Libcore.os.sysconf(OsConstants._SC_CLK_TCK);
    }

    private int[] collectStats(String s, int i, boolean flag, int ai[], ArrayList arraylist)
    {
        int k;
        int l;
        int i1;
        int l1;
        ai = Process.getPids(s, ai);
        int j;
        int j1;
        if(ai == null)
            j = 0;
        else
            j = ai.length;
        k = arraylist.size();
        l = 0;
        i1 = 0;
_L2:
        int k1;
        int k2;
label0:
        {
            j1 = k;
            if(i1 < j)
            {
                l1 = ai[i1];
                if(l1 >= 0)
                    break label0;
            }
            for(j1 = k; l < j1; j1--)
            {
                s = (Stats)arraylist.get(l);
                s.rel_utime = 0;
                s.rel_stime = 0;
                s.rel_minfaults = 0;
                s.rel_majfaults = 0;
                s.removed = true;
                s.working = true;
                arraylist.remove(l);
            }

            break MISSING_BLOCK_LABEL_941;
        }
        int i2;
        if(l < k)
            s = (Stats)arraylist.get(l);
        else
            s = null;
        if(s == null || ((Stats) (s)).pid != l1)
            break; /* Loop/switch isn't completed */
        s.added = false;
        s.working = false;
        i2 = l + 1;
        k1 = k;
        l = i2;
        k2 = i1;
        if(((Stats) (s)).interesting)
        {
            long l2 = SystemClock.uptimeMillis();
            long al[] = mProcessStatsData;
            if(!Process.readProcFile(((Stats) (s)).statFile.toString(), PROCESS_STATS_FORMAT, null, al, null))
            {
                k2 = i1;
                l = i2;
                k1 = k;
            } else
            {
                long l3 = al[0];
                long l4 = al[1];
                long l5 = al[2] * mJiffyMillis;
                long l6 = al[3] * mJiffyMillis;
                if(l5 == ((Stats) (s)).base_utime && l6 == ((Stats) (s)).base_stime)
                {
                    s.rel_utime = 0;
                    s.rel_stime = 0;
                    s.rel_minfaults = 0;
                    s.rel_majfaults = 0;
                    k1 = k;
                    l = i2;
                    k2 = i1;
                    if(((Stats) (s)).active)
                    {
                        s.active = false;
                        k1 = k;
                        l = i2;
                        k2 = i1;
                    }
                } else
                {
                    if(!((Stats) (s)).active)
                        s.active = true;
                    if(i < 0)
                    {
                        getName(s, ((Stats) (s)).cmdlineFile);
                        if(((Stats) (s)).threadStats != null)
                            mCurThreadPids = collectStats(((Stats) (s)).threadsDir, l1, false, mCurThreadPids, ((Stats) (s)).threadStats);
                    }
                    s.rel_uptime = l2 - ((Stats) (s)).base_uptime;
                    s.base_uptime = l2;
                    s.rel_utime = (int)(l5 - ((Stats) (s)).base_utime);
                    s.rel_stime = (int)(l6 - ((Stats) (s)).base_stime);
                    s.base_utime = l5;
                    s.base_stime = l6;
                    s.rel_minfaults = (int)(l3 - ((Stats) (s)).base_minfaults);
                    s.rel_majfaults = (int)(l4 - ((Stats) (s)).base_majfaults);
                    s.base_minfaults = l3;
                    s.base_majfaults = l4;
                    s.working = true;
                    k1 = k;
                    l = i2;
                    k2 = i1;
                }
            }
        }
_L3:
        i1 = k2 + 1;
        k = k1;
        if(true) goto _L2; else goto _L1
_L1:
        if(s == null || ((Stats) (s)).pid > l1)
        {
            s = new Stats(l1, i, mIncludeThreads);
            arraylist.add(l, s);
            int j2 = l + 1;
            k++;
            String as[] = mProcessFullStatsStringData;
            long al1[] = mProcessFullStatsData;
            s.base_uptime = SystemClock.uptimeMillis();
            if(Process.readProcFile(((Stats) (s)).statFile.toString(), PROCESS_FULL_STATS_FORMAT, as, al1, null))
            {
                s.vsize = al1[5];
                s.interesting = true;
                s.baseName = as[0];
                s.base_minfaults = al1[1];
                s.base_majfaults = al1[2];
                s.base_utime = al1[3] * mJiffyMillis;
                s.base_stime = al1[4] * mJiffyMillis;
            } else
            {
                Slog.w("ProcessCpuTracker", (new StringBuilder()).append("Skipping unknown process pid ").append(l1).toString());
                s.baseName = "<unknown>";
                s.base_stime = 0L;
                s.base_utime = 0L;
                s.base_majfaults = 0L;
                s.base_minfaults = 0L;
            }
            if(i < 0)
            {
                getName(s, ((Stats) (s)).cmdlineFile);
                if(((Stats) (s)).threadStats != null)
                    mCurThreadPids = collectStats(((Stats) (s)).threadsDir, l1, true, mCurThreadPids, ((Stats) (s)).threadStats);
            } else
            if(((Stats) (s)).interesting)
            {
                s.name = ((Stats) (s)).baseName;
                s.nameWidth = onMeasureProcessName(((Stats) (s)).name);
            }
            s.rel_utime = 0;
            s.rel_stime = 0;
            s.rel_minfaults = 0;
            s.rel_majfaults = 0;
            s.added = true;
            k1 = k;
            l = j2;
            k2 = i1;
            if(!flag)
            {
                k1 = k;
                l = j2;
                k2 = i1;
                if(((Stats) (s)).interesting)
                {
                    s.working = true;
                    k1 = k;
                    l = j2;
                    k2 = i1;
                }
            }
        } else
        {
            s.rel_utime = 0;
            s.rel_stime = 0;
            s.rel_minfaults = 0;
            s.rel_majfaults = 0;
            s.removed = true;
            s.working = true;
            arraylist.remove(l);
            k1 = k - 1;
            k2 = i1 - 1;
        }
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
        return ai;
    }

    private void getName(Stats stats, String s)
    {
        String s2;
label0:
        {
            String s1 = stats.name;
            if(stats.name != null && !stats.name.equals("app_process"))
            {
                s2 = s1;
                if(!stats.name.equals("<pre-initialized>"))
                    break label0;
            }
            String s3 = readFile(s, '\0');
            s = s1;
            if(s3 != null)
            {
                s = s1;
                if(s3.length() > 1)
                {
                    s2 = s3;
                    int i = s3.lastIndexOf("/");
                    s = s2;
                    if(i > 0)
                    {
                        s = s2;
                        if(i < s3.length() - 1)
                            s = s3.substring(i + 1);
                    }
                }
            }
            s2 = s;
            if(s == null)
                s2 = stats.baseName;
        }
        if(stats.name == null || s2.equals(stats.name) ^ true)
        {
            stats.name = s2;
            stats.nameWidth = onMeasureProcessName(stats.name);
        }
    }

    private void printProcessCPU(PrintWriter printwriter, String s, int i, String s1, int j, int k, int l, 
            int i1, int j1, int k1, int l1, int i2)
    {
        printwriter.print(s);
        int j2 = j;
        if(j == 0)
            j2 = 1;
        printRatio(printwriter, k + l + i1 + j1 + k1, j2);
        printwriter.print("% ");
        if(i >= 0)
        {
            printwriter.print(i);
            printwriter.print("/");
        }
        printwriter.print(s1);
        printwriter.print(": ");
        printRatio(printwriter, k, j2);
        printwriter.print("% user + ");
        printRatio(printwriter, l, j2);
        printwriter.print("% kernel");
        if(i1 > 0)
        {
            printwriter.print(" + ");
            printRatio(printwriter, i1, j2);
            printwriter.print("% iowait");
        }
        if(j1 > 0)
        {
            printwriter.print(" + ");
            printRatio(printwriter, j1, j2);
            printwriter.print("% irq");
        }
        if(k1 > 0)
        {
            printwriter.print(" + ");
            printRatio(printwriter, k1, j2);
            printwriter.print("% softirq");
        }
        if(l1 > 0 || i2 > 0)
        {
            printwriter.print(" / faults:");
            if(l1 > 0)
            {
                printwriter.print(" ");
                printwriter.print(l1);
                printwriter.print(" minor");
            }
            if(i2 > 0)
            {
                printwriter.print(" ");
                printwriter.print(i2);
                printwriter.print(" major");
            }
        }
        printwriter.println();
    }

    private void printRatio(PrintWriter printwriter, long l, long l1)
    {
        l = (1000L * l) / l1;
        l1 = l / 10L;
        printwriter.print(l1);
        if(l1 < 10L)
        {
            l -= l1 * 10L;
            if(l != 0L)
            {
                printwriter.print('.');
                printwriter.print(l);
            }
        }
    }

    private String readFile(String s, char c)
    {
        android.os.StrictMode.ThreadPolicy threadpolicy;
        Object obj;
        Object obj1;
        Object obj2;
        threadpolicy = StrictMode.allowThreadDiskReads();
        obj = null;
        obj1 = null;
        obj2 = null;
        FileInputStream fileinputstream;
        fileinputstream = JVM INSTR new #393 <Class FileInputStream>;
        fileinputstream.FileInputStream(s);
        int i;
        i = fileinputstream.read(mBuffer);
        fileinputstream.close();
        if(i <= 0) goto _L2; else goto _L1
_L1:
        int j = 0;
_L3:
        if(j >= i)
            break MISSING_BLOCK_LABEL_66;
        if(mBuffer[j] != c)
            break MISSING_BLOCK_LABEL_92;
        s = new String(mBuffer, 0, j);
        IoUtils.closeQuietly(fileinputstream);
        StrictMode.setThreadPolicy(threadpolicy);
        return s;
        j++;
        if(true) goto _L3; else goto _L2
_L2:
        IoUtils.closeQuietly(fileinputstream);
        StrictMode.setThreadPolicy(threadpolicy);
_L4:
        return null;
        s;
        fileinputstream = obj2;
_L8:
        IoUtils.closeQuietly(fileinputstream);
        StrictMode.setThreadPolicy(threadpolicy);
          goto _L4
        s;
        s = obj;
_L7:
        IoUtils.closeQuietly(s);
        StrictMode.setThreadPolicy(threadpolicy);
          goto _L4
        s;
        fileinputstream = obj1;
_L6:
        IoUtils.closeQuietly(fileinputstream);
        StrictMode.setThreadPolicy(threadpolicy);
        throw s;
        s;
        if(true) goto _L6; else goto _L5
_L5:
        s;
        s = fileinputstream;
          goto _L7
        s;
          goto _L8
    }

    final void buildWorkingProcs()
    {
        if(!mWorkingProcsSorted)
        {
            mWorkingProcs.clear();
            int i = mProcStats.size();
            for(int j = 0; j < i; j++)
            {
                Stats stats = (Stats)mProcStats.get(j);
                if(!stats.working)
                    continue;
                mWorkingProcs.add(stats);
                if(stats.threadStats == null || stats.threadStats.size() <= 1)
                    continue;
                stats.workingThreads.clear();
                int k = stats.threadStats.size();
                for(int l = 0; l < k; l++)
                {
                    Stats stats1 = (Stats)stats.threadStats.get(l);
                    if(stats1.working)
                        stats.workingThreads.add(stats1);
                }

                Collections.sort(stats.workingThreads, sLoadComparator);
            }

            Collections.sort(mWorkingProcs, sLoadComparator);
            mWorkingProcsSorted = true;
        }
    }

    public final int countStats()
    {
        return mProcStats.size();
    }

    public final int countWorkingStats()
    {
        buildWorkingProcs();
        return mWorkingProcs.size();
    }

    public long getCpuTimeForPid(int i)
    {
        long al[] = mSinglePidStatsData;
        al;
        JVM INSTR monitorenter ;
        long al1[];
        Object obj = JVM INSTR new #276 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        obj = ((StringBuilder) (obj)).append("/proc/").append(i).append("/stat").toString();
        al1 = mSinglePidStatsData;
        if(!Process.readProcFile(((String) (obj)), PROCESS_STATS_FORMAT, null, al1, null))
            break MISSING_BLOCK_LABEL_85;
        long l;
        long l1;
        l = al1[2];
        l1 = al1[3];
        long l2 = mJiffyMillis;
        al;
        JVM INSTR monitorexit ;
        return l2 * (l + l1);
        al;
        JVM INSTR monitorexit ;
        return 0L;
        Exception exception;
        exception;
        throw exception;
    }

    public final int getLastIdleTime()
    {
        return mRelIdleTime;
    }

    public final int getLastIoWaitTime()
    {
        return mRelIoWaitTime;
    }

    public final int getLastIrqTime()
    {
        return mRelIrqTime;
    }

    public final int getLastSoftIrqTime()
    {
        return mRelSoftIrqTime;
    }

    public final int getLastSystemTime()
    {
        return mRelSystemTime;
    }

    public final int getLastUserTime()
    {
        return mRelUserTime;
    }

    public final Stats getStats(int i)
    {
        return (Stats)mProcStats.get(i);
    }

    public final List getStats(FilterStats filterstats)
    {
        ArrayList arraylist = new ArrayList(mProcStats.size());
        int i = mProcStats.size();
        for(int j = 0; j < i; j++)
        {
            Stats stats = (Stats)mProcStats.get(j);
            if(filterstats.needed(stats))
                arraylist.add(stats);
        }

        return arraylist;
    }

    public final float getTotalCpuPercent()
    {
        int i = mRelUserTime + mRelSystemTime + mRelIrqTime + mRelIdleTime;
        if(i <= 0)
            return 0.0F;
        else
            return ((float)(mRelUserTime + mRelSystemTime + mRelIrqTime) * 100F) / (float)i;
    }

    public final Stats getWorkingStats(int i)
    {
        return (Stats)mWorkingProcs.get(i);
    }

    public final boolean hasGoodLastStats()
    {
        return mRelStatsAreGood;
    }

    public void init()
    {
        mFirst = true;
        update();
    }

    public void onLoadChanged(float f, float f1, float f2)
    {
    }

    public int onMeasureProcessName(String s)
    {
        return 0;
    }

    public final String printCurrentLoad()
    {
        StringWriter stringwriter = new StringWriter();
        FastPrintWriter fastprintwriter = new FastPrintWriter(stringwriter, false, 128);
        fastprintwriter.print("Load: ");
        fastprintwriter.print(mLoad1);
        fastprintwriter.print(" / ");
        fastprintwriter.print(mLoad5);
        fastprintwriter.print(" / ");
        fastprintwriter.println(mLoad15);
        fastprintwriter.flush();
        return stringwriter.toString();
    }

    public final String printCurrentState(long l)
    {
        Object obj;
        StringWriter stringwriter;
        FastPrintWriter fastprintwriter;
        int i;
        int j;
        int k;
        int i1;
        int j1;
        int k1;
        int j2;
        Stats stats1;
        obj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        buildWorkingProcs();
        stringwriter = new StringWriter();
        fastprintwriter = new FastPrintWriter(stringwriter, false, 1024);
        fastprintwriter.print("CPU usage from ");
        long l1;
        long l2;
        int i2;
        int k2;
        int i3;
        if(l > mLastSampleTime)
        {
            fastprintwriter.print(l - mLastSampleTime);
            fastprintwriter.print("ms to ");
            fastprintwriter.print(l - mCurrentSampleTime);
            fastprintwriter.print("ms ago");
        } else
        {
            fastprintwriter.print(mLastSampleTime - l);
            fastprintwriter.print("ms to ");
            fastprintwriter.print(mCurrentSampleTime - l);
            fastprintwriter.print("ms later");
        }
        fastprintwriter.print(" (");
        fastprintwriter.print(((SimpleDateFormat) (obj)).format(new Date(mLastSampleWallTime)));
        fastprintwriter.print(" to ");
        fastprintwriter.print(((SimpleDateFormat) (obj)).format(new Date(mCurrentSampleWallTime)));
        fastprintwriter.print(")");
        l1 = mCurrentSampleTime;
        l = mLastSampleTime;
        l2 = mCurrentSampleRealTime - mLastSampleRealTime;
        if(l2 > 0L)
            l = (100L * (l1 - l)) / l2;
        else
            l = 0L;
        if(l != 100L)
        {
            fastprintwriter.print(" with ");
            fastprintwriter.print(l);
            fastprintwriter.print("% awake");
        }
        fastprintwriter.println(":");
        i = mRelUserTime;
        j = mRelSystemTime;
        k = mRelIoWaitTime;
        i1 = mRelIrqTime;
        j1 = mRelSoftIrqTime;
        k1 = mRelIdleTime;
        i2 = mWorkingProcs.size();
        j2 = 0;
_L9:
        if(j2 >= i2) goto _L2; else goto _L1
_L1:
        Stats stats = (Stats)mWorkingProcs.get(j2);
        if(stats.added)
            obj = " +";
        else
        if(stats.removed)
            obj = " -";
        else
            obj = "  ";
        printProcessCPU(fastprintwriter, ((String) (obj)), stats.pid, stats.name, (int)stats.rel_uptime, stats.rel_utime, stats.rel_stime, 0, 0, 0, stats.rel_minfaults, stats.rel_majfaults);
        if(stats.removed || stats.workingThreads == null)
            continue; /* Loop/switch isn't completed */
        k2 = stats.workingThreads.size();
        i3 = 0;
_L8:
        if(i3 >= k2)
            continue; /* Loop/switch isn't completed */
        stats1 = (Stats)stats.workingThreads.get(i3);
        if(!stats1.added)
            break; /* Loop/switch isn't completed */
        obj = "   +";
_L6:
        printProcessCPU(fastprintwriter, ((String) (obj)), stats1.pid, stats1.name, (int)stats.rel_uptime, stats1.rel_utime, stats1.rel_stime, 0, 0, 0, 0, 0);
        i3++;
        if(true) goto _L4; else goto _L3
_L4:
        break MISSING_BLOCK_LABEL_393;
_L3:
        if(stats1.removed)
            obj = "   -";
        else
            obj = "    ";
        if(true) goto _L6; else goto _L5
_L5:
        if(true) goto _L8; else goto _L7
_L7:
        j2++;
          goto _L9
_L2:
        printProcessCPU(fastprintwriter, "", -1, "TOTAL", i + j + k + i1 + j1 + k1, mRelUserTime, mRelSystemTime, mRelIoWaitTime, mRelIrqTime, mRelSoftIrqTime, 0, 0);
        fastprintwriter.flush();
        return stringwriter.toString();
    }

    public void update()
    {
        android.os.StrictMode.ThreadPolicy threadpolicy;
        long l = SystemClock.uptimeMillis();
        long l1 = SystemClock.elapsedRealtime();
        long l2 = System.currentTimeMillis();
        long al[] = mSystemCpuData;
        if(Process.readProcFile("/proc/stat", SYSTEM_CPU_FORMAT, null, al, null))
        {
            long l3 = (al[0] + al[1]) * mJiffyMillis;
            long l4 = al[2] * mJiffyMillis;
            long l5 = al[3] * mJiffyMillis;
            long l6 = al[4] * mJiffyMillis;
            long l7 = al[5] * mJiffyMillis;
            long l8 = al[6] * mJiffyMillis;
            mRelUserTime = (int)(l3 - mBaseUserTime);
            mRelSystemTime = (int)(l4 - mBaseSystemTime);
            mRelIoWaitTime = (int)(l6 - mBaseIoWaitTime);
            mRelIrqTime = (int)(l7 - mBaseIrqTime);
            mRelSoftIrqTime = (int)(l8 - mBaseSoftIrqTime);
            mRelIdleTime = (int)(l5 - mBaseIdleTime);
            mRelStatsAreGood = true;
            mBaseUserTime = l3;
            mBaseSystemTime = l4;
            mBaseIoWaitTime = l6;
            mBaseIrqTime = l7;
            mBaseSoftIrqTime = l8;
            mBaseIdleTime = l5;
        }
        mLastSampleTime = mCurrentSampleTime;
        mCurrentSampleTime = l;
        mLastSampleRealTime = mCurrentSampleRealTime;
        mCurrentSampleRealTime = l1;
        mLastSampleWallTime = mCurrentSampleWallTime;
        mCurrentSampleWallTime = l2;
        threadpolicy = StrictMode.allowThreadDiskReads();
        mCurPids = collectStats("/proc", -1, mFirst, mCurPids, mProcStats);
        float f;
        float f1;
        float f2;
label0:
        {
            StrictMode.setThreadPolicy(threadpolicy);
            threadpolicy = mLoadAverageData;
            if(!Process.readProcFile("/proc/loadavg", LOAD_AVERAGE_FORMAT, null, null, threadpolicy))
                break label0;
            f = threadpolicy[0];
            f1 = threadpolicy[1];
            f2 = threadpolicy[2];
        }
        Exception exception;
        if(f != mLoad1 || f1 != mLoad5 || f2 != mLoad15)
        {
            mLoad1 = f;
            mLoad5 = f1;
            mLoad15 = f2;
            onLoadChanged(f, f1, f2);
        }
        mWorkingProcsSorted = false;
        mFirst = false;
        return;
        exception;
        StrictMode.setThreadPolicy(threadpolicy);
        throw exception;
    }

    private static final boolean DEBUG = false;
    private static final int LOAD_AVERAGE_FORMAT[] = {
        16416, 16416, 16416
    };
    private static final int PROCESS_FULL_STATS_FORMAT[] = {
        32, 4640, 32, 32, 32, 32, 32, 32, 32, 8224, 
        32, 8224, 32, 8224, 8224, 32, 32, 32, 32, 32, 
        32, 32, 8224
    };
    static final int PROCESS_FULL_STAT_MAJOR_FAULTS = 2;
    static final int PROCESS_FULL_STAT_MINOR_FAULTS = 1;
    static final int PROCESS_FULL_STAT_STIME = 4;
    static final int PROCESS_FULL_STAT_UTIME = 3;
    static final int PROCESS_FULL_STAT_VSIZE = 5;
    private static final int PROCESS_STATS_FORMAT[] = {
        32, 544, 32, 32, 32, 32, 32, 32, 32, 8224, 
        32, 8224, 32, 8224, 8224
    };
    static final int PROCESS_STAT_MAJOR_FAULTS = 1;
    static final int PROCESS_STAT_MINOR_FAULTS = 0;
    static final int PROCESS_STAT_STIME = 3;
    static final int PROCESS_STAT_UTIME = 2;
    private static final int SYSTEM_CPU_FORMAT[] = {
        288, 8224, 8224, 8224, 8224, 8224, 8224, 8224
    };
    private static final String TAG = "ProcessCpuTracker";
    private static final boolean localLOGV = false;
    private static final Comparator sLoadComparator = new Comparator() {

        public final int compare(Stats stats, Stats stats1)
        {
            byte byte0 = -1;
            int i = stats.rel_utime + stats.rel_stime;
            int j = stats1.rel_utime + stats1.rel_stime;
            if(i != j)
            {
                if(i <= j)
                    byte0 = 1;
                return byte0;
            }
            if(stats.added != stats1.added)
            {
                if(!stats.added)
                    byte0 = 1;
                return byte0;
            }
            if(stats.removed != stats1.removed)
            {
                if(!stats.added)
                    byte0 = 1;
                return byte0;
            } else
            {
                return 0;
            }
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((Stats)obj, (Stats)obj1);
        }

    }
;
    private long mBaseIdleTime;
    private long mBaseIoWaitTime;
    private long mBaseIrqTime;
    private long mBaseSoftIrqTime;
    private long mBaseSystemTime;
    private long mBaseUserTime;
    private byte mBuffer[];
    private int mCurPids[];
    private int mCurThreadPids[];
    private long mCurrentSampleRealTime;
    private long mCurrentSampleTime;
    private long mCurrentSampleWallTime;
    private boolean mFirst;
    private final boolean mIncludeThreads;
    private final long mJiffyMillis;
    private long mLastSampleRealTime;
    private long mLastSampleTime;
    private long mLastSampleWallTime;
    private float mLoad1;
    private float mLoad15;
    private float mLoad5;
    private final float mLoadAverageData[] = new float[3];
    private final ArrayList mProcStats = new ArrayList();
    private final long mProcessFullStatsData[] = new long[6];
    private final String mProcessFullStatsStringData[] = new String[6];
    private final long mProcessStatsData[] = new long[4];
    private int mRelIdleTime;
    private int mRelIoWaitTime;
    private int mRelIrqTime;
    private int mRelSoftIrqTime;
    private boolean mRelStatsAreGood;
    private int mRelSystemTime;
    private int mRelUserTime;
    private final long mSinglePidStatsData[] = new long[4];
    private final long mSystemCpuData[] = new long[7];
    private final ArrayList mWorkingProcs = new ArrayList();
    private boolean mWorkingProcsSorted;

}
