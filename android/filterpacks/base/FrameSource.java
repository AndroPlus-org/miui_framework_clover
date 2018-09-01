// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.base;

import android.filterfw.core.*;

public class FrameSource extends Filter
{

    public FrameSource(String s)
    {
        super(s);
        mFrame = null;
        mRepeatFrame = false;
    }

    public void process(FilterContext filtercontext)
    {
        if(mFrame != null)
            pushOutput("frame", mFrame);
        if(!mRepeatFrame)
            closeOutputPort("frame");
    }

    public void setupPorts()
    {
        addOutputPort("frame", mFormat);
    }

    private FrameFormat mFormat;
    private Frame mFrame;
    private boolean mRepeatFrame;
}
