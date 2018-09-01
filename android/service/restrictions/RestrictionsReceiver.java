// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.restrictions;

import android.content.*;
import android.os.PersistableBundle;

public abstract class RestrictionsReceiver extends BroadcastReceiver
{

    public RestrictionsReceiver()
    {
    }

    public void onReceive(Context context, Intent intent)
    {
        if("android.content.action.REQUEST_PERMISSION".equals(intent.getAction()))
            onRequestPermission(context, intent.getStringExtra("android.content.extra.PACKAGE_NAME"), intent.getStringExtra("android.content.extra.REQUEST_TYPE"), intent.getStringExtra("android.content.extra.REQUEST_ID"), (PersistableBundle)intent.getParcelableExtra("android.content.extra.REQUEST_BUNDLE"));
    }

    public abstract void onRequestPermission(Context context, String s, String s1, String s2, PersistableBundle persistablebundle);

    private static final String TAG = "RestrictionsReceiver";
}
