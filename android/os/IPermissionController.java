// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, Binder, IBinder, 
//            Parcel

public interface IPermissionController
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPermissionController
    {

        public static IPermissionController asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IPermissionController");
            if(iinterface != null && (iinterface instanceof IPermissionController))
                return (IPermissionController)iinterface;
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
            boolean flag3;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.os.IPermissionController");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IPermissionController");
                boolean flag2 = checkPermission(parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                i = ((flag1) ? 1 : 0);
                if(flag2)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.os.IPermissionController");
                parcel = getPackagesForUid(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.os.IPermissionController");
                flag3 = isRuntimePermission(parcel.readString());
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                break;
            }
            if(flag3)
                i = 1;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.os.IPermissionController";
        static final int TRANSACTION_checkPermission = 1;
        static final int TRANSACTION_getPackagesForUid = 2;
        static final int TRANSACTION_isRuntimePermission = 3;

        public Stub()
        {
            attachInterface(this, "android.os.IPermissionController");
        }
    }

    private static class Stub.Proxy
        implements IPermissionController
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean checkPermission(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPermissionController");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
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

        public String getInterfaceDescriptor()
        {
            return "android.os.IPermissionController";
        }

        public String[] getPackagesForUid(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.os.IPermissionController");
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isRuntimePermission(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.IPermissionController");
            parcel.writeString(s);
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


    public abstract boolean checkPermission(String s, int i, int j)
        throws RemoteException;

    public abstract String[] getPackagesForUid(int i)
        throws RemoteException;

    public abstract boolean isRuntimePermission(String s)
        throws RemoteException;
}
