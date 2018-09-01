// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, Binder, IBinder, 
//            Parcel

public interface IIncidentReportStatusListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IIncidentReportStatusListener
    {

        public static IIncidentReportStatusListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IIncidentReportStatusListener");
            if(iinterface != null && (iinterface instanceof IIncidentReportStatusListener))
                return (IIncidentReportStatusListener)iinterface;
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
                parcel1.writeString("android.os.IIncidentReportStatusListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IIncidentReportStatusListener");
                onReportStarted();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.os.IIncidentReportStatusListener");
                onReportSectionStatus(parcel.readInt(), parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.os.IIncidentReportStatusListener");
                onReportFinished();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.os.IIncidentReportStatusListener");
                onReportFailed();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.os.IIncidentReportStatusListener";
        static final int TRANSACTION_onReportFailed = 4;
        static final int TRANSACTION_onReportFinished = 3;
        static final int TRANSACTION_onReportSectionStatus = 2;
        static final int TRANSACTION_onReportStarted = 1;

        public Stub()
        {
            attachInterface(this, "android.os.IIncidentReportStatusListener");
        }
    }

    private static class Stub.Proxy
        implements IIncidentReportStatusListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.IIncidentReportStatusListener";
        }

        public void onReportFailed()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IIncidentReportStatusListener");
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onReportFinished()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IIncidentReportStatusListener");
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onReportSectionStatus(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IIncidentReportStatusListener");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onReportStarted()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IIncidentReportStatusListener");
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


    public abstract void onReportFailed()
        throws RemoteException;

    public abstract void onReportFinished()
        throws RemoteException;

    public abstract void onReportSectionStatus(int i, int j)
        throws RemoteException;

    public abstract void onReportStarted()
        throws RemoteException;

    public static final int STATUS_FINISHED = 2;
    public static final int STATUS_STARTING = 1;
}
