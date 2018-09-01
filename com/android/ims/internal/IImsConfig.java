// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims.internal;

import android.os.*;
import com.android.ims.ImsConfigListener;

public interface IImsConfig
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IImsConfig
    {

        public static IImsConfig asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.ims.internal.IImsConfig");
            if(iinterface != null && (iinterface instanceof IImsConfig))
                return (IImsConfig)iinterface;
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
                parcel1.writeString("com.android.ims.internal.IImsConfig");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.ims.internal.IImsConfig");
                i = getProvisionedValue(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.ims.internal.IImsConfig");
                parcel = getProvisionedStringValue(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.ims.internal.IImsConfig");
                i = setProvisionedValue(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.ims.internal.IImsConfig");
                i = setProvisionedStringValue(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.ims.internal.IImsConfig");
                getFeatureValue(parcel.readInt(), parcel.readInt(), com.android.ims.ImsConfigListener.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.ims.internal.IImsConfig");
                setFeatureValue(parcel.readInt(), parcel.readInt(), parcel.readInt(), com.android.ims.ImsConfigListener.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.ims.internal.IImsConfig");
                boolean flag = getVolteProvisioned();
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.ims.internal.IImsConfig");
                getVideoQuality(com.android.ims.ImsConfigListener.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.ims.internal.IImsConfig");
                setVideoQuality(parcel.readInt(), com.android.ims.ImsConfigListener.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.ims.internal.IImsConfig";
        static final int TRANSACTION_getFeatureValue = 5;
        static final int TRANSACTION_getProvisionedStringValue = 2;
        static final int TRANSACTION_getProvisionedValue = 1;
        static final int TRANSACTION_getVideoQuality = 8;
        static final int TRANSACTION_getVolteProvisioned = 7;
        static final int TRANSACTION_setFeatureValue = 6;
        static final int TRANSACTION_setProvisionedStringValue = 4;
        static final int TRANSACTION_setProvisionedValue = 3;
        static final int TRANSACTION_setVideoQuality = 9;

        public Stub()
        {
            attachInterface(this, "com.android.ims.internal.IImsConfig");
        }
    }

    private static class Stub.Proxy
        implements IImsConfig
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void getFeatureValue(int i, int j, ImsConfigListener imsconfiglistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsConfig");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(imsconfiglistener == null)
                break MISSING_BLOCK_LABEL_39;
            ibinder = imsconfiglistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            imsconfiglistener;
            parcel.recycle();
            throw imsconfiglistener;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.ims.internal.IImsConfig";
        }

        public String getProvisionedStringValue(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.ims.internal.IImsConfig");
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
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

        public int getProvisionedValue(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsConfig");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void getVideoQuality(ImsConfigListener imsconfiglistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsConfig");
            if(imsconfiglistener == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = imsconfiglistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            imsconfiglistener;
            parcel.recycle();
            throw imsconfiglistener;
        }

        public boolean getVolteProvisioned()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.ims.internal.IImsConfig");
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setFeatureValue(int i, int j, int k, ImsConfigListener imsconfiglistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsConfig");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            if(imsconfiglistener == null)
                break MISSING_BLOCK_LABEL_47;
            ibinder = imsconfiglistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            imsconfiglistener;
            parcel.recycle();
            throw imsconfiglistener;
        }

        public int setProvisionedStringValue(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsConfig");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int setProvisionedValue(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsConfig");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setVideoQuality(int i, ImsConfigListener imsconfiglistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.ims.internal.IImsConfig");
            parcel.writeInt(i);
            if(imsconfiglistener == null)
                break MISSING_BLOCK_LABEL_31;
            ibinder = imsconfiglistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            imsconfiglistener;
            parcel.recycle();
            throw imsconfiglistener;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void getFeatureValue(int i, int j, ImsConfigListener imsconfiglistener)
        throws RemoteException;

    public abstract String getProvisionedStringValue(int i)
        throws RemoteException;

    public abstract int getProvisionedValue(int i)
        throws RemoteException;

    public abstract void getVideoQuality(ImsConfigListener imsconfiglistener)
        throws RemoteException;

    public abstract boolean getVolteProvisioned()
        throws RemoteException;

    public abstract void setFeatureValue(int i, int j, int k, ImsConfigListener imsconfiglistener)
        throws RemoteException;

    public abstract int setProvisionedStringValue(int i, String s)
        throws RemoteException;

    public abstract int setProvisionedValue(int i, int j)
        throws RemoteException;

    public abstract void setVideoQuality(int i, ImsConfigListener imsconfiglistener)
        throws RemoteException;
}
