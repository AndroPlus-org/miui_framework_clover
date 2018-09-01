// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;
import android.filterfw.format.ObjectFormat;
import android.filterfw.geometry.Quad;

public class CropFilter extends Filter
{

    public CropFilter(String s)
    {
        super(s);
        mLastFormat = null;
        mOutputWidth = -1;
        mOutputHeight = -1;
        mFillBlack = false;
    }

    protected void createProgram(FilterContext filtercontext, FrameFormat frameformat)
    {
        if(mLastFormat != null && mLastFormat.getTarget() == frameformat.getTarget())
            return;
        mLastFormat = frameformat;
        mProgram = null;
        frameformat.getTarget();
        JVM INSTR tableswitch 3 3: default 56
    //                   3 95;
           goto _L1 _L2
_L1:
        if(mProgram == null)
            throw new RuntimeException((new StringBuilder()).append("Could not create a program for crop filter ").append(this).append("!").toString());
        else
            return;
_L2:
        if(mFillBlack)
            mProgram = new ShaderProgram(filtercontext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  const vec2 lo = vec2(0.0, 0.0);\n  const vec2 hi = vec2(1.0, 1.0);\n  const vec4 black = vec4(0.0, 0.0, 0.0, 1.0);\n  bool out_of_bounds =\n    any(lessThan(v_texcoord, lo)) ||\n    any(greaterThan(v_texcoord, hi));\n  if (out_of_bounds) {\n    gl_FragColor = black;\n  } else {\n    gl_FragColor = texture2D(tex_sampler_0, v_texcoord);\n  }\n}\n");
        else
            mProgram = ShaderProgram.createIdentity(filtercontext);
          goto _L1
    }

    public FrameFormat getOutputFormat(String s, FrameFormat frameformat)
    {
        s = frameformat.mutableCopy();
        s.setDimensions(0, 0);
        return s;
    }

    public void process(FilterContext filtercontext)
    {
        Frame frame = pullInput("image");
        Object obj = pullInput("box");
        createProgram(filtercontext, frame.getFormat());
        obj = (Quad)((Frame) (obj)).getObjectValue();
        MutableFrameFormat mutableframeformat = frame.getFormat().mutableCopy();
        int i;
        int j;
        if(mOutputWidth == -1)
            i = mutableframeformat.getWidth();
        else
            i = mOutputWidth;
        if(mOutputHeight == -1)
            j = mutableframeformat.getHeight();
        else
            j = mOutputHeight;
        mutableframeformat.setDimensions(i, j);
        filtercontext = filtercontext.getFrameManager().newFrame(mutableframeformat);
        if(mProgram instanceof ShaderProgram)
            ((ShaderProgram)mProgram).setSourceRegion(((Quad) (obj)));
        mProgram.process(frame, filtercontext);
        pushOutput("image", filtercontext);
        filtercontext.release();
    }

    public void setupPorts()
    {
        addMaskedInputPort("image", ImageFormat.create(3));
        addMaskedInputPort("box", ObjectFormat.fromClass(android/filterfw/geometry/Quad, 1));
        addOutputBasedOnInput("image", "image");
    }

    private boolean mFillBlack;
    private final String mFragShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  const vec2 lo = vec2(0.0, 0.0);\n  const vec2 hi = vec2(1.0, 1.0);\n  const vec4 black = vec4(0.0, 0.0, 0.0, 1.0);\n  bool out_of_bounds =\n    any(lessThan(v_texcoord, lo)) ||\n    any(greaterThan(v_texcoord, hi));\n  if (out_of_bounds) {\n    gl_FragColor = black;\n  } else {\n    gl_FragColor = texture2D(tex_sampler_0, v_texcoord);\n  }\n}\n";
    private FrameFormat mLastFormat;
    private int mOutputHeight;
    private int mOutputWidth;
    private Program mProgram;
}
