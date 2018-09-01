// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.os.*;

// Referenced classes of package com.miui.whetstone:
//            AppInfo

public interface IAppObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAppObserver
    {

        public static IAppObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.miui.whetstone.IAppObserver");
            if(iinterface != null && (iinterface instanceof IAppObserver))
                return (IAppObserver)iinterface;
            else
                return new Proxy(ibinder);
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.miui.whetstone.IAppObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.miui.whetstone.IAppObserver");
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                parcel1 = (AppInfo)AppInfo.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            onScreenAppChanged(parcel1, flag);
            return true;
        }

        private static final String DESCRIPTOR = "com.miui.whetstone.IAppObserver";
        static final int TRANSACTION_onScreenAppChanged = 1;

        public Stub()
        {
            attachInterface(this, "com.miui.whetstone.IAppObserver");
        }
    }

    private static class Stub.Proxy
        implements IAppObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.miui.whetstone.IAppObserver";
        }

        public void onScreenAppChanged(AppInfo appinfo, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IAppObserver");
            if(appinfo == null)
                break MISSING_BLOCK_LABEL_62;
            parcel.writeInt(1);
            appinfo.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            appinfo;
            parcel.recycle();
            throw appinfo;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onScreenAppChanged(AppInfo appinfo, boolean flag)
        throws RemoteException;
}
