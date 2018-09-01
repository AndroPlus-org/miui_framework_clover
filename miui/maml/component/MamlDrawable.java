// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.component;

import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import miui.maml.RenderUpdater;
import miui.maml.ScreenElementRoot;

public class MamlDrawable extends Drawable
{

    static Handler _2D_get0(MamlDrawable mamldrawable)
    {
        return mamldrawable.mHandler;
    }

    static Runnable _2D_get1(MamlDrawable mamldrawable)
    {
        return mamldrawable.mInvalidateSelf;
    }

    static Runnable _2D_get2(MamlDrawable mamldrawable)
    {
        return mamldrawable.mRenderTimeout;
    }

    static void _2D_wrap0(MamlDrawable mamldrawable)
    {
        mamldrawable.doPause();
    }

    public MamlDrawable(ScreenElementRoot screenelementroot)
    {
        this(screenelementroot, false);
    }

    public MamlDrawable(ScreenElementRoot screenelementroot, boolean flag)
    {
        mPaused = true;
        mHandler = new Handler(Looper.getMainLooper());
        mRoot = screenelementroot;
        setIntrinsicSize((int)mRoot.getWidth(), (int)mRoot.getHeight());
        mUpdater = new RenderUpdater(mRoot, new Handler(), flag) {

            public void doRenderImp()
            {
                MamlDrawable._2D_get0(MamlDrawable.this).removeCallbacks(MamlDrawable._2D_get2(MamlDrawable.this));
                MamlDrawable._2D_get0(MamlDrawable.this).postDelayed(MamlDrawable._2D_get2(MamlDrawable.this), 100L);
                MamlDrawable._2D_get0(MamlDrawable.this).post(MamlDrawable._2D_get1(MamlDrawable.this));
            }

            final MamlDrawable this$0;

            
            {
                this$0 = MamlDrawable.this;
                super(screenelementroot, handler, flag);
            }
        }
;
        mUpdater.init();
        mUpdater.runUpdater();
    }

    private void doPause()
    {
        if(mPaused)
        {
            return;
        } else
        {
            logd("doPause: ");
            mPaused = true;
            mUpdater.onPause();
            return;
        }
    }

    private void doResume()
    {
        if(!mPaused)
        {
            return;
        } else
        {
            logd("doResume: ");
            mPaused = false;
            mUpdater.onResume();
            return;
        }
    }

    private void logd(CharSequence charsequence)
    {
        Log.d("MamlDrawable", (new StringBuilder()).append(charsequence).append("  [").append(toString()).append("]").toString());
    }

    public void cleanUp()
    {
        logd("cleanUp: ");
        mUpdater.cleanUp();
    }

    public void draw(Canvas canvas)
    {
        mHandler.removeCallbacks(mRenderTimeout);
        doResume();
        int i = canvas.save();
        canvas.translate(getBounds().left, getBounds().top);
        canvas.scale((float)mWidth / (float)mIntrinsicWidth, (float)mHeight / (float)mIntrinsicHeight, 0.0F, 0.0F);
        mRoot.render(canvas);
        canvas.restoreToCount(i);
_L1:
        return;
        canvas;
        canvas.printStackTrace();
        Log.e("MamlDrawable", canvas.toString());
          goto _L1
        canvas;
        canvas.printStackTrace();
        Log.e("MamlDrawable", canvas.toString());
          goto _L1
    }

    public int getIntrinsicHeight()
    {
        return mIntrinsicHeight;
    }

    public int getIntrinsicWidth()
    {
        return mIntrinsicWidth;
    }

    public int getOpacity()
    {
        return -3;
    }

    public ScreenElementRoot getRoot()
    {
        return mRoot;
    }

    public void setAlpha(int i)
    {
    }

    public MamlDrawable setAutoCleanup(boolean flag)
    {
        mUpdater.setAutoCleanup(flag);
        return this;
    }

    public void setBounds(int i, int j, int k, int l)
    {
        super.setBounds(i, j, k, l);
        mWidth = k - i;
        mHeight = l - j;
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
    }

    public void setIntrinsicSize(int i, int j)
    {
        mIntrinsicWidth = i;
        mIntrinsicHeight = j;
    }

    private static final boolean DBG = true;
    private static final String LOG_TAG = "MamlDrawable";
    private static final int RENDER_TIMEOUT = 100;
    private Handler mHandler;
    private int mHeight;
    private int mIntrinsicHeight;
    private int mIntrinsicWidth;
    private Runnable mInvalidateSelf = new Runnable() {

        public void run()
        {
            invalidateSelf();
        }

        final MamlDrawable this$0;

            
            {
                this$0 = MamlDrawable.this;
                super();
            }
    }
;
    private boolean mPaused;
    private Runnable mRenderTimeout = new Runnable() {

        public void run()
        {
            MamlDrawable._2D_wrap0(MamlDrawable.this);
        }

        final MamlDrawable this$0;

            
            {
                this$0 = MamlDrawable.this;
                super();
            }
    }
;
    private ScreenElementRoot mRoot;
    private RenderUpdater mUpdater;
    private int mWidth;
}
