// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.*;

// Referenced classes of package android.widget:
//            ImageButton

public class ZoomButton extends ImageButton
    implements android.view.View.OnLongClickListener
{

    static boolean _2D_get0(ZoomButton zoombutton)
    {
        return zoombutton.mIsInLongpress;
    }

    static long _2D_get1(ZoomButton zoombutton)
    {
        return zoombutton.mZoomSpeed;
    }

    public ZoomButton(Context context)
    {
        this(context, null);
    }

    public ZoomButton(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ZoomButton(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ZoomButton(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mRunnable = new Runnable() {

            public void run()
            {
                if(hasOnClickListeners() && ZoomButton._2D_get0(ZoomButton.this) && isEnabled())
                {
                    callOnClick();
                    postDelayed(this, ZoomButton._2D_get1(ZoomButton.this));
                }
            }

            final ZoomButton this$0;

            
            {
                this$0 = ZoomButton.this;
                super();
            }
        }
;
        mZoomSpeed = 1000L;
        setOnLongClickListener(this);
    }

    public boolean dispatchUnhandledMove(View view, int i)
    {
        clearFocus();
        return super.dispatchUnhandledMove(view, i);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/ZoomButton.getName();
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        mIsInLongpress = false;
        return super.onKeyUp(i, keyevent);
    }

    public boolean onLongClick(View view)
    {
        mIsInLongpress = true;
        post(mRunnable);
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(motionevent.getAction() == 3 || motionevent.getAction() == 1)
            mIsInLongpress = false;
        return super.onTouchEvent(motionevent);
    }

    public void setEnabled(boolean flag)
    {
        if(!flag)
            setPressed(false);
        super.setEnabled(flag);
    }

    public void setZoomSpeed(long l)
    {
        mZoomSpeed = l;
    }

    private boolean mIsInLongpress;
    private final Runnable mRunnable;
    private long mZoomSpeed;
}
