// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accounts;

import android.os.*;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import java.util.Set;

// Referenced classes of package android.accounts:
//            IAccountManager

public class Account
    implements Parcelable
{

    public Account(Account account, String s)
    {
        this(account.name, account.type, s);
    }

    public Account(Parcel parcel)
    {
        name = parcel.readString();
        type = parcel.readString();
        accessId = parcel.readString();
        if(accessId == null) goto _L2; else goto _L1
_L1:
        parcel = sAccessedAccounts;
        parcel;
        JVM INSTR monitorenter ;
        boolean flag = sAccessedAccounts.add(this);
        if(!flag)
            break MISSING_BLOCK_LABEL_72;
        IAccountManager.Stub.asInterface(ServiceManager.getService("account")).onAccountAccessed(accessId);
_L3:
        parcel;
        JVM INSTR monitorexit ;
_L2:
        return;
        Object obj;
        obj;
        Log.e("Account", "Error noting account access", ((Throwable) (obj)));
          goto _L3
        obj;
        throw obj;
    }

    public Account(String s, String s1)
    {
        this(s, s1, null);
    }

    public Account(String s, String s1, String s2)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException((new StringBuilder()).append("the name must not be empty: ").append(s).toString());
        if(TextUtils.isEmpty(s1))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("the type must not be empty: ").append(s1).toString());
        } else
        {
            name = s;
            type = s1;
            accessId = s2;
            return;
        }
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
        if(!(obj instanceof Account))
            return false;
        obj = (Account)obj;
        if(name.equals(((Account) (obj)).name))
            flag = type.equals(((Account) (obj)).type);
        return flag;
    }

    public String getAccessId()
    {
        return accessId;
    }

    public int hashCode()
    {
        return (name.hashCode() + 527) * 31 + type.hashCode();
    }

    public String hideNameToString()
    {
        String s = name;
        String s1 = s;
        if(s != null)
        {
            int i = s.length();
            int j = i / 2;
            s1 = s;
            if(i > 0)
                if(i > 3)
                    s1 = s.replace(s.substring(j - 1, j + 2), "***");
                else
                    s1 = s.replace(s.substring(0, i), "***");
        }
        return (new StringBuilder()).append("Account {name=").append(s1).append(", type=").append(type).append("}").toString();
    }

    public String toString()
    {
        return (new StringBuilder()).append("Account {name=").append(name).append(", type=").append(type).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(name);
        parcel.writeString(type);
        parcel.writeString(accessId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Account createFromParcel(Parcel parcel)
        {
            return new Account(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Account[] newArray(int i)
        {
            return new Account[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "Account";
    private static final Set sAccessedAccounts = new ArraySet();
    private final String accessId;
    public final String name;
    public final String type;

}
