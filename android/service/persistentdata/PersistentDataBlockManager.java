// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.persistentdata;

import android.os.RemoteException;

// Referenced classes of package android.service.persistentdata:
//            IPersistentDataBlockService

public class PersistentDataBlockManager
{

    public PersistentDataBlockManager(IPersistentDataBlockService ipersistentdatablockservice)
    {
        sService = ipersistentdatablockservice;
    }

    public int getDataBlockSize()
    {
        int i;
        try
        {
            i = sService.getDataBlockSize();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public int getFlashLockState()
    {
        int i;
        try
        {
            i = sService.getFlashLockState();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public long getMaximumDataBlockSize()
    {
        long l;
        try
        {
            l = sService.getMaximumDataBlockSize();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return l;
    }

    public boolean getOemUnlockEnabled()
    {
        boolean flag;
        try
        {
            flag = sService.getOemUnlockEnabled();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public byte[] read()
    {
        byte abyte0[];
        try
        {
            abyte0 = sService.read();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return abyte0;
    }

    public void setOemUnlockEnabled(boolean flag)
    {
        try
        {
            sService.setOemUnlockEnabled(flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void wipe()
    {
        try
        {
            sService.wipe();
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public int write(byte abyte0[])
    {
        int i;
        try
        {
            i = sService.write(abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw abyte0.rethrowFromSystemServer();
        }
        return i;
    }

    public static final int FLASH_LOCK_LOCKED = 1;
    public static final int FLASH_LOCK_UNKNOWN = -1;
    public static final int FLASH_LOCK_UNLOCKED = 0;
    private static final String TAG = android/service/persistentdata/PersistentDataBlockManager.getSimpleName();
    private IPersistentDataBlockService sService;

}
