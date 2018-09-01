// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;

import android.view.View;
import android.view.ViewTreeObserver;

public class OneShotPreDrawListener
    implements android.view.ViewTreeObserver.OnPreDrawListener, android.view.View.OnAttachStateChangeListener
{

    private OneShotPreDrawListener(View view, boolean flag, Runnable runnable)
    {
        mView = view;
        mViewTreeObserver = view.getViewTreeObserver();
        mRunnable = runnable;
        mReturnValue = flag;
    }

    public static OneShotPreDrawListener add(View view, Runnable runnable)
    {
        return add(view, true, runnable);
    }

    public static OneShotPreDrawListener add(View view, boolean flag, Runnable runnable)
    {
        runnable = new OneShotPreDrawListener(view, flag, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(runnable);
        view.addOnAttachStateChangeListener(runnable);
        return runnable;
    }

    public boolean onPreDraw()
    {
        removeListener();
        mRunnable.run();
        return mReturnValue;
    }

    public void onViewAttachedToWindow(View view)
    {
        mViewTreeObserver = view.getViewTreeObserver();
    }

    public void onViewDetachedFromWindow(View view)
    {
        removeListener();
    }

    public void removeListener()
    {
        if(mViewTreeObserver.isAlive())
            mViewTreeObserver.removeOnPreDrawListener(this);
        else
            mView.getViewTreeObserver().removeOnPreDrawListener(this);
        mView.removeOnAttachStateChangeListener(this);
    }

    private final boolean mReturnValue;
    private final Runnable mRunnable;
    private final View mView;
    private ViewTreeObserver mViewTreeObserver;
}
