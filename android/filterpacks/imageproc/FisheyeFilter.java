// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;

public class FisheyeFilter extends Filter
{

    public FisheyeFilter(String s)
    {
        super(s);
        mScale = 0.0F;
        mTileSize = 640;
        mWidth = 0;
        mHeight = 0;
        mTarget = 0;
    }

    private void updateFrameSize(int i, int j)
    {
        mWidth = i;
        mHeight = j;
        updateProgramParams();
    }

    private void updateProgramParams()
    {
        float af[] = new float[2];
        float f;
        float f1;
        float f2;
        float f3;
        if(mWidth > mHeight)
        {
            af[0] = 1.0F;
            af[1] = (float)mHeight / (float)mWidth;
        } else
        {
            af[0] = (float)mWidth / (float)mHeight;
            af[1] = 1.0F;
        }
        f = mScale * 2.0F + 0.75F;
        f1 = 0.25F * (af[0] * af[0] + af[1] * af[1]);
        f2 = (float)Math.sqrt(f1);
        f3 = 1.15F * f2;
        f3 *= f3;
        f2 /= 1.570796F - (float)Math.atan((f / f2) * (float)Math.sqrt(f3 - f1));
        mProgram.setHostValue("scale", af);
        mProgram.setHostValue("radius2", Float.valueOf(f3));
        mProgram.setHostValue("factor", Float.valueOf(f2));
        mProgram.setHostValue("alpha", Float.valueOf(f));
    }

    public void fieldPortValueUpdated(String s, FilterContext filtercontext)
    {
        if(mProgram != null)
            updateProgramParams();
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
            throw new RuntimeException((new StringBuilder()).append("Filter FisheyeFilter does not support frames of target ").append(i).append("!").toString());

        case 3: // '\003'
            filtercontext = new ShaderProgram(filtercontext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform vec2 scale;\nuniform float alpha;\nuniform float radius2;\nuniform float factor;\nvarying vec2 v_texcoord;\nvoid main() {\n  const float m_pi_2 = 1.570963;\n  vec2 coord = v_texcoord - vec2(0.5, 0.5);\n  float dist = length(coord * scale);\n  float radian = m_pi_2 - atan(alpha * sqrt(radius2 - dist * dist), dist);\n  float scalar = radian * factor / dist;\n  vec2 new_coord = coord * scalar + vec2(0.5, 0.5);\n  gl_FragColor = texture2D(tex_sampler_0, new_coord);\n}\n");
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
        Frame frame1 = filtercontext.getFrameManager().newFrame(frameformat);
        if(mProgram == null || frameformat.getTarget() != mTarget)
            initProgram(filtercontext, frameformat.getTarget());
        if(frameformat.getWidth() != mWidth || frameformat.getHeight() != mHeight)
            updateFrameSize(frameformat.getWidth(), frameformat.getHeight());
        mProgram.process(frame, frame1);
        pushOutput("image", frame1);
        frame1.release();
    }

    public void setupPorts()
    {
        addMaskedInputPort("image", ImageFormat.create(3));
        addOutputBasedOnInput("image", "image");
    }

    private static final String TAG = "FisheyeFilter";
    private static final String mFisheyeShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform vec2 scale;\nuniform float alpha;\nuniform float radius2;\nuniform float factor;\nvarying vec2 v_texcoord;\nvoid main() {\n  const float m_pi_2 = 1.570963;\n  vec2 coord = v_texcoord - vec2(0.5, 0.5);\n  float dist = length(coord * scale);\n  float radian = m_pi_2 - atan(alpha * sqrt(radius2 - dist * dist), dist);\n  float scalar = radian * factor / dist;\n  vec2 new_coord = coord * scalar + vec2(0.5, 0.5);\n  gl_FragColor = texture2D(tex_sampler_0, new_coord);\n}\n";
    private int mHeight;
    private Program mProgram;
    private float mScale;
    private int mTarget;
    private int mTileSize;
    private int mWidth;
}
