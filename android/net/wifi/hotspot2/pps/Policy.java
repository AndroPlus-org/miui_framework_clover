// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.hotspot2.pps;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.nio.charset.StandardCharsets;
import java.util.*;

// Referenced classes of package android.net.wifi.hotspot2.pps:
//            UpdateParameter

public final class Policy
    implements Parcelable
{
    public static final class RoamingPartner
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(this == obj)
                return true;
            if(!(obj instanceof RoamingPartner))
                return false;
            obj = (RoamingPartner)obj;
            boolean flag1 = flag;
            if(TextUtils.equals(mFqdn, ((RoamingPartner) (obj)).mFqdn))
            {
                flag1 = flag;
                if(mFqdnExactMatch == ((RoamingPartner) (obj)).mFqdnExactMatch)
                {
                    flag1 = flag;
                    if(mPriority == ((RoamingPartner) (obj)).mPriority)
                        flag1 = TextUtils.equals(mCountries, ((RoamingPartner) (obj)).mCountries);
                }
            }
            return flag1;
        }

        public String getCountries()
        {
            return mCountries;
        }

        public String getFqdn()
        {
            return mFqdn;
        }

        public boolean getFqdnExactMatch()
        {
            return mFqdnExactMatch;
        }

        public int getPriority()
        {
            return mPriority;
        }

        public int hashCode()
        {
            return Objects.hash(new Object[] {
                mFqdn, Boolean.valueOf(mFqdnExactMatch), Integer.valueOf(mPriority), mCountries
            });
        }

        public void setCountries(String s)
        {
            mCountries = s;
        }

        public void setFqdn(String s)
        {
            mFqdn = s;
        }

        public void setFqdnExactMatch(boolean flag)
        {
            mFqdnExactMatch = flag;
        }

        public void setPriority(int i)
        {
            mPriority = i;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("FQDN: ").append(mFqdn).append("\n");
            stringbuilder.append("ExactMatch: ").append("mFqdnExactMatch").append("\n");
            stringbuilder.append("Priority: ").append(mPriority).append("\n");
            stringbuilder.append("Countries: ").append(mCountries).append("\n");
            return stringbuilder.toString();
        }

        public boolean validate()
        {
            if(TextUtils.isEmpty(mFqdn))
            {
                Log.d("Policy", "Missing FQDN");
                return false;
            }
            if(TextUtils.isEmpty(mCountries))
            {
                Log.d("Policy", "Missing countries");
                return false;
            } else
            {
                return true;
            }
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(mFqdn);
            if(mFqdnExactMatch)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(mPriority);
            parcel.writeString(mCountries);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public RoamingPartner createFromParcel(Parcel parcel)
            {
                boolean flag = false;
                RoamingPartner roamingpartner = new RoamingPartner();
                roamingpartner.setFqdn(parcel.readString());
                if(parcel.readInt() != 0)
                    flag = true;
                roamingpartner.setFqdnExactMatch(flag);
                roamingpartner.setPriority(parcel.readInt());
                roamingpartner.setCountries(parcel.readString());
                return roamingpartner;
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public RoamingPartner[] newArray(int i)
            {
                return new RoamingPartner[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private String mCountries;
        private String mFqdn;
        private boolean mFqdnExactMatch;
        private int mPriority;


        public RoamingPartner()
        {
            mFqdn = null;
            mFqdnExactMatch = false;
            mPriority = 0x80000000;
            mCountries = null;
        }

        public RoamingPartner(RoamingPartner roamingpartner)
        {
            mFqdn = null;
            mFqdnExactMatch = false;
            mPriority = 0x80000000;
            mCountries = null;
            if(roamingpartner != null)
            {
                mFqdn = roamingpartner.mFqdn;
                mFqdnExactMatch = roamingpartner.mFqdnExactMatch;
                mPriority = roamingpartner.mPriority;
                mCountries = roamingpartner.mCountries;
            }
        }
    }


    public Policy()
    {
        mMinHomeDownlinkBandwidth = 0x8000000000000000L;
        mMinHomeUplinkBandwidth = 0x8000000000000000L;
        mMinRoamingDownlinkBandwidth = 0x8000000000000000L;
        mMinRoamingUplinkBandwidth = 0x8000000000000000L;
        mExcludedSsidList = null;
        mRequiredProtoPortMap = null;
        mMaximumBssLoadValue = 0x80000000;
        mPreferredRoamingPartnerList = null;
        mPolicyUpdate = null;
    }

    public Policy(Policy policy)
    {
        mMinHomeDownlinkBandwidth = 0x8000000000000000L;
        mMinHomeUplinkBandwidth = 0x8000000000000000L;
        mMinRoamingDownlinkBandwidth = 0x8000000000000000L;
        mMinRoamingUplinkBandwidth = 0x8000000000000000L;
        mExcludedSsidList = null;
        mRequiredProtoPortMap = null;
        mMaximumBssLoadValue = 0x80000000;
        mPreferredRoamingPartnerList = null;
        mPolicyUpdate = null;
        if(policy == null)
            return;
        mMinHomeDownlinkBandwidth = policy.mMinHomeDownlinkBandwidth;
        mMinHomeUplinkBandwidth = policy.mMinHomeUplinkBandwidth;
        mMinRoamingDownlinkBandwidth = policy.mMinRoamingDownlinkBandwidth;
        mMinRoamingUplinkBandwidth = policy.mMinRoamingUplinkBandwidth;
        mMaximumBssLoadValue = policy.mMaximumBssLoadValue;
        if(policy.mExcludedSsidList != null)
            mExcludedSsidList = (String[])Arrays.copyOf(policy.mExcludedSsidList, policy.mExcludedSsidList.length);
        if(policy.mRequiredProtoPortMap != null)
            mRequiredProtoPortMap = Collections.unmodifiableMap(policy.mRequiredProtoPortMap);
        if(policy.mPreferredRoamingPartnerList != null)
            mPreferredRoamingPartnerList = Collections.unmodifiableList(policy.mPreferredRoamingPartnerList);
        if(policy.mPolicyUpdate != null)
            mPolicyUpdate = new UpdateParameter(policy.mPolicyUpdate);
    }

    private static void writeProtoPortMap(Parcel parcel, Map map)
    {
        if(map == null)
        {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(map.size());
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); parcel.writeString((String)map.getValue()))
        {
            map = (java.util.Map.Entry)iterator.next();
            parcel.writeInt(((Integer)map.getKey()).intValue());
        }

    }

    private static void writeRoamingPartnerList(Parcel parcel, int i, List list)
    {
        if(list == null)
        {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(list.size());
        for(list = list.iterator(); list.hasNext(); parcel.writeParcelable((RoamingPartner)list.next(), i));
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
        if(!(obj instanceof Policy))
            return false;
        obj = (Policy)obj;
        if(mMinHomeDownlinkBandwidth == ((Policy) (obj)).mMinHomeDownlinkBandwidth && mMinHomeUplinkBandwidth == ((Policy) (obj)).mMinHomeUplinkBandwidth && mMinRoamingDownlinkBandwidth == ((Policy) (obj)).mMinRoamingDownlinkBandwidth && mMinRoamingUplinkBandwidth == ((Policy) (obj)).mMinRoamingUplinkBandwidth && Arrays.equals(mExcludedSsidList, ((Policy) (obj)).mExcludedSsidList) && (mRequiredProtoPortMap != null ? mRequiredProtoPortMap.equals(((Policy) (obj)).mRequiredProtoPortMap) : ((Policy) (obj)).mRequiredProtoPortMap == null) && mMaximumBssLoadValue == ((Policy) (obj)).mMaximumBssLoadValue && (mPreferredRoamingPartnerList != null ? mPreferredRoamingPartnerList.equals(((Policy) (obj)).mPreferredRoamingPartnerList) : ((Policy) (obj)).mPreferredRoamingPartnerList == null))
        {
            if(mPolicyUpdate == null)
            {
                if(((Policy) (obj)).mPolicyUpdate != null)
                    flag = false;
            } else
            {
                flag = mPolicyUpdate.equals(((Policy) (obj)).mPolicyUpdate);
            }
        } else
        {
            flag = false;
        }
        return flag;
    }

    public String[] getExcludedSsidList()
    {
        return mExcludedSsidList;
    }

    public int getMaximumBssLoadValue()
    {
        return mMaximumBssLoadValue;
    }

    public long getMinHomeDownlinkBandwidth()
    {
        return mMinHomeDownlinkBandwidth;
    }

    public long getMinHomeUplinkBandwidth()
    {
        return mMinHomeUplinkBandwidth;
    }

    public long getMinRoamingDownlinkBandwidth()
    {
        return mMinRoamingDownlinkBandwidth;
    }

    public long getMinRoamingUplinkBandwidth()
    {
        return mMinRoamingUplinkBandwidth;
    }

    public UpdateParameter getPolicyUpdate()
    {
        return mPolicyUpdate;
    }

    public List getPreferredRoamingPartnerList()
    {
        return mPreferredRoamingPartnerList;
    }

    public Map getRequiredProtoPortMap()
    {
        return mRequiredProtoPortMap;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Long.valueOf(mMinHomeDownlinkBandwidth), Long.valueOf(mMinHomeUplinkBandwidth), Long.valueOf(mMinRoamingDownlinkBandwidth), Long.valueOf(mMinRoamingUplinkBandwidth), mExcludedSsidList, mRequiredProtoPortMap, Integer.valueOf(mMaximumBssLoadValue), mPreferredRoamingPartnerList, mPolicyUpdate
        });
    }

    public void setExcludedSsidList(String as[])
    {
        mExcludedSsidList = as;
    }

    public void setMaximumBssLoadValue(int i)
    {
        mMaximumBssLoadValue = i;
    }

    public void setMinHomeDownlinkBandwidth(long l)
    {
        mMinHomeDownlinkBandwidth = l;
    }

    public void setMinHomeUplinkBandwidth(long l)
    {
        mMinHomeUplinkBandwidth = l;
    }

    public void setMinRoamingDownlinkBandwidth(long l)
    {
        mMinRoamingDownlinkBandwidth = l;
    }

    public void setMinRoamingUplinkBandwidth(long l)
    {
        mMinRoamingUplinkBandwidth = l;
    }

    public void setPolicyUpdate(UpdateParameter updateparameter)
    {
        mPolicyUpdate = updateparameter;
    }

    public void setPreferredRoamingPartnerList(List list)
    {
        mPreferredRoamingPartnerList = list;
    }

    public void setRequiredProtoPortMap(Map map)
    {
        mRequiredProtoPortMap = map;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("MinHomeDownlinkBandwidth: ").append(mMinHomeDownlinkBandwidth).append("\n");
        stringbuilder.append("MinHomeUplinkBandwidth: ").append(mMinHomeUplinkBandwidth).append("\n");
        stringbuilder.append("MinRoamingDownlinkBandwidth: ").append(mMinRoamingDownlinkBandwidth).append("\n");
        stringbuilder.append("MinRoamingUplinkBandwidth: ").append(mMinRoamingUplinkBandwidth).append("\n");
        stringbuilder.append("ExcludedSSIDList: ").append(mExcludedSsidList).append("\n");
        stringbuilder.append("RequiredProtoPortMap: ").append(mRequiredProtoPortMap).append("\n");
        stringbuilder.append("MaximumBSSLoadValue: ").append(mMaximumBssLoadValue).append("\n");
        stringbuilder.append("PreferredRoamingPartnerList: ").append(mPreferredRoamingPartnerList).append("\n");
        if(mPolicyUpdate != null)
        {
            stringbuilder.append("PolicyUpdate Begin ---\n");
            stringbuilder.append(mPolicyUpdate);
            stringbuilder.append("PolicyUpdate End ---\n");
        }
        return stringbuilder.toString();
    }

    public boolean validate()
    {
label0:
        {
            if(mPolicyUpdate == null)
            {
                Log.d("Policy", "PolicyUpdate not specified");
                return false;
            }
            if(!mPolicyUpdate.validate())
                return false;
            if(mExcludedSsidList != null)
            {
                if(mExcludedSsidList.length > 128)
                {
                    Log.d("Policy", (new StringBuilder()).append("SSID exclusion list size exceeded the max: ").append(mExcludedSsidList.length).toString());
                    return false;
                }
                String as[] = mExcludedSsidList;
                int i = as.length;
                for(int j = 0; j < i; j++)
                {
                    String s1 = as[j];
                    if(s1.getBytes(StandardCharsets.UTF_8).length > 32)
                    {
                        Log.d("Policy", (new StringBuilder()).append("Invalid SSID: ").append(s1).toString());
                        return false;
                    }
                }

            }
            if(mRequiredProtoPortMap == null)
                break label0;
            Iterator iterator = mRequiredProtoPortMap.entrySet().iterator();
            String s;
            do
            {
                if(!iterator.hasNext())
                    break label0;
                s = (String)((java.util.Map.Entry)iterator.next()).getValue();
            } while(s.getBytes(StandardCharsets.UTF_8).length <= 64);
            Log.d("Policy", (new StringBuilder()).append("PortNumber string bytes exceeded the max: ").append(s).toString());
            return false;
        }
label1:
        {
            if(mPreferredRoamingPartnerList == null)
                break label1;
            Iterator iterator1 = mPreferredRoamingPartnerList.iterator();
            do
                if(!iterator1.hasNext())
                    break label1;
            while(((RoamingPartner)iterator1.next()).validate());
            return false;
        }
        return true;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(mMinHomeDownlinkBandwidth);
        parcel.writeLong(mMinHomeUplinkBandwidth);
        parcel.writeLong(mMinRoamingDownlinkBandwidth);
        parcel.writeLong(mMinRoamingUplinkBandwidth);
        parcel.writeStringArray(mExcludedSsidList);
        writeProtoPortMap(parcel, mRequiredProtoPortMap);
        parcel.writeInt(mMaximumBssLoadValue);
        writeRoamingPartnerList(parcel, i, mPreferredRoamingPartnerList);
        parcel.writeParcelable(mPolicyUpdate, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        private Map readProtoPortMap(Parcel parcel)
        {
            int i = parcel.readInt();
            if(i == -1)
                return null;
            HashMap hashmap = new HashMap(i);
            for(int j = 0; j < i; j++)
                hashmap.put(Integer.valueOf(parcel.readInt()), parcel.readString());

            return hashmap;
        }

        private List readRoamingPartnerList(Parcel parcel)
        {
            int i = parcel.readInt();
            if(i == -1)
                return null;
            ArrayList arraylist = new ArrayList();
            for(int j = 0; j < i; j++)
                arraylist.add((RoamingPartner)parcel.readParcelable(null));

            return arraylist;
        }

        public Policy createFromParcel(Parcel parcel)
        {
            Policy policy = new Policy();
            policy.setMinHomeDownlinkBandwidth(parcel.readLong());
            policy.setMinHomeUplinkBandwidth(parcel.readLong());
            policy.setMinRoamingDownlinkBandwidth(parcel.readLong());
            policy.setMinRoamingUplinkBandwidth(parcel.readLong());
            policy.setExcludedSsidList(parcel.createStringArray());
            policy.setRequiredProtoPortMap(readProtoPortMap(parcel));
            policy.setMaximumBssLoadValue(parcel.readInt());
            policy.setPreferredRoamingPartnerList(readRoamingPartnerList(parcel));
            policy.setPolicyUpdate((UpdateParameter)parcel.readParcelable(null));
            return policy;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Policy[] newArray(int i)
        {
            return new Policy[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int MAX_EXCLUSION_SSIDS = 128;
    private static final int MAX_PORT_STRING_BYTES = 64;
    private static final int MAX_SSID_BYTES = 32;
    private static final int NULL_VALUE = -1;
    private static final String TAG = "Policy";
    private String mExcludedSsidList[];
    private int mMaximumBssLoadValue;
    private long mMinHomeDownlinkBandwidth;
    private long mMinHomeUplinkBandwidth;
    private long mMinRoamingDownlinkBandwidth;
    private long mMinRoamingUplinkBandwidth;
    private UpdateParameter mPolicyUpdate;
    private List mPreferredRoamingPartnerList;
    private Map mRequiredProtoPortMap;

}
