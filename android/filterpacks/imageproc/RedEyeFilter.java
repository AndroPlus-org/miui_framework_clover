// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;
import android.graphics.*;

public class RedEyeFilter extends Filter
{

    public RedEyeFilter(String s)
    {
        super(s);
        mTileSize = 640;
        mWidth = 0;
        mHeight = 0;
        mTarget = 0;
    }

    private void createRedEyeFrame(FilterContext filtercontext)
    {
        int i = mWidth / 2;
        int j = mHeight / 2;
        Bitmap bitmap = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
        mCanvas.setBitmap(bitmap);
        mPaint.setColor(-1);
        mRadius = Math.max(10F, (float)Math.min(i, j) * 0.06F);
        for(int k = 0; k < mCenters.length; k += 2)
            mCanvas.drawCircle(mCenters[k] * (float)i, mCenters[k + 1] * (float)j, mRadius, mPaint);

        android.filterfw.core.MutableFrameFormat mutableframeformat = ImageFormat.create(i, j, 3, 3);
        mRedEyeFrame = filtercontext.getFrameManager().newFrame(mutableframeformat);
        mRedEyeFrame.setBitmap(bitmap);
        bitmap.recycle();
    }

    private void updateProgramParams()
    {
        if(mCenters.length % 2 == 1)
            throw new RuntimeException("The size of center array must be even.");
        else
            return;
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
            throw new RuntimeException((new StringBuilder()).append("Filter RedEye does not support frames of target ").append(i).append("!").toString());

        case 3: // '\003'
            filtercontext = new ShaderProgram(filtercontext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform float intensity;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  vec4 mask = texture2D(tex_sampler_1, v_texcoord);\n  if (mask.a > 0.0) {\n    float green_blue = color.g + color.b;\n    float red_intensity = color.r / green_blue;\n    if (red_intensity > intensity) {\n      color.r = 0.5 * green_blue;\n    }\n  }\n  gl_FragColor = color;\n}\n");
            break;
        }
        filtercontext.setMaximumTileSize(mTileSize);
        mProgram = filtercontext;
        mProgram.setHostValue("intensity", Float.valueOf(1.3F));
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
        {
            mWidth = frameformat.getWidth();
            mHeight = frameformat.getHeight();
        }
        createRedEyeFrame(filtercontext);
        filtercontext = mRedEyeFrame;
        mProgram.process(new Frame[] {
            frame, filtercontext
        }, frame1);
        pushOutput("image", frame1);
        frame1.release();
        mRedEyeFrame.release();
        mRedEyeFrame = null;
    }

    public void setupPorts()
    {
        addMaskedInputPort("image", ImageFormat.create(3));
        addOutputBasedOnInput("image", "image");
    }

    private static final float DEFAULT_RED_INTENSITY = 1.3F;
    private static final float MIN_RADIUS = 10F;
    private static final float RADIUS_RATIO = 0.06F;
    private final Canvas mCanvas = new Canvas();
    private float mCenters[];
    private int mHeight;
    private final Paint mPaint = new Paint();
    private Program mProgram;
    private float mRadius;
    private Bitmap mRedEyeBitmap;
    private Frame mRedEyeFrame;
    private final String mRedEyeShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform float intensity;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  vec4 mask = texture2D(tex_sampler_1, v_texcoord);\n  if (mask.a > 0.0) {\n    float green_blue = color.g + color.b;\n    float red_intensity = color.r / green_blue;\n    if (red_intensity > intensity) {\n      color.r = 0.5 * green_blue;\n    }\n  }\n  gl_FragColor = color;\n}\n";
    private int mTarget;
    private int mTileSize;
    private int mWidth;
}
