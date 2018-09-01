// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.ComponentName;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public final class AutomaticZenRule
    implements Parcelable
{

    public AutomaticZenRule(Parcel parcel)
    {
        boolean flag = false;
        super();
        enabled = false;
        if(parcel.readInt() == 1)
            flag = true;
        enabled = flag;
        if(parcel.readInt() == 1)
            name = parcel.readString();
        interruptionFilter = parcel.readInt();
        conditionId = (Uri)parcel.readParcelable(null);
        owner = (ComponentName)parcel.readParcelable(null);
        creationTime = parcel.readLong();
    }

    public AutomaticZenRule(String s, ComponentName componentname, Uri uri, int i, boolean flag)
    {
        enabled = false;
        name = s;
        owner = componentname;
        conditionId = uri;
        interruptionFilter = i;
        enabled = flag;
    }

    public AutomaticZenRule(String s, ComponentName componentname, Uri uri, int i, boolean flag, long l)
    {
        this(s, componentname, uri, i, flag);
        creationTime = l;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(!(obj instanceof AutomaticZenRule))
            return false;
        if(obj == this)
            return true;
        obj = (AutomaticZenRule)obj;
        if(((AutomaticZenRule) (obj)).enabled == enabled && Objects.equals(((AutomaticZenRule) (obj)).name, name) && ((AutomaticZenRule) (obj)).interruptionFilter == interruptionFilter && Objects.equals(((AutomaticZenRule) (obj)).conditionId, conditionId) && Objects.equals(((AutomaticZenRule) (obj)).owner, owner))
        {
            if(((AutomaticZenRule) (obj)).creationTime != creationTime)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public Uri getConditionId()
    {
        return conditionId;
    }

    public long getCreationTime()
    {
        return creationTime;
    }

    public int getInterruptionFilter()
    {
        return interruptionFilter;
    }

    public String getName()
    {
        return name;
    }

    public ComponentName getOwner()
    {
        return owner;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Boolean.valueOf(enabled), name, Integer.valueOf(interruptionFilter), conditionId, owner, Long.valueOf(creationTime)
        });
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setConditionId(Uri uri)
    {
        conditionId = uri;
    }

    public void setEnabled(boolean flag)
    {
        enabled = flag;
    }

    public void setInterruptionFilter(int i)
    {
        interruptionFilter = i;
    }

    public void setName(String s)
    {
        name = s;
    }

    public String toString()
    {
        return (new StringBuilder(android/app/AutomaticZenRule.getSimpleName())).append('[').append("enabled=").append(enabled).append(",name=").append(name).append(",interruptionFilter=").append(interruptionFilter).append(",conditionId=").append(conditionId).append(",owner=").append(owner).append(",creationTime=").append(creationTime).append(']').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(enabled)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(name != null)
        {
            parcel.writeInt(1);
            parcel.writeString(name);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(interruptionFilter);
        parcel.writeParcelable(conditionId, 0);
        parcel.writeParcelable(owner, 0);
        parcel.writeLong(creationTime);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AutomaticZenRule createFromParcel(Parcel parcel)
        {
            return new AutomaticZenRule(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AutomaticZenRule[] newArray(int i)
        {
            return new AutomaticZenRule[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private Uri conditionId;
    private long creationTime;
    private boolean enabled;
    private int interruptionFilter;
    private String name;
    private ComponentName owner;

}
