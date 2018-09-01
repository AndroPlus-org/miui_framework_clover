// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app.procstats;

import android.os.Parcel;
import android.os.SystemClock;
import android.util.Slog;
import android.util.TimeUtils;
import java.io.PrintWriter;

// Referenced classes of package com.android.internal.app.procstats:
//            DurationsTable, ProcessStats, DumpUtils, SparseMappingTable, 
//            ProcessState

public final class ServiceState
{

    public ServiceState(ProcessStats processstats, String s, String s1, String s2, ProcessState processstate)
    {
        mRunState = -1;
        mStartedState = -1;
        mBoundState = -1;
        mExecState = -1;
        mPackage = s;
        mName = s1;
        mProcessName = s2;
        mProc = processstate;
        mDurations = new DurationsTable(processstats.mTableData);
    }

    private void dumpStats(PrintWriter printwriter, String s, String s1, String s2, String s3, int i, int j, 
            int k, long l, long l1, long l2, 
            boolean flag)
    {
        if(i != 0)
            if(flag)
            {
                printwriter.print(s);
                printwriter.print(s3);
                printwriter.print(" op count ");
                printwriter.print(i);
                printwriter.println(":");
                dumpTime(printwriter, s1, j, k, l, l1);
            } else
            {
                l = dumpTime(null, null, j, k, l, l1);
                printwriter.print(s);
                printwriter.print(s2);
                printwriter.print(s3);
                printwriter.print(" count ");
                printwriter.print(i);
                printwriter.print(" / time ");
                DumpUtils.printPercent(printwriter, (double)l / (double)l2);
                printwriter.println();
            }
    }

    private void dumpTimeCheckin(PrintWriter printwriter, String s, String s1, int i, int j, String s2, int k, 
            int l, int i1, long l1, long l2)
    {
        if(l <= 0)
            return;
        printwriter.print(s);
        printwriter.print(",");
        printwriter.print(s1);
        printwriter.print(",");
        printwriter.print(i);
        printwriter.print(",");
        printwriter.print(j);
        printwriter.print(",");
        printwriter.print(s2);
        printwriter.print(",");
        printwriter.print(l);
        i = 0;
        l = mDurations.getKeyCount();
        j = 0;
        while(j < l) 
        {
            int j1 = mDurations.getKeyAt(j);
            long l3 = mDurations.getValue(j1);
            byte byte0 = SparseMappingTable.getIdFromKey(j1);
            j1 = byte0 / 4;
            if(byte0 % 4 == k)
            {
                long l4 = l3;
                if(i1 == j1)
                {
                    i = 1;
                    l4 = l3 + (l2 - l1);
                }
                DumpUtils.printAdjTagAndValue(printwriter, j1, l4);
            }
            j++;
        }
        if(i == 0 && i1 != -1)
            DumpUtils.printAdjTagAndValue(printwriter, i1, l2 - l1);
        printwriter.println();
    }

    private void updateRunning(int i, long l)
    {
        if(mStartedState == -1 && mBoundState == -1 && mExecState == -1)
            i = -1;
        if(mRunState != i)
        {
            if(mRunState != -1)
                mDurations.addDuration(mRunState * 4 + 0, l - mRunStartTime);
            else
            if(i != -1)
                mRunCount = mRunCount + 1;
            mRunState = i;
            mRunStartTime = l;
        }
    }

    public void add(ServiceState servicestate)
    {
        mDurations.addDurations(servicestate.mDurations);
        mRunCount = mRunCount + servicestate.mRunCount;
        mStartedCount = mStartedCount + servicestate.mStartedCount;
        mBoundCount = mBoundCount + servicestate.mBoundCount;
        mExecCount = mExecCount + servicestate.mExecCount;
    }

    public void applyNewOwner(Object obj)
    {
        if(mOwner != obj)
            if(mOwner == null)
            {
                mOwner = obj;
                mProc.incActiveServices(mName);
            } else
            {
                mOwner = obj;
                while(false) 
                    if(mStarted || mBoundState != -1 || mExecState != -1)
                    {
                        long l = SystemClock.uptimeMillis();
                        if(mStarted)
                            setStarted(false, 0, l);
                        if(mBoundState != -1)
                            setBound(false, 0, l);
                        if(mExecState != -1)
                            setExecuting(false, 0, l);
                    }
            }
    }

    public void clearCurrentOwner(Object obj, boolean flag)
    {
        if(mOwner == obj)
        {
            mProc.decActiveServices(mName);
            if(mStarted || mBoundState != -1 || mExecState != -1)
            {
                long l = SystemClock.uptimeMillis();
                if(mStarted)
                {
                    if(!flag)
                        Slog.wtfStack("ProcessStats", (new StringBuilder()).append("Service owner ").append(obj).append(" cleared while started: pkg=").append(mPackage).append(" service=").append(mName).append(" proc=").append(mProc).toString());
                    setStarted(false, 0, l);
                }
                if(mBoundState != -1)
                {
                    if(!flag)
                        Slog.wtfStack("ProcessStats", (new StringBuilder()).append("Service owner ").append(obj).append(" cleared while bound: pkg=").append(mPackage).append(" service=").append(mName).append(" proc=").append(mProc).toString());
                    setBound(false, 0, l);
                }
                if(mExecState != -1)
                {
                    if(!flag)
                        Slog.wtfStack("ProcessStats", (new StringBuilder()).append("Service owner ").append(obj).append(" cleared while exec: pkg=").append(mPackage).append(" service=").append(mName).append(" proc=").append(mProc).toString());
                    setExecuting(false, 0, l);
                }
            }
            mOwner = null;
        }
    }

    public void commitStateTime(long l)
    {
        if(mRunState != -1)
        {
            mDurations.addDuration(mRunState * 4 + 0, l - mRunStartTime);
            mRunStartTime = l;
        }
        if(mStartedState != -1)
        {
            mDurations.addDuration(mStartedState * 4 + 1, l - mStartedStartTime);
            mStartedStartTime = l;
        }
        if(mBoundState != -1)
        {
            mDurations.addDuration(mBoundState * 4 + 2, l - mBoundStartTime);
            mBoundStartTime = l;
        }
        if(mExecState != -1)
        {
            mDurations.addDuration(mExecState * 4 + 3, l - mExecStartTime);
            mExecStartTime = l;
        }
    }

    public void dumpStats(PrintWriter printwriter, String s, String s1, String s2, long l, long l1, boolean flag, boolean flag1)
    {
        int i = mRunCount;
        int j = mRunState;
        long l2 = mRunStartTime;
        boolean flag2;
        if(flag)
            flag2 = flag1;
        else
            flag2 = true;
        dumpStats(printwriter, s, s1, s2, "Running", i, 0, j, l2, l, l1, flag2);
        j = mStartedCount;
        i = mStartedState;
        l2 = mStartedStartTime;
        if(flag)
            flag2 = flag1;
        else
            flag2 = true;
        dumpStats(printwriter, s, s1, s2, "Started", j, 1, i, l2, l, l1, flag2);
        j = mBoundCount;
        i = mBoundState;
        l2 = mBoundStartTime;
        if(flag)
            flag2 = flag1;
        else
            flag2 = true;
        dumpStats(printwriter, s, s1, s2, "Bound", j, 2, i, l2, l, l1, flag2);
        i = mExecCount;
        j = mExecState;
        l2 = mExecStartTime;
        if(flag)
            flag = flag1;
        else
            flag = true;
        dumpStats(printwriter, s, s1, s2, "Executing", i, 3, j, l2, l, l1, flag);
        if(flag1)
        {
            if(mOwner != null)
            {
                printwriter.print("        mOwner=");
                printwriter.println(mOwner);
            }
            if(mStarted || mRestarting)
            {
                printwriter.print("        mStarted=");
                printwriter.print(mStarted);
                printwriter.print(" mRestarting=");
                printwriter.println(mRestarting);
            }
        }
    }

    public long dumpTime(PrintWriter printwriter, String s, int i, int j, long l, long l1)
    {
        long l2 = 0L;
        int k = -1;
        for(int i1 = 0; i1 < 8; i1 += 4)
        {
            int j1 = -1;
            int k1 = 0;
            while(k1 < 4) 
            {
                int i2 = k1 + i1;
                long l3 = getDuration(i, j, l, i2, l1);
                String s1 = "";
                String s2 = s1;
                if(j == i2)
                {
                    s2 = s1;
                    if(printwriter != null)
                        s2 = " (running)";
                }
                i2 = j1;
                int j2 = k;
                long l4 = l2;
                if(l3 != 0L)
                {
                    i2 = j1;
                    j2 = k;
                    if(printwriter != null)
                    {
                        printwriter.print(s);
                        if(k != i1)
                            k = i1;
                        else
                            k = -1;
                        DumpUtils.printScreenLabel(printwriter, k);
                        j2 = i1;
                        if(j1 != k1)
                            j1 = k1;
                        else
                            j1 = -1;
                        DumpUtils.printMemLabel(printwriter, j1, '\0');
                        i2 = k1;
                        printwriter.print(": ");
                        TimeUtils.formatDuration(l3, printwriter);
                        printwriter.println(s2);
                    }
                    l4 = l2 + l3;
                }
                k1++;
                j1 = i2;
                k = j2;
                l2 = l4;
            }
        }

        if(l2 != 0L && printwriter != null)
        {
            printwriter.print(s);
            printwriter.print("    TOTAL: ");
            TimeUtils.formatDuration(l2, printwriter);
            printwriter.println();
        }
        return l2;
    }

    public void dumpTimesCheckin(PrintWriter printwriter, String s, int i, int j, String s1, long l)
    {
        dumpTimeCheckin(printwriter, "pkgsvc-run", s, i, j, s1, 0, mRunCount, mRunState, mRunStartTime, l);
        dumpTimeCheckin(printwriter, "pkgsvc-start", s, i, j, s1, 1, mStartedCount, mStartedState, mStartedStartTime, l);
        dumpTimeCheckin(printwriter, "pkgsvc-bound", s, i, j, s1, 2, mBoundCount, mBoundState, mBoundStartTime, l);
        dumpTimeCheckin(printwriter, "pkgsvc-exec", s, i, j, s1, 3, mExecCount, mExecState, mExecStartTime, l);
    }

    public long getDuration(int i, int j, long l, int k, long l1)
    {
        long l2 = mDurations.getValueForId((byte)(i + k * 4));
        long l3 = l2;
        if(j == k)
            l3 = l2 + (l1 - l);
        return l3;
    }

    public String getName()
    {
        return mName;
    }

    public String getPackage()
    {
        return mPackage;
    }

    public ProcessState getProcess()
    {
        return mProc;
    }

    public String getProcessName()
    {
        return mProcessName;
    }

    public boolean isInUse()
    {
        boolean flag;
        if(mOwner == null)
            flag = mRestarting;
        else
            flag = true;
        return flag;
    }

    public boolean isRestarting()
    {
        return mRestarting;
    }

    public boolean readFromParcel(Parcel parcel)
    {
        if(!mDurations.readFromParcel(parcel))
        {
            return false;
        } else
        {
            mRunCount = parcel.readInt();
            mStartedCount = parcel.readInt();
            mBoundCount = parcel.readInt();
            mExecCount = parcel.readInt();
            return true;
        }
    }

    public void resetSafely(long l)
    {
        boolean flag = true;
        mDurations.resetTable();
        int i;
        if(mRunState != -1)
            i = 1;
        else
            i = 0;
        mRunCount = i;
        if(mStartedState != -1)
            i = 1;
        else
            i = 0;
        mStartedCount = i;
        if(mBoundState != -1)
            i = 1;
        else
            i = 0;
        mBoundCount = i;
        if(mExecState != -1)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        mExecCount = i;
        mExecStartTime = l;
        mBoundStartTime = l;
        mStartedStartTime = l;
        mRunStartTime = l;
    }

    public void setBound(boolean flag, int i, long l)
    {
        if(mOwner == null)
            Slog.wtf("ProcessStats", (new StringBuilder()).append("Binding service ").append(this).append(" without owner").toString());
        int j;
        if(flag)
            j = i;
        else
            j = -1;
        if(mBoundState == j) goto _L2; else goto _L1
_L1:
        if(mBoundState == -1) goto _L4; else goto _L3
_L3:
        mDurations.addDuration(mBoundState * 4 + 2, l - mBoundStartTime);
_L6:
        mBoundState = j;
        mBoundStartTime = l;
        updateRunning(i, l);
_L2:
        return;
_L4:
        if(flag)
            mBoundCount = mBoundCount + 1;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void setExecuting(boolean flag, int i, long l)
    {
        if(mOwner == null)
            Slog.wtf("ProcessStats", (new StringBuilder()).append("Executing service ").append(this).append(" without owner").toString());
        int j;
        if(flag)
            j = i;
        else
            j = -1;
        if(mExecState == j) goto _L2; else goto _L1
_L1:
        if(mExecState == -1) goto _L4; else goto _L3
_L3:
        mDurations.addDuration(mExecState * 4 + 3, l - mExecStartTime);
_L6:
        mExecState = j;
        mExecStartTime = l;
        updateRunning(i, l);
_L2:
        return;
_L4:
        if(flag)
            mExecCount = mExecCount + 1;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void setMemFactor(int i, long l)
    {
        if(!isRestarting()) goto _L2; else goto _L1
_L1:
        setRestarting(true, i, l);
_L4:
        return;
_L2:
        if(isInUse())
        {
            if(mStartedState != -1)
                setStarted(true, i, l);
            if(mBoundState != -1)
                setBound(true, i, l);
            if(mExecState != -1)
                setExecuting(true, i, l);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setProcess(ProcessState processstate)
    {
        mProc = processstate;
    }

    public void setRestarting(boolean flag, int i, long l)
    {
        mRestarting = flag;
        updateStartedState(i, l);
    }

    public void setStarted(boolean flag, int i, long l)
    {
        if(mOwner == null)
            Slog.wtf("ProcessStats", (new StringBuilder()).append("Starting service ").append(this).append(" without owner").toString());
        mStarted = flag;
        updateStartedState(i, l);
    }

    public String toString()
    {
        return (new StringBuilder()).append("ServiceState{").append(Integer.toHexString(System.identityHashCode(this))).append(" ").append(mName).append(" pkg=").append(mPackage).append(" proc=").append(Integer.toHexString(System.identityHashCode(this))).append("}").toString();
    }

    public void updateStartedState(int i, long l)
    {
        boolean flag;
        boolean flag1;
        int j;
        if(mStartedState != -1)
            flag = true;
        else
            flag = false;
        if(!mStarted)
            flag1 = mRestarting;
        else
            flag1 = true;
        if(flag1)
            j = i;
        else
            j = -1;
        if(mStartedState != j)
        {
            if(mStartedState != -1)
                mDurations.addDuration(mStartedState * 4 + 1, l - mStartedStartTime);
            else
            if(flag1)
                mStartedCount = mStartedCount + 1;
            mStartedState = j;
            mStartedStartTime = l;
            mProc = mProc.pullFixedProc(mPackage);
            if(flag != flag1)
                if(flag1)
                    mProc.incStartedServices(i, l, mName);
                else
                    mProc.decStartedServices(i, l, mName);
            updateRunning(i, l);
        }
    }

    public void writeToParcel(Parcel parcel, long l)
    {
        mDurations.writeToParcel(parcel);
        parcel.writeInt(mRunCount);
        parcel.writeInt(mStartedCount);
        parcel.writeInt(mBoundCount);
        parcel.writeInt(mExecCount);
    }

    private static final boolean DEBUG = false;
    public static final int SERVICE_BOUND = 2;
    public static final int SERVICE_COUNT = 4;
    public static final int SERVICE_EXEC = 3;
    public static final int SERVICE_RUN = 0;
    public static final int SERVICE_STARTED = 1;
    private static final String TAG = "ProcessStats";
    private int mBoundCount;
    private long mBoundStartTime;
    private int mBoundState;
    private final DurationsTable mDurations;
    private int mExecCount;
    private long mExecStartTime;
    private int mExecState;
    private final String mName;
    private Object mOwner;
    private final String mPackage;
    private ProcessState mProc;
    private final String mProcessName;
    private boolean mRestarting;
    private int mRunCount;
    private long mRunStartTime;
    private int mRunState;
    private boolean mStarted;
    private int mStartedCount;
    private long mStartedStartTime;
    private int mStartedState;
}
