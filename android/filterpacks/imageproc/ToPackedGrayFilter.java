// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;

public class ToPackedGrayFilter extends Filter
{

    public ToPackedGrayFilter(String s)
    {
        super(s);
        mOWidth = 0;
        mOHeight = 0;
        mKeepAspectRatio = false;
    }

    private void checkOutputDimensions(int i, int j)
    {
        if(i <= 0 || j <= 0)
            throw new RuntimeException((new StringBuilder()).append("Invalid output dimensions: ").append(i).append(" ").append(j).toString());
        else
            return;
    }

    private FrameFormat convertInputFormat(FrameFormat frameformat)
    {
        int i = mOWidth;
        int j = mOHeight;
        int k = frameformat.getWidth();
        int l = frameformat.getHeight();
        if(mOWidth == 0)
            i = k;
        if(mOHeight == 0)
            j = l;
        int i1 = j;
        int j1 = i;
        if(mKeepAspectRatio)
            if(k > l)
            {
                j1 = Math.max(i, j);
                i1 = (j1 * l) / k;
            } else
            {
                i1 = Math.max(i, j);
                j1 = (i1 * k) / l;
            }
        if(j1 > 0 && j1 < 4)
            i = 4;
        else
            i = (j1 / 4) * 4;
        return ImageFormat.create(i, i1, 1, 2);
    }

    public FrameFormat getOutputFormat(String s, FrameFormat frameformat)
    {
        return convertInputFormat(frameformat);
    }

    public void prepare(FilterContext filtercontext)
    {
        mProgram = new ShaderProgram(filtercontext, "precision mediump float;\nconst vec4 coeff_y = vec4(0.299, 0.587, 0.114, 0);\nuniform sampler2D tex_sampler_0;\nuniform float pix_stride;\nvarying vec2 v_texcoord;\nvoid main() {\n  for (int i = 0; i < 4; ++i) {\n    vec4 p = texture2D(tex_sampler_0,\n                       v_texcoord + vec2(pix_stride * float(i), 0.0));\n    gl_FragColor[i] = dot(p, coeff_y);\n  }\n}\n");
    }

    public void process(FilterContext filtercontext)
    {
        Frame frame = pullInput("image");
        Object obj = frame.getFormat();
        FrameFormat frameformat = convertInputFormat(((FrameFormat) (obj)));
        int i = frameformat.getWidth();
        int j = frameformat.getHeight();
        checkOutputDimensions(i, j);
        mProgram.setHostValue("pix_stride", Float.valueOf(1.0F / (float)i));
        obj = ((FrameFormat) (obj)).mutableCopy();
        ((MutableFrameFormat) (obj)).setDimensions(i / 4, j);
        obj = filtercontext.getFrameManager().newFrame(((FrameFormat) (obj)));
        mProgram.process(frame, ((Frame) (obj)));
        filtercontext = filtercontext.getFrameManager().newFrame(frameformat);
        filtercontext.setDataFromFrame(((Frame) (obj)));
        ((Frame) (obj)).release();
        pushOutput("image", filtercontext);
        filtercontext.release();
    }

    public void setupPorts()
    {
        addMaskedInputPort("image", ImageFormat.create(3, 3));
        addOutputBasedOnInput("image", "image");
    }

    private final String mColorToPackedGrayShader = "precision mediump float;\nconst vec4 coeff_y = vec4(0.299, 0.587, 0.114, 0);\nuniform sampler2D tex_sampler_0;\nuniform float pix_stride;\nvarying vec2 v_texcoord;\nvoid main() {\n  for (int i = 0; i < 4; ++i) {\n    vec4 p = texture2D(tex_sampler_0,\n                       v_texcoord + vec2(pix_stride * float(i), 0.0));\n    gl_FragColor[i] = dot(p, coeff_y);\n  }\n}\n";
    private boolean mKeepAspectRatio;
    private int mOHeight;
    private int mOWidth;
    private Program mProgram;
}
