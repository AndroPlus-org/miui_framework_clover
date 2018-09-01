// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.content.pm.PackageInfo;
import android.os.Parcel;
import android.os.Parcelable;

public final class WebViewProviderResponse
    implements Parcelable
{

    public WebViewProviderResponse(PackageInfo packageinfo, int i)
    {
        packageInfo = packageinfo;
        status = i;
    }

    private WebViewProviderResponse(Parcel parcel)
    {
        packageInfo = (PackageInfo)parcel.readTypedObject(PackageInfo.CREATOR);
        status = parcel.readInt();
    }

    WebViewProviderResponse(Parcel parcel, WebViewProviderResponse webviewproviderresponse)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeTypedObject(packageInfo, i);
        parcel.writeInt(status);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WebViewProviderResponse createFromParcel(Parcel parcel)
        {
            return new WebViewProviderResponse(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WebViewProviderResponse[] newArray(int i)
        {
            return new WebViewProviderResponse[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final PackageInfo packageInfo;
    public final int status;

}
