// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;
import android.filterfw.geometry.Point;
import android.filterfw.geometry.Quad;

public class RotateFilter extends Filter
{

    public RotateFilter(String s)
    {
        super(s);
        mTileSize = 640;
        mWidth = 0;
        mHeight = 0;
        mTarget = 0;
    }

    private void updateParameters()
    {
        if(mAngle % 90 == 0)
        {
            float f;
            float f1;
            if(mAngle % 180 == 0)
            {
                f = 0.0F;
                Quad quad;
                if(mAngle % 360 == 0)
                    f1 = 1.0F;
                else
                    f1 = -1F;
            } else
            {
                f1 = 0.0F;
                if((mAngle + 90) % 360 == 0)
                    f = -1F;
                else
                    f = 1.0F;
                mOutputWidth = mHeight;
                mOutputHeight = mWidth;
            }
            quad = new Quad(new Point((-f1 + f + 1.0F) * 0.5F, ((-f - f1) + 1.0F) * 0.5F), new Point((f1 + f + 1.0F) * 0.5F, ((f - f1) + 1.0F) * 0.5F), new Point(((-f1 - f) + 1.0F) * 0.5F, (-f + f1 + 1.0F) * 0.5F), new Point(((f1 - f) + 1.0F) * 0.5F, (f + f1 + 1.0F) * 0.5F));
            ((ShaderProgram)mProgram).setTargetRegion(quad);
            return;
        } else
        {
            throw new RuntimeException("degree has to be multiply of 90.");
        }
    }

    public void fieldPortValueUpdated(String s, FilterContext filtercontext)
    {
        if(mProgram != null)
            updateParameters();
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
        filtercontext.setClearsOutput(true);
        mProgram = filtercontext;
        mTarget = i;
    }

    public void process(FilterContext filtercontext)
    {
        Frame frame = pullInput("image");
        Object obj = frame.getFormat();
        if(mProgram == null || ((FrameFormat) (obj)).getTarget() != mTarget)
            initProgram(filtercontext, ((FrameFormat) (obj)).getTarget());
        if(((FrameFormat) (obj)).getWidth() != mWidth || ((FrameFormat) (obj)).getHeight() != mHeight)
        {
            mWidth = ((FrameFormat) (obj)).getWidth();
            mHeight = ((FrameFormat) (obj)).getHeight();
            mOutputWidth = mWidth;
            mOutputHeight = mHeight;
            updateParameters();
        }
        obj = ImageFormat.create(mOutputWidth, mOutputHeight, 3, 3);
        filtercontext = filtercontext.getFrameManager().newFrame(((FrameFormat) (obj)));
        mProgram.process(frame, filtercontext);
        pushOutput("image", filtercontext);
        filtercontext.release();
    }

    public void setupPorts()
    {
        addMaskedInputPort("image", ImageFormat.create(3));
        addOutputBasedOnInput("image", "image");
    }

    private int mAngle;
    private int mHeight;
    private int mOutputHeight;
    private int mOutputWidth;
    private Program mProgram;
    private int mTarget;
    private int mTileSize;
    private int mWidth;
}
