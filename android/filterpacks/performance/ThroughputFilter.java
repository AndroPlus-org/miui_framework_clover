// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.performance;

import android.filterfw.core.*;
import android.filterfw.format.ObjectFormat;
import android.os.SystemClock;

// Referenced classes of package android.filterpacks.performance:
//            Throughput

public class ThroughputFilter extends Filter
{

    public ThroughputFilter(String s)
    {
        super(s);
        mPeriod = 5;
        mLastTime = 0L;
        mTotalFrameCount = 0;
        mPeriodFrameCount = 0;
    }

    public FrameFormat getOutputFormat(String s, FrameFormat frameformat)
    {
        return frameformat;
    }

    public void open(FilterContext filtercontext)
    {
        mTotalFrameCount = 0;
        mPeriodFrameCount = 0;
        mLastTime = 0L;
    }

    public void process(FilterContext filtercontext)
    {
        Object obj = pullInput("frame");
        pushOutput("frame", ((Frame) (obj)));
        mTotalFrameCount = mTotalFrameCount + 1;
        mPeriodFrameCount = mPeriodFrameCount + 1;
        if(mLastTime == 0L)
            mLastTime = SystemClock.elapsedRealtime();
        long l = SystemClock.elapsedRealtime();
        if(l - mLastTime >= (long)(mPeriod * 1000))
        {
            obj = ((Frame) (obj)).getFormat();
            int i = ((FrameFormat) (obj)).getWidth();
            int j = ((FrameFormat) (obj)).getHeight();
            obj = new Throughput(mTotalFrameCount, mPeriodFrameCount, mPeriod, i * j);
            filtercontext = filtercontext.getFrameManager().newFrame(mOutputFormat);
            filtercontext.setObjectValue(obj);
            pushOutput("throughput", filtercontext);
            mLastTime = l;
            mPeriodFrameCount = 0;
        }
    }

    public void setupPorts()
    {
        addInputPort("frame");
        mOutputFormat = ObjectFormat.fromClass(android/filterpacks/performance/Throughput, 1);
        addOutputBasedOnInput("frame", "frame");
        addOutputPort("throughput", mOutputFormat);
    }

    private long mLastTime;
    private FrameFormat mOutputFormat;
    private int mPeriod;
    private int mPeriodFrameCount;
    private int mTotalFrameCount;
}
