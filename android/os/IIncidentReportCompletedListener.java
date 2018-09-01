// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, Binder, IBinder, 
//            Parcel

public interface IIncidentReportCompletedListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IIncidentReportCompletedListener
    {

        public static IIncidentReportCompletedListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IIncidentReportCompletedListener");
            if(iinterface != null && (iinterface instanceof IIncidentReportCompletedListener))
                return (IIncidentReportCompletedListener)iinterface;
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
                parcel1.writeString("android.os.IIncidentReportCompletedListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IIncidentReportCompletedListener");
                onIncidentReport(parcel.readString());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.os.IIncidentReportCompletedListener";
        static final int TRANSACTION_onIncidentReport = 1;

        public Stub()
        {
            attachInterface(this, "android.os.IIncidentReportCompletedListener");
        }
    }

    private static class Stub.Proxy
        implements IIncidentReportCompletedListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.IIncidentReportCompletedListener";
        }

        public void onIncidentReport(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IIncidentReportCompletedListener");
            parcel.writeString(s);
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


    public abstract void onIncidentReport(String s)
        throws RemoteException;
}
