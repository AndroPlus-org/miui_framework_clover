// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.net.wifi:
//            WifiSsid, AnqpInformationElement

public class ScanResult
    implements Parcelable
{
    public static class InformationElement
    {

        public static final int EID_BSS_LOAD = 11;
        public static final int EID_ERP = 42;
        public static final int EID_EXTENDED_CAPS = 127;
        public static final int EID_EXTENDED_SUPPORTED_RATES = 50;
        public static final int EID_HT_OPERATION = 61;
        public static final int EID_INTERWORKING = 107;
        public static final int EID_ROAMING_CONSORTIUM = 111;
        public static final int EID_RSN = 48;
        public static final int EID_SSID = 0;
        public static final int EID_SUPPORTED_RATES = 1;
        public static final int EID_TIM = 5;
        public static final int EID_VHT_OPERATION = 192;
        public static final int EID_VSA = 221;
        public static final int EID_WAPI = 68;
        public byte bytes[];
        public int id;

        public InformationElement()
        {
        }

        public InformationElement(InformationElement informationelement)
        {
            id = informationelement.id;
            bytes = (byte[])informationelement.bytes.clone();
        }
    }


    public ScanResult()
    {
    }

    public ScanResult(ScanResult scanresult)
    {
        if(scanresult != null)
        {
            wifiSsid = scanresult.wifiSsid;
            SSID = scanresult.SSID;
            BSSID = scanresult.BSSID;
            hessid = scanresult.hessid;
            anqpDomainId = scanresult.anqpDomainId;
            informationElements = scanresult.informationElements;
            anqpElements = scanresult.anqpElements;
            capabilities = scanresult.capabilities;
            level = scanresult.level;
            frequency = scanresult.frequency;
            channelWidth = scanresult.channelWidth;
            centerFreq0 = scanresult.centerFreq0;
            centerFreq1 = scanresult.centerFreq1;
            timestamp = scanresult.timestamp;
            distanceCm = scanresult.distanceCm;
            distanceSdCm = scanresult.distanceSdCm;
            seen = scanresult.seen;
            untrusted = scanresult.untrusted;
            numConnection = scanresult.numConnection;
            numUsage = scanresult.numUsage;
            numIpConfigFailures = scanresult.numIpConfigFailures;
            venueName = scanresult.venueName;
            operatorFriendlyName = scanresult.operatorFriendlyName;
            flags = scanresult.flags;
            isCarrierAp = scanresult.isCarrierAp;
            carrierApEapType = scanresult.carrierApEapType;
            carrierName = scanresult.carrierName;
        }
    }

    public ScanResult(WifiSsid wifissid, String s, long l, int i, byte abyte0[], String s1, 
            int j, int k, long l1)
    {
        wifiSsid = wifissid;
        if(wifissid != null)
            wifissid = wifissid.toString();
        else
            wifissid = "<unknown ssid>";
        SSID = wifissid;
        BSSID = s;
        hessid = l;
        anqpDomainId = i;
        if(abyte0 != null)
        {
            anqpElements = new AnqpInformationElement[1];
            anqpElements[0] = new AnqpInformationElement(0x506f9a, 8, abyte0);
        }
        capabilities = s1;
        level = j;
        frequency = k;
        timestamp = l1;
        distanceCm = -1;
        distanceSdCm = -1;
        channelWidth = -1;
        centerFreq0 = -1;
        centerFreq1 = -1;
        flags = 0L;
        isCarrierAp = false;
        carrierApEapType = -1;
        carrierName = null;
    }

    public ScanResult(WifiSsid wifissid, String s, String s1, int i, int j, long l, 
            int k, int i1)
    {
        wifiSsid = wifissid;
        if(wifissid != null)
            wifissid = wifissid.toString();
        else
            wifissid = "<unknown ssid>";
        SSID = wifissid;
        BSSID = s;
        capabilities = s1;
        level = i;
        frequency = j;
        timestamp = l;
        distanceCm = k;
        distanceSdCm = i1;
        channelWidth = -1;
        centerFreq0 = -1;
        centerFreq1 = -1;
        flags = 0L;
        isCarrierAp = false;
        carrierApEapType = -1;
        carrierName = null;
    }

    public ScanResult(WifiSsid wifissid, String s, String s1, long l, int i, String s2, 
            int j, int k, long l1, int i1, int j1, int k1, 
            int i2, int j2, boolean flag)
    {
        this(s, s1, l, i, s2, j, k, l1, i1, j1, k1, i2, j2, flag);
        wifiSsid = wifissid;
    }

    public ScanResult(String s, String s1, long l, int i, String s2, int j, 
            int k, long l1, int i1, int j1, int k1, int i2, 
            int j2, boolean flag)
    {
        SSID = s;
        BSSID = s1;
        hessid = l;
        anqpDomainId = i;
        capabilities = s2;
        level = j;
        frequency = k;
        timestamp = l1;
        distanceCm = i1;
        distanceSdCm = j1;
        channelWidth = k1;
        centerFreq0 = i2;
        centerFreq1 = j2;
        if(flag)
            flags = 2L;
        else
            flags = 0L;
        isCarrierAp = false;
        carrierApEapType = -1;
        carrierName = null;
    }

    public static boolean is24GHz(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i > 2400)
        {
            flag1 = flag;
            if(i < 2500)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean is5GHz(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i > 4900)
        {
            flag1 = flag;
            if(i < 5900)
                flag1 = true;
        }
        return flag1;
    }

    public void averageRssi(int i, long l, int j)
    {
        if(seen == 0L)
            seen = System.currentTimeMillis();
        long l1 = seen - l;
        if(l > 0L && l1 > 0L && l1 < (long)(j / 2))
        {
            double d = 0.5D - (double)l1 / (double)j;
            level = (int)((double)level * (1.0D - d) + (double)i * d);
        }
    }

    public void clearFlag(long l)
    {
        flags = flags & l;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean is24GHz()
    {
        return is24GHz(frequency);
    }

    public boolean is5GHz()
    {
        return is5GHz(frequency);
    }

    public boolean is80211mcResponder()
    {
        boolean flag;
        if((flags & 2L) != 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isPasspointNetwork()
    {
        boolean flag;
        if((flags & 1L) != 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void setFlag(long l)
    {
        flags = flags | l;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        Object obj = "<none>";
        StringBuffer stringbuffer1 = stringbuffer.append("SSID: ");
        Object obj1;
        if(wifiSsid == null)
            obj1 = "<unknown ssid>";
        else
            obj1 = wifiSsid;
        stringbuffer1 = stringbuffer1.append(obj1).append(", BSSID: ");
        if(BSSID == null)
            obj1 = "<none>";
        else
            obj1 = BSSID;
        stringbuffer1 = stringbuffer1.append(((String) (obj1))).append(", capabilities: ");
        if(capabilities == null)
            obj1 = obj;
        else
            obj1 = capabilities;
        stringbuffer1.append(((String) (obj1))).append(", level: ").append(level).append(", frequency: ").append(frequency).append(", timestamp: ").append(timestamp);
        obj = stringbuffer.append(", distance: ");
        if(distanceCm != -1)
            obj1 = Integer.valueOf(distanceCm);
        else
            obj1 = "?";
        ((StringBuffer) (obj)).append(obj1).append("(cm)");
        obj = stringbuffer.append(", distanceSd: ");
        if(distanceSdCm != -1)
            obj1 = Integer.valueOf(distanceSdCm);
        else
            obj1 = "?";
        ((StringBuffer) (obj)).append(obj1).append("(cm)");
        stringbuffer.append(", passpoint: ");
        if((flags & 1L) != 0L)
            obj1 = "yes";
        else
            obj1 = "no";
        stringbuffer.append(((String) (obj1)));
        stringbuffer.append(", ChannelBandwidth: ").append(channelWidth);
        stringbuffer.append(", centerFreq0: ").append(centerFreq0);
        stringbuffer.append(", centerFreq1: ").append(centerFreq1);
        stringbuffer.append(", 80211mcResponder: ");
        if((flags & 2L) != 0L)
            obj1 = "is supported";
        else
            obj1 = "is not supported";
        stringbuffer.append(((String) (obj1)));
        obj = stringbuffer.append(", Carrier AP: ");
        if(isCarrierAp)
            obj1 = "yes";
        else
            obj1 = "no";
        ((StringBuffer) (obj)).append(((String) (obj1)));
        stringbuffer.append(", Carrier AP EAP Type: ").append(carrierApEapType);
        stringbuffer.append(", Carrier name: ").append(carrierName);
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        String s;
        if(wifiSsid != null)
        {
            parcel.writeInt(1);
            wifiSsid.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeString(SSID);
        parcel.writeString(BSSID);
        parcel.writeLong(hessid);
        parcel.writeInt(anqpDomainId);
        parcel.writeString(capabilities);
        parcel.writeInt(level);
        parcel.writeInt(frequency);
        parcel.writeLong(timestamp);
        parcel.writeInt(distanceCm);
        parcel.writeInt(distanceSdCm);
        parcel.writeInt(channelWidth);
        parcel.writeInt(centerFreq0);
        parcel.writeInt(centerFreq1);
        parcel.writeLong(seen);
        if(untrusted)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(numConnection);
        parcel.writeInt(numUsage);
        parcel.writeInt(numIpConfigFailures);
        if(venueName != null)
            s = venueName.toString();
        else
            s = "";
        parcel.writeString(s);
        if(operatorFriendlyName != null)
            s = operatorFriendlyName.toString();
        else
            s = "";
        parcel.writeString(s);
        parcel.writeLong(flags);
        if(informationElements != null)
        {
            parcel.writeInt(informationElements.length);
            for(i = 0; i < informationElements.length; i++)
            {
                parcel.writeInt(informationElements[i].id);
                parcel.writeInt(informationElements[i].bytes.length);
                parcel.writeByteArray(informationElements[i].bytes);
            }

        } else
        {
            parcel.writeInt(0);
        }
        if(anqpLines != null)
        {
            parcel.writeInt(anqpLines.size());
            for(i = 0; i < anqpLines.size(); i++)
                parcel.writeString((String)anqpLines.get(i));

        } else
        {
            parcel.writeInt(0);
        }
        if(anqpElements != null)
        {
            parcel.writeInt(anqpElements.length);
            AnqpInformationElement aanqpinformationelement[] = anqpElements;
            int j = aanqpinformationelement.length;
            for(i = 0; i < j; i++)
            {
                AnqpInformationElement anqpinformationelement = aanqpinformationelement[i];
                parcel.writeInt(anqpinformationelement.getVendorId());
                parcel.writeInt(anqpinformationelement.getElementId());
                parcel.writeInt(anqpinformationelement.getPayload().length);
                parcel.writeByteArray(anqpinformationelement.getPayload());
            }

        } else
        {
            parcel.writeInt(0);
        }
        if(isCarrierAp)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(carrierApEapType);
        parcel.writeString(carrierName);
    }

    public static final int CHANNEL_WIDTH_160MHZ = 3;
    public static final int CHANNEL_WIDTH_20MHZ = 0;
    public static final int CHANNEL_WIDTH_40MHZ = 1;
    public static final int CHANNEL_WIDTH_80MHZ = 2;
    public static final int CHANNEL_WIDTH_80MHZ_PLUS_MHZ = 4;
    public static final int CIPHER_CCMP = 3;
    public static final int CIPHER_NONE = 0;
    public static final int CIPHER_NO_GROUP_ADDRESSED = 1;
    public static final int CIPHER_SMS4 = 4;
    public static final int CIPHER_TKIP = 2;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ScanResult createFromParcel(Parcel parcel)
        {
            WifiSsid wifissid = null;
            if(parcel.readInt() == 1)
                wifissid = (WifiSsid)WifiSsid.CREATOR.createFromParcel(parcel);
            ScanResult scanresult = new ScanResult(wifissid, parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readLong(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), false);
            scanresult.seen = parcel.readLong();
            boolean flag;
            int i;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            scanresult.untrusted = flag;
            scanresult.numConnection = parcel.readInt();
            scanresult.numUsage = parcel.readInt();
            scanresult.numIpConfigFailures = parcel.readInt();
            scanresult.venueName = parcel.readString();
            scanresult.operatorFriendlyName = parcel.readString();
            scanresult.flags = parcel.readLong();
            i = parcel.readInt();
            if(i != 0)
            {
                scanresult.informationElements = new InformationElement[i];
                for(int j = 0; j < i; j++)
                {
                    scanresult.informationElements[j] = new InformationElement();
                    scanresult.informationElements[j].id = parcel.readInt();
                    int i1 = parcel.readInt();
                    scanresult.informationElements[j].bytes = new byte[i1];
                    parcel.readByteArray(scanresult.informationElements[j].bytes);
                }

            }
            i = parcel.readInt();
            if(i != 0)
            {
                scanresult.anqpLines = new ArrayList();
                for(int k = 0; k < i; k++)
                    scanresult.anqpLines.add(parcel.readString());

            }
            i = parcel.readInt();
            if(i != 0)
            {
                scanresult.anqpElements = new AnqpInformationElement[i];
                for(int l = 0; l < i; l++)
                {
                    int k1 = parcel.readInt();
                    int j1 = parcel.readInt();
                    byte abyte0[] = new byte[parcel.readInt()];
                    parcel.readByteArray(abyte0);
                    scanresult.anqpElements[l] = new AnqpInformationElement(k1, j1, abyte0);
                }

            }
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            scanresult.isCarrierAp = flag;
            scanresult.carrierApEapType = parcel.readInt();
            scanresult.carrierName = parcel.readString();
            return scanresult;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ScanResult[] newArray(int i)
        {
            return new ScanResult[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final long FLAG_80211mc_RESPONDER = 2L;
    public static final long FLAG_PASSPOINT_NETWORK = 1L;
    public static final int KEY_MGMT_EAP = 2;
    public static final int KEY_MGMT_EAP_SHA256 = 6;
    public static final int KEY_MGMT_FILS_SHA256 = 8;
    public static final int KEY_MGMT_FILS_SHA384 = 9;
    public static final int KEY_MGMT_FT_EAP = 4;
    public static final int KEY_MGMT_FT_PSK = 3;
    public static final int KEY_MGMT_NONE = 0;
    public static final int KEY_MGMT_OSEN = 7;
    public static final int KEY_MGMT_PSK = 1;
    public static final int KEY_MGMT_PSK_SHA256 = 5;
    public static final int KEY_MGMT_WAPI_CERT = 11;
    public static final int KEY_MGMT_WAPI_PSK = 10;
    public static final int PROTOCOL_NONE = 0;
    public static final int PROTOCOL_OSEN = 3;
    public static final int PROTOCOL_WAPI = 4;
    public static final int PROTOCOL_WPA = 1;
    public static final int PROTOCOL_WPA2 = 2;
    public static final int UNSPECIFIED = -1;
    public String BSSID;
    public String SSID;
    public int anqpDomainId;
    public AnqpInformationElement anqpElements[];
    public List anqpLines;
    public long blackListTimestamp;
    public byte bytes[];
    public String capabilities;
    public int carrierApEapType;
    public String carrierName;
    public int centerFreq0;
    public int centerFreq1;
    public int channelWidth;
    public int distanceCm;
    public int distanceSdCm;
    public long flags;
    public int frequency;
    public long hessid;
    public InformationElement informationElements[];
    public boolean is80211McRTTResponder;
    public boolean isCarrierAp;
    public int level;
    public int numConnection;
    public int numIpConfigFailures;
    public int numUsage;
    public CharSequence operatorFriendlyName;
    public long seen;
    public long timestamp;
    public boolean untrusted;
    public CharSequence venueName;
    public WifiSsid wifiSsid;

}
