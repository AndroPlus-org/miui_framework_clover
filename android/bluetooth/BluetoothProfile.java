// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import java.util.List;

// Referenced classes of package android.bluetooth:
//            BluetoothDevice

public interface BluetoothProfile
{
    public static interface ServiceListener
    {

        public abstract void onServiceConnected(int i, BluetoothProfile bluetoothprofile);

        public abstract void onServiceDisconnected(int i);
    }


    public abstract List getConnectedDevices();

    public abstract int getConnectionState(BluetoothDevice bluetoothdevice);

    public abstract List getDevicesMatchingConnectionStates(int ai[]);

    public static final int A2DP = 2;
    public static final int A2DP_SINK = 11;
    public static final int AVRCP_CONTROLLER = 12;
    public static final int DUN = 20;
    public static final String EXTRA_PREVIOUS_STATE = "android.bluetooth.profile.extra.PREVIOUS_STATE";
    public static final String EXTRA_STATE = "android.bluetooth.profile.extra.STATE";
    public static final int GATT = 7;
    public static final int GATT_SERVER = 8;
    public static final int HEADSET = 1;
    public static final int HEADSET_CLIENT = 16;
    public static final int HEALTH = 3;
    public static final int INPUT_DEVICE = 4;
    public static final int INPUT_HOST = 19;
    public static final int MAP = 9;
    public static final int MAP_CLIENT = 18;
    public static final int MAX_PROFILE_ID = 20;
    public static final int PAN = 5;
    public static final int PBAP = 6;
    public static final int PBAP_CLIENT = 17;
    public static final int PRIORITY_AUTO_CONNECT = 1000;
    public static final int PRIORITY_OFF = 0;
    public static final int PRIORITY_ON = 100;
    public static final int PRIORITY_UNDEFINED = -1;
    public static final int SAP = 10;
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_DISCONNECTING = 3;
}
