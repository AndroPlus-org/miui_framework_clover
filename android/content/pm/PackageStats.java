// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.os.*;
import android.text.TextUtils;
import java.util.Objects;

public class PackageStats
    implements Parcelable
{

    public PackageStats(PackageStats packagestats)
    {
        packageName = packagestats.packageName;
        userHandle = packagestats.userHandle;
        codeSize = packagestats.codeSize;
        dataSize = packagestats.dataSize;
        cacheSize = packagestats.cacheSize;
        externalCodeSize = packagestats.externalCodeSize;
        externalDataSize = packagestats.externalDataSize;
        externalCacheSize = packagestats.externalCacheSize;
        externalMediaSize = packagestats.externalMediaSize;
        externalObbSize = packagestats.externalObbSize;
    }

    public PackageStats(Parcel parcel)
    {
        packageName = parcel.readString();
        userHandle = parcel.readInt();
        codeSize = parcel.readLong();
        dataSize = parcel.readLong();
        cacheSize = parcel.readLong();
        externalCodeSize = parcel.readLong();
        externalDataSize = parcel.readLong();
        externalCacheSize = parcel.readLong();
        externalMediaSize = parcel.readLong();
        externalObbSize = parcel.readLong();
    }

    public PackageStats(String s)
    {
        packageName = s;
        userHandle = UserHandle.myUserId();
    }

    public PackageStats(String s, int i)
    {
        packageName = s;
        userHandle = i;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof PackageStats))
            return false;
        obj = (PackageStats)obj;
        boolean flag1 = flag;
        if(TextUtils.equals(packageName, ((PackageStats) (obj)).packageName))
        {
            flag1 = flag;
            if(userHandle == ((PackageStats) (obj)).userHandle)
            {
                flag1 = flag;
                if(codeSize == ((PackageStats) (obj)).codeSize)
                {
                    flag1 = flag;
                    if(dataSize == ((PackageStats) (obj)).dataSize)
                    {
                        flag1 = flag;
                        if(cacheSize == ((PackageStats) (obj)).cacheSize)
                        {
                            flag1 = flag;
                            if(externalCodeSize == ((PackageStats) (obj)).externalCodeSize)
                            {
                                flag1 = flag;
                                if(externalDataSize == ((PackageStats) (obj)).externalDataSize)
                                {
                                    flag1 = flag;
                                    if(externalCacheSize == ((PackageStats) (obj)).externalCacheSize)
                                    {
                                        flag1 = flag;
                                        if(externalMediaSize == ((PackageStats) (obj)).externalMediaSize)
                                        {
                                            flag1 = flag;
                                            if(externalObbSize == ((PackageStats) (obj)).externalObbSize)
                                                flag1 = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            packageName, Integer.valueOf(userHandle), Long.valueOf(codeSize), Long.valueOf(dataSize), Long.valueOf(cacheSize), Long.valueOf(externalCodeSize), Long.valueOf(externalDataSize), Long.valueOf(externalCacheSize), Long.valueOf(externalMediaSize), Long.valueOf(externalObbSize)
        });
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("PackageStats{");
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringbuilder.append(" ");
        stringbuilder.append(packageName);
        if(codeSize != 0L)
        {
            stringbuilder.append(" code=");
            stringbuilder.append(codeSize);
        }
        if(dataSize != 0L)
        {
            stringbuilder.append(" data=");
            stringbuilder.append(dataSize);
        }
        if(cacheSize != 0L)
        {
            stringbuilder.append(" cache=");
            stringbuilder.append(cacheSize);
        }
        if(externalCodeSize != 0L)
        {
            stringbuilder.append(" extCode=");
            stringbuilder.append(externalCodeSize);
        }
        if(externalDataSize != 0L)
        {
            stringbuilder.append(" extData=");
            stringbuilder.append(externalDataSize);
        }
        if(externalCacheSize != 0L)
        {
            stringbuilder.append(" extCache=");
            stringbuilder.append(externalCacheSize);
        }
        if(externalMediaSize != 0L)
        {
            stringbuilder.append(" media=");
            stringbuilder.append(externalMediaSize);
        }
        if(externalObbSize != 0L)
        {
            stringbuilder.append(" obb=");
            stringbuilder.append(externalObbSize);
        }
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(packageName);
        parcel.writeInt(userHandle);
        parcel.writeLong(codeSize);
        parcel.writeLong(dataSize);
        parcel.writeLong(cacheSize);
        parcel.writeLong(externalCodeSize);
        parcel.writeLong(externalDataSize);
        parcel.writeLong(externalCacheSize);
        parcel.writeLong(externalMediaSize);
        parcel.writeLong(externalObbSize);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PackageStats createFromParcel(Parcel parcel)
        {
            return new PackageStats(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PackageStats[] newArray(int i)
        {
            return new PackageStats[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public long cacheSize;
    public long codeSize;
    public long dataSize;
    public long externalCacheSize;
    public long externalCodeSize;
    public long externalDataSize;
    public long externalMediaSize;
    public long externalObbSize;
    public String packageName;
    public int userHandle;

}
