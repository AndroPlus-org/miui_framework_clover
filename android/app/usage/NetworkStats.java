// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.usage;

import android.content.Context;
import android.net.INetworkStatsService;
import android.net.INetworkStatsSession;
import android.net.NetworkStatsHistory;
import android.net.NetworkTemplate;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.IntArray;
import android.util.Log;
import dalvik.system.CloseGuard;

public final class NetworkStats
    implements AutoCloseable
{
    public static class Bucket
    {

        static long _2D_set0(Bucket bucket, long l)
        {
            bucket.mBeginTimeStamp = l;
            return l;
        }

        static long _2D_set1(Bucket bucket, long l)
        {
            bucket.mEndTimeStamp = l;
            return l;
        }

        static int _2D_set10(Bucket bucket, int i)
        {
            bucket.mUid = i;
            return i;
        }

        static int _2D_set2(Bucket bucket, int i)
        {
            bucket.mMetered = i;
            return i;
        }

        static int _2D_set3(Bucket bucket, int i)
        {
            bucket.mRoaming = i;
            return i;
        }

        static long _2D_set4(Bucket bucket, long l)
        {
            bucket.mRxBytes = l;
            return l;
        }

        static long _2D_set5(Bucket bucket, long l)
        {
            bucket.mRxPackets = l;
            return l;
        }

        static int _2D_set6(Bucket bucket, int i)
        {
            bucket.mState = i;
            return i;
        }

        static int _2D_set7(Bucket bucket, int i)
        {
            bucket.mTag = i;
            return i;
        }

        static long _2D_set8(Bucket bucket, long l)
        {
            bucket.mTxBytes = l;
            return l;
        }

        static long _2D_set9(Bucket bucket, long l)
        {
            bucket.mTxPackets = l;
            return l;
        }

        static int _2D_wrap0(int i)
        {
            return convertMetered(i);
        }

        static int _2D_wrap1(int i)
        {
            return convertRoaming(i);
        }

        static int _2D_wrap2(int i)
        {
            return convertState(i);
        }

        static int _2D_wrap3(int i)
        {
            return convertTag(i);
        }

        static int _2D_wrap4(int i)
        {
            return convertUid(i);
        }

        private static int convertMetered(int i)
        {
            switch(i)
            {
            default:
                return 0;

            case -1: 
                return -1;

            case 0: // '\0'
                return 1;

            case 1: // '\001'
                return 2;
            }
        }

        private static int convertRoaming(int i)
        {
            switch(i)
            {
            default:
                return 0;

            case -1: 
                return -1;

            case 0: // '\0'
                return 1;

            case 1: // '\001'
                return 2;
            }
        }

        private static int convertState(int i)
        {
            switch(i)
            {
            default:
                return 0;

            case -1: 
                return -1;

            case 0: // '\0'
                return 1;

            case 1: // '\001'
                return 2;
            }
        }

        private static int convertTag(int i)
        {
            switch(i)
            {
            default:
                return i;

            case 0: // '\0'
                return 0;
            }
        }

        private static int convertUid(int i)
        {
            switch(i)
            {
            default:
                return i;

            case -4: 
                return -4;

            case -5: 
                return -5;
            }
        }

        public long getEndTimeStamp()
        {
            return mEndTimeStamp;
        }

        public int getMetered()
        {
            return mMetered;
        }

        public int getRoaming()
        {
            return mRoaming;
        }

        public long getRxBytes()
        {
            return mRxBytes;
        }

        public long getRxPackets()
        {
            return mRxPackets;
        }

        public long getStartTimeStamp()
        {
            return mBeginTimeStamp;
        }

        public int getState()
        {
            return mState;
        }

        public int getTag()
        {
            return mTag;
        }

        public long getTxBytes()
        {
            return mTxBytes;
        }

        public long getTxPackets()
        {
            return mTxPackets;
        }

        public int getUid()
        {
            return mUid;
        }

        public static final int METERED_ALL = -1;
        public static final int METERED_NO = 1;
        public static final int METERED_YES = 2;
        public static final int ROAMING_ALL = -1;
        public static final int ROAMING_NO = 1;
        public static final int ROAMING_YES = 2;
        public static final int STATE_ALL = -1;
        public static final int STATE_DEFAULT = 1;
        public static final int STATE_FOREGROUND = 2;
        public static final int TAG_NONE = 0;
        public static final int UID_ALL = -1;
        public static final int UID_REMOVED = -4;
        public static final int UID_TETHERING = -5;
        private long mBeginTimeStamp;
        private long mEndTimeStamp;
        private int mMetered;
        private int mRoaming;
        private long mRxBytes;
        private long mRxPackets;
        private int mState;
        private int mTag;
        private long mTxBytes;
        private long mTxPackets;
        private int mUid;

        public Bucket()
        {
        }
    }


    NetworkStats(Context context, NetworkTemplate networktemplate, int i, long l, long l1)
        throws RemoteException, SecurityException
    {
        mTag = 0;
        mSummary = null;
        mHistory = null;
        mEnumerationIndex = 0;
        mRecycledSummaryEntry = null;
        mRecycledHistoryEntry = null;
        mSession = android.net.INetworkStatsService.Stub.asInterface(ServiceManager.getService("netstats")).openSessionForUsageStats(i, context.getOpPackageName());
        mCloseGuard.open("close");
        mTemplate = networktemplate;
        mStartTimeStamp = l;
        mEndTimeStamp = l1;
    }

    private void fillBucketFromSummaryEntry(Bucket bucket)
    {
        Bucket._2D_set10(bucket, Bucket._2D_wrap4(mRecycledSummaryEntry.uid));
        Bucket._2D_set7(bucket, Bucket._2D_wrap3(mRecycledSummaryEntry.tag));
        Bucket._2D_set6(bucket, Bucket._2D_wrap2(mRecycledSummaryEntry.set));
        Bucket._2D_set2(bucket, Bucket._2D_wrap0(mRecycledSummaryEntry.metered));
        Bucket._2D_set3(bucket, Bucket._2D_wrap1(mRecycledSummaryEntry.roaming));
        Bucket._2D_set0(bucket, mStartTimeStamp);
        Bucket._2D_set1(bucket, mEndTimeStamp);
        Bucket._2D_set4(bucket, mRecycledSummaryEntry.rxBytes);
        Bucket._2D_set5(bucket, mRecycledSummaryEntry.rxPackets);
        Bucket._2D_set8(bucket, mRecycledSummaryEntry.txBytes);
        Bucket._2D_set9(bucket, mRecycledSummaryEntry.txPackets);
    }

    private boolean getNextHistoryBucket(Bucket bucket)
    {
        if(bucket != null && mHistory != null)
        {
            if(mEnumerationIndex < mHistory.size())
            {
                NetworkStatsHistory networkstatshistory = mHistory;
                int i = mEnumerationIndex;
                mEnumerationIndex = i + 1;
                mRecycledHistoryEntry = networkstatshistory.getValues(i, mRecycledHistoryEntry);
                Bucket._2D_set10(bucket, Bucket._2D_wrap4(getUid()));
                Bucket._2D_set7(bucket, Bucket._2D_wrap3(mTag));
                Bucket._2D_set6(bucket, -1);
                Bucket._2D_set2(bucket, -1);
                Bucket._2D_set3(bucket, -1);
                Bucket._2D_set0(bucket, mRecycledHistoryEntry.bucketStart);
                Bucket._2D_set1(bucket, mRecycledHistoryEntry.bucketStart + mRecycledHistoryEntry.bucketDuration);
                Bucket._2D_set4(bucket, mRecycledHistoryEntry.rxBytes);
                Bucket._2D_set5(bucket, mRecycledHistoryEntry.rxPackets);
                Bucket._2D_set8(bucket, mRecycledHistoryEntry.txBytes);
                Bucket._2D_set9(bucket, mRecycledHistoryEntry.txPackets);
                return true;
            }
            if(hasNextUid())
            {
                stepHistory();
                return getNextHistoryBucket(bucket);
            }
        }
        return false;
    }

    private boolean getNextSummaryBucket(Bucket bucket)
    {
        if(bucket != null && mEnumerationIndex < mSummary.size())
        {
            android.net.NetworkStats networkstats = mSummary;
            int i = mEnumerationIndex;
            mEnumerationIndex = i + 1;
            mRecycledSummaryEntry = networkstats.getValues(i, mRecycledSummaryEntry);
            fillBucketFromSummaryEntry(bucket);
            return true;
        } else
        {
            return false;
        }
    }

    private int getUid()
    {
        if(isUidEnumeration())
        {
            if(mUidOrUidIndex < 0 || mUidOrUidIndex >= mUids.length)
                throw new IndexOutOfBoundsException((new StringBuilder()).append("Index=").append(mUidOrUidIndex).append(" mUids.length=").append(mUids.length).toString());
            else
                return mUids[mUidOrUidIndex];
        } else
        {
            return mUidOrUidIndex;
        }
    }

    private boolean hasNextUid()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(isUidEnumeration())
        {
            flag1 = flag;
            if(mUidOrUidIndex + 1 < mUids.length)
                flag1 = true;
        }
        return flag1;
    }

    private boolean isUidEnumeration()
    {
        boolean flag;
        if(mUids != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void setSingleUidTag(int i, int j)
    {
        mUidOrUidIndex = i;
        mTag = j;
    }

    private void stepHistory()
    {
        if(hasNextUid())
        {
            stepUid();
            mHistory = null;
            try
            {
                mHistory = mSession.getHistoryIntervalForUid(mTemplate, getUid(), -1, 0, -1, mStartTimeStamp, mEndTimeStamp);
            }
            catch(RemoteException remoteexception)
            {
                Log.w("NetworkStats", remoteexception);
            }
            mEnumerationIndex = 0;
        }
    }

    private void stepUid()
    {
        if(mUids != null)
            mUidOrUidIndex = mUidOrUidIndex + 1;
    }

    public void close()
    {
        if(mSession != null)
            try
            {
                mSession.close();
            }
            catch(RemoteException remoteexception)
            {
                Log.w("NetworkStats", remoteexception);
            }
        mSession = null;
        if(mCloseGuard != null)
            mCloseGuard.close();
    }

    protected void finalize()
        throws Throwable
    {
        if(mCloseGuard != null)
            mCloseGuard.warnIfOpen();
        close();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    Bucket getDeviceSummaryForNetwork()
        throws RemoteException
    {
        mSummary = mSession.getDeviceSummaryForNetwork(mTemplate, mStartTimeStamp, mEndTimeStamp);
        mEnumerationIndex = mSummary.size();
        return getSummaryAggregate();
    }

    public boolean getNextBucket(Bucket bucket)
    {
        if(mSummary != null)
            return getNextSummaryBucket(bucket);
        else
            return getNextHistoryBucket(bucket);
    }

    Bucket getSummaryAggregate()
    {
        if(mSummary == null)
            return null;
        Bucket bucket = new Bucket();
        if(mRecycledSummaryEntry == null)
            mRecycledSummaryEntry = new android.net.Entry();
        mSummary.getTotal(mRecycledSummaryEntry);
        fillBucketFromSummaryEntry(bucket);
        return bucket;
    }

    public boolean hasNextBucket()
    {
        boolean flag = true;
        boolean flag1 = true;
        if(mSummary != null)
        {
            if(mEnumerationIndex >= mSummary.size())
                flag1 = false;
            return flag1;
        }
        if(mHistory != null)
        {
            boolean flag2 = flag;
            if(mEnumerationIndex >= mHistory.size())
                flag2 = hasNextUid();
            return flag2;
        } else
        {
            return false;
        }
    }

    void startHistoryEnumeration(int i)
    {
        startHistoryEnumeration(i, 0);
    }

    void startHistoryEnumeration(int i, int j)
    {
        mHistory = null;
        try
        {
            mHistory = mSession.getHistoryIntervalForUid(mTemplate, i, -1, j, -1, mStartTimeStamp, mEndTimeStamp);
            setSingleUidTag(i, j);
        }
        catch(RemoteException remoteexception)
        {
            Log.w("NetworkStats", remoteexception);
        }
        mEnumerationIndex = 0;
    }

    void startSummaryEnumeration()
        throws RemoteException
    {
        mSummary = mSession.getSummaryForAllUid(mTemplate, mStartTimeStamp, mEndTimeStamp, false);
        mEnumerationIndex = 0;
    }

    void startUserUidEnumeration()
        throws RemoteException
    {
        int ai[];
        IntArray intarray;
        int i;
        int j;
        ai = mSession.getRelevantUids();
        intarray = new IntArray(ai.length);
        i = ai.length;
        j = 0;
_L3:
        if(j >= i) goto _L2; else goto _L1
_L1:
        int k = ai[j];
        NetworkStatsHistory networkstatshistory = mSession.getHistoryIntervalForUid(mTemplate, k, -1, 0, -1, mStartTimeStamp, mEndTimeStamp);
        if(networkstatshistory != null)
            try
            {
                if(networkstatshistory.size() > 0)
                    intarray.add(k);
            }
            catch(RemoteException remoteexception)
            {
                Log.w("NetworkStats", (new StringBuilder()).append("Error while getting history of uid ").append(k).toString(), remoteexception);
            }
        j++;
          goto _L3
_L2:
        mUids = intarray.toArray();
        mUidOrUidIndex = -1;
        stepHistory();
        return;
    }

    private static final String TAG = "NetworkStats";
    private final CloseGuard mCloseGuard = CloseGuard.get();
    private final long mEndTimeStamp;
    private int mEnumerationIndex;
    private NetworkStatsHistory mHistory;
    private android.net.NetworkStatsHistory.Entry mRecycledHistoryEntry;
    private android.net.Entry mRecycledSummaryEntry;
    private INetworkStatsSession mSession;
    private final long mStartTimeStamp;
    private android.net.NetworkStats mSummary;
    private int mTag;
    private NetworkTemplate mTemplate;
    private int mUidOrUidIndex;
    private int mUids[];
}
