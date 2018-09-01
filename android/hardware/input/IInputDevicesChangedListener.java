// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.input;

import android.os.*;

public interface IInputDevicesChangedListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IInputDevicesChangedListener
    {

        public static IInputDevicesChangedListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.input.IInputDevicesChangedListener");
            if(iinterface != null && (iinterface instanceof IInputDevicesChangedListener))
                return (IInputDevicesChangedListener)iinterface;
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
                parcel1.writeString("android.hardware.input.IInputDevicesChangedListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.input.IInputDevicesChangedListener");
                onInputDevicesChanged(parcel.createIntArray());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.hardware.input.IInputDevicesChangedListener";
        static final int TRANSACTION_onInputDevicesChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.hardware.input.IInputDevicesChangedListener");
        }
    }

    private static class Stub.Proxy
        implements IInputDevicesChangedListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.input.IInputDevicesChangedListener";
        }

        public void onInputDevicesChanged(int ai[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputDevicesChangedListener");
            parcel.writeIntArray(ai);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            ai;
            parcel.recycle();
            throw ai;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onInputDevicesChanged(int ai[])
        throws RemoteException;
}
