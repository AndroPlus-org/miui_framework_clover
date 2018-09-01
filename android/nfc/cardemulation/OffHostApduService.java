// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc.cardemulation;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public abstract class OffHostApduService extends Service
{

    public OffHostApduService()
    {
    }

    public abstract IBinder onBind(Intent intent);

    public static final String SERVICE_INTERFACE = "android.nfc.cardemulation.action.OFF_HOST_APDU_SERVICE";
    public static final String SERVICE_META_DATA = "android.nfc.cardemulation.off_host_apdu_service";
}
