// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.usb;

import android.os.*;

public interface IMiuiUsbManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMiuiUsbManager
    {

        public static IMiuiUsbManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.usb.IMiuiUsbManager");
            if(iinterface != null && (iinterface instanceof IMiuiUsbManager))
                return (IMiuiUsbManager)iinterface;
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
                parcel1.writeString("miui.usb.IMiuiUsbManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.usb.IMiuiUsbManager");
                acceptMdbRestore();
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("miui.usb.IMiuiUsbManager");
                cancelMdbRestore();
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("miui.usb.IMiuiUsbManager");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                allowUsbDebugging(flag, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("miui.usb.IMiuiUsbManager");
                denyUsbDebugging();
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "miui.usb.IMiuiUsbManager";
        static final int TRANSACTION_acceptMdbRestore = 1;
        static final int TRANSACTION_allowUsbDebugging = 3;
        static final int TRANSACTION_cancelMdbRestore = 2;
        static final int TRANSACTION_denyUsbDebugging = 4;

        public Stub()
        {
            attachInterface(this, "miui.usb.IMiuiUsbManager");
        }
    }

    private static class Stub.Proxy
        implements IMiuiUsbManager
    {

        public void acceptMdbRestore()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.usb.IMiuiUsbManager");
            mRemote.transact(1, parcel, parcel1, 0);
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

        public void allowUsbDebugging(boolean flag, String s)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.usb.IMiuiUsbManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void cancelMdbRestore()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.usb.IMiuiUsbManager");
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

        public void denyUsbDebugging()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.usb.IMiuiUsbManager");
            mRemote.transact(4, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "miui.usb.IMiuiUsbManager";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void acceptMdbRestore()
        throws RemoteException;

    public abstract void allowUsbDebugging(boolean flag, String s)
        throws RemoteException;

    public abstract void cancelMdbRestore()
        throws RemoteException;

    public abstract void denyUsbDebugging()
        throws RemoteException;
}
