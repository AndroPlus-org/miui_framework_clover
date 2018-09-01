// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.graphics;

import android.view.Choreographer;

public final class SfVsyncFrameCallbackProvider
    implements android.animation.AnimationHandler.AnimationFrameCallbackProvider
{

    public SfVsyncFrameCallbackProvider()
    {
    }

    public long getFrameDelay()
    {
        return Choreographer.getFrameDelay();
    }

    public long getFrameTime()
    {
        return mChoreographer.getFrameTime();
    }

    public void postCommitCallback(Runnable runnable)
    {
        mChoreographer.postCallback(3, runnable, null);
    }

    public void postFrameCallback(android.view.Choreographer.FrameCallback framecallback)
    {
        mChoreographer.postFrameCallback(framecallback);
    }

    public void setFrameDelay(long l)
    {
        Choreographer.setFrameDelay(l);
    }

    private final Choreographer mChoreographer = Choreographer.getSfInstance();
}
