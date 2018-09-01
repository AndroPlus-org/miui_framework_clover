// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;


// Referenced classes of package com.android.internal.widget:
//            LockScreenWidgetCallback

public interface LockScreenWidgetInterface
{

    public abstract boolean providesClock();

    public abstract void setCallback(LockScreenWidgetCallback lockscreenwidgetcallback);
}
