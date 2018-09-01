// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;

public class ImageStitcher extends Filter
{

    public ImageStitcher(String s)
    {
        super(s);
        mSliceIndex = 0;
    }

    private FrameFormat calcOutputFormatForInput(FrameFormat frameformat)
    {
        MutableFrameFormat mutableframeformat = frameformat.mutableCopy();
        mInputWidth = frameformat.getWidth();
        mInputHeight = frameformat.getHeight();
        mSliceWidth = mInputWidth - mPadSize * 2;
        mSliceHeight = mInputHeight - mPadSize * 2;
        mImageWidth = mSliceWidth * mXSlices;
        mImageHeight = mSliceHeight * mYSlices;
        mutableframeformat.setDimensions(mImageWidth, mImageHeight);
        return mutableframeformat;
    }

    public FrameFormat getOutputFormat(String s, FrameFormat frameformat)
    {
        return frameformat;
    }

    public void process(FilterContext filtercontext)
    {
        Frame frame = pullInput("image");
        FrameFormat frameformat = frame.getFormat();
        float f;
        float f1;
        int i;
        int j;
        float f2;
        float f3;
        if(mSliceIndex == 0)
            mOutputFrame = filtercontext.getFrameManager().newFrame(calcOutputFormatForInput(frameformat));
        else
        if(frameformat.getWidth() != mInputWidth || frameformat.getHeight() != mInputHeight)
            throw new RuntimeException("Image size should not change.");
        if(mProgram == null)
            mProgram = ShaderProgram.createIdentity(filtercontext);
        f = (float)mPadSize / (float)mInputWidth;
        f1 = (float)mPadSize / (float)mInputHeight;
        i = (mSliceIndex % mXSlices) * mSliceWidth;
        j = (mSliceIndex / mXSlices) * mSliceHeight;
        f2 = Math.min(mSliceWidth, mImageWidth - i);
        f3 = Math.min(mSliceHeight, mImageHeight - j);
        ((ShaderProgram)mProgram).setSourceRect(f, f1, f2 / (float)mInputWidth, f3 / (float)mInputHeight);
        ((ShaderProgram)mProgram).setTargetRect((float)i / (float)mImageWidth, (float)j / (float)mImageHeight, f2 / (float)mImageWidth, f3 / (float)mImageHeight);
        mProgram.process(frame, mOutputFrame);
        mSliceIndex = mSliceIndex + 1;
        if(mSliceIndex == mXSlices * mYSlices)
        {
            pushOutput("image", mOutputFrame);
            mOutputFrame.release();
            mSliceIndex = 0;
        }
    }

    public void setupPorts()
    {
        addMaskedInputPort("image", ImageFormat.create(3, 3));
        addOutputBasedOnInput("image", "image");
    }

    private int mImageHeight;
    private int mImageWidth;
    private int mInputHeight;
    private int mInputWidth;
    private Frame mOutputFrame;
    private int mPadSize;
    private Program mProgram;
    private int mSliceHeight;
    private int mSliceIndex;
    private int mSliceWidth;
    private int mXSlices;
    private int mYSlices;
}
