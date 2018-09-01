// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.internal.util.Preconditions;
import java.util.Objects;

// Referenced classes of package android.net:
//            NetworkSpecifier

public final class StringNetworkSpecifier extends NetworkSpecifier
    implements Parcelable
{

    public StringNetworkSpecifier(String s)
    {
        Preconditions.checkStringNotEmpty(s);
        specifier = s;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof StringNetworkSpecifier))
            return false;
        else
            return TextUtils.equals(specifier, ((StringNetworkSpecifier)obj).specifier);
    }

    public int hashCode()
    {
        return Objects.hashCode(specifier);
    }

    public boolean satisfiedBy(NetworkSpecifier networkspecifier)
    {
        return equals(networkspecifier);
    }

    public String toString()
    {
        return specifier;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(specifier);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public StringNetworkSpecifier createFromParcel(Parcel parcel)
        {
            return new StringNetworkSpecifier(parcel.readString());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public StringNetworkSpecifier[] newArray(int i)
        {
            return new StringNetworkSpecifier[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final String specifier;

}
