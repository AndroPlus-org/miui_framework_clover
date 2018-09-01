// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;

public class CropRectFilter extends Filter
{

    public CropRectFilter(String s)
    {
        super(s);
        mTileSize = 640;
        mWidth = 0;
        mHeight = 0;
        mTarget = 0;
    }

    public void fieldPortValueUpdated(String s, FilterContext filtercontext)
    {
        if(mProgram != null)
            updateSourceRect(mWidth, mHeight);
    }

    public void initProgram(FilterContext filtercontext, int i)
    {
        switch(i)
        {
        default:
            throw new RuntimeException((new StringBuilder()).append("Filter Sharpen does not support frames of target ").append(i).append("!").toString());

        case 3: // '\003'
            filtercontext = ShaderProgram.createIdentity(filtercontext);
            break;
        }
        filtercontext.setMaximumTileSize(mTileSize);
        mProgram = filtercontext;
        mTarget = i;
    }

    public void process(FilterContext filtercontext)
    {
        Frame frame = pullInput("image");
        FrameFormat frameformat = frame.getFormat();
        Object obj = ImageFormat.create(mOutputWidth, mOutputHeight, 3, 3);
        obj = filtercontext.getFrameManager().newFrame(((FrameFormat) (obj)));
        if(mProgram == null || frameformat.getTarget() != mTarget)
            initProgram(filtercontext, frameformat.getTarget());
        if(frameformat.getWidth() != mWidth || frameformat.getHeight() != mHeight)
            updateSourceRect(frameformat.getWidth(), frameformat.getHeight());
        mProgram.process(frame, ((Frame) (obj)));
        pushOutput("image", ((Frame) (obj)));
        ((Frame) (obj)).release();
    }

    public void setupPorts()
    {
        addMaskedInputPort("image", ImageFormat.create(3));
        addOutputBasedOnInput("image", "image");
    }

    void updateSourceRect(int i, int j)
    {
        mWidth = i;
        mHeight = j;
        ((ShaderProgram)mProgram).setSourceRect((float)mXorigin / (float)mWidth, (float)mYorigin / (float)mHeight, (float)mOutputWidth / (float)mWidth, (float)mOutputHeight / (float)mHeight);
    }

    private int mHeight;
    private int mOutputHeight;
    private int mOutputWidth;
    private Program mProgram;
    private int mTarget;
    private int mTileSize;
    private int mWidth;
    private int mXorigin;
    private int mYorigin;
}
