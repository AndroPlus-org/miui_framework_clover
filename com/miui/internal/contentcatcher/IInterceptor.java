// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.internal.contentcatcher;

import android.app.Activity;
import android.view.*;

public interface IInterceptor
{

    public abstract boolean dispatchKeyEvent(KeyEvent keyevent, View view, Activity activity);

    public abstract boolean dispatchTouchEvent(MotionEvent motionevent, View view, Activity activity);

    public abstract void notifyActivityCreate();

    public abstract void notifyActivityDestroy();

    public abstract void notifyActivityPause();

    public abstract void notifyActivityResume();

    public abstract void notifyActivityStart();

    public abstract void notifyActivityStop();

    public abstract void notifyContentChange();

    public abstract void notifyWebView(View view, boolean flag);

    public abstract void setWebView(View view, boolean flag);
}
