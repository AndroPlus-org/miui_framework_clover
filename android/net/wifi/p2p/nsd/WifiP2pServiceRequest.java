// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.p2p.nsd;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

public class WifiP2pServiceRequest
    implements Parcelable
{

    private WifiP2pServiceRequest(int i, int j, int k, String s)
    {
        mProtocolType = i;
        mLength = j;
        mTransId = k;
        mQuery = s;
    }

    WifiP2pServiceRequest(int i, int j, int k, String s, WifiP2pServiceRequest wifip2pservicerequest)
    {
        this(i, j, k, s);
    }

    protected WifiP2pServiceRequest(int i, String s)
    {
        validateQuery(s);
        mProtocolType = i;
        mQuery = s;
        if(s != null)
            mLength = s.length() / 2 + 2;
        else
            mLength = 2;
    }

    public static WifiP2pServiceRequest newInstance(int i)
    {
        return new WifiP2pServiceRequest(i, null);
    }

    public static WifiP2pServiceRequest newInstance(int i, String s)
    {
        return new WifiP2pServiceRequest(i, s);
    }

    private void validateQuery(String s)
    {
        if(s == null)
            return;
        if(s.length() % 2 == 1)
            throw new IllegalArgumentException((new StringBuilder()).append("query size is invalid. query=").append(s).toString());
        if(s.length() / 2 > 65535)
            throw new IllegalArgumentException((new StringBuilder()).append("query size is too large. len=").append(s.length()).toString());
        s = s.toLowerCase(Locale.ROOT);
        char ac[] = s.toCharArray();
        int i = 0;
        for(int j = ac.length; i < j; i++)
        {
            char c = ac[i];
            if((c < '0' || c > '9') && (c < 'a' || c > 'f'))
                throw new IllegalArgumentException((new StringBuilder()).append("query should be hex string. query=").append(s).toString());
        }

    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;
        if(!(obj instanceof WifiP2pServiceRequest))
            return false;
        obj = (WifiP2pServiceRequest)obj;
        if(((WifiP2pServiceRequest) (obj)).mProtocolType != mProtocolType || ((WifiP2pServiceRequest) (obj)).mLength != mLength)
            return false;
        if(((WifiP2pServiceRequest) (obj)).mQuery == null && mQuery == null)
            return true;
        if(((WifiP2pServiceRequest) (obj)).mQuery != null)
            return ((WifiP2pServiceRequest) (obj)).mQuery.equals(mQuery);
        else
            return false;
    }

    public String getSupplicantQuery()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(String.format(Locale.US, "%02x", new Object[] {
            Integer.valueOf(mLength & 0xff)
        }));
        stringbuffer.append(String.format(Locale.US, "%02x", new Object[] {
            Integer.valueOf(mLength >> 8 & 0xff)
        }));
        stringbuffer.append(String.format(Locale.US, "%02x", new Object[] {
            Integer.valueOf(mProtocolType)
        }));
        stringbuffer.append(String.format(Locale.US, "%02x", new Object[] {
            Integer.valueOf(mTransId)
        }));
        if(mQuery != null)
            stringbuffer.append(mQuery);
        return stringbuffer.toString();
    }

    public int getTransactionId()
    {
        return mTransId;
    }

    public int hashCode()
    {
        int i = mProtocolType;
        int j = mLength;
        int k;
        if(mQuery == null)
            k = 0;
        else
            k = mQuery.hashCode();
        return ((i + 527) * 31 + j) * 31 + k;
    }

    public void setTransactionId(int i)
    {
        mTransId = i;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mProtocolType);
        parcel.writeInt(mLength);
        parcel.writeInt(mTransId);
        parcel.writeString(mQuery);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiP2pServiceRequest createFromParcel(Parcel parcel)
        {
            return new WifiP2pServiceRequest(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiP2pServiceRequest[] newArray(int i)
        {
            return new WifiP2pServiceRequest[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private int mLength;
    private int mProtocolType;
    private String mQuery;
    private int mTransId;

}
