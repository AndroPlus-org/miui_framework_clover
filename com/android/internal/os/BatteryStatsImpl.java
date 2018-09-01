// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.app.ActivityManager;
import android.bluetooth.BluetoothActivityEnergyInfo;
import android.bluetooth.UidTraffic;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkStats;
import android.net.wifi.WifiActivityEnergyInfo;
import android.net.wifi.WifiManager;
import android.os.*;
import android.telephony.ModemActivityInfo;
import android.telephony.SignalStrength;
import android.text.TextUtils;
import android.util.*;
import com.android.internal.net.NetworkStatsFactory;
import com.android.internal.util.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import libcore.util.EmptyArray;
import org.xmlpull.v1.*;

// Referenced classes of package com.android.internal.os:
//            KernelWakelockReader, KernelWakelockStats, KernelUidCpuTimeReader, KernelUidCpuFreqTimeReader, 
//            KernelMemoryBandwidthStats, RpmStats, AtomicFile, PowerProfile, 
//            BatteryStatsImplInjector, BatteryStatsHelper, BackgroundThread, KernelCpuSpeedReader

public class BatteryStatsImpl extends BatteryStats
{
    public static class BatchTimer extends Timer
    {

        private long computeOverage(long l)
        {
            if(mLastAddedTime > 0L)
                return (mLastTime + mLastAddedDuration) - l;
            else
                return 0L;
        }

        private void recomputeLastDuration(long l, boolean flag)
        {
            long l1 = computeOverage(l);
            if(l1 > 0L)
            {
                if(mInDischarge)
                    mTotalTime = mTotalTime - l1;
                if(flag)
                {
                    mLastAddedTime = 0L;
                } else
                {
                    mLastAddedTime = l;
                    mLastAddedDuration = mLastAddedDuration - l1;
                }
            }
        }

        public void abortLastDuration(BatteryStatsImpl batterystatsimpl)
        {
            recomputeLastDuration(mClocks.elapsedRealtime() * 1000L, true);
        }

        public void addDuration(BatteryStatsImpl batterystatsimpl, long l)
        {
            long l1 = mClocks.elapsedRealtime() * 1000L;
            recomputeLastDuration(l1, true);
            mLastAddedTime = l1;
            mLastAddedDuration = l * 1000L;
            if(mInDischarge)
            {
                mTotalTime = mTotalTime + mLastAddedDuration;
                mCount = mCount + 1;
            }
        }

        protected int computeCurrentCountLocked()
        {
            return mCount;
        }

        protected long computeRunTimeLocked(long l)
        {
            l = computeOverage(mClocks.elapsedRealtime() * 1000L);
            if(l > 0L)
            {
                mTotalTime = l;
                return l;
            } else
            {
                return mTotalTime;
            }
        }

        public void logState(Printer printer, String s)
        {
            super.logState(printer, s);
            printer.println((new StringBuilder()).append(s).append("mLastAddedTime=").append(mLastAddedTime).append(" mLastAddedDuration=").append(mLastAddedDuration).toString());
        }

        public void onTimeStarted(long l, long l1, long l2)
        {
            recomputeLastDuration(l, false);
            mInDischarge = true;
            if(mLastAddedTime == l)
                mTotalTime = mTotalTime + mLastAddedDuration;
            super.onTimeStarted(l, l1, l2);
        }

        public void onTimeStopped(long l, long l1, long l2)
        {
            recomputeLastDuration(mClocks.elapsedRealtime() * 1000L, false);
            mInDischarge = false;
            super.onTimeStopped(l, l1, l2);
        }

        public boolean reset(boolean flag)
        {
            long l = mClocks.elapsedRealtime() * 1000L;
            recomputeLastDuration(l, true);
            boolean flag1;
            if(mLastAddedTime == l)
                flag1 = true;
            else
                flag1 = false;
            if(flag1)
                flag = false;
            super.reset(flag);
            return flag1 ^ true;
        }

        public void writeToParcel(Parcel parcel, long l)
        {
            super.writeToParcel(parcel, l);
            parcel.writeLong(mLastAddedTime);
            parcel.writeLong(mLastAddedDuration);
        }

        boolean mInDischarge;
        long mLastAddedDuration;
        long mLastAddedTime;
        final Uid mUid;

        BatchTimer(Clocks clocks, Uid uid, int i, TimeBase timebase)
        {
            super(clocks, i, timebase);
            mUid = uid;
            mInDischarge = timebase.isRunning();
        }

        BatchTimer(Clocks clocks, Uid uid, int i, TimeBase timebase, Parcel parcel)
        {
            super(clocks, i, timebase, parcel);
            mUid = uid;
            mLastAddedTime = parcel.readLong();
            mLastAddedDuration = parcel.readLong();
            mInDischarge = timebase.isRunning();
        }
    }

    public static interface BatteryCallback
    {

        public abstract void batteryNeedsCpuUpdate();

        public abstract void batteryPowerChanged(boolean flag);

        public abstract void batterySendBroadcast(Intent intent);
    }

    public static interface Clocks
    {

        public abstract long elapsedRealtime();

        public abstract long uptimeMillis();
    }

    public static class ControllerActivityCounterImpl extends android.os.BatteryStats.ControllerActivityCounter
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void detach()
        {
            mIdleTimeMillis.detach();
            mRxTimeMillis.detach();
            LongSamplingCounter alongsamplingcounter[] = mTxTimeMillis;
            int i = 0;
            for(int j = alongsamplingcounter.length; i < j; i++)
                alongsamplingcounter[i].detach();

            mPowerDrainMaMs.detach();
        }

        public volatile android.os.BatteryStats.LongCounter getIdleTimeCounter()
        {
            return getIdleTimeCounter();
        }

        public LongSamplingCounter getIdleTimeCounter()
        {
            return mIdleTimeMillis;
        }

        public volatile android.os.BatteryStats.LongCounter getPowerCounter()
        {
            return getPowerCounter();
        }

        public LongSamplingCounter getPowerCounter()
        {
            return mPowerDrainMaMs;
        }

        public volatile android.os.BatteryStats.LongCounter getRxTimeCounter()
        {
            return getRxTimeCounter();
        }

        public LongSamplingCounter getRxTimeCounter()
        {
            return mRxTimeMillis;
        }

        public volatile android.os.BatteryStats.LongCounter[] getTxTimeCounters()
        {
            return getTxTimeCounters();
        }

        public LongSamplingCounter[] getTxTimeCounters()
        {
            return mTxTimeMillis;
        }

        public void readSummaryFromParcel(Parcel parcel)
        {
            mIdleTimeMillis.readSummaryFromParcelLocked(parcel);
            mRxTimeMillis.readSummaryFromParcelLocked(parcel);
            if(parcel.readInt() != mTxTimeMillis.length)
                throw new ParcelFormatException("inconsistent tx state lengths");
            LongSamplingCounter alongsamplingcounter[] = mTxTimeMillis;
            int i = 0;
            for(int j = alongsamplingcounter.length; i < j; i++)
                alongsamplingcounter[i].readSummaryFromParcelLocked(parcel);

            mPowerDrainMaMs.readSummaryFromParcelLocked(parcel);
        }

        public void reset(boolean flag)
        {
            mIdleTimeMillis.reset(flag);
            mRxTimeMillis.reset(flag);
            LongSamplingCounter alongsamplingcounter[] = mTxTimeMillis;
            int i = 0;
            for(int j = alongsamplingcounter.length; i < j; i++)
                alongsamplingcounter[i].reset(flag);

            mPowerDrainMaMs.reset(flag);
        }

        public void writeSummaryToParcel(Parcel parcel)
        {
            mIdleTimeMillis.writeSummaryFromParcelLocked(parcel);
            mRxTimeMillis.writeSummaryFromParcelLocked(parcel);
            parcel.writeInt(mTxTimeMillis.length);
            LongSamplingCounter alongsamplingcounter[] = mTxTimeMillis;
            int i = 0;
            for(int j = alongsamplingcounter.length; i < j; i++)
                alongsamplingcounter[i].writeSummaryFromParcelLocked(parcel);

            mPowerDrainMaMs.writeSummaryFromParcelLocked(parcel);
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            mIdleTimeMillis.writeToParcel(parcel);
            mRxTimeMillis.writeToParcel(parcel);
            parcel.writeInt(mTxTimeMillis.length);
            LongSamplingCounter alongsamplingcounter[] = mTxTimeMillis;
            i = 0;
            for(int j = alongsamplingcounter.length; i < j; i++)
                alongsamplingcounter[i].writeToParcel(parcel);

            mPowerDrainMaMs.writeToParcel(parcel);
        }

        private final LongSamplingCounter mIdleTimeMillis;
        private final LongSamplingCounter mPowerDrainMaMs;
        private final LongSamplingCounter mRxTimeMillis;
        private final LongSamplingCounter mTxTimeMillis[];

        public ControllerActivityCounterImpl(TimeBase timebase, int i)
        {
            mIdleTimeMillis = new LongSamplingCounter(timebase);
            mRxTimeMillis = new LongSamplingCounter(timebase);
            mTxTimeMillis = new LongSamplingCounter[i];
            for(int j = 0; j < i; j++)
                mTxTimeMillis[j] = new LongSamplingCounter(timebase);

            mPowerDrainMaMs = new LongSamplingCounter(timebase);
        }

        public ControllerActivityCounterImpl(TimeBase timebase, int i, Parcel parcel)
        {
            mIdleTimeMillis = new LongSamplingCounter(timebase, parcel);
            mRxTimeMillis = new LongSamplingCounter(timebase, parcel);
            if(parcel.readInt() != i)
                throw new ParcelFormatException("inconsistent tx state lengths");
            mTxTimeMillis = new LongSamplingCounter[i];
            for(int j = 0; j < i; j++)
                mTxTimeMillis[j] = new LongSamplingCounter(timebase, parcel);

            mPowerDrainMaMs = new LongSamplingCounter(timebase, parcel);
        }
    }

    public static class Counter extends android.os.BatteryStats.Counter
        implements TimeBaseObs
    {

        public static void writeCounterToParcel(Parcel parcel, Counter counter)
        {
            if(counter == null)
            {
                parcel.writeInt(0);
                return;
            } else
            {
                parcel.writeInt(1);
                counter.writeToParcel(parcel);
                return;
            }
        }

        void addAtomic(int i)
        {
            if(mTimeBase.isRunning())
                mCount.addAndGet(i);
        }

        void detach()
        {
            mTimeBase.remove(this);
        }

        public int getCountLocked(int i)
        {
            int j = mCount.get();
            if(i != 2) goto _L2; else goto _L1
_L1:
            int k = j - mUnpluggedCount;
_L4:
            return k;
_L2:
            k = j;
            if(i != 0)
                k = j - mLoadedCount;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void logState(Printer printer, String s)
        {
            printer.println((new StringBuilder()).append(s).append("mCount=").append(mCount.get()).append(" mLoadedCount=").append(mLoadedCount).append(" mUnpluggedCount=").append(mUnpluggedCount).append(" mPluggedCount=").append(mPluggedCount).toString());
        }

        public void onTimeStarted(long l, long l1, long l2)
        {
            mUnpluggedCount = mPluggedCount;
        }

        public void onTimeStopped(long l, long l1, long l2)
        {
            mPluggedCount = mCount.get();
        }

        public void readSummaryFromParcelLocked(Parcel parcel)
        {
            mLoadedCount = parcel.readInt();
            mCount.set(mLoadedCount);
            int i = mLoadedCount;
            mPluggedCount = i;
            mUnpluggedCount = i;
        }

        void reset(boolean flag)
        {
            mCount.set(0);
            mUnpluggedCount = 0;
            mPluggedCount = 0;
            mLoadedCount = 0;
            if(flag)
                detach();
        }

        public void stepAtomic()
        {
            if(mTimeBase.isRunning())
                mCount.incrementAndGet();
        }

        public void writeSummaryFromParcelLocked(Parcel parcel)
        {
            parcel.writeInt(mCount.get());
        }

        public void writeToParcel(Parcel parcel)
        {
            parcel.writeInt(mCount.get());
            parcel.writeInt(mLoadedCount);
            parcel.writeInt(mUnpluggedCount);
        }

        final AtomicInteger mCount;
        int mLoadedCount;
        int mPluggedCount;
        final TimeBase mTimeBase;
        int mUnpluggedCount;

        public Counter(TimeBase timebase)
        {
            mCount = new AtomicInteger();
            mTimeBase = timebase;
            timebase.add(this);
        }

        public Counter(TimeBase timebase, Parcel parcel)
        {
            mCount = new AtomicInteger();
            mTimeBase = timebase;
            mPluggedCount = parcel.readInt();
            mCount.set(mPluggedCount);
            mLoadedCount = parcel.readInt();
            mUnpluggedCount = parcel.readInt();
            timebase.add(this);
        }
    }

    public static class DualTimer extends DurationTimer
    {

        public void detach()
        {
            mSubTimer.detach();
            super.detach();
        }

        public volatile android.os.BatteryStats.Timer getSubTimer()
        {
            return getSubTimer();
        }

        public DurationTimer getSubTimer()
        {
            return mSubTimer;
        }

        public void readSummaryFromParcelLocked(Parcel parcel)
        {
            super.readSummaryFromParcelLocked(parcel);
            mSubTimer.readSummaryFromParcelLocked(parcel);
        }

        public boolean reset(boolean flag)
        {
            return (mSubTimer.reset(false) ^ true | super.reset(flag) ^ true) ^ true;
        }

        public void startRunningLocked(long l)
        {
            super.startRunningLocked(l);
            mSubTimer.startRunningLocked(l);
        }

        public void stopAllRunningLocked(long l)
        {
            super.stopAllRunningLocked(l);
            mSubTimer.stopAllRunningLocked(l);
        }

        public void stopRunningLocked(long l)
        {
            super.stopRunningLocked(l);
            mSubTimer.stopRunningLocked(l);
        }

        public void writeSummaryFromParcelLocked(Parcel parcel, long l)
        {
            super.writeSummaryFromParcelLocked(parcel, l);
            mSubTimer.writeSummaryFromParcelLocked(parcel, l);
        }

        public void writeToParcel(Parcel parcel, long l)
        {
            super.writeToParcel(parcel, l);
            mSubTimer.writeToParcel(parcel, l);
        }

        private final DurationTimer mSubTimer;

        public DualTimer(Clocks clocks, Uid uid, int i, ArrayList arraylist, TimeBase timebase, TimeBase timebase1)
        {
            super(clocks, uid, i, arraylist, timebase);
            mSubTimer = new DurationTimer(clocks, uid, i, null, timebase1);
        }

        public DualTimer(Clocks clocks, Uid uid, int i, ArrayList arraylist, TimeBase timebase, TimeBase timebase1, Parcel parcel)
        {
            super(clocks, uid, i, arraylist, timebase, parcel);
            mSubTimer = new DurationTimer(clocks, uid, i, null, timebase1, parcel);
        }
    }

    public static class DurationTimer extends StopwatchTimer
    {

        public long getCurrentDurationMsLocked(long l)
        {
            long l1 = mCurrentDurationMs;
            long l2 = l1;
            if(mNesting > 0)
            {
                l2 = l1;
                if(mTimeBase.isRunning())
                    l2 = l1 + (mTimeBase.getRealtime(l * 1000L) / 1000L - mStartTimeMs);
            }
            return l2;
        }

        public long getMaxDurationMsLocked(long l)
        {
            if(mNesting > 0)
            {
                l = getCurrentDurationMsLocked(l);
                if(l > mMaxDurationMs)
                    return l;
            }
            return mMaxDurationMs;
        }

        public long getTotalDurationMsLocked(long l)
        {
            return mTotalDurationMs + getCurrentDurationMsLocked(l);
        }

        public void logState(Printer printer, String s)
        {
            super.logState(printer, s);
        }

        public void onTimeStarted(long l, long l1, long l2)
        {
            super.onTimeStarted(l, l1, l2);
            if(mNesting > 0)
                mStartTimeMs = l2 / 1000L;
        }

        public void onTimeStopped(long l, long l1, long l2)
        {
            super.onTimeStopped(l, l1, l2);
            if(mNesting > 0)
                mCurrentDurationMs = mCurrentDurationMs + (l2 / 1000L - mStartTimeMs);
            mStartTimeMs = -1L;
        }

        public void readSummaryFromParcelLocked(Parcel parcel)
        {
            super.readSummaryFromParcelLocked(parcel);
            mMaxDurationMs = parcel.readLong();
            mTotalDurationMs = parcel.readLong();
            mStartTimeMs = -1L;
            mCurrentDurationMs = 0L;
        }

        public boolean reset(boolean flag)
        {
            flag = super.reset(flag);
            mMaxDurationMs = 0L;
            mTotalDurationMs = 0L;
            mCurrentDurationMs = 0L;
            if(mNesting > 0)
                mStartTimeMs = mTimeBase.getRealtime(mClocks.elapsedRealtime() * 1000L) / 1000L;
            else
                mStartTimeMs = -1L;
            return flag;
        }

        public void startRunningLocked(long l)
        {
            super.startRunningLocked(l);
            if(mNesting == 1 && mTimeBase.isRunning())
                mStartTimeMs = mTimeBase.getRealtime(l * 1000L) / 1000L;
        }

        public void stopRunningLocked(long l)
        {
            if(mNesting == 1)
            {
                long l1 = getCurrentDurationMsLocked(l);
                mTotalDurationMs = mTotalDurationMs + l1;
                if(l1 > mMaxDurationMs)
                    mMaxDurationMs = l1;
                mStartTimeMs = -1L;
                mCurrentDurationMs = 0L;
            }
            super.stopRunningLocked(l);
        }

        public void writeSummaryFromParcelLocked(Parcel parcel, long l)
        {
            super.writeSummaryFromParcelLocked(parcel, l);
            parcel.writeLong(getMaxDurationMsLocked(l / 1000L));
            parcel.writeLong(getTotalDurationMsLocked(l / 1000L));
        }

        public void writeToParcel(Parcel parcel, long l)
        {
            super.writeToParcel(parcel, l);
            parcel.writeLong(getMaxDurationMsLocked(l / 1000L));
            parcel.writeLong(mTotalDurationMs);
            parcel.writeLong(getCurrentDurationMsLocked(l / 1000L));
        }

        long mCurrentDurationMs;
        long mMaxDurationMs;
        long mStartTimeMs;
        long mTotalDurationMs;

        public DurationTimer(Clocks clocks, Uid uid, int i, ArrayList arraylist, TimeBase timebase)
        {
            super(clocks, uid, i, arraylist, timebase);
            mStartTimeMs = -1L;
        }

        public DurationTimer(Clocks clocks, Uid uid, int i, ArrayList arraylist, TimeBase timebase, Parcel parcel)
        {
            super(clocks, uid, i, arraylist, timebase, parcel);
            mStartTimeMs = -1L;
            mMaxDurationMs = parcel.readLong();
            mTotalDurationMs = parcel.readLong();
            mCurrentDurationMs = parcel.readLong();
        }
    }

    public static interface ExternalStatsSync
    {

        public abstract Future scheduleCpuSyncDueToRemovedUid(int i);

        public abstract Future scheduleSync(String s, int i);

        public static final int UPDATE_ALL = 31;
        public static final int UPDATE_BT = 8;
        public static final int UPDATE_CPU = 1;
        public static final int UPDATE_RADIO = 4;
        public static final int UPDATE_RPM = 16;
        public static final int UPDATE_WIFI = 2;
    }

    public static class LongSamplingCounter extends android.os.BatteryStats.LongCounter
        implements TimeBaseObs
    {

        void addCountLocked(long l)
        {
            if(mTimeBase.isRunning())
                mCount = mCount + l;
        }

        void detach()
        {
            mTimeBase.remove(this);
        }

        public long getCountLocked(int i)
        {
            long l;
            long l1;
            if(mTimeBase.isRunning())
                l = mCount;
            else
                l = mPluggedCount;
            if(i != 2) goto _L2; else goto _L1
_L1:
            l1 = l - mUnpluggedCount;
_L4:
            return l1;
_L2:
            l1 = l;
            if(i != 0)
                l1 = l - mLoadedCount;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void logState(Printer printer, String s)
        {
            printer.println((new StringBuilder()).append(s).append("mCount=").append(mCount).append(" mLoadedCount=").append(mLoadedCount).append(" mUnpluggedCount=").append(mUnpluggedCount).append(" mPluggedCount=").append(mPluggedCount).toString());
        }

        public void onTimeStarted(long l, long l1, long l2)
        {
            mUnpluggedCount = mPluggedCount;
        }

        public void onTimeStopped(long l, long l1, long l2)
        {
            mPluggedCount = mCount;
        }

        void readSummaryFromParcelLocked(Parcel parcel)
        {
            mLoadedCount = parcel.readLong();
            mCount = mLoadedCount;
            long l = mLoadedCount;
            mPluggedCount = l;
            mUnpluggedCount = l;
        }

        void reset(boolean flag)
        {
            mCount = 0L;
            mUnpluggedCount = 0L;
            mPluggedCount = 0L;
            mLoadedCount = 0L;
            if(flag)
                detach();
        }

        void writeSummaryFromParcelLocked(Parcel parcel)
        {
            parcel.writeLong(mCount);
        }

        public void writeToParcel(Parcel parcel)
        {
            parcel.writeLong(mCount);
            parcel.writeLong(mLoadedCount);
            parcel.writeLong(mUnpluggedCount);
        }

        long mCount;
        long mLoadedCount;
        long mPluggedCount;
        final TimeBase mTimeBase;
        long mUnpluggedCount;

        LongSamplingCounter(TimeBase timebase)
        {
            mTimeBase = timebase;
            timebase.add(this);
        }

        LongSamplingCounter(TimeBase timebase, Parcel parcel)
        {
            mTimeBase = timebase;
            mPluggedCount = parcel.readLong();
            mCount = mPluggedCount;
            mLoadedCount = parcel.readLong();
            mUnpluggedCount = parcel.readLong();
            timebase.add(this);
        }
    }

    public static class LongSamplingCounterArray extends android.os.BatteryStats.LongCounterArray
        implements TimeBaseObs
    {

        private static long[] copyArray(long al[], long al1[])
        {
            if(al == null)
                return null;
            long al2[] = al1;
            if(al1 == null)
                al2 = new long[al.length];
            System.arraycopy(al, 0, al2, 0, al.length);
            return al2;
        }

        private static void fillArray(long al[], long l)
        {
            if(al != null)
                Arrays.fill(al, l);
        }

        public static LongSamplingCounterArray readFromParcel(Parcel parcel, TimeBase timebase)
        {
            if(parcel.readInt() != 0)
                return new LongSamplingCounterArray(timebase, parcel);
            else
                return null;
        }

        public static LongSamplingCounterArray readSummaryFromParcelLocked(Parcel parcel, TimeBase timebase)
        {
            if(parcel.readInt() != 0)
            {
                timebase = new LongSamplingCounterArray(timebase);
                timebase.readSummaryFromParcelLocked(parcel);
                return timebase;
            } else
            {
                return null;
            }
        }

        private void readSummaryFromParcelLocked(Parcel parcel)
        {
            mCounts = parcel.createLongArray();
            mLoadedCounts = copyArray(mCounts, mLoadedCounts);
            mUnpluggedCounts = copyArray(mCounts, mUnpluggedCounts);
            mPluggedCounts = copyArray(mCounts, mPluggedCounts);
        }

        private static void subtract(long al[], long al1[])
        {
            if(al1 == null)
                return;
            for(int i = 0; i < al.length; i++)
                al[i] = al[i] - al1[i];

        }

        private void writeSummaryToParcelLocked(Parcel parcel)
        {
            parcel.writeLongArray(mCounts);
        }

        public static void writeSummaryToParcelLocked(Parcel parcel, LongSamplingCounterArray longsamplingcounterarray)
        {
            if(longsamplingcounterarray != null)
            {
                parcel.writeInt(1);
                longsamplingcounterarray.writeSummaryToParcelLocked(parcel);
            } else
            {
                parcel.writeInt(0);
            }
        }

        private void writeToParcel(Parcel parcel)
        {
            parcel.writeLongArray(mCounts);
            parcel.writeLongArray(mLoadedCounts);
            parcel.writeLongArray(mUnpluggedCounts);
        }

        public static void writeToParcel(Parcel parcel, LongSamplingCounterArray longsamplingcounterarray)
        {
            if(longsamplingcounterarray != null)
            {
                parcel.writeInt(1);
                longsamplingcounterarray.writeToParcel(parcel);
            } else
            {
                parcel.writeInt(0);
            }
        }

        public void addCountLocked(long al[])
        {
            if(al == null)
                return;
            if(mTimeBase.isRunning())
            {
                if(mCounts == null)
                    mCounts = new long[al.length];
                for(int i = 0; i < al.length; i++)
                {
                    long al1[] = mCounts;
                    al1[i] = al1[i] + al[i];
                }

            }
        }

        public void detach()
        {
            mTimeBase.remove(this);
        }

        public long[] getCountsLocked(int i)
        {
            long al[];
            if(mTimeBase.isRunning())
                al = mCounts;
            else
                al = mPluggedCounts;
            al = copyArray(al, null);
            if(i != 2) goto _L2; else goto _L1
_L1:
            subtract(al, mUnpluggedCounts);
_L4:
            return al;
_L2:
            if(i != 0)
                subtract(al, mLoadedCounts);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public int getSize()
        {
            int i;
            if(mCounts == null)
                i = 0;
            else
                i = mCounts.length;
            return i;
        }

        public void logState(Printer printer, String s)
        {
            printer.println((new StringBuilder()).append(s).append("mCounts=").append(Arrays.toString(mCounts)).append(" mLoadedCounts=").append(Arrays.toString(mLoadedCounts)).append(" mUnpluggedCounts=").append(Arrays.toString(mUnpluggedCounts)).append(" mPluggedCounts=").append(Arrays.toString(mPluggedCounts)).toString());
        }

        public void onTimeStarted(long l, long l1, long l2)
        {
            mUnpluggedCounts = copyArray(mPluggedCounts, mUnpluggedCounts);
        }

        public void onTimeStopped(long l, long l1, long l2)
        {
            mPluggedCounts = copyArray(mCounts, mPluggedCounts);
        }

        public void reset(boolean flag)
        {
            fillArray(mCounts, 0L);
            fillArray(mLoadedCounts, 0L);
            fillArray(mPluggedCounts, 0L);
            fillArray(mUnpluggedCounts, 0L);
            if(flag)
                detach();
        }

        public long mCounts[];
        public long mLoadedCounts[];
        public long mPluggedCounts[];
        final TimeBase mTimeBase;
        public long mUnpluggedCounts[];

        public LongSamplingCounterArray(TimeBase timebase)
        {
            mTimeBase = timebase;
            timebase.add(this);
        }

        private LongSamplingCounterArray(TimeBase timebase, Parcel parcel)
        {
            mTimeBase = timebase;
            mPluggedCounts = parcel.createLongArray();
            mCounts = copyArray(mPluggedCounts, mCounts);
            mLoadedCounts = parcel.createLongArray();
            mUnpluggedCounts = parcel.createLongArray();
            timebase.add(this);
        }
    }

    final class MyHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            boolean flag;
            Object obj;
            flag = false;
            obj = BatteryStatsImpl._2D_get1(BatteryStatsImpl.this);
            message.what;
            JVM INSTR tableswitch 1 3: default 40
        //                       1 41
        //                       2 76
        //                       3 99;
               goto _L1 _L2 _L3 _L4
_L1:
            return;
_L2:
            message = BatteryStatsImpl.this;
            message;
            JVM INSTR monitorenter ;
            updateCpuTimeLocked(false);
            message;
            JVM INSTR monitorexit ;
            if(obj != null)
                ((BatteryCallback) (obj)).batteryNeedsCpuUpdate();
            continue; /* Loop/switch isn't completed */
            obj;
            throw obj;
_L3:
            if(obj != null)
            {
                if(message.arg1 != 0)
                    flag = true;
                ((BatteryCallback) (obj)).batteryPowerChanged(flag);
            }
            continue; /* Loop/switch isn't completed */
_L4:
            if(obj == null)
                continue; /* Loop/switch isn't completed */
            BatteryStatsImpl batterystatsimpl = BatteryStatsImpl.this;
            batterystatsimpl;
            JVM INSTR monitorenter ;
            if(mCharging)
                message = "android.os.action.CHARGING";
            else
                message = "android.os.action.DISCHARGING";
            batterystatsimpl;
            JVM INSTR monitorexit ;
            message = new Intent(message);
            message.addFlags(0x4000000);
            ((BatteryCallback) (obj)).batterySendBroadcast(message);
            continue; /* Loop/switch isn't completed */
            message;
            throw message;
            if(true) goto _L1; else goto _L5
_L5:
        }

        final BatteryStatsImpl this$0;

        public MyHandler(Looper looper)
        {
            this$0 = BatteryStatsImpl.this;
            super(looper, null, true);
        }
    }

    public abstract class OverflowArrayMap
    {

        public void add(String s, Object obj)
        {
            String s1 = s;
            if(s == null)
                s1 = "";
            mMap.put(s1, obj);
            if("*overflow*".equals(s1))
                mCurOverflow = obj;
        }

        public void cleanup()
        {
            mLastCleanupTime = SystemClock.elapsedRealtime();
            if(mActiveOverflow != null && mActiveOverflow.size() == 0)
                mActiveOverflow = null;
            if(mActiveOverflow != null) goto _L2; else goto _L1
_L1:
            if(mMap.containsKey("*overflow*"))
            {
                Slog.wtf("BatteryStatsImpl", (new StringBuilder()).append("Cleaning up with no active overflow, but have overflow entry ").append(mMap.get("*overflow*")).toString());
                mMap.remove("*overflow*");
            }
            mCurOverflow = null;
_L4:
            return;
_L2:
            if(mCurOverflow == null || mMap.containsKey("*overflow*") ^ true)
                Slog.wtf("BatteryStatsImpl", (new StringBuilder()).append("Cleaning up with active overflow, but no overflow entry: cur=").append(mCurOverflow).append(" map=").append(mMap.get("*overflow*")).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void clear()
        {
            mLastClearTime = SystemClock.elapsedRealtime();
            mMap.clear();
            mCurOverflow = null;
            mActiveOverflow = null;
        }

        public ArrayMap getMap()
        {
            return mMap;
        }

        public abstract Object instantiateObject();

        public Object startObject(String s)
        {
            String s1 = s;
            if(s == null)
                s1 = "";
            s = ((String) (mMap.get(s1)));
            if(s != null)
                return s;
            if(mActiveOverflow != null)
            {
                MutableInt mutableint = (MutableInt)mActiveOverflow.get(s1);
                if(mutableint != null)
                {
                    Object obj = mCurOverflow;
                    s = ((String) (obj));
                    if(obj == null)
                    {
                        Slog.wtf("BatteryStatsImpl", (new StringBuilder()).append("Have active overflow ").append(s1).append(" but null overflow").toString());
                        s = ((String) (instantiateObject()));
                        mCurOverflow = s;
                        mMap.put("*overflow*", s);
                    }
                    mutableint.value = mutableint.value + 1;
                    return s;
                }
            }
            if(mMap.size() >= BatteryStatsImpl._2D_get0())
            {
                Object obj1 = mCurOverflow;
                s = ((String) (obj1));
                if(obj1 == null)
                {
                    s = ((String) (instantiateObject()));
                    mCurOverflow = s;
                    mMap.put("*overflow*", s);
                }
                if(mActiveOverflow == null)
                    mActiveOverflow = new ArrayMap();
                mActiveOverflow.put(s1, new MutableInt(1));
                mLastOverflowTime = SystemClock.elapsedRealtime();
                return s;
            } else
            {
                s = ((String) (instantiateObject()));
                mMap.put(s1, s);
                return s;
            }
        }

        public Object stopObject(String s)
        {
            String s1 = s;
            if(s == null)
                s1 = "";
            s = ((String) (mMap.get(s1)));
            if(s != null)
                return s;
            if(mActiveOverflow != null)
            {
                s = (MutableInt)mActiveOverflow.get(s1);
                if(s != null)
                {
                    Object obj = mCurOverflow;
                    if(obj != null)
                    {
                        s.value = ((MutableInt) (s)).value - 1;
                        if(((MutableInt) (s)).value <= 0)
                        {
                            mActiveOverflow.remove(s1);
                            mLastOverflowFinishTime = SystemClock.elapsedRealtime();
                        }
                        return obj;
                    }
                }
            }
            s = new StringBuilder();
            s.append("Unable to find object for ");
            s.append(s1);
            s.append(" in uid ");
            s.append(mUid);
            s.append(" mapsize=");
            s.append(mMap.size());
            s.append(" activeoverflow=");
            s.append(mActiveOverflow);
            s.append(" curoverflow=");
            s.append(mCurOverflow);
            long l = SystemClock.elapsedRealtime();
            if(mLastOverflowTime != 0L)
            {
                s.append(" lastOverflowTime=");
                TimeUtils.formatDuration(mLastOverflowTime - l, s);
            }
            if(mLastOverflowFinishTime != 0L)
            {
                s.append(" lastOverflowFinishTime=");
                TimeUtils.formatDuration(mLastOverflowFinishTime - l, s);
            }
            if(mLastClearTime != 0L)
            {
                s.append(" lastClearTime=");
                TimeUtils.formatDuration(mLastClearTime - l, s);
            }
            if(mLastCleanupTime != 0L)
            {
                s.append(" lastCleanupTime=");
                TimeUtils.formatDuration(mLastCleanupTime - l, s);
            }
            Slog.wtf("BatteryStatsImpl", s.toString());
            return null;
        }

        private static final String OVERFLOW_NAME = "*overflow*";
        ArrayMap mActiveOverflow;
        Object mCurOverflow;
        long mLastCleanupTime;
        long mLastClearTime;
        long mLastOverflowFinishTime;
        long mLastOverflowTime;
        final ArrayMap mMap = new ArrayMap();
        final int mUid;
        final BatteryStatsImpl this$0;

        public OverflowArrayMap(int i)
        {
            this$0 = BatteryStatsImpl.this;
            super();
            mUid = i;
        }
    }

    public static interface PlatformIdleStateCallback
    {

        public abstract void fillLowPowerStats(RpmStats rpmstats);

        public abstract String getPlatformLowPowerStats();

        public abstract String getSubsystemLowPowerStats();
    }

    public static class SamplingTimer extends Timer
    {

        public void add(long l, int i)
        {
            update(mCurrentReportedTotalTime + l, mCurrentReportedCount + i);
        }

        protected int computeCurrentCountLocked()
        {
            int i = mCount;
            int j;
            if(mTimeBaseRunning && mTrackingReportedValues)
                j = mCurrentReportedCount - mUnpluggedReportedCount;
            else
                j = 0;
            return j + i;
        }

        protected long computeRunTimeLocked(long l)
        {
            long l1 = mTotalTime;
            if(mTimeBaseRunning && mTrackingReportedValues)
                l = mCurrentReportedTotalTime - mUnpluggedReportedTotalTime;
            else
                l = 0L;
            return l + l1;
        }

        public void endSample()
        {
            mTotalTime = computeRunTimeLocked(0L);
            mCount = computeCurrentCountLocked();
            mCurrentReportedTotalTime = 0L;
            mUnpluggedReportedTotalTime = 0L;
            mCurrentReportedCount = 0;
            mUnpluggedReportedCount = 0;
        }

        public int getUpdateVersion()
        {
            return mUpdateVersion;
        }

        public void logState(Printer printer, String s)
        {
            super.logState(printer, s);
            printer.println((new StringBuilder()).append(s).append("mCurrentReportedCount=").append(mCurrentReportedCount).append(" mUnpluggedReportedCount=").append(mUnpluggedReportedCount).append(" mCurrentReportedTotalTime=").append(mCurrentReportedTotalTime).append(" mUnpluggedReportedTotalTime=").append(mUnpluggedReportedTotalTime).toString());
        }

        public void onTimeStarted(long l, long l1, long l2)
        {
            super.onTimeStarted(l, l1, l2);
            if(mTrackingReportedValues)
            {
                mUnpluggedReportedTotalTime = mCurrentReportedTotalTime;
                mUnpluggedReportedCount = mCurrentReportedCount;
            }
            mTimeBaseRunning = true;
        }

        public void onTimeStopped(long l, long l1, long l2)
        {
            super.onTimeStopped(l, l1, l2);
            mTimeBaseRunning = false;
        }

        public boolean reset(boolean flag)
        {
            super.reset(flag);
            mTrackingReportedValues = false;
            mUnpluggedReportedTotalTime = 0L;
            mUnpluggedReportedCount = 0;
            return true;
        }

        public void setUpdateVersion(int i)
        {
            mUpdateVersion = i;
        }

        public void update(long l, int i)
        {
            if(mTimeBaseRunning && mTrackingReportedValues ^ true)
            {
                mUnpluggedReportedTotalTime = l;
                mUnpluggedReportedCount = i;
            }
            mTrackingReportedValues = true;
            if(l < mCurrentReportedTotalTime || i < mCurrentReportedCount)
                endSample();
            mCurrentReportedTotalTime = l;
            mCurrentReportedCount = i;
        }

        public void writeToParcel(Parcel parcel, long l)
        {
            super.writeToParcel(parcel, l);
            parcel.writeInt(mCurrentReportedCount);
            parcel.writeInt(mUnpluggedReportedCount);
            parcel.writeLong(mCurrentReportedTotalTime);
            parcel.writeLong(mUnpluggedReportedTotalTime);
            int i;
            if(mTrackingReportedValues)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
        }

        int mCurrentReportedCount;
        long mCurrentReportedTotalTime;
        boolean mTimeBaseRunning;
        boolean mTrackingReportedValues;
        int mUnpluggedReportedCount;
        long mUnpluggedReportedTotalTime;
        int mUpdateVersion;

        public SamplingTimer(Clocks clocks, TimeBase timebase)
        {
            super(clocks, 0, timebase);
            mTrackingReportedValues = false;
            mTimeBaseRunning = timebase.isRunning();
        }

        public SamplingTimer(Clocks clocks, TimeBase timebase, Parcel parcel)
        {
            boolean flag = true;
            super(clocks, 0, timebase, parcel);
            mCurrentReportedCount = parcel.readInt();
            mUnpluggedReportedCount = parcel.readInt();
            mCurrentReportedTotalTime = parcel.readLong();
            mUnpluggedReportedTotalTime = parcel.readLong();
            if(parcel.readInt() != 1)
                flag = false;
            mTrackingReportedValues = flag;
            mTimeBaseRunning = timebase.isRunning();
        }
    }

    public static class StopwatchTimer extends Timer
    {

        private static long refreshTimersLocked(long l, ArrayList arraylist, StopwatchTimer stopwatchtimer)
        {
            long l1 = 0L;
            int i = arraylist.size();
            for(int j = i - 1; j >= 0;)
            {
                StopwatchTimer stopwatchtimer1 = (StopwatchTimer)arraylist.get(j);
                long l2 = l - stopwatchtimer1.mUpdateTime;
                long l3 = l1;
                if(l2 > 0L)
                {
                    l3 = l2 / (long)i;
                    if(stopwatchtimer1 == stopwatchtimer)
                        l1 = l3;
                    stopwatchtimer1.mTotalTime = stopwatchtimer1.mTotalTime + l3;
                    l3 = l1;
                }
                stopwatchtimer1.mUpdateTime = l;
                j--;
                l1 = l3;
            }

            return l1;
        }

        protected int computeCurrentCountLocked()
        {
            return mCount;
        }

        protected long computeRunTimeLocked(long l)
        {
            long l1 = 0L;
            long l2 = l;
            if(mTimeout > 0L)
            {
                l2 = l;
                if(l > mUpdateTime + mTimeout)
                    l2 = mUpdateTime + mTimeout;
            }
            long l3 = mTotalTime;
            l = l1;
            if(mNesting > 0)
            {
                l = mUpdateTime;
                int i;
                if(mTimerPool != null)
                    i = mTimerPool.size();
                else
                    i = 1;
                l = (l2 - l) / (long)i;
            }
            return l + l3;
        }

        public void detach()
        {
            super.detach();
            if(mTimerPool != null)
                mTimerPool.remove(this);
        }

        public boolean isRunningLocked()
        {
            boolean flag = false;
            if(mNesting > 0)
                flag = true;
            return flag;
        }

        public void logState(Printer printer, String s)
        {
            super.logState(printer, s);
            printer.println((new StringBuilder()).append(s).append("mNesting=").append(mNesting).append(" mUpdateTime=").append(mUpdateTime).append(" mAcquireTime=").append(mAcquireTime).toString());
        }

        public void onTimeStopped(long l, long l1, long l2)
        {
            if(mNesting > 0)
            {
                super.onTimeStopped(l, l1, l2);
                mUpdateTime = l2;
            }
        }

        public void readSummaryFromParcelLocked(Parcel parcel)
        {
            super.readSummaryFromParcelLocked(parcel);
            mNesting = 0;
        }

        public boolean reset(boolean flag)
        {
            boolean flag1;
            if(mNesting <= 0)
                flag1 = true;
            else
                flag1 = false;
            if(!flag1)
                flag = false;
            super.reset(flag);
            if(mNesting > 0)
                mUpdateTime = mTimeBase.getRealtime(mClocks.elapsedRealtime() * 1000L);
            mAcquireTime = -1L;
            return flag1;
        }

        public void setMark(long l)
        {
            l = mTimeBase.getRealtime(1000L * l);
            if(mNesting > 0)
                if(mTimerPool != null)
                {
                    refreshTimersLocked(l, mTimerPool, this);
                } else
                {
                    mTotalTime = mTotalTime + (l - mUpdateTime);
                    mUpdateTime = l;
                }
            mTimeBeforeMark = mTotalTime;
        }

        public void setTimeout(long l)
        {
            mTimeout = l;
        }

        public void startRunningLocked(long l)
        {
            int i = mNesting;
            mNesting = i + 1;
            if(i == 0)
            {
                l = mTimeBase.getRealtime(1000L * l);
                mUpdateTime = l;
                if(mTimerPool != null)
                {
                    refreshTimersLocked(l, mTimerPool, null);
                    mTimerPool.add(this);
                }
                if(mTimeBase.isRunning())
                {
                    mCount = mCount + 1;
                    mAcquireTime = mTotalTime;
                } else
                {
                    mAcquireTime = -1L;
                }
            }
        }

        public void stopAllRunningLocked(long l)
        {
            if(mNesting > 0)
            {
                mNesting = 1;
                stopRunningLocked(l);
            }
        }

        public void stopRunningLocked(long l)
        {
            if(mNesting == 0)
                return;
            int i = mNesting - 1;
            mNesting = i;
            if(i == 0)
            {
                l = mTimeBase.getRealtime(1000L * l);
                if(mTimerPool != null)
                {
                    refreshTimersLocked(l, mTimerPool, null);
                    mTimerPool.remove(this);
                } else
                {
                    mNesting = 1;
                    mTotalTime = computeRunTimeLocked(l);
                    mNesting = 0;
                }
                if(mAcquireTime >= 0L && mTotalTime == mAcquireTime)
                    mCount = mCount - 1;
            }
        }

        public void writeToParcel(Parcel parcel, long l)
        {
            super.writeToParcel(parcel, l);
            parcel.writeLong(mUpdateTime);
        }

        long mAcquireTime;
        boolean mInList;
        int mNesting;
        long mTimeout;
        final ArrayList mTimerPool;
        final Uid mUid;
        long mUpdateTime;

        public StopwatchTimer(Clocks clocks, Uid uid, int i, ArrayList arraylist, TimeBase timebase)
        {
            super(clocks, i, timebase);
            mAcquireTime = -1L;
            mUid = uid;
            mTimerPool = arraylist;
        }

        public StopwatchTimer(Clocks clocks, Uid uid, int i, ArrayList arraylist, TimeBase timebase, Parcel parcel)
        {
            super(clocks, i, timebase, parcel);
            mAcquireTime = -1L;
            mUid = uid;
            mTimerPool = arraylist;
            mUpdateTime = parcel.readLong();
        }
    }

    public static class SystemClocks
        implements Clocks
    {

        public long elapsedRealtime()
        {
            return SystemClock.elapsedRealtime();
        }

        public long uptimeMillis()
        {
            return SystemClock.uptimeMillis();
        }

        public SystemClocks()
        {
        }
    }

    public static class TimeBase
    {

        public void add(TimeBaseObs timebaseobs)
        {
            mObservers.add(timebaseobs);
        }

        public long computeRealtime(long l, int i)
        {
            switch(i)
            {
            default:
                return 0L;

            case 0: // '\0'
                return mRealtime + getRealtime(l);

            case 1: // '\001'
                return getRealtime(l);

            case 2: // '\002'
                return getRealtime(l) - mUnpluggedRealtime;
            }
        }

        public long computeUptime(long l, int i)
        {
            switch(i)
            {
            default:
                return 0L;

            case 0: // '\0'
                return mUptime + getUptime(l);

            case 1: // '\001'
                return getUptime(l);

            case 2: // '\002'
                return getUptime(l) - mUnpluggedUptime;
            }
        }

        public void dump(PrintWriter printwriter, String s)
        {
            StringBuilder stringbuilder = new StringBuilder(128);
            printwriter.print(s);
            printwriter.print("mRunning=");
            printwriter.println(mRunning);
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("mUptime=");
            BatteryStatsImpl.formatTimeMs(stringbuilder, mUptime / 1000L);
            printwriter.println(stringbuilder.toString());
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("mRealtime=");
            BatteryStatsImpl.formatTimeMs(stringbuilder, mRealtime / 1000L);
            printwriter.println(stringbuilder.toString());
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("mPastUptime=");
            BatteryStatsImpl.formatTimeMs(stringbuilder, mPastUptime / 1000L);
            stringbuilder.append("mUptimeStart=");
            BatteryStatsImpl.formatTimeMs(stringbuilder, mUptimeStart / 1000L);
            stringbuilder.append("mUnpluggedUptime=");
            BatteryStatsImpl.formatTimeMs(stringbuilder, mUnpluggedUptime / 1000L);
            printwriter.println(stringbuilder.toString());
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("mPastRealtime=");
            BatteryStatsImpl.formatTimeMs(stringbuilder, mPastRealtime / 1000L);
            stringbuilder.append("mRealtimeStart=");
            BatteryStatsImpl.formatTimeMs(stringbuilder, mRealtimeStart / 1000L);
            stringbuilder.append("mUnpluggedRealtime=");
            BatteryStatsImpl.formatTimeMs(stringbuilder, mUnpluggedRealtime / 1000L);
            printwriter.println(stringbuilder.toString());
        }

        public long getRealtime(long l)
        {
            long l1 = mPastRealtime;
            long l2 = l1;
            if(mRunning)
                l2 = l1 + (l - mRealtimeStart);
            return l2;
        }

        public long getRealtimeStart()
        {
            return mRealtimeStart;
        }

        public long getUptime(long l)
        {
            long l1 = mPastUptime;
            long l2 = l1;
            if(mRunning)
                l2 = l1 + (l - mUptimeStart);
            return l2;
        }

        public long getUptimeStart()
        {
            return mUptimeStart;
        }

        public boolean hasObserver(TimeBaseObs timebaseobs)
        {
            return mObservers.contains(timebaseobs);
        }

        public void init(long l, long l1)
        {
            mRealtime = 0L;
            mUptime = 0L;
            mPastUptime = 0L;
            mPastRealtime = 0L;
            mUptimeStart = l;
            mRealtimeStart = l1;
            mUnpluggedUptime = getUptime(mUptimeStart);
            mUnpluggedRealtime = getRealtime(mRealtimeStart);
        }

        public boolean isRunning()
        {
            return mRunning;
        }

        public void readFromParcel(Parcel parcel)
        {
            mRunning = false;
            mUptime = parcel.readLong();
            mPastUptime = parcel.readLong();
            mUptimeStart = parcel.readLong();
            mRealtime = parcel.readLong();
            mPastRealtime = parcel.readLong();
            mRealtimeStart = parcel.readLong();
            mUnpluggedUptime = parcel.readLong();
            mUnpluggedRealtime = parcel.readLong();
        }

        public void readSummaryFromParcel(Parcel parcel)
        {
            mUptime = parcel.readLong();
            mRealtime = parcel.readLong();
        }

        public void remove(TimeBaseObs timebaseobs)
        {
            if(!mObservers.remove(timebaseobs))
                Slog.wtf("BatteryStatsImpl", (new StringBuilder()).append("Removed unknown observer: ").append(timebaseobs).toString());
        }

        public void reset(long l, long l1)
        {
            if(!mRunning)
            {
                mPastUptime = 0L;
                mPastRealtime = 0L;
            } else
            {
                mUptimeStart = l;
                mRealtimeStart = l1;
                mUnpluggedUptime = getUptime(l);
                mUnpluggedRealtime = getRealtime(l1);
            }
        }

        public boolean setRunning(boolean flag, long l, long l1)
        {
            if(mRunning != flag)
            {
                mRunning = flag;
                if(flag)
                {
                    mUptimeStart = l;
                    mRealtimeStart = l1;
                    l = getUptime(l);
                    mUnpluggedUptime = l;
                    long l2 = getRealtime(l1);
                    mUnpluggedRealtime = l2;
                    for(int i = mObservers.size() - 1; i >= 0; i--)
                        ((TimeBaseObs)mObservers.get(i)).onTimeStarted(l1, l, l2);

                } else
                {
                    mPastUptime = mPastUptime + (l - mUptimeStart);
                    mPastRealtime = mPastRealtime + (l1 - mRealtimeStart);
                    l = getUptime(l);
                    long l3 = getRealtime(l1);
                    for(int j = mObservers.size() - 1; j >= 0; j--)
                        ((TimeBaseObs)mObservers.get(j)).onTimeStopped(l1, l, l3);

                }
                return true;
            } else
            {
                return false;
            }
        }

        public void writeSummaryToParcel(Parcel parcel, long l, long l1)
        {
            parcel.writeLong(computeUptime(l, 0));
            parcel.writeLong(computeRealtime(l1, 0));
        }

        public void writeToParcel(Parcel parcel, long l, long l1)
        {
            l = getUptime(l);
            l1 = getRealtime(l1);
            parcel.writeLong(mUptime);
            parcel.writeLong(l);
            parcel.writeLong(mUptimeStart);
            parcel.writeLong(mRealtime);
            parcel.writeLong(l1);
            parcel.writeLong(mRealtimeStart);
            parcel.writeLong(mUnpluggedUptime);
            parcel.writeLong(mUnpluggedRealtime);
        }

        protected final ArrayList mObservers = new ArrayList();
        protected long mPastRealtime;
        protected long mPastUptime;
        protected long mRealtime;
        protected long mRealtimeStart;
        protected boolean mRunning;
        protected long mUnpluggedRealtime;
        protected long mUnpluggedUptime;
        protected long mUptime;
        protected long mUptimeStart;

        public TimeBase()
        {
        }
    }

    public static interface TimeBaseObs
    {

        public abstract void onTimeStarted(long l, long l1, long l2);

        public abstract void onTimeStopped(long l, long l1, long l2);
    }

    public static abstract class Timer extends android.os.BatteryStats.Timer
        implements TimeBaseObs
    {

        public static void writeTimerToParcel(Parcel parcel, Timer timer, long l)
        {
            if(timer == null)
            {
                parcel.writeInt(0);
                return;
            } else
            {
                parcel.writeInt(1);
                timer.writeToParcel(parcel, l);
                return;
            }
        }

        protected abstract int computeCurrentCountLocked();

        protected abstract long computeRunTimeLocked(long l);

        public void detach()
        {
            mTimeBase.remove(this);
        }

        public int getCountLocked(int i)
        {
            int j = computeCurrentCountLocked();
            if(i != 2) goto _L2; else goto _L1
_L1:
            int k = j - mUnpluggedCount;
_L4:
            return k;
_L2:
            k = j;
            if(i != 0)
                k = j - mLoadedCount;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public long getTimeSinceMarkLocked(long l)
        {
            return computeRunTimeLocked(mTimeBase.getRealtime(l)) - mTimeBeforeMark;
        }

        public long getTotalTimeLocked(long l, int i)
        {
            long l1 = computeRunTimeLocked(mTimeBase.getRealtime(l));
            if(i != 2) goto _L2; else goto _L1
_L1:
            l = l1 - mUnpluggedTime;
_L4:
            return l;
_L2:
            l = l1;
            if(i != 0)
                l = l1 - mLoadedTime;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void logState(Printer printer, String s)
        {
            printer.println((new StringBuilder()).append(s).append("mCount=").append(mCount).append(" mLoadedCount=").append(mLoadedCount).append(" mLastCount=").append(mLastCount).append(" mUnpluggedCount=").append(mUnpluggedCount).toString());
            printer.println((new StringBuilder()).append(s).append("mTotalTime=").append(mTotalTime).append(" mLoadedTime=").append(mLoadedTime).toString());
            printer.println((new StringBuilder()).append(s).append("mLastTime=").append(mLastTime).append(" mUnpluggedTime=").append(mUnpluggedTime).toString());
        }

        public void onTimeStarted(long l, long l1, long l2)
        {
            mUnpluggedTime = computeRunTimeLocked(l2);
            mUnpluggedCount = computeCurrentCountLocked();
        }

        public void onTimeStopped(long l, long l1, long l2)
        {
            mTotalTime = computeRunTimeLocked(l2);
            mCount = computeCurrentCountLocked();
        }

        public void readSummaryFromParcelLocked(Parcel parcel)
        {
            long l = parcel.readLong();
            mLoadedTime = l;
            mTotalTime = l;
            mLastTime = 0L;
            mUnpluggedTime = mTotalTime;
            int i = parcel.readInt();
            mLoadedCount = i;
            mCount = i;
            mLastCount = 0;
            mUnpluggedCount = mCount;
            mTimeBeforeMark = mTotalTime;
        }

        public boolean reset(boolean flag)
        {
            mTimeBeforeMark = 0L;
            mLastTime = 0L;
            mLoadedTime = 0L;
            mTotalTime = 0L;
            mLastCount = 0;
            mLoadedCount = 0;
            mCount = 0;
            if(flag)
                detach();
            return true;
        }

        public void writeSummaryFromParcelLocked(Parcel parcel, long l)
        {
            parcel.writeLong(computeRunTimeLocked(mTimeBase.getRealtime(l)));
            parcel.writeInt(computeCurrentCountLocked());
        }

        public void writeToParcel(Parcel parcel, long l)
        {
            parcel.writeInt(computeCurrentCountLocked());
            parcel.writeInt(mLoadedCount);
            parcel.writeInt(mUnpluggedCount);
            parcel.writeLong(computeRunTimeLocked(mTimeBase.getRealtime(l)));
            parcel.writeLong(mLoadedTime);
            parcel.writeLong(mUnpluggedTime);
            parcel.writeLong(mTimeBeforeMark);
        }

        protected final Clocks mClocks;
        protected int mCount;
        protected int mLastCount;
        protected long mLastTime;
        protected int mLoadedCount;
        protected long mLoadedTime;
        protected final TimeBase mTimeBase;
        protected long mTimeBeforeMark;
        protected long mTotalTime;
        protected final int mType;
        protected int mUnpluggedCount;
        protected long mUnpluggedTime;

        public Timer(Clocks clocks, int i, TimeBase timebase)
        {
            mClocks = clocks;
            mType = i;
            mTimeBase = timebase;
            timebase.add(this);
        }

        public Timer(Clocks clocks, int i, TimeBase timebase, Parcel parcel)
        {
            mClocks = clocks;
            mType = i;
            mTimeBase = timebase;
            mCount = parcel.readInt();
            mLoadedCount = parcel.readInt();
            mLastCount = 0;
            mUnpluggedCount = parcel.readInt();
            mTotalTime = parcel.readLong();
            mLoadedTime = parcel.readLong();
            mLastTime = 0L;
            mUnpluggedTime = parcel.readLong();
            mTimeBeforeMark = parcel.readLong();
            timebase.add(this);
        }
    }

    public static class Uid extends android.os.BatteryStats.Uid
    {

        static LongSamplingCounter _2D_get0(Uid uid)
        {
            return uid.mMobileRadioApWakeupCount;
        }

        static LongSamplingCounter _2D_get1(Uid uid)
        {
            return uid.mWifiRadioApWakeupCount;
        }

        static LongSamplingCounter _2D_set0(Uid uid, LongSamplingCounter longsamplingcounter)
        {
            uid.mMobileRadioApWakeupCount = longsamplingcounter;
            return longsamplingcounter;
        }

        static LongSamplingCounter _2D_set1(Uid uid, LongSamplingCounter longsamplingcounter)
        {
            uid.mWifiRadioApWakeupCount = longsamplingcounter;
            return longsamplingcounter;
        }

        public DualTimer createAggregatedPartialWakelockTimerLocked()
        {
            if(mAggregatedPartialWakelockTimer == null)
                mAggregatedPartialWakelockTimer = new DualTimer(mBsi.mClocks, this, 20, null, mBsi.mOnBatteryScreenOffTimeBase, mOnBatteryScreenOffBackgroundTimeBase);
            return mAggregatedPartialWakelockTimer;
        }

        public StopwatchTimer createAudioTurnedOnTimerLocked()
        {
            if(mAudioTurnedOnTimer == null)
                mAudioTurnedOnTimer = new StopwatchTimer(mBsi.mClocks, this, 15, mBsi.mAudioTurnedOnTimers, mBsi.mOnBatteryTimeBase);
            return mAudioTurnedOnTimer;
        }

        public Counter createBluetoothScanResultBgCounterLocked()
        {
            if(mBluetoothScanResultBgCounter == null)
                mBluetoothScanResultBgCounter = new Counter(mOnBatteryBackgroundTimeBase);
            return mBluetoothScanResultBgCounter;
        }

        public Counter createBluetoothScanResultCounterLocked()
        {
            if(mBluetoothScanResultCounter == null)
                mBluetoothScanResultCounter = new Counter(mBsi.mOnBatteryTimeBase);
            return mBluetoothScanResultCounter;
        }

        public DualTimer createBluetoothScanTimerLocked()
        {
            if(mBluetoothScanTimer == null)
                mBluetoothScanTimer = new DualTimer(mBsi.mClocks, this, 19, mBsi.mBluetoothScanOnTimers, mBsi.mOnBatteryTimeBase, mOnBatteryBackgroundTimeBase);
            return mBluetoothScanTimer;
        }

        public DualTimer createBluetoothUnoptimizedScanTimerLocked()
        {
            if(mBluetoothUnoptimizedScanTimer == null)
                mBluetoothUnoptimizedScanTimer = new DualTimer(mBsi.mClocks, this, 21, null, mBsi.mOnBatteryTimeBase, mOnBatteryBackgroundTimeBase);
            return mBluetoothUnoptimizedScanTimer;
        }

        public StopwatchTimer createCameraTurnedOnTimerLocked()
        {
            if(mCameraTurnedOnTimer == null)
                mCameraTurnedOnTimer = new StopwatchTimer(mBsi.mClocks, this, 17, mBsi.mCameraTurnedOnTimers, mBsi.mOnBatteryTimeBase);
            return mCameraTurnedOnTimer;
        }

        public StopwatchTimer createFlashlightTurnedOnTimerLocked()
        {
            if(mFlashlightTurnedOnTimer == null)
                mFlashlightTurnedOnTimer = new StopwatchTimer(mBsi.mClocks, this, 16, mBsi.mFlashlightTurnedOnTimers, mBsi.mOnBatteryTimeBase);
            return mFlashlightTurnedOnTimer;
        }

        public StopwatchTimer createForegroundActivityTimerLocked()
        {
            if(mForegroundActivityTimer == null)
                mForegroundActivityTimer = new StopwatchTimer(mBsi.mClocks, this, 10, null, mBsi.mOnBatteryTimeBase);
            return mForegroundActivityTimer;
        }

        public StopwatchTimer createForegroundServiceTimerLocked()
        {
            if(mForegroundServiceTimer == null)
                mForegroundServiceTimer = new StopwatchTimer(mBsi.mClocks, this, 22, null, mBsi.mOnBatteryTimeBase);
            return mForegroundServiceTimer;
        }

        public BatchTimer createVibratorOnTimerLocked()
        {
            if(mVibratorOnTimer == null)
                mVibratorOnTimer = new BatchTimer(mBsi.mClocks, this, 9, mBsi.mOnBatteryTimeBase);
            return mVibratorOnTimer;
        }

        public StopwatchTimer createVideoTurnedOnTimerLocked()
        {
            if(mVideoTurnedOnTimer == null)
                mVideoTurnedOnTimer = new StopwatchTimer(mBsi.mClocks, this, 8, mBsi.mVideoTurnedOnTimers, mBsi.mOnBatteryTimeBase);
            return mVideoTurnedOnTimer;
        }

        public volatile android.os.BatteryStats.Timer getAggregatedPartialWakelockTimer()
        {
            return getAggregatedPartialWakelockTimer();
        }

        public Timer getAggregatedPartialWakelockTimer()
        {
            return mAggregatedPartialWakelockTimer;
        }

        public volatile android.os.BatteryStats.Timer getAudioTurnedOnTimer()
        {
            return getAudioTurnedOnTimer();
        }

        public Timer getAudioTurnedOnTimer()
        {
            return mAudioTurnedOnTimer;
        }

        public BatteryStatsImpl getBatteryStats()
        {
            return mBsi;
        }

        public android.os.BatteryStats.ControllerActivityCounter getBluetoothControllerActivity()
        {
            return mBluetoothControllerActivity;
        }

        public volatile android.os.BatteryStats.Timer getBluetoothScanBackgroundTimer()
        {
            return getBluetoothScanBackgroundTimer();
        }

        public Timer getBluetoothScanBackgroundTimer()
        {
            if(mBluetoothScanTimer == null)
                return null;
            else
                return mBluetoothScanTimer.getSubTimer();
        }

        public volatile android.os.BatteryStats.Counter getBluetoothScanResultBgCounter()
        {
            return getBluetoothScanResultBgCounter();
        }

        public Counter getBluetoothScanResultBgCounter()
        {
            return mBluetoothScanResultBgCounter;
        }

        public volatile android.os.BatteryStats.Counter getBluetoothScanResultCounter()
        {
            return getBluetoothScanResultCounter();
        }

        public Counter getBluetoothScanResultCounter()
        {
            return mBluetoothScanResultCounter;
        }

        public volatile android.os.BatteryStats.Timer getBluetoothScanTimer()
        {
            return getBluetoothScanTimer();
        }

        public Timer getBluetoothScanTimer()
        {
            return mBluetoothScanTimer;
        }

        public volatile android.os.BatteryStats.Timer getBluetoothUnoptimizedScanBackgroundTimer()
        {
            return getBluetoothUnoptimizedScanBackgroundTimer();
        }

        public Timer getBluetoothUnoptimizedScanBackgroundTimer()
        {
            if(mBluetoothUnoptimizedScanTimer == null)
                return null;
            else
                return mBluetoothUnoptimizedScanTimer.getSubTimer();
        }

        public volatile android.os.BatteryStats.Timer getBluetoothUnoptimizedScanTimer()
        {
            return getBluetoothUnoptimizedScanTimer();
        }

        public Timer getBluetoothUnoptimizedScanTimer()
        {
            return mBluetoothUnoptimizedScanTimer;
        }

        public volatile android.os.BatteryStats.Timer getCameraTurnedOnTimer()
        {
            return getCameraTurnedOnTimer();
        }

        public Timer getCameraTurnedOnTimer()
        {
            return mCameraTurnedOnTimer;
        }

        public long[] getCpuFreqTimes(int i)
        {
            if(mCpuFreqTimeMs == null)
                return null;
            long al[] = mCpuFreqTimeMs.getCountsLocked(i);
            if(al == null)
                return null;
            for(i = 0; i < al.length; i++)
                if(al[i] != 0L)
                    return al;

            return null;
        }

        public volatile android.os.BatteryStats.Timer getFlashlightTurnedOnTimer()
        {
            return getFlashlightTurnedOnTimer();
        }

        public Timer getFlashlightTurnedOnTimer()
        {
            return mFlashlightTurnedOnTimer;
        }

        public volatile android.os.BatteryStats.Timer getForegroundActivityTimer()
        {
            return getForegroundActivityTimer();
        }

        public Timer getForegroundActivityTimer()
        {
            return mForegroundActivityTimer;
        }

        public volatile android.os.BatteryStats.Timer getForegroundServiceTimer()
        {
            return getForegroundServiceTimer();
        }

        public Timer getForegroundServiceTimer()
        {
            return mForegroundServiceTimer;
        }

        public long getFullWifiLockTime(long l, int i)
        {
            if(mFullWifiLockTimer == null)
                return 0L;
            else
                return mFullWifiLockTimer.getTotalTimeLocked(l, i);
        }

        public ArrayMap getJobCompletionStats()
        {
            return mJobCompletions;
        }

        public ArrayMap getJobStats()
        {
            return mJobStats.getMap();
        }

        public int getMobileRadioActiveCount(int i)
        {
            if(mMobileRadioActiveCount != null)
                i = (int)mMobileRadioActiveCount.getCountLocked(i);
            else
                i = 0;
            return i;
        }

        public long getMobileRadioActiveTime(int i)
        {
            long l;
            if(mMobileRadioActiveTime != null)
                l = mMobileRadioActiveTime.getCountLocked(i);
            else
                l = 0L;
            return l;
        }

        public long getMobileRadioApWakeupCount(int i)
        {
            if(mMobileRadioApWakeupCount != null)
                return mMobileRadioApWakeupCount.getCountLocked(i);
            else
                return 0L;
        }

        public android.os.BatteryStats.ControllerActivityCounter getModemControllerActivity()
        {
            return mModemControllerActivity;
        }

        public long getNetworkActivityBytes(int i, int j)
        {
            if(mNetworkByteActivityCounters != null && i >= 0 && i < mNetworkByteActivityCounters.length)
                return mNetworkByteActivityCounters[i].getCountLocked(j);
            else
                return 0L;
        }

        public long getNetworkActivityPackets(int i, int j)
        {
            if(mNetworkPacketActivityCounters != null && i >= 0 && i < mNetworkPacketActivityCounters.length)
                return mNetworkPacketActivityCounters[i].getCountLocked(j);
            else
                return 0L;
        }

        public ControllerActivityCounterImpl getOrCreateBluetoothControllerActivityLocked()
        {
            if(mBluetoothControllerActivity == null)
                mBluetoothControllerActivity = new ControllerActivityCounterImpl(mBsi.mOnBatteryTimeBase, 1);
            return mBluetoothControllerActivity;
        }

        public ControllerActivityCounterImpl getOrCreateModemControllerActivityLocked()
        {
            if(mModemControllerActivity == null)
                mModemControllerActivity = new ControllerActivityCounterImpl(mBsi.mOnBatteryTimeBase, 5);
            return mModemControllerActivity;
        }

        public ControllerActivityCounterImpl getOrCreateWifiControllerActivityLocked()
        {
            if(mWifiControllerActivity == null)
                mWifiControllerActivity = new ControllerActivityCounterImpl(mBsi.mOnBatteryTimeBase, 1);
            return mWifiControllerActivity;
        }

        public ArrayMap getPackageStats()
        {
            return mPackageStats;
        }

        public Pkg getPackageStatsLocked(String s)
        {
            Pkg pkg = (Pkg)mPackageStats.get(s);
            Pkg pkg1 = pkg;
            if(pkg == null)
            {
                pkg1 = new Pkg(mBsi);
                mPackageStats.put(s, pkg1);
            }
            return pkg1;
        }

        public SparseArray getPidStats()
        {
            return mPids;
        }

        public android.os.BatteryStats.Uid.Pid getPidStatsLocked(int i)
        {
            android.os.BatteryStats.Uid.Pid pid = (android.os.BatteryStats.Uid.Pid)mPids.get(i);
            android.os.BatteryStats.Uid.Pid pid1 = pid;
            if(pid == null)
            {
                pid1 = new android.os.BatteryStats.Uid.Pid(this);
                mPids.put(i, pid1);
            }
            return pid1;
        }

        public long getProcessStateTime(int i, long l, int j)
        {
            if(i < 0 || i >= 6)
                return 0L;
            if(mProcessStateTimer[i] == null)
                return 0L;
            else
                return mProcessStateTimer[i].getTotalTimeLocked(l, j);
        }

        public volatile android.os.BatteryStats.Timer getProcessStateTimer(int i)
        {
            return getProcessStateTimer(i);
        }

        public Timer getProcessStateTimer(int i)
        {
            if(i < 0 || i >= 6)
                return null;
            else
                return mProcessStateTimer[i];
        }

        public ArrayMap getProcessStats()
        {
            return mProcessStats;
        }

        public Proc getProcessStatsLocked(String s)
        {
            Proc proc = (Proc)mProcessStats.get(s);
            Proc proc1 = proc;
            if(proc == null)
            {
                proc1 = new Proc(mBsi, s);
                mProcessStats.put(s, proc1);
            }
            return proc1;
        }

        public long[] getScreenOffCpuFreqTimes(int i)
        {
            if(mScreenOffCpuFreqTimeMs == null)
                return null;
            long al[] = mScreenOffCpuFreqTimeMs.getCountsLocked(i);
            if(al == null)
                return null;
            for(i = 0; i < al.length; i++)
                if(al[i] != 0L)
                    return al;

            return null;
        }

        public SparseArray getSensorStats()
        {
            return mSensorStats;
        }

        public DualTimer getSensorTimerLocked(int i, boolean flag)
        {
            Object obj = (Sensor)mSensorStats.get(i);
            Sensor sensor = ((Sensor) (obj));
            if(obj == null)
            {
                if(!flag)
                    return null;
                sensor = new Sensor(mBsi, this, i);
                mSensorStats.put(i, sensor);
            }
            obj = sensor.mTimer;
            if(obj != null)
                return ((DualTimer) (obj));
            ArrayList arraylist = (ArrayList)mBsi.mSensorTimers.get(i);
            obj = arraylist;
            if(arraylist == null)
            {
                obj = new ArrayList();
                mBsi.mSensorTimers.put(i, obj);
            }
            obj = new DualTimer(mBsi.mClocks, this, 3, ((ArrayList) (obj)), mBsi.mOnBatteryTimeBase, mOnBatteryBackgroundTimeBase);
            sensor.mTimer = ((DualTimer) (obj));
            return ((DualTimer) (obj));
        }

        public Pkg.Serv getServiceStatsLocked(String s, String s1)
        {
            Pkg pkg = getPackageStatsLocked(s);
            Pkg.Serv serv = (Pkg.Serv)pkg.mServiceStats.get(s1);
            s = serv;
            if(serv == null)
            {
                s = pkg.newServiceStatsLocked();
                pkg.mServiceStats.put(s1, s);
            }
            return s;
        }

        public ArrayMap getSyncStats()
        {
            return mSyncStats.getMap();
        }

        public long getSystemCpuTimeUs(int i)
        {
            return mSystemCpuTime.getCountLocked(i);
        }

        public long getTimeAtCpuSpeed(int i, int j, int k)
        {
            if(mCpuClusterSpeedTimesUs != null && i >= 0 && i < mCpuClusterSpeedTimesUs.length)
            {
                LongSamplingCounter alongsamplingcounter[] = mCpuClusterSpeedTimesUs[i];
                if(alongsamplingcounter != null && j >= 0 && j < alongsamplingcounter.length)
                {
                    LongSamplingCounter longsamplingcounter = alongsamplingcounter[j];
                    if(longsamplingcounter != null)
                        return longsamplingcounter.getCountLocked(k);
                }
            }
            return 0L;
        }

        public int getUid()
        {
            return mUid;
        }

        public int getUserActivityCount(int i, int j)
        {
            if(mUserActivityCounters == null)
                return 0;
            else
                return mUserActivityCounters[i].getCountLocked(j);
        }

        public long getUserCpuTimeUs(int i)
        {
            return mUserCpuTime.getCountLocked(i);
        }

        public volatile android.os.BatteryStats.Timer getVibratorOnTimer()
        {
            return getVibratorOnTimer();
        }

        public Timer getVibratorOnTimer()
        {
            return mVibratorOnTimer;
        }

        public volatile android.os.BatteryStats.Timer getVideoTurnedOnTimer()
        {
            return getVideoTurnedOnTimer();
        }

        public Timer getVideoTurnedOnTimer()
        {
            return mVideoTurnedOnTimer;
        }

        public ArrayMap getWakelockStats()
        {
            return mWakelockStats.getMap();
        }

        public StopwatchTimer getWakelockTimerLocked(Wakelock wakelock, int i)
        {
            if(wakelock == null)
                return null;
            StopwatchTimer stopwatchtimer2;
            StopwatchTimer stopwatchtimer5;
            switch(i)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("type=").append(i).toString());

            case 0: // '\0'
                DualTimer dualtimer = wakelock.mTimerPartial;
                DualTimer dualtimer1 = dualtimer;
                if(dualtimer == null)
                {
                    dualtimer1 = new DualTimer(mBsi.mClocks, this, 0, mBsi.mPartialTimers, mBsi.mOnBatteryScreenOffTimeBase, mOnBatteryScreenOffBackgroundTimeBase);
                    wakelock.mTimerPartial = dualtimer1;
                }
                return dualtimer1;

            case 1: // '\001'
                StopwatchTimer stopwatchtimer = wakelock.mTimerFull;
                StopwatchTimer stopwatchtimer3 = stopwatchtimer;
                if(stopwatchtimer == null)
                {
                    stopwatchtimer3 = new StopwatchTimer(mBsi.mClocks, this, 1, mBsi.mFullTimers, mBsi.mOnBatteryTimeBase);
                    wakelock.mTimerFull = stopwatchtimer3;
                }
                return stopwatchtimer3;

            case 2: // '\002'
                StopwatchTimer stopwatchtimer1 = wakelock.mTimerWindow;
                StopwatchTimer stopwatchtimer4 = stopwatchtimer1;
                if(stopwatchtimer1 == null)
                {
                    stopwatchtimer4 = new StopwatchTimer(mBsi.mClocks, this, 2, mBsi.mWindowTimers, mBsi.mOnBatteryTimeBase);
                    wakelock.mTimerWindow = stopwatchtimer4;
                }
                return stopwatchtimer4;

            case 18: // '\022'
                stopwatchtimer2 = wakelock.mTimerDraw;
                stopwatchtimer5 = stopwatchtimer2;
                break;
            }
            if(stopwatchtimer2 == null)
            {
                stopwatchtimer5 = new StopwatchTimer(mBsi.mClocks, this, 18, mBsi.mDrawTimers, mBsi.mOnBatteryTimeBase);
                wakelock.mTimerDraw = stopwatchtimer5;
            }
            return stopwatchtimer5;
        }

        public int getWifiBatchedScanCount(int i, int j)
        {
            if(i < 0 || i >= 5)
                return 0;
            if(mWifiBatchedScanTimer[i] == null)
                return 0;
            else
                return mWifiBatchedScanTimer[i].getCountLocked(j);
        }

        public long getWifiBatchedScanTime(int i, long l, int j)
        {
            if(i < 0 || i >= 5)
                return 0L;
            if(mWifiBatchedScanTimer[i] == null)
                return 0L;
            else
                return mWifiBatchedScanTimer[i].getTotalTimeLocked(l, j);
        }

        public android.os.BatteryStats.ControllerActivityCounter getWifiControllerActivity()
        {
            return mWifiControllerActivity;
        }

        public long getWifiMulticastTime(long l, int i)
        {
            if(mWifiMulticastTimer == null)
                return 0L;
            else
                return mWifiMulticastTimer.getTotalTimeLocked(l, i);
        }

        public long getWifiRadioApWakeupCount(int i)
        {
            if(mWifiRadioApWakeupCount != null)
                return mWifiRadioApWakeupCount.getCountLocked(i);
            else
                return 0L;
        }

        public long getWifiRunningTime(long l, int i)
        {
            if(mWifiRunningTimer == null)
                return 0L;
            else
                return mWifiRunningTimer.getTotalTimeLocked(l, i);
        }

        public long getWifiScanActualTime(long l)
        {
            if(mWifiScanTimer == null)
            {
                return 0L;
            } else
            {
                l = (500L + l) / 1000L;
                return mWifiScanTimer.getTotalDurationMsLocked(l) * 1000L;
            }
        }

        public int getWifiScanBackgroundCount(int i)
        {
            if(mWifiScanTimer == null || mWifiScanTimer.getSubTimer() == null)
                return 0;
            else
                return mWifiScanTimer.getSubTimer().getCountLocked(i);
        }

        public long getWifiScanBackgroundTime(long l)
        {
            if(mWifiScanTimer == null || mWifiScanTimer.getSubTimer() == null)
            {
                return 0L;
            } else
            {
                l = (500L + l) / 1000L;
                return mWifiScanTimer.getSubTimer().getTotalDurationMsLocked(l) * 1000L;
            }
        }

        public int getWifiScanCount(int i)
        {
            if(mWifiScanTimer == null)
                return 0;
            else
                return mWifiScanTimer.getCountLocked(i);
        }

        public long getWifiScanTime(long l, int i)
        {
            if(mWifiScanTimer == null)
                return 0L;
            else
                return mWifiScanTimer.getTotalTimeLocked(l, i);
        }

        public boolean hasNetworkActivity()
        {
            boolean flag;
            if(mNetworkByteActivityCounters != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean hasUserActivity()
        {
            boolean flag;
            if(mUserActivityCounters != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        void initNetworkActivityLocked()
        {
            mNetworkByteActivityCounters = new LongSamplingCounter[10];
            mNetworkPacketActivityCounters = new LongSamplingCounter[10];
            for(int i = 0; i < 10; i++)
            {
                mNetworkByteActivityCounters[i] = new LongSamplingCounter(mBsi.mOnBatteryTimeBase);
                mNetworkPacketActivityCounters[i] = new LongSamplingCounter(mBsi.mOnBatteryTimeBase);
            }

            mMobileRadioActiveTime = new LongSamplingCounter(mBsi.mOnBatteryTimeBase);
            mMobileRadioActiveCount = new LongSamplingCounter(mBsi.mOnBatteryTimeBase);
        }

        void initUserActivityLocked()
        {
            mUserActivityCounters = new Counter[4];
            for(int i = 0; i < 4; i++)
                mUserActivityCounters[i] = new Counter(mBsi.mOnBatteryTimeBase);

        }

        public boolean isInBackground()
        {
            boolean flag;
            if(mProcessState >= 4)
                flag = true;
            else
                flag = false;
            return flag;
        }

        void makeProcessState(int i, Parcel parcel)
        {
            if(i < 0 || i >= 6)
                return;
            if(parcel == null)
                mProcessStateTimer[i] = new StopwatchTimer(mBsi.mClocks, this, 12, null, mBsi.mOnBatteryTimeBase);
            else
                mProcessStateTimer[i] = new StopwatchTimer(mBsi.mClocks, this, 12, null, mBsi.mOnBatteryTimeBase, parcel);
        }

        void makeWifiBatchedScanBin(int i, Parcel parcel)
        {
            if(i < 0 || i >= 5)
                return;
            ArrayList arraylist = (ArrayList)mBsi.mWifiBatchedScanTimers.get(i);
            ArrayList arraylist1 = arraylist;
            if(arraylist == null)
            {
                arraylist1 = new ArrayList();
                mBsi.mWifiBatchedScanTimers.put(i, arraylist1);
            }
            if(parcel == null)
                mWifiBatchedScanTimer[i] = new StopwatchTimer(mBsi.mClocks, this, 11, arraylist1, mBsi.mOnBatteryTimeBase);
            else
                mWifiBatchedScanTimer[i] = new StopwatchTimer(mBsi.mClocks, this, 11, arraylist1, mBsi.mOnBatteryTimeBase, parcel);
        }

        public void noteActivityPausedLocked(long l)
        {
            if(mForegroundActivityTimer != null)
                mForegroundActivityTimer.stopRunningLocked(l);
        }

        public void noteActivityResumedLocked(long l)
        {
            createForegroundActivityTimerLocked().startRunningLocked(l);
        }

        public void noteAudioTurnedOffLocked(long l)
        {
            if(mAudioTurnedOnTimer != null)
                mAudioTurnedOnTimer.stopRunningLocked(l);
        }

        public void noteAudioTurnedOnLocked(long l)
        {
            createAudioTurnedOnTimerLocked().startRunningLocked(l);
        }

        public void noteBluetoothScanResultsLocked(int i)
        {
            createBluetoothScanResultCounterLocked().addAtomic(i);
            createBluetoothScanResultBgCounterLocked().addAtomic(i);
        }

        public void noteBluetoothScanStartedLocked(long l, boolean flag)
        {
            createBluetoothScanTimerLocked().startRunningLocked(l);
            if(flag)
                createBluetoothUnoptimizedScanTimerLocked().startRunningLocked(l);
        }

        public void noteBluetoothScanStoppedLocked(long l, boolean flag)
        {
            if(mBluetoothScanTimer != null)
                mBluetoothScanTimer.stopRunningLocked(l);
            if(flag && mBluetoothUnoptimizedScanTimer != null)
                mBluetoothUnoptimizedScanTimer.stopRunningLocked(l);
        }

        public void noteCameraTurnedOffLocked(long l)
        {
            if(mCameraTurnedOnTimer != null)
                mCameraTurnedOnTimer.stopRunningLocked(l);
        }

        public void noteCameraTurnedOnLocked(long l)
        {
            createCameraTurnedOnTimerLocked().startRunningLocked(l);
        }

        public void noteFlashlightTurnedOffLocked(long l)
        {
            if(mFlashlightTurnedOnTimer != null)
                mFlashlightTurnedOnTimer.stopRunningLocked(l);
        }

        public void noteFlashlightTurnedOnLocked(long l)
        {
            createFlashlightTurnedOnTimerLocked().startRunningLocked(l);
        }

        public void noteForegroundServicePausedLocked(long l)
        {
            if(mForegroundServiceTimer != null)
                mForegroundServiceTimer.stopRunningLocked(l);
        }

        public void noteForegroundServiceResumedLocked(long l)
        {
            createForegroundServiceTimerLocked().startRunningLocked(l);
        }

        public void noteFullWifiLockAcquiredLocked(long l)
        {
            if(!mFullWifiLockOut)
            {
                mFullWifiLockOut = true;
                if(mFullWifiLockTimer == null)
                    mFullWifiLockTimer = new StopwatchTimer(mBsi.mClocks, this, 5, mBsi.mFullWifiLockTimers, mBsi.mOnBatteryTimeBase);
                mFullWifiLockTimer.startRunningLocked(l);
            }
        }

        public void noteFullWifiLockReleasedLocked(long l)
        {
            if(mFullWifiLockOut)
            {
                mFullWifiLockOut = false;
                mFullWifiLockTimer.stopRunningLocked(l);
            }
        }

        void noteMobileRadioActiveTimeLocked(long l)
        {
            if(mNetworkByteActivityCounters == null)
                initNetworkActivityLocked();
            mMobileRadioActiveTime.addCountLocked(l);
            mMobileRadioActiveCount.addCountLocked(1L);
        }

        public void noteMobileRadioApWakeupLocked()
        {
            if(mMobileRadioApWakeupCount == null)
                mMobileRadioApWakeupCount = new LongSamplingCounter(mBsi.mOnBatteryTimeBase);
            mMobileRadioApWakeupCount.addCountLocked(1L);
        }

        void noteNetworkActivityLocked(int i, long l, long l1)
        {
            if(mNetworkByteActivityCounters == null)
                initNetworkActivityLocked();
            if(i >= 0 && i < 10)
            {
                mNetworkByteActivityCounters[i].addCountLocked(l);
                mNetworkPacketActivityCounters[i].addCountLocked(l1);
            } else
            {
                Slog.w("BatteryStatsImpl", (new StringBuilder()).append("Unknown network activity type ").append(i).append(" was specified.").toString(), new Throwable());
            }
        }

        public void noteResetAudioLocked(long l)
        {
            if(mAudioTurnedOnTimer != null)
                mAudioTurnedOnTimer.stopAllRunningLocked(l);
        }

        public void noteResetBluetoothScanLocked(long l)
        {
            if(mBluetoothScanTimer != null)
                mBluetoothScanTimer.stopAllRunningLocked(l);
            if(mBluetoothUnoptimizedScanTimer != null)
                mBluetoothUnoptimizedScanTimer.stopAllRunningLocked(l);
        }

        public void noteResetCameraLocked(long l)
        {
            if(mCameraTurnedOnTimer != null)
                mCameraTurnedOnTimer.stopAllRunningLocked(l);
        }

        public void noteResetFlashlightLocked(long l)
        {
            if(mFlashlightTurnedOnTimer != null)
                mFlashlightTurnedOnTimer.stopAllRunningLocked(l);
        }

        public void noteResetVideoLocked(long l)
        {
            if(mVideoTurnedOnTimer != null)
                mVideoTurnedOnTimer.stopAllRunningLocked(l);
        }

        public void noteStartGps(long l)
        {
            noteStartSensor(-10000, l);
        }

        public void noteStartJobLocked(String s, long l)
        {
            s = (DualTimer)mJobStats.startObject(s);
            if(s != null)
                s.startRunningLocked(l);
        }

        public void noteStartSensor(int i, long l)
        {
            getSensorTimerLocked(i, true).startRunningLocked(l);
        }

        public void noteStartSyncLocked(String s, long l)
        {
            s = (DualTimer)mSyncStats.startObject(s);
            if(s != null)
                s.startRunningLocked(l);
        }

        public void noteStartWakeLocked(int i, String s, int j, long l)
        {
            s = (Wakelock)mWakelockStats.startObject(s);
            if(s != null)
                getWakelockTimerLocked(s, j).startRunningLocked(l);
            if(j == 0)
            {
                createAggregatedPartialWakelockTimerLocked().startRunningLocked(l);
                if(i >= 0)
                {
                    s = getPidStatsLocked(i);
                    i = ((android.os.BatteryStats.Uid.Pid) (s)).mWakeNesting;
                    s.mWakeNesting = i + 1;
                    if(i == 0)
                        s.mWakeStartMs = l;
                }
            }
        }

        public void noteStopGps(long l)
        {
            noteStopSensor(-10000, l);
        }

        public void noteStopJobLocked(String s, long l, int i)
        {
            DualTimer dualtimer = (DualTimer)mJobStats.stopObject(s);
            if(dualtimer != null)
                dualtimer.stopRunningLocked(l);
            if(mBsi.mOnBatteryTimeBase.isRunning())
            {
                SparseIntArray sparseintarray1 = (SparseIntArray)mJobCompletions.get(s);
                SparseIntArray sparseintarray = sparseintarray1;
                if(sparseintarray1 == null)
                {
                    sparseintarray = new SparseIntArray();
                    mJobCompletions.put(s, sparseintarray);
                }
                sparseintarray.put(i, sparseintarray.get(i, 0) + 1);
            }
        }

        public void noteStopSensor(int i, long l)
        {
            DualTimer dualtimer = getSensorTimerLocked(i, false);
            if(dualtimer != null)
                dualtimer.stopRunningLocked(l);
        }

        public void noteStopSyncLocked(String s, long l)
        {
            s = (DualTimer)mSyncStats.stopObject(s);
            if(s != null)
                s.stopRunningLocked(l);
        }

        public void noteStopWakeLocked(int i, String s, int j, long l)
        {
            s = (Wakelock)mWakelockStats.stopObject(s);
            if(s != null)
                getWakelockTimerLocked(s, j).stopRunningLocked(l);
            if(j == 0)
            {
                if(mAggregatedPartialWakelockTimer != null)
                    mAggregatedPartialWakelockTimer.stopRunningLocked(l);
                if(i >= 0)
                {
                    s = (android.os.BatteryStats.Uid.Pid)mPids.get(i);
                    if(s != null && ((android.os.BatteryStats.Uid.Pid) (s)).mWakeNesting > 0)
                    {
                        i = ((android.os.BatteryStats.Uid.Pid) (s)).mWakeNesting;
                        s.mWakeNesting = i - 1;
                        if(i == 1)
                        {
                            s.mWakeSumMs = ((android.os.BatteryStats.Uid.Pid) (s)).mWakeSumMs + (l - ((android.os.BatteryStats.Uid.Pid) (s)).mWakeStartMs);
                            s.mWakeStartMs = 0L;
                        }
                    }
                }
            }
        }

        public void noteUserActivityLocked(int i)
        {
            if(mUserActivityCounters == null)
                initUserActivityLocked();
            if(i >= 0 && i < 4)
                mUserActivityCounters[i].stepAtomic();
            else
                Slog.w("BatteryStatsImpl", (new StringBuilder()).append("Unknown user activity type ").append(i).append(" was specified.").toString(), new Throwable());
        }

        public void noteVibratorOffLocked()
        {
            if(mVibratorOnTimer != null)
                mVibratorOnTimer.abortLastDuration(mBsi);
        }

        public void noteVibratorOnLocked(long l)
        {
            createVibratorOnTimerLocked().addDuration(mBsi, l);
        }

        public void noteVideoTurnedOffLocked(long l)
        {
            if(mVideoTurnedOnTimer != null)
                mVideoTurnedOnTimer.stopRunningLocked(l);
        }

        public void noteVideoTurnedOnLocked(long l)
        {
            createVideoTurnedOnTimerLocked().startRunningLocked(l);
        }

        public void noteWifiBatchedScanStartedLocked(int i, long l)
        {
            boolean flag = false;
            int j = i;
            for(i = ((flag) ? 1 : 0); j > 8 && i < 4; i++)
                j >>= 3;

            if(mWifiBatchedScanBinStarted == i)
                return;
            if(mWifiBatchedScanBinStarted != -1)
                mWifiBatchedScanTimer[mWifiBatchedScanBinStarted].stopRunningLocked(l);
            mWifiBatchedScanBinStarted = i;
            if(mWifiBatchedScanTimer[i] == null)
                makeWifiBatchedScanBin(i, null);
            mWifiBatchedScanTimer[i].startRunningLocked(l);
        }

        public void noteWifiBatchedScanStoppedLocked(long l)
        {
            if(mWifiBatchedScanBinStarted != -1)
            {
                mWifiBatchedScanTimer[mWifiBatchedScanBinStarted].stopRunningLocked(l);
                mWifiBatchedScanBinStarted = -1;
            }
        }

        public void noteWifiMulticastDisabledLocked(long l)
        {
            if(mWifiMulticastEnabled)
            {
                mWifiMulticastEnabled = false;
                mWifiMulticastTimer.stopRunningLocked(l);
            }
        }

        public void noteWifiMulticastEnabledLocked(long l)
        {
            if(!mWifiMulticastEnabled)
            {
                mWifiMulticastEnabled = true;
                if(mWifiMulticastTimer == null)
                    mWifiMulticastTimer = new StopwatchTimer(mBsi.mClocks, this, 7, mBsi.mWifiMulticastTimers, mBsi.mOnBatteryTimeBase);
                mWifiMulticastTimer.startRunningLocked(l);
            }
        }

        public void noteWifiRadioApWakeupLocked()
        {
            if(mWifiRadioApWakeupCount == null)
                mWifiRadioApWakeupCount = new LongSamplingCounter(mBsi.mOnBatteryTimeBase);
            mWifiRadioApWakeupCount.addCountLocked(1L);
        }

        public void noteWifiRunningLocked(long l)
        {
            if(!mWifiRunning)
            {
                mWifiRunning = true;
                if(mWifiRunningTimer == null)
                    mWifiRunningTimer = new StopwatchTimer(mBsi.mClocks, this, 4, mBsi.mWifiRunningTimers, mBsi.mOnBatteryTimeBase);
                mWifiRunningTimer.startRunningLocked(l);
            }
        }

        public void noteWifiScanStartedLocked(long l)
        {
            if(!mWifiScanStarted)
            {
                mWifiScanStarted = true;
                if(mWifiScanTimer == null)
                    mWifiScanTimer = new DualTimer(mBsi.mClocks, this, 6, mBsi.mWifiScanTimers, mBsi.mOnBatteryTimeBase, mOnBatteryBackgroundTimeBase);
                mWifiScanTimer.startRunningLocked(l);
            }
        }

        public void noteWifiScanStoppedLocked(long l)
        {
            if(mWifiScanStarted)
            {
                mWifiScanStarted = false;
                mWifiScanTimer.stopRunningLocked(l);
            }
        }

        public void noteWifiStoppedLocked(long l)
        {
            if(mWifiRunning)
            {
                mWifiRunning = false;
                mWifiRunningTimer.stopRunningLocked(l);
            }
        }

        void readFromParcelLocked(TimeBase timebase, TimeBase timebase1, Parcel parcel)
        {
            mOnBatteryBackgroundTimeBase.readFromParcel(parcel);
            mOnBatteryScreenOffBackgroundTimeBase.readFromParcel(parcel);
            int i = parcel.readInt();
            mWakelockStats.clear();
            for(int k = 0; k < i; k++)
            {
                String s = parcel.readString();
                Wakelock wakelock = new Wakelock(mBsi, this);
                wakelock.readFromParcelLocked(timebase, timebase1, mOnBatteryScreenOffBackgroundTimeBase, parcel);
                mWakelockStats.add(s, wakelock);
            }

            i = parcel.readInt();
            mSyncStats.clear();
            for(int l = 0; l < i; l++)
            {
                timebase = parcel.readString();
                if(parcel.readInt() != 0)
                    mSyncStats.add(timebase, new DualTimer(mBsi.mClocks, this, 13, null, mBsi.mOnBatteryTimeBase, mOnBatteryBackgroundTimeBase, parcel));
            }

            i = parcel.readInt();
            mJobStats.clear();
            for(int i1 = 0; i1 < i; i1++)
            {
                timebase = parcel.readString();
                if(parcel.readInt() != 0)
                    mJobStats.add(timebase, new DualTimer(mBsi.mClocks, this, 14, null, mBsi.mOnBatteryTimeBase, mOnBatteryBackgroundTimeBase, parcel));
            }

            readJobCompletionsFromParcelLocked(parcel);
            i = parcel.readInt();
            mSensorStats.clear();
            for(int j1 = 0; j1 < i; j1++)
            {
                int i3 = parcel.readInt();
                timebase = new Sensor(mBsi, this, i3);
                timebase.readFromParcelLocked(mBsi.mOnBatteryTimeBase, mOnBatteryBackgroundTimeBase, parcel);
                mSensorStats.put(i3, timebase);
            }

            i = parcel.readInt();
            mProcessStats.clear();
            for(int k1 = 0; k1 < i; k1++)
            {
                timebase1 = parcel.readString();
                timebase = new Proc(mBsi, timebase1);
                timebase.readFromParcelLocked(parcel);
                mProcessStats.put(timebase1, timebase);
            }

            i = parcel.readInt();
            mPackageStats.clear();
            for(int l1 = 0; l1 < i; l1++)
            {
                timebase = parcel.readString();
                timebase1 = new Pkg(mBsi);
                timebase1.readFromParcelLocked(parcel);
                mPackageStats.put(timebase, timebase1);
            }

            mWifiRunning = false;
            int i2;
            if(parcel.readInt() != 0)
                mWifiRunningTimer = new StopwatchTimer(mBsi.mClocks, this, 4, mBsi.mWifiRunningTimers, mBsi.mOnBatteryTimeBase, parcel);
            else
                mWifiRunningTimer = null;
            mFullWifiLockOut = false;
            if(parcel.readInt() != 0)
                mFullWifiLockTimer = new StopwatchTimer(mBsi.mClocks, this, 5, mBsi.mFullWifiLockTimers, mBsi.mOnBatteryTimeBase, parcel);
            else
                mFullWifiLockTimer = null;
            mWifiScanStarted = false;
            if(parcel.readInt() != 0)
                mWifiScanTimer = new DualTimer(mBsi.mClocks, this, 6, mBsi.mWifiScanTimers, mBsi.mOnBatteryTimeBase, mOnBatteryBackgroundTimeBase, parcel);
            else
                mWifiScanTimer = null;
            mWifiBatchedScanBinStarted = -1;
            i2 = 0;
            while(i2 < 5) 
            {
                if(parcel.readInt() != 0)
                    makeWifiBatchedScanBin(i2, parcel);
                else
                    mWifiBatchedScanTimer[i2] = null;
                i2++;
            }
            mWifiMulticastEnabled = false;
            if(parcel.readInt() != 0)
                mWifiMulticastTimer = new StopwatchTimer(mBsi.mClocks, this, 7, mBsi.mWifiMulticastTimers, mBsi.mOnBatteryTimeBase, parcel);
            else
                mWifiMulticastTimer = null;
            if(parcel.readInt() != 0)
                mAudioTurnedOnTimer = new StopwatchTimer(mBsi.mClocks, this, 15, mBsi.mAudioTurnedOnTimers, mBsi.mOnBatteryTimeBase, parcel);
            else
                mAudioTurnedOnTimer = null;
            if(parcel.readInt() != 0)
                mVideoTurnedOnTimer = new StopwatchTimer(mBsi.mClocks, this, 8, mBsi.mVideoTurnedOnTimers, mBsi.mOnBatteryTimeBase, parcel);
            else
                mVideoTurnedOnTimer = null;
            if(parcel.readInt() != 0)
                mFlashlightTurnedOnTimer = new StopwatchTimer(mBsi.mClocks, this, 16, mBsi.mFlashlightTurnedOnTimers, mBsi.mOnBatteryTimeBase, parcel);
            else
                mFlashlightTurnedOnTimer = null;
            if(parcel.readInt() != 0)
                mCameraTurnedOnTimer = new StopwatchTimer(mBsi.mClocks, this, 17, mBsi.mCameraTurnedOnTimers, mBsi.mOnBatteryTimeBase, parcel);
            else
                mCameraTurnedOnTimer = null;
            if(parcel.readInt() != 0)
                mForegroundActivityTimer = new StopwatchTimer(mBsi.mClocks, this, 10, null, mBsi.mOnBatteryTimeBase, parcel);
            else
                mForegroundActivityTimer = null;
            if(parcel.readInt() != 0)
                mForegroundServiceTimer = new StopwatchTimer(mBsi.mClocks, this, 22, null, mBsi.mOnBatteryTimeBase, parcel);
            else
                mForegroundServiceTimer = null;
            if(parcel.readInt() != 0)
                mAggregatedPartialWakelockTimer = new DualTimer(mBsi.mClocks, this, 20, null, mBsi.mOnBatteryScreenOffTimeBase, mOnBatteryScreenOffBackgroundTimeBase, parcel);
            else
                mAggregatedPartialWakelockTimer = null;
            if(parcel.readInt() != 0)
                mBluetoothScanTimer = new DualTimer(mBsi.mClocks, this, 19, mBsi.mBluetoothScanOnTimers, mBsi.mOnBatteryTimeBase, mOnBatteryBackgroundTimeBase, parcel);
            else
                mBluetoothScanTimer = null;
            if(parcel.readInt() != 0)
                mBluetoothUnoptimizedScanTimer = new DualTimer(mBsi.mClocks, this, 21, null, mBsi.mOnBatteryTimeBase, mOnBatteryBackgroundTimeBase, parcel);
            else
                mBluetoothUnoptimizedScanTimer = null;
            if(parcel.readInt() != 0)
                mBluetoothScanResultCounter = new Counter(mBsi.mOnBatteryTimeBase, parcel);
            else
                mBluetoothScanResultCounter = null;
            if(parcel.readInt() != 0)
                mBluetoothScanResultBgCounter = new Counter(mOnBatteryBackgroundTimeBase, parcel);
            else
                mBluetoothScanResultBgCounter = null;
            mProcessState = 18;
            i2 = 0;
            while(i2 < 6) 
            {
                if(parcel.readInt() != 0)
                    makeProcessState(i2, parcel);
                else
                    mProcessStateTimer[i2] = null;
                i2++;
            }
            if(parcel.readInt() != 0)
                mVibratorOnTimer = new BatchTimer(mBsi.mClocks, this, 9, mBsi.mOnBatteryTimeBase, parcel);
            else
                mVibratorOnTimer = null;
            if(parcel.readInt() != 0)
            {
                mUserActivityCounters = new Counter[4];
                for(int j2 = 0; j2 < 4; j2++)
                    mUserActivityCounters[j2] = new Counter(mBsi.mOnBatteryTimeBase, parcel);

            } else
            {
                mUserActivityCounters = null;
            }
            if(parcel.readInt() != 0)
            {
                mNetworkByteActivityCounters = new LongSamplingCounter[10];
                mNetworkPacketActivityCounters = new LongSamplingCounter[10];
                for(int k2 = 0; k2 < 10; k2++)
                {
                    mNetworkByteActivityCounters[k2] = new LongSamplingCounter(mBsi.mOnBatteryTimeBase, parcel);
                    mNetworkPacketActivityCounters[k2] = new LongSamplingCounter(mBsi.mOnBatteryTimeBase, parcel);
                }

                mMobileRadioActiveTime = new LongSamplingCounter(mBsi.mOnBatteryTimeBase, parcel);
                mMobileRadioActiveCount = new LongSamplingCounter(mBsi.mOnBatteryTimeBase, parcel);
            } else
            {
                mNetworkByteActivityCounters = null;
                mNetworkPacketActivityCounters = null;
            }
            if(parcel.readInt() != 0)
                mWifiControllerActivity = new ControllerActivityCounterImpl(mBsi.mOnBatteryTimeBase, 1, parcel);
            else
                mWifiControllerActivity = null;
            if(parcel.readInt() != 0)
                mBluetoothControllerActivity = new ControllerActivityCounterImpl(mBsi.mOnBatteryTimeBase, 1, parcel);
            else
                mBluetoothControllerActivity = null;
            if(parcel.readInt() != 0)
                mModemControllerActivity = new ControllerActivityCounterImpl(mBsi.mOnBatteryTimeBase, 5, parcel);
            else
                mModemControllerActivity = null;
            mUserCpuTime = new LongSamplingCounter(mBsi.mOnBatteryTimeBase, parcel);
            mSystemCpuTime = new LongSamplingCounter(mBsi.mOnBatteryTimeBase, parcel);
            if(parcel.readInt() != 0)
            {
                int j3 = parcel.readInt();
                if(BatteryStatsImpl._2D_get4(mBsi) != null && BatteryStatsImpl._2D_get4(mBsi).getNumCpuClusters() != j3)
                    throw new ParcelFormatException("Incompatible number of cpu clusters");
                mCpuClusterSpeedTimesUs = new LongSamplingCounter[j3][];
                for(int l2 = 0; l2 < j3; l2++)
                    if(parcel.readInt() != 0)
                    {
                        int k3 = parcel.readInt();
                        if(BatteryStatsImpl._2D_get4(mBsi) != null && BatteryStatsImpl._2D_get4(mBsi).getNumSpeedStepsInCpuCluster(l2) != k3)
                            throw new ParcelFormatException("Incompatible number of cpu speeds");
                        timebase = new LongSamplingCounter[k3];
                        mCpuClusterSpeedTimesUs[l2] = timebase;
                        for(int j = 0; j < k3; j++)
                            if(parcel.readInt() != 0)
                                timebase[j] = new LongSamplingCounter(mBsi.mOnBatteryTimeBase, parcel);

                    } else
                    {
                        mCpuClusterSpeedTimesUs[l2] = null;
                    }

            } else
            {
                mCpuClusterSpeedTimesUs = null;
            }
            mCpuFreqTimeMs = LongSamplingCounterArray.readFromParcel(parcel, mBsi.mOnBatteryTimeBase);
            mScreenOffCpuFreqTimeMs = LongSamplingCounterArray.readFromParcel(parcel, mBsi.mOnBatteryScreenOffTimeBase);
            if(parcel.readInt() != 0)
                mMobileRadioApWakeupCount = new LongSamplingCounter(mBsi.mOnBatteryTimeBase, parcel);
            else
                mMobileRadioApWakeupCount = null;
            if(parcel.readInt() != 0)
                mWifiRadioApWakeupCount = new LongSamplingCounter(mBsi.mOnBatteryTimeBase, parcel);
            else
                mWifiRadioApWakeupCount = null;
        }

        void readJobCompletionsFromParcelLocked(Parcel parcel)
        {
            int i = parcel.readInt();
            mJobCompletions.clear();
            for(int j = 0; j < i; j++)
            {
                String s = parcel.readString();
                int k = parcel.readInt();
                if(k <= 0)
                    continue;
                SparseIntArray sparseintarray = new SparseIntArray();
                for(int l = 0; l < k; l++)
                    sparseintarray.put(parcel.readInt(), parcel.readInt());

                mJobCompletions.put(s, sparseintarray);
            }

        }

        public void readJobSummaryFromParcelLocked(String s, Parcel parcel)
        {
            DualTimer dualtimer = (DualTimer)mJobStats.instantiateObject();
            dualtimer.readSummaryFromParcelLocked(parcel);
            mJobStats.add(s, dualtimer);
        }

        public void readSyncSummaryFromParcelLocked(String s, Parcel parcel)
        {
            DualTimer dualtimer = (DualTimer)mSyncStats.instantiateObject();
            dualtimer.readSummaryFromParcelLocked(parcel);
            mSyncStats.add(s, dualtimer);
        }

        public void readWakeSummaryFromParcelLocked(String s, Parcel parcel)
        {
            Wakelock wakelock = new Wakelock(mBsi, this);
            mWakelockStats.add(s, wakelock);
            if(parcel.readInt() != 0)
                getWakelockTimerLocked(wakelock, 1).readSummaryFromParcelLocked(parcel);
            if(parcel.readInt() != 0)
                getWakelockTimerLocked(wakelock, 0).readSummaryFromParcelLocked(parcel);
            if(parcel.readInt() != 0)
                getWakelockTimerLocked(wakelock, 2).readSummaryFromParcelLocked(parcel);
            if(parcel.readInt() != 0)
                getWakelockTimerLocked(wakelock, 18).readSummaryFromParcelLocked(parcel);
        }

        public void reportExcessiveCpuLocked(String s, long l, long l1)
        {
            s = getProcessStatsLocked(s);
            if(s != null)
                s.addExcessiveCpu(l, l1);
        }

        public boolean reset(long l, long l1)
        {
            int i = 0;
            mOnBatteryBackgroundTimeBase.init(l, l1);
            mOnBatteryScreenOffBackgroundTimeBase.init(l, l1);
            if(mWifiRunningTimer != null)
                i = mWifiRunningTimer.reset(false) ^ true | mWifiRunning;
            int k1 = i;
            if(mFullWifiLockTimer != null)
                k1 = i | mFullWifiLockTimer.reset(false) ^ true | mFullWifiLockOut;
            i = k1;
            if(mWifiScanTimer != null)
                i = k1 | mWifiScanTimer.reset(false) ^ true | mWifiScanStarted;
            k1 = i;
            if(mWifiBatchedScanTimer != null)
            {
                for(k1 = 0; k1 < 5;)
                {
                    boolean flag = i;
                    if(mWifiBatchedScanTimer[k1] != null)
                        flag = i | mWifiBatchedScanTimer[k1].reset(false) ^ true;
                    k1++;
                    i = ((flag) ? 1 : 0);
                }

                int i2;
                if(mWifiBatchedScanBinStarted != -1)
                    k1 = 1;
                else
                    k1 = 0;
                k1 = i | k1;
            }
            i = k1;
            if(mWifiMulticastTimer != null)
                i = k1 | mWifiMulticastTimer.reset(false) ^ true | mWifiMulticastEnabled;
            i = i | BatteryStatsImpl._2D_wrap1(mAudioTurnedOnTimer, false) ^ true | BatteryStatsImpl._2D_wrap1(mVideoTurnedOnTimer, false) ^ true | BatteryStatsImpl._2D_wrap1(mFlashlightTurnedOnTimer, false) ^ true | BatteryStatsImpl._2D_wrap1(mCameraTurnedOnTimer, false) ^ true | BatteryStatsImpl._2D_wrap1(mForegroundActivityTimer, false) ^ true | BatteryStatsImpl._2D_wrap1(mForegroundServiceTimer, false) ^ true | BatteryStatsImpl._2D_wrap0(mAggregatedPartialWakelockTimer, false) ^ true | BatteryStatsImpl._2D_wrap0(mBluetoothScanTimer, false) ^ true | BatteryStatsImpl._2D_wrap0(mBluetoothUnoptimizedScanTimer, false) ^ true;
            if(mBluetoothScanResultCounter != null)
                mBluetoothScanResultCounter.reset(false);
            if(mBluetoothScanResultBgCounter != null)
                mBluetoothScanResultBgCounter.reset(false);
            k1 = i;
            if(mProcessStateTimer != null)
            {
                for(k1 = 0; k1 < 6;)
                {
                    i2 = i;
                    if(mProcessStateTimer[k1] != null)
                        i2 = i | mProcessStateTimer[k1].reset(false) ^ true;
                    k1++;
                    i = i2;
                }

                if(mProcessState != 18)
                    k1 = 1;
                else
                    k1 = 0;
                k1 = i | k1;
            }
            i = k1;
            if(mVibratorOnTimer != null)
                if(mVibratorOnTimer.reset(false))
                {
                    mVibratorOnTimer.detach();
                    mVibratorOnTimer = null;
                    i = k1;
                } else
                {
                    i = 1;
                }
            if(mUserActivityCounters != null)
                for(k1 = 0; k1 < 4; k1++)
                    mUserActivityCounters[k1].reset(false);

            if(mNetworkByteActivityCounters != null)
            {
                for(k1 = 0; k1 < 10; k1++)
                {
                    mNetworkByteActivityCounters[k1].reset(false);
                    mNetworkPacketActivityCounters[k1].reset(false);
                }

                mMobileRadioActiveTime.reset(false);
                mMobileRadioActiveCount.reset(false);
            }
            if(mWifiControllerActivity != null)
                mWifiControllerActivity.reset(false);
            if(mBluetoothControllerActivity != null)
                mBluetoothControllerActivity.reset(false);
            if(mModemControllerActivity != null)
                mModemControllerActivity.reset(false);
            mUserCpuTime.reset(false);
            mSystemCpuTime.reset(false);
            if(mCpuClusterSpeedTimesUs != null)
            {
                LongSamplingCounter alongsamplingcounter[][] = mCpuClusterSpeedTimesUs;
                int i3 = alongsamplingcounter.length;
                for(k1 = 0; k1 < i3; k1++)
                {
                    LongSamplingCounter alongsamplingcounter2[] = alongsamplingcounter[k1];
                    if(alongsamplingcounter2 == null)
                        continue;
                    int j2 = 0;
                    for(int k3 = alongsamplingcounter2.length; j2 < k3; j2++)
                    {
                        LongSamplingCounter longsamplingcounter = alongsamplingcounter2[j2];
                        if(longsamplingcounter != null)
                            longsamplingcounter.reset(false);
                    }

                }

            }
            if(mCpuFreqTimeMs != null)
                mCpuFreqTimeMs.reset(false);
            if(mScreenOffCpuFreqTimeMs != null)
                mScreenOffCpuFreqTimeMs.reset(false);
            BatteryStatsImpl._2D_wrap3(mMobileRadioApWakeupCount, false);
            BatteryStatsImpl._2D_wrap3(mWifiRadioApWakeupCount, false);
            ArrayMap arraymap = mWakelockStats.getMap();
            k1 = arraymap.size() - 1;
            while(k1 >= 0) 
            {
                if(((Wakelock)arraymap.valueAt(k1)).reset())
                    arraymap.removeAt(k1);
                else
                    i = 1;
                k1--;
            }
            mWakelockStats.cleanup();
            ArrayMap arraymap1 = mSyncStats.getMap();
            k1 = arraymap1.size() - 1;
            while(k1 >= 0) 
            {
                DualTimer dualtimer = (DualTimer)arraymap1.valueAt(k1);
                if(dualtimer.reset(false))
                {
                    arraymap1.removeAt(k1);
                    dualtimer.detach();
                } else
                {
                    i = 1;
                }
                k1--;
            }
            mSyncStats.cleanup();
            arraymap1 = mJobStats.getMap();
            k1 = arraymap1.size() - 1;
            while(k1 >= 0) 
            {
                DualTimer dualtimer1 = (DualTimer)arraymap1.valueAt(k1);
                if(dualtimer1.reset(false))
                {
                    arraymap1.removeAt(k1);
                    dualtimer1.detach();
                } else
                {
                    i = 1;
                }
                k1--;
            }
            mJobStats.cleanup();
            mJobCompletions.clear();
            k1 = mSensorStats.size() - 1;
            while(k1 >= 0) 
            {
                if(((Sensor)mSensorStats.valueAt(k1)).reset())
                    mSensorStats.removeAt(k1);
                else
                    i = 1;
                k1--;
            }
            for(k1 = mProcessStats.size() - 1; k1 >= 0; k1--)
                ((Proc)mProcessStats.valueAt(k1)).detach();

            mProcessStats.clear();
            k1 = i;
            if(mPids.size() > 0)
            {
                int k2 = mPids.size() - 1;
                do
                {
                    k1 = i;
                    if(k2 < 0)
                        break;
                    if(((android.os.BatteryStats.Uid.Pid)mPids.valueAt(k2)).mWakeNesting > 0)
                        i = 1;
                    else
                        mPids.removeAt(k2);
                    k2--;
                } while(true);
            }
            if(mPackageStats.size() > 0)
            {
                Iterator iterator = mPackageStats.entrySet().iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    Object obj = (Pkg)((java.util.Map.Entry)iterator.next()).getValue();
                    ((Pkg) (obj)).detach();
                    if(((Pkg) (obj)).mServiceStats.size() > 0)
                    {
                        obj = ((Pkg) (obj)).mServiceStats.entrySet().iterator();
                        while(((Iterator) (obj)).hasNext()) 
                            ((Pkg.Serv)((java.util.Map.Entry)((Iterator) (obj)).next()).getValue()).detach();
                    }
                } while(true);
                mPackageStats.clear();
            }
            mLastStepSystemTime = 0L;
            mLastStepUserTime = 0L;
            mCurStepSystemTime = 0L;
            mCurStepUserTime = 0L;
            if(k1 == 0)
            {
                if(mWifiRunningTimer != null)
                    mWifiRunningTimer.detach();
                if(mFullWifiLockTimer != null)
                    mFullWifiLockTimer.detach();
                if(mWifiScanTimer != null)
                    mWifiScanTimer.detach();
                for(int j = 0; j < 5; j++)
                    if(mWifiBatchedScanTimer[j] != null)
                        mWifiBatchedScanTimer[j].detach();

                if(mWifiMulticastTimer != null)
                    mWifiMulticastTimer.detach();
                if(mAudioTurnedOnTimer != null)
                {
                    mAudioTurnedOnTimer.detach();
                    mAudioTurnedOnTimer = null;
                }
                if(mVideoTurnedOnTimer != null)
                {
                    mVideoTurnedOnTimer.detach();
                    mVideoTurnedOnTimer = null;
                }
                if(mFlashlightTurnedOnTimer != null)
                {
                    mFlashlightTurnedOnTimer.detach();
                    mFlashlightTurnedOnTimer = null;
                }
                if(mCameraTurnedOnTimer != null)
                {
                    mCameraTurnedOnTimer.detach();
                    mCameraTurnedOnTimer = null;
                }
                if(mForegroundActivityTimer != null)
                {
                    mForegroundActivityTimer.detach();
                    mForegroundActivityTimer = null;
                }
                if(mForegroundServiceTimer != null)
                {
                    mForegroundServiceTimer.detach();
                    mForegroundServiceTimer = null;
                }
                if(mAggregatedPartialWakelockTimer != null)
                {
                    mAggregatedPartialWakelockTimer.detach();
                    mAggregatedPartialWakelockTimer = null;
                }
                if(mBluetoothScanTimer != null)
                {
                    mBluetoothScanTimer.detach();
                    mBluetoothScanTimer = null;
                }
                if(mBluetoothUnoptimizedScanTimer != null)
                {
                    mBluetoothUnoptimizedScanTimer.detach();
                    mBluetoothUnoptimizedScanTimer = null;
                }
                if(mBluetoothScanResultCounter != null)
                {
                    mBluetoothScanResultCounter.detach();
                    mBluetoothScanResultCounter = null;
                }
                if(mBluetoothScanResultBgCounter != null)
                {
                    mBluetoothScanResultBgCounter.detach();
                    mBluetoothScanResultBgCounter = null;
                }
                if(mUserActivityCounters != null)
                {
                    for(int k = 0; k < 4; k++)
                        mUserActivityCounters[k].detach();

                }
                if(mNetworkByteActivityCounters != null)
                {
                    for(int i1 = 0; i1 < 10; i1++)
                    {
                        mNetworkByteActivityCounters[i1].detach();
                        mNetworkPacketActivityCounters[i1].detach();
                    }

                }
                if(mWifiControllerActivity != null)
                    mWifiControllerActivity.detach();
                if(mBluetoothControllerActivity != null)
                    mBluetoothControllerActivity.detach();
                if(mModemControllerActivity != null)
                    mModemControllerActivity.detach();
                mPids.clear();
                mUserCpuTime.detach();
                mSystemCpuTime.detach();
                if(mCpuClusterSpeedTimesUs != null)
                {
                    LongSamplingCounter alongsamplingcounter1[][] = mCpuClusterSpeedTimesUs;
                    int j3 = alongsamplingcounter1.length;
                    for(int j1 = 0; j1 < j3; j1++)
                    {
                        LongSamplingCounter alongsamplingcounter3[] = alongsamplingcounter1[j1];
                        if(alongsamplingcounter3 == null)
                            continue;
                        int l2 = 0;
                        for(int l3 = alongsamplingcounter3.length; l2 < l3; l2++)
                        {
                            LongSamplingCounter longsamplingcounter1 = alongsamplingcounter3[l2];
                            if(longsamplingcounter1 != null)
                                longsamplingcounter1.detach();
                        }

                    }

                }
                if(mCpuFreqTimeMs != null)
                    mCpuFreqTimeMs.detach();
                if(mScreenOffCpuFreqTimeMs != null)
                    mScreenOffCpuFreqTimeMs.detach();
                BatteryStatsImpl._2D_wrap2(mMobileRadioApWakeupCount);
                BatteryStatsImpl._2D_wrap2(mWifiRadioApWakeupCount);
            }
            return k1 ^ 1;
        }

        public boolean updateOnBatteryBgTimeBase(long l, long l1)
        {
            boolean flag;
            if(mBsi.mOnBatteryTimeBase.isRunning())
                flag = isInBackground();
            else
                flag = false;
            return mOnBatteryBackgroundTimeBase.setRunning(flag, l, l1);
        }

        public boolean updateOnBatteryScreenOffBgTimeBase(long l, long l1)
        {
            boolean flag;
            if(mBsi.mOnBatteryScreenOffTimeBase.isRunning())
                flag = isInBackground();
            else
                flag = false;
            return mOnBatteryScreenOffBackgroundTimeBase.setRunning(flag, l, l1);
        }

        public void updateUidProcessStateLocked(int i)
        {
            boolean flag;
            if(i == 4)
                flag = true;
            else
                flag = false;
            if(i == 18)
                i = 18;
            else
            if(i == 2)
                i = 0;
            else
            if(i <= 4)
                i = 1;
            else
            if(i <= 5)
                i = 2;
            else
            if(i <= 6)
                i = 3;
            else
            if(i <= 12)
                i = 4;
            else
                i = 5;
            if(mProcessState == i && flag == mInForegroundService)
                return;
            long l = mBsi.mClocks.elapsedRealtime();
            if(mProcessState != i)
            {
                long l1 = mBsi.mClocks.uptimeMillis();
                if(mProcessState != 18)
                    mProcessStateTimer[mProcessState].stopRunningLocked(l);
                mProcessState = i;
                if(i != 18)
                {
                    if(mProcessStateTimer[i] == null)
                        makeProcessState(i, null);
                    mProcessStateTimer[i].startRunningLocked(l);
                }
                updateOnBatteryBgTimeBase(l1 * 1000L, l * 1000L);
                updateOnBatteryScreenOffBgTimeBase(l1 * 1000L, l * 1000L);
            }
            if(flag != mInForegroundService)
            {
                if(flag)
                    noteForegroundServiceResumedLocked(l);
                else
                    noteForegroundServicePausedLocked(l);
                mInForegroundService = flag;
            }
        }

        void writeJobCompletionsToParcelLocked(Parcel parcel)
        {
            int i = mJobCompletions.size();
            parcel.writeInt(i);
            for(int j = 0; j < i; j++)
            {
                parcel.writeString((String)mJobCompletions.keyAt(j));
                SparseIntArray sparseintarray = (SparseIntArray)mJobCompletions.valueAt(j);
                int k = sparseintarray.size();
                parcel.writeInt(k);
                for(int l = 0; l < k; l++)
                {
                    parcel.writeInt(sparseintarray.keyAt(l));
                    parcel.writeInt(sparseintarray.valueAt(l));
                }

            }

        }

        void writeToParcelLocked(Parcel parcel, long l, long l1)
        {
            mOnBatteryBackgroundTimeBase.writeToParcel(parcel, l, l1);
            mOnBatteryScreenOffBackgroundTimeBase.writeToParcel(parcel, l, l1);
            ArrayMap arraymap = mWakelockStats.getMap();
            int i = arraymap.size();
            parcel.writeInt(i);
            for(int k = 0; k < i; k++)
            {
                parcel.writeString((String)arraymap.keyAt(k));
                ((Wakelock)arraymap.valueAt(k)).writeToParcelLocked(parcel, l1);
            }

            arraymap = mSyncStats.getMap();
            i = arraymap.size();
            parcel.writeInt(i);
            for(int i1 = 0; i1 < i; i1++)
            {
                parcel.writeString((String)arraymap.keyAt(i1));
                Timer.writeTimerToParcel(parcel, (DualTimer)arraymap.valueAt(i1), l1);
            }

            arraymap = mJobStats.getMap();
            i = arraymap.size();
            parcel.writeInt(i);
            for(int j1 = 0; j1 < i; j1++)
            {
                parcel.writeString((String)arraymap.keyAt(j1));
                Timer.writeTimerToParcel(parcel, (DualTimer)arraymap.valueAt(j1), l1);
            }

            writeJobCompletionsToParcelLocked(parcel);
            i = mSensorStats.size();
            parcel.writeInt(i);
            for(int k1 = 0; k1 < i; k1++)
            {
                parcel.writeInt(mSensorStats.keyAt(k1));
                ((Sensor)mSensorStats.valueAt(k1)).writeToParcelLocked(parcel, l1);
            }

            i = mProcessStats.size();
            parcel.writeInt(i);
            for(int i2 = 0; i2 < i; i2++)
            {
                parcel.writeString((String)mProcessStats.keyAt(i2));
                ((Proc)mProcessStats.valueAt(i2)).writeToParcelLocked(parcel);
            }

            parcel.writeInt(mPackageStats.size());
            java.util.Map.Entry entry;
            for(Iterator iterator = mPackageStats.entrySet().iterator(); iterator.hasNext(); ((Pkg)entry.getValue()).writeToParcelLocked(parcel))
            {
                entry = (java.util.Map.Entry)iterator.next();
                parcel.writeString((String)entry.getKey());
            }

            int j2;
            if(mWifiRunningTimer != null)
            {
                parcel.writeInt(1);
                mWifiRunningTimer.writeToParcel(parcel, l1);
            } else
            {
                parcel.writeInt(0);
            }
            if(mFullWifiLockTimer != null)
            {
                parcel.writeInt(1);
                mFullWifiLockTimer.writeToParcel(parcel, l1);
            } else
            {
                parcel.writeInt(0);
            }
            if(mWifiScanTimer != null)
            {
                parcel.writeInt(1);
                mWifiScanTimer.writeToParcel(parcel, l1);
            } else
            {
                parcel.writeInt(0);
            }
            j2 = 0;
            while(j2 < 5) 
            {
                if(mWifiBatchedScanTimer[j2] != null)
                {
                    parcel.writeInt(1);
                    mWifiBatchedScanTimer[j2].writeToParcel(parcel, l1);
                } else
                {
                    parcel.writeInt(0);
                }
                j2++;
            }
            if(mWifiMulticastTimer != null)
            {
                parcel.writeInt(1);
                mWifiMulticastTimer.writeToParcel(parcel, l1);
            } else
            {
                parcel.writeInt(0);
            }
            if(mAudioTurnedOnTimer != null)
            {
                parcel.writeInt(1);
                mAudioTurnedOnTimer.writeToParcel(parcel, l1);
            } else
            {
                parcel.writeInt(0);
            }
            if(mVideoTurnedOnTimer != null)
            {
                parcel.writeInt(1);
                mVideoTurnedOnTimer.writeToParcel(parcel, l1);
            } else
            {
                parcel.writeInt(0);
            }
            if(mFlashlightTurnedOnTimer != null)
            {
                parcel.writeInt(1);
                mFlashlightTurnedOnTimer.writeToParcel(parcel, l1);
            } else
            {
                parcel.writeInt(0);
            }
            if(mCameraTurnedOnTimer != null)
            {
                parcel.writeInt(1);
                mCameraTurnedOnTimer.writeToParcel(parcel, l1);
            } else
            {
                parcel.writeInt(0);
            }
            if(mForegroundActivityTimer != null)
            {
                parcel.writeInt(1);
                mForegroundActivityTimer.writeToParcel(parcel, l1);
            } else
            {
                parcel.writeInt(0);
            }
            if(mForegroundServiceTimer != null)
            {
                parcel.writeInt(1);
                mForegroundServiceTimer.writeToParcel(parcel, l1);
            } else
            {
                parcel.writeInt(0);
            }
            if(mAggregatedPartialWakelockTimer != null)
            {
                parcel.writeInt(1);
                mAggregatedPartialWakelockTimer.writeToParcel(parcel, l1);
            } else
            {
                parcel.writeInt(0);
            }
            if(mBluetoothScanTimer != null)
            {
                parcel.writeInt(1);
                mBluetoothScanTimer.writeToParcel(parcel, l1);
            } else
            {
                parcel.writeInt(0);
            }
            if(mBluetoothUnoptimizedScanTimer != null)
            {
                parcel.writeInt(1);
                mBluetoothUnoptimizedScanTimer.writeToParcel(parcel, l1);
            } else
            {
                parcel.writeInt(0);
            }
            if(mBluetoothScanResultCounter != null)
            {
                parcel.writeInt(1);
                mBluetoothScanResultCounter.writeToParcel(parcel);
            } else
            {
                parcel.writeInt(0);
            }
            if(mBluetoothScanResultBgCounter != null)
            {
                parcel.writeInt(1);
                mBluetoothScanResultBgCounter.writeToParcel(parcel);
            } else
            {
                parcel.writeInt(0);
            }
            j2 = 0;
            while(j2 < 6) 
            {
                if(mProcessStateTimer[j2] != null)
                {
                    parcel.writeInt(1);
                    mProcessStateTimer[j2].writeToParcel(parcel, l1);
                } else
                {
                    parcel.writeInt(0);
                }
                j2++;
            }
            if(mVibratorOnTimer != null)
            {
                parcel.writeInt(1);
                mVibratorOnTimer.writeToParcel(parcel, l1);
            } else
            {
                parcel.writeInt(0);
            }
            if(mUserActivityCounters != null)
            {
                parcel.writeInt(1);
                for(int k2 = 0; k2 < 4; k2++)
                    mUserActivityCounters[k2].writeToParcel(parcel);

            } else
            {
                parcel.writeInt(0);
            }
            if(mNetworkByteActivityCounters != null)
            {
                parcel.writeInt(1);
                for(int l2 = 0; l2 < 10; l2++)
                {
                    mNetworkByteActivityCounters[l2].writeToParcel(parcel);
                    mNetworkPacketActivityCounters[l2].writeToParcel(parcel);
                }

                mMobileRadioActiveTime.writeToParcel(parcel);
                mMobileRadioActiveCount.writeToParcel(parcel);
            } else
            {
                parcel.writeInt(0);
            }
            if(mWifiControllerActivity != null)
            {
                parcel.writeInt(1);
                mWifiControllerActivity.writeToParcel(parcel, 0);
            } else
            {
                parcel.writeInt(0);
            }
            if(mBluetoothControllerActivity != null)
            {
                parcel.writeInt(1);
                mBluetoothControllerActivity.writeToParcel(parcel, 0);
            } else
            {
                parcel.writeInt(0);
            }
            if(mModemControllerActivity != null)
            {
                parcel.writeInt(1);
                mModemControllerActivity.writeToParcel(parcel, 0);
            } else
            {
                parcel.writeInt(0);
            }
            mUserCpuTime.writeToParcel(parcel);
            mSystemCpuTime.writeToParcel(parcel);
            if(mCpuClusterSpeedTimesUs != null)
            {
                parcel.writeInt(1);
                parcel.writeInt(mCpuClusterSpeedTimesUs.length);
                LongSamplingCounter alongsamplingcounter[][] = mCpuClusterSpeedTimesUs;
                int j3 = alongsamplingcounter.length;
                for(int i3 = 0; i3 < j3; i3++)
                {
                    LongSamplingCounter alongsamplingcounter1[] = alongsamplingcounter[i3];
                    if(alongsamplingcounter1 != null)
                    {
                        parcel.writeInt(1);
                        parcel.writeInt(alongsamplingcounter1.length);
                        int j = 0;
                        int k3 = alongsamplingcounter1.length;
                        while(j < k3) 
                        {
                            LongSamplingCounter longsamplingcounter = alongsamplingcounter1[j];
                            if(longsamplingcounter != null)
                            {
                                parcel.writeInt(1);
                                longsamplingcounter.writeToParcel(parcel);
                            } else
                            {
                                parcel.writeInt(0);
                            }
                            j++;
                        }
                    } else
                    {
                        parcel.writeInt(0);
                    }
                }

            } else
            {
                parcel.writeInt(0);
            }
            LongSamplingCounterArray.writeToParcel(parcel, mCpuFreqTimeMs);
            LongSamplingCounterArray.writeToParcel(parcel, mScreenOffCpuFreqTimeMs);
            if(mMobileRadioApWakeupCount != null)
            {
                parcel.writeInt(1);
                mMobileRadioApWakeupCount.writeToParcel(parcel);
            } else
            {
                parcel.writeInt(0);
            }
            if(mWifiRadioApWakeupCount != null)
            {
                parcel.writeInt(1);
                mWifiRadioApWakeupCount.writeToParcel(parcel);
            } else
            {
                parcel.writeInt(0);
            }
        }

        static final int NO_BATCHED_SCAN_STARTED = -1;
        DualTimer mAggregatedPartialWakelockTimer;
        StopwatchTimer mAudioTurnedOnTimer;
        private ControllerActivityCounterImpl mBluetoothControllerActivity;
        Counter mBluetoothScanResultBgCounter;
        Counter mBluetoothScanResultCounter;
        DualTimer mBluetoothScanTimer;
        DualTimer mBluetoothUnoptimizedScanTimer;
        protected BatteryStatsImpl mBsi;
        StopwatchTimer mCameraTurnedOnTimer;
        LongSamplingCounter mCpuClusterSpeedTimesUs[][];
        LongSamplingCounterArray mCpuFreqTimeMs;
        long mCurStepSystemTime;
        long mCurStepUserTime;
        StopwatchTimer mFlashlightTurnedOnTimer;
        StopwatchTimer mForegroundActivityTimer;
        StopwatchTimer mForegroundServiceTimer;
        boolean mFullWifiLockOut;
        StopwatchTimer mFullWifiLockTimer;
        boolean mInForegroundService;
        final ArrayMap mJobCompletions = new ArrayMap();
        final OverflowArrayMap mJobStats;
        long mLastStepSystemTime;
        long mLastStepUserTime;
        LongSamplingCounter mMobileRadioActiveCount;
        LongSamplingCounter mMobileRadioActiveTime;
        private LongSamplingCounter mMobileRadioApWakeupCount;
        private ControllerActivityCounterImpl mModemControllerActivity;
        LongSamplingCounter mNetworkByteActivityCounters[];
        LongSamplingCounter mNetworkPacketActivityCounters[];
        public final TimeBase mOnBatteryBackgroundTimeBase = new TimeBase();
        public final TimeBase mOnBatteryScreenOffBackgroundTimeBase = new TimeBase();
        final ArrayMap mPackageStats = new ArrayMap();
        final SparseArray mPids = new SparseArray();
        int mProcessState;
        StopwatchTimer mProcessStateTimer[];
        final ArrayMap mProcessStats = new ArrayMap();
        LongSamplingCounterArray mScreenOffCpuFreqTimeMs;
        final SparseArray mSensorStats = new SparseArray();
        final OverflowArrayMap mSyncStats;
        LongSamplingCounter mSystemCpuTime;
        final int mUid;
        Counter mUserActivityCounters[];
        LongSamplingCounter mUserCpuTime;
        BatchTimer mVibratorOnTimer;
        StopwatchTimer mVideoTurnedOnTimer;
        final OverflowArrayMap mWakelockStats;
        int mWifiBatchedScanBinStarted;
        StopwatchTimer mWifiBatchedScanTimer[];
        private ControllerActivityCounterImpl mWifiControllerActivity;
        boolean mWifiMulticastEnabled;
        StopwatchTimer mWifiMulticastTimer;
        private LongSamplingCounter mWifiRadioApWakeupCount;
        boolean mWifiRunning;
        StopwatchTimer mWifiRunningTimer;
        boolean mWifiScanStarted;
        DualTimer mWifiScanTimer;

        public Uid(BatteryStatsImpl batterystatsimpl, int i)
        {
            mWifiBatchedScanBinStarted = -1;
            mProcessState = 18;
            mInForegroundService = false;
            mBsi = batterystatsimpl;
            mUid = i;
            mOnBatteryBackgroundTimeBase.init(mBsi.mClocks.uptimeMillis() * 1000L, mBsi.mClocks.elapsedRealtime() * 1000L);
            mOnBatteryScreenOffBackgroundTimeBase.init(mBsi.mClocks.uptimeMillis() * 1000L, mBsi.mClocks.elapsedRealtime() * 1000L);
            mUserCpuTime = new LongSamplingCounter(mBsi.mOnBatteryTimeBase);
            mSystemCpuTime = new LongSamplingCounter(mBsi.mOnBatteryTimeBase);
            batterystatsimpl = mBsi;
            batterystatsimpl.getClass();
            mWakelockStats = new _cls1(batterystatsimpl, i);
            batterystatsimpl = mBsi;
            batterystatsimpl.getClass();
            mSyncStats = new _cls2(batterystatsimpl, i);
            batterystatsimpl = mBsi;
            batterystatsimpl.getClass();
            mJobStats = new _cls3(batterystatsimpl, i);
            mWifiRunningTimer = new StopwatchTimer(mBsi.mClocks, this, 4, mBsi.mWifiRunningTimers, mBsi.mOnBatteryTimeBase);
            mFullWifiLockTimer = new StopwatchTimer(mBsi.mClocks, this, 5, mBsi.mFullWifiLockTimers, mBsi.mOnBatteryTimeBase);
            mWifiScanTimer = new DualTimer(mBsi.mClocks, this, 6, mBsi.mWifiScanTimers, mBsi.mOnBatteryTimeBase, mOnBatteryBackgroundTimeBase);
            mWifiBatchedScanTimer = new StopwatchTimer[5];
            mWifiMulticastTimer = new StopwatchTimer(mBsi.mClocks, this, 7, mBsi.mWifiMulticastTimers, mBsi.mOnBatteryTimeBase);
            mProcessStateTimer = new StopwatchTimer[6];
        }
    }

    public static class Uid.Pkg extends android.os.BatteryStats.Uid.Pkg
        implements TimeBaseObs
    {

        void detach()
        {
            mBsi.mOnBatteryScreenOffTimeBase.remove(this);
        }

        public ArrayMap getServiceStats()
        {
            return mServiceStats;
        }

        public ArrayMap getWakeupAlarmStats()
        {
            return mWakeupAlarms;
        }

        final Serv newServiceStatsLocked()
        {
            return new Serv(mBsi);
        }

        public void noteWakeupAlarmLocked(String s)
        {
            Counter counter = (Counter)mWakeupAlarms.get(s);
            Counter counter1 = counter;
            if(counter == null)
            {
                counter1 = new Counter(mBsi.mOnBatteryScreenOffTimeBase);
                mWakeupAlarms.put(s, counter1);
            }
            counter1.stepAtomic();
        }

        public void onTimeStarted(long l, long l1, long l2)
        {
        }

        public void onTimeStopped(long l, long l1, long l2)
        {
        }

        void readFromParcelLocked(Parcel parcel)
        {
            int i = parcel.readInt();
            mWakeupAlarms.clear();
            for(int j = 0; j < i; j++)
            {
                String s = parcel.readString();
                mWakeupAlarms.put(s, new Counter(mBsi.mOnBatteryScreenOffTimeBase, parcel));
            }

            i = parcel.readInt();
            mServiceStats.clear();
            for(int k = 0; k < i; k++)
            {
                String s1 = parcel.readString();
                Serv serv = new Serv(mBsi);
                mServiceStats.put(s1, serv);
                serv.readFromParcelLocked(parcel);
            }

        }

        void writeToParcelLocked(Parcel parcel)
        {
            int i = mWakeupAlarms.size();
            parcel.writeInt(i);
            for(int j = 0; j < i; j++)
            {
                parcel.writeString((String)mWakeupAlarms.keyAt(j));
                ((Counter)mWakeupAlarms.valueAt(j)).writeToParcel(parcel);
            }

            i = mServiceStats.size();
            parcel.writeInt(i);
            for(int k = 0; k < i; k++)
            {
                parcel.writeString((String)mServiceStats.keyAt(k));
                ((Serv)mServiceStats.valueAt(k)).writeToParcelLocked(parcel);
            }

        }

        protected BatteryStatsImpl mBsi;
        final ArrayMap mServiceStats = new ArrayMap();
        ArrayMap mWakeupAlarms;

        public Uid.Pkg(BatteryStatsImpl batterystatsimpl)
        {
            mWakeupAlarms = new ArrayMap();
            mBsi = batterystatsimpl;
            mBsi.mOnBatteryScreenOffTimeBase.add(this);
        }
    }

    public static class Uid.Pkg.Serv extends android.os.BatteryStats.Uid.Pkg.Serv
        implements TimeBaseObs
    {

        public void detach()
        {
            mBsi.mOnBatteryTimeBase.remove(this);
        }

        public BatteryStatsImpl getBatteryStats()
        {
            return mBsi;
        }

        public long getLaunchTimeToNowLocked(long l)
        {
            if(!mLaunched)
                return mLaunchedTime;
            else
                return (mLaunchedTime + l) - mLaunchedSince;
        }

        public int getLaunches(int i)
        {
            int j = mLaunches;
            if(i != 1) goto _L2; else goto _L1
_L1:
            int k = j - mLoadedLaunches;
_L4:
            return k;
_L2:
            k = j;
            if(i == 2)
                k = j - mUnpluggedLaunches;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public long getStartTime(long l, int i)
        {
            long l1 = getStartTimeToNowLocked(l);
            if(i != 1) goto _L2; else goto _L1
_L1:
            l = l1 - mLoadedStartTime;
_L4:
            return l;
_L2:
            l = l1;
            if(i == 2)
                l = l1 - mUnpluggedStartTime;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public long getStartTimeToNowLocked(long l)
        {
            if(!mRunning)
                return mStartTime;
            else
                return (mStartTime + l) - mRunningSince;
        }

        public int getStarts(int i)
        {
            int j = mStarts;
            if(i != 1) goto _L2; else goto _L1
_L1:
            int k = j - mLoadedStarts;
_L4:
            return k;
_L2:
            k = j;
            if(i == 2)
                k = j - mUnpluggedStarts;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void onTimeStarted(long l, long l1, long l2)
        {
            mUnpluggedStartTime = getStartTimeToNowLocked(l1);
            mUnpluggedStarts = mStarts;
            mUnpluggedLaunches = mLaunches;
        }

        public void onTimeStopped(long l, long l1, long l2)
        {
        }

        public void readFromParcelLocked(Parcel parcel)
        {
            boolean flag = true;
            mStartTime = parcel.readLong();
            mRunningSince = parcel.readLong();
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            mRunning = flag1;
            mStarts = parcel.readInt();
            mLaunchedTime = parcel.readLong();
            mLaunchedSince = parcel.readLong();
            if(parcel.readInt() != 0)
                flag1 = flag;
            else
                flag1 = false;
            mLaunched = flag1;
            mLaunches = parcel.readInt();
            mLoadedStartTime = parcel.readLong();
            mLoadedStarts = parcel.readInt();
            mLoadedLaunches = parcel.readInt();
            mLastStartTime = 0L;
            mLastStarts = 0;
            mLastLaunches = 0;
            mUnpluggedStartTime = parcel.readLong();
            mUnpluggedStarts = parcel.readInt();
            mUnpluggedLaunches = parcel.readInt();
        }

        public void startLaunchedLocked()
        {
            if(!mLaunched)
            {
                mLaunches = mLaunches + 1;
                mLaunchedSince = mBsi.getBatteryUptimeLocked();
                mLaunched = true;
            }
        }

        public void startRunningLocked()
        {
            if(!mRunning)
            {
                mStarts = mStarts + 1;
                mRunningSince = mBsi.getBatteryUptimeLocked();
                mRunning = true;
            }
        }

        public void stopLaunchedLocked()
        {
            if(mLaunched)
            {
                long l = mBsi.getBatteryUptimeLocked() - mLaunchedSince;
                if(l > 0L)
                    mLaunchedTime = mLaunchedTime + l;
                else
                    mLaunches = mLaunches - 1;
                mLaunched = false;
            }
        }

        public void stopRunningLocked()
        {
            if(mRunning)
            {
                long l = mBsi.getBatteryUptimeLocked() - mRunningSince;
                if(l > 0L)
                    mStartTime = mStartTime + l;
                else
                    mStarts = mStarts - 1;
                mRunning = false;
            }
        }

        public void writeToParcelLocked(Parcel parcel)
        {
            boolean flag = true;
            parcel.writeLong(mStartTime);
            parcel.writeLong(mRunningSince);
            int i;
            if(mRunning)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(mStarts);
            parcel.writeLong(mLaunchedTime);
            parcel.writeLong(mLaunchedSince);
            if(mLaunched)
                i = ((flag) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(mLaunches);
            parcel.writeLong(mLoadedStartTime);
            parcel.writeInt(mLoadedStarts);
            parcel.writeInt(mLoadedLaunches);
            parcel.writeLong(mUnpluggedStartTime);
            parcel.writeInt(mUnpluggedStarts);
            parcel.writeInt(mUnpluggedLaunches);
        }

        protected BatteryStatsImpl mBsi;
        protected int mLastLaunches;
        protected long mLastStartTime;
        protected int mLastStarts;
        protected boolean mLaunched;
        protected long mLaunchedSince;
        protected long mLaunchedTime;
        protected int mLaunches;
        protected int mLoadedLaunches;
        protected long mLoadedStartTime;
        protected int mLoadedStarts;
        protected Uid.Pkg mPkg;
        protected boolean mRunning;
        protected long mRunningSince;
        protected long mStartTime;
        protected int mStarts;
        protected int mUnpluggedLaunches;
        protected long mUnpluggedStartTime;
        protected int mUnpluggedStarts;

        public Uid.Pkg.Serv(BatteryStatsImpl batterystatsimpl)
        {
            mBsi = batterystatsimpl;
            mBsi.mOnBatteryTimeBase.add(this);
        }
    }

    public static class Uid.Proc extends android.os.BatteryStats.Uid.Proc
        implements TimeBaseObs
    {

        public void addCpuTimeLocked(int i, int j)
        {
            mUserTime = mUserTime + (long)i;
            mSystemTime = mSystemTime + (long)j;
        }

        public void addExcessiveCpu(long l, long l1)
        {
            if(mExcessivePower == null)
                mExcessivePower = new ArrayList();
            android.os.BatteryStats.Uid.Proc.ExcessivePower excessivepower = new android.os.BatteryStats.Uid.Proc.ExcessivePower();
            excessivepower.type = 2;
            excessivepower.overTime = l;
            excessivepower.usedTime = l1;
            mExcessivePower.add(excessivepower);
        }

        public void addForegroundTimeLocked(long l)
        {
            mForegroundTime = mForegroundTime + l;
        }

        public int countExcessivePowers()
        {
            int i;
            if(mExcessivePower != null)
                i = mExcessivePower.size();
            else
                i = 0;
            return i;
        }

        void detach()
        {
            mActive = false;
            mBsi.mOnBatteryTimeBase.remove(this);
        }

        public android.os.BatteryStats.Uid.Proc.ExcessivePower getExcessivePower(int i)
        {
            if(mExcessivePower != null)
                return (android.os.BatteryStats.Uid.Proc.ExcessivePower)mExcessivePower.get(i);
            else
                return null;
        }

        public long getForegroundTime(int i)
        {
            long l = mForegroundTime;
            if(i != 1) goto _L2; else goto _L1
_L1:
            long l1 = l - mLoadedForegroundTime;
_L4:
            return l1;
_L2:
            l1 = l;
            if(i == 2)
                l1 = l - mUnpluggedForegroundTime;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public int getNumAnrs(int i)
        {
            int j = mNumAnrs;
            if(i != 1) goto _L2; else goto _L1
_L1:
            int k = j - mLoadedNumAnrs;
_L4:
            return k;
_L2:
            k = j;
            if(i == 2)
                k = j - mUnpluggedNumAnrs;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public int getNumCrashes(int i)
        {
            int j = mNumCrashes;
            if(i != 1) goto _L2; else goto _L1
_L1:
            int k = j - mLoadedNumCrashes;
_L4:
            return k;
_L2:
            k = j;
            if(i == 2)
                k = j - mUnpluggedNumCrashes;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public int getStarts(int i)
        {
            int j = mStarts;
            if(i != 1) goto _L2; else goto _L1
_L1:
            int k = j - mLoadedStarts;
_L4:
            return k;
_L2:
            k = j;
            if(i == 2)
                k = j - mUnpluggedStarts;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public long getSystemTime(int i)
        {
            long l = mSystemTime;
            if(i != 1) goto _L2; else goto _L1
_L1:
            long l1 = l - mLoadedSystemTime;
_L4:
            return l1;
_L2:
            l1 = l;
            if(i == 2)
                l1 = l - mUnpluggedSystemTime;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public long getUserTime(int i)
        {
            long l = mUserTime;
            if(i != 1) goto _L2; else goto _L1
_L1:
            long l1 = l - mLoadedUserTime;
_L4:
            return l1;
_L2:
            l1 = l;
            if(i == 2)
                l1 = l - mUnpluggedUserTime;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void incNumAnrsLocked()
        {
            mNumAnrs = mNumAnrs + 1;
        }

        public void incNumCrashesLocked()
        {
            mNumCrashes = mNumCrashes + 1;
        }

        public void incStartsLocked()
        {
            mStarts = mStarts + 1;
        }

        public boolean isActive()
        {
            return mActive;
        }

        public void onTimeStarted(long l, long l1, long l2)
        {
            mUnpluggedUserTime = mUserTime;
            mUnpluggedSystemTime = mSystemTime;
            mUnpluggedForegroundTime = mForegroundTime;
            mUnpluggedStarts = mStarts;
            mUnpluggedNumCrashes = mNumCrashes;
            mUnpluggedNumAnrs = mNumAnrs;
        }

        public void onTimeStopped(long l, long l1, long l2)
        {
        }

        void readExcessivePowerFromParcelLocked(Parcel parcel)
        {
            int i = parcel.readInt();
            if(i == 0)
            {
                mExcessivePower = null;
                return;
            }
            if(i > 10000)
                throw new ParcelFormatException((new StringBuilder()).append("File corrupt: too many excessive power entries ").append(i).toString());
            mExcessivePower = new ArrayList();
            for(int j = 0; j < i; j++)
            {
                android.os.BatteryStats.Uid.Proc.ExcessivePower excessivepower = new android.os.BatteryStats.Uid.Proc.ExcessivePower();
                excessivepower.type = parcel.readInt();
                excessivepower.overTime = parcel.readLong();
                excessivepower.usedTime = parcel.readLong();
                mExcessivePower.add(excessivepower);
            }

        }

        void readFromParcelLocked(Parcel parcel)
        {
            mUserTime = parcel.readLong();
            mSystemTime = parcel.readLong();
            mForegroundTime = parcel.readLong();
            mStarts = parcel.readInt();
            mNumCrashes = parcel.readInt();
            mNumAnrs = parcel.readInt();
            mLoadedUserTime = parcel.readLong();
            mLoadedSystemTime = parcel.readLong();
            mLoadedForegroundTime = parcel.readLong();
            mLoadedStarts = parcel.readInt();
            mLoadedNumCrashes = parcel.readInt();
            mLoadedNumAnrs = parcel.readInt();
            mUnpluggedUserTime = parcel.readLong();
            mUnpluggedSystemTime = parcel.readLong();
            mUnpluggedForegroundTime = parcel.readLong();
            mUnpluggedStarts = parcel.readInt();
            mUnpluggedNumCrashes = parcel.readInt();
            mUnpluggedNumAnrs = parcel.readInt();
            readExcessivePowerFromParcelLocked(parcel);
        }

        void writeExcessivePowerToParcelLocked(Parcel parcel)
        {
            if(mExcessivePower == null)
            {
                parcel.writeInt(0);
                return;
            }
            int i = mExcessivePower.size();
            parcel.writeInt(i);
            for(int j = 0; j < i; j++)
            {
                android.os.BatteryStats.Uid.Proc.ExcessivePower excessivepower = (android.os.BatteryStats.Uid.Proc.ExcessivePower)mExcessivePower.get(j);
                parcel.writeInt(excessivepower.type);
                parcel.writeLong(excessivepower.overTime);
                parcel.writeLong(excessivepower.usedTime);
            }

        }

        void writeToParcelLocked(Parcel parcel)
        {
            parcel.writeLong(mUserTime);
            parcel.writeLong(mSystemTime);
            parcel.writeLong(mForegroundTime);
            parcel.writeInt(mStarts);
            parcel.writeInt(mNumCrashes);
            parcel.writeInt(mNumAnrs);
            parcel.writeLong(mLoadedUserTime);
            parcel.writeLong(mLoadedSystemTime);
            parcel.writeLong(mLoadedForegroundTime);
            parcel.writeInt(mLoadedStarts);
            parcel.writeInt(mLoadedNumCrashes);
            parcel.writeInt(mLoadedNumAnrs);
            parcel.writeLong(mUnpluggedUserTime);
            parcel.writeLong(mUnpluggedSystemTime);
            parcel.writeLong(mUnpluggedForegroundTime);
            parcel.writeInt(mUnpluggedStarts);
            parcel.writeInt(mUnpluggedNumCrashes);
            parcel.writeInt(mUnpluggedNumAnrs);
            writeExcessivePowerToParcelLocked(parcel);
        }

        boolean mActive;
        protected BatteryStatsImpl mBsi;
        ArrayList mExcessivePower;
        long mForegroundTime;
        long mLoadedForegroundTime;
        int mLoadedNumAnrs;
        int mLoadedNumCrashes;
        int mLoadedStarts;
        long mLoadedSystemTime;
        long mLoadedUserTime;
        final String mName;
        int mNumAnrs;
        int mNumCrashes;
        int mStarts;
        long mSystemTime;
        long mUnpluggedForegroundTime;
        int mUnpluggedNumAnrs;
        int mUnpluggedNumCrashes;
        int mUnpluggedStarts;
        long mUnpluggedSystemTime;
        long mUnpluggedUserTime;
        long mUserTime;

        public Uid.Proc(BatteryStatsImpl batterystatsimpl, String s)
        {
            mActive = true;
            mBsi = batterystatsimpl;
            mName = s;
            mBsi.mOnBatteryTimeBase.add(this);
        }
    }

    public static class Uid.Sensor extends android.os.BatteryStats.Uid.Sensor
    {

        private DualTimer readTimersFromParcel(TimeBase timebase, TimeBase timebase1, Parcel parcel)
        {
            if(parcel.readInt() == 0)
                return null;
            ArrayList arraylist = (ArrayList)mBsi.mSensorTimers.get(mHandle);
            ArrayList arraylist1 = arraylist;
            if(arraylist == null)
            {
                arraylist1 = new ArrayList();
                mBsi.mSensorTimers.put(mHandle, arraylist1);
            }
            return new DualTimer(mBsi.mClocks, mUid, 0, arraylist1, timebase, timebase1, parcel);
        }

        public int getHandle()
        {
            return mHandle;
        }

        public volatile android.os.BatteryStats.Timer getSensorBackgroundTime()
        {
            return getSensorBackgroundTime();
        }

        public Timer getSensorBackgroundTime()
        {
            if(mTimer == null)
                return null;
            else
                return mTimer.getSubTimer();
        }

        public volatile android.os.BatteryStats.Timer getSensorTime()
        {
            return getSensorTime();
        }

        public Timer getSensorTime()
        {
            return mTimer;
        }

        void readFromParcelLocked(TimeBase timebase, TimeBase timebase1, Parcel parcel)
        {
            mTimer = readTimersFromParcel(timebase, timebase1, parcel);
        }

        boolean reset()
        {
            if(mTimer.reset(true))
            {
                mTimer = null;
                return true;
            } else
            {
                return false;
            }
        }

        void writeToParcelLocked(Parcel parcel, long l)
        {
            Timer.writeTimerToParcel(parcel, mTimer, l);
        }

        protected BatteryStatsImpl mBsi;
        final int mHandle;
        DualTimer mTimer;
        protected Uid mUid;

        public Uid.Sensor(BatteryStatsImpl batterystatsimpl, Uid uid, int i)
        {
            mBsi = batterystatsimpl;
            mUid = uid;
            mHandle = i;
        }
    }

    public static class Uid.Wakelock extends android.os.BatteryStats.Uid.Wakelock
    {

        private DualTimer readDualTimerFromParcel(int i, ArrayList arraylist, TimeBase timebase, TimeBase timebase1, Parcel parcel)
        {
            if(parcel.readInt() == 0)
                return null;
            else
                return new DualTimer(mBsi.mClocks, mUid, i, arraylist, timebase, timebase1, parcel);
        }

        private StopwatchTimer readStopwatchTimerFromParcel(int i, ArrayList arraylist, TimeBase timebase, Parcel parcel)
        {
            if(parcel.readInt() == 0)
                return null;
            else
                return new StopwatchTimer(mBsi.mClocks, mUid, i, arraylist, timebase, parcel);
        }

        public volatile android.os.BatteryStats.Timer getWakeTime(int i)
        {
            return getWakeTime(i);
        }

        public Timer getWakeTime(int i)
        {
            switch(i)
            {
            default:
                throw new IllegalArgumentException((new StringBuilder()).append("type = ").append(i).toString());

            case 1: // '\001'
                return mTimerFull;

            case 0: // '\0'
                return mTimerPartial;

            case 2: // '\002'
                return mTimerWindow;

            case 18: // '\022'
                return mTimerDraw;
            }
        }

        void readFromParcelLocked(TimeBase timebase, TimeBase timebase1, TimeBase timebase2, Parcel parcel)
        {
            mTimerPartial = readDualTimerFromParcel(0, mBsi.mPartialTimers, timebase1, timebase2, parcel);
            mTimerFull = readStopwatchTimerFromParcel(1, mBsi.mFullTimers, timebase, parcel);
            mTimerWindow = readStopwatchTimerFromParcel(2, mBsi.mWindowTimers, timebase, parcel);
            mTimerDraw = readStopwatchTimerFromParcel(18, mBsi.mDrawTimers, timebase, parcel);
        }

        boolean reset()
        {
            boolean flag = false;
            if(mTimerFull != null)
                flag = mTimerFull.reset(false) ^ true;
            boolean flag1 = flag;
            if(mTimerPartial != null)
                flag1 = flag | mTimerPartial.reset(false) ^ true;
            flag = flag1;
            if(mTimerWindow != null)
                flag = flag1 | mTimerWindow.reset(false) ^ true;
            flag1 = flag;
            if(mTimerDraw != null)
                flag1 = flag | mTimerDraw.reset(false) ^ true;
            if(!flag1)
            {
                if(mTimerFull != null)
                {
                    mTimerFull.detach();
                    mTimerFull = null;
                }
                if(mTimerPartial != null)
                {
                    mTimerPartial.detach();
                    mTimerPartial = null;
                }
                if(mTimerWindow != null)
                {
                    mTimerWindow.detach();
                    mTimerWindow = null;
                }
                if(mTimerDraw != null)
                {
                    mTimerDraw.detach();
                    mTimerDraw = null;
                }
            }
            return flag1 ^ true;
        }

        void writeToParcelLocked(Parcel parcel, long l)
        {
            Timer.writeTimerToParcel(parcel, mTimerPartial, l);
            Timer.writeTimerToParcel(parcel, mTimerFull, l);
            Timer.writeTimerToParcel(parcel, mTimerWindow, l);
            Timer.writeTimerToParcel(parcel, mTimerDraw, l);
        }

        protected BatteryStatsImpl mBsi;
        StopwatchTimer mTimerDraw;
        StopwatchTimer mTimerFull;
        DualTimer mTimerPartial;
        StopwatchTimer mTimerWindow;
        protected Uid mUid;

        public Uid.Wakelock(BatteryStatsImpl batterystatsimpl, Uid uid)
        {
            mBsi = batterystatsimpl;
            mUid = uid;
        }
    }

    public static abstract class UserInfoProvider
    {

        static boolean _2D_wrap0(UserInfoProvider userinfoprovider, int i)
        {
            return userinfoprovider.exists(i);
        }

        static void _2D_wrap1(UserInfoProvider userinfoprovider)
        {
            userinfoprovider.refreshUserIds();
        }

        private final boolean exists(int i)
        {
            boolean flag;
            if(userIds != null)
                flag = ArrayUtils.contains(userIds, i);
            else
                flag = true;
            return flag;
        }

        private final void refreshUserIds()
        {
            userIds = getUserIds();
        }

        protected abstract int[] getUserIds();

        private int userIds[];

        public UserInfoProvider()
        {
        }
    }


    static int _2D_get0()
    {
        return MAX_WAKELOCKS_PER_UID;
    }

    static BatteryCallback _2D_get1(BatteryStatsImpl batterystatsimpl)
    {
        return batterystatsimpl.mCallback;
    }

    static KernelUidCpuFreqTimeReader _2D_get2(BatteryStatsImpl batterystatsimpl)
    {
        return batterystatsimpl.mKernelUidCpuFreqTimeReader;
    }

    static KernelUidCpuTimeReader _2D_get3(BatteryStatsImpl batterystatsimpl)
    {
        return batterystatsimpl.mKernelUidCpuTimeReader;
    }

    static PowerProfile _2D_get4(BatteryStatsImpl batterystatsimpl)
    {
        return batterystatsimpl.mPowerProfile;
    }

    static UserInfoProvider _2D_get5(BatteryStatsImpl batterystatsimpl)
    {
        return batterystatsimpl.mUserInfoProvider;
    }

    static long[] _2D_set0(BatteryStatsImpl batterystatsimpl, long al[])
    {
        batterystatsimpl.mCpuFreqs = al;
        return al;
    }

    static boolean _2D_wrap0(DualTimer dualtimer, boolean flag)
    {
        return resetTimerIfNotNull(dualtimer, flag);
    }

    static boolean _2D_wrap1(Timer timer, boolean flag)
    {
        return resetTimerIfNotNull(timer, flag);
    }

    static void _2D_wrap2(LongSamplingCounter longsamplingcounter)
    {
        detachLongCounterIfNotNull(longsamplingcounter);
    }

    static void _2D_wrap3(LongSamplingCounter longsamplingcounter, boolean flag)
    {
        resetLongCounterIfNotNull(longsamplingcounter, flag);
    }

    public BatteryStatsImpl()
    {
        this(((Clocks) (new SystemClocks())));
    }

    public BatteryStatsImpl(Parcel parcel)
    {
        this(((Clocks) (new SystemClocks())), parcel);
    }

    public BatteryStatsImpl(Clocks clocks)
    {
        mKernelWakelockReader = new KernelWakelockReader();
        mTmpWakelockStats = new KernelWakelockStats();
        mKernelUidCpuTimeReader = new KernelUidCpuTimeReader();
        mKernelUidCpuFreqTimeReader = new KernelUidCpuFreqTimeReader();
        mKernelMemoryBandwidthStats = new KernelMemoryBandwidthStats();
        mKernelMemoryStats = new LongSparseArray();
        mTmpRpmStats = new RpmStats();
        mLastRpmStatsUpdateTimeMs = -1000L;
        mExternalSync = null;
        mUserInfoProvider = null;
        mIsolatedUids = new SparseIntArray();
        mUidStats = new SparseArray();
        mPartialTimers = new ArrayList();
        mFullTimers = new ArrayList();
        mWindowTimers = new ArrayList();
        mDrawTimers = new ArrayList();
        mSensorTimers = new SparseArray();
        mWifiRunningTimers = new ArrayList();
        mFullWifiLockTimers = new ArrayList();
        mWifiMulticastTimers = new ArrayList();
        mWifiScanTimers = new ArrayList();
        mWifiBatchedScanTimers = new SparseArray();
        mAudioTurnedOnTimers = new ArrayList();
        mVideoTurnedOnTimers = new ArrayList();
        mFlashlightTurnedOnTimers = new ArrayList();
        mCameraTurnedOnTimers = new ArrayList();
        mBluetoothScanOnTimers = new ArrayList();
        mLastPartialTimers = new ArrayList();
        mOnBatteryTimeBase = new TimeBase();
        mOnBatteryScreenOffTimeBase = new TimeBase();
        mActiveEvents = new android.os.BatteryStats.HistoryEventTracker();
        mHaveBatteryLevel = false;
        mRecordingHistory = false;
        mHistoryBuffer = Parcel.obtain();
        mHistoryLastWritten = new android.os.BatteryStats.HistoryItem();
        mHistoryLastLastWritten = new android.os.BatteryStats.HistoryItem();
        mHistoryReadTmp = new android.os.BatteryStats.HistoryItem();
        mHistoryAddTmp = new android.os.BatteryStats.HistoryItem();
        mHistoryTagPool = new HashMap();
        mNextHistoryTagIdx = 0;
        mNumHistoryTagChars = 0;
        mHistoryBufferLastPos = -1;
        mHistoryOverflow = false;
        mActiveHistoryStates = -1;
        mActiveHistoryStates2 = -1;
        mLastHistoryElapsedRealtime = 0L;
        mTrackRunningHistoryElapsedRealtime = 0L;
        mTrackRunningHistoryUptime = 0L;
        mHistoryCur = new android.os.BatteryStats.HistoryItem();
        mLastHistoryStepDetails = null;
        mLastHistoryStepLevel = (byte)0;
        mCurHistoryStepDetails = new android.os.BatteryStats.HistoryStepDetails();
        mReadHistoryStepDetails = new android.os.BatteryStats.HistoryStepDetails();
        mTmpHistoryStepDetails = new android.os.BatteryStats.HistoryStepDetails();
        mScreenState = 0;
        mScreenBrightnessBin = -1;
        mScreenBrightnessTimer = new StopwatchTimer[5];
        mPhoneSignalStrengthBin = -1;
        mPhoneSignalStrengthBinRaw = -1;
        mPhoneSignalStrengthsTimer = new StopwatchTimer[6];
        mPhoneDataConnectionType = -1;
        mPhoneDataConnectionsTimer = new StopwatchTimer[17];
        mNetworkByteActivityCounters = new LongSamplingCounter[10];
        mNetworkPacketActivityCounters = new LongSamplingCounter[10];
        mHasWifiReporting = false;
        mHasBluetoothReporting = false;
        mHasModemReporting = false;
        mWifiState = -1;
        mWifiStateTimer = new StopwatchTimer[8];
        mWifiSupplState = -1;
        mWifiSupplStateTimer = new StopwatchTimer[13];
        mWifiSignalStrengthBin = -1;
        mWifiSignalStrengthsTimer = new StopwatchTimer[5];
        mMobileRadioPowerState = 1;
        mWifiRadioPowerState = 1;
        mCharging = true;
        mInitStepMode = 0;
        mCurStepMode = 0;
        mModStepMode = 0;
        mDischargeStepTracker = new android.os.BatteryStats.LevelStepTracker(200);
        mDailyDischargeStepTracker = new android.os.BatteryStats.LevelStepTracker(400);
        mChargeStepTracker = new android.os.BatteryStats.LevelStepTracker(200);
        mDailyChargeStepTracker = new android.os.BatteryStats.LevelStepTracker(400);
        mDailyStartTime = 0L;
        mNextMinDailyDeadline = 0L;
        mNextMaxDailyDeadline = 0L;
        mDailyItems = new ArrayList();
        mLastWriteTime = 0L;
        mPhoneServiceState = -1;
        mPhoneServiceStateRaw = -1;
        mPhoneSimStateRaw = -1;
        mEstimatedBatteryCapacity = -1;
        mMinLearnedBatteryCapacity = -1;
        mMaxLearnedBatteryCapacity = -1;
        mRpmStats = new HashMap();
        mScreenOffRpmStats = new HashMap();
        mKernelWakelockStats = new HashMap();
        mLastWakeupReason = null;
        mLastWakeupUptimeMs = 0L;
        mWakeupReasonStats = new HashMap();
        mChangedStates = 0;
        mChangedStates2 = 0;
        mInitialAcquireWakeUid = -1;
        mWifiFullLockNesting = 0;
        mWifiScanNesting = 0;
        mWifiMulticastNesting = 0;
        mNetworkStatsFactory = new NetworkStatsFactory();
        mNetworkStatsPool = new android.util.Pools.SynchronizedPool(6);
        mWifiNetworkLock = new Object();
        mWifiIfaces = EmptyArray.STRING;
        mLastWifiNetworkStats = new NetworkStats(0L, -1);
        mModemNetworkLock = new Object();
        mModemIfaces = EmptyArray.STRING;
        mLastModemNetworkStats = new NetworkStats(0L, -1);
        mPendingWrite = null;
        mWriteLock = new ReentrantLock();
        init(clocks);
        mFile = null;
        mCheckinFile = null;
        mDailyFile = null;
        mHandler = null;
        mPlatformIdleStateCallback = null;
        mUserInfoProvider = null;
        clearHistoryLocked();
    }

    public BatteryStatsImpl(Clocks clocks, Parcel parcel)
    {
        mKernelWakelockReader = new KernelWakelockReader();
        mTmpWakelockStats = new KernelWakelockStats();
        mKernelUidCpuTimeReader = new KernelUidCpuTimeReader();
        mKernelUidCpuFreqTimeReader = new KernelUidCpuFreqTimeReader();
        mKernelMemoryBandwidthStats = new KernelMemoryBandwidthStats();
        mKernelMemoryStats = new LongSparseArray();
        mTmpRpmStats = new RpmStats();
        mLastRpmStatsUpdateTimeMs = -1000L;
        mExternalSync = null;
        mUserInfoProvider = null;
        mIsolatedUids = new SparseIntArray();
        mUidStats = new SparseArray();
        mPartialTimers = new ArrayList();
        mFullTimers = new ArrayList();
        mWindowTimers = new ArrayList();
        mDrawTimers = new ArrayList();
        mSensorTimers = new SparseArray();
        mWifiRunningTimers = new ArrayList();
        mFullWifiLockTimers = new ArrayList();
        mWifiMulticastTimers = new ArrayList();
        mWifiScanTimers = new ArrayList();
        mWifiBatchedScanTimers = new SparseArray();
        mAudioTurnedOnTimers = new ArrayList();
        mVideoTurnedOnTimers = new ArrayList();
        mFlashlightTurnedOnTimers = new ArrayList();
        mCameraTurnedOnTimers = new ArrayList();
        mBluetoothScanOnTimers = new ArrayList();
        mLastPartialTimers = new ArrayList();
        mOnBatteryTimeBase = new TimeBase();
        mOnBatteryScreenOffTimeBase = new TimeBase();
        mActiveEvents = new android.os.BatteryStats.HistoryEventTracker();
        mHaveBatteryLevel = false;
        mRecordingHistory = false;
        mHistoryBuffer = Parcel.obtain();
        mHistoryLastWritten = new android.os.BatteryStats.HistoryItem();
        mHistoryLastLastWritten = new android.os.BatteryStats.HistoryItem();
        mHistoryReadTmp = new android.os.BatteryStats.HistoryItem();
        mHistoryAddTmp = new android.os.BatteryStats.HistoryItem();
        mHistoryTagPool = new HashMap();
        mNextHistoryTagIdx = 0;
        mNumHistoryTagChars = 0;
        mHistoryBufferLastPos = -1;
        mHistoryOverflow = false;
        mActiveHistoryStates = -1;
        mActiveHistoryStates2 = -1;
        mLastHistoryElapsedRealtime = 0L;
        mTrackRunningHistoryElapsedRealtime = 0L;
        mTrackRunningHistoryUptime = 0L;
        mHistoryCur = new android.os.BatteryStats.HistoryItem();
        mLastHistoryStepDetails = null;
        mLastHistoryStepLevel = (byte)0;
        mCurHistoryStepDetails = new android.os.BatteryStats.HistoryStepDetails();
        mReadHistoryStepDetails = new android.os.BatteryStats.HistoryStepDetails();
        mTmpHistoryStepDetails = new android.os.BatteryStats.HistoryStepDetails();
        mScreenState = 0;
        mScreenBrightnessBin = -1;
        mScreenBrightnessTimer = new StopwatchTimer[5];
        mPhoneSignalStrengthBin = -1;
        mPhoneSignalStrengthBinRaw = -1;
        mPhoneSignalStrengthsTimer = new StopwatchTimer[6];
        mPhoneDataConnectionType = -1;
        mPhoneDataConnectionsTimer = new StopwatchTimer[17];
        mNetworkByteActivityCounters = new LongSamplingCounter[10];
        mNetworkPacketActivityCounters = new LongSamplingCounter[10];
        mHasWifiReporting = false;
        mHasBluetoothReporting = false;
        mHasModemReporting = false;
        mWifiState = -1;
        mWifiStateTimer = new StopwatchTimer[8];
        mWifiSupplState = -1;
        mWifiSupplStateTimer = new StopwatchTimer[13];
        mWifiSignalStrengthBin = -1;
        mWifiSignalStrengthsTimer = new StopwatchTimer[5];
        mMobileRadioPowerState = 1;
        mWifiRadioPowerState = 1;
        mCharging = true;
        mInitStepMode = 0;
        mCurStepMode = 0;
        mModStepMode = 0;
        mDischargeStepTracker = new android.os.BatteryStats.LevelStepTracker(200);
        mDailyDischargeStepTracker = new android.os.BatteryStats.LevelStepTracker(400);
        mChargeStepTracker = new android.os.BatteryStats.LevelStepTracker(200);
        mDailyChargeStepTracker = new android.os.BatteryStats.LevelStepTracker(400);
        mDailyStartTime = 0L;
        mNextMinDailyDeadline = 0L;
        mNextMaxDailyDeadline = 0L;
        mDailyItems = new ArrayList();
        mLastWriteTime = 0L;
        mPhoneServiceState = -1;
        mPhoneServiceStateRaw = -1;
        mPhoneSimStateRaw = -1;
        mEstimatedBatteryCapacity = -1;
        mMinLearnedBatteryCapacity = -1;
        mMaxLearnedBatteryCapacity = -1;
        mRpmStats = new HashMap();
        mScreenOffRpmStats = new HashMap();
        mKernelWakelockStats = new HashMap();
        mLastWakeupReason = null;
        mLastWakeupUptimeMs = 0L;
        mWakeupReasonStats = new HashMap();
        mChangedStates = 0;
        mChangedStates2 = 0;
        mInitialAcquireWakeUid = -1;
        mWifiFullLockNesting = 0;
        mWifiScanNesting = 0;
        mWifiMulticastNesting = 0;
        mNetworkStatsFactory = new NetworkStatsFactory();
        mNetworkStatsPool = new android.util.Pools.SynchronizedPool(6);
        mWifiNetworkLock = new Object();
        mWifiIfaces = EmptyArray.STRING;
        mLastWifiNetworkStats = new NetworkStats(0L, -1);
        mModemNetworkLock = new Object();
        mModemIfaces = EmptyArray.STRING;
        mLastModemNetworkStats = new NetworkStats(0L, -1);
        mPendingWrite = null;
        mWriteLock = new ReentrantLock();
        init(clocks);
        mFile = null;
        mCheckinFile = null;
        mDailyFile = null;
        mHandler = null;
        mExternalSync = null;
        clearHistoryLocked();
        readFromParcel(parcel);
        mPlatformIdleStateCallback = null;
    }

    private BatteryStatsImpl(Clocks clocks, File file, Handler handler, PlatformIdleStateCallback platformidlestatecallback, UserInfoProvider userinfoprovider)
    {
        mKernelWakelockReader = new KernelWakelockReader();
        mTmpWakelockStats = new KernelWakelockStats();
        mKernelUidCpuTimeReader = new KernelUidCpuTimeReader();
        mKernelUidCpuFreqTimeReader = new KernelUidCpuFreqTimeReader();
        mKernelMemoryBandwidthStats = new KernelMemoryBandwidthStats();
        mKernelMemoryStats = new LongSparseArray();
        mTmpRpmStats = new RpmStats();
        mLastRpmStatsUpdateTimeMs = -1000L;
        mExternalSync = null;
        mUserInfoProvider = null;
        mIsolatedUids = new SparseIntArray();
        mUidStats = new SparseArray();
        mPartialTimers = new ArrayList();
        mFullTimers = new ArrayList();
        mWindowTimers = new ArrayList();
        mDrawTimers = new ArrayList();
        mSensorTimers = new SparseArray();
        mWifiRunningTimers = new ArrayList();
        mFullWifiLockTimers = new ArrayList();
        mWifiMulticastTimers = new ArrayList();
        mWifiScanTimers = new ArrayList();
        mWifiBatchedScanTimers = new SparseArray();
        mAudioTurnedOnTimers = new ArrayList();
        mVideoTurnedOnTimers = new ArrayList();
        mFlashlightTurnedOnTimers = new ArrayList();
        mCameraTurnedOnTimers = new ArrayList();
        mBluetoothScanOnTimers = new ArrayList();
        mLastPartialTimers = new ArrayList();
        mOnBatteryTimeBase = new TimeBase();
        mOnBatteryScreenOffTimeBase = new TimeBase();
        mActiveEvents = new android.os.BatteryStats.HistoryEventTracker();
        mHaveBatteryLevel = false;
        mRecordingHistory = false;
        mHistoryBuffer = Parcel.obtain();
        mHistoryLastWritten = new android.os.BatteryStats.HistoryItem();
        mHistoryLastLastWritten = new android.os.BatteryStats.HistoryItem();
        mHistoryReadTmp = new android.os.BatteryStats.HistoryItem();
        mHistoryAddTmp = new android.os.BatteryStats.HistoryItem();
        mHistoryTagPool = new HashMap();
        mNextHistoryTagIdx = 0;
        mNumHistoryTagChars = 0;
        mHistoryBufferLastPos = -1;
        mHistoryOverflow = false;
        mActiveHistoryStates = -1;
        mActiveHistoryStates2 = -1;
        mLastHistoryElapsedRealtime = 0L;
        mTrackRunningHistoryElapsedRealtime = 0L;
        mTrackRunningHistoryUptime = 0L;
        mHistoryCur = new android.os.BatteryStats.HistoryItem();
        mLastHistoryStepDetails = null;
        mLastHistoryStepLevel = (byte)0;
        mCurHistoryStepDetails = new android.os.BatteryStats.HistoryStepDetails();
        mReadHistoryStepDetails = new android.os.BatteryStats.HistoryStepDetails();
        mTmpHistoryStepDetails = new android.os.BatteryStats.HistoryStepDetails();
        mScreenState = 0;
        mScreenBrightnessBin = -1;
        mScreenBrightnessTimer = new StopwatchTimer[5];
        mPhoneSignalStrengthBin = -1;
        mPhoneSignalStrengthBinRaw = -1;
        mPhoneSignalStrengthsTimer = new StopwatchTimer[6];
        mPhoneDataConnectionType = -1;
        mPhoneDataConnectionsTimer = new StopwatchTimer[17];
        mNetworkByteActivityCounters = new LongSamplingCounter[10];
        mNetworkPacketActivityCounters = new LongSamplingCounter[10];
        mHasWifiReporting = false;
        mHasBluetoothReporting = false;
        mHasModemReporting = false;
        mWifiState = -1;
        mWifiStateTimer = new StopwatchTimer[8];
        mWifiSupplState = -1;
        mWifiSupplStateTimer = new StopwatchTimer[13];
        mWifiSignalStrengthBin = -1;
        mWifiSignalStrengthsTimer = new StopwatchTimer[5];
        mMobileRadioPowerState = 1;
        mWifiRadioPowerState = 1;
        mCharging = true;
        mInitStepMode = 0;
        mCurStepMode = 0;
        mModStepMode = 0;
        mDischargeStepTracker = new android.os.BatteryStats.LevelStepTracker(200);
        mDailyDischargeStepTracker = new android.os.BatteryStats.LevelStepTracker(400);
        mChargeStepTracker = new android.os.BatteryStats.LevelStepTracker(200);
        mDailyChargeStepTracker = new android.os.BatteryStats.LevelStepTracker(400);
        mDailyStartTime = 0L;
        mNextMinDailyDeadline = 0L;
        mNextMaxDailyDeadline = 0L;
        mDailyItems = new ArrayList();
        mLastWriteTime = 0L;
        mPhoneServiceState = -1;
        mPhoneServiceStateRaw = -1;
        mPhoneSimStateRaw = -1;
        mEstimatedBatteryCapacity = -1;
        mMinLearnedBatteryCapacity = -1;
        mMaxLearnedBatteryCapacity = -1;
        mRpmStats = new HashMap();
        mScreenOffRpmStats = new HashMap();
        mKernelWakelockStats = new HashMap();
        mLastWakeupReason = null;
        mLastWakeupUptimeMs = 0L;
        mWakeupReasonStats = new HashMap();
        mChangedStates = 0;
        mChangedStates2 = 0;
        mInitialAcquireWakeUid = -1;
        mWifiFullLockNesting = 0;
        mWifiScanNesting = 0;
        mWifiMulticastNesting = 0;
        mNetworkStatsFactory = new NetworkStatsFactory();
        mNetworkStatsPool = new android.util.Pools.SynchronizedPool(6);
        mWifiNetworkLock = new Object();
        mWifiIfaces = EmptyArray.STRING;
        mLastWifiNetworkStats = new NetworkStats(0L, -1);
        mModemNetworkLock = new Object();
        mModemIfaces = EmptyArray.STRING;
        mLastModemNetworkStats = new NetworkStats(0L, -1);
        mPendingWrite = null;
        mWriteLock = new ReentrantLock();
        init(clocks);
        if(file != null)
            mFile = new JournaledFile(new File(file, "batterystats.bin"), new File(file, "batterystats.bin.tmp"));
        else
            mFile = null;
        mCheckinFile = new AtomicFile(new File(file, "batterystats-checkin.bin"));
        mDailyFile = new AtomicFile(new File(file, "batterystats-daily.xml"));
        mHandler = new MyHandler(handler.getLooper());
        mStartCount = mStartCount + 1;
        mScreenOnTimer = new StopwatchTimer(mClocks, null, -1, null, mOnBatteryTimeBase);
        mScreenDozeTimer = new StopwatchTimer(mClocks, null, -1, null, mOnBatteryTimeBase);
        for(int i = 0; i < 5; i++)
            mScreenBrightnessTimer[i] = new StopwatchTimer(mClocks, null, -100 - i, null, mOnBatteryTimeBase);

        mInteractiveTimer = new StopwatchTimer(mClocks, null, -10, null, mOnBatteryTimeBase);
        mPowerSaveModeEnabledTimer = new StopwatchTimer(mClocks, null, -2, null, mOnBatteryTimeBase);
        mDeviceIdleModeLightTimer = new StopwatchTimer(mClocks, null, -11, null, mOnBatteryTimeBase);
        mDeviceIdleModeFullTimer = new StopwatchTimer(mClocks, null, -14, null, mOnBatteryTimeBase);
        mDeviceLightIdlingTimer = new StopwatchTimer(mClocks, null, -15, null, mOnBatteryTimeBase);
        mDeviceIdlingTimer = new StopwatchTimer(mClocks, null, -12, null, mOnBatteryTimeBase);
        mPhoneOnTimer = new StopwatchTimer(mClocks, null, -3, null, mOnBatteryTimeBase);
        for(int j = 0; j < 6; j++)
            mPhoneSignalStrengthsTimer[j] = new StopwatchTimer(mClocks, null, -200 - j, null, mOnBatteryTimeBase);

        mPhoneSignalScanningTimer = new StopwatchTimer(mClocks, null, -199, null, mOnBatteryTimeBase);
        for(int k = 0; k < 17; k++)
            mPhoneDataConnectionsTimer[k] = new StopwatchTimer(mClocks, null, -300 - k, null, mOnBatteryTimeBase);

        for(int l = 0; l < 10; l++)
        {
            mNetworkByteActivityCounters[l] = new LongSamplingCounter(mOnBatteryTimeBase);
            mNetworkPacketActivityCounters[l] = new LongSamplingCounter(mOnBatteryTimeBase);
        }

        mWifiActivity = new ControllerActivityCounterImpl(mOnBatteryTimeBase, 1);
        mBluetoothActivity = new ControllerActivityCounterImpl(mOnBatteryTimeBase, 1);
        mModemActivity = new ControllerActivityCounterImpl(mOnBatteryTimeBase, 5);
        mMobileRadioActiveTimer = new StopwatchTimer(mClocks, null, -400, null, mOnBatteryTimeBase);
        mMobileRadioActivePerAppTimer = new StopwatchTimer(mClocks, null, -401, null, mOnBatteryTimeBase);
        mMobileRadioActiveAdjustedTime = new LongSamplingCounter(mOnBatteryTimeBase);
        mMobileRadioActiveUnknownTime = new LongSamplingCounter(mOnBatteryTimeBase);
        mMobileRadioActiveUnknownCount = new LongSamplingCounter(mOnBatteryTimeBase);
        mWifiOnTimer = new StopwatchTimer(mClocks, null, -4, null, mOnBatteryTimeBase);
        mGlobalWifiRunningTimer = new StopwatchTimer(mClocks, null, -5, null, mOnBatteryTimeBase);
        for(int i1 = 0; i1 < 8; i1++)
            mWifiStateTimer[i1] = new StopwatchTimer(mClocks, null, -600 - i1, null, mOnBatteryTimeBase);

        for(int j1 = 0; j1 < 13; j1++)
            mWifiSupplStateTimer[j1] = new StopwatchTimer(mClocks, null, -700 - j1, null, mOnBatteryTimeBase);

        for(int k1 = 0; k1 < 5; k1++)
            mWifiSignalStrengthsTimer[k1] = new StopwatchTimer(mClocks, null, -800 - k1, null, mOnBatteryTimeBase);

        mAudioOnTimer = new StopwatchTimer(mClocks, null, -7, null, mOnBatteryTimeBase);
        mVideoOnTimer = new StopwatchTimer(mClocks, null, -8, null, mOnBatteryTimeBase);
        mFlashlightOnTimer = new StopwatchTimer(mClocks, null, -9, null, mOnBatteryTimeBase);
        mCameraOnTimer = new StopwatchTimer(mClocks, null, -13, null, mOnBatteryTimeBase);
        mBluetoothScanTimer = new StopwatchTimer(mClocks, null, -14, null, mOnBatteryTimeBase);
        mDischargeScreenOffCounter = new LongSamplingCounter(mOnBatteryScreenOffTimeBase);
        mDischargeScreenDozeCounter = new LongSamplingCounter(mOnBatteryTimeBase);
        mDischargeCounter = new LongSamplingCounter(mOnBatteryTimeBase);
        mOnBatteryInternal = false;
        mOnBattery = false;
        initTimes(mClocks.uptimeMillis() * 1000L, mClocks.elapsedRealtime() * 1000L);
        clocks = Build.ID;
        mEndPlatformVersion = clocks;
        mStartPlatformVersion = clocks;
        mDischargeStartLevel = 0;
        mDischargeUnplugLevel = 0;
        mDischargePlugLevel = -1;
        mDischargeCurrentLevel = 0;
        mCurrentBatteryLevel = 0;
        initDischarge();
        clearHistoryLocked();
        updateDailyDeadlineLocked();
        mPlatformIdleStateCallback = platformidlestatecallback;
        mUserInfoProvider = userinfoprovider;
    }

    public BatteryStatsImpl(File file, Handler handler, PlatformIdleStateCallback platformidlestatecallback, UserInfoProvider userinfoprovider)
    {
        this(((Clocks) (new SystemClocks())), file, handler, platformidlestatecallback, userinfoprovider);
    }

    private void addHistoryBufferLocked(long l, long l1, byte byte0, android.os.BatteryStats.HistoryItem historyitem)
    {
        if(mIteratingHistory)
        {
            throw new IllegalStateException("Can't do this while iterating history!");
        } else
        {
            mHistoryBufferLastPos = mHistoryBuffer.dataPosition();
            mHistoryLastLastWritten.setTo(mHistoryLastWritten);
            mHistoryLastWritten.setTo(mHistoryBaseTime + l, byte0, historyitem);
            android.os.BatteryStats.HistoryItem historyitem1 = mHistoryLastWritten;
            historyitem1.states = historyitem1.states & mActiveHistoryStates;
            historyitem1 = mHistoryLastWritten;
            historyitem1.states2 = historyitem1.states2 & mActiveHistoryStates2;
            writeHistoryDelta(mHistoryBuffer, mHistoryLastWritten, mHistoryLastLastWritten);
            mLastHistoryElapsedRealtime = l;
            historyitem.wakelockTag = null;
            historyitem.wakeReasonTag = null;
            historyitem.eventCode = 0;
            historyitem.eventTag = null;
            return;
        }
    }

    private void addPackageChange(android.os.BatteryStats.PackageChange packagechange)
    {
        if(mDailyPackageChanges == null)
            mDailyPackageChanges = new ArrayList();
        mDailyPackageChanges.add(packagechange);
    }

    private int buildBatteryLevelInt(android.os.BatteryStats.HistoryItem historyitem)
    {
        return historyitem.batteryLevel << 25 & 0xfe000000 | historyitem.batteryTemperature << 15 & 0x1ff8000 | historyitem.batteryVoltage << 1 & 0x7ffe;
    }

    private int buildStateInt(android.os.BatteryStats.HistoryItem historyitem)
    {
        int i = 0;
        if((historyitem.batteryPlugType & 1) == 0) goto _L2; else goto _L1
_L1:
        i = 1;
_L4:
        return (historyitem.batteryStatus & 7) << 29 | (historyitem.batteryHealth & 7) << 26 | (i & 3) << 24 | historyitem.states & 0xffffff;
_L2:
        if((historyitem.batteryPlugType & 2) != 0)
            i = 2;
        else
        if((historyitem.batteryPlugType & 4) != 0)
            i = 3;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void computeHistoryStepDetails(android.os.BatteryStats.HistoryStepDetails historystepdetails, android.os.BatteryStats.HistoryStepDetails historystepdetails1)
    {
        android.os.BatteryStats.HistoryStepDetails historystepdetails2;
        if(historystepdetails1 != null)
            historystepdetails2 = mTmpHistoryStepDetails;
        else
            historystepdetails2 = historystepdetails;
        requestImmediateCpuUpdate();
        if(historystepdetails1 == null)
        {
            int i = mUidStats.size();
            for(int k = 0; k < i; k++)
            {
                historystepdetails = (Uid)mUidStats.valueAt(k);
                historystepdetails.mLastStepUserTime = ((Uid) (historystepdetails)).mCurStepUserTime;
                historystepdetails.mLastStepSystemTime = ((Uid) (historystepdetails)).mCurStepSystemTime;
            }

            mLastStepCpuUserTime = mCurStepCpuUserTime;
            mLastStepCpuSystemTime = mCurStepCpuSystemTime;
            mLastStepStatUserTime = mCurStepStatUserTime;
            mLastStepStatSystemTime = mCurStepStatSystemTime;
            mLastStepStatIOWaitTime = mCurStepStatIOWaitTime;
            mLastStepStatIrqTime = mCurStepStatIrqTime;
            mLastStepStatSoftIrqTime = mCurStepStatSoftIrqTime;
            mLastStepStatIdleTime = mCurStepStatIdleTime;
            historystepdetails2.clear();
            return;
        }
        historystepdetails.userTime = (int)(mCurStepCpuUserTime - mLastStepCpuUserTime);
        historystepdetails.systemTime = (int)(mCurStepCpuSystemTime - mLastStepCpuSystemTime);
        historystepdetails.statUserTime = (int)(mCurStepStatUserTime - mLastStepStatUserTime);
        historystepdetails.statSystemTime = (int)(mCurStepStatSystemTime - mLastStepStatSystemTime);
        historystepdetails.statIOWaitTime = (int)(mCurStepStatIOWaitTime - mLastStepStatIOWaitTime);
        historystepdetails.statIrqTime = (int)(mCurStepStatIrqTime - mLastStepStatIrqTime);
        historystepdetails.statSoftIrqTime = (int)(mCurStepStatSoftIrqTime - mLastStepStatSoftIrqTime);
        historystepdetails.statIdlTime = (int)(mCurStepStatIdleTime - mLastStepStatIdleTime);
        historystepdetails.appCpuUid3 = -1;
        historystepdetails.appCpuUid2 = -1;
        historystepdetails.appCpuUid1 = -1;
        historystepdetails.appCpuUTime3 = 0;
        historystepdetails.appCpuUTime2 = 0;
        historystepdetails.appCpuUTime1 = 0;
        historystepdetails.appCpuSTime3 = 0;
        historystepdetails.appCpuSTime2 = 0;
        historystepdetails.appCpuSTime1 = 0;
        int j = mUidStats.size();
        int l = 0;
        while(l < j) 
        {
            historystepdetails1 = (Uid)mUidStats.valueAt(l);
            int i1 = (int)(((Uid) (historystepdetails1)).mCurStepUserTime - ((Uid) (historystepdetails1)).mLastStepUserTime);
            int j1 = (int)(((Uid) (historystepdetails1)).mCurStepSystemTime - ((Uid) (historystepdetails1)).mLastStepSystemTime);
            int k1 = i1 + j1;
            historystepdetails1.mLastStepUserTime = ((Uid) (historystepdetails1)).mCurStepUserTime;
            historystepdetails1.mLastStepSystemTime = ((Uid) (historystepdetails1)).mCurStepSystemTime;
            if(k1 > historystepdetails.appCpuUTime3 + historystepdetails.appCpuSTime3)
                if(k1 <= historystepdetails.appCpuUTime2 + historystepdetails.appCpuSTime2)
                {
                    historystepdetails.appCpuUid3 = ((Uid) (historystepdetails1)).mUid;
                    historystepdetails.appCpuUTime3 = i1;
                    historystepdetails.appCpuSTime3 = j1;
                } else
                {
                    historystepdetails.appCpuUid3 = historystepdetails.appCpuUid2;
                    historystepdetails.appCpuUTime3 = historystepdetails.appCpuUTime2;
                    historystepdetails.appCpuSTime3 = historystepdetails.appCpuSTime2;
                    if(k1 <= historystepdetails.appCpuUTime1 + historystepdetails.appCpuSTime1)
                    {
                        historystepdetails.appCpuUid2 = ((Uid) (historystepdetails1)).mUid;
                        historystepdetails.appCpuUTime2 = i1;
                        historystepdetails.appCpuSTime2 = j1;
                    } else
                    {
                        historystepdetails.appCpuUid2 = historystepdetails.appCpuUid1;
                        historystepdetails.appCpuUTime2 = historystepdetails.appCpuUTime1;
                        historystepdetails.appCpuSTime2 = historystepdetails.appCpuSTime1;
                        historystepdetails.appCpuUid1 = ((Uid) (historystepdetails1)).mUid;
                        historystepdetails.appCpuUTime1 = i1;
                        historystepdetails.appCpuSTime1 = j1;
                    }
                }
            l++;
        }
        mLastStepCpuUserTime = mCurStepCpuUserTime;
        mLastStepCpuSystemTime = mCurStepCpuSystemTime;
        mLastStepStatUserTime = mCurStepStatUserTime;
        mLastStepStatSystemTime = mCurStepStatSystemTime;
        mLastStepStatIOWaitTime = mCurStepStatIOWaitTime;
        mLastStepStatIrqTime = mCurStepStatIrqTime;
        mLastStepStatSoftIrqTime = mCurStepStatSoftIrqTime;
        mLastStepStatIdleTime = mCurStepStatIdleTime;
    }

    private long computeTimePerLevel(long al[], int i)
    {
        if(i <= 0)
            return -1L;
        long l = 0L;
        for(int j = 0; j < i; j++)
            l += al[j] & 0xffffffffffL;

        return l / (long)i;
    }

    private static void detachLongCounterIfNotNull(LongSamplingCounter longsamplingcounter)
    {
        if(longsamplingcounter != null)
            longsamplingcounter.detach();
    }

    private static void detachTimerIfNotNull(Timer timer)
    {
        if(timer != null)
            timer.detach();
    }

    private static String[] excludeFromStringArray(String as[], String s)
    {
        int i = ArrayUtils.indexOf(as, s);
        if(i >= 0)
        {
            s = new String[as.length - 1];
            if(i > 0)
                System.arraycopy(as, 0, s, 0, i);
            if(i < as.length - 1)
                System.arraycopy(as, i + 1, s, i, as.length - i - 1);
            return s;
        } else
        {
            return as;
        }
    }

    private int fixPhoneServiceState(int i, int j)
    {
        int k = i;
        if(mPhoneSimStateRaw == 1)
        {
            k = i;
            if(i == 1)
            {
                k = i;
                if(j > 0)
                    k = 0;
            }
        }
        return k;
    }

    private static String[] includeInStringArray(String as[], String s)
    {
        if(ArrayUtils.indexOf(as, s) >= 0)
        {
            return as;
        } else
        {
            String as1[] = new String[as.length + 1];
            System.arraycopy(as, 0, as1, 0, as.length);
            as1[as.length] = s;
            return as1;
        }
    }

    private void init(Clocks clocks)
    {
        mClocks = clocks;
    }

    private void initActiveHistoryEventsLocked(long l, long l1)
    {
        int i = 0;
        while(i < 22) 
        {
            HashMap hashmap;
            if(mRecordAllHistory || i != 1)
                if((hashmap = mActiveEvents.getStateForEvent(i)) != null)
                {
                    Iterator iterator = hashmap.entrySet().iterator();
                    while(iterator.hasNext()) 
                    {
                        java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                        SparseIntArray sparseintarray = (SparseIntArray)entry.getValue();
                        int j = 0;
                        while(j < sparseintarray.size()) 
                        {
                            addHistoryEventLocked(l, l1, i, (String)entry.getKey(), sparseintarray.keyAt(j));
                            j++;
                        }
                    }
                }
            i++;
        }
    }

    private void noteBluetoothScanStartedLocked(int i, boolean flag)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        if(mBluetoothScanNesting == 0)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states2 = historyitem.states2 | 0x100000;
            addHistoryRecordLocked(l, l1);
            mBluetoothScanTimer.startRunningLocked(l);
        }
        mBluetoothScanNesting = mBluetoothScanNesting + 1;
        getUidStatsLocked(i).noteBluetoothScanStartedLocked(l, flag);
    }

    private void noteBluetoothScanStoppedLocked(int i, boolean flag)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        mBluetoothScanNesting = mBluetoothScanNesting - 1;
        if(mBluetoothScanNesting == 0)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states2 = historyitem.states2 & 0xffefffff;
            addHistoryRecordLocked(l, l1);
            mBluetoothScanTimer.stopRunningLocked(l);
        }
        getUidStatsLocked(i).noteBluetoothScanStoppedLocked(l, flag);
    }

    private void noteMobileRadioApWakeupLocked(long l, long l1, int i)
    {
        i = mapUid(i);
        addHistoryEventLocked(l, l1, 19, "", i);
        getUidStatsLocked(i).noteMobileRadioApWakeupLocked();
    }

    private void noteWifiRadioApWakeupLocked(long l, long l1, int i)
    {
        i = mapUid(i);
        addHistoryEventLocked(l, l1, 19, "", i);
        getUidStatsLocked(i).noteWifiRadioApWakeupLocked();
    }

    private void readBatteryLevelInt(int i, android.os.BatteryStats.HistoryItem historyitem)
    {
        historyitem.batteryLevel = (byte)((0xfe000000 & i) >>> 25);
        historyitem.batteryTemperature = (short)((0x1ff8000 & i) >>> 15);
        historyitem.batteryVoltage = (char)((i & 0x7ffe) >>> 1);
    }

    private void readDailyItemsLocked(XmlPullParser xmlpullparser)
    {
label0:
        {
            {
                int i;
                do
                    i = xmlpullparser.next();
                while(i != 2 && i != 1);
                if(i == 2)
                    break label0;
                int j;
                StringBuilder stringbuilder;
                try
                {
                    xmlpullparser = JVM INSTR new #1033 <Class IllegalStateException>;
                    xmlpullparser.IllegalStateException("no start tag found");
                    throw xmlpullparser;
                }
                // Misplaced declaration of an exception variable
                catch(XmlPullParser xmlpullparser)
                {
                    Slog.w("BatteryStatsImpl", (new StringBuilder()).append("Failed parsing daily ").append(xmlpullparser).toString());
                }
                // Misplaced declaration of an exception variable
                catch(XmlPullParser xmlpullparser)
                {
                    Slog.w("BatteryStatsImpl", (new StringBuilder()).append("Failed parsing daily ").append(xmlpullparser).toString());
                }
                // Misplaced declaration of an exception variable
                catch(XmlPullParser xmlpullparser)
                {
                    Slog.w("BatteryStatsImpl", (new StringBuilder()).append("Failed parsing daily ").append(xmlpullparser).toString());
                }
                // Misplaced declaration of an exception variable
                catch(XmlPullParser xmlpullparser)
                {
                    Slog.w("BatteryStatsImpl", (new StringBuilder()).append("Failed parsing daily ").append(xmlpullparser).toString());
                }
                // Misplaced declaration of an exception variable
                catch(XmlPullParser xmlpullparser)
                {
                    Slog.w("BatteryStatsImpl", (new StringBuilder()).append("Failed parsing daily ").append(xmlpullparser).toString());
                }
                // Misplaced declaration of an exception variable
                catch(XmlPullParser xmlpullparser)
                {
                    Slog.w("BatteryStatsImpl", (new StringBuilder()).append("Failed parsing daily ").append(xmlpullparser).toString());
                }
            }
            return;
        }
        j = xmlpullparser.getDepth();
_L4:
        i = xmlpullparser.next();
        if(i == 1)
            break MISSING_BLOCK_LABEL_62;
        if(i != 3)
            break; /* Loop/switch isn't completed */
        if(xmlpullparser.getDepth() <= j) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_62;
_L1:
        if(i == 3 || i == 4) goto _L4; else goto _L3
_L3:
label1:
        {
            if(!xmlpullparser.getName().equals("item"))
                break label1;
            readDailyItemTagLocked(xmlpullparser);
        }
          goto _L4
        stringbuilder = JVM INSTR new #1352 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Slog.w("BatteryStatsImpl", stringbuilder.append("Unknown element under <daily-items>: ").append(xmlpullparser.getName()).toString());
        XmlUtils.skipCurrentTag(xmlpullparser);
          goto _L4
    }

    private void readHistoryTag(int i, android.os.BatteryStats.HistoryTag historytag)
    {
        historytag.string = mReadHistoryStrings[i];
        historytag.uid = mReadHistoryUids[i];
        historytag.poolIdx = i;
    }

    private NetworkStats readNetworkStatsLocked(String as[])
    {
        NetworkStats networkstats;
        if(ArrayUtils.isEmpty(as))
            break MISSING_BLOCK_LABEL_62;
        networkstats = mNetworkStatsFactory.readNetworkStatsDetail(-1, as, 0, (NetworkStats)mNetworkStatsPool.acquire());
        return networkstats;
        IOException ioexception;
        ioexception;
        Slog.e("BatteryStatsImpl", (new StringBuilder()).append("failed to read network stats for ifaces: ").append(Arrays.toString(as)).toString());
        return null;
    }

    private void recordCurrentTimeChangeLocked(long l, long l1, long l2)
    {
        if(mRecordingHistory)
        {
            mHistoryCur.currentTime = l;
            addHistoryBufferLocked(l1, l2, (byte)5, mHistoryCur);
            mHistoryCur.currentTime = 0L;
        }
    }

    private void recordShutdownLocked(long l, long l1)
    {
        if(mRecordingHistory)
        {
            mHistoryCur.currentTime = System.currentTimeMillis();
            addHistoryBufferLocked(l, l1, (byte)8, mHistoryCur);
            mHistoryCur.currentTime = 0L;
        }
    }

    private void requestImmediateCpuUpdate()
    {
        mHandler.removeMessages(1);
        mHandler.sendEmptyMessage(1);
    }

    private void requestWakelockCpuUpdate()
    {
        if(!mHandler.hasMessages(1))
        {
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessageDelayed(message, 5000L);
        }
    }

    private void resetAllStatsLocked()
    {
        long l = mClocks.uptimeMillis();
        long l1 = mClocks.elapsedRealtime();
        mStartCount = 0;
        initTimes(1000L * l, 1000L * l1);
        mScreenOnTimer.reset(false);
        mScreenDozeTimer.reset(false);
        for(int i = 0; i < 5; i++)
            mScreenBrightnessTimer[i].reset(false);

        if(mPowerProfile != null)
            mEstimatedBatteryCapacity = (int)mPowerProfile.getBatteryCapacity();
        else
            mEstimatedBatteryCapacity = -1;
        mMinLearnedBatteryCapacity = -1;
        mMaxLearnedBatteryCapacity = -1;
        mInteractiveTimer.reset(false);
        mPowerSaveModeEnabledTimer.reset(false);
        mLastIdleTimeStart = l1;
        mLongestLightIdleTime = 0L;
        mLongestFullIdleTime = 0L;
        mDeviceIdleModeLightTimer.reset(false);
        mDeviceIdleModeFullTimer.reset(false);
        mDeviceLightIdlingTimer.reset(false);
        mDeviceIdlingTimer.reset(false);
        mPhoneOnTimer.reset(false);
        mAudioOnTimer.reset(false);
        mVideoOnTimer.reset(false);
        mFlashlightOnTimer.reset(false);
        mCameraOnTimer.reset(false);
        mBluetoothScanTimer.reset(false);
        for(int j = 0; j < 6; j++)
            mPhoneSignalStrengthsTimer[j].reset(false);

        mPhoneSignalScanningTimer.reset(false);
        for(int k = 0; k < 17; k++)
            mPhoneDataConnectionsTimer[k].reset(false);

        for(int i1 = 0; i1 < 10; i1++)
        {
            mNetworkByteActivityCounters[i1].reset(false);
            mNetworkPacketActivityCounters[i1].reset(false);
        }

        mMobileRadioActiveTimer.reset(false);
        mMobileRadioActivePerAppTimer.reset(false);
        mMobileRadioActiveAdjustedTime.reset(false);
        mMobileRadioActiveUnknownTime.reset(false);
        mMobileRadioActiveUnknownCount.reset(false);
        mWifiOnTimer.reset(false);
        mGlobalWifiRunningTimer.reset(false);
        for(int j1 = 0; j1 < 8; j1++)
            mWifiStateTimer[j1].reset(false);

        for(int k1 = 0; k1 < 13; k1++)
            mWifiSupplStateTimer[k1].reset(false);

        for(int i2 = 0; i2 < 5; i2++)
            mWifiSignalStrengthsTimer[i2].reset(false);

        mWifiActivity.reset(false);
        mBluetoothActivity.reset(false);
        mModemActivity.reset(false);
        mUnpluggedNumConnectivityChange = 0;
        mLoadedNumConnectivityChange = 0;
        mNumConnectivityChange = 0;
        int l2;
        for(int j2 = 0; j2 < mUidStats.size(); j2 = l2 + 1)
        {
            l2 = j2;
            if(((Uid)mUidStats.valueAt(j2)).reset(1000L * l, 1000L * l1))
            {
                mUidStats.remove(mUidStats.keyAt(j2));
                l2 = j2 - 1;
            }
        }

        if(mRpmStats.size() > 0)
        {
            SamplingTimer samplingtimer2;
            for(Iterator iterator = mRpmStats.values().iterator(); iterator.hasNext(); mOnBatteryTimeBase.remove(samplingtimer2))
                samplingtimer2 = (SamplingTimer)iterator.next();

            mRpmStats.clear();
        }
        if(mScreenOffRpmStats.size() > 0)
        {
            SamplingTimer samplingtimer3;
            for(Iterator iterator1 = mScreenOffRpmStats.values().iterator(); iterator1.hasNext(); mOnBatteryScreenOffTimeBase.remove(samplingtimer3))
                samplingtimer3 = (SamplingTimer)iterator1.next();

            mScreenOffRpmStats.clear();
        }
        if(mKernelWakelockStats.size() > 0)
        {
            SamplingTimer samplingtimer;
            for(Iterator iterator2 = mKernelWakelockStats.values().iterator(); iterator2.hasNext(); mOnBatteryScreenOffTimeBase.remove(samplingtimer))
                samplingtimer = (SamplingTimer)iterator2.next();

            mKernelWakelockStats.clear();
        }
        if(mKernelMemoryStats.size() > 0)
        {
            for(int k2 = 0; k2 < mKernelMemoryStats.size(); k2++)
                mOnBatteryTimeBase.remove((TimeBaseObs)mKernelMemoryStats.valueAt(k2));

            mKernelMemoryStats.clear();
        }
        if(mWakeupReasonStats.size() > 0)
        {
            SamplingTimer samplingtimer1;
            for(Iterator iterator3 = mWakeupReasonStats.values().iterator(); iterator3.hasNext(); mOnBatteryTimeBase.remove(samplingtimer1))
                samplingtimer1 = (SamplingTimer)iterator3.next();

            mWakeupReasonStats.clear();
        }
        mLastHistoryStepDetails = null;
        mLastStepCpuSystemTime = 0L;
        mLastStepCpuUserTime = 0L;
        mCurStepCpuSystemTime = 0L;
        mCurStepCpuUserTime = 0L;
        mCurStepCpuUserTime = 0L;
        mLastStepCpuUserTime = 0L;
        mCurStepCpuSystemTime = 0L;
        mLastStepCpuSystemTime = 0L;
        mCurStepStatUserTime = 0L;
        mLastStepStatUserTime = 0L;
        mCurStepStatSystemTime = 0L;
        mLastStepStatSystemTime = 0L;
        mCurStepStatIOWaitTime = 0L;
        mLastStepStatIOWaitTime = 0L;
        mCurStepStatIrqTime = 0L;
        mLastStepStatIrqTime = 0L;
        mCurStepStatSoftIrqTime = 0L;
        mLastStepStatSoftIrqTime = 0L;
        mCurStepStatIdleTime = 0L;
        mLastStepStatIdleTime = 0L;
        initDischarge();
        clearHistoryLocked();
    }

    private static void resetLongCounterIfNotNull(LongSamplingCounter longsamplingcounter, boolean flag)
    {
        if(longsamplingcounter != null)
            longsamplingcounter.reset(flag);
    }

    private static boolean resetTimerIfNotNull(DualTimer dualtimer, boolean flag)
    {
        if(dualtimer != null)
            return dualtimer.reset(flag);
        else
            return true;
    }

    private static boolean resetTimerIfNotNull(Timer timer, boolean flag)
    {
        if(timer != null)
            return timer.reset(flag);
        else
            return true;
    }

    private void scheduleSyncExternalStatsLocked(String s, int i)
    {
        if(mExternalSync != null)
            mExternalSync.scheduleSync(s, i);
    }

    private void startRecordingHistory(long l, long l1, boolean flag)
    {
        mRecordingHistory = true;
        mHistoryCur.currentTime = System.currentTimeMillis();
        byte byte2;
        if(flag)
        {
            byte byte0 = 7;
            byte2 = byte0;
        } else
        {
            byte byte1 = 5;
            byte2 = byte1;
        }
        addHistoryBufferLocked(l, l1, byte2, mHistoryCur);
        mHistoryCur.currentTime = 0L;
        if(flag)
            initActiveHistoryEventsLocked(l, l1);
    }

    private void updateAllPhoneStateLocked(int i, int j, int k)
    {
        boolean flag = false;
        boolean flag2 = false;
        mPhoneServiceStateRaw = i;
        mPhoneSimStateRaw = j;
        mPhoneSignalStrengthBinRaw = k;
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        int i1 = i;
        if(j == 1)
        {
            i1 = i;
            if(i == 1)
            {
                i1 = i;
                if(k > 0)
                    i1 = 0;
            }
        }
        int j1;
        if(i1 == 3)
        {
            j = -1;
            j1 = ((flag) ? 1 : 0);
            i = ((flag2) ? 1 : 0);
        } else
        {
            i = ((flag2) ? 1 : 0);
            j1 = ((flag) ? 1 : 0);
            j = k;
            if(i1 != 0)
            {
                i = ((flag2) ? 1 : 0);
                j1 = ((flag) ? 1 : 0);
                j = k;
                if(i1 == 1)
                {
                    k = 1;
                    boolean flag1 = false;
                    i = ((flag2) ? 1 : 0);
                    j1 = k;
                    j = ((flag1) ? 1 : 0);
                    if(!mPhoneSignalScanningTimer.isRunningLocked())
                    {
                        android.os.BatteryStats.HistoryItem historyitem1 = mHistoryCur;
                        historyitem1.states = historyitem1.states | 0x200000;
                        i = 1;
                        mPhoneSignalScanningTimer.startRunningLocked(l);
                        j1 = k;
                        j = ((flag1) ? 1 : 0);
                    }
                }
            }
        }
        k = i;
        if(j1 == 0)
        {
            k = i;
            if(mPhoneSignalScanningTimer.isRunningLocked())
            {
                android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
                historyitem.states = historyitem.states & 0xffdfffff;
                k = 1;
                mPhoneSignalScanningTimer.stopRunningLocked(l);
            }
        }
        i = k;
        if(mPhoneServiceState != i1)
        {
            mHistoryCur.states = mHistoryCur.states & 0xfffffe3f | i1 << 6;
            i = 1;
            mPhoneServiceState = i1;
        }
        k = i;
        if(mPhoneSignalStrengthBin != j)
        {
            if(mPhoneSignalStrengthBin >= 0)
                mPhoneSignalStrengthsTimer[mPhoneSignalStrengthBin].stopRunningLocked(l);
            if(j >= 0)
            {
                if(!mPhoneSignalStrengthsTimer[j].isRunningLocked())
                    mPhoneSignalStrengthsTimer[j].startRunningLocked(l);
                mHistoryCur.states = mHistoryCur.states & 0xffffffc7 | j << 3;
                i = 1;
            } else
            {
                stopAllPhoneSignalStrengthTimersLocked(-1);
            }
            mPhoneSignalStrengthBin = j;
            k = i;
        }
        if(k != 0)
            addHistoryRecordLocked(l, l1);
    }

    private void updateBatteryPropertiesLocked()
    {
        android.os.IBatteryPropertiesRegistrar.Stub.asInterface(ServiceManager.getService("batteryproperties")).scheduleUpdate();
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void updateNewDischargeScreenLevelLocked(int i)
    {
        if(!isScreenOn(i)) goto _L2; else goto _L1
_L1:
        mDischargeScreenOnUnplugLevel = mDischargeCurrentLevel;
        mDischargeScreenOffUnplugLevel = 0;
        mDischargeScreenDozeUnplugLevel = 0;
_L4:
        return;
_L2:
        if(isScreenDoze(i))
        {
            mDischargeScreenOnUnplugLevel = 0;
            mDischargeScreenDozeUnplugLevel = mDischargeCurrentLevel;
            mDischargeScreenOffUnplugLevel = 0;
        } else
        if(isScreenOff(i))
        {
            mDischargeScreenOnUnplugLevel = 0;
            mDischargeScreenDozeUnplugLevel = 0;
            mDischargeScreenOffUnplugLevel = mDischargeCurrentLevel;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void updateOldDischargeScreenLevelLocked(int i)
    {
        if(!isScreenOn(i)) goto _L2; else goto _L1
_L1:
        i = mDischargeScreenOnUnplugLevel - mDischargeCurrentLevel;
        if(i > 0)
        {
            mDischargeAmountScreenOn = mDischargeAmountScreenOn + i;
            mDischargeAmountScreenOnSinceCharge = mDischargeAmountScreenOnSinceCharge + i;
        }
_L4:
        return;
_L2:
        if(isScreenDoze(i))
        {
            i = mDischargeScreenDozeUnplugLevel - mDischargeCurrentLevel;
            if(i > 0)
            {
                mDischargeAmountScreenDoze = mDischargeAmountScreenDoze + i;
                mDischargeAmountScreenDozeSinceCharge = mDischargeAmountScreenDozeSinceCharge + i;
            }
        } else
        if(isScreenOff(i))
        {
            i = mDischargeScreenOffUnplugLevel - mDischargeCurrentLevel;
            if(i > 0)
            {
                mDischargeAmountScreenOff = mDischargeAmountScreenOff + i;
                mDischargeAmountScreenOffSinceCharge = mDischargeAmountScreenOffSinceCharge + i;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void writeDailyItemsLocked(XmlSerializer xmlserializer)
        throws IOException
    {
        StringBuilder stringbuilder = new StringBuilder(64);
        xmlserializer.startDocument(null, Boolean.valueOf(true));
        xmlserializer.startTag(null, "daily-items");
        for(int i = 0; i < mDailyItems.size(); i++)
        {
            android.os.BatteryStats.DailyItem dailyitem = (android.os.BatteryStats.DailyItem)mDailyItems.get(i);
            xmlserializer.startTag(null, "item");
            xmlserializer.attribute(null, "start", Long.toString(dailyitem.mStartTime));
            xmlserializer.attribute(null, "end", Long.toString(dailyitem.mEndTime));
            writeDailyLevelSteps(xmlserializer, "dis", dailyitem.mDischargeSteps, stringbuilder);
            writeDailyLevelSteps(xmlserializer, "chg", dailyitem.mChargeSteps, stringbuilder);
            if(dailyitem.mPackageChanges != null)
            {
                int j = 0;
                while(j < dailyitem.mPackageChanges.size()) 
                {
                    android.os.BatteryStats.PackageChange packagechange = (android.os.BatteryStats.PackageChange)dailyitem.mPackageChanges.get(j);
                    if(packagechange.mUpdate)
                    {
                        xmlserializer.startTag(null, "upd");
                        xmlserializer.attribute(null, "pkg", packagechange.mPackageName);
                        xmlserializer.attribute(null, "ver", Integer.toString(packagechange.mVersionCode));
                        xmlserializer.endTag(null, "upd");
                    } else
                    {
                        xmlserializer.startTag(null, "rem");
                        xmlserializer.attribute(null, "pkg", packagechange.mPackageName);
                        xmlserializer.endTag(null, "rem");
                    }
                    j++;
                }
            }
            xmlserializer.endTag(null, "item");
        }

        xmlserializer.endTag(null, "daily-items");
        xmlserializer.endDocument();
    }

    private void writeDailyLevelSteps(XmlSerializer xmlserializer, String s, android.os.BatteryStats.LevelStepTracker levelsteptracker, StringBuilder stringbuilder)
        throws IOException
    {
        if(levelsteptracker != null)
        {
            xmlserializer.startTag(null, s);
            xmlserializer.attribute(null, "n", Integer.toString(levelsteptracker.mNumStepDurations));
            for(int i = 0; i < levelsteptracker.mNumStepDurations; i++)
            {
                xmlserializer.startTag(null, "s");
                stringbuilder.setLength(0);
                levelsteptracker.encodeEntryAt(i, stringbuilder);
                xmlserializer.attribute(null, "v", stringbuilder.toString());
                xmlserializer.endTag(null, "s");
            }

            xmlserializer.endTag(null, s);
        }
    }

    private int writeHistoryTag(android.os.BatteryStats.HistoryTag historytag)
    {
        Integer integer = (Integer)mHistoryTagPool.get(historytag);
        int i;
        if(integer != null)
        {
            i = integer.intValue();
        } else
        {
            i = mNextHistoryTagIdx;
            android.os.BatteryStats.HistoryTag historytag1 = new android.os.BatteryStats.HistoryTag();
            historytag1.setTo(historytag);
            historytag.poolIdx = i;
            mHistoryTagPool.put(historytag1, Integer.valueOf(i));
            mNextHistoryTagIdx = mNextHistoryTagIdx + 1;
            mNumHistoryTagChars = mNumHistoryTagChars + (historytag1.string.length() + 1);
        }
        return i;
    }

    void addHistoryBufferLocked(long l, long l1, android.os.BatteryStats.HistoryItem historyitem)
    {
        long l4;
label0:
        {
            if(!mHaveBatteryLevel || mRecordingHistory ^ true)
                return;
            long l2 = mHistoryBaseTime;
            long l3 = mHistoryLastWritten.time;
            int i = mHistoryLastWritten.states;
            int j = historyitem.states;
            int i1 = mActiveHistoryStates;
            int j1 = mHistoryLastWritten.states2;
            int k1 = historyitem.states2;
            int j2 = mActiveHistoryStates2;
            int k2 = mHistoryLastWritten.states;
            int j3 = mHistoryLastLastWritten.states;
            int k3 = mHistoryLastWritten.states2;
            int i4 = mHistoryLastLastWritten.states2;
            l4 = l;
            if(mHistoryBufferLastPos < 0)
                break label0;
            l4 = l;
            if(mHistoryLastWritten.cmd != 0)
                break label0;
            l4 = l;
            if((l2 + l) - l3 >= 1000L)
                break label0;
            l4 = l;
            if(((i ^ j & i1) & (k2 ^ j3)) != 0)
                break label0;
            l4 = l;
            if(((j1 ^ k1 & j2) & (k3 ^ i4)) != 0)
                break label0;
            if(mHistoryLastWritten.wakelockTag != null)
            {
                l4 = l;
                if(historyitem.wakelockTag != null)
                    break label0;
            }
            if(mHistoryLastWritten.wakeReasonTag != null)
            {
                l4 = l;
                if(historyitem.wakeReasonTag != null)
                    break label0;
            }
            l4 = l;
            if(mHistoryLastWritten.stepDetails != null)
                break label0;
            if(mHistoryLastWritten.eventCode != 0)
            {
                l4 = l;
                if(historyitem.eventCode != 0)
                    break label0;
            }
            l4 = l;
            if(mHistoryLastWritten.batteryLevel == historyitem.batteryLevel)
            {
                l4 = l;
                if(mHistoryLastWritten.batteryStatus == historyitem.batteryStatus)
                {
                    l4 = l;
                    if(mHistoryLastWritten.batteryHealth == historyitem.batteryHealth)
                    {
                        l4 = l;
                        if(mHistoryLastWritten.batteryPlugType == historyitem.batteryPlugType)
                        {
                            l4 = l;
                            if(mHistoryLastWritten.batteryTemperature == historyitem.batteryTemperature)
                            {
                                l4 = l;
                                if(mHistoryLastWritten.batteryVoltage == historyitem.batteryVoltage)
                                {
                                    mHistoryBuffer.setDataSize(mHistoryBufferLastPos);
                                    mHistoryBuffer.setDataPosition(mHistoryBufferLastPos);
                                    mHistoryBufferLastPos = -1;
                                    l4 = mHistoryLastWritten.time - mHistoryBaseTime;
                                    if(mHistoryLastWritten.wakelockTag != null)
                                    {
                                        historyitem.wakelockTag = historyitem.localWakelockTag;
                                        historyitem.wakelockTag.setTo(mHistoryLastWritten.wakelockTag);
                                    }
                                    if(mHistoryLastWritten.wakeReasonTag != null)
                                    {
                                        historyitem.wakeReasonTag = historyitem.localWakeReasonTag;
                                        historyitem.wakeReasonTag.setTo(mHistoryLastWritten.wakeReasonTag);
                                    }
                                    if(mHistoryLastWritten.eventCode != 0)
                                    {
                                        historyitem.eventCode = mHistoryLastWritten.eventCode;
                                        historyitem.eventTag = historyitem.localEventTag;
                                        historyitem.eventTag.setTo(mHistoryLastWritten.eventTag);
                                    }
                                    mHistoryLastWritten.setTo(mHistoryLastLastWritten);
                                }
                            }
                        }
                    }
                }
            }
        }
        boolean flag = false;
        int i2 = mHistoryBuffer.dataSize();
        if(i2 >= MAX_MAX_HISTORY_BUFFER * 3)
        {
            resetAllStatsLocked();
            flag = true;
        } else
        if(i2 >= MAX_HISTORY_BUFFER)
        {
            if(!mHistoryOverflow)
            {
                mHistoryOverflow = true;
                addHistoryBufferLocked(l4, l1, (byte)0, historyitem);
                addHistoryBufferLocked(l4, l1, (byte)6, historyitem);
                return;
            }
            int i3 = 0;
            int k = historyitem.states & 0xffe30000 & mActiveHistoryStates;
            if(mHistoryLastWritten.states != k)
            {
                i3 = mActiveHistoryStates;
                mActiveHistoryStates = mActiveHistoryStates & (0x1cffff | k);
                int j4;
                if(i3 != mActiveHistoryStates)
                    i3 = 1;
                else
                    i3 = 0;
            }
            j4 = historyitem.states2 & 0x683f0000 & mActiveHistoryStates2;
            k = i3;
            if(mHistoryLastWritten.states2 != j4)
            {
                k = mActiveHistoryStates2;
                mActiveHistoryStates2 = mActiveHistoryStates2 & (0x97c0ffff | j4);
                if(k != mActiveHistoryStates2)
                    k = 1;
                else
                    k = 0;
                k = i3 | k;
            }
            while(k == 0 && mHistoryLastWritten.batteryLevel == historyitem.batteryLevel && (i2 >= MAX_MAX_HISTORY_BUFFER || ((mHistoryLastWritten.states ^ historyitem.states) & 0x1c0000) == 0 || ((mHistoryLastWritten.states2 ^ historyitem.states2) & 0x97c00000) == 0)) 
                return;
            addHistoryBufferLocked(l4, l1, (byte)0, historyitem);
            return;
        }
        if(i2 == 0 || flag)
        {
            historyitem.currentTime = System.currentTimeMillis();
            if(flag)
                addHistoryBufferLocked(l4, l1, (byte)6, historyitem);
            addHistoryBufferLocked(l4, l1, (byte)7, historyitem);
        }
        addHistoryBufferLocked(l4, l1, (byte)0, historyitem);
    }

    public void addHistoryEventLocked(long l, long l1, int i, String s, int j)
    {
        mHistoryCur.eventCode = i;
        mHistoryCur.eventTag = mHistoryCur.localEventTag;
        mHistoryCur.eventTag.string = s;
        mHistoryCur.eventTag.uid = j;
        addHistoryRecordLocked(l, l1);
    }

    void addHistoryRecordInnerLocked(long l, long l1, android.os.BatteryStats.HistoryItem historyitem)
    {
        addHistoryBufferLocked(l, l1, historyitem);
    }

    void addHistoryRecordLocked(long l, long l1)
    {
        if(mTrackRunningHistoryElapsedRealtime != 0L)
        {
            long l2 = l - mTrackRunningHistoryElapsedRealtime;
            long l3 = l1 - mTrackRunningHistoryUptime;
            if(l3 < l2 - 20L)
            {
                mHistoryAddTmp.setTo(mHistoryLastWritten);
                mHistoryAddTmp.wakelockTag = null;
                mHistoryAddTmp.wakeReasonTag = null;
                mHistoryAddTmp.eventCode = 0;
                android.os.BatteryStats.HistoryItem historyitem = mHistoryAddTmp;
                historyitem.states = historyitem.states & 0x7fffffff;
                addHistoryRecordInnerLocked(l - (l2 - l3), l1, mHistoryAddTmp);
            }
        }
        android.os.BatteryStats.HistoryItem historyitem1 = mHistoryCur;
        historyitem1.states = historyitem1.states | 0x80000000;
        mTrackRunningHistoryElapsedRealtime = l;
        mTrackRunningHistoryUptime = l1;
        addHistoryRecordInnerLocked(l, l1, mHistoryCur);
    }

    void addHistoryRecordLocked(long l, long l1, byte byte0, android.os.BatteryStats.HistoryItem historyitem)
    {
        android.os.BatteryStats.HistoryItem historyitem1 = mHistoryCache;
        if(historyitem1 != null)
            mHistoryCache = historyitem1.next;
        else
            historyitem1 = new android.os.BatteryStats.HistoryItem();
        historyitem1.setTo(mHistoryBaseTime + l, byte0, historyitem);
        addHistoryRecordLocked(historyitem1);
    }

    void addHistoryRecordLocked(android.os.BatteryStats.HistoryItem historyitem)
    {
        mNumHistoryItems = mNumHistoryItems + 1;
        historyitem.next = null;
        mHistoryLastEnd = mHistoryEnd;
        if(mHistoryEnd != null)
        {
            mHistoryEnd.next = historyitem;
            mHistoryEnd = historyitem;
        } else
        {
            mHistoryEnd = historyitem;
            mHistory = historyitem;
        }
    }

    public void addIsolatedUidLocked(int i, int j)
    {
        mIsolatedUids.put(i, j);
    }

    void aggregateLastWakeupUptimeLocked(long l)
    {
        if(mLastWakeupReason != null)
        {
            long l1 = mLastWakeupUptimeMs;
            getWakeupReasonTimerLocked(mLastWakeupReason).add(1000L * (l - l1), 1);
            mLastWakeupReason = null;
        }
    }

    void clearHistoryLocked()
    {
        mHistoryBaseTime = 0L;
        mLastHistoryElapsedRealtime = 0L;
        mTrackRunningHistoryElapsedRealtime = 0L;
        mTrackRunningHistoryUptime = 0L;
        mHistoryBuffer.setDataSize(0);
        mHistoryBuffer.setDataPosition(0);
        mHistoryBuffer.setDataCapacity(MAX_HISTORY_BUFFER / 2);
        mHistoryLastLastWritten.clear();
        mHistoryLastWritten.clear();
        mHistoryTagPool.clear();
        mNextHistoryTagIdx = 0;
        mNumHistoryTagChars = 0;
        mHistoryBufferLastPos = -1;
        mHistoryOverflow = false;
        mActiveHistoryStates = -1;
        mActiveHistoryStates2 = -1;
    }

    public void commitCurrentHistoryBatchLocked()
    {
        mHistoryLastWritten.cmd = (byte)-1;
    }

    public void commitPendingDataToDisk()
    {
        this;
        JVM INSTR monitorenter ;
        Object obj;
        obj = mPendingWrite;
        mPendingWrite = null;
        if(obj != null)
            break MISSING_BLOCK_LABEL_19;
        this;
        JVM INSTR monitorexit ;
        return;
        this;
        JVM INSTR monitorexit ;
        mWriteLock.lock();
        FileOutputStream fileoutputstream = JVM INSTR new #1796 <Class FileOutputStream>;
        fileoutputstream.FileOutputStream(mFile.chooseForWrite());
        fileoutputstream.write(((Parcel) (obj)).marshall());
        fileoutputstream.flush();
        FileUtils.sync(fileoutputstream);
        fileoutputstream.close();
        mFile.commit();
        ((Parcel) (obj)).recycle();
        mWriteLock.unlock();
_L2:
        return;
        obj;
        throw obj;
        Object obj1;
        obj1;
        Slog.w("BatteryStats", "Error writing battery statistics", ((Throwable) (obj1)));
        mFile.rollback();
        ((Parcel) (obj)).recycle();
        mWriteLock.unlock();
        if(true) goto _L2; else goto _L1
_L1:
        obj1;
        ((Parcel) (obj)).recycle();
        mWriteLock.unlock();
        throw obj1;
    }

    public long computeBatteryRealtime(long l, int i)
    {
        return mOnBatteryTimeBase.computeRealtime(l, i);
    }

    public long computeBatteryScreenOffRealtime(long l, int i)
    {
        return mOnBatteryScreenOffTimeBase.computeRealtime(l, i);
    }

    public long computeBatteryScreenOffUptime(long l, int i)
    {
        return mOnBatteryScreenOffTimeBase.computeUptime(l, i);
    }

    public long computeBatteryTimeRemaining(long l)
    {
        if(!mOnBattery)
            return -1L;
        if(mDischargeStepTracker.mNumStepDurations < 1)
            return -1L;
        l = mDischargeStepTracker.computeTimePerLevel();
        if(l <= 0L)
            return -1L;
        else
            return (long)mCurrentBatteryLevel * l * 1000L;
    }

    public long computeBatteryUptime(long l, int i)
    {
        return mOnBatteryTimeBase.computeUptime(l, i);
    }

    public long computeChargeTimeRemaining(long l)
    {
        if(mOnBattery)
            return -1L;
        if(mChargeStepTracker.mNumStepDurations < 1)
            return -1L;
        l = mChargeStepTracker.computeTimePerLevel();
        if(l <= 0L)
            return -1L;
        else
            return (long)(100 - mCurrentBatteryLevel) * l * 1000L;
    }

    public long computeRealtime(long l, int i)
    {
        switch(i)
        {
        default:
            return 0L;

        case 0: // '\0'
            return mRealtime + (l - mRealtimeStart);

        case 1: // '\001'
            return l - mRealtimeStart;

        case 2: // '\002'
            return l - mOnBatteryTimeBase.getRealtimeStart();
        }
    }

    public long computeUptime(long l, int i)
    {
        switch(i)
        {
        default:
            return 0L;

        case 0: // '\0'
            return mUptime + (l - mUptimeStart);

        case 1: // '\001'
            return l - mUptimeStart;

        case 2: // '\002'
            return l - mOnBatteryTimeBase.getUptimeStart();
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public void dumpLocked(Context context, PrintWriter printwriter, int i, int j, long l)
    {
        super.dumpLocked(context, printwriter, i, j, l);
    }

    boolean ensureStartClockTime(long l)
    {
        if(l > 0x757b12c00L && mStartClockTime < l - 0x757b12c00L)
        {
            mStartClockTime = l - (mClocks.elapsedRealtime() - mRealtimeStart / 1000L);
            return true;
        } else
        {
            return false;
        }
    }

    public void finishAddingCpuLocked(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1)
    {
        mCurStepCpuUserTime = mCurStepCpuUserTime + (long)i;
        mCurStepCpuSystemTime = mCurStepCpuSystemTime + (long)j;
        mCurStepStatUserTime = mCurStepStatUserTime + (long)k;
        mCurStepStatSystemTime = mCurStepStatSystemTime + (long)l;
        mCurStepStatIOWaitTime = mCurStepStatIOWaitTime + (long)i1;
        mCurStepStatIrqTime = mCurStepStatIrqTime + (long)j1;
        mCurStepStatSoftIrqTime = mCurStepStatSoftIrqTime + (long)k1;
        mCurStepStatIdleTime = mCurStepStatIdleTime + (long)l1;
    }

    public void finishIteratingHistoryLocked()
    {
        mIteratingHistory = false;
        mHistoryBuffer.setDataPosition(mHistoryBuffer.dataSize());
        mReadHistoryStrings = null;
    }

    public void finishIteratingOldHistoryLocked()
    {
        mIteratingHistory = false;
        mHistoryBuffer.setDataPosition(mHistoryBuffer.dataSize());
        mHistoryIterator = null;
    }

    public long getAwakeTimeBattery()
    {
        return computeBatteryUptime(getBatteryUptimeLocked(), 1);
    }

    public long getAwakeTimePlugged()
    {
        return mClocks.uptimeMillis() * 1000L - getAwakeTimeBattery();
    }

    public long getBatteryRealtime(long l)
    {
        return mOnBatteryTimeBase.getRealtime(l);
    }

    public long getBatteryUptime(long l)
    {
        return mOnBatteryTimeBase.getUptime(l);
    }

    protected long getBatteryUptimeLocked()
    {
        return mOnBatteryTimeBase.getUptime(mClocks.uptimeMillis() * 1000L);
    }

    public android.os.BatteryStats.ControllerActivityCounter getBluetoothControllerActivity()
    {
        return mBluetoothActivity;
    }

    public long getBluetoothScanTime(long l, int i)
    {
        return mBluetoothScanTimer.getTotalTimeLocked(l, i);
    }

    public long getCameraOnTime(long l, int i)
    {
        return mCameraOnTimer.getTotalTimeLocked(l, i);
    }

    public android.os.BatteryStats.LevelStepTracker getChargeLevelStepTracker()
    {
        return mChargeStepTracker;
    }

    public long[] getCpuFreqs()
    {
        return mCpuFreqs;
    }

    public long getCurrentDailyStartTime()
    {
        return mDailyStartTime;
    }

    public android.os.BatteryStats.LevelStepTracker getDailyChargeLevelStepTracker()
    {
        return mDailyChargeStepTracker;
    }

    public android.os.BatteryStats.LevelStepTracker getDailyDischargeLevelStepTracker()
    {
        return mDailyDischargeStepTracker;
    }

    public android.os.BatteryStats.DailyItem getDailyItemLocked(int i)
    {
        i = mDailyItems.size() - 1 - i;
        android.os.BatteryStats.DailyItem dailyitem;
        if(i >= 0)
            dailyitem = (android.os.BatteryStats.DailyItem)mDailyItems.get(i);
        else
            dailyitem = null;
        return dailyitem;
    }

    public ArrayList getDailyPackageChanges()
    {
        return mDailyPackageChanges;
    }

    public int getDeviceIdleModeCount(int i, int j)
    {
        switch(i)
        {
        default:
            return 0;

        case 1: // '\001'
            return mDeviceIdleModeLightTimer.getCountLocked(j);

        case 2: // '\002'
            return mDeviceIdleModeFullTimer.getCountLocked(j);
        }
    }

    public long getDeviceIdleModeTime(int i, long l, int j)
    {
        switch(i)
        {
        default:
            return 0L;

        case 1: // '\001'
            return mDeviceIdleModeLightTimer.getTotalTimeLocked(l, j);

        case 2: // '\002'
            return mDeviceIdleModeFullTimer.getTotalTimeLocked(l, j);
        }
    }

    public int getDeviceIdlingCount(int i, int j)
    {
        switch(i)
        {
        default:
            return 0;

        case 1: // '\001'
            return mDeviceLightIdlingTimer.getCountLocked(j);

        case 2: // '\002'
            return mDeviceIdlingTimer.getCountLocked(j);
        }
    }

    public long getDeviceIdlingTime(int i, long l, int j)
    {
        switch(i)
        {
        default:
            return 0L;

        case 1: // '\001'
            return mDeviceLightIdlingTimer.getTotalTimeLocked(l, j);

        case 2: // '\002'
            return mDeviceIdlingTimer.getTotalTimeLocked(l, j);
        }
    }

    public int getDischargeAmount(int i)
    {
        int j;
        if(i == 0)
            i = getHighDischargeAmountSinceCharge();
        else
            i = getDischargeStartLevel() - getDischargeCurrentLevel();
        j = i;
        if(i < 0)
            j = 0;
        return j;
    }

    public int getDischargeAmountScreenDoze()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mDischargeAmountScreenDoze;
        int j = i;
        if(!mOnBattery)
            break MISSING_BLOCK_LABEL_58;
        j = i;
        if(!isScreenDoze(mScreenState))
            break MISSING_BLOCK_LABEL_58;
        j = i;
        int k;
        if(mDischargeCurrentLevel >= mDischargeScreenDozeUnplugLevel)
            break MISSING_BLOCK_LABEL_58;
        k = mDischargeScreenDozeUnplugLevel;
        j = mDischargeCurrentLevel;
        j = i + (k - j);
        this;
        JVM INSTR monitorexit ;
        return j;
        Exception exception;
        exception;
        throw exception;
    }

    public int getDischargeAmountScreenDozeSinceCharge()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mDischargeAmountScreenDozeSinceCharge;
        int j = i;
        if(!mOnBattery)
            break MISSING_BLOCK_LABEL_58;
        j = i;
        if(!isScreenDoze(mScreenState))
            break MISSING_BLOCK_LABEL_58;
        j = i;
        int k;
        if(mDischargeCurrentLevel >= mDischargeScreenDozeUnplugLevel)
            break MISSING_BLOCK_LABEL_58;
        k = mDischargeScreenDozeUnplugLevel;
        j = mDischargeCurrentLevel;
        j = i + (k - j);
        this;
        JVM INSTR monitorexit ;
        return j;
        Exception exception;
        exception;
        throw exception;
    }

    public int getDischargeAmountScreenOff()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mDischargeAmountScreenOff;
        int j = i;
        if(!mOnBattery)
            break MISSING_BLOCK_LABEL_54;
        j = i;
        if(!isScreenOff(mScreenState))
            break MISSING_BLOCK_LABEL_54;
        j = i;
        if(mDischargeCurrentLevel < mDischargeScreenOffUnplugLevel)
            j = i + (mDischargeScreenOffUnplugLevel - mDischargeCurrentLevel);
        i = getDischargeAmountScreenDoze();
        this;
        JVM INSTR monitorexit ;
        return i + j;
        Exception exception;
        exception;
        throw exception;
    }

    public int getDischargeAmountScreenOffSinceCharge()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mDischargeAmountScreenOffSinceCharge;
        int j = i;
        if(!mOnBattery)
            break MISSING_BLOCK_LABEL_54;
        j = i;
        if(!isScreenOff(mScreenState))
            break MISSING_BLOCK_LABEL_54;
        j = i;
        if(mDischargeCurrentLevel < mDischargeScreenOffUnplugLevel)
            j = i + (mDischargeScreenOffUnplugLevel - mDischargeCurrentLevel);
        i = getDischargeAmountScreenDozeSinceCharge();
        this;
        JVM INSTR monitorexit ;
        return i + j;
        Exception exception;
        exception;
        throw exception;
    }

    public int getDischargeAmountScreenOn()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mDischargeAmountScreenOn;
        int j = i;
        if(!mOnBattery)
            break MISSING_BLOCK_LABEL_58;
        j = i;
        if(!isScreenOn(mScreenState))
            break MISSING_BLOCK_LABEL_58;
        j = i;
        int k;
        if(mDischargeCurrentLevel >= mDischargeScreenOnUnplugLevel)
            break MISSING_BLOCK_LABEL_58;
        k = mDischargeScreenOnUnplugLevel;
        j = mDischargeCurrentLevel;
        j = i + (k - j);
        this;
        JVM INSTR monitorexit ;
        return j;
        Exception exception;
        exception;
        throw exception;
    }

    public int getDischargeAmountScreenOnSinceCharge()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mDischargeAmountScreenOnSinceCharge;
        int j = i;
        if(!mOnBattery)
            break MISSING_BLOCK_LABEL_58;
        j = i;
        if(!isScreenOn(mScreenState))
            break MISSING_BLOCK_LABEL_58;
        j = i;
        int k;
        if(mDischargeCurrentLevel >= mDischargeScreenOnUnplugLevel)
            break MISSING_BLOCK_LABEL_58;
        j = mDischargeScreenOnUnplugLevel;
        k = mDischargeCurrentLevel;
        j = i + (j - k);
        this;
        JVM INSTR monitorexit ;
        return j;
        Exception exception;
        exception;
        throw exception;
    }

    public int getDischargeCurrentLevel()
    {
        this;
        JVM INSTR monitorenter ;
        int i = getDischargeCurrentLevelLocked();
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int getDischargeCurrentLevelLocked()
    {
        return mDischargeCurrentLevel;
    }

    public android.os.BatteryStats.LevelStepTracker getDischargeLevelStepTracker()
    {
        return mDischargeStepTracker;
    }

    public int getDischargeStartLevel()
    {
        this;
        JVM INSTR monitorenter ;
        int i = getDischargeStartLevelLocked();
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int getDischargeStartLevelLocked()
    {
        return mDischargeUnplugLevel;
    }

    public String getEndPlatformVersion()
    {
        return mEndPlatformVersion;
    }

    public int getEstimatedBatteryCapacity()
    {
        return mEstimatedBatteryCapacity;
    }

    public long getFlashlightOnCount(int i)
    {
        return (long)mFlashlightOnTimer.getCountLocked(i);
    }

    public long getFlashlightOnTime(long l, int i)
    {
        return mFlashlightOnTimer.getTotalTimeLocked(l, i);
    }

    public long getGlobalWifiRunningTime(long l, int i)
    {
        return mGlobalWifiRunningTimer.getTotalTimeLocked(l, i);
    }

    public int getHighDischargeAmountSinceCharge()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mHighDischargeAmountSinceCharge;
        int j = i;
        if(!mOnBattery)
            break MISSING_BLOCK_LABEL_45;
        j = i;
        int k;
        if(mDischargeCurrentLevel >= mDischargeUnplugLevel)
            break MISSING_BLOCK_LABEL_45;
        k = mDischargeUnplugLevel;
        j = mDischargeCurrentLevel;
        j = i + (k - j);
        this;
        JVM INSTR monitorexit ;
        return j;
        Exception exception;
        exception;
        throw exception;
    }

    public long getHistoryBaseTime()
    {
        return mHistoryBaseTime;
    }

    public int getHistoryStringPoolBytes()
    {
        return mReadHistoryStrings.length * 12 + mReadHistoryChars * 2;
    }

    public int getHistoryStringPoolSize()
    {
        return mReadHistoryStrings.length;
    }

    public String getHistoryTagPoolString(int i)
    {
        return mReadHistoryStrings[i];
    }

    public int getHistoryTagPoolUid(int i)
    {
        return mReadHistoryUids[i];
    }

    public int getHistoryTotalSize()
    {
        return MAX_HISTORY_BUFFER;
    }

    public int getHistoryUsedSize()
    {
        return mHistoryBuffer.dataSize();
    }

    public long getInteractiveTime(long l, int i)
    {
        return mInteractiveTimer.getTotalTimeLocked(l, i);
    }

    public boolean getIsOnBattery()
    {
        return mOnBattery;
    }

    public LongSparseArray getKernelMemoryStats()
    {
        return mKernelMemoryStats;
    }

    public SamplingTimer getKernelMemoryTimerLocked(long l)
    {
        SamplingTimer samplingtimer = (SamplingTimer)mKernelMemoryStats.get(l);
        SamplingTimer samplingtimer1 = samplingtimer;
        if(samplingtimer == null)
        {
            samplingtimer1 = new SamplingTimer(mClocks, mOnBatteryTimeBase);
            mKernelMemoryStats.put(l, samplingtimer1);
        }
        return samplingtimer1;
    }

    public Map getKernelWakelockStats()
    {
        return mKernelWakelockStats;
    }

    public SamplingTimer getKernelWakelockTimerLocked(String s)
    {
        SamplingTimer samplingtimer = (SamplingTimer)mKernelWakelockStats.get(s);
        SamplingTimer samplingtimer1 = samplingtimer;
        if(samplingtimer == null)
        {
            samplingtimer1 = new SamplingTimer(mClocks, mOnBatteryScreenOffTimeBase);
            mKernelWakelockStats.put(s, samplingtimer1);
        }
        return samplingtimer1;
    }

    public long getLongestDeviceIdleModeTime(int i)
    {
        switch(i)
        {
        default:
            return 0L;

        case 1: // '\001'
            return mLongestLightIdleTime;

        case 2: // '\002'
            return mLongestFullIdleTime;
        }
    }

    public int getLowDischargeAmountSinceCharge()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mLowDischargeAmountSinceCharge;
        int j = i;
        if(!mOnBattery)
            break MISSING_BLOCK_LABEL_47;
        j = i;
        int k;
        if(mDischargeCurrentLevel >= mDischargeUnplugLevel)
            break MISSING_BLOCK_LABEL_47;
        j = mDischargeUnplugLevel;
        k = mDischargeCurrentLevel;
        j = i + (j - k - 1);
        this;
        JVM INSTR monitorexit ;
        return j;
        Exception exception;
        exception;
        throw exception;
    }

    public long getMahDischarge(int i)
    {
        return mDischargeCounter.getCountLocked(i);
    }

    public long getMahDischargeScreenDoze(int i)
    {
        return mDischargeScreenDozeCounter.getCountLocked(i);
    }

    public long getMahDischargeScreenOff(int i)
    {
        return mDischargeScreenOffCounter.getCountLocked(i);
    }

    public int getMaxLearnedBatteryCapacity()
    {
        return mMaxLearnedBatteryCapacity;
    }

    public int getMinLearnedBatteryCapacity()
    {
        return mMinLearnedBatteryCapacity;
    }

    public long getMobileRadioActiveAdjustedTime(int i)
    {
        return mMobileRadioActiveAdjustedTime.getCountLocked(i);
    }

    public int getMobileRadioActiveCount(int i)
    {
        return mMobileRadioActiveTimer.getCountLocked(i);
    }

    public long getMobileRadioActiveTime(long l, int i)
    {
        return mMobileRadioActiveTimer.getTotalTimeLocked(l, i);
    }

    public int getMobileRadioActiveUnknownCount(int i)
    {
        return (int)mMobileRadioActiveUnknownCount.getCountLocked(i);
    }

    public long getMobileRadioActiveUnknownTime(int i)
    {
        return mMobileRadioActiveUnknownTime.getCountLocked(i);
    }

    public android.os.BatteryStats.ControllerActivityCounter getModemControllerActivity()
    {
        return mModemActivity;
    }

    public long getNetworkActivityBytes(int i, int j)
    {
        if(i >= 0 && i < mNetworkByteActivityCounters.length)
            return mNetworkByteActivityCounters[i].getCountLocked(j);
        else
            return 0L;
    }

    public long getNetworkActivityPackets(int i, int j)
    {
        if(i >= 0 && i < mNetworkPacketActivityCounters.length)
            return mNetworkPacketActivityCounters[i].getCountLocked(j);
        else
            return 0L;
    }

    public boolean getNextHistoryLocked(android.os.BatteryStats.HistoryItem historyitem)
    {
        int i = mHistoryBuffer.dataPosition();
        if(i == 0)
            historyitem.clear();
        boolean flag;
        if(i >= mHistoryBuffer.dataSize())
            flag = true;
        else
            flag = false;
        if(flag)
            return false;
        long l = historyitem.time;
        long l1 = historyitem.currentTime;
        readHistoryDelta(mHistoryBuffer, historyitem);
        if(historyitem.cmd != 5 && historyitem.cmd != 7 && l1 != 0L)
            historyitem.currentTime = (historyitem.time - l) + l1;
        return true;
    }

    public long getNextMaxDailyDeadline()
    {
        return mNextMaxDailyDeadline;
    }

    public long getNextMinDailyDeadline()
    {
        return mNextMinDailyDeadline;
    }

    public boolean getNextOldHistoryLocked(android.os.BatteryStats.HistoryItem historyitem)
    {
        boolean flag;
        android.os.BatteryStats.HistoryItem historyitem1;
        if(mHistoryBuffer.dataPosition() >= mHistoryBuffer.dataSize())
            flag = true;
        else
            flag = false;
        if(!flag)
        {
            readHistoryDelta(mHistoryBuffer, mHistoryReadTmp);
            boolean flag1 = mReadOverflow;
            boolean flag2;
            if(mHistoryReadTmp.cmd == 6)
                flag2 = true;
            else
                flag2 = false;
            mReadOverflow = flag2 | flag1;
        }
        historyitem1 = mHistoryIterator;
        if(historyitem1 == null)
        {
            if(!mReadOverflow && flag ^ true)
                Slog.w("BatteryStatsImpl", "Old history ends before new history!");
            return false;
        }
        historyitem.setTo(historyitem1);
        mHistoryIterator = historyitem1.next;
        if(mReadOverflow) goto _L2; else goto _L1
_L1:
        if(!flag) goto _L4; else goto _L3
_L3:
        Slog.w("BatteryStatsImpl", "New history ends before old history!");
_L2:
        return true;
_L4:
        if(!historyitem.same(mHistoryReadTmp))
        {
            FastPrintWriter fastprintwriter = new FastPrintWriter(new LogWriter(5, "BatteryStatsImpl"));
            fastprintwriter.println("Histories differ!");
            fastprintwriter.println("Old history:");
            (new android.os.BatteryStats.HistoryPrinter()).printNextItem(fastprintwriter, historyitem, 0L, false, true);
            fastprintwriter.println("New history:");
            (new android.os.BatteryStats.HistoryPrinter()).printNextItem(fastprintwriter, mHistoryReadTmp, 0L, false, true);
            fastprintwriter.flush();
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public int getNumConnectivityChange(int i)
    {
        int j = mNumConnectivityChange;
        if(i != 1) goto _L2; else goto _L1
_L1:
        int k = j - mLoadedNumConnectivityChange;
_L4:
        return k;
_L2:
        k = j;
        if(i == 2)
            k = j - mUnpluggedNumConnectivityChange;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public Uid.Pkg getPackageStatsLocked(int i, String s)
    {
        return getUidStatsLocked(mapUid(i)).getPackageStatsLocked(s);
    }

    public int getParcelVersion()
    {
        return 167;
    }

    public int getPhoneDataConnectionCount(int i, int j)
    {
        return mPhoneDataConnectionsTimer[i].getCountLocked(j);
    }

    public long getPhoneDataConnectionTime(int i, long l, int j)
    {
        return mPhoneDataConnectionsTimer[i].getTotalTimeLocked(l, j);
    }

    public int getPhoneOnCount(int i)
    {
        return mPhoneOnTimer.getCountLocked(i);
    }

    public long getPhoneOnTime(long l, int i)
    {
        return mPhoneOnTimer.getTotalTimeLocked(l, i);
    }

    public long getPhoneSignalScanningTime(long l, int i)
    {
        return mPhoneSignalScanningTimer.getTotalTimeLocked(l, i);
    }

    public int getPhoneSignalStrengthCount(int i, int j)
    {
        return mPhoneSignalStrengthsTimer[i].getCountLocked(j);
    }

    public long getPhoneSignalStrengthTime(int i, long l, int j)
    {
        return mPhoneSignalStrengthsTimer[i].getTotalTimeLocked(l, j);
    }

    public int getPowerSaveModeEnabledCount(int i)
    {
        return mPowerSaveModeEnabledTimer.getCountLocked(i);
    }

    public long getPowerSaveModeEnabledTime(long l, int i)
    {
        return mPowerSaveModeEnabledTimer.getTotalTimeLocked(l, i);
    }

    public Uid.Proc getProcessStatsLocked(int i, String s)
    {
        return getUidStatsLocked(mapUid(i)).getProcessStatsLocked(s);
    }

    public long getProcessWakeTime(int i, int j, long l)
    {
        long l1 = 0L;
        i = mapUid(i);
        Object obj = (Uid)mUidStats.get(i);
        if(obj != null)
        {
            obj = (android.os.BatteryStats.Uid.Pid)((Uid) (obj)).mPids.get(j);
            if(obj != null)
            {
                long l2 = ((android.os.BatteryStats.Uid.Pid) (obj)).mWakeSumMs;
                if(((android.os.BatteryStats.Uid.Pid) (obj)).mWakeNesting > 0)
                    l1 = l - ((android.os.BatteryStats.Uid.Pid) (obj)).mWakeStartMs;
                return l1 + l2;
            }
        }
        return 0L;
    }

    public Map getRpmStats()
    {
        return mRpmStats;
    }

    public SamplingTimer getRpmTimerLocked(String s)
    {
        SamplingTimer samplingtimer = (SamplingTimer)mRpmStats.get(s);
        SamplingTimer samplingtimer1 = samplingtimer;
        if(samplingtimer == null)
        {
            samplingtimer1 = new SamplingTimer(mClocks, mOnBatteryTimeBase);
            mRpmStats.put(s, samplingtimer1);
        }
        return samplingtimer1;
    }

    public long getScreenBrightnessTime(int i, long l, int j)
    {
        return mScreenBrightnessTimer[i].getTotalTimeLocked(l, j);
    }

    public int getScreenDozeCount(int i)
    {
        return mScreenDozeTimer.getCountLocked(i);
    }

    public long getScreenDozeTime(long l, int i)
    {
        return mScreenDozeTimer.getTotalTimeLocked(l, i);
    }

    public Map getScreenOffRpmStats()
    {
        return mScreenOffRpmStats;
    }

    public SamplingTimer getScreenOffRpmTimerLocked(String s)
    {
        SamplingTimer samplingtimer = (SamplingTimer)mScreenOffRpmStats.get(s);
        SamplingTimer samplingtimer1 = samplingtimer;
        if(samplingtimer == null)
        {
            samplingtimer1 = new SamplingTimer(mClocks, mOnBatteryScreenOffTimeBase);
            mScreenOffRpmStats.put(s, samplingtimer1);
        }
        return samplingtimer1;
    }

    public int getScreenOnCount(int i)
    {
        return mScreenOnTimer.getCountLocked(i);
    }

    public long getScreenOnTime(long l, int i)
    {
        return mScreenOnTimer.getTotalTimeLocked(l, i);
    }

    public Uid.Pkg.Serv getServiceStatsLocked(int i, String s, String s1)
    {
        return getUidStatsLocked(mapUid(i)).getServiceStatsLocked(s, s1);
    }

    public long getStartClockTime()
    {
        long l = System.currentTimeMillis();
        if(ensureStartClockTime(l))
            recordCurrentTimeChangeLocked(l, mClocks.elapsedRealtime(), mClocks.uptimeMillis());
        return mStartClockTime;
    }

    public int getStartCount()
    {
        return mStartCount;
    }

    public String getStartPlatformVersion()
    {
        return mStartPlatformVersion;
    }

    public SparseArray getUidStats()
    {
        return mUidStats;
    }

    public Uid getUidStatsLocked(int i)
    {
        Uid uid = (Uid)mUidStats.get(i);
        Uid uid1 = uid;
        if(uid == null)
        {
            uid1 = new Uid(this, i);
            mUidStats.put(i, uid1);
        }
        return uid1;
    }

    public Map getWakeupReasonStats()
    {
        return mWakeupReasonStats;
    }

    public SamplingTimer getWakeupReasonTimerLocked(String s)
    {
        SamplingTimer samplingtimer = (SamplingTimer)mWakeupReasonStats.get(s);
        SamplingTimer samplingtimer1 = samplingtimer;
        if(samplingtimer == null)
        {
            samplingtimer1 = new SamplingTimer(mClocks, mOnBatteryTimeBase);
            mWakeupReasonStats.put(s, samplingtimer1);
        }
        return samplingtimer1;
    }

    public android.os.BatteryStats.ControllerActivityCounter getWifiControllerActivity()
    {
        return mWifiActivity;
    }

    public long getWifiOnTime(long l, int i)
    {
        return mWifiOnTimer.getTotalTimeLocked(l, i);
    }

    public int getWifiSignalStrengthCount(int i, int j)
    {
        return mWifiSignalStrengthsTimer[i].getCountLocked(j);
    }

    public long getWifiSignalStrengthTime(int i, long l, int j)
    {
        return mWifiSignalStrengthsTimer[i].getTotalTimeLocked(l, j);
    }

    public int getWifiStateCount(int i, int j)
    {
        return mWifiStateTimer[i].getCountLocked(j);
    }

    public long getWifiStateTime(int i, long l, int j)
    {
        return mWifiStateTimer[i].getTotalTimeLocked(l, j);
    }

    public int getWifiSupplStateCount(int i, int j)
    {
        return mWifiSupplStateTimer[i].getCountLocked(j);
    }

    public long getWifiSupplStateTime(int i, long l, int j)
    {
        return mWifiSupplStateTimer[i].getTotalTimeLocked(l, j);
    }

    public boolean hasBluetoothActivityReporting()
    {
        return mHasBluetoothReporting;
    }

    public boolean hasModemActivityReporting()
    {
        return mHasModemReporting;
    }

    public boolean hasWifiActivityReporting()
    {
        return mHasWifiReporting;
    }

    void initDischarge()
    {
        mLowDischargeAmountSinceCharge = 0;
        mHighDischargeAmountSinceCharge = 0;
        mDischargeAmountScreenOn = 0;
        mDischargeAmountScreenOnSinceCharge = 0;
        mDischargeAmountScreenOff = 0;
        mDischargeAmountScreenOffSinceCharge = 0;
        mDischargeAmountScreenDoze = 0;
        mDischargeAmountScreenDozeSinceCharge = 0;
        mDischargeStepTracker.init();
        mChargeStepTracker.init();
        mDischargeScreenOffCounter.reset(false);
        mDischargeScreenDozeCounter.reset(false);
        mDischargeCounter.reset(false);
    }

    void initTimes(long l, long l1)
    {
        mStartClockTime = System.currentTimeMillis();
        mOnBatteryTimeBase.init(l, l1);
        mOnBatteryScreenOffTimeBase.init(l, l1);
        mRealtime = 0L;
        mUptime = 0L;
        mRealtimeStart = l1;
        mUptimeStart = l;
    }

    public boolean isCharging()
    {
        return mCharging;
    }

    public boolean isOnBattery()
    {
        return mOnBattery;
    }

    public boolean isScreenDoze(int i)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(i != 3)
            if(i == 4)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public boolean isScreenOff(int i)
    {
        boolean flag = true;
        if(i != 1)
            flag = false;
        return flag;
    }

    public boolean isScreenOn(int i)
    {
        boolean flag;
        if(i == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int mapUid(int i)
    {
        int j = mIsolatedUids.get(i, -1);
        if(j > 0)
            i = j;
        return i;
    }

    public void noteActivityPausedLocked(int i)
    {
        getUidStatsLocked(mapUid(i)).noteActivityPausedLocked(mClocks.elapsedRealtime());
    }

    public void noteActivityResumedLocked(int i)
    {
        getUidStatsLocked(mapUid(i)).noteActivityResumedLocked(mClocks.elapsedRealtime());
    }

    public void noteAlarmFinishLocked(String s, int i)
    {
        if(!mRecordAllHistory)
            return;
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        if(!mActiveEvents.updateState(16397, s, i, 0))
        {
            return;
        } else
        {
            addHistoryEventLocked(l, l1, 16397, s, i);
            return;
        }
    }

    public void noteAlarmStartLocked(String s, int i)
    {
        if(!mRecordAllHistory)
            return;
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        if(!mActiveEvents.updateState(32781, s, i, 0))
        {
            return;
        } else
        {
            addHistoryEventLocked(l, l1, 32781, s, i);
            return;
        }
    }

    public void noteAudioOffLocked(int i)
    {
        if(mAudioOnNesting == 0)
            return;
        int j = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        i = mAudioOnNesting - 1;
        mAudioOnNesting = i;
        if(i == 0)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states = historyitem.states & 0xffbfffff;
            addHistoryRecordLocked(l, l1);
            mAudioOnTimer.stopRunningLocked(l);
        }
        getUidStatsLocked(j).noteAudioTurnedOffLocked(l);
    }

    public void noteAudioOnLocked(int i)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        if(mAudioOnNesting == 0)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states = historyitem.states | 0x400000;
            addHistoryRecordLocked(l, l1);
            mAudioOnTimer.startRunningLocked(l);
        }
        mAudioOnNesting = mAudioOnNesting + 1;
        getUidStatsLocked(i).noteAudioTurnedOnLocked(l);
    }

    public void noteBluetoothScanResultsFromSourceLocked(WorkSource worksource, int i)
    {
        int j = worksource.size();
        for(int k = 0; k < j; k++)
            getUidStatsLocked(mapUid(worksource.get(k))).noteBluetoothScanResultsLocked(i);

    }

    public void noteBluetoothScanStartedFromSourceLocked(WorkSource worksource, boolean flag)
    {
        int i = worksource.size();
        for(int j = 0; j < i; j++)
            noteBluetoothScanStartedLocked(worksource.get(j), flag);

    }

    public void noteBluetoothScanStoppedFromSourceLocked(WorkSource worksource, boolean flag)
    {
        int i = worksource.size();
        for(int j = 0; j < i; j++)
            noteBluetoothScanStoppedLocked(worksource.get(j), flag);

    }

    public void noteCameraOffLocked(int i)
    {
        if(mCameraOnNesting == 0)
            return;
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        int j = mCameraOnNesting - 1;
        mCameraOnNesting = j;
        if(j == 0)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states2 = historyitem.states2 & 0xffdfffff;
            addHistoryRecordLocked(l, l1);
            mCameraOnTimer.stopRunningLocked(l);
        }
        getUidStatsLocked(i).noteCameraTurnedOffLocked(l);
    }

    public void noteCameraOnLocked(int i)
    {
        int j = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        i = mCameraOnNesting;
        mCameraOnNesting = i + 1;
        if(i == 0)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states2 = historyitem.states2 | 0x200000;
            addHistoryRecordLocked(l, l1);
            mCameraOnTimer.startRunningLocked(l);
        }
        getUidStatsLocked(j).noteCameraTurnedOnLocked(l);
    }

    public void noteChangeWakelockFromSourceLocked(WorkSource worksource, int i, String s, String s1, int j, WorkSource worksource1, int k, 
            String s2, String s3, int l, boolean flag)
    {
        long l1 = mClocks.elapsedRealtime();
        long l2 = mClocks.uptimeMillis();
        int i1 = worksource1.size();
        for(int j1 = 0; j1 < i1; j1++)
            noteStartWakeLocked(worksource1.get(j1), k, s2, s3, l, flag, l1, l2);

        l = worksource.size();
        for(k = 0; k < l; k++)
            noteStopWakeLocked(worksource.get(k), i, s, s1, j, l1, l2);

    }

    public void noteConnectivityChangedLocked(int i, String s)
    {
        addHistoryEventLocked(mClocks.elapsedRealtime(), mClocks.uptimeMillis(), 9, s, i);
        mNumConnectivityChange = mNumConnectivityChange + 1;
    }

    public void noteCurrentTimeChangedLocked()
    {
        long l = System.currentTimeMillis();
        recordCurrentTimeChangeLocked(l, mClocks.elapsedRealtime(), mClocks.uptimeMillis());
        ensureStartClockTime(l);
    }

    public void noteDeviceIdleModeLocked(int i, String s, int j)
    {
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        boolean flag;
        boolean flag1;
        boolean flag2;
        if(i == 2)
            flag = true;
        else
            flag = false;
        flag1 = flag;
        if(mDeviceIdling)
        {
            flag1 = flag;
            if(flag ^ true)
            {
                flag1 = flag;
                if(s == null)
                    flag1 = true;
            }
        }
        if(i == 1)
            flag = true;
        else
            flag = false;
        flag2 = flag;
        if(mDeviceLightIdling)
        {
            flag2 = flag;
            if(flag ^ true)
            {
                flag2 = flag;
                if(flag1 ^ true)
                {
                    flag2 = flag;
                    if(s == null)
                        flag2 = true;
                }
            }
        }
        if(s != null && (mDeviceIdling || mDeviceLightIdling))
            addHistoryEventLocked(l, l1, 10, s, j);
        if(mDeviceIdling != flag1)
        {
            mDeviceIdling = flag1;
            if(flag1)
                j = 8;
            else
                j = 0;
            mModStepMode = mModStepMode | mCurStepMode & 8 ^ j;
            mCurStepMode = mCurStepMode & -9 | j;
            if(flag1)
                mDeviceIdlingTimer.startRunningLocked(l);
            else
                mDeviceIdlingTimer.stopRunningLocked(l);
        }
        if(mDeviceLightIdling != flag2)
        {
            mDeviceLightIdling = flag2;
            if(flag2)
                mDeviceLightIdlingTimer.startRunningLocked(l);
            else
                mDeviceLightIdlingTimer.stopRunningLocked(l);
        }
        if(mDeviceIdleMode != i)
        {
            mHistoryCur.states2 = mHistoryCur.states2 & 0xf9ffffff | i << 25;
            addHistoryRecordLocked(l, l1);
            l1 = l - mLastIdleTimeStart;
            mLastIdleTimeStart = l;
            if(mDeviceIdleMode == 1)
            {
                if(l1 > mLongestLightIdleTime)
                    mLongestLightIdleTime = l1;
                mDeviceIdleModeLightTimer.stopRunningLocked(l);
            } else
            if(mDeviceIdleMode == 2)
            {
                if(l1 > mLongestFullIdleTime)
                    mLongestFullIdleTime = l1;
                mDeviceIdleModeFullTimer.stopRunningLocked(l);
            }
            if(i == 1)
                mDeviceIdleModeLightTimer.startRunningLocked(l);
            else
            if(i == 2)
                mDeviceIdleModeFullTimer.startRunningLocked(l);
            mDeviceIdleMode = i;
        }
    }

    public void noteEventLocked(int i, String s, int j)
    {
        j = mapUid(j);
        if(!mActiveEvents.updateState(i, s, j, 0))
        {
            return;
        } else
        {
            addHistoryEventLocked(mClocks.elapsedRealtime(), mClocks.uptimeMillis(), i, s, j);
            return;
        }
    }

    public void noteFlashlightOffLocked(int i)
    {
        if(mFlashlightOnNesting == 0)
            return;
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        int j = mFlashlightOnNesting - 1;
        mFlashlightOnNesting = j;
        if(j == 0)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states2 = historyitem.states2 & 0xf7ffffff;
            addHistoryRecordLocked(l, l1);
            mFlashlightOnTimer.stopRunningLocked(l);
        }
        getUidStatsLocked(i).noteFlashlightTurnedOffLocked(l);
    }

    public void noteFlashlightOnLocked(int i)
    {
        int j = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        i = mFlashlightOnNesting;
        mFlashlightOnNesting = i + 1;
        if(i == 0)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states2 = historyitem.states2 | 0x8000000;
            addHistoryRecordLocked(l, l1);
            mFlashlightOnTimer.startRunningLocked(l);
        }
        getUidStatsLocked(j).noteFlashlightTurnedOnLocked(l);
    }

    public void noteFullWifiLockAcquiredFromSourceLocked(WorkSource worksource)
    {
        int i = worksource.size();
        for(int j = 0; j < i; j++)
            noteFullWifiLockAcquiredLocked(worksource.get(j));

    }

    public void noteFullWifiLockAcquiredLocked(int i)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        if(mWifiFullLockNesting == 0)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states = historyitem.states | 0x10000000;
            addHistoryRecordLocked(l, l1);
        }
        mWifiFullLockNesting = mWifiFullLockNesting + 1;
        getUidStatsLocked(i).noteFullWifiLockAcquiredLocked(l);
    }

    public void noteFullWifiLockReleasedFromSourceLocked(WorkSource worksource)
    {
        int i = worksource.size();
        for(int j = 0; j < i; j++)
            noteFullWifiLockReleasedLocked(worksource.get(j));

    }

    public void noteFullWifiLockReleasedLocked(int i)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        mWifiFullLockNesting = mWifiFullLockNesting - 1;
        if(mWifiFullLockNesting == 0)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states = historyitem.states & 0xefffffff;
            addHistoryRecordLocked(l, l1);
        }
        getUidStatsLocked(i).noteFullWifiLockReleasedLocked(l);
    }

    public void noteInteractiveLocked(boolean flag)
    {
        if(mInteractive != flag)
        {
            long l = mClocks.elapsedRealtime();
            mInteractive = flag;
            if(flag)
                mInteractiveTimer.startRunningLocked(l);
            else
                mInteractiveTimer.stopRunningLocked(l);
        }
    }

    public void noteJobFinishLocked(String s, int i, int j)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        getUidStatsLocked(i).noteStopJobLocked(s, l, j);
        if(!mActiveEvents.updateState(16390, s, i, 0))
        {
            return;
        } else
        {
            addHistoryEventLocked(l, l1, 16390, s, i);
            return;
        }
    }

    public void noteJobStartLocked(String s, int i)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        getUidStatsLocked(i).noteStartJobLocked(s, l);
        if(!mActiveEvents.updateState(32774, s, i, 0))
        {
            return;
        } else
        {
            addHistoryEventLocked(l, l1, 32774, s, i);
            return;
        }
    }

    public void noteLongPartialWakelockFinish(String s, String s1, int i)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        String s2 = s1;
        if(s1 == null)
            s2 = s;
        if(!mActiveEvents.updateState(16404, s2, i, 0))
        {
            return;
        } else
        {
            addHistoryEventLocked(l, l1, 16404, s2, i);
            return;
        }
    }

    public void noteLongPartialWakelockStart(String s, String s1, int i)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        String s2 = s1;
        if(s1 == null)
            s2 = s;
        if(!mActiveEvents.updateState(32788, s2, i, 0))
        {
            return;
        } else
        {
            addHistoryEventLocked(l, l1, 32788, s2, i);
            return;
        }
    }

    public boolean noteMobileRadioPowerStateLocked(int i, long l, int j)
    {
label0:
        {
            long l1 = mClocks.elapsedRealtime();
            long l2 = mClocks.uptimeMillis();
            if(mMobileRadioPowerState != i)
            {
                boolean flag;
                if(i != 2)
                {
                    if(i == 3)
                        flag = true;
                    else
                        flag = false;
                } else
                {
                    flag = true;
                }
                if(flag)
                {
                    if(j > 0)
                        noteMobileRadioApWakeupLocked(l1, l2, j);
                    l /= 0xf4240L;
                    mMobileRadioActiveStartTime = l;
                    android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
                    historyitem.states = historyitem.states | 0x2000000;
                } else
                {
                    long l3 = l / 0xf4240L;
                    l = mMobileRadioActiveStartTime;
                    android.os.BatteryStats.HistoryItem historyitem1;
                    if(l3 < l)
                    {
                        Slog.wtf("BatteryStatsImpl", (new StringBuilder()).append("Data connection inactive timestamp ").append(l3).append(" is before start time ").append(l).toString());
                        l = l1;
                    } else
                    {
                        l = l3;
                        if(l3 < l1)
                        {
                            mMobileRadioActiveAdjustedTime.addCountLocked(l1 - l3);
                            l = l3;
                        }
                    }
                    historyitem1 = mHistoryCur;
                    historyitem1.states = historyitem1.states & 0xfdffffff;
                }
                addHistoryRecordLocked(l1, l2);
                mMobileRadioPowerState = i;
                if(!flag)
                    break label0;
                mMobileRadioActiveTimer.startRunningLocked(l1);
                mMobileRadioActivePerAppTimer.startRunningLocked(l1);
            }
            return false;
        }
        mMobileRadioActiveTimer.stopRunningLocked(l);
        mMobileRadioActivePerAppTimer.stopRunningLocked(l);
        return true;
    }

    public void noteNetworkInterfaceTypeLocked(String s, int i)
    {
        if(TextUtils.isEmpty(s))
            return;
        Object obj = mModemNetworkLock;
        obj;
        JVM INSTR monitorenter ;
        if(!ConnectivityManager.isNetworkTypeMobile(i)) goto _L2; else goto _L1
_L1:
        mModemIfaces = includeInStringArray(mModemIfaces, s);
_L5:
        obj;
        JVM INSTR monitorexit ;
        obj = mWifiNetworkLock;
        obj;
        JVM INSTR monitorenter ;
        if(!ConnectivityManager.isNetworkTypeWifi(i)) goto _L4; else goto _L3
_L3:
        mWifiIfaces = includeInStringArray(mWifiIfaces, s);
_L6:
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        mModemIfaces = excludeFromStringArray(mModemIfaces, s);
          goto _L5
        s;
        throw s;
_L4:
        mWifiIfaces = excludeFromStringArray(mWifiIfaces, s);
          goto _L6
        s;
        throw s;
          goto _L5
    }

    public void notePackageInstalledLocked(String s, int i)
    {
        addHistoryEventLocked(mClocks.elapsedRealtime(), mClocks.uptimeMillis(), 11, s, i);
        android.os.BatteryStats.PackageChange packagechange = new android.os.BatteryStats.PackageChange();
        packagechange.mPackageName = s;
        packagechange.mUpdate = true;
        packagechange.mVersionCode = i;
        addPackageChange(packagechange);
    }

    public void notePackageUninstalledLocked(String s)
    {
        addHistoryEventLocked(mClocks.elapsedRealtime(), mClocks.uptimeMillis(), 12, s, 0);
        android.os.BatteryStats.PackageChange packagechange = new android.os.BatteryStats.PackageChange();
        packagechange.mPackageName = s;
        packagechange.mUpdate = true;
        addPackageChange(packagechange);
    }

    public void notePhoneDataConnectionStateLocked(int i, boolean flag)
    {
        int j = 0;
        if(!flag) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 1 15: default 80
    //                   1 187
    //                   2 182
    //                   3 192
    //                   4 197
    //                   5 202
    //                   6 207
    //                   7 213
    //                   8 219
    //                   9 225
    //                   10 231
    //                   11 237
    //                   12 243
    //                   13 249
    //                   14 255
    //                   15 261;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18
_L3:
        j = 16;
_L2:
        if(mPhoneDataConnectionType != j)
        {
            long l = mClocks.elapsedRealtime();
            long l1 = mClocks.uptimeMillis();
            mHistoryCur.states = mHistoryCur.states & 0xffffc1ff | j << 9;
            addHistoryRecordLocked(l, l1);
            if(mPhoneDataConnectionType >= 0)
                mPhoneDataConnectionsTimer[mPhoneDataConnectionType].stopRunningLocked(l);
            mPhoneDataConnectionType = j;
            mPhoneDataConnectionsTimer[j].startRunningLocked(l);
        }
        return;
_L5:
        j = 2;
        continue; /* Loop/switch isn't completed */
_L4:
        j = 1;
        continue; /* Loop/switch isn't completed */
_L6:
        j = 3;
        continue; /* Loop/switch isn't completed */
_L7:
        j = 4;
        continue; /* Loop/switch isn't completed */
_L8:
        j = 5;
        continue; /* Loop/switch isn't completed */
_L9:
        j = 6;
        continue; /* Loop/switch isn't completed */
_L10:
        j = 7;
        continue; /* Loop/switch isn't completed */
_L11:
        j = 8;
        continue; /* Loop/switch isn't completed */
_L12:
        j = 9;
        continue; /* Loop/switch isn't completed */
_L13:
        j = 10;
        continue; /* Loop/switch isn't completed */
_L14:
        j = 11;
        continue; /* Loop/switch isn't completed */
_L15:
        j = 12;
        continue; /* Loop/switch isn't completed */
_L16:
        j = 13;
        continue; /* Loop/switch isn't completed */
_L17:
        j = 14;
        continue; /* Loop/switch isn't completed */
_L18:
        j = 15;
        if(true) goto _L2; else goto _L19
_L19:
    }

    public void notePhoneOffLocked()
    {
        if(mPhoneOn)
        {
            long l = mClocks.elapsedRealtime();
            long l1 = mClocks.uptimeMillis();
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states2 = historyitem.states2 & 0xff7fffff;
            addHistoryRecordLocked(l, l1);
            mPhoneOn = false;
            mPhoneOnTimer.stopRunningLocked(l);
        }
    }

    public void notePhoneOnLocked()
    {
        if(!mPhoneOn)
        {
            long l = mClocks.elapsedRealtime();
            long l1 = mClocks.uptimeMillis();
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states2 = historyitem.states2 | 0x800000;
            addHistoryRecordLocked(l, l1);
            mPhoneOn = true;
            mPhoneOnTimer.startRunningLocked(l);
        }
    }

    public void notePhoneSignalStrengthLocked(SignalStrength signalstrength)
    {
        int i = signalstrength.getLevel();
        updateAllPhoneStateLocked(mPhoneServiceStateRaw, mPhoneSimStateRaw, i);
    }

    public void notePhoneStateLocked(int i, int j)
    {
        updateAllPhoneStateLocked(i, j, mPhoneSignalStrengthBinRaw);
    }

    public void notePowerSaveModeLocked(boolean flag)
    {
        if(mPowerSaveModeEnabled != flag)
        {
            byte byte0;
            long l;
            long l1;
            if(flag)
                byte0 = 4;
            else
                byte0 = 0;
            mModStepMode = mModStepMode | mCurStepMode & 4 ^ byte0;
            mCurStepMode = mCurStepMode & -5 | byte0;
            l = mClocks.elapsedRealtime();
            l1 = mClocks.uptimeMillis();
            mPowerSaveModeEnabled = flag;
            if(flag)
            {
                android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
                historyitem.states2 = historyitem.states2 | 0x80000000;
                mPowerSaveModeEnabledTimer.startRunningLocked(l);
            } else
            {
                android.os.BatteryStats.HistoryItem historyitem1 = mHistoryCur;
                historyitem1.states2 = historyitem1.states2 & 0x7fffffff;
                mPowerSaveModeEnabledTimer.stopRunningLocked(l);
            }
            addHistoryRecordLocked(l, l1);
        }
    }

    public void noteProcessAnrLocked(String s, int i)
    {
        i = mapUid(i);
        if(isOnBattery())
            getUidStatsLocked(i).getProcessStatsLocked(s).incNumAnrsLocked();
    }

    public void noteProcessCrashLocked(String s, int i)
    {
        i = mapUid(i);
        if(isOnBattery())
            getUidStatsLocked(i).getProcessStatsLocked(s).incNumCrashesLocked();
    }

    public void noteProcessDiedLocked(int i, int j)
    {
        i = mapUid(i);
        Uid uid = (Uid)mUidStats.get(i);
        if(uid != null)
            uid.mPids.remove(j);
    }

    public void noteProcessFinishLocked(String s, int i)
    {
        i = mapUid(i);
        if(!mActiveEvents.updateState(16385, s, i, 0))
            return;
        if(!mRecordAllHistory)
        {
            return;
        } else
        {
            addHistoryEventLocked(mClocks.elapsedRealtime(), mClocks.uptimeMillis(), 16385, s, i);
            return;
        }
    }

    public void noteProcessStartLocked(String s, int i)
    {
        String s1 = BatteryStatsImplInjector.getProcessName(s);
        i = mapUid(i);
        if(isOnBattery())
            getUidStatsLocked(i).getProcessStatsLocked(s1).incStartsLocked();
        if(!mActiveEvents.updateState(32769, s1, i, 0))
            return;
        if(!mRecordAllHistory)
        {
            return;
        } else
        {
            addHistoryEventLocked(mClocks.elapsedRealtime(), mClocks.uptimeMillis(), 32769, s, i);
            return;
        }
    }

    public void noteResetAudioLocked()
    {
        if(mAudioOnNesting > 0)
        {
            long l = mClocks.elapsedRealtime();
            long l1 = mClocks.uptimeMillis();
            mAudioOnNesting = 0;
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states = historyitem.states & 0xffbfffff;
            addHistoryRecordLocked(l, l1);
            mAudioOnTimer.stopAllRunningLocked(l);
            for(int i = 0; i < mUidStats.size(); i++)
                ((Uid)mUidStats.valueAt(i)).noteResetAudioLocked(l);

        }
    }

    public void noteResetBluetoothScanLocked()
    {
        if(mBluetoothScanNesting > 0)
        {
            long l = mClocks.elapsedRealtime();
            long l1 = mClocks.uptimeMillis();
            mBluetoothScanNesting = 0;
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states2 = historyitem.states2 & 0xffefffff;
            addHistoryRecordLocked(l, l1);
            mBluetoothScanTimer.stopAllRunningLocked(l);
            for(int i = 0; i < mUidStats.size(); i++)
                ((Uid)mUidStats.valueAt(i)).noteResetBluetoothScanLocked(l);

        }
    }

    public void noteResetCameraLocked()
    {
        if(mCameraOnNesting > 0)
        {
            long l = mClocks.elapsedRealtime();
            long l1 = mClocks.uptimeMillis();
            mCameraOnNesting = 0;
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states2 = historyitem.states2 & 0xffdfffff;
            addHistoryRecordLocked(l, l1);
            mCameraOnTimer.stopAllRunningLocked(l);
            for(int i = 0; i < mUidStats.size(); i++)
                ((Uid)mUidStats.valueAt(i)).noteResetCameraLocked(l);

        }
    }

    public void noteResetFlashlightLocked()
    {
        if(mFlashlightOnNesting > 0)
        {
            long l = mClocks.elapsedRealtime();
            long l1 = mClocks.uptimeMillis();
            mFlashlightOnNesting = 0;
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states2 = historyitem.states2 & 0xf7ffffff;
            addHistoryRecordLocked(l, l1);
            mFlashlightOnTimer.stopAllRunningLocked(l);
            for(int i = 0; i < mUidStats.size(); i++)
                ((Uid)mUidStats.valueAt(i)).noteResetFlashlightLocked(l);

        }
    }

    public void noteResetVideoLocked()
    {
        if(mVideoOnNesting > 0)
        {
            long l = mClocks.elapsedRealtime();
            long l1 = mClocks.uptimeMillis();
            mAudioOnNesting = 0;
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states2 = historyitem.states2 & 0xbfffffff;
            addHistoryRecordLocked(l, l1);
            mVideoOnTimer.stopAllRunningLocked(l);
            for(int i = 0; i < mUidStats.size(); i++)
                ((Uid)mUidStats.valueAt(i)).noteResetVideoLocked(l);

        }
    }

    public void noteScreenBrightnessLocked(int i)
    {
        int j = i / 51;
        if(j >= 0) goto _L2; else goto _L1
_L1:
        i = 0;
_L4:
        if(mScreenBrightnessBin != i)
        {
            long l = mClocks.elapsedRealtime();
            long l1 = mClocks.uptimeMillis();
            mHistoryCur.states = mHistoryCur.states & -8 | i << 0;
            addHistoryRecordLocked(l, l1);
            if(mScreenState == 2)
            {
                if(mScreenBrightnessBin >= 0)
                    mScreenBrightnessTimer[mScreenBrightnessBin].stopRunningLocked(l);
                mScreenBrightnessTimer[i].startRunningLocked(l);
            }
            mScreenBrightnessBin = i;
        }
        return;
_L2:
        i = j;
        if(j >= 5)
            i = 4;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void noteScreenStateLocked(int i)
    {
        int j;
        if(mPretendScreenOff)
            i = 1;
        j = i;
        if(i > 4)
            switch(i)
            {
            default:
                Slog.wtf("BatteryStatsImpl", (new StringBuilder()).append("Unknown screen state (not mapped): ").append(i).toString());
                j = i;
                break;

            case 5: // '\005'
                break MISSING_BLOCK_LABEL_335;
            }
_L1:
        if(mScreenState != j)
        {
            recordDailyStatsIfNeededLocked(true);
            int k = mScreenState;
            mScreenState = j;
            long l;
            long l1;
            if(j != 0)
            {
                i = j - 1;
                android.os.BatteryStats.HistoryItem historyitem;
                if((i & 3) == i)
                {
                    mModStepMode = mModStepMode | mCurStepMode & 3 ^ i;
                    mCurStepMode = mCurStepMode & -4 | i;
                } else
                {
                    Slog.wtf("BatteryStatsImpl", (new StringBuilder()).append("Unexpected screen state: ").append(j).toString());
                }
            }
            l = mClocks.elapsedRealtime();
            l1 = mClocks.uptimeMillis();
            i = 0;
            if(isScreenDoze(j))
            {
                historyitem = mHistoryCur;
                historyitem.states = historyitem.states | 0x40000;
                mScreenDozeTimer.startRunningLocked(l);
                i = 1;
            } else
            if(isScreenDoze(k))
            {
                android.os.BatteryStats.HistoryItem historyitem1 = mHistoryCur;
                historyitem1.states = historyitem1.states & 0xfffbffff;
                mScreenDozeTimer.stopRunningLocked(l);
                i = 1;
            }
            if(isScreenOn(j))
            {
                historyitem = mHistoryCur;
                historyitem.states = historyitem.states | 0x100000;
                mScreenOnTimer.startRunningLocked(l);
                if(mScreenBrightnessBin >= 0)
                    mScreenBrightnessTimer[mScreenBrightnessBin].startRunningLocked(l);
                i = 1;
            } else
            if(isScreenOn(k))
            {
                android.os.BatteryStats.HistoryItem historyitem2 = mHistoryCur;
                historyitem2.states = historyitem2.states & 0xffefffff;
                mScreenOnTimer.stopRunningLocked(l);
                if(mScreenBrightnessBin >= 0)
                    mScreenBrightnessTimer[mScreenBrightnessBin].stopRunningLocked(l);
                i = 1;
            }
            if(i != 0)
                addHistoryRecordLocked(l, l1);
            if(isScreenOn(j))
            {
                updateTimeBasesLocked(mOnBatteryTimeBase.isRunning(), j, mClocks.uptimeMillis() * 1000L, 1000L * l);
                noteStartWakeLocked(-1, -1, "screen", null, 0, false, l, l1);
            } else
            if(isScreenOn(k))
            {
                noteStopWakeLocked(-1, -1, "screen", "screen", 0, l, l1);
                updateTimeBasesLocked(mOnBatteryTimeBase.isRunning(), j, mClocks.uptimeMillis() * 1000L, 1000L * l);
            }
            if(mOnBatteryInternal)
                updateDischargeScreenLevelsLocked(k, j);
        }
        return;
        j = 2;
          goto _L1
    }

    public void noteStartGpsLocked(int i)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        if(mGpsNesting == 0)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states = historyitem.states | 0x20000000;
            addHistoryRecordLocked(l, l1);
        }
        mGpsNesting = mGpsNesting + 1;
        getUidStatsLocked(i).noteStartGps(l);
    }

    public void noteStartSensorLocked(int i, int j)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        if(mSensorNesting == 0)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states = historyitem.states | 0x800000;
            addHistoryRecordLocked(l, l1);
        }
        mSensorNesting = mSensorNesting + 1;
        getUidStatsLocked(i).noteStartSensor(j, l);
    }

    public void noteStartWakeFromSourceLocked(WorkSource worksource, int i, String s, String s1, int j, boolean flag)
    {
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        int k = worksource.size();
        for(int i1 = 0; i1 < k; i1++)
            noteStartWakeLocked(worksource.get(i1), i, s, s1, j, flag, l, l1);

    }

    public void noteStartWakeLocked(int i, int j, String s, String s1, int k, boolean flag, long l, long l1)
    {
        i = mapUid(i);
        if(k != 0) goto _L2; else goto _L1
_L1:
        String s2;
        aggregateLastWakeupUptimeLocked(l1);
        s2 = s1;
        if(s1 == null)
            s2 = s;
        if(mRecordAllHistory && mActiveEvents.updateState(32773, s2, i, 0))
            addHistoryEventLocked(l, l1, 32773, s2, i);
        if(mWakeLockNesting != 0) goto _L4; else goto _L3
_L3:
        s1 = mHistoryCur;
        s1.states = ((android.os.BatteryStats.HistoryItem) (s1)).states | 0x40000000;
        mHistoryCur.wakelockTag = mHistoryCur.localWakelockTag;
        s1 = mHistoryCur.wakelockTag;
        mInitialAcquireWakeName = s2;
        s1.string = s2;
        s1 = mHistoryCur.wakelockTag;
        mInitialAcquireWakeUid = i;
        s1.uid = i;
        mWakeLockImportant = flag ^ true;
        addHistoryRecordLocked(l, l1);
_L6:
        mWakeLockNesting = mWakeLockNesting + 1;
_L2:
        if(i >= 0)
        {
            if(mOnBatteryScreenOffTimeBase.isRunning())
                requestWakelockCpuUpdate();
            getUidStatsLocked(i).noteStartWakeLocked(j, s, k, l);
        }
        return;
_L4:
        if(!mWakeLockImportant && flag ^ true && mHistoryLastWritten.cmd == 0)
        {
            if(mHistoryLastWritten.wakelockTag != null)
            {
                mHistoryLastWritten.wakelockTag = null;
                mHistoryCur.wakelockTag = mHistoryCur.localWakelockTag;
                s1 = mHistoryCur.wakelockTag;
                mInitialAcquireWakeName = s2;
                s1.string = s2;
                s1 = mHistoryCur.wakelockTag;
                mInitialAcquireWakeUid = i;
                s1.uid = i;
                addHistoryRecordLocked(l, l1);
            }
            mWakeLockImportant = true;
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void noteStopGpsLocked(int i)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        mGpsNesting = mGpsNesting - 1;
        if(mGpsNesting == 0)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states = historyitem.states & 0xdfffffff;
            addHistoryRecordLocked(l, l1);
        }
        getUidStatsLocked(i).noteStopGps(l);
    }

    public void noteStopSensorLocked(int i, int j)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        mSensorNesting = mSensorNesting - 1;
        if(mSensorNesting == 0)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states = historyitem.states & 0xff7fffff;
            addHistoryRecordLocked(l, l1);
        }
        getUidStatsLocked(i).noteStopSensor(j, l);
    }

    public void noteStopWakeFromSourceLocked(WorkSource worksource, int i, String s, String s1, int j)
    {
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        int k = worksource.size();
        for(int i1 = 0; i1 < k; i1++)
            noteStopWakeLocked(worksource.get(i1), i, s, s1, j, l, l1);

    }

    public void noteStopWakeLocked(int i, int j, String s, String s1, int k, long l, 
            long l1)
    {
        i = mapUid(i);
        if(k == 0)
        {
            mWakeLockNesting = mWakeLockNesting - 1;
            if(mRecordAllHistory)
            {
                String s2 = s1;
                if(s1 == null)
                    s2 = s;
                if(mActiveEvents.updateState(16389, s2, i, 0))
                    addHistoryEventLocked(l, l1, 16389, s2, i);
            }
            if(mWakeLockNesting == 0)
            {
                s1 = mHistoryCur;
                s1.states = ((android.os.BatteryStats.HistoryItem) (s1)).states & 0xbfffffff;
                mInitialAcquireWakeName = null;
                mInitialAcquireWakeUid = -1;
                addHistoryRecordLocked(l, l1);
            }
        }
        if(i >= 0)
        {
            if(mOnBatteryScreenOffTimeBase.isRunning())
                requestWakelockCpuUpdate();
            getUidStatsLocked(i).noteStopWakeLocked(j, s, k, l);
        }
    }

    public void noteSyncFinishLocked(String s, int i)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        getUidStatsLocked(i).noteStopSyncLocked(s, l);
        if(!mActiveEvents.updateState(16388, s, i, 0))
        {
            return;
        } else
        {
            addHistoryEventLocked(l, l1, 16388, s, i);
            return;
        }
    }

    public void noteSyncStartLocked(String s, int i)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        getUidStatsLocked(i).noteStartSyncLocked(s, l);
        if(!mActiveEvents.updateState(32772, s, i, 0))
        {
            return;
        } else
        {
            addHistoryEventLocked(l, l1, 32772, s, i);
            return;
        }
    }

    public void noteUidProcessStateLocked(int i, int j)
    {
        if(i != mapUid(i))
        {
            return;
        } else
        {
            getUidStatsLocked(i).updateUidProcessStateLocked(j);
            return;
        }
    }

    public void noteUserActivityLocked(int i, int j)
    {
        if(mOnBatteryInternal)
            getUidStatsLocked(mapUid(i)).noteUserActivityLocked(j);
    }

    public void noteVibratorOffLocked(int i)
    {
        getUidStatsLocked(mapUid(i)).noteVibratorOffLocked();
    }

    public void noteVibratorOnLocked(int i, long l)
    {
        getUidStatsLocked(mapUid(i)).noteVibratorOnLocked(l);
    }

    public void noteVideoOffLocked(int i)
    {
        if(mVideoOnNesting == 0)
            return;
        int j = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        i = mVideoOnNesting - 1;
        mVideoOnNesting = i;
        if(i == 0)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states2 = historyitem.states2 & 0xbfffffff;
            addHistoryRecordLocked(l, l1);
            mVideoOnTimer.stopRunningLocked(l);
        }
        getUidStatsLocked(j).noteVideoTurnedOffLocked(l);
    }

    public void noteVideoOnLocked(int i)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        if(mVideoOnNesting == 0)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states2 = historyitem.states2 | 0x40000000;
            addHistoryRecordLocked(l, l1);
            mVideoOnTimer.startRunningLocked(l);
        }
        mVideoOnNesting = mVideoOnNesting + 1;
        getUidStatsLocked(i).noteVideoTurnedOnLocked(l);
    }

    public void noteWakeUpLocked(String s, int i)
    {
        addHistoryEventLocked(mClocks.elapsedRealtime(), mClocks.uptimeMillis(), 18, s, i);
    }

    public void noteWakeupReasonLocked(String s)
    {
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        aggregateLastWakeupUptimeLocked(l1);
        mHistoryCur.wakeReasonTag = mHistoryCur.localWakeReasonTag;
        mHistoryCur.wakeReasonTag.string = s;
        mHistoryCur.wakeReasonTag.uid = 0;
        mLastWakeupReason = s;
        mLastWakeupUptimeMs = l1;
        addHistoryRecordLocked(l, l1);
    }

    public void noteWifiBatchedScanStartedFromSourceLocked(WorkSource worksource, int i)
    {
        int j = worksource.size();
        for(int k = 0; k < j; k++)
            noteWifiBatchedScanStartedLocked(worksource.get(k), i);

    }

    public void noteWifiBatchedScanStartedLocked(int i, int j)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        getUidStatsLocked(i).noteWifiBatchedScanStartedLocked(j, l);
    }

    public void noteWifiBatchedScanStoppedFromSourceLocked(WorkSource worksource)
    {
        int i = worksource.size();
        for(int j = 0; j < i; j++)
            noteWifiBatchedScanStoppedLocked(worksource.get(j));

    }

    public void noteWifiBatchedScanStoppedLocked(int i)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        getUidStatsLocked(i).noteWifiBatchedScanStoppedLocked(l);
    }

    public void noteWifiMulticastDisabledFromSourceLocked(WorkSource worksource)
    {
        int i = worksource.size();
        for(int j = 0; j < i; j++)
            noteWifiMulticastDisabledLocked(worksource.get(j));

    }

    public void noteWifiMulticastDisabledLocked(int i)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        mWifiMulticastNesting = mWifiMulticastNesting - 1;
        if(mWifiMulticastNesting == 0)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states = historyitem.states & 0xfffeffff;
            addHistoryRecordLocked(l, l1);
        }
        getUidStatsLocked(i).noteWifiMulticastDisabledLocked(l);
    }

    public void noteWifiMulticastEnabledFromSourceLocked(WorkSource worksource)
    {
        int i = worksource.size();
        for(int j = 0; j < i; j++)
            noteWifiMulticastEnabledLocked(worksource.get(j));

    }

    public void noteWifiMulticastEnabledLocked(int i)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        if(mWifiMulticastNesting == 0)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states = historyitem.states | 0x10000;
            addHistoryRecordLocked(l, l1);
        }
        mWifiMulticastNesting = mWifiMulticastNesting + 1;
        getUidStatsLocked(i).noteWifiMulticastEnabledLocked(l);
    }

    public void noteWifiOffLocked()
    {
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        if(mWifiOn)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states2 = historyitem.states2 & 0xefffffff;
            addHistoryRecordLocked(l, l1);
            mWifiOn = false;
            mWifiOnTimer.stopRunningLocked(l);
            scheduleSyncExternalStatsLocked("wifi-on", 2);
        }
    }

    public void noteWifiOnLocked()
    {
        if(!mWifiOn)
        {
            long l = mClocks.elapsedRealtime();
            long l1 = mClocks.uptimeMillis();
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states2 = historyitem.states2 | 0x10000000;
            addHistoryRecordLocked(l, l1);
            mWifiOn = true;
            mWifiOnTimer.startRunningLocked(l);
            scheduleSyncExternalStatsLocked("wifi-off", 2);
        }
    }

    public void noteWifiRadioPowerState(int i, long l, int j)
    {
        l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        if(mWifiRadioPowerState != i)
        {
            boolean flag;
            if(i != 2)
            {
                if(i == 3)
                    flag = true;
                else
                    flag = false;
            } else
            {
                flag = true;
            }
            if(flag)
            {
                if(j > 0)
                    noteWifiRadioApWakeupLocked(l, l1, j);
                android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
                historyitem.states = historyitem.states | 0x4000000;
            } else
            {
                android.os.BatteryStats.HistoryItem historyitem1 = mHistoryCur;
                historyitem1.states = historyitem1.states & 0xfbffffff;
            }
            addHistoryRecordLocked(l, l1);
            mWifiRadioPowerState = i;
        }
    }

    public void noteWifiRssiChangedLocked(int i)
    {
        i = WifiManager.calculateSignalLevel(i, 5);
        if(mWifiSignalStrengthBin != i)
        {
            long l = mClocks.elapsedRealtime();
            long l1 = mClocks.uptimeMillis();
            if(mWifiSignalStrengthBin >= 0)
                mWifiSignalStrengthsTimer[mWifiSignalStrengthBin].stopRunningLocked(l);
            if(i >= 0)
            {
                if(!mWifiSignalStrengthsTimer[i].isRunningLocked())
                    mWifiSignalStrengthsTimer[i].startRunningLocked(l);
                mHistoryCur.states2 = mHistoryCur.states2 & 0xffffff8f | i << 4;
                addHistoryRecordLocked(l, l1);
            } else
            {
                stopAllWifiSignalStrengthTimersLocked(-1);
            }
            mWifiSignalStrengthBin = i;
        }
    }

    public void noteWifiRunningChangedLocked(WorkSource worksource, WorkSource worksource1)
    {
        if(mGlobalWifiRunning)
        {
            long l = mClocks.elapsedRealtime();
            int i = worksource.size();
            for(int j = 0; j < i; j++)
                getUidStatsLocked(mapUid(worksource.get(j))).noteWifiStoppedLocked(l);

            i = worksource1.size();
            for(int k = 0; k < i; k++)
                getUidStatsLocked(mapUid(worksource1.get(k))).noteWifiRunningLocked(l);

        } else
        {
            Log.w("BatteryStatsImpl", "noteWifiRunningChangedLocked -- called while WIFI not running");
        }
    }

    public void noteWifiRunningLocked(WorkSource worksource)
    {
        if(!mGlobalWifiRunning)
        {
            long l = mClocks.elapsedRealtime();
            long l1 = mClocks.uptimeMillis();
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states2 = historyitem.states2 | 0x20000000;
            addHistoryRecordLocked(l, l1);
            mGlobalWifiRunning = true;
            mGlobalWifiRunningTimer.startRunningLocked(l);
            int i = worksource.size();
            for(int j = 0; j < i; j++)
                getUidStatsLocked(mapUid(worksource.get(j))).noteWifiRunningLocked(l);

            scheduleSyncExternalStatsLocked("wifi-running", 2);
        } else
        {
            Log.w("BatteryStatsImpl", "noteWifiRunningLocked -- called while WIFI running");
        }
    }

    public void noteWifiScanStartedFromSourceLocked(WorkSource worksource)
    {
        int i = worksource.size();
        for(int j = 0; j < i; j++)
            noteWifiScanStartedLocked(worksource.get(j));

    }

    public void noteWifiScanStartedLocked(int i)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        if(mWifiScanNesting == 0)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states = historyitem.states | 0x8000000;
            addHistoryRecordLocked(l, l1);
        }
        mWifiScanNesting = mWifiScanNesting + 1;
        getUidStatsLocked(i).noteWifiScanStartedLocked(l);
    }

    public void noteWifiScanStoppedFromSourceLocked(WorkSource worksource)
    {
        int i = worksource.size();
        for(int j = 0; j < i; j++)
            noteWifiScanStoppedLocked(worksource.get(j));

    }

    public void noteWifiScanStoppedLocked(int i)
    {
        i = mapUid(i);
        long l = mClocks.elapsedRealtime();
        long l1 = mClocks.uptimeMillis();
        mWifiScanNesting = mWifiScanNesting - 1;
        if(mWifiScanNesting == 0)
        {
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states = historyitem.states & 0xf7ffffff;
            addHistoryRecordLocked(l, l1);
        }
        getUidStatsLocked(i).noteWifiScanStoppedLocked(l);
    }

    public void noteWifiStateLocked(int i, String s)
    {
        if(mWifiState != i)
        {
            long l = mClocks.elapsedRealtime();
            if(mWifiState >= 0)
                mWifiStateTimer[mWifiState].stopRunningLocked(l);
            mWifiState = i;
            mWifiStateTimer[i].startRunningLocked(l);
            scheduleSyncExternalStatsLocked("wifi-state", 2);
        }
    }

    public void noteWifiStoppedLocked(WorkSource worksource)
    {
        if(mGlobalWifiRunning)
        {
            long l = mClocks.elapsedRealtime();
            long l1 = mClocks.uptimeMillis();
            android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
            historyitem.states2 = historyitem.states2 & 0xdfffffff;
            addHistoryRecordLocked(l, l1);
            mGlobalWifiRunning = false;
            mGlobalWifiRunningTimer.stopRunningLocked(l);
            int i = worksource.size();
            for(int j = 0; j < i; j++)
                getUidStatsLocked(mapUid(worksource.get(j))).noteWifiStoppedLocked(l);

            scheduleSyncExternalStatsLocked("wifi-stopped", 2);
        } else
        {
            Log.w("BatteryStatsImpl", "noteWifiStoppedLocked -- called while WIFI not running");
        }
    }

    public void noteWifiSupplicantStateChangedLocked(int i, boolean flag)
    {
        if(mWifiSupplState != i)
        {
            long l = mClocks.elapsedRealtime();
            long l1 = mClocks.uptimeMillis();
            if(mWifiSupplState >= 0)
                mWifiSupplStateTimer[mWifiSupplState].stopRunningLocked(l);
            mWifiSupplState = i;
            mWifiSupplStateTimer[i].startRunningLocked(l);
            mHistoryCur.states2 = mHistoryCur.states2 & 0xfffffff0 | i << 0;
            addHistoryRecordLocked(l, l1);
        }
    }

    public void onCleanupUserLocked(int i)
    {
        int j = UserHandle.getUid(i, 0);
        i = UserHandle.getUid(i, 0x1869f);
        mKernelUidCpuFreqTimeReader.removeUidsInRange(j, i);
        mKernelUidCpuTimeReader.removeUidsInRange(j, i);
    }

    public void onUserRemovedLocked(int i)
    {
        int j = UserHandle.getUid(i, 0);
        int k = UserHandle.getUid(i, 0x1869f);
        mUidStats.put(j, null);
        mUidStats.put(k, null);
        i = mUidStats.indexOfKey(j);
        j = mUidStats.indexOfKey(k);
        mUidStats.removeAtRange(i, (j - i) + 1);
    }

    public void prepareForDumpLocked()
    {
        pullPendingStateUpdatesLocked();
        getStartClockTime();
    }

    public void pullPendingStateUpdatesLocked()
    {
        if(mOnBatteryInternal)
            updateDischargeScreenLevelsLocked(mScreenState, mScreenState);
    }

    void readDailyItemTagDetailsLocked(XmlPullParser xmlpullparser, android.os.BatteryStats.DailyItem dailyitem, boolean flag, String s)
        throws NumberFormatException, XmlPullParserException, IOException
    {
        Object obj = xmlpullparser.getAttributeValue(null, "n");
        if(obj == null)
        {
            Slog.w("BatteryStatsImpl", (new StringBuilder()).append("Missing 'n' attribute at ").append(xmlpullparser.getPositionDescription()).toString());
            XmlUtils.skipCurrentTag(xmlpullparser);
            return;
        }
        int i = Integer.parseInt(((String) (obj)));
        obj = new android.os.BatteryStats.LevelStepTracker(i);
        int j;
        int k;
        if(flag)
            dailyitem.mChargeSteps = ((android.os.BatteryStats.LevelStepTracker) (obj));
        else
            dailyitem.mDischargeSteps = ((android.os.BatteryStats.LevelStepTracker) (obj));
        j = 0;
        k = xmlpullparser.getDepth();
        do
        {
            int l = xmlpullparser.next();
            if(l == 1 || l == 3 && xmlpullparser.getDepth() <= k)
                break;
            if(l != 3 && l != 4)
                if("s".equals(xmlpullparser.getName()))
                {
                    if(j < i)
                    {
                        dailyitem = xmlpullparser.getAttributeValue(null, "v");
                        if(dailyitem != null)
                        {
                            ((android.os.BatteryStats.LevelStepTracker) (obj)).decodeEntryAt(j, dailyitem);
                            j++;
                        }
                    }
                } else
                {
                    Slog.w("BatteryStatsImpl", (new StringBuilder()).append("Unknown element under <").append(s).append(">: ").append(xmlpullparser.getName()).toString());
                    XmlUtils.skipCurrentTag(xmlpullparser);
                }
        } while(true);
        obj.mNumStepDurations = j;
    }

    void readDailyItemTagLocked(XmlPullParser xmlpullparser)
        throws NumberFormatException, XmlPullParserException, IOException
    {
        android.os.BatteryStats.DailyItem dailyitem = new android.os.BatteryStats.DailyItem();
        String s = xmlpullparser.getAttributeValue(null, "start");
        if(s != null)
            dailyitem.mStartTime = Long.parseLong(s);
        s = xmlpullparser.getAttributeValue(null, "end");
        if(s != null)
            dailyitem.mEndTime = Long.parseLong(s);
        int i = xmlpullparser.getDepth();
        do
        {
            int j = xmlpullparser.next();
            if(j == 1 || j == 3 && xmlpullparser.getDepth() <= i)
                break;
            if(j != 3 && j != 4)
            {
                String s1 = xmlpullparser.getName();
                if(s1.equals("dis"))
                    readDailyItemTagDetailsLocked(xmlpullparser, dailyitem, false, "dis");
                else
                if(s1.equals("chg"))
                    readDailyItemTagDetailsLocked(xmlpullparser, dailyitem, true, "chg");
                else
                if(s1.equals("upd"))
                {
                    if(dailyitem.mPackageChanges == null)
                        dailyitem.mPackageChanges = new ArrayList();
                    android.os.BatteryStats.PackageChange packagechange1 = new android.os.BatteryStats.PackageChange();
                    packagechange1.mUpdate = true;
                    packagechange1.mPackageName = xmlpullparser.getAttributeValue(null, "pkg");
                    s1 = xmlpullparser.getAttributeValue(null, "ver");
                    int k;
                    if(s1 != null)
                        k = Integer.parseInt(s1);
                    else
                        k = 0;
                    packagechange1.mVersionCode = k;
                    dailyitem.mPackageChanges.add(packagechange1);
                    XmlUtils.skipCurrentTag(xmlpullparser);
                } else
                if(s1.equals("rem"))
                {
                    if(dailyitem.mPackageChanges == null)
                        dailyitem.mPackageChanges = new ArrayList();
                    android.os.BatteryStats.PackageChange packagechange = new android.os.BatteryStats.PackageChange();
                    packagechange.mUpdate = false;
                    packagechange.mPackageName = xmlpullparser.getAttributeValue(null, "pkg");
                    dailyitem.mPackageChanges.add(packagechange);
                    XmlUtils.skipCurrentTag(xmlpullparser);
                } else
                {
                    Slog.w("BatteryStatsImpl", (new StringBuilder()).append("Unknown element under <item>: ").append(xmlpullparser.getName()).toString());
                    XmlUtils.skipCurrentTag(xmlpullparser);
                }
            }
        } while(true);
        mDailyItems.add(dailyitem);
    }

    public void readDailyStatsLocked()
    {
        Object obj;
        Slog.d("BatteryStatsImpl", (new StringBuilder()).append("Reading daily items from ").append(mDailyFile.getBaseFile()).toString());
        mDailyItems.clear();
        XmlPullParser xmlpullparser;
        try
        {
            obj = mDailyFile.openRead();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return;
        }
        xmlpullparser = Xml.newPullParser();
        xmlpullparser.setInput(((java.io.InputStream) (obj)), StandardCharsets.UTF_8.name());
        readDailyItemsLocked(xmlpullparser);
        ((FileInputStream) (obj)).close();
_L1:
        return;
        obj;
          goto _L1
        Object obj1;
        obj1;
        try
        {
            ((FileInputStream) (obj)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj) { }
          goto _L1
        obj1;
        try
        {
            ((FileInputStream) (obj)).close();
        }
        catch(IOException ioexception) { }
        throw obj1;
    }

    public void readFromParcel(Parcel parcel)
    {
        readFromParcelLocked(parcel);
    }

    void readFromParcelLocked(Parcel parcel)
    {
        int i = parcel.readInt();
        if(i != 0xba757475)
            throw new ParcelFormatException((new StringBuilder()).append("Bad magic number: #").append(Integer.toHexString(i)).toString());
        readHistory(parcel, false);
        mStartCount = parcel.readInt();
        mStartClockTime = parcel.readLong();
        mStartPlatformVersion = parcel.readString();
        mEndPlatformVersion = parcel.readString();
        mUptime = parcel.readLong();
        mUptimeStart = parcel.readLong();
        mRealtime = parcel.readLong();
        mRealtimeStart = parcel.readLong();
        boolean flag;
        if(parcel.readInt() != 0)
            flag = true;
        else
            flag = false;
        mOnBattery = flag;
        mEstimatedBatteryCapacity = parcel.readInt();
        mMinLearnedBatteryCapacity = parcel.readInt();
        mMaxLearnedBatteryCapacity = parcel.readInt();
        mOnBatteryInternal = false;
        mOnBatteryTimeBase.readFromParcel(parcel);
        mOnBatteryScreenOffTimeBase.readFromParcel(parcel);
        mScreenState = 0;
        mScreenOnTimer = new StopwatchTimer(mClocks, null, -1, null, mOnBatteryTimeBase, parcel);
        mScreenDozeTimer = new StopwatchTimer(mClocks, null, -1, null, mOnBatteryTimeBase, parcel);
        for(int j = 0; j < 5; j++)
            mScreenBrightnessTimer[j] = new StopwatchTimer(mClocks, null, -100 - j, null, mOnBatteryTimeBase, parcel);

        mInteractive = false;
        mInteractiveTimer = new StopwatchTimer(mClocks, null, -10, null, mOnBatteryTimeBase, parcel);
        mPhoneOn = false;
        mPowerSaveModeEnabledTimer = new StopwatchTimer(mClocks, null, -2, null, mOnBatteryTimeBase, parcel);
        mLongestLightIdleTime = parcel.readLong();
        mLongestFullIdleTime = parcel.readLong();
        mDeviceIdleModeLightTimer = new StopwatchTimer(mClocks, null, -14, null, mOnBatteryTimeBase, parcel);
        mDeviceIdleModeFullTimer = new StopwatchTimer(mClocks, null, -11, null, mOnBatteryTimeBase, parcel);
        mDeviceLightIdlingTimer = new StopwatchTimer(mClocks, null, -15, null, mOnBatteryTimeBase, parcel);
        mDeviceIdlingTimer = new StopwatchTimer(mClocks, null, -12, null, mOnBatteryTimeBase, parcel);
        mPhoneOnTimer = new StopwatchTimer(mClocks, null, -3, null, mOnBatteryTimeBase, parcel);
        for(int k = 0; k < 6; k++)
            mPhoneSignalStrengthsTimer[k] = new StopwatchTimer(mClocks, null, -200 - k, null, mOnBatteryTimeBase, parcel);

        mPhoneSignalScanningTimer = new StopwatchTimer(mClocks, null, -199, null, mOnBatteryTimeBase, parcel);
        for(int l = 0; l < 17; l++)
            mPhoneDataConnectionsTimer[l] = new StopwatchTimer(mClocks, null, -300 - l, null, mOnBatteryTimeBase, parcel);

        for(int i1 = 0; i1 < 10; i1++)
        {
            mNetworkByteActivityCounters[i1] = new LongSamplingCounter(mOnBatteryTimeBase, parcel);
            mNetworkPacketActivityCounters[i1] = new LongSamplingCounter(mOnBatteryTimeBase, parcel);
        }

        mMobileRadioPowerState = 1;
        mMobileRadioActiveTimer = new StopwatchTimer(mClocks, null, -400, null, mOnBatteryTimeBase, parcel);
        mMobileRadioActivePerAppTimer = new StopwatchTimer(mClocks, null, -401, null, mOnBatteryTimeBase, parcel);
        mMobileRadioActiveAdjustedTime = new LongSamplingCounter(mOnBatteryTimeBase, parcel);
        mMobileRadioActiveUnknownTime = new LongSamplingCounter(mOnBatteryTimeBase, parcel);
        mMobileRadioActiveUnknownCount = new LongSamplingCounter(mOnBatteryTimeBase, parcel);
        mWifiRadioPowerState = 1;
        mWifiOn = false;
        mWifiOnTimer = new StopwatchTimer(mClocks, null, -4, null, mOnBatteryTimeBase, parcel);
        mGlobalWifiRunning = false;
        mGlobalWifiRunningTimer = new StopwatchTimer(mClocks, null, -5, null, mOnBatteryTimeBase, parcel);
        for(int j1 = 0; j1 < 8; j1++)
            mWifiStateTimer[j1] = new StopwatchTimer(mClocks, null, -600 - j1, null, mOnBatteryTimeBase, parcel);

        for(int k1 = 0; k1 < 13; k1++)
            mWifiSupplStateTimer[k1] = new StopwatchTimer(mClocks, null, -700 - k1, null, mOnBatteryTimeBase, parcel);

        for(int l1 = 0; l1 < 5; l1++)
            mWifiSignalStrengthsTimer[l1] = new StopwatchTimer(mClocks, null, -800 - l1, null, mOnBatteryTimeBase, parcel);

        mWifiActivity = new ControllerActivityCounterImpl(mOnBatteryTimeBase, 1, parcel);
        mBluetoothActivity = new ControllerActivityCounterImpl(mOnBatteryTimeBase, 1, parcel);
        mModemActivity = new ControllerActivityCounterImpl(mOnBatteryTimeBase, 5, parcel);
        int k3;
        if(parcel.readInt() != 0)
            flag = true;
        else
            flag = false;
        mHasWifiReporting = flag;
        if(parcel.readInt() != 0)
            flag = true;
        else
            flag = false;
        mHasBluetoothReporting = flag;
        if(parcel.readInt() != 0)
            flag = true;
        else
            flag = false;
        mHasModemReporting = flag;
        mNumConnectivityChange = parcel.readInt();
        mLoadedNumConnectivityChange = parcel.readInt();
        mUnpluggedNumConnectivityChange = parcel.readInt();
        mAudioOnNesting = 0;
        mAudioOnTimer = new StopwatchTimer(mClocks, null, -7, null, mOnBatteryTimeBase);
        mVideoOnNesting = 0;
        mVideoOnTimer = new StopwatchTimer(mClocks, null, -8, null, mOnBatteryTimeBase);
        mFlashlightOnNesting = 0;
        mFlashlightOnTimer = new StopwatchTimer(mClocks, null, -9, null, mOnBatteryTimeBase, parcel);
        mCameraOnNesting = 0;
        mCameraOnTimer = new StopwatchTimer(mClocks, null, -13, null, mOnBatteryTimeBase, parcel);
        mBluetoothScanNesting = 0;
        mBluetoothScanTimer = new StopwatchTimer(mClocks, null, -14, null, mOnBatteryTimeBase, parcel);
        mDischargeUnplugLevel = parcel.readInt();
        mDischargePlugLevel = parcel.readInt();
        mDischargeCurrentLevel = parcel.readInt();
        mCurrentBatteryLevel = parcel.readInt();
        mLowDischargeAmountSinceCharge = parcel.readInt();
        mHighDischargeAmountSinceCharge = parcel.readInt();
        mDischargeAmountScreenOn = parcel.readInt();
        mDischargeAmountScreenOnSinceCharge = parcel.readInt();
        mDischargeAmountScreenOff = parcel.readInt();
        mDischargeAmountScreenOffSinceCharge = parcel.readInt();
        mDischargeAmountScreenDoze = parcel.readInt();
        mDischargeAmountScreenDozeSinceCharge = parcel.readInt();
        mDischargeStepTracker.readFromParcel(parcel);
        mChargeStepTracker.readFromParcel(parcel);
        mDischargeCounter = new LongSamplingCounter(mOnBatteryTimeBase, parcel);
        mDischargeScreenOffCounter = new LongSamplingCounter(mOnBatteryScreenOffTimeBase, parcel);
        mDischargeScreenDozeCounter = new LongSamplingCounter(mOnBatteryTimeBase, parcel);
        mLastWriteTime = parcel.readLong();
        mRpmStats.clear();
        k3 = parcel.readInt();
        for(int i2 = 0; i2 < k3; i2++)
            if(parcel.readInt() != 0)
            {
                String s = parcel.readString();
                SamplingTimer samplingtimer1 = new SamplingTimer(mClocks, mOnBatteryTimeBase, parcel);
                mRpmStats.put(s, samplingtimer1);
            }

        mScreenOffRpmStats.clear();
        k3 = parcel.readInt();
        for(int j2 = 0; j2 < k3; j2++)
            if(parcel.readInt() != 0)
            {
                String s1 = parcel.readString();
                SamplingTimer samplingtimer2 = new SamplingTimer(mClocks, mOnBatteryScreenOffTimeBase, parcel);
                mScreenOffRpmStats.put(s1, samplingtimer2);
            }

        mKernelWakelockStats.clear();
        k3 = parcel.readInt();
        for(int k2 = 0; k2 < k3; k2++)
            if(parcel.readInt() != 0)
            {
                String s3 = parcel.readString();
                SamplingTimer samplingtimer = new SamplingTimer(mClocks, mOnBatteryScreenOffTimeBase, parcel);
                mKernelWakelockStats.put(s3, samplingtimer);
            }

        mWakeupReasonStats.clear();
        k3 = parcel.readInt();
        for(int l2 = 0; l2 < k3; l2++)
            if(parcel.readInt() != 0)
            {
                String s2 = parcel.readString();
                SamplingTimer samplingtimer3 = new SamplingTimer(mClocks, mOnBatteryTimeBase, parcel);
                mWakeupReasonStats.put(s2, samplingtimer3);
            }

        mKernelMemoryStats.clear();
        k3 = parcel.readInt();
        for(int i3 = 0; i3 < k3; i3++)
            if(parcel.readInt() != 0)
            {
                long l3 = parcel.readLong();
                SamplingTimer samplingtimer4 = new SamplingTimer(mClocks, mOnBatteryTimeBase, parcel);
                mKernelMemoryStats.put(Long.valueOf(l3).longValue(), samplingtimer4);
            }

        mPartialTimers.clear();
        mFullTimers.clear();
        mWindowTimers.clear();
        mWifiRunningTimers.clear();
        mFullWifiLockTimers.clear();
        mWifiScanTimers.clear();
        mWifiBatchedScanTimers.clear();
        mWifiMulticastTimers.clear();
        mAudioTurnedOnTimers.clear();
        mVideoTurnedOnTimers.clear();
        mFlashlightTurnedOnTimers.clear();
        mCameraTurnedOnTimers.clear();
        mCpuFreqs = parcel.createLongArray();
        k3 = parcel.readInt();
        mUidStats.clear();
        for(int j3 = 0; j3 < k3; j3++)
        {
            int i4 = parcel.readInt();
            Uid uid = new Uid(this, i4);
            uid.readFromParcelLocked(mOnBatteryTimeBase, mOnBatteryScreenOffTimeBase, parcel);
            mUidStats.append(i4, uid);
        }

    }

    void readHistory(Parcel parcel, boolean flag)
        throws ParcelFormatException
    {
        long l = parcel.readLong();
        mHistoryBuffer.setDataSize(0);
        mHistoryBuffer.setDataPosition(0);
        mHistoryTagPool.clear();
        mNextHistoryTagIdx = 0;
        mNumHistoryTagChars = 0;
        int i = parcel.readInt();
        for(int j = 0; j < i; j++)
        {
            int i1 = parcel.readInt();
            String s = parcel.readString();
            if(s == null)
                throw new ParcelFormatException("null history tag string");
            int j1 = parcel.readInt();
            android.os.BatteryStats.HistoryTag historytag = new android.os.BatteryStats.HistoryTag();
            historytag.string = s;
            historytag.uid = j1;
            historytag.poolIdx = i1;
            mHistoryTagPool.put(historytag, Integer.valueOf(i1));
            if(i1 >= mNextHistoryTagIdx)
                mNextHistoryTagIdx = i1 + 1;
            mNumHistoryTagChars = mNumHistoryTagChars + (historytag.string.length() + 1);
        }

        int k = parcel.readInt();
        i = parcel.dataPosition();
        if(k >= MAX_MAX_HISTORY_BUFFER * 3)
            throw new ParcelFormatException((new StringBuilder()).append("File corrupt: history data buffer too large ").append(k).toString());
        if((k & -4) != k)
            throw new ParcelFormatException((new StringBuilder()).append("File corrupt: history data buffer not aligned ").append(k).toString());
        mHistoryBuffer.appendFrom(parcel, i, k);
        parcel.setDataPosition(i + k);
        if(flag)
            readOldHistory(parcel);
        mHistoryBaseTime = l;
        if(mHistoryBaseTime > 0L)
        {
            long l1 = mClocks.elapsedRealtime();
            mHistoryBaseTime = (mHistoryBaseTime - l1) + 1L;
        }
    }

    public void readHistoryDelta(Parcel parcel, android.os.BatteryStats.HistoryItem historyitem)
    {
        int i;
        i = parcel.readInt();
        int j = i & 0x7ffff;
        historyitem.cmd = (byte)0;
        historyitem.numReadInts = 1;
        int k;
        int l;
        if(j < 0x7fffd)
        {
            historyitem.time = historyitem.time + (long)j;
        } else
        {
            if(j == 0x7fffd)
            {
                historyitem.time = parcel.readLong();
                historyitem.numReadInts = historyitem.numReadInts + 2;
                historyitem.readFromParcel(parcel);
                return;
            }
            if(j == 0x7fffe)
            {
                j = parcel.readInt();
                historyitem.time = historyitem.time + (long)j;
                historyitem.numReadInts = historyitem.numReadInts + 1;
            } else
            {
                long l1 = parcel.readLong();
                historyitem.time = historyitem.time + l1;
                historyitem.numReadInts = historyitem.numReadInts + 2;
            }
        }
        if((0x80000 & i) != 0)
        {
            j = parcel.readInt();
            readBatteryLevelInt(j, historyitem);
            historyitem.numReadInts = historyitem.numReadInts + 1;
        } else
        {
            j = 0;
        }
        if((0x100000 & i) == 0)
            break MISSING_BLOCK_LABEL_520;
        k = parcel.readInt();
        historyitem.states = 0xfe000000 & i | 0xffffff & k;
        historyitem.batteryStatus = (byte)(k >> 29 & 7);
        historyitem.batteryHealth = (byte)(k >> 26 & 7);
        historyitem.batteryPlugType = (byte)(k >> 24 & 3);
        historyitem.batteryPlugType;
        JVM INSTR tableswitch 1 3: default 172
    //                   1 493
    //                   2 502
    //                   3 511;
           goto _L1 _L2 _L3 _L4
_L1:
        historyitem.numReadInts = historyitem.numReadInts + 1;
_L5:
        if((0x200000 & i) != 0)
            historyitem.states2 = parcel.readInt();
        if((0x400000 & i) != 0)
        {
            l = parcel.readInt();
            k = l & 0xffff;
            l = l >> 16 & 0xffff;
            if(k != 65535)
            {
                historyitem.wakelockTag = historyitem.localWakelockTag;
                readHistoryTag(k, historyitem.wakelockTag);
            } else
            {
                historyitem.wakelockTag = null;
            }
            if(l != 65535)
            {
                historyitem.wakeReasonTag = historyitem.localWakeReasonTag;
                readHistoryTag(l, historyitem.wakeReasonTag);
            } else
            {
                historyitem.wakeReasonTag = null;
            }
            historyitem.numReadInts = historyitem.numReadInts + 1;
        } else
        {
            historyitem.wakelockTag = null;
            historyitem.wakeReasonTag = null;
        }
        if((0x800000 & i) != 0)
        {
            historyitem.eventTag = historyitem.localEventTag;
            k = parcel.readInt();
            historyitem.eventCode = 0xffff & k;
            readHistoryTag(k >> 16 & 0xffff, historyitem.eventTag);
            historyitem.numReadInts = historyitem.numReadInts + 1;
        } else
        {
            historyitem.eventCode = 0;
        }
        if((j & 1) != 0)
        {
            historyitem.stepDetails = mReadHistoryStepDetails;
            historyitem.stepDetails.readFromParcel(parcel);
        } else
        {
            historyitem.stepDetails = null;
        }
        if((0x1000000 & i) != 0)
            historyitem.batteryChargeUAh = parcel.readInt();
        return;
_L2:
        historyitem.batteryPlugType = (byte)1;
          goto _L1
_L3:
        historyitem.batteryPlugType = (byte)2;
          goto _L1
_L4:
        historyitem.batteryPlugType = (byte)4;
          goto _L1
        historyitem.states = 0xfe000000 & i | historyitem.states & 0xffffff;
          goto _L5
    }

    void readKernelUidCpuFreqTimesLocked()
    {
        KernelUidCpuFreqTimeReader kerneluidcpufreqtimereader = mKernelUidCpuFreqTimeReader;
        KernelUidCpuFreqTimeReader.Callback callback;
        if(!mOnBatteryInternal)
            callback = null;
        else
            callback = new KernelUidCpuFreqTimeReader.Callback() {

                public void onCpuFreqs(long al[])
                {
                    BatteryStatsImpl._2D_set0(BatteryStatsImpl.this, al);
                }

                public void onUidCpuFreqTime(int i, long al[])
                {
                    i = mapUid(i);
                    if(Process.isIsolated(i))
                    {
                        BatteryStatsImpl._2D_get2(BatteryStatsImpl.this).removeUid(i);
                        Slog.d("BatteryStatsImpl", (new StringBuilder()).append("Got freq readings for an isolated uid with no mapping to owning uid: ").append(i).toString());
                        return;
                    }
                    if(!UserInfoProvider._2D_wrap0(BatteryStatsImpl._2D_get5(BatteryStatsImpl.this), UserHandle.getUserId(i)))
                    {
                        Slog.d("BatteryStatsImpl", (new StringBuilder()).append("Got readings for an invalid user's uid ").append(i).toString());
                        BatteryStatsImpl._2D_get2(BatteryStatsImpl.this).removeUid(i);
                        return;
                    }
                    Uid uid = getUidStatsLocked(i);
                    if(uid.mCpuFreqTimeMs == null || uid.mCpuFreqTimeMs.getSize() != al.length)
                        uid.mCpuFreqTimeMs = new LongSamplingCounterArray(mOnBatteryTimeBase);
                    uid.mCpuFreqTimeMs.addCountLocked(al);
                    if(uid.mScreenOffCpuFreqTimeMs == null || uid.mScreenOffCpuFreqTimeMs.getSize() != al.length)
                        uid.mScreenOffCpuFreqTimeMs = new LongSamplingCounterArray(mOnBatteryScreenOffTimeBase);
                    uid.mScreenOffCpuFreqTimeMs.addCountLocked(al);
                }

                final BatteryStatsImpl this$0;

            
            {
                this$0 = BatteryStatsImpl.this;
                super();
            }
            }
;
        kerneluidcpufreqtimereader.readDelta(callback);
    }

    public void readLocked()
    {
        if(mDailyFile != null)
            readDailyStatsLocked();
        if(mFile == null)
        {
            Slog.w("BatteryStats", "readLocked: no file associated with this instance");
            return;
        }
        mUidStats.clear();
        File file;
        file = mFile.chooseForRead();
        if(!file.exists())
            return;
        try
        {
            FileInputStream fileinputstream = JVM INSTR new #2668 <Class FileInputStream>;
            fileinputstream.FileInputStream(file);
            byte abyte0[] = BatteryStatsHelper.readFully(fileinputstream);
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(abyte0, 0, abyte0.length);
            parcel.setDataPosition(0);
            fileinputstream.close();
            readSummaryFromParcel(parcel);
        }
        catch(Exception exception)
        {
            Slog.e("BatteryStats", "Error reading battery statistics", exception);
            resetAllStatsLocked();
        }
        mEndPlatformVersion = Build.ID;
        if(mHistoryBuffer.dataPosition() > 0)
        {
            mRecordingHistory = true;
            long l = mClocks.elapsedRealtime();
            long l1 = mClocks.uptimeMillis();
            addHistoryBufferLocked(l, l1, (byte)4, mHistoryCur);
            startRecordingHistory(l, l1, false);
        }
        recordDailyStatsIfNeededLocked(false);
        return;
    }

    void readOldHistory(Parcel parcel)
    {
    }

    public void readSummaryFromParcel(Parcel parcel)
        throws ParcelFormatException
    {
        int i = parcel.readInt();
        if(i != 167)
        {
            Slog.w("BatteryStats", (new StringBuilder()).append("readFromParcel: version got ").append(i).append(", expected ").append(167).append("; erasing old stats").toString());
            return;
        }
        readHistory(parcel, true);
        mStartCount = parcel.readInt();
        mUptime = parcel.readLong();
        mRealtime = parcel.readLong();
        mStartClockTime = parcel.readLong();
        mStartPlatformVersion = parcel.readString();
        mEndPlatformVersion = parcel.readString();
        mOnBatteryTimeBase.readSummaryFromParcel(parcel);
        mOnBatteryScreenOffTimeBase.readSummaryFromParcel(parcel);
        mDischargeUnplugLevel = parcel.readInt();
        mDischargePlugLevel = parcel.readInt();
        mDischargeCurrentLevel = parcel.readInt();
        mCurrentBatteryLevel = parcel.readInt();
        mEstimatedBatteryCapacity = parcel.readInt();
        mMinLearnedBatteryCapacity = parcel.readInt();
        mMaxLearnedBatteryCapacity = parcel.readInt();
        mLowDischargeAmountSinceCharge = parcel.readInt();
        mHighDischargeAmountSinceCharge = parcel.readInt();
        mDischargeAmountScreenOnSinceCharge = parcel.readInt();
        mDischargeAmountScreenOffSinceCharge = parcel.readInt();
        mDischargeAmountScreenDozeSinceCharge = parcel.readInt();
        mDischargeStepTracker.readFromParcel(parcel);
        mChargeStepTracker.readFromParcel(parcel);
        mDailyDischargeStepTracker.readFromParcel(parcel);
        mDailyChargeStepTracker.readFromParcel(parcel);
        mDischargeCounter.readSummaryFromParcelLocked(parcel);
        mDischargeScreenOffCounter.readSummaryFromParcelLocked(parcel);
        mDischargeScreenDozeCounter.readSummaryFromParcelLocked(parcel);
        i = parcel.readInt();
        if(i > 0)
        {
            mDailyPackageChanges = new ArrayList(i);
            while(i > 0) 
            {
                i--;
                android.os.BatteryStats.PackageChange packagechange = new android.os.BatteryStats.PackageChange();
                packagechange.mPackageName = parcel.readString();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                packagechange.mUpdate = flag;
                packagechange.mVersionCode = parcel.readInt();
                mDailyPackageChanges.add(packagechange);
            }
        } else
        {
            mDailyPackageChanges = null;
        }
        mDailyStartTime = parcel.readLong();
        mNextMinDailyDeadline = parcel.readLong();
        mNextMaxDailyDeadline = parcel.readLong();
        mStartCount = mStartCount + 1;
        mScreenState = 0;
        mScreenOnTimer.readSummaryFromParcelLocked(parcel);
        mScreenDozeTimer.readSummaryFromParcelLocked(parcel);
        for(i = 0; i < 5; i++)
            mScreenBrightnessTimer[i].readSummaryFromParcelLocked(parcel);

        mInteractive = false;
        mInteractiveTimer.readSummaryFromParcelLocked(parcel);
        mPhoneOn = false;
        mPowerSaveModeEnabledTimer.readSummaryFromParcelLocked(parcel);
        mLongestLightIdleTime = parcel.readLong();
        mLongestFullIdleTime = parcel.readLong();
        mDeviceIdleModeLightTimer.readSummaryFromParcelLocked(parcel);
        mDeviceIdleModeFullTimer.readSummaryFromParcelLocked(parcel);
        mDeviceLightIdlingTimer.readSummaryFromParcelLocked(parcel);
        mDeviceIdlingTimer.readSummaryFromParcelLocked(parcel);
        mPhoneOnTimer.readSummaryFromParcelLocked(parcel);
        for(i = 0; i < 6; i++)
            mPhoneSignalStrengthsTimer[i].readSummaryFromParcelLocked(parcel);

        mPhoneSignalScanningTimer.readSummaryFromParcelLocked(parcel);
        for(i = 0; i < 17; i++)
            mPhoneDataConnectionsTimer[i].readSummaryFromParcelLocked(parcel);

        for(i = 0; i < 10; i++)
        {
            mNetworkByteActivityCounters[i].readSummaryFromParcelLocked(parcel);
            mNetworkPacketActivityCounters[i].readSummaryFromParcelLocked(parcel);
        }

        mMobileRadioPowerState = 1;
        mMobileRadioActiveTimer.readSummaryFromParcelLocked(parcel);
        mMobileRadioActivePerAppTimer.readSummaryFromParcelLocked(parcel);
        mMobileRadioActiveAdjustedTime.readSummaryFromParcelLocked(parcel);
        mMobileRadioActiveUnknownTime.readSummaryFromParcelLocked(parcel);
        mMobileRadioActiveUnknownCount.readSummaryFromParcelLocked(parcel);
        mWifiRadioPowerState = 1;
        mWifiOn = false;
        mWifiOnTimer.readSummaryFromParcelLocked(parcel);
        mGlobalWifiRunning = false;
        mGlobalWifiRunningTimer.readSummaryFromParcelLocked(parcel);
        for(i = 0; i < 8; i++)
            mWifiStateTimer[i].readSummaryFromParcelLocked(parcel);

        for(i = 0; i < 13; i++)
            mWifiSupplStateTimer[i].readSummaryFromParcelLocked(parcel);

        for(i = 0; i < 5; i++)
            mWifiSignalStrengthsTimer[i].readSummaryFromParcelLocked(parcel);

        mWifiActivity.readSummaryFromParcel(parcel);
        mBluetoothActivity.readSummaryFromParcel(parcel);
        mModemActivity.readSummaryFromParcel(parcel);
        boolean flag1;
        int l1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mHasWifiReporting = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mHasBluetoothReporting = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mHasModemReporting = flag1;
        i = parcel.readInt();
        mLoadedNumConnectivityChange = i;
        mNumConnectivityChange = i;
        mFlashlightOnNesting = 0;
        mFlashlightOnTimer.readSummaryFromParcelLocked(parcel);
        mCameraOnNesting = 0;
        mCameraOnTimer.readSummaryFromParcelLocked(parcel);
        mBluetoothScanNesting = 0;
        mBluetoothScanTimer.readSummaryFromParcelLocked(parcel);
        l1 = parcel.readInt();
        if(l1 > 10000)
            throw new ParcelFormatException((new StringBuilder()).append("File corrupt: too many rpm stats ").append(l1).toString());
        for(int j = 0; j < l1; j++)
            if(parcel.readInt() != 0)
                getRpmTimerLocked(parcel.readString()).readSummaryFromParcelLocked(parcel);

        l1 = parcel.readInt();
        if(l1 > 10000)
            throw new ParcelFormatException((new StringBuilder()).append("File corrupt: too many screen-off rpm stats ").append(l1).toString());
        for(int k = 0; k < l1; k++)
            if(parcel.readInt() != 0)
                getScreenOffRpmTimerLocked(parcel.readString()).readSummaryFromParcelLocked(parcel);

        l1 = parcel.readInt();
        if(l1 > 10000)
            throw new ParcelFormatException((new StringBuilder()).append("File corrupt: too many kernel wake locks ").append(l1).toString());
        for(int l = 0; l < l1; l++)
            if(parcel.readInt() != 0)
                getKernelWakelockTimerLocked(parcel.readString()).readSummaryFromParcelLocked(parcel);

        l1 = parcel.readInt();
        if(l1 > 10000)
            throw new ParcelFormatException((new StringBuilder()).append("File corrupt: too many wakeup reasons ").append(l1).toString());
        for(int i1 = 0; i1 < l1; i1++)
            if(parcel.readInt() != 0)
                getWakeupReasonTimerLocked(parcel.readString()).readSummaryFromParcelLocked(parcel);

        l1 = parcel.readInt();
        for(int j1 = 0; j1 < l1; j1++)
            if(parcel.readInt() != 0)
                getKernelMemoryTimerLocked(parcel.readLong()).readSummaryFromParcelLocked(parcel);

        mCpuFreqs = parcel.createLongArray();
        int i5 = parcel.readInt();
        if(i5 > 10000)
            throw new ParcelFormatException((new StringBuilder()).append("File corrupt: too many uids ").append(i5).toString());
        for(int k1 = 0; k1 < i5; k1++)
        {
            int i2 = parcel.readInt();
            Uid uid = new Uid(this, i2);
            mUidStats.put(i2, uid);
            uid.mOnBatteryBackgroundTimeBase.readSummaryFromParcel(parcel);
            uid.mOnBatteryScreenOffBackgroundTimeBase.readSummaryFromParcel(parcel);
            uid.mWifiRunning = false;
            if(parcel.readInt() != 0)
                uid.mWifiRunningTimer.readSummaryFromParcelLocked(parcel);
            uid.mFullWifiLockOut = false;
            if(parcel.readInt() != 0)
                uid.mFullWifiLockTimer.readSummaryFromParcelLocked(parcel);
            uid.mWifiScanStarted = false;
            if(parcel.readInt() != 0)
                uid.mWifiScanTimer.readSummaryFromParcelLocked(parcel);
            uid.mWifiBatchedScanBinStarted = -1;
            for(int j2 = 0; j2 < 5; j2++)
                if(parcel.readInt() != 0)
                {
                    uid.makeWifiBatchedScanBin(j2, null);
                    uid.mWifiBatchedScanTimer[j2].readSummaryFromParcelLocked(parcel);
                }

            uid.mWifiMulticastEnabled = false;
            if(parcel.readInt() != 0)
                uid.mWifiMulticastTimer.readSummaryFromParcelLocked(parcel);
            if(parcel.readInt() != 0)
                uid.createAudioTurnedOnTimerLocked().readSummaryFromParcelLocked(parcel);
            if(parcel.readInt() != 0)
                uid.createVideoTurnedOnTimerLocked().readSummaryFromParcelLocked(parcel);
            if(parcel.readInt() != 0)
                uid.createFlashlightTurnedOnTimerLocked().readSummaryFromParcelLocked(parcel);
            if(parcel.readInt() != 0)
                uid.createCameraTurnedOnTimerLocked().readSummaryFromParcelLocked(parcel);
            if(parcel.readInt() != 0)
                uid.createForegroundActivityTimerLocked().readSummaryFromParcelLocked(parcel);
            if(parcel.readInt() != 0)
                uid.createForegroundServiceTimerLocked().readSummaryFromParcelLocked(parcel);
            if(parcel.readInt() != 0)
                uid.createAggregatedPartialWakelockTimerLocked().readSummaryFromParcelLocked(parcel);
            if(parcel.readInt() != 0)
                uid.createBluetoothScanTimerLocked().readSummaryFromParcelLocked(parcel);
            if(parcel.readInt() != 0)
                uid.createBluetoothUnoptimizedScanTimerLocked().readSummaryFromParcelLocked(parcel);
            if(parcel.readInt() != 0)
                uid.createBluetoothScanResultCounterLocked().readSummaryFromParcelLocked(parcel);
            if(parcel.readInt() != 0)
                uid.createBluetoothScanResultBgCounterLocked().readSummaryFromParcelLocked(parcel);
            uid.mProcessState = 18;
            for(int k2 = 0; k2 < 6; k2++)
                if(parcel.readInt() != 0)
                {
                    uid.makeProcessState(k2, null);
                    uid.mProcessStateTimer[k2].readSummaryFromParcelLocked(parcel);
                }

            if(parcel.readInt() != 0)
                uid.createVibratorOnTimerLocked().readSummaryFromParcelLocked(parcel);
            if(parcel.readInt() != 0)
            {
                if(uid.mUserActivityCounters == null)
                    uid.initUserActivityLocked();
                for(int l2 = 0; l2 < 4; l2++)
                    uid.mUserActivityCounters[l2].readSummaryFromParcelLocked(parcel);

            }
            if(parcel.readInt() != 0)
            {
                if(uid.mNetworkByteActivityCounters == null)
                    uid.initNetworkActivityLocked();
                for(int i3 = 0; i3 < 10; i3++)
                {
                    uid.mNetworkByteActivityCounters[i3].readSummaryFromParcelLocked(parcel);
                    uid.mNetworkPacketActivityCounters[i3].readSummaryFromParcelLocked(parcel);
                }

                uid.mMobileRadioActiveTime.readSummaryFromParcelLocked(parcel);
                uid.mMobileRadioActiveCount.readSummaryFromParcelLocked(parcel);
            }
            uid.mUserCpuTime.readSummaryFromParcelLocked(parcel);
            uid.mSystemCpuTime.readSummaryFromParcelLocked(parcel);
            if(parcel.readInt() != 0)
            {
                int j5 = parcel.readInt();
                if(mPowerProfile != null && mPowerProfile.getNumCpuClusters() != j5)
                    throw new ParcelFormatException("Incompatible cpu cluster arrangement");
                uid.mCpuClusterSpeedTimesUs = new LongSamplingCounter[j5][];
                for(int j3 = 0; j3 < j5; j3++)
                    if(parcel.readInt() != 0)
                    {
                        int j6 = parcel.readInt();
                        if(mPowerProfile != null && mPowerProfile.getNumSpeedStepsInCpuCluster(j3) != j6)
                            throw new ParcelFormatException((new StringBuilder()).append("File corrupt: too many speed bins ").append(j6).toString());
                        uid.mCpuClusterSpeedTimesUs[j3] = new LongSamplingCounter[j6];
                        for(int l6 = 0; l6 < j6; l6++)
                            if(parcel.readInt() != 0)
                            {
                                uid.mCpuClusterSpeedTimesUs[j3][l6] = new LongSamplingCounter(mOnBatteryTimeBase);
                                uid.mCpuClusterSpeedTimesUs[j3][l6].readSummaryFromParcelLocked(parcel);
                            }

                    } else
                    {
                        uid.mCpuClusterSpeedTimesUs[j3] = null;
                    }

            } else
            {
                uid.mCpuClusterSpeedTimesUs = null;
            }
            uid.mCpuFreqTimeMs = LongSamplingCounterArray.readSummaryFromParcelLocked(parcel, mOnBatteryTimeBase);
            uid.mScreenOffCpuFreqTimeMs = LongSamplingCounterArray.readSummaryFromParcelLocked(parcel, mOnBatteryScreenOffTimeBase);
            int i7;
            if(parcel.readInt() != 0)
            {
                Uid._2D_set0(uid, new LongSamplingCounter(mOnBatteryTimeBase));
                Uid._2D_get0(uid).readSummaryFromParcelLocked(parcel);
            } else
            {
                Uid._2D_set0(uid, null);
            }
            if(parcel.readInt() != 0)
            {
                Uid._2D_set1(uid, new LongSamplingCounter(mOnBatteryTimeBase));
                Uid._2D_get1(uid).readSummaryFromParcelLocked(parcel);
            } else
            {
                Uid._2D_set1(uid, null);
            }
            i7 = parcel.readInt();
            if(i7 > MAX_WAKELOCKS_PER_UID + 1)
                throw new ParcelFormatException((new StringBuilder()).append("File corrupt: too many wake locks ").append(i7).toString());
            for(int k3 = 0; k3 < i7; k3++)
                uid.readWakeSummaryFromParcelLocked(parcel.readString(), parcel);

            i7 = parcel.readInt();
            if(i7 > MAX_WAKELOCKS_PER_UID + 1)
                throw new ParcelFormatException((new StringBuilder()).append("File corrupt: too many syncs ").append(i7).toString());
            for(int l3 = 0; l3 < i7; l3++)
                uid.readSyncSummaryFromParcelLocked(parcel.readString(), parcel);

            i7 = parcel.readInt();
            if(i7 > MAX_WAKELOCKS_PER_UID + 1)
                throw new ParcelFormatException((new StringBuilder()).append("File corrupt: too many job timers ").append(i7).toString());
            for(int i4 = 0; i4 < i7; i4++)
                uid.readJobSummaryFromParcelLocked(parcel.readString(), parcel);

            uid.readJobCompletionsFromParcelLocked(parcel);
            i7 = parcel.readInt();
            if(i7 > 1000)
                throw new ParcelFormatException((new StringBuilder()).append("File corrupt: too many sensors ").append(i7).toString());
            for(int j4 = 0; j4 < i7; j4++)
            {
                int k5 = parcel.readInt();
                if(parcel.readInt() != 0)
                    uid.getSensorTimerLocked(k5, true).readSummaryFromParcelLocked(parcel);
            }

            i7 = parcel.readInt();
            if(i7 > 1000)
                throw new ParcelFormatException((new StringBuilder()).append("File corrupt: too many processes ").append(i7).toString());
            for(int k4 = 0; k4 < i7; k4++)
            {
                Uid.Proc proc = uid.getProcessStatsLocked(parcel.readString());
                long l7 = parcel.readLong();
                proc.mLoadedUserTime = l7;
                proc.mUserTime = l7;
                l7 = parcel.readLong();
                proc.mLoadedSystemTime = l7;
                proc.mSystemTime = l7;
                l7 = parcel.readLong();
                proc.mLoadedForegroundTime = l7;
                proc.mForegroundTime = l7;
                int l5 = parcel.readInt();
                proc.mLoadedStarts = l5;
                proc.mStarts = l5;
                l5 = parcel.readInt();
                proc.mLoadedNumCrashes = l5;
                proc.mNumCrashes = l5;
                l5 = parcel.readInt();
                proc.mLoadedNumAnrs = l5;
                proc.mNumAnrs = l5;
                proc.readExcessivePowerFromParcelLocked(parcel);
            }

            int i6 = parcel.readInt();
            if(i6 > 10000)
                throw new ParcelFormatException((new StringBuilder()).append("File corrupt: too many packages ").append(i6).toString());
            for(int l4 = 0; l4 < i6; l4++)
            {
                String s = parcel.readString();
                Uid.Pkg pkg = uid.getPackageStatsLocked(s);
                int k6 = parcel.readInt();
                if(k6 > 1000)
                    throw new ParcelFormatException((new StringBuilder()).append("File corrupt: too many wakeup alarms ").append(k6).toString());
                pkg.mWakeupAlarms.clear();
                for(int j7 = 0; j7 < k6; j7++)
                {
                    String s1 = parcel.readString();
                    Counter counter = new Counter(mOnBatteryScreenOffTimeBase);
                    counter.readSummaryFromParcelLocked(parcel);
                    pkg.mWakeupAlarms.put(s1, counter);
                }

                k6 = parcel.readInt();
                if(k6 > 1000)
                    throw new ParcelFormatException((new StringBuilder()).append("File corrupt: too many services ").append(k6).toString());
                for(int k7 = 0; k7 < k6; k7++)
                {
                    Uid.Pkg.Serv serv = uid.getServiceStatsLocked(s, parcel.readString());
                    long l8 = parcel.readLong();
                    serv.mLoadedStartTime = l8;
                    serv.mStartTime = l8;
                    int i8 = parcel.readInt();
                    serv.mLoadedStarts = i8;
                    serv.mStarts = i8;
                    i8 = parcel.readInt();
                    serv.mLoadedLaunches = i8;
                    serv.mLaunches = i8;
                }

            }

        }

    }

    public void recordDailyStatsIfNeededLocked(boolean flag)
    {
        long l = System.currentTimeMillis();
        if(l < mNextMaxDailyDeadline) goto _L2; else goto _L1
_L1:
        recordDailyStatsLocked();
_L4:
        return;
_L2:
        if(flag && l >= mNextMinDailyDeadline)
            recordDailyStatsLocked();
        else
        if(l < mDailyStartTime - 0x5265c00L)
            recordDailyStatsLocked();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void recordDailyStatsLocked()
    {
        Object obj;
        obj = new android.os.BatteryStats.DailyItem();
        obj.mStartTime = mDailyStartTime;
        obj.mEndTime = System.currentTimeMillis();
        boolean flag = false;
        if(mDailyDischargeStepTracker.mNumStepDurations > 0)
        {
            flag = true;
            obj.mDischargeSteps = new android.os.BatteryStats.LevelStepTracker(mDailyDischargeStepTracker.mNumStepDurations, mDailyDischargeStepTracker.mStepDurations);
        }
        if(mDailyChargeStepTracker.mNumStepDurations > 0)
        {
            flag = true;
            obj.mChargeSteps = new android.os.BatteryStats.LevelStepTracker(mDailyChargeStepTracker.mNumStepDurations, mDailyChargeStepTracker.mStepDurations);
        }
        if(mDailyPackageChanges != null)
        {
            flag = true;
            obj.mPackageChanges = mDailyPackageChanges;
            mDailyPackageChanges = null;
        }
        mDailyDischargeStepTracker.init();
        mDailyChargeStepTracker.init();
        updateDailyDeadlineLocked();
        if(!flag)
            break MISSING_BLOCK_LABEL_233;
        mDailyItems.add(obj);
        for(; mDailyItems.size() > 10; mDailyItems.remove(0));
        obj = new ByteArrayOutputStream();
        Object obj1 = JVM INSTR new #3085 <Class FastXmlSerializer>;
        ((FastXmlSerializer) (obj1)).FastXmlSerializer();
        ((XmlSerializer) (obj1)).setOutput(((java.io.OutputStream) (obj)), StandardCharsets.UTF_8.name());
        writeDailyItemsLocked(((XmlSerializer) (obj1)));
        obj1 = BackgroundThread.getHandler();
        Runnable runnable = JVM INSTR new #8   <Class BatteryStatsImpl$2>;
        runnable.this. _cls2();
        ((Handler) (obj1)).post(runnable);
_L2:
        return;
        IOException ioexception;
        ioexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void removeIsolatedUidLocked(int i)
    {
        mIsolatedUids.delete(i);
        mKernelUidCpuTimeReader.removeUid(i);
        mKernelUidCpuFreqTimeReader.removeUid(i);
    }

    public void removeUidStatsLocked(int i)
    {
        mKernelUidCpuTimeReader.removeUid(i);
        mKernelUidCpuFreqTimeReader.removeUid(i);
        mUidStats.remove(i);
    }

    public void reportExcessiveCpuLocked(int i, String s, long l, long l1)
    {
        i = mapUid(i);
        Uid uid = (Uid)mUidStats.get(i);
        if(uid != null)
            uid.reportExcessiveCpuLocked(s, l, l1);
    }

    public void resetAllStatsCmdLocked()
    {
        resetAllStatsLocked();
        long l = mClocks.uptimeMillis();
        long l1 = l * 1000L;
        long l2 = mClocks.elapsedRealtime();
        long l3 = l2 * 1000L;
        mDischargeStartLevel = mHistoryCur.batteryLevel;
        pullPendingStateUpdatesLocked();
        addHistoryRecordLocked(l2, l);
        int i = mHistoryCur.batteryLevel;
        mCurrentBatteryLevel = i;
        mDischargePlugLevel = i;
        mDischargeUnplugLevel = i;
        mDischargeCurrentLevel = i;
        mOnBatteryTimeBase.reset(l1, l3);
        mOnBatteryScreenOffTimeBase.reset(l1, l3);
        if((mHistoryCur.states & 0x80000) == 0)
        {
            if(isScreenOn(mScreenState))
            {
                mDischargeScreenOnUnplugLevel = mHistoryCur.batteryLevel;
                mDischargeScreenDozeUnplugLevel = 0;
                mDischargeScreenOffUnplugLevel = 0;
            } else
            if(isScreenDoze(mScreenState))
            {
                mDischargeScreenOnUnplugLevel = 0;
                mDischargeScreenDozeUnplugLevel = mHistoryCur.batteryLevel;
                mDischargeScreenOffUnplugLevel = 0;
            } else
            {
                mDischargeScreenOnUnplugLevel = 0;
                mDischargeScreenDozeUnplugLevel = 0;
                mDischargeScreenOffUnplugLevel = mHistoryCur.batteryLevel;
            }
            mDischargeAmountScreenOn = 0;
            mDischargeAmountScreenOff = 0;
            mDischargeAmountScreenDoze = 0;
        }
        initActiveHistoryEventsLocked(l2, l);
    }

    public void scheduleRemoveIsolatedUidLocked(int i, int j)
    {
        if(mIsolatedUids.get(i, -1) == j && mExternalSync != null)
            mExternalSync.scheduleCpuSyncDueToRemovedUid(i);
    }

    public void setBatteryStateLocked(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1)
    {
        int i2 = Math.max(0, i1);
        boolean flag;
        long l2;
        long l3;
        if(k == 0)
            flag = true;
        else
            flag = false;
        l2 = mClocks.uptimeMillis();
        l3 = mClocks.elapsedRealtime();
        if(!mHaveBatteryLevel)
        {
            mHaveBatteryLevel = true;
            android.os.BatteryStats.HistoryItem historyitem1;
            long l4;
            if(flag == mOnBattery)
                if(flag)
                {
                    android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
                    historyitem.states = historyitem.states & 0xfff7ffff;
                } else
                {
                    android.os.BatteryStats.HistoryItem historyitem2 = mHistoryCur;
                    historyitem2.states = historyitem2.states | 0x80000;
                }
            historyitem1 = mHistoryCur;
            historyitem1.states2 = historyitem1.states2 | 0x1000000;
            mHistoryCur.batteryStatus = (byte)i;
            mHistoryCur.batteryLevel = (byte)l;
            mHistoryCur.batteryChargeUAh = k1;
            mLastDischargeStepLevel = l;
            mLastChargeStepLevel = l;
            mMinDischargeStepLevel = l;
            mMaxChargeStepLevel = l;
            mLastChargingStateLevel = l;
        } else
        if(mCurrentBatteryLevel != l || mOnBattery != flag)
        {
            boolean flag1;
            if(l >= 100)
                flag1 = flag;
            else
                flag1 = false;
            recordDailyStatsIfNeededLocked(flag1);
        }
        i1 = mHistoryCur.batteryStatus;
        if(flag)
        {
            mDischargeCurrentLevel = l;
            if(!mRecordingHistory)
            {
                mRecordingHistory = true;
                startRecordingHistory(l3, l2, true);
            }
        } else
        if(l < 96 && !mRecordingHistory)
        {
            mRecordingHistory = true;
            startRecordingHistory(l3, l2, true);
        }
        mCurrentBatteryLevel = l;
        if(mDischargePlugLevel < 0)
            mDischargePlugLevel = l;
        if(flag != mOnBattery)
        {
            mHistoryCur.batteryLevel = (byte)l;
            mHistoryCur.batteryStatus = (byte)i;
            mHistoryCur.batteryHealth = (byte)j;
            mHistoryCur.batteryPlugType = (byte)k;
            mHistoryCur.batteryTemperature = (short)i2;
            mHistoryCur.batteryVoltage = (char)j1;
            if(k1 < mHistoryCur.batteryChargeUAh)
            {
                l4 = mHistoryCur.batteryChargeUAh - k1;
                mDischargeCounter.addCountLocked(l4);
                mDischargeScreenOffCounter.addCountLocked(l4);
                if(isScreenDoze(mScreenState))
                    mDischargeScreenDozeCounter.addCountLocked(l4);
            }
            mHistoryCur.batteryChargeUAh = k1;
            setOnBatteryLocked(l3, l2, flag, i1, l, k1);
        } else
        {
label0:
            {
                i1 = 0;
                if(mHistoryCur.batteryLevel != l)
                {
                    mHistoryCur.batteryLevel = (byte)l;
                    i1 = 1;
                    scheduleSyncExternalStatsLocked("battery-level", 31);
                }
                if(mHistoryCur.batteryStatus != i)
                {
                    mHistoryCur.batteryStatus = (byte)i;
                    i1 = 1;
                }
                if(mHistoryCur.batteryHealth != j)
                {
                    mHistoryCur.batteryHealth = (byte)j;
                    i1 = 1;
                }
                if(mHistoryCur.batteryPlugType != k)
                {
                    mHistoryCur.batteryPlugType = (byte)k;
                    i1 = 1;
                }
                if(i2 >= mHistoryCur.batteryTemperature + 10 || i2 <= mHistoryCur.batteryTemperature - 10)
                {
                    mHistoryCur.batteryTemperature = (short)i2;
                    i1 = 1;
                }
                if(j1 <= mHistoryCur.batteryVoltage + 20)
                {
                    j = i1;
                    if(j1 >= mHistoryCur.batteryVoltage - 20)
                        break label0;
                }
                mHistoryCur.batteryVoltage = (char)j1;
                j = 1;
            }
            if(k1 >= mHistoryCur.batteryChargeUAh + 10 || k1 <= mHistoryCur.batteryChargeUAh - 10)
            {
                if(k1 < mHistoryCur.batteryChargeUAh)
                {
                    long l5 = mHistoryCur.batteryChargeUAh - k1;
                    mDischargeCounter.addCountLocked(l5);
                    mDischargeScreenOffCounter.addCountLocked(l5);
                    if(isScreenDoze(mScreenState))
                        mDischargeScreenDozeCounter.addCountLocked(l5);
                }
                mHistoryCur.batteryChargeUAh = k1;
                j = 1;
            }
            long l6 = (long)mInitStepMode << 48 | (long)mModStepMode << 56 | (long)(l & 0xff) << 40;
            if(flag)
            {
                j |= setChargingLocked(false);
                k = j;
                if(mLastDischargeStepLevel != l)
                {
                    k = j;
                    if(mMinDischargeStepLevel > l)
                    {
                        mDischargeStepTracker.addLevelSteps(mLastDischargeStepLevel - l, l6, l3);
                        mDailyDischargeStepTracker.addLevelSteps(mLastDischargeStepLevel - l, l6, l3);
                        mLastDischargeStepLevel = l;
                        mMinDischargeStepLevel = l;
                        mInitStepMode = mCurStepMode;
                        mModStepMode = 0;
                        k = j;
                    }
                }
            } else
            {
                k = j;
                if(l >= 90)
                {
                    k = j | setChargingLocked(true);
                    mLastChargeStepLevel = l;
                }
                if(!mCharging)
                {
                    j = k;
                    if(mLastChargeStepLevel < l)
                    {
                        j = k | setChargingLocked(true);
                        mLastChargeStepLevel = l;
                    }
                } else
                {
                    j = k;
                    if(mLastChargeStepLevel > l)
                    {
                        j = k | setChargingLocked(false);
                        mLastChargeStepLevel = l;
                    }
                }
                k = j;
                if(mLastChargeStepLevel != l)
                {
                    k = j;
                    if(mMaxChargeStepLevel < l)
                    {
                        mChargeStepTracker.addLevelSteps(l - mLastChargeStepLevel, l6, l3);
                        mDailyChargeStepTracker.addLevelSteps(l - mLastChargeStepLevel, l6, l3);
                        mLastChargeStepLevel = l;
                        mMaxChargeStepLevel = l;
                        mInitStepMode = mCurStepMode;
                        mModStepMode = 0;
                        k = j;
                    }
                }
            }
            if(k != 0)
                addHistoryRecordLocked(l3, l2);
        }
        if(!flag && i == 5)
            mRecordingHistory = false;
        if(mMinLearnedBatteryCapacity == -1)
            mMinLearnedBatteryCapacity = l1;
        else
            Math.min(mMinLearnedBatteryCapacity, l1);
        mMaxLearnedBatteryCapacity = Math.max(mMaxLearnedBatteryCapacity, l1);
    }

    public void setCallback(BatteryCallback batterycallback)
    {
        mCallback = batterycallback;
    }

    boolean setChargingLocked(boolean flag)
    {
        if(mCharging != flag)
        {
            mCharging = flag;
            if(flag)
            {
                android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
                historyitem.states2 = historyitem.states2 | 0x1000000;
            } else
            {
                android.os.BatteryStats.HistoryItem historyitem1 = mHistoryCur;
                historyitem1.states2 = historyitem1.states2 & 0xfeffffff;
            }
            mHandler.sendEmptyMessage(3);
            return true;
        } else
        {
            return false;
        }
    }

    public void setExternalStatsSyncLocked(ExternalStatsSync externalstatssync)
    {
        mExternalSync = externalstatssync;
    }

    public void setNoAutoReset(boolean flag)
    {
        mNoAutoReset = flag;
    }

    protected void setOnBatteryLocked(long l, long l1, boolean flag, int i, int j, 
            int k)
    {
        boolean flag1;
        boolean flag2;
        int i1;
        long l2;
        long l3;
        int j1;
        boolean flag3;
        flag1 = false;
        flag2 = false;
        final Parcel parcel = mHandler.obtainMessage(2);
        if(flag)
            i1 = 1;
        else
            i1 = 0;
        parcel.arg1 = i1;
        mHandler.sendMessage(parcel);
        l2 = l1 * 1000L;
        l3 = l * 1000L;
        j1 = mScreenState;
        if(!flag) goto _L2; else goto _L1
_L1:
        flag3 = false;
        i1 = ((flag2) ? 1 : 0);
        flag = flag3;
        if(mNoAutoReset) goto _L4; else goto _L3
_L6:
        Slog.i("BatteryStatsImpl", (new StringBuilder()).append("Resetting battery stats: level=").append(j).append(" status=").append(i).append(" dischargeLevel=").append(mDischargeCurrentLevel).append(" lowAmount=").append(getLowDischargeAmountSinceCharge()).append(" highAmount=").append(getHighDischargeAmountSinceCharge()).toString());
        if(getLowDischargeAmountSinceCharge() >= 20)
        {
            parcel = Parcel.obtain();
            writeSummaryToParcel(parcel, true);
            BackgroundThread.getHandler().post(new Runnable() {

                public void run()
                {
                    AtomicFile atomicfile = mCheckinFile;
                    atomicfile;
                    JVM INSTR monitorenter ;
                    FileOutputStream fileoutputstream = null;
                    FileOutputStream fileoutputstream1 = mCheckinFile.startWrite();
                    fileoutputstream = fileoutputstream1;
                    fileoutputstream1.write(parcel.marshall());
                    fileoutputstream = fileoutputstream1;
                    fileoutputstream1.flush();
                    fileoutputstream = fileoutputstream1;
                    FileUtils.sync(fileoutputstream1);
                    fileoutputstream = fileoutputstream1;
                    fileoutputstream1.close();
                    fileoutputstream = fileoutputstream1;
                    mCheckinFile.finishWrite(fileoutputstream1);
                    parcel.recycle();
_L1:
                    atomicfile;
                    JVM INSTR monitorexit ;
                    return;
                    IOException ioexception;
                    ioexception;
                    Slog.w("BatteryStats", "Error writing checkin battery statistics", ioexception);
                    mCheckinFile.failWrite(fileoutputstream);
                    parcel.recycle();
                      goto _L1
                    Exception exception;
                    exception;
                    throw exception;
                    exception;
                    parcel.recycle();
                    throw exception;
                }

                final BatteryStatsImpl this$0;
                final Parcel val$parcel;

            
            {
                this$0 = BatteryStatsImpl.this;
                parcel = parcel1;
                super();
            }
            }
);
        }
        i1 = 1;
        resetAllStatsLocked();
        if(k > 0 && j > 0)
            mEstimatedBatteryCapacity = (int)((double)(k / 1000) / ((double)j / 100D));
        mDischargeStartLevel = j;
        flag = true;
        mDischargeStepTracker.init();
_L4:
        if(mCharging)
            setChargingLocked(false);
        mLastChargingStateLevel = j;
        mOnBatteryInternal = true;
        mOnBattery = true;
        mLastDischargeStepLevel = j;
        mMinDischargeStepLevel = j;
        mDischargeStepTracker.clearTime();
        mDailyDischargeStepTracker.clearTime();
        mInitStepMode = mCurStepMode;
        mModStepMode = 0;
        pullPendingStateUpdatesLocked();
        mHistoryCur.batteryLevel = (byte)j;
        parcel = mHistoryCur;
        parcel.states = ((android.os.BatteryStats.HistoryItem) (parcel)).states & 0xfff7ffff;
        if(flag)
        {
            mRecordingHistory = true;
            startRecordingHistory(l, l1, flag);
        }
        addHistoryRecordLocked(l, l1);
        mDischargeUnplugLevel = j;
        mDischargeCurrentLevel = j;
        if(isScreenOn(j1))
        {
            mDischargeScreenOnUnplugLevel = j;
            mDischargeScreenDozeUnplugLevel = 0;
            mDischargeScreenOffUnplugLevel = 0;
        } else
        if(isScreenDoze(j1))
        {
            mDischargeScreenOnUnplugLevel = 0;
            mDischargeScreenDozeUnplugLevel = j;
            mDischargeScreenOffUnplugLevel = 0;
        } else
        {
            mDischargeScreenOnUnplugLevel = 0;
            mDischargeScreenDozeUnplugLevel = 0;
            mDischargeScreenOffUnplugLevel = j;
        }
        mDischargeAmountScreenOn = 0;
        mDischargeAmountScreenDoze = 0;
        mDischargeAmountScreenOff = 0;
        updateTimeBasesLocked(true, j1, l2, l3);
_L8:
        if((i1 != 0 || mLastWriteTime + 60000L < l) && mFile != null)
            writeAsyncLocked();
        return;
_L3:
        if(i == 5 || j >= 90 || mDischargeCurrentLevel < 20 && j >= 80) goto _L6; else goto _L5
_L5:
        i1 = ((flag2) ? 1 : 0);
        flag = flag3;
        if(getHighDischargeAmountSinceCharge() < 200) goto _L4; else goto _L7
_L7:
        i1 = ((flag2) ? 1 : 0);
        flag = flag3;
        if(mHistoryBuffer.dataSize() < MAX_HISTORY_BUFFER) goto _L4; else goto _L6
_L2:
        mLastChargingStateLevel = j;
        mOnBatteryInternal = false;
        mOnBattery = false;
        pullPendingStateUpdatesLocked();
        mHistoryCur.batteryLevel = (byte)j;
        android.os.BatteryStats.HistoryItem historyitem = mHistoryCur;
        historyitem.states = historyitem.states | 0x80000;
        addHistoryRecordLocked(l, l1);
        mDischargePlugLevel = j;
        mDischargeCurrentLevel = j;
        if(j < mDischargeUnplugLevel)
        {
            mLowDischargeAmountSinceCharge = mLowDischargeAmountSinceCharge + (mDischargeUnplugLevel - j - 1);
            mHighDischargeAmountSinceCharge = mHighDischargeAmountSinceCharge + (mDischargeUnplugLevel - j);
        }
        updateDischargeScreenLevelsLocked(j1, j1);
        updateTimeBasesLocked(false, j1, l2, l3);
        mChargeStepTracker.init();
        mLastChargeStepLevel = j;
        mMaxChargeStepLevel = j;
        mInitStepMode = mCurStepMode;
        mModStepMode = 0;
        i1 = ((flag1) ? 1 : 0);
          goto _L8
    }

    public void setPowerProfileLocked(PowerProfile powerprofile)
    {
        mPowerProfile = powerprofile;
        int i = mPowerProfile.getNumCpuClusters();
        mKernelCpuSpeedReaders = new KernelCpuSpeedReader[i];
        int j = 0;
        for(int k = 0; k < i; k++)
        {
            int l = mPowerProfile.getNumSpeedStepsInCpuCluster(k);
            mKernelCpuSpeedReaders[k] = new KernelCpuSpeedReader(j, l);
            j += mPowerProfile.getNumCoresInCpuCluster(k);
        }

        if(mEstimatedBatteryCapacity == -1)
            mEstimatedBatteryCapacity = (int)mPowerProfile.getBatteryCapacity();
    }

    public void setPretendScreenOff(boolean flag)
    {
        if(mPretendScreenOff != flag)
        {
            mPretendScreenOff = flag;
            int i;
            if(flag)
                i = 1;
            else
                i = 2;
            noteScreenStateLocked(i);
        }
    }

    public void setRadioScanningTimeoutLocked(long l)
    {
        if(mPhoneSignalScanningTimer != null)
            mPhoneSignalScanningTimer.setTimeout(l);
    }

    public void setRecordAllHistoryLocked(boolean flag)
    {
        mRecordAllHistory = flag;
        if(!flag)
        {
            mActiveEvents.removeEvents(5);
            mActiveEvents.removeEvents(13);
            HashMap hashmap = mActiveEvents.getStateForEvent(1);
            if(hashmap != null)
            {
                long l = mClocks.elapsedRealtime();
                long l2 = mClocks.uptimeMillis();
                for(Iterator iterator = hashmap.entrySet().iterator(); iterator.hasNext();)
                {
                    java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                    SparseIntArray sparseintarray = (SparseIntArray)entry.getValue();
                    int i = 0;
                    while(i < sparseintarray.size()) 
                    {
                        addHistoryEventLocked(l, l2, 16385, (String)entry.getKey(), sparseintarray.keyAt(i));
                        i++;
                    }
                }

            }
        } else
        {
            HashMap hashmap1 = mActiveEvents.getStateForEvent(1);
            if(hashmap1 != null)
            {
                long l3 = mClocks.elapsedRealtime();
                long l1 = mClocks.uptimeMillis();
                for(Iterator iterator1 = hashmap1.entrySet().iterator(); iterator1.hasNext();)
                {
                    java.util.Map.Entry entry1 = (java.util.Map.Entry)iterator1.next();
                    SparseIntArray sparseintarray1 = (SparseIntArray)entry1.getValue();
                    int j = 0;
                    while(j < sparseintarray1.size()) 
                    {
                        addHistoryEventLocked(l3, l1, 32769, (String)entry1.getKey(), sparseintarray1.keyAt(j));
                        j++;
                    }
                }

            }
        }
    }

    public void shutdownLocked()
    {
        recordShutdownLocked(mClocks.elapsedRealtime(), mClocks.uptimeMillis());
        writeSyncLocked();
        mShuttingDown = true;
    }

    public boolean startAddingCpuLocked()
    {
        mHandler.removeMessages(1);
        return mOnBatteryInternal;
    }

    public boolean startIteratingHistoryLocked()
    {
        if(mHistoryBuffer.dataSize() <= 0)
            return false;
        mHistoryBuffer.setDataPosition(0);
        mReadOverflow = false;
        mIteratingHistory = true;
        mReadHistoryStrings = new String[mHistoryTagPool.size()];
        mReadHistoryUids = new int[mHistoryTagPool.size()];
        mReadHistoryChars = 0;
        for(Iterator iterator = mHistoryTagPool.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            android.os.BatteryStats.HistoryTag historytag = (android.os.BatteryStats.HistoryTag)entry.getKey();
            int i = ((Integer)entry.getValue()).intValue();
            mReadHistoryStrings[i] = historytag.string;
            mReadHistoryUids[i] = historytag.uid;
            mReadHistoryChars = mReadHistoryChars + (historytag.string.length() + 1);
        }

        return true;
    }

    public boolean startIteratingOldHistoryLocked()
    {
        android.os.BatteryStats.HistoryItem historyitem = mHistory;
        mHistoryIterator = historyitem;
        if(historyitem == null)
        {
            return false;
        } else
        {
            mHistoryBuffer.setDataPosition(0);
            mHistoryReadTmp.clear();
            mReadOverflow = false;
            mIteratingHistory = true;
            return true;
        }
    }

    void stopAllPhoneSignalStrengthTimersLocked(int i)
    {
        long l = mClocks.elapsedRealtime();
        int j = 0;
        while(j < 6) 
        {
            if(j != i)
                while(mPhoneSignalStrengthsTimer[j].isRunningLocked()) 
                    mPhoneSignalStrengthsTimer[j].stopRunningLocked(l);
            j++;
        }
    }

    void stopAllWifiSignalStrengthTimersLocked(int i)
    {
        long l = mClocks.elapsedRealtime();
        int j = 0;
        while(j < 5) 
        {
            if(j != i)
                while(mWifiSignalStrengthsTimer[j].isRunningLocked()) 
                    mWifiSignalStrengthsTimer[j].stopRunningLocked(l);
            j++;
        }
    }

    public void updateBluetoothStateLocked(BluetoothActivityEnergyInfo bluetoothactivityenergyinfo)
    {
        if(bluetoothactivityenergyinfo == null || mOnBatteryInternal ^ true)
            return;
        mHasBluetoothReporting = true;
        long l = mClocks.elapsedRealtime();
        long l1 = bluetoothactivityenergyinfo.getControllerRxTimeMillis();
        long l2 = bluetoothactivityenergyinfo.getControllerTxTimeMillis();
        long l3 = 0L;
        int i = mUidStats.size();
        int j = 0;
        while(j < i) 
        {
            Uid uid = (Uid)mUidStats.valueAt(j);
            if(uid.mBluetoothScanTimer != null)
                l3 += uid.mBluetoothScanTimer.getTimeSinceMarkLocked(1000L * l) / 1000L;
            j++;
        }
        boolean flag;
        boolean flag1;
        long l4;
        long l6;
        int j1;
        if(l3 > l1)
            flag = true;
        else
            flag = false;
        if(l3 > l2)
            flag1 = true;
        else
            flag1 = false;
        l4 = l1;
        l6 = l2;
        j1 = 0;
        while(j1 < i) 
        {
            Object obj = (Uid)mUidStats.valueAt(j1);
            long l7;
            long l9;
            if(((Uid) (obj)).mBluetoothScanTimer == null)
            {
                l7 = l6;
                l9 = l4;
            } else
            {
                long l11 = ((Uid) (obj)).mBluetoothScanTimer.getTimeSinceMarkLocked(1000L * l) / 1000L;
                l9 = l4;
                l7 = l6;
                if(l11 > 0L)
                {
                    ((Uid) (obj)).mBluetoothScanTimer.setMark(l);
                    l7 = l11;
                    l9 = l11;
                    if(flag)
                        l7 = (l1 * l11) / l3;
                    if(flag1)
                        l9 = (l2 * l11) / l3;
                    obj = ((Uid) (obj)).getOrCreateBluetoothControllerActivityLocked();
                    ((ControllerActivityCounterImpl) (obj)).getRxTimeCounter().addCountLocked(l7);
                    ((ControllerActivityCounterImpl) (obj)).getTxTimeCounters()[0].addCountLocked(l9);
                    l4 -= l7;
                    l7 = l6 - l9;
                    l9 = l4;
                }
            }
            j1++;
            l4 = l9;
            l6 = l7;
        }
        l3 = 0L;
        long l12 = 0L;
        UidTraffic auidtraffic[] = bluetoothactivityenergyinfo.getUidTraffic();
        int k;
        if(auidtraffic != null)
            flag = auidtraffic.length;
        else
            flag = false;
        for(k = 0; k < flag; k++)
        {
            UidTraffic uidtraffic = auidtraffic[k];
            mNetworkByteActivityCounters[4].addCountLocked(uidtraffic.getRxBytes());
            mNetworkByteActivityCounters[5].addCountLocked(uidtraffic.getTxBytes());
            Uid uid1 = getUidStatsLocked(mapUid(uidtraffic.getUid()));
            uid1.noteNetworkActivityLocked(4, uidtraffic.getRxBytes(), 0L);
            uid1.noteNetworkActivityLocked(5, uidtraffic.getTxBytes(), 0L);
            l3 += uidtraffic.getTxBytes();
            l12 += uidtraffic.getRxBytes();
        }

        if((l3 != 0L || l12 != 0L) && (l4 != 0L || l6 != 0L))
        {
            int i1 = 0;
            long l10 = l4;
            while(i1 < flag) 
            {
                UidTraffic uidtraffic1 = auidtraffic[i1];
                ControllerActivityCounterImpl controlleractivitycounterimpl = getUidStatsLocked(mapUid(uidtraffic1.getUid())).getOrCreateBluetoothControllerActivityLocked();
                long l5 = l10;
                if(l12 > 0L)
                {
                    l5 = l10;
                    if(uidtraffic1.getRxBytes() > 0L)
                    {
                        l5 = (uidtraffic1.getRxBytes() * l10) / l12;
                        controlleractivitycounterimpl.getRxTimeCounter().addCountLocked(l5);
                        l5 = l10 - l5;
                    }
                }
                long l8 = l6;
                if(l3 > 0L)
                {
                    l8 = l6;
                    if(uidtraffic1.getTxBytes() > 0L)
                    {
                        l10 = (uidtraffic1.getTxBytes() * l6) / l3;
                        controlleractivitycounterimpl.getTxTimeCounters()[0].addCountLocked(l10);
                        l8 = l6 - l10;
                    }
                }
                i1++;
                l10 = l5;
                l6 = l8;
            }
        }
        mBluetoothActivity.getRxTimeCounter().addCountLocked(bluetoothactivityenergyinfo.getControllerRxTimeMillis());
        mBluetoothActivity.getTxTimeCounters()[0].addCountLocked(bluetoothactivityenergyinfo.getControllerTxTimeMillis());
        mBluetoothActivity.getIdleTimeCounter().addCountLocked(bluetoothactivityenergyinfo.getControllerIdleTimeMillis());
        double d = mPowerProfile.getAveragePower("bluetooth.controller.voltage") / 1000D;
        if(d != 0.0D)
            mBluetoothActivity.getPowerCounter().addCountLocked((long)((double)bluetoothactivityenergyinfo.getControllerEnergyUsed() / d));
    }

    public void updateCpuTimeLocked(boolean flag)
    {
        if(mPowerProfile == null)
            return;
        final int numWakelocksF = 0;
        int i1 = 0;
        int l2 = mPartialTimers.size();
        if(mOnBatteryScreenOffTimeBase.isRunning())
        {
            int i3 = 0;
            do
            {
                numWakelocksF = i1;
                if(i3 >= l2)
                    break;
                StopwatchTimer stopwatchtimer = (StopwatchTimer)mPartialTimers.get(i3);
                numWakelocksF = i1;
                if(stopwatchtimer.mInList)
                {
                    numWakelocksF = i1;
                    if(stopwatchtimer.mUid != null)
                    {
                        numWakelocksF = i1;
                        if(stopwatchtimer.mUid.mUid != 1000)
                            numWakelocksF = i1 + 1;
                    }
                }
                i3++;
                i1 = numWakelocksF;
            } while(true);
        }
        mTempTotalCpuUserTimeUs = 0L;
        mTempTotalCpuSystemTimeUs = 0L;
        final SparseLongArray updatedUids = new SparseLongArray();
        long l3 = mClocks.uptimeMillis();
        UserInfoProvider._2D_wrap1(mUserInfoProvider);
        KernelUidCpuTimeReader kerneluidcputimereader = mKernelUidCpuTimeReader;
        Object obj;
        if(!mOnBatteryInternal)
            obj = null;
        else
            obj = new _cls3();
        kerneluidcputimereader.readDelta(((KernelUidCpuTimeReader.Callback) (obj)));
        if(flag)
            readKernelUidCpuFreqTimesLocked();
        l3 = mClocks.uptimeMillis() - l3;
        if(l3 >= 100L)
            Slog.d("BatteryStatsImpl", (new StringBuilder()).append("Reading cpu stats took ").append(l3).append(" ms").toString());
        if(mOnBatteryInternal && numWakelocksF > 0)
        {
            mTempTotalCpuUserTimeUs = (mTempTotalCpuUserTimeUs * 50L) / 100L;
            mTempTotalCpuSystemTimeUs = (mTempTotalCpuSystemTimeUs * 50L) / 100L;
            class _cls3
                implements KernelUidCpuTimeReader.Callback
            {

                public void onUidCpuTime(int i5, long l7, long l8)
                {
                    i5 = mapUid(i5);
                    if(Process.isIsolated(i5))
                    {
                        BatteryStatsImpl._2D_get3(BatteryStatsImpl.this).removeUid(i5);
                        Slog.d("BatteryStatsImpl", (new StringBuilder()).append("Got readings for an isolated uid with no mapping to owning uid: ").append(i5).toString());
                        return;
                    }
                    if(!UserInfoProvider._2D_wrap0(BatteryStatsImpl._2D_get5(BatteryStatsImpl.this), UserHandle.getUserId(i5)))
                    {
                        Slog.d("BatteryStatsImpl", (new StringBuilder()).append("Got readings for an invalid user's uid ").append(i5).toString());
                        BatteryStatsImpl._2D_get3(BatteryStatsImpl.this).removeUid(i5);
                        return;
                    }
                    Uid uid2 = getUidStatsLocked(i5);
                    BatteryStatsImpl batterystatsimpl = BatteryStatsImpl.this;
                    batterystatsimpl.mTempTotalCpuUserTimeUs = batterystatsimpl.mTempTotalCpuUserTimeUs + l7;
                    batterystatsimpl = BatteryStatsImpl.this;
                    batterystatsimpl.mTempTotalCpuSystemTimeUs = batterystatsimpl.mTempTotalCpuSystemTimeUs + l8;
                    long l9 = l7;
                    long l10 = l8;
                    if(numWakelocksF > 0)
                    {
                        l9 = (l7 * 50L) / 100L;
                        l10 = (l8 * 50L) / 100L;
                    }
                    uid2.mUserCpuTime.addCountLocked(l9);
                    uid2.mSystemCpuTime.addCountLocked(l10);
                    updatedUids.put(uid2.getUid(), l9 + l10);
                }

                final BatteryStatsImpl this$0;
                final int val$numWakelocksF;
                final SparseLongArray val$updatedUids;

            
            {
                this$0 = BatteryStatsImpl.this;
                numWakelocksF = i;
                updatedUids = sparselongarray;
                super();
            }
            }

            for(int j1 = 0; j1 < l2;)
            {
                obj = (StopwatchTimer)mPartialTimers.get(j1);
                int j3 = numWakelocksF;
                if(((StopwatchTimer) (obj)).mInList)
                {
                    j3 = numWakelocksF;
                    if(((StopwatchTimer) (obj)).mUid != null)
                    {
                        j3 = numWakelocksF;
                        if(((StopwatchTimer) (obj)).mUid.mUid != 1000)
                        {
                            j3 = (int)(mTempTotalCpuUserTimeUs / (long)numWakelocksF);
                            int i4 = (int)(mTempTotalCpuSystemTimeUs / (long)numWakelocksF);
                            ((StopwatchTimer) (obj)).mUid.mUserCpuTime.addCountLocked(j3);
                            ((StopwatchTimer) (obj)).mUid.mSystemCpuTime.addCountLocked(i4);
                            int k4 = ((StopwatchTimer) (obj)).mUid.getUid();
                            updatedUids.put(k4, updatedUids.get(k4, 0L) + (long)j3 + (long)i4);
                            ((StopwatchTimer) (obj)).mUid.getProcessStatsLocked("*wakelock*").addCpuTimeLocked(j3 / 1000, i4 / 1000);
                            mTempTotalCpuUserTimeUs = mTempTotalCpuUserTimeUs - (long)j3;
                            mTempTotalCpuSystemTimeUs = mTempTotalCpuSystemTimeUs - (long)i4;
                            j3 = numWakelocksF - 1;
                        }
                    }
                }
                j1++;
                numWakelocksF = j3;
            }

            if(mTempTotalCpuUserTimeUs > 0L || mTempTotalCpuSystemTimeUs > 0L)
            {
                Uid uid = getUidStatsLocked(1000);
                uid.mUserCpuTime.addCountLocked(mTempTotalCpuUserTimeUs);
                uid.mSystemCpuTime.addCountLocked(mTempTotalCpuSystemTimeUs);
                updatedUids.put(1000, updatedUids.get(1000, 0L) + mTempTotalCpuUserTimeUs + mTempTotalCpuSystemTimeUs);
                uid.getProcessStatsLocked("*lost*").addCpuTimeLocked((int)mTempTotalCpuUserTimeUs / 1000, (int)mTempTotalCpuSystemTimeUs / 1000);
            }
        }
        l3 = 0L;
        long al[][] = new long[mKernelCpuSpeedReaders.length][];
        for(int k1 = 0; k1 < mKernelCpuSpeedReaders.length;)
        {
            al[k1] = mKernelCpuSpeedReaders[k1].readDelta();
            long l5 = l3;
            if(al[k1] != null)
            {
                int i = al[k1].length - 1;
                do
                {
                    l5 = l3;
                    if(i < 0)
                        break;
                    l3 += al[k1][i];
                    i--;
                } while(true);
            }
            k1++;
            l3 = l5;
        }

        if(l3 != 0L)
        {
            int l4 = updatedUids.size();
            for(int l1 = 0; l1 < l4; l1++)
            {
                Uid uid1 = getUidStatsLocked(updatedUids.keyAt(l1));
                long l6 = updatedUids.valueAt(l1);
                int j = mPowerProfile.getNumCpuClusters();
                if(uid1.mCpuClusterSpeedTimesUs == null || uid1.mCpuClusterSpeedTimesUs.length != j)
                    uid1.mCpuClusterSpeedTimesUs = new LongSamplingCounter[j][];
                for(int k = 0; k < al.length; k++)
                {
                    int j4 = al[k].length;
                    if(uid1.mCpuClusterSpeedTimesUs[k] == null || j4 != uid1.mCpuClusterSpeedTimesUs[k].length)
                        uid1.mCpuClusterSpeedTimesUs[k] = new LongSamplingCounter[j4];
                    LongSamplingCounter alongsamplingcounter[] = uid1.mCpuClusterSpeedTimesUs[k];
                    for(int k3 = 0; k3 < j4; k3++)
                    {
                        if(alongsamplingcounter[k3] == null)
                            alongsamplingcounter[k3] = new LongSamplingCounter(mOnBatteryTimeBase);
                        alongsamplingcounter[k3].addCountLocked((al[k][k3] * l6) / l3);
                    }

                }

            }

        }
        if(ArrayUtils.referenceEquals(mPartialTimers, mLastPartialTimers))
        {
            for(int i2 = 0; i2 < l2; i2++)
                ((StopwatchTimer)mPartialTimers.get(i2)).mInList = true;

        } else
        {
            int l = mLastPartialTimers.size();
            for(int j2 = 0; j2 < l; j2++)
                ((StopwatchTimer)mLastPartialTimers.get(j2)).mInList = false;

            mLastPartialTimers.clear();
            for(int k2 = 0; k2 < l2; k2++)
            {
                StopwatchTimer stopwatchtimer1 = (StopwatchTimer)mPartialTimers.get(k2);
                stopwatchtimer1.mInList = true;
                mLastPartialTimers.add(stopwatchtimer1);
            }

        }
    }

    public void updateDailyDeadlineLocked()
    {
        long l = System.currentTimeMillis();
        mDailyStartTime = l;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(l);
        calendar.set(6, calendar.get(6) + 1);
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(11, 1);
        mNextMinDailyDeadline = calendar.getTimeInMillis();
        calendar.set(11, 3);
        mNextMaxDailyDeadline = calendar.getTimeInMillis();
    }

    void updateDischargeScreenLevelsLocked(int i, int j)
    {
        updateOldDischargeScreenLevelLocked(i);
        updateNewDischargeScreenLevelLocked(j);
    }

    public void updateKernelMemoryBandwidthLocked()
    {
        mKernelMemoryBandwidthStats.updateStats();
        LongSparseLongArray longsparselongarray = mKernelMemoryBandwidthStats.getBandwidthEntries();
        int i = longsparselongarray.size();
        int j = 0;
        while(j < i) 
        {
            int k = mKernelMemoryStats.indexOfKey(longsparselongarray.keyAt(j));
            SamplingTimer samplingtimer;
            if(k >= 0)
            {
                samplingtimer = (SamplingTimer)mKernelMemoryStats.valueAt(k);
            } else
            {
                samplingtimer = new SamplingTimer(mClocks, mOnBatteryTimeBase);
                mKernelMemoryStats.put(longsparselongarray.keyAt(j), samplingtimer);
            }
            samplingtimer.update(longsparselongarray.valueAt(j), 1);
            j++;
        }
    }

    public void updateKernelWakelocksLocked()
    {
        KernelWakelockStats kernelwakelockstats = mKernelWakelockReader.readKernelWakelockStats(mTmpWakelockStats);
        if(kernelwakelockstats == null)
        {
            Slog.w("BatteryStatsImpl", "Couldn't get kernel wake lock stats");
            return;
        }
        Object obj;
        KernelWakelockStats.Entry entry;
        for(Iterator iterator = kernelwakelockstats.entrySet().iterator(); iterator.hasNext(); ((SamplingTimer) (obj)).setUpdateVersion(entry.mVersion))
        {
            obj = (java.util.Map.Entry)iterator.next();
            String s = (String)((java.util.Map.Entry) (obj)).getKey();
            entry = (KernelWakelockStats.Entry)((java.util.Map.Entry) (obj)).getValue();
            SamplingTimer samplingtimer1 = (SamplingTimer)mKernelWakelockStats.get(s);
            obj = samplingtimer1;
            if(samplingtimer1 == null)
            {
                obj = new SamplingTimer(mClocks, mOnBatteryScreenOffTimeBase);
                mKernelWakelockStats.put(s, obj);
            }
            ((SamplingTimer) (obj)).update(entry.mTotalTime, entry.mCount);
        }

        int i = 0;
        Iterator iterator1 = mKernelWakelockStats.entrySet().iterator();
        do
        {
            if(!iterator1.hasNext())
                break;
            SamplingTimer samplingtimer = (SamplingTimer)((java.util.Map.Entry)iterator1.next()).getValue();
            if(samplingtimer.getUpdateVersion() != kernelwakelockstats.kernelWakelockVersion)
            {
                samplingtimer.endSample();
                i++;
            }
        } while(true);
        if(kernelwakelockstats.isEmpty())
            Slog.wtf("BatteryStatsImpl", "All kernel wakelocks had time of zero");
        if(i == mKernelWakelockStats.size())
            Slog.wtf("BatteryStatsImpl", (new StringBuilder()).append("All kernel wakelocks were set stale. new version=").append(kernelwakelockstats.kernelWakelockVersion).toString());
    }

    public void updateMobileRadioState(ModemActivityInfo modemactivityinfo)
    {
        NetworkStats networkstats = null;
        Object obj = mModemNetworkLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = readNetworkStatsLocked(mModemIfaces);
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_68;
        networkstats = NetworkStats.subtract(((NetworkStats) (obj1)), mLastModemNetworkStats, null, null, (NetworkStats)mNetworkStatsPool.acquire());
        mNetworkStatsPool.release(mLastModemNetworkStats);
        mLastModemNetworkStats = ((NetworkStats) (obj1));
        obj;
        JVM INSTR monitorexit ;
        this;
        JVM INSTR monitorenter ;
        if(mOnBatteryInternal)
            break MISSING_BLOCK_LABEL_102;
        if(networkstats == null)
            break MISSING_BLOCK_LABEL_94;
        mNetworkStatsPool.release(networkstats);
        this;
        JVM INSTR monitorexit ;
        return;
        modemactivityinfo;
        throw modemactivityinfo;
        long l2;
        long l = mClocks.elapsedRealtime();
        l2 = mMobileRadioActivePerAppTimer.getTimeSinceMarkLocked(1000L * l);
        mMobileRadioActivePerAppTimer.setMark(l);
        long l1;
        long l4;
        l4 = 0L;
        l1 = 0L;
        if(networkstats == null)
            break MISSING_BLOCK_LABEL_661;
        int i;
        obj = JVM INSTR new #3457 <Class android.net.NetworkStats$Entry>;
        ((android.net.NetworkStats.Entry) (obj)).android.net.NetworkStats.Entry();
        i = networkstats.size();
        int j = 0;
_L2:
        if(j >= i)
            break MISSING_BLOCK_LABEL_360;
        obj = networkstats.getValues(j, ((android.net.NetworkStats.Entry) (obj)));
        if(((android.net.NetworkStats.Entry) (obj)).rxPackets != 0L || ((android.net.NetworkStats.Entry) (obj)).txPackets != 0L)
            break; /* Loop/switch isn't completed */
_L3:
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        l4 += ((android.net.NetworkStats.Entry) (obj)).rxPackets;
        l1 += ((android.net.NetworkStats.Entry) (obj)).txPackets;
        obj1 = getUidStatsLocked(mapUid(((android.net.NetworkStats.Entry) (obj)).uid));
        ((Uid) (obj1)).noteNetworkActivityLocked(0, ((android.net.NetworkStats.Entry) (obj)).rxBytes, ((android.net.NetworkStats.Entry) (obj)).rxPackets);
        ((Uid) (obj1)).noteNetworkActivityLocked(1, ((android.net.NetworkStats.Entry) (obj)).txBytes, ((android.net.NetworkStats.Entry) (obj)).txPackets);
        if(((android.net.NetworkStats.Entry) (obj)).set == 0)
        {
            ((Uid) (obj1)).noteNetworkActivityLocked(6, ((android.net.NetworkStats.Entry) (obj)).rxBytes, ((android.net.NetworkStats.Entry) (obj)).rxPackets);
            ((Uid) (obj1)).noteNetworkActivityLocked(7, ((android.net.NetworkStats.Entry) (obj)).txBytes, ((android.net.NetworkStats.Entry) (obj)).txPackets);
        }
        mNetworkByteActivityCounters[0].addCountLocked(((android.net.NetworkStats.Entry) (obj)).rxBytes);
        mNetworkByteActivityCounters[1].addCountLocked(((android.net.NetworkStats.Entry) (obj)).txBytes);
        mNetworkPacketActivityCounters[0].addCountLocked(((android.net.NetworkStats.Entry) (obj)).rxPackets);
        mNetworkPacketActivityCounters[1].addCountLocked(((android.net.NetworkStats.Entry) (obj)).txPackets);
          goto _L3
        modemactivityinfo;
        throw modemactivityinfo;
        long l5;
        long l6;
        l5 = l4 + l1;
        l6 = l2;
        if(l5 <= 0L)
            break MISSING_BLOCK_LABEL_626;
        j = 0;
_L5:
        l6 = l2;
        if(j >= i)
            break MISSING_BLOCK_LABEL_626;
        obj = networkstats.getValues(j, ((android.net.NetworkStats.Entry) (obj)));
        if(((android.net.NetworkStats.Entry) (obj)).rxPackets != 0L || ((android.net.NetworkStats.Entry) (obj)).txPackets != 0L)
            break; /* Loop/switch isn't completed */
_L7:
        j++;
        if(true) goto _L5; else goto _L4
_L4:
        long l7;
        obj1 = getUidStatsLocked(mapUid(((android.net.NetworkStats.Entry) (obj)).uid));
        l7 = ((android.net.NetworkStats.Entry) (obj)).rxPackets + ((android.net.NetworkStats.Entry) (obj)).txPackets;
        l6 = (l2 * l7) / l5;
        ((Uid) (obj1)).noteMobileRadioActiveTimeLocked(l6);
        l6 = l2 - l6;
        l7 = l5 - l7;
        l2 = l6;
        l5 = l7;
        if(modemactivityinfo == null) goto _L7; else goto _L6
_L6:
        obj1 = ((Uid) (obj1)).getOrCreateModemControllerActivityLocked();
        if(l4 <= 0L)
            break MISSING_BLOCK_LABEL_540;
        if(((android.net.NetworkStats.Entry) (obj)).rxPackets > 0L)
        {
            l2 = (((android.net.NetworkStats.Entry) (obj)).rxPackets * (long)modemactivityinfo.getRxTimeMillis()) / l4;
            ((ControllerActivityCounterImpl) (obj1)).getRxTimeCounter().addCountLocked(l2);
        }
        l2 = l6;
        l5 = l7;
        if(l1 <= 0L) goto _L7; else goto _L8
_L8:
        l2 = l6;
        l5 = l7;
        if(((android.net.NetworkStats.Entry) (obj)).txPackets <= 0L) goto _L7; else goto _L9
_L9:
        int k = 0;
_L11:
        l2 = l6;
        l5 = l7;
        if(k >= 5) goto _L7; else goto _L10
_L10:
        long l3 = (((android.net.NetworkStats.Entry) (obj)).txPackets * (long)modemactivityinfo.getTxTimeMillis()[k]) / l1;
        ((ControllerActivityCounterImpl) (obj1)).getTxTimeCounters()[k].addCountLocked(l3);
        k++;
          goto _L11
        if(l6 <= 0L)
            break MISSING_BLOCK_LABEL_650;
        mMobileRadioActiveUnknownTime.addCountLocked(l6);
        mMobileRadioActiveUnknownCount.addCountLocked(1L);
        mNetworkStatsPool.release(networkstats);
        if(modemactivityinfo == null)
            break MISSING_BLOCK_LABEL_778;
        mHasModemReporting = true;
        mModemActivity.getIdleTimeCounter().addCountLocked(modemactivityinfo.getIdleTimeMillis());
        mModemActivity.getRxTimeCounter().addCountLocked(modemactivityinfo.getRxTimeMillis());
        j = 0;
_L13:
        if(j >= 5)
            break; /* Loop/switch isn't completed */
        mModemActivity.getTxTimeCounters()[j].addCountLocked(modemactivityinfo.getTxTimeMillis()[j]);
        j++;
        if(true) goto _L13; else goto _L12
_L12:
        double d = mPowerProfile.getAveragePower("modem.controller.voltage") / 1000D;
        if(d == 0.0D)
            break MISSING_BLOCK_LABEL_778;
        mModemActivity.getPowerCounter().addCountLocked((long)((double)modemactivityinfo.getEnergyUsed() / d));
        this;
        JVM INSTR monitorexit ;
    }

    public void updateRpmStatsLocked()
    {
        if(mPlatformIdleStateCallback == null)
            return;
        long l = SystemClock.elapsedRealtime();
        if(l - mLastRpmStatsUpdateTimeMs >= 1000L)
        {
            mPlatformIdleStateCallback.fillLowPowerStats(mTmpRpmStats);
            mLastRpmStatsUpdateTimeMs = l;
        }
        for(Iterator iterator = mTmpRpmStats.mPlatformLowPowerStats.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            String s = (String)entry.getKey();
            long l1 = ((RpmStats.PowerStatePlatformSleepState)entry.getValue()).mTimeMs;
            int i = ((RpmStats.PowerStatePlatformSleepState)entry.getValue()).mCount;
            getRpmTimerLocked(s).update(l1 * 1000L, i);
            Iterator iterator2 = ((RpmStats.PowerStatePlatformSleepState)entry.getValue()).mVoters.entrySet().iterator();
            while(iterator2.hasNext()) 
            {
                java.util.Map.Entry entry1 = (java.util.Map.Entry)iterator2.next();
                String s2 = (new StringBuilder()).append(s).append(".").append((String)entry1.getKey()).toString();
                long l2 = ((RpmStats.PowerStateElement)entry1.getValue()).mTimeMs;
                int j = ((RpmStats.PowerStateElement)entry1.getValue()).mCount;
                getRpmTimerLocked(s2).update(l2 * 1000L, j);
            }
        }

        for(Iterator iterator1 = mTmpRpmStats.mSubsystemLowPowerStats.entrySet().iterator(); iterator1.hasNext();)
        {
            Object obj = (java.util.Map.Entry)iterator1.next();
            String s1 = (String)((java.util.Map.Entry) (obj)).getKey();
            obj = ((RpmStats.PowerStateSubsystem)((java.util.Map.Entry) (obj)).getValue()).mStates.entrySet().iterator();
            while(((Iterator) (obj)).hasNext()) 
            {
                java.util.Map.Entry entry2 = (java.util.Map.Entry)((Iterator) (obj)).next();
                String s3 = (new StringBuilder()).append(s1).append(".").append((String)entry2.getKey()).toString();
                long l3 = ((RpmStats.PowerStateElement)entry2.getValue()).mTimeMs;
                int k = ((RpmStats.PowerStateElement)entry2.getValue()).mCount;
                getRpmTimerLocked(s3).update(l3 * 1000L, k);
            }
        }

    }

    public void updateTimeBasesLocked(boolean flag, int i, long l, long l1)
    {
        boolean flag1;
        int j;
        boolean flag2;
        if(!isScreenOff(i))
            flag1 = isScreenDoze(i);
        else
            flag1 = true;
        if(flag != mOnBatteryTimeBase.isRunning())
            j = 1;
        else
            j = 0;
        if(flag)
            flag2 = flag1;
        else
            flag2 = false;
        if(flag2 != mOnBatteryScreenOffTimeBase.isRunning())
            i = 1;
        else
            i = 0;
        if(i != 0 || j != 0)
        {
            if(i != 0)
            {
                updateKernelWakelocksLocked();
                updateBatteryPropertiesLocked();
            }
            if(j != 0)
                updateRpmStatsLocked();
            updateCpuTimeLocked(true);
            mOnBatteryTimeBase.setRunning(flag, l, l1);
            if(j != 0)
                for(j = mUidStats.size() - 1; j >= 0; j--)
                    ((Uid)mUidStats.valueAt(j)).updateOnBatteryBgTimeBase(l, l1);

            if(i != 0)
            {
                TimeBase timebase = mOnBatteryScreenOffTimeBase;
                if(flag)
                    flag = flag1;
                else
                    flag = false;
                timebase.setRunning(flag, l, l1);
                for(i = mUidStats.size() - 1; i >= 0; i--)
                    ((Uid)mUidStats.valueAt(i)).updateOnBatteryScreenOffBgTimeBase(l, l1);

            }
        }
    }

    public void updateWifiState(WifiActivityEnergyInfo wifiactivityenergyinfo)
    {
        NetworkStats networkstats = null;
        Object obj = mWifiNetworkLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = readNetworkStatsLocked(mWifiIfaces);
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_68;
        networkstats = NetworkStats.subtract(((NetworkStats) (obj1)), mLastWifiNetworkStats, null, null, (NetworkStats)mNetworkStatsPool.acquire());
        mNetworkStatsPool.release(mLastWifiNetworkStats);
        mLastWifiNetworkStats = ((NetworkStats) (obj1));
        obj;
        JVM INSTR monitorexit ;
        this;
        JVM INSTR monitorenter ;
        if(mOnBatteryInternal)
            break MISSING_BLOCK_LABEL_102;
        if(networkstats == null)
            break MISSING_BLOCK_LABEL_94;
        mNetworkStatsPool.release(networkstats);
        this;
        JVM INSTR monitorexit ;
        return;
        wifiactivityenergyinfo;
        throw wifiactivityenergyinfo;
        long l;
        SparseLongArray sparselongarray;
        l = mClocks.elapsedRealtime();
        obj1 = JVM INSTR new #3321 <Class SparseLongArray>;
        ((SparseLongArray) (obj1)).SparseLongArray();
        sparselongarray = JVM INSTR new #3321 <Class SparseLongArray>;
        sparselongarray.SparseLongArray();
        long l1;
        long l2;
        long l4;
        long l5;
        l1 = 0L;
        l2 = 0L;
        l4 = l2;
        l5 = l1;
        if(networkstats == null)
            break MISSING_BLOCK_LABEL_455;
        int i;
        obj = JVM INSTR new #3457 <Class android.net.NetworkStats$Entry>;
        ((android.net.NetworkStats.Entry) (obj)).android.net.NetworkStats.Entry();
        i = networkstats.size();
        int j;
        j = 0;
        l5 = l1;
        l4 = l2;
_L2:
        if(j >= i)
            break MISSING_BLOCK_LABEL_444;
        obj = networkstats.getValues(j, ((android.net.NetworkStats.Entry) (obj)));
        if(((android.net.NetworkStats.Entry) (obj)).rxBytes != 0L || ((android.net.NetworkStats.Entry) (obj)).txBytes != 0L)
            break; /* Loop/switch isn't completed */
        l1 = l5;
_L4:
        j++;
        l5 = l1;
        if(true) goto _L2; else goto _L1
_L1:
        Uid uid3 = getUidStatsLocked(mapUid(((android.net.NetworkStats.Entry) (obj)).uid));
        l2 = l4;
        if(((android.net.NetworkStats.Entry) (obj)).rxBytes != 0L)
        {
            uid3.noteNetworkActivityLocked(2, ((android.net.NetworkStats.Entry) (obj)).rxBytes, ((android.net.NetworkStats.Entry) (obj)).rxPackets);
            if(((android.net.NetworkStats.Entry) (obj)).set == 0)
                uid3.noteNetworkActivityLocked(8, ((android.net.NetworkStats.Entry) (obj)).rxBytes, ((android.net.NetworkStats.Entry) (obj)).rxPackets);
            mNetworkByteActivityCounters[2].addCountLocked(((android.net.NetworkStats.Entry) (obj)).rxBytes);
            mNetworkPacketActivityCounters[2].addCountLocked(((android.net.NetworkStats.Entry) (obj)).rxPackets);
            ((SparseLongArray) (obj1)).put(uid3.getUid(), ((android.net.NetworkStats.Entry) (obj)).rxPackets);
            l2 = l4 + ((android.net.NetworkStats.Entry) (obj)).rxPackets;
        }
        l4 = l2;
        l1 = l5;
        if(((android.net.NetworkStats.Entry) (obj)).txBytes == 0L) goto _L4; else goto _L3
_L3:
        uid3.noteNetworkActivityLocked(3, ((android.net.NetworkStats.Entry) (obj)).txBytes, ((android.net.NetworkStats.Entry) (obj)).txPackets);
        if(((android.net.NetworkStats.Entry) (obj)).set == 0)
            uid3.noteNetworkActivityLocked(9, ((android.net.NetworkStats.Entry) (obj)).txBytes, ((android.net.NetworkStats.Entry) (obj)).txPackets);
        mNetworkByteActivityCounters[3].addCountLocked(((android.net.NetworkStats.Entry) (obj)).txBytes);
        mNetworkPacketActivityCounters[3].addCountLocked(((android.net.NetworkStats.Entry) (obj)).txPackets);
        sparselongarray.put(uid3.getUid(), ((android.net.NetworkStats.Entry) (obj)).txPackets);
        l1 = l5 + ((android.net.NetworkStats.Entry) (obj)).txPackets;
        l4 = l2;
          goto _L4
        mNetworkStatsPool.release(networkstats);
        if(wifiactivityenergyinfo == null)
            break MISSING_BLOCK_LABEL_1006;
        long l7;
        long l8;
        long l9;
        mHasWifiReporting = true;
        l7 = wifiactivityenergyinfo.getControllerTxTimeMillis();
        l8 = wifiactivityenergyinfo.getControllerRxTimeMillis();
        l9 = wifiactivityenergyinfo.getControllerIdleTimeMillis();
        long l10;
        long l11;
        l10 = l8;
        l11 = l7;
        l2 = 0L;
        l1 = 0L;
        i = mUidStats.size();
        j = 0;
_L6:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        Uid uid = (Uid)mUidStats.valueAt(j);
        l1 += uid.mWifiScanTimer.getTimeSinceMarkLocked(1000L * l) / 1000L;
        l2 += uid.mFullWifiLockTimer.getTimeSinceMarkLocked(1000L * l) / 1000L;
        j++;
        if(true) goto _L6; else goto _L5
_L5:
        j = 0;
_L9:
        if(j >= i) goto _L8; else goto _L7
_L7:
        long l12;
        obj = (Uid)mUidStats.valueAt(j);
        l12 = ((Uid) (obj)).mWifiScanTimer.getTimeSinceMarkLocked(1000L * l) / 1000L;
        long l13;
        long l14;
        l13 = l10;
        l14 = l11;
        if(l12 <= 0L)
            break MISSING_BLOCK_LABEL_731;
        ((Uid) (obj)).mWifiScanTimer.setMark(l);
        l14 = l12;
        l13 = l12;
        if(l1 <= l8)
            break MISSING_BLOCK_LABEL_670;
        l14 = (l8 * l12) / l1;
        if(l1 <= l7)
            break MISSING_BLOCK_LABEL_688;
        l13 = (l7 * l12) / l1;
        ControllerActivityCounterImpl controlleractivitycounterimpl = ((Uid) (obj)).getOrCreateWifiControllerActivityLocked();
        controlleractivitycounterimpl.getRxTimeCounter().addCountLocked(l14);
        controlleractivitycounterimpl.getTxTimeCounters()[0].addCountLocked(l13);
        l10 -= l14;
        l14 = l11 - l13;
        l13 = l10;
        l11 = ((Uid) (obj)).mFullWifiLockTimer.getTimeSinceMarkLocked(1000L * l) / 1000L;
        if(l11 <= 0L)
            break MISSING_BLOCK_LABEL_788;
        ((Uid) (obj)).mFullWifiLockTimer.setMark(l);
        l11 = (l11 * l9) / l2;
        ((Uid) (obj)).getOrCreateWifiControllerActivityLocked().getIdleTimeCounter().addCountLocked(l11);
        j++;
        l10 = l13;
        l11 = l14;
          goto _L9
_L8:
        j = 0;
_L11:
        if(j >= sparselongarray.size())
            break; /* Loop/switch isn't completed */
        Uid uid1 = getUidStatsLocked(sparselongarray.keyAt(j));
        long l3 = (sparselongarray.valueAt(j) * l11) / l5;
        uid1.getOrCreateWifiControllerActivityLocked().getTxTimeCounters()[0].addCountLocked(l3);
        j++;
        if(true) goto _L11; else goto _L10
_L10:
        j = 0;
_L13:
        if(j >= ((SparseLongArray) (obj1)).size())
            break; /* Loop/switch isn't completed */
        Uid uid2 = getUidStatsLocked(((SparseLongArray) (obj1)).keyAt(j));
        long l6 = (((SparseLongArray) (obj1)).valueAt(j) * l10) / l4;
        uid2.getOrCreateWifiControllerActivityLocked().getRxTimeCounter().addCountLocked(l6);
        j++;
        if(true) goto _L13; else goto _L12
_L12:
        double d;
        mWifiActivity.getRxTimeCounter().addCountLocked(wifiactivityenergyinfo.getControllerRxTimeMillis());
        mWifiActivity.getTxTimeCounters()[0].addCountLocked(wifiactivityenergyinfo.getControllerTxTimeMillis());
        mWifiActivity.getIdleTimeCounter().addCountLocked(wifiactivityenergyinfo.getControllerIdleTimeMillis());
        d = mPowerProfile.getAveragePower("wifi.controller.voltage") / 1000D;
        if(d == 0.0D)
            break MISSING_BLOCK_LABEL_1006;
        mWifiActivity.getPowerCounter().addCountLocked((long)((double)wifiactivityenergyinfo.getControllerEnergyUsed() / d));
        this;
        JVM INSTR monitorexit ;
        return;
        wifiactivityenergyinfo;
        throw wifiactivityenergyinfo;
    }

    public void writeAsyncLocked()
    {
        writeLocked(false);
    }

    void writeHistory(Parcel parcel, boolean flag, boolean flag1)
    {
        parcel.writeLong(mHistoryBaseTime + mLastHistoryElapsedRealtime);
        if(!flag)
        {
            parcel.writeInt(0);
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(mHistoryTagPool.size());
        android.os.BatteryStats.HistoryTag historytag;
        for(Iterator iterator = mHistoryTagPool.entrySet().iterator(); iterator.hasNext(); parcel.writeInt(historytag.uid))
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            historytag = (android.os.BatteryStats.HistoryTag)entry.getKey();
            parcel.writeInt(((Integer)entry.getValue()).intValue());
            parcel.writeString(historytag.string);
        }

        parcel.writeInt(mHistoryBuffer.dataSize());
        parcel.appendFrom(mHistoryBuffer, 0, mHistoryBuffer.dataSize());
        if(flag1)
            writeOldHistory(parcel);
    }

    public void writeHistoryDelta(Parcel parcel, android.os.BatteryStats.HistoryItem historyitem, android.os.BatteryStats.HistoryItem historyitem1)
    {
        long l;
        boolean flag;
        int k;
label0:
        {
            if(historyitem1 == null || historyitem.cmd != 0)
            {
                parcel.writeInt(0x7fffd);
                historyitem.writeToParcel(parcel, 0);
                return;
            }
            l = historyitem.time - historyitem1.time;
            int i = buildBatteryLevelInt(historyitem1);
            int j = buildStateInt(historyitem1);
            int i1;
            boolean flag1;
            boolean flag2;
            int k1;
            boolean flag3;
            int l1;
            boolean flag4;
            boolean flag5;
            if(l < 0L || l > 0x7fffffffL)
                k = 0x7ffff;
            else
            if(l >= 0x7fffdL)
                k = 0x7fffe;
            else
                k = (int)l;
            i1 = k | historyitem.states & 0xfe000000;
            if(mLastHistoryStepLevel > historyitem.batteryLevel)
                flag1 = true;
            else
                flag1 = false;
            if(!flag1)
            {
                if(mLastHistoryStepDetails == null)
                    flag2 = true;
                else
                    flag2 = false;
            } else
            {
                flag2 = true;
            }
            k1 = buildBatteryLevelInt(historyitem) | flag1;
            if(k1 != i)
                flag3 = true;
            else
                flag3 = false;
            i = i1;
            if(flag3)
                i = i1 | 0x80000;
            l1 = buildStateInt(historyitem);
            if(l1 != j)
                flag4 = true;
            else
                flag4 = false;
            i1 = i;
            if(flag4)
                i1 = i | 0x100000;
            if(historyitem.states2 != historyitem1.states2)
                flag5 = true;
            else
                flag5 = false;
            j = i1;
            if(flag5)
                j = i1 | 0x200000;
            if(historyitem.wakelockTag == null)
            {
                i = j;
                if(historyitem.wakeReasonTag == null)
                    break label0;
            }
            i = j | 0x400000;
        }
        i1 = i;
        if(historyitem.eventCode != 0)
            i1 = i | 0x800000;
        if(historyitem.batteryChargeUAh != historyitem1.batteryChargeUAh)
            flag = true;
        else
            flag = false;
        j = i1;
        if(flag)
            j = i1 | 0x1000000;
        parcel.writeInt(j);
        if(k >= 0x7fffe)
            if(k == 0x7fffe)
                parcel.writeInt((int)l);
            else
                parcel.writeLong(l);
        if(flag3)
            parcel.writeInt(k1);
        if(flag4)
            parcel.writeInt(l1);
        if(flag5)
            parcel.writeInt(historyitem.states2);
        if(historyitem.wakelockTag != null || historyitem.wakeReasonTag != null)
        {
            int j1;
            if(historyitem.wakelockTag != null)
                k = writeHistoryTag(historyitem.wakelockTag);
            else
                k = 65535;
            if(historyitem.wakeReasonTag != null)
                j1 = writeHistoryTag(historyitem.wakeReasonTag);
            else
                j1 = 65535;
            parcel.writeInt(j1 << 16 | k);
        }
        if(historyitem.eventCode != 0)
        {
            k = writeHistoryTag(historyitem.eventTag);
            parcel.writeInt(historyitem.eventCode & 0xffff | k << 16);
        }
        if(flag2)
        {
            if(mPlatformIdleStateCallback != null)
            {
                mCurHistoryStepDetails.statPlatformIdleState = mPlatformIdleStateCallback.getPlatformLowPowerStats();
                mCurHistoryStepDetails.statSubsystemPowerState = mPlatformIdleStateCallback.getSubsystemLowPowerStats();
            }
            computeHistoryStepDetails(mCurHistoryStepDetails, mLastHistoryStepDetails);
            if(flag1)
                mCurHistoryStepDetails.writeToParcel(parcel);
            historyitem.stepDetails = mCurHistoryStepDetails;
            mLastHistoryStepDetails = mCurHistoryStepDetails;
        } else
        {
            historyitem.stepDetails = null;
        }
        if(mLastHistoryStepLevel < historyitem.batteryLevel)
            mLastHistoryStepDetails = null;
        mLastHistoryStepLevel = historyitem.batteryLevel;
        if(flag)
            parcel.writeInt(historyitem.batteryChargeUAh);
    }

    void writeLocked(boolean flag)
    {
        if(mFile == null)
        {
            Slog.w("BatteryStats", "writeLocked: no file associated with this instance");
            return;
        }
        if(mShuttingDown)
            return;
        Parcel parcel = Parcel.obtain();
        writeSummaryToParcel(parcel, true);
        mLastWriteTime = mClocks.elapsedRealtime();
        if(mPendingWrite != null)
            mPendingWrite.recycle();
        mPendingWrite = parcel;
        if(flag)
            commitPendingDataToDisk();
        else
            BackgroundThread.getHandler().post(new Runnable() {

                public void run()
                {
                    commitPendingDataToDisk();
                }

                final BatteryStatsImpl this$0;

            
            {
                this$0 = BatteryStatsImpl.this;
                super();
            }
            }
);
    }

    void writeOldHistory(Parcel parcel)
    {
    }

    public void writeSummaryToParcel(Parcel parcel, boolean flag)
    {
        pullPendingStateUpdatesLocked();
        long l = getStartClockTime();
        long l1 = mClocks.uptimeMillis() * 1000L;
        long l2 = mClocks.elapsedRealtime() * 1000L;
        parcel.writeInt(167);
        writeHistory(parcel, flag, true);
        parcel.writeInt(mStartCount);
        parcel.writeLong(computeUptime(l1, 0));
        parcel.writeLong(computeRealtime(l2, 0));
        parcel.writeLong(l);
        parcel.writeString(mStartPlatformVersion);
        parcel.writeString(mEndPlatformVersion);
        mOnBatteryTimeBase.writeSummaryToParcel(parcel, l1, l2);
        mOnBatteryScreenOffTimeBase.writeSummaryToParcel(parcel, l1, l2);
        parcel.writeInt(mDischargeUnplugLevel);
        parcel.writeInt(mDischargePlugLevel);
        parcel.writeInt(mDischargeCurrentLevel);
        parcel.writeInt(mCurrentBatteryLevel);
        parcel.writeInt(mEstimatedBatteryCapacity);
        parcel.writeInt(mMinLearnedBatteryCapacity);
        parcel.writeInt(mMaxLearnedBatteryCapacity);
        parcel.writeInt(getLowDischargeAmountSinceCharge());
        parcel.writeInt(getHighDischargeAmountSinceCharge());
        parcel.writeInt(getDischargeAmountScreenOnSinceCharge());
        parcel.writeInt(getDischargeAmountScreenOffSinceCharge());
        parcel.writeInt(getDischargeAmountScreenDozeSinceCharge());
        mDischargeStepTracker.writeToParcel(parcel);
        mChargeStepTracker.writeToParcel(parcel);
        mDailyDischargeStepTracker.writeToParcel(parcel);
        mDailyChargeStepTracker.writeToParcel(parcel);
        mDischargeCounter.writeSummaryFromParcelLocked(parcel);
        mDischargeScreenOffCounter.writeSummaryFromParcelLocked(parcel);
        mDischargeScreenDozeCounter.writeSummaryFromParcelLocked(parcel);
        if(mDailyPackageChanges != null)
        {
            int i = mDailyPackageChanges.size();
            parcel.writeInt(i);
            int j1 = 0;
            while(j1 < i) 
            {
                android.os.BatteryStats.PackageChange packagechange = (android.os.BatteryStats.PackageChange)mDailyPackageChanges.get(j1);
                parcel.writeString(packagechange.mPackageName);
                int j4;
                if(packagechange.mUpdate)
                    j4 = 1;
                else
                    j4 = 0;
                parcel.writeInt(j4);
                parcel.writeInt(packagechange.mVersionCode);
                j1++;
            }
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeLong(mDailyStartTime);
        parcel.writeLong(mNextMinDailyDeadline);
        parcel.writeLong(mNextMaxDailyDeadline);
        mScreenOnTimer.writeSummaryFromParcelLocked(parcel, l2);
        mScreenDozeTimer.writeSummaryFromParcelLocked(parcel, l2);
        for(int k1 = 0; k1 < 5; k1++)
            mScreenBrightnessTimer[k1].writeSummaryFromParcelLocked(parcel, l2);

        mInteractiveTimer.writeSummaryFromParcelLocked(parcel, l2);
        mPowerSaveModeEnabledTimer.writeSummaryFromParcelLocked(parcel, l2);
        parcel.writeLong(mLongestLightIdleTime);
        parcel.writeLong(mLongestFullIdleTime);
        mDeviceIdleModeLightTimer.writeSummaryFromParcelLocked(parcel, l2);
        mDeviceIdleModeFullTimer.writeSummaryFromParcelLocked(parcel, l2);
        mDeviceLightIdlingTimer.writeSummaryFromParcelLocked(parcel, l2);
        mDeviceIdlingTimer.writeSummaryFromParcelLocked(parcel, l2);
        mPhoneOnTimer.writeSummaryFromParcelLocked(parcel, l2);
        for(int i2 = 0; i2 < 6; i2++)
            mPhoneSignalStrengthsTimer[i2].writeSummaryFromParcelLocked(parcel, l2);

        mPhoneSignalScanningTimer.writeSummaryFromParcelLocked(parcel, l2);
        for(int j2 = 0; j2 < 17; j2++)
            mPhoneDataConnectionsTimer[j2].writeSummaryFromParcelLocked(parcel, l2);

        for(int k2 = 0; k2 < 10; k2++)
        {
            mNetworkByteActivityCounters[k2].writeSummaryFromParcelLocked(parcel);
            mNetworkPacketActivityCounters[k2].writeSummaryFromParcelLocked(parcel);
        }

        mMobileRadioActiveTimer.writeSummaryFromParcelLocked(parcel, l2);
        mMobileRadioActivePerAppTimer.writeSummaryFromParcelLocked(parcel, l2);
        mMobileRadioActiveAdjustedTime.writeSummaryFromParcelLocked(parcel);
        mMobileRadioActiveUnknownTime.writeSummaryFromParcelLocked(parcel);
        mMobileRadioActiveUnknownCount.writeSummaryFromParcelLocked(parcel);
        mWifiOnTimer.writeSummaryFromParcelLocked(parcel, l2);
        mGlobalWifiRunningTimer.writeSummaryFromParcelLocked(parcel, l2);
        for(int i3 = 0; i3 < 8; i3++)
            mWifiStateTimer[i3].writeSummaryFromParcelLocked(parcel, l2);

        for(int j3 = 0; j3 < 13; j3++)
            mWifiSupplStateTimer[j3].writeSummaryFromParcelLocked(parcel, l2);

        for(int k3 = 0; k3 < 5; k3++)
            mWifiSignalStrengthsTimer[k3].writeSummaryFromParcelLocked(parcel, l2);

        mWifiActivity.writeSummaryToParcel(parcel);
        mBluetoothActivity.writeSummaryToParcel(parcel);
        mModemActivity.writeSummaryToParcel(parcel);
        int l3;
        if(mHasWifiReporting)
            l3 = 1;
        else
            l3 = 0;
        parcel.writeInt(l3);
        if(mHasBluetoothReporting)
            l3 = 1;
        else
            l3 = 0;
        parcel.writeInt(l3);
        if(mHasModemReporting)
            l3 = 1;
        else
            l3 = 0;
        parcel.writeInt(l3);
        parcel.writeInt(mNumConnectivityChange);
        mFlashlightOnTimer.writeSummaryFromParcelLocked(parcel, l2);
        mCameraOnTimer.writeSummaryFromParcelLocked(parcel, l2);
        mBluetoothScanTimer.writeSummaryFromParcelLocked(parcel, l2);
        parcel.writeInt(mRpmStats.size());
        for(Iterator iterator = mRpmStats.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            Timer timer3 = (Timer)entry.getValue();
            if(timer3 != null)
            {
                parcel.writeInt(1);
                parcel.writeString((String)entry.getKey());
                timer3.writeSummaryFromParcelLocked(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
        }

        parcel.writeInt(mScreenOffRpmStats.size());
        for(Iterator iterator2 = mScreenOffRpmStats.entrySet().iterator(); iterator2.hasNext();)
        {
            java.util.Map.Entry entry2 = (java.util.Map.Entry)iterator2.next();
            Timer timer = (Timer)entry2.getValue();
            if(timer != null)
            {
                parcel.writeInt(1);
                parcel.writeString((String)entry2.getKey());
                timer.writeSummaryFromParcelLocked(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
        }

        parcel.writeInt(mKernelWakelockStats.size());
        for(Iterator iterator3 = mKernelWakelockStats.entrySet().iterator(); iterator3.hasNext();)
        {
            java.util.Map.Entry entry1 = (java.util.Map.Entry)iterator3.next();
            Timer timer1 = (Timer)entry1.getValue();
            if(timer1 != null)
            {
                parcel.writeInt(1);
                parcel.writeString((String)entry1.getKey());
                timer1.writeSummaryFromParcelLocked(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
        }

        parcel.writeInt(mWakeupReasonStats.size());
        for(Iterator iterator1 = mWakeupReasonStats.entrySet().iterator(); iterator1.hasNext();)
        {
            java.util.Map.Entry entry3 = (java.util.Map.Entry)iterator1.next();
            SamplingTimer samplingtimer = (SamplingTimer)entry3.getValue();
            if(samplingtimer != null)
            {
                parcel.writeInt(1);
                parcel.writeString((String)entry3.getKey());
                samplingtimer.writeSummaryFromParcelLocked(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
        }

        parcel.writeInt(mKernelMemoryStats.size());
        l3 = 0;
        while(l3 < mKernelMemoryStats.size()) 
        {
            Timer timer2 = (Timer)mKernelMemoryStats.valueAt(l3);
            if(timer2 != null)
            {
                parcel.writeInt(1);
                parcel.writeLong(mKernelMemoryStats.keyAt(l3));
                timer2.writeSummaryFromParcelLocked(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
            l3++;
        }
        parcel.writeLongArray(mCpuFreqs);
        int j5 = mUidStats.size();
        parcel.writeInt(j5);
label0:
        for(int i4 = 0; i4 < j5; i4++)
        {
            parcel.writeInt(mUidStats.keyAt(i4));
            Object obj = (Uid)mUidStats.valueAt(i4);
            ((Uid) (obj)).mOnBatteryBackgroundTimeBase.writeSummaryToParcel(parcel, l1, l2);
            ((Uid) (obj)).mOnBatteryScreenOffBackgroundTimeBase.writeSummaryToParcel(parcel, l1, l2);
            int k4;
            if(((Uid) (obj)).mWifiRunningTimer != null)
            {
                parcel.writeInt(1);
                ((Uid) (obj)).mWifiRunningTimer.writeSummaryFromParcelLocked(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
            if(((Uid) (obj)).mFullWifiLockTimer != null)
            {
                parcel.writeInt(1);
                ((Uid) (obj)).mFullWifiLockTimer.writeSummaryFromParcelLocked(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
            if(((Uid) (obj)).mWifiScanTimer != null)
            {
                parcel.writeInt(1);
                ((Uid) (obj)).mWifiScanTimer.writeSummaryFromParcelLocked(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
            k4 = 0;
            while(k4 < 5) 
            {
                if(((Uid) (obj)).mWifiBatchedScanTimer[k4] != null)
                {
                    parcel.writeInt(1);
                    ((Uid) (obj)).mWifiBatchedScanTimer[k4].writeSummaryFromParcelLocked(parcel, l2);
                } else
                {
                    parcel.writeInt(0);
                }
                k4++;
            }
            if(((Uid) (obj)).mWifiMulticastTimer != null)
            {
                parcel.writeInt(1);
                ((Uid) (obj)).mWifiMulticastTimer.writeSummaryFromParcelLocked(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
            if(((Uid) (obj)).mAudioTurnedOnTimer != null)
            {
                parcel.writeInt(1);
                ((Uid) (obj)).mAudioTurnedOnTimer.writeSummaryFromParcelLocked(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
            if(((Uid) (obj)).mVideoTurnedOnTimer != null)
            {
                parcel.writeInt(1);
                ((Uid) (obj)).mVideoTurnedOnTimer.writeSummaryFromParcelLocked(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
            if(((Uid) (obj)).mFlashlightTurnedOnTimer != null)
            {
                parcel.writeInt(1);
                ((Uid) (obj)).mFlashlightTurnedOnTimer.writeSummaryFromParcelLocked(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
            if(((Uid) (obj)).mCameraTurnedOnTimer != null)
            {
                parcel.writeInt(1);
                ((Uid) (obj)).mCameraTurnedOnTimer.writeSummaryFromParcelLocked(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
            if(((Uid) (obj)).mForegroundActivityTimer != null)
            {
                parcel.writeInt(1);
                ((Uid) (obj)).mForegroundActivityTimer.writeSummaryFromParcelLocked(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
            if(((Uid) (obj)).mForegroundServiceTimer != null)
            {
                parcel.writeInt(1);
                ((Uid) (obj)).mForegroundServiceTimer.writeSummaryFromParcelLocked(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
            if(((Uid) (obj)).mAggregatedPartialWakelockTimer != null)
            {
                parcel.writeInt(1);
                ((Uid) (obj)).mAggregatedPartialWakelockTimer.writeSummaryFromParcelLocked(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
            if(((Uid) (obj)).mBluetoothScanTimer != null)
            {
                parcel.writeInt(1);
                ((Uid) (obj)).mBluetoothScanTimer.writeSummaryFromParcelLocked(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
            if(((Uid) (obj)).mBluetoothUnoptimizedScanTimer != null)
            {
                parcel.writeInt(1);
                ((Uid) (obj)).mBluetoothUnoptimizedScanTimer.writeSummaryFromParcelLocked(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
            if(((Uid) (obj)).mBluetoothScanResultCounter != null)
            {
                parcel.writeInt(1);
                ((Uid) (obj)).mBluetoothScanResultCounter.writeSummaryFromParcelLocked(parcel);
            } else
            {
                parcel.writeInt(0);
            }
            if(((Uid) (obj)).mBluetoothScanResultBgCounter != null)
            {
                parcel.writeInt(1);
                ((Uid) (obj)).mBluetoothScanResultBgCounter.writeSummaryFromParcelLocked(parcel);
            } else
            {
                parcel.writeInt(0);
            }
            k4 = 0;
            while(k4 < 6) 
            {
                if(((Uid) (obj)).mProcessStateTimer[k4] != null)
                {
                    parcel.writeInt(1);
                    ((Uid) (obj)).mProcessStateTimer[k4].writeSummaryFromParcelLocked(parcel, l2);
                } else
                {
                    parcel.writeInt(0);
                }
                k4++;
            }
            if(((Uid) (obj)).mVibratorOnTimer != null)
            {
                parcel.writeInt(1);
                ((Uid) (obj)).mVibratorOnTimer.writeSummaryFromParcelLocked(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
            if(((Uid) (obj)).mUserActivityCounters == null)
            {
                parcel.writeInt(0);
            } else
            {
                parcel.writeInt(1);
                k4 = 0;
                while(k4 < 4) 
                {
                    ((Uid) (obj)).mUserActivityCounters[k4].writeSummaryFromParcelLocked(parcel);
                    k4++;
                }
            }
            if(((Uid) (obj)).mNetworkByteActivityCounters == null)
            {
                parcel.writeInt(0);
            } else
            {
                parcel.writeInt(1);
                for(k4 = 0; k4 < 10; k4++)
                {
                    ((Uid) (obj)).mNetworkByteActivityCounters[k4].writeSummaryFromParcelLocked(parcel);
                    ((Uid) (obj)).mNetworkPacketActivityCounters[k4].writeSummaryFromParcelLocked(parcel);
                }

                ((Uid) (obj)).mMobileRadioActiveTime.writeSummaryFromParcelLocked(parcel);
                ((Uid) (obj)).mMobileRadioActiveCount.writeSummaryFromParcelLocked(parcel);
            }
            ((Uid) (obj)).mUserCpuTime.writeSummaryFromParcelLocked(parcel);
            ((Uid) (obj)).mSystemCpuTime.writeSummaryFromParcelLocked(parcel);
            if(((Uid) (obj)).mCpuClusterSpeedTimesUs != null)
            {
                parcel.writeInt(1);
                parcel.writeInt(((Uid) (obj)).mCpuClusterSpeedTimesUs.length);
                LongSamplingCounter alongsamplingcounter1[][] = ((Uid) (obj)).mCpuClusterSpeedTimesUs;
                int k5 = alongsamplingcounter1.length;
                for(k4 = 0; k4 < k5; k4++)
                {
                    LongSamplingCounter alongsamplingcounter[] = alongsamplingcounter1[k4];
                    if(alongsamplingcounter != null)
                    {
                        parcel.writeInt(1);
                        parcel.writeInt(alongsamplingcounter.length);
                        int j = 0;
                        int l5 = alongsamplingcounter.length;
                        while(j < l5) 
                        {
                            LongSamplingCounter longsamplingcounter = alongsamplingcounter[j];
                            if(longsamplingcounter != null)
                            {
                                parcel.writeInt(1);
                                longsamplingcounter.writeSummaryFromParcelLocked(parcel);
                            } else
                            {
                                parcel.writeInt(0);
                            }
                            j++;
                        }
                    } else
                    {
                        parcel.writeInt(0);
                    }
                }

            } else
            {
                parcel.writeInt(0);
            }
            LongSamplingCounterArray.writeSummaryToParcelLocked(parcel, ((Uid) (obj)).mCpuFreqTimeMs);
            LongSamplingCounterArray.writeSummaryToParcelLocked(parcel, ((Uid) (obj)).mScreenOffCpuFreqTimeMs);
            int k;
            ArrayMap arraymap;
            if(Uid._2D_get0(((Uid) (obj))) != null)
            {
                parcel.writeInt(1);
                Uid._2D_get0(((Uid) (obj))).writeSummaryFromParcelLocked(parcel);
            } else
            {
                parcel.writeInt(0);
            }
            if(Uid._2D_get1(((Uid) (obj))) != null)
            {
                parcel.writeInt(1);
                Uid._2D_get1(((Uid) (obj))).writeSummaryFromParcelLocked(parcel);
            } else
            {
                parcel.writeInt(0);
            }
            arraymap = ((Uid) (obj)).mWakelockStats.getMap();
            k = arraymap.size();
            parcel.writeInt(k);
            k4 = 0;
            while(k4 < k) 
            {
                parcel.writeString((String)arraymap.keyAt(k4));
                Uid.Wakelock wakelock = (Uid.Wakelock)arraymap.valueAt(k4);
                if(wakelock.mTimerFull != null)
                {
                    parcel.writeInt(1);
                    wakelock.mTimerFull.writeSummaryFromParcelLocked(parcel, l2);
                } else
                {
                    parcel.writeInt(0);
                }
                if(wakelock.mTimerPartial != null)
                {
                    parcel.writeInt(1);
                    wakelock.mTimerPartial.writeSummaryFromParcelLocked(parcel, l2);
                } else
                {
                    parcel.writeInt(0);
                }
                if(wakelock.mTimerWindow != null)
                {
                    parcel.writeInt(1);
                    wakelock.mTimerWindow.writeSummaryFromParcelLocked(parcel, l2);
                } else
                {
                    parcel.writeInt(0);
                }
                if(wakelock.mTimerDraw != null)
                {
                    parcel.writeInt(1);
                    wakelock.mTimerDraw.writeSummaryFromParcelLocked(parcel, l2);
                } else
                {
                    parcel.writeInt(0);
                }
                k4++;
            }
            arraymap = ((Uid) (obj)).mSyncStats.getMap();
            k = arraymap.size();
            parcel.writeInt(k);
            for(k4 = 0; k4 < k; k4++)
            {
                parcel.writeString((String)arraymap.keyAt(k4));
                ((DualTimer)arraymap.valueAt(k4)).writeSummaryFromParcelLocked(parcel, l2);
            }

            arraymap = ((Uid) (obj)).mJobStats.getMap();
            k = arraymap.size();
            parcel.writeInt(k);
            for(k4 = 0; k4 < k; k4++)
            {
                parcel.writeString((String)arraymap.keyAt(k4));
                ((DualTimer)arraymap.valueAt(k4)).writeSummaryFromParcelLocked(parcel, l2);
            }

            ((Uid) (obj)).writeJobCompletionsToParcelLocked(parcel);
            k = ((Uid) (obj)).mSensorStats.size();
            parcel.writeInt(k);
            k4 = 0;
            while(k4 < k) 
            {
                parcel.writeInt(((Uid) (obj)).mSensorStats.keyAt(k4));
                Uid.Sensor sensor = (Uid.Sensor)((Uid) (obj)).mSensorStats.valueAt(k4);
                if(sensor.mTimer != null)
                {
                    parcel.writeInt(1);
                    sensor.mTimer.writeSummaryFromParcelLocked(parcel, l2);
                } else
                {
                    parcel.writeInt(0);
                }
                k4++;
            }
            k = ((Uid) (obj)).mProcessStats.size();
            parcel.writeInt(k);
            for(k4 = 0; k4 < k; k4++)
            {
                parcel.writeString((String)((Uid) (obj)).mProcessStats.keyAt(k4));
                Uid.Proc proc = (Uid.Proc)((Uid) (obj)).mProcessStats.valueAt(k4);
                parcel.writeLong(proc.mUserTime);
                parcel.writeLong(proc.mSystemTime);
                parcel.writeLong(proc.mForegroundTime);
                parcel.writeInt(proc.mStarts);
                parcel.writeInt(proc.mNumCrashes);
                parcel.writeInt(proc.mNumAnrs);
                proc.writeExcessivePowerToParcelLocked(parcel);
            }

            k4 = ((Uid) (obj)).mPackageStats.size();
            parcel.writeInt(k4);
            if(k4 <= 0)
                continue;
            obj = ((Uid) (obj)).mPackageStats.entrySet().iterator();
            do
            {
                if(!((Iterator) (obj)).hasNext())
                    continue label0;
                Object obj1 = (java.util.Map.Entry)((Iterator) (obj)).next();
                parcel.writeString((String)((java.util.Map.Entry) (obj1)).getKey());
                obj1 = (Uid.Pkg)((java.util.Map.Entry) (obj1)).getValue();
                int i1 = ((Uid.Pkg) (obj1)).mWakeupAlarms.size();
                parcel.writeInt(i1);
                for(int l4 = 0; l4 < i1; l4++)
                {
                    parcel.writeString((String)((Uid.Pkg) (obj1)).mWakeupAlarms.keyAt(l4));
                    ((Counter)((Uid.Pkg) (obj1)).mWakeupAlarms.valueAt(l4)).writeSummaryFromParcelLocked(parcel);
                }

                i1 = ((Uid.Pkg) (obj1)).mServiceStats.size();
                parcel.writeInt(i1);
                int i5 = 0;
                while(i5 < i1) 
                {
                    parcel.writeString((String)((Uid.Pkg) (obj1)).mServiceStats.keyAt(i5));
                    Uid.Pkg.Serv serv = (Uid.Pkg.Serv)((Uid.Pkg) (obj1)).mServiceStats.valueAt(i5);
                    parcel.writeLong(serv.getStartTimeToNowLocked(mOnBatteryTimeBase.getUptime(l1)));
                    parcel.writeInt(serv.mStarts);
                    parcel.writeInt(serv.mLaunches);
                    i5++;
                }
            } while(true);
        }

    }

    public void writeSyncLocked()
    {
        writeLocked(true);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcelLocked(parcel, true, i);
    }

    void writeToParcelLocked(Parcel parcel, boolean flag, int i)
    {
        pullPendingStateUpdatesLocked();
        long l = getStartClockTime();
        long l1 = mClocks.uptimeMillis() * 1000L;
        long l2 = mClocks.elapsedRealtime() * 1000L;
        mOnBatteryTimeBase.getRealtime(l2);
        mOnBatteryScreenOffTimeBase.getRealtime(l2);
        parcel.writeInt(0xba757475);
        writeHistory(parcel, true, false);
        parcel.writeInt(mStartCount);
        parcel.writeLong(l);
        parcel.writeString(mStartPlatformVersion);
        parcel.writeString(mEndPlatformVersion);
        parcel.writeLong(mUptime);
        parcel.writeLong(mUptimeStart);
        parcel.writeLong(mRealtime);
        parcel.writeLong(mRealtimeStart);
        if(mOnBattery)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mEstimatedBatteryCapacity);
        parcel.writeInt(mMinLearnedBatteryCapacity);
        parcel.writeInt(mMaxLearnedBatteryCapacity);
        mOnBatteryTimeBase.writeToParcel(parcel, l1, l2);
        mOnBatteryScreenOffTimeBase.writeToParcel(parcel, l1, l2);
        mScreenOnTimer.writeToParcel(parcel, l2);
        mScreenDozeTimer.writeToParcel(parcel, l2);
        for(i = 0; i < 5; i++)
            mScreenBrightnessTimer[i].writeToParcel(parcel, l2);

        mInteractiveTimer.writeToParcel(parcel, l2);
        mPowerSaveModeEnabledTimer.writeToParcel(parcel, l2);
        parcel.writeLong(mLongestLightIdleTime);
        parcel.writeLong(mLongestFullIdleTime);
        mDeviceIdleModeLightTimer.writeToParcel(parcel, l2);
        mDeviceIdleModeFullTimer.writeToParcel(parcel, l2);
        mDeviceLightIdlingTimer.writeToParcel(parcel, l2);
        mDeviceIdlingTimer.writeToParcel(parcel, l2);
        mPhoneOnTimer.writeToParcel(parcel, l2);
        for(i = 0; i < 6; i++)
            mPhoneSignalStrengthsTimer[i].writeToParcel(parcel, l2);

        mPhoneSignalScanningTimer.writeToParcel(parcel, l2);
        for(i = 0; i < 17; i++)
            mPhoneDataConnectionsTimer[i].writeToParcel(parcel, l2);

        for(i = 0; i < 10; i++)
        {
            mNetworkByteActivityCounters[i].writeToParcel(parcel);
            mNetworkPacketActivityCounters[i].writeToParcel(parcel);
        }

        mMobileRadioActiveTimer.writeToParcel(parcel, l2);
        mMobileRadioActivePerAppTimer.writeToParcel(parcel, l2);
        mMobileRadioActiveAdjustedTime.writeToParcel(parcel);
        mMobileRadioActiveUnknownTime.writeToParcel(parcel);
        mMobileRadioActiveUnknownCount.writeToParcel(parcel);
        mWifiOnTimer.writeToParcel(parcel, l2);
        mGlobalWifiRunningTimer.writeToParcel(parcel, l2);
        for(i = 0; i < 8; i++)
            mWifiStateTimer[i].writeToParcel(parcel, l2);

        for(i = 0; i < 13; i++)
            mWifiSupplStateTimer[i].writeToParcel(parcel, l2);

        for(i = 0; i < 5; i++)
            mWifiSignalStrengthsTimer[i].writeToParcel(parcel, l2);

        mWifiActivity.writeToParcel(parcel, 0);
        mBluetoothActivity.writeToParcel(parcel, 0);
        mModemActivity.writeToParcel(parcel, 0);
        if(mHasWifiReporting)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mHasBluetoothReporting)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mHasModemReporting)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mNumConnectivityChange);
        parcel.writeInt(mLoadedNumConnectivityChange);
        parcel.writeInt(mUnpluggedNumConnectivityChange);
        mFlashlightOnTimer.writeToParcel(parcel, l2);
        mCameraOnTimer.writeToParcel(parcel, l2);
        mBluetoothScanTimer.writeToParcel(parcel, l2);
        parcel.writeInt(mDischargeUnplugLevel);
        parcel.writeInt(mDischargePlugLevel);
        parcel.writeInt(mDischargeCurrentLevel);
        parcel.writeInt(mCurrentBatteryLevel);
        parcel.writeInt(mLowDischargeAmountSinceCharge);
        parcel.writeInt(mHighDischargeAmountSinceCharge);
        parcel.writeInt(mDischargeAmountScreenOn);
        parcel.writeInt(mDischargeAmountScreenOnSinceCharge);
        parcel.writeInt(mDischargeAmountScreenOff);
        parcel.writeInt(mDischargeAmountScreenOffSinceCharge);
        parcel.writeInt(mDischargeAmountScreenDoze);
        parcel.writeInt(mDischargeAmountScreenDozeSinceCharge);
        mDischargeStepTracker.writeToParcel(parcel);
        mChargeStepTracker.writeToParcel(parcel);
        mDischargeCounter.writeToParcel(parcel);
        mDischargeScreenOffCounter.writeToParcel(parcel);
        mDischargeScreenDozeCounter.writeToParcel(parcel);
        parcel.writeLong(mLastWriteTime);
        parcel.writeInt(mRpmStats.size());
        for(Iterator iterator = mRpmStats.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            SamplingTimer samplingtimer3 = (SamplingTimer)entry.getValue();
            if(samplingtimer3 != null)
            {
                parcel.writeInt(1);
                parcel.writeString((String)entry.getKey());
                samplingtimer3.writeToParcel(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
        }

        parcel.writeInt(mScreenOffRpmStats.size());
        for(Iterator iterator1 = mScreenOffRpmStats.entrySet().iterator(); iterator1.hasNext();)
        {
            java.util.Map.Entry entry1 = (java.util.Map.Entry)iterator1.next();
            SamplingTimer samplingtimer = (SamplingTimer)entry1.getValue();
            if(samplingtimer != null)
            {
                parcel.writeInt(1);
                parcel.writeString((String)entry1.getKey());
                samplingtimer.writeToParcel(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
        }

        if(flag)
        {
            parcel.writeInt(mKernelWakelockStats.size());
            for(Iterator iterator2 = mKernelWakelockStats.entrySet().iterator(); iterator2.hasNext();)
            {
                java.util.Map.Entry entry2 = (java.util.Map.Entry)iterator2.next();
                SamplingTimer samplingtimer1 = (SamplingTimer)entry2.getValue();
                if(samplingtimer1 != null)
                {
                    parcel.writeInt(1);
                    parcel.writeString((String)entry2.getKey());
                    samplingtimer1.writeToParcel(parcel, l2);
                } else
                {
                    parcel.writeInt(0);
                }
            }

            parcel.writeInt(mWakeupReasonStats.size());
            for(Iterator iterator3 = mWakeupReasonStats.entrySet().iterator(); iterator3.hasNext();)
            {
                java.util.Map.Entry entry3 = (java.util.Map.Entry)iterator3.next();
                SamplingTimer samplingtimer2 = (SamplingTimer)entry3.getValue();
                if(samplingtimer2 != null)
                {
                    parcel.writeInt(1);
                    parcel.writeString((String)entry3.getKey());
                    samplingtimer2.writeToParcel(parcel, l2);
                } else
                {
                    parcel.writeInt(0);
                }
            }

        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(mKernelMemoryStats.size());
        i = 0;
        while(i < mKernelMemoryStats.size()) 
        {
            SamplingTimer samplingtimer4 = (SamplingTimer)mKernelMemoryStats.valueAt(i);
            if(samplingtimer4 != null)
            {
                parcel.writeInt(1);
                parcel.writeLong(mKernelMemoryStats.keyAt(i));
                samplingtimer4.writeToParcel(parcel, l2);
            } else
            {
                parcel.writeInt(0);
            }
            i++;
        }
        parcel.writeLongArray(mCpuFreqs);
        if(flag)
        {
            int j = mUidStats.size();
            parcel.writeInt(j);
            for(i = 0; i < j; i++)
            {
                parcel.writeInt(mUidStats.keyAt(i));
                ((Uid)mUidStats.valueAt(i)).writeToParcelLocked(parcel, l1, l2);
            }

        } else
        {
            parcel.writeInt(0);
        }
    }

    public void writeToParcelWithoutUids(Parcel parcel, int i)
    {
        writeToParcelLocked(parcel, false, i);
    }

    static final int BATTERY_DELTA_LEVEL_FLAG = 1;
    public static final int BATTERY_PLUGGED_NONE = 0;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BatteryStatsImpl createFromParcel(Parcel parcel)
        {
            return new BatteryStatsImpl(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BatteryStatsImpl[] newArray(int i)
        {
            return new BatteryStatsImpl[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DEBUG = false;
    public static final boolean DEBUG_ENERGY = false;
    private static final boolean DEBUG_ENERGY_CPU = false;
    private static final boolean DEBUG_HISTORY = false;
    private static final boolean DEBUG_MEMORY = false;
    static final long DELAY_UPDATE_WAKELOCKS = 5000L;
    static final int DELTA_BATTERY_CHARGE_FLAG = 0x1000000;
    static final int DELTA_BATTERY_LEVEL_FLAG = 0x80000;
    static final int DELTA_EVENT_FLAG = 0x800000;
    static final int DELTA_STATE2_FLAG = 0x200000;
    static final int DELTA_STATE_FLAG = 0x100000;
    static final int DELTA_STATE_MASK = 0xfe000000;
    static final int DELTA_TIME_ABS = 0x7fffd;
    static final int DELTA_TIME_INT = 0x7fffe;
    static final int DELTA_TIME_LONG = 0x7ffff;
    static final int DELTA_TIME_MASK = 0x7ffff;
    static final int DELTA_WAKELOCK_FLAG = 0x400000;
    private static final int MAGIC = 0xba757475;
    static final int MAX_DAILY_ITEMS = 10;
    static final int MAX_HISTORY_BUFFER;
    private static final int MAX_HISTORY_ITEMS;
    static final int MAX_LEVEL_STEPS = 200;
    static final int MAX_MAX_HISTORY_BUFFER;
    private static final int MAX_MAX_HISTORY_ITEMS;
    private static final int MAX_WAKELOCKS_PER_UID;
    static final int MSG_REPORT_CHARGING = 3;
    static final int MSG_REPORT_POWER_CHANGE = 2;
    static final int MSG_UPDATE_WAKELOCKS = 1;
    private static final int NUM_BT_TX_LEVELS = 1;
    private static final int NUM_WIFI_TX_LEVELS = 1;
    private static final long RPM_STATS_UPDATE_FREQ_MS = 1000L;
    static final int STATE_BATTERY_HEALTH_MASK = 7;
    static final int STATE_BATTERY_HEALTH_SHIFT = 26;
    static final int STATE_BATTERY_MASK = 0xff000000;
    static final int STATE_BATTERY_PLUG_MASK = 3;
    static final int STATE_BATTERY_PLUG_SHIFT = 24;
    static final int STATE_BATTERY_STATUS_MASK = 7;
    static final int STATE_BATTERY_STATUS_SHIFT = 29;
    private static final String TAG = "BatteryStatsImpl";
    private static final boolean USE_OLD_HISTORY = false;
    private static final int VERSION = 167;
    final android.os.BatteryStats.HistoryEventTracker mActiveEvents;
    int mActiveHistoryStates;
    int mActiveHistoryStates2;
    int mAudioOnNesting;
    StopwatchTimer mAudioOnTimer;
    final ArrayList mAudioTurnedOnTimers;
    ControllerActivityCounterImpl mBluetoothActivity;
    int mBluetoothScanNesting;
    final ArrayList mBluetoothScanOnTimers;
    protected StopwatchTimer mBluetoothScanTimer;
    private BatteryCallback mCallback;
    int mCameraOnNesting;
    StopwatchTimer mCameraOnTimer;
    final ArrayList mCameraTurnedOnTimers;
    int mChangedStates;
    int mChangedStates2;
    final android.os.BatteryStats.LevelStepTracker mChargeStepTracker;
    boolean mCharging;
    public final AtomicFile mCheckinFile;
    protected Clocks mClocks;
    private long mCpuFreqs[];
    final android.os.BatteryStats.HistoryStepDetails mCurHistoryStepDetails;
    long mCurStepCpuSystemTime;
    long mCurStepCpuUserTime;
    int mCurStepMode;
    long mCurStepStatIOWaitTime;
    long mCurStepStatIdleTime;
    long mCurStepStatIrqTime;
    long mCurStepStatSoftIrqTime;
    long mCurStepStatSystemTime;
    long mCurStepStatUserTime;
    int mCurrentBatteryLevel;
    final android.os.BatteryStats.LevelStepTracker mDailyChargeStepTracker;
    final android.os.BatteryStats.LevelStepTracker mDailyDischargeStepTracker;
    public final AtomicFile mDailyFile;
    final ArrayList mDailyItems;
    ArrayList mDailyPackageChanges;
    long mDailyStartTime;
    int mDeviceIdleMode;
    StopwatchTimer mDeviceIdleModeFullTimer;
    StopwatchTimer mDeviceIdleModeLightTimer;
    boolean mDeviceIdling;
    StopwatchTimer mDeviceIdlingTimer;
    boolean mDeviceLightIdling;
    StopwatchTimer mDeviceLightIdlingTimer;
    int mDischargeAmountScreenDoze;
    int mDischargeAmountScreenDozeSinceCharge;
    int mDischargeAmountScreenOff;
    int mDischargeAmountScreenOffSinceCharge;
    int mDischargeAmountScreenOn;
    int mDischargeAmountScreenOnSinceCharge;
    private LongSamplingCounter mDischargeCounter;
    int mDischargeCurrentLevel;
    int mDischargePlugLevel;
    private LongSamplingCounter mDischargeScreenDozeCounter;
    int mDischargeScreenDozeUnplugLevel;
    private LongSamplingCounter mDischargeScreenOffCounter;
    int mDischargeScreenOffUnplugLevel;
    int mDischargeScreenOnUnplugLevel;
    int mDischargeStartLevel;
    final android.os.BatteryStats.LevelStepTracker mDischargeStepTracker;
    int mDischargeUnplugLevel;
    boolean mDistributeWakelockCpu;
    final ArrayList mDrawTimers;
    String mEndPlatformVersion;
    private int mEstimatedBatteryCapacity;
    private ExternalStatsSync mExternalSync;
    private final JournaledFile mFile;
    int mFlashlightOnNesting;
    StopwatchTimer mFlashlightOnTimer;
    final ArrayList mFlashlightTurnedOnTimers;
    final ArrayList mFullTimers;
    final ArrayList mFullWifiLockTimers;
    boolean mGlobalWifiRunning;
    StopwatchTimer mGlobalWifiRunningTimer;
    int mGpsNesting;
    public final MyHandler mHandler;
    boolean mHasBluetoothReporting;
    boolean mHasModemReporting;
    boolean mHasWifiReporting;
    boolean mHaveBatteryLevel;
    int mHighDischargeAmountSinceCharge;
    android.os.BatteryStats.HistoryItem mHistory;
    final android.os.BatteryStats.HistoryItem mHistoryAddTmp;
    long mHistoryBaseTime;
    final Parcel mHistoryBuffer;
    int mHistoryBufferLastPos;
    android.os.BatteryStats.HistoryItem mHistoryCache;
    final android.os.BatteryStats.HistoryItem mHistoryCur;
    android.os.BatteryStats.HistoryItem mHistoryEnd;
    private android.os.BatteryStats.HistoryItem mHistoryIterator;
    android.os.BatteryStats.HistoryItem mHistoryLastEnd;
    final android.os.BatteryStats.HistoryItem mHistoryLastLastWritten;
    final android.os.BatteryStats.HistoryItem mHistoryLastWritten;
    boolean mHistoryOverflow;
    final android.os.BatteryStats.HistoryItem mHistoryReadTmp;
    final HashMap mHistoryTagPool;
    int mInitStepMode;
    private String mInitialAcquireWakeName;
    private int mInitialAcquireWakeUid;
    boolean mInteractive;
    StopwatchTimer mInteractiveTimer;
    final SparseIntArray mIsolatedUids;
    private boolean mIteratingHistory;
    private KernelCpuSpeedReader mKernelCpuSpeedReaders[];
    private final KernelMemoryBandwidthStats mKernelMemoryBandwidthStats;
    private final LongSparseArray mKernelMemoryStats;
    private final KernelUidCpuFreqTimeReader mKernelUidCpuFreqTimeReader;
    private final KernelUidCpuTimeReader mKernelUidCpuTimeReader;
    private final KernelWakelockReader mKernelWakelockReader;
    private final HashMap mKernelWakelockStats;
    int mLastChargeStepLevel;
    int mLastChargingStateLevel;
    int mLastDischargeStepLevel;
    long mLastHistoryElapsedRealtime;
    android.os.BatteryStats.HistoryStepDetails mLastHistoryStepDetails;
    byte mLastHistoryStepLevel;
    long mLastIdleTimeStart;
    private NetworkStats mLastModemNetworkStats;
    final ArrayList mLastPartialTimers;
    private long mLastRpmStatsUpdateTimeMs;
    long mLastStepCpuSystemTime;
    long mLastStepCpuUserTime;
    long mLastStepStatIOWaitTime;
    long mLastStepStatIdleTime;
    long mLastStepStatIrqTime;
    long mLastStepStatSoftIrqTime;
    long mLastStepStatSystemTime;
    long mLastStepStatUserTime;
    String mLastWakeupReason;
    long mLastWakeupUptimeMs;
    private NetworkStats mLastWifiNetworkStats;
    long mLastWriteTime;
    private int mLoadedNumConnectivityChange;
    long mLongestFullIdleTime;
    long mLongestLightIdleTime;
    int mLowDischargeAmountSinceCharge;
    int mMaxChargeStepLevel;
    private int mMaxLearnedBatteryCapacity;
    int mMinDischargeStepLevel;
    private int mMinLearnedBatteryCapacity;
    LongSamplingCounter mMobileRadioActiveAdjustedTime;
    StopwatchTimer mMobileRadioActivePerAppTimer;
    long mMobileRadioActiveStartTime;
    StopwatchTimer mMobileRadioActiveTimer;
    LongSamplingCounter mMobileRadioActiveUnknownCount;
    LongSamplingCounter mMobileRadioActiveUnknownTime;
    int mMobileRadioPowerState;
    int mModStepMode;
    ControllerActivityCounterImpl mModemActivity;
    private String mModemIfaces[];
    private final Object mModemNetworkLock;
    final LongSamplingCounter mNetworkByteActivityCounters[];
    final LongSamplingCounter mNetworkPacketActivityCounters[];
    private final NetworkStatsFactory mNetworkStatsFactory;
    private final android.util.Pools.Pool mNetworkStatsPool;
    int mNextHistoryTagIdx;
    long mNextMaxDailyDeadline;
    long mNextMinDailyDeadline;
    boolean mNoAutoReset;
    private int mNumConnectivityChange;
    int mNumHistoryItems;
    int mNumHistoryTagChars;
    boolean mOnBattery;
    boolean mOnBatteryInternal;
    protected final TimeBase mOnBatteryScreenOffTimeBase;
    protected final TimeBase mOnBatteryTimeBase;
    final ArrayList mPartialTimers;
    Parcel mPendingWrite;
    int mPhoneDataConnectionType;
    final StopwatchTimer mPhoneDataConnectionsTimer[];
    boolean mPhoneOn;
    StopwatchTimer mPhoneOnTimer;
    private int mPhoneServiceState;
    private int mPhoneServiceStateRaw;
    StopwatchTimer mPhoneSignalScanningTimer;
    int mPhoneSignalStrengthBin;
    int mPhoneSignalStrengthBinRaw;
    final StopwatchTimer mPhoneSignalStrengthsTimer[];
    private int mPhoneSimStateRaw;
    private final PlatformIdleStateCallback mPlatformIdleStateCallback;
    private PowerProfile mPowerProfile;
    boolean mPowerSaveModeEnabled;
    StopwatchTimer mPowerSaveModeEnabledTimer;
    boolean mPretendScreenOff;
    int mReadHistoryChars;
    final android.os.BatteryStats.HistoryStepDetails mReadHistoryStepDetails;
    String mReadHistoryStrings[];
    int mReadHistoryUids[];
    private boolean mReadOverflow;
    long mRealtime;
    long mRealtimeStart;
    public boolean mRecordAllHistory;
    boolean mRecordingHistory;
    private final HashMap mRpmStats;
    int mScreenBrightnessBin;
    final StopwatchTimer mScreenBrightnessTimer[];
    protected StopwatchTimer mScreenDozeTimer;
    private final HashMap mScreenOffRpmStats;
    protected StopwatchTimer mScreenOnTimer;
    protected int mScreenState;
    int mSensorNesting;
    final SparseArray mSensorTimers;
    boolean mShuttingDown;
    long mStartClockTime;
    int mStartCount;
    String mStartPlatformVersion;
    long mTempTotalCpuSystemTimeUs;
    long mTempTotalCpuUserTimeUs;
    final android.os.BatteryStats.HistoryStepDetails mTmpHistoryStepDetails;
    private final RpmStats mTmpRpmStats;
    private final KernelWakelockStats mTmpWakelockStats;
    long mTrackRunningHistoryElapsedRealtime;
    long mTrackRunningHistoryUptime;
    final SparseArray mUidStats;
    private int mUnpluggedNumConnectivityChange;
    long mUptime;
    long mUptimeStart;
    private UserInfoProvider mUserInfoProvider;
    int mVideoOnNesting;
    StopwatchTimer mVideoOnTimer;
    final ArrayList mVideoTurnedOnTimers;
    boolean mWakeLockImportant;
    int mWakeLockNesting;
    private final HashMap mWakeupReasonStats;
    ControllerActivityCounterImpl mWifiActivity;
    final SparseArray mWifiBatchedScanTimers;
    int mWifiFullLockNesting;
    private String mWifiIfaces[];
    int mWifiMulticastNesting;
    final ArrayList mWifiMulticastTimers;
    private final Object mWifiNetworkLock;
    boolean mWifiOn;
    StopwatchTimer mWifiOnTimer;
    int mWifiRadioPowerState;
    final ArrayList mWifiRunningTimers;
    int mWifiScanNesting;
    final ArrayList mWifiScanTimers;
    int mWifiSignalStrengthBin;
    final StopwatchTimer mWifiSignalStrengthsTimer[];
    int mWifiState;
    final StopwatchTimer mWifiStateTimer[];
    int mWifiSupplState;
    final StopwatchTimer mWifiSupplStateTimer[];
    final ArrayList mWindowTimers;
    final ReentrantLock mWriteLock;

    static 
    {
        if(ActivityManager.isLowRamDeviceStatic())
        {
            MAX_HISTORY_ITEMS = 800;
            MAX_MAX_HISTORY_ITEMS = 1200;
            MAX_WAKELOCKS_PER_UID = 40;
            MAX_HISTORY_BUFFER = 0x18000;
            MAX_MAX_HISTORY_BUFFER = 0x20000;
        } else
        {
            MAX_HISTORY_ITEMS = 2000;
            MAX_MAX_HISTORY_ITEMS = 3000;
            MAX_WAKELOCKS_PER_UID = 100;
            MAX_HISTORY_BUFFER = 0x40000;
            MAX_MAX_HISTORY_BUFFER = 0x50000;
        }
    }

    // Unreferenced inner class com/android/internal/os/BatteryStatsImpl$2

/* anonymous class */
    class _cls2
        implements Runnable
    {

        public void run()
        {
            AtomicFile atomicfile = mCheckinFile;
            atomicfile;
            JVM INSTR monitorenter ;
            FileOutputStream fileoutputstream = null;
            FileOutputStream fileoutputstream1 = mDailyFile.startWrite();
            fileoutputstream = fileoutputstream1;
            memStream.writeTo(fileoutputstream1);
            fileoutputstream = fileoutputstream1;
            fileoutputstream1.flush();
            fileoutputstream = fileoutputstream1;
            FileUtils.sync(fileoutputstream1);
            fileoutputstream = fileoutputstream1;
            fileoutputstream1.close();
            fileoutputstream = fileoutputstream1;
            mDailyFile.finishWrite(fileoutputstream1);
_L1:
            atomicfile;
            JVM INSTR monitorexit ;
            return;
            IOException ioexception;
            ioexception;
            Slog.w("BatteryStats", "Error writing battery daily items", ioexception);
            mDailyFile.failWrite(fileoutputstream);
              goto _L1
            Exception exception;
            exception;
            throw exception;
        }

        final BatteryStatsImpl this$0;
        final ByteArrayOutputStream val$memStream;

            
            {
                this$0 = BatteryStatsImpl.this;
                memStream = bytearrayoutputstream;
                super();
            }
    }


    // Unreferenced inner class com/android/internal/os/BatteryStatsImpl$Uid$1

/* anonymous class */
    class Uid._cls1 extends OverflowArrayMap
    {

        public Uid.Wakelock instantiateObject()
        {
            return new Uid.Wakelock(mBsi, Uid.this);
        }

        public volatile Object instantiateObject()
        {
            return instantiateObject();
        }

        final Uid this$1;

            
            {
                this$1 = Uid.this;
                batterystatsimpl. super(i);
            }
    }


    // Unreferenced inner class com/android/internal/os/BatteryStatsImpl$Uid$2

/* anonymous class */
    class Uid._cls2 extends OverflowArrayMap
    {

        public DualTimer instantiateObject()
        {
            return new DualTimer(mBsi.mClocks, Uid.this, 13, null, mBsi.mOnBatteryTimeBase, mOnBatteryBackgroundTimeBase);
        }

        public volatile Object instantiateObject()
        {
            return instantiateObject();
        }

        final Uid this$1;

            
            {
                this$1 = Uid.this;
                batterystatsimpl. super(i);
            }
    }


    // Unreferenced inner class com/android/internal/os/BatteryStatsImpl$Uid$3

/* anonymous class */
    class Uid._cls3 extends OverflowArrayMap
    {

        public DualTimer instantiateObject()
        {
            return new DualTimer(mBsi.mClocks, Uid.this, 14, null, mBsi.mOnBatteryTimeBase, mOnBatteryBackgroundTimeBase);
        }

        public volatile Object instantiateObject()
        {
            return instantiateObject();
        }

        final Uid this$1;

            
            {
                this$1 = Uid.this;
                batterystatsimpl. super(i);
            }
    }

}
