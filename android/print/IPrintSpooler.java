// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.content.ComponentName;
import android.graphics.drawable.Icon;
import android.os.*;
import android.text.TextUtils;
import java.util.List;

// Referenced classes of package android.print:
//            IPrintSpoolerCallbacks, PrintJobInfo, PrinterId, PrintJobId, 
//            IPrintSpoolerClient

public interface IPrintSpooler
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPrintSpooler
    {

        public static IPrintSpooler asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.print.IPrintSpooler");
            if(iinterface != null && (iinterface instanceof IPrintSpooler))
                return (IPrintSpooler)iinterface;
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
                parcel1.writeString("android.print.IPrintSpooler");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.print.IPrintSpooler");
                removeObsoletePrintJobs();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.print.IPrintSpooler");
                IPrintSpoolerCallbacks iprintspoolercallbacks = IPrintSpoolerCallbacks.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                getPrintJobInfos(iprintspoolercallbacks, parcel1, parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.print.IPrintSpooler");
                if(parcel.readInt() != 0)
                    parcel1 = (PrintJobId)PrintJobId.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                getPrintJobInfo(parcel1, IPrintSpoolerCallbacks.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.print.IPrintSpooler");
                if(parcel.readInt() != 0)
                    parcel = (PrintJobInfo)PrintJobInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                createPrintJob(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.print.IPrintSpooler");
                if(parcel.readInt() != 0)
                    parcel1 = (PrintJobId)PrintJobId.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                setPrintJobState(parcel1, parcel.readInt(), parcel.readString(), IPrintSpoolerCallbacks.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.print.IPrintSpooler");
                if(parcel.readInt() != 0)
                    parcel1 = (PrintJobId)PrintJobId.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                setProgress(parcel1, parcel.readFloat());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.print.IPrintSpooler");
                if(parcel.readInt() != 0)
                    parcel1 = (PrintJobId)PrintJobId.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setStatus(parcel1, parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.print.IPrintSpooler");
                if(parcel.readInt() != 0)
                    parcel1 = (PrintJobId)PrintJobId.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setStatusRes(parcel1, i, parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.print.IPrintSpooler");
                Icon icon;
                if(parcel.readInt() != 0)
                    parcel1 = (PrinterId)PrinterId.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    icon = (Icon)Icon.CREATOR.createFromParcel(parcel);
                else
                    icon = null;
                onCustomPrinterIconLoaded(parcel1, icon, IPrintSpoolerCallbacks.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.print.IPrintSpooler");
                if(parcel.readInt() != 0)
                    parcel1 = (PrinterId)PrinterId.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                getCustomPrinterIcon(parcel1, IPrintSpoolerCallbacks.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.print.IPrintSpooler");
                clearCustomPrinterIconCache(IPrintSpoolerCallbacks.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.print.IPrintSpooler");
                if(parcel.readInt() != 0)
                    parcel1 = (PrintJobId)PrintJobId.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                setPrintJobTag(parcel1, parcel.readString(), IPrintSpoolerCallbacks.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.print.IPrintSpooler");
                if(parcel.readInt() != 0)
                    parcel1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (PrintJobId)PrintJobId.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                writePrintJobData(parcel1, parcel);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.print.IPrintSpooler");
                setClient(IPrintSpoolerClient.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.print.IPrintSpooler");
                boolean flag;
                if(parcel.readInt() != 0)
                    parcel1 = (PrintJobId)PrintJobId.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                setPrintJobCancelling(parcel1, flag);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.print.IPrintSpooler");
                pruneApprovedPrintServices(parcel.createTypedArrayList(ComponentName.CREATOR));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.print.IPrintSpooler";
        static final int TRANSACTION_clearCustomPrinterIconCache = 11;
        static final int TRANSACTION_createPrintJob = 4;
        static final int TRANSACTION_getCustomPrinterIcon = 10;
        static final int TRANSACTION_getPrintJobInfo = 3;
        static final int TRANSACTION_getPrintJobInfos = 2;
        static final int TRANSACTION_onCustomPrinterIconLoaded = 9;
        static final int TRANSACTION_pruneApprovedPrintServices = 16;
        static final int TRANSACTION_removeObsoletePrintJobs = 1;
        static final int TRANSACTION_setClient = 14;
        static final int TRANSACTION_setPrintJobCancelling = 15;
        static final int TRANSACTION_setPrintJobState = 5;
        static final int TRANSACTION_setPrintJobTag = 12;
        static final int TRANSACTION_setProgress = 6;
        static final int TRANSACTION_setStatus = 7;
        static final int TRANSACTION_setStatusRes = 8;
        static final int TRANSACTION_writePrintJobData = 13;

        public Stub()
        {
            attachInterface(this, "android.print.IPrintSpooler");
        }
    }

    private static class Stub.Proxy
        implements IPrintSpooler
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void clearCustomPrinterIconCache(IPrintSpoolerCallbacks iprintspoolercallbacks, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpooler");
            if(iprintspoolercallbacks == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = iprintspoolercallbacks.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            iprintspoolercallbacks;
            parcel.recycle();
            throw iprintspoolercallbacks;
        }

        public void createPrintJob(PrintJobInfo printjobinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpooler");
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

        public void getCustomPrinterIcon(PrinterId printerid, IPrintSpoolerCallbacks iprintspoolercallbacks, int i)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpooler");
            if(printerid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            printerid.writeToParcel(parcel, 0);
_L4:
            printerid = obj;
            if(iprintspoolercallbacks == null)
                break MISSING_BLOCK_LABEL_46;
            printerid = iprintspoolercallbacks.asBinder();
            parcel.writeStrongBinder(printerid);
            parcel.writeInt(i);
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            printerid;
            parcel.recycle();
            throw printerid;
        }

        public String getInterfaceDescriptor()
        {
            return "android.print.IPrintSpooler";
        }

        public void getPrintJobInfo(PrintJobId printjobid, IPrintSpoolerCallbacks iprintspoolercallbacks, int i, int j)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpooler");
            if(printjobid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            printjobid.writeToParcel(parcel, 0);
_L4:
            printjobid = obj;
            if(iprintspoolercallbacks == null)
                break MISSING_BLOCK_LABEL_46;
            printjobid = iprintspoolercallbacks.asBinder();
            parcel.writeStrongBinder(printjobid);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            printjobid;
            parcel.recycle();
            throw printjobid;
        }

        public void getPrintJobInfos(IPrintSpoolerCallbacks iprintspoolercallbacks, ComponentName componentname, int i, int j, int k)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpooler");
            if(iprintspoolercallbacks == null)
                break MISSING_BLOCK_LABEL_27;
            ibinder = iprintspoolercallbacks.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_92;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iprintspoolercallbacks;
            parcel.recycle();
            throw iprintspoolercallbacks;
        }

        public void onCustomPrinterIconLoaded(PrinterId printerid, Icon icon, IPrintSpoolerCallbacks iprintspoolercallbacks, int i)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpooler");
            if(printerid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            printerid.writeToParcel(parcel, 0);
_L3:
            if(icon == null)
                break MISSING_BLOCK_LABEL_115;
            parcel.writeInt(1);
            icon.writeToParcel(parcel, 0);
_L4:
            printerid = obj;
            if(iprintspoolercallbacks == null)
                break MISSING_BLOCK_LABEL_63;
            printerid = iprintspoolercallbacks.asBinder();
            parcel.writeStrongBinder(printerid);
            parcel.writeInt(i);
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            printerid;
            parcel.recycle();
            throw printerid;
            parcel.writeInt(0);
              goto _L4
        }

        public void pruneApprovedPrintServices(List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpooler");
            parcel.writeTypedList(list);
            mRemote.transact(16, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public void removeObsoletePrintJobs()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpooler");
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setClient(IPrintSpoolerClient iprintspoolerclient)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpooler");
            if(iprintspoolerclient == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = iprintspoolerclient.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
            iprintspoolerclient;
            parcel.recycle();
            throw iprintspoolerclient;
        }

        public void setPrintJobCancelling(PrintJobId printjobid, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpooler");
            if(printjobid == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            printjobid.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(15, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            printjobid;
            parcel.recycle();
            throw printjobid;
        }

        public void setPrintJobState(PrintJobId printjobid, int i, String s, IPrintSpoolerCallbacks iprintspoolercallbacks, int j)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpooler");
            if(printjobid == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            printjobid.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeString(s);
            printjobid = obj;
            if(iprintspoolercallbacks == null)
                break MISSING_BLOCK_LABEL_60;
            printjobid = iprintspoolercallbacks.asBinder();
            parcel.writeStrongBinder(printjobid);
            parcel.writeInt(j);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            printjobid;
            parcel.recycle();
            throw printjobid;
        }

        public void setPrintJobTag(PrintJobId printjobid, String s, IPrintSpoolerCallbacks iprintspoolercallbacks, int i)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpooler");
            if(printjobid == null)
                break MISSING_BLOCK_LABEL_87;
            parcel.writeInt(1);
            printjobid.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            printjobid = obj;
            if(iprintspoolercallbacks == null)
                break MISSING_BLOCK_LABEL_52;
            printjobid = iprintspoolercallbacks.asBinder();
            parcel.writeStrongBinder(printjobid);
            parcel.writeInt(i);
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            printjobid;
            parcel.recycle();
            throw printjobid;
        }

        public void setProgress(PrintJobId printjobid, float f)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpooler");
            if(printjobid == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            printjobid.writeToParcel(parcel, 0);
_L1:
            parcel.writeFloat(f);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            printjobid;
            parcel.recycle();
            throw printjobid;
        }

        public void setStatus(PrintJobId printjobid, CharSequence charsequence)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpooler");
            if(printjobid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            printjobid.writeToParcel(parcel, 0);
_L3:
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_75;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L4:
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            printjobid;
            parcel.recycle();
            throw printjobid;
            parcel.writeInt(0);
              goto _L4
        }

        public void setStatusRes(PrintJobId printjobid, int i, CharSequence charsequence)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpooler");
            if(printjobid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            printjobid.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_91;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L4:
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            printjobid;
            parcel.recycle();
            throw printjobid;
            parcel.writeInt(0);
              goto _L4
        }

        public void writePrintJobData(ParcelFileDescriptor parcelfiledescriptor, PrintJobId printjobid)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpooler");
            if(parcelfiledescriptor == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L3:
            if(printjobid == null)
                break MISSING_BLOCK_LABEL_75;
            parcel.writeInt(1);
            printjobid.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(13, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            parcelfiledescriptor;
            parcel.recycle();
            throw parcelfiledescriptor;
            parcel.writeInt(0);
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void clearCustomPrinterIconCache(IPrintSpoolerCallbacks iprintspoolercallbacks, int i)
        throws RemoteException;

    public abstract void createPrintJob(PrintJobInfo printjobinfo)
        throws RemoteException;

    public abstract void getCustomPrinterIcon(PrinterId printerid, IPrintSpoolerCallbacks iprintspoolercallbacks, int i)
        throws RemoteException;

    public abstract void getPrintJobInfo(PrintJobId printjobid, IPrintSpoolerCallbacks iprintspoolercallbacks, int i, int j)
        throws RemoteException;

    public abstract void getPrintJobInfos(IPrintSpoolerCallbacks iprintspoolercallbacks, ComponentName componentname, int i, int j, int k)
        throws RemoteException;

    public abstract void onCustomPrinterIconLoaded(PrinterId printerid, Icon icon, IPrintSpoolerCallbacks iprintspoolercallbacks, int i)
        throws RemoteException;

    public abstract void pruneApprovedPrintServices(List list)
        throws RemoteException;

    public abstract void removeObsoletePrintJobs()
        throws RemoteException;

    public abstract void setClient(IPrintSpoolerClient iprintspoolerclient)
        throws RemoteException;

    public abstract void setPrintJobCancelling(PrintJobId printjobid, boolean flag)
        throws RemoteException;

    public abstract void setPrintJobState(PrintJobId printjobid, int i, String s, IPrintSpoolerCallbacks iprintspoolercallbacks, int j)
        throws RemoteException;

    public abstract void setPrintJobTag(PrintJobId printjobid, String s, IPrintSpoolerCallbacks iprintspoolercallbacks, int i)
        throws RemoteException;

    public abstract void setProgress(PrintJobId printjobid, float f)
        throws RemoteException;

    public abstract void setStatus(PrintJobId printjobid, CharSequence charsequence)
        throws RemoteException;

    public abstract void setStatusRes(PrintJobId printjobid, int i, CharSequence charsequence)
        throws RemoteException;

    public abstract void writePrintJobData(ParcelFileDescriptor parcelfiledescriptor, PrintJobId printjobid)
        throws RemoteException;
}
