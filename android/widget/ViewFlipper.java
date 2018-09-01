// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.*;
import android.content.res.TypedArray;
import android.os.Process;
import android.util.AttributeSet;

// Referenced classes of package android.widget:
//            ViewAnimator

public class ViewFlipper extends ViewAnimator
{

    static int _2D_get0(ViewFlipper viewflipper)
    {
        return viewflipper.mFlipInterval;
    }

    static Runnable _2D_get1(ViewFlipper viewflipper)
    {
        return viewflipper.mFlipRunnable;
    }

    static boolean _2D_get2(ViewFlipper viewflipper)
    {
        return viewflipper.mRunning;
    }

    static boolean _2D_set0(ViewFlipper viewflipper, boolean flag)
    {
        viewflipper.mUserPresent = flag;
        return flag;
    }

    static void _2D_wrap0(ViewFlipper viewflipper)
    {
        viewflipper.updateRunning();
    }

    static void _2D_wrap1(ViewFlipper viewflipper, boolean flag)
    {
        viewflipper.updateRunning(flag);
    }

    public ViewFlipper(Context context)
    {
        super(context);
        mFlipInterval = 3000;
        mAutoStart = false;
        mRunning = false;
        mStarted = false;
        mVisible = false;
        mUserPresent = true;
        mReceiver = new BroadcastReceiver() {

            public void onReceive(Context context1, Intent intent)
            {
                context1 = intent.getAction();
                if(!"android.intent.action.SCREEN_OFF".equals(context1)) goto _L2; else goto _L1
_L1:
                ViewFlipper._2D_set0(ViewFlipper.this, false);
                ViewFlipper._2D_wrap0(ViewFlipper.this);
_L4:
                return;
_L2:
                if("android.intent.action.USER_PRESENT".equals(context1))
                {
                    ViewFlipper._2D_set0(ViewFlipper.this, true);
                    ViewFlipper._2D_wrap1(ViewFlipper.this, false);
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            final ViewFlipper this$0;

            
            {
                this$0 = ViewFlipper.this;
                super();
            }
        }
;
        mFlipRunnable = new Runnable() {

            public void run()
            {
                if(ViewFlipper._2D_get2(ViewFlipper.this))
                {
                    showNext();
                    postDelayed(ViewFlipper._2D_get1(ViewFlipper.this), ViewFlipper._2D_get0(ViewFlipper.this));
                }
            }

            final ViewFlipper this$0;

            
            {
                this$0 = ViewFlipper.this;
                super();
            }
        }
;
    }

    public ViewFlipper(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mFlipInterval = 3000;
        mAutoStart = false;
        mRunning = false;
        mStarted = false;
        mVisible = false;
        mUserPresent = true;
        mReceiver = new _cls1();
        mFlipRunnable = new _cls2();
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ViewFlipper);
        mFlipInterval = context.getInt(0, 3000);
        mAutoStart = context.getBoolean(1, false);
        context.recycle();
    }

    private void updateRunning()
    {
        updateRunning(true);
    }

    private void updateRunning(boolean flag)
    {
        boolean flag1;
        if(mVisible && mStarted)
            flag1 = mUserPresent;
        else
            flag1 = false;
        if(flag1 != mRunning)
        {
            if(flag1)
            {
                showOnly(mWhichChild, flag);
                postDelayed(mFlipRunnable, mFlipInterval);
            } else
            {
                removeCallbacks(mFlipRunnable);
            }
            mRunning = flag1;
        }
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/ViewFlipper.getName();
    }

    public boolean isAutoStart()
    {
        return mAutoStart;
    }

    public boolean isFlipping()
    {
        return mStarted;
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("android.intent.action.SCREEN_OFF");
        intentfilter.addAction("android.intent.action.USER_PRESENT");
        getContext().registerReceiverAsUser(mReceiver, Process.myUserHandle(), intentfilter, null, getHandler());
        if(mAutoStart)
            startFlipping();
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        mVisible = false;
        getContext().unregisterReceiver(mReceiver);
        updateRunning();
    }

    protected void onWindowVisibilityChanged(int i)
    {
        super.onWindowVisibilityChanged(i);
        boolean flag;
        if(i == 0)
            flag = true;
        else
            flag = false;
        mVisible = flag;
        updateRunning(false);
    }

    public void setAutoStart(boolean flag)
    {
        mAutoStart = flag;
    }

    public void setFlipInterval(int i)
    {
        mFlipInterval = i;
    }

    public void startFlipping()
    {
        mStarted = true;
        updateRunning();
    }

    public void stopFlipping()
    {
        mStarted = false;
        updateRunning();
    }

    private static final int DEFAULT_INTERVAL = 3000;
    private static final boolean LOGD = false;
    private static final String TAG = "ViewFlipper";
    private boolean mAutoStart;
    private int mFlipInterval;
    private final Runnable mFlipRunnable;
    private final BroadcastReceiver mReceiver;
    private boolean mRunning;
    private boolean mStarted;
    private boolean mUserPresent;
    private boolean mVisible;
}
