// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;

public class SyncInfo
    implements Parcelable
{

    public SyncInfo(int i, Account account1, String s, long l)
    {
        authorityId = i;
        account = account1;
        authority = s;
        startTime = l;
    }

    public SyncInfo(SyncInfo syncinfo)
    {
        authorityId = syncinfo.authorityId;
        account = new Account(syncinfo.account.name, syncinfo.account.type);
        authority = syncinfo.authority;
        startTime = syncinfo.startTime;
    }

    SyncInfo(Parcel parcel)
    {
        authorityId = parcel.readInt();
        account = (Account)parcel.readParcelable(android/accounts/Account.getClassLoader());
        authority = parcel.readString();
        startTime = parcel.readLong();
    }

    public static SyncInfo createAccountRedacted(int i, String s, long l)
    {
        return new SyncInfo(i, REDACTED_ACCOUNT, s, l);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(authorityId);
        parcel.writeParcelable(account, i);
        parcel.writeString(authority);
        parcel.writeLong(startTime);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SyncInfo createFromParcel(Parcel parcel)
        {
            return new SyncInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SyncInfo[] newArray(int i)
        {
            return new SyncInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final Account REDACTED_ACCOUNT = new Account("*****", "*****");
    public final Account account;
    public final String authority;
    public final int authorityId;
    public final long startTime;

}
