// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.hotspot2;

import android.net.wifi.hotspot2.pps.Credential;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.net.wifi.hotspot2.pps.Policy;
import android.net.wifi.hotspot2.pps.UpdateParameter;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.nio.charset.StandardCharsets;
import java.util.*;

public final class PasspointConfiguration
    implements Parcelable
{

    public PasspointConfiguration()
    {
        mHomeSp = null;
        mCredential = null;
        mPolicy = null;
        mSubscriptionUpdate = null;
        mTrustRootCertList = null;
        mUpdateIdentifier = 0x80000000;
        mCredentialPriority = 0x80000000;
        mSubscriptionCreationTimeInMillis = 0x8000000000000000L;
        mSubscriptionExpirationTimeInMillis = 0x8000000000000000L;
        mSubscriptionType = null;
        mUsageLimitUsageTimePeriodInMinutes = 0x8000000000000000L;
        mUsageLimitStartTimeInMillis = 0x8000000000000000L;
        mUsageLimitDataLimit = 0x8000000000000000L;
        mUsageLimitTimeLimitInMinutes = 0x8000000000000000L;
    }

    public PasspointConfiguration(PasspointConfiguration passpointconfiguration)
    {
        mHomeSp = null;
        mCredential = null;
        mPolicy = null;
        mSubscriptionUpdate = null;
        mTrustRootCertList = null;
        mUpdateIdentifier = 0x80000000;
        mCredentialPriority = 0x80000000;
        mSubscriptionCreationTimeInMillis = 0x8000000000000000L;
        mSubscriptionExpirationTimeInMillis = 0x8000000000000000L;
        mSubscriptionType = null;
        mUsageLimitUsageTimePeriodInMinutes = 0x8000000000000000L;
        mUsageLimitStartTimeInMillis = 0x8000000000000000L;
        mUsageLimitDataLimit = 0x8000000000000000L;
        mUsageLimitTimeLimitInMinutes = 0x8000000000000000L;
        if(passpointconfiguration == null)
            return;
        if(passpointconfiguration.mHomeSp != null)
            mHomeSp = new HomeSp(passpointconfiguration.mHomeSp);
        if(passpointconfiguration.mCredential != null)
            mCredential = new Credential(passpointconfiguration.mCredential);
        if(passpointconfiguration.mPolicy != null)
            mPolicy = new Policy(passpointconfiguration.mPolicy);
        if(passpointconfiguration.mTrustRootCertList != null)
            mTrustRootCertList = Collections.unmodifiableMap(passpointconfiguration.mTrustRootCertList);
        if(passpointconfiguration.mSubscriptionUpdate != null)
            mSubscriptionUpdate = new UpdateParameter(passpointconfiguration.mSubscriptionUpdate);
        mUpdateIdentifier = passpointconfiguration.mUpdateIdentifier;
        mCredentialPriority = passpointconfiguration.mCredentialPriority;
        mSubscriptionCreationTimeInMillis = passpointconfiguration.mSubscriptionCreationTimeInMillis;
        mSubscriptionExpirationTimeInMillis = passpointconfiguration.mSubscriptionExpirationTimeInMillis;
        mSubscriptionType = passpointconfiguration.mSubscriptionType;
        mUsageLimitDataLimit = passpointconfiguration.mUsageLimitDataLimit;
        mUsageLimitStartTimeInMillis = passpointconfiguration.mUsageLimitStartTimeInMillis;
        mUsageLimitTimeLimitInMinutes = passpointconfiguration.mUsageLimitTimeLimitInMinutes;
        mUsageLimitUsageTimePeriodInMinutes = passpointconfiguration.mUsageLimitUsageTimePeriodInMinutes;
    }

    private static boolean isTrustRootCertListEquals(Map map, Map map1)
    {
        if(map == null || map1 == null)
        {
            boolean flag;
            if(map == map1)
                flag = true;
            else
                flag = false;
            return flag;
        }
        if(map.size() != map1.size())
            return false;
        for(map = map.entrySet().iterator(); map.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)map.next();
            if(!Arrays.equals((byte[])entry.getValue(), (byte[])map1.get(entry.getKey())))
                return false;
        }

        return true;
    }

    private static void writeTrustRootCerts(Parcel parcel, Map map)
    {
        if(map == null)
        {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(map.size());
        java.util.Map.Entry entry;
        for(map = map.entrySet().iterator(); map.hasNext(); parcel.writeByteArray((byte[])entry.getValue()))
        {
            entry = (java.util.Map.Entry)map.next();
            parcel.writeString((String)entry.getKey());
        }

    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(!(obj instanceof PasspointConfiguration))
            return false;
        obj = (PasspointConfiguration)obj;
        if((mHomeSp != null ? mHomeSp.equals(((PasspointConfiguration) (obj)).mHomeSp) : ((PasspointConfiguration) (obj)).mHomeSp == null) && (mCredential != null ? mCredential.equals(((PasspointConfiguration) (obj)).mCredential) : ((PasspointConfiguration) (obj)).mCredential == null) && (mPolicy != null ? mPolicy.equals(((PasspointConfiguration) (obj)).mPolicy) : ((PasspointConfiguration) (obj)).mPolicy == null) && (mSubscriptionUpdate != null ? mSubscriptionUpdate.equals(((PasspointConfiguration) (obj)).mSubscriptionUpdate) : ((PasspointConfiguration) (obj)).mSubscriptionUpdate == null) && (isTrustRootCertListEquals(mTrustRootCertList, ((PasspointConfiguration) (obj)).mTrustRootCertList) && mUpdateIdentifier == ((PasspointConfiguration) (obj)).mUpdateIdentifier && mCredentialPriority == ((PasspointConfiguration) (obj)).mCredentialPriority && mSubscriptionCreationTimeInMillis == ((PasspointConfiguration) (obj)).mSubscriptionCreationTimeInMillis && mSubscriptionExpirationTimeInMillis == ((PasspointConfiguration) (obj)).mSubscriptionExpirationTimeInMillis && TextUtils.equals(mSubscriptionType, ((PasspointConfiguration) (obj)).mSubscriptionType) && mUsageLimitUsageTimePeriodInMinutes == ((PasspointConfiguration) (obj)).mUsageLimitUsageTimePeriodInMinutes && mUsageLimitStartTimeInMillis == ((PasspointConfiguration) (obj)).mUsageLimitStartTimeInMillis && mUsageLimitDataLimit == ((PasspointConfiguration) (obj)).mUsageLimitDataLimit))
        {
            if(mUsageLimitTimeLimitInMinutes != ((PasspointConfiguration) (obj)).mUsageLimitTimeLimitInMinutes)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public Credential getCredential()
    {
        return mCredential;
    }

    public int getCredentialPriority()
    {
        return mCredentialPriority;
    }

    public HomeSp getHomeSp()
    {
        return mHomeSp;
    }

    public Policy getPolicy()
    {
        return mPolicy;
    }

    public long getSubscriptionCreationTimeInMillis()
    {
        return mSubscriptionCreationTimeInMillis;
    }

    public long getSubscriptionExpirationTimeInMillis()
    {
        return mSubscriptionExpirationTimeInMillis;
    }

    public String getSubscriptionType()
    {
        return mSubscriptionType;
    }

    public UpdateParameter getSubscriptionUpdate()
    {
        return mSubscriptionUpdate;
    }

    public Map getTrustRootCertList()
    {
        return mTrustRootCertList;
    }

    public int getUpdateIdentifier()
    {
        return mUpdateIdentifier;
    }

    public long getUsageLimitDataLimit()
    {
        return mUsageLimitDataLimit;
    }

    public long getUsageLimitStartTimeInMillis()
    {
        return mUsageLimitStartTimeInMillis;
    }

    public long getUsageLimitTimeLimitInMinutes()
    {
        return mUsageLimitTimeLimitInMinutes;
    }

    public long getUsageLimitUsageTimePeriodInMinutes()
    {
        return mUsageLimitUsageTimePeriodInMinutes;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mHomeSp, mCredential, mPolicy, mSubscriptionUpdate, mTrustRootCertList, Integer.valueOf(mUpdateIdentifier), Integer.valueOf(mCredentialPriority), Long.valueOf(mSubscriptionCreationTimeInMillis), Long.valueOf(mSubscriptionExpirationTimeInMillis), Long.valueOf(mUsageLimitUsageTimePeriodInMinutes), 
            Long.valueOf(mUsageLimitStartTimeInMillis), Long.valueOf(mUsageLimitDataLimit), Long.valueOf(mUsageLimitTimeLimitInMinutes)
        });
    }

    public void setCredential(Credential credential)
    {
        mCredential = credential;
    }

    public void setCredentialPriority(int i)
    {
        mCredentialPriority = i;
    }

    public void setHomeSp(HomeSp homesp)
    {
        mHomeSp = homesp;
    }

    public void setPolicy(Policy policy)
    {
        mPolicy = policy;
    }

    public void setSubscriptionCreationTimeInMillis(long l)
    {
        mSubscriptionCreationTimeInMillis = l;
    }

    public void setSubscriptionExpirationTimeInMillis(long l)
    {
        mSubscriptionExpirationTimeInMillis = l;
    }

    public void setSubscriptionType(String s)
    {
        mSubscriptionType = s;
    }

    public void setSubscriptionUpdate(UpdateParameter updateparameter)
    {
        mSubscriptionUpdate = updateparameter;
    }

    public void setTrustRootCertList(Map map)
    {
        mTrustRootCertList = map;
    }

    public void setUpdateIdentifier(int i)
    {
        mUpdateIdentifier = i;
    }

    public void setUsageLimitDataLimit(long l)
    {
        mUsageLimitDataLimit = l;
    }

    public void setUsageLimitStartTimeInMillis(long l)
    {
        mUsageLimitStartTimeInMillis = l;
    }

    public void setUsageLimitTimeLimitInMinutes(long l)
    {
        mUsageLimitTimeLimitInMinutes = l;
    }

    public void setUsageLimitUsageTimePeriodInMinutes(long l)
    {
        mUsageLimitUsageTimePeriodInMinutes = l;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("UpdateIdentifier: ").append(mUpdateIdentifier).append("\n");
        stringbuilder.append("CredentialPriority: ").append(mCredentialPriority).append("\n");
        StringBuilder stringbuilder1 = stringbuilder.append("SubscriptionCreationTime: ");
        Object obj;
        if(mSubscriptionCreationTimeInMillis != 0x8000000000000000L)
            obj = new Date(mSubscriptionCreationTimeInMillis);
        else
            obj = "Not specified";
        stringbuilder1.append(obj).append("\n");
        stringbuilder1 = stringbuilder.append("SubscriptionExpirationTime: ");
        if(mSubscriptionExpirationTimeInMillis != 0x8000000000000000L)
            obj = new Date(mSubscriptionExpirationTimeInMillis);
        else
            obj = "Not specified";
        stringbuilder1.append(obj).append("\n");
        stringbuilder1 = stringbuilder.append("UsageLimitStartTime: ");
        if(mUsageLimitStartTimeInMillis != 0x8000000000000000L)
            obj = new Date(mUsageLimitStartTimeInMillis);
        else
            obj = "Not specified";
        stringbuilder1.append(obj).append("\n");
        stringbuilder.append("UsageTimePeriod: ").append(mUsageLimitUsageTimePeriodInMinutes).append("\n");
        stringbuilder.append("UsageLimitDataLimit: ").append(mUsageLimitDataLimit).append("\n");
        stringbuilder.append("UsageLimitTimeLimit: ").append(mUsageLimitTimeLimitInMinutes).append("\n");
        if(mHomeSp != null)
        {
            stringbuilder.append("HomeSP Begin ---\n");
            stringbuilder.append(mHomeSp);
            stringbuilder.append("HomeSP End ---\n");
        }
        if(mCredential != null)
        {
            stringbuilder.append("Credential Begin ---\n");
            stringbuilder.append(mCredential);
            stringbuilder.append("Credential End ---\n");
        }
        if(mPolicy != null)
        {
            stringbuilder.append("Policy Begin ---\n");
            stringbuilder.append(mPolicy);
            stringbuilder.append("Policy End ---\n");
        }
        if(mSubscriptionUpdate != null)
        {
            stringbuilder.append("SubscriptionUpdate Begin ---\n");
            stringbuilder.append(mSubscriptionUpdate);
            stringbuilder.append("SubscriptionUpdate End ---\n");
        }
        if(mTrustRootCertList != null)
            stringbuilder.append("TrustRootCertServers: ").append(mTrustRootCertList.keySet()).append("\n");
        return stringbuilder.toString();
    }

    public boolean validate()
    {
label0:
        {
            if(mHomeSp == null || mHomeSp.validate() ^ true)
                return false;
            if(mCredential == null || mCredential.validate() ^ true)
                return false;
            if(mPolicy != null && mPolicy.validate() ^ true)
                return false;
            if(mSubscriptionUpdate != null && mSubscriptionUpdate.validate() ^ true)
                return false;
            if(mTrustRootCertList == null)
                break label0;
            Iterator iterator = mTrustRootCertList.entrySet().iterator();
            byte abyte0[];
            do
            {
                if(!iterator.hasNext())
                    break label0;
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                String s = (String)entry.getKey();
                abyte0 = (byte[])entry.getValue();
                if(TextUtils.isEmpty(s))
                {
                    Log.d("PasspointConfiguration", "Empty URL");
                    return false;
                }
                if(s.getBytes(StandardCharsets.UTF_8).length > 1023)
                {
                    Log.d("PasspointConfiguration", (new StringBuilder()).append("URL bytes exceeded the max: ").append(s.getBytes(StandardCharsets.UTF_8).length).toString());
                    return false;
                }
                if(abyte0 == null)
                {
                    Log.d("PasspointConfiguration", "Fingerprint not specified");
                    return false;
                }
            } while(abyte0.length == 32);
            Log.d("PasspointConfiguration", (new StringBuilder()).append("Incorrect size of trust root certificate SHA-256 fingerprint: ").append(abyte0.length).toString());
            return false;
        }
        return true;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mHomeSp, i);
        parcel.writeParcelable(mCredential, i);
        parcel.writeParcelable(mPolicy, i);
        parcel.writeParcelable(mSubscriptionUpdate, i);
        writeTrustRootCerts(parcel, mTrustRootCertList);
        parcel.writeInt(mUpdateIdentifier);
        parcel.writeInt(mCredentialPriority);
        parcel.writeLong(mSubscriptionCreationTimeInMillis);
        parcel.writeLong(mSubscriptionExpirationTimeInMillis);
        parcel.writeString(mSubscriptionType);
        parcel.writeLong(mUsageLimitUsageTimePeriodInMinutes);
        parcel.writeLong(mUsageLimitStartTimeInMillis);
        parcel.writeLong(mUsageLimitDataLimit);
        parcel.writeLong(mUsageLimitTimeLimitInMinutes);
    }

    private static final int CERTIFICATE_SHA256_BYTES = 32;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        private Map readTrustRootCerts(Parcel parcel)
        {
            int i = parcel.readInt();
            if(i == -1)
                return null;
            HashMap hashmap = new HashMap(i);
            for(int j = 0; j < i; j++)
                hashmap.put(parcel.readString(), parcel.createByteArray());

            return hashmap;
        }

        public PasspointConfiguration createFromParcel(Parcel parcel)
        {
            PasspointConfiguration passpointconfiguration = new PasspointConfiguration();
            passpointconfiguration.setHomeSp((HomeSp)parcel.readParcelable(null));
            passpointconfiguration.setCredential((Credential)parcel.readParcelable(null));
            passpointconfiguration.setPolicy((Policy)parcel.readParcelable(null));
            passpointconfiguration.setSubscriptionUpdate((UpdateParameter)parcel.readParcelable(null));
            passpointconfiguration.setTrustRootCertList(readTrustRootCerts(parcel));
            passpointconfiguration.setUpdateIdentifier(parcel.readInt());
            passpointconfiguration.setCredentialPriority(parcel.readInt());
            passpointconfiguration.setSubscriptionCreationTimeInMillis(parcel.readLong());
            passpointconfiguration.setSubscriptionExpirationTimeInMillis(parcel.readLong());
            passpointconfiguration.setSubscriptionType(parcel.readString());
            passpointconfiguration.setUsageLimitUsageTimePeriodInMinutes(parcel.readLong());
            passpointconfiguration.setUsageLimitStartTimeInMillis(parcel.readLong());
            passpointconfiguration.setUsageLimitDataLimit(parcel.readLong());
            passpointconfiguration.setUsageLimitTimeLimitInMinutes(parcel.readLong());
            return passpointconfiguration;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PasspointConfiguration[] newArray(int i)
        {
            return new PasspointConfiguration[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int MAX_URL_BYTES = 1023;
    private static final int NULL_VALUE = -1;
    private static final String TAG = "PasspointConfiguration";
    private Credential mCredential;
    private int mCredentialPriority;
    private HomeSp mHomeSp;
    private Policy mPolicy;
    private long mSubscriptionCreationTimeInMillis;
    private long mSubscriptionExpirationTimeInMillis;
    private String mSubscriptionType;
    private UpdateParameter mSubscriptionUpdate;
    private Map mTrustRootCertList;
    private int mUpdateIdentifier;
    private long mUsageLimitDataLimit;
    private long mUsageLimitStartTimeInMillis;
    private long mUsageLimitTimeLimitInMinutes;
    private long mUsageLimitUsageTimePeriodInMinutes;

}
