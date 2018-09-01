// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import android.util.Log;

// Referenced classes of package android.filterfw.core:
//            Frame, FilterContext, FrameFormat, Filter

public abstract class FilterPort
{

    public FilterPort(Filter filter, String s)
    {
        mIsBlocking = true;
        mIsOpen = false;
        mChecksType = false;
        mName = s;
        mFilter = filter;
        mLogVerbose = Log.isLoggable("FilterPort", 2);
    }

    protected void assertPortIsOpen()
    {
        if(!isOpen())
            throw new RuntimeException((new StringBuilder()).append("Illegal operation on closed ").append(this).append("!").toString());
        else
            return;
    }

    protected void checkFrameManager(Frame frame, FilterContext filtercontext)
    {
        if(frame.getFrameManager() != null && frame.getFrameManager() != filtercontext.getFrameManager())
            throw new RuntimeException((new StringBuilder()).append("Frame ").append(frame).append(" is managed by foreign FrameManager! ").toString());
        else
            return;
    }

    protected void checkFrameType(Frame frame, boolean flag)
    {
        if((mChecksType || flag) && mPortFormat != null && frame.getFormat().isCompatibleWith(mPortFormat) ^ true)
            throw new RuntimeException((new StringBuilder()).append("Frame passed to ").append(this).append(" is of incorrect type! ").append("Expected ").append(mPortFormat).append(" but got ").append(frame.getFormat()).toString());
        else
            return;
    }

    public abstract void clear();

    public void close()
    {
        if(mIsOpen && mLogVerbose)
            Log.v("FilterPort", (new StringBuilder()).append("Closing ").append(this).toString());
        mIsOpen = false;
    }

    public abstract boolean filterMustClose();

    public Filter getFilter()
    {
        return mFilter;
    }

    public String getName()
    {
        return mName;
    }

    public FrameFormat getPortFormat()
    {
        return mPortFormat;
    }

    public abstract boolean hasFrame();

    public boolean isAttached()
    {
        boolean flag;
        if(mFilter != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isBlocking()
    {
        return mIsBlocking;
    }

    public boolean isOpen()
    {
        return mIsOpen;
    }

    public abstract boolean isReady();

    public void open()
    {
        if(!mIsOpen && mLogVerbose)
            Log.v("FilterPort", (new StringBuilder()).append("Opening ").append(this).toString());
        mIsOpen = true;
    }

    public abstract Frame pullFrame();

    public abstract void pushFrame(Frame frame);

    public void setBlocking(boolean flag)
    {
        mIsBlocking = flag;
    }

    public void setChecksType(boolean flag)
    {
        mChecksType = flag;
    }

    public abstract void setFrame(Frame frame);

    public void setPortFormat(FrameFormat frameformat)
    {
        mPortFormat = frameformat;
    }

    public String toString()
    {
        return (new StringBuilder()).append("port '").append(mName).append("' of ").append(mFilter).toString();
    }

    private static final String TAG = "FilterPort";
    protected boolean mChecksType;
    protected Filter mFilter;
    protected boolean mIsBlocking;
    protected boolean mIsOpen;
    private boolean mLogVerbose;
    protected String mName;
    protected FrameFormat mPortFormat;
}
