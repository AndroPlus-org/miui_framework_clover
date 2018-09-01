// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.net:
//            NetworkSpecifier

public final class MatchAllNetworkSpecifier extends NetworkSpecifier
    implements Parcelable
{

    public MatchAllNetworkSpecifier()
    {
    }

    public static void checkNotMatchAllNetworkSpecifier(NetworkSpecifier networkspecifier)
    {
        if(networkspecifier instanceof MatchAllNetworkSpecifier)
            throw new IllegalArgumentException("A MatchAllNetworkSpecifier is not permitted");
        else
            return;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        return obj instanceof MatchAllNetworkSpecifier;
    }

    public int hashCode()
    {
        return 0;
    }

    public boolean satisfiedBy(NetworkSpecifier networkspecifier)
    {
        throw new IllegalStateException("MatchAllNetworkSpecifier must not be used in NetworkRequests");
    }

    public void writeToParcel(Parcel parcel, int i)
    {
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public MatchAllNetworkSpecifier createFromParcel(Parcel parcel)
        {
            return new MatchAllNetworkSpecifier();
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public MatchAllNetworkSpecifier[] newArray(int i)
        {
            return new MatchAllNetworkSpecifier[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;

}
