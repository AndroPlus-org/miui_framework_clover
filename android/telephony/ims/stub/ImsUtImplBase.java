// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.ims.stub;

import android.os.Bundle;
import android.os.RemoteException;
import com.android.ims.internal.IImsUtListener;

public class ImsUtImplBase extends com.android.ims.internal.IImsUt.Stub
{

    public ImsUtImplBase()
    {
    }

    public void close()
        throws RemoteException
    {
    }

    public int queryCFForServiceClass(int i, String s, int j)
        throws RemoteException
    {
        return -1;
    }

    public int queryCLIP()
        throws RemoteException
    {
        return -1;
    }

    public int queryCLIR()
        throws RemoteException
    {
        return -1;
    }

    public int queryCOLP()
        throws RemoteException
    {
        return -1;
    }

    public int queryCOLR()
        throws RemoteException
    {
        return -1;
    }

    public int queryCallBarring(int i)
        throws RemoteException
    {
        return -1;
    }

    public int queryCallBarringForServiceClass(int i, int j)
        throws RemoteException
    {
        return -1;
    }

    public int queryCallForward(int i, String s)
        throws RemoteException
    {
        return -1;
    }

    public int queryCallWaiting()
        throws RemoteException
    {
        return -1;
    }

    public void setListener(IImsUtListener iimsutlistener)
        throws RemoteException
    {
    }

    public int transact(Bundle bundle)
        throws RemoteException
    {
        return -1;
    }

    public int updateCLIP(boolean flag)
        throws RemoteException
    {
        return -1;
    }

    public int updateCLIR(int i)
        throws RemoteException
    {
        return -1;
    }

    public int updateCOLP(boolean flag)
        throws RemoteException
    {
        return -1;
    }

    public int updateCOLR(int i)
        throws RemoteException
    {
        return -1;
    }

    public int updateCallBarring(int i, int j, String as[])
        throws RemoteException
    {
        return -1;
    }

    public int updateCallBarringForServiceClass(int i, int j, int k, String as[])
        throws RemoteException
    {
        return -1;
    }

    public int updateCallForward(int i, int j, String s, int k, int l)
        throws RemoteException
    {
        return 0;
    }

    public int updateCallWaiting(boolean flag, int i)
        throws RemoteException
    {
        return -1;
    }
}
