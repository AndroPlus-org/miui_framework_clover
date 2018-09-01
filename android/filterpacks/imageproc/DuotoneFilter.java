// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;
import android.graphics.Color;

public class DuotoneFilter extends Filter
{

    public DuotoneFilter(String s)
    {
        super(s);
        mFirstColor = 0xffff0000;
        mSecondColor = -256;
        mTileSize = 640;
        mTarget = 0;
    }

    private void updateParameters()
    {
        float f = (float)Color.red(mFirstColor) / 255F;
        float f1 = (float)Color.green(mFirstColor) / 255F;
        float f2 = (float)Color.blue(mFirstColor) / 255F;
        float f3 = (float)Color.red(mSecondColor) / 255F;
        float f4 = (float)Color.green(mSecondColor) / 255F;
        float f5 = (float)Color.blue(mSecondColor) / 255F;
        mProgram.setHostValue("first", new float[] {
            f, f1, f2
        });
        mProgram.setHostValue("second", new float[] {
            f3, f4, f5
        });
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
            throw new RuntimeException((new StringBuilder()).append("Filter Duotone does not support frames of target ").append(i).append("!").toString());

        case 3: // '\003'
            filtercontext = new ShaderProgram(filtercontext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform vec3 first;\nuniform vec3 second;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  float energy = (color.r + color.g + color.b) * 0.3333;\n  vec3 new_color = (1.0 - energy) * first + energy * second;\n  gl_FragColor = vec4(new_color.rgb, color.a);\n}\n");
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
        updateParameters();
        mProgram.process(frame, frame1);
        pushOutput("image", frame1);
        frame1.release();
    }

    public void setupPorts()
    {
        addMaskedInputPort("image", ImageFormat.create(3));
        addOutputBasedOnInput("image", "image");
    }

    private final String mDuotoneShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform vec3 first;\nuniform vec3 second;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  float energy = (color.r + color.g + color.b) * 0.3333;\n  vec3 new_color = (1.0 - energy) * first + energy * second;\n  gl_FragColor = vec4(new_color.rgb, color.a);\n}\n";
    private int mFirstColor;
    private Program mProgram;
    private int mSecondColor;
    private int mTarget;
    private int mTileSize;
}
