// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.printservice;

import android.os.*;
import android.print.PrintJobInfo;
import android.print.PrinterId;
import java.util.List;

// Referenced classes of package android.printservice:
//            IPrintServiceClient

public interface IPrintService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPrintService
    {

        public static IPrintService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.printservice.IPrintService");
            if(iinterface != null && (iinterface instanceof IPrintService))
                return (IPrintService)iinterface;
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
                parcel1.writeString("android.printservice.IPrintService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.printservice.IPrintService");
                setClient(IPrintServiceClient.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.printservice.IPrintService");
                if(parcel.readInt() != 0)
                    parcel = (PrintJobInfo)PrintJobInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                requestCancelPrintJob(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.printservice.IPrintService");
                if(parcel.readInt() != 0)
                    parcel = (PrintJobInfo)PrintJobInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onPrintJobQueued(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.printservice.IPrintService");
                createPrinterDiscoverySession();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.printservice.IPrintService");
                startPrinterDiscovery(parcel.createTypedArrayList(PrinterId.CREATOR));
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.printservice.IPrintService");
                stopPrinterDiscovery();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.printservice.IPrintService");
                validatePrinters(parcel.createTypedArrayList(PrinterId.CREATOR));
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.printservice.IPrintService");
                if(parcel.readInt() != 0)
                    parcel = (PrinterId)PrinterId.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startPrinterStateTracking(parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.printservice.IPrintService");
                if(parcel.readInt() != 0)
                    parcel = (PrinterId)PrinterId.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                requestCustomPrinterIcon(parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.printservice.IPrintService");
                if(parcel.readInt() != 0)
                    parcel = (PrinterId)PrinterId.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                stopPrinterStateTracking(parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.printservice.IPrintService");
                destroyPrinterDiscoverySession();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.printservice.IPrintService";
        static final int TRANSACTION_createPrinterDiscoverySession = 4;
        static final int TRANSACTION_destroyPrinterDiscoverySession = 11;
        static final int TRANSACTION_onPrintJobQueued = 3;
        static final int TRANSACTION_requestCancelPrintJob = 2;
        static final int TRANSACTION_requestCustomPrinterIcon = 9;
        static final int TRANSACTION_setClient = 1;
        static final int TRANSACTION_startPrinterDiscovery = 5;
        static final int TRANSACTION_startPrinterStateTracking = 8;
        static final int TRANSACTION_stopPrinterDiscovery = 6;
        static final int TRANSACTION_stopPrinterStateTracking = 10;
        static final int TRANSACTION_validatePrinters = 7;

        public Stub()
        {
            attachInterface(this, "android.printservice.IPrintService");
        }
    }

    private static class Stub.Proxy
        implements IPrintService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void createPrinterDiscoverySession()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintService");
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void destroyPrinterDiscoverySession()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintService");
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.printservice.IPrintService";
        }

        public void onPrintJobQueued(PrintJobInfo printjobinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintService");
            if(printjobinfo == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            printjobinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            printjobinfo;
            parcel.recycle();
            throw printjobinfo;
        }

        public void requestCancelPrintJob(PrintJobInfo printjobinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintService");
            if(printjobinfo == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            printjobinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            printjobinfo;
            parcel.recycle();
            throw printjobinfo;
        }

        public void requestCustomPrinterIcon(PrinterId printerid)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintService");
            if(printerid == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            printerid.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            printerid;
            parcel.recycle();
            throw printerid;
        }

        public void setClient(IPrintServiceClient iprintserviceclient)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintService");
            if(iprintserviceclient == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = iprintserviceclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            iprintserviceclient;
            parcel.recycle();
            throw iprintserviceclient;
        }

        public void startPrinterDiscovery(List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintService");
            parcel.writeTypedList(list);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public void startPrinterStateTracking(PrinterId printerid)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintService");
            if(printerid == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            printerid.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            printerid;
            parcel.recycle();
            throw printerid;
        }

        public void stopPrinterDiscovery()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintService");
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void stopPrinterStateTracking(PrinterId printerid)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintService");
            if(printerid == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            printerid.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            printerid;
            parcel.recycle();
            throw printerid;
        }

        public void validatePrinters(List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintService");
            parcel.writeTypedList(list);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void createPrinterDiscoverySession()
        throws RemoteException;

    public abstract void destroyPrinterDiscoverySession()
        throws RemoteException;

    public abstract void onPrintJobQueued(PrintJobInfo printjobinfo)
        throws RemoteException;

    public abstract void requestCancelPrintJob(PrintJobInfo printjobinfo)
        throws RemoteException;

    public abstract void requestCustomPrinterIcon(PrinterId printerid)
        throws RemoteException;

    public abstract void setClient(IPrintServiceClient iprintserviceclient)
        throws RemoteException;

    public abstract void startPrinterDiscovery(List list)
        throws RemoteException;

    public abstract void startPrinterStateTracking(PrinterId printerid)
        throws RemoteException;

    public abstract void stopPrinterDiscovery()
        throws RemoteException;

    public abstract void stopPrinterStateTracking(PrinterId printerid)
        throws RemoteException;

    public abstract void validatePrinters(List list)
        throws RemoteException;
}
