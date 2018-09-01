// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.content.pm:
//            FeatureInfo

public final class FeatureGroupInfo
    implements Parcelable
{

    public FeatureGroupInfo()
    {
    }

    public FeatureGroupInfo(FeatureGroupInfo featuregroupinfo)
    {
        features = featuregroupinfo.features;
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeTypedArray(features, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public FeatureGroupInfo createFromParcel(Parcel parcel)
        {
            FeatureGroupInfo featuregroupinfo = new FeatureGroupInfo();
            featuregroupinfo.features = (FeatureInfo[])parcel.createTypedArray(FeatureInfo.CREATOR);
            return featuregroupinfo;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public FeatureGroupInfo[] newArray(int i)
        {
            return new FeatureGroupInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public FeatureInfo features[];

}
