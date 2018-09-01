// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.p2p;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

public class WifiP2pWfdInfo
    implements Parcelable
{

    public WifiP2pWfdInfo()
    {
    }

    public WifiP2pWfdInfo(int i, int j, int k)
    {
        mWfdEnabled = true;
        mDeviceInfo = i;
        mCtrlPort = j;
        mMaxThroughput = k;
    }

    public WifiP2pWfdInfo(WifiP2pWfdInfo wifip2pwfdinfo)
    {
        if(wifip2pwfdinfo != null)
        {
            mWfdEnabled = wifip2pwfdinfo.mWfdEnabled;
            mDeviceInfo = wifip2pwfdinfo.mDeviceInfo;
            mCtrlPort = wifip2pwfdinfo.mCtrlPort;
            mMaxThroughput = wifip2pwfdinfo.mMaxThroughput;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public int getControlPort()
    {
        return mCtrlPort;
    }

    public String getDeviceInfoHex()
    {
        return String.format(Locale.US, "%04x%04x%04x", new Object[] {
            Integer.valueOf(mDeviceInfo), Integer.valueOf(mCtrlPort), Integer.valueOf(mMaxThroughput)
        });
    }

    public int getDeviceType()
    {
        return mDeviceInfo & 3;
    }

    public int getMaxThroughput()
    {
        return mMaxThroughput;
    }

    public boolean isCoupledSinkSupportedAtSink()
    {
        boolean flag = false;
        if((mDeviceInfo & 8) != 0)
            flag = true;
        return flag;
    }

    public boolean isCoupledSinkSupportedAtSource()
    {
        boolean flag = false;
        if((mDeviceInfo & 8) != 0)
            flag = true;
        return flag;
    }

    public boolean isSessionAvailable()
    {
        boolean flag = false;
        if((mDeviceInfo & 0x30) != 0)
            flag = true;
        return flag;
    }

    public boolean isWfdEnabled()
    {
        return mWfdEnabled;
    }

    public void readFromParcel(Parcel parcel)
    {
        boolean flag = true;
        if(parcel.readInt() != 1)
            flag = false;
        mWfdEnabled = flag;
        mDeviceInfo = parcel.readInt();
        mCtrlPort = parcel.readInt();
        mMaxThroughput = parcel.readInt();
    }

    public void setControlPort(int i)
    {
        mCtrlPort = i;
    }

    public void setCoupledSinkSupportAtSink(boolean flag)
    {
        if(flag)
            mDeviceInfo = mDeviceInfo | 8;
        else
            mDeviceInfo = mDeviceInfo & -9;
    }

    public void setCoupledSinkSupportAtSource(boolean flag)
    {
        if(flag)
            mDeviceInfo = mDeviceInfo | 8;
        else
            mDeviceInfo = mDeviceInfo & -9;
    }

    public boolean setDeviceType(int i)
    {
        if(i >= 0 && i <= 3)
        {
            mDeviceInfo = mDeviceInfo & -4;
            mDeviceInfo = mDeviceInfo | i;
            return true;
        } else
        {
            return false;
        }
    }

    public void setMaxThroughput(int i)
    {
        mMaxThroughput = i;
    }

    public void setSessionAvailable(boolean flag)
    {
        if(flag)
        {
            mDeviceInfo = mDeviceInfo | 0x10;
            mDeviceInfo = mDeviceInfo & 0xffffffdf;
        } else
        {
            mDeviceInfo = mDeviceInfo & 0xffffffcf;
        }
    }

    public void setWfdEnabled(boolean flag)
    {
        mWfdEnabled = flag;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("WFD enabled: ").append(mWfdEnabled);
        stringbuffer.append("WFD DeviceInfo: ").append(mDeviceInfo);
        stringbuffer.append("\n WFD CtrlPort: ").append(mCtrlPort);
        stringbuffer.append("\n WFD MaxThroughput: ").append(mMaxThroughput);
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mWfdEnabled)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mDeviceInfo);
        parcel.writeInt(mCtrlPort);
        parcel.writeInt(mMaxThroughput);
    }

    private static final int COUPLED_SINK_SUPPORT_AT_SINK = 8;
    private static final int COUPLED_SINK_SUPPORT_AT_SOURCE = 4;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiP2pWfdInfo createFromParcel(Parcel parcel)
        {
            WifiP2pWfdInfo wifip2pwfdinfo = new WifiP2pWfdInfo();
            wifip2pwfdinfo.readFromParcel(parcel);
            return wifip2pwfdinfo;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiP2pWfdInfo[] newArray(int i)
        {
            return new WifiP2pWfdInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int DEVICE_TYPE = 3;
    public static final int PRIMARY_SINK = 1;
    public static final int SECONDARY_SINK = 2;
    private static final int SESSION_AVAILABLE = 48;
    private static final int SESSION_AVAILABLE_BIT1 = 16;
    private static final int SESSION_AVAILABLE_BIT2 = 32;
    public static final int SOURCE_OR_PRIMARY_SINK = 3;
    private static final String TAG = "WifiP2pWfdInfo";
    public static final int WFD_SOURCE = 0;
    private int mCtrlPort;
    private int mDeviceInfo;
    private int mMaxThroughput;
    private boolean mWfdEnabled;

}
