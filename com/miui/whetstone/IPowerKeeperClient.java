// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.os.*;

public interface IPowerKeeperClient
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPowerKeeperClient
    {

        public static IPowerKeeperClient asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.miui.whetstone.IPowerKeeperClient");
            if(iinterface != null && (iinterface instanceof IPowerKeeperClient))
                return (IPowerKeeperClient)iinterface;
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
                parcel1.writeString("com.miui.whetstone.IPowerKeeperClient");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.miui.whetstone.IPowerKeeperClient");
                notifyFrozenAppWakeUpByHighPriorityAlarm(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.miui.whetstone.IPowerKeeperClient";
        static final int TRANSACTION_notifyFrozenAppWakeUpByHighPriorityAlarm = 1;

        public Stub()
        {
            attachInterface(this, "com.miui.whetstone.IPowerKeeperClient");
        }
    }

    private static class Stub.Proxy
        implements IPowerKeeperClient
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.miui.whetstone.IPowerKeeperClient";
        }

        public void notifyFrozenAppWakeUpByHighPriorityAlarm(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IPowerKeeperClient");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void notifyFrozenAppWakeUpByHighPriorityAlarm(int i)
        throws RemoteException;
}
