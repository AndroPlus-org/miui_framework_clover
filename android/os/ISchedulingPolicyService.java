// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, Binder, IBinder, 
//            Parcel

public interface ISchedulingPolicyService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ISchedulingPolicyService
    {

        public static ISchedulingPolicyService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.ISchedulingPolicyService");
            if(iinterface != null && (iinterface instanceof ISchedulingPolicyService))
                return (ISchedulingPolicyService)iinterface;
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
            int k;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.os.ISchedulingPolicyService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.ISchedulingPolicyService");
                k = parcel.readInt();
                j = parcel.readInt();
                i = parcel.readInt();
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            i = requestPriority(k, j, i, flag);
            parcel1.writeNoException();
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.os.ISchedulingPolicyService";
        static final int TRANSACTION_requestPriority = 1;

        public Stub()
        {
            attachInterface(this, "android.os.ISchedulingPolicyService");
        }
    }

    private static class Stub.Proxy
        implements ISchedulingPolicyService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.ISchedulingPolicyService";
        }

        public int requestPriority(int i, int j, int k, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.ISchedulingPolicyService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
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


    public abstract int requestPriority(int i, int j, int k, boolean flag)
        throws RemoteException;
}
