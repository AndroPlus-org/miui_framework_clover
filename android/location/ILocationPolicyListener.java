// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.*;

public interface ILocationPolicyListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ILocationPolicyListener
    {

        public static ILocationPolicyListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.location.ILocationPolicyListener");
            if(iinterface != null && (iinterface instanceof ILocationPolicyListener))
                return (ILocationPolicyListener)iinterface;
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
                parcel1.writeString("android.location.ILocationPolicyListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.location.ILocationPolicyListener");
                onUidRulesChanged(parcel.readInt(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.location.ILocationPolicyListener");
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            onRestrictBackgroundChanged(flag);
            return true;
        }

        private static final String DESCRIPTOR = "android.location.ILocationPolicyListener";
        static final int TRANSACTION_onRestrictBackgroundChanged = 2;
        static final int TRANSACTION_onUidRulesChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.location.ILocationPolicyListener");
        }
    }

    private static class Stub.Proxy
        implements ILocationPolicyListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.location.ILocationPolicyListener";
        }

        public void onRestrictBackgroundChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationPolicyListener");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
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
            parcel.writeInterfaceToken("android.location.ILocationPolicyListener");
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


    public abstract void onRestrictBackgroundChanged(boolean flag)
        throws RemoteException;

    public abstract void onUidRulesChanged(int i, int j)
        throws RemoteException;
}
