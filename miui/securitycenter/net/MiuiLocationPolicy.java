// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securitycenter.net;

import android.content.Context;
import android.location.LocationPolicyManager;

public class MiuiLocationPolicy
{

    public MiuiLocationPolicy(Context context)
    {
        mLocalPolicy = LocationPolicyManager.from(context);
    }

    public boolean getAppRestrictBackground(int i)
    {
        boolean flag = false;
        if(mLocalPolicy.getUidPolicy(i) != 0)
            flag = true;
        return flag;
    }

    public void setAppRestrictBackground(int i, boolean flag)
    {
        if(flag)
            mLocalPolicy.setUidPolicy(i, 255);
        else
            mLocalPolicy.setUidPolicy(i, 0);
    }

    LocationPolicyManager mLocalPolicy;
}
