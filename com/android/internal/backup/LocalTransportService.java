// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.backup;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

// Referenced classes of package com.android.internal.backup:
//            LocalTransport

public class LocalTransportService extends Service
{

    public LocalTransportService()
    {
    }

    public IBinder onBind(Intent intent)
    {
        return sTransport.getBinder();
    }

    public void onCreate()
    {
        if(sTransport == null)
            sTransport = new LocalTransport(this);
    }

    private static LocalTransport sTransport = null;

}
