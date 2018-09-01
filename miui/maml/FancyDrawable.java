// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import miui.maml.util.Utils;

// Referenced classes of package miui.maml:
//            MamlDrawable, RendererCore, RenderThread, ScreenElementRootFactory, 
//            ScreenElementRoot, ScreenContext, ResourceManager

public class FancyDrawable extends MamlDrawable
    implements RendererController.IRenderable
{
    static final class FancyDrawableState extends MamlDrawable.MamlDrawableState
    {

        protected MamlDrawable createDrawable()
        {
            return new FancyDrawable(mRendererCore);
        }

        RendererCore mRendererCore;

        public FancyDrawableState(RendererCore renderercore)
        {
            mRendererCore = renderercore;
        }
    }


    static boolean _2D_set0(FancyDrawable fancydrawable, boolean flag)
    {
        fancydrawable.mTimeOut = flag;
        return flag;
    }

    static void _2D_wrap0(FancyDrawable fancydrawable)
    {
        fancydrawable.doPause();
    }

    public FancyDrawable(RendererCore renderercore)
    {
        mHandler = new Handler(Looper.getMainLooper());
        mPauseLock = new Object();
        init(renderercore);
    }

    public FancyDrawable(ScreenElementRoot screenelementroot, RenderThread renderthread)
    {
        mHandler = new Handler(Looper.getMainLooper());
        mPauseLock = new Object();
        init(screenelementroot, renderthread);
    }

    private void doPause()
    {
        Object obj = mPauseLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mPaused;
        if(!flag)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        logd("doPause: ");
        mPaused = true;
        mRendererCore.pauseRenderable(this);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void doResume()
    {
        Object obj = mPauseLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mPaused;
        if(flag)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        logd("doResume: ");
        mPaused = false;
        mRendererCore.resumeRenderable(this);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static FancyDrawable fromZipFile(Context context, String s)
    {
        return fromZipFile(context, s, RenderThread.globalThread(true));
    }

    public static FancyDrawable fromZipFile(Context context, String s, RenderThread renderthread)
    {
        Object obj = null;
        s = ScreenElementRootFactory.create(new ScreenElementRootFactory.Parameter(context, s));
        if(s == null)
            return null;
        s.setDefaultFramerate(0.0F);
        context = null;
        if(s.load())
            context = new RendererCore(s, renderthread);
        if(context == null)
            context = obj;
        else
            context = new FancyDrawable(context);
        return context;
    }

    private void init(RendererCore renderercore)
    {
        if(renderercore == null)
            throw new NullPointerException();
        mState = new FancyDrawableState(renderercore);
        mRendererCore = renderercore;
        mRendererCore.addRenderable(this);
        setIntrinsicSize((int)mRendererCore.getRoot().getWidth(), (int)mRendererCore.getRoot().getHeight());
        renderercore = mRendererCore.getRoot().getContext();
        mQuietDrawable = ((ScreenContext) (renderercore)).mResourceManager.getDrawable(((ScreenContext) (renderercore)).mContext.getResources(), "quietImage.png");
        if(mQuietDrawable != null)
        {
            mQuietDrawable = mQuietDrawable.mutate();
            mQuietDrawable.setBounds(0, 0, mQuietDrawable.getIntrinsicWidth(), mQuietDrawable.getIntrinsicHeight());
        }
        mStartDrawable = ((ScreenContext) (renderercore)).mResourceManager.getDrawable(((ScreenContext) (renderercore)).mContext.getResources(), "startImage.png");
        if(mStartDrawable != null)
        {
            mStartDrawable = mStartDrawable.mutate();
            mStartDrawable.setBounds(0, 0, mStartDrawable.getIntrinsicWidth(), mStartDrawable.getIntrinsicHeight());
        }
    }

    private void init(ScreenElementRoot screenelementroot, RenderThread renderthread)
    {
        logd((new StringBuilder()).append("init  root:").append(screenelementroot.toString()).toString());
        init(new RendererCore(screenelementroot, renderthread));
    }

    private void logd(CharSequence charsequence)
    {
        Log.d("FancyDrawable", (new StringBuilder()).append(charsequence).append("  [").append(toString()).append("]").toString());
    }

    public void cleanUp()
    {
        logd("cleanUp: ");
        mRendererCore.removeRenderable(this);
    }

    public void doRender()
    {
        mHandler.removeCallbacks(mRenderTimeout);
        mHandler.postDelayed(mRenderTimeout, 100L);
        mHandler.post(mInvalidateSelf);
    }

    protected void drawIcon(Canvas canvas)
    {
        mHandler.removeCallbacks(mRenderTimeout);
        if(mTimeOut)
        {
            doResume();
            mTimeOut = false;
        }
        int i;
        i = canvas.save();
        canvas.translate(getBounds().left, getBounds().top);
        canvas.scale((float)mWidth / (float)mIntrinsicWidth, (float)mHeight / (float)mIntrinsicHeight, 0.0F, 0.0F);
        if(Utils.getVariableNumber("useQuietImage", mRendererCore.getRoot().getVariables()) <= 0.0D || mQuietDrawable == null) goto _L2; else goto _L1
_L1:
        mQuietDrawable.draw(canvas);
_L3:
        canvas.restoreToCount(i);
_L4:
        return;
_L2:
        mRendererCore.render(canvas);
          goto _L3
        canvas;
        canvas.printStackTrace();
          goto _L4
    }

    protected void finalize()
        throws Throwable
    {
        cleanUp();
        super.finalize();
    }

    public int getIntrinsicHeight()
    {
        return mIntrinsicHeight;
    }

    public int getIntrinsicWidth()
    {
        return mIntrinsicWidth;
    }

    public Drawable getQuietDrawable()
    {
        return mQuietDrawable;
    }

    public ScreenElementRoot getRoot()
    {
        return mRendererCore.getRoot();
    }

    public Drawable getStartDrawable()
    {
        return mStartDrawable;
    }

    public void onPause()
    {
        getRoot().onCommand("pause");
        doPause();
        mHandler.removeCallbacks(mRenderTimeout);
    }

    public void onResume()
    {
        getRoot().onCommand("resume");
        doResume();
    }

    public void setAlpha(int i)
    {
        if(mQuietDrawable != null)
            mQuietDrawable.setAlpha(i);
        if(mStartDrawable != null)
            mStartDrawable.setAlpha(i);
    }

    public void setBadgeInfo(Drawable drawable, Rect rect)
    {
        while(rect != null && (rect.left < 0 || rect.top < 0 || rect.width() > mIntrinsicWidth || rect.height() > mIntrinsicHeight)) 
            throw new IllegalArgumentException((new StringBuilder()).append("Badge location ").append(rect).append(" not in badged drawable bounds ").append(new Rect(0, 0, mIntrinsicWidth, mIntrinsicHeight)).toString());
        mBadgeDrawable = drawable;
        mBadgeLocation = rect;
        mState.mStateBadgeDrawable = drawable;
        mState.mStateBadgeLocation = rect;
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        if(mQuietDrawable != null)
            mQuietDrawable.setColorFilter(colorfilter);
        if(mStartDrawable != null)
            mStartDrawable.setColorFilter(colorfilter);
        if(mBadgeDrawable != null)
            mBadgeDrawable.setColorFilter(colorfilter);
    }

    private static final boolean DBG = true;
    private static final String LOG_TAG = "FancyDrawable";
    private static final String QUIET_IMAGE_NAME = "quietImage.png";
    private static final int RENDER_TIMEOUT = 100;
    private static final String START_IMAGE_NAME = "startImage.png";
    private static final String USE_QUIET_IMAGE_TAG = "useQuietImage";
    private Handler mHandler;
    private Object mPauseLock;
    private boolean mPaused;
    private Drawable mQuietDrawable;
    private Runnable mRenderTimeout = new Runnable() {

        public void run()
        {
            FancyDrawable._2D_set0(FancyDrawable.this, true);
            FancyDrawable._2D_wrap0(FancyDrawable.this);
        }

        final FancyDrawable this$0;

            
            {
                this$0 = FancyDrawable.this;
                super();
            }
    }
;
    private RendererCore mRendererCore;
    private Drawable mStartDrawable;
    private boolean mTimeOut;
}
