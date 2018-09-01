// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, IUpdateEngineCallback, Binder, 
//            IBinder, Parcel

public interface IUpdateEngine
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IUpdateEngine
    {

        public static IUpdateEngine asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IUpdateEngine");
            if(iinterface != null && (iinterface instanceof IUpdateEngine))
                return (IUpdateEngine)iinterface;
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
            boolean flag = false;
            boolean flag1 = false;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.os.IUpdateEngine");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IUpdateEngine");
                applyPayload(parcel.readString(), parcel.readLong(), parcel.readLong(), parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.os.IUpdateEngine");
                boolean flag2 = bind(IUpdateEngineCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                i = ((flag1) ? 1 : 0);
                if(flag2)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.os.IUpdateEngine");
                boolean flag3 = unbind(IUpdateEngineCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag3)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.os.IUpdateEngine");
                suspend();
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.os.IUpdateEngine");
                resume();
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.os.IUpdateEngine");
                cancel();
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.os.IUpdateEngine");
                resetStatus();
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.os.IUpdateEngine";
        static final int TRANSACTION_applyPayload = 1;
        static final int TRANSACTION_bind = 2;
        static final int TRANSACTION_cancel = 6;
        static final int TRANSACTION_resetStatus = 7;
        static final int TRANSACTION_resume = 5;
        static final int TRANSACTION_suspend = 4;
        static final int TRANSACTION_unbind = 3;

        public Stub()
        {
            attachInterface(this, "android.os.IUpdateEngine");
        }
    }

    private static class Stub.Proxy
        implements IUpdateEngine
    {

        public void applyPayload(String s, long l, long l1, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IUpdateEngine");
            parcel.writeString(s);
            parcel.writeLong(l);
            parcel.writeLong(l1);
            parcel.writeStringArray(as);
            mRemote.transact(1, parcel, parcel1, 0);
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

        public boolean bind(IUpdateEngineCallback iupdateenginecallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IUpdateEngine");
            if(iupdateenginecallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iupdateenginecallback.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
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
            iupdateenginecallback;
            parcel1.recycle();
            parcel.recycle();
            throw iupdateenginecallback;
        }

        public void cancel()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IUpdateEngine");
            mRemote.transact(6, parcel, parcel1, 0);
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
            return "android.os.IUpdateEngine";
        }

        public void resetStatus()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IUpdateEngine");
            mRemote.transact(7, parcel, parcel1, 0);
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

        public void resume()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IUpdateEngine");
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

        public void suspend()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IUpdateEngine");
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

        public boolean unbind(IUpdateEngineCallback iupdateenginecallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IUpdateEngine");
            if(iupdateenginecallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iupdateenginecallback.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
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
            iupdateenginecallback;
            parcel1.recycle();
            parcel.recycle();
            throw iupdateenginecallback;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void applyPayload(String s, long l, long l1, String as[])
        throws RemoteException;

    public abstract boolean bind(IUpdateEngineCallback iupdateenginecallback)
        throws RemoteException;

    public abstract void cancel()
        throws RemoteException;

    public abstract void resetStatus()
        throws RemoteException;

    public abstract void resume()
        throws RemoteException;

    public abstract void suspend()
        throws RemoteException;

    public abstract boolean unbind(IUpdateEngineCallback iupdateenginecallback)
        throws RemoteException;
}
