// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.ims.stub;

import android.os.Message;
import android.os.RemoteException;
import com.android.ims.ImsCallProfile;
import com.android.ims.ImsStreamMediaProfile;
import com.android.ims.internal.IImsCallSessionListener;
import com.android.ims.internal.IImsVideoCallProvider;

public class ImsCallSessionImplBase extends com.android.ims.internal.IImsCallSession.Stub
{

    public ImsCallSessionImplBase()
    {
    }

    public void accept(int i, ImsStreamMediaProfile imsstreammediaprofile)
        throws RemoteException
    {
    }

    public void close()
        throws RemoteException
    {
    }

    public void extendToConference(String as[])
        throws RemoteException
    {
    }

    public String getCallId()
        throws RemoteException
    {
        return null;
    }

    public ImsCallProfile getCallProfile()
        throws RemoteException
    {
        return null;
    }

    public ImsCallProfile getLocalCallProfile()
        throws RemoteException
    {
        return null;
    }

    public String getProperty(String s)
        throws RemoteException
    {
        return null;
    }

    public ImsCallProfile getRemoteCallProfile()
        throws RemoteException
    {
        return null;
    }

    public int getState()
        throws RemoteException
    {
        return -1;
    }

    public IImsVideoCallProvider getVideoCallProvider()
        throws RemoteException
    {
        return null;
    }

    public void hold(ImsStreamMediaProfile imsstreammediaprofile)
        throws RemoteException
    {
    }

    public void inviteParticipants(String as[])
        throws RemoteException
    {
    }

    public boolean isInCall()
        throws RemoteException
    {
        return false;
    }

    public boolean isMultiparty()
        throws RemoteException
    {
        return false;
    }

    public void merge()
        throws RemoteException
    {
    }

    public void reject(int i)
        throws RemoteException
    {
    }

    public void removeParticipants(String as[])
        throws RemoteException
    {
    }

    public void resume(ImsStreamMediaProfile imsstreammediaprofile)
        throws RemoteException
    {
    }

    public void sendDtmf(char c, Message message)
        throws RemoteException
    {
    }

    public void sendRttMessage(String s)
    {
    }

    public void sendRttModifyRequest(ImsCallProfile imscallprofile)
    {
    }

    public void sendRttModifyResponse(boolean flag)
    {
    }

    public void sendUssd(String s)
        throws RemoteException
    {
    }

    public void setListener(IImsCallSessionListener iimscallsessionlistener)
        throws RemoteException
    {
    }

    public void setMute(boolean flag)
        throws RemoteException
    {
    }

    public void start(String s, ImsCallProfile imscallprofile)
        throws RemoteException
    {
    }

    public void startConference(String as[], ImsCallProfile imscallprofile)
        throws RemoteException
    {
    }

    public void startDtmf(char c)
        throws RemoteException
    {
    }

    public void stopDtmf()
        throws RemoteException
    {
    }

    public void terminate(int i)
        throws RemoteException
    {
    }

    public void update(int i, ImsStreamMediaProfile imsstreammediaprofile)
        throws RemoteException
    {
    }
}
