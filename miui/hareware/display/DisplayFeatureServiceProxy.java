// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.hareware.display;

import android.os.*;
import android.text.TextUtils;

class DisplayFeatureServiceProxy
{

    DisplayFeatureServiceProxy(IBinder ibinder)
    {
        mService = ibinder;
        mDescriptor = mService.getInterfaceDescriptor();
        if(TextUtils.isEmpty(mDescriptor))
            mDescriptor = "miui.hardware.display.IDisplayFeatureService";
_L2:
        return;
        ibinder;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private transient int callTransact(int i, int ai[])
        throws RemoteException
    {
        int j;
        Parcel parcel;
        Parcel parcel1;
        byte byte0;
        j = 0;
        parcel = Parcel.obtain();
        parcel1 = Parcel.obtain();
        byte0 = -1;
        int k;
        parcel.writeInterfaceToken(mDescriptor);
        k = ai.length;
_L2:
        if(j >= k)
            break; /* Loop/switch isn't completed */
        parcel.writeInt(ai[j]);
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        j = byte0;
        if(mService.transact(i, parcel, parcel1, 0))
        {
            parcel1.readException();
            j = parcel1.readInt();
        }
        parcel1.recycle();
        parcel.recycle();
        return j;
        ai;
        parcel1.recycle();
        parcel.recycle();
        throw ai;
    }

    int setAd(int i, int j, int k)
        throws RemoteException
    {
        return callTransact(3, new int[] {
            i, j, k
        });
    }

    int setCABC(int i, int j)
        throws RemoteException
    {
        return callTransact(5, new int[] {
            i, j
        });
    }

    int setCE(int i, int j)
        throws RemoteException
    {
        return callTransact(4, new int[] {
            i, j
        });
    }

    int setColorPrefer(int i, int j)
        throws RemoteException
    {
        return callTransact(1, new int[] {
            i, j
        });
    }

    int setEyeCare(int i, int j)
        throws RemoteException
    {
        return callTransact(2, new int[] {
            i, j
        });
    }

    int setFeature(int i, int j, int k, int l)
        throws RemoteException
    {
        return callTransact(100, new int[] {
            i, j, k, l
        });
    }

    int setGamutMode(int i, int j)
        throws RemoteException
    {
        return callTransact(6, new int[] {
            i, j
        });
    }

    private static final String INTERFACE_DESCRIPTOR = "miui.hardware.display.IDisplayFeatureService";
    private static final int TRANSACTION_setAd = 3;
    private static final int TRANSACTION_setCABC = 5;
    private static final int TRANSACTION_setCE = 4;
    private static final int TRANSACTION_setColorPrefer = 1;
    private static final int TRANSACTION_setEyeCare = 2;
    private static final int TRANSACTION_setFeature = 100;
    private static final int TRANSACTION_setGamutMode = 6;
    private String mDescriptor;
    private IBinder mService;
}
