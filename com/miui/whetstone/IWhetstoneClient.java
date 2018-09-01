// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.os.*;

public interface IWhetstoneClient
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWhetstoneClient
    {

        public static IWhetstoneClient asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.miui.whetstone.IWhetstoneClient");
            if(iinterface != null && (iinterface instanceof IWhetstoneClient))
                return (IWhetstoneClient)iinterface;
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
                parcel1.writeString("com.miui.whetstone.IWhetstoneClient");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.miui.whetstone.IWhetstoneClient");
                setForegroundProcess(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.miui.whetstone.IWhetstoneClient";
        static final int TRANSACTION_setForegroundProcess = 1;

        public Stub()
        {
            attachInterface(this, "com.miui.whetstone.IWhetstoneClient");
        }
    }

    private static class Stub.Proxy
        implements IWhetstoneClient
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.miui.whetstone.IWhetstoneClient";
        }

        public void setForegroundProcess(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IWhetstoneClient");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 1);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void setForegroundProcess(int i, String s)
        throws RemoteException;
}
