// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;

// Referenced classes of package android.filterpacks.imageproc:
//            SimpleImageFilter

public class ToGrayFilter extends SimpleImageFilter
{

    public ToGrayFilter(String s)
    {
        super(s, null);
        mInvertSource = false;
        mTileSize = 640;
    }

    protected Program getNativeProgram(FilterContext filtercontext)
    {
        throw new RuntimeException("Native toGray not implemented yet!");
    }

    protected Program getShaderProgram(FilterContext filtercontext)
    {
        int i = getInputFormat("image").getBytesPerSample();
        if(i != 4)
            throw new RuntimeException((new StringBuilder()).append("Unsupported GL input channels: ").append(i).append("! Channels must be 4!").toString());
        filtercontext = new ShaderProgram(filtercontext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  float y = dot(color, vec4(0.299, 0.587, 0.114, 0));\n  gl_FragColor = vec4(y, y, y, color.a);\n}\n");
        filtercontext.setMaximumTileSize(mTileSize);
        if(mInvertSource)
            filtercontext.setSourceRect(0.0F, 1.0F, 1.0F, -1F);
        return filtercontext;
    }

    public void setupPorts()
    {
        addMaskedInputPort("image", ImageFormat.create(3, 3));
        addOutputBasedOnInput("image", "image");
    }

    private static final String mColorToGray4Shader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  float y = dot(color, vec4(0.299, 0.587, 0.114, 0));\n  gl_FragColor = vec4(y, y, y, color.a);\n}\n";
    private boolean mInvertSource;
    private MutableFrameFormat mOutputFormat;
    private int mTileSize;
}
