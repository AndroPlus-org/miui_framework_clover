// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.os.Parcel;
import android.os.Parcelable;

public final class WebViewProviderInfo
    implements Parcelable
{

    private WebViewProviderInfo(Parcel parcel)
    {
        boolean flag = true;
        super();
        packageName = parcel.readString();
        description = parcel.readString();
        boolean flag1;
        if(parcel.readInt() > 0)
            flag1 = true;
        else
            flag1 = false;
        availableByDefault = flag1;
        if(parcel.readInt() > 0)
            flag1 = flag;
        else
            flag1 = false;
        isFallback = flag1;
        signatures = parcel.createStringArray();
    }

    WebViewProviderInfo(Parcel parcel, WebViewProviderInfo webviewproviderinfo)
    {
        this(parcel);
    }

    public WebViewProviderInfo(String s, String s1, boolean flag, boolean flag1, String as[])
    {
        packageName = s;
        description = s1;
        availableByDefault = flag;
        isFallback = flag1;
        signatures = as;
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeString(packageName);
        parcel.writeString(description);
        if(availableByDefault)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(isFallback)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeStringArray(signatures);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WebViewProviderInfo createFromParcel(Parcel parcel)
        {
            return new WebViewProviderInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WebViewProviderInfo[] newArray(int i)
        {
            return new WebViewProviderInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final boolean availableByDefault;
    public final String description;
    public final boolean isFallback;
    public final String packageName;
    public final String signatures[];

}
