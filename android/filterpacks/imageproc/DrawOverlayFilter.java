// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;
import android.filterfw.format.ObjectFormat;
import android.filterfw.geometry.Quad;

public class DrawOverlayFilter extends Filter
{

    public DrawOverlayFilter(String s)
    {
        super(s);
    }

    public FrameFormat getOutputFormat(String s, FrameFormat frameformat)
    {
        return frameformat;
    }

    public void prepare(FilterContext filtercontext)
    {
        mProgram = ShaderProgram.createIdentity(filtercontext);
    }

    public void process(FilterContext filtercontext)
    {
        Frame frame = pullInput("source");
        Frame frame1 = pullInput("overlay");
        Quad quad = ((Quad)pullInput("box").getObjectValue()).translated(1.0F, 1.0F).scaled(2.0F);
        mProgram.setTargetRegion(quad);
        filtercontext = filtercontext.getFrameManager().newFrame(frame.getFormat());
        filtercontext.setDataFromFrame(frame);
        mProgram.process(frame1, filtercontext);
        pushOutput("image", filtercontext);
        filtercontext.release();
    }

    public void setupPorts()
    {
        android.filterfw.core.MutableFrameFormat mutableframeformat = ImageFormat.create(3, 3);
        addMaskedInputPort("source", mutableframeformat);
        addMaskedInputPort("overlay", mutableframeformat);
        addMaskedInputPort("box", ObjectFormat.fromClass(android/filterfw/geometry/Quad, 1));
        addOutputBasedOnInput("image", "source");
    }

    private ShaderProgram mProgram;
}
