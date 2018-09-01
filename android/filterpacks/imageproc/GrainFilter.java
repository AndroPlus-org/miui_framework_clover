// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;
import java.util.Date;
import java.util.Random;

public class GrainFilter extends Filter
{

    public GrainFilter(String s)
    {
        super(s);
        mScale = 0.0F;
        mTileSize = 640;
        mWidth = 0;
        mHeight = 0;
        mTarget = 0;
        mRandom = new Random((new Date()).getTime());
    }

    private void updateFrameSize(int i, int j)
    {
        mWidth = i;
        mHeight = j;
        if(mGrainProgram != null)
        {
            mGrainProgram.setHostValue("stepX", Float.valueOf(0.5F / (float)mWidth));
            mGrainProgram.setHostValue("stepY", Float.valueOf(0.5F / (float)mHeight));
            updateParameters();
        }
    }

    private void updateParameters()
    {
        float f = mRandom.nextFloat();
        float f1 = mRandom.nextFloat();
        mNoiseProgram.setHostValue("seed", new float[] {
            f, f1
        });
        mGrainProgram.setHostValue("scale", Float.valueOf(mScale));
    }

    public void fieldPortValueUpdated(String s, FilterContext filtercontext)
    {
        if(mGrainProgram != null && mNoiseProgram != null)
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
            shaderprogram = new ShaderProgram(filtercontext, "precision mediump float;\nuniform vec2 seed;\nvarying vec2 v_texcoord;\nfloat rand(vec2 loc) {\n  float theta1 = dot(loc, vec2(0.9898, 0.233));\n  float theta2 = dot(loc, vec2(12.0, 78.0));\n  float value = cos(theta1) * sin(theta2) + sin(theta1) * cos(theta2);\n  float temp = mod(197.0 * value, 1.0) + value;\n  float part1 = mod(220.0 * temp, 1.0) + temp;\n  float part2 = value * 0.5453;\n  float part3 = cos(theta1 + theta2) * 0.43758;\n  return fract(part1 + part2 + part3);\n}\nvoid main() {\n  gl_FragColor = vec4(rand(v_texcoord + seed), 0.0, 0.0, 1.0);\n}\n");
            break;
        }
        shaderprogram.setMaximumTileSize(mTileSize);
        mNoiseProgram = shaderprogram;
        filtercontext = new ShaderProgram(filtercontext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform float scale;\nuniform float stepX;\nuniform float stepY;\nvarying vec2 v_texcoord;\nvoid main() {\n  float noise = texture2D(tex_sampler_1, v_texcoord + vec2(-stepX, -stepY)).r * 0.224;\n  noise += texture2D(tex_sampler_1, v_texcoord + vec2(-stepX, stepY)).r * 0.224;\n  noise += texture2D(tex_sampler_1, v_texcoord + vec2(stepX, -stepY)).r * 0.224;\n  noise += texture2D(tex_sampler_1, v_texcoord + vec2(stepX, stepY)).r * 0.224;\n  noise += 0.4448;\n  noise *= scale;\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  float energy = 0.33333 * color.r + 0.33333 * color.g + 0.33333 * color.b;\n  float mask = (1.0 - sqrt(energy));\n  float weight = 1.0 - 1.333 * mask * noise;\n  gl_FragColor = vec4(color.rgb * weight, color.a);\n}\n");
        filtercontext.setMaximumTileSize(mTileSize);
        mGrainProgram = filtercontext;
        mTarget = i;
    }

    public void process(FilterContext filtercontext)
    {
        Frame frame;
        FrameFormat frameformat;
        Frame frame1;
        Frame frame2;
        frame = pullInput("image");
        frameformat = frame.getFormat();
        ImageFormat.create(frameformat.getWidth() / 2, frameformat.getHeight() / 2, 3, 3);
        frame1 = filtercontext.getFrameManager().newFrame(frameformat);
        frame2 = filtercontext.getFrameManager().newFrame(frameformat);
        break MISSING_BLOCK_LABEL_50;
        if(mNoiseProgram == null || mGrainProgram == null || frameformat.getTarget() != mTarget)
        {
            initProgram(filtercontext, frameformat.getTarget());
            updateParameters();
        }
        if(frameformat.getWidth() != mWidth || frameformat.getHeight() != mHeight)
            updateFrameSize(frameformat.getWidth(), frameformat.getHeight());
        mNoiseProgram.process(new Frame[0], frame1);
        mGrainProgram.process(new Frame[] {
            frame, frame1
        }, frame2);
        pushOutput("image", frame2);
        frame2.release();
        frame1.release();
        return;
    }

    public void setupPorts()
    {
        addMaskedInputPort("image", ImageFormat.create(3));
        addOutputBasedOnInput("image", "image");
    }

    private static final int RAND_THRESHOLD = 128;
    private Program mGrainProgram;
    private final String mGrainShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform float scale;\nuniform float stepX;\nuniform float stepY;\nvarying vec2 v_texcoord;\nvoid main() {\n  float noise = texture2D(tex_sampler_1, v_texcoord + vec2(-stepX, -stepY)).r * 0.224;\n  noise += texture2D(tex_sampler_1, v_texcoord + vec2(-stepX, stepY)).r * 0.224;\n  noise += texture2D(tex_sampler_1, v_texcoord + vec2(stepX, -stepY)).r * 0.224;\n  noise += texture2D(tex_sampler_1, v_texcoord + vec2(stepX, stepY)).r * 0.224;\n  noise += 0.4448;\n  noise *= scale;\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  float energy = 0.33333 * color.r + 0.33333 * color.g + 0.33333 * color.b;\n  float mask = (1.0 - sqrt(energy));\n  float weight = 1.0 - 1.333 * mask * noise;\n  gl_FragColor = vec4(color.rgb * weight, color.a);\n}\n";
    private int mHeight;
    private Program mNoiseProgram;
    private final String mNoiseShader = "precision mediump float;\nuniform vec2 seed;\nvarying vec2 v_texcoord;\nfloat rand(vec2 loc) {\n  float theta1 = dot(loc, vec2(0.9898, 0.233));\n  float theta2 = dot(loc, vec2(12.0, 78.0));\n  float value = cos(theta1) * sin(theta2) + sin(theta1) * cos(theta2);\n  float temp = mod(197.0 * value, 1.0) + value;\n  float part1 = mod(220.0 * temp, 1.0) + temp;\n  float part2 = value * 0.5453;\n  float part3 = cos(theta1 + theta2) * 0.43758;\n  return fract(part1 + part2 + part3);\n}\nvoid main() {\n  gl_FragColor = vec4(rand(v_texcoord + seed), 0.0, 0.0, 1.0);\n}\n";
    private Random mRandom;
    private float mScale;
    private int mTarget;
    private int mTileSize;
    private int mWidth;
}
