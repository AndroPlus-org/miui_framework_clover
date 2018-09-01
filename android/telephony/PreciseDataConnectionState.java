// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.net.LinkProperties;
import android.os.Parcel;
import android.os.Parcelable;

public class PreciseDataConnectionState
    implements Parcelable
{

    public PreciseDataConnectionState()
    {
        mState = -1;
        mNetworkType = 0;
        mAPNType = "";
        mAPN = "";
        mReason = "";
        mLinkProperties = null;
        mFailCause = "";
    }

    public PreciseDataConnectionState(int i, int j, String s, String s1, String s2, LinkProperties linkproperties, String s3)
    {
        mState = -1;
        mNetworkType = 0;
        mAPNType = "";
        mAPN = "";
        mReason = "";
        mLinkProperties = null;
        mFailCause = "";
        mState = i;
        mNetworkType = j;
        mAPNType = s;
        mAPN = s1;
        mReason = s2;
        mLinkProperties = linkproperties;
        mFailCause = s3;
    }

    private PreciseDataConnectionState(Parcel parcel)
    {
        mState = -1;
        mNetworkType = 0;
        mAPNType = "";
        mAPN = "";
        mReason = "";
        mLinkProperties = null;
        mFailCause = "";
        mState = parcel.readInt();
        mNetworkType = parcel.readInt();
        mAPNType = parcel.readString();
        mAPN = parcel.readString();
        mReason = parcel.readString();
        mLinkProperties = (LinkProperties)parcel.readParcelable(null);
        mFailCause = parcel.readString();
    }

    PreciseDataConnectionState(Parcel parcel, PreciseDataConnectionState precisedataconnectionstate)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        obj = (PreciseDataConnectionState)obj;
        if(mAPN == null)
        {
            if(((PreciseDataConnectionState) (obj)).mAPN != null)
                return false;
        } else
        if(!mAPN.equals(((PreciseDataConnectionState) (obj)).mAPN))
            return false;
        if(mAPNType == null)
        {
            if(((PreciseDataConnectionState) (obj)).mAPNType != null)
                return false;
        } else
        if(!mAPNType.equals(((PreciseDataConnectionState) (obj)).mAPNType))
            return false;
        if(mFailCause == null)
        {
            if(((PreciseDataConnectionState) (obj)).mFailCause != null)
                return false;
        } else
        if(!mFailCause.equals(((PreciseDataConnectionState) (obj)).mFailCause))
            return false;
        if(mLinkProperties == null)
        {
            if(((PreciseDataConnectionState) (obj)).mLinkProperties != null)
                return false;
        } else
        if(!mLinkProperties.equals(((PreciseDataConnectionState) (obj)).mLinkProperties))
            return false;
        if(mNetworkType != ((PreciseDataConnectionState) (obj)).mNetworkType)
            return false;
        if(mReason == null)
        {
            if(((PreciseDataConnectionState) (obj)).mReason != null)
                return false;
        } else
        if(!mReason.equals(((PreciseDataConnectionState) (obj)).mReason))
            return false;
        return mState == ((PreciseDataConnectionState) (obj)).mState;
    }

    public String getDataConnectionAPN()
    {
        return mAPN;
    }

    public String getDataConnectionAPNType()
    {
        return mAPNType;
    }

    public String getDataConnectionChangeReason()
    {
        return mReason;
    }

    public String getDataConnectionFailCause()
    {
        return mFailCause;
    }

    public LinkProperties getDataConnectionLinkProperties()
    {
        return mLinkProperties;
    }

    public int getDataConnectionNetworkType()
    {
        return mNetworkType;
    }

    public int getDataConnectionState()
    {
        return mState;
    }

    public int hashCode()
    {
        int i = 0;
        int j = mState;
        int k = mNetworkType;
        int l;
        int i1;
        int j1;
        int k1;
        if(mAPNType == null)
            l = 0;
        else
            l = mAPNType.hashCode();
        if(mAPN == null)
            i1 = 0;
        else
            i1 = mAPN.hashCode();
        if(mReason == null)
            j1 = 0;
        else
            j1 = mReason.hashCode();
        if(mLinkProperties == null)
            k1 = 0;
        else
            k1 = mLinkProperties.hashCode();
        if(mFailCause != null)
            i = mFailCause.hashCode();
        return ((((((j + 31) * 31 + k) * 31 + l) * 31 + i1) * 31 + j1) * 31 + k1) * 31 + i;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Data Connection state: ").append(mState);
        stringbuilder.append(", Network type: ").append(mNetworkType);
        stringbuilder.append(", APN type: ").append(mAPNType);
        stringbuilder.append(", APN: ").append(mAPN);
        stringbuilder.append(", Change reason: ").append(mReason);
        stringbuilder.append(", Link properties: ").append(mLinkProperties);
        stringbuilder.append(", Fail cause: ").append(mFailCause);
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mState);
        parcel.writeInt(mNetworkType);
        parcel.writeString(mAPNType);
        parcel.writeString(mAPN);
        parcel.writeString(mReason);
        parcel.writeParcelable(mLinkProperties, i);
        parcel.writeString(mFailCause);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PreciseDataConnectionState createFromParcel(Parcel parcel)
        {
            return new PreciseDataConnectionState(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PreciseDataConnectionState[] newArray(int i)
        {
            return new PreciseDataConnectionState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private String mAPN;
    private String mAPNType;
    private String mFailCause;
    private LinkProperties mLinkProperties;
    private int mNetworkType;
    private String mReason;
    private int mState;

}
