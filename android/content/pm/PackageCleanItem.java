// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public class PackageCleanItem
    implements Parcelable
{

    public PackageCleanItem(int i, String s, boolean flag)
    {
        userId = i;
        packageName = s;
        andCode = flag;
    }

    private PackageCleanItem(Parcel parcel)
    {
        boolean flag = false;
        super();
        userId = parcel.readInt();
        packageName = parcel.readString();
        if(parcel.readInt() != 0)
            flag = true;
        andCode = flag;
    }

    PackageCleanItem(Parcel parcel, PackageCleanItem packagecleanitem)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        flag = true;
        if(this == obj)
            return true;
        if(obj == null)
            break MISSING_BLOCK_LABEL_73;
        obj = (PackageCleanItem)obj;
        if(userId != ((PackageCleanItem) (obj)).userId || !packageName.equals(((PackageCleanItem) (obj)).packageName)) goto _L2; else goto _L1
_L1:
        boolean flag1;
        boolean flag2;
        flag1 = andCode;
        flag2 = ((PackageCleanItem) (obj)).andCode;
        if(flag1 != flag2)
            flag = false;
_L4:
        return flag;
_L2:
        flag = false;
        if(true) goto _L4; else goto _L3
_L3:
        obj;
        return false;
    }

    public int hashCode()
    {
        int i = userId;
        int j = packageName.hashCode();
        int k;
        if(andCode)
            k = 1;
        else
            k = 0;
        return ((i + 527) * 31 + j) * 31 + k;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(userId);
        parcel.writeString(packageName);
        if(andCode)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PackageCleanItem createFromParcel(Parcel parcel)
        {
            return new PackageCleanItem(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PackageCleanItem[] newArray(int i)
        {
            return new PackageCleanItem[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final boolean andCode;
    public final String packageName;
    public final int userId;

}
