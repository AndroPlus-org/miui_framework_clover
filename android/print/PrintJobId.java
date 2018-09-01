// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.util.UUID;

public final class PrintJobId
    implements Parcelable
{

    public PrintJobId()
    {
        this(UUID.randomUUID().toString());
    }

    public PrintJobId(String s)
    {
        mValue = s;
    }

    public static PrintJobId unflattenFromString(String s)
    {
        return new PrintJobId(s);
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
        obj = (PrintJobId)obj;
        return mValue.equals(((PrintJobId) (obj)).mValue);
    }

    public String flattenToString()
    {
        return mValue;
    }

    public int hashCode()
    {
        return mValue.hashCode() + 31;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mValue);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PrintJobId createFromParcel(Parcel parcel)
        {
            return new PrintJobId((String)Preconditions.checkNotNull(parcel.readString()));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PrintJobId[] newArray(int i)
        {
            return new PrintJobId[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final String mValue;

}
