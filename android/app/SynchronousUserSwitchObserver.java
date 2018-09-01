// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.IRemoteCallback;
import android.os.RemoteException;

// Referenced classes of package android.app:
//            UserSwitchObserver

public abstract class SynchronousUserSwitchObserver extends UserSwitchObserver
{

    public SynchronousUserSwitchObserver()
    {
    }

    public abstract void onUserSwitching(int i)
        throws RemoteException;

    public final void onUserSwitching(int i, IRemoteCallback iremotecallback)
        throws RemoteException
    {
        onUserSwitching(i);
        if(iremotecallback != null)
            iremotecallback.sendResult(null);
        return;
        Exception exception;
        exception;
        if(iremotecallback != null)
            iremotecallback.sendResult(null);
        throw exception;
    }
}
