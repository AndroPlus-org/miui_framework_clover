// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securityspace;

import android.content.Intent;

public class XSpaceIntentCompat
{

    public XSpaceIntentCompat()
    {
    }

    public static void prepareToLeaveUser(Intent intent, int i)
    {
        intent.prepareToLeaveUser(i);
    }
}
