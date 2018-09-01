// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.telephony;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.telephony.Rlog;
import java.util.ArrayList;
import java.util.List;

public abstract class MiuiTelephonyBase extends IMiuiTelephony.Stub
{

    public MiuiTelephonyBase()
    {
    }

    public Bundle getCellLocationForSlot(int i, String s)
    {
        Rlog.d(LOG_TAG, "unexpected getCellLocation method call");
        return null;
    }

    public String getDeviceId(String s)
    {
        return null;
    }

    public List getDeviceIdList(String s)
    {
        Rlog.d(LOG_TAG, "unexpected getDeviceIdList method call");
        return new ArrayList(0);
    }

    public String getImei(int i, String s)
    {
        return null;
    }

    public List getImeiList(String s)
    {
        return new ArrayList(0);
    }

    public String getMeid(int i, String s)
    {
        return null;
    }

    public List getMeidList(String s)
    {
        return new ArrayList(0);
    }

    public String getSmallDeviceId(String s)
    {
        return null;
    }

    public boolean isImsRegistered(int i)
    {
        return false;
    }

    public boolean isVolteEnabledByPlatform()
    {
        return false;
    }

    public boolean isVolteEnabledByPlatformForSlot(int i)
    {
        return false;
    }

    public boolean isVolteEnabledByUser()
    {
        return false;
    }

    public boolean isVolteEnabledByUserForSlot(int i)
    {
        return false;
    }

    public boolean isVtEnabledByPlatform()
    {
        return false;
    }

    public boolean isVtEnabledByPlatformForSlot(int i)
    {
        return false;
    }

    public void setCallForwardingOption(int i, int j, int k, String s, ResultReceiver resultreceiver)
    {
        Rlog.d(LOG_TAG, "unexpected setCallForwardingOption method call");
    }

    public void setIccCardActivate(int i, boolean flag)
    {
    }

    private static String LOG_TAG = "MiuiTelephonyBase";

}
