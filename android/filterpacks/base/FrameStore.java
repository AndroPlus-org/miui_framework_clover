// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.base;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;

public class FrameStore extends Filter
{

    public FrameStore(String s)
    {
        super(s);
    }

    public void process(FilterContext filtercontext)
    {
        android.filterfw.core.Frame frame = pullInput("frame");
        filtercontext.storeFrame(mKey, frame);
    }

    public void setupPorts()
    {
        addInputPort("frame");
    }

    private String mKey;
}
