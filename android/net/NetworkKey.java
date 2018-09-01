// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiSsid;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.util.Objects;

// Referenced classes of package android.net:
//            WifiKey

public class NetworkKey
    implements Parcelable
{

    public NetworkKey(WifiKey wifikey)
    {
        type = 1;
        wifiKey = wifikey;
    }

    private NetworkKey(Parcel parcel)
    {
        type = parcel.readInt();
        switch(type)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Parcel has unknown type: ").append(type).toString());

        case 1: // '\001'
            wifiKey = (WifiKey)WifiKey.CREATOR.createFromParcel(parcel);
            break;
        }
    }

    NetworkKey(Parcel parcel, NetworkKey networkkey)
    {
        this(parcel);
    }

    public static NetworkKey createFromScanResult(ScanResult scanresult)
    {
        if(scanresult != null && scanresult.wifiSsid != null)
        {
            String s = scanresult.wifiSsid.toString();
            scanresult = scanresult.BSSID;
            if(!TextUtils.isEmpty(s) && s.equals("<unknown ssid>") ^ true && TextUtils.isEmpty(scanresult) ^ true)
            {
                try
                {
                    scanresult = new WifiKey(String.format("\"%s\"", new Object[] {
                        s
                    }), scanresult);
                }
                // Misplaced declaration of an exception variable
                catch(ScanResult scanresult)
                {
                    Log.e("NetworkKey", "Unable to create WifiKey.", scanresult);
                    return null;
                }
                return new NetworkKey(scanresult);
            }
        }
        return null;
    }

    public static NetworkKey createFromWifiInfo(WifiInfo wifiinfo)
    {
        if(wifiinfo != null)
        {
            String s = wifiinfo.getSSID();
            wifiinfo = wifiinfo.getBSSID();
            if(!TextUtils.isEmpty(s) && s.equals("<unknown ssid>") ^ true && TextUtils.isEmpty(wifiinfo) ^ true)
            {
                try
                {
                    wifiinfo = new WifiKey(s, wifiinfo);
                }
                // Misplaced declaration of an exception variable
                catch(WifiInfo wifiinfo)
                {
                    Log.e("NetworkKey", "Unable to create WifiKey.", wifiinfo);
                    return null;
                }
                return new NetworkKey(wifiinfo);
            }
        }
        return null;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (NetworkKey)obj;
        if(type == ((NetworkKey) (obj)).type)
            flag = Objects.equals(wifiKey, ((NetworkKey) (obj)).wifiKey);
        return flag;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(type), wifiKey
        });
    }

    public String toString()
    {
        switch(type)
        {
        default:
            return "InvalidKey";

        case 1: // '\001'
            return wifiKey.toString();
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(type);
        switch(type)
        {
        default:
            throw new IllegalStateException((new StringBuilder()).append("NetworkKey has unknown type ").append(type).toString());

        case 1: // '\001'
            wifiKey.writeToParcel(parcel, i);
            break;
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NetworkKey createFromParcel(Parcel parcel)
        {
            return new NetworkKey(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NetworkKey[] newArray(int i)
        {
            return new NetworkKey[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "NetworkKey";
    public static final int TYPE_WIFI = 1;
    public final int type;
    public final WifiKey wifiKey;

}
