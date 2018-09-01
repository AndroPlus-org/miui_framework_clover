// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;

public class ResizeFilter extends Filter
{

    public ResizeFilter(String s)
    {
        super(s);
        mKeepAspectRatio = false;
        mGenerateMipMap = false;
        mLastFormat = null;
    }

    protected void createProgram(FilterContext filtercontext, FrameFormat frameformat)
    {
        if(mLastFormat != null && mLastFormat.getTarget() == frameformat.getTarget())
            return;
        mLastFormat = frameformat;
        switch(frameformat.getTarget())
        {
        default:
            throw new RuntimeException("ResizeFilter could not create suitable program!");

        case 2: // '\002'
            throw new RuntimeException("Native ResizeFilter not implemented yet!");

        case 3: // '\003'
            mProgram = ShaderProgram.createIdentity(filtercontext);
            break;
        }
    }

    public FrameFormat getOutputFormat(String s, FrameFormat frameformat)
    {
        return frameformat;
    }

    public void process(FilterContext filtercontext)
    {
        Frame frame = pullInput("image");
        createProgram(filtercontext, frame.getFormat());
        MutableFrameFormat mutableframeformat = frame.getFormat().mutableCopy();
        if(mKeepAspectRatio)
        {
            FrameFormat frameformat = frame.getFormat();
            mOHeight = (mOWidth * frameformat.getHeight()) / frameformat.getWidth();
        }
        mutableframeformat.setDimensions(mOWidth, mOHeight);
        Frame frame1 = filtercontext.getFrameManager().newFrame(mutableframeformat);
        if(mGenerateMipMap)
        {
            filtercontext = (GLFrame)filtercontext.getFrameManager().newFrame(frame.getFormat());
            filtercontext.setTextureParameter(10241, 9985);
            filtercontext.setDataFromFrame(frame);
            filtercontext.generateMipMap();
            mProgram.process(filtercontext, frame1);
            filtercontext.release();
        } else
        {
            mProgram.process(frame, frame1);
        }
        pushOutput("image", frame1);
        frame1.release();
    }

    public void setupPorts()
    {
        addMaskedInputPort("image", ImageFormat.create(3));
        addOutputBasedOnInput("image", "image");
    }

    private boolean mGenerateMipMap;
    private int mInputChannels;
    private boolean mKeepAspectRatio;
    private FrameFormat mLastFormat;
    private int mOHeight;
    private int mOWidth;
    private MutableFrameFormat mOutputFormat;
    private Program mProgram;
}
