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

public final class HomeSp
    implements Parcelable
{

    public HomeSp()
    {
        mFqdn = null;
        mFriendlyName = null;
        mIconUrl = null;
        mHomeNetworkIds = null;
        mMatchAllOis = null;
        mMatchAnyOis = null;
        mOtherHomePartners = null;
        mRoamingConsortiumOis = null;
    }

    public HomeSp(HomeSp homesp)
    {
        mFqdn = null;
        mFriendlyName = null;
        mIconUrl = null;
        mHomeNetworkIds = null;
        mMatchAllOis = null;
        mMatchAnyOis = null;
        mOtherHomePartners = null;
        mRoamingConsortiumOis = null;
        if(homesp == null)
            return;
        mFqdn = homesp.mFqdn;
        mFriendlyName = homesp.mFriendlyName;
        mIconUrl = homesp.mIconUrl;
        if(homesp.mHomeNetworkIds != null)
            mHomeNetworkIds = Collections.unmodifiableMap(homesp.mHomeNetworkIds);
        if(homesp.mMatchAllOis != null)
            mMatchAllOis = Arrays.copyOf(homesp.mMatchAllOis, homesp.mMatchAllOis.length);
        if(homesp.mMatchAnyOis != null)
            mMatchAnyOis = Arrays.copyOf(homesp.mMatchAnyOis, homesp.mMatchAnyOis.length);
        if(homesp.mOtherHomePartners != null)
            mOtherHomePartners = (String[])Arrays.copyOf(homesp.mOtherHomePartners, homesp.mOtherHomePartners.length);
        if(homesp.mRoamingConsortiumOis != null)
            mRoamingConsortiumOis = Arrays.copyOf(homesp.mRoamingConsortiumOis, homesp.mRoamingConsortiumOis.length);
    }

    private static void writeHomeNetworkIds(Parcel parcel, Map map)
    {
        if(map == null)
        {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(map.size());
        for(map = map.entrySet().iterator(); map.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)map.next();
            parcel.writeString((String)entry.getKey());
            if(entry.getValue() == null)
                parcel.writeLong(-1L);
            else
                parcel.writeLong(((Long)entry.getValue()).longValue());
        }

    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        boolean flag1;
        flag = false;
        if(this == obj)
            return true;
        if(!(obj instanceof HomeSp))
            return false;
        obj = (HomeSp)obj;
        flag1 = flag;
        if(!TextUtils.equals(mFqdn, ((HomeSp) (obj)).mFqdn)) goto _L2; else goto _L1
_L1:
        flag1 = flag;
        if(!TextUtils.equals(mFriendlyName, ((HomeSp) (obj)).mFriendlyName)) goto _L2; else goto _L3
_L3:
        flag1 = flag;
        if(!TextUtils.equals(mIconUrl, ((HomeSp) (obj)).mIconUrl)) goto _L2; else goto _L4
_L4:
        if(mHomeNetworkIds != null) goto _L6; else goto _L5
_L5:
        flag1 = flag;
        if(((HomeSp) (obj)).mHomeNetworkIds != null) goto _L2; else goto _L7
_L7:
        flag1 = flag;
        if(Arrays.equals(mMatchAllOis, ((HomeSp) (obj)).mMatchAllOis))
        {
            flag1 = flag;
            if(Arrays.equals(mMatchAnyOis, ((HomeSp) (obj)).mMatchAnyOis))
            {
                flag1 = flag;
                if(Arrays.equals(mOtherHomePartners, ((HomeSp) (obj)).mOtherHomePartners))
                    flag1 = Arrays.equals(mRoamingConsortiumOis, ((HomeSp) (obj)).mRoamingConsortiumOis);
            }
        }
_L2:
        return flag1;
_L6:
        flag1 = flag;
        if(!mHomeNetworkIds.equals(((HomeSp) (obj)).mHomeNetworkIds)) goto _L2; else goto _L7
    }

    public String getFqdn()
    {
        return mFqdn;
    }

    public String getFriendlyName()
    {
        return mFriendlyName;
    }

    public Map getHomeNetworkIds()
    {
        return mHomeNetworkIds;
    }

    public String getIconUrl()
    {
        return mIconUrl;
    }

    public long[] getMatchAllOis()
    {
        return mMatchAllOis;
    }

    public long[] getMatchAnyOis()
    {
        return mMatchAnyOis;
    }

    public String[] getOtherHomePartners()
    {
        return mOtherHomePartners;
    }

    public long[] getRoamingConsortiumOis()
    {
        return mRoamingConsortiumOis;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mFqdn, mFriendlyName, mIconUrl, mHomeNetworkIds, mMatchAllOis, mMatchAnyOis, mOtherHomePartners, mRoamingConsortiumOis
        });
    }

    public void setFqdn(String s)
    {
        mFqdn = s;
    }

    public void setFriendlyName(String s)
    {
        mFriendlyName = s;
    }

    public void setHomeNetworkIds(Map map)
    {
        mHomeNetworkIds = map;
    }

    public void setIconUrl(String s)
    {
        mIconUrl = s;
    }

    public void setMatchAllOis(long al[])
    {
        mMatchAllOis = al;
    }

    public void setMatchAnyOis(long al[])
    {
        mMatchAnyOis = al;
    }

    public void setOtherHomePartners(String as[])
    {
        mOtherHomePartners = as;
    }

    public void setRoamingConsortiumOis(long al[])
    {
        mRoamingConsortiumOis = al;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("FQDN: ").append(mFqdn).append("\n");
        stringbuilder.append("FriendlyName: ").append(mFriendlyName).append("\n");
        stringbuilder.append("IconURL: ").append(mIconUrl).append("\n");
        stringbuilder.append("HomeNetworkIDs: ").append(mHomeNetworkIds).append("\n");
        stringbuilder.append("MatchAllOIs: ").append(mMatchAllOis).append("\n");
        stringbuilder.append("MatchAnyOIs: ").append(mMatchAnyOis).append("\n");
        stringbuilder.append("OtherHomePartners: ").append(mOtherHomePartners).append("\n");
        stringbuilder.append("RoamingConsortiumOIs: ").append(mRoamingConsortiumOis).append("\n");
        return stringbuilder.toString();
    }

    public boolean validate()
    {
label0:
        {
            if(TextUtils.isEmpty(mFqdn))
            {
                Log.d("HomeSp", "Missing FQDN");
                return false;
            }
            if(TextUtils.isEmpty(mFriendlyName))
            {
                Log.d("HomeSp", "Missing friendly name");
                return false;
            }
            if(mHomeNetworkIds == null)
                break label0;
            Iterator iterator = mHomeNetworkIds.entrySet().iterator();
            java.util.Map.Entry entry;
            do
            {
                if(!iterator.hasNext())
                    break label0;
                entry = (java.util.Map.Entry)iterator.next();
            } while(entry.getKey() != null && ((String)entry.getKey()).getBytes(StandardCharsets.UTF_8).length <= 32);
            Log.d("HomeSp", "Invalid SSID in HomeNetworkIDs");
            return false;
        }
        return true;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mFqdn);
        parcel.writeString(mFriendlyName);
        parcel.writeString(mIconUrl);
        writeHomeNetworkIds(parcel, mHomeNetworkIds);
        parcel.writeLongArray(mMatchAllOis);
        parcel.writeLongArray(mMatchAnyOis);
        parcel.writeStringArray(mOtherHomePartners);
        parcel.writeLongArray(mRoamingConsortiumOis);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        private Map readHomeNetworkIds(Parcel parcel)
        {
            int i = parcel.readInt();
            if(i == -1)
                return null;
            HashMap hashmap = new HashMap(i);
            for(int j = 0; j < i; j++)
            {
                String s = parcel.readString();
                Long long1 = null;
                long l = parcel.readLong();
                if(l != -1L)
                    long1 = Long.valueOf(l);
                hashmap.put(s, long1);
            }

            return hashmap;
        }

        public HomeSp createFromParcel(Parcel parcel)
        {
            HomeSp homesp = new HomeSp();
            homesp.setFqdn(parcel.readString());
            homesp.setFriendlyName(parcel.readString());
            homesp.setIconUrl(parcel.readString());
            homesp.setHomeNetworkIds(readHomeNetworkIds(parcel));
            homesp.setMatchAllOis(parcel.createLongArray());
            homesp.setMatchAnyOis(parcel.createLongArray());
            homesp.setOtherHomePartners(parcel.createStringArray());
            homesp.setRoamingConsortiumOis(parcel.createLongArray());
            return homesp;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public HomeSp[] newArray(int i)
        {
            return new HomeSp[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int MAX_SSID_BYTES = 32;
    private static final int NULL_VALUE = -1;
    private static final String TAG = "HomeSp";
    private String mFqdn;
    private String mFriendlyName;
    private Map mHomeNetworkIds;
    private String mIconUrl;
    private long mMatchAllOis[];
    private long mMatchAnyOis[];
    private String mOtherHomePartners[];
    private long mRoamingConsortiumOis[];

}
