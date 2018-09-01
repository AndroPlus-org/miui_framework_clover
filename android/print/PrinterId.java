// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.content.ComponentName;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;

public final class PrinterId
    implements Parcelable
{

    public PrinterId(ComponentName componentname, String s)
    {
        mServiceName = componentname;
        mLocalId = s;
    }

    private PrinterId(Parcel parcel)
    {
        mServiceName = (ComponentName)Preconditions.checkNotNull((ComponentName)parcel.readParcelable(null));
        mLocalId = (String)Preconditions.checkNotNull(parcel.readString());
    }

    PrinterId(Parcel parcel, PrinterId printerid)
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
        obj = (PrinterId)obj;
        if(!mServiceName.equals(((PrinterId) (obj)).mServiceName))
            return false;
        return mLocalId.equals(((PrinterId) (obj)).mLocalId);
    }

    public String getLocalId()
    {
        return mLocalId;
    }

    public ComponentName getServiceName()
    {
        return mServiceName;
    }

    public int hashCode()
    {
        return (mServiceName.hashCode() + 31) * 31 + mLocalId.hashCode();
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("PrinterId{");
        stringbuilder.append("serviceName=").append(mServiceName.flattenToString());
        stringbuilder.append(", localId=").append(mLocalId);
        stringbuilder.append('}');
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mServiceName, i);
        parcel.writeString(mLocalId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PrinterId createFromParcel(Parcel parcel)
        {
            return new PrinterId(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PrinterId[] newArray(int i)
        {
            return new PrinterId[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final String mLocalId;
    private final ComponentName mServiceName;

}
