// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;

public class SepiaFilter extends Filter
{

    public SepiaFilter(String s)
    {
        super(s);
        mTileSize = 640;
        mTarget = 0;
    }

    private void initParameters()
    {
        mProgram.setHostValue("matrix", new float[] {
            0.3930664F, 0.3491211F, 0.2719727F, 0.769043F, 0.6860352F, 0.5356445F, 0.1889648F, 0.1679688F, 0.1308594F
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
            throw new RuntimeException((new StringBuilder()).append("Filter Sharpen does not support frames of target ").append(i).append("!").toString());

        case 3: // '\003'
            filtercontext = new ShaderProgram(filtercontext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform mat3 matrix;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  vec3 new_color = min(matrix * color.rgb, 1.0);\n  gl_FragColor = vec4(new_color.rgb, color.a);\n}\n");
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
        {
            initProgram(filtercontext, frameformat.getTarget());
            initParameters();
        }
        mProgram.process(frame, frame1);
        pushOutput("image", frame1);
        frame1.release();
    }

    public void setupPorts()
    {
        addMaskedInputPort("image", ImageFormat.create(3));
        addOutputBasedOnInput("image", "image");
    }

    private Program mProgram;
    private final String mSepiaShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform mat3 matrix;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  vec3 new_color = min(matrix * color.rgb, 1.0);\n  gl_FragColor = vec4(new_color.rgb, color.a);\n}\n";
    private int mTarget;
    private int mTileSize;
}
