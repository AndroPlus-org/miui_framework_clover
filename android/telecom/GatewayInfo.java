// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class GatewayInfo
    implements Parcelable
{

    public GatewayInfo(String s, Uri uri, Uri uri1)
    {
        mGatewayProviderPackageName = s;
        mGatewayAddress = uri;
        mOriginalAddress = uri1;
    }

    public int describeContents()
    {
        return 0;
    }

    public Uri getGatewayAddress()
    {
        return mGatewayAddress;
    }

    public String getGatewayProviderPackageName()
    {
        return mGatewayProviderPackageName;
    }

    public Uri getOriginalAddress()
    {
        return mOriginalAddress;
    }

    public boolean isEmpty()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(!TextUtils.isEmpty(mGatewayProviderPackageName))
            if(mGatewayAddress == null)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mGatewayProviderPackageName);
        mGatewayAddress.writeToParcel(parcel, 0);
        mOriginalAddress.writeToParcel(parcel, 0);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public GatewayInfo createFromParcel(Parcel parcel)
        {
            return new GatewayInfo(parcel.readString(), (Uri)Uri.CREATOR.createFromParcel(parcel), (Uri)Uri.CREATOR.createFromParcel(parcel));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public GatewayInfo[] newArray(int i)
        {
            return new GatewayInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final Uri mGatewayAddress;
    private final String mGatewayProviderPackageName;
    private final Uri mOriginalAddress;

}
