// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.content.ComponentName;
import android.os.*;

// Referenced classes of package android.print:
//            PrintJobInfo

public interface IPrintSpoolerClient
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPrintSpoolerClient
    {

        public static IPrintSpoolerClient asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.print.IPrintSpoolerClient");
            if(iinterface != null && (iinterface instanceof IPrintSpoolerClient))
                return (IPrintSpoolerClient)iinterface;
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
                parcel1.writeString("android.print.IPrintSpoolerClient");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.print.IPrintSpoolerClient");
                if(parcel.readInt() != 0)
                    parcel = (PrintJobInfo)PrintJobInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onPrintJobQueued(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.print.IPrintSpoolerClient");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onAllPrintJobsForServiceHandled(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.print.IPrintSpoolerClient");
                onAllPrintJobsHandled();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.print.IPrintSpoolerClient");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (PrintJobInfo)PrintJobInfo.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onPrintJobStateChanged(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.print.IPrintSpoolerClient";
        static final int TRANSACTION_onAllPrintJobsForServiceHandled = 2;
        static final int TRANSACTION_onAllPrintJobsHandled = 3;
        static final int TRANSACTION_onPrintJobQueued = 1;
        static final int TRANSACTION_onPrintJobStateChanged = 4;

        public Stub()
        {
            attachInterface(this, "android.print.IPrintSpoolerClient");
        }
    }

    private static class Stub.Proxy
        implements IPrintSpoolerClient
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.print.IPrintSpoolerClient";
        }

        public void onAllPrintJobsForServiceHandled(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpoolerClient");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel.recycle();
            throw componentname;
        }

        public void onAllPrintJobsHandled()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpoolerClient");
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onPrintJobQueued(PrintJobInfo printjobinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpoolerClient");
            if(printjobinfo == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            printjobinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            printjobinfo;
            parcel.recycle();
            throw printjobinfo;
        }

        public void onPrintJobStateChanged(PrintJobInfo printjobinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpoolerClient");
            if(printjobinfo == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            printjobinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            printjobinfo;
            parcel.recycle();
            throw printjobinfo;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onAllPrintJobsForServiceHandled(ComponentName componentname)
        throws RemoteException;

    public abstract void onAllPrintJobsHandled()
        throws RemoteException;

    public abstract void onPrintJobQueued(PrintJobInfo printjobinfo)
        throws RemoteException;

    public abstract void onPrintJobStateChanged(PrintJobInfo printjobinfo)
        throws RemoteException;
}
