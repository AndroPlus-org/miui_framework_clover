// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.oemlock;

import android.os.RemoteException;

// Referenced classes of package android.service.oemlock:
//            IOemLockService

public class OemLockManager
{

    public OemLockManager(IOemLockService ioemlockservice)
    {
        mService = ioemlockservice;
    }

    public boolean isDeviceOemUnlocked()
    {
        boolean flag;
        try
        {
            flag = mService.isDeviceOemUnlocked();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isOemUnlockAllowed()
    {
        boolean flag;
        try
        {
            flag = mService.isOemUnlockAllowed();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isOemUnlockAllowedByCarrier()
    {
        boolean flag;
        try
        {
            flag = mService.isOemUnlockAllowedByCarrier();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isOemUnlockAllowedByUser()
    {
        boolean flag;
        try
        {
            flag = mService.isOemUnlockAllowedByUser();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void setOemUnlockAllowedByCarrier(boolean flag, byte abyte0[])
    {
        try
        {
            mService.setOemUnlockAllowedByCarrier(flag, abyte0);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw abyte0.rethrowFromSystemServer();
        }
    }

    public void setOemUnlockAllowedByUser(boolean flag)
    {
        try
        {
            mService.setOemUnlockAllowedByUser(flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    private IOemLockService mService;
}
