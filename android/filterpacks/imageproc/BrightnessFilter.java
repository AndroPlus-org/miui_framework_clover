// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;

// Referenced classes of package android.filterpacks.imageproc:
//            SimpleImageFilter

public class BrightnessFilter extends SimpleImageFilter
{

    public BrightnessFilter(String s)
    {
        super(s, "brightness");
    }

    protected Program getNativeProgram(FilterContext filtercontext)
    {
        return new NativeProgram("filterpack_imageproc", "brightness");
    }

    protected Program getShaderProgram(FilterContext filtercontext)
    {
        return new ShaderProgram(filtercontext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform float brightness;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  gl_FragColor = brightness * color;\n}\n");
    }

    private static final String mBrightnessShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform float brightness;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  gl_FragColor = brightness * color;\n}\n";
}
