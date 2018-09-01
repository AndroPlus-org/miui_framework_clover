// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.content.ComponentName;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public final class NetworkScorerAppData
    implements Parcelable
{

    public NetworkScorerAppData(int i, ComponentName componentname, String s, ComponentName componentname1, String s1)
    {
        packageUid = i;
        mRecommendationService = componentname;
        mRecommendationServiceLabel = s;
        mEnableUseOpenWifiActivity = componentname1;
        mNetworkAvailableNotificationChannelId = s1;
    }

    protected NetworkScorerAppData(Parcel parcel)
    {
        packageUid = parcel.readInt();
        mRecommendationService = ComponentName.readFromParcel(parcel);
        mRecommendationServiceLabel = parcel.readString();
        mEnableUseOpenWifiActivity = ComponentName.readFromParcel(parcel);
        mNetworkAvailableNotificationChannelId = parcel.readString();
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
        obj = (NetworkScorerAppData)obj;
        boolean flag1 = flag;
        if(packageUid == ((NetworkScorerAppData) (obj)).packageUid)
        {
            flag1 = flag;
            if(Objects.equals(mRecommendationService, ((NetworkScorerAppData) (obj)).mRecommendationService))
            {
                flag1 = flag;
                if(Objects.equals(mRecommendationServiceLabel, ((NetworkScorerAppData) (obj)).mRecommendationServiceLabel))
                {
                    flag1 = flag;
                    if(Objects.equals(mEnableUseOpenWifiActivity, ((NetworkScorerAppData) (obj)).mEnableUseOpenWifiActivity))
                        flag1 = Objects.equals(mNetworkAvailableNotificationChannelId, ((NetworkScorerAppData) (obj)).mNetworkAvailableNotificationChannelId);
                }
            }
        }
        return flag1;
    }

    public ComponentName getEnableUseOpenWifiActivity()
    {
        return mEnableUseOpenWifiActivity;
    }

    public String getNetworkAvailableNotificationChannelId()
    {
        return mNetworkAvailableNotificationChannelId;
    }

    public ComponentName getRecommendationServiceComponent()
    {
        return mRecommendationService;
    }

    public String getRecommendationServiceLabel()
    {
        return mRecommendationServiceLabel;
    }

    public String getRecommendationServicePackageName()
    {
        return mRecommendationService.getPackageName();
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(packageUid), mRecommendationService, mRecommendationServiceLabel, mEnableUseOpenWifiActivity, mNetworkAvailableNotificationChannelId
        });
    }

    public String toString()
    {
        return (new StringBuilder()).append("NetworkScorerAppData{packageUid=").append(packageUid).append(", mRecommendationService=").append(mRecommendationService).append(", mRecommendationServiceLabel=").append(mRecommendationServiceLabel).append(", mEnableUseOpenWifiActivity=").append(mEnableUseOpenWifiActivity).append(", mNetworkAvailableNotificationChannelId=").append(mNetworkAvailableNotificationChannelId).append('}').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(packageUid);
        ComponentName.writeToParcel(mRecommendationService, parcel);
        parcel.writeString(mRecommendationServiceLabel);
        ComponentName.writeToParcel(mEnableUseOpenWifiActivity, parcel);
        parcel.writeString(mNetworkAvailableNotificationChannelId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NetworkScorerAppData createFromParcel(Parcel parcel)
        {
            return new NetworkScorerAppData(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NetworkScorerAppData[] newArray(int i)
        {
            return new NetworkScorerAppData[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final ComponentName mEnableUseOpenWifiActivity;
    private final String mNetworkAvailableNotificationChannelId;
    private final ComponentName mRecommendationService;
    private final String mRecommendationServiceLabel;
    public final int packageUid;

}
