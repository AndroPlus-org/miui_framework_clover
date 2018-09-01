// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class SyncAdapterType
    implements Parcelable
{

    public SyncAdapterType(Parcel parcel)
    {
        boolean flag = false;
        String s = parcel.readString();
        String s1 = parcel.readString();
        boolean flag1;
        boolean flag2;
        boolean flag3;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        if(parcel.readInt() != 0)
            flag2 = true;
        else
            flag2 = false;
        if(parcel.readInt() != 0)
            flag3 = true;
        else
            flag3 = false;
        if(parcel.readInt() != 0)
            flag = true;
        this(s, s1, flag1, flag2, flag3, flag, parcel.readString(), parcel.readString());
    }

    private SyncAdapterType(String s, String s1)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException((new StringBuilder()).append("the authority must not be empty: ").append(s).toString());
        if(TextUtils.isEmpty(s1))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("the accountType must not be empty: ").append(s1).toString());
        } else
        {
            authority = s;
            accountType = s1;
            userVisible = true;
            supportsUploading = true;
            isAlwaysSyncable = false;
            allowParallelSyncs = false;
            settingsActivity = null;
            isKey = true;
            packageName = null;
            return;
        }
    }

    public SyncAdapterType(String s, String s1, boolean flag, boolean flag1)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException((new StringBuilder()).append("the authority must not be empty: ").append(s).toString());
        if(TextUtils.isEmpty(s1))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("the accountType must not be empty: ").append(s1).toString());
        } else
        {
            authority = s;
            accountType = s1;
            userVisible = flag;
            supportsUploading = flag1;
            isAlwaysSyncable = false;
            allowParallelSyncs = false;
            settingsActivity = null;
            isKey = false;
            packageName = null;
            return;
        }
    }

    public SyncAdapterType(String s, String s1, boolean flag, boolean flag1, boolean flag2, boolean flag3, String s2, 
            String s3)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException((new StringBuilder()).append("the authority must not be empty: ").append(s).toString());
        if(TextUtils.isEmpty(s1))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("the accountType must not be empty: ").append(s1).toString());
        } else
        {
            authority = s;
            accountType = s1;
            userVisible = flag;
            supportsUploading = flag1;
            isAlwaysSyncable = flag2;
            allowParallelSyncs = flag3;
            settingsActivity = s2;
            isKey = false;
            packageName = s3;
            return;
        }
    }

    public static SyncAdapterType newKey(String s, String s1)
    {
        return new SyncAdapterType(s, s1);
    }

    public boolean allowParallelSyncs()
    {
        if(isKey)
            throw new IllegalStateException("this method is not allowed to be called when this is a key");
        else
            return allowParallelSyncs;
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
        if(!(obj instanceof SyncAdapterType))
            return false;
        obj = (SyncAdapterType)obj;
        if(authority.equals(((SyncAdapterType) (obj)).authority))
            flag = accountType.equals(((SyncAdapterType) (obj)).accountType);
        return flag;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public String getSettingsActivity()
    {
        if(isKey)
            throw new IllegalStateException("this method is not allowed to be called when this is a key");
        else
            return settingsActivity;
    }

    public int hashCode()
    {
        return (authority.hashCode() + 527) * 31 + accountType.hashCode();
    }

    public boolean isAlwaysSyncable()
    {
        if(isKey)
            throw new IllegalStateException("this method is not allowed to be called when this is a key");
        else
            return isAlwaysSyncable;
    }

    public boolean isUserVisible()
    {
        if(isKey)
            throw new IllegalStateException("this method is not allowed to be called when this is a key");
        else
            return userVisible;
    }

    public boolean supportsUploading()
    {
        if(isKey)
            throw new IllegalStateException("this method is not allowed to be called when this is a key");
        else
            return supportsUploading;
    }

    public String toString()
    {
        if(isKey)
            return (new StringBuilder()).append("SyncAdapterType Key {name=").append(authority).append(", type=").append(accountType).append("}").toString();
        else
            return (new StringBuilder()).append("SyncAdapterType {name=").append(authority).append(", type=").append(accountType).append(", userVisible=").append(userVisible).append(", supportsUploading=").append(supportsUploading).append(", isAlwaysSyncable=").append(isAlwaysSyncable).append(", allowParallelSyncs=").append(allowParallelSyncs).append(", settingsActivity=").append(settingsActivity).append(", packageName=").append(packageName).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        if(isKey)
            throw new IllegalStateException("keys aren't parcelable");
        parcel.writeString(authority);
        parcel.writeString(accountType);
        if(userVisible)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(supportsUploading)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(isAlwaysSyncable)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(allowParallelSyncs)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeString(settingsActivity);
        parcel.writeString(packageName);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SyncAdapterType createFromParcel(Parcel parcel)
        {
            return new SyncAdapterType(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SyncAdapterType[] newArray(int i)
        {
            return new SyncAdapterType[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final String accountType;
    private final boolean allowParallelSyncs;
    public final String authority;
    private final boolean isAlwaysSyncable;
    public final boolean isKey;
    private final String packageName;
    private final String settingsActivity;
    private final boolean supportsUploading;
    private final boolean userVisible;

}
