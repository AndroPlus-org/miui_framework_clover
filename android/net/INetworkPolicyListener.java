// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;

public interface INetworkPolicyListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements INetworkPolicyListener
    {

        public static INetworkPolicyListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.INetworkPolicyListener");
            if(iinterface != null && (iinterface instanceof INetworkPolicyListener))
                return (INetworkPolicyListener)iinterface;
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
                parcel1.writeString("android.net.INetworkPolicyListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.INetworkPolicyListener");
                onUidRulesChanged(parcel.readInt(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.net.INetworkPolicyListener");
                onMeteredIfacesChanged(parcel.createStringArray());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.net.INetworkPolicyListener");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onRestrictBackgroundChanged(flag);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.net.INetworkPolicyListener");
                onUidPoliciesChanged(parcel.readInt(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.net.INetworkPolicyListener";
        static final int TRANSACTION_onMeteredIfacesChanged = 2;
        static final int TRANSACTION_onRestrictBackgroundChanged = 3;
        static final int TRANSACTION_onUidPoliciesChanged = 4;
        static final int TRANSACTION_onUidRulesChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.net.INetworkPolicyListener");
        }
    }

    private static class Stub.Proxy
        implements INetworkPolicyListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.INetworkPolicyListener";
        }

        public void onMeteredIfacesChanged(String as[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyListener");
            parcel.writeStringArray(as);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            as;
            parcel.recycle();
            throw as;
        }

        public void onRestrictBackgroundChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyListener");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onUidPoliciesChanged(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyListener");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onUidRulesChanged(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyListener");
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onMeteredIfacesChanged(String as[])
        throws RemoteException;

    public abstract void onRestrictBackgroundChanged(boolean flag)
        throws RemoteException;

    public abstract void onUidPoliciesChanged(int i, int j)
        throws RemoteException;

    public abstract void onUidRulesChanged(int i, int j)
        throws RemoteException;
}
