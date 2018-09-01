// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.timezone;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;

public final class RulesUpdaterContract
{

    public RulesUpdaterContract()
    {
    }

    public static Intent createUpdaterIntent(String s)
    {
        Intent intent = new Intent("com.android.intent.action.timezone.TRIGGER_RULES_UPDATE_CHECK");
        intent.setPackage(s);
        intent.setFlags(32);
        return intent;
    }

    public static void sendBroadcast(Context context, String s, byte abyte0[])
    {
        s = createUpdaterIntent(s);
        s.putExtra("com.android.intent.extra.timezone.CHECK_TOKEN", abyte0);
        context.sendBroadcastAsUser(s, UserHandle.of(UserHandle.myUserId()), "android.permission.UPDATE_TIME_ZONE_RULES");
    }

    public static final String ACTION_TRIGGER_RULES_UPDATE_CHECK = "com.android.intent.action.timezone.TRIGGER_RULES_UPDATE_CHECK";
    public static final String EXTRA_CHECK_TOKEN = "com.android.intent.extra.timezone.CHECK_TOKEN";
    public static final String TRIGGER_TIME_ZONE_RULES_CHECK_PERMISSION = "android.permission.TRIGGER_TIME_ZONE_RULES_CHECK";
    public static final String UPDATE_TIME_ZONE_RULES_PERMISSION = "android.permission.UPDATE_TIME_ZONE_RULES";
}
