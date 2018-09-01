// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import com.android.internal.util.ArrayUtils;

public class SmsApplicationInjector
{

    public SmsApplicationInjector()
    {
    }

    static boolean isIgnoreSmsStorageApplication(String s)
    {
        return ArrayUtils.contains(IGNORE_PACKAGE_NAMES, s);
    }

    private static final String IGNORE_PACKAGE_NAMES[] = {
        "com.xiaomi.xmsf", "com.miui.cloudservice", "com.miui.networkassistant", "com.miui.yellowpage", "com.miui.securitycenter", "com.xiaomi.simactivate.service", "com.android.mms", "com.xiaomi.finddevice"
    };

}
