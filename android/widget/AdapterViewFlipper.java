// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.*;
import android.content.res.TypedArray;
import android.os.Process;
import android.util.AttributeSet;

// Referenced classes of package android.widget:
//            AdapterViewAnimator, Adapter

public class AdapterViewFlipper extends AdapterViewAnimator
{

    static boolean _2D_get0(AdapterViewFlipper adapterviewflipper)
    {
        return adapterviewflipper.mRunning;
    }

    static boolean _2D_set0(AdapterViewFlipper adapterviewflipper, boolean flag)
    {
        adapterviewflipper.mUserPresent = flag;
        return flag;
    }

    static void _2D_wrap0(AdapterViewFlipper adapterviewflipper)
    {
        adapterviewflipper.updateRunning();
    }

    static void _2D_wrap1(AdapterViewFlipper adapterviewflipper, boolean flag)
    {
        adapterviewflipper.updateRunning(flag);
    }

    public AdapterViewFlipper(Context context)
    {
        super(context);
        mFlipInterval = 10000;
        mAutoStart = false;
        mRunning = false;
        mStarted = false;
        mVisible = false;
        mUserPresent = true;
        mAdvancedByHost = false;
        mReceiver = new BroadcastReceiver() {

            public void onReceive(Context context1, Intent intent)
            {
                context1 = intent.getAction();
                if(!"android.intent.action.SCREEN_OFF".equals(context1)) goto _L2; else goto _L1
_L1:
                AdapterViewFlipper._2D_set0(AdapterViewFlipper.this, false);
                AdapterViewFlipper._2D_wrap0(AdapterViewFlipper.this);
_L4:
                return;
_L2:
                if("android.intent.action.USER_PRESENT".equals(context1))
                {
                    AdapterViewFlipper._2D_set0(AdapterViewFlipper.this, true);
                    AdapterViewFlipper._2D_wrap1(AdapterViewFlipper.this, false);
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            final AdapterViewFlipper this$0;

            
            {
                this$0 = AdapterViewFlipper.this;
                super();
            }
        }
;
        mFlipRunnable = new Runnable() {

            public void run()
            {
                if(AdapterViewFlipper._2D_get0(AdapterViewFlipper.this))
                    showNext();
            }

            final AdapterViewFlipper this$0;

            
            {
                this$0 = AdapterViewFlipper.this;
                super();
            }
        }
;
    }

    public AdapterViewFlipper(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public AdapterViewFlipper(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public AdapterViewFlipper(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mFlipInterval = 10000;
        mAutoStart = false;
        mRunning = false;
        mStarted = false;
        mVisible = false;
        mUserPresent = true;
        mAdvancedByHost = false;
        mReceiver = new _cls1();
        mFlipRunnable = new _cls2();
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.AdapterViewFlipper, i, j);
        mFlipInterval = context.getInt(0, 10000);
        mAutoStart = context.getBoolean(1, false);
        mLoopViews = true;
        context.recycle();
    }

    private void updateRunning()
    {
        updateRunning(true);
    }

    private void updateRunning(boolean flag)
    {
        boolean flag1;
        if(!mAdvancedByHost && mVisible && mStarted && mUserPresent)
        {
            if(mAdapter != null)
                flag1 = true;
            else
                flag1 = false;
        } else
        {
            flag1 = false;
        }
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

    public void fyiWillBeAdvancedByHostKThx()
    {
        mAdvancedByHost = true;
        updateRunning(false);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/AdapterViewFlipper.getName();
    }

    public int getFlipInterval()
    {
        return mFlipInterval;
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

    public void setAdapter(Adapter adapter)
    {
        super.setAdapter(adapter);
        updateRunning();
    }

    public void setAutoStart(boolean flag)
    {
        mAutoStart = flag;
    }

    public void setFlipInterval(int i)
    {
        mFlipInterval = i;
    }

    public void showNext()
    {
        if(mRunning)
        {
            removeCallbacks(mFlipRunnable);
            postDelayed(mFlipRunnable, mFlipInterval);
        }
        super.showNext();
    }

    public void showPrevious()
    {
        if(mRunning)
        {
            removeCallbacks(mFlipRunnable);
            postDelayed(mFlipRunnable, mFlipInterval);
        }
        super.showPrevious();
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

    private static final int DEFAULT_INTERVAL = 10000;
    private static final boolean LOGD = false;
    private static final String TAG = "ViewFlipper";
    private boolean mAdvancedByHost;
    private boolean mAutoStart;
    private int mFlipInterval;
    private final Runnable mFlipRunnable;
    private final BroadcastReceiver mReceiver;
    private boolean mRunning;
    private boolean mStarted;
    private boolean mUserPresent;
    private boolean mVisible;
}
