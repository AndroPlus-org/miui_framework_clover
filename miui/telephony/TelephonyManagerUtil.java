// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.telephony;


// Referenced classes of package miui.telephony:
//            TelephonyManager

public class TelephonyManagerUtil
{

    public TelephonyManagerUtil()
    {
    }

    public static String getDeviceId()
    {
        return TelephonyManager.getDefault().getMiuiDeviceId();
    }
}
