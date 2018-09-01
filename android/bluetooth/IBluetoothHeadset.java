// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;
import java.util.List;

// Referenced classes of package android.bluetooth:
//            BluetoothDevice

public interface IBluetoothHeadset
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBluetoothHeadset
    {

        public static IBluetoothHeadset asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.bluetooth.IBluetoothHeadset");
            if(iinterface != null && (iinterface instanceof IBluetoothHeadset))
                return (IBluetoothHeadset)iinterface;
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
                parcel1.writeString("android.bluetooth.IBluetoothHeadset");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                boolean flag;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag = connect(parcel);
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                boolean flag1;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag1 = disconnect(parcel);
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                parcel = getConnectedDevices();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                parcel = getDevicesMatchingConnectionStates(parcel.createIntArray());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getConnectionState(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                boolean flag2;
                BluetoothDevice bluetoothdevice;
                if(parcel.readInt() != 0)
                    bluetoothdevice = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice = null;
                flag2 = setPriority(bluetoothdevice, parcel.readInt());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getPriority(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                boolean flag3;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag3 = startVoiceRecognition(parcel);
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                boolean flag4;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag4 = stopVoiceRecognition(parcel);
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                boolean flag5;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag5 = isAudioConnected(parcel);
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                boolean flag6;
                BluetoothDevice bluetoothdevice1;
                if(parcel.readInt() != 0)
                    bluetoothdevice1 = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    bluetoothdevice1 = null;
                flag6 = sendVendorSpecificResultCode(bluetoothdevice1, parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getBatteryUsageHint(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                boolean flag7;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag7 = acceptIncomingConnect(parcel);
                parcel1.writeNoException();
                if(flag7)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                boolean flag8;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag8 = rejectIncomingConnect(parcel);
                parcel1.writeNoException();
                if(flag8)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getAudioState(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                boolean flag9 = isAudioOn();
                parcel1.writeNoException();
                if(flag9)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                boolean flag10 = connectAudio();
                parcel1.writeNoException();
                if(flag10)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                boolean flag11 = disconnectAudio();
                parcel1.writeNoException();
                if(flag11)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                boolean flag12;
                if(parcel.readInt() != 0)
                    flag12 = true;
                else
                    flag12 = false;
                setAudioRouteAllowed(flag12);
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                boolean flag13 = getAudioRouteAllowed();
                parcel1.writeNoException();
                if(flag13)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                boolean flag14;
                if(parcel.readInt() != 0)
                    flag14 = true;
                else
                    flag14 = false;
                setForceScoAudio(flag14);
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                boolean flag15;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag15 = startScoUsingVirtualVoiceCall(parcel);
                parcel1.writeNoException();
                if(flag15)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                boolean flag16;
                if(parcel.readInt() != 0)
                    parcel = (BluetoothDevice)BluetoothDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag16 = stopScoUsingVirtualVoiceCall(parcel);
                parcel1.writeNoException();
                if(flag16)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                phoneStateChanged(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt());
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                i = parcel.readInt();
                int k = parcel.readInt();
                int l = parcel.readInt();
                j = parcel.readInt();
                boolean flag17;
                if(parcel.readInt() != 0)
                    flag17 = true;
                else
                    flag17 = false;
                clccResponse(i, k, l, j, flag17, parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                boolean flag18 = enableWBS();
                parcel1.writeNoException();
                if(flag18)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                boolean flag19 = disableWBS();
                parcel1.writeNoException();
                if(flag19)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadset");
                i = parcel.readInt();
                break;
            }
            boolean flag20;
            if(parcel.readInt() != 0)
                flag20 = true;
            else
                flag20 = false;
            bindResponse(i, flag20);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothHeadset";
        static final int TRANSACTION_acceptIncomingConnect = 13;
        static final int TRANSACTION_bindResponse = 28;
        static final int TRANSACTION_clccResponse = 25;
        static final int TRANSACTION_connect = 1;
        static final int TRANSACTION_connectAudio = 17;
        static final int TRANSACTION_disableWBS = 27;
        static final int TRANSACTION_disconnect = 2;
        static final int TRANSACTION_disconnectAudio = 18;
        static final int TRANSACTION_enableWBS = 26;
        static final int TRANSACTION_getAudioRouteAllowed = 20;
        static final int TRANSACTION_getAudioState = 15;
        static final int TRANSACTION_getBatteryUsageHint = 12;
        static final int TRANSACTION_getConnectedDevices = 3;
        static final int TRANSACTION_getConnectionState = 5;
        static final int TRANSACTION_getDevicesMatchingConnectionStates = 4;
        static final int TRANSACTION_getPriority = 7;
        static final int TRANSACTION_isAudioConnected = 10;
        static final int TRANSACTION_isAudioOn = 16;
        static final int TRANSACTION_phoneStateChanged = 24;
        static final int TRANSACTION_rejectIncomingConnect = 14;
        static final int TRANSACTION_sendVendorSpecificResultCode = 11;
        static final int TRANSACTION_setAudioRouteAllowed = 19;
        static final int TRANSACTION_setForceScoAudio = 21;
        static final int TRANSACTION_setPriority = 6;
        static final int TRANSACTION_startScoUsingVirtualVoiceCall = 22;
        static final int TRANSACTION_startVoiceRecognition = 8;
        static final int TRANSACTION_stopScoUsingVirtualVoiceCall = 23;
        static final int TRANSACTION_stopVoiceRecognition = 9;

        public Stub()
        {
            attachInterface(this, "android.bluetooth.IBluetoothHeadset");
        }
    }

    private static class Stub.Proxy
        implements IBluetoothHeadset
    {

        public boolean acceptIncomingConnect(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
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
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void bindResponse(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(28, parcel, parcel1, 0);
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

        public void clccResponse(int i, int j, int k, int l, boolean flag, String s, int i1)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(i1);
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

        public boolean connect(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_72;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
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
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean connectAudio()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            mRemote.transact(17, parcel, parcel1, 0);
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

        public boolean disableWBS()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            mRemote.transact(27, parcel, parcel1, 0);
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

        public boolean disconnect(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_72;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(2, parcel, parcel1, 0);
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

        public boolean disconnectAudio()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            mRemote.transact(18, parcel, parcel1, 0);
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

        public boolean enableWBS()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean getAudioRouteAllowed()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            mRemote.transact(20, parcel, parcel1, 0);
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

        public int getAudioState(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(15, parcel, parcel1, 0);
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

        public int getBatteryUsageHint(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(12, parcel, parcel1, 0);
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

        public List getConnectedDevices()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(BluetoothDevice.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
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
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_64;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(5, parcel, parcel1, 0);
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

        public List getDevicesMatchingConnectionStates(int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            parcel.writeIntArray(ai);
            mRemote.transact(4, parcel, parcel1, 0);
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
            return "android.bluetooth.IBluetoothHeadset";
        }

        public int getPriority(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(7, parcel, parcel1, 0);
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

        public boolean isAudioConnected(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(10, parcel, parcel1, 0);
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

        public boolean isAudioOn()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
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

        public void phoneStateChanged(int i, int j, int k, String s, int l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeString(s);
            parcel.writeInt(l);
            mRemote.transact(24, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public boolean rejectIncomingConnect(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
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
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean sendVendorSpecificResultCode(BluetoothDevice bluetoothdevice, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
            parcel.writeString(s1);
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
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public void setAudioRouteAllowed(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(19, parcel, parcel1, 0);
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

        public void setForceScoAudio(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(21, parcel, parcel1, 0);
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

        public boolean setPriority(BluetoothDevice bluetoothdevice, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_81;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
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

        public boolean startScoUsingVirtualVoiceCall(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
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
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean startVoiceRecognition(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
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
            parcel.writeInt(0);
              goto _L1
            bluetoothdevice;
            parcel1.recycle();
            parcel.recycle();
            throw bluetoothdevice;
        }

        public boolean stopScoUsingVirtualVoiceCall(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
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

        public boolean stopVoiceRecognition(BluetoothDevice bluetoothdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadset");
            if(bluetoothdevice == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            bluetoothdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(9, parcel, parcel1, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean acceptIncomingConnect(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract void bindResponse(int i, boolean flag)
        throws RemoteException;

    public abstract void clccResponse(int i, int j, int k, int l, boolean flag, String s, int i1)
        throws RemoteException;

    public abstract boolean connect(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract boolean connectAudio()
        throws RemoteException;

    public abstract boolean disableWBS()
        throws RemoteException;

    public abstract boolean disconnect(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract boolean disconnectAudio()
        throws RemoteException;

    public abstract boolean enableWBS()
        throws RemoteException;

    public abstract boolean getAudioRouteAllowed()
        throws RemoteException;

    public abstract int getAudioState(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract int getBatteryUsageHint(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract List getConnectedDevices()
        throws RemoteException;

    public abstract int getConnectionState(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract List getDevicesMatchingConnectionStates(int ai[])
        throws RemoteException;

    public abstract int getPriority(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract boolean isAudioConnected(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract boolean isAudioOn()
        throws RemoteException;

    public abstract void phoneStateChanged(int i, int j, int k, String s, int l)
        throws RemoteException;

    public abstract boolean rejectIncomingConnect(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract boolean sendVendorSpecificResultCode(BluetoothDevice bluetoothdevice, String s, String s1)
        throws RemoteException;

    public abstract void setAudioRouteAllowed(boolean flag)
        throws RemoteException;

    public abstract void setForceScoAudio(boolean flag)
        throws RemoteException;

    public abstract boolean setPriority(BluetoothDevice bluetoothdevice, int i)
        throws RemoteException;

    public abstract boolean startScoUsingVirtualVoiceCall(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract boolean startVoiceRecognition(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract boolean stopScoUsingVirtualVoiceCall(BluetoothDevice bluetoothdevice)
        throws RemoteException;

    public abstract boolean stopVoiceRecognition(BluetoothDevice bluetoothdevice)
        throws RemoteException;
}
