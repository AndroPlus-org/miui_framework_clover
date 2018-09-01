// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.base;

import android.filterfw.core.*;

public class FrameBranch extends Filter
{

    public FrameBranch(String s)
    {
        super(s);
        mNumberOfOutputs = 2;
    }

    public FrameFormat getOutputFormat(String s, FrameFormat frameformat)
    {
        return frameformat;
    }

    public void process(FilterContext filtercontext)
    {
        filtercontext = pullInput("in");
        for(int i = 0; i < mNumberOfOutputs; i++)
            pushOutput((new StringBuilder()).append("out").append(i).toString(), filtercontext);

    }

    public void setupPorts()
    {
        addInputPort("in");
        for(int i = 0; i < mNumberOfOutputs; i++)
            addOutputBasedOnInput((new StringBuilder()).append("out").append(i).toString(), "in");

    }

    private int mNumberOfOutputs;
}
