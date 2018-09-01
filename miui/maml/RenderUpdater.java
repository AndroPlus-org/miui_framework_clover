// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.os.Handler;
import android.os.SystemClock;

// Referenced classes of package miui.maml:
//            ScreenElementRoot

public abstract class RenderUpdater
    implements RendererController.ISelfUpdateRenderable
{

    static Handler _2D_get0(RenderUpdater renderupdater)
    {
        return renderupdater.mHandler;
    }

    static boolean _2D_get1(RenderUpdater renderupdater)
    {
        return renderupdater.mPaused;
    }

    static ScreenElementRoot _2D_get2(RenderUpdater renderupdater)
    {
        return renderupdater.mRoot;
    }

    static boolean _2D_get3(RenderUpdater renderupdater)
    {
        return renderupdater.mSignaled;
    }

    static Runnable _2D_get4(RenderUpdater renderupdater)
    {
        return renderupdater.mUpdater;
    }

    static boolean _2D_set0(RenderUpdater renderupdater, boolean flag)
    {
        renderupdater.mSignaled = flag;
        return flag;
    }

    static boolean _2D_set1(RenderUpdater renderupdater, boolean flag)
    {
        renderupdater.mStarted = flag;
        return flag;
    }

    static void _2D_wrap0(RenderUpdater renderupdater)
    {
        renderupdater.doRunUpdater();
    }

    public RenderUpdater(ScreenElementRoot screenelementroot, Handler handler)
    {
        this(screenelementroot, handler, false);
    }

    public RenderUpdater(ScreenElementRoot screenelementroot, Handler handler, boolean flag)
    {
        mRoot = screenelementroot;
        mHandler = handler;
        mAutoCleanup = flag;
    }

    private long checkDelay()
    {
        long l = 0L;
        if(mDelay <= 0L)
            return 0L;
        long l1 = SystemClock.elapsedRealtime() - mCreateTime;
        if(l1 < mDelay)
            l = mDelay - l1;
        return l;
    }

    private void doRunUpdater()
    {
        if(mSignaled)
        {
            return;
        } else
        {
            mSignaled = true;
            mHandler.removeCallbacks(mUpdater);
            mHandler.post(mUpdater);
            return;
        }
    }

    public void cleanUp()
    {
        mHandler.removeCallbacks(mUpdater);
        mPaused = true;
        mRoot.selfFinish();
        mSignaled = false;
    }

    public final void doRender()
    {
        mPendingRender = true;
        doRenderImp();
    }

    protected abstract void doRenderImp();

    public void doneRender()
    {
        mPendingRender = false;
        if(!mPaused && mSignaled ^ true && mNextUpdateInterval < 0x7fffffffffffffffL)
            mHandler.postDelayed(mUpdater, mNextUpdateInterval - (SystemClock.elapsedRealtime() - mLastUpdateTime));
    }

    protected void finalize()
        throws Throwable
    {
        if(mAutoCleanup)
            cleanUp();
        super.finalize();
    }

    public void init()
    {
        mPaused = false;
        mRoot.setRenderControllerRenderable(this);
        mRoot.selfInit();
    }

    public boolean isStarted()
    {
        return mStarted;
    }

    public void onPause()
    {
        mRoot.selfPause();
        mSignaled = false;
        mPaused = true;
    }

    public void onResume()
    {
        mPaused = false;
        mRoot.selfResume();
        runUpdater();
    }

    public void runUpdater()
    {
        long l;
        if(mStarted)
            l = 0L;
        else
            l = checkDelay();
        if(l > 0L)
        {
            if(!mHandler.hasCallbacks(mRunUpdater))
                mHandler.postDelayed(mRunUpdater, l);
        } else
        {
            doRunUpdater();
            mStarted = true;
        }
    }

    public void setAutoCleanup(boolean flag)
    {
        mAutoCleanup = flag;
    }

    public void setStartDelay(long l, long l1)
    {
        mCreateTime = l;
        mDelay = l1;
        if(mDelay <= 0L)
            mStarted = true;
    }

    public void triggerUpdate()
    {
        runUpdater();
    }

    private boolean mAutoCleanup;
    private long mCreateTime;
    private long mDelay;
    private Handler mHandler;
    protected long mLastUpdateTime;
    protected long mNextUpdateInterval;
    private boolean mPaused;
    protected boolean mPendingRender;
    private ScreenElementRoot mRoot;
    private Runnable mRunUpdater = new Runnable() {

        public void run()
        {
            RenderUpdater._2D_wrap0(RenderUpdater.this);
            RenderUpdater._2D_set1(RenderUpdater.this, true);
        }

        final RenderUpdater this$0;

            
            {
                this$0 = RenderUpdater.this;
                super();
            }
    }
;
    private boolean mSignaled;
    private boolean mStarted;
    private Runnable mUpdater = new Runnable() {

        public void run()
        {
            RenderUpdater._2D_set0(RenderUpdater.this, false);
            long l = SystemClock.elapsedRealtime();
            mNextUpdateInterval = RenderUpdater._2D_get2(RenderUpdater.this).updateIfNeeded(l);
            mLastUpdateTime = l;
            if(!mPendingRender && RenderUpdater._2D_get1(RenderUpdater.this) ^ true && RenderUpdater._2D_get3(RenderUpdater.this) ^ true && mNextUpdateInterval < 0x7fffffffffffffffL)
                RenderUpdater._2D_get0(RenderUpdater.this).postDelayed(RenderUpdater._2D_get4(RenderUpdater.this), mNextUpdateInterval);
        }

        final RenderUpdater this$0;

            
            {
                this$0 = RenderUpdater.this;
                super();
            }
    }
;
}
