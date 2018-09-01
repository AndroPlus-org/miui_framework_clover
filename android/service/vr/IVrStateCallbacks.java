// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.vr;

import android.os.*;

public interface IVrStateCallbacks
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IVrStateCallbacks
    {

        public static IVrStateCallbacks asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.vr.IVrStateCallbacks");
            if(iinterface != null && (iinterface instanceof IVrStateCallbacks))
                return (IVrStateCallbacks)iinterface;
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
                parcel1.writeString("android.service.vr.IVrStateCallbacks");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.vr.IVrStateCallbacks");
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            onVrStateChanged(flag);
            return true;
        }

        private static final String DESCRIPTOR = "android.service.vr.IVrStateCallbacks";
        static final int TRANSACTION_onVrStateChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.service.vr.IVrStateCallbacks");
        }
    }

    private static class Stub.Proxy
        implements IVrStateCallbacks
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.vr.IVrStateCallbacks";
        }

        public void onVrStateChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.vr.IVrStateCallbacks");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
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


    public abstract void onVrStateChanged(boolean flag)
        throws RemoteException;
}
