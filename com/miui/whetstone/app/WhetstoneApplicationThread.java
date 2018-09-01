// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.app;

import android.os.*;

// Referenced classes of package com.miui.whetstone.app:
//            IWhetstoneApplicationThread, WhetstoneApplicationThreadProxy

public class WhetstoneApplicationThread extends Binder
    implements IWhetstoneApplicationThread
{

    public WhetstoneApplicationThread()
    {
        attachInterface(this, "com.miui.whetstone.app.IWhetstoneApplicationThread");
    }

    public static IWhetstoneApplicationThread asInterface(IBinder ibinder)
    {
        if(ibinder == null)
            return null;
        IWhetstoneApplicationThread iwhetstoneapplicationthread = (IWhetstoneApplicationThread)ibinder.queryLocalInterface("com.miui.whetstone.app.IWhetstoneApplicationThread");
        if(iwhetstoneapplicationthread != null)
            return iwhetstoneapplicationthread;
        else
            return new WhetstoneApplicationThreadProxy(ibinder);
    }

    public IBinder asBinder()
    {
        return this;
    }

    public android.os.Debug.MemoryInfo dumpMemInfo(String as[])
        throws RemoteException
    {
        as = new android.os.Debug.MemoryInfo();
        Debug.getMemoryInfo(as);
        return as;
    }

    public boolean longScreenshot(int i)
        throws RemoteException
    {
        if(mContentPort == null)
            mContentPort = new miui.util.LongScreenshotUtils.ContentPort();
        return mContentPort.longScreenshot(i);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
        throws RemoteException
    {
        boolean flag = false;
        switch(i)
        {
        default:
            return super.onTransact(i, parcel, parcel1, j);

        case 1: // '\001'
            parcel.enforceInterface("com.miui.whetstone.app.IWhetstoneApplicationThread");
            parcel = dumpMemInfo(parcel.readStringArray());
            parcel1.writeNoException();
            parcel.writeToParcel(parcel1, 0);
            return true;

        case 2: // '\002'
            parcel.enforceInterface("com.miui.whetstone.app.IWhetstoneApplicationThread");
            break;
        }
        boolean flag1 = longScreenshot(parcel.readInt());
        parcel1.writeNoException();
        i = ((flag) ? 1 : 0);
        if(flag1)
            i = 1;
        parcel1.writeByte((byte)i);
        return true;
    }

    private miui.util.LongScreenshotUtils.ContentPort mContentPort;
}
