// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.om;

import android.os.Parcel;
import android.os.Parcelable;

public final class OverlayInfo
    implements Parcelable
{

    public OverlayInfo(OverlayInfo overlayinfo, int i)
    {
        this(overlayinfo.packageName, overlayinfo.targetPackageName, overlayinfo.baseCodePath, i, overlayinfo.userId);
    }

    public OverlayInfo(Parcel parcel)
    {
        packageName = parcel.readString();
        targetPackageName = parcel.readString();
        baseCodePath = parcel.readString();
        state = parcel.readInt();
        userId = parcel.readInt();
        ensureValidState();
    }

    public OverlayInfo(String s, String s1, String s2, int i, int j)
    {
        packageName = s;
        targetPackageName = s1;
        baseCodePath = s2;
        state = i;
        userId = j;
        ensureValidState();
    }

    private void ensureValidState()
    {
        if(packageName == null)
            throw new IllegalArgumentException("packageName must not be null");
        if(targetPackageName == null)
            throw new IllegalArgumentException("targetPackageName must not be null");
        if(baseCodePath == null)
            throw new IllegalArgumentException("baseCodePath must not be null");
        switch(state)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("State ").append(state).append(" is not a valid state").toString());

        case -1: 
        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            return;
        }
    }

    public static String stateToString(int i)
    {
        switch(i)
        {
        default:
            return "<unknown state>";

        case -1: 
            return "STATE_UNKNOWN";

        case 0: // '\0'
            return "STATE_MISSING_TARGET";

        case 1: // '\001'
            return "STATE_NO_IDMAP";

        case 2: // '\002'
            return "STATE_DISABLED";

        case 3: // '\003'
            return "STATE_ENABLED";
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        obj = (OverlayInfo)obj;
        if(userId != ((OverlayInfo) (obj)).userId)
            return false;
        if(state != ((OverlayInfo) (obj)).state)
            return false;
        if(!packageName.equals(((OverlayInfo) (obj)).packageName))
            return false;
        if(!targetPackageName.equals(((OverlayInfo) (obj)).targetPackageName))
            return false;
        return baseCodePath.equals(((OverlayInfo) (obj)).baseCodePath);
    }

    public int hashCode()
    {
        int i = 0;
        int j = userId;
        int k = state;
        int l;
        int i1;
        if(packageName == null)
            l = 0;
        else
            l = packageName.hashCode();
        if(targetPackageName == null)
            i1 = 0;
        else
            i1 = targetPackageName.hashCode();
        if(baseCodePath != null)
            i = baseCodePath.hashCode();
        return ((((j + 31) * 31 + k) * 31 + l) * 31 + i1) * 31 + i;
    }

    public boolean isEnabled()
    {
        switch(state)
        {
        default:
            return false;

        case 3: // '\003'
            return true;
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append("OverlayInfo { overlay=").append(packageName).append(", target=").append(targetPackageName).append(", state=").append(state).append(" (").append(stateToString(state)).append("), userId=").append(userId).append(" }").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(packageName);
        parcel.writeString(targetPackageName);
        parcel.writeString(baseCodePath);
        parcel.writeInt(state);
        parcel.writeInt(userId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public OverlayInfo createFromParcel(Parcel parcel)
        {
            return new OverlayInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public OverlayInfo[] newArray(int i)
        {
            return new OverlayInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int STATE_DISABLED = 2;
    public static final int STATE_ENABLED = 3;
    public static final int STATE_MISSING_TARGET = 0;
    public static final int STATE_NO_IDMAP = 1;
    public static final int STATE_UNKNOWN = -1;
    public final String baseCodePath;
    public final String packageName;
    public final int state;
    public final String targetPackageName;
    public final int userId;

}
