// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security;

import android.os.*;

public interface IKeyChainAliasCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IKeyChainAliasCallback
    {

        public static IKeyChainAliasCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.security.IKeyChainAliasCallback");
            if(iinterface != null && (iinterface instanceof IKeyChainAliasCallback))
                return (IKeyChainAliasCallback)iinterface;
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
                parcel1.writeString("android.security.IKeyChainAliasCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.security.IKeyChainAliasCallback");
                alias(parcel.readString());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.security.IKeyChainAliasCallback";
        static final int TRANSACTION_alias = 1;

        public Stub()
        {
            attachInterface(this, "android.security.IKeyChainAliasCallback");
        }
    }

    private static class Stub.Proxy
        implements IKeyChainAliasCallback
    {

        public void alias(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.IKeyChainAliasCallback");
            parcel.writeString(s);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.security.IKeyChainAliasCallback";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void alias(String s)
        throws RemoteException;
}
