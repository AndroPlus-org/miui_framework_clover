// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import java.util.UUID;

// Referenced classes of package android.os:
//            Parcelable, Parcel

public final class ParcelUuid
    implements Parcelable
{

    public ParcelUuid(UUID uuid)
    {
        mUuid = uuid;
    }

    public static ParcelUuid fromString(String s)
    {
        return new ParcelUuid(UUID.fromString(s));
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(!(obj instanceof ParcelUuid))
        {
            return false;
        } else
        {
            obj = (ParcelUuid)obj;
            return mUuid.equals(((ParcelUuid) (obj)).mUuid);
        }
    }

    public UUID getUuid()
    {
        return mUuid;
    }

    public int hashCode()
    {
        return mUuid.hashCode();
    }

    public String toString()
    {
        return mUuid.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(mUuid.getMostSignificantBits());
        parcel.writeLong(mUuid.getLeastSignificantBits());
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public ParcelUuid createFromParcel(Parcel parcel)
        {
            return new ParcelUuid(new UUID(parcel.readLong(), parcel.readLong()));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ParcelUuid[] newArray(int i)
        {
            return new ParcelUuid[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final UUID mUuid;

}
