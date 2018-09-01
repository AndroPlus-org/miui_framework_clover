// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;
import android.graphics.Bitmap;

public class BitmapSource extends Filter
{

    public BitmapSource(String s)
    {
        super(s);
        mRecycleBitmap = true;
        mRepeatFrame = false;
    }

    public void fieldPortValueUpdated(String s, FilterContext filtercontext)
    {
        if((s.equals("bitmap") || s.equals("target")) && mImageFrame != null)
        {
            mImageFrame.release();
            mImageFrame = null;
        }
    }

    public void loadImage(FilterContext filtercontext)
    {
        mTarget = FrameFormat.readTargetString(mTargetString);
        android.filterfw.core.MutableFrameFormat mutableframeformat = ImageFormat.create(mBitmap.getWidth(), mBitmap.getHeight(), 3, mTarget);
        mImageFrame = filtercontext.getFrameManager().newFrame(mutableframeformat);
        mImageFrame.setBitmap(mBitmap);
        mImageFrame.setTimestamp(-1L);
        if(mRecycleBitmap)
            mBitmap.recycle();
        mBitmap = null;
    }

    public void process(FilterContext filtercontext)
    {
        if(mImageFrame == null)
            loadImage(filtercontext);
        pushOutput("image", mImageFrame);
        if(!mRepeatFrame)
            closeOutputPort("image");
    }

    public void setupPorts()
    {
        addOutputPort("image", ImageFormat.create(3, 0));
    }

    public void tearDown(FilterContext filtercontext)
    {
        if(mImageFrame != null)
        {
            mImageFrame.release();
            mImageFrame = null;
        }
    }

    private Bitmap mBitmap;
    private Frame mImageFrame;
    private boolean mRecycleBitmap;
    boolean mRepeatFrame;
    private int mTarget;
    String mTargetString;
}
