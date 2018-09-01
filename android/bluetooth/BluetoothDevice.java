// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.content.Context;
import android.os.*;
import android.util.Log;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

// Referenced classes of package android.bluetooth:
//            BluetoothAdapter, IBluetooth, IBluetoothManager, BluetoothGatt, 
//            BluetoothSocket, BluetoothClass, IBluetoothManagerCallback, BluetoothGattCallback, 
//            OobData

public final class BluetoothDevice
    implements Parcelable
{

    static IBluetooth _2D_get0()
    {
        return sService;
    }

    static IBluetooth _2D_set0(IBluetooth ibluetooth)
    {
        sService = ibluetooth;
        return ibluetooth;
    }

    BluetoothDevice(String s)
    {
        getService();
        if(!BluetoothAdapter.checkBluetoothAddress(s))
        {
            throw new IllegalArgumentException((new StringBuilder()).append(s).append(" is not a valid Bluetooth address").toString());
        } else
        {
            mAddress = s;
            return;
        }
    }

    public static byte[] convertPinToBytes(String s)
    {
        if(s == null)
            return null;
        try
        {
            s = s.getBytes("UTF-8");
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("BluetoothDevice", "UTF-8 not supported?!?");
            return null;
        }
        if(s.length <= 0 || s.length > 16)
            return null;
        else
            return s;
    }

    static IBluetooth getService()
    {
        android/bluetooth/BluetoothDevice;
        JVM INSTR monitorenter ;
        if(sService == null)
            sService = BluetoothAdapter.getDefaultAdapter().getBluetoothService(mStateChangeCallback);
        android/bluetooth/BluetoothDevice;
        JVM INSTR monitorexit ;
        return sService;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean cancelBondProcess()
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
        {
            Log.e("BluetoothDevice", "BT not enabled. Cannot cancel Remote Device bond");
            return false;
        }
        boolean flag;
        try
        {
            StringBuilder stringbuilder = JVM INSTR new #268 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.i("BluetoothDevice", stringbuilder.append("cancelBondProcess() for device ").append(getAddress()).append(" called by pid: ").append(Process.myPid()).append(" tid: ").append(Process.myTid()).toString());
            flag = ibluetooth.cancelBondProcess(this);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return false;
        }
        return flag;
    }

    public boolean cancelPairingUserInput()
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
        {
            Log.e("BluetoothDevice", "BT not enabled. Cannot create pairing user input");
            return false;
        }
        boolean flag;
        try
        {
            flag = ibluetooth.cancelBondProcess(this);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return false;
        }
        return flag;
    }

    public BluetoothGatt connectGatt(Context context, boolean flag, BluetoothGattCallback bluetoothgattcallback)
    {
        return connectGatt(context, flag, bluetoothgattcallback, 0);
    }

    public BluetoothGatt connectGatt(Context context, boolean flag, BluetoothGattCallback bluetoothgattcallback, int i)
    {
        return connectGatt(context, flag, bluetoothgattcallback, i, 1);
    }

    public BluetoothGatt connectGatt(Context context, boolean flag, BluetoothGattCallback bluetoothgattcallback, int i, int j)
    {
        return connectGatt(context, flag, bluetoothgattcallback, i, j, null);
    }

    public BluetoothGatt connectGatt(Context context, boolean flag, BluetoothGattCallback bluetoothgattcallback, int i, int j, Handler handler)
    {
        return connectGatt(context, flag, bluetoothgattcallback, i, false, j, handler);
    }

    public BluetoothGatt connectGatt(Context context, boolean flag, BluetoothGattCallback bluetoothgattcallback, int i, boolean flag1, int j, Handler handler)
    {
        if(bluetoothgattcallback == null)
            throw new NullPointerException("callback is null");
        context = BluetoothAdapter.getDefaultAdapter().getBluetoothManager();
        BluetoothGatt bluetoothgatt;
        try
        {
            context = context.getBluetoothGatt();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("BluetoothDevice", "", context);
            return null;
        }
        if(context == null)
            return null;
        bluetoothgatt = JVM INSTR new #384 <Class BluetoothGatt>;
        bluetoothgatt.BluetoothGatt(context, this, i, flag1, j);
        bluetoothgatt.connect(Boolean.valueOf(flag), bluetoothgattcallback, handler);
        return bluetoothgatt;
    }

    public boolean createBond()
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
        {
            Log.e("BluetoothDevice", "BT not enabled. Cannot create bond to Remote Device");
            return false;
        }
        boolean flag;
        try
        {
            StringBuilder stringbuilder = JVM INSTR new #268 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.i("BluetoothDevice", stringbuilder.append("createBond() for device ").append(getAddress()).append(" called by pid: ").append(Process.myPid()).append(" tid: ").append(Process.myTid()).toString());
            flag = ibluetooth.createBond(this, 0);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return false;
        }
        return flag;
    }

    public boolean createBond(int i)
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
        {
            Log.e("BluetoothDevice", "BT not enabled. Cannot create bond to Remote Device");
            return false;
        }
        if(i < 0 || i > 2)
            throw new IllegalArgumentException((new StringBuilder()).append(i).append(" is not a valid Bluetooth transport").toString());
        boolean flag;
        try
        {
            StringBuilder stringbuilder = JVM INSTR new #268 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.i("BluetoothDevice", stringbuilder.append("createBond() for device ").append(getAddress()).append(" called by pid: ").append(Process.myPid()).append(" tid: ").append(Process.myTid()).toString());
            flag = ibluetooth.createBond(this, i);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return false;
        }
        return flag;
    }

    public boolean createBondOutOfBand(int i, OobData oobdata)
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
        {
            Log.w("BluetoothDevice", "BT not enabled, createBondOutOfBand failed");
            return false;
        }
        boolean flag;
        try
        {
            flag = ibluetooth.createBondOutOfBand(this, i, oobdata);
        }
        // Misplaced declaration of an exception variable
        catch(OobData oobdata)
        {
            Log.e("BluetoothDevice", "", oobdata);
            return false;
        }
        return flag;
    }

    public BluetoothSocket createInsecureL2capSocket(int i)
        throws IOException
    {
        return new BluetoothSocket(3, -1, false, false, this, i, null);
    }

    public BluetoothSocket createInsecureRfcommSocket(int i)
        throws IOException
    {
        if(!isBluetoothEnabled())
        {
            Log.e("BluetoothDevice", "Bluetooth is not enabled");
            throw new IOException();
        } else
        {
            return new BluetoothSocket(1, -1, false, false, this, i, null);
        }
    }

    public BluetoothSocket createInsecureRfcommSocketToServiceRecord(UUID uuid)
        throws IOException
    {
        if(!isBluetoothEnabled())
        {
            Log.e("BluetoothDevice", "Bluetooth is not enabled");
            throw new IOException();
        } else
        {
            return new BluetoothSocket(1, -1, false, false, this, -1, new ParcelUuid(uuid));
        }
    }

    public BluetoothSocket createL2capSocket(int i)
        throws IOException
    {
        return new BluetoothSocket(3, -1, true, true, this, i, null);
    }

    public BluetoothSocket createRfcommSocket(int i)
        throws IOException
    {
        if(!isBluetoothEnabled())
        {
            Log.e("BluetoothDevice", "Bluetooth is not enabled");
            throw new IOException();
        } else
        {
            return new BluetoothSocket(1, -1, true, true, this, i, null);
        }
    }

    public BluetoothSocket createRfcommSocketToServiceRecord(UUID uuid)
        throws IOException
    {
        if(!isBluetoothEnabled())
        {
            Log.e("BluetoothDevice", "Bluetooth is not enabled");
            throw new IOException();
        } else
        {
            return new BluetoothSocket(1, -1, true, true, this, -1, new ParcelUuid(uuid));
        }
    }

    public BluetoothSocket createScoSocket()
        throws IOException
    {
        if(!isBluetoothEnabled())
        {
            Log.e("BluetoothDevice", "Bluetooth is not enabled");
            throw new IOException();
        } else
        {
            return new BluetoothSocket(2, -1, true, true, this, -1, null);
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof BluetoothDevice)
            return mAddress.equals(((BluetoothDevice)obj).getAddress());
        else
            return false;
    }

    public boolean fetchUuidsWithSdp()
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null || isBluetoothEnabled() ^ true)
        {
            Log.e("BluetoothDevice", "BT not enabled. Cannot fetchUuidsWithSdp");
            return false;
        }
        boolean flag;
        try
        {
            flag = ibluetooth.fetchRemoteUuids(this);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return false;
        }
        return flag;
    }

    public String getAddress()
    {
        return mAddress;
    }

    public String getAlias()
    {
        Object obj = sService;
        if(obj == null)
        {
            Log.e("BluetoothDevice", "BT not enabled. Cannot get Remote Device Alias");
            return null;
        }
        try
        {
            obj = ((IBluetooth) (obj)).getRemoteAlias(this);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return null;
        }
        return ((String) (obj));
    }

    public String getAliasName()
    {
        String s = getAlias();
        String s1 = s;
        if(s == null)
            s1 = getName();
        return s1;
    }

    public int getBatteryLevel()
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
        {
            Log.e("BluetoothDevice", "Bluetooth disabled. Cannot get remote device battery level");
            return -1;
        }
        int i;
        try
        {
            i = ibluetooth.getBatteryLevel(this);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return -1;
        }
        return i;
    }

    public BluetoothClass getBluetoothClass()
    {
        Object obj = sService;
        if(obj == null)
        {
            Log.e("BluetoothDevice", "BT not enabled. Cannot get Bluetooth Class");
            return null;
        }
        int i;
        try
        {
            i = ((IBluetooth) (obj)).getRemoteClass(this);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return null;
        }
        if(i == 0xff000000)
            return null;
        obj = new BluetoothClass(i);
        return ((BluetoothClass) (obj));
    }

    public int getBondState()
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
        {
            Log.e("BluetoothDevice", "BT not enabled. Cannot get bond state");
            return 10;
        }
        int i;
        try
        {
            i = ibluetooth.getBondState(this);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return 10;
        }
        return i;
    }

    public int getMessageAccessPermission()
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
            return 0;
        int i;
        try
        {
            i = ibluetooth.getMessageAccessPermission(this);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return 0;
        }
        return i;
    }

    public String getName()
    {
        Object obj = sService;
        if(obj == null)
        {
            Log.e("BluetoothDevice", "BT not enabled. Cannot get Remote Device name");
            return null;
        }
        try
        {
            obj = ((IBluetooth) (obj)).getRemoteName(this);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return null;
        }
        if(obj == null)
            break MISSING_BLOCK_LABEL_44;
        obj = ((String) (obj)).replaceAll("[\\t\\n\\r]+", " ");
        return ((String) (obj));
        return null;
    }

    public int getPhonebookAccessPermission()
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
            return 0;
        int i;
        try
        {
            i = ibluetooth.getPhonebookAccessPermission(this);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return 0;
        }
        return i;
    }

    public int getSimAccessPermission()
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
            return 0;
        int i;
        try
        {
            i = ibluetooth.getSimAccessPermission(this);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return 0;
        }
        return i;
    }

    public int getType()
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
        {
            Log.e("BluetoothDevice", "BT not enabled. Cannot get Remote Device type");
            return 0;
        }
        int i;
        try
        {
            i = ibluetooth.getRemoteType(this);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return 0;
        }
        return i;
    }

    public ParcelUuid[] getUuids()
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null || isBluetoothEnabled() ^ true)
        {
            Log.e("BluetoothDevice", "BT not enabled. Cannot get remote device Uuids");
            return null;
        }
        ParcelUuid aparceluuid[];
        try
        {
            aparceluuid = ibluetooth.getRemoteUuids(this);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return null;
        }
        return aparceluuid;
    }

    public int hashCode()
    {
        return mAddress.hashCode();
    }

    public boolean isBluetoothDock()
    {
        return false;
    }

    boolean isBluetoothEnabled()
    {
        boolean flag = false;
        BluetoothAdapter bluetoothadapter = BluetoothAdapter.getDefaultAdapter();
        boolean flag1 = flag;
        if(bluetoothadapter != null)
        {
            flag1 = flag;
            if(bluetoothadapter.isEnabled())
                flag1 = true;
        }
        return flag1;
    }

    public boolean isBondingInitiatedLocally()
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
        {
            Log.w("BluetoothDevice", "BT not enabled, isBondingInitiatedLocally failed");
            return false;
        }
        boolean flag;
        try
        {
            flag = ibluetooth.isBondingInitiatedLocally(this);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return false;
        }
        return flag;
    }

    public boolean isConnected()
    {
        boolean flag = false;
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
            return false;
        int i;
        try
        {
            i = ibluetooth.getConnectionState(this);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return false;
        }
        if(i != 0)
            flag = true;
        return flag;
    }

    public boolean isEncrypted()
    {
        boolean flag = true;
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
            return false;
        int i;
        try
        {
            i = ibluetooth.getConnectionState(this);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return false;
        }
        if(i <= 1)
            flag = false;
        return flag;
    }

    public boolean removeBond()
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
        {
            Log.e("BluetoothDevice", "BT not enabled. Cannot remove Remote Device bond");
            return false;
        }
        boolean flag;
        try
        {
            StringBuilder stringbuilder = JVM INSTR new #268 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.i("BluetoothDevice", stringbuilder.append("removeBond() for device ").append(getAddress()).append(" called by pid: ").append(Process.myPid()).append(" tid: ").append(Process.myTid()).toString());
            flag = ibluetooth.removeBond(this);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return false;
        }
        return flag;
    }

    public boolean sdpSearch(ParcelUuid parceluuid)
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
        {
            Log.e("BluetoothDevice", "BT not enabled. Cannot query remote device sdp records");
            return false;
        }
        boolean flag;
        try
        {
            flag = ibluetooth.sdpSearch(this, parceluuid);
        }
        // Misplaced declaration of an exception variable
        catch(ParcelUuid parceluuid)
        {
            Log.e("BluetoothDevice", "", parceluuid);
            return false;
        }
        return flag;
    }

    public boolean setAlias(String s)
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
        {
            Log.e("BluetoothDevice", "BT not enabled. Cannot set Remote Device name");
            return false;
        }
        boolean flag;
        try
        {
            flag = ibluetooth.setRemoteAlias(this, s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("BluetoothDevice", "", s);
            return false;
        }
        return flag;
    }

    public boolean setDeviceOutOfBandData(byte abyte0[], byte abyte1[])
    {
        return false;
    }

    public boolean setMessageAccessPermission(int i)
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
            return false;
        boolean flag;
        try
        {
            flag = ibluetooth.setMessageAccessPermission(this, i);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return false;
        }
        return flag;
    }

    public boolean setPairingConfirmation(boolean flag)
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
        {
            Log.e("BluetoothDevice", "BT not enabled. Cannot set pairing confirmation");
            return false;
        }
        try
        {
            flag = ibluetooth.setPairingConfirmation(this, flag);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return false;
        }
        return flag;
    }

    public boolean setPasskey(int i)
    {
        return false;
    }

    public boolean setPhonebookAccessPermission(int i)
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
            return false;
        boolean flag;
        try
        {
            flag = ibluetooth.setPhonebookAccessPermission(this, i);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return false;
        }
        return flag;
    }

    public boolean setPin(byte abyte0[])
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
        {
            Log.e("BluetoothDevice", "BT not enabled. Cannot set Remote Device pin");
            return false;
        }
        boolean flag;
        try
        {
            flag = ibluetooth.setPin(this, true, abyte0.length, abyte0);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Log.e("BluetoothDevice", "", abyte0);
            return false;
        }
        return flag;
    }

    public boolean setRemoteOutOfBandData()
    {
        return false;
    }

    public boolean setSimAccessPermission(int i)
    {
        IBluetooth ibluetooth = sService;
        if(ibluetooth == null)
            return false;
        boolean flag;
        try
        {
            flag = ibluetooth.setSimAccessPermission(this, i);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("BluetoothDevice", "", remoteexception);
            return false;
        }
        return flag;
    }

    public String toString()
    {
        return mAddress;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mAddress);
    }

    public static final int ACCESS_ALLOWED = 1;
    public static final int ACCESS_REJECTED = 2;
    public static final int ACCESS_UNKNOWN = 0;
    public static final String ACTION_ACL_CONNECTED = "android.bluetooth.device.action.ACL_CONNECTED";
    public static final String ACTION_ACL_DISCONNECTED = "android.bluetooth.device.action.ACL_DISCONNECTED";
    public static final String ACTION_ACL_DISCONNECT_REQUESTED = "android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED";
    public static final String ACTION_ALIAS_CHANGED = "android.bluetooth.device.action.ALIAS_CHANGED";
    public static final String ACTION_BATTERY_LEVEL_CHANGED = "android.bluetooth.device.action.BATTERY_LEVEL_CHANGED";
    public static final String ACTION_BOND_STATE_CHANGED = "android.bluetooth.device.action.BOND_STATE_CHANGED";
    public static final String ACTION_CLASS_CHANGED = "android.bluetooth.device.action.CLASS_CHANGED";
    public static final String ACTION_CONNECTION_ACCESS_CANCEL = "android.bluetooth.device.action.CONNECTION_ACCESS_CANCEL";
    public static final String ACTION_CONNECTION_ACCESS_REPLY = "android.bluetooth.device.action.CONNECTION_ACCESS_REPLY";
    public static final String ACTION_CONNECTION_ACCESS_REQUEST = "android.bluetooth.device.action.CONNECTION_ACCESS_REQUEST";
    public static final String ACTION_DISAPPEARED = "android.bluetooth.device.action.DISAPPEARED";
    public static final String ACTION_FOUND = "android.bluetooth.device.action.FOUND";
    public static final String ACTION_MAS_INSTANCE = "android.bluetooth.device.action.MAS_INSTANCE";
    public static final String ACTION_NAME_CHANGED = "android.bluetooth.device.action.NAME_CHANGED";
    public static final String ACTION_NAME_FAILED = "android.bluetooth.device.action.NAME_FAILED";
    public static final String ACTION_PAIRING_CANCEL = "android.bluetooth.device.action.PAIRING_CANCEL";
    public static final String ACTION_PAIRING_REQUEST = "android.bluetooth.device.action.PAIRING_REQUEST";
    public static final String ACTION_REMOTE_ISSUE_OCCURRED = "org.codeaurora.intent.bluetooth.action.REMOTE_ISSUE_OCCURRED";
    public static final String ACTION_SDP_RECORD = "android.bluetooth.device.action.SDP_RECORD";
    public static final String ACTION_UUID = "android.bluetooth.device.action.UUID";
    public static final int BATTERY_LEVEL_UNKNOWN = -1;
    public static final int BOND_BONDED = 12;
    public static final int BOND_BONDING = 11;
    public static final int BOND_NONE = 10;
    public static final int BOND_SUCCESS = 0;
    public static final int CONNECTION_ACCESS_NO = 2;
    public static final int CONNECTION_ACCESS_YES = 1;
    private static final int CONNECTION_STATE_CONNECTED = 1;
    private static final int CONNECTION_STATE_DISCONNECTED = 0;
    private static final int CONNECTION_STATE_ENCRYPTED_BREDR = 2;
    private static final int CONNECTION_STATE_ENCRYPTED_LE = 4;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BluetoothDevice createFromParcel(Parcel parcel)
        {
            return new BluetoothDevice(parcel.readString());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BluetoothDevice[] newArray(int i)
        {
            return new BluetoothDevice[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DBG = false;
    public static final int DEVICE_TYPE_CLASSIC = 1;
    public static final int DEVICE_TYPE_DUAL = 3;
    public static final int DEVICE_TYPE_LE = 2;
    public static final int DEVICE_TYPE_UNKNOWN = 0;
    public static final int ERROR = 0x80000000;
    public static final String EXTRA_ACCESS_REQUEST_TYPE = "android.bluetooth.device.extra.ACCESS_REQUEST_TYPE";
    public static final String EXTRA_ALWAYS_ALLOWED = "android.bluetooth.device.extra.ALWAYS_ALLOWED";
    public static final String EXTRA_BATTERY_LEVEL = "android.bluetooth.device.extra.BATTERY_LEVEL";
    public static final String EXTRA_BOND_STATE = "android.bluetooth.device.extra.BOND_STATE";
    public static final String EXTRA_CLASS = "android.bluetooth.device.extra.CLASS";
    public static final String EXTRA_CLASS_NAME = "android.bluetooth.device.extra.CLASS_NAME";
    public static final String EXTRA_CONNECTION_ACCESS_RESULT = "android.bluetooth.device.extra.CONNECTION_ACCESS_RESULT";
    public static final String EXTRA_DEVICE = "android.bluetooth.device.extra.DEVICE";
    public static final String EXTRA_ERROR_CODE = "android.bluetooth.qti.extra.ERROR_CODE";
    public static final String EXTRA_ERROR_EVENT_MASK = "android.bluetooth.qti.extra.ERROR_EVENT_MASK";
    public static final String EXTRA_GLITCH_COUNT = "android.bluetooth.qti.extra.EXTRA_GLITCH_COUNT";
    public static final String EXTRA_ISSUE_TYPE = "android.bluetooth.qti.extra.ERROR_TYPE";
    public static final String EXTRA_LINK_QUALITY = "android.bluetooth.qti.extra.EXTRA_LINK_QUALITY";
    public static final String EXTRA_LMP_SUBVER = "android.bluetooth.qti.extra.EXTRA_LMP_SUBVER";
    public static final String EXTRA_LMP_VERSION = "android.bluetooth.qti.extra.EXTRA_LMP_VERSION";
    public static final String EXTRA_MANUFACTURER = "android.bluetooth.qti.extra.EXTRA_MANUFACTURER";
    public static final String EXTRA_MAS_INSTANCE = "android.bluetooth.device.extra.MAS_INSTANCE";
    public static final String EXTRA_NAME = "android.bluetooth.device.extra.NAME";
    public static final String EXTRA_PACKAGE_NAME = "android.bluetooth.device.extra.PACKAGE_NAME";
    public static final String EXTRA_PAIRING_KEY = "android.bluetooth.device.extra.PAIRING_KEY";
    public static final String EXTRA_PAIRING_VARIANT = "android.bluetooth.device.extra.PAIRING_VARIANT";
    public static final String EXTRA_POWER_LEVEL = "android.bluetooth.qti.extra.EXTRA_POWER_LEVEL";
    public static final String EXTRA_PREVIOUS_BOND_STATE = "android.bluetooth.device.extra.PREVIOUS_BOND_STATE";
    public static final String EXTRA_REASON = "android.bluetooth.device.extra.REASON";
    public static final String EXTRA_RSSI = "android.bluetooth.device.extra.RSSI";
    public static final String EXTRA_SDP_RECORD = "android.bluetooth.device.extra.SDP_RECORD";
    public static final String EXTRA_SDP_SEARCH_STATUS = "android.bluetooth.device.extra.SDP_SEARCH_STATUS";
    public static final String EXTRA_UUID = "android.bluetooth.device.extra.UUID";
    public static final int PAIRING_VARIANT_CONSENT = 3;
    public static final int PAIRING_VARIANT_DISPLAY_PASSKEY = 4;
    public static final int PAIRING_VARIANT_DISPLAY_PIN = 5;
    public static final int PAIRING_VARIANT_OOB_CONSENT = 6;
    public static final int PAIRING_VARIANT_PASSKEY = 1;
    public static final int PAIRING_VARIANT_PASSKEY_CONFIRMATION = 2;
    public static final int PAIRING_VARIANT_PIN = 0;
    public static final int PAIRING_VARIANT_PIN_16_DIGITS = 7;
    public static final int PHY_LE_1M = 1;
    public static final int PHY_LE_1M_MASK = 1;
    public static final int PHY_LE_2M = 2;
    public static final int PHY_LE_2M_MASK = 2;
    public static final int PHY_LE_CODED = 3;
    public static final int PHY_LE_CODED_MASK = 4;
    public static final int PHY_OPTION_NO_PREFERRED = 0;
    public static final int PHY_OPTION_S2 = 1;
    public static final int PHY_OPTION_S8 = 2;
    public static final int REQUEST_TYPE_MESSAGE_ACCESS = 3;
    public static final int REQUEST_TYPE_PHONEBOOK_ACCESS = 2;
    public static final int REQUEST_TYPE_PROFILE_CONNECTION = 1;
    public static final int REQUEST_TYPE_SIM_ACCESS = 4;
    private static final String TAG = "BluetoothDevice";
    public static final int TRANSPORT_AUTO = 0;
    public static final int TRANSPORT_BREDR = 1;
    public static final int TRANSPORT_LE = 2;
    public static final int UNBOND_REASON_AUTH_CANCELED = 3;
    public static final int UNBOND_REASON_AUTH_FAILED = 1;
    public static final int UNBOND_REASON_AUTH_REJECTED = 2;
    public static final int UNBOND_REASON_AUTH_TIMEOUT = 6;
    public static final int UNBOND_REASON_DISCOVERY_IN_PROGRESS = 5;
    public static final int UNBOND_REASON_REMOTE_AUTH_CANCELED = 8;
    public static final int UNBOND_REASON_REMOTE_DEVICE_DOWN = 4;
    public static final int UNBOND_REASON_REMOVED = 9;
    public static final int UNBOND_REASON_REPEATED_ATTEMPTS = 7;
    static IBluetoothManagerCallback mStateChangeCallback = new IBluetoothManagerCallback.Stub() {

        public void onBluetoothServiceDown()
            throws RemoteException
        {
            android/bluetooth/BluetoothDevice;
            JVM INSTR monitorenter ;
            BluetoothDevice._2D_set0(null);
            android/bluetooth/BluetoothDevice;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public void onBluetoothServiceUp(IBluetooth ibluetooth)
            throws RemoteException
        {
            android/bluetooth/BluetoothDevice;
            JVM INSTR monitorenter ;
            if(BluetoothDevice._2D_get0() != null)
                Log.w("BluetoothDevice", "sService is not NULL");
            BluetoothDevice._2D_set0(ibluetooth);
            android/bluetooth/BluetoothDevice;
            JVM INSTR monitorexit ;
            return;
            ibluetooth;
            throw ibluetooth;
        }

        public void onBrEdrDown()
        {
        }

    }
;
    private static volatile IBluetooth sService;
    private final String mAddress;

}
