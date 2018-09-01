// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;

// Referenced classes of package android.filterpacks.imageproc:
//            SimpleImageFilter

public class ContrastFilter extends SimpleImageFilter
{

    public ContrastFilter(String s)
    {
        super(s, "contrast");
    }

    protected Program getNativeProgram(FilterContext filtercontext)
    {
        return new NativeProgram("filterpack_imageproc", "contrast");
    }

    protected Program getShaderProgram(FilterContext filtercontext)
    {
        return new ShaderProgram(filtercontext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform float contrast;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  color -= 0.5;\n  color *= contrast;\n  color += 0.5;\n  gl_FragColor = color;\n}\n");
    }

    private static final String mContrastShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform float contrast;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  color -= 0.5;\n  color *= contrast;\n  color += 0.5;\n  gl_FragColor = color;\n}\n";
}
