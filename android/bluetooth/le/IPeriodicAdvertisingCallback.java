// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth.le;

import android.bluetooth.BluetoothDevice;
import android.os.*;

// Referenced classes of package android.bluetooth.le:
//            PeriodicAdvertisingReport

public interface IPeriodicAdvertisingCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPeriodicAdvertisingCallback
    {

        public static IPeriodicAdvertisingCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.bluetooth.le.IPeriodicAdvertisingCallback");
            if(iinterface != null && (iinterface instanceof IPeriodicAdvertisingCallback))
                return (IPeriodicAdvertisingCallback)iinterface;
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
                parcel1.writeString("android.bluetooth.le.IPeriodicAdvertisingCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.bluetooth.le.IPeriodicAdvertisingCallback");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onSyncEstablished(i, parcel1, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.bluetooth.le.IPeriodicAdvertisingCallback");
                if(parcel.readInt() != 0)
                    parcel = (PeriodicAdvertisingReport)PeriodicAdvertisingReport.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onPeriodicAdvertisingReport(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.bluetooth.le.IPeriodicAdvertisingCallback");
                onSyncLost(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.bluetooth.le.IPeriodicAdvertisingCallback";
        static final int TRANSACTION_onPeriodicAdvertisingReport = 2;
        static final int TRANSACTION_onSyncEstablished = 1;
        static final int TRANSACTION_onSyncLost = 3;

        public Stub()
        {
            attachInterface(this, "android.bluetooth.le.IPeriodicAdvertisingCallback");
        }
    }

    private static class Stub.Proxy
        implements IPeriodicAdvertisingCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.bluetooth.le.IPeriodicAdvertisingCallback";
        }

        public void onPeriodicAdvertisingReport(PeriodicAdvertisingReport periodicadvertisingreport)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.le.IPeriodicAdvertisingCallback");
            if(periodicadvertisingreport == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            periodicadvertisingreport.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            periodicadvertisingreport;
            parcel.recycle();
            throw periodicadvertisingreport;
        }

        public void onSyncEstablished(int i, BluetoothDevice bluetoothdevice, int j, int k, int l, int i1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.le.IPeriodicAdvertisingCallback");
            parcel.writeInt(i);
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_83;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            parcel.writeInt(i1);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel.recycle();
            throw bluetoothdevice;
        }

        public void onSyncLost(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.le.IPeriodicAdvertisingCallback");
            parcel.writeInt(i);
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


    public abstract void onPeriodicAdvertisingReport(PeriodicAdvertisingReport periodicadvertisingreport)
        throws RemoteException;

    public abstract void onSyncEstablished(int i, BluetoothDevice bluetoothdevice, int j, int k, int l, int i1)
        throws RemoteException;

    public abstract void onSyncLost(int i)
        throws RemoteException;
}
