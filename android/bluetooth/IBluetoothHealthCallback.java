// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;

// Referenced classes of package android.bluetooth:
//            BluetoothHealthAppConfiguration, BluetoothDevice

public interface IBluetoothHealthCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBluetoothHealthCallback
    {

        public static IBluetoothHealthCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.bluetooth.IBluetoothHealthCallback");
            if(iinterface != null && (iinterface instanceof IBluetoothHealthCallback))
                return (IBluetoothHealthCallback)iinterface;
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
                parcel1.writeString("android.bluetooth.IBluetoothHealthCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.bluetooth.IBluetoothHealthCallback");
                BluetoothHealthAppConfiguration bluetoothhealthappconfiguration;
                if(parcel.readInt() != 0)
                    bluetoothhealthappconfiguration = (BluetoothHealthAppConfiguration)BluetoothHealthAppConfiguration.CREATOR.createFromParcel(parcel);
                else
                    bluetoothhealthappconfiguration = null;
                onHealthAppConfigurationStatusChange(bluetoothhealthappconfiguration, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.bluetooth.IBluetoothHealthCallback");
                break;
            }
            BluetoothHealthAppConfiguration bluetoothhealthappconfiguration1;
            BluetoothDevice bluetoothdevice;
            ParcelFileDescriptor parcelfiledescriptor;
            if(parcel.readInt() != 0)
                bluetoothhealthappconfiguration1 = (BluetoothHealthAppConfiguration)BluetoothHealthAppConfiguration.CREATOR.createFromParcel(parcel);
            else
                bluetoothhealthappconfiguration1 = null;
            if(parcel.readInt() != 0)
                bluetoothdevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
            else
                bluetoothdevice = null;
            i = parcel.readInt();
            j = parcel.readInt();
            if(parcel.readInt() != 0)
                parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
            else
                parcelfiledescriptor = null;
            onHealthChannelStateChange(bluetoothhealthappconfiguration1, bluetoothdevice, i, j, parcelfiledescriptor, parcel.readInt());
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothHealthCallback";
        static final int TRANSACTION_onHealthAppConfigurationStatusChange = 1;
        static final int TRANSACTION_onHealthChannelStateChange = 2;

        public Stub()
        {
            attachInterface(this, "android.bluetooth.IBluetoothHealthCallback");
        }
    }

    private static class Stub.Proxy
        implements IBluetoothHealthCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.bluetooth.IBluetoothHealthCallback";
        }

        public void onHealthAppConfigurationStatusChange(BluetoothHealthAppConfiguration bluetoothhealthappconfiguration, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHealthCallback");
            if(bluetoothhealthappconfiguration == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothhealthappconfiguration.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bluetoothhealthappconfiguration;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothhealthappconfiguration;
        }

        public void onHealthChannelStateChange(BluetoothHealthAppConfiguration bluetoothhealthappconfiguration, BluetoothDevice bluetoothdevice, int i, int j, ParcelFileDescriptor parcelfiledescriptor, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHealthCallback");
            if(bluetoothhealthappconfiguration == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bluetoothhealthappconfiguration.writeToParcel(parcel, 0);
_L5:
            if(bluetoothdevice == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L6:
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(parcelfiledescriptor == null)
                break MISSING_BLOCK_LABEL_153;
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L7:
            parcel.writeInt(k);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            bluetoothhealthappconfiguration;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothhealthappconfiguration;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onHealthAppConfigurationStatusChange(BluetoothHealthAppConfiguration bluetoothhealthappconfiguration, int i)
        throws RemoteException;

    public abstract void onHealthChannelStateChange(BluetoothHealthAppConfiguration bluetoothhealthappconfiguration, BluetoothDevice bluetoothdevice, int i, int j, ParcelFileDescriptor parcelfiledescriptor, int k)
        throws RemoteException;
}
