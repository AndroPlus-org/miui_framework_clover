// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.UserHandle;

public class EmergencyAffordanceManager
{

    public EmergencyAffordanceManager(Context context)
    {
        mContext = context;
    }

    private boolean forceShowing()
    {
        boolean flag = false;
        if(android.provider.Settings.Global.getInt(mContext.getContentResolver(), "force_emergency_affordance", 0) != 0)
            flag = true;
        return flag;
    }

    private static Uri getPhoneUri(Context context)
    {
        String s = context.getResources().getString(0x1040138);
        Object obj = s;
        if(Build.IS_DEBUGGABLE)
        {
            context = android.provider.Settings.Global.getString(context.getContentResolver(), "emergency_affordance_number");
            obj = s;
            if(context != null)
                obj = context;
        }
        return Uri.fromParts("tel", ((String) (obj)), null);
    }

    private boolean isEmergencyAffordanceNeeded()
    {
        boolean flag = false;
        if(android.provider.Settings.Global.getInt(mContext.getContentResolver(), "emergency_affordance_needed", 0) != 0)
            flag = true;
        return flag;
    }

    private static void performEmergencyCall(Context context)
    {
        Intent intent = new Intent("android.intent.action.CALL_EMERGENCY");
        intent.setData(getPhoneUri(context));
        intent.setFlags(0x10000000);
        context.startActivityAsUser(intent, UserHandle.CURRENT);
    }

    public boolean needsEmergencyAffordance()
    {
        if(forceShowing())
            return true;
        else
            return isEmergencyAffordanceNeeded();
    }

    public final void performEmergencyCall()
    {
        performEmergencyCall(mContext);
    }

    private static final String EMERGENCY_CALL_NUMBER_SETTING = "emergency_affordance_number";
    public static final boolean ENABLED = true;
    private static final String FORCE_EMERGENCY_AFFORDANCE_SETTING = "force_emergency_affordance";
    private final Context mContext;
}
