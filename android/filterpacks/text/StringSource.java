// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.text;

import android.filterfw.core.*;
import android.filterfw.format.ObjectFormat;

public class StringSource extends Filter
{

    public StringSource(String s)
    {
        super(s);
    }

    public void process(FilterContext filtercontext)
    {
        filtercontext = filtercontext.getFrameManager().newFrame(mOutputFormat);
        filtercontext.setObjectValue(mString);
        filtercontext.setTimestamp(-1L);
        pushOutput("string", filtercontext);
        closeOutputPort("string");
    }

    public void setupPorts()
    {
        mOutputFormat = ObjectFormat.fromClass(java/lang/String, 1);
        addOutputPort("string", mOutputFormat);
    }

    private FrameFormat mOutputFormat;
    private String mString;
}
