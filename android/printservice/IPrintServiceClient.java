// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.printservice;

import android.content.pm.ParceledListSlice;
import android.graphics.drawable.Icon;
import android.os.*;
import android.print.*;
import android.text.TextUtils;
import java.util.List;

public interface IPrintServiceClient
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPrintServiceClient
    {

        public static IPrintServiceClient asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.printservice.IPrintServiceClient");
            if(iinterface != null && (iinterface instanceof IPrintServiceClient))
                return (IPrintServiceClient)iinterface;
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
                parcel1.writeString("android.printservice.IPrintServiceClient");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.printservice.IPrintServiceClient");
                parcel = getPrintJobInfos();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.printservice.IPrintServiceClient");
                if(parcel.readInt() != 0)
                    parcel = (PrintJobId)PrintJobId.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getPrintJobInfo(parcel);
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.printservice.IPrintServiceClient");
                PrintJobId printjobid;
                boolean flag;
                if(parcel.readInt() != 0)
                    printjobid = (PrintJobId)PrintJobId.CREATOR.createFromParcel(parcel);
                else
                    printjobid = null;
                flag = setPrintJobState(printjobid, parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.printservice.IPrintServiceClient");
                PrintJobId printjobid1;
                boolean flag1;
                if(parcel.readInt() != 0)
                    printjobid1 = (PrintJobId)PrintJobId.CREATOR.createFromParcel(parcel);
                else
                    printjobid1 = null;
                flag1 = setPrintJobTag(printjobid1, parcel.readString());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.printservice.IPrintServiceClient");
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

            case 6: // '\006'
                parcel.enforceInterface("android.printservice.IPrintServiceClient");
                PrintJobId printjobid2;
                if(parcel.readInt() != 0)
                    printjobid2 = (PrintJobId)PrintJobId.CREATOR.createFromParcel(parcel);
                else
                    printjobid2 = null;
                setProgress(printjobid2, parcel.readFloat());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.printservice.IPrintServiceClient");
                PrintJobId printjobid3;
                if(parcel.readInt() != 0)
                    printjobid3 = (PrintJobId)PrintJobId.CREATOR.createFromParcel(parcel);
                else
                    printjobid3 = null;
                if(parcel.readInt() != 0)
                    parcel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setStatus(printjobid3, parcel);
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.printservice.IPrintServiceClient");
                PrintJobId printjobid4;
                if(parcel.readInt() != 0)
                    printjobid4 = (PrintJobId)PrintJobId.CREATOR.createFromParcel(parcel);
                else
                    printjobid4 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setStatusRes(printjobid4, i, parcel);
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.printservice.IPrintServiceClient");
                if(parcel.readInt() != 0)
                    parcel = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onPrintersAdded(parcel);
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.printservice.IPrintServiceClient");
                if(parcel.readInt() != 0)
                    parcel = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onPrintersRemoved(parcel);
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.printservice.IPrintServiceClient");
                break;
            }
            PrinterId printerid;
            if(parcel.readInt() != 0)
                printerid = (PrinterId)PrinterId.CREATOR.createFromParcel(parcel);
            else
                printerid = null;
            if(parcel.readInt() != 0)
                parcel = (Icon)Icon.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onCustomPrinterIconLoaded(printerid, parcel);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.printservice.IPrintServiceClient";
        static final int TRANSACTION_getPrintJobInfo = 2;
        static final int TRANSACTION_getPrintJobInfos = 1;
        static final int TRANSACTION_onCustomPrinterIconLoaded = 11;
        static final int TRANSACTION_onPrintersAdded = 9;
        static final int TRANSACTION_onPrintersRemoved = 10;
        static final int TRANSACTION_setPrintJobState = 3;
        static final int TRANSACTION_setPrintJobTag = 4;
        static final int TRANSACTION_setProgress = 6;
        static final int TRANSACTION_setStatus = 7;
        static final int TRANSACTION_setStatusRes = 8;
        static final int TRANSACTION_writePrintJobData = 5;

        public Stub()
        {
            attachInterface(this, "android.printservice.IPrintServiceClient");
        }
    }

    private static class Stub.Proxy
        implements IPrintServiceClient
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.printservice.IPrintServiceClient";
        }

        public PrintJobInfo getPrintJobInfo(PrintJobId printjobid)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintServiceClient");
            if(printjobid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            printjobid.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_96;
            printjobid = (PrintJobInfo)PrintJobInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return printjobid;
_L2:
            parcel.writeInt(0);
              goto _L3
            printjobid;
            parcel1.recycle();
            parcel.recycle();
            throw printjobid;
            printjobid = null;
              goto _L4
        }

        public List getPrintJobInfos()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.printservice.IPrintServiceClient");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(PrintJobInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void onCustomPrinterIconLoaded(PrinterId printerid, Icon icon)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintServiceClient");
            if(printerid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            printerid.writeToParcel(parcel, 0);
_L3:
            if(icon == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            icon.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            printerid;
            parcel1.recycle();
            parcel.recycle();
            throw printerid;
            parcel.writeInt(0);
              goto _L4
        }

        public void onPrintersAdded(ParceledListSlice parceledlistslice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintServiceClient");
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parceledlistslice;
            parcel1.recycle();
            parcel.recycle();
            throw parceledlistslice;
        }

        public void onPrintersRemoved(ParceledListSlice parceledlistslice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintServiceClient");
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parceledlistslice;
            parcel1.recycle();
            parcel.recycle();
            throw parceledlistslice;
        }

        public boolean setPrintJobState(PrintJobId printjobid, int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintServiceClient");
            if(printjobid == null)
                break MISSING_BLOCK_LABEL_93;
            parcel.writeInt(1);
            printjobid.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
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
            parcel.writeInt(0);
              goto _L1
            printjobid;
            parcel1.recycle();
            parcel.recycle();
            throw printjobid;
        }

        public boolean setPrintJobTag(PrintJobId printjobid, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintServiceClient");
            if(printjobid == null)
                break MISSING_BLOCK_LABEL_82;
            parcel.writeInt(1);
            printjobid.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
            mRemote.transact(4, parcel, parcel1, 0);
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
            parcel.writeInt(0);
              goto _L1
            printjobid;
            parcel1.recycle();
            parcel.recycle();
            throw printjobid;
        }

        public void setProgress(PrintJobId printjobid, float f)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintServiceClient");
            if(printjobid == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            printjobid.writeToParcel(parcel, 0);
_L1:
            parcel.writeFloat(f);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            printjobid;
            parcel1.recycle();
            parcel.recycle();
            throw printjobid;
        }

        public void setStatus(PrintJobId printjobid, CharSequence charsequence)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintServiceClient");
            if(printjobid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            printjobid.writeToParcel(parcel, 0);
_L3:
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L4:
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            printjobid;
            parcel1.recycle();
            parcel.recycle();
            throw printjobid;
            parcel.writeInt(0);
              goto _L4
        }

        public void setStatusRes(PrintJobId printjobid, int i, CharSequence charsequence)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintServiceClient");
            if(printjobid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            printjobid.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_112;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L4:
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            printjobid;
            parcel1.recycle();
            parcel.recycle();
            throw printjobid;
            parcel.writeInt(0);
              goto _L4
        }

        public void writePrintJobData(ParcelFileDescriptor parcelfiledescriptor, PrintJobId printjobid)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.printservice.IPrintServiceClient");
            if(parcelfiledescriptor == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L3:
            if(printjobid == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            printjobid.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(5, parcel, null, 1);
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


    public abstract PrintJobInfo getPrintJobInfo(PrintJobId printjobid)
        throws RemoteException;

    public abstract List getPrintJobInfos()
        throws RemoteException;

    public abstract void onCustomPrinterIconLoaded(PrinterId printerid, Icon icon)
        throws RemoteException;

    public abstract void onPrintersAdded(ParceledListSlice parceledlistslice)
        throws RemoteException;

    public abstract void onPrintersRemoved(ParceledListSlice parceledlistslice)
        throws RemoteException;

    public abstract boolean setPrintJobState(PrintJobId printjobid, int i, String s)
        throws RemoteException;

    public abstract boolean setPrintJobTag(PrintJobId printjobid, String s)
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
