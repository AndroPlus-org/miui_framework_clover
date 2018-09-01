// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.telephony.TelephonyManager;

// Referenced classes of package android.app:
//            BaseMobileDataUtils

public class MobileDataUtils extends BaseMobileDataUtils
{

    public MobileDataUtils()
    {
    }

    public static MobileDataUtils getInstance()
    {
        Object obj = Class.forName("miui.msim.util.MSimMobileDataUtils");
        if(obj == null)
            break MISSING_BLOCK_LABEL_21;
        obj = (MobileDataUtils)((Class) (obj)).newInstance();
        return ((MobileDataUtils) (obj));
        Exception exception;
        exception;
        return new MobileDataUtils();
    }

    public void enableMobileData(Context context, boolean flag)
    {
        ((TelephonyManager)context.getSystemService("phone")).setDataEnabled(flag);
    }

    public Uri getMobileDataUri(int i)
    {
        return android.provider.Settings.Global.getUriFor((new StringBuilder()).append("mobile_data").append(i).toString());
    }

    public boolean isMobileEnable(Context context)
    {
        if(android.os.Build.VERSION.SDK_INT > 23)
            return ((TelephonyManager)context.getSystemService("phone")).getDataEnabled();
        else
            return super.isMobileEnable(context);
    }

    public void registerContentObserver(Context context, ContentObserver contentobserver)
    {
        context.getContentResolver().registerContentObserver(getMobileDataUri(), false, contentobserver);
        TelephonyManager telephonymanager = (TelephonyManager)context.getSystemService("phone");
        for(int i = 0; i < telephonymanager.getPhoneCount(); i++)
            context.getContentResolver().registerContentObserver(getMobileDataUri(i), false, contentobserver);

    }
}
