// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.input;

import android.os.*;

public interface ITabletModeChangedListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITabletModeChangedListener
    {

        public static ITabletModeChangedListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.input.ITabletModeChangedListener");
            if(iinterface != null && (iinterface instanceof ITabletModeChangedListener))
                return (ITabletModeChangedListener)iinterface;
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
            long l;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.hardware.input.ITabletModeChangedListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.input.ITabletModeChangedListener");
                l = parcel.readLong();
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            onTabletModeChanged(l, flag);
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.input.ITabletModeChangedListener";
        static final int TRANSACTION_onTabletModeChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.hardware.input.ITabletModeChangedListener");
        }
    }

    private static class Stub.Proxy
        implements ITabletModeChangedListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.input.ITabletModeChangedListener";
        }

        public void onTabletModeChanged(long l, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.ITabletModeChangedListener");
            parcel.writeLong(l);
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


    public abstract void onTabletModeChanged(long l, boolean flag)
        throws RemoteException;
}
