// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class WhetstoneConfig
    implements Parcelable
{

    private WhetstoneConfig(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    WhetstoneConfig(Parcel parcel, WhetstoneConfig whetstoneconfig)
    {
        this(parcel);
    }

    public WhetstoneConfig(List list, int i)
    {
        mWhiteList = list;
        mType = i;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getDeepCleanType()
    {
        return mType;
    }

    public List getWhiteList()
    {
        return mWhiteList;
    }

    public void readFromParcel(Parcel parcel)
    {
        mWhiteList = parcel.readArrayList(java/util/List.getClassLoader());
        mType = parcel.readInt();
    }

    public void setWhiteList(List list)
    {
        mWhiteList = list;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mWhiteList == null)
            mWhiteList = new ArrayList();
        parcel.writeList(mWhiteList);
        parcel.writeInt(mType);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WhetstoneConfig createFromParcel(Parcel parcel)
        {
            return new WhetstoneConfig(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WhetstoneConfig[] newArray(int i)
        {
            return new WhetstoneConfig[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DEEP_CLEAN_TYPE_GAME_CLEAN = 10;
    public static final int DEEP_CLEAN_TYPE_LOCK_SCREEN = 4;
    public static final int DEEP_CLEAN_TYPE_NORMAL = 0;
    public static final int DEEP_CLEAN_TYPE_ONE_KEY = 2;
    public static final int DEEP_CLEAN_TYPE_STRONG_CLEAN = 8;
    public static final int DEEP_CLEAN_TYPE_UNKNOW = -1;
    int mType;
    List mWhiteList;

}
