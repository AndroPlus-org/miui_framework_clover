// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.policy;

import android.view.View;
import android.view.WindowInsets;

// Referenced classes of package com.android.internal.policy:
//            PhoneWindow

class PhoneWindowInjector
{

    private PhoneWindowInjector()
    {
    }

    static void onNavigationBarColorChange(PhoneWindow phonewindow)
    {
        if(phonewindow.getNavigationBarColor() == -1)
            phonewindow.addExtraFlags(64);
        else
            phonewindow.clearExtraFlags(64);
    }

    static void requestApplyInsetsIfNeeded(View view, WindowInsets windowinsets)
    {
        if(view != null && windowinsets != null && windowinsets.getSystemWindowInsetBottom() != sLastInsetBottom)
        {
            sLastInsetBottom = windowinsets.getSystemWindowInsetBottom();
            view.requestApplyInsets();
        }
    }

    private static int sLastInsetBottom;
}
