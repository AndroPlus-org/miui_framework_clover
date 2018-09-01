// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.hdmi;

import android.os.Parcel;
import android.os.Parcelable;

public class HdmiDeviceInfo
    implements Parcelable
{

    public HdmiDeviceInfo()
    {
        mHdmiDeviceType = 100;
        mPhysicalAddress = 65535;
        mId = 65535;
        mLogicalAddress = -1;
        mDeviceType = -1;
        mPortId = -1;
        mDevicePowerStatus = -1;
        mDisplayName = "Inactive";
        mVendorId = 0;
        mDeviceId = -1;
        mAdopterId = -1;
    }

    public HdmiDeviceInfo(int i, int j)
    {
        mHdmiDeviceType = 2;
        mPhysicalAddress = i;
        mPortId = j;
        mId = idForHardware(j);
        mLogicalAddress = -1;
        mDeviceType = 2;
        mVendorId = 0;
        mDevicePowerStatus = -1;
        mDisplayName = (new StringBuilder()).append("HDMI").append(j).toString();
        mDeviceId = -1;
        mAdopterId = -1;
    }

    public HdmiDeviceInfo(int i, int j, int k, int l)
    {
        mHdmiDeviceType = 1;
        mPhysicalAddress = i;
        mPortId = j;
        mId = idForMhlDevice(j);
        mLogicalAddress = -1;
        mDeviceType = 2;
        mVendorId = 0;
        mDevicePowerStatus = -1;
        mDisplayName = "Mobile";
        mDeviceId = k;
        mAdopterId = l;
    }

    public HdmiDeviceInfo(int i, int j, int k, int l, int i1, String s)
    {
        this(i, j, k, l, i1, s, -1);
    }

    public HdmiDeviceInfo(int i, int j, int k, int l, int i1, String s, int j1)
    {
        mHdmiDeviceType = 0;
        mPhysicalAddress = j;
        mPortId = k;
        mId = idForCecDevice(i);
        mLogicalAddress = i;
        mDeviceType = l;
        mVendorId = i1;
        mDevicePowerStatus = j1;
        mDisplayName = s;
        mDeviceId = -1;
        mAdopterId = -1;
    }

    public static int idForCecDevice(int i)
    {
        return i + 0;
    }

    public static int idForHardware(int i)
    {
        return i + 192;
    }

    public static int idForMhlDevice(int i)
    {
        return i + 128;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof HdmiDeviceInfo))
            return false;
        obj = (HdmiDeviceInfo)obj;
        boolean flag1 = flag;
        if(mHdmiDeviceType == ((HdmiDeviceInfo) (obj)).mHdmiDeviceType)
        {
            flag1 = flag;
            if(mPhysicalAddress == ((HdmiDeviceInfo) (obj)).mPhysicalAddress)
            {
                flag1 = flag;
                if(mPortId == ((HdmiDeviceInfo) (obj)).mPortId)
                {
                    flag1 = flag;
                    if(mLogicalAddress == ((HdmiDeviceInfo) (obj)).mLogicalAddress)
                    {
                        flag1 = flag;
                        if(mDeviceType == ((HdmiDeviceInfo) (obj)).mDeviceType)
                        {
                            flag1 = flag;
                            if(mVendorId == ((HdmiDeviceInfo) (obj)).mVendorId)
                            {
                                flag1 = flag;
                                if(mDevicePowerStatus == ((HdmiDeviceInfo) (obj)).mDevicePowerStatus)
                                {
                                    flag1 = flag;
                                    if(mDisplayName.equals(((HdmiDeviceInfo) (obj)).mDisplayName))
                                    {
                                        flag1 = flag;
                                        if(mDeviceId == ((HdmiDeviceInfo) (obj)).mDeviceId)
                                        {
                                            flag1 = flag;
                                            if(mAdopterId == ((HdmiDeviceInfo) (obj)).mAdopterId)
                                                flag1 = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public int getAdopterId()
    {
        return mAdopterId;
    }

    public int getDeviceId()
    {
        return mDeviceId;
    }

    public int getDevicePowerStatus()
    {
        return mDevicePowerStatus;
    }

    public int getDeviceType()
    {
        return mDeviceType;
    }

    public String getDisplayName()
    {
        return mDisplayName;
    }

    public int getId()
    {
        return mId;
    }

    public int getLogicalAddress()
    {
        return mLogicalAddress;
    }

    public int getPhysicalAddress()
    {
        return mPhysicalAddress;
    }

    public int getPortId()
    {
        return mPortId;
    }

    public int getVendorId()
    {
        return mVendorId;
    }

    public boolean isCecDevice()
    {
        boolean flag = false;
        if(mHdmiDeviceType == 0)
            flag = true;
        return flag;
    }

    public boolean isInactivated()
    {
        boolean flag;
        if(mHdmiDeviceType == 100)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isMhlDevice()
    {
        boolean flag = true;
        if(mHdmiDeviceType != 1)
            flag = false;
        return flag;
    }

    public boolean isSourceType()
    {
        boolean flag = true;
        if(!isCecDevice()) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
        if(mDeviceType == 4) goto _L4; else goto _L3
_L3:
        if(mDeviceType != 1) goto _L6; else goto _L5
_L5:
        flag1 = flag;
_L4:
        return flag1;
_L6:
        flag1 = flag;
        if(mDeviceType != 3)
            flag1 = false;
        if(true) goto _L4; else goto _L2
_L2:
        return isMhlDevice();
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        mHdmiDeviceType;
        JVM INSTR lookupswitch 4: default 56
    //                   0: 59
    //                   1: 233
    //                   2: 311
    //                   100: 321;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return "";
_L2:
        stringbuffer.append("CEC: ");
        stringbuffer.append("logical_address: ").append(String.format("0x%02X", new Object[] {
            Integer.valueOf(mLogicalAddress)
        }));
        stringbuffer.append(" ");
        stringbuffer.append("device_type: ").append(mDeviceType).append(" ");
        stringbuffer.append("vendor_id: ").append(mVendorId).append(" ");
        stringbuffer.append("display_name: ").append(mDisplayName).append(" ");
        stringbuffer.append("power_status: ").append(mDevicePowerStatus).append(" ");
_L7:
        stringbuffer.append("physical_address: ").append(String.format("0x%04X", new Object[] {
            Integer.valueOf(mPhysicalAddress)
        }));
        stringbuffer.append(" ");
        stringbuffer.append("port_id: ").append(mPortId);
        return stringbuffer.toString();
_L3:
        stringbuffer.append("MHL: ");
        stringbuffer.append("device_id: ").append(String.format("0x%04X", new Object[] {
            Integer.valueOf(mDeviceId)
        })).append(" ");
        stringbuffer.append("adopter_id: ").append(String.format("0x%04X", new Object[] {
            Integer.valueOf(mAdopterId)
        })).append(" ");
        continue; /* Loop/switch isn't completed */
_L4:
        stringbuffer.append("Hardware: ");
        continue; /* Loop/switch isn't completed */
_L5:
        stringbuffer.append("Inactivated: ");
        if(true) goto _L7; else goto _L6
_L6:
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mHdmiDeviceType);
        parcel.writeInt(mPhysicalAddress);
        parcel.writeInt(mPortId);
        mHdmiDeviceType;
        JVM INSTR tableswitch 0 1: default 52
    //                   0 53
    //                   1 96;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        parcel.writeInt(mLogicalAddress);
        parcel.writeInt(mDeviceType);
        parcel.writeInt(mVendorId);
        parcel.writeInt(mDevicePowerStatus);
        parcel.writeString(mDisplayName);
        continue; /* Loop/switch isn't completed */
_L3:
        parcel.writeInt(mDeviceId);
        parcel.writeInt(mAdopterId);
        if(true) goto _L1; else goto _L4
_L4:
    }

    public static final int ADDR_INTERNAL = 0;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public HdmiDeviceInfo createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            int l = parcel.readInt();
            int i1 = parcel.readInt();
            switch(i)
            {
            default:
                return null;

            case 0: // '\0'
                int j1 = parcel.readInt();
                int k1 = parcel.readInt();
                int j = parcel.readInt();
                int l1 = parcel.readInt();
                return new HdmiDeviceInfo(j1, l, i1, k1, j, parcel.readString(), l1);

            case 1: // '\001'
                int k = parcel.readInt();
                return new HdmiDeviceInfo(l, i1, parcel.readInt(), k);

            case 2: // '\002'
                return new HdmiDeviceInfo(l, i1);

            case 100: // 'd'
                return HdmiDeviceInfo.INACTIVE_DEVICE;
            }
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public HdmiDeviceInfo[] newArray(int i)
        {
            return new HdmiDeviceInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DEVICE_AUDIO_SYSTEM = 5;
    public static final int DEVICE_INACTIVE = -1;
    public static final int DEVICE_PLAYBACK = 4;
    public static final int DEVICE_PURE_CEC_SWITCH = 6;
    public static final int DEVICE_RECORDER = 1;
    public static final int DEVICE_RESERVED = 2;
    public static final int DEVICE_TUNER = 3;
    public static final int DEVICE_TV = 0;
    public static final int DEVICE_VIDEO_PROCESSOR = 7;
    private static final int HDMI_DEVICE_TYPE_CEC = 0;
    private static final int HDMI_DEVICE_TYPE_HARDWARE = 2;
    private static final int HDMI_DEVICE_TYPE_INACTIVE = 100;
    private static final int HDMI_DEVICE_TYPE_MHL = 1;
    public static final int ID_INVALID = 65535;
    private static final int ID_OFFSET_CEC = 0;
    private static final int ID_OFFSET_HARDWARE = 192;
    private static final int ID_OFFSET_MHL = 128;
    public static final HdmiDeviceInfo INACTIVE_DEVICE = new HdmiDeviceInfo();
    public static final int PATH_INTERNAL = 0;
    public static final int PATH_INVALID = 65535;
    public static final int PORT_INVALID = -1;
    private final int mAdopterId;
    private final int mDeviceId;
    private final int mDevicePowerStatus;
    private final int mDeviceType;
    private final String mDisplayName;
    private final int mHdmiDeviceType;
    private final int mId;
    private final int mLogicalAddress;
    private final int mPhysicalAddress;
    private final int mPortId;
    private final int mVendorId;

}
