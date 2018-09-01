// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.util.Log;

public abstract class SettingInjectorService extends Service
{

    public SettingInjectorService(String s)
    {
        mName = s;
    }

    private void onHandleIntent(Intent intent)
    {
        boolean flag;
        try
        {
            flag = onGetEnabled();
        }
        catch(RuntimeException runtimeexception)
        {
            sendStatus(intent, true);
            throw runtimeexception;
        }
        sendStatus(intent, flag);
    }

    private void sendStatus(Intent intent, boolean flag)
    {
        Message message;
        message = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putBoolean("enabled", flag);
        message.setData(bundle);
        if(Log.isLoggable("SettingInjectorService", 3))
            Log.d("SettingInjectorService", (new StringBuilder()).append(mName).append(": received ").append(intent).append(", enabled=").append(flag).append(", sending message: ").append(message).toString());
        intent = (Messenger)intent.getParcelableExtra("messenger");
        intent.send(message);
_L1:
        return;
        intent;
        Log.e("SettingInjectorService", (new StringBuilder()).append(mName).append(": sending dynamic status failed").toString(), intent);
          goto _L1
    }

    public final IBinder onBind(Intent intent)
    {
        return null;
    }

    protected abstract boolean onGetEnabled();

    protected abstract String onGetSummary();

    public final void onStart(Intent intent, int i)
    {
        super.onStart(intent, i);
    }

    public final int onStartCommand(Intent intent, int i, int j)
    {
        onHandleIntent(intent);
        stopSelf(j);
        return 2;
    }

    public static final String ACTION_INJECTED_SETTING_CHANGED = "android.location.InjectedSettingChanged";
    public static final String ACTION_SERVICE_INTENT = "android.location.SettingInjectorService";
    public static final String ATTRIBUTES_NAME = "injected-location-setting";
    public static final String ENABLED_KEY = "enabled";
    public static final String MESSENGER_KEY = "messenger";
    public static final String META_DATA_NAME = "android.location.SettingInjectorService";
    private static final String TAG = "SettingInjectorService";
    private final String mName;
}
