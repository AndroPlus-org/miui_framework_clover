// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securitycenter.net;

import android.content.Context;
import android.net.NetworkPolicyManager;

public class MiuiNetworkPolicyManager
{

    public MiuiNetworkPolicyManager(Context context)
    {
        mPolicyService = NetworkPolicyManager.from(context);
    }

    public int getAppRestrictBackground(int i)
    {
        return mPolicyService.getUidPolicy(i);
    }

    public boolean getRestrictBackground()
    {
        return mPolicyService.getRestrictBackground();
    }

    public boolean isAppRestrictBackground(int i)
    {
        boolean flag = true;
        if(mPolicyService.getUidPolicy(i) != 1)
            flag = false;
        return flag;
    }

    public void setAppRestrictBackground(int i, boolean flag)
    {
        NetworkPolicyManager networkpolicymanager = mPolicyService;
        int j;
        if(flag)
            j = 1;
        else
            j = 0;
        networkpolicymanager.setUidPolicy(i, j);
    }

    public void setRestrictBackground(boolean flag)
    {
        mPolicyService.setRestrictBackground(flag);
    }

    private NetworkPolicyManager mPolicyService;
}
