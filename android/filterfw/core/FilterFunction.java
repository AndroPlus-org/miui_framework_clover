// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import java.util.Iterator;

// Referenced classes of package android.filterfw.core:
//            Filter, OutputPort, FilterContext, GLEnvironment, 
//            KeyValueMap, Frame, StreamPort

public class FilterFunction
{
    private class FrameHolderPort extends StreamPort
    {

        final FilterFunction this$0;

        public FrameHolderPort()
        {
            this$0 = FilterFunction.this;
            super(null, "holder");
        }
    }


    public FilterFunction(FilterContext filtercontext, Filter filter)
    {
        mFilterIsSetup = false;
        mFilterContext = filtercontext;
        mFilter = filter;
    }

    private void connectFilterOutputs()
    {
        int i = 0;
        mResultHolders = new FrameHolderPort[mFilter.getNumberOfOutputs()];
        for(Iterator iterator = mFilter.getOutputPorts().iterator(); iterator.hasNext();)
        {
            OutputPort outputport = (OutputPort)iterator.next();
            mResultHolders[i] = new FrameHolderPort();
            outputport.connectTo(mResultHolders[i]);
            i++;
        }

    }

    public void close()
    {
        mFilter.performClose(mFilterContext);
    }

    public Frame execute(KeyValueMap keyvaluemap)
    {
        int i = mFilter.getNumberOfOutputs();
        if(i > 1)
            throw new RuntimeException((new StringBuilder()).append("Calling execute on filter ").append(mFilter).append(" with multiple ").append("outputs! Use executeMulti() instead!").toString());
        if(!mFilterIsSetup)
        {
            connectFilterOutputs();
            mFilterIsSetup = true;
        }
        boolean flag = false;
        GLEnvironment glenvironment = mFilterContext.getGLEnvironment();
        boolean flag1 = flag;
        if(glenvironment != null)
        {
            flag1 = flag;
            if(glenvironment.isActive() ^ true)
            {
                glenvironment.activate();
                flag1 = true;
            }
        }
        for(keyvaluemap = keyvaluemap.entrySet().iterator(); keyvaluemap.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)keyvaluemap.next();
            if(entry.getValue() instanceof Frame)
                mFilter.pushInputFrame((String)entry.getKey(), (Frame)entry.getValue());
            else
                mFilter.pushInputValue((String)entry.getKey(), entry.getValue());
        }

        if(mFilter.getStatus() != 3)
            mFilter.openOutputs();
        mFilter.performProcess(mFilterContext);
        Object obj = null;
        keyvaluemap = obj;
        if(i == 1)
        {
            keyvaluemap = obj;
            if(mResultHolders[0].hasFrame())
                keyvaluemap = mResultHolders[0].pullFrame();
        }
        if(flag1)
            glenvironment.deactivate();
        return keyvaluemap;
    }

    public transient Frame executeWithArgList(Object aobj[])
    {
        return execute(KeyValueMap.fromKeyValues(aobj));
    }

    public FilterContext getContext()
    {
        return mFilterContext;
    }

    public Filter getFilter()
    {
        return mFilter;
    }

    public void setInputFrame(String s, Frame frame)
    {
        mFilter.setInputFrame(s, frame);
    }

    public void setInputValue(String s, Object obj)
    {
        mFilter.setInputValue(s, obj);
    }

    public void tearDown()
    {
        mFilter.performTearDown(mFilterContext);
        mFilter = null;
    }

    public String toString()
    {
        return mFilter.getName();
    }

    private Filter mFilter;
    private FilterContext mFilterContext;
    private boolean mFilterIsSetup;
    private FrameHolderPort mResultHolders[];
}
