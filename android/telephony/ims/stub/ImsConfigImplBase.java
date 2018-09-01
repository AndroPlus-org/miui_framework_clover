// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.ims.stub;

import android.os.RemoteException;
import com.android.ims.ImsConfigListener;

public class ImsConfigImplBase extends com.android.ims.internal.IImsConfig.Stub
{

    public ImsConfigImplBase()
    {
    }

    public void getFeatureValue(int i, int j, ImsConfigListener imsconfiglistener)
        throws RemoteException
    {
    }

    public String getProvisionedStringValue(int i)
        throws RemoteException
    {
        return null;
    }

    public int getProvisionedValue(int i)
        throws RemoteException
    {
        return -1;
    }

    public void getVideoQuality(ImsConfigListener imsconfiglistener)
        throws RemoteException
    {
    }

    public boolean getVolteProvisioned()
        throws RemoteException
    {
        return false;
    }

    public void setFeatureValue(int i, int j, int k, ImsConfigListener imsconfiglistener)
        throws RemoteException
    {
    }

    public int setProvisionedStringValue(int i, String s)
        throws RemoteException
    {
        return 1;
    }

    public int setProvisionedValue(int i, int j)
        throws RemoteException
    {
        return 1;
    }

    public void setVideoQuality(int i, ImsConfigListener imsconfiglistener)
        throws RemoteException
    {
    }
}
