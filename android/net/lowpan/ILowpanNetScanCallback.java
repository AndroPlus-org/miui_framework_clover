// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;

import android.os.*;

// Referenced classes of package android.net.lowpan:
//            LowpanBeaconInfo

public interface ILowpanNetScanCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ILowpanNetScanCallback
    {

        public static ILowpanNetScanCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.lowpan.ILowpanNetScanCallback");
            if(iinterface != null && (iinterface instanceof ILowpanNetScanCallback))
                return (ILowpanNetScanCallback)iinterface;
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
                parcel1.writeString("android.net.lowpan.ILowpanNetScanCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.lowpan.ILowpanNetScanCallback");
                if(parcel.readInt() != 0)
                    parcel = (LowpanBeaconInfo)LowpanBeaconInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onNetScanBeacon(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.net.lowpan.ILowpanNetScanCallback");
                onNetScanFinished();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.net.lowpan.ILowpanNetScanCallback";
        static final int TRANSACTION_onNetScanBeacon = 1;
        static final int TRANSACTION_onNetScanFinished = 2;

        public Stub()
        {
            attachInterface(this, "android.net.lowpan.ILowpanNetScanCallback");
        }
    }

    private static class Stub.Proxy
        implements ILowpanNetScanCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.lowpan.ILowpanNetScanCallback";
        }

        public void onNetScanBeacon(LowpanBeaconInfo lowpanbeaconinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanNetScanCallback");
            if(lowpanbeaconinfo == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            lowpanbeaconinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            lowpanbeaconinfo;
            parcel.recycle();
            throw lowpanbeaconinfo;
        }

        public void onNetScanFinished()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanNetScanCallback");
            mRemote.transact(2, parcel, null, 1);
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


    public abstract void onNetScanBeacon(LowpanBeaconInfo lowpanbeaconinfo)
        throws RemoteException;

    public abstract void onNetScanFinished()
        throws RemoteException;
}
