// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm.permission;

import android.os.Parcel;
import android.os.Parcelable;

public final class RuntimePermissionPresentationInfo
    implements Parcelable
{

    private RuntimePermissionPresentationInfo(Parcel parcel)
    {
        mLabel = parcel.readCharSequence();
        mFlags = parcel.readInt();
    }

    RuntimePermissionPresentationInfo(Parcel parcel, RuntimePermissionPresentationInfo runtimepermissionpresentationinfo)
    {
        this(parcel);
    }

    public RuntimePermissionPresentationInfo(CharSequence charsequence, boolean flag, boolean flag1)
    {
        mLabel = charsequence;
        int i = 0;
        if(flag)
            i = 1;
        int j = i;
        if(flag1)
            j = i | 2;
        mFlags = j;
    }

    public int describeContents()
    {
        return 0;
    }

    public CharSequence getLabel()
    {
        return mLabel;
    }

    public boolean isGranted()
    {
        boolean flag = false;
        if((mFlags & 1) != 0)
            flag = true;
        return flag;
    }

    public boolean isStandard()
    {
        boolean flag = false;
        if((mFlags & 2) != 0)
            flag = true;
        return flag;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeCharSequence(mLabel);
        parcel.writeInt(mFlags);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RuntimePermissionPresentationInfo createFromParcel(Parcel parcel)
        {
            return new RuntimePermissionPresentationInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RuntimePermissionPresentationInfo[] newArray(int i)
        {
            return new RuntimePermissionPresentationInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int FLAG_GRANTED = 1;
    private static final int FLAG_STANDARD = 2;
    private final int mFlags;
    private final CharSequence mLabel;

}
