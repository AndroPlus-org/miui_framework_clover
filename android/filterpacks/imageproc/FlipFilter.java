// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;

public class FlipFilter extends Filter
{

    public FlipFilter(String s)
    {
        super(s);
        mVertical = false;
        mHorizontal = false;
        mTileSize = 640;
        mTarget = 0;
    }

    private void updateParameters()
    {
        float f;
        float f1;
        float f2;
        float f3;
        if(mHorizontal)
            f = 1.0F;
        else
            f = 0.0F;
        if(mVertical)
            f1 = 1.0F;
        else
            f1 = 0.0F;
        if(mHorizontal)
            f2 = -1F;
        else
            f2 = 1.0F;
        if(mVertical)
            f3 = -1F;
        else
            f3 = 1.0F;
        ((ShaderProgram)mProgram).setSourceRect(f, f1, f2, f3);
    }

    public void fieldPortValueUpdated(String s, FilterContext filtercontext)
    {
        if(mProgram != null)
            updateParameters();
    }

    public FrameFormat getOutputFormat(String s, FrameFormat frameformat)
    {
        return frameformat;
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
        updateParameters();
    }

    public void process(FilterContext filtercontext)
    {
        Frame frame = pullInput("image");
        FrameFormat frameformat = frame.getFormat();
        if(mProgram == null || frameformat.getTarget() != mTarget)
            initProgram(filtercontext, frameformat.getTarget());
        filtercontext = filtercontext.getFrameManager().newFrame(frameformat);
        mProgram.process(frame, filtercontext);
        pushOutput("image", filtercontext);
        filtercontext.release();
    }

    public void setupPorts()
    {
        addMaskedInputPort("image", ImageFormat.create(3));
        addOutputBasedOnInput("image", "image");
    }

    private boolean mHorizontal;
    private Program mProgram;
    private int mTarget;
    private int mTileSize;
    private boolean mVertical;
}
