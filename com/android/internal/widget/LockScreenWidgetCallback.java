// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.view.View;

public interface LockScreenWidgetCallback
{

    public abstract boolean isVisible(View view);

    public abstract void requestHide(View view);

    public abstract void requestShow(View view);

    public abstract void userActivity(View view);
}
