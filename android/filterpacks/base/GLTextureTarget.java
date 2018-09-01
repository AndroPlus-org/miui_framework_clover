// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.base;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;

public class GLTextureTarget extends Filter
{

    public GLTextureTarget(String s)
    {
        super(s);
    }

    public void process(FilterContext filtercontext)
    {
        Frame frame = pullInput("frame");
        android.filterfw.core.MutableFrameFormat mutableframeformat = ImageFormat.create(frame.getFormat().getWidth(), frame.getFormat().getHeight(), 3, 3);
        filtercontext = filtercontext.getFrameManager().newBoundFrame(mutableframeformat, 100, mTexId);
        filtercontext.setDataFromFrame(frame);
        filtercontext.release();
    }

    public void setupPorts()
    {
        addMaskedInputPort("frame", ImageFormat.create(3));
    }

    private int mTexId;
}
