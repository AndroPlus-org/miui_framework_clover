// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.os.*;
import android.view.DisplayEventReceiver;

// Referenced classes of package miui.maml:
//            ScreenElementRoot

public abstract class RenderVsyncUpdater
    implements RendererController.ISelfUpdateRenderable
{
    private final class FrameDisplayEventReceiver extends DisplayEventReceiver
        implements Runnable
    {

        public void onVsync(long l, int i, int j)
        {
            Object obj = RenderVsyncUpdater.this;
            RenderVsyncUpdater._2D_set1(((RenderVsyncUpdater) (obj)), RenderVsyncUpdater._2D_get1(((RenderVsyncUpdater) (obj))) - 1);
            obj = Message.obtain(RenderVsyncUpdater._2D_get0(RenderVsyncUpdater.this), this);
            RenderVsyncUpdater._2D_get0(RenderVsyncUpdater.this).sendMessageAtTime(((Message) (obj)), l / 0xf4240L);
        }

        public void run()
        {
            if(RenderVsyncUpdater._2D_get1(RenderVsyncUpdater.this) <= 0)
                RenderVsyncUpdater._2D_wrap1(RenderVsyncUpdater.this);
            else
                scheduleVsync();
        }

        final RenderVsyncUpdater this$0;

        public FrameDisplayEventReceiver(Looper looper)
        {
            this$0 = RenderVsyncUpdater.this;
            super(looper);
        }
    }


    static Handler _2D_get0(RenderVsyncUpdater rendervsyncupdater)
    {
        return rendervsyncupdater.mHandler;
    }

    static int _2D_get1(RenderVsyncUpdater rendervsyncupdater)
    {
        return rendervsyncupdater.mVsyncLeft;
    }

    static boolean _2D_set0(RenderVsyncUpdater rendervsyncupdater, boolean flag)
    {
        rendervsyncupdater.mStarted = flag;
        return flag;
    }

    static int _2D_set1(RenderVsyncUpdater rendervsyncupdater, int i)
    {
        rendervsyncupdater.mVsyncLeft = i;
        return i;
    }

    static void _2D_wrap0(RenderVsyncUpdater rendervsyncupdater)
    {
        rendervsyncupdater.doRunUpdater();
    }

    static void _2D_wrap1(RenderVsyncUpdater rendervsyncupdater)
    {
        rendervsyncupdater.scheduleFrame();
    }

    public RenderVsyncUpdater(ScreenElementRoot screenelementroot, Handler handler)
    {
        this(screenelementroot, handler, false);
    }

    public RenderVsyncUpdater(ScreenElementRoot screenelementroot, Handler handler, boolean flag)
    {
        mRoot = screenelementroot;
        mHandler = handler;
        mAutoCleanup = flag;
        mDisplayEventReceiver = new FrameDisplayEventReceiver(handler.getLooper());
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
        if(mVsyncLeft > 0)
            mDisplayEventReceiver.scheduleVsync();
        else
            mHandler.post(new Runnable() {

                public void run()
                {
                    RenderVsyncUpdater._2D_wrap1(RenderVsyncUpdater.this);
                }

                final RenderVsyncUpdater this$0;

            
            {
                this$0 = RenderVsyncUpdater.this;
                super();
            }
            }
);
    }

    private void scheduleFrame()
    {
        long l = SystemClock.elapsedRealtime();
        mNextUpdateInterval = mRoot.update(l);
        mVsyncLeft = (int)(mNextUpdateInterval / 16L);
        if(mVsyncLeft > 0)
            mVsyncLeft = mVsyncLeft - 1;
    }

    public void cleanUp()
    {
        mPaused = true;
        mRoot.selfFinish();
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
        long l = checkDelay();
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
    private FrameDisplayEventReceiver mDisplayEventReceiver;
    private Handler mHandler;
    protected long mLastUpdateTime;
    protected long mNextUpdateInterval;
    private boolean mPaused;
    protected boolean mPendingRender;
    private ScreenElementRoot mRoot;
    private Runnable mRunUpdater = new Runnable() {

        public void run()
        {
            RenderVsyncUpdater._2D_wrap0(RenderVsyncUpdater.this);
            RenderVsyncUpdater._2D_set0(RenderVsyncUpdater.this, true);
        }

        final RenderVsyncUpdater this$0;

            
            {
                this$0 = RenderVsyncUpdater.this;
                super();
            }
    }
;
    private boolean mStarted;
    private int mVsyncLeft;
}
