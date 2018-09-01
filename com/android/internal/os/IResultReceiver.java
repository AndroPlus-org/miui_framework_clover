// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.*;

public interface IResultReceiver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IResultReceiver
    {

        public static IResultReceiver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.os.IResultReceiver");
            if(iinterface != null && (iinterface instanceof IResultReceiver))
                return (IResultReceiver)iinterface;
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
                parcel1.writeString("com.android.internal.os.IResultReceiver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.os.IResultReceiver");
                i = parcel.readInt();
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            send(i, parcel);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.os.IResultReceiver";
        static final int TRANSACTION_send = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.os.IResultReceiver");
        }
    }

    private static class Stub.Proxy
        implements IResultReceiver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.os.IResultReceiver";
        }

        public void send(int i, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.os.IResultReceiver");
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel.recycle();
            throw bundle;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void send(int i, Bundle bundle)
        throws RemoteException;
}
