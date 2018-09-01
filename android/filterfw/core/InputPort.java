// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;


// Referenced classes of package android.filterfw.core:
//            FilterPort, OutputPort, Filter, FrameFormat, 
//            FilterContext

public abstract class InputPort extends FilterPort
{

    public InputPort(Filter filter, String s)
    {
        super(filter, s);
    }

    public boolean acceptsFrame()
    {
        return hasFrame() ^ true;
    }

    public void close()
    {
        if(mSourcePort != null && mSourcePort.isOpen())
            mSourcePort.close();
        super.close();
    }

    public boolean filterMustClose()
    {
        boolean flag;
        if(!isOpen() && isBlocking())
            flag = hasFrame() ^ true;
        else
            flag = false;
        return flag;
    }

    public Filter getSourceFilter()
    {
        Filter filter = null;
        if(mSourcePort != null)
            filter = mSourcePort.getFilter();
        return filter;
    }

    public FrameFormat getSourceFormat()
    {
        FrameFormat frameformat;
        if(mSourcePort != null)
            frameformat = mSourcePort.getPortFormat();
        else
            frameformat = getPortFormat();
        return frameformat;
    }

    public OutputPort getSourcePort()
    {
        return mSourcePort;
    }

    public Object getTarget()
    {
        return null;
    }

    public boolean isConnected()
    {
        boolean flag;
        if(mSourcePort != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isReady()
    {
        boolean flag;
        if(!hasFrame())
            flag = isBlocking() ^ true;
        else
            flag = true;
        return flag;
    }

    public void open()
    {
        super.open();
        if(mSourcePort != null && mSourcePort.isOpen() ^ true)
            mSourcePort.open();
    }

    public void setSourcePort(OutputPort outputport)
    {
        if(mSourcePort != null)
        {
            throw new RuntimeException((new StringBuilder()).append(this).append(" already connected to ").append(mSourcePort).append("!").toString());
        } else
        {
            mSourcePort = outputport;
            return;
        }
    }

    public abstract void transfer(FilterContext filtercontext);

    protected OutputPort mSourcePort;
}
