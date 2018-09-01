// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;


// Referenced classes of package android.filterfw.core:
//            InputPort, Frame, Filter, FilterContext

public class StreamPort extends InputPort
{

    public StreamPort(Filter filter, String s)
    {
        super(filter, s);
    }

    protected void assignFrame(Frame frame, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        assertPortIsOpen();
        checkFrameType(frame, flag);
        if(!flag) goto _L2; else goto _L1
_L1:
        if(mFrame != null)
            mFrame.release();
_L4:
        mFrame = frame.retain();
        mFrame.markReadOnly();
        mPersistent = flag;
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(mFrame == null) goto _L4; else goto _L3
_L3:
        frame = JVM INSTR new #40  <Class RuntimeException>;
        StringBuilder stringbuilder = JVM INSTR new #42  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        frame.RuntimeException(stringbuilder.append("Attempting to push more than one frame on port: ").append(this).append("!").toString());
        throw frame;
        frame;
        this;
        JVM INSTR monitorexit ;
        throw frame;
    }

    public void clear()
    {
        if(mFrame != null)
        {
            mFrame.release();
            mFrame = null;
        }
    }

    public boolean hasFrame()
    {
        this;
        JVM INSTR monitorenter ;
        Frame frame = mFrame;
        boolean flag;
        if(frame != null)
            flag = true;
        else
            flag = false;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public Frame pullFrame()
    {
        this;
        JVM INSTR monitorenter ;
        if(mFrame == null)
        {
            RuntimeException runtimeexception = JVM INSTR new #40  <Class RuntimeException>;
            StringBuilder stringbuilder = JVM INSTR new #42  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            runtimeexception.RuntimeException(stringbuilder.append("No frame available to pull on port: ").append(this).append("!").toString());
            throw runtimeexception;
        }
        break MISSING_BLOCK_LABEL_50;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        Frame frame;
        frame = mFrame;
        if(!mPersistent)
            break MISSING_BLOCK_LABEL_74;
        mFrame.retain();
_L1:
        this;
        JVM INSTR monitorexit ;
        return frame;
        mFrame = null;
          goto _L1
    }

    public void pushFrame(Frame frame)
    {
        assignFrame(frame, false);
    }

    public void setFrame(Frame frame)
    {
        assignFrame(frame, true);
    }

    public String toString()
    {
        return (new StringBuilder()).append("input ").append(super.toString()).toString();
    }

    public void transfer(FilterContext filtercontext)
    {
        this;
        JVM INSTR monitorenter ;
        if(mFrame != null)
            checkFrameManager(mFrame, filtercontext);
        this;
        JVM INSTR monitorexit ;
        return;
        filtercontext;
        throw filtercontext;
    }

    private Frame mFrame;
    private boolean mPersistent;
}
