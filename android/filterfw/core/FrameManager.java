// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;


// Referenced classes of package android.filterfw.core:
//            Frame, FrameFormat, MutableFrameFormat, FilterContext, 
//            GLEnvironment

public abstract class FrameManager
{

    public FrameManager()
    {
    }

    public Frame duplicateFrame(Frame frame)
    {
        Frame frame1 = newFrame(frame.getFormat());
        frame1.setDataFromFrame(frame);
        return frame1;
    }

    public Frame duplicateFrameToTarget(Frame frame, int i)
    {
        Object obj = frame.getFormat().mutableCopy();
        ((MutableFrameFormat) (obj)).setTarget(i);
        obj = newFrame(((FrameFormat) (obj)));
        ((Frame) (obj)).setDataFromFrame(frame);
        return ((Frame) (obj));
    }

    public FilterContext getContext()
    {
        return mContext;
    }

    public GLEnvironment getGLEnvironment()
    {
        GLEnvironment glenvironment = null;
        if(mContext != null)
            glenvironment = mContext.getGLEnvironment();
        return glenvironment;
    }

    public abstract Frame newBoundFrame(FrameFormat frameformat, int i, long l);

    public abstract Frame newFrame(FrameFormat frameformat);

    public abstract Frame releaseFrame(Frame frame);

    public abstract Frame retainFrame(Frame frame);

    void setContext(FilterContext filtercontext)
    {
        mContext = filtercontext;
    }

    public void tearDown()
    {
    }

    private FilterContext mContext;
}
