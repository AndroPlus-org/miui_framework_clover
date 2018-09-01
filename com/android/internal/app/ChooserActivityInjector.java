// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.content.Context;
import android.content.Intent;
import miui.security.SecurityManager;

public class ChooserActivityInjector
{

    public ChooserActivityInjector()
    {
    }

    public static boolean canBindService(Context context, Intent intent, int i)
    {
        return ((SecurityManager)context.getSystemService("security")).isAllowStartService(intent, i);
    }
}
