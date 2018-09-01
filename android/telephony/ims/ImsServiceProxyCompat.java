// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.ims;

import android.app.PendingIntent;
import android.os.*;
import android.telephony.ims.feature.IMMTelFeature;
import com.android.ims.ImsCallProfile;
import com.android.ims.internal.*;

public class ImsServiceProxyCompat
    implements IMMTelFeature
{

    public ImsServiceProxyCompat(int i, IBinder ibinder)
    {
        mSlotId = i;
        mBinder = ibinder;
    }

    private IImsService getServiceInterface(IBinder ibinder)
    {
        return com.android.ims.internal.IImsService.Stub.asInterface(ibinder);
    }

    public void addRegistrationListener(IImsRegistrationListener iimsregistrationlistener)
        throws RemoteException
    {
        checkBinderConnection();
        getServiceInterface(mBinder).addRegistrationListener(mSlotId, 1, iimsregistrationlistener);
    }

    protected void checkBinderConnection()
        throws RemoteException
    {
        if(!isBinderAlive())
            throw new RemoteException("ImsServiceProxy is not available for that feature.");
        else
            return;
    }

    public ImsCallProfile createCallProfile(int i, int j, int k)
        throws RemoteException
    {
        checkBinderConnection();
        return getServiceInterface(mBinder).createCallProfile(i, j, k);
    }

    public IImsCallSession createCallSession(int i, ImsCallProfile imscallprofile, IImsCallSessionListener iimscallsessionlistener)
        throws RemoteException
    {
        checkBinderConnection();
        return getServiceInterface(mBinder).createCallSession(i, imscallprofile, iimscallsessionlistener);
    }

    public void endSession(int i)
        throws RemoteException
    {
        checkBinderConnection();
        getServiceInterface(mBinder).close(i);
    }

    public IImsConfig getConfigInterface()
        throws RemoteException
    {
        checkBinderConnection();
        return getServiceInterface(mBinder).getConfigInterface(mSlotId);
    }

    public IImsEcbm getEcbmInterface()
        throws RemoteException
    {
        checkBinderConnection();
        return getServiceInterface(mBinder).getEcbmInterface(1);
    }

    public int getFeatureStatus()
    {
        return 2;
    }

    public IImsMultiEndpoint getMultiEndpointInterface()
        throws RemoteException
    {
        checkBinderConnection();
        return getServiceInterface(mBinder).getMultiEndpointInterface(1);
    }

    public IImsCallSession getPendingCallSession(int i, String s)
        throws RemoteException
    {
        checkBinderConnection();
        return getServiceInterface(mBinder).getPendingCallSession(i, s);
    }

    public IImsUt getUtInterface()
        throws RemoteException
    {
        checkBinderConnection();
        return getServiceInterface(mBinder).getUtInterface(1);
    }

    public boolean isBinderAlive()
    {
        boolean flag;
        if(mBinder != null)
            flag = mBinder.isBinderAlive();
        else
            flag = false;
        return flag;
    }

    public boolean isConnected(int i, int j)
        throws RemoteException
    {
        checkBinderConnection();
        return getServiceInterface(mBinder).isConnected(1, i, j);
    }

    public boolean isOpened()
        throws RemoteException
    {
        checkBinderConnection();
        return getServiceInterface(mBinder).isOpened(1);
    }

    public void removeRegistrationListener(IImsRegistrationListener iimsregistrationlistener)
        throws RemoteException
    {
    }

    public void setUiTTYMode(int i, Message message)
        throws RemoteException
    {
        checkBinderConnection();
        getServiceInterface(mBinder).setUiTTYMode(1, i, message);
    }

    public int startSession(PendingIntent pendingintent, IImsRegistrationListener iimsregistrationlistener)
        throws RemoteException
    {
        checkBinderConnection();
        return getServiceInterface(mBinder).open(mSlotId, 1, pendingintent, iimsregistrationlistener);
    }

    public void turnOffIms()
        throws RemoteException
    {
        checkBinderConnection();
        getServiceInterface(mBinder).turnOffIms(mSlotId);
    }

    public void turnOnIms()
        throws RemoteException
    {
        checkBinderConnection();
        getServiceInterface(mBinder).turnOnIms(mSlotId);
    }

    private static final int SERVICE_ID = 1;
    protected IBinder mBinder;
    protected final int mSlotId;
}
