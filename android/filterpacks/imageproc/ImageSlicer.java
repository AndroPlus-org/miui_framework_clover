// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;

public class ImageSlicer extends Filter
{

    public ImageSlicer(String s)
    {
        super(s);
        mSliceIndex = 0;
    }

    private void calcOutputFormatForInput(Frame frame)
    {
        mInputWidth = frame.getFormat().getWidth();
        mInputHeight = frame.getFormat().getHeight();
        mSliceWidth = ((mInputWidth + mXSlices) - 1) / mXSlices;
        mSliceHeight = ((mInputHeight + mYSlices) - 1) / mYSlices;
        mOutputWidth = mSliceWidth + mPadSize * 2;
        mOutputHeight = mSliceHeight + mPadSize * 2;
    }

    public FrameFormat getOutputFormat(String s, FrameFormat frameformat)
    {
        return frameformat;
    }

    public void process(FilterContext filtercontext)
    {
        if(mSliceIndex == 0)
        {
            mOriginalFrame = pullInput("image");
            calcOutputFormatForInput(mOriginalFrame);
        }
        Object obj = mOriginalFrame.getFormat().mutableCopy();
        ((MutableFrameFormat) (obj)).setDimensions(mOutputWidth, mOutputHeight);
        obj = filtercontext.getFrameManager().newFrame(((FrameFormat) (obj)));
        if(mProgram == null)
            mProgram = ShaderProgram.createIdentity(filtercontext);
        int i = mSliceIndex;
        int j = mXSlices;
        int k = mSliceIndex / mXSlices;
        float f = (float)(mSliceWidth * (i % j) - mPadSize) / (float)mInputWidth;
        float f1 = (float)(mSliceHeight * k - mPadSize) / (float)mInputHeight;
        ((ShaderProgram)mProgram).setSourceRect(f, f1, (float)mOutputWidth / (float)mInputWidth, (float)mOutputHeight / (float)mInputHeight);
        mProgram.process(mOriginalFrame, ((Frame) (obj)));
        mSliceIndex = mSliceIndex + 1;
        if(mSliceIndex == mXSlices * mYSlices)
        {
            mSliceIndex = 0;
            mOriginalFrame.release();
            setWaitsOnInputPort("image", true);
        } else
        {
            mOriginalFrame.retain();
            setWaitsOnInputPort("image", false);
        }
        pushOutput("image", ((Frame) (obj)));
        ((Frame) (obj)).release();
    }

    public void setupPorts()
    {
        addMaskedInputPort("image", ImageFormat.create(3, 3));
        addOutputBasedOnInput("image", "image");
    }

    private int mInputHeight;
    private int mInputWidth;
    private Frame mOriginalFrame;
    private int mOutputHeight;
    private int mOutputWidth;
    private int mPadSize;
    private Program mProgram;
    private int mSliceHeight;
    private int mSliceIndex;
    private int mSliceWidth;
    private int mXSlices;
    private int mYSlices;
}
