// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.storage;

import android.os.*;

public interface IObbActionListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IObbActionListener
    {

        public static IObbActionListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.storage.IObbActionListener");
            if(iinterface != null && (iinterface instanceof IObbActionListener))
                return (IObbActionListener)iinterface;
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
                parcel1.writeString("android.os.storage.IObbActionListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.storage.IObbActionListener");
                onObbResult(parcel.readString(), parcel.readInt(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.os.storage.IObbActionListener";
        static final int TRANSACTION_onObbResult = 1;

        public Stub()
        {
            attachInterface(this, "android.os.storage.IObbActionListener");
        }
    }

    private static class Stub.Proxy
        implements IObbActionListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.storage.IObbActionListener";
        }

        public void onObbResult(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.storage.IObbActionListener");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onObbResult(String s, int i, int j)
        throws RemoteException;
}
