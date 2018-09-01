// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;

public class SaturateFilter extends Filter
{

    public SaturateFilter(String s)
    {
        super(s);
        mScale = 0.0F;
        mTileSize = 640;
        mTarget = 0;
    }

    private void initParameters()
    {
        float af[] = new float[3];
        float[] _tmp = af;
        af[0] = 0.25F;
        af[1] = 0.625F;
        af[2] = 0.125F;
        mBenProgram.setHostValue("weights", af);
        mBenProgram.setHostValue("shift", Float.valueOf(0.003921569F));
        mHerfProgram.setHostValue("weights", af);
        updateParameters();
    }

    private void updateParameters()
    {
        if(mScale > 0.0F)
        {
            float f = mScale;
            float f1 = mScale;
            float f2 = mScale;
            mHerfProgram.setHostValue("exponents", new float[] {
                f * 0.9F + 1.0F, f1 * 2.1F + 1.0F, f2 * 2.7F + 1.0F
            });
        } else
        {
            mBenProgram.setHostValue("scale", Float.valueOf(mScale + 1.0F));
        }
    }

    public void fieldPortValueUpdated(String s, FilterContext filtercontext)
    {
        if(mBenProgram != null && mHerfProgram != null)
            updateParameters();
    }

    public FrameFormat getOutputFormat(String s, FrameFormat frameformat)
    {
        return frameformat;
    }

    public void initProgram(FilterContext filtercontext, int i)
    {
        ShaderProgram shaderprogram;
        switch(i)
        {
        default:
            throw new RuntimeException((new StringBuilder()).append("Filter Sharpen does not support frames of target ").append(i).append("!").toString());

        case 3: // '\003'
            shaderprogram = new ShaderProgram(filtercontext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform float scale;\nuniform float shift;\nuniform vec3 weights;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  float kv = dot(color.rgb, weights) + shift;\n  vec3 new_color = scale * color.rgb + (1.0 - scale) * kv;\n  gl_FragColor = vec4(new_color, color.a);\n}\n");
            break;
        }
        shaderprogram.setMaximumTileSize(mTileSize);
        mBenProgram = shaderprogram;
        filtercontext = new ShaderProgram(filtercontext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform vec3 weights;\nuniform vec3 exponents;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  float de = dot(color.rgb, weights);\n  float inv_de = 1.0 / de;\n  vec3 new_color = de * pow(color.rgb * inv_de, exponents);\n  float max_color = max(max(max(new_color.r, new_color.g), new_color.b), 1.0);\n  gl_FragColor = vec4(new_color / max_color, color.a);\n}\n");
        filtercontext.setMaximumTileSize(mTileSize);
        mHerfProgram = filtercontext;
        mTarget = i;
    }

    public void process(FilterContext filtercontext)
    {
        Frame frame = pullInput("image");
        FrameFormat frameformat = frame.getFormat();
        if(mBenProgram == null || frameformat.getTarget() != mTarget)
        {
            initProgram(filtercontext, frameformat.getTarget());
            initParameters();
        }
        filtercontext = filtercontext.getFrameManager().newFrame(frameformat);
        if(mScale > 0.0F)
            mHerfProgram.process(frame, filtercontext);
        else
            mBenProgram.process(frame, filtercontext);
        pushOutput("image", filtercontext);
        filtercontext.release();
    }

    public void setupPorts()
    {
        addMaskedInputPort("image", ImageFormat.create(3));
        addOutputBasedOnInput("image", "image");
    }

    private Program mBenProgram;
    private final String mBenSaturateShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform float scale;\nuniform float shift;\nuniform vec3 weights;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  float kv = dot(color.rgb, weights) + shift;\n  vec3 new_color = scale * color.rgb + (1.0 - scale) * kv;\n  gl_FragColor = vec4(new_color, color.a);\n}\n";
    private Program mHerfProgram;
    private final String mHerfSaturateShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform vec3 weights;\nuniform vec3 exponents;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  float de = dot(color.rgb, weights);\n  float inv_de = 1.0 / de;\n  vec3 new_color = de * pow(color.rgb * inv_de, exponents);\n  float max_color = max(max(max(new_color.r, new_color.g), new_color.b), 1.0);\n  gl_FragColor = vec4(new_color / max_color, color.a);\n}\n";
    private float mScale;
    private int mTarget;
    private int mTileSize;
}
