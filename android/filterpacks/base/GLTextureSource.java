// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.base;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;

public class GLTextureSource extends Filter
{

    public GLTextureSource(String s)
    {
        super(s);
        mRepeatFrame = false;
        mTimestamp = -1L;
    }

    public void fieldPortValueUpdated(String s, FilterContext filtercontext)
    {
        if(mFrame != null)
        {
            mFrame.release();
            mFrame = null;
        }
    }

    public void process(FilterContext filtercontext)
    {
        if(mFrame == null)
        {
            android.filterfw.core.MutableFrameFormat mutableframeformat = ImageFormat.create(mWidth, mHeight, 3, 3);
            mFrame = filtercontext.getFrameManager().newBoundFrame(mutableframeformat, 100, mTexId);
            mFrame.setTimestamp(mTimestamp);
        }
        pushOutput("frame", mFrame);
        if(!mRepeatFrame)
            closeOutputPort("frame");
    }

    public void setupPorts()
    {
        addOutputPort("frame", ImageFormat.create(3, 3));
    }

    public void tearDown(FilterContext filtercontext)
    {
        if(mFrame != null)
            mFrame.release();
    }

    private Frame mFrame;
    private int mHeight;
    private boolean mRepeatFrame;
    private int mTexId;
    private long mTimestamp;
    private int mWidth;
}
