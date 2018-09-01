// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.ims.feature;

import android.app.PendingIntent;
import android.os.Message;
import com.android.ims.ImsCallProfile;
import com.android.ims.internal.*;

// Referenced classes of package android.telephony.ims.feature:
//            ImsFeature, IMMTelFeature

public class MMTelFeature extends ImsFeature
    implements IMMTelFeature
{

    public MMTelFeature()
    {
    }

    public void addRegistrationListener(IImsRegistrationListener iimsregistrationlistener)
    {
    }

    public ImsCallProfile createCallProfile(int i, int j, int k)
    {
        return null;
    }

    public IImsCallSession createCallSession(int i, ImsCallProfile imscallprofile, IImsCallSessionListener iimscallsessionlistener)
    {
        return null;
    }

    public void endSession(int i)
    {
    }

    public IImsConfig getConfigInterface()
    {
        return null;
    }

    public IImsEcbm getEcbmInterface()
    {
        return null;
    }

    public IImsMultiEndpoint getMultiEndpointInterface()
    {
        return null;
    }

    public IImsCallSession getPendingCallSession(int i, String s)
    {
        return null;
    }

    public IImsUt getUtInterface()
    {
        return null;
    }

    public boolean isConnected(int i, int j)
    {
        return false;
    }

    public boolean isOpened()
    {
        return false;
    }

    public void onFeatureRemoved()
    {
    }

    public void removeRegistrationListener(IImsRegistrationListener iimsregistrationlistener)
    {
    }

    public void setUiTTYMode(int i, Message message)
    {
    }

    public int startSession(PendingIntent pendingintent, IImsRegistrationListener iimsregistrationlistener)
    {
        return 0;
    }

    public void turnOffIms()
    {
    }

    public void turnOnIms()
    {
    }
}
