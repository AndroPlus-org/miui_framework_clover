// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.content.res.Resources;
import android.os.Build;
import android.os.SystemProperties;

public class ScreenShapeHelper
{

    public ScreenShapeHelper()
    {
    }

    public static int getWindowOutsetBottomPx(Resources resources)
    {
        if(Build.IS_EMULATOR)
            return SystemProperties.getInt("ro.emu.win_outset_bottom_px", 0);
        else
            return resources.getInteger(0x10e00e2);
    }
}
