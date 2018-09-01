// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.numeric;

import android.filterfw.core.*;
import android.filterfw.format.ObjectFormat;

public class SinWaveFilter extends Filter
{

    public SinWaveFilter(String s)
    {
        super(s);
        mStepSize = 0.05F;
        mValue = 0.0F;
    }

    public void open(FilterContext filtercontext)
    {
        mValue = 0.0F;
    }

    public void process(FilterContext filtercontext)
    {
        filtercontext = filtercontext.getFrameManager().newFrame(mOutputFormat);
        filtercontext.setObjectValue(Float.valueOf(((float)Math.sin(mValue) + 1.0F) / 2.0F));
        pushOutput("value", filtercontext);
        mValue = mValue + mStepSize;
        filtercontext.release();
    }

    public void setupPorts()
    {
        mOutputFormat = ObjectFormat.fromClass(java/lang/Float, 1);
        addOutputPort("value", mOutputFormat);
    }

    private FrameFormat mOutputFormat;
    private float mStepSize;
    private float mValue;
}
