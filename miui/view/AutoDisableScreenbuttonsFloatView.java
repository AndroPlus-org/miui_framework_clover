// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class AutoDisableScreenbuttonsFloatView extends FrameLayout
{

    public AutoDisableScreenbuttonsFloatView(Context context)
    {
        super(context);
    }

    public AutoDisableScreenbuttonsFloatView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public static AutoDisableScreenbuttonsFloatView inflate(Context context)
    {
        return (AutoDisableScreenbuttonsFloatView)LayoutInflater.from(context).inflate(0x11030004, null);
    }

    public void dismiss()
    {
        if(!mIsShowing)
        {
            return;
        } else
        {
            mIsShowing = false;
            removeCallbacks(mDismissRunnable);
            ((WindowManager)mContext.getSystemService("window")).removeView(this);
            return;
        }
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
    }

    public void show()
    {
        if(mIsShowing)
            return;
        WindowManager windowmanager = (WindowManager)mContext.getSystemService("window");
        if(windowmanager == null)
        {
            return;
        } else
        {
            android.view.WindowManager.LayoutParams layoutparams = new android.view.WindowManager.LayoutParams(2010);
            layoutparams.gravity = 80;
            layoutparams.height = -2;
            layoutparams.width = -2;
            layoutparams.flags = 264;
            layoutparams.format = -3;
            layoutparams.windowAnimations = 0x110d0008;
            windowmanager.addView(this, layoutparams);
            postDelayed(mDismissRunnable, 8000L);
            mIsShowing = true;
            return;
        }
    }

    private static final int DISMISS_DELAY_TIME = 8000;
    private Runnable mDismissRunnable = new Runnable() {

        public void run()
        {
            dismiss();
        }

        final AutoDisableScreenbuttonsFloatView this$0;

            
            {
                this$0 = AutoDisableScreenbuttonsFloatView.this;
                super();
            }
    }
;
    private boolean mIsShowing;
}
