// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.app;

import android.os.*;

// Referenced classes of package com.miui.whetstone.app:
//            IWhetstoneApplicationThread

class WhetstoneApplicationThreadProxy
    implements IWhetstoneApplicationThread
{

    public WhetstoneApplicationThreadProxy(IBinder ibinder)
    {
        mRemote = ibinder;
    }

    public IBinder asBinder()
    {
        return mRemote;
    }

    public android.os.Debug.MemoryInfo dumpMemInfo(String as[])
        throws RemoteException
    {
        Parcel parcel = Parcel.obtain();
        Parcel parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.miui.whetstone.app.IWhetstoneApplicationThread");
        parcel.writeStringArray(as);
        mRemote.transact(1, parcel, parcel1, 0);
        parcel1.readException();
        as = new android.os.Debug.MemoryInfo();
        as.readFromParcel(parcel1);
        parcel.recycle();
        parcel1.recycle();
        return as;
    }

    public boolean longScreenshot(int i)
        throws RemoteException
    {
        Parcel parcel = Parcel.obtain();
        Parcel parcel1 = Parcel.obtain();
        parcel.writeInterfaceToken("com.miui.whetstone.app.IWhetstoneApplicationThread");
        parcel.writeInt(i);
        mRemote.transact(2, parcel, parcel1, 0);
        parcel1.readException();
        boolean flag;
        if(parcel1.readByte() == 0)
            flag = false;
        else
            flag = true;
        parcel.recycle();
        parcel1.recycle();
        return flag;
    }

    private final IBinder mRemote;
}
