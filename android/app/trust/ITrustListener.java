// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.trust;

import android.os.*;

public interface ITrustListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITrustListener
    {

        public static ITrustListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.trust.ITrustListener");
            if(iinterface != null && (iinterface instanceof ITrustListener))
                return (ITrustListener)iinterface;
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
                parcel1.writeString("android.app.trust.ITrustListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.trust.ITrustListener");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onTrustChanged(flag, parcel.readInt(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.trust.ITrustListener");
                break;
            }
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            onTrustManagedChanged(flag1, parcel.readInt());
            return true;
        }

        private static final String DESCRIPTOR = "android.app.trust.ITrustListener";
        static final int TRANSACTION_onTrustChanged = 1;
        static final int TRANSACTION_onTrustManagedChanged = 2;

        public Stub()
        {
            attachInterface(this, "android.app.trust.ITrustListener");
        }
    }

    private static class Stub.Proxy
        implements ITrustListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.trust.ITrustListener";
        }

        public void onTrustChanged(boolean flag, int i, int j)
            throws RemoteException
        {
            int k;
            Parcel parcel;
            k = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.trust.ITrustListener");
            if(!flag)
                k = 0;
            parcel.writeInt(k);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onTrustManagedChanged(boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            j = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.trust.ITrustListener");
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
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


    public abstract void onTrustChanged(boolean flag, int i, int j)
        throws RemoteException;

    public abstract void onTrustManagedChanged(boolean flag, int i)
        throws RemoteException;
}
