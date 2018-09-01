// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.os.Parcel;
import android.os.Parcelable;

public class AppInfo
    implements Parcelable
{

    private AppInfo(Parcel parcel)
    {
        mScreenPackageName = parcel.readString();
        mScreenUid = parcel.readInt();
        mPrePackageName = parcel.readString();
        mPreUid = parcel.readInt();
        mTopMultiPackageName = parcel.readString();
        mTopMultiUid = parcel.readInt();
    }

    AppInfo(Parcel parcel, AppInfo appinfo)
    {
        this(parcel);
    }

    public AppInfo(String s, int i, String s1, int j, String s2, int k)
    {
        mScreenPackageName = s;
        mScreenUid = i;
        mPrePackageName = s1;
        mPreUid = j;
        mTopMultiPackageName = s2;
        mTopMultiUid = k;
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mScreenPackageName);
        parcel.writeInt(mScreenUid);
        parcel.writeString(mPrePackageName);
        parcel.writeInt(mPreUid);
        parcel.writeString(mTopMultiPackageName);
        parcel.writeInt(mTopMultiUid);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AppInfo createFromParcel(Parcel parcel)
        {
            return new AppInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AppInfo[] newArray(int i)
        {
            return new AppInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public String mPrePackageName;
    public int mPreUid;
    public String mScreenPackageName;
    public int mScreenUid;
    public String mTopMultiPackageName;
    public int mTopMultiUid;

}
