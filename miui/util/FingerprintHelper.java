// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;

public class FingerprintHelper
{

    public FingerprintHelper()
    {
    }

    public static boolean isFingerprintHardwareDetected(Context context)
    {
        context = (FingerprintManager)context.getSystemService("fingerprint");
        boolean flag;
        if(context == null)
            flag = false;
        else
            flag = context.isHardwareDetected();
        return flag;
    }
}
