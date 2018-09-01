// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.inputmethodservice;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.IBinder;
import android.view.*;

public class SoftInputWindow extends Dialog
{
    public static interface Callback
    {

        public abstract void onBackPressed();
    }


    public SoftInputWindow(Context context, String s, int i, Callback callback, android.view.KeyEvent.Callback callback1, android.view.KeyEvent.DispatcherState dispatcherstate, int j, 
            int k, boolean flag)
    {
        super(context, i);
        mName = s;
        mCallback = callback;
        mKeyEventCallback = callback1;
        mDispatcherState = dispatcherstate;
        mWindowType = j;
        mGravity = k;
        mTakesFocus = flag;
        initDockWindow();
    }

    private void initDockWindow()
    {
        android.view.WindowManager.LayoutParams layoutparams = getWindow().getAttributes();
        layoutparams.type = mWindowType;
        layoutparams.setTitle(mName);
        layoutparams.gravity = mGravity;
        updateWidthHeight(layoutparams);
        getWindow().setAttributes(layoutparams);
        char c = '\u010A';
        char c1;
        if(!mTakesFocus)
        {
            c1 = '\u0108';
        } else
        {
            c1 = '\u0120';
            c = '\u012A';
        }
        getWindow().setFlags(c1, c);
    }

    private void updateWidthHeight(android.view.WindowManager.LayoutParams layoutparams)
    {
        if(layoutparams.gravity == 48 || layoutparams.gravity == 80)
        {
            layoutparams.width = -1;
            layoutparams.height = -2;
        } else
        {
            layoutparams.width = -2;
            layoutparams.height = -1;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionevent)
    {
        getWindow().getDecorView().getHitRect(mBounds);
        if(motionevent.isWithinBoundsNoHistory(mBounds.left, mBounds.top, mBounds.right - 1, mBounds.bottom - 1))
        {
            return super.dispatchTouchEvent(motionevent);
        } else
        {
            motionevent = motionevent.clampNoHistory(mBounds.left, mBounds.top, mBounds.right - 1, mBounds.bottom - 1);
            boolean flag = super.dispatchTouchEvent(motionevent);
            motionevent.recycle();
            return flag;
        }
    }

    public int getGravity()
    {
        return getWindow().getAttributes().gravity;
    }

    public void onBackPressed()
    {
        if(mCallback != null)
            mCallback.onBackPressed();
        else
            super.onBackPressed();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(mKeyEventCallback != null && mKeyEventCallback.onKeyDown(i, keyevent))
            return true;
        else
            return super.onKeyDown(i, keyevent);
    }

    public boolean onKeyLongPress(int i, KeyEvent keyevent)
    {
        if(mKeyEventCallback != null && mKeyEventCallback.onKeyLongPress(i, keyevent))
            return true;
        else
            return super.onKeyLongPress(i, keyevent);
    }

    public boolean onKeyMultiple(int i, int j, KeyEvent keyevent)
    {
        if(mKeyEventCallback != null && mKeyEventCallback.onKeyMultiple(i, j, keyevent))
            return true;
        else
            return super.onKeyMultiple(i, j, keyevent);
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if(mKeyEventCallback != null && mKeyEventCallback.onKeyUp(i, keyevent))
            return true;
        else
            return super.onKeyUp(i, keyevent);
    }

    public void onWindowFocusChanged(boolean flag)
    {
        super.onWindowFocusChanged(flag);
        mDispatcherState.reset();
    }

    public void setGravity(int i)
    {
        android.view.WindowManager.LayoutParams layoutparams = getWindow().getAttributes();
        layoutparams.gravity = i;
        updateWidthHeight(layoutparams);
        getWindow().setAttributes(layoutparams);
    }

    public void setToken(IBinder ibinder)
    {
        android.view.WindowManager.LayoutParams layoutparams = getWindow().getAttributes();
        layoutparams.token = ibinder;
        getWindow().setAttributes(layoutparams);
    }

    private final Rect mBounds = new Rect();
    final Callback mCallback;
    final android.view.KeyEvent.DispatcherState mDispatcherState;
    final int mGravity;
    final android.view.KeyEvent.Callback mKeyEventCallback;
    final String mName;
    final boolean mTakesFocus;
    final int mWindowType;
}
