// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware;

import android.os.*;

public interface IConsumerIrService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IConsumerIrService
    {

        public static IConsumerIrService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.IConsumerIrService");
            if(iinterface != null && (iinterface instanceof IConsumerIrService))
                return (IConsumerIrService)iinterface;
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
                parcel1.writeString("android.hardware.IConsumerIrService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.IConsumerIrService");
                boolean flag = hasIrEmitter();
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.IConsumerIrService");
                transmit(parcel.readString(), parcel.readInt(), parcel.createIntArray());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.hardware.IConsumerIrService");
                parcel = getCarrierFrequencies();
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.hardware.IConsumerIrService";
        static final int TRANSACTION_getCarrierFrequencies = 3;
        static final int TRANSACTION_hasIrEmitter = 1;
        static final int TRANSACTION_transmit = 2;

        public Stub()
        {
            attachInterface(this, "android.hardware.IConsumerIrService");
        }
    }

    private static class Stub.Proxy
        implements IConsumerIrService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public int[] getCarrierFrequencies()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int ai[];
            parcel.writeInterfaceToken("android.hardware.IConsumerIrService");
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            ai = parcel1.createIntArray();
            parcel1.recycle();
            parcel.recycle();
            return ai;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.IConsumerIrService";
        }

        public boolean hasIrEmitter()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.hardware.IConsumerIrService");
            mRemote.transact(1, parcel, parcel1, 0);
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

        public void transmit(String s, int i, int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.IConsumerIrService");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeIntArray(ai);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract int[] getCarrierFrequencies()
        throws RemoteException;

    public abstract boolean hasIrEmitter()
        throws RemoteException;

    public abstract void transmit(String s, int i, int ai[])
        throws RemoteException;
}
