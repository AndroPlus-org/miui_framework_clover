// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.effect;

import android.filterfw.core.*;
import android.filterfw.io.*;

// Referenced classes of package android.media.effect:
//            FilterEffect, EffectContext

public class FilterGraphEffect extends FilterEffect
{

    public FilterGraphEffect(EffectContext effectcontext, String s, String s1, String s2, String s3, Class class1)
    {
        super(effectcontext, s);
        mInputName = s2;
        mOutputName = s3;
        mSchedulerClass = class1;
        createGraph(s1);
    }

    private void createGraph(String s)
    {
        TextGraphReader textgraphreader = new TextGraphReader();
        try
        {
            mGraph = textgraphreader.readGraphString(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException("Could not setup effect", s);
        }
        if(mGraph == null)
        {
            throw new RuntimeException("Could not setup effect");
        } else
        {
            mRunner = new SyncRunner(getFilterContext(), mGraph, mSchedulerClass);
            return;
        }
    }

    public void apply(int i, int j, int k, int l)
    {
        beginGLEffect();
        Filter filter = mGraph.getFilter(mInputName);
        if(filter != null)
        {
            filter.setInputValue("texId", Integer.valueOf(i));
            filter.setInputValue("width", Integer.valueOf(j));
            filter.setInputValue("height", Integer.valueOf(k));
            filter = mGraph.getFilter(mOutputName);
            if(filter != null)
            {
                filter.setInputValue("texId", Integer.valueOf(l));
                try
                {
                    mRunner.run();
                }
                catch(RuntimeException runtimeexception)
                {
                    throw new RuntimeException("Internal error applying effect: ", runtimeexception);
                }
                endGLEffect();
                return;
            } else
            {
                throw new RuntimeException("Internal error applying effect");
            }
        } else
        {
            throw new RuntimeException("Internal error applying effect");
        }
    }

    public void release()
    {
        mGraph.tearDown(getFilterContext());
        mGraph = null;
    }

    public void setParameter(String s, Object obj)
    {
    }

    private static final String TAG = "FilterGraphEffect";
    protected FilterGraph mGraph;
    protected String mInputName;
    protected String mOutputName;
    protected GraphRunner mRunner;
    protected Class mSchedulerClass;
}
