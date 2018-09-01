// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app.procstats;

import android.os.*;
import android.text.format.DateFormat;
import android.util.*;
import com.android.internal.app.ProcessMap;
import dalvik.system.VMRuntime;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.android.internal.app.procstats:
//            SparseMappingTable, SysMemUsageTable, ProcessState, ServiceState, 
//            DumpUtils

public final class ProcessStats
    implements Parcelable
{
    public static final class PackageState
    {

        public final String mPackageName;
        public final ArrayMap mProcesses = new ArrayMap();
        public final ArrayMap mServices = new ArrayMap();
        public final int mUid;

        public PackageState(String s, int i)
        {
            mUid = i;
            mPackageName = s;
        }
    }

    public static final class ProcessDataCollection
    {

        void print(PrintWriter printwriter, long l, boolean flag)
        {
            if(totalTime > l)
                printwriter.print("*");
            DumpUtils.printPercent(printwriter, (double)totalTime / (double)l);
            if(numPss > 0L)
            {
                printwriter.print(" (");
                DebugUtils.printSizeValue(printwriter, minPss * 1024L);
                printwriter.print("-");
                DebugUtils.printSizeValue(printwriter, avgPss * 1024L);
                printwriter.print("-");
                DebugUtils.printSizeValue(printwriter, maxPss * 1024L);
                printwriter.print("/");
                DebugUtils.printSizeValue(printwriter, minUss * 1024L);
                printwriter.print("-");
                DebugUtils.printSizeValue(printwriter, avgUss * 1024L);
                printwriter.print("-");
                DebugUtils.printSizeValue(printwriter, maxUss * 1024L);
                if(flag)
                {
                    printwriter.print(" over ");
                    printwriter.print(numPss);
                }
                printwriter.print(")");
            }
        }

        public long avgPss;
        public long avgUss;
        public long maxPss;
        public long maxUss;
        final int memStates[];
        public long minPss;
        public long minUss;
        public long numPss;
        final int procStates[];
        final int screenStates[];
        public long totalTime;

        public ProcessDataCollection(int ai[], int ai1[], int ai2[])
        {
            screenStates = ai;
            memStates = ai1;
            procStates = ai2;
        }
    }

    public static final class ProcessStateHolder
    {

        public final int appVersion;
        public ProcessState state;

        public ProcessStateHolder(int i)
        {
            appVersion = i;
        }
    }

    public static class TotalMemoryUseCollection
    {

        public boolean hasSwappedOutPss;
        final int memStates[];
        public long processStatePss[];
        public int processStateSamples[];
        public long processStateTime[];
        public double processStateWeight[];
        final int screenStates[];
        public double sysMemCachedWeight;
        public double sysMemFreeWeight;
        public double sysMemKernelWeight;
        public double sysMemNativeWeight;
        public int sysMemSamples;
        public long sysMemUsage[];
        public double sysMemZRamWeight;
        public long totalTime;

        public TotalMemoryUseCollection(int ai[], int ai1[])
        {
            processStatePss = new long[14];
            processStateWeight = new double[14];
            processStateTime = new long[14];
            processStateSamples = new int[14];
            sysMemUsage = new long[16];
            screenStates = ai;
            memStates = ai1;
        }
    }


    public ProcessStats(Parcel parcel)
    {
        mPackages = new ProcessMap();
        mProcesses = new ProcessMap();
        mMemFactorDurations = new long[8];
        mMemFactor = -1;
        mTableData = new SparseMappingTable();
        mSysMemUsageArgs = new long[16];
        mSysMemUsage = new SysMemUsageTable(mTableData);
        mPageTypeZones = new ArrayList();
        mPageTypeLabels = new ArrayList();
        mPageTypeSizes = new ArrayList();
        reset();
        readFromParcel(parcel);
    }

    public ProcessStats(boolean flag)
    {
        mPackages = new ProcessMap();
        mProcesses = new ProcessMap();
        mMemFactorDurations = new long[8];
        mMemFactor = -1;
        mTableData = new SparseMappingTable();
        mSysMemUsageArgs = new long[16];
        mSysMemUsage = new SysMemUsageTable(mTableData);
        mPageTypeZones = new ArrayList();
        mPageTypeLabels = new ArrayList();
        mPageTypeSizes = new ArrayList();
        mRunning = flag;
        reset();
        if(flag)
        {
            android.os.Debug.MemoryInfo memoryinfo = new android.os.Debug.MemoryInfo();
            Debug.getMemoryInfo(Process.myPid(), memoryinfo);
            mHasSwappedOutPss = memoryinfo.hasSwappedOutPss();
        }
    }

    private void buildTimePeriodStartClockStr()
    {
        mTimePeriodStartClockStr = DateFormat.format("yyyy-MM-dd-HH-mm-ss", mTimePeriodStartClock).toString();
    }

    private void dumpFragmentationLocked(PrintWriter printwriter)
    {
        printwriter.println();
        printwriter.println("Available pages by page size:");
        int i = mPageTypeLabels.size();
        for(int j = 0; j < i; j++)
        {
            printwriter.format("Zone %3d  %14s ", new Object[] {
                mPageTypeZones.get(j), mPageTypeLabels.get(j)
            });
            int ai[] = (int[])mPageTypeSizes.get(j);
            int k;
            int l;
            if(ai == null)
                k = 0;
            else
                k = ai.length;
            for(l = 0; l < k; l++)
                printwriter.format("%6d", new Object[] {
                    Integer.valueOf(ai[l])
                });

            printwriter.println();
        }

    }

    private boolean readCheckedInt(Parcel parcel, int i, String s)
    {
        int j = parcel.readInt();
        if(j != i)
        {
            mReadError = (new StringBuilder()).append("bad ").append(s).append(": ").append(j).toString();
            return false;
        } else
        {
            return true;
        }
    }

    private String readCommonString(Parcel parcel, int i)
    {
        if(i <= 9)
            return parcel.readString();
        i = parcel.readInt();
        if(i >= 0)
            return (String)mIndexToCommonString.get(i);
        i = i;
        parcel = parcel.readString();
        for(; mIndexToCommonString.size() <= i; mIndexToCommonString.add(null));
        mIndexToCommonString.set(i, parcel);
        return parcel;
    }

    private void readCompactedLongArray(Parcel parcel, int i, long al[], int j)
    {
        if(i <= 10)
        {
            parcel.readLongArray(al);
            return;
        }
        int k = al.length;
        if(j > k)
            throw new RuntimeException((new StringBuilder()).append("bad array lengths: got ").append(j).append(" array is ").append(k).toString());
        i = 0;
        int l;
        do
        {
            l = i;
            if(i >= j)
                break;
            l = parcel.readInt();
            if(l >= 0)
            {
                al[i] = l;
            } else
            {
                int i1 = parcel.readInt();
                al[i] = (long)l << 32 | (long)i1;
            }
            i++;
        } while(true);
        for(; l < k; l++)
            al[l] = 0L;

    }

    static byte[] readFully(InputStream inputstream, int ai[])
        throws IOException
    {
        boolean flag = false;
        int j = inputstream.available();
        byte abyte0[];
        if(j > 0)
            j++;
        else
            j = 16384;
        abyte0 = new byte[j];
        j = ((flag) ? 1 : 0);
        do
        {
            int i;
            do
            {
                i = inputstream.read(abyte0, j, abyte0.length - j);
                if(i < 0)
                {
                    ai[0] = j;
                    return abyte0;
                }
                i = j + i;
                j = i;
            } while(i < abyte0.length);
            byte abyte1[] = new byte[i + 16384];
            System.arraycopy(abyte0, 0, abyte1, 0, i);
            abyte0 = abyte1;
            j = i;
        } while(true);
    }

    private void resetCommon()
    {
        mTimePeriodStartClock = System.currentTimeMillis();
        buildTimePeriodStartClockStr();
        long l = SystemClock.elapsedRealtime();
        mTimePeriodEndRealtime = l;
        mTimePeriodStartRealtime = l;
        l = SystemClock.uptimeMillis();
        mTimePeriodEndUptime = l;
        mTimePeriodStartUptime = l;
        mTableData.reset();
        Arrays.fill(mMemFactorDurations, 0L);
        mSysMemUsage.resetTable();
        mStartTime = 0L;
        mReadError = null;
        mFlags = 0;
        evaluateSystemProperties(true);
        updateFragmentation();
    }

    private static int[] splitAndParseNumbers(String s)
    {
        boolean flag = false;
        int i = 0;
        int j = s.length();
        int k = 0;
        while(k < j) 
        {
            char c = s.charAt(k);
            int i1;
            if(c >= '0' && c <= '9')
            {
                i1 = i;
                c = flag;
                if(!flag)
                {
                    c = '\001';
                    i1 = i + 1;
                }
            } else
            {
                c = '\0';
                i1 = i;
            }
            k++;
            i = i1;
            flag = c;
        }
        int ai[] = new int[i];
        k = 0;
        int j1 = 0;
        int l = 0;
        while(j1 < j) 
        {
            char c1 = s.charAt(j1);
            if(c1 >= '0' && c1 <= '9')
            {
                if(!flag)
                {
                    flag = true;
                    k = c1 - 48;
                } else
                {
                    k = k * 10 + (c1 - 48);
                }
            } else
            if(flag)
            {
                flag = false;
                int k1 = l + 1;
                ai[l] = k;
                l = k1;
            }
            j1++;
        }
        if(i > 0)
            ai[i - 1] = k;
        return ai;
    }

    private void writeCommonString(Parcel parcel, String s)
    {
        Integer integer = (Integer)mCommonStringToIndex.get(s);
        if(integer != null)
        {
            parcel.writeInt(integer.intValue());
            return;
        } else
        {
            Integer integer1 = Integer.valueOf(mCommonStringToIndex.size());
            mCommonStringToIndex.put(s, integer1);
            parcel.writeInt(integer1.intValue());
            parcel.writeString(s);
            return;
        }
    }

    private void writeCompactedLongArray(Parcel parcel, long al[], int i)
    {
        int j = 0;
        while(j < i) 
        {
            long l = al[j];
            long l1 = l;
            if(l < 0L)
            {
                Slog.w("ProcessStats", (new StringBuilder()).append("Time val negative: ").append(l).toString());
                l1 = 0L;
            }
            if(l1 <= 0x7fffffffL)
            {
                parcel.writeInt((int)l1);
            } else
            {
                int k = (int)(l1 >> 32 & 0x7fffffffL);
                int i1 = (int)(0xffffffffL & l1);
                parcel.writeInt(k);
                parcel.writeInt(i1);
            }
            j++;
        }
    }

    public void add(ProcessStats processstats)
    {
        ArrayMap arraymap = processstats.mPackages.getMap();
        for(int i = 0; i < arraymap.size(); i++)
        {
            String s = (String)arraymap.keyAt(i);
            SparseArray sparsearray1 = (SparseArray)arraymap.valueAt(i);
            for(int l = 0; l < sparsearray1.size(); l++)
            {
                int j1 = sparsearray1.keyAt(l);
                SparseArray sparsearray2 = (SparseArray)sparsearray1.valueAt(l);
                for(int k1 = 0; k1 < sparsearray2.size(); k1++)
                {
                    int i2 = sparsearray2.keyAt(k1);
                    PackageState packagestate = (PackageState)sparsearray2.valueAt(k1);
                    int j2 = packagestate.mProcesses.size();
                    int k2 = packagestate.mServices.size();
                    for(int l2 = 0; l2 < j2; l2++)
                    {
                        ProcessState processstate1 = (ProcessState)packagestate.mProcesses.valueAt(l2);
                        if(processstate1.getCommonProcess() == processstate1)
                            continue;
                        ProcessState processstate2 = getProcessStateLocked(s, j1, i2, processstate1.getName());
                        ProcessState processstate5 = processstate2;
                        if(processstate2.getCommonProcess() == processstate2)
                        {
                            processstate2.setMultiPackage(true);
                            long l3 = SystemClock.uptimeMillis();
                            PackageState packagestate1 = getPackageStateLocked(s, j1, i2);
                            processstate5 = processstate2.clone(l3);
                            packagestate1.mProcesses.put(processstate5.getName(), processstate5);
                        }
                        processstate5.add(processstate1);
                    }

                    for(int i3 = 0; i3 < k2; i3++)
                    {
                        ServiceState servicestate = (ServiceState)packagestate.mServices.valueAt(i3);
                        getServiceStateLocked(s, j1, i2, servicestate.getProcessName(), servicestate.getName()).add(servicestate);
                    }

                }

            }

        }

        ArrayMap arraymap1 = processstats.mProcesses.getMap();
        for(int j = 0; j < arraymap1.size(); j++)
        {
            SparseArray sparsearray = (SparseArray)arraymap1.valueAt(j);
            for(int i1 = 0; i1 < sparsearray.size(); i1++)
            {
                int l1 = sparsearray.keyAt(i1);
                ProcessState processstate = (ProcessState)sparsearray.valueAt(i1);
                String s1 = processstate.getName();
                Object obj = processstate.getPackage();
                int j3 = processstate.getVersion();
                ProcessState processstate3 = (ProcessState)mProcesses.get(s1, l1);
                ProcessState processstate6 = processstate3;
                if(processstate3 == null)
                {
                    ProcessState processstate4 = new ProcessState(this, ((String) (obj)), l1, j3, s1);
                    mProcesses.put(s1, l1, processstate4);
                    obj = getPackageStateLocked(((String) (obj)), l1, j3);
                    processstate6 = processstate4;
                    if(!((PackageState) (obj)).mProcesses.containsKey(s1))
                    {
                        ((PackageState) (obj)).mProcesses.put(s1, processstate4);
                        processstate6 = processstate4;
                    }
                }
                processstate6.add(processstate);
            }

        }

        for(int k = 0; k < 8; k++)
        {
            long al[] = mMemFactorDurations;
            al[k] = al[k] + processstats.mMemFactorDurations[k];
        }

        mSysMemUsage.mergeStats(processstats.mSysMemUsage);
        if(processstats.mTimePeriodStartClock < mTimePeriodStartClock)
        {
            mTimePeriodStartClock = processstats.mTimePeriodStartClock;
            mTimePeriodStartClockStr = processstats.mTimePeriodStartClockStr;
        }
        mTimePeriodEndRealtime = mTimePeriodEndRealtime + (processstats.mTimePeriodEndRealtime - processstats.mTimePeriodStartRealtime);
        mTimePeriodEndUptime = mTimePeriodEndUptime + (processstats.mTimePeriodEndUptime - processstats.mTimePeriodStartUptime);
        mHasSwappedOutPss = mHasSwappedOutPss | processstats.mHasSwappedOutPss;
    }

    public void addSysMemUsage(long l, long l1, long l2, long l3, long l4)
    {
        if(mMemFactor != -1)
        {
            int i = mMemFactor;
            mSysMemUsageArgs[0] = 1L;
            for(int j = 0; j < 3; j++)
            {
                mSysMemUsageArgs[j + 1] = l;
                mSysMemUsageArgs[j + 4] = l1;
                mSysMemUsageArgs[j + 7] = l2;
                mSysMemUsageArgs[j + 10] = l3;
                mSysMemUsageArgs[j + 13] = l4;
            }

            mSysMemUsage.mergeStats(i * 14, mSysMemUsageArgs, 0);
        }
    }

    public ArrayList collectProcessesLocked(int ai[], int ai1[], int ai2[], int ai3[], long l, String s, 
            boolean flag)
    {
        ArraySet arrayset;
        ArrayMap arraymap;
        int i;
        arrayset = new ArraySet();
        arraymap = mPackages.getMap();
        i = 0;
_L8:
        if(i >= arraymap.size()) goto _L2; else goto _L1
_L1:
        String s1;
        SparseArray sparsearray;
        int k;
        s1 = (String)arraymap.keyAt(i);
        sparsearray = (SparseArray)arraymap.valueAt(i);
        k = 0;
_L7:
        SparseArray sparsearray1;
        int i1;
        int j1;
        if(k >= sparsearray.size())
            continue; /* Loop/switch isn't completed */
        sparsearray1 = (SparseArray)sparsearray.valueAt(k);
        i1 = sparsearray1.size();
        j1 = 0;
_L5:
        if(j1 >= i1) goto _L4; else goto _L3
_L3:
        ProcessState processstate;
        PackageState packagestate = (PackageState)sparsearray1.valueAt(j1);
        int k1 = packagestate.mProcesses.size();
        boolean flag1;
        int l1;
        if(s != null)
            flag1 = s.equals(s1);
        else
            flag1 = true;
        l1 = 0;
        if(l1 >= k1)
            continue; /* Loop/switch isn't completed */
        processstate = (ProcessState)packagestate.mProcesses.valueAt(l1);
        break MISSING_BLOCK_LABEL_158;
        if((flag1 || !(s.equals(processstate.getName()) ^ true)) && (!flag || !(processstate.isInUse() ^ true)))
            arrayset.add(processstate.getCommonProcess());
        l1++;
        if(false)
            continue; /* Loop/switch isn't completed */
        else
            break MISSING_BLOCK_LABEL_136;
        j1++;
          goto _L5
_L4:
        k++;
        if(true) goto _L7; else goto _L6
_L6:
        i++;
          goto _L8
_L2:
        ArrayList arraylist = new ArrayList(arrayset.size());
        for(int j = 0; j < arrayset.size(); j++)
        {
            s = (ProcessState)arrayset.valueAt(j);
            if(s.computeProcessTimeLocked(ai, ai1, ai2, l) <= 0L)
                continue;
            arraylist.add(s);
            if(ai2 != ai3)
                s.computeProcessTimeLocked(ai, ai1, ai3, l);
        }

        Collections.sort(arraylist, ProcessState.COMPARATOR);
        return arraylist;
    }

    public void computeTotalMemoryUse(TotalMemoryUseCollection totalmemoryusecollection, long l)
    {
        totalmemoryusecollection.totalTime = 0L;
        for(int i = 0; i < 14; i++)
        {
            totalmemoryusecollection.processStateWeight[i] = 0.0D;
            totalmemoryusecollection.processStatePss[i] = 0L;
            totalmemoryusecollection.processStateTime[i] = 0L;
            totalmemoryusecollection.processStateSamples[i] = 0;
        }

        for(int j = 0; j < 16; j++)
            totalmemoryusecollection.sysMemUsage[j] = 0L;

        totalmemoryusecollection.sysMemCachedWeight = 0.0D;
        totalmemoryusecollection.sysMemFreeWeight = 0.0D;
        totalmemoryusecollection.sysMemZRamWeight = 0.0D;
        totalmemoryusecollection.sysMemKernelWeight = 0.0D;
        totalmemoryusecollection.sysMemNativeWeight = 0.0D;
        totalmemoryusecollection.sysMemSamples = 0;
        long al[] = mSysMemUsage.getTotalMemUsage();
        for(int k = 0; k < totalmemoryusecollection.screenStates.length; k++)
        {
            for(int j1 = 0; j1 < totalmemoryusecollection.memStates.length; j1++)
            {
                int l1 = totalmemoryusecollection.screenStates[k] + totalmemoryusecollection.memStates[j1];
                long l2 = mMemFactorDurations[l1];
                long l3 = l2;
                if(mMemFactor == l1)
                    l3 = l2 + (l - mStartTime);
                totalmemoryusecollection.totalTime = totalmemoryusecollection.totalTime + l3;
                int i2 = mSysMemUsage.getKey((byte)(l1 * 14));
                long al1[] = al;
                boolean flag = false;
                l1 = ((flag) ? 1 : 0);
                long al2[] = al1;
                if(i2 != -1)
                {
                    long al3[] = mSysMemUsage.getArrayForKey(i2);
                    i2 = SparseMappingTable.getIndexFromKey(i2);
                    l1 = ((flag) ? 1 : 0);
                    al2 = al1;
                    if(al3[i2 + 0] >= 3L)
                    {
                        SysMemUsageTable.mergeSysMemUsage(totalmemoryusecollection.sysMemUsage, 0, al, 0);
                        al2 = al3;
                        l1 = i2;
                    }
                }
                totalmemoryusecollection.sysMemCachedWeight = totalmemoryusecollection.sysMemCachedWeight + (double)al2[l1 + 2] * (double)l3;
                totalmemoryusecollection.sysMemFreeWeight = totalmemoryusecollection.sysMemFreeWeight + (double)al2[l1 + 5] * (double)l3;
                totalmemoryusecollection.sysMemZRamWeight = totalmemoryusecollection.sysMemZRamWeight + (double)al2[l1 + 8] * (double)l3;
                totalmemoryusecollection.sysMemKernelWeight = totalmemoryusecollection.sysMemKernelWeight + (double)al2[l1 + 11] * (double)l3;
                totalmemoryusecollection.sysMemNativeWeight = totalmemoryusecollection.sysMemNativeWeight + (double)al2[l1 + 14] * (double)l3;
                totalmemoryusecollection.sysMemSamples = (int)((long)totalmemoryusecollection.sysMemSamples + al2[l1 + 0]);
            }

        }

        totalmemoryusecollection.hasSwappedOutPss = mHasSwappedOutPss;
        ArrayMap arraymap = mProcesses.getMap();
        for(int i1 = 0; i1 < arraymap.size(); i1++)
        {
            SparseArray sparsearray = (SparseArray)arraymap.valueAt(i1);
            for(int k1 = 0; k1 < sparsearray.size(); k1++)
                ((ProcessState)sparsearray.valueAt(k1)).aggregatePss(totalmemoryusecollection, l);

        }

    }

    public int describeContents()
    {
        return 0;
    }

    public void dumpCheckinLocked(PrintWriter printwriter, String s)
    {
        long l = SystemClock.uptimeMillis();
        ArrayMap arraymap = mPackages.getMap();
        printwriter.println("vers,5");
        printwriter.print("period,");
        printwriter.print(mTimePeriodStartClockStr);
        printwriter.print(",");
        printwriter.print(mTimePeriodStartRealtime);
        printwriter.print(",");
        long l1;
        boolean flag;
        int i;
        if(mRunning)
            l1 = SystemClock.elapsedRealtime();
        else
            l1 = mTimePeriodEndRealtime;
        printwriter.print(l1);
        flag = true;
        if((mFlags & 2) != 0)
        {
            printwriter.print(",shutdown");
            flag = false;
        }
        if((mFlags & 4) != 0)
        {
            printwriter.print(",sysprops");
            flag = false;
        }
        if((mFlags & 1) != 0)
        {
            printwriter.print(",complete");
            flag = false;
        }
        if(flag)
            printwriter.print(",partial");
        if(mHasSwappedOutPss)
            printwriter.print(",swapped-out-pss");
        printwriter.println();
        printwriter.print("config,");
        printwriter.println(mRuntime);
        i = 0;
        while(i < arraymap.size()) 
        {
            String s1 = (String)arraymap.keyAt(i);
            if(s == null || !(s.equals(s1) ^ true))
            {
                SparseArray sparsearray = (SparseArray)arraymap.valueAt(i);
                int k1 = 0;
                while(k1 < sparsearray.size()) 
                {
                    int l2 = sparsearray.keyAt(k1);
                    SparseArray sparsearray1 = (SparseArray)sparsearray.valueAt(k1);
                    for(int i3 = 0; i3 < sparsearray1.size(); i3++)
                    {
                        int i4 = sparsearray1.keyAt(i3);
                        PackageState packagestate = (PackageState)sparsearray1.valueAt(i3);
                        int j4 = packagestate.mProcesses.size();
                        int k4 = packagestate.mServices.size();
                        for(int l4 = 0; l4 < j4; l4++)
                            ((ProcessState)packagestate.mProcesses.valueAt(l4)).dumpPackageProcCheckin(printwriter, s1, l2, i4, (String)packagestate.mProcesses.keyAt(l4), l);

                        for(int i5 = 0; i5 < k4; i5++)
                        {
                            String s3 = DumpUtils.collapseString(s1, (String)packagestate.mServices.keyAt(i5));
                            ((ServiceState)packagestate.mServices.valueAt(i5)).dumpTimesCheckin(printwriter, s1, l2, i4, s3, l);
                        }

                    }

                    k1++;
                }
            }
            i++;
        }
        ArrayMap arraymap1 = mProcesses.getMap();
        for(int j = 0; j < arraymap1.size(); j++)
        {
            String s2 = (String)arraymap1.keyAt(j);
            s = (SparseArray)arraymap1.valueAt(j);
            for(int i2 = 0; i2 < s.size(); i2++)
            {
                int j3 = s.keyAt(i2);
                ((ProcessState)s.valueAt(i2)).dumpProcCheckin(printwriter, s2, j3, l);
            }

        }

        printwriter.print("total");
        DumpUtils.dumpAdjTimesCheckin(printwriter, ",", mMemFactorDurations, mMemFactor, mStartTime, l);
        printwriter.println();
        int k3 = mSysMemUsage.getKeyCount();
        if(k3 > 0)
        {
            printwriter.print("sysmemusage");
            for(int k = 0; k < k3; k++)
            {
                int j5 = mSysMemUsage.getKeyAt(k);
                byte byte0 = SparseMappingTable.getIdFromKey(j5);
                printwriter.print(",");
                DumpUtils.printProcStateTag(printwriter, byte0);
                for(int j2 = 0; j2 < 16; j2++)
                {
                    if(j2 > 1)
                        printwriter.print(":");
                    printwriter.print(mSysMemUsage.getValue(j5, j2));
                }

            }

        }
        printwriter.println();
        s = new TotalMemoryUseCollection(ALL_SCREEN_ADJ, ALL_MEM_ADJ);
        computeTotalMemoryUse(s, l);
        printwriter.print("weights,");
        printwriter.print(((TotalMemoryUseCollection) (s)).totalTime);
        printwriter.print(",");
        printwriter.print(((TotalMemoryUseCollection) (s)).sysMemCachedWeight);
        printwriter.print(":");
        printwriter.print(((TotalMemoryUseCollection) (s)).sysMemSamples);
        printwriter.print(",");
        printwriter.print(((TotalMemoryUseCollection) (s)).sysMemFreeWeight);
        printwriter.print(":");
        printwriter.print(((TotalMemoryUseCollection) (s)).sysMemSamples);
        printwriter.print(",");
        printwriter.print(((TotalMemoryUseCollection) (s)).sysMemZRamWeight);
        printwriter.print(":");
        printwriter.print(((TotalMemoryUseCollection) (s)).sysMemSamples);
        printwriter.print(",");
        printwriter.print(((TotalMemoryUseCollection) (s)).sysMemKernelWeight);
        printwriter.print(":");
        printwriter.print(((TotalMemoryUseCollection) (s)).sysMemSamples);
        printwriter.print(",");
        printwriter.print(((TotalMemoryUseCollection) (s)).sysMemNativeWeight);
        printwriter.print(":");
        printwriter.print(((TotalMemoryUseCollection) (s)).sysMemSamples);
        for(int i1 = 0; i1 < 14; i1++)
        {
            printwriter.print(",");
            printwriter.print(((TotalMemoryUseCollection) (s)).processStateWeight[i1]);
            printwriter.print(":");
            printwriter.print(((TotalMemoryUseCollection) (s)).processStateSamples[i1]);
        }

        printwriter.println();
        int k5 = mPageTypeLabels.size();
        for(int j1 = 0; j1 < k5; j1++)
        {
            printwriter.print("availablepages,");
            printwriter.print((String)mPageTypeLabels.get(j1));
            printwriter.print(",");
            printwriter.print(mPageTypeZones.get(j1));
            printwriter.print(",");
            s = (int[])mPageTypeSizes.get(j1);
            int k2;
            int l3;
            if(s == null)
                k2 = 0;
            else
                k2 = s.length;
            for(l3 = 0; l3 < k2; l3++)
            {
                if(l3 != 0)
                    printwriter.print(",");
                printwriter.print(s[l3]);
            }

            printwriter.println();
        }

    }

    void dumpFilteredSummaryLocked(PrintWriter printwriter, String s, String s1, int ai[], int ai1[], int ai2[], int ai3[], 
            long l, long l1, String s2, boolean flag)
    {
        ai2 = collectProcessesLocked(ai, ai1, ai2, ai3, l, s2, flag);
        if(ai2.size() > 0)
        {
            if(s != null)
            {
                printwriter.println();
                printwriter.println(s);
            }
            DumpUtils.dumpProcessSummaryLocked(printwriter, s1, ai2, ai, ai1, ai3, l, l1);
        }
    }

    public void dumpLocked(PrintWriter printwriter, String s, long l, boolean flag, boolean flag1, boolean flag2)
    {
        long l1;
        int i;
        Object obj;
        int j;
        int k;
        l1 = DumpUtils.dumpSingleTime(null, null, mMemFactorDurations, mMemFactor, mStartTime, l);
        i = 0;
        if(mSysMemUsage.getKeyCount() > 0)
        {
            printwriter.println("System memory usage:");
            mSysMemUsage.dump(printwriter, "  ", ALL_SCREEN_ADJ, ALL_MEM_ADJ);
            i = 1;
        }
        obj = mPackages.getMap();
        j = 0;
        k = 0;
_L16:
        if(k >= ((ArrayMap) (obj)).size()) goto _L2; else goto _L1
_L1:
        Object obj1;
        SparseArray sparsearray;
        int i1;
        int j1;
        obj1 = (String)((ArrayMap) (obj)).keyAt(k);
        sparsearray = (SparseArray)((ArrayMap) (obj)).valueAt(k);
        i1 = 0;
        j1 = i;
        i = j;
_L15:
        int k1;
        Object obj2;
        int i2;
        if(i1 >= sparsearray.size())
            break; /* Loop/switch isn't completed */
        k1 = sparsearray.keyAt(i1);
        obj2 = (SparseArray)sparsearray.valueAt(i1);
        i2 = 0;
_L10:
        int j2;
        Object obj3;
        int k2;
        int l2;
        boolean flag3;
        int j3;
        if(i2 >= ((SparseArray) (obj2)).size())
            break MISSING_BLOCK_LABEL_922;
        j2 = ((SparseArray) (obj2)).keyAt(i2);
        obj3 = (PackageState)((SparseArray) (obj2)).valueAt(i2);
        k2 = ((PackageState) (obj3)).mProcesses.size();
        l2 = ((PackageState) (obj3)).mServices.size();
        boolean flag4;
        if(s != null)
            flag3 = s.equals(obj1);
        else
            flag3 = true;
        if(flag3) goto _L4; else goto _L3
_L3:
        flag4 = false;
        j3 = 0;
_L11:
        j = ((flag4) ? 1 : 0);
        if(j3 >= k2) goto _L6; else goto _L5
_L5:
        if(!s.equals(((ProcessState)((PackageState) (obj3)).mProcesses.valueAt(j3)).getName())) goto _L8; else goto _L7
_L7:
        j = 1;
_L6:
        if(j != 0) goto _L4; else goto _L9
_L9:
        i2++;
          goto _L10
_L8:
        j3++;
          goto _L11
_L4:
        int i3;
label0:
        {
label1:
            {
                if(k2 <= 0)
                {
                    j3 = i;
                    j = j1;
                    if(l2 <= 0)
                        break label1;
                }
                j3 = i;
                j = j1;
                if(i == 0)
                {
                    if(j1 != 0)
                        printwriter.println();
                    printwriter.println("Per-Package Stats:");
                    j3 = 1;
                    j = 1;
                }
                printwriter.print("  * ");
                printwriter.print(((String) (obj1)));
                printwriter.print(" / ");
                UserHandle.formatUid(printwriter, k1);
                printwriter.print(" / v");
                printwriter.print(j2);
                printwriter.println(":");
            }
            if(!flag || flag1)
            {
                i = 0;
                while(i < k2) 
                {
                    ProcessState processstate = (ProcessState)((PackageState) (obj3)).mProcesses.valueAt(i);
                    if(flag3 || !(s.equals(processstate.getName()) ^ true))
                        if(flag2 && processstate.isInUse() ^ true)
                        {
                            printwriter.print("      (Not active: ");
                            printwriter.print((String)((PackageState) (obj3)).mProcesses.keyAt(i));
                            printwriter.println(")");
                        } else
                        {
                            printwriter.print("      Process ");
                            printwriter.print((String)((PackageState) (obj3)).mProcesses.keyAt(i));
                            if(processstate.getCommonProcess().isMultiPackage())
                                printwriter.print(" (multi, ");
                            else
                                printwriter.print(" (unique, ");
                            printwriter.print(processstate.getDurationsBucketCount());
                            printwriter.print(" entries)");
                            printwriter.println(":");
                            processstate.dumpProcessState(printwriter, "        ", ALL_SCREEN_ADJ, ALL_MEM_ADJ, ALL_PROC_STATES, l);
                            processstate.dumpPss(printwriter, "        ", ALL_SCREEN_ADJ, ALL_MEM_ADJ, ALL_PROC_STATES);
                            processstate.dumpInternalLocked(printwriter, "        ", flag1);
                        }
                    i++;
                }
                break label0;
            }
            ArrayList arraylist = new ArrayList();
            i = 0;
            do
            {
                if(i >= k2)
                    break;
                ProcessState processstate1 = (ProcessState)((PackageState) (obj3)).mProcesses.valueAt(i);
                if((flag3 || !(s.equals(processstate1.getName()) ^ true)) && (!flag2 || !(processstate1.isInUse() ^ true)))
                    arraylist.add(processstate1);
                i++;
            } while(true);
            DumpUtils.dumpProcessSummaryLocked(printwriter, "      ", arraylist, ALL_SCREEN_ADJ, ALL_MEM_ADJ, NON_CACHED_PROC_STATES, l, l1);
        }
        i3 = 0;
_L13:
        i = j3;
        j1 = j;
        if(i3 >= l2) goto _L9; else goto _L12
_L12:
        ServiceState servicestate = (ServiceState)((PackageState) (obj3)).mServices.valueAt(i3);
        if(flag3 || !(s.equals(servicestate.getProcessName()) ^ true))
            if(flag2 && servicestate.isInUse() ^ true)
            {
                printwriter.print("      (Not active: ");
                printwriter.print((String)((PackageState) (obj3)).mServices.keyAt(i3));
                printwriter.println(")");
            } else
            {
                if(flag1)
                    printwriter.print("      Service ");
                else
                    printwriter.print("      * ");
                printwriter.print((String)((PackageState) (obj3)).mServices.keyAt(i3));
                printwriter.println(":");
                printwriter.print("        Process: ");
                printwriter.println(servicestate.getProcessName());
                servicestate.dumpStats(printwriter, "        ", "          ", "    ", l, l1, flag, flag1);
            }
        i3++;
          goto _L13
          goto _L9
        i1++;
        if(true) goto _L15; else goto _L14
_L14:
        k++;
        j = i;
        i = j1;
          goto _L16
_L2:
        obj2 = mProcesses.getMap();
        j3 = 0;
        k = 0;
        i1 = 0;
        j = 0;
_L24:
        if(j >= ((ArrayMap) (obj2)).size())
            break; /* Loop/switch isn't completed */
        obj = (String)((ArrayMap) (obj2)).keyAt(j);
        obj3 = (SparseArray)((ArrayMap) (obj2)).valueAt(j);
        i2 = 0;
        j1 = i;
        i = j3;
        j3 = i2;
_L18:
        if(j3 >= ((SparseArray) (obj3)).size())
            break MISSING_BLOCK_LABEL_1342;
        l2 = ((SparseArray) (obj3)).keyAt(j3);
        k1 = i1 + 1;
        obj1 = (ProcessState)((SparseArray) (obj3)).valueAt(j3);
        if(!((ProcessState) (obj1)).hasAnyData())
            break; /* Loop/switch isn't completed */
        i3 = j1;
        i2 = i;
        i1 = k;
_L20:
        j3++;
        k = i1;
        i1 = k1;
        i = i2;
        j1 = i3;
        if(true) goto _L18; else goto _L17
_L17:
        i1 = k;
        i2 = i;
        i3 = j1;
        if(!((ProcessState) (obj1)).isMultiPackage()) goto _L20; else goto _L19
_L19:
        if(s == null || !(s.equals(obj) ^ true)) goto _L22; else goto _L21
_L21:
        i1 = k;
        i2 = i;
        i3 = j1;
        if(s.equals(((ProcessState) (obj1)).getPackage()) ^ true) goto _L20; else goto _L22
_L22:
        i1 = k + 1;
        if(j1 != 0)
            printwriter.println();
        i3 = 1;
        j1 = i;
        if(i == 0)
        {
            printwriter.println("Multi-Package Common Processes:");
            j1 = 1;
        }
        if(flag2 && ((ProcessState) (obj1)).isInUse() ^ true)
        {
            printwriter.print("      (Not active: ");
            printwriter.print(((String) (obj)));
            printwriter.println(")");
            i2 = j1;
        } else
        {
            printwriter.print("  * ");
            printwriter.print(((String) (obj)));
            printwriter.print(" / ");
            UserHandle.formatUid(printwriter, l2);
            printwriter.print(" (");
            printwriter.print(((ProcessState) (obj1)).getDurationsBucketCount());
            printwriter.print(" entries)");
            printwriter.println(":");
            ((ProcessState) (obj1)).dumpProcessState(printwriter, "        ", ALL_SCREEN_ADJ, ALL_MEM_ADJ, ALL_PROC_STATES, l);
            ((ProcessState) (obj1)).dumpPss(printwriter, "        ", ALL_SCREEN_ADJ, ALL_MEM_ADJ, ALL_PROC_STATES);
            ((ProcessState) (obj1)).dumpInternalLocked(printwriter, "        ", flag1);
            i2 = j1;
        }
          goto _L20
        j++;
        j3 = i;
        i = j1;
        if(true) goto _L24; else goto _L23
_L23:
        if(flag1)
        {
            printwriter.println();
            printwriter.print("  Total procs: ");
            printwriter.print(k);
            printwriter.print(" shown of ");
            printwriter.print(i1);
            printwriter.println(" total");
        }
        if(i != 0)
            printwriter.println();
        if(flag)
        {
            printwriter.println("Summary:");
            dumpSummaryLocked(printwriter, s, l, flag2);
        } else
        {
            dumpTotalsLocked(printwriter, l);
        }
        if(flag1)
        {
            printwriter.println();
            printwriter.println("Internal state:");
            printwriter.print("  mRunning=");
            printwriter.println(mRunning);
        }
        dumpFragmentationLocked(printwriter);
        return;
    }

    public void dumpSummaryLocked(PrintWriter printwriter, String s, long l, boolean flag)
    {
        long l1 = DumpUtils.dumpSingleTime(null, null, mMemFactorDurations, mMemFactor, mStartTime, l);
        dumpFilteredSummaryLocked(printwriter, null, "  ", ALL_SCREEN_ADJ, ALL_MEM_ADJ, ALL_PROC_STATES, NON_CACHED_PROC_STATES, l, l1, s, flag);
        printwriter.println();
        dumpTotalsLocked(printwriter, l);
    }

    void dumpTotalsLocked(PrintWriter printwriter, long l)
    {
        printwriter.println("Run time Stats:");
        DumpUtils.dumpSingleTime(printwriter, "  ", mMemFactorDurations, mMemFactor, mStartTime, l);
        printwriter.println();
        printwriter.println("Memory usage:");
        TotalMemoryUseCollection totalmemoryusecollection = new TotalMemoryUseCollection(ALL_SCREEN_ADJ, ALL_MEM_ADJ);
        computeTotalMemoryUse(totalmemoryusecollection, l);
        l = printMemoryCategory(printwriter, "  ", "Kernel ", totalmemoryusecollection.sysMemKernelWeight, totalmemoryusecollection.totalTime, 0L, totalmemoryusecollection.sysMemSamples);
        long l1 = printMemoryCategory(printwriter, "  ", "Native ", totalmemoryusecollection.sysMemNativeWeight, totalmemoryusecollection.totalTime, l, totalmemoryusecollection.sysMemSamples);
        for(int i = 0; i < 14;)
        {
            l = l1;
            if(i != 7)
                l = printMemoryCategory(printwriter, "  ", DumpUtils.STATE_NAMES[i], totalmemoryusecollection.processStateWeight[i], totalmemoryusecollection.totalTime, l1, totalmemoryusecollection.processStateSamples[i]);
            i++;
            l1 = l;
        }

        l = printMemoryCategory(printwriter, "  ", "Cached ", totalmemoryusecollection.sysMemCachedWeight, totalmemoryusecollection.totalTime, l1, totalmemoryusecollection.sysMemSamples);
        l = printMemoryCategory(printwriter, "  ", "Free   ", totalmemoryusecollection.sysMemFreeWeight, totalmemoryusecollection.totalTime, l, totalmemoryusecollection.sysMemSamples);
        l = printMemoryCategory(printwriter, "  ", "Z-Ram  ", totalmemoryusecollection.sysMemZRamWeight, totalmemoryusecollection.totalTime, l, totalmemoryusecollection.sysMemSamples);
        printwriter.print("  TOTAL  : ");
        DebugUtils.printSizeValue(printwriter, l);
        printwriter.println();
        printMemoryCategory(printwriter, "  ", DumpUtils.STATE_NAMES[7], totalmemoryusecollection.processStateWeight[7], totalmemoryusecollection.totalTime, l, totalmemoryusecollection.processStateSamples[7]);
        printwriter.println();
        printwriter.print("          Start time: ");
        printwriter.print(DateFormat.format("yyyy-MM-dd HH:mm:ss", mTimePeriodStartClock));
        printwriter.println();
        printwriter.print("  Total elapsed time: ");
        boolean flag;
        if(mRunning)
            l = SystemClock.elapsedRealtime();
        else
            l = mTimePeriodEndRealtime;
        TimeUtils.formatDuration(l - mTimePeriodStartRealtime, printwriter);
        flag = true;
        if((mFlags & 2) != 0)
        {
            printwriter.print(" (shutdown)");
            flag = false;
        }
        if((mFlags & 4) != 0)
        {
            printwriter.print(" (sysprops)");
            flag = false;
        }
        if((mFlags & 1) != 0)
        {
            printwriter.print(" (complete)");
            flag = false;
        }
        if(flag)
            printwriter.print(" (partial)");
        if(mHasSwappedOutPss)
            printwriter.print(" (swapped-out-pss)");
        printwriter.print(' ');
        printwriter.print(mRuntime);
        printwriter.println();
    }

    public boolean evaluateSystemProperties(boolean flag)
    {
        boolean flag1 = false;
        String s = SystemProperties.get("persist.sys.dalvik.vm.lib.2", VMRuntime.getRuntime().vmLibrary());
        if(!Objects.equals(s, mRuntime))
        {
            boolean flag2 = true;
            flag1 = flag2;
            if(flag)
            {
                mRuntime = s;
                flag1 = flag2;
            }
        }
        return flag1;
    }

    public PackageState getPackageStateLocked(String s, int i, int j)
    {
        Object obj = (SparseArray)mPackages.get(s, i);
        SparseArray sparsearray = ((SparseArray) (obj));
        if(obj == null)
        {
            sparsearray = new SparseArray();
            mPackages.put(s, i, sparsearray);
        }
        obj = (PackageState)sparsearray.get(j);
        if(obj != null)
        {
            return ((PackageState) (obj));
        } else
        {
            s = new PackageState(s, i);
            sparsearray.put(j, s);
            return s;
        }
    }

    public ProcessState getProcessStateLocked(String s, int i, int j, String s1)
    {
        PackageState packagestate = getPackageStateLocked(s, i, j);
        ProcessState processstate = (ProcessState)packagestate.mProcesses.get(s1);
        if(processstate != null)
            return processstate;
        ProcessState processstate1 = (ProcessState)mProcesses.get(s1, i);
        processstate = processstate1;
        if(processstate1 == null)
        {
            processstate = new ProcessState(this, s, i, j, s1);
            mProcesses.put(s1, i, processstate);
        }
        if(!processstate.isMultiPackage())
        {
            if(s.equals(processstate.getPackage()) && j == processstate.getVersion())
            {
                s = processstate;
            } else
            {
                processstate.setMultiPackage(true);
                long l = SystemClock.uptimeMillis();
                PackageState packagestate1 = getPackageStateLocked(processstate.getPackage(), i, processstate.getVersion());
                if(packagestate1 != null)
                {
                    ProcessState processstate2 = processstate.clone(l);
                    packagestate1.mProcesses.put(processstate.getName(), processstate2);
                    for(int k = packagestate1.mServices.size() - 1; k >= 0; k--)
                    {
                        ServiceState servicestate = (ServiceState)packagestate1.mServices.valueAt(k);
                        if(servicestate.getProcess() == processstate)
                            servicestate.setProcess(processstate2);
                    }

                } else
                {
                    Slog.w("ProcessStats", (new StringBuilder()).append("Cloning proc state: no package state ").append(processstate.getPackage()).append("/").append(i).append(" for proc ").append(processstate.getName()).toString());
                }
                s = new ProcessState(processstate, s, i, j, s1, l);
            }
        } else
        {
            s = new ProcessState(processstate, s, i, j, s1, SystemClock.uptimeMillis());
        }
        packagestate.mProcesses.put(s1, s);
        return s;
    }

    public ServiceState getServiceStateLocked(String s, int i, int j, String s1, String s2)
    {
        PackageState packagestate = getPackageStateLocked(s, i, j);
        Object obj = (ServiceState)packagestate.mServices.get(s2);
        if(obj != null)
            return ((ServiceState) (obj));
        if(s1 != null)
            obj = getProcessStateLocked(s, i, j, s1);
        else
            obj = null;
        s = new ServiceState(this, s, s2, s1, ((ProcessState) (obj)));
        packagestate.mServices.put(s2, s);
        return s;
    }

    long printMemoryCategory(PrintWriter printwriter, String s, String s1, double d, long l, 
            long l1, int i)
    {
        if(d != 0.0D)
        {
            l = (long)((1024D * d) / (double)l);
            printwriter.print(s);
            printwriter.print(s1);
            printwriter.print(": ");
            DebugUtils.printSizeValue(printwriter, l);
            printwriter.print(" (");
            printwriter.print(i);
            printwriter.print(" samples)");
            printwriter.println();
            return l1 + l;
        } else
        {
            return l1;
        }
    }

    public void read(InputStream inputstream)
    {
        int ai[] = new int[1];
        byte abyte0[] = readFully(inputstream, ai);
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(abyte0, 0, ai[0]);
        parcel.setDataPosition(0);
        inputstream.close();
        readFromParcel(parcel);
_L1:
        return;
        inputstream;
        mReadError = (new StringBuilder()).append("caught exception: ").append(inputstream).toString();
          goto _L1
    }

    public void readFromParcel(Parcel parcel)
    {
        boolean flag;
        int j;
        int j1;
        if(mPackages.getMap().size() <= 0)
        {
            if(mProcesses.getMap().size() > 0)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = true;
        }
        if(flag)
            resetSafely();
        if(!readCheckedInt(parcel, 0x50535454, "magic number"))
            return;
        j = parcel.readInt();
        if(j != 21)
        {
            mReadError = (new StringBuilder()).append("bad version: ").append(j).toString();
            return;
        }
        if(!readCheckedInt(parcel, 14, "state count"))
            return;
        if(!readCheckedInt(parcel, 8, "adj count"))
            return;
        if(!readCheckedInt(parcel, 7, "pss count"))
            return;
        if(!readCheckedInt(parcel, 16, "sys mem usage count"))
            return;
        if(!readCheckedInt(parcel, 4096, "longs size"))
            return;
        mIndexToCommonString = new ArrayList();
        mTimePeriodStartClock = parcel.readLong();
        buildTimePeriodStartClockStr();
        mTimePeriodStartRealtime = parcel.readLong();
        mTimePeriodEndRealtime = parcel.readLong();
        mTimePeriodStartUptime = parcel.readLong();
        mTimePeriodEndUptime = parcel.readLong();
        mRuntime = parcel.readString();
        boolean flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mHasSwappedOutPss = flag1;
        mFlags = parcel.readInt();
        mTableData.readFromParcel(parcel);
        readCompactedLongArray(parcel, j, mMemFactorDurations, mMemFactorDurations.length);
        if(!mSysMemUsage.readFromParcel(parcel))
            return;
        int k = parcel.readInt();
        j1 = k;
        if(k < 0)
        {
            mReadError = (new StringBuilder()).append("bad process count: ").append(k).toString();
            return;
        }
_L19:
        int l;
        int k1;
        Object obj;
        int l1;
        Object obj1;
        if(j1 <= 0)
            break MISSING_BLOCK_LABEL_578;
        k1 = j1 - 1;
        obj = readCommonString(parcel, j);
        if(obj == null)
        {
            mReadError = "bad process name";
            return;
        }
        j1 = parcel.readInt();
        l = j1;
        if(j1 < 0)
        {
            mReadError = (new StringBuilder()).append("bad uid count: ").append(j1).toString();
            return;
        }
_L2:
        j1 = k1;
        if(l <= 0)
            continue; /* Loop/switch isn't completed */
label0:
        {
            l--;
            l1 = parcel.readInt();
            if(l1 < 0)
            {
                mReadError = (new StringBuilder()).append("bad uid: ").append(l1).toString();
                return;
            }
            obj1 = readCommonString(parcel, j);
            if(obj1 == null)
            {
                mReadError = "bad process package name";
                return;
            }
            j1 = parcel.readInt();
            ProcessState processstate;
            if(flag)
                processstate = (ProcessState)mProcesses.get(((String) (obj)), l1);
            else
                processstate = null;
            if(processstate == null)
                break label0;
            obj1 = processstate;
            if(!processstate.readFromParcel(parcel, false))
                return;
        }
        if(false)
            break; /* Loop/switch isn't completed */
_L4:
        mProcesses.put(((String) (obj)), l1, obj1);
        if(true) goto _L2; else goto _L1
_L1:
        continue; /* Loop/switch isn't completed */
        ProcessState processstate1;
        processstate1 = new ProcessState(this, ((String) (obj1)), l1, j1, ((String) (obj)));
        obj1 = processstate1;
        if(processstate1.readFromParcel(parcel, true)) goto _L4; else goto _L3
_L3:
        return;
        j1 = parcel.readInt();
        l = j1;
        if(j1 < 0)
        {
            mReadError = (new StringBuilder()).append("bad package count: ").append(j1).toString();
            return;
        }
_L6:
        String s1;
        if(l <= 0)
            break MISSING_BLOCK_LABEL_1279;
        k1 = l - 1;
        s1 = readCommonString(parcel, j);
        if(s1 == null)
        {
            mReadError = "bad package name";
            return;
        }
        l = parcel.readInt();
        j1 = l;
        if(l < 0)
        {
            mReadError = (new StringBuilder()).append("bad uid count: ").append(l).toString();
            return;
        }
_L8:
        l = k1;
        if(j1 <= 0) goto _L6; else goto _L5
_L5:
        int i2;
        l1 = j1 - 1;
        i2 = parcel.readInt();
        if(i2 < 0)
        {
            mReadError = (new StringBuilder()).append("bad uid: ").append(i2).toString();
            return;
        }
        j1 = parcel.readInt();
        l = j1;
        if(j1 < 0)
        {
            mReadError = (new StringBuilder()).append("bad versions count: ").append(j1).toString();
            return;
        }
_L15:
        j1 = l1;
        if(l <= 0) goto _L8; else goto _L7
_L7:
        int j2;
        int k2;
        PackageState packagestate;
        j2 = l - 1;
        k2 = parcel.readInt();
        packagestate = new PackageState(s1, i2);
        obj1 = (SparseArray)mPackages.get(s1, i2);
        SparseArray sparsearray = ((SparseArray) (obj1));
        if(obj1 == null)
        {
            sparsearray = new SparseArray();
            mPackages.put(s1, i2, sparsearray);
        }
        sparsearray.put(k2, packagestate);
        j1 = parcel.readInt();
        l = j1;
        if(j1 < 0)
        {
            mReadError = (new StringBuilder()).append("bad package process count: ").append(j1).toString();
            return;
        }
_L12:
label1:
        do
        {
label2:
            {
label3:
                {
                    if(l <= 0)
                        break label2;
                    l--;
                    obj = readCommonString(parcel, j);
                    if(obj == null)
                    {
                        mReadError = "bad package process name";
                        return;
                    }
                    j1 = parcel.readInt();
                    obj1 = (ProcessState)mProcesses.get(((String) (obj)), i2);
                    if(obj1 == null)
                    {
                        mReadError = (new StringBuilder()).append("no common proc: ").append(((String) (obj))).toString();
                        return;
                    }
                    if(j1 == 0)
                        break label3;
                    ProcessState processstate2;
                    if(flag)
                        processstate2 = (ProcessState)packagestate.mProcesses.get(obj);
                    else
                        processstate2 = null;
                    if(processstate2 != null)
                    {
                        obj1 = processstate2;
                        if(!processstate2.readFromParcel(parcel, false))
                            return;
                    } else
                    {
                        ProcessState processstate3 = new ProcessState(((ProcessState) (obj1)), s1, i2, k2, ((String) (obj)), 0L);
                        obj1 = processstate3;
                        if(!processstate3.readFromParcel(parcel, true))
                            return;
                    }
                }
                if(true)
                    break label1;
                packagestate.mProcesses.put(obj, obj1);
            }
        } while(true);
        if(true) goto _L10; else goto _L9
_L9:
        break; /* Loop/switch isn't completed */
_L10:
        packagestate.mProcesses.put(obj, obj1);
        if(true) goto _L12; else goto _L11
_L11:
        l = parcel.readInt();
        j1 = l;
        if(l < 0)
        {
            mReadError = (new StringBuilder()).append("bad package service count: ").append(l).toString();
            return;
        }
          goto _L13
_L17:
        String s2;
        packagestate.mServices.put(s2, obj);
_L13:
        l = j2;
        if(j1 <= 0) goto _L15; else goto _L14
_L14:
        j1--;
        s2 = parcel.readString();
        if(s2 == null)
        {
            mReadError = "bad package service name";
            return;
        }
        String s;
        ServiceState servicestate;
        if(j > 9)
            s = readCommonString(parcel, j);
        else
            s = null;
        if(flag)
            servicestate = (ServiceState)packagestate.mServices.get(s2);
        else
            servicestate = null;
        obj = servicestate;
        if(servicestate == null)
            obj = new ServiceState(this, s1, s2, s, null);
        if(((ServiceState) (obj)).readFromParcel(parcel)) goto _L17; else goto _L16
_L16:
        return;
        int i1 = parcel.readInt();
        mPageTypeZones.clear();
        mPageTypeZones.ensureCapacity(i1);
        mPageTypeLabels.clear();
        mPageTypeLabels.ensureCapacity(i1);
        mPageTypeSizes.clear();
        mPageTypeSizes.ensureCapacity(i1);
        for(int i = 0; i < i1; i++)
        {
            mPageTypeZones.add(Integer.valueOf(parcel.readInt()));
            mPageTypeLabels.add(parcel.readString());
            mPageTypeSizes.add(parcel.createIntArray());
        }

        mIndexToCommonString = null;
        return;
        if(true) goto _L19; else goto _L18
_L18:
    }

    public void reset()
    {
        resetCommon();
        mPackages.getMap().clear();
        mProcesses.getMap().clear();
        mMemFactor = -1;
        mStartTime = 0L;
    }

    public void resetSafely()
    {
        resetCommon();
        long l = SystemClock.uptimeMillis();
        ArrayMap arraymap = mProcesses.getMap();
        for(int i = arraymap.size() - 1; i >= 0; i--)
        {
            SparseArray sparsearray = (SparseArray)arraymap.valueAt(i);
            for(int i1 = sparsearray.size() - 1; i1 >= 0; i1--)
                ((ProcessState)sparsearray.valueAt(i1)).tmpNumInUse = 0;

        }

        ArrayMap arraymap1 = mPackages.getMap();
        for(int j = arraymap1.size() - 1; j >= 0; j--)
        {
            SparseArray sparsearray2 = (SparseArray)arraymap1.valueAt(j);
            for(int j1 = sparsearray2.size() - 1; j1 >= 0; j1--)
            {
                SparseArray sparsearray3 = (SparseArray)sparsearray2.valueAt(j1);
                for(int l1 = sparsearray3.size() - 1; l1 >= 0; l1--)
                {
                    PackageState packagestate = (PackageState)sparsearray3.valueAt(l1);
                    int i2 = packagestate.mProcesses.size() - 1;
                    while(i2 >= 0) 
                    {
                        ProcessState processstate1 = (ProcessState)packagestate.mProcesses.valueAt(i2);
                        if(processstate1.isInUse())
                        {
                            processstate1.resetSafely(l);
                            ProcessState processstate2 = processstate1.getCommonProcess();
                            processstate2.tmpNumInUse = processstate2.tmpNumInUse + 1;
                            processstate1.getCommonProcess().tmpFoundSubProc = processstate1;
                        } else
                        {
                            ((ProcessState)packagestate.mProcesses.valueAt(i2)).makeDead();
                            packagestate.mProcesses.removeAt(i2);
                        }
                        i2--;
                    }
                    i2 = packagestate.mServices.size() - 1;
                    while(i2 >= 0) 
                    {
                        ServiceState servicestate = (ServiceState)packagestate.mServices.valueAt(i2);
                        if(servicestate.isInUse())
                            servicestate.resetSafely(l);
                        else
                            packagestate.mServices.removeAt(i2);
                        i2--;
                    }
                    if(packagestate.mProcesses.size() <= 0 && packagestate.mServices.size() <= 0)
                        sparsearray3.removeAt(l1);
                }

                if(sparsearray3.size() <= 0)
                    sparsearray2.removeAt(j1);
            }

            if(sparsearray2.size() <= 0)
                arraymap1.removeAt(j);
        }

        for(int k = arraymap.size() - 1; k >= 0; k--)
        {
            SparseArray sparsearray1 = (SparseArray)arraymap.valueAt(k);
            int k1 = sparsearray1.size() - 1;
            while(k1 >= 0) 
            {
                ProcessState processstate = (ProcessState)sparsearray1.valueAt(k1);
                if(processstate.isInUse() || processstate.tmpNumInUse > 0)
                {
                    if(!processstate.isActive() && processstate.isMultiPackage() && processstate.tmpNumInUse == 1)
                    {
                        processstate = processstate.tmpFoundSubProc;
                        processstate.makeStandalone();
                        sparsearray1.setValueAt(k1, processstate);
                    } else
                    {
                        processstate.resetSafely(l);
                    }
                } else
                {
                    processstate.makeDead();
                    sparsearray1.removeAt(k1);
                }
                k1--;
            }
            if(sparsearray1.size() <= 0)
                arraymap.removeAt(k);
        }

        mStartTime = l;
    }

    public void updateFragmentation()
    {
        Object obj;
        Object obj1;
        Object obj2;
        obj = null;
        obj1 = null;
        obj2 = obj;
        Object obj4 = JVM INSTR new #1115 <Class BufferedReader>;
        obj2 = obj;
        FileReader filereader = JVM INSTR new #1117 <Class FileReader>;
        obj2 = obj;
        filereader.FileReader("/proc/pagetypeinfo");
        obj2 = obj;
        ((BufferedReader) (obj4)).BufferedReader(filereader);
        obj2 = sPageTypeRegex.matcher("");
        mPageTypeZones.clear();
        mPageTypeLabels.clear();
        mPageTypeSizes.clear();
_L4:
        obj1 = ((BufferedReader) (obj4)).readLine();
        if(obj1 != null) goto _L2; else goto _L1
_L1:
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_88;
        ((BufferedReader) (obj4)).close();
_L6:
        return;
_L2:
        ((Matcher) (obj2)).reset(((CharSequence) (obj1)));
        if(!((Matcher) (obj2)).matches()) goto _L4; else goto _L3
_L3:
        obj1 = Integer.valueOf(((Matcher) (obj2)).group(1), 10);
        if(obj1 == null) goto _L4; else goto _L5
_L5:
        mPageTypeZones.add(obj1);
        mPageTypeLabels.add(((Matcher) (obj2)).group(2));
        mPageTypeSizes.add(splitAndParseNumbers(((Matcher) (obj2)).group(3)));
          goto _L4
        obj2;
_L10:
        obj2 = obj4;
        mPageTypeZones.clear();
        obj2 = obj4;
        mPageTypeLabels.clear();
        obj2 = obj4;
        mPageTypeSizes.clear();
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_199;
        ((BufferedReader) (obj4)).close();
_L7:
        return;
        obj2;
          goto _L6
        obj2;
          goto _L7
        obj4;
        obj1 = obj2;
_L9:
        if(obj1 != null)
            try
            {
                ((BufferedReader) (obj1)).close();
            }
            catch(IOException ioexception) { }
        throw obj4;
        Object obj3;
        obj3;
        obj1 = obj4;
        obj4 = obj3;
        if(true) goto _L9; else goto _L8
_L8:
        obj3;
        obj4 = obj1;
          goto _L10
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcel(parcel, SystemClock.uptimeMillis(), i);
    }

    public void writeToParcel(Parcel parcel, long l, int i)
    {
        parcel.writeInt(0x50535454);
        parcel.writeInt(21);
        parcel.writeInt(14);
        parcel.writeInt(8);
        parcel.writeInt(7);
        parcel.writeInt(16);
        parcel.writeInt(4096);
        mCommonStringToIndex = new ArrayMap(mProcesses.size());
        ArrayMap arraymap = mProcesses.getMap();
        int j = arraymap.size();
        for(i = 0; i < j; i++)
        {
            SparseArray sparsearray = (SparseArray)arraymap.valueAt(i);
            int i1 = sparsearray.size();
            for(int i2 = 0; i2 < i1; i2++)
                ((ProcessState)sparsearray.valueAt(i2)).commitStateTime(l);

        }

        ArrayMap arraymap1 = mPackages.getMap();
        int j3 = arraymap1.size();
        for(i = 0; i < j3; i++)
        {
            SparseArray sparsearray1 = (SparseArray)arraymap1.valueAt(i);
            int k3 = sparsearray1.size();
            for(int j2 = 0; j2 < k3; j2++)
            {
                SparseArray sparsearray4 = (SparseArray)sparsearray1.valueAt(j2);
                int i4 = sparsearray4.size();
                for(int j1 = 0; j1 < i4; j1++)
                {
                    PackageState packagestate1 = (PackageState)sparsearray4.valueAt(j1);
                    int k4 = packagestate1.mProcesses.size();
                    for(int l4 = 0; l4 < k4; l4++)
                    {
                        ProcessState processstate1 = (ProcessState)packagestate1.mProcesses.valueAt(l4);
                        if(processstate1.getCommonProcess() != processstate1)
                            processstate1.commitStateTime(l);
                    }

                    k4 = packagestate1.mServices.size();
                    for(int i5 = 0; i5 < k4; i5++)
                        ((ServiceState)packagestate1.mServices.valueAt(i5)).commitStateTime(l);

                }

            }

        }

        parcel.writeLong(mTimePeriodStartClock);
        parcel.writeLong(mTimePeriodStartRealtime);
        parcel.writeLong(mTimePeriodEndRealtime);
        parcel.writeLong(mTimePeriodStartUptime);
        parcel.writeLong(mTimePeriodEndUptime);
        parcel.writeString(mRuntime);
        if(mHasSwappedOutPss)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mFlags);
        mTableData.writeToParcel(parcel);
        if(mMemFactor != -1)
        {
            long al[] = mMemFactorDurations;
            i = mMemFactor;
            al[i] = al[i] + (l - mStartTime);
            mStartTime = l;
        }
        writeCompactedLongArray(parcel, mMemFactorDurations, mMemFactorDurations.length);
        mSysMemUsage.writeToParcel(parcel);
        parcel.writeInt(j);
        for(i = 0; i < j; i++)
        {
            writeCommonString(parcel, (String)arraymap.keyAt(i));
            SparseArray sparsearray2 = (SparseArray)arraymap.valueAt(i);
            int k1 = sparsearray2.size();
            parcel.writeInt(k1);
            for(int k2 = 0; k2 < k1; k2++)
            {
                parcel.writeInt(sparsearray2.keyAt(k2));
                ProcessState processstate = (ProcessState)sparsearray2.valueAt(k2);
                writeCommonString(parcel, processstate.getPackage());
                parcel.writeInt(processstate.getVersion());
                processstate.writeToParcel(parcel, l);
            }

        }

        parcel.writeInt(j3);
        for(i = 0; i < j3; i++)
        {
            writeCommonString(parcel, (String)arraymap1.keyAt(i));
            SparseArray sparsearray3 = (SparseArray)arraymap1.valueAt(i);
            int k = sparsearray3.size();
            parcel.writeInt(k);
            for(int l2 = 0; l2 < k; l2++)
            {
                parcel.writeInt(sparsearray3.keyAt(l2));
                SparseArray sparsearray5 = (SparseArray)sparsearray3.valueAt(l2);
                int l3 = sparsearray5.size();
                parcel.writeInt(l3);
                for(int l1 = 0; l1 < l3; l1++)
                {
                    parcel.writeInt(sparsearray5.keyAt(l1));
                    PackageState packagestate = (PackageState)sparsearray5.valueAt(l1);
                    int j4 = packagestate.mProcesses.size();
                    parcel.writeInt(j4);
                    int j5 = 0;
                    while(j5 < j4) 
                    {
                        writeCommonString(parcel, (String)packagestate.mProcesses.keyAt(j5));
                        ProcessState processstate2 = (ProcessState)packagestate.mProcesses.valueAt(j5);
                        if(processstate2.getCommonProcess() == processstate2)
                        {
                            parcel.writeInt(0);
                        } else
                        {
                            parcel.writeInt(1);
                            processstate2.writeToParcel(parcel, l);
                        }
                        j5++;
                    }
                    j4 = packagestate.mServices.size();
                    parcel.writeInt(j4);
                    for(int k5 = 0; k5 < j4; k5++)
                    {
                        parcel.writeString((String)packagestate.mServices.keyAt(k5));
                        ServiceState servicestate = (ServiceState)packagestate.mServices.valueAt(k5);
                        writeCommonString(parcel, servicestate.getProcessName());
                        servicestate.writeToParcel(parcel, l);
                    }

                }

            }

        }

        int i3 = mPageTypeLabels.size();
        parcel.writeInt(i3);
        for(i = 0; i < i3; i++)
        {
            parcel.writeInt(((Integer)mPageTypeZones.get(i)).intValue());
            parcel.writeString((String)mPageTypeLabels.get(i));
            parcel.writeIntArray((int[])mPageTypeSizes.get(i));
        }

        mCommonStringToIndex = null;
    }

    public static final int ADJ_COUNT = 8;
    public static final int ADJ_MEM_FACTOR_COUNT = 4;
    public static final int ADJ_MEM_FACTOR_CRITICAL = 3;
    public static final int ADJ_MEM_FACTOR_LOW = 2;
    public static final int ADJ_MEM_FACTOR_MODERATE = 1;
    public static final int ADJ_MEM_FACTOR_NORMAL = 0;
    public static final int ADJ_NOTHING = -1;
    public static final int ADJ_SCREEN_MOD = 4;
    public static final int ADJ_SCREEN_OFF = 0;
    public static final int ADJ_SCREEN_ON = 4;
    public static final int ALL_MEM_ADJ[] = {
        0, 1, 2, 3
    };
    public static final int ALL_PROC_STATES[] = {
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
        10, 11, 12, 13
    };
    public static final int ALL_SCREEN_ADJ[] = {
        0, 4
    };
    public static final int BACKGROUND_PROC_STATES[] = {
        2, 3, 4, 5, 6, 7, 8
    };
    static final int BAD_TABLE[] = new int[0];
    public static long COMMIT_PERIOD = 0L;
    public static long COMMIT_UPTIME_PERIOD = 0L;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ProcessStats createFromParcel(Parcel parcel)
        {
            return new ProcessStats(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ProcessStats[] newArray(int i)
        {
            return new ProcessStats[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final boolean DEBUG = false;
    static final boolean DEBUG_PARCEL = false;
    public static final int FLAG_COMPLETE = 1;
    public static final int FLAG_SHUTDOWN = 2;
    public static final int FLAG_SYSPROPS = 4;
    private static final int MAGIC = 0x50535454;
    public static final int NON_CACHED_PROC_STATES[] = {
        0, 1, 2, 3, 4, 5, 6, 7, 8
    };
    private static final int PARCEL_VERSION = 21;
    public static final int PSS_AVERAGE = 2;
    public static final int PSS_COUNT = 7;
    public static final int PSS_MAXIMUM = 3;
    public static final int PSS_MINIMUM = 1;
    public static final int PSS_SAMPLE_COUNT = 0;
    public static final int PSS_USS_AVERAGE = 5;
    public static final int PSS_USS_MAXIMUM = 6;
    public static final int PSS_USS_MINIMUM = 4;
    public static final String SERVICE_NAME = "procstats";
    public static final int STATE_BACKUP = 4;
    public static final int STATE_CACHED_ACTIVITY = 11;
    public static final int STATE_CACHED_ACTIVITY_CLIENT = 12;
    public static final int STATE_CACHED_EMPTY = 13;
    public static final int STATE_COUNT = 14;
    public static final int STATE_HEAVY_WEIGHT = 5;
    public static final int STATE_HOME = 9;
    public static final int STATE_IMPORTANT_BACKGROUND = 3;
    public static final int STATE_IMPORTANT_FOREGROUND = 2;
    public static final int STATE_LAST_ACTIVITY = 10;
    public static final int STATE_NOTHING = -1;
    public static final int STATE_PERSISTENT = 0;
    public static final int STATE_RECEIVER = 8;
    public static final int STATE_SERVICE = 6;
    public static final int STATE_SERVICE_RESTARTING = 7;
    public static final int STATE_TOP = 1;
    public static final int SYS_MEM_USAGE_CACHED_AVERAGE = 2;
    public static final int SYS_MEM_USAGE_CACHED_MAXIMUM = 3;
    public static final int SYS_MEM_USAGE_CACHED_MINIMUM = 1;
    public static final int SYS_MEM_USAGE_COUNT = 16;
    public static final int SYS_MEM_USAGE_FREE_AVERAGE = 5;
    public static final int SYS_MEM_USAGE_FREE_MAXIMUM = 6;
    public static final int SYS_MEM_USAGE_FREE_MINIMUM = 4;
    public static final int SYS_MEM_USAGE_KERNEL_AVERAGE = 11;
    public static final int SYS_MEM_USAGE_KERNEL_MAXIMUM = 12;
    public static final int SYS_MEM_USAGE_KERNEL_MINIMUM = 10;
    public static final int SYS_MEM_USAGE_NATIVE_AVERAGE = 14;
    public static final int SYS_MEM_USAGE_NATIVE_MAXIMUM = 15;
    public static final int SYS_MEM_USAGE_NATIVE_MINIMUM = 13;
    public static final int SYS_MEM_USAGE_SAMPLE_COUNT = 0;
    public static final int SYS_MEM_USAGE_ZRAM_AVERAGE = 8;
    public static final int SYS_MEM_USAGE_ZRAM_MAXIMUM = 9;
    public static final int SYS_MEM_USAGE_ZRAM_MINIMUM = 7;
    public static final String TAG = "ProcessStats";
    private static final Pattern sPageTypeRegex = Pattern.compile("^Node\\s+(\\d+),.*. type\\s+(\\w+)\\s+([\\s\\d]+?)\\s*$");
    ArrayMap mCommonStringToIndex;
    public int mFlags;
    boolean mHasSwappedOutPss;
    ArrayList mIndexToCommonString;
    public int mMemFactor;
    public final long mMemFactorDurations[];
    public final ProcessMap mPackages;
    private final ArrayList mPageTypeLabels;
    private final ArrayList mPageTypeSizes;
    private final ArrayList mPageTypeZones;
    public final ProcessMap mProcesses;
    public String mReadError;
    boolean mRunning;
    String mRuntime;
    public long mStartTime;
    public final SysMemUsageTable mSysMemUsage;
    public final long mSysMemUsageArgs[];
    public final SparseMappingTable mTableData;
    public long mTimePeriodEndRealtime;
    public long mTimePeriodEndUptime;
    public long mTimePeriodStartClock;
    public String mTimePeriodStartClockStr;
    public long mTimePeriodStartRealtime;
    public long mTimePeriodStartUptime;

    static 
    {
        COMMIT_PERIOD = 0xa4cb80L;
        COMMIT_UPTIME_PERIOD = 0x36ee80L;
    }
}
