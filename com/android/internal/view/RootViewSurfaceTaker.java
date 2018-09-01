// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;


public interface RootViewSurfaceTaker
{

    public abstract void onRootViewScrollYChanged(int i);

    public abstract void setSurfaceFormat(int i);

    public abstract void setSurfaceKeepScreenOn(boolean flag);

    public abstract void setSurfaceType(int i);

    public abstract android.view.InputQueue.Callback willYouTakeTheInputQueue();

    public abstract android.view.SurfaceHolder.Callback2 willYouTakeTheSurface();
}
