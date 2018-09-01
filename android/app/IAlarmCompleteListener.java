// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.*;

public interface IAlarmCompleteListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAlarmCompleteListener
    {

        public static IAlarmCompleteListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.IAlarmCompleteListener");
            if(iinterface != null && (iinterface instanceof IAlarmCompleteListener))
                return (IAlarmCompleteListener)iinterface;
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
                parcel1.writeString("android.app.IAlarmCompleteListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.IAlarmCompleteListener");
                alarmComplete(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.IAlarmCompleteListener";
        static final int TRANSACTION_alarmComplete = 1;

        public Stub()
        {
            attachInterface(this, "android.app.IAlarmCompleteListener");
        }
    }

    private static class Stub.Proxy
        implements IAlarmCompleteListener
    {

        public void alarmComplete(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IAlarmCompleteListener");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.IAlarmCompleteListener";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void alarmComplete(IBinder ibinder)
        throws RemoteException;
}
