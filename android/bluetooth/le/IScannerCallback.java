// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import android.os.*;
import java.util.List;

// Referenced classes of package android.bluetooth.le:
//            ScanResult

public interface IScannerCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IScannerCallback
    {

        public static IScannerCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.bluetooth.le.IScannerCallback");
            if(iinterface != null && (iinterface instanceof IScannerCallback))
                return (IScannerCallback)iinterface;
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
                parcel1.writeString("android.bluetooth.le.IScannerCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.bluetooth.le.IScannerCallback");
                onScannerRegistered(parcel.readInt(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.bluetooth.le.IScannerCallback");
                if(parcel.readInt() != 0)
                    parcel = (ScanResult)ScanResult.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onScanResult(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.bluetooth.le.IScannerCallback");
                onBatchScanResults(parcel.createTypedArrayList(ScanResult.CREATOR));
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.bluetooth.le.IScannerCallback");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                if(parcel.readInt() != 0)
                    parcel = (ScanResult)ScanResult.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onFoundOrLost(flag, parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.bluetooth.le.IScannerCallback");
                onScanManagerErrorCallback(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.bluetooth.le.IScannerCallback";
        static final int TRANSACTION_onBatchScanResults = 3;
        static final int TRANSACTION_onFoundOrLost = 4;
        static final int TRANSACTION_onScanManagerErrorCallback = 5;
        static final int TRANSACTION_onScanResult = 2;
        static final int TRANSACTION_onScannerRegistered = 1;

        public Stub()
        {
            attachInterface(this, "android.bluetooth.le.IScannerCallback");
        }
    }

    private static class Stub.Proxy
        implements IScannerCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.bluetooth.le.IScannerCallback";
        }

        public void onBatchScanResults(List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.le.IScannerCallback");
            parcel.writeTypedList(list);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public void onFoundOrLost(boolean flag, ScanResult scanresult)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.le.IScannerCallback");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            if(scanresult == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            scanresult.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            scanresult;
            parcel.recycle();
            throw scanresult;
        }

        public void onScanManagerErrorCallback(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.le.IScannerCallback");
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onScanResult(ScanResult scanresult)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.le.IScannerCallback");
            if(scanresult == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            scanresult.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            scanresult;
            parcel.recycle();
            throw scanresult;
        }

        public void onScannerRegistered(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.le.IScannerCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
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


    public abstract void onBatchScanResults(List list)
        throws RemoteException;

    public abstract void onFoundOrLost(boolean flag, ScanResult scanresult)
        throws RemoteException;

    public abstract void onScanManagerErrorCallback(int i)
        throws RemoteException;

    public abstract void onScanResult(ScanResult scanresult)
        throws RemoteException;

    public abstract void onScannerRegistered(int i, int j)
        throws RemoteException;
}
