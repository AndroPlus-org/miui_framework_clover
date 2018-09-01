// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app.procstats;

import android.os.*;
import android.util.*;
import com.android.internal.app.ProcessMap;
import java.io.PrintWriter;
import java.util.Comparator;

// Referenced classes of package com.android.internal.app.procstats:
//            DurationsTable, ProcessStats, PssTable, SparseMappingTable, 
//            DumpUtils

public final class ProcessState
{
    static class PssAggr
    {

        void add(long l, long l1)
        {
            pss = (long)((double)pss * (double)samples + (double)l * (double)l1) / (samples + l1);
            samples = samples + l1;
        }

        long pss;
        long samples;

        PssAggr()
        {
            pss = 0L;
            samples = 0L;
        }
    }


    static long _2D_get0(ProcessState processstate)
    {
        return processstate.mTmpTotalTime;
    }

    public ProcessState(ProcessState processstate, String s, int i, int j, String s1, long l)
    {
        mCurState = -1;
        mLastPssState = -1;
        mStats = processstate.mStats;
        mName = s1;
        mCommonProcess = processstate;
        mPackage = s;
        mUid = i;
        mVersion = j;
        mCurState = processstate.mCurState;
        mStartTime = l;
        mDurations = new DurationsTable(processstate.mStats.mTableData);
        mPssTable = new PssTable(processstate.mStats.mTableData);
    }

    public ProcessState(ProcessStats processstats, String s, int i, int j, String s1)
    {
        mCurState = -1;
        mLastPssState = -1;
        mStats = processstats;
        mName = s1;
        mCommonProcess = this;
        mPackage = s;
        mUid = i;
        mVersion = j;
        mDurations = new DurationsTable(processstats.mTableData);
        mPssTable = new PssTable(processstats.mTableData);
    }

    private void addCachedKill(int i, long l, long l1, long l2)
    {
        if(mNumCachedKill <= 0)
        {
            mNumCachedKill = i;
            mMinCachedKillPss = l;
            mAvgCachedKillPss = l1;
            mMaxCachedKillPss = l2;
        } else
        {
            if(l < mMinCachedKillPss)
                mMinCachedKillPss = l;
            if(l2 > mMaxCachedKillPss)
                mMaxCachedKillPss = l2;
            mAvgCachedKillPss = (long)(((double)mAvgCachedKillPss * (double)mNumCachedKill + (double)l1) / (double)(mNumCachedKill + i));
            mNumCachedKill = mNumCachedKill + i;
        }
    }

    private void dumpProcessSummaryDetails(PrintWriter printwriter, String s, String s1, int ai[], int ai1[], int ai2[], long l, long l1, boolean flag)
    {
        ai = new ProcessStats.ProcessDataCollection(ai, ai1, ai2);
        computeProcessData(ai, l);
        if(((double)((ProcessStats.ProcessDataCollection) (ai)).totalTime / (double)l1) * 100D >= 0.0050000000000000001D || ((ProcessStats.ProcessDataCollection) (ai)).numPss != 0L)
        {
            if(s != null)
                printwriter.print(s);
            if(s1 != null)
                printwriter.print(s1);
            ai.print(printwriter, l1, flag);
            if(s != null)
                printwriter.println();
        }
    }

    private void ensureNotDead()
    {
        if(!mDead)
        {
            return;
        } else
        {
            Slog.w("ProcessStats", (new StringBuilder()).append("ProcessState dead: name=").append(mName).append(" pkg=").append(mPackage).append(" uid=").append(mUid).append(" common.name=").append(mCommonProcess.mName).toString());
            return;
        }
    }

    private ProcessState pullFixedProc(ArrayMap arraymap, int i)
    {
        ProcessStats.ProcessStateHolder processstateholder = (ProcessStats.ProcessStateHolder)arraymap.valueAt(i);
        Object obj = processstateholder.state;
        ProcessState processstate = ((ProcessState) (obj));
        if(mDead)
        {
            processstate = ((ProcessState) (obj));
            if(((ProcessState) (obj)).mCommonProcess != obj)
            {
                Log.wtf("ProcessStats", (new StringBuilder()).append("Pulling dead proc: name=").append(mName).append(" pkg=").append(mPackage).append(" uid=").append(mUid).append(" common.name=").append(mCommonProcess.mName).toString());
                processstate = mStats.getProcessStateLocked(((ProcessState) (obj)).mPackage, ((ProcessState) (obj)).mUid, ((ProcessState) (obj)).mVersion, ((ProcessState) (obj)).mName);
            }
        }
        obj = processstate;
        if(processstate.mMultiPackage)
        {
            obj = (SparseArray)mStats.mPackages.get((String)arraymap.keyAt(i), processstate.mUid);
            if(obj == null)
                throw new IllegalStateException((new StringBuilder()).append("No existing package ").append((String)arraymap.keyAt(i)).append("/").append(processstate.mUid).append(" for multi-proc ").append(processstate.mName).toString());
            ProcessStats.PackageState packagestate = (ProcessStats.PackageState)((SparseArray) (obj)).get(processstate.mVersion);
            if(packagestate == null)
                throw new IllegalStateException((new StringBuilder()).append("No existing package ").append((String)arraymap.keyAt(i)).append("/").append(processstate.mUid).append(" for multi-proc ").append(processstate.mName).append(" version ").append(processstate.mVersion).toString());
            arraymap = processstate.mName;
            obj = (ProcessState)packagestate.mProcesses.get(processstate.mName);
            if(obj == null)
                throw new IllegalStateException((new StringBuilder()).append("Didn't create per-package process ").append(arraymap).append(" in pkg ").append(packagestate.mPackageName).append("/").append(packagestate.mUid).toString());
            processstateholder.state = ((ProcessState) (obj));
        }
        return ((ProcessState) (obj));
    }

    public void add(ProcessState processstate)
    {
        mDurations.addDurations(processstate.mDurations);
        mPssTable.mergeStats(processstate.mPssTable);
        mNumExcessiveCpu = mNumExcessiveCpu + processstate.mNumExcessiveCpu;
        if(processstate.mNumCachedKill > 0)
            addCachedKill(processstate.mNumCachedKill, processstate.mMinCachedKillPss, processstate.mAvgCachedKillPss, processstate.mMaxCachedKillPss);
    }

    public void addPss(long l, long l1, boolean flag, ArrayMap arraymap)
    {
        ensureNotDead();
        if(!flag && mLastPssState == mCurState && SystemClock.uptimeMillis() < mLastPssTime + 30000L)
            return;
        mLastPssState = mCurState;
        mLastPssTime = SystemClock.uptimeMillis();
        if(mCurState != -1)
        {
            mCommonProcess.mPssTable.mergeStats(mCurState, 1, l, l, l, l1, l1, l1);
            if(!mCommonProcess.mMultiPackage)
                return;
            if(arraymap != null)
            {
                for(int i = arraymap.size() - 1; i >= 0; i--)
                    pullFixedProc(arraymap, i).mPssTable.mergeStats(mCurState, 1, l, l, l, l1, l1, l1);

            }
        }
    }

    public void aggregatePss(ProcessStats.TotalMemoryUseCollection totalmemoryusecollection, long l)
    {
        PssAggr pssaggr = new PssAggr();
        PssAggr pssaggr1 = new PssAggr();
        PssAggr pssaggr2 = new PssAggr();
        boolean flag = false;
        int i = 0;
        while(i < mDurations.getKeyCount()) 
        {
            byte byte1 = SparseMappingTable.getIdFromKey(mDurations.getKeyAt(i));
            int j = byte1 % 14;
            long l1 = getPssSampleCount(byte1);
            if(l1 > 0L)
            {
                long l3 = getPssAverage(byte1);
                flag = true;
                if(j <= 2)
                    pssaggr.add(l3, l1);
                else
                if(j <= 8)
                    pssaggr1.add(l3, l1);
                else
                    pssaggr2.add(l3, l1);
            }
            i++;
        }
        if(!flag)
            return;
        flag = false;
        boolean flag2 = false;
        boolean flag1 = false;
        i = ((flag) ? 1 : 0);
        if(pssaggr.samples < 3L)
        {
            i = ((flag) ? 1 : 0);
            if(pssaggr1.samples > 0L)
            {
                i = 1;
                pssaggr.add(pssaggr1.pss, pssaggr1.samples);
            }
        }
        flag = flag2;
        if(pssaggr.samples < 3L)
        {
            flag = flag2;
            if(pssaggr2.samples > 0L)
            {
                flag = true;
                pssaggr.add(pssaggr2.pss, pssaggr2.samples);
            }
        }
        flag2 = flag1;
        if(pssaggr1.samples < 3L)
        {
            flag2 = flag1;
            if(pssaggr2.samples > 0L)
            {
                flag2 = true;
                pssaggr1.add(pssaggr2.pss, pssaggr2.samples);
            }
        }
        if(pssaggr1.samples < 3L && i ^ true && pssaggr.samples > 0L)
            pssaggr1.add(pssaggr.pss, pssaggr.samples);
        if(pssaggr2.samples < 3L && flag2 ^ true && pssaggr1.samples > 0L)
            pssaggr2.add(pssaggr1.pss, pssaggr1.samples);
        if(pssaggr2.samples < 3L && flag ^ true && pssaggr.samples > 0L)
            pssaggr2.add(pssaggr.pss, pssaggr.samples);
        i = 0;
        while(i < mDurations.getKeyCount()) 
        {
            int k = mDurations.getKeyAt(i);
            byte byte0 = SparseMappingTable.getIdFromKey(k);
            long l4 = mDurations.getValue(k);
            long l5 = l4;
            if(mCurState == byte0)
                l5 = l4 + (l - mStartTime);
            k = byte0 % 14;
            double ad[] = totalmemoryusecollection.processStateTime;
            ad[k] = ad[k] + l5;
            long l2 = getPssSampleCount(byte0);
            double d;
            if(l2 > 0L)
                l4 = getPssAverage(byte0);
            else
            if(k <= 2)
            {
                l2 = pssaggr.samples;
                l4 = pssaggr.pss;
            } else
            if(k <= 8)
            {
                l2 = pssaggr1.samples;
                l4 = pssaggr1.pss;
            } else
            {
                l2 = pssaggr2.samples;
                l4 = pssaggr2.pss;
            }
            d = ((double)totalmemoryusecollection.processStatePss[k] * (double)totalmemoryusecollection.processStateSamples[k] + (double)l4 * (double)l2) / (double)((long)totalmemoryusecollection.processStateSamples[k] + l2);
            totalmemoryusecollection.processStatePss[k] = (long)d;
            ad = totalmemoryusecollection.processStateSamples;
            ad[k] = (int)((long)ad[k] + l2);
            ad = totalmemoryusecollection.processStateWeight;
            ad[k] = ad[k] + (double)l4 * (double)l5;
            i++;
        }
    }

    public ProcessState clone(long l)
    {
        ProcessState processstate = new ProcessState(this, mPackage, mUid, mVersion, mName, l);
        processstate.mDurations.addDurations(mDurations);
        processstate.mPssTable.copyFrom(mPssTable, 7);
        processstate.mNumExcessiveCpu = mNumExcessiveCpu;
        processstate.mNumCachedKill = mNumCachedKill;
        processstate.mMinCachedKillPss = mMinCachedKillPss;
        processstate.mAvgCachedKillPss = mAvgCachedKillPss;
        processstate.mMaxCachedKillPss = mMaxCachedKillPss;
        processstate.mActive = mActive;
        processstate.mNumActiveServices = mNumActiveServices;
        processstate.mNumStartedServices = mNumStartedServices;
        return processstate;
    }

    public void commitStateTime(long l)
    {
        if(mCurState != -1)
        {
            long l1 = l - mStartTime;
            if(l1 > 0L)
                mDurations.addDuration(mCurState, l1);
        }
        mStartTime = l;
    }

    public void computeProcessData(ProcessStats.ProcessDataCollection processdatacollection, long l)
    {
        processdatacollection.totalTime = 0L;
        processdatacollection.maxUss = 0L;
        processdatacollection.avgUss = 0L;
        processdatacollection.minUss = 0L;
        processdatacollection.maxPss = 0L;
        processdatacollection.avgPss = 0L;
        processdatacollection.minPss = 0L;
        processdatacollection.numPss = 0L;
label0:
        for(int i = 0; i < processdatacollection.screenStates.length; i++)
        {
            int j = 0;
            do
            {
                if(j >= processdatacollection.memStates.length)
                    continue label0;
                int k = 0;
                while(k < processdatacollection.procStates.length) 
                {
                    int i1 = (processdatacollection.screenStates[i] + processdatacollection.memStates[j]) * 14 + processdatacollection.procStates[k];
                    processdatacollection.totalTime = processdatacollection.totalTime + getDuration(i1, l);
                    long l1 = getPssSampleCount(i1);
                    if(l1 > 0L)
                    {
                        long l2 = getPssMinimum(i1);
                        long l3 = getPssAverage(i1);
                        long l4 = getPssMaximum(i1);
                        long l5 = getPssUssMinimum(i1);
                        long l6 = getPssUssAverage(i1);
                        long l7 = getPssUssMaximum(i1);
                        if(processdatacollection.numPss == 0L)
                        {
                            processdatacollection.minPss = l2;
                            processdatacollection.avgPss = l3;
                            processdatacollection.maxPss = l4;
                            processdatacollection.minUss = l5;
                            processdatacollection.avgUss = l6;
                            processdatacollection.maxUss = l7;
                        } else
                        {
                            if(l2 < processdatacollection.minPss)
                                processdatacollection.minPss = l2;
                            processdatacollection.avgPss = (long)(((double)processdatacollection.avgPss * (double)processdatacollection.numPss + (double)l3 * (double)l1) / (double)(processdatacollection.numPss + l1));
                            if(l4 > processdatacollection.maxPss)
                                processdatacollection.maxPss = l4;
                            if(l5 < processdatacollection.minUss)
                                processdatacollection.minUss = l5;
                            processdatacollection.avgUss = (long)(((double)processdatacollection.avgUss * (double)processdatacollection.numPss + (double)l6 * (double)l1) / (double)(processdatacollection.numPss + l1));
                            if(l7 > processdatacollection.maxUss)
                                processdatacollection.maxUss = l7;
                        }
                        processdatacollection.numPss = processdatacollection.numPss + l1;
                    }
                    k++;
                }
                j++;
            } while(true);
        }

    }

    public long computeProcessTimeLocked(int ai[], int ai1[], int ai2[], long l)
    {
        long l1 = 0L;
        for(int i = 0; i < ai.length; i++)
        {
            for(int j = 0; j < ai1.length; j++)
            {
                for(int k = 0; k < ai2.length; k++)
                    l1 += getDuration((ai[i] + ai1[j]) * 14 + ai2[k], l);

            }

        }

        mTmpTotalTime = l1;
        return l1;
    }

    public void decActiveServices(String s)
    {
        if(mCommonProcess != this)
            mCommonProcess.decActiveServices(s);
        mNumActiveServices = mNumActiveServices - 1;
        if(mNumActiveServices < 0)
        {
            Slog.wtfStack("ProcessStats", (new StringBuilder()).append("Proc active services underrun: pkg=").append(mPackage).append(" uid=").append(mUid).append(" proc=").append(mName).append(" service=").append(s).toString());
            mNumActiveServices = 0;
        }
    }

    public void decStartedServices(int i, long l, String s)
    {
        if(mCommonProcess != this)
            mCommonProcess.decStartedServices(i, l, s);
        mNumStartedServices = mNumStartedServices - 1;
        if(mNumStartedServices != 0 || mCurState % 14 != 7) goto _L2; else goto _L1
_L1:
        setState(-1, l);
_L4:
        return;
_L2:
        if(mNumStartedServices < 0)
        {
            Slog.wtfStack("ProcessStats", (new StringBuilder()).append("Proc started services underrun: pkg=").append(mPackage).append(" uid=").append(mUid).append(" name=").append(mName).toString());
            mNumStartedServices = 0;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void dumpAllPssCheckin(PrintWriter printwriter)
    {
        int i = mPssTable.getKeyCount();
        for(int j = 0; j < i; j++)
        {
            int k = mPssTable.getKeyAt(j);
            byte byte0 = SparseMappingTable.getIdFromKey(k);
            printwriter.print(',');
            DumpUtils.printProcStateTag(printwriter, byte0);
            printwriter.print(':');
            printwriter.print(mPssTable.getValue(k, 0));
            printwriter.print(':');
            printwriter.print(mPssTable.getValue(k, 1));
            printwriter.print(':');
            printwriter.print(mPssTable.getValue(k, 2));
            printwriter.print(':');
            printwriter.print(mPssTable.getValue(k, 3));
            printwriter.print(':');
            printwriter.print(mPssTable.getValue(k, 4));
            printwriter.print(':');
            printwriter.print(mPssTable.getValue(k, 5));
            printwriter.print(':');
            printwriter.print(mPssTable.getValue(k, 6));
        }

    }

    public void dumpAllStateCheckin(PrintWriter printwriter, long l)
    {
        boolean flag = false;
        for(int i = 0; i < mDurations.getKeyCount(); i++)
        {
            int j = mDurations.getKeyAt(i);
            byte byte0 = SparseMappingTable.getIdFromKey(j);
            long l1 = mDurations.getValue(j);
            long l2 = l1;
            if(mCurState == byte0)
            {
                flag = true;
                l2 = l1 + (l - mStartTime);
            }
            DumpUtils.printProcStateTagAndValue(printwriter, byte0, l2);
        }

        if(!flag && mCurState != -1)
            DumpUtils.printProcStateTagAndValue(printwriter, mCurState, l - mStartTime);
    }

    public void dumpCsv(PrintWriter printwriter, boolean flag, int ai[], boolean flag1, int ai1[], boolean flag2, int ai2[], 
            long l)
    {
        int i;
        int j;
        int k;
        int i1;
        if(flag)
            i = ai.length;
        else
            i = 1;
        if(flag1)
            j = ai1.length;
        else
            j = 1;
        if(flag2)
            k = ai2.length;
        else
            k = 1;
        for(i1 = 0; i1 < i; i1++)
        {
            for(int j1 = 0; j1 < j; j1++)
            {
                for(int k1 = 0; k1 < k; k1++)
                {
                    int l1;
                    int i2;
                    int j2;
                    int k2;
                    int l2;
                    int i3;
                    long l3;
                    if(flag)
                        l1 = ai[i1];
                    else
                        l1 = 0;
                    if(flag1)
                        i2 = ai1[j1];
                    else
                        i2 = 0;
                    if(flag2)
                        j2 = ai2[k1];
                    else
                        j2 = 0;
                    if(flag)
                        k2 = 1;
                    else
                        k2 = ai.length;
                    if(flag1)
                        l2 = 1;
                    else
                        l2 = ai1.length;
                    if(flag2)
                        i3 = 1;
                    else
                        i3 = ai2.length;
                    l3 = 0L;
                    for(int j3 = 0; j3 < k2; j3++)
                    {
                        for(int k3 = 0; k3 < l2; k3++)
                        {
                            int i4 = 0;
                            while(i4 < i3) 
                            {
                                int j4;
                                int k4;
                                int l4;
                                if(flag)
                                    j4 = 0;
                                else
                                    j4 = ai[j3];
                                if(flag1)
                                    k4 = 0;
                                else
                                    k4 = ai1[k3];
                                if(flag2)
                                    l4 = 0;
                                else
                                    l4 = ai2[i4];
                                l3 += getDuration((l1 + j4 + i2 + k4) * 14 + j2 + l4, l);
                                i4++;
                            }
                        }

                    }

                    printwriter.print("\t");
                    printwriter.print(l3);
                }

            }

        }

    }

    public void dumpInternalLocked(PrintWriter printwriter, String s, boolean flag)
    {
        if(flag)
        {
            printwriter.print(s);
            printwriter.print("myID=");
            printwriter.print(Integer.toHexString(System.identityHashCode(this)));
            printwriter.print(" mCommonProcess=");
            printwriter.print(Integer.toHexString(System.identityHashCode(mCommonProcess)));
            printwriter.print(" mPackage=");
            printwriter.println(mPackage);
            if(mMultiPackage)
            {
                printwriter.print(s);
                printwriter.print("mMultiPackage=");
                printwriter.println(mMultiPackage);
            }
            if(this != mCommonProcess)
            {
                printwriter.print(s);
                printwriter.print("Common Proc: ");
                printwriter.print(mCommonProcess.mName);
                printwriter.print("/");
                printwriter.print(mCommonProcess.mUid);
                printwriter.print(" pkg=");
                printwriter.println(mCommonProcess.mPackage);
            }
        }
        if(mActive)
        {
            printwriter.print(s);
            printwriter.print("mActive=");
            printwriter.println(mActive);
        }
        if(mDead)
        {
            printwriter.print(s);
            printwriter.print("mDead=");
            printwriter.println(mDead);
        }
        if(mNumActiveServices != 0 || mNumStartedServices != 0)
        {
            printwriter.print(s);
            printwriter.print("mNumActiveServices=");
            printwriter.print(mNumActiveServices);
            printwriter.print(" mNumStartedServices=");
            printwriter.println(mNumStartedServices);
        }
    }

    public void dumpPackageProcCheckin(PrintWriter printwriter, String s, int i, int j, String s1, long l)
    {
        printwriter.print("pkgproc,");
        printwriter.print(s);
        printwriter.print(",");
        printwriter.print(i);
        printwriter.print(",");
        printwriter.print(j);
        printwriter.print(",");
        printwriter.print(DumpUtils.collapseString(s, s1));
        dumpAllStateCheckin(printwriter, l);
        printwriter.println();
        if(mPssTable.getKeyCount() > 0)
        {
            printwriter.print("pkgpss,");
            printwriter.print(s);
            printwriter.print(",");
            printwriter.print(i);
            printwriter.print(",");
            printwriter.print(j);
            printwriter.print(",");
            printwriter.print(DumpUtils.collapseString(s, s1));
            dumpAllPssCheckin(printwriter);
            printwriter.println();
        }
        if(mNumExcessiveCpu > 0 || mNumCachedKill > 0)
        {
            printwriter.print("pkgkills,");
            printwriter.print(s);
            printwriter.print(",");
            printwriter.print(i);
            printwriter.print(",");
            printwriter.print(j);
            printwriter.print(",");
            printwriter.print(DumpUtils.collapseString(s, s1));
            printwriter.print(",");
            printwriter.print("0");
            printwriter.print(",");
            printwriter.print(mNumExcessiveCpu);
            printwriter.print(",");
            printwriter.print(mNumCachedKill);
            printwriter.print(",");
            printwriter.print(mMinCachedKillPss);
            printwriter.print(":");
            printwriter.print(mAvgCachedKillPss);
            printwriter.print(":");
            printwriter.print(mMaxCachedKillPss);
            printwriter.println();
        }
    }

    public void dumpProcCheckin(PrintWriter printwriter, String s, int i, long l)
    {
        if(mDurations.getKeyCount() > 0)
        {
            printwriter.print("proc,");
            printwriter.print(s);
            printwriter.print(",");
            printwriter.print(i);
            dumpAllStateCheckin(printwriter, l);
            printwriter.println();
        }
        if(mPssTable.getKeyCount() > 0)
        {
            printwriter.print("pss,");
            printwriter.print(s);
            printwriter.print(",");
            printwriter.print(i);
            dumpAllPssCheckin(printwriter);
            printwriter.println();
        }
        if(mNumExcessiveCpu > 0 || mNumCachedKill > 0)
        {
            printwriter.print("kills,");
            printwriter.print(s);
            printwriter.print(",");
            printwriter.print(i);
            printwriter.print(",");
            printwriter.print("0");
            printwriter.print(",");
            printwriter.print(mNumExcessiveCpu);
            printwriter.print(",");
            printwriter.print(mNumCachedKill);
            printwriter.print(",");
            printwriter.print(mMinCachedKillPss);
            printwriter.print(":");
            printwriter.print(mAvgCachedKillPss);
            printwriter.print(":");
            printwriter.print(mMaxCachedKillPss);
            printwriter.println();
        }
    }

    public void dumpProcessState(PrintWriter printwriter, String s, int ai[], int ai1[], int ai2[], long l)
    {
        l = 0L;
        int i = -1;
        for(int j = 0; j < ai.length; j++)
        {
            int k = -1;
            for(int i1 = 0; i1 < ai1.length; i1++)
            {
                int j1 = 0;
                while(j1 < ai2.length) 
                {
                    int k1 = ai[j];
                    int l1 = ai1[i1];
                    int i2 = (k1 + l1) * 14 + ai2[j1];
                    long l2 = mDurations.getValueForId((byte)i2);
                    String s1 = "";
                    if(mCurState == i2)
                        s1 = " (running)";
                    int j2 = k;
                    i2 = i;
                    long l3 = l;
                    if(l2 != 0L)
                    {
                        printwriter.print(s);
                        i2 = i;
                        if(ai.length > 1)
                        {
                            if(i != k1)
                                i = k1;
                            else
                                i = -1;
                            DumpUtils.printScreenLabel(printwriter, i);
                            i2 = k1;
                        }
                        i = k;
                        if(ai1.length > 1)
                        {
                            if(k != l1)
                                k = l1;
                            else
                                k = -1;
                            DumpUtils.printMemLabel(printwriter, k, '/');
                            i = l1;
                        }
                        printwriter.print(DumpUtils.STATE_NAMES[ai2[j1]]);
                        printwriter.print(": ");
                        TimeUtils.formatDuration(l2, printwriter);
                        printwriter.println(s1);
                        l3 = l + l2;
                        j2 = i;
                    }
                    j1++;
                    k = j2;
                    i = i2;
                    l = l3;
                }
            }

        }

        if(l != 0L)
        {
            printwriter.print(s);
            if(ai.length > 1)
                DumpUtils.printScreenLabel(printwriter, -1);
            if(ai1.length > 1)
                DumpUtils.printMemLabel(printwriter, -1, '/');
            printwriter.print("TOTAL  : ");
            TimeUtils.formatDuration(l, printwriter);
            printwriter.println();
        }
    }

    public void dumpPss(PrintWriter printwriter, String s, int ai[], int ai1[], int ai2[])
    {
        int i = 0;
        int j = -1;
        for(int k = 0; k < ai.length; k++)
        {
            int l = -1;
            for(int i1 = 0; i1 < ai1.length; i1++)
            {
                int j1 = 0;
                while(j1 < ai2.length) 
                {
                    int k1 = ai[k];
                    int l1 = ai1[i1];
                    int i2 = (k1 + l1) * 14 + ai2[j1];
                    long l2 = getPssSampleCount(i2);
                    boolean flag = i;
                    int j2 = l;
                    int k2 = j;
                    if(l2 > 0L)
                    {
                        flag = i;
                        if(i == 0)
                        {
                            printwriter.print(s);
                            printwriter.print("PSS/USS (");
                            printwriter.print(mPssTable.getKeyCount());
                            printwriter.println(" entries):");
                            flag = true;
                        }
                        printwriter.print(s);
                        printwriter.print("  ");
                        i = j;
                        if(ai.length > 1)
                        {
                            if(j != k1)
                                j = k1;
                            else
                                j = -1;
                            DumpUtils.printScreenLabel(printwriter, j);
                            i = k1;
                        }
                        j = l;
                        if(ai1.length > 1)
                        {
                            if(l != l1)
                                l = l1;
                            else
                                l = -1;
                            DumpUtils.printMemLabel(printwriter, l, '/');
                            j = l1;
                        }
                        printwriter.print(DumpUtils.STATE_NAMES[ai2[j1]]);
                        printwriter.print(": ");
                        printwriter.print(l2);
                        printwriter.print(" samples ");
                        DebugUtils.printSizeValue(printwriter, getPssMinimum(i2) * 1024L);
                        printwriter.print(" ");
                        DebugUtils.printSizeValue(printwriter, getPssAverage(i2) * 1024L);
                        printwriter.print(" ");
                        DebugUtils.printSizeValue(printwriter, getPssMaximum(i2) * 1024L);
                        printwriter.print(" / ");
                        DebugUtils.printSizeValue(printwriter, getPssUssMinimum(i2) * 1024L);
                        printwriter.print(" ");
                        DebugUtils.printSizeValue(printwriter, getPssUssAverage(i2) * 1024L);
                        printwriter.print(" ");
                        DebugUtils.printSizeValue(printwriter, getPssUssMaximum(i2) * 1024L);
                        printwriter.println();
                        k2 = i;
                        j2 = j;
                    }
                    j1++;
                    i = ((flag) ? 1 : 0);
                    l = j2;
                    j = k2;
                }
            }

        }

        if(mNumExcessiveCpu != 0)
        {
            printwriter.print(s);
            printwriter.print("Killed for excessive CPU use: ");
            printwriter.print(mNumExcessiveCpu);
            printwriter.println(" times");
        }
        if(mNumCachedKill != 0)
        {
            printwriter.print(s);
            printwriter.print("Killed from cached state: ");
            printwriter.print(mNumCachedKill);
            printwriter.print(" times from pss ");
            DebugUtils.printSizeValue(printwriter, mMinCachedKillPss * 1024L);
            printwriter.print("-");
            DebugUtils.printSizeValue(printwriter, mAvgCachedKillPss * 1024L);
            printwriter.print("-");
            DebugUtils.printSizeValue(printwriter, mMaxCachedKillPss * 1024L);
            printwriter.println();
        }
    }

    public void dumpSummary(PrintWriter printwriter, String s, int ai[], int ai1[], int ai2[], long l, 
            long l1)
    {
        printwriter.print(s);
        printwriter.print("* ");
        printwriter.print(mName);
        printwriter.print(" / ");
        UserHandle.formatUid(printwriter, mUid);
        printwriter.print(" / v");
        printwriter.print(mVersion);
        printwriter.println(":");
        dumpProcessSummaryDetails(printwriter, s, "         TOTAL: ", ai, ai1, ai2, l, l1, true);
        dumpProcessSummaryDetails(printwriter, s, "    Persistent: ", ai, ai1, new int[] {
            0
        }, l, l1, true);
        dumpProcessSummaryDetails(printwriter, s, "           Top: ", ai, ai1, new int[] {
            1
        }, l, l1, true);
        dumpProcessSummaryDetails(printwriter, s, "        Imp Fg: ", ai, ai1, new int[] {
            2
        }, l, l1, true);
        dumpProcessSummaryDetails(printwriter, s, "        Imp Bg: ", ai, ai1, new int[] {
            3
        }, l, l1, true);
        dumpProcessSummaryDetails(printwriter, s, "        Backup: ", ai, ai1, new int[] {
            4
        }, l, l1, true);
        dumpProcessSummaryDetails(printwriter, s, "     Heavy Wgt: ", ai, ai1, new int[] {
            5
        }, l, l1, true);
        dumpProcessSummaryDetails(printwriter, s, "       Service: ", ai, ai1, new int[] {
            6
        }, l, l1, true);
        dumpProcessSummaryDetails(printwriter, s, "    Service Rs: ", ai, ai1, new int[] {
            7
        }, l, l1, true);
        dumpProcessSummaryDetails(printwriter, s, "      Receiver: ", ai, ai1, new int[] {
            8
        }, l, l1, true);
        dumpProcessSummaryDetails(printwriter, s, "        (Home): ", ai, ai1, new int[] {
            9
        }, l, l1, true);
        dumpProcessSummaryDetails(printwriter, s, "    (Last Act): ", ai, ai1, new int[] {
            10
        }, l, l1, true);
        dumpProcessSummaryDetails(printwriter, s, "      (Cached): ", ai, ai1, new int[] {
            11, 12, 13
        }, l, l1, true);
    }

    public ProcessState getCommonProcess()
    {
        return mCommonProcess;
    }

    public long getDuration(int i, long l)
    {
        long l1 = mDurations.getValueForId((byte)i);
        long l2 = l1;
        if(mCurState == i)
            l2 = l1 + (l - mStartTime);
        return l2;
    }

    public int getDurationsBucketCount()
    {
        return mDurations.getKeyCount();
    }

    public String getName()
    {
        return mName;
    }

    public String getPackage()
    {
        return mPackage;
    }

    public long getPssAverage(int i)
    {
        return mPssTable.getValueForId((byte)i, 2);
    }

    public long getPssMaximum(int i)
    {
        return mPssTable.getValueForId((byte)i, 3);
    }

    public long getPssMinimum(int i)
    {
        return mPssTable.getValueForId((byte)i, 1);
    }

    public long getPssSampleCount(int i)
    {
        return mPssTable.getValueForId((byte)i, 0);
    }

    public long getPssUssAverage(int i)
    {
        return mPssTable.getValueForId((byte)i, 5);
    }

    public long getPssUssMaximum(int i)
    {
        return mPssTable.getValueForId((byte)i, 6);
    }

    public long getPssUssMinimum(int i)
    {
        return mPssTable.getValueForId((byte)i, 4);
    }

    public int getUid()
    {
        return mUid;
    }

    public int getVersion()
    {
        return mVersion;
    }

    public boolean hasAnyData()
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(mDurations.getKeyCount() != 0) goto _L2; else goto _L1
_L1:
        if(mCurState == -1) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(mPssTable.getKeyCount() == 0)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void incActiveServices(String s)
    {
        if(mCommonProcess != this)
            mCommonProcess.incActiveServices(s);
        mNumActiveServices = mNumActiveServices + 1;
    }

    public void incStartedServices(int i, long l, String s)
    {
        if(mCommonProcess != this)
            mCommonProcess.incStartedServices(i, l, s);
        mNumStartedServices = mNumStartedServices + 1;
        if(mNumStartedServices == 1 && mCurState == -1)
            setState(i * 14 + 7, l);
    }

    public boolean isActive()
    {
        return mActive;
    }

    public boolean isInUse()
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(mActive) goto _L2; else goto _L1
_L1:
        if(mNumActiveServices <= 0) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(mNumStartedServices <= 0)
        {
            flag1 = flag;
            if(mCurState == -1)
                flag1 = false;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public boolean isMultiPackage()
    {
        return mMultiPackage;
    }

    public void makeActive()
    {
        ensureNotDead();
        mActive = true;
    }

    public void makeDead()
    {
        mDead = true;
    }

    public void makeInactive()
    {
        mActive = false;
    }

    public void makeStandalone()
    {
        mCommonProcess = this;
    }

    public ProcessState pullFixedProc(String s)
    {
        if(mMultiPackage)
        {
            Object obj = (SparseArray)mStats.mPackages.get(s, mUid);
            if(obj == null)
                throw new IllegalStateException((new StringBuilder()).append("Didn't find package ").append(s).append(" / ").append(mUid).toString());
            obj = (ProcessStats.PackageState)((SparseArray) (obj)).get(mVersion);
            if(obj == null)
                throw new IllegalStateException((new StringBuilder()).append("Didn't find package ").append(s).append(" / ").append(mUid).append(" vers ").append(mVersion).toString());
            obj = (ProcessState)((ProcessStats.PackageState) (obj)).mProcesses.get(mName);
            if(obj == null)
                throw new IllegalStateException((new StringBuilder()).append("Didn't create per-package process ").append(mName).append(" in pkg ").append(s).append(" / ").append(mUid).append(" vers ").append(mVersion).toString());
            else
                return ((ProcessState) (obj));
        } else
        {
            return this;
        }
    }

    public boolean readFromParcel(Parcel parcel, boolean flag)
    {
        boolean flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        if(flag)
            mMultiPackage = flag1;
        if(!mDurations.readFromParcel(parcel))
            return false;
        if(!mPssTable.readFromParcel(parcel))
            return false;
        parcel.readInt();
        mNumExcessiveCpu = parcel.readInt();
        mNumCachedKill = parcel.readInt();
        if(mNumCachedKill > 0)
        {
            mMinCachedKillPss = parcel.readLong();
            mAvgCachedKillPss = parcel.readLong();
            mMaxCachedKillPss = parcel.readLong();
        } else
        {
            mMaxCachedKillPss = 0L;
            mAvgCachedKillPss = 0L;
            mMinCachedKillPss = 0L;
        }
        return true;
    }

    public void reportCachedKill(ArrayMap arraymap, long l)
    {
        ensureNotDead();
        mCommonProcess.addCachedKill(1, l, l, l);
        if(!mCommonProcess.mMultiPackage)
            return;
        for(int i = arraymap.size() - 1; i >= 0; i--)
            pullFixedProc(arraymap, i).addCachedKill(1, l, l, l);

    }

    public void reportExcessiveCpu(ArrayMap arraymap)
    {
        ensureNotDead();
        ProcessState processstate = mCommonProcess;
        processstate.mNumExcessiveCpu = processstate.mNumExcessiveCpu + 1;
        if(!mCommonProcess.mMultiPackage)
            return;
        for(int i = arraymap.size() - 1; i >= 0; i--)
        {
            ProcessState processstate1 = pullFixedProc(arraymap, i);
            processstate1.mNumExcessiveCpu = processstate1.mNumExcessiveCpu + 1;
        }

    }

    public void resetSafely(long l)
    {
        mDurations.resetTable();
        mPssTable.resetTable();
        mStartTime = l;
        mLastPssState = -1;
        mLastPssTime = 0L;
        mNumExcessiveCpu = 0;
        mNumCachedKill = 0;
        mMaxCachedKillPss = 0L;
        mAvgCachedKillPss = 0L;
        mMinCachedKillPss = 0L;
    }

    public void setMultiPackage(boolean flag)
    {
        mMultiPackage = flag;
    }

    public void setState(int i, int j, long l, ArrayMap arraymap)
    {
        if(i < 0)
        {
            if(mNumStartedServices > 0)
                i = j * 14 + 7;
            else
                i = -1;
        } else
        {
            i = PROCESS_STATE_TO_STATE[i] + j * 14;
        }
        mCommonProcess.setState(i, l);
        if(!mCommonProcess.mMultiPackage)
            return;
        if(arraymap != null)
            for(j = arraymap.size() - 1; j >= 0; j--)
                pullFixedProc(arraymap, j).setState(i, l);

    }

    public void setState(int i, long l)
    {
        ensureNotDead();
        if(!mDead && mCurState != i)
        {
            commitStateTime(l);
            mCurState = i;
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("ProcessState{").append(Integer.toHexString(System.identityHashCode(this))).append(" ").append(mName).append("/").append(mUid).append(" pkg=").append(mPackage);
        if(mMultiPackage)
            stringbuilder.append(" (multi)");
        if(mCommonProcess != this)
            stringbuilder.append(" (sub)");
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, long l)
    {
        int i;
        if(mMultiPackage)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        mDurations.writeToParcel(parcel);
        mPssTable.writeToParcel(parcel);
        parcel.writeInt(0);
        parcel.writeInt(mNumExcessiveCpu);
        parcel.writeInt(mNumCachedKill);
        if(mNumCachedKill > 0)
        {
            parcel.writeLong(mMinCachedKillPss);
            parcel.writeLong(mAvgCachedKillPss);
            parcel.writeLong(mMaxCachedKillPss);
        }
    }

    public static final Comparator COMPARATOR = new Comparator() {

        public int compare(ProcessState processstate, ProcessState processstate1)
        {
            if(ProcessState._2D_get0(processstate) < ProcessState._2D_get0(processstate1))
                return -1;
            return ProcessState._2D_get0(processstate) <= ProcessState._2D_get0(processstate1) ? 0 : 1;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((ProcessState)obj, (ProcessState)obj1);
        }

    }
;
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_PARCEL = false;
    private static final int PROCESS_STATE_TO_STATE[] = {
        0, 0, 1, 2, 2, 1, 2, 3, 3, 4, 
        5, 6, 8, 9, 10, 11, 12, 13
    };
    private static final String TAG = "ProcessStats";
    private boolean mActive;
    private long mAvgCachedKillPss;
    private ProcessState mCommonProcess;
    private int mCurState;
    private boolean mDead;
    private final DurationsTable mDurations;
    private int mLastPssState;
    private long mLastPssTime;
    private long mMaxCachedKillPss;
    private long mMinCachedKillPss;
    private boolean mMultiPackage;
    private final String mName;
    private int mNumActiveServices;
    private int mNumCachedKill;
    private int mNumExcessiveCpu;
    private int mNumExcessiveWake;
    private int mNumStartedServices;
    private final String mPackage;
    private final PssTable mPssTable;
    private long mStartTime;
    private final ProcessStats mStats;
    private long mTmpTotalTime;
    private final int mUid;
    private final int mVersion;
    public ProcessState tmpFoundSubProc;
    public int tmpNumInUse;

}
