// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.os.*;

// Referenced classes of package com.miui.whetstone:
//            IWhetstoneResult, WhetstoneResult

class WhetstoneResultProxy
    implements IWhetstoneResult
{

    public WhetstoneResultProxy(IBinder ibinder)
    {
        mRemote = ibinder;
    }

    public IBinder asBinder()
    {
        return mRemote;
    }

    public void onResult(WhetstoneResult whetstoneresult)
        throws RemoteException
    {
        Parcel parcel = Parcel.obtain();
        Parcel parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.miui.whetstone.IWhetstoneResult");
        whetstoneresult.writeToParcel(parcel, 0);
        mRemote.transact(1, parcel, parcel1, 0);
        parcel1.readException();
        parcel.recycle();
        parcel1.recycle();
    }

    private IBinder mRemote;
}
