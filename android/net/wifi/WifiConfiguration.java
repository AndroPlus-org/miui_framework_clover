// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.net.*;
import android.os.*;
import android.text.TextUtils;
import android.util.BackupUtils;
import java.io.*;
import java.util.*;

// Referenced classes of package android.net.wifi:
//            WifiEnterpriseConfig, ScanResult, WifiInfo, WifiSsid

public class WifiConfiguration
    implements Parcelable
{
    public static class AuthAlgorithm
    {

        public static final int LEAP = 2;
        public static final int OPEN = 0;
        public static final int SHARED = 1;
        public static final String strings[] = {
            "OPEN", "SHARED", "LEAP"
        };
        public static final String varName = "auth_alg";


        private AuthAlgorithm()
        {
        }
    }

    public static class GroupCipher
    {

        public static final int CCMP = 3;
        public static final int GTK_NOT_USED = 4;
        public static final int TKIP = 2;
        public static final int WEP104 = 1;
        public static final int WEP40 = 0;
        public static final String strings[] = {
            "WEP40", "WEP104", "TKIP", "CCMP", "GTK_NOT_USED", "SMS4"
        };
        public static final String varName = "group";


        private GroupCipher()
        {
        }
    }

    public static class KeyMgmt
    {

        public static final int FILS_SHA256 = 8;
        public static final int FILS_SHA384 = 9;
        public static final int FT_EAP = 7;
        public static final int FT_PSK = 6;
        public static final int IEEE8021X = 3;
        public static final int NONE = 0;
        public static final int OSEN = 5;
        public static final int WAPI_CERT = 11;
        public static final int WAPI_PSK = 10;
        public static final int WPA2_PSK = 4;
        public static final int WPA_EAP = 2;
        public static final int WPA_PSK = 1;
        public static final String strings[] = {
            "NONE", "WPA_PSK", "WPA_EAP", "IEEE8021X", "WPA2_PSK", "OSEN", "FT_PSK", "FT_EAP", "FILS_SHA256", "FILS_SHA384", 
            "WAPI_PSK", "WAPI_CERT"
        };
        public static final String varName = "key_mgmt";


        private KeyMgmt()
        {
        }
    }

    public static class NetworkSelectionStatus
    {

        public static String getNetworkDisableReasonString(int i)
        {
            if(i >= 0 && i < 13)
                return QUALITY_NETWORK_SELECTION_DISABLE_REASON[i];
            else
                return null;
        }

        public void clearDisableReasonCounter()
        {
            Arrays.fill(mNetworkSeclectionDisableCounter, 0);
        }

        public void clearDisableReasonCounter(int i)
        {
            if(i >= 0 && i < 13)
            {
                mNetworkSeclectionDisableCounter[i] = 0;
                return;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Illegal reason value: ").append(i).toString());
            }
        }

        public void copy(NetworkSelectionStatus networkselectionstatus)
        {
            mStatus = networkselectionstatus.mStatus;
            mNetworkSelectionDisableReason = networkselectionstatus.mNetworkSelectionDisableReason;
            for(int i = 0; i < 13; i++)
                mNetworkSeclectionDisableCounter[i] = networkselectionstatus.mNetworkSeclectionDisableCounter[i];

            mTemporarilyDisabledTimestamp = networkselectionstatus.mTemporarilyDisabledTimestamp;
            mNetworkSelectionBSSID = networkselectionstatus.mNetworkSelectionBSSID;
            setSeenInLastQualifiedNetworkSelection(networkselectionstatus.getSeenInLastQualifiedNetworkSelection());
            setCandidate(networkselectionstatus.getCandidate());
            setCandidateScore(networkselectionstatus.getCandidateScore());
            setConnectChoice(networkselectionstatus.getConnectChoice());
            setConnectChoiceTimestamp(networkselectionstatus.getConnectChoiceTimestamp());
            setHasEverConnected(networkselectionstatus.getHasEverConnected());
            setNotRecommended(networkselectionstatus.isNotRecommended());
        }

        public ScanResult getCandidate()
        {
            return mCandidate;
        }

        public int getCandidateScore()
        {
            return mCandidateScore;
        }

        public String getConnectChoice()
        {
            return mConnectChoice;
        }

        public long getConnectChoiceTimestamp()
        {
            return mConnectChoiceTimestamp;
        }

        public int getDisableReasonCounter(int i)
        {
            if(i >= 0 && i < 13)
                return mNetworkSeclectionDisableCounter[i];
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Illegal reason value: ").append(i).toString());
        }

        public long getDisableTime()
        {
            return mTemporarilyDisabledTimestamp;
        }

        public boolean getHasEverConnected()
        {
            return mHasEverConnected;
        }

        public String getNetworkDisableReasonString()
        {
            return QUALITY_NETWORK_SELECTION_DISABLE_REASON[mNetworkSelectionDisableReason];
        }

        public String getNetworkSelectionBSSID()
        {
            return mNetworkSelectionBSSID;
        }

        public int getNetworkSelectionDisableReason()
        {
            return mNetworkSelectionDisableReason;
        }

        public int getNetworkSelectionStatus()
        {
            return mStatus;
        }

        public String getNetworkStatusString()
        {
            return QUALITY_NETWORK_SELECTION_STATUS[mStatus];
        }

        public boolean getSeenInLastQualifiedNetworkSelection()
        {
            return mSeenInLastQualifiedNetworkSelection;
        }

        public void incrementDisableReasonCounter(int i)
        {
            if(i >= 0 && i < 13)
            {
                int ai[] = mNetworkSeclectionDisableCounter;
                ai[i] = ai[i] + 1;
                return;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Illegal reason value: ").append(i).toString());
            }
        }

        public boolean isDisabledByReason(int i)
        {
            boolean flag;
            if(mNetworkSelectionDisableReason == i)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean isNetworkEnabled()
        {
            boolean flag = false;
            if(mStatus == 0)
                flag = true;
            return flag;
        }

        public boolean isNetworkPermanentlyDisabled()
        {
            boolean flag;
            if(mStatus == 2)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public boolean isNetworkTemporaryDisabled()
        {
            boolean flag = true;
            if(mStatus != 1)
                flag = false;
            return flag;
        }

        public boolean isNotRecommended()
        {
            return mNotRecommended;
        }

        public void readFromParcel(Parcel parcel)
        {
            boolean flag = true;
            setNetworkSelectionStatus(parcel.readInt());
            setNetworkSelectionDisableReason(parcel.readInt());
            for(int i = 0; i < 13; i++)
                setDisableReasonCounter(i, parcel.readInt());

            setDisableTime(parcel.readLong());
            setNetworkSelectionBSSID(parcel.readString());
            boolean flag1;
            if(parcel.readInt() == 1)
            {
                setConnectChoice(parcel.readString());
                setConnectChoiceTimestamp(parcel.readLong());
            } else
            {
                setConnectChoice(null);
                setConnectChoiceTimestamp(-1L);
            }
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            setHasEverConnected(flag1);
            if(parcel.readInt() != 0)
                flag1 = flag;
            else
                flag1 = false;
            setNotRecommended(flag1);
        }

        public void setCandidate(ScanResult scanresult)
        {
            mCandidate = scanresult;
        }

        public void setCandidateScore(int i)
        {
            mCandidateScore = i;
        }

        public void setConnectChoice(String s)
        {
            mConnectChoice = s;
        }

        public void setConnectChoiceTimestamp(long l)
        {
            mConnectChoiceTimestamp = l;
        }

        public void setDisableReasonCounter(int i, int j)
        {
            if(i >= 0 && i < 13)
            {
                mNetworkSeclectionDisableCounter[i] = j;
                return;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Illegal reason value: ").append(i).toString());
            }
        }

        public void setDisableTime(long l)
        {
            mTemporarilyDisabledTimestamp = l;
        }

        public void setHasEverConnected(boolean flag)
        {
            mHasEverConnected = flag;
        }

        public void setNetworkSelectionBSSID(String s)
        {
            mNetworkSelectionBSSID = s;
        }

        public void setNetworkSelectionDisableReason(int i)
        {
            if(i >= 0 && i < 13)
            {
                mNetworkSelectionDisableReason = i;
                return;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Illegal reason value: ").append(i).toString());
            }
        }

        public void setNetworkSelectionStatus(int i)
        {
            if(i >= 0 && i < 3)
                mStatus = i;
        }

        public void setNotRecommended(boolean flag)
        {
            mNotRecommended = flag;
        }

        public void setSeenInLastQualifiedNetworkSelection(boolean flag)
        {
            mSeenInLastQualifiedNetworkSelection = flag;
        }

        public void writeToParcel(Parcel parcel)
        {
            boolean flag = true;
            parcel.writeInt(getNetworkSelectionStatus());
            parcel.writeInt(getNetworkSelectionDisableReason());
            for(int i = 0; i < 13; i++)
                parcel.writeInt(getDisableReasonCounter(i));

            parcel.writeLong(getDisableTime());
            parcel.writeString(getNetworkSelectionBSSID());
            int j;
            if(getConnectChoice() != null)
            {
                parcel.writeInt(1);
                parcel.writeString(getConnectChoice());
                parcel.writeLong(getConnectChoiceTimestamp());
            } else
            {
                parcel.writeInt(-1);
            }
            if(getHasEverConnected())
                j = 1;
            else
                j = 0;
            parcel.writeInt(j);
            if(isNotRecommended())
                j = ((flag) ? 1 : 0);
            else
                j = 0;
            parcel.writeInt(j);
        }

        private static final int CONNECT_CHOICE_EXISTS = 1;
        private static final int CONNECT_CHOICE_NOT_EXISTS = -1;
        public static final int DISABLED_ASSOCIATION_REJECTION = 2;
        public static final int DISABLED_AUTHENTICATION_FAILURE = 3;
        public static final int DISABLED_AUTHENTICATION_NO_CREDENTIALS = 8;
        public static final int DISABLED_BAD_LINK = 1;
        public static final int DISABLED_BY_WIFI_MANAGER = 10;
        public static final int DISABLED_BY_WRONG_PASSWORD = 12;
        public static final int DISABLED_DHCP_FAILURE = 4;
        public static final int DISABLED_DNS_FAILURE = 5;
        public static final int DISABLED_DUE_TO_USER_SWITCH = 11;
        public static final int DISABLED_NO_INTERNET = 9;
        public static final int DISABLED_TLS_VERSION_MISMATCH = 7;
        public static final int DISABLED_WPS_START = 6;
        public static final long INVALID_NETWORK_SELECTION_DISABLE_TIMESTAMP = -1L;
        public static final int NETWORK_SELECTION_DISABLED_MAX = 13;
        public static final int NETWORK_SELECTION_DISABLED_STARTING_INDEX = 1;
        public static final int NETWORK_SELECTION_ENABLE = 0;
        public static final int NETWORK_SELECTION_ENABLED = 0;
        public static final int NETWORK_SELECTION_PERMANENTLY_DISABLED = 2;
        public static final int NETWORK_SELECTION_STATUS_MAX = 3;
        public static final int NETWORK_SELECTION_TEMPORARY_DISABLED = 1;
        public static final String QUALITY_NETWORK_SELECTION_DISABLE_REASON[] = {
            "NETWORK_SELECTION_ENABLE", "NETWORK_SELECTION_DISABLED_BAD_LINK", "NETWORK_SELECTION_DISABLED_ASSOCIATION_REJECTION ", "NETWORK_SELECTION_DISABLED_AUTHENTICATION_FAILURE", "NETWORK_SELECTION_DISABLED_DHCP_FAILURE", "NETWORK_SELECTION_DISABLED_DNS_FAILURE", "NETWORK_SELECTION_DISABLED_WPS_START", "NETWORK_SELECTION_DISABLED_TLS_VERSION", "NETWORK_SELECTION_DISABLED_AUTHENTICATION_NO_CREDENTIALS", "NETWORK_SELECTION_DISABLED_NO_INTERNET", 
            "NETWORK_SELECTION_DISABLED_BY_WIFI_MANAGER", "NETWORK_SELECTION_DISABLED_BY_USER_SWITCH", "NETWORK_SELECTION_DISABLED_BY_WRONG_PASSWORD"
        };
        public static final String QUALITY_NETWORK_SELECTION_STATUS[] = {
            "NETWORK_SELECTION_ENABLED", "NETWORK_SELECTION_TEMPORARY_DISABLED", "NETWORK_SELECTION_PERMANENTLY_DISABLED"
        };
        private ScanResult mCandidate;
        private int mCandidateScore;
        private String mConnectChoice;
        private long mConnectChoiceTimestamp;
        private boolean mHasEverConnected;
        private int mNetworkSeclectionDisableCounter[];
        private String mNetworkSelectionBSSID;
        private int mNetworkSelectionDisableReason;
        private boolean mNotRecommended;
        private boolean mSeenInLastQualifiedNetworkSelection;
        private int mStatus;
        private long mTemporarilyDisabledTimestamp;


        public NetworkSelectionStatus()
        {
            mTemporarilyDisabledTimestamp = -1L;
            mNetworkSeclectionDisableCounter = new int[13];
            mConnectChoiceTimestamp = -1L;
            mHasEverConnected = false;
        }
    }

    public static class PairwiseCipher
    {

        public static final int CCMP = 2;
        public static final int NONE = 0;
        public static final int TKIP = 1;
        public static final String strings[] = {
            "NONE", "TKIP", "CCMP", "SMS4"
        };
        public static final String varName = "pairwise";


        private PairwiseCipher()
        {
        }
    }

    public static class Protocol
    {

        public static final int OSEN = 2;
        public static final int RSN = 1;
        public static final int WAPI = 3;
        public static final int WPA = 0;
        public static final String strings[] = {
            "WPA", "RSN", "OSEN", "WAPI"
        };
        public static final String varName = "proto";


        private Protocol()
        {
        }
    }

    public static class RecentFailure
    {

        public void clear()
        {
            mAssociationStatus = 0;
        }

        public int getAssociationStatus()
        {
            return mAssociationStatus;
        }

        public void setAssociationStatus(int i)
        {
            mAssociationStatus = i;
        }

        public static final int NONE = 0;
        public static final int STATUS_AP_UNABLE_TO_HANDLE_NEW_STA = 17;
        private int mAssociationStatus;

        public RecentFailure()
        {
            mAssociationStatus = 0;
        }
    }

    public static class Status
    {

        public static final int CURRENT = 0;
        public static final int DISABLED = 1;
        public static final int ENABLED = 2;
        public static final String strings[] = {
            "current", "disabled", "enabled"
        };


        private Status()
        {
        }
    }

    public static final class Visibility
    {

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("[");
            if(rssi24 > WifiConfiguration.INVALID_RSSI)
            {
                stringbuilder.append(Integer.toString(rssi24));
                stringbuilder.append(",");
                stringbuilder.append(Integer.toString(num24));
                if(BSSID24 != null)
                    stringbuilder.append(",").append(BSSID24);
            }
            stringbuilder.append("; ");
            if(rssi5 > WifiConfiguration.INVALID_RSSI)
            {
                stringbuilder.append(Integer.toString(rssi5));
                stringbuilder.append(",");
                stringbuilder.append(Integer.toString(num5));
                if(BSSID5 != null)
                    stringbuilder.append(",").append(BSSID5);
            }
            if(score != 0)
            {
                stringbuilder.append("; ").append(score);
                stringbuilder.append(", ").append(currentNetworkBoost);
                stringbuilder.append(", ").append(bandPreferenceBoost);
                if(lastChoiceConfig != null)
                {
                    stringbuilder.append(", ").append(lastChoiceBoost);
                    stringbuilder.append(", ").append(lastChoiceConfig);
                }
            }
            stringbuilder.append("]");
            return stringbuilder.toString();
        }

        public String BSSID24;
        public String BSSID5;
        public long age24;
        public long age5;
        public int bandPreferenceBoost;
        public int currentNetworkBoost;
        public int lastChoiceBoost;
        public String lastChoiceConfig;
        public int num24;
        public int num5;
        public int rssi24;
        public int rssi5;
        public int score;

        public Visibility()
        {
            rssi5 = WifiConfiguration.INVALID_RSSI;
            rssi24 = WifiConfiguration.INVALID_RSSI;
        }

        public Visibility(Visibility visibility1)
        {
            rssi5 = visibility1.rssi5;
            rssi24 = visibility1.rssi24;
            age24 = visibility1.age24;
            age5 = visibility1.age5;
            num24 = visibility1.num24;
            num5 = visibility1.num5;
            BSSID5 = visibility1.BSSID5;
            BSSID24 = visibility1.BSSID24;
        }
    }


    static NetworkSelectionStatus _2D_get0(WifiConfiguration wificonfiguration)
    {
        return wificonfiguration.mNetworkSelectionStatus;
    }

    static IpConfiguration _2D_set0(WifiConfiguration wificonfiguration, IpConfiguration ipconfiguration)
    {
        wificonfiguration.mIpConfiguration = ipconfiguration;
        return ipconfiguration;
    }

    static String _2D_set1(WifiConfiguration wificonfiguration, String s)
    {
        wificonfiguration.mPasspointManagementObjectTree = s;
        return s;
    }

    static BitSet _2D_wrap0(Parcel parcel)
    {
        return readBitSet(parcel);
    }

    public WifiConfiguration()
    {
        apBand = 0;
        apChannel = 0;
        dtimInterval = 0;
        isLegacyPasspointConfig = false;
        userApproved = 0;
        roamingFailureBlackListTimeMilli = 1000L;
        meteredOverride = 0;
        mNetworkSelectionStatus = new NetworkSelectionStatus();
        recentFailure = new RecentFailure();
        networkId = -1;
        SSID = null;
        BSSID = null;
        FQDN = null;
        roamingConsortiumIds = new long[0];
        priority = 0;
        hiddenSSID = false;
        shareThisAp = false;
        allowedKeyManagement = new BitSet();
        allowedProtocols = new BitSet();
        allowedAuthAlgorithms = new BitSet();
        allowedPairwiseCiphers = new BitSet();
        allowedGroupCiphers = new BitSet();
        wepKeys = new String[4];
        for(int i = 0; i < wepKeys.length; i++)
            wepKeys[i] = null;

        enterpriseConfig = new WifiEnterpriseConfig();
        selfAdded = false;
        didSelfAdd = false;
        ephemeral = false;
        meteredHint = false;
        meteredOverride = 0;
        useExternalScores = false;
        validatedInternetAccess = false;
        mIpConfiguration = new IpConfiguration();
        wapiPskType = -1;
        wapiPsk = null;
        wapiCertSelMode = -1;
        wapiCertSel = null;
        lastUpdateUid = -1;
        creatorUid = -1;
        shared = true;
        dtimInterval = 0;
        SIMNum = 0;
    }

    public WifiConfiguration(WifiConfiguration wificonfiguration)
    {
        apBand = 0;
        apChannel = 0;
        dtimInterval = 0;
        isLegacyPasspointConfig = false;
        userApproved = 0;
        roamingFailureBlackListTimeMilli = 1000L;
        meteredOverride = 0;
        mNetworkSelectionStatus = new NetworkSelectionStatus();
        recentFailure = new RecentFailure();
        if(wificonfiguration != null)
        {
            networkId = wificonfiguration.networkId;
            status = wificonfiguration.status;
            SSID = wificonfiguration.SSID;
            BSSID = wificonfiguration.BSSID;
            FQDN = wificonfiguration.FQDN;
            shareThisAp = wificonfiguration.shareThisAp;
            roamingConsortiumIds = (long[])wificonfiguration.roamingConsortiumIds.clone();
            providerFriendlyName = wificonfiguration.providerFriendlyName;
            isHomeProviderNetwork = wificonfiguration.isHomeProviderNetwork;
            preSharedKey = wificonfiguration.preSharedKey;
            wapiPskType = wificonfiguration.wapiPskType;
            wapiPsk = wificonfiguration.wapiPsk;
            wapiCertSelMode = wificonfiguration.wapiCertSelMode;
            wapiCertSel = wificonfiguration.wapiCertSel;
            mNetworkSelectionStatus.copy(wificonfiguration.getNetworkSelectionStatus());
            apBand = wificonfiguration.apBand;
            apChannel = wificonfiguration.apChannel;
            wepKeys = new String[4];
            for(int i = 0; i < wepKeys.length; i++)
                wepKeys[i] = wificonfiguration.wepKeys[i];

            wepTxKeyIndex = wificonfiguration.wepTxKeyIndex;
            priority = wificonfiguration.priority;
            hiddenSSID = wificonfiguration.hiddenSSID;
            allowedKeyManagement = (BitSet)wificonfiguration.allowedKeyManagement.clone();
            allowedProtocols = (BitSet)wificonfiguration.allowedProtocols.clone();
            allowedAuthAlgorithms = (BitSet)wificonfiguration.allowedAuthAlgorithms.clone();
            allowedPairwiseCiphers = (BitSet)wificonfiguration.allowedPairwiseCiphers.clone();
            allowedGroupCiphers = (BitSet)wificonfiguration.allowedGroupCiphers.clone();
            enterpriseConfig = new WifiEnterpriseConfig(wificonfiguration.enterpriseConfig);
            defaultGwMacAddress = wificonfiguration.defaultGwMacAddress;
            mIpConfiguration = new IpConfiguration(wificonfiguration.mIpConfiguration);
            if(wificonfiguration.linkedConfigurations != null && wificonfiguration.linkedConfigurations.size() > 0)
            {
                linkedConfigurations = new HashMap();
                linkedConfigurations.putAll(wificonfiguration.linkedConfigurations);
            }
            mCachedConfigKey = null;
            selfAdded = wificonfiguration.selfAdded;
            validatedInternetAccess = wificonfiguration.validatedInternetAccess;
            isLegacyPasspointConfig = wificonfiguration.isLegacyPasspointConfig;
            ephemeral = wificonfiguration.ephemeral;
            meteredHint = wificonfiguration.meteredHint;
            meteredOverride = wificonfiguration.meteredOverride;
            useExternalScores = wificonfiguration.useExternalScores;
            if(wificonfiguration.visibility != null)
                visibility = new Visibility(wificonfiguration.visibility);
            didSelfAdd = wificonfiguration.didSelfAdd;
            lastConnectUid = wificonfiguration.lastConnectUid;
            lastUpdateUid = wificonfiguration.lastUpdateUid;
            creatorUid = wificonfiguration.creatorUid;
            creatorName = wificonfiguration.creatorName;
            lastUpdateName = wificonfiguration.lastUpdateName;
            peerWifiConfiguration = wificonfiguration.peerWifiConfiguration;
            lastConnected = wificonfiguration.lastConnected;
            lastDisconnected = wificonfiguration.lastDisconnected;
            lastConnectionFailure = wificonfiguration.lastConnectionFailure;
            lastRoamingFailure = wificonfiguration.lastRoamingFailure;
            lastRoamingFailureReason = wificonfiguration.lastRoamingFailureReason;
            roamingFailureBlackListTimeMilli = wificonfiguration.roamingFailureBlackListTimeMilli;
            numScorerOverride = wificonfiguration.numScorerOverride;
            numScorerOverrideAndSwitchedNetwork = wificonfiguration.numScorerOverrideAndSwitchedNetwork;
            numAssociation = wificonfiguration.numAssociation;
            userApproved = wificonfiguration.userApproved;
            numNoInternetAccessReports = wificonfiguration.numNoInternetAccessReports;
            noInternetAccessExpected = wificonfiguration.noInternetAccessExpected;
            creationTime = wificonfiguration.creationTime;
            updateTime = wificonfiguration.updateTime;
            shared = wificonfiguration.shared;
            recentFailure.setAssociationStatus(wificonfiguration.recentFailure.getAssociationStatus());
            SIMNum = wificonfiguration.SIMNum;
        }
    }

    public static String configKey(ScanResult scanresult)
    {
        return (new StringBuilder()).append("\"").append(scanresult.SSID).append("\"").toString();
    }

    public static WifiConfiguration getWifiConfigFromBackup(DataInputStream datainputstream)
        throws IOException, android.util.BackupUtils.BadVersionException
    {
        WifiConfiguration wificonfiguration = new WifiConfiguration();
        int i = datainputstream.readInt();
        if(i < 1 || i > 2)
            throw new android.util.BackupUtils.BadVersionException("Unknown Backup Serialization Version");
        if(i == 1)
        {
            return null;
        } else
        {
            wificonfiguration.SSID = BackupUtils.readString(datainputstream);
            wificonfiguration.apBand = datainputstream.readInt();
            wificonfiguration.apChannel = datainputstream.readInt();
            wificonfiguration.preSharedKey = BackupUtils.readString(datainputstream);
            wificonfiguration.allowedKeyManagement.set(datainputstream.readInt());
            return wificonfiguration;
        }
    }

    public static boolean isMetered(WifiConfiguration wificonfiguration, WifiInfo wifiinfo)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(wifiinfo != null)
        {
            flag1 = flag;
            if(wifiinfo.getMeteredHint())
                flag1 = true;
        }
        flag = flag1;
        if(wificonfiguration != null)
        {
            flag = flag1;
            if(wificonfiguration.meteredHint)
                flag = true;
        }
        flag1 = flag;
        if(wificonfiguration != null)
        {
            flag1 = flag;
            if(wificonfiguration.meteredOverride == 1)
                flag1 = true;
        }
        flag = flag1;
        if(wificonfiguration != null)
        {
            flag = flag1;
            if(wificonfiguration.meteredOverride == 2)
                flag = false;
        }
        return flag;
    }

    private static BitSet readBitSet(Parcel parcel)
    {
        int i = parcel.readInt();
        BitSet bitset = new BitSet();
        for(int j = 0; j < i; j++)
            bitset.set(parcel.readInt());

        return bitset;
    }

    private String trimStringForKeyId(String s)
    {
        return s.replace("\"", "").replace(" ", "");
    }

    public static String userApprovedAsString(int i)
    {
        switch(i)
        {
        default:
            return "INVALID";

        case 1: // '\001'
            return "USER_APPROVED";

        case 2: // '\002'
            return "USER_BANNED";

        case 0: // '\0'
            return "USER_UNSPECIFIED";
        }
    }

    private static void writeBitSet(Parcel parcel, BitSet bitset)
    {
        int i = -1;
        parcel.writeInt(bitset.cardinality());
        do
        {
            i = bitset.nextSetBit(i + 1);
            if(i != -1)
                parcel.writeInt(i);
            else
                return;
        } while(true);
    }

    public String configKey()
    {
        return configKey(false);
    }

    public String configKey(boolean flag)
    {
        if(!flag || mCachedConfigKey == null) goto _L2; else goto _L1
_L1:
        String s = mCachedConfigKey;
_L4:
        return s;
_L2:
        if(providerFriendlyName != null)
        {
            String s1 = (new StringBuilder()).append(FQDN).append(KeyMgmt.strings[2]).toString();
            s = s1;
            if(!shared)
                s = (new StringBuilder()).append(s1).append("-").append(Integer.toString(UserHandle.getUserId(creatorUid))).toString();
            continue; /* Loop/switch isn't completed */
        }
        if(!allowedKeyManagement.get(1))
            break; /* Loop/switch isn't completed */
        s = (new StringBuilder()).append(SSID).append("-").append(KeyMgmt.strings[1]).toString();
_L5:
        String s2 = s;
        if(!shared)
            s2 = (new StringBuilder()).append(s).append("-").append(Integer.toString(UserHandle.getUserId(creatorUid))).toString();
        mCachedConfigKey = s2;
        s = s2;
        if(true) goto _L4; else goto _L3
_L3:
        if(allowedKeyManagement.get(2) || allowedKeyManagement.get(3))
            s = (new StringBuilder()).append(SSID).append("-").append(KeyMgmt.strings[2]).toString();
        else
        if(wepKeys[0] != null)
            s = (new StringBuilder()).append(SSID).append("-WEP").toString();
        else
        if(allowedKeyManagement.get(10))
            s = (new StringBuilder()).append(SSID).append("-").append(KeyMgmt.strings[10]).toString();
        else
        if(allowedKeyManagement.get(11))
            s = (new StringBuilder()).append(SSID).append("-").append(KeyMgmt.strings[11]).toString();
        else
            s = (new StringBuilder()).append(SSID).append(KeyMgmt.strings[0]).toString();
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    public int describeContents()
    {
        return 0;
    }

    public int getAuthType()
    {
        if(allowedKeyManagement.cardinality() > 1)
            if(allowedKeyManagement.get(10) && allowedKeyManagement.get(11))
            {
                if(allowedKeyManagement.cardinality() != 4)
                    throw new IllegalStateException("More than one auth type set");
            } else
            {
                throw new IllegalStateException("More than one auth type set");
            }
        if(allowedKeyManagement.get(1))
            return 1;
        if(allowedKeyManagement.get(4))
            return 4;
        if(allowedKeyManagement.get(2))
            return 2;
        if(allowedKeyManagement.get(3))
            return 3;
        if(allowedKeyManagement.get(10))
            return 10;
        return !allowedKeyManagement.get(11) ? 0 : 11;
    }

    public byte[] getBytesForBackup()
        throws IOException
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);
        dataoutputstream.writeInt(2);
        BackupUtils.writeString(dataoutputstream, SSID);
        dataoutputstream.writeInt(apBand);
        dataoutputstream.writeInt(apChannel);
        BackupUtils.writeString(dataoutputstream, preSharedKey);
        dataoutputstream.writeInt(getAuthType());
        return bytearrayoutputstream.toByteArray();
    }

    public ProxyInfo getHttpProxy()
    {
        if(mIpConfiguration.proxySettings == android.net.IpConfiguration.ProxySettings.NONE)
            return null;
        else
            return new ProxyInfo(mIpConfiguration.httpProxy);
    }

    public android.net.IpConfiguration.IpAssignment getIpAssignment()
    {
        return mIpConfiguration.ipAssignment;
    }

    public IpConfiguration getIpConfiguration()
    {
        return mIpConfiguration;
    }

    public String getKeyIdForCredentials(WifiConfiguration wificonfiguration)
    {
        Object obj;
        Object obj1;
        obj = null;
        String s = null;
        try
        {
            if(TextUtils.isEmpty(SSID))
                SSID = wificonfiguration.SSID;
            if(allowedKeyManagement.cardinality() == 0)
                allowedKeyManagement = wificonfiguration.allowedKeyManagement;
            if(allowedKeyManagement.get(2))
                s = KeyMgmt.strings[2];
            if(allowedKeyManagement.get(5))
                s = KeyMgmt.strings[5];
        }
        // Misplaced declaration of an exception variable
        catch(WifiConfiguration wificonfiguration)
        {
            throw new IllegalStateException("Invalid config details");
        }
        obj1 = s;
        if(allowedKeyManagement.get(3))
        {
            obj1 = JVM INSTR new #423 <Class StringBuilder>;
            ((StringBuilder) (obj1)).StringBuilder();
            obj1 = ((StringBuilder) (obj1)).append(s).append(KeyMgmt.strings[3]).toString();
        }
        if(TextUtils.isEmpty(((CharSequence) (obj1))))
        {
            wificonfiguration = JVM INSTR new #531 <Class IllegalStateException>;
            wificonfiguration.IllegalStateException("Not an EAP network");
            throw wificonfiguration;
        }
        StringBuilder stringbuilder1;
        StringBuilder stringbuilder = JVM INSTR new #423 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        stringbuilder1 = stringbuilder.append(trimStringForKeyId(SSID)).append("_").append(((String) (obj1))).append("_");
        obj1 = enterpriseConfig;
        WifiEnterpriseConfig wifienterpriseconfig;
        wifienterpriseconfig = obj;
        if(wificonfiguration == null)
            break MISSING_BLOCK_LABEL_206;
        wifienterpriseconfig = wificonfiguration.enterpriseConfig;
        wificonfiguration = stringbuilder1.append(trimStringForKeyId(((WifiEnterpriseConfig) (obj1)).getKeyId(wifienterpriseconfig))).toString();
        return wificonfiguration;
    }

    public String getMoTree()
    {
        return mPasspointManagementObjectTree;
    }

    public NetworkSelectionStatus getNetworkSelectionStatus()
    {
        return mNetworkSelectionStatus;
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

    public android.net.IpConfiguration.ProxySettings getProxySettings()
    {
        return mIpConfiguration.proxySettings;
    }

    public StaticIpConfiguration getStaticIpConfiguration()
    {
        return mIpConfiguration.getStaticIpConfiguration();
    }

    public boolean hasNoInternetAccess()
    {
        boolean flag = false;
        if(numNoInternetAccessReports > 0)
            flag = validatedInternetAccess ^ true;
        return flag;
    }

    public boolean isEnterprise()
    {
        boolean flag1;
label0:
        {
            boolean flag = false;
            if(!allowedKeyManagement.get(2))
            {
                flag1 = flag;
                if(!allowedKeyManagement.get(3))
                    break label0;
            }
            flag1 = flag;
            if(enterpriseConfig != null)
            {
                flag1 = flag;
                if(enterpriseConfig.getEapMethod() != -1)
                    flag1 = true;
            }
        }
        return flag1;
    }

    public boolean isEphemeral()
    {
        return ephemeral;
    }

    public boolean isLinked(WifiConfiguration wificonfiguration)
    {
        return wificonfiguration != null && wificonfiguration.linkedConfigurations != null && linkedConfigurations != null && wificonfiguration.linkedConfigurations.get(configKey()) != null && linkedConfigurations.get(wificonfiguration.configKey()) != null;
    }

    public boolean isNoInternetAccessExpected()
    {
        return noInternetAccessExpected;
    }

    public boolean isOpenNetwork()
    {
        int i;
        boolean flag2;
        i = allowedKeyManagement.cardinality();
        boolean flag;
        boolean flag1;
        if(i != 0)
        {
            if(i == 1)
                flag = allowedKeyManagement.get(0);
            else
                flag = false;
        } else
        {
            flag = true;
        }
        flag1 = true;
        flag2 = flag1;
        if(wepKeys == null) goto _L2; else goto _L1
_L1:
        i = 0;
_L7:
        flag2 = flag1;
        if(i >= wepKeys.length) goto _L2; else goto _L3
_L3:
        if(wepKeys[i] == null) goto _L5; else goto _L4
_L4:
        flag2 = false;
_L2:
        if(!flag)
            flag2 = false;
        return flag2;
_L5:
        i++;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public boolean isPasspoint()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(!TextUtils.isEmpty(FQDN))
        {
            flag1 = flag;
            if(TextUtils.isEmpty(providerFriendlyName) ^ true)
            {
                flag1 = flag;
                if(enterpriseConfig != null)
                {
                    flag1 = flag;
                    if(enterpriseConfig.getEapMethod() != -1)
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    public void setHttpProxy(ProxyInfo proxyinfo)
    {
        if(proxyinfo == null)
        {
            mIpConfiguration.setProxySettings(android.net.IpConfiguration.ProxySettings.NONE);
            mIpConfiguration.setHttpProxy(null);
            return;
        }
        android.net.IpConfiguration.ProxySettings proxysettings;
        if(!Uri.EMPTY.equals(proxyinfo.getPacFileUrl()))
        {
            proxysettings = android.net.IpConfiguration.ProxySettings.PAC;
            proxyinfo = new ProxyInfo(proxyinfo.getPacFileUrl(), proxyinfo.getPort());
        } else
        {
            proxysettings = android.net.IpConfiguration.ProxySettings.STATIC;
            proxyinfo = new ProxyInfo(proxyinfo.getHost(), proxyinfo.getPort(), proxyinfo.getExclusionListAsString());
        }
        if(!proxyinfo.isValid())
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid ProxyInfo: ").append(proxyinfo.toString()).toString());
        } else
        {
            mIpConfiguration.setProxySettings(proxysettings);
            mIpConfiguration.setHttpProxy(proxyinfo);
            return;
        }
    }

    public void setIpAssignment(android.net.IpConfiguration.IpAssignment ipassignment)
    {
        mIpConfiguration.ipAssignment = ipassignment;
    }

    public void setIpConfiguration(IpConfiguration ipconfiguration)
    {
        mIpConfiguration = ipconfiguration;
    }

    public void setNetworkSelectionStatus(NetworkSelectionStatus networkselectionstatus)
    {
        mNetworkSelectionStatus = networkselectionstatus;
    }

    public void setPasspointManagementObjectTree(String s)
    {
        mPasspointManagementObjectTree = s;
    }

    public void setProxy(android.net.IpConfiguration.ProxySettings proxysettings, ProxyInfo proxyinfo)
    {
        mIpConfiguration.proxySettings = proxysettings;
        mIpConfiguration.httpProxy = proxyinfo;
    }

    public void setProxySettings(android.net.IpConfiguration.ProxySettings proxysettings)
    {
        mIpConfiguration.proxySettings = proxysettings;
    }

    public void setStaticIpConfiguration(StaticIpConfiguration staticipconfiguration)
    {
        mIpConfiguration.setStaticIpConfiguration(staticipconfiguration);
    }

    public void setVisibility(Visibility visibility1)
    {
        visibility = visibility1;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        if(status != 0) goto _L2; else goto _L1
_L1:
        stringbuilder.append("* ");
_L4:
        stringbuilder.append("ID: ").append(networkId).append(" SSID: ").append(SSID).append(" PROVIDER-NAME: ").append(providerFriendlyName).append(" BSSID: ").append(BSSID).append(" FQDN: ").append(FQDN).append(" PRIO: ").append(priority).append(" HIDDEN: ").append(hiddenSSID).append('\n');
        stringbuilder.append(" NetworkSelectionStatus ").append(mNetworkSelectionStatus.getNetworkStatusString()).append("\n");
        if(mNetworkSelectionStatus.getNetworkSelectionDisableReason() > 0)
        {
            stringbuilder.append(" mNetworkSelectionDisableReason ").append(mNetworkSelectionStatus.getNetworkDisableReasonString()).append("\n");
            for(int i = 0; i < 13; i++)
                if(mNetworkSelectionStatus.getDisableReasonCounter(i) != 0)
                    stringbuilder.append(NetworkSelectionStatus.getNetworkDisableReasonString(i)).append(" counter:").append(mNetworkSelectionStatus.getDisableReasonCounter(i)).append("\n");

        }
        break; /* Loop/switch isn't completed */
_L2:
        if(status == 1)
            stringbuilder.append("- DSBLE ");
        if(true) goto _L4; else goto _L3
_L3:
        if(mNetworkSelectionStatus.getConnectChoice() != null)
        {
            stringbuilder.append(" connect choice: ").append(mNetworkSelectionStatus.getConnectChoice());
            stringbuilder.append(" connect choice set time: ").append(mNetworkSelectionStatus.getConnectChoiceTimestamp());
        }
        stringbuilder.append(" hasEverConnected: ").append(mNetworkSelectionStatus.getHasEverConnected()).append("\n");
        if(numAssociation > 0)
            stringbuilder.append(" numAssociation ").append(numAssociation).append("\n");
        if(numNoInternetAccessReports > 0)
        {
            stringbuilder.append(" numNoInternetAccessReports ");
            stringbuilder.append(numNoInternetAccessReports).append("\n");
        }
        if(updateTime != null)
            stringbuilder.append(" update ").append(updateTime).append("\n");
        if(creationTime != null)
            stringbuilder.append(" creation ").append(creationTime).append("\n");
        if(didSelfAdd)
            stringbuilder.append(" didSelfAdd");
        if(selfAdded)
            stringbuilder.append(" selfAdded");
        if(validatedInternetAccess)
            stringbuilder.append(" validatedInternetAccess");
        if(ephemeral)
            stringbuilder.append(" ephemeral");
        if(meteredHint)
            stringbuilder.append(" meteredHint");
        if(useExternalScores)
            stringbuilder.append(" useExternalScores");
        if(didSelfAdd || selfAdded || validatedInternetAccess || ephemeral || meteredHint || useExternalScores)
            stringbuilder.append("\n");
        if(meteredOverride != 0)
            stringbuilder.append(" meteredOverride ").append(meteredOverride).append("\n");
        stringbuilder.append(" KeyMgmt:");
        int j = 0;
        while(j < allowedKeyManagement.size()) 
        {
            if(allowedKeyManagement.get(j))
            {
                stringbuilder.append(" ");
                if(j < KeyMgmt.strings.length)
                    stringbuilder.append(KeyMgmt.strings[j]);
                else
                    stringbuilder.append("??");
            }
            j++;
        }
        stringbuilder.append(" Protocols:");
        j = 0;
        while(j < allowedProtocols.size()) 
        {
            if(allowedProtocols.get(j))
            {
                stringbuilder.append(" ");
                if(j < Protocol.strings.length)
                    stringbuilder.append(Protocol.strings[j]);
                else
                    stringbuilder.append("??");
            }
            j++;
        }
        stringbuilder.append('\n');
        stringbuilder.append(" AuthAlgorithms:");
        j = 0;
        while(j < allowedAuthAlgorithms.size()) 
        {
            if(allowedAuthAlgorithms.get(j))
            {
                stringbuilder.append(" ");
                if(j < AuthAlgorithm.strings.length)
                    stringbuilder.append(AuthAlgorithm.strings[j]);
                else
                    stringbuilder.append("??");
            }
            j++;
        }
        stringbuilder.append('\n');
        stringbuilder.append(" PairwiseCiphers:");
        j = 0;
        while(j < allowedPairwiseCiphers.size()) 
        {
            if(allowedPairwiseCiphers.get(j))
            {
                stringbuilder.append(" ");
                if(j < PairwiseCipher.strings.length)
                    stringbuilder.append(PairwiseCipher.strings[j]);
                else
                    stringbuilder.append("??");
            }
            j++;
        }
        stringbuilder.append('\n');
        stringbuilder.append(" GroupCiphers:");
        j = 0;
        while(j < allowedGroupCiphers.size()) 
        {
            if(allowedGroupCiphers.get(j))
            {
                stringbuilder.append(" ");
                if(j < GroupCipher.strings.length)
                    stringbuilder.append(GroupCipher.strings[j]);
                else
                    stringbuilder.append("??");
            }
            j++;
        }
        stringbuilder.append('\n').append(" PSK: ");
        if(preSharedKey != null)
            stringbuilder.append('*');
        stringbuilder.append('\n').append(" sim_num ");
        if(SIMNum > 0)
            stringbuilder.append('*');
        stringbuilder.append('\n');
        if(wapiPskType != -1)
            stringbuilder.append(" WapiPskType: ").append(wapiPskType);
        stringbuilder.append('\n');
        if(wapiPsk != null)
            stringbuilder.append(" WapiPsk: ").append(wapiPsk);
        stringbuilder.append('\n');
        if(wapiCertSelMode != -1)
            stringbuilder.append(" WapiCertSelMode: ").append(wapiCertSelMode);
        stringbuilder.append('\n');
        if(wapiCertSel != null)
            stringbuilder.append(" WapiCertSel: ").append(wapiCertSel);
        stringbuilder.append("\nEnterprise config:\n");
        stringbuilder.append(enterpriseConfig);
        stringbuilder.append('\n');
        stringbuilder.append("IP config:\n");
        stringbuilder.append(mIpConfiguration.toString());
        if(mNetworkSelectionStatus.getNetworkSelectionBSSID() != null)
            stringbuilder.append(" networkSelectionBSSID=").append(mNetworkSelectionStatus.getNetworkSelectionBSSID());
        long l = System.currentTimeMillis();
        long l1;
        if(mNetworkSelectionStatus.getDisableTime() != -1L)
        {
            stringbuilder.append('\n');
            l1 = l - mNetworkSelectionStatus.getDisableTime();
            Iterator iterator;
            String s;
            if(l1 <= 0L)
                stringbuilder.append(" blackListed since <incorrect>");
            else
                stringbuilder.append(" blackListed: ").append(Long.toString(l1 / 1000L)).append("sec ");
        }
        if(creatorUid != 0)
            stringbuilder.append(" cuid=").append(creatorUid);
        if(creatorName != null)
            stringbuilder.append(" cname=").append(creatorName);
        if(lastUpdateUid != 0)
            stringbuilder.append(" luid=").append(lastUpdateUid);
        if(lastUpdateName != null)
            stringbuilder.append(" lname=").append(lastUpdateName);
        stringbuilder.append(" lcuid=").append(lastConnectUid);
        stringbuilder.append(" userApproved=").append(userApprovedAsString(userApproved));
        stringbuilder.append(" noInternetAccessExpected=").append(noInternetAccessExpected);
        stringbuilder.append(" ");
        if(lastConnected != 0L)
        {
            stringbuilder.append('\n');
            l1 = l - lastConnected;
            if(l1 <= 0L)
                stringbuilder.append("lastConnected since <incorrect>");
            else
                stringbuilder.append("lastConnected: ").append(Long.toString(l1 / 1000L)).append("sec ");
        }
        if(lastConnectionFailure != 0L)
        {
            stringbuilder.append('\n');
            l1 = l - lastConnectionFailure;
            if(l1 <= 0L)
            {
                stringbuilder.append("lastConnectionFailure since <incorrect> ");
            } else
            {
                stringbuilder.append("lastConnectionFailure: ").append(Long.toString(l1 / 1000L));
                stringbuilder.append("sec ");
            }
        }
        if(lastRoamingFailure != 0L)
        {
            stringbuilder.append('\n');
            l -= lastRoamingFailure;
            if(l <= 0L)
            {
                stringbuilder.append("lastRoamingFailure since <incorrect> ");
            } else
            {
                stringbuilder.append("lastRoamingFailure: ").append(Long.toString(l / 1000L));
                stringbuilder.append("sec ");
            }
        }
        stringbuilder.append("roamingFailureBlackListTimeMilli: ").append(Long.toString(roamingFailureBlackListTimeMilli));
        stringbuilder.append('\n');
        if(linkedConfigurations != null)
            for(iterator = linkedConfigurations.keySet().iterator(); iterator.hasNext(); stringbuilder.append('\n'))
            {
                s = (String)iterator.next();
                stringbuilder.append(" linked: ").append(s);
            }

        stringbuilder.append("recentFailure: ").append("Association Rejection code: ").append(recentFailure.getAssociationStatus()).append("\n");
        stringbuilder.append("ShareThisAp: ").append(shareThisAp);
        stringbuilder.append('\n');
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(networkId);
        parcel.writeInt(status);
        mNetworkSelectionStatus.writeToParcel(parcel);
        parcel.writeString(SSID);
        parcel.writeString(BSSID);
        int j;
        Object aobj[];
        int k;
        if(shareThisAp)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeInt(apBand);
        parcel.writeInt(apChannel);
        parcel.writeString(FQDN);
        parcel.writeString(providerFriendlyName);
        if(isHomeProviderNetwork)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeInt(roamingConsortiumIds.length);
        aobj = roamingConsortiumIds;
        k = aobj.length;
        for(j = 0; j < k; j++)
            parcel.writeLong(aobj[j]);

        parcel.writeString(preSharedKey);
        aobj = wepKeys;
        k = aobj.length;
        for(j = 0; j < k; j++)
            parcel.writeString(aobj[j]);

        parcel.writeInt(wepTxKeyIndex);
        parcel.writeInt(priority);
        if(hiddenSSID)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(requirePMF)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeString(updateIdentifier);
        writeBitSet(parcel, allowedKeyManagement);
        writeBitSet(parcel, allowedProtocols);
        writeBitSet(parcel, allowedAuthAlgorithms);
        writeBitSet(parcel, allowedPairwiseCiphers);
        writeBitSet(parcel, allowedGroupCiphers);
        if(allowedKeyManagement.get(10))
        {
            parcel.writeInt(wapiPskType);
            parcel.writeString(wapiPsk);
        } else
        if(allowedKeyManagement.get(11))
        {
            parcel.writeInt(wapiCertSelMode);
            parcel.writeString(wapiCertSel);
        }
        parcel.writeParcelable(enterpriseConfig, i);
        parcel.writeParcelable(mIpConfiguration, i);
        parcel.writeString(dhcpServer);
        parcel.writeString(defaultGwMacAddress);
        if(selfAdded)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(didSelfAdd)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(validatedInternetAccess)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(isLegacyPasspointConfig)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(ephemeral)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(meteredHint)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(meteredOverride);
        if(useExternalScores)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(creatorUid);
        parcel.writeInt(lastConnectUid);
        parcel.writeInt(lastUpdateUid);
        parcel.writeString(creatorName);
        parcel.writeString(lastUpdateName);
        parcel.writeLong(lastConnectionFailure);
        parcel.writeLong(lastRoamingFailure);
        parcel.writeInt(lastRoamingFailureReason);
        parcel.writeLong(roamingFailureBlackListTimeMilli);
        parcel.writeInt(numScorerOverride);
        parcel.writeInt(numScorerOverrideAndSwitchedNetwork);
        parcel.writeInt(numAssociation);
        parcel.writeInt(userApproved);
        parcel.writeInt(numNoInternetAccessReports);
        if(noInternetAccessExpected)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(shared)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeString(mPasspointManagementObjectTree);
        parcel.writeInt(recentFailure.getAssociationStatus());
        parcel.writeInt(SIMNum);
    }

    public static final int AP_BAND_2GHZ = 0;
    public static final int AP_BAND_5GHZ = 1;
    public static final int AP_BAND_DUAL = 2;
    private static final int BACKUP_VERSION = 2;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiConfiguration createFromParcel(Parcel parcel)
        {
            boolean flag = true;
            WifiConfiguration wificonfiguration = new WifiConfiguration();
            wificonfiguration.networkId = parcel.readInt();
            wificonfiguration.status = parcel.readInt();
            WifiConfiguration._2D_get0(wificonfiguration).readFromParcel(parcel);
            wificonfiguration.SSID = parcel.readString();
            wificonfiguration.BSSID = parcel.readString();
            boolean flag1;
            int i;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            wificonfiguration.shareThisAp = flag1;
            wificonfiguration.apBand = parcel.readInt();
            wificonfiguration.apChannel = parcel.readInt();
            wificonfiguration.FQDN = parcel.readString();
            wificonfiguration.providerFriendlyName = parcel.readString();
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            wificonfiguration.isHomeProviderNetwork = flag1;
            i = parcel.readInt();
            wificonfiguration.roamingConsortiumIds = new long[i];
            for(int j = 0; j < i; j++)
                wificonfiguration.roamingConsortiumIds[j] = parcel.readLong();

            wificonfiguration.preSharedKey = parcel.readString();
            for(int k = 0; k < wificonfiguration.wepKeys.length; k++)
                wificonfiguration.wepKeys[k] = parcel.readString();

            wificonfiguration.wepTxKeyIndex = parcel.readInt();
            wificonfiguration.priority = parcel.readInt();
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            wificonfiguration.hiddenSSID = flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            wificonfiguration.requirePMF = flag1;
            wificonfiguration.updateIdentifier = parcel.readString();
            wificonfiguration.allowedKeyManagement = WifiConfiguration._2D_wrap0(parcel);
            wificonfiguration.allowedProtocols = WifiConfiguration._2D_wrap0(parcel);
            wificonfiguration.allowedAuthAlgorithms = WifiConfiguration._2D_wrap0(parcel);
            wificonfiguration.allowedPairwiseCiphers = WifiConfiguration._2D_wrap0(parcel);
            wificonfiguration.allowedGroupCiphers = WifiConfiguration._2D_wrap0(parcel);
            if(wificonfiguration.allowedKeyManagement.get(10))
            {
                wificonfiguration.wapiPskType = parcel.readInt();
                wificonfiguration.wapiPsk = parcel.readString();
            } else
            if(wificonfiguration.allowedKeyManagement.get(11))
            {
                wificonfiguration.wapiCertSelMode = parcel.readInt();
                wificonfiguration.wapiCertSel = parcel.readString();
            }
            wificonfiguration.enterpriseConfig = (WifiEnterpriseConfig)parcel.readParcelable(null);
            WifiConfiguration._2D_set0(wificonfiguration, (IpConfiguration)parcel.readParcelable(null));
            wificonfiguration.dhcpServer = parcel.readString();
            wificonfiguration.defaultGwMacAddress = parcel.readString();
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            wificonfiguration.selfAdded = flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            wificonfiguration.didSelfAdd = flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            wificonfiguration.validatedInternetAccess = flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            wificonfiguration.isLegacyPasspointConfig = flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            wificonfiguration.ephemeral = flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            wificonfiguration.meteredHint = flag1;
            wificonfiguration.meteredOverride = parcel.readInt();
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            wificonfiguration.useExternalScores = flag1;
            wificonfiguration.creatorUid = parcel.readInt();
            wificonfiguration.lastConnectUid = parcel.readInt();
            wificonfiguration.lastUpdateUid = parcel.readInt();
            wificonfiguration.creatorName = parcel.readString();
            wificonfiguration.lastUpdateName = parcel.readString();
            wificonfiguration.lastConnectionFailure = parcel.readLong();
            wificonfiguration.lastRoamingFailure = parcel.readLong();
            wificonfiguration.lastRoamingFailureReason = parcel.readInt();
            wificonfiguration.roamingFailureBlackListTimeMilli = parcel.readLong();
            wificonfiguration.numScorerOverride = parcel.readInt();
            wificonfiguration.numScorerOverrideAndSwitchedNetwork = parcel.readInt();
            wificonfiguration.numAssociation = parcel.readInt();
            wificonfiguration.userApproved = parcel.readInt();
            wificonfiguration.numNoInternetAccessReports = parcel.readInt();
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            wificonfiguration.noInternetAccessExpected = flag1;
            if(parcel.readInt() != 0)
                flag1 = flag;
            else
                flag1 = false;
            wificonfiguration.shared = flag1;
            WifiConfiguration._2D_set1(wificonfiguration, parcel.readString());
            wificonfiguration.recentFailure.setAssociationStatus(parcel.readInt());
            wificonfiguration.SIMNum = parcel.readInt();
            return wificonfiguration;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiConfiguration[] newArray(int i)
        {
            return new WifiConfiguration[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int HOME_NETWORK_RSSI_BOOST = 5;
    public static final int INVALID_NETWORK_ID = -1;
    public static int INVALID_RSSI = 0;
    public static final int LOCAL_ONLY_NETWORK_ID = -2;
    public static final int METERED_OVERRIDE_METERED = 1;
    public static final int METERED_OVERRIDE_NONE = 0;
    public static final int METERED_OVERRIDE_NOT_METERED = 2;
    public static int ROAMING_FAILURE_AUTH_FAILURE = 0;
    public static int ROAMING_FAILURE_IP_CONFIG = 0;
    public static final String SIMNumVarName = "sim_num";
    private static final String TAG = "WifiConfiguration";
    public static final int UNKNOWN_UID = -1;
    public static final int USER_APPROVED = 1;
    public static final int USER_BANNED = 2;
    public static final int USER_PENDING = 3;
    public static final int USER_UNSPECIFIED = 0;
    public static final int WAPI_PSK_TYPE_ASCII = 0;
    public static final int WAPI_PSK_TYPE_HEX = 1;
    public static final String bssidVarName = "bssid";
    public static final String erpVarName = "erp";
    public static final String hiddenSSIDVarName = "scan_ssid";
    public static final String pmfVarName = "ieee80211w";
    public static final String priorityVarName = "priority";
    public static final String pskVarName = "psk";
    public static final String shareThisApVarName = "share_this_ap";
    public static final String ssidVarName = "ssid";
    public static final String updateIdentiferVarName = "update_identifier";
    public static final String wapiCertSelModeVarName = "wapi_user_cert_mode";
    public static final String wapiCertSelVarName = "wapi_user_sel_cert";
    public static final String wapiPskTypeVarName = "psk_key_type";
    public static final String wapiPskVarName = "wapi_psk";
    public static final String wepKeyVarNames[] = {
        "wep_key0", "wep_key1", "wep_key2", "wep_key3"
    };
    public static final String wepTxKeyIdxVarName = "wep_tx_keyidx";
    public String BSSID;
    public String FQDN;
    public int SIMNum;
    public String SSID;
    public BitSet allowedAuthAlgorithms;
    public BitSet allowedGroupCiphers;
    public BitSet allowedKeyManagement;
    public BitSet allowedPairwiseCiphers;
    public BitSet allowedProtocols;
    public int apBand;
    public int apChannel;
    public String creationTime;
    public String creatorName;
    public int creatorUid;
    public String defaultGwMacAddress;
    public String dhcpServer;
    public boolean didSelfAdd;
    public int dtimInterval;
    public WifiEnterpriseConfig enterpriseConfig;
    public boolean ephemeral;
    public boolean hiddenSSID;
    public boolean isHomeProviderNetwork;
    public boolean isLegacyPasspointConfig;
    public int lastConnectUid;
    public long lastConnected;
    public long lastConnectionFailure;
    public long lastDisconnected;
    public long lastRoamingFailure;
    public int lastRoamingFailureReason;
    public String lastUpdateName;
    public int lastUpdateUid;
    public HashMap linkedConfigurations;
    String mCachedConfigKey;
    private IpConfiguration mIpConfiguration;
    private NetworkSelectionStatus mNetworkSelectionStatus;
    private String mPasspointManagementObjectTree;
    public boolean meteredHint;
    public int meteredOverride;
    public int networkId;
    public boolean noInternetAccessExpected;
    public int numAssociation;
    public int numNoInternetAccessReports;
    public int numScorerOverride;
    public int numScorerOverrideAndSwitchedNetwork;
    public String peerWifiConfiguration;
    public String preSharedKey;
    public int priority;
    public String providerFriendlyName;
    public final RecentFailure recentFailure;
    public boolean requirePMF;
    public long roamingConsortiumIds[];
    public long roamingFailureBlackListTimeMilli;
    public boolean selfAdded;
    public boolean shareThisAp;
    public boolean shared;
    public int status;
    public String updateIdentifier;
    public String updateTime;
    public boolean useExternalScores;
    public int userApproved;
    public boolean validatedInternetAccess;
    public Visibility visibility;
    public String wapiCertSel;
    public int wapiCertSelMode;
    public String wapiPsk;
    public int wapiPskType;
    public String wepKeys[];
    public int wepTxKeyIndex;

    static 
    {
        INVALID_RSSI = -127;
        ROAMING_FAILURE_IP_CONFIG = 1;
        ROAMING_FAILURE_AUTH_FAILURE = 2;
    }
}
