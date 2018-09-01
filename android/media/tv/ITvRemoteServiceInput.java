// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.os.*;

public interface ITvRemoteServiceInput
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITvRemoteServiceInput
    {

        public static ITvRemoteServiceInput asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.tv.ITvRemoteServiceInput");
            if(iinterface != null && (iinterface instanceof ITvRemoteServiceInput))
                return (ITvRemoteServiceInput)iinterface;
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
                parcel1.writeString("android.media.tv.ITvRemoteServiceInput");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.tv.ITvRemoteServiceInput");
                openInputBridge(parcel.readStrongBinder(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.tv.ITvRemoteServiceInput");
                closeInputBridge(parcel.readStrongBinder());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.tv.ITvRemoteServiceInput");
                clearInputBridge(parcel.readStrongBinder());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.tv.ITvRemoteServiceInput");
                sendTimestamp(parcel.readStrongBinder(), parcel.readLong());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.media.tv.ITvRemoteServiceInput");
                sendKeyDown(parcel.readStrongBinder(), parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.media.tv.ITvRemoteServiceInput");
                sendKeyUp(parcel.readStrongBinder(), parcel.readInt());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.media.tv.ITvRemoteServiceInput");
                sendPointerDown(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.media.tv.ITvRemoteServiceInput");
                sendPointerUp(parcel.readStrongBinder(), parcel.readInt());
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.media.tv.ITvRemoteServiceInput");
                sendPointerSync(parcel.readStrongBinder());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.tv.ITvRemoteServiceInput";
        static final int TRANSACTION_clearInputBridge = 3;
        static final int TRANSACTION_closeInputBridge = 2;
        static final int TRANSACTION_openInputBridge = 1;
        static final int TRANSACTION_sendKeyDown = 5;
        static final int TRANSACTION_sendKeyUp = 6;
        static final int TRANSACTION_sendPointerDown = 7;
        static final int TRANSACTION_sendPointerSync = 9;
        static final int TRANSACTION_sendPointerUp = 8;
        static final int TRANSACTION_sendTimestamp = 4;

        public Stub()
        {
            attachInterface(this, "android.media.tv.ITvRemoteServiceInput");
        }
    }

    private static class Stub.Proxy
        implements ITvRemoteServiceInput
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void clearInputBridge(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvRemoteServiceInput");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void closeInputBridge(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvRemoteServiceInput");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.tv.ITvRemoteServiceInput";
        }

        public void openInputBridge(IBinder ibinder, String s, int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvRemoteServiceInput");
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void sendKeyDown(IBinder ibinder, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvRemoteServiceInput");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void sendKeyUp(IBinder ibinder, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvRemoteServiceInput");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void sendPointerDown(IBinder ibinder, int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvRemoteServiceInput");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void sendPointerSync(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvRemoteServiceInput");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void sendPointerUp(IBinder ibinder, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvRemoteServiceInput");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void sendTimestamp(IBinder ibinder, long l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvRemoteServiceInput");
            parcel.writeStrongBinder(ibinder);
            parcel.writeLong(l);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void clearInputBridge(IBinder ibinder)
        throws RemoteException;

    public abstract void closeInputBridge(IBinder ibinder)
        throws RemoteException;

    public abstract void openInputBridge(IBinder ibinder, String s, int i, int j, int k)
        throws RemoteException;

    public abstract void sendKeyDown(IBinder ibinder, int i)
        throws RemoteException;

    public abstract void sendKeyUp(IBinder ibinder, int i)
        throws RemoteException;

    public abstract void sendPointerDown(IBinder ibinder, int i, int j, int k)
        throws RemoteException;

    public abstract void sendPointerSync(IBinder ibinder)
        throws RemoteException;

    public abstract void sendPointerUp(IBinder ibinder, int i)
        throws RemoteException;

    public abstract void sendTimestamp(IBinder ibinder, long l)
        throws RemoteException;
}
