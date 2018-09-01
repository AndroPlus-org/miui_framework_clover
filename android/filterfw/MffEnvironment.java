// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw;

import android.filterfw.core.CachedFrameManager;
import android.filterfw.core.FilterContext;
import android.filterfw.core.FrameManager;
import android.filterfw.core.GLEnvironment;

public class MffEnvironment
{

    protected MffEnvironment(FrameManager framemanager)
    {
        Object obj = framemanager;
        if(framemanager == null)
            obj = new CachedFrameManager();
        mContext = new FilterContext();
        mContext.setFrameManager(((FrameManager) (obj)));
    }

    public void activateGLEnvironment()
    {
        if(mContext.getGLEnvironment() != null)
        {
            mContext.getGLEnvironment().activate();
            return;
        } else
        {
            throw new NullPointerException("No GLEnvironment in place to activate!");
        }
    }

    public void createGLEnvironment()
    {
        GLEnvironment glenvironment = new GLEnvironment();
        glenvironment.initWithNewContext();
        setGLEnvironment(glenvironment);
    }

    public void deactivateGLEnvironment()
    {
        if(mContext.getGLEnvironment() != null)
        {
            mContext.getGLEnvironment().deactivate();
            return;
        } else
        {
            throw new NullPointerException("No GLEnvironment in place to deactivate!");
        }
    }

    public FilterContext getContext()
    {
        return mContext;
    }

    public void setGLEnvironment(GLEnvironment glenvironment)
    {
        mContext.initGLEnvironment(glenvironment);
    }

    private FilterContext mContext;
}
