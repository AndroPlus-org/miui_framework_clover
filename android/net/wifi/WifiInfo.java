// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.net.NetworkUtils;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.net.*;
import java.util.EnumMap;
import java.util.Locale;

// Referenced classes of package android.net.wifi:
//            SupplicantState, WifiSsid, ScanResult, WifiLinkLayerStats

public class WifiInfo
    implements Parcelable
{

    static String _2D_set0(WifiInfo wifiinfo, String s)
    {
        wifiinfo.mBSSID = s;
        return s;
    }

    static boolean _2D_set1(WifiInfo wifiinfo, boolean flag)
    {
        wifiinfo.mEphemeral = flag;
        return flag;
    }

    static String _2D_set2(WifiInfo wifiinfo, String s)
    {
        wifiinfo.mMacAddress = s;
        return s;
    }

    static boolean _2D_set3(WifiInfo wifiinfo, boolean flag)
    {
        wifiinfo.mMeteredHint = flag;
        return flag;
    }

    static SupplicantState _2D_set4(WifiInfo wifiinfo, SupplicantState supplicantstate)
    {
        wifiinfo.mSupplicantState = supplicantstate;
        return supplicantstate;
    }

    static String _2D_set5(WifiInfo wifiinfo, String s)
    {
        wifiinfo.mVendorInfo = s;
        return s;
    }

    static WifiSsid _2D_set6(WifiInfo wifiinfo, WifiSsid wifissid)
    {
        wifiinfo.mWifiSsid = wifissid;
        return wifissid;
    }

    public WifiInfo()
    {
        mMacAddress = "02:00:00:00:00:00";
        mWifiSsid = null;
        mBSSID = null;
        mNetworkId = -1;
        mSupplicantState = SupplicantState.UNINITIALIZED;
        mRssi = -127;
        mLinkSpeed = -1;
        mFrequency = -1;
        mLastPacketCountUpdateTimeStamp = 0x8000000000000000L;
    }

    public WifiInfo(WifiInfo wifiinfo)
    {
        mMacAddress = "02:00:00:00:00:00";
        if(wifiinfo != null)
        {
            mSupplicantState = wifiinfo.mSupplicantState;
            mBSSID = wifiinfo.mBSSID;
            mWifiSsid = wifiinfo.mWifiSsid;
            mNetworkId = wifiinfo.mNetworkId;
            mRssi = wifiinfo.mRssi;
            mLinkSpeed = wifiinfo.mLinkSpeed;
            mFrequency = wifiinfo.mFrequency;
            mIpAddress = wifiinfo.mIpAddress;
            mMacAddress = wifiinfo.mMacAddress;
            mMeteredHint = wifiinfo.mMeteredHint;
            mEphemeral = wifiinfo.mEphemeral;
            txBad = wifiinfo.txBad;
            txRetries = wifiinfo.txRetries;
            txSuccess = wifiinfo.txSuccess;
            rxSuccess = wifiinfo.rxSuccess;
            txBadRate = wifiinfo.txBadRate;
            txRetriesRate = wifiinfo.txRetriesRate;
            txSuccessRate = wifiinfo.txSuccessRate;
            rxSuccessRate = wifiinfo.rxSuccessRate;
            mLastPacketCountUpdateTimeStamp = wifiinfo.mLastPacketCountUpdateTimeStamp;
            score = wifiinfo.score;
            badRssiCount = wifiinfo.badRssiCount;
            lowRssiCount = wifiinfo.lowRssiCount;
            linkStuckCount = wifiinfo.linkStuckCount;
            mVendorInfo = wifiinfo.mVendorInfo;
        }
    }

    public static android.net.NetworkInfo.DetailedState getDetailedStateOf(SupplicantState supplicantstate)
    {
        return (android.net.NetworkInfo.DetailedState)stateMap.get(supplicantstate);
    }

    public static String removeDoubleQuotes(String s)
    {
        if(s == null)
            return null;
        int i = s.length();
        if(i > 1 && s.charAt(0) == '"' && s.charAt(i - 1) == '"')
            return s.substring(1, i - 1);
        else
            return s;
    }

    static SupplicantState valueOf(String s)
    {
        if("4WAY_HANDSHAKE".equalsIgnoreCase(s))
            return SupplicantState.FOUR_WAY_HANDSHAKE;
        try
        {
            s = SupplicantState.valueOf(s.toUpperCase(Locale.ROOT));
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return SupplicantState.INVALID;
        }
        return s;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getBSSID()
    {
        return mBSSID;
    }

    public int getFrequency()
    {
        return mFrequency;
    }

    public boolean getHiddenSSID()
    {
        if(mWifiSsid == null)
            return false;
        else
            return mWifiSsid.isHidden();
    }

    public int getIpAddress()
    {
        int i = 0;
        if(mIpAddress instanceof Inet4Address)
            i = NetworkUtils.inetAddressToInt((Inet4Address)mIpAddress);
        return i;
    }

    public int getLinkSpeed()
    {
        return mLinkSpeed;
    }

    public String getMacAddress()
    {
        return mMacAddress;
    }

    public boolean getMeteredHint()
    {
        return mMeteredHint;
    }

    public int getNetworkId()
    {
        return mNetworkId;
    }

    public int getRssi()
    {
        return mRssi;
    }

    public double getRxSuccessRatePps()
    {
        return rxSuccessRate / 5D;
    }

    public String getSSID()
    {
        if(mWifiSsid != null)
        {
            String s = mWifiSsid.toString();
            if(!TextUtils.isEmpty(s))
                return (new StringBuilder()).append("\"").append(s).append("\"").toString();
            s = mWifiSsid.getHexString();
            if(s == null)
                s = "<unknown ssid>";
            return s;
        } else
        {
            return "<unknown ssid>";
        }
    }

    public SupplicantState getSupplicantState()
    {
        return mSupplicantState;
    }

    public double getTxSuccessRatePps()
    {
        return txSuccessRate / 5D;
    }

    public String getVendorInfo()
    {
        return mVendorInfo;
    }

    public WifiSsid getWifiSsid()
    {
        return mWifiSsid;
    }

    public boolean hasRealMacAddress()
    {
        boolean flag;
        if(mMacAddress != null)
            flag = "02:00:00:00:00:00".equals(mMacAddress) ^ true;
        else
            flag = false;
        return flag;
    }

    public boolean is24GHz()
    {
        return ScanResult.is24GHz(mFrequency);
    }

    public boolean is5GHz()
    {
        return ScanResult.is5GHz(mFrequency);
    }

    public boolean isEphemeral()
    {
        return mEphemeral;
    }

    public void reset()
    {
        setInetAddress(null);
        setBSSID(null);
        setSSID(null);
        setNetworkId(-1);
        setRssi(-127);
        setLinkSpeed(-1);
        setFrequency(-1);
        setMeteredHint(false);
        setEphemeral(false);
        txBad = 0L;
        txSuccess = 0L;
        rxSuccess = 0L;
        txRetries = 0L;
        txBadRate = 0.0D;
        txSuccessRate = 0.0D;
        rxSuccessRate = 0.0D;
        txRetriesRate = 0.0D;
        lowRssiCount = 0;
        badRssiCount = 0;
        linkStuckCount = 0;
        score = 0;
        mLastPacketCountUpdateTimeStamp = 0x8000000000000000L;
        mVendorInfo = null;
    }

    public void setBSSID(String s)
    {
        mBSSID = s;
    }

    public void setEphemeral(boolean flag)
    {
        mEphemeral = flag;
    }

    public void setFrequency(int i)
    {
        mFrequency = i;
    }

    public void setInetAddress(InetAddress inetaddress)
    {
        mIpAddress = inetaddress;
    }

    public void setLinkSpeed(int i)
    {
        mLinkSpeed = i;
    }

    public void setMacAddress(String s)
    {
        mMacAddress = s;
    }

    public void setMeteredHint(boolean flag)
    {
        mMeteredHint = flag;
    }

    public void setNetworkId(int i)
    {
        mNetworkId = i;
    }

    public void setRssi(int i)
    {
        int j = i;
        if(i < -127)
            j = -127;
        i = j;
        if(j > 200)
            i = 200;
        mRssi = i;
    }

    public void setSSID(WifiSsid wifissid)
    {
        mWifiSsid = wifissid;
    }

    public void setSupplicantState(SupplicantState supplicantstate)
    {
        mSupplicantState = supplicantstate;
    }

    void setSupplicantState(String s)
    {
        mSupplicantState = valueOf(s);
    }

    public void setVendorInfo(String s)
    {
        mVendorInfo = s;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        String s = "<none>";
        StringBuffer stringbuffer1 = stringbuffer.append("SSID: ");
        Object obj;
        if(mWifiSsid == null)
            obj = "<unknown ssid>";
        else
            obj = mWifiSsid;
        stringbuffer1 = stringbuffer1.append(obj).append(", BSSID: ");
        if(mBSSID == null)
            obj = "<none>";
        else
            obj = mBSSID;
        stringbuffer1 = stringbuffer1.append(((String) (obj))).append(", MAC: ");
        if(mMacAddress == null)
            obj = "<none>";
        else
            obj = mMacAddress;
        stringbuffer1 = stringbuffer1.append(((String) (obj))).append(", Supplicant state: ");
        if(mSupplicantState == null)
            obj = s;
        else
            obj = mSupplicantState;
        stringbuffer1.append(obj).append(", RSSI: ").append(mRssi).append(", Link speed: ").append(mLinkSpeed).append("Mbps").append(", Frequency: ").append(mFrequency).append("MHz").append(", Net ID: ").append(mNetworkId).append(", Metered hint: ").append(mMeteredHint).append(", score: ").append(Integer.toString(score));
        return stringbuffer.toString();
    }

    public void updatePacketRates(long l, long l1)
    {
        txBad = 0L;
        txRetries = 0L;
        txBadRate = 0.0D;
        txRetriesRate = 0.0D;
        if(txSuccess <= l && rxSuccess <= l1)
        {
            txSuccessRate = txSuccessRate * 0.5D + (double)(l - txSuccess) * 0.5D;
            rxSuccessRate = rxSuccessRate * 0.5D + (double)(l1 - rxSuccess) * 0.5D;
        } else
        {
            txBadRate = 0.0D;
            txRetriesRate = 0.0D;
        }
        txSuccess = l;
        rxSuccess = l1;
    }

    public void updatePacketRates(WifiLinkLayerStats wifilinklayerstats, long l)
    {
        if(wifilinklayerstats != null)
        {
            long l1 = wifilinklayerstats.txmpdu_be + wifilinklayerstats.txmpdu_bk + wifilinklayerstats.txmpdu_vi + wifilinklayerstats.txmpdu_vo;
            long l2 = wifilinklayerstats.retries_be + wifilinklayerstats.retries_bk + wifilinklayerstats.retries_vi + wifilinklayerstats.retries_vo;
            long l3 = wifilinklayerstats.rxmpdu_be + wifilinklayerstats.rxmpdu_bk + wifilinklayerstats.rxmpdu_vi + wifilinklayerstats.rxmpdu_vo;
            long l4 = wifilinklayerstats.lostmpdu_be + wifilinklayerstats.lostmpdu_bk + wifilinklayerstats.lostmpdu_vi + wifilinklayerstats.lostmpdu_vo;
            if(mLastPacketCountUpdateTimeStamp != 0x8000000000000000L && mLastPacketCountUpdateTimeStamp < l && txBad <= l4 && txSuccess <= l1 && rxSuccess <= l3 && txRetries <= l2)
            {
                long l5 = l - mLastPacketCountUpdateTimeStamp;
                double d = Math.exp(((double)l5 * -1D) / 3000D);
                double d1 = 1.0D - d;
                txBadRate = txBadRate * d + (double)(((l4 - txBad) * 5L * 1000L) / l5) * d1;
                txSuccessRate = txSuccessRate * d + (double)(((l1 - txSuccess) * 5L * 1000L) / l5) * d1;
                rxSuccessRate = rxSuccessRate * d + (double)(((l3 - rxSuccess) * 5L * 1000L) / l5) * d1;
                txRetriesRate = txRetriesRate * d + (double)(((l2 - txRetries) * 5L * 1000L) / l5) * d1;
            } else
            {
                txBadRate = 0.0D;
                txSuccessRate = 0.0D;
                rxSuccessRate = 0.0D;
                txRetriesRate = 0.0D;
            }
            txBad = l4;
            txSuccess = l1;
            rxSuccess = l3;
            txRetries = l2;
            mLastPacketCountUpdateTimeStamp = l;
        } else
        {
            txBad = 0L;
            txSuccess = 0L;
            rxSuccess = 0L;
            txRetries = 0L;
            txBadRate = 0.0D;
            txSuccessRate = 0.0D;
            rxSuccessRate = 0.0D;
            txRetriesRate = 0.0D;
            mLastPacketCountUpdateTimeStamp = 0x8000000000000000L;
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(mNetworkId);
        parcel.writeInt(mRssi);
        parcel.writeInt(mLinkSpeed);
        parcel.writeInt(mFrequency);
        int j;
        if(mIpAddress != null)
        {
            parcel.writeByte((byte)1);
            parcel.writeByteArray(mIpAddress.getAddress());
        } else
        {
            parcel.writeByte((byte)0);
        }
        if(mWifiSsid != null)
        {
            parcel.writeInt(1);
            mWifiSsid.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeString(mBSSID);
        parcel.writeString(mMacAddress);
        if(mMeteredHint)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(mEphemeral)
            j = ((flag) ? 1 : 0);
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeInt(score);
        parcel.writeDouble(txSuccessRate);
        parcel.writeDouble(txRetriesRate);
        parcel.writeDouble(txBadRate);
        parcel.writeDouble(rxSuccessRate);
        parcel.writeInt(badRssiCount);
        parcel.writeInt(lowRssiCount);
        parcel.writeString(mVendorInfo);
        mSupplicantState.writeToParcel(parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiInfo createFromParcel(Parcel parcel)
        {
            boolean flag = true;
            WifiInfo wifiinfo = new WifiInfo();
            wifiinfo.setNetworkId(parcel.readInt());
            wifiinfo.setRssi(parcel.readInt());
            wifiinfo.setLinkSpeed(parcel.readInt());
            wifiinfo.setFrequency(parcel.readInt());
            boolean flag1;
            if(parcel.readByte() == 1)
                try
                {
                    wifiinfo.setInetAddress(InetAddress.getByAddress(parcel.createByteArray()));
                }
                catch(UnknownHostException unknownhostexception) { }
            if(parcel.readInt() == 1)
                WifiInfo._2D_set6(wifiinfo, (WifiSsid)WifiSsid.CREATOR.createFromParcel(parcel));
            WifiInfo._2D_set0(wifiinfo, parcel.readString());
            WifiInfo._2D_set2(wifiinfo, parcel.readString());
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            WifiInfo._2D_set3(wifiinfo, flag1);
            if(parcel.readInt() != 0)
                flag1 = flag;
            else
                flag1 = false;
            WifiInfo._2D_set1(wifiinfo, flag1);
            wifiinfo.score = parcel.readInt();
            wifiinfo.txSuccessRate = parcel.readDouble();
            wifiinfo.txRetriesRate = parcel.readDouble();
            wifiinfo.txBadRate = parcel.readDouble();
            wifiinfo.rxSuccessRate = parcel.readDouble();
            wifiinfo.badRssiCount = parcel.readInt();
            wifiinfo.lowRssiCount = parcel.readInt();
            WifiInfo._2D_set5(wifiinfo, parcel.readString());
            WifiInfo._2D_set4(wifiinfo, (SupplicantState)SupplicantState.CREATOR.createFromParcel(parcel));
            return wifiinfo;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiInfo[] newArray(int i)
        {
            return new WifiInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String DEFAULT_MAC_ADDRESS = "02:00:00:00:00:00";
    private static final long FILTER_TIME_CONSTANT = 3000L;
    public static final String FREQUENCY_UNITS = "MHz";
    public static final int INVALID_RSSI = -127;
    public static final String LINK_SPEED_UNITS = "Mbps";
    public static final int MAX_RSSI = 200;
    public static final int MIN_RSSI = -126;
    private static final long OUTPUT_SCALE_FACTOR = 5L;
    private static final long RESET_TIME_STAMP = 0x8000000000000000L;
    private static final String TAG = "WifiInfo";
    private static final EnumMap stateMap;
    public int badRssiCount;
    public int linkStuckCount;
    public int lowRssiCount;
    private String mBSSID;
    private boolean mEphemeral;
    private int mFrequency;
    private InetAddress mIpAddress;
    private long mLastPacketCountUpdateTimeStamp;
    private int mLinkSpeed;
    private String mMacAddress;
    private boolean mMeteredHint;
    private int mNetworkId;
    private int mRssi;
    private SupplicantState mSupplicantState;
    private String mVendorInfo;
    private WifiSsid mWifiSsid;
    public long rxSuccess;
    public double rxSuccessRate;
    public int score;
    public long txBad;
    public double txBadRate;
    public long txRetries;
    public double txRetriesRate;
    public long txSuccess;
    public double txSuccessRate;

    static 
    {
        stateMap = new EnumMap(android/net/wifi/SupplicantState);
        stateMap.put(SupplicantState.DISCONNECTED, android.net.NetworkInfo.DetailedState.DISCONNECTED);
        stateMap.put(SupplicantState.INTERFACE_DISABLED, android.net.NetworkInfo.DetailedState.DISCONNECTED);
        stateMap.put(SupplicantState.INACTIVE, android.net.NetworkInfo.DetailedState.IDLE);
        stateMap.put(SupplicantState.SCANNING, android.net.NetworkInfo.DetailedState.SCANNING);
        stateMap.put(SupplicantState.AUTHENTICATING, android.net.NetworkInfo.DetailedState.CONNECTING);
        stateMap.put(SupplicantState.ASSOCIATING, android.net.NetworkInfo.DetailedState.CONNECTING);
        stateMap.put(SupplicantState.ASSOCIATED, android.net.NetworkInfo.DetailedState.CONNECTING);
        stateMap.put(SupplicantState.FOUR_WAY_HANDSHAKE, android.net.NetworkInfo.DetailedState.AUTHENTICATING);
        stateMap.put(SupplicantState.GROUP_HANDSHAKE, android.net.NetworkInfo.DetailedState.AUTHENTICATING);
        stateMap.put(SupplicantState.COMPLETED, android.net.NetworkInfo.DetailedState.OBTAINING_IPADDR);
        stateMap.put(SupplicantState.DORMANT, android.net.NetworkInfo.DetailedState.DISCONNECTED);
        stateMap.put(SupplicantState.UNINITIALIZED, android.net.NetworkInfo.DetailedState.IDLE);
        stateMap.put(SupplicantState.INVALID, android.net.NetworkInfo.DetailedState.FAILED);
    }
}
