// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.p2p;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package android.net.wifi.p2p:
//            WifiP2pWfdInfo

public class WifiP2pDevice
    implements Parcelable
{

    public WifiP2pDevice()
    {
        deviceName = "";
        deviceAddress = "";
        status = 4;
    }

    public WifiP2pDevice(WifiP2pDevice wifip2pdevice)
    {
        deviceName = "";
        deviceAddress = "";
        status = 4;
        if(wifip2pdevice != null)
        {
            deviceName = wifip2pdevice.deviceName;
            deviceAddress = wifip2pdevice.deviceAddress;
            primaryDeviceType = wifip2pdevice.primaryDeviceType;
            secondaryDeviceType = wifip2pdevice.secondaryDeviceType;
            wpsConfigMethodsSupported = wifip2pdevice.wpsConfigMethodsSupported;
            deviceCapability = wifip2pdevice.deviceCapability;
            groupCapability = wifip2pdevice.groupCapability;
            status = wifip2pdevice.status;
            wfdInfo = new WifiP2pWfdInfo(wifip2pdevice.wfdInfo);
        }
    }

    public WifiP2pDevice(String s)
        throws IllegalArgumentException
    {
        deviceName = "";
        deviceAddress = "";
        status = 4;
        String as[] = s.split("[ \n]");
        if(as.length < 1)
            throw new IllegalArgumentException("Malformed supplicant event");
        switch(as.length)
        {
        default:
            s = detailedDevicePattern.matcher(s);
            if(!s.find())
                throw new IllegalArgumentException("Malformed supplicant event");
            break;

        case 1: // '\001'
            deviceAddress = s;
            return;

        case 2: // '\002'
            s = twoTokenPattern.matcher(s);
            if(!s.find())
            {
                throw new IllegalArgumentException("Malformed supplicant event");
            } else
            {
                deviceAddress = s.group(2);
                return;
            }

        case 3: // '\003'
            s = threeTokenPattern.matcher(s);
            if(!s.find())
            {
                throw new IllegalArgumentException("Malformed supplicant event");
            } else
            {
                deviceAddress = s.group(1);
                return;
            }
        }
        deviceAddress = s.group(3);
        primaryDeviceType = s.group(4);
        deviceName = s.group(5);
        wpsConfigMethodsSupported = parseHex(s.group(6));
        deviceCapability = parseHex(s.group(7));
        groupCapability = parseHex(s.group(8));
        if(s.group(9) != null)
        {
            s = s.group(10);
            wfdInfo = new WifiP2pWfdInfo(parseHex(s.substring(0, 4)), parseHex(s.substring(4, 8)), parseHex(s.substring(8, 12)));
        }
        if(as[0].startsWith("P2P-DEVICE-FOUND"))
            status = 3;
    }

    private int parseHex(String s)
    {
        boolean flag;
        String s1;
label0:
        {
            flag = false;
            if(!s.startsWith("0x"))
            {
                s1 = s;
                if(!s.startsWith("0X"))
                    break label0;
            }
            s1 = s.substring(2);
        }
        int i;
        try
        {
            i = Integer.parseInt(s1, 16);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("WifiP2pDevice", (new StringBuilder()).append("Failed to parse hex string ").append(s1).toString());
            i = ((flag) ? 1 : 0);
        }
        return i;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(!(obj instanceof WifiP2pDevice))
            return false;
        obj = (WifiP2pDevice)obj;
        if(obj == null || ((WifiP2pDevice) (obj)).deviceAddress == null)
        {
            if(deviceAddress != null)
                flag = false;
            return flag;
        } else
        {
            return ((WifiP2pDevice) (obj)).deviceAddress.equals(deviceAddress);
        }
    }

    public boolean isDeviceLimit()
    {
        boolean flag = false;
        if((deviceCapability & 0x10) != 0)
            flag = true;
        return flag;
    }

    public boolean isGroupLimit()
    {
        boolean flag = false;
        if((groupCapability & 4) != 0)
            flag = true;
        return flag;
    }

    public boolean isGroupOwner()
    {
        boolean flag = false;
        if((groupCapability & 1) != 0)
            flag = true;
        return flag;
    }

    public boolean isInvitationCapable()
    {
        boolean flag = false;
        if((deviceCapability & 0x20) != 0)
            flag = true;
        return flag;
    }

    public boolean isServiceDiscoveryCapable()
    {
        boolean flag = false;
        if((deviceCapability & 1) != 0)
            flag = true;
        return flag;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("Device: ").append(deviceName);
        stringbuffer.append("\n deviceAddress: ").append(deviceAddress);
        stringbuffer.append("\n primary type: ").append(primaryDeviceType);
        stringbuffer.append("\n secondary type: ").append(secondaryDeviceType);
        stringbuffer.append("\n wps: ").append(wpsConfigMethodsSupported);
        stringbuffer.append("\n grpcapab: ").append(groupCapability);
        stringbuffer.append("\n devcapab: ").append(deviceCapability);
        stringbuffer.append("\n status: ").append(status);
        stringbuffer.append("\n wfdInfo: ").append(wfdInfo);
        return stringbuffer.toString();
    }

    public void update(WifiP2pDevice wifip2pdevice)
    {
        updateSupplicantDetails(wifip2pdevice);
        status = wifip2pdevice.status;
    }

    public void updateSupplicantDetails(WifiP2pDevice wifip2pdevice)
    {
        if(wifip2pdevice == null)
            throw new IllegalArgumentException("device is null");
        if(wifip2pdevice.deviceAddress == null)
            throw new IllegalArgumentException("deviceAddress is null");
        if(!deviceAddress.equals(wifip2pdevice.deviceAddress))
        {
            throw new IllegalArgumentException("deviceAddress does not match");
        } else
        {
            deviceName = wifip2pdevice.deviceName;
            primaryDeviceType = wifip2pdevice.primaryDeviceType;
            secondaryDeviceType = wifip2pdevice.secondaryDeviceType;
            wpsConfigMethodsSupported = wifip2pdevice.wpsConfigMethodsSupported;
            deviceCapability = wifip2pdevice.deviceCapability;
            groupCapability = wifip2pdevice.groupCapability;
            wfdInfo = wifip2pdevice.wfdInfo;
            return;
        }
    }

    public boolean wpsDisplaySupported()
    {
        boolean flag = false;
        if((wpsConfigMethodsSupported & 8) != 0)
            flag = true;
        return flag;
    }

    public boolean wpsKeypadSupported()
    {
        boolean flag = false;
        if((wpsConfigMethodsSupported & 0x100) != 0)
            flag = true;
        return flag;
    }

    public boolean wpsPbcSupported()
    {
        boolean flag = false;
        if((wpsConfigMethodsSupported & 0x80) != 0)
            flag = true;
        return flag;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(deviceName);
        parcel.writeString(deviceAddress);
        parcel.writeString(primaryDeviceType);
        parcel.writeString(secondaryDeviceType);
        parcel.writeInt(wpsConfigMethodsSupported);
        parcel.writeInt(deviceCapability);
        parcel.writeInt(groupCapability);
        parcel.writeInt(status);
        if(wfdInfo != null)
        {
            parcel.writeInt(1);
            wfdInfo.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
    }

    public static final int AVAILABLE = 3;
    public static final int CONNECTED = 0;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiP2pDevice createFromParcel(Parcel parcel)
        {
            WifiP2pDevice wifip2pdevice = new WifiP2pDevice();
            wifip2pdevice.deviceName = parcel.readString();
            wifip2pdevice.deviceAddress = parcel.readString();
            wifip2pdevice.primaryDeviceType = parcel.readString();
            wifip2pdevice.secondaryDeviceType = parcel.readString();
            wifip2pdevice.wpsConfigMethodsSupported = parcel.readInt();
            wifip2pdevice.deviceCapability = parcel.readInt();
            wifip2pdevice.groupCapability = parcel.readInt();
            wifip2pdevice.status = parcel.readInt();
            if(parcel.readInt() == 1)
                wifip2pdevice.wfdInfo = (WifiP2pWfdInfo)WifiP2pWfdInfo.CREATOR.createFromParcel(parcel);
            return wifip2pdevice;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiP2pDevice[] newArray(int i)
        {
            return new WifiP2pDevice[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int DEVICE_CAPAB_CLIENT_DISCOVERABILITY = 2;
    private static final int DEVICE_CAPAB_CONCURRENT_OPER = 4;
    private static final int DEVICE_CAPAB_DEVICE_LIMIT = 16;
    private static final int DEVICE_CAPAB_INFRA_MANAGED = 8;
    private static final int DEVICE_CAPAB_INVITATION_PROCEDURE = 32;
    private static final int DEVICE_CAPAB_SERVICE_DISCOVERY = 1;
    public static final int FAILED = 2;
    private static final int GROUP_CAPAB_CROSS_CONN = 16;
    private static final int GROUP_CAPAB_GROUP_FORMATION = 64;
    private static final int GROUP_CAPAB_GROUP_LIMIT = 4;
    private static final int GROUP_CAPAB_GROUP_OWNER = 1;
    private static final int GROUP_CAPAB_INTRA_BSS_DIST = 8;
    private static final int GROUP_CAPAB_PERSISTENT_GROUP = 2;
    private static final int GROUP_CAPAB_PERSISTENT_RECONN = 32;
    public static final int INVITED = 1;
    private static final String TAG = "WifiP2pDevice";
    public static final int UNAVAILABLE = 4;
    private static final int WPS_CONFIG_DISPLAY = 8;
    private static final int WPS_CONFIG_KEYPAD = 256;
    private static final int WPS_CONFIG_PUSHBUTTON = 128;
    private static final Pattern detailedDevicePattern = Pattern.compile("((?:[0-9a-f]{2}:){5}[0-9a-f]{2}) (\\d+ )?p2p_dev_addr=((?:[0-9a-f]{2}:){5}[0-9a-f]{2}) pri_dev_type=(\\d+-[0-9a-fA-F]+-\\d+) name='(.*)' config_methods=(0x[0-9a-fA-F]+) dev_capab=(0x[0-9a-fA-F]+) group_capab=(0x[0-9a-fA-F]+)( wfd_dev_info=0x([0-9a-fA-F]{12}))?");
    private static final Pattern threeTokenPattern = Pattern.compile("(?:[0-9a-f]{2}:){5}[0-9a-f]{2} p2p_dev_addr=((?:[0-9a-f]{2}:){5}[0-9a-f]{2})");
    private static final Pattern twoTokenPattern = Pattern.compile("(p2p_dev_addr=)?((?:[0-9a-f]{2}:){5}[0-9a-f]{2})");
    public String deviceAddress;
    public int deviceCapability;
    public String deviceName;
    public int groupCapability;
    public String primaryDeviceType;
    public String secondaryDeviceType;
    public int status;
    public WifiP2pWfdInfo wfdInfo;
    public int wpsConfigMethodsSupported;

}
