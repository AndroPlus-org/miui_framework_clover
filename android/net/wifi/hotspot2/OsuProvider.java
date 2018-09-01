// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.hotspot2;

import android.graphics.drawable.Icon;
import android.net.Uri;
import android.net.wifi.WifiSsid;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.*;

public final class OsuProvider
    implements Parcelable
{

    public OsuProvider(WifiSsid wifissid, String s, String s1, Uri uri, String s2, List list, Icon icon)
    {
        mOsuSsid = wifissid;
        mFriendlyName = s;
        mServiceDescription = s1;
        mServerUri = uri;
        mNetworkAccessIdentifier = s2;
        if(list == null)
            mMethodList = new ArrayList();
        else
            mMethodList = new ArrayList(list);
        mIcon = icon;
    }

    public OsuProvider(OsuProvider osuprovider)
    {
        if(osuprovider == null)
        {
            mOsuSsid = null;
            mFriendlyName = null;
            mServiceDescription = null;
            mServerUri = null;
            mNetworkAccessIdentifier = null;
            mMethodList = new ArrayList();
            mIcon = null;
            return;
        }
        mOsuSsid = osuprovider.mOsuSsid;
        mFriendlyName = osuprovider.mFriendlyName;
        mServiceDescription = osuprovider.mServiceDescription;
        mServerUri = osuprovider.mServerUri;
        mNetworkAccessIdentifier = osuprovider.mNetworkAccessIdentifier;
        if(osuprovider.mMethodList == null)
            mMethodList = new ArrayList();
        else
            mMethodList = new ArrayList(osuprovider.mMethodList);
        mIcon = osuprovider.mIcon;
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
        if(!(obj instanceof OsuProvider))
            return false;
        obj = (OsuProvider)obj;
        if((mOsuSsid != null ? mOsuSsid.equals(((OsuProvider) (obj)).mOsuSsid) : ((OsuProvider) (obj)).mOsuSsid == null) && (TextUtils.equals(mFriendlyName, ((OsuProvider) (obj)).mFriendlyName) && TextUtils.equals(mServiceDescription, ((OsuProvider) (obj)).mServiceDescription)) && (mServerUri != null ? mServerUri.equals(((OsuProvider) (obj)).mServerUri) : ((OsuProvider) (obj)).mServerUri == null) && TextUtils.equals(mNetworkAccessIdentifier, ((OsuProvider) (obj)).mNetworkAccessIdentifier) && (mMethodList != null ? mMethodList.equals(((OsuProvider) (obj)).mMethodList) : ((OsuProvider) (obj)).mMethodList == null))
        {
            if(mIcon == null)
            {
                if(((OsuProvider) (obj)).mIcon != null)
                    flag = false;
            } else
            {
                flag = mIcon.sameAs(((OsuProvider) (obj)).mIcon);
            }
        } else
        {
            flag = false;
        }
        return flag;
    }

    public String getFriendlyName()
    {
        return mFriendlyName;
    }

    public Icon getIcon()
    {
        return mIcon;
    }

    public List getMethodList()
    {
        return Collections.unmodifiableList(mMethodList);
    }

    public String getNetworkAccessIdentifier()
    {
        return mNetworkAccessIdentifier;
    }

    public WifiSsid getOsuSsid()
    {
        return mOsuSsid;
    }

    public Uri getServerUri()
    {
        return mServerUri;
    }

    public String getServiceDescription()
    {
        return mServiceDescription;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mOsuSsid, mFriendlyName, mServiceDescription, mServerUri, mNetworkAccessIdentifier, mMethodList, mIcon
        });
    }

    public String toString()
    {
        return (new StringBuilder()).append("OsuProvider{mOsuSsid=").append(mOsuSsid).append(" mFriendlyName=").append(mFriendlyName).append(" mServiceDescription=").append(mServiceDescription).append(" mServerUri=").append(mServerUri).append(" mNetworkAccessIdentifier=").append(mNetworkAccessIdentifier).append(" mMethodList=").append(mMethodList).append(" mIcon=").append(mIcon).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mOsuSsid, i);
        parcel.writeString(mFriendlyName);
        parcel.writeString(mServiceDescription);
        parcel.writeParcelable(mServerUri, i);
        parcel.writeString(mNetworkAccessIdentifier);
        parcel.writeList(mMethodList);
        parcel.writeParcelable(mIcon, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public OsuProvider createFromParcel(Parcel parcel)
        {
            WifiSsid wifissid = (WifiSsid)parcel.readParcelable(null);
            String s = parcel.readString();
            String s1 = parcel.readString();
            Uri uri = (Uri)parcel.readParcelable(null);
            String s2 = parcel.readString();
            ArrayList arraylist = new ArrayList();
            parcel.readList(arraylist, null);
            return new OsuProvider(wifissid, s, s1, uri, s2, arraylist, (Icon)parcel.readParcelable(null));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public OsuProvider[] newArray(int i)
        {
            return new OsuProvider[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int METHOD_OMA_DM = 0;
    public static final int METHOD_SOAP_XML_SPP = 1;
    private final String mFriendlyName;
    private final Icon mIcon;
    private final List mMethodList;
    private final String mNetworkAccessIdentifier;
    private final WifiSsid mOsuSsid;
    private final Uri mServerUri;
    private final String mServiceDescription;

}
