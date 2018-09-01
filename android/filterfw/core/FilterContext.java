// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import java.util.*;

// Referenced classes of package android.filterfw.core:
//            Frame, FrameManager, FilterGraph, GLEnvironment, 
//            Filter

public class FilterContext
{
    public static interface OnFrameReceivedListener
    {

        public abstract void onFrameReceived(Filter filter, Frame frame, Object obj);
    }


    public FilterContext()
    {
        mStoredFrames = new HashMap();
        mGraphs = new HashSet();
    }

    final void addGraph(FilterGraph filtergraph)
    {
        mGraphs.add(filtergraph);
    }

    public Frame fetchFrame(String s)
    {
        this;
        JVM INSTR monitorenter ;
        s = (Frame)mStoredFrames.get(s);
        if(s == null)
            break MISSING_BLOCK_LABEL_22;
        s.onFrameFetch();
        this;
        JVM INSTR monitorexit ;
        return s;
        s;
        throw s;
    }

    public FrameManager getFrameManager()
    {
        return mFrameManager;
    }

    public GLEnvironment getGLEnvironment()
    {
        return mGLEnvironment;
    }

    public void initGLEnvironment(GLEnvironment glenvironment)
    {
        if(mGLEnvironment == null)
        {
            mGLEnvironment = glenvironment;
            return;
        } else
        {
            throw new RuntimeException("Attempting to re-initialize GL Environment for FilterContext!");
        }
    }

    public void removeFrame(String s)
    {
        this;
        JVM INSTR monitorenter ;
        Frame frame = (Frame)mStoredFrames.get(s);
        if(frame == null)
            break MISSING_BLOCK_LABEL_32;
        mStoredFrames.remove(s);
        frame.release();
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public void setFrameManager(FrameManager framemanager)
    {
        if(framemanager == null)
            throw new NullPointerException("Attempting to set null FrameManager!");
        if(framemanager.getContext() != null)
        {
            throw new IllegalArgumentException("Attempting to set FrameManager which is already bound to another FilterContext!");
        } else
        {
            mFrameManager = framemanager;
            mFrameManager.setContext(this);
            return;
        }
    }

    public void storeFrame(String s, Frame frame)
    {
        this;
        JVM INSTR monitorenter ;
        Frame frame1 = fetchFrame(s);
        if(frame1 == null)
            break MISSING_BLOCK_LABEL_17;
        frame1.release();
        frame.onFrameStore();
        mStoredFrames.put(s, frame.retain());
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public void tearDown()
    {
        this;
        JVM INSTR monitorenter ;
        for(Iterator iterator = mStoredFrames.values().iterator(); iterator.hasNext(); ((Frame)iterator.next()).release());
        break MISSING_BLOCK_LABEL_45;
        Exception exception;
        exception;
        throw exception;
        mStoredFrames.clear();
        for(Iterator iterator1 = mGraphs.iterator(); iterator1.hasNext(); ((FilterGraph)iterator1.next()).tearDown(this));
        mGraphs.clear();
        if(mFrameManager != null)
        {
            mFrameManager.tearDown();
            mFrameManager = null;
        }
        if(mGLEnvironment != null)
        {
            mGLEnvironment.tearDown();
            mGLEnvironment = null;
        }
        this;
        JVM INSTR monitorexit ;
    }

    private FrameManager mFrameManager;
    private GLEnvironment mGLEnvironment;
    private Set mGraphs;
    private HashMap mStoredFrames;
}
