// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.base;

import android.filterfw.core.*;

public class FrameFetch extends Filter
{

    public FrameFetch(String s)
    {
        super(s);
        mRepeatFrame = false;
    }

    public void process(FilterContext filtercontext)
    {
        filtercontext = filtercontext.fetchFrame(mKey);
        if(filtercontext != null)
        {
            pushOutput("frame", filtercontext);
            if(!mRepeatFrame)
                closeOutputPort("frame");
        } else
        {
            delayNextProcess(250);
        }
    }

    public void setupPorts()
    {
        FrameFormat frameformat;
        if(mFormat == null)
            frameformat = FrameFormat.unspecified();
        else
            frameformat = mFormat;
        addOutputPort("frame", frameformat);
    }

    private FrameFormat mFormat;
    private String mKey;
    private boolean mRepeatFrame;
}
