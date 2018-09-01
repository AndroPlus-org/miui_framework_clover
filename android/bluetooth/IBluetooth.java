// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;

// Referenced classes of package android.bluetooth:
//            BluetoothDevice, OobData, IBluetoothCallback, BluetoothActivityEnergyInfo

public interface IBluetooth
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBluetooth
    {

        public static IBluetooth asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.bluetooth.IBluetooth");
            if(iinterface != null && (iinterface instanceof IBluetooth))
                return (IBluetooth)iinterface;
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
            int k;
            int i1;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.bluetooth.IBluetooth");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag = isEnabled();
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                i = getState();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag1 = enable();
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag2 = enableNoAutoConnect();
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag3 = disable();
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                parcel = getAddress();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                parcel = getUuids();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag4 = setName(parcel.readString());
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                parcel = getName();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                i = getScanMode();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag5 = setScanMode(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                i = getDiscoverableTimeout();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag6 = setDiscoverableTimeout(parcel.readInt());
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag7 = startDiscovery();
                parcel1.writeNoException();
                if(flag7)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag8 = cancelDiscovery();
                parcel1.writeNoException();
                if(flag8)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag9 = isDiscovering();
                parcel1.writeNoException();
                if(flag9)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                long l = getDiscoveryEndMillis();
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                i = getAdapterConnectionState();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                i = getProfileConnectionState(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                parcel = getBondedDevices();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag10;
                BluetoothDevice bluetoothdevice;
                if(parcel.readInt() != 0)
                    bluetoothdevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice = null;
                flag10 = createBond(bluetoothdevice, parcel.readInt());
                parcel1.writeNoException();
                if(flag10)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag11;
                BluetoothDevice bluetoothdevice1;
                if(parcel.readInt() != 0)
                    bluetoothdevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice1 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (OobData)OobData.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag11 = createBondOutOfBand(bluetoothdevice1, i, parcel);
                parcel1.writeNoException();
                if(flag11)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag12;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag12 = cancelBondProcess(parcel);
                parcel1.writeNoException();
                if(flag12)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag13;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag13 = removeBond(parcel);
                parcel1.writeNoException();
                if(flag13)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getBondState(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag14;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag14 = isBondingInitiatedLocally(parcel);
                parcel1.writeNoException();
                if(flag14)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                long l1 = getSupportedProfiles();
                parcel1.writeNoException();
                parcel1.writeLong(l1);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getConnectionState(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getRemoteName(parcel);
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getRemoteType(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 31: // '\037'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getRemoteAlias(parcel);
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag15;
                BluetoothDevice bluetoothdevice2;
                if(parcel.readInt() != 0)
                    bluetoothdevice2 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice2 = null;
                flag15 = setRemoteAlias(bluetoothdevice2, parcel.readString());
                parcel1.writeNoException();
                if(flag15)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getRemoteClass(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getRemoteUuids(parcel);
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag16;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag16 = fetchRemoteUuids(parcel);
                parcel1.writeNoException();
                if(flag16)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 36: // '$'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag17;
                BluetoothDevice bluetoothdevice3;
                if(parcel.readInt() != 0)
                    bluetoothdevice3 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice3 = null;
                if(parcel.readInt() != 0)
                    parcel = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag17 = sdpSearch(bluetoothdevice3, parcel);
                parcel1.writeNoException();
                if(flag17)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 37: // '%'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getBatteryLevel(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 38: // '&'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag18;
                BluetoothDevice bluetoothdevice4;
                if(parcel.readInt() != 0)
                    bluetoothdevice4 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice4 = null;
                if(parcel.readInt() != 0)
                    flag18 = true;
                else
                    flag18 = false;
                flag18 = setPin(bluetoothdevice4, flag18, parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                if(flag18)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 39: // '\''
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag19;
                BluetoothDevice bluetoothdevice5;
                if(parcel.readInt() != 0)
                    bluetoothdevice5 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice5 = null;
                if(parcel.readInt() != 0)
                    flag19 = true;
                else
                    flag19 = false;
                flag19 = setPasskey(bluetoothdevice5, flag19, parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                if(flag19)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 40: // '('
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag20;
                BluetoothDevice bluetoothdevice6;
                if(parcel.readInt() != 0)
                    bluetoothdevice6 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice6 = null;
                if(parcel.readInt() != 0)
                    flag20 = true;
                else
                    flag20 = false;
                flag20 = setPairingConfirmation(bluetoothdevice6, flag20);
                parcel1.writeNoException();
                if(flag20)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 41: // ')'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getPhonebookAccessPermission(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 42: // '*'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag21;
                BluetoothDevice bluetoothdevice7;
                if(parcel.readInt() != 0)
                    bluetoothdevice7 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice7 = null;
                flag21 = setPhonebookAccessPermission(bluetoothdevice7, parcel.readInt());
                parcel1.writeNoException();
                if(flag21)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 43: // '+'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getMessageAccessPermission(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 44: // ','
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag22;
                BluetoothDevice bluetoothdevice8;
                if(parcel.readInt() != 0)
                    bluetoothdevice8 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice8 = null;
                flag22 = setMessageAccessPermission(bluetoothdevice8, parcel.readInt());
                parcel1.writeNoException();
                if(flag22)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 45: // '-'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getSimAccessPermission(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 46: // '.'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag23;
                BluetoothDevice bluetoothdevice9;
                if(parcel.readInt() != 0)
                    bluetoothdevice9 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice9 = null;
                flag23 = setSimAccessPermission(bluetoothdevice9, parcel.readInt());
                parcel1.writeNoException();
                if(flag23)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 47: // '/'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                BluetoothDevice bluetoothdevice10;
                if(parcel.readInt() != 0)
                    bluetoothdevice10 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice10 = null;
                sendConnectionStateChange(bluetoothdevice10, parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 48: // '0'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                registerCallback(IBluetoothCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 49: // '1'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                unregisterCallback(IBluetoothCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 50: // '2'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                BluetoothDevice bluetoothdevice11;
                ParcelUuid parceluuid1;
                if(parcel.readInt() != 0)
                    bluetoothdevice11 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice11 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parceluuid1 = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel(parcel);
                else
                    parceluuid1 = null;
                parcel = connectSocket(bluetoothdevice11, i, parceluuid1, parcel.readInt(), parcel.readInt());
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

            case 51: // '3'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                i = parcel.readInt();
                String s = parcel.readString();
                ParcelUuid parceluuid;
                if(parcel.readInt() != 0)
                    parceluuid = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel(parcel);
                else
                    parceluuid = null;
                parcel = createSocketChannel(i, s, parceluuid, parcel.readInt(), parcel.readInt());
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

            case 52: // '4'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag24 = factoryReset();
                parcel1.writeNoException();
                if(flag24)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 53: // '5'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag25 = isMultiAdvertisementSupported();
                parcel1.writeNoException();
                if(flag25)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 54: // '6'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag26 = isOffloadedFilteringSupported();
                parcel1.writeNoException();
                if(flag26)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 55: // '7'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag27 = isOffloadedScanBatchingSupported();
                parcel1.writeNoException();
                if(flag27)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 56: // '8'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag28 = isActivityAndEnergyReportingSupported();
                parcel1.writeNoException();
                if(flag28)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 57: // '9'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag29 = isLe2MPhySupported();
                parcel1.writeNoException();
                if(flag29)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 58: // ':'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag30 = isLeCodedPhySupported();
                parcel1.writeNoException();
                if(flag30)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 59: // ';'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag31 = isLeExtendedAdvertisingSupported();
                parcel1.writeNoException();
                if(flag31)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 60: // '<'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                boolean flag32 = isLePeriodicAdvertisingSupported();
                parcel1.writeNoException();
                if(flag32)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 61: // '='
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                i = getLeMaximumAdvertisingDataLength();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 62: // '>'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                parcel = reportActivityInfo();
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

            case 63: // '?'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                if(parcel.readInt() != 0)
                    parcel = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                requestActivityInfo(parcel);
                return true;

            case 64: // '@'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                onLeServiceUp();
                parcel1.writeNoException();
                return true;

            case 65: // 'A'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                onBrEdrDown();
                parcel1.writeNoException();
                return true;

            case 66: // 'B'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                i = setSocketOpt(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createByteArray(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 67: // 'C'
                parcel.enforceInterface("android.bluetooth.IBluetooth");
                k = parcel.readInt();
                i1 = parcel.readInt();
                j = parcel.readInt();
                i = parcel.readInt();
                break;
            }
            if(i < 0)
                parcel = null;
            else
                parcel = new byte[i];
            i = getSocketOpt(k, i1, j, parcel);
            parcel1.writeNoException();
            parcel1.writeInt(i);
            parcel1.writeByteArray(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.bluetooth.IBluetooth";
        static final int TRANSACTION_cancelBondProcess = 23;
        static final int TRANSACTION_cancelDiscovery = 15;
        static final int TRANSACTION_connectSocket = 50;
        static final int TRANSACTION_createBond = 21;
        static final int TRANSACTION_createBondOutOfBand = 22;
        static final int TRANSACTION_createSocketChannel = 51;
        static final int TRANSACTION_disable = 5;
        static final int TRANSACTION_enable = 3;
        static final int TRANSACTION_enableNoAutoConnect = 4;
        static final int TRANSACTION_factoryReset = 52;
        static final int TRANSACTION_fetchRemoteUuids = 35;
        static final int TRANSACTION_getAdapterConnectionState = 18;
        static final int TRANSACTION_getAddress = 6;
        static final int TRANSACTION_getBatteryLevel = 37;
        static final int TRANSACTION_getBondState = 25;
        static final int TRANSACTION_getBondedDevices = 20;
        static final int TRANSACTION_getConnectionState = 28;
        static final int TRANSACTION_getDiscoverableTimeout = 12;
        static final int TRANSACTION_getDiscoveryEndMillis = 17;
        static final int TRANSACTION_getLeMaximumAdvertisingDataLength = 61;
        static final int TRANSACTION_getMessageAccessPermission = 43;
        static final int TRANSACTION_getName = 9;
        static final int TRANSACTION_getPhonebookAccessPermission = 41;
        static final int TRANSACTION_getProfileConnectionState = 19;
        static final int TRANSACTION_getRemoteAlias = 31;
        static final int TRANSACTION_getRemoteClass = 33;
        static final int TRANSACTION_getRemoteName = 29;
        static final int TRANSACTION_getRemoteType = 30;
        static final int TRANSACTION_getRemoteUuids = 34;
        static final int TRANSACTION_getScanMode = 10;
        static final int TRANSACTION_getSimAccessPermission = 45;
        static final int TRANSACTION_getSocketOpt = 67;
        static final int TRANSACTION_getState = 2;
        static final int TRANSACTION_getSupportedProfiles = 27;
        static final int TRANSACTION_getUuids = 7;
        static final int TRANSACTION_isActivityAndEnergyReportingSupported = 56;
        static final int TRANSACTION_isBondingInitiatedLocally = 26;
        static final int TRANSACTION_isDiscovering = 16;
        static final int TRANSACTION_isEnabled = 1;
        static final int TRANSACTION_isLe2MPhySupported = 57;
        static final int TRANSACTION_isLeCodedPhySupported = 58;
        static final int TRANSACTION_isLeExtendedAdvertisingSupported = 59;
        static final int TRANSACTION_isLePeriodicAdvertisingSupported = 60;
        static final int TRANSACTION_isMultiAdvertisementSupported = 53;
        static final int TRANSACTION_isOffloadedFilteringSupported = 54;
        static final int TRANSACTION_isOffloadedScanBatchingSupported = 55;
        static final int TRANSACTION_onBrEdrDown = 65;
        static final int TRANSACTION_onLeServiceUp = 64;
        static final int TRANSACTION_registerCallback = 48;
        static final int TRANSACTION_removeBond = 24;
        static final int TRANSACTION_reportActivityInfo = 62;
        static final int TRANSACTION_requestActivityInfo = 63;
        static final int TRANSACTION_sdpSearch = 36;
        static final int TRANSACTION_sendConnectionStateChange = 47;
        static final int TRANSACTION_setDiscoverableTimeout = 13;
        static final int TRANSACTION_setMessageAccessPermission = 44;
        static final int TRANSACTION_setName = 8;
        static final int TRANSACTION_setPairingConfirmation = 40;
        static final int TRANSACTION_setPasskey = 39;
        static final int TRANSACTION_setPhonebookAccessPermission = 42;
        static final int TRANSACTION_setPin = 38;
        static final int TRANSACTION_setRemoteAlias = 32;
        static final int TRANSACTION_setScanMode = 11;
        static final int TRANSACTION_setSimAccessPermission = 46;
        static final int TRANSACTION_setSocketOpt = 66;
        static final int TRANSACTION_startDiscovery = 14;
        static final int TRANSACTION_unregisterCallback = 49;

        public Stub()
        {
            attachInterface(this, "android.bluetooth.IBluetooth");
        }
    }

    private static class Stub.Proxy
        implements IBluetooth
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean cancelBondProcess(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(23, parcel, parcel1, 0);
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
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean cancelDiscovery()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(15, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ParcelFileDescriptor connectSocket(BluetoothDevice bluetoothdevice, int i, ParcelUuid parceluuid, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L5:
            parcel.writeInt(i);
            if(parceluuid == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            parceluuid.writeToParcel(parcel, 0);
_L6:
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(50, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_158;
            bluetoothdevice = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L7:
            parcel1.recycle();
            parcel.recycle();
            return bluetoothdevice;
_L2:
            parcel.writeInt(0);
              goto _L5
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
_L4:
            parcel.writeInt(0);
              goto _L6
            bluetoothdevice = null;
              goto _L7
        }

        public boolean createBond(BluetoothDevice bluetoothdevice, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_81;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(21, parcel, parcel1, 0);
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
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean createBondOutOfBand(BluetoothDevice bluetoothdevice, int i, OobData oobdata)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            if(oobdata == null)
                break MISSING_BLOCK_LABEL_127;
            parcel.writeInt(1);
            oobdata.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(22, parcel, parcel1, 0);
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
_L2:
            parcel.writeInt(0);
              goto _L3
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
            parcel.writeInt(0);
              goto _L4
        }

        public ParcelFileDescriptor createSocketChannel(int i, String s, ParcelUuid parceluuid, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            parcel.writeInt(i);
            parcel.writeString(s);
            if(parceluuid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parceluuid.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(51, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_138;
            s = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            s = null;
              goto _L4
        }

        public boolean disable()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(5, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean enable()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean enableNoAutoConnect()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean factoryReset()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(52, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean fetchRemoteUuids(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(35, parcel, parcel1, 0);
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
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public int getAdapterConnectionState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getAddress()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getBatteryLevel(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(37, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public int getBondState(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public BluetoothDevice[] getBondedDevices()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            BluetoothDevice abluetoothdevice[];
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            abluetoothdevice = (BluetoothDevice[])parcel1.createTypedArray(BluetoothDevice.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return abluetoothdevice;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getConnectionState(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public int getDiscoverableTimeout()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public long getDiscoveryEndMillis()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.bluetooth.IBluetooth";
        }

        public int getLeMaximumAdvertisingDataLength()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(61, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getMessageAccessPermission(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(43, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public String getName()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getPhonebookAccessPermission(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(41, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public int getProfileConnectionState(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            parcel.writeInt(i);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getRemoteAlias(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            bluetoothdevice = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return bluetoothdevice;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public int getRemoteClass(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(33, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public String getRemoteName(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            bluetoothdevice = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return bluetoothdevice;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public int getRemoteType(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(30, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public ParcelUuid[] getRemoteUuids(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_69;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(34, parcel, parcel1, 0);
            parcel1.readException();
            bluetoothdevice = (ParcelUuid[])parcel1.createTypedArray(ParcelUuid.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return bluetoothdevice;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public int getScanMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getSimAccessPermission(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(45, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public int getSocketOpt(int i, int j, int k, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            if(abyte0 != null)
                break MISSING_BLOCK_LABEL_93;
            parcel.writeInt(-1);
_L1:
            mRemote.transact(67, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.readByteArray(abyte0);
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(abyte0.length);
              goto _L1
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public int getState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public long getSupportedProfiles()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ParcelUuid[] getUuids()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            ParcelUuid aparceluuid[];
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            aparceluuid = (ParcelUuid[])parcel1.createTypedArray(ParcelUuid.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return aparceluuid;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isActivityAndEnergyReportingSupported()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(56, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isBondingInitiatedLocally(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(26, parcel, parcel1, 0);
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
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean isDiscovering()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(16, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(1, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isLe2MPhySupported()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(57, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isLeCodedPhySupported()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(58, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isLeExtendedAdvertisingSupported()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(59, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isLePeriodicAdvertisingSupported()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(60, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isMultiAdvertisementSupported()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(53, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isOffloadedFilteringSupported()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(54, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isOffloadedScanBatchingSupported()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(55, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void onBrEdrDown()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(65, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void onLeServiceUp()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(64, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void registerCallback(IBluetoothCallback ibluetoothcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(ibluetoothcallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ibluetoothcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(48, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibluetoothcallback;
            parcel1.recycle();
            parcel.recycle();
            throw ibluetoothcallback;
        }

        public boolean removeBond(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(24, parcel, parcel1, 0);
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
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public BluetoothActivityEnergyInfo reportActivityInfo()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(62, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            BluetoothActivityEnergyInfo bluetoothactivityenergyinfo = (BluetoothActivityEnergyInfo)BluetoothActivityEnergyInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return bluetoothactivityenergyinfo;
_L2:
            bluetoothactivityenergyinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void requestActivityInfo(ResultReceiver resultreceiver)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(resultreceiver == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            resultreceiver.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(63, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            resultreceiver;
            parcel.recycle();
            throw resultreceiver;
        }

        public boolean sdpSearch(BluetoothDevice bluetoothdevice, ParcelUuid parceluuid)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L3:
            if(parceluuid == null)
                break MISSING_BLOCK_LABEL_113;
            parcel.writeInt(1);
            parceluuid.writeToParcel(parcel, 0);
_L4:
            int i;
            mRemote.transact(36, parcel, parcel1, 0);
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
_L2:
            parcel.writeInt(0);
              goto _L3
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
            parcel.writeInt(0);
              goto _L4
        }

        public void sendConnectionStateChange(BluetoothDevice bluetoothdevice, int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(47, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean setDiscoverableTimeout(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            parcel.writeInt(i);
            mRemote.transact(13, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean setMessageAccessPermission(BluetoothDevice bluetoothdevice, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_81;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(44, parcel, parcel1, 0);
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
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean setName(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            parcel.writeString(s);
            mRemote.transact(8, parcel, parcel1, 0);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean setPairingConfirmation(BluetoothDevice bluetoothdevice, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_92;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(40, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean setPasskey(BluetoothDevice bluetoothdevice, boolean flag, int i, byte abyte0[])
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_107;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
            mRemote.transact(39, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean setPhonebookAccessPermission(BluetoothDevice bluetoothdevice, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_81;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(42, parcel, parcel1, 0);
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
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean setPin(BluetoothDevice bluetoothdevice, boolean flag, int i, byte abyte0[])
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_107;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
            mRemote.transact(38, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean setRemoteAlias(BluetoothDevice bluetoothdevice, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_83;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
            mRemote.transact(32, parcel, parcel1, 0);
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
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean setScanMode(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(11, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean setSimAccessPermission(BluetoothDevice bluetoothdevice, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_81;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(46, parcel, parcel1, 0);
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
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public int setSocketOpt(int i, int j, int k, byte abyte0[], int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeByteArray(abyte0);
            parcel.writeInt(l);
            mRemote.transact(66, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public boolean startDiscovery()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            mRemote.transact(14, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void unregisterCallback(IBluetoothCallback ibluetoothcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetooth");
            if(ibluetoothcallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ibluetoothcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(49, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibluetoothcallback;
            parcel1.recycle();
            parcel.recycle();
            throw ibluetoothcallback;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean cancelBondProcess(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract boolean cancelDiscovery()
        throws RemoteException;

    public abstract ParcelFileDescriptor connectSocket(BluetoothDevice bluetoothdevice, int i, ParcelUuid parceluuid, int j, int k)
        throws RemoteException;

    public abstract boolean createBond(BluetoothDevice bluetoothdevice, int i)
        throws RemoteException;

    public abstract boolean createBondOutOfBand(BluetoothDevice bluetoothdevice, int i, OobData oobdata)
        throws RemoteException;

    public abstract ParcelFileDescriptor createSocketChannel(int i, String s, ParcelUuid parceluuid, int j, int k)
        throws RemoteException;

    public abstract boolean disable()
        throws RemoteException;

    public abstract boolean enable()
        throws RemoteException;

    public abstract boolean enableNoAutoConnect()
        throws RemoteException;

    public abstract boolean factoryReset()
        throws RemoteException;

    public abstract boolean fetchRemoteUuids(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract int getAdapterConnectionState()
        throws RemoteException;

    public abstract String getAddress()
        throws RemoteException;

    public abstract int getBatteryLevel(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract int getBondState(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract BluetoothDevice[] getBondedDevices()
        throws RemoteException;

    public abstract int getConnectionState(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract int getDiscoverableTimeout()
        throws RemoteException;

    public abstract long getDiscoveryEndMillis()
        throws RemoteException;

    public abstract int getLeMaximumAdvertisingDataLength()
        throws RemoteException;

    public abstract int getMessageAccessPermission(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract String getName()
        throws RemoteException;

    public abstract int getPhonebookAccessPermission(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract int getProfileConnectionState(int i)
        throws RemoteException;

    public abstract String getRemoteAlias(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract int getRemoteClass(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract String getRemoteName(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract int getRemoteType(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract ParcelUuid[] getRemoteUuids(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract int getScanMode()
        throws RemoteException;

    public abstract int getSimAccessPermission(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract int getSocketOpt(int i, int j, int k, byte abyte0[])
        throws RemoteException;

    public abstract int getState()
        throws RemoteException;

    public abstract long getSupportedProfiles()
        throws RemoteException;

    public abstract ParcelUuid[] getUuids()
        throws RemoteException;

    public abstract boolean isActivityAndEnergyReportingSupported()
        throws RemoteException;

    public abstract boolean isBondingInitiatedLocally(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract boolean isDiscovering()
        throws RemoteException;

    public abstract boolean isEnabled()
        throws RemoteException;

    public abstract boolean isLe2MPhySupported()
        throws RemoteException;

    public abstract boolean isLeCodedPhySupported()
        throws RemoteException;

    public abstract boolean isLeExtendedAdvertisingSupported()
        throws RemoteException;

    public abstract boolean isLePeriodicAdvertisingSupported()
        throws RemoteException;

    public abstract boolean isMultiAdvertisementSupported()
        throws RemoteException;

    public abstract boolean isOffloadedFilteringSupported()
        throws RemoteException;

    public abstract boolean isOffloadedScanBatchingSupported()
        throws RemoteException;

    public abstract void onBrEdrDown()
        throws RemoteException;

    public abstract void onLeServiceUp()
        throws RemoteException;

    public abstract void registerCallback(IBluetoothCallback ibluetoothcallback)
        throws RemoteException;

    public abstract boolean removeBond(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract BluetoothActivityEnergyInfo reportActivityInfo()
        throws RemoteException;

    public abstract void requestActivityInfo(ResultReceiver resultreceiver)
        throws RemoteException;

    public abstract boolean sdpSearch(BluetoothDevice bluetoothdevice, ParcelUuid parceluuid)
        throws RemoteException;

    public abstract void sendConnectionStateChange(BluetoothDevice bluetoothdevice, int i, int j, int k)
        throws RemoteException;

    public abstract boolean setDiscoverableTimeout(int i)
        throws RemoteException;

    public abstract boolean setMessageAccessPermission(BluetoothDevice bluetoothdevice, int i)
        throws RemoteException;

    public abstract boolean setName(String s)
        throws RemoteException;

    public abstract boolean setPairingConfirmation(BluetoothDevice bluetoothdevice, boolean flag)
        throws RemoteException;

    public abstract boolean setPasskey(BluetoothDevice bluetoothdevice, boolean flag, int i, byte abyte0[])
        throws RemoteException;

    public abstract boolean setPhonebookAccessPermission(BluetoothDevice bluetoothdevice, int i)
        throws RemoteException;

    public abstract boolean setPin(BluetoothDevice bluetoothdevice, boolean flag, int i, byte abyte0[])
        throws RemoteException;

    public abstract boolean setRemoteAlias(BluetoothDevice bluetoothdevice, String s)
        throws RemoteException;

    public abstract boolean setScanMode(int i, int j)
        throws RemoteException;

    public abstract boolean setSimAccessPermission(BluetoothDevice bluetoothdevice, int i)
        throws RemoteException;

    public abstract int setSocketOpt(int i, int j, int k, byte abyte0[], int l)
        throws RemoteException;

    public abstract boolean startDiscovery()
        throws RemoteException;

    public abstract void unregisterCallback(IBluetoothCallback ibluetoothcallback)
        throws RemoteException;
}
