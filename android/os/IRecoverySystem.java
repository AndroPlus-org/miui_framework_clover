// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, IRecoverySystemProgressListener, Binder, 
//            IBinder, Parcel

public interface IRecoverySystem
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRecoverySystem
    {

        public static IRecoverySystem asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IRecoverySystem");
            if(iinterface != null && (iinterface instanceof IRecoverySystem))
                return (IRecoverySystem)iinterface;
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
            boolean flag2 = false;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.os.IRecoverySystem");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IRecoverySystem");
                boolean flag3 = uncrypt(parcel.readString(), IRecoverySystemProgressListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                i = ((flag2) ? 1 : 0);
                if(flag3)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.os.IRecoverySystem");
                boolean flag4 = setupBcb(parcel.readString());
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag4)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.os.IRecoverySystem");
                boolean flag5 = clearBcb();
                parcel1.writeNoException();
                i = ((flag1) ? 1 : 0);
                if(flag5)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.os.IRecoverySystem");
                rebootRecoveryWithCommand(parcel.readString());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.os.IRecoverySystem";
        static final int TRANSACTION_clearBcb = 3;
        static final int TRANSACTION_rebootRecoveryWithCommand = 4;
        static final int TRANSACTION_setupBcb = 2;
        static final int TRANSACTION_uncrypt = 1;

        public Stub()
        {
            attachInterface(this, "android.os.IRecoverySystem");
        }
    }

    private static class Stub.Proxy
        implements IRecoverySystem
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean clearBcb()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.IRecoverySystem");
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

        public String getInterfaceDescriptor()
        {
            return "android.os.IRecoverySystem";
        }

        public void rebootRecoveryWithCommand(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IRecoverySystem");
            parcel.writeString(s);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean setupBcb(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.IRecoverySystem");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean uncrypt(String s, IRecoverySystemProgressListener irecoverysystemprogresslistener)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IRecoverySystem");
            parcel.writeString(s);
            s = obj;
            if(irecoverysystemprogresslistener == null)
                break MISSING_BLOCK_LABEL_38;
            s = irecoverysystemprogresslistener.asBinder();
            int i;
            parcel.writeStrongBinder(s);
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


    public abstract boolean clearBcb()
        throws RemoteException;

    public abstract void rebootRecoveryWithCommand(String s)
        throws RemoteException;

    public abstract boolean setupBcb(String s)
        throws RemoteException;

    public abstract boolean uncrypt(String s, IRecoverySystemProgressListener irecoverysystemprogresslistener)
        throws RemoteException;
}
