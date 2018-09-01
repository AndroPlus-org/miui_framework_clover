// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.base;

import android.filterfw.core.*;
import android.filterfw.format.ObjectFormat;

public class ObjectSource extends Filter
{

    public ObjectSource(String s)
    {
        super(s);
        mOutputFormat = FrameFormat.unspecified();
        mRepeatFrame = false;
    }

    public void fieldPortValueUpdated(String s, FilterContext filtercontext)
    {
        if(s.equals("object") && mFrame != null)
        {
            mFrame.release();
            mFrame = null;
        }
    }

    public void process(FilterContext filtercontext)
    {
        if(mFrame == null)
        {
            if(mObject == null)
                throw new NullPointerException("ObjectSource producing frame with no object set!");
            android.filterfw.core.MutableFrameFormat mutableframeformat = ObjectFormat.fromObject(mObject, 1);
            mFrame = filtercontext.getFrameManager().newFrame(mutableframeformat);
            mFrame.setObjectValue(mObject);
            mFrame.setTimestamp(-1L);
        }
        pushOutput("frame", mFrame);
        if(!mRepeatFrame)
            closeOutputPort("frame");
    }

    public void setupPorts()
    {
        addOutputPort("frame", mOutputFormat);
    }

    public void tearDown(FilterContext filtercontext)
    {
        mFrame.release();
    }

    private Frame mFrame;
    private Object mObject;
    private FrameFormat mOutputFormat;
    boolean mRepeatFrame;
}
