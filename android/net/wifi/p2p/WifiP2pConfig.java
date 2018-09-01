// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.p2p;

import android.net.wifi.WpsInfo;
import android.os.Parcel;
import android.os.Parcelable;

public class WifiP2pConfig
    implements Parcelable
{

    public WifiP2pConfig()
    {
        deviceAddress = "";
        groupOwnerIntent = -1;
        netId = -2;
        wps = new WpsInfo();
        wps.setup = 0;
    }

    public WifiP2pConfig(WifiP2pConfig wifip2pconfig)
    {
        deviceAddress = "";
        groupOwnerIntent = -1;
        netId = -2;
        if(wifip2pconfig != null)
        {
            deviceAddress = wifip2pconfig.deviceAddress;
            wps = new WpsInfo(wifip2pconfig.wps);
            groupOwnerIntent = wifip2pconfig.groupOwnerIntent;
            netId = wifip2pconfig.netId;
        }
    }

    public WifiP2pConfig(String s)
        throws IllegalArgumentException
    {
        deviceAddress = "";
        groupOwnerIntent = -1;
        netId = -2;
        s = s.split(" ");
        if(s.length < 2 || s[0].equals("P2P-GO-NEG-REQUEST") ^ true)
            throw new IllegalArgumentException("Malformed supplicant event");
        deviceAddress = s[1];
        wps = new WpsInfo();
        if(s.length <= 2) goto _L2; else goto _L1
_L1:
        s = s[2].split("=");
        int i;
        try
        {
            i = Integer.parseInt(s[1]);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            i = 0;
        }
        i;
        JVM INSTR tableswitch 1 5: default 132
    //                   1 147
    //                   2 132
    //                   3 132
    //                   4 158
    //                   5 169;
           goto _L3 _L4 _L3 _L3 _L5 _L6
_L3:
        wps.setup = 0;
_L2:
        return;
_L4:
        wps.setup = 1;
        continue; /* Loop/switch isn't completed */
_L5:
        wps.setup = 0;
        continue; /* Loop/switch isn't completed */
_L6:
        wps.setup = 2;
        if(true) goto _L2; else goto _L7
_L7:
    }

    public int describeContents()
    {
        return 0;
    }

    public void invalidate()
    {
        deviceAddress = "";
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("\n address: ").append(deviceAddress);
        stringbuffer.append("\n wps: ").append(wps);
        stringbuffer.append("\n groupOwnerIntent: ").append(groupOwnerIntent);
        stringbuffer.append("\n persist: ").append(netId);
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(deviceAddress);
        parcel.writeParcelable(wps, i);
        parcel.writeInt(groupOwnerIntent);
        parcel.writeInt(netId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiP2pConfig createFromParcel(Parcel parcel)
        {
            WifiP2pConfig wifip2pconfig = new WifiP2pConfig();
            wifip2pconfig.deviceAddress = parcel.readString();
            wifip2pconfig.wps = (WpsInfo)parcel.readParcelable(null);
            wifip2pconfig.groupOwnerIntent = parcel.readInt();
            wifip2pconfig.netId = parcel.readInt();
            return wifip2pconfig;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiP2pConfig[] newArray(int i)
        {
            return new WifiP2pConfig[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int MAX_GROUP_OWNER_INTENT = 15;
    public static final int MIN_GROUP_OWNER_INTENT = 0;
    public String deviceAddress;
    public int groupOwnerIntent;
    public int netId;
    public WpsInfo wps;

}
