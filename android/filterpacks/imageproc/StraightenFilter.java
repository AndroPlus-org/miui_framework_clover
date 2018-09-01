// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;
import android.filterfw.geometry.Point;
import android.filterfw.geometry.Quad;

public class StraightenFilter extends Filter
{

    public StraightenFilter(String s)
    {
        super(s);
        mAngle = 0.0F;
        mMaxAngle = 45F;
        mTileSize = 640;
        mWidth = 0;
        mHeight = 0;
        mTarget = 0;
    }

    private void updateParameters()
    {
        float f = (float)Math.cos(mAngle * 0.01745329F);
        float f1 = (float)Math.sin(mAngle * 0.01745329F);
        if(mMaxAngle <= 0.0F)
            throw new RuntimeException("Max angle is out of range (0-180).");
        float f2;
        Point point;
        Point point1;
        Object obj;
        Point point2;
        if(mMaxAngle > 90F)
            f2 = 90F;
        else
            f2 = mMaxAngle;
        mMaxAngle = f2;
        point = new Point(-f * (float)mWidth + (float)mHeight * f1, -f1 * (float)mWidth - (float)mHeight * f);
        point1 = new Point((float)mWidth * f + (float)mHeight * f1, (float)mWidth * f1 - (float)mHeight * f);
        obj = new Point(-f * (float)mWidth - (float)mHeight * f1, -f1 * (float)mWidth + (float)mHeight * f);
        point2 = new Point((float)mWidth * f - (float)mHeight * f1, (float)mWidth * f1 + (float)mHeight * f);
        f1 = Math.max(Math.abs(point.x), Math.abs(point1.x));
        f2 = Math.max(Math.abs(point.y), Math.abs(point1.y));
        f2 = 0.5F * Math.min((float)mWidth / f1, (float)mHeight / f2);
        point.set((point.x * f2) / (float)mWidth + 0.5F, (point.y * f2) / (float)mHeight + 0.5F);
        point1.set((point1.x * f2) / (float)mWidth + 0.5F, (point1.y * f2) / (float)mHeight + 0.5F);
        ((Point) (obj)).set((((Point) (obj)).x * f2) / (float)mWidth + 0.5F, (((Point) (obj)).y * f2) / (float)mHeight + 0.5F);
        point2.set((point2.x * f2) / (float)mWidth + 0.5F, (point2.y * f2) / (float)mHeight + 0.5F);
        obj = new Quad(point, point1, ((Point) (obj)), point2);
        ((ShaderProgram)mProgram).setSourceRegion(((Quad) (obj)));
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
        mProgram = filtercontext;
        mTarget = i;
    }

    public void process(FilterContext filtercontext)
    {
        Frame frame = pullInput("image");
        FrameFormat frameformat = frame.getFormat();
        if(mProgram == null || frameformat.getTarget() != mTarget)
            initProgram(filtercontext, frameformat.getTarget());
        if(frameformat.getWidth() != mWidth || frameformat.getHeight() != mHeight)
        {
            mWidth = frameformat.getWidth();
            mHeight = frameformat.getHeight();
            updateParameters();
        }
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

    private static final float DEGREE_TO_RADIAN = 0.01745329F;
    private float mAngle;
    private int mHeight;
    private float mMaxAngle;
    private Program mProgram;
    private int mTarget;
    private int mTileSize;
    private int mWidth;
}
