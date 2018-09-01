// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;


// Referenced classes of package android.filterfw.core:
//            FilterPort, InputPort, Filter, Frame

public class OutputPort extends FilterPort
{

    public OutputPort(Filter filter, String s)
    {
        super(filter, s);
    }

    public void clear()
    {
        if(mTargetPort != null)
            mTargetPort.clear();
    }

    public void close()
    {
        super.close();
        if(mTargetPort != null && mTargetPort.isOpen())
            mTargetPort.close();
    }

    public void connectTo(InputPort inputport)
    {
        if(mTargetPort != null)
        {
            throw new RuntimeException((new StringBuilder()).append(this).append(" already connected to ").append(mTargetPort).append("!").toString());
        } else
        {
            mTargetPort = inputport;
            mTargetPort.setSourcePort(this);
            return;
        }
    }

    public boolean filterMustClose()
    {
        boolean flag;
        if(!isOpen())
            flag = isBlocking();
        else
            flag = false;
        return flag;
    }

    public InputPort getBasePort()
    {
        return mBasePort;
    }

    public Filter getTargetFilter()
    {
        Filter filter = null;
        if(mTargetPort != null)
            filter = mTargetPort.getFilter();
        return filter;
    }

    public InputPort getTargetPort()
    {
        return mTargetPort;
    }

    public boolean hasFrame()
    {
        boolean flag;
        if(mTargetPort == null)
            flag = false;
        else
            flag = mTargetPort.hasFrame();
        return flag;
    }

    public boolean isConnected()
    {
        boolean flag;
        if(mTargetPort != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isReady()
    {
        boolean flag;
        if(!isOpen() || !mTargetPort.acceptsFrame())
            flag = isBlocking() ^ true;
        else
            flag = true;
        return flag;
    }

    public void open()
    {
        super.open();
        if(mTargetPort != null && mTargetPort.isOpen() ^ true)
            mTargetPort.open();
    }

    public Frame pullFrame()
    {
        throw new RuntimeException((new StringBuilder()).append("Cannot pull frame on ").append(this).append("!").toString());
    }

    public void pushFrame(Frame frame)
    {
        if(mTargetPort == null)
        {
            throw new RuntimeException((new StringBuilder()).append("Attempting to push frame on unconnected port: ").append(this).append("!").toString());
        } else
        {
            mTargetPort.pushFrame(frame);
            return;
        }
    }

    public void setBasePort(InputPort inputport)
    {
        mBasePort = inputport;
    }

    public void setFrame(Frame frame)
    {
        assertPortIsOpen();
        if(mTargetPort == null)
        {
            throw new RuntimeException((new StringBuilder()).append("Attempting to set frame on unconnected port: ").append(this).append("!").toString());
        } else
        {
            mTargetPort.setFrame(frame);
            return;
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append("output ").append(super.toString()).toString();
    }

    protected InputPort mBasePort;
    protected InputPort mTargetPort;
}
