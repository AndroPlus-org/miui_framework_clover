// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;
import android.graphics.Bitmap;

public class BitmapOverlayFilter extends Filter
{

    public BitmapOverlayFilter(String s)
    {
        super(s);
        mTileSize = 640;
        mTarget = 0;
    }

    private Frame createBitmapFrame(FilterContext filtercontext)
    {
        android.filterfw.core.MutableFrameFormat mutableframeformat = ImageFormat.create(mBitmap.getWidth(), mBitmap.getHeight(), 3, 3);
        filtercontext = filtercontext.getFrameManager().newFrame(mutableframeformat);
        filtercontext.setBitmap(mBitmap);
        mBitmap.recycle();
        mBitmap = null;
        return filtercontext;
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
            filtercontext = new ShaderProgram(filtercontext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 original = texture2D(tex_sampler_0, v_texcoord);\n  vec4 mask = texture2D(tex_sampler_1, v_texcoord);\n  gl_FragColor = vec4(original.rgb * (1.0 - mask.a) + mask.rgb, 1.0);\n}\n");
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
        if(mBitmap != null)
        {
            filtercontext = createBitmapFrame(filtercontext);
            mProgram.process(new Frame[] {
                frame, filtercontext
            }, frame1);
            filtercontext.release();
        } else
        {
            frame1.setDataFromFrame(frame);
        }
        pushOutput("image", frame1);
        frame1.release();
    }

    public void setupPorts()
    {
        addMaskedInputPort("image", ImageFormat.create(3));
        addOutputBasedOnInput("image", "image");
    }

    public void tearDown(FilterContext filtercontext)
    {
        if(mFrame != null)
        {
            mFrame.release();
            mFrame = null;
        }
    }

    private Bitmap mBitmap;
    private Frame mFrame;
    private final String mOverlayShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 original = texture2D(tex_sampler_0, v_texcoord);\n  vec4 mask = texture2D(tex_sampler_1, v_texcoord);\n  gl_FragColor = vec4(original.rgb * (1.0 - mask.a) + mask.rgb, 1.0);\n}\n";
    private Program mProgram;
    private int mTarget;
    private int mTileSize;
}
