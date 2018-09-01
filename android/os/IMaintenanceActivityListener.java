// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, Binder, IBinder, 
//            Parcel

public interface IMaintenanceActivityListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMaintenanceActivityListener
    {

        public static IMaintenanceActivityListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IMaintenanceActivityListener");
            if(iinterface != null && (iinterface instanceof IMaintenanceActivityListener))
                return (IMaintenanceActivityListener)iinterface;
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
                parcel1.writeString("android.os.IMaintenanceActivityListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IMaintenanceActivityListener");
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            onMaintenanceActivityChanged(flag);
            return true;
        }

        private static final String DESCRIPTOR = "android.os.IMaintenanceActivityListener";
        static final int TRANSACTION_onMaintenanceActivityChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.os.IMaintenanceActivityListener");
        }
    }

    private static class Stub.Proxy
        implements IMaintenanceActivityListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.IMaintenanceActivityListener";
        }

        public void onMaintenanceActivityChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IMaintenanceActivityListener");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onMaintenanceActivityChanged(boolean flag)
        throws RemoteException;
}
