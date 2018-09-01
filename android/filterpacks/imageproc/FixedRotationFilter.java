// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;
import android.filterfw.geometry.Point;
import android.filterfw.geometry.Quad;

public class FixedRotationFilter extends Filter
{

    public FixedRotationFilter(String s)
    {
        super(s);
        mRotation = 0;
        mProgram = null;
    }

    public FrameFormat getOutputFormat(String s, FrameFormat frameformat)
    {
        return frameformat;
    }

    public void process(FilterContext filtercontext)
    {
        Frame frame;
        Object obj;
        MutableFrameFormat mutableframeformat;
        int i;
        int j;
        Point point;
        Point point1;
        Point point2;
        frame = pullInput("image");
        if(mRotation == 0)
        {
            pushOutput("image", frame);
            return;
        }
        obj = frame.getFormat();
        if(mProgram == null)
            mProgram = ShaderProgram.createIdentity(filtercontext);
        mutableframeformat = ((FrameFormat) (obj)).mutableCopy();
        i = ((FrameFormat) (obj)).getWidth();
        j = ((FrameFormat) (obj)).getHeight();
        point = new Point(0.0F, 0.0F);
        obj = new Point(1.0F, 0.0F);
        point1 = new Point(0.0F, 1.0F);
        point2 = new Point(1.0F, 1.0F);
        Math.round((float)mRotation / 90F) % 4;
        JVM INSTR tableswitch 1 3: default 144
    //                   1 199
    //                   2 226
    //                   3 244;
           goto _L1 _L2 _L3 _L4
_L1:
        obj = new Quad(point, ((Point) (obj)), point1, point2);
_L6:
        filtercontext = filtercontext.getFrameManager().newFrame(mutableframeformat);
        mProgram.setSourceRegion(((Quad) (obj)));
        mProgram.process(frame, filtercontext);
        pushOutput("image", filtercontext);
        filtercontext.release();
        return;
_L2:
        obj = new Quad(point1, point, point2, ((Point) (obj)));
        mutableframeformat.setDimensions(j, i);
        continue; /* Loop/switch isn't completed */
_L3:
        obj = new Quad(point2, point1, ((Point) (obj)), point);
        continue; /* Loop/switch isn't completed */
_L4:
        obj = new Quad(((Point) (obj)), point2, point, point1);
        mutableframeformat.setDimensions(j, i);
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void setupPorts()
    {
        addMaskedInputPort("image", ImageFormat.create(3, 3));
        addOutputBasedOnInput("image", "image");
    }

    private ShaderProgram mProgram;
    private int mRotation;
}
