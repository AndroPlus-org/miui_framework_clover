// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.internal.search;

import android.content.Context;

public interface BackgroundCheckable
{

    public abstract boolean isChecked(Context context);

    public abstract void setChecked(Context context, boolean flag);

    public abstract void toggle(Context context);
}
