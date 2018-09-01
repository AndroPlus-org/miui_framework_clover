// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.os.Parcel;
import android.os.Parcelable;

public class PasspointManagementObjectDefinition
    implements Parcelable
{

    public PasspointManagementObjectDefinition(String s, String s1, String s2)
    {
        mBaseUri = s;
        mUrn = s1;
        mMoTree = s2;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getBaseUri()
    {
        return mBaseUri;
    }

    public String getMoTree()
    {
        return mMoTree;
    }

    public String getUrn()
    {
        return mUrn;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mBaseUri);
        parcel.writeString(mUrn);
        parcel.writeString(mMoTree);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PasspointManagementObjectDefinition createFromParcel(Parcel parcel)
        {
            return new PasspointManagementObjectDefinition(parcel.readString(), parcel.readString(), parcel.readString());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PasspointManagementObjectDefinition[] newArray(int i)
        {
            return new PasspointManagementObjectDefinition[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final String mBaseUri;
    private final String mMoTree;
    private final String mUrn;

}
