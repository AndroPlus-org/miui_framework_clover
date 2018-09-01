// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.content.ComponentName;
import android.os.*;
import android.util.Log;
import java.util.Objects;

// Referenced classes of package android.telecom:
//            Log

public final class PhoneAccountHandle
    implements Parcelable
{

    public PhoneAccountHandle(ComponentName componentname, String s)
    {
        this(componentname, s, Process.myUserHandle());
    }

    public PhoneAccountHandle(ComponentName componentname, String s, UserHandle userhandle)
    {
        checkParameters(componentname, userhandle);
        mComponentName = componentname;
        mId = s;
        mUserHandle = userhandle;
    }

    private PhoneAccountHandle(Parcel parcel)
    {
        this((ComponentName)ComponentName.CREATOR.createFromParcel(parcel), parcel.readString(), (UserHandle)UserHandle.CREATOR.createFromParcel(parcel));
    }

    PhoneAccountHandle(Parcel parcel, PhoneAccountHandle phoneaccounthandle)
    {
        this(parcel);
    }

    private void checkParameters(ComponentName componentname, UserHandle userhandle)
    {
        if(componentname == null)
            Log.w("PhoneAccountHandle", new Exception("PhoneAccountHandle has been created with null ComponentName!"));
        if(userhandle == null)
            Log.w("PhoneAccountHandle", new Exception("PhoneAccountHandle has been created with null UserHandle!"));
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(obj != null && (obj instanceof PhoneAccountHandle) && Objects.equals(((PhoneAccountHandle)obj).getComponentName(), getComponentName()) && Objects.equals(((PhoneAccountHandle)obj).getId(), getId()))
            flag = Objects.equals(((PhoneAccountHandle)obj).getUserHandle(), getUserHandle());
        else
            flag = false;
        return flag;
    }

    public ComponentName getComponentName()
    {
        return mComponentName;
    }

    public String getId()
    {
        return mId;
    }

    public UserHandle getUserHandle()
    {
        return mUserHandle;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mComponentName, mId, mUserHandle
        });
    }

    public String toString()
    {
        return (new StringBuilder()).append(mComponentName).append(", ").append(Log.pii(mId)).append(", ").append(mUserHandle).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        mComponentName.writeToParcel(parcel, i);
        parcel.writeString(mId);
        mUserHandle.writeToParcel(parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PhoneAccountHandle createFromParcel(Parcel parcel)
        {
            return new PhoneAccountHandle(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PhoneAccountHandle[] newArray(int i)
        {
            return new PhoneAccountHandle[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final ComponentName mComponentName;
    private final String mId;
    private final UserHandle mUserHandle;

}
