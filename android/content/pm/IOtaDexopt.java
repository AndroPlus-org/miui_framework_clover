// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.*;

public interface IOtaDexopt
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IOtaDexopt
    {

        public static IOtaDexopt asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.pm.IOtaDexopt");
            if(iinterface != null && (iinterface instanceof IOtaDexopt))
                return (IOtaDexopt)iinterface;
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
                parcel1.writeString("android.content.pm.IOtaDexopt");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.pm.IOtaDexopt");
                prepare();
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.content.pm.IOtaDexopt");
                cleanup();
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.content.pm.IOtaDexopt");
                boolean flag = isDone();
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.content.pm.IOtaDexopt");
                float f = getProgress();
                parcel1.writeNoException();
                parcel1.writeFloat(f);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.content.pm.IOtaDexopt");
                dexoptNextPackage();
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.content.pm.IOtaDexopt");
                parcel = nextDexoptCommand();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.content.pm.IOtaDexopt";
        static final int TRANSACTION_cleanup = 2;
        static final int TRANSACTION_dexoptNextPackage = 5;
        static final int TRANSACTION_getProgress = 4;
        static final int TRANSACTION_isDone = 3;
        static final int TRANSACTION_nextDexoptCommand = 6;
        static final int TRANSACTION_prepare = 1;

        public Stub()
        {
            attachInterface(this, "android.content.pm.IOtaDexopt");
        }
    }

    private static class Stub.Proxy
        implements IOtaDexopt
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void cleanup()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IOtaDexopt");
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

        public void dexoptNextPackage()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IOtaDexopt");
            mRemote.transact(5, parcel, parcel1, 0);
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
            return "android.content.pm.IOtaDexopt";
        }

        public float getProgress()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            float f;
            parcel.writeInterfaceToken("android.content.pm.IOtaDexopt");
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            f = parcel1.readFloat();
            parcel1.recycle();
            parcel.recycle();
            return f;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isDone()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.content.pm.IOtaDexopt");
            mRemote.transact(3, parcel, parcel1, 0);
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

        public String nextDexoptCommand()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.content.pm.IOtaDexopt");
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void prepare()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IOtaDexopt");
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void cleanup()
        throws RemoteException;

    public abstract void dexoptNextPackage()
        throws RemoteException;

    public abstract float getProgress()
        throws RemoteException;

    public abstract boolean isDone()
        throws RemoteException;

    public abstract String nextDexoptCommand()
        throws RemoteException;

    public abstract void prepare()
        throws RemoteException;
}
