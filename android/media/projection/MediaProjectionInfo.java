// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.projection;

import android.os.*;
import java.util.Objects;

public final class MediaProjectionInfo
    implements Parcelable
{

    public MediaProjectionInfo(Parcel parcel)
    {
        mPackageName = parcel.readString();
        mUserHandle = UserHandle.readFromParcel(parcel);
    }

    public MediaProjectionInfo(String s, UserHandle userhandle)
    {
        mPackageName = s;
        mUserHandle = userhandle;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof MediaProjectionInfo)
        {
            obj = (MediaProjectionInfo)obj;
            if(Objects.equals(((MediaProjectionInfo) (obj)).mPackageName, mPackageName))
                flag = Objects.equals(((MediaProjectionInfo) (obj)).mUserHandle, mUserHandle);
            return flag;
        } else
        {
            return false;
        }
    }

    public String getPackageName()
    {
        return mPackageName;
    }

    public UserHandle getUserHandle()
    {
        return mUserHandle;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mPackageName, mUserHandle
        });
    }

    public String toString()
    {
        return (new StringBuilder()).append("MediaProjectionInfo{mPackageName=").append(mPackageName).append(", mUserHandle=").append(mUserHandle).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mPackageName);
        UserHandle.writeToParcel(mUserHandle, parcel);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public MediaProjectionInfo createFromParcel(Parcel parcel)
        {
            return new MediaProjectionInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public MediaProjectionInfo[] newArray(int i)
        {
            return new MediaProjectionInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final String mPackageName;
    private final UserHandle mUserHandle;

}
