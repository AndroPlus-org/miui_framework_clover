// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.Activity;
import android.app.IUiModeManager;
import android.os.*;
import android.util.Log;

public class DisableCarModeActivity extends Activity
{

    public DisableCarModeActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        try
        {
            android.app.IUiModeManager.Stub.asInterface(ServiceManager.getService("uimode")).disableCarMode(1);
        }
        // Misplaced declaration of an exception variable
        catch(Bundle bundle)
        {
            Log.e("DisableCarModeActivity", "Failed to disable car mode", bundle);
        }
        finish();
    }

    private static final String TAG = "DisableCarModeActivity";
}
