// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.hdmi;

import android.os.*;

public interface IHdmiVendorCommandListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IHdmiVendorCommandListener
    {

        public static IHdmiVendorCommandListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.hdmi.IHdmiVendorCommandListener");
            if(iinterface != null && (iinterface instanceof IHdmiVendorCommandListener))
                return (IHdmiVendorCommandListener)iinterface;
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
                parcel1.writeString("android.hardware.hdmi.IHdmiVendorCommandListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiVendorCommandListener");
                i = parcel.readInt();
                j = parcel.readInt();
                byte abyte0[] = parcel.createByteArray();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onReceived(i, j, abyte0, flag);
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiVendorCommandListener");
                break;
            }
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            onControlStateChanged(flag1, parcel.readInt());
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiVendorCommandListener";
        static final int TRANSACTION_onControlStateChanged = 2;
        static final int TRANSACTION_onReceived = 1;

        public Stub()
        {
            attachInterface(this, "android.hardware.hdmi.IHdmiVendorCommandListener");
        }
    }

    private static class Stub.Proxy
        implements IHdmiVendorCommandListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.hdmi.IHdmiVendorCommandListener";
        }

        public void onControlStateChanged(boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiVendorCommandListener");
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void onReceived(int i, int j, byte abyte0[], boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiVendorCommandListener");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeByteArray(abyte0);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onControlStateChanged(boolean flag, int i)
        throws RemoteException;

    public abstract void onReceived(int i, int j, byte abyte0[], boolean flag)
        throws RemoteException;
}
