// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accounts;

import android.os.*;

public interface IAccountManagerResponse
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAccountManagerResponse
    {

        public static IAccountManagerResponse asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.accounts.IAccountManagerResponse");
            if(iinterface != null && (iinterface instanceof IAccountManagerResponse))
                return (IAccountManagerResponse)iinterface;
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
                parcel1.writeString("android.accounts.IAccountManagerResponse");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.accounts.IAccountManagerResponse");
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onResult(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.accounts.IAccountManagerResponse");
                onError(parcel.readInt(), parcel.readString());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.accounts.IAccountManagerResponse";
        static final int TRANSACTION_onError = 2;
        static final int TRANSACTION_onResult = 1;

        public Stub()
        {
            attachInterface(this, "android.accounts.IAccountManagerResponse");
        }
    }

    private static class Stub.Proxy
        implements IAccountManagerResponse
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.accounts.IAccountManagerResponse";
        }

        public void onError(int i, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManagerResponse");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onResult(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.accounts.IAccountManagerResponse");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_44;
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


    public abstract void onError(int i, String s)
        throws RemoteException;

    public abstract void onResult(Bundle bundle)
        throws RemoteException;
}
