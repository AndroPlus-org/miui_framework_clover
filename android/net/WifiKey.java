// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WifiKey
    implements Parcelable
{

    private WifiKey(Parcel parcel)
    {
        ssid = parcel.readString();
        bssid = parcel.readString();
    }

    WifiKey(Parcel parcel, WifiKey wifikey)
    {
        this(parcel);
    }

    public WifiKey(String s, String s1)
    {
        if(s == null || SSID_PATTERN.matcher(s).matches() ^ true)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid ssid: ").append(s).toString());
        if(s1 == null || BSSID_PATTERN.matcher(s1).matches() ^ true)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid bssid: ").append(s1).toString());
        } else
        {
            ssid = s;
            bssid = s1;
            return;
        }
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
        obj = (WifiKey)obj;
        if(Objects.equals(ssid, ((WifiKey) (obj)).ssid))
            flag = Objects.equals(bssid, ((WifiKey) (obj)).bssid);
        return flag;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            ssid, bssid
        });
    }

    public String toString()
    {
        return (new StringBuilder()).append("WifiKey[SSID=").append(ssid).append(",BSSID=").append(bssid).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(ssid);
        parcel.writeString(bssid);
    }

    private static final Pattern BSSID_PATTERN = Pattern.compile("([\\p{XDigit}]{2}:){5}[\\p{XDigit}]{2}");
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiKey createFromParcel(Parcel parcel)
        {
            return new WifiKey(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiKey[] newArray(int i)
        {
            return new WifiKey[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final Pattern SSID_PATTERN = Pattern.compile("(\".*\")|(0x[\\p{XDigit}]+)", 32);
    public final String bssid;
    public final String ssid;

}
