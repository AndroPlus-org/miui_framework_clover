// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.legacy;

import android.os.SystemClock;
import android.util.Log;
import java.io.*;
import java.util.*;

class PerfMeasurement
{

    public PerfMeasurement()
    {
        mCompletedQueryCount = 0;
        mCollectedGpuDurations = new ArrayList();
        mCollectedCpuDurations = new ArrayList();
        mCollectedTimestamps = new ArrayList();
        mTimestampQueue = new LinkedList();
        mCpuDurationsQueue = new LinkedList();
        mNativeContext = nativeCreateContext(3);
    }

    public PerfMeasurement(int i)
    {
        mCompletedQueryCount = 0;
        mCollectedGpuDurations = new ArrayList();
        mCollectedCpuDurations = new ArrayList();
        mCollectedTimestamps = new ArrayList();
        mTimestampQueue = new LinkedList();
        mCpuDurationsQueue = new LinkedList();
        if(i < 1)
        {
            throw new IllegalArgumentException("maxQueries is less than 1");
        } else
        {
            mNativeContext = nativeCreateContext(i);
            return;
        }
    }

    private long getNextGlDuration()
    {
        long l = nativeGetNextGlDuration(mNativeContext);
        if(l > 0L)
            mCompletedQueryCount = mCompletedQueryCount + 1;
        return l;
    }

    public static boolean isGlTimingSupported()
    {
        return nativeQuerySupport();
    }

    private static native long nativeCreateContext(int i);

    private static native void nativeDeleteContext(long l);

    protected static native long nativeGetNextGlDuration(long l);

    private static native boolean nativeQuerySupport();

    protected static native void nativeStartGlTimer(long l);

    protected static native void nativeStopGlTimer(long l);

    public void addTimestamp(long l)
    {
        mTimestampQueue.add(Long.valueOf(l));
    }

    public void dumpPerformanceData(String s)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        Object obj4;
        obj4 = JVM INSTR new #102 <Class BufferedWriter>;
        FileWriter filewriter = JVM INSTR new #104 <Class FileWriter>;
        filewriter.FileWriter(s);
        ((BufferedWriter) (obj4)).BufferedWriter(filewriter);
        ((BufferedWriter) (obj4)).write("timestamp gpu_duration cpu_duration\n");
        int i = 0;
_L2:
        if(i >= mCollectedGpuDurations.size())
            break; /* Loop/switch isn't completed */
        ((BufferedWriter) (obj4)).write(String.format("%d %d %d\n", new Object[] {
            mCollectedTimestamps.get(i), mCollectedGpuDurations.get(i), mCollectedCpuDurations.get(i)
        }));
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        mCollectedTimestamps.clear();
        mCollectedGpuDurations.clear();
        mCollectedCpuDurations.clear();
        Throwable throwable;
        throwable = obj1;
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_148;
        ((BufferedWriter) (obj4)).close();
        throwable = obj1;
_L5:
        if(throwable == null) goto _L4; else goto _L3
_L3:
        try
        {
            throw throwable;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj4) { }
_L6:
        Log.e("PerfMeasurement", (new StringBuilder()).append("Error writing data dump to ").append(s).append(":").append(obj4).toString());
_L4:
        return;
        throwable;
          goto _L5
        Object obj5;
        obj5;
        obj4 = obj3;
_L10:
        throw obj5;
        Exception exception;
        exception;
        obj = obj5;
        obj5 = exception;
_L9:
        exception = ((Exception) (obj));
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_236;
        ((BufferedWriter) (obj4)).close();
        exception = ((Exception) (obj));
_L7:
        if(exception == null)
            break MISSING_BLOCK_LABEL_283;
        try
        {
            throw exception;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj4) { }
          goto _L6
        obj4;
label0:
        {
            if(obj != null)
                break label0;
            exception = ((Exception) (obj4));
        }
          goto _L7
        exception = ((Exception) (obj));
        if(obj == obj4) goto _L7; else goto _L8
_L8:
        ((Throwable) (obj)).addSuppressed(((Throwable) (obj4)));
        exception = ((Exception) (obj));
          goto _L7
        throw obj5;
        obj5;
        obj4 = obj2;
          goto _L9
        obj5;
          goto _L9
        obj5;
          goto _L10
    }

    protected void finalize()
    {
        nativeDeleteContext(mNativeContext);
    }

    public int getCompletedQueryCount()
    {
        return mCompletedQueryCount;
    }

    public void startTimer()
    {
        nativeStartGlTimer(mNativeContext);
        mStartTimeNs = SystemClock.elapsedRealtimeNanos();
    }

    public void stopTimer()
    {
        long l = -1L;
        long l1 = SystemClock.elapsedRealtimeNanos();
        mCpuDurationsQueue.add(Long.valueOf(l1 - mStartTimeNs));
        nativeStopGlTimer(mNativeContext);
        long l3 = getNextGlDuration();
        if(l3 > 0L)
        {
            mCollectedGpuDurations.add(Long.valueOf(l3));
            ArrayList arraylist = mCollectedTimestamps;
            long l2;
            if(mTimestampQueue.isEmpty())
                l2 = -1L;
            else
                l2 = ((Long)mTimestampQueue.poll()).longValue();
            arraylist.add(Long.valueOf(l2));
            arraylist = mCollectedCpuDurations;
            if(mCpuDurationsQueue.isEmpty())
                l2 = l;
            else
                l2 = ((Long)mCpuDurationsQueue.poll()).longValue();
            arraylist.add(Long.valueOf(l2));
        }
        if(l3 == -2L)
        {
            if(!mTimestampQueue.isEmpty())
                mTimestampQueue.poll();
            if(!mCpuDurationsQueue.isEmpty())
                mCpuDurationsQueue.poll();
        }
    }

    public static final int DEFAULT_MAX_QUERIES = 3;
    private static final long FAILED_TIMING = -2L;
    private static final long NO_DURATION_YET = -1L;
    private static final String TAG = "PerfMeasurement";
    private ArrayList mCollectedCpuDurations;
    private ArrayList mCollectedGpuDurations;
    private ArrayList mCollectedTimestamps;
    private int mCompletedQueryCount;
    private Queue mCpuDurationsQueue;
    private final long mNativeContext;
    private long mStartTimeNs;
    private Queue mTimestampQueue;
}
