// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.os.*;

public interface ICarrierConfigLoader
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICarrierConfigLoader
    {

        public static ICarrierConfigLoader asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.telephony.ICarrierConfigLoader");
            if(iinterface != null && (iinterface instanceof ICarrierConfigLoader))
                return (ICarrierConfigLoader)iinterface;
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
                parcel1.writeString("com.android.internal.telephony.ICarrierConfigLoader");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.telephony.ICarrierConfigLoader");
                parcel = getConfigForSubId(parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.telephony.ICarrierConfigLoader");
                notifyConfigChangedForSubId(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.telephony.ICarrierConfigLoader");
                updateConfigForPhoneId(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.telephony.ICarrierConfigLoader");
                parcel = getDefaultCarrierServicePackageName();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.telephony.ICarrierConfigLoader";
        static final int TRANSACTION_getConfigForSubId = 1;
        static final int TRANSACTION_getDefaultCarrierServicePackageName = 4;
        static final int TRANSACTION_notifyConfigChangedForSubId = 2;
        static final int TRANSACTION_updateConfigForPhoneId = 3;

        public Stub()
        {
            attachInterface(this, "com.android.internal.telephony.ICarrierConfigLoader");
        }
    }

    private static class Stub.Proxy
        implements ICarrierConfigLoader
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public PersistableBundle getConfigForSubId(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ICarrierConfigLoader");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            PersistableBundle persistablebundle = (PersistableBundle)PersistableBundle.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return persistablebundle;
_L2:
            persistablebundle = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getDefaultCarrierServicePackageName()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.telephony.ICarrierConfigLoader");
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.telephony.ICarrierConfigLoader";
        }

        public void notifyConfigChangedForSubId(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ICarrierConfigLoader");
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void updateConfigForPhoneId(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ICarrierConfigLoader");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
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


    public abstract PersistableBundle getConfigForSubId(int i)
        throws RemoteException;

    public abstract String getDefaultCarrierServicePackageName()
        throws RemoteException;

    public abstract void notifyConfigChangedForSubId(int i)
        throws RemoteException;

    public abstract void updateConfigForPhoneId(int i, String s)
        throws RemoteException;
}
