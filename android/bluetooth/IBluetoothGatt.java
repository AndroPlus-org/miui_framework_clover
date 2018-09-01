// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.app.PendingIntent;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertisingSetParameters;
import android.bluetooth.le.IAdvertisingSetCallback;
import android.bluetooth.le.IPeriodicAdvertisingCallback;
import android.bluetooth.le.IScannerCallback;
import android.bluetooth.le.PeriodicAdvertisingParameters;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.*;
import java.util.List;

// Referenced classes of package android.bluetooth:
//            BluetoothGattService, IBluetoothGattCallback, IBluetoothGattServerCallback, BluetoothDevice

public interface IBluetoothGatt
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBluetoothGatt
    {

        public static IBluetoothGatt asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.bluetooth.IBluetoothGatt");
            if(iinterface != null && (iinterface instanceof IBluetoothGatt))
                return (IBluetoothGatt)iinterface;
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
                parcel1.writeString("android.bluetooth.IBluetoothGatt");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                parcel = getDevicesMatchingConnectionStates(parcel.createIntArray());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                IScannerCallback iscannercallback = android.bluetooth.le.IScannerCallback.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                registerScanner(iscannercallback, parcel);
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                unregisterScanner(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                i = parcel.readInt();
                ScanSettings scansettings;
                if(parcel.readInt() != 0)
                    scansettings = (ScanSettings)ScanSettings.CREATOR.createFromParcel(parcel);
                else
                    scansettings = null;
                startScan(i, scansettings, parcel.createTypedArrayList(ScanFilter.CREATOR), parcel.readArrayList(getClass().getClassLoader()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                PendingIntent pendingintent;
                ScanSettings scansettings1;
                if(parcel.readInt() != 0)
                    pendingintent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent = null;
                if(parcel.readInt() != 0)
                    scansettings1 = (ScanSettings)ScanSettings.CREATOR.createFromParcel(parcel);
                else
                    scansettings1 = null;
                startScanForIntent(pendingintent, scansettings1, parcel.createTypedArrayList(ScanFilter.CREATOR), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                PendingIntent pendingintent1;
                if(parcel.readInt() != 0)
                    pendingintent1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent1 = null;
                stopScanForIntent(pendingintent1, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                stopScan(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                flushPendingBatchResults(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                AdvertisingSetParameters advertisingsetparameters;
                AdvertiseData advertisedata;
                AdvertiseData advertisedata1;
                PeriodicAdvertisingParameters periodicadvertisingparameters;
                AdvertiseData advertisedata2;
                if(parcel.readInt() != 0)
                    advertisingsetparameters = (AdvertisingSetParameters)AdvertisingSetParameters.CREATOR.createFromParcel(parcel);
                else
                    advertisingsetparameters = null;
                if(parcel.readInt() != 0)
                    advertisedata = (AdvertiseData)AdvertiseData.CREATOR.createFromParcel(parcel);
                else
                    advertisedata = null;
                if(parcel.readInt() != 0)
                    advertisedata1 = (AdvertiseData)AdvertiseData.CREATOR.createFromParcel(parcel);
                else
                    advertisedata1 = null;
                if(parcel.readInt() != 0)
                    periodicadvertisingparameters = (PeriodicAdvertisingParameters)PeriodicAdvertisingParameters.CREATOR.createFromParcel(parcel);
                else
                    periodicadvertisingparameters = null;
                if(parcel.readInt() != 0)
                    advertisedata2 = (AdvertiseData)AdvertiseData.CREATOR.createFromParcel(parcel);
                else
                    advertisedata2 = null;
                startAdvertisingSet(advertisingsetparameters, advertisedata, advertisedata1, periodicadvertisingparameters, advertisedata2, parcel.readInt(), parcel.readInt(), android.bluetooth.le.IAdvertisingSetCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                stopAdvertisingSet(android.bluetooth.le.IAdvertisingSetCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                getOwnAddress(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                i = parcel.readInt();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                enableAdvertisingSet(i, flag, parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (AdvertiseData)AdvertiseData.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setAdvertisingData(i, parcel);
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (AdvertiseData)AdvertiseData.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setScanResponseData(i, parcel);
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (AdvertisingSetParameters)AdvertisingSetParameters.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setAdvertisingParameters(i, parcel);
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (PeriodicAdvertisingParameters)PeriodicAdvertisingParameters.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setPeriodicAdvertisingParameters(i, parcel);
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (AdvertiseData)AdvertiseData.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setPeriodicAdvertisingData(i, parcel);
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                i = parcel.readInt();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                setPeriodicAdvertisingEnable(i, flag1);
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                ScanResult scanresult;
                if(parcel.readInt() != 0)
                    scanresult = (ScanResult)ScanResult.CREATOR.createFromParcel(parcel);
                else
                    scanresult = null;
                registerSync(scanresult, parcel.readInt(), parcel.readInt(), android.bluetooth.le.IPeriodicAdvertisingCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                unregisterSync(android.bluetooth.le.IPeriodicAdvertisingCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                ParcelUuid parceluuid;
                if(parcel.readInt() != 0)
                    parceluuid = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel(parcel);
                else
                    parceluuid = null;
                registerClient(parceluuid, IBluetoothGattCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                unregisterClient(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                j = parcel.readInt();
                String s = parcel.readString();
                boolean flag2;
                boolean flag7;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                clientConnect(j, s, flag2, i, flag7, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                clientDisconnect(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                clientSetPreferredPhy(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                clientReadPhy(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                refreshDevice(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                discoverServices(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                i = parcel.readInt();
                String s1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                discoverServiceByUuid(i, s1, parcel);
                parcel1.writeNoException();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                readCharacteristic(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 31: // '\037'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                i = parcel.readInt();
                String s6 = parcel.readString();
                ParcelUuid parceluuid1;
                if(parcel.readInt() != 0)
                    parceluuid1 = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel(parcel);
                else
                    parceluuid1 = null;
                readUsingCharacteristicUuid(i, s6, parceluuid1, parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                writeCharacteristic(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                readDescriptor(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                writeDescriptor(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                j = parcel.readInt();
                String s2 = parcel.readString();
                i = parcel.readInt();
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                registerForNotification(j, s2, i, flag3);
                parcel1.writeNoException();
                return true;

            case 36: // '$'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                beginReliableWrite(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 37: // '%'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                i = parcel.readInt();
                String s3 = parcel.readString();
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                endReliableWrite(i, s3, flag4);
                parcel1.writeNoException();
                return true;

            case 38: // '&'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                readRemoteRssi(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 39: // '\''
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                configureMTU(parcel.readInt(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 40: // '('
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                connectionParameterUpdate(parcel.readInt(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 41: // ')'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                ParcelUuid parceluuid2;
                if(parcel.readInt() != 0)
                    parceluuid2 = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel(parcel);
                else
                    parceluuid2 = null;
                registerServer(parceluuid2, IBluetoothGattServerCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 42: // '*'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                unregisterServer(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 43: // '+'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                i = parcel.readInt();
                String s4 = parcel.readString();
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                serverConnect(i, s4, flag5, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 44: // ','
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                serverDisconnect(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 45: // '-'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                serverSetPreferredPhy(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 46: // '.'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                serverReadPhy(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 47: // '/'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (BluetoothGattService)BluetoothGattService.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                addService(i, parcel);
                parcel1.writeNoException();
                return true;

            case 48: // '0'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                removeService(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 49: // '1'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                clearServices(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 50: // '2'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                sendResponse(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                return true;

            case 51: // '3'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                j = parcel.readInt();
                String s5 = parcel.readString();
                i = parcel.readInt();
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                sendNotification(j, s5, i, flag6, parcel.createByteArray());
                parcel1.writeNoException();
                return true;

            case 52: // '4'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                disconnectAll();
                parcel1.writeNoException();
                return true;

            case 53: // '5'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                unregAll();
                parcel1.writeNoException();
                return true;

            case 54: // '6'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                i = numHwTrackFiltersAvailable();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 55: // '7'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                registerStatisticsClient(android.bluetooth.le.IScannerCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 56: // '8'
                parcel.enforceInterface("android.bluetooth.IBluetoothGatt");
                unregisterStatisticsClient(android.bluetooth.le.IScannerCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothGatt";
        static final int TRANSACTION_addService = 47;
        static final int TRANSACTION_beginReliableWrite = 36;
        static final int TRANSACTION_clearServices = 49;
        static final int TRANSACTION_clientConnect = 23;
        static final int TRANSACTION_clientDisconnect = 24;
        static final int TRANSACTION_clientReadPhy = 26;
        static final int TRANSACTION_clientSetPreferredPhy = 25;
        static final int TRANSACTION_configureMTU = 39;
        static final int TRANSACTION_connectionParameterUpdate = 40;
        static final int TRANSACTION_disconnectAll = 52;
        static final int TRANSACTION_discoverServiceByUuid = 29;
        static final int TRANSACTION_discoverServices = 28;
        static final int TRANSACTION_enableAdvertisingSet = 12;
        static final int TRANSACTION_endReliableWrite = 37;
        static final int TRANSACTION_flushPendingBatchResults = 8;
        static final int TRANSACTION_getDevicesMatchingConnectionStates = 1;
        static final int TRANSACTION_getOwnAddress = 11;
        static final int TRANSACTION_numHwTrackFiltersAvailable = 54;
        static final int TRANSACTION_readCharacteristic = 30;
        static final int TRANSACTION_readDescriptor = 33;
        static final int TRANSACTION_readRemoteRssi = 38;
        static final int TRANSACTION_readUsingCharacteristicUuid = 31;
        static final int TRANSACTION_refreshDevice = 27;
        static final int TRANSACTION_registerClient = 21;
        static final int TRANSACTION_registerForNotification = 35;
        static final int TRANSACTION_registerScanner = 2;
        static final int TRANSACTION_registerServer = 41;
        static final int TRANSACTION_registerStatisticsClient = 55;
        static final int TRANSACTION_registerSync = 19;
        static final int TRANSACTION_removeService = 48;
        static final int TRANSACTION_sendNotification = 51;
        static final int TRANSACTION_sendResponse = 50;
        static final int TRANSACTION_serverConnect = 43;
        static final int TRANSACTION_serverDisconnect = 44;
        static final int TRANSACTION_serverReadPhy = 46;
        static final int TRANSACTION_serverSetPreferredPhy = 45;
        static final int TRANSACTION_setAdvertisingData = 13;
        static final int TRANSACTION_setAdvertisingParameters = 15;
        static final int TRANSACTION_setPeriodicAdvertisingData = 17;
        static final int TRANSACTION_setPeriodicAdvertisingEnable = 18;
        static final int TRANSACTION_setPeriodicAdvertisingParameters = 16;
        static final int TRANSACTION_setScanResponseData = 14;
        static final int TRANSACTION_startAdvertisingSet = 9;
        static final int TRANSACTION_startScan = 4;
        static final int TRANSACTION_startScanForIntent = 5;
        static final int TRANSACTION_stopAdvertisingSet = 10;
        static final int TRANSACTION_stopScan = 7;
        static final int TRANSACTION_stopScanForIntent = 6;
        static final int TRANSACTION_unregAll = 53;
        static final int TRANSACTION_unregisterClient = 22;
        static final int TRANSACTION_unregisterScanner = 3;
        static final int TRANSACTION_unregisterServer = 42;
        static final int TRANSACTION_unregisterStatisticsClient = 56;
        static final int TRANSACTION_unregisterSync = 20;
        static final int TRANSACTION_writeCharacteristic = 32;
        static final int TRANSACTION_writeDescriptor = 34;

        public Stub()
        {
            attachInterface(this, "android.bluetooth.IBluetoothGatt");
        }
    }

    private static class Stub.Proxy
        implements IBluetoothGatt
    {

        public void addService(int i, BluetoothGattService bluetoothgattservice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            if(bluetoothgattservice == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            bluetoothgattservice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(47, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bluetoothgattservice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothgattservice;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void beginReliableWrite(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(36, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void clearServices(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            mRemote.transact(49, parcel, parcel1, 0);
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

        public void clientConnect(int i, String s, boolean flag, int j, boolean flag1, int k)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(k);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void clientDisconnect(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void clientReadPhy(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void clientSetPreferredPhy(int i, String s, int j, int k, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void configureMTU(int i, String s, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            mRemote.transact(39, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void connectionParameterUpdate(int i, String s, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            mRemote.transact(40, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void disconnectAll()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            mRemote.transact(52, parcel, parcel1, 0);
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

        public void discoverServiceByUuid(int i, String s, ParcelUuid parceluuid)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            if(parceluuid == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            parceluuid.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void discoverServices(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void enableAdvertisingSet(int i, boolean flag, int j, int k)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(12, parcel, parcel1, 0);
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

        public void endReliableWrite(int i, String s, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(37, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void flushPendingBatchResults(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
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

        public List getDevicesMatchingConnectionStates(int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeIntArray(ai);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            ai = parcel1.createTypedArrayList(BluetoothDevice.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return ai;
            ai;
            parcel1.recycle();
            parcel.recycle();
            throw ai;
        }

        public String getInterfaceDescriptor()
        {
            return "android.bluetooth.IBluetoothGatt";
        }

        public void getOwnAddress(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
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

        public int numHwTrackFiltersAvailable()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            mRemote.transact(54, parcel, parcel1, 0);
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

        public void readCharacteristic(int i, String s, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(30, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void readDescriptor(int i, String s, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(33, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void readRemoteRssi(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(38, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void readUsingCharacteristicUuid(int i, String s, ParcelUuid parceluuid, int j, int k, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            if(parceluuid == null)
                break MISSING_BLOCK_LABEL_100;
            parcel.writeInt(1);
            parceluuid.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void refreshDevice(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void registerClient(ParcelUuid parceluuid, IBluetoothGattCallback ibluetoothgattcallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            if(parceluuid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parceluuid.writeToParcel(parcel, 0);
_L4:
            parceluuid = obj;
            if(ibluetoothgattcallback == null)
                break MISSING_BLOCK_LABEL_49;
            parceluuid = ibluetoothgattcallback.asBinder();
            parcel.writeStrongBinder(parceluuid);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            parceluuid;
            parcel1.recycle();
            parcel.recycle();
            throw parceluuid;
        }

        public void registerForNotification(int i, String s, int j, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(35, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void registerScanner(IScannerCallback iscannercallback, WorkSource worksource)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            if(iscannercallback == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = iscannercallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(worksource == null)
                break MISSING_BLOCK_LABEL_85;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            iscannercallback;
            parcel1.recycle();
            parcel.recycle();
            throw iscannercallback;
        }

        public void registerServer(ParcelUuid parceluuid, IBluetoothGattServerCallback ibluetoothgattservercallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            if(parceluuid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parceluuid.writeToParcel(parcel, 0);
_L4:
            parceluuid = obj;
            if(ibluetoothgattservercallback == null)
                break MISSING_BLOCK_LABEL_49;
            parceluuid = ibluetoothgattservercallback.asBinder();
            parcel.writeStrongBinder(parceluuid);
            mRemote.transact(41, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            parceluuid;
            parcel1.recycle();
            parcel.recycle();
            throw parceluuid;
        }

        public void registerStatisticsClient(IScannerCallback iscannercallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            if(iscannercallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iscannercallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(55, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iscannercallback;
            parcel1.recycle();
            parcel.recycle();
            throw iscannercallback;
        }

        public void registerSync(ScanResult scanresult, int i, int j, IPeriodicAdvertisingCallback iperiodicadvertisingcallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            if(scanresult == null)
                break MISSING_BLOCK_LABEL_104;
            parcel.writeInt(1);
            scanresult.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
            scanresult = obj;
            if(iperiodicadvertisingcallback == null)
                break MISSING_BLOCK_LABEL_65;
            scanresult = iperiodicadvertisingcallback.asBinder();
            parcel.writeStrongBinder(scanresult);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            scanresult;
            parcel1.recycle();
            parcel.recycle();
            throw scanresult;
        }

        public void removeService(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(48, parcel, parcel1, 0);
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

        public void sendNotification(int i, String s, int j, boolean flag, byte abyte0[])
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
            mRemote.transact(51, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void sendResponse(int i, String s, int j, int k, int l, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            parcel.writeByteArray(abyte0);
            mRemote.transact(50, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void serverConnect(int i, String s, boolean flag, int j)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(43, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void serverDisconnect(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(44, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void serverReadPhy(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(46, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void serverSetPreferredPhy(int i, String s, int j, int k, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(45, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setAdvertisingData(int i, AdvertiseData advertisedata)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            if(advertisedata == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            advertisedata.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            advertisedata;
            parcel1.recycle();
            parcel.recycle();
            throw advertisedata;
        }

        public void setAdvertisingParameters(int i, AdvertisingSetParameters advertisingsetparameters)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            if(advertisingsetparameters == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            advertisingsetparameters.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            advertisingsetparameters;
            parcel1.recycle();
            parcel.recycle();
            throw advertisingsetparameters;
        }

        public void setPeriodicAdvertisingData(int i, AdvertiseData advertisedata)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            if(advertisedata == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            advertisedata.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            advertisedata;
            parcel1.recycle();
            parcel.recycle();
            throw advertisedata;
        }

        public void setPeriodicAdvertisingEnable(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(18, parcel, parcel1, 0);
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

        public void setPeriodicAdvertisingParameters(int i, PeriodicAdvertisingParameters periodicadvertisingparameters)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            if(periodicadvertisingparameters == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            periodicadvertisingparameters.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            periodicadvertisingparameters;
            parcel1.recycle();
            parcel.recycle();
            throw periodicadvertisingparameters;
        }

        public void setScanResponseData(int i, AdvertiseData advertisedata)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            if(advertisedata == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            advertisedata.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            advertisedata;
            parcel1.recycle();
            parcel.recycle();
            throw advertisedata;
        }

        public void startAdvertisingSet(AdvertisingSetParameters advertisingsetparameters, AdvertiseData advertisedata, AdvertiseData advertisedata1, PeriodicAdvertisingParameters periodicadvertisingparameters, AdvertiseData advertisedata2, int i, int j, 
                IAdvertisingSetCallback iadvertisingsetcallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            if(advertisingsetparameters == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            advertisingsetparameters.writeToParcel(parcel, 0);
_L9:
            if(advertisedata == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            advertisedata.writeToParcel(parcel, 0);
_L10:
            if(advertisedata1 == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            advertisedata1.writeToParcel(parcel, 0);
_L11:
            if(periodicadvertisingparameters == null) goto _L8; else goto _L7
_L7:
            parcel.writeInt(1);
            periodicadvertisingparameters.writeToParcel(parcel, 0);
_L12:
            if(advertisedata2 == null)
                break MISSING_BLOCK_LABEL_227;
            parcel.writeInt(1);
            advertisedata2.writeToParcel(parcel, 0);
_L13:
            parcel.writeInt(i);
            parcel.writeInt(j);
            advertisingsetparameters = obj;
            if(iadvertisingsetcallback == null)
                break MISSING_BLOCK_LABEL_139;
            advertisingsetparameters = iadvertisingsetcallback.asBinder();
            parcel.writeStrongBinder(advertisingsetparameters);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L9
            advertisingsetparameters;
            parcel1.recycle();
            parcel.recycle();
            throw advertisingsetparameters;
_L4:
            parcel.writeInt(0);
              goto _L10
_L6:
            parcel.writeInt(0);
              goto _L11
_L8:
            parcel.writeInt(0);
              goto _L12
            parcel.writeInt(0);
              goto _L13
        }

        public void startScan(int i, ScanSettings scansettings, List list, List list1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            if(scansettings == null)
                break MISSING_BLOCK_LABEL_92;
            parcel.writeInt(1);
            scansettings.writeToParcel(parcel, 0);
_L1:
            parcel.writeTypedList(list);
            parcel.writeList(list1);
            parcel.writeString(s);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            scansettings;
            parcel1.recycle();
            parcel.recycle();
            throw scansettings;
        }

        public void startScanForIntent(PendingIntent pendingintent, ScanSettings scansettings, List list, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            if(pendingintent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L3:
            if(scansettings == null)
                break MISSING_BLOCK_LABEL_118;
            parcel.writeInt(1);
            scansettings.writeToParcel(parcel, 0);
_L4:
            parcel.writeTypedList(list);
            parcel.writeString(s);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            pendingintent;
            parcel1.recycle();
            parcel.recycle();
            throw pendingintent;
            parcel.writeInt(0);
              goto _L4
        }

        public void stopAdvertisingSet(IAdvertisingSetCallback iadvertisingsetcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            if(iadvertisingsetcallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iadvertisingsetcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iadvertisingsetcallback;
            parcel1.recycle();
            parcel.recycle();
            throw iadvertisingsetcallback;
        }

        public void stopScan(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
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

        public void stopScanForIntent(PendingIntent pendingintent, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            pendingintent;
            parcel1.recycle();
            parcel.recycle();
            throw pendingintent;
        }

        public void unregAll()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            mRemote.transact(53, parcel, parcel1, 0);
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

        public void unregisterClient(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            mRemote.transact(22, parcel, parcel1, 0);
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

        public void unregisterScanner(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
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

        public void unregisterServer(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            mRemote.transact(42, parcel, parcel1, 0);
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

        public void unregisterStatisticsClient(IScannerCallback iscannercallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            if(iscannercallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iscannercallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(56, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iscannercallback;
            parcel1.recycle();
            parcel.recycle();
            throw iscannercallback;
        }

        public void unregisterSync(IPeriodicAdvertisingCallback iperiodicadvertisingcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            if(iperiodicadvertisingcallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iperiodicadvertisingcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iperiodicadvertisingcallback;
            parcel1.recycle();
            parcel.recycle();
            throw iperiodicadvertisingcallback;
        }

        public void writeCharacteristic(int i, String s, int j, int k, int l, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            parcel.writeByteArray(abyte0);
            mRemote.transact(32, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void writeDescriptor(int i, String s, int j, int k, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothGatt");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeByteArray(abyte0);
            mRemote.transact(34, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addService(int i, BluetoothGattService bluetoothgattservice)
        throws RemoteException;

    public abstract void beginReliableWrite(int i, String s)
        throws RemoteException;

    public abstract void clearServices(int i)
        throws RemoteException;

    public abstract void clientConnect(int i, String s, boolean flag, int j, boolean flag1, int k)
        throws RemoteException;

    public abstract void clientDisconnect(int i, String s)
        throws RemoteException;

    public abstract void clientReadPhy(int i, String s)
        throws RemoteException;

    public abstract void clientSetPreferredPhy(int i, String s, int j, int k, int l)
        throws RemoteException;

    public abstract void configureMTU(int i, String s, int j)
        throws RemoteException;

    public abstract void connectionParameterUpdate(int i, String s, int j)
        throws RemoteException;

    public abstract void disconnectAll()
        throws RemoteException;

    public abstract void discoverServiceByUuid(int i, String s, ParcelUuid parceluuid)
        throws RemoteException;

    public abstract void discoverServices(int i, String s)
        throws RemoteException;

    public abstract void enableAdvertisingSet(int i, boolean flag, int j, int k)
        throws RemoteException;

    public abstract void endReliableWrite(int i, String s, boolean flag)
        throws RemoteException;

    public abstract void flushPendingBatchResults(int i)
        throws RemoteException;

    public abstract List getDevicesMatchingConnectionStates(int ai[])
        throws RemoteException;

    public abstract void getOwnAddress(int i)
        throws RemoteException;

    public abstract int numHwTrackFiltersAvailable()
        throws RemoteException;

    public abstract void readCharacteristic(int i, String s, int j, int k)
        throws RemoteException;

    public abstract void readDescriptor(int i, String s, int j, int k)
        throws RemoteException;

    public abstract void readRemoteRssi(int i, String s)
        throws RemoteException;

    public abstract void readUsingCharacteristicUuid(int i, String s, ParcelUuid parceluuid, int j, int k, int l)
        throws RemoteException;

    public abstract void refreshDevice(int i, String s)
        throws RemoteException;

    public abstract void registerClient(ParcelUuid parceluuid, IBluetoothGattCallback ibluetoothgattcallback)
        throws RemoteException;

    public abstract void registerForNotification(int i, String s, int j, boolean flag)
        throws RemoteException;

    public abstract void registerScanner(IScannerCallback iscannercallback, WorkSource worksource)
        throws RemoteException;

    public abstract void registerServer(ParcelUuid parceluuid, IBluetoothGattServerCallback ibluetoothgattservercallback)
        throws RemoteException;

    public abstract void registerStatisticsClient(IScannerCallback iscannercallback)
        throws RemoteException;

    public abstract void registerSync(ScanResult scanresult, int i, int j, IPeriodicAdvertisingCallback iperiodicadvertisingcallback)
        throws RemoteException;

    public abstract void removeService(int i, int j)
        throws RemoteException;

    public abstract void sendNotification(int i, String s, int j, boolean flag, byte abyte0[])
        throws RemoteException;

    public abstract void sendResponse(int i, String s, int j, int k, int l, byte abyte0[])
        throws RemoteException;

    public abstract void serverConnect(int i, String s, boolean flag, int j)
        throws RemoteException;

    public abstract void serverDisconnect(int i, String s)
        throws RemoteException;

    public abstract void serverReadPhy(int i, String s)
        throws RemoteException;

    public abstract void serverSetPreferredPhy(int i, String s, int j, int k, int l)
        throws RemoteException;

    public abstract void setAdvertisingData(int i, AdvertiseData advertisedata)
        throws RemoteException;

    public abstract void setAdvertisingParameters(int i, AdvertisingSetParameters advertisingsetparameters)
        throws RemoteException;

    public abstract void setPeriodicAdvertisingData(int i, AdvertiseData advertisedata)
        throws RemoteException;

    public abstract void setPeriodicAdvertisingEnable(int i, boolean flag)
        throws RemoteException;

    public abstract void setPeriodicAdvertisingParameters(int i, PeriodicAdvertisingParameters periodicadvertisingparameters)
        throws RemoteException;

    public abstract void setScanResponseData(int i, AdvertiseData advertisedata)
        throws RemoteException;

    public abstract void startAdvertisingSet(AdvertisingSetParameters advertisingsetparameters, AdvertiseData advertisedata, AdvertiseData advertisedata1, PeriodicAdvertisingParameters periodicadvertisingparameters, AdvertiseData advertisedata2, int i, int j, 
            IAdvertisingSetCallback iadvertisingsetcallback)
        throws RemoteException;

    public abstract void startScan(int i, ScanSettings scansettings, List list, List list1, String s)
        throws RemoteException;

    public abstract void startScanForIntent(PendingIntent pendingintent, ScanSettings scansettings, List list, String s)
        throws RemoteException;

    public abstract void stopAdvertisingSet(IAdvertisingSetCallback iadvertisingsetcallback)
        throws RemoteException;

    public abstract void stopScan(int i)
        throws RemoteException;

    public abstract void stopScanForIntent(PendingIntent pendingintent, String s)
        throws RemoteException;

    public abstract void unregAll()
        throws RemoteException;

    public abstract void unregisterClient(int i)
        throws RemoteException;

    public abstract void unregisterScanner(int i)
        throws RemoteException;

    public abstract void unregisterServer(int i)
        throws RemoteException;

    public abstract void unregisterStatisticsClient(IScannerCallback iscannercallback)
        throws RemoteException;

    public abstract void unregisterSync(IPeriodicAdvertisingCallback iperiodicadvertisingcallback)
        throws RemoteException;

    public abstract void writeCharacteristic(int i, String s, int j, int k, int l, byte abyte0[])
        throws RemoteException;

    public abstract void writeDescriptor(int i, String s, int j, int k, byte abyte0[])
        throws RemoteException;
}
