// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.location;

import android.os.*;

// Referenced classes of package com.android.internal.location:
//            ProviderProperties, ProviderRequest

public interface ILocationProvider
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ILocationProvider
    {

        public static ILocationProvider asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.location.ILocationProvider");
            if(iinterface != null && (iinterface instanceof ILocationProvider))
                return (ILocationProvider)iinterface;
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
            String s;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.android.internal.location.ILocationProvider");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.location.ILocationProvider");
                enable();
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.location.ILocationProvider");
                disable();
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.location.ILocationProvider");
                ProviderRequest providerrequest;
                if(parcel.readInt() != 0)
                    providerrequest = (ProviderRequest)ProviderRequest.CREATOR.createFromParcel(parcel);
                else
                    providerrequest = null;
                if(parcel.readInt() != 0)
                    parcel = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setRequest(providerrequest, parcel);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.location.ILocationProvider");
                parcel = getProperties();
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

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.location.ILocationProvider");
                parcel = new Bundle();
                i = getStatus(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.location.ILocationProvider");
                long l = getStatusUpdateTime();
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.location.ILocationProvider");
                s = parcel.readString();
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            flag = sendExtraCommand(s, parcel);
            parcel1.writeNoException();
            if(flag)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
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

        private static final String DESCRIPTOR = "com.android.internal.location.ILocationProvider";
        static final int TRANSACTION_disable = 2;
        static final int TRANSACTION_enable = 1;
        static final int TRANSACTION_getProperties = 4;
        static final int TRANSACTION_getStatus = 5;
        static final int TRANSACTION_getStatusUpdateTime = 6;
        static final int TRANSACTION_sendExtraCommand = 7;
        static final int TRANSACTION_setRequest = 3;

        public Stub()
        {
            attachInterface(this, "com.android.internal.location.ILocationProvider");
        }
    }

    private static class Stub.Proxy
        implements ILocationProvider
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void disable()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.location.ILocationProvider");
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

        public void enable()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.location.ILocationProvider");
            mRemote.transact(1, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.location.ILocationProvider";
        }

        public ProviderProperties getProperties()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.location.ILocationProvider");
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ProviderProperties providerproperties = (ProviderProperties)ProviderProperties.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return providerproperties;
_L2:
            providerproperties = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getStatus(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.location.ILocationProvider");
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(parcel1.readInt() != 0)
                bundle.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return i;
            bundle;
            parcel1.recycle();
            parcel.recycle();
            throw bundle;
        }

        public long getStatusUpdateTime()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("com.android.internal.location.ILocationProvider");
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean sendExtraCommand(String s, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.location.ILocationProvider");
            parcel.writeString(s);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_93;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            boolean flag;
            if(parcel1.readInt() != 0)
                flag = true;
            else
                flag = false;
            if(parcel1.readInt() != 0)
                bundle.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setRequest(ProviderRequest providerrequest, WorkSource worksource)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.location.ILocationProvider");
            if(providerrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            providerrequest.writeToParcel(parcel, 0);
_L3:
            if(worksource == null)
                break MISSING_BLOCK_LABEL_95;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            providerrequest;
            parcel1.recycle();
            parcel.recycle();
            throw providerrequest;
            parcel.writeInt(0);
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void disable()
        throws RemoteException;

    public abstract void enable()
        throws RemoteException;

    public abstract ProviderProperties getProperties()
        throws RemoteException;

    public abstract int getStatus(Bundle bundle)
        throws RemoteException;

    public abstract long getStatusUpdateTime()
        throws RemoteException;

    public abstract boolean sendExtraCommand(String s, Bundle bundle)
        throws RemoteException;

    public abstract void setRequest(ProviderRequest providerrequest, WorkSource worksource)
        throws RemoteException;
}
