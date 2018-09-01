// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.accounts.Account;
import android.os.*;
import java.util.Iterator;
import java.util.Objects;

public class PeriodicSync
    implements Parcelable
{

    public PeriodicSync(Account account1, String s, Bundle bundle, long l)
    {
        account = account1;
        authority = s;
        if(bundle == null)
            extras = new Bundle();
        else
            extras = new Bundle(bundle);
        period = l;
        flexTime = 0L;
    }

    public PeriodicSync(Account account1, String s, Bundle bundle, long l, long l1)
    {
        account = account1;
        authority = s;
        extras = new Bundle(bundle);
        period = l;
        flexTime = l1;
    }

    public PeriodicSync(PeriodicSync periodicsync)
    {
        account = periodicsync.account;
        authority = periodicsync.authority;
        extras = new Bundle(periodicsync.extras);
        period = periodicsync.period;
        flexTime = periodicsync.flexTime;
    }

    private PeriodicSync(Parcel parcel)
    {
        account = (Account)parcel.readParcelable(null);
        authority = parcel.readString();
        extras = parcel.readBundle();
        period = parcel.readLong();
        flexTime = parcel.readLong();
    }

    PeriodicSync(Parcel parcel, PeriodicSync periodicsync)
    {
        this(parcel);
    }

    public static boolean syncExtrasEquals(Bundle bundle, Bundle bundle1)
    {
        if(bundle.size() != bundle1.size())
            return false;
        if(bundle.isEmpty())
            return true;
        for(Iterator iterator = bundle.keySet().iterator(); iterator.hasNext();)
        {
            String s = (String)iterator.next();
            if(!bundle1.containsKey(s))
                return false;
            if(!Objects.equals(bundle.get(s), bundle1.get(s)))
                return false;
        }

        return true;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == this)
            return true;
        if(!(obj instanceof PeriodicSync))
            return false;
        obj = (PeriodicSync)obj;
        boolean flag1 = flag;
        if(account.equals(((PeriodicSync) (obj)).account))
        {
            flag1 = flag;
            if(authority.equals(((PeriodicSync) (obj)).authority))
            {
                flag1 = flag;
                if(period == ((PeriodicSync) (obj)).period)
                    flag1 = syncExtrasEquals(extras, ((PeriodicSync) (obj)).extras);
            }
        }
        return flag1;
    }

    public String toString()
    {
        return (new StringBuilder()).append("account: ").append(account).append(", authority: ").append(authority).append(". period: ").append(period).append("s ").append(", flex: ").append(flexTime).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(account, i);
        parcel.writeString(authority);
        parcel.writeBundle(extras);
        parcel.writeLong(period);
        parcel.writeLong(flexTime);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PeriodicSync createFromParcel(Parcel parcel)
        {
            return new PeriodicSync(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PeriodicSync[] newArray(int i)
        {
            return new PeriodicSync[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final Account account;
    public final String authority;
    public final Bundle extras;
    public final long flexTime;
    public final long period;

}
