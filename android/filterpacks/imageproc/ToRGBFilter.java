// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;

public class ToRGBFilter extends Filter
{

    public ToRGBFilter(String s)
    {
        super(s);
        mLastFormat = null;
    }

    public void createProgram(FilterContext filtercontext, FrameFormat frameformat)
    {
        mInputBPP = frameformat.getBytesPerSample();
        if(mLastFormat != null && mLastFormat.getBytesPerSample() == mInputBPP)
            return;
        mLastFormat = frameformat;
        mInputBPP;
        JVM INSTR tableswitch 1 4: default 68
    //                   1 103
    //                   2 68
    //                   3 68
    //                   4 119;
           goto _L1 _L2 _L1 _L1 _L3
_L1:
        throw new RuntimeException((new StringBuilder()).append("Unsupported BytesPerPixel: ").append(mInputBPP).append("!").toString());
_L2:
        mProgram = new NativeProgram("filterpack_imageproc", "gray_to_rgb");
_L5:
        return;
_L3:
        mProgram = new NativeProgram("filterpack_imageproc", "rgba_to_rgb");
        if(true) goto _L5; else goto _L4
_L4:
    }

    public FrameFormat getConvertedFormat(FrameFormat frameformat)
    {
        frameformat = frameformat.mutableCopy();
        frameformat.setMetaValue("colorspace", Integer.valueOf(2));
        frameformat.setBytesPerSample(3);
        return frameformat;
    }

    public FrameFormat getOutputFormat(String s, FrameFormat frameformat)
    {
        return getConvertedFormat(frameformat);
    }

    public void process(FilterContext filtercontext)
    {
        Frame frame = pullInput("image");
        createProgram(filtercontext, frame.getFormat());
        filtercontext = filtercontext.getFrameManager().newFrame(getConvertedFormat(frame.getFormat()));
        mProgram.process(frame, filtercontext);
        pushOutput("image", filtercontext);
        filtercontext.release();
    }

    public void setupPorts()
    {
        MutableFrameFormat mutableframeformat = new MutableFrameFormat(2, 2);
        mutableframeformat.setDimensionCount(2);
        addMaskedInputPort("image", mutableframeformat);
        addOutputBasedOnInput("image", "image");
    }

    private int mInputBPP;
    private FrameFormat mLastFormat;
    private Program mProgram;
}
