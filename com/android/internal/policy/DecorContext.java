// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.policy;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.view.*;

// Referenced classes of package com.android.internal.policy:
//            PhoneWindow

class DecorContext extends ContextThemeWrapper
{

    public DecorContext(Context context, Resources resources)
    {
        super(context, null);
        mActivityResources = resources;
    }

    public AssetManager getAssets()
    {
        return mActivityResources.getAssets();
    }

    public Resources getResources()
    {
        return mActivityResources;
    }

    public Object getSystemService(String s)
    {
        if("window".equals(s))
        {
            if(mWindowManager == null)
                mWindowManager = ((WindowManagerImpl)super.getSystemService("window")).createLocalWindowManager(mPhoneWindow);
            return mWindowManager;
        } else
        {
            return super.getSystemService(s);
        }
    }

    void setPhoneWindow(PhoneWindow phonewindow)
    {
        mPhoneWindow = phonewindow;
        mWindowManager = null;
    }

    private Resources mActivityResources;
    private PhoneWindow mPhoneWindow;
    private WindowManager mWindowManager;
}
