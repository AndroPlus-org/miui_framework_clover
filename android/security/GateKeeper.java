// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security;

import android.os.*;
import android.service.gatekeeper.IGateKeeperService;
import android.util.Log;
import miui.securityspace.XSpaceUserHandle;

public abstract class GateKeeper
{

    private GateKeeper()
    {
    }

    public static long getSecureUserId()
        throws IllegalStateException
    {
        long l;
        try
        {
            if(XSpaceUserHandle.isXSpaceUserId(UserHandle.myUserId()))
            {
                Log.d("GateKeeper", "getSecureUserId for user 999");
                return getService().getSecureUserId(0);
            }
            l = getService().getSecureUserId(UserHandle.myUserId());
        }
        catch(RemoteException remoteexception)
        {
            throw new IllegalStateException("Failed to obtain secure user ID from gatekeeper", remoteexception);
        }
        return l;
    }

    public static IGateKeeperService getService()
    {
        IGateKeeperService igatekeeperservice = android.service.gatekeeper.IGateKeeperService.Stub.asInterface(ServiceManager.getService("android.service.gatekeeper.IGateKeeperService"));
        if(igatekeeperservice == null)
            throw new IllegalStateException("Gatekeeper service not available");
        else
            return igatekeeperservice;
    }

    public static final long INVALID_SECURE_USER_ID = 0L;
}
