// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.graphics.drawable.Icon;
import android.os.*;
import java.util.List;

// Referenced classes of package android.print:
//            PrintJobInfo

public interface IPrintSpoolerCallbacks
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPrintSpoolerCallbacks
    {

        public static IPrintSpoolerCallbacks asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.print.IPrintSpoolerCallbacks");
            if(iinterface != null && (iinterface instanceof IPrintSpoolerCallbacks))
                return (IPrintSpoolerCallbacks)iinterface;
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
                parcel1.writeString("android.print.IPrintSpoolerCallbacks");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.print.IPrintSpoolerCallbacks");
                onGetPrintJobInfosResult(parcel.createTypedArrayList(PrintJobInfo.CREATOR), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.print.IPrintSpoolerCallbacks");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onCancelPrintJobResult(flag, parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.print.IPrintSpoolerCallbacks");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                onSetPrintJobStateResult(flag1, parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.print.IPrintSpoolerCallbacks");
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                onSetPrintJobTagResult(flag2, parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.print.IPrintSpoolerCallbacks");
                if(parcel.readInt() != 0)
                    parcel1 = (PrintJobInfo)PrintJobInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onGetPrintJobInfoResult(parcel1, parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.print.IPrintSpoolerCallbacks");
                if(parcel.readInt() != 0)
                    parcel1 = (Icon)Icon.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onGetCustomPrinterIconResult(parcel1, parcel.readInt());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.print.IPrintSpoolerCallbacks");
                onCustomPrinterIconCached(parcel.readInt());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.print.IPrintSpoolerCallbacks");
                customPrinterIconCacheCleared(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.print.IPrintSpoolerCallbacks";
        static final int TRANSACTION_customPrinterIconCacheCleared = 8;
        static final int TRANSACTION_onCancelPrintJobResult = 2;
        static final int TRANSACTION_onCustomPrinterIconCached = 7;
        static final int TRANSACTION_onGetCustomPrinterIconResult = 6;
        static final int TRANSACTION_onGetPrintJobInfoResult = 5;
        static final int TRANSACTION_onGetPrintJobInfosResult = 1;
        static final int TRANSACTION_onSetPrintJobStateResult = 3;
        static final int TRANSACTION_onSetPrintJobTagResult = 4;

        public Stub()
        {
            attachInterface(this, "android.print.IPrintSpoolerCallbacks");
        }
    }

    private static class Stub.Proxy
        implements IPrintSpoolerCallbacks
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void customPrinterIconCacheCleared(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpoolerCallbacks");
            parcel.writeInt(i);
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.print.IPrintSpoolerCallbacks";
        }

        public void onCancelPrintJobResult(boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            j = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpoolerCallbacks");
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onCustomPrinterIconCached(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpoolerCallbacks");
            parcel.writeInt(i);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onGetCustomPrinterIconResult(Icon icon, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpoolerCallbacks");
            if(icon == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            icon.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            icon;
            parcel.recycle();
            throw icon;
        }

        public void onGetPrintJobInfoResult(PrintJobInfo printjobinfo, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpoolerCallbacks");
            if(printjobinfo == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            printjobinfo.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            printjobinfo;
            parcel.recycle();
            throw printjobinfo;
        }

        public void onGetPrintJobInfosResult(List list, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpoolerCallbacks");
            parcel.writeTypedList(list);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public void onSetPrintJobStateResult(boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            j = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpoolerCallbacks");
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onSetPrintJobTagResult(boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            j = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintSpoolerCallbacks");
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
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


    public abstract void customPrinterIconCacheCleared(int i)
        throws RemoteException;

    public abstract void onCancelPrintJobResult(boolean flag, int i)
        throws RemoteException;

    public abstract void onCustomPrinterIconCached(int i)
        throws RemoteException;

    public abstract void onGetCustomPrinterIconResult(Icon icon, int i)
        throws RemoteException;

    public abstract void onGetPrintJobInfoResult(PrintJobInfo printjobinfo, int i)
        throws RemoteException;

    public abstract void onGetPrintJobInfosResult(List list, int i)
        throws RemoteException;

    public abstract void onSetPrintJobStateResult(boolean flag, int i)
        throws RemoteException;

    public abstract void onSetPrintJobTagResult(boolean flag, int i)
        throws RemoteException;
}
