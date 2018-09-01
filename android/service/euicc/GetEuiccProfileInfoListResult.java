// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.euicc;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.service.euicc:
//            EuiccProfileInfo

public final class GetEuiccProfileInfoListResult
    implements Parcelable
{

    public GetEuiccProfileInfoListResult(int i, EuiccProfileInfo aeuiccprofileinfo[], boolean flag)
    {
        result = i;
        isRemovable = flag;
        if(result == 0)
        {
            profiles = aeuiccprofileinfo;
        } else
        {
            if(aeuiccprofileinfo != null)
                throw new IllegalArgumentException((new StringBuilder()).append("Error result with non-null profiles: ").append(i).toString());
            profiles = null;
        }
    }

    private GetEuiccProfileInfoListResult(Parcel parcel)
    {
        result = parcel.readInt();
        profiles = (EuiccProfileInfo[])parcel.createTypedArray(EuiccProfileInfo.CREATOR);
        isRemovable = parcel.readBoolean();
    }

    GetEuiccProfileInfoListResult(Parcel parcel, GetEuiccProfileInfoListResult geteuiccprofileinfolistresult)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(result);
        parcel.writeTypedArray(profiles, i);
        parcel.writeBoolean(isRemovable);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public GetEuiccProfileInfoListResult createFromParcel(Parcel parcel)
        {
            return new GetEuiccProfileInfoListResult(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public GetEuiccProfileInfoListResult[] newArray(int i)
        {
            return new GetEuiccProfileInfoListResult[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final boolean isRemovable;
    public final EuiccProfileInfo profiles[];
    public final int result;

}
