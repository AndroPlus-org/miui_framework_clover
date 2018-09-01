// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

// Referenced classes of package android.net.wifi:
//            WifiSsid

public class WifiLinkLayerStats
    implements Parcelable
{

    public WifiLinkLayerStats()
    {
    }

    public int describeContents()
    {
        return 0;
    }

    public String getPrintableSsid()
    {
        if(SSID == null)
            return "";
        int i = SSID.length();
        if(i > 2 && SSID.charAt(0) == '"' && SSID.charAt(i - 1) == '"')
            return SSID.substring(1, i - 1);
        if(i > 3 && SSID.charAt(0) == 'P' && SSID.charAt(1) == '"' && SSID.charAt(i - 1) == '"')
            return WifiSsid.createFromAsciiEncoded(SSID.substring(2, i - 1)).toString();
        else
            return SSID;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(" WifiLinkLayerStats: ").append('\n');
        if(SSID != null)
            stringbuilder.append(" SSID: ").append(SSID).append('\n');
        if(BSSID != null)
            stringbuilder.append(" BSSID: ").append(BSSID).append('\n');
        stringbuilder.append(" my bss beacon rx: ").append(Integer.toString(beacon_rx)).append('\n');
        stringbuilder.append(" RSSI mgmt: ").append(Integer.toString(rssi_mgmt)).append('\n');
        stringbuilder.append(" BE : ").append(" rx=").append(Long.toString(rxmpdu_be)).append(" tx=").append(Long.toString(txmpdu_be)).append(" lost=").append(Long.toString(lostmpdu_be)).append(" retries=").append(Long.toString(retries_be)).append('\n');
        stringbuilder.append(" BK : ").append(" rx=").append(Long.toString(rxmpdu_bk)).append(" tx=").append(Long.toString(txmpdu_bk)).append(" lost=").append(Long.toString(lostmpdu_bk)).append(" retries=").append(Long.toString(retries_bk)).append('\n');
        stringbuilder.append(" VI : ").append(" rx=").append(Long.toString(rxmpdu_vi)).append(" tx=").append(Long.toString(txmpdu_vi)).append(" lost=").append(Long.toString(lostmpdu_vi)).append(" retries=").append(Long.toString(retries_vi)).append('\n');
        stringbuilder.append(" VO : ").append(" rx=").append(Long.toString(rxmpdu_vo)).append(" tx=").append(Long.toString(txmpdu_vo)).append(" lost=").append(Long.toString(lostmpdu_vo)).append(" retries=").append(Long.toString(retries_vo)).append('\n');
        stringbuilder.append(" on_time : ").append(Integer.toString(on_time)).append(" rx_time=").append(Integer.toString(rx_time)).append(" scan_time=").append(Integer.toString(on_time_scan)).append('\n').append(" tx_time=").append(Integer.toString(tx_time)).append(" tx_time_per_level=").append(Arrays.toString(tx_time_per_level));
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(SSID);
        parcel.writeString(BSSID);
        parcel.writeInt(on_time);
        parcel.writeInt(tx_time);
        parcel.writeIntArray(tx_time_per_level);
        parcel.writeInt(rx_time);
        parcel.writeInt(on_time_scan);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiLinkLayerStats createFromParcel(Parcel parcel)
        {
            WifiLinkLayerStats wifilinklayerstats = new WifiLinkLayerStats();
            wifilinklayerstats.SSID = parcel.readString();
            wifilinklayerstats.BSSID = parcel.readString();
            wifilinklayerstats.on_time = parcel.readInt();
            wifilinklayerstats.tx_time = parcel.readInt();
            wifilinklayerstats.tx_time_per_level = parcel.createIntArray();
            wifilinklayerstats.rx_time = parcel.readInt();
            wifilinklayerstats.on_time_scan = parcel.readInt();
            return wifilinklayerstats;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiLinkLayerStats[] newArray(int i)
        {
            return new WifiLinkLayerStats[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "WifiLinkLayerStats";
    public String BSSID;
    public String SSID;
    public int beacon_rx;
    public long lostmpdu_be;
    public long lostmpdu_bk;
    public long lostmpdu_vi;
    public long lostmpdu_vo;
    public int on_time;
    public int on_time_scan;
    public long retries_be;
    public long retries_bk;
    public long retries_vi;
    public long retries_vo;
    public int rssi_mgmt;
    public int rx_time;
    public long rxmpdu_be;
    public long rxmpdu_bk;
    public long rxmpdu_vi;
    public long rxmpdu_vo;
    public int status;
    public int tx_time;
    public int tx_time_per_level[];
    public long txmpdu_be;
    public long txmpdu_bk;
    public long txmpdu_vi;
    public long txmpdu_vo;

}
