// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.ims.stub;

import android.os.Bundle;
import android.os.RemoteException;
import com.android.ims.*;
import com.android.ims.internal.IImsUt;

public class ImsUtListenerImplBase extends com.android.ims.internal.IImsUtListener.Stub
{

    public ImsUtListenerImplBase()
    {
    }

    public void onSupplementaryServiceIndication(ImsSsData imsssdata)
    {
    }

    public void utConfigurationCallBarringQueried(IImsUt iimsut, int i, ImsSsInfo aimsssinfo[])
        throws RemoteException
    {
    }

    public void utConfigurationCallForwardQueried(IImsUt iimsut, int i, ImsCallForwardInfo aimscallforwardinfo[])
        throws RemoteException
    {
    }

    public void utConfigurationCallWaitingQueried(IImsUt iimsut, int i, ImsSsInfo aimsssinfo[])
        throws RemoteException
    {
    }

    public void utConfigurationQueried(IImsUt iimsut, int i, Bundle bundle)
        throws RemoteException
    {
    }

    public void utConfigurationQueryFailed(IImsUt iimsut, int i, ImsReasonInfo imsreasoninfo)
        throws RemoteException
    {
    }

    public void utConfigurationUpdateFailed(IImsUt iimsut, int i, ImsReasonInfo imsreasoninfo)
        throws RemoteException
    {
    }

    public void utConfigurationUpdated(IImsUt iimsut, int i)
        throws RemoteException
    {
    }
}
