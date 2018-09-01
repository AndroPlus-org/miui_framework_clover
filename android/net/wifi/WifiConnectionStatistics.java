// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Iterator;

// Referenced classes of package android.net.wifi:
//            WifiNetworkConnectionStatistics

public class WifiConnectionStatistics
    implements Parcelable
{

    public WifiConnectionStatistics()
    {
        untrustedNetworkHistory = new HashMap();
    }

    public WifiConnectionStatistics(WifiConnectionStatistics wificonnectionstatistics)
    {
        untrustedNetworkHistory = new HashMap();
        if(wificonnectionstatistics != null)
            untrustedNetworkHistory.putAll(wificonnectionstatistics.untrustedNetworkHistory);
    }

    public int describeContents()
    {
        return 0;
    }

    public void incrementOrAddUntrusted(String s, int i, int j)
    {
        if(TextUtils.isEmpty(s))
            return;
        WifiNetworkConnectionStatistics wifinetworkconnectionstatistics1;
        if(untrustedNetworkHistory.containsKey(s))
        {
            WifiNetworkConnectionStatistics wifinetworkconnectionstatistics = (WifiNetworkConnectionStatistics)untrustedNetworkHistory.get(s);
            wifinetworkconnectionstatistics1 = wifinetworkconnectionstatistics;
            if(wifinetworkconnectionstatistics != null)
            {
                wifinetworkconnectionstatistics.numConnection = wifinetworkconnectionstatistics.numConnection + i;
                wifinetworkconnectionstatistics.numUsage = wifinetworkconnectionstatistics.numUsage + j;
                wifinetworkconnectionstatistics1 = wifinetworkconnectionstatistics;
            }
        } else
        {
            wifinetworkconnectionstatistics1 = new WifiNetworkConnectionStatistics(i, j);
        }
        if(wifinetworkconnectionstatistics1 != null)
            untrustedNetworkHistory.put(s, wifinetworkconnectionstatistics1);
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Connected on: 2.4Ghz=").append(num24GhzConnected);
        stringbuilder.append(" 5Ghz=").append(num5GhzConnected).append("\n");
        stringbuilder.append(" join=").append(numWifiManagerJoinAttempt);
        stringbuilder.append("\\").append(numAutoJoinAttempt).append("\n");
        stringbuilder.append(" roam=").append(numAutoRoamAttempt).append("\n");
        Iterator iterator = untrustedNetworkHistory.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            String s = (String)iterator.next();
            WifiNetworkConnectionStatistics wifinetworkconnectionstatistics = (WifiNetworkConnectionStatistics)untrustedNetworkHistory.get(s);
            if(wifinetworkconnectionstatistics != null)
                stringbuilder.append(s).append(" ").append(wifinetworkconnectionstatistics.toString()).append("\n");
        } while(true);
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(num24GhzConnected);
        parcel.writeInt(num5GhzConnected);
        parcel.writeInt(numAutoJoinAttempt);
        parcel.writeInt(numAutoRoamAttempt);
        parcel.writeInt(numWifiManagerJoinAttempt);
        parcel.writeInt(untrustedNetworkHistory.size());
        WifiNetworkConnectionStatistics wifinetworkconnectionstatistics;
        for(Iterator iterator = untrustedNetworkHistory.keySet().iterator(); iterator.hasNext(); parcel.writeInt(wifinetworkconnectionstatistics.numUsage))
        {
            String s = (String)iterator.next();
            wifinetworkconnectionstatistics = (WifiNetworkConnectionStatistics)untrustedNetworkHistory.get(s);
            parcel.writeString(s);
            parcel.writeInt(wifinetworkconnectionstatistics.numConnection);
        }

    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiConnectionStatistics createFromParcel(Parcel parcel)
        {
            WifiConnectionStatistics wificonnectionstatistics = new WifiConnectionStatistics();
            wificonnectionstatistics.num24GhzConnected = parcel.readInt();
            wificonnectionstatistics.num5GhzConnected = parcel.readInt();
            wificonnectionstatistics.numAutoJoinAttempt = parcel.readInt();
            wificonnectionstatistics.numAutoRoamAttempt = parcel.readInt();
            wificonnectionstatistics.numWifiManagerJoinAttempt = parcel.readInt();
            for(int i = parcel.readInt(); i > 0; i--)
            {
                String s = parcel.readString();
                WifiNetworkConnectionStatistics wifinetworkconnectionstatistics = new WifiNetworkConnectionStatistics(parcel.readInt(), parcel.readInt());
                wificonnectionstatistics.untrustedNetworkHistory.put(s, wifinetworkconnectionstatistics);
            }

            return wificonnectionstatistics;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiConnectionStatistics[] newArray(int i)
        {
            return new WifiConnectionStatistics[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "WifiConnnectionStatistics";
    public int num24GhzConnected;
    public int num5GhzConnected;
    public int numAutoJoinAttempt;
    public int numAutoRoamAttempt;
    public int numWifiManagerJoinAttempt;
    public HashMap untrustedNetworkHistory;

}
