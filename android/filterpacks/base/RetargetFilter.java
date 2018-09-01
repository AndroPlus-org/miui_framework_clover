// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.base;

import android.filterfw.core.*;

public class RetargetFilter extends Filter
{

    public RetargetFilter(String s)
    {
        super(s);
        mTarget = -1;
    }

    public FrameFormat getOutputFormat(String s, FrameFormat frameformat)
    {
        s = frameformat.mutableCopy();
        s.setTarget(mTarget);
        return s;
    }

    public void process(FilterContext filtercontext)
    {
        Frame frame = pullInput("frame");
        filtercontext = filtercontext.getFrameManager().duplicateFrameToTarget(frame, mTarget);
        pushOutput("frame", filtercontext);
        filtercontext.release();
    }

    public void setupPorts()
    {
        mTarget = FrameFormat.readTargetString(mTargetString);
        addInputPort("frame");
        addOutputBasedOnInput("frame", "frame");
    }

    private MutableFrameFormat mOutputFormat;
    private int mTarget;
    private String mTargetString;
}
