// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.*;

public interface IParcelFileDescriptorFactory
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IParcelFileDescriptorFactory
    {

        public static IParcelFileDescriptorFactory asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.os.IParcelFileDescriptorFactory");
            if(iinterface != null && (iinterface instanceof IParcelFileDescriptorFactory))
                return (IParcelFileDescriptorFactory)iinterface;
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
                parcel1.writeString("com.android.internal.os.IParcelFileDescriptorFactory");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.os.IParcelFileDescriptorFactory");
                parcel = open(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                break;
            }
            if(parcel != null)
            {
                parcel1.writeInt(1);
                parcel.writeToParcel(parcel1, 1);
            } else
            {
                parcel1.writeInt(0);
            }
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.os.IParcelFileDescriptorFactory";
        static final int TRANSACTION_open = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.os.IParcelFileDescriptorFactory");
        }
    }

    private static class Stub.Proxy
        implements IParcelFileDescriptorFactory
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.os.IParcelFileDescriptorFactory";
        }

        public ParcelFileDescriptor open(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.os.IParcelFileDescriptorFactory");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
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


    public abstract ParcelFileDescriptor open(String s, int i)
        throws RemoteException;
}
