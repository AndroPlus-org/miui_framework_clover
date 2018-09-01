// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import java.io.FileDescriptor;

// Referenced classes of package android.os:
//            IInterface, RemoteException, IncidentReportArgs, IIncidentReportStatusListener, 
//            Binder, IBinder, Parcel

public interface IIncidentManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IIncidentManager
    {

        public static IIncidentManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IIncidentManager");
            if(iinterface != null && (iinterface instanceof IIncidentManager))
                return (IIncidentManager)iinterface;
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
                parcel1.writeString("android.os.IIncidentManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IIncidentManager");
                if(parcel.readInt() != 0)
                    parcel = (IncidentReportArgs)IncidentReportArgs.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                reportIncident(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.os.IIncidentManager");
                if(parcel.readInt() != 0)
                    parcel1 = (IncidentReportArgs)IncidentReportArgs.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                reportIncidentToStream(parcel1, IIncidentReportStatusListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readRawFileDescriptor());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.os.IIncidentManager");
                systemRunning();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.os.IIncidentManager";
        static final int TRANSACTION_reportIncident = 1;
        static final int TRANSACTION_reportIncidentToStream = 2;
        static final int TRANSACTION_systemRunning = 3;

        public Stub()
        {
            attachInterface(this, "android.os.IIncidentManager");
        }
    }

    private static class Stub.Proxy
        implements IIncidentManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.IIncidentManager";
        }

        public void reportIncident(IncidentReportArgs incidentreportargs)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IIncidentManager");
            if(incidentreportargs == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            incidentreportargs.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            incidentreportargs;
            parcel.recycle();
            throw incidentreportargs;
        }

        public void reportIncidentToStream(IncidentReportArgs incidentreportargs, IIncidentReportStatusListener iincidentreportstatuslistener, FileDescriptor filedescriptor)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IIncidentManager");
            if(incidentreportargs == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            incidentreportargs.writeToParcel(parcel, 0);
_L4:
            incidentreportargs = obj;
            if(iincidentreportstatuslistener == null)
                break MISSING_BLOCK_LABEL_46;
            incidentreportargs = iincidentreportstatuslistener.asBinder();
            parcel.writeStrongBinder(incidentreportargs);
            parcel.writeRawFileDescriptor(filedescriptor);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            incidentreportargs;
            parcel.recycle();
            throw incidentreportargs;
        }

        public void systemRunning()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IIncidentManager");
            mRemote.transact(3, parcel, null, 1);
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


    public abstract void reportIncident(IncidentReportArgs incidentreportargs)
        throws RemoteException;

    public abstract void reportIncidentToStream(IncidentReportArgs incidentreportargs, IIncidentReportStatusListener iincidentreportstatuslistener, FileDescriptor filedescriptor)
        throws RemoteException;

    public abstract void systemRunning()
        throws RemoteException;
}
