// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.ims.feature;

import android.app.PendingIntent;
import android.os.Message;
import android.os.RemoteException;
import com.android.ims.ImsCallProfile;
import com.android.ims.internal.*;

public interface IMMTelFeature
{

    public abstract void addRegistrationListener(IImsRegistrationListener iimsregistrationlistener)
        throws RemoteException;

    public abstract ImsCallProfile createCallProfile(int i, int j, int k)
        throws RemoteException;

    public abstract IImsCallSession createCallSession(int i, ImsCallProfile imscallprofile, IImsCallSessionListener iimscallsessionlistener)
        throws RemoteException;

    public abstract void endSession(int i)
        throws RemoteException;

    public abstract IImsConfig getConfigInterface()
        throws RemoteException;

    public abstract IImsEcbm getEcbmInterface()
        throws RemoteException;

    public abstract IImsMultiEndpoint getMultiEndpointInterface()
        throws RemoteException;

    public abstract IImsCallSession getPendingCallSession(int i, String s)
        throws RemoteException;

    public abstract IImsUt getUtInterface()
        throws RemoteException;

    public abstract boolean isConnected(int i, int j)
        throws RemoteException;

    public abstract boolean isOpened()
        throws RemoteException;

    public abstract void removeRegistrationListener(IImsRegistrationListener iimsregistrationlistener)
        throws RemoteException;

    public abstract void setUiTTYMode(int i, Message message)
        throws RemoteException;

    public abstract int startSession(PendingIntent pendingintent, IImsRegistrationListener iimsregistrationlistener)
        throws RemoteException;

    public abstract void turnOffIms()
        throws RemoteException;

    public abstract void turnOnIms()
        throws RemoteException;
}
