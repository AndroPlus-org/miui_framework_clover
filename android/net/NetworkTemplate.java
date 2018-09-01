// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.net.wifi.WifiInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.TelephonyManager;
import android.util.BackupUtils;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;

// Referenced classes of package android.net:
//            NetworkIdentity

public class NetworkTemplate
    implements Parcelable
{

    public NetworkTemplate(int i, String s, String s1)
    {
        this(i, s, new String[] {
            s
        }, s1);
    }

    public NetworkTemplate(int i, String s, String as[], String s1)
    {
        mMatchRule = i;
        mSubscriberId = s;
        mMatchSubscriberIds = as;
        mNetworkId = s1;
        if(!isKnownMatchRule(i))
            Log.e("NetworkTemplate", (new StringBuilder()).append("Unknown network template rule ").append(i).append(" will not match any identity.").toString());
    }

    private NetworkTemplate(Parcel parcel)
    {
        mMatchRule = parcel.readInt();
        mSubscriberId = parcel.readString();
        mMatchSubscriberIds = parcel.createStringArray();
        mNetworkId = parcel.readString();
    }

    NetworkTemplate(Parcel parcel, NetworkTemplate networktemplate)
    {
        this(parcel);
    }

    public static NetworkTemplate buildTemplateBluetooth()
    {
        return new NetworkTemplate(8, null, null);
    }

    public static NetworkTemplate buildTemplateEthernet()
    {
        return new NetworkTemplate(5, null, null);
    }

    public static NetworkTemplate buildTemplateMobile3gLower(String s)
    {
        return new NetworkTemplate(2, s, null);
    }

    public static NetworkTemplate buildTemplateMobile4g(String s)
    {
        return new NetworkTemplate(3, s, null);
    }

    public static NetworkTemplate buildTemplateMobileAll(String s)
    {
        return new NetworkTemplate(1, s, null);
    }

    public static NetworkTemplate buildTemplateMobileWildcard()
    {
        return new NetworkTemplate(6, null, null);
    }

    public static NetworkTemplate buildTemplateProxy()
    {
        return new NetworkTemplate(9, null, null);
    }

    public static NetworkTemplate buildTemplateWifi()
    {
        return buildTemplateWifiWildcard();
    }

    public static NetworkTemplate buildTemplateWifi(String s)
    {
        return new NetworkTemplate(4, null, s);
    }

    public static NetworkTemplate buildTemplateWifiWildcard()
    {
        return new NetworkTemplate(7, null, null);
    }

    private static void ensureSubtypeAvailable()
    {
        throw new IllegalArgumentException("Unable to enforce 3G_LOWER template on combined data.");
    }

    public static void forceAllNetworkTypes()
    {
        sForceAllNetworkTypes = true;
    }

    private static String getMatchRuleName(int i)
    {
        switch(i)
        {
        default:
            return (new StringBuilder()).append("UNKNOWN(").append(i).append(")").toString();

        case 2: // '\002'
            return "MOBILE_3G_LOWER";

        case 3: // '\003'
            return "MOBILE_4G";

        case 1: // '\001'
            return "MOBILE_ALL";

        case 4: // '\004'
            return "WIFI";

        case 5: // '\005'
            return "ETHERNET";

        case 6: // '\006'
            return "MOBILE_WILDCARD";

        case 7: // '\007'
            return "WIFI_WILDCARD";

        case 8: // '\b'
            return "BLUETOOTH";

        case 9: // '\t'
            return "PROXY";
        }
    }

    public static NetworkTemplate getNetworkTemplateFromBackup(DataInputStream datainputstream)
        throws IOException, android.util.BackupUtils.BadVersionException
    {
        int i = datainputstream.readInt();
        if(i < 1 || i > 1)
            throw new android.util.BackupUtils.BadVersionException("Unknown Backup Serialization Version");
        i = datainputstream.readInt();
        String s = BackupUtils.readString(datainputstream);
        datainputstream = BackupUtils.readString(datainputstream);
        if(!isKnownMatchRule(i))
            throw new android.util.BackupUtils.BadVersionException((new StringBuilder()).append("Restored network template contains unknown match rule ").append(i).toString());
        else
            return new NetworkTemplate(i, s, datainputstream);
    }

    private static boolean isKnownMatchRule(int i)
    {
        switch(i)
        {
        default:
            return false;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
            return true;
        }
    }

    private boolean matchesBluetooth(NetworkIdentity networkidentity)
    {
        return networkidentity.mType == 7;
    }

    private boolean matchesEthernet(NetworkIdentity networkidentity)
    {
        return networkidentity.mType == 9;
    }

    private boolean matchesMobile(NetworkIdentity networkidentity)
    {
        boolean flag1;
label0:
        {
            boolean flag = false;
            if(networkidentity.mType == 6)
                return true;
            if(!sForceAllNetworkTypes)
            {
                flag1 = flag;
                if(networkidentity.mType != 0)
                    break label0;
                flag1 = flag;
                if(!networkidentity.mMetered)
                    break label0;
            }
            flag1 = flag;
            if(ArrayUtils.isEmpty(mMatchSubscriberIds) ^ true)
                flag1 = ArrayUtils.contains(mMatchSubscriberIds, networkidentity.mSubscriberId);
        }
        return flag1;
    }

    private boolean matchesMobile3gLower(NetworkIdentity networkidentity)
    {
        ensureSubtypeAvailable();
        if(networkidentity.mType == 6)
            return false;
        if(!matchesMobile(networkidentity)) goto _L2; else goto _L1
_L1:
        TelephonyManager.getNetworkClass(networkidentity.mSubType);
        JVM INSTR tableswitch 0 2: default 56
    //                   0 58
    //                   1 58
    //                   2 58;
           goto _L2 _L3 _L3 _L3
_L2:
        return false;
_L3:
        return true;
    }

    private boolean matchesMobile4g(NetworkIdentity networkidentity)
    {
        ensureSubtypeAvailable();
        if(networkidentity.mType == 6)
            return true;
        if(!matchesMobile(networkidentity)) goto _L2; else goto _L1
_L1:
        TelephonyManager.getNetworkClass(networkidentity.mSubType);
        JVM INSTR tableswitch 3 3: default 48
    //                   3 50;
           goto _L2 _L3
_L2:
        return false;
_L3:
        return true;
    }

    private boolean matchesMobileWildcard(NetworkIdentity networkidentity)
    {
        boolean flag = true;
        if(networkidentity.mType == 6)
            return true;
        if(!sForceAllNetworkTypes)
            if(networkidentity.mType == 0)
                flag = networkidentity.mMetered;
            else
                flag = false;
        return flag;
    }

    private boolean matchesProxy(NetworkIdentity networkidentity)
    {
        boolean flag;
        if(networkidentity.mType == 16)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private boolean matchesWifi(NetworkIdentity networkidentity)
    {
        switch(networkidentity.mType)
        {
        default:
            return false;

        case 1: // '\001'
            return Objects.equals(WifiInfo.removeDoubleQuotes(mNetworkId), WifiInfo.removeDoubleQuotes(networkidentity.mNetworkId));
        }
    }

    private boolean matchesWifiWildcard(NetworkIdentity networkidentity)
    {
        switch(networkidentity.mType)
        {
        default:
            return false;

        case 1: // '\001'
        case 13: // '\r'
            return true;
        }
    }

    public static NetworkTemplate normalize(NetworkTemplate networktemplate, String as[])
    {
        if(networktemplate.isMatchRuleMobile() && ArrayUtils.contains(as, networktemplate.mSubscriberId))
            return new NetworkTemplate(networktemplate.mMatchRule, as[0], as, networktemplate.mNetworkId);
        else
            return networktemplate;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof NetworkTemplate)
        {
            obj = (NetworkTemplate)obj;
            boolean flag1 = flag;
            if(mMatchRule == ((NetworkTemplate) (obj)).mMatchRule)
            {
                flag1 = flag;
                if(Objects.equals(mSubscriberId, ((NetworkTemplate) (obj)).mSubscriberId))
                    flag1 = Objects.equals(mNetworkId, ((NetworkTemplate) (obj)).mNetworkId);
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public byte[] getBytesForBackup()
        throws IOException
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);
        dataoutputstream.writeInt(1);
        dataoutputstream.writeInt(mMatchRule);
        BackupUtils.writeString(dataoutputstream, mSubscriberId);
        BackupUtils.writeString(dataoutputstream, mNetworkId);
        return bytearrayoutputstream.toByteArray();
    }

    public int getMatchRule()
    {
        return mMatchRule;
    }

    public String getNetworkId()
    {
        return mNetworkId;
    }

    public String getSubscriberId()
    {
        return mSubscriberId;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(mMatchRule), mSubscriberId, mNetworkId
        });
    }

    public boolean isMatchRuleMobile()
    {
        switch(mMatchRule)
        {
        case 4: // '\004'
        case 5: // '\005'
        default:
            return false;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 6: // '\006'
            return true;
        }
    }

    public boolean isPersistable()
    {
        switch(mMatchRule)
        {
        default:
            return true;

        case 6: // '\006'
        case 7: // '\007'
            return false;
        }
    }

    public boolean matches(NetworkIdentity networkidentity)
    {
        switch(mMatchRule)
        {
        default:
            return false;

        case 1: // '\001'
            return matchesMobile(networkidentity);

        case 2: // '\002'
            return matchesMobile3gLower(networkidentity);

        case 3: // '\003'
            return matchesMobile4g(networkidentity);

        case 4: // '\004'
            return matchesWifi(networkidentity);

        case 5: // '\005'
            return matchesEthernet(networkidentity);

        case 6: // '\006'
            return matchesMobileWildcard(networkidentity);

        case 7: // '\007'
            return matchesWifiWildcard(networkidentity);

        case 8: // '\b'
            return matchesBluetooth(networkidentity);

        case 9: // '\t'
            return matchesProxy(networkidentity);
        }
    }

    public boolean matchesSubscriberId(String s)
    {
        return ArrayUtils.contains(mMatchSubscriberIds, s);
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("NetworkTemplate: ");
        stringbuilder.append("matchRule=").append(getMatchRuleName(mMatchRule));
        if(mSubscriberId != null)
            stringbuilder.append(", subscriberId=").append(NetworkIdentity.scrubSubscriberId(mSubscriberId));
        if(mMatchSubscriberIds != null)
            stringbuilder.append(", matchSubscriberIds=").append(Arrays.toString(NetworkIdentity.scrubSubscriberId(mMatchSubscriberIds)));
        if(mNetworkId != null)
            stringbuilder.append(", networkId=").append(mNetworkId);
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mMatchRule);
        parcel.writeString(mSubscriberId);
        parcel.writeStringArray(mMatchSubscriberIds);
        parcel.writeString(mNetworkId);
    }

    private static final int BACKUP_VERSION = 1;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NetworkTemplate createFromParcel(Parcel parcel)
        {
            return new NetworkTemplate(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NetworkTemplate[] newArray(int i)
        {
            return new NetworkTemplate[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int MATCH_BLUETOOTH = 8;
    public static final int MATCH_ETHERNET = 5;
    public static final int MATCH_MOBILE_3G_LOWER = 2;
    public static final int MATCH_MOBILE_4G = 3;
    public static final int MATCH_MOBILE_ALL = 1;
    public static final int MATCH_MOBILE_WILDCARD = 6;
    public static final int MATCH_PROXY = 9;
    public static final int MATCH_WIFI = 4;
    public static final int MATCH_WIFI_WILDCARD = 7;
    private static final String TAG = "NetworkTemplate";
    private static boolean sForceAllNetworkTypes = false;
    private final int mMatchRule;
    private final String mMatchSubscriberIds[];
    private final String mNetworkId;
    private final String mSubscriberId;

}
