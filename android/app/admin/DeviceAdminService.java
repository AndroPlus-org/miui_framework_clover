// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.admin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DeviceAdminService extends Service
{
    private class IDeviceAdminServiceImpl extends IDeviceAdminService.Stub
    {

        final DeviceAdminService this$0;

        private IDeviceAdminServiceImpl()
        {
            this$0 = DeviceAdminService.this;
            super();
        }

        IDeviceAdminServiceImpl(IDeviceAdminServiceImpl ideviceadminserviceimpl)
        {
            this();
        }
    }


    public DeviceAdminService()
    {
    }

    public final IBinder onBind(Intent intent)
    {
        return mImpl.asBinder();
    }

    private final IDeviceAdminServiceImpl mImpl = new IDeviceAdminServiceImpl(null);
}
