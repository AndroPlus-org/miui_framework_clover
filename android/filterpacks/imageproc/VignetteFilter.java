// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;

public class VignetteFilter extends Filter
{

    public VignetteFilter(String s)
    {
        super(s);
        mScale = 0.0F;
        mTileSize = 640;
        mWidth = 0;
        mHeight = 0;
        mTarget = 0;
    }

    private void initParameters()
    {
        if(mProgram != null)
        {
            float af[] = new float[2];
            float f;
            if(mWidth > mHeight)
            {
                af[0] = 1.0F;
                af[1] = (float)mHeight / (float)mWidth;
            } else
            {
                af[0] = (float)mWidth / (float)mHeight;
                af[1] = 1.0F;
            }
            f = (float)Math.sqrt(af[0] * af[0] + af[1] * af[1]);
            mProgram.setHostValue("scale", af);
            mProgram.setHostValue("inv_max_dist", Float.valueOf(1.0F / (f * 0.5F)));
            mProgram.setHostValue("shade", Float.valueOf(0.85F));
            updateParameters();
        }
    }

    private void updateParameters()
    {
        mProgram.setHostValue("range", Float.valueOf(1.3F - (float)Math.sqrt(mScale) * 0.7F));
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
            filtercontext = new ShaderProgram(filtercontext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform float range;\nuniform float inv_max_dist;\nuniform float shade;\nuniform vec2 scale;\nvarying vec2 v_texcoord;\nvoid main() {\n  const float slope = 20.0;\n  vec2 coord = v_texcoord - vec2(0.5, 0.5);\n  float dist = length(coord * scale);\n  float lumen = shade / (1.0 + exp((dist * inv_max_dist - range) * slope)) + (1.0 - shade);\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  gl_FragColor = vec4(color.rgb * lumen, color.a);\n}\n");
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
            initParameters();
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

    private int mHeight;
    private Program mProgram;
    private float mScale;
    private final float mShade = 0.85F;
    private final float mSlope = 20F;
    private int mTarget;
    private int mTileSize;
    private final String mVignetteShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform float range;\nuniform float inv_max_dist;\nuniform float shade;\nuniform vec2 scale;\nvarying vec2 v_texcoord;\nvoid main() {\n  const float slope = 20.0;\n  vec2 coord = v_texcoord - vec2(0.5, 0.5);\n  float dist = length(coord * scale);\n  float lumen = shade / (1.0 + exp((dist * inv_max_dist - range) * slope)) + (1.0 - shade);\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  gl_FragColor = vec4(color.rgb * lumen, color.a);\n}\n";
    private int mWidth;
}
