// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.os.SystemClock;
import java.util.ArrayList;

// Referenced classes of package android.util:
//            Log

public class TimingLogger
{

    public TimingLogger(String s, String s1)
    {
        reset(s, s1);
    }

    public void addSplit(String s)
    {
        if(mDisabled)
        {
            return;
        } else
        {
            long l = SystemClock.elapsedRealtime();
            mSplits.add(Long.valueOf(l));
            mSplitLabels.add(s);
            return;
        }
    }

    public void dumpToLog()
    {
        if(mDisabled)
            return;
        Log.d(mTag, (new StringBuilder()).append(mLabel).append(": begin").toString());
        long l = ((Long)mSplits.get(0)).longValue();
        long l1 = l;
        for(int i = 1; i < mSplits.size(); i++)
        {
            l1 = ((Long)mSplits.get(i)).longValue();
            String s = (String)mSplitLabels.get(i);
            long l2 = ((Long)mSplits.get(i - 1)).longValue();
            Log.d(mTag, (new StringBuilder()).append(mLabel).append(":      ").append(l1 - l2).append(" ms, ").append(s).toString());
        }

        Log.d(mTag, (new StringBuilder()).append(mLabel).append(": end, ").append(l1 - l).append(" ms").toString());
    }

    public void reset()
    {
        mDisabled = Log.isLoggable(mTag, 2) ^ true;
        if(mDisabled)
            return;
        if(mSplits == null)
        {
            mSplits = new ArrayList();
            mSplitLabels = new ArrayList();
        } else
        {
            mSplits.clear();
            mSplitLabels.clear();
        }
        addSplit(null);
    }

    public void reset(String s, String s1)
    {
        mTag = s;
        mLabel = s1;
        reset();
    }

    private boolean mDisabled;
    private String mLabel;
    ArrayList mSplitLabels;
    ArrayList mSplits;
    private String mTag;
}
