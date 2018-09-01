// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.trust;

import android.os.*;

public interface IStrongAuthTracker
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IStrongAuthTracker
    {

        public static IStrongAuthTracker asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.trust.IStrongAuthTracker");
            if(iinterface != null && (iinterface instanceof IStrongAuthTracker))
                return (IStrongAuthTracker)iinterface;
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
                parcel1.writeString("android.app.trust.IStrongAuthTracker");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.trust.IStrongAuthTracker");
                onStrongAuthRequiredChanged(parcel.readInt(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.trust.IStrongAuthTracker";
        static final int TRANSACTION_onStrongAuthRequiredChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.app.trust.IStrongAuthTracker");
        }
    }

    private static class Stub.Proxy
        implements IStrongAuthTracker
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.trust.IStrongAuthTracker";
        }

        public void onStrongAuthRequiredChanged(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.trust.IStrongAuthTracker");
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


    public abstract void onStrongAuthRequiredChanged(int i, int j)
        throws RemoteException;
}
