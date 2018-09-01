// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.os.Parcel;
import android.os.Parcelable;

public class BackupProgress
    implements Parcelable
{

    public BackupProgress(long l, long l1)
    {
        bytesExpected = l;
        bytesTransferred = l1;
    }

    private BackupProgress(Parcel parcel)
    {
        bytesExpected = parcel.readLong();
        bytesTransferred = parcel.readLong();
    }

    BackupProgress(Parcel parcel, BackupProgress backupprogress)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(bytesExpected);
        parcel.writeLong(bytesTransferred);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BackupProgress createFromParcel(Parcel parcel)
        {
            return new BackupProgress(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BackupProgress[] newArray(int i)
        {
            return new BackupProgress[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final long bytesExpected;
    public final long bytesTransferred;

}
