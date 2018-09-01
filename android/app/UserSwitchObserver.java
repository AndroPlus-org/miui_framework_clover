// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.IRemoteCallback;
import android.os.RemoteException;

public class UserSwitchObserver extends IUserSwitchObserver.Stub
{

    public UserSwitchObserver()
    {
    }

    public void onForegroundProfileSwitch(int i)
        throws RemoteException
    {
    }

    public void onLockedBootComplete(int i)
        throws RemoteException
    {
    }

    public void onUserSwitchComplete(int i)
        throws RemoteException
    {
    }

    public void onUserSwitching(int i, IRemoteCallback iremotecallback)
        throws RemoteException
    {
        if(iremotecallback != null)
            iremotecallback.sendResult(null);
    }
}
