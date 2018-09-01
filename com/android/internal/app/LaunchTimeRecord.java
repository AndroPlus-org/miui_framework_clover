// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class LaunchTimeRecord
    implements Parcelable, Serializable
{

    public LaunchTimeRecord()
    {
        packageName = null;
        activity = null;
    }

    private LaunchTimeRecord(Parcel parcel)
    {
        packageName = null;
        activity = null;
        readFromParcel(parcel);
    }

    LaunchTimeRecord(Parcel parcel, LaunchTimeRecord launchtimerecord)
    {
        this(parcel);
    }

    public LaunchTimeRecord(String s, String s1, long l, long l1, boolean flag)
    {
        packageName = null;
        activity = null;
        packageName = s;
        activity = s1;
        launchStartTime = l;
        launchEndTime = l1;
        launchTime = l1 - l;
        isColdStart = flag;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getActivity()
    {
        return activity;
    }

    public long getLaunchEndTime()
    {
        return launchEndTime;
    }

    public long getLaunchStartTime()
    {
        return launchStartTime;
    }

    public long getLaunchTime()
    {
        return launchTime;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public int getType()
    {
        return type;
    }

    public boolean isColdStart()
    {
        return isColdStart;
    }

    public void readFromParcel(Parcel parcel)
    {
        boolean flag = true;
        activity = parcel.readString();
        launchStartTime = parcel.readLong();
        launchEndTime = parcel.readLong();
        launchTime = parcel.readLong();
        type = parcel.readInt();
        if(parcel.readInt() != 1)
            flag = false;
        isColdStart = flag;
    }

    public void setActivity(String s)
    {
        activity = s;
    }

    public void setIsColdStart(boolean flag)
    {
        isColdStart = flag;
    }

    public void setLaunchEndTime(long l)
    {
        launchEndTime = l;
    }

    public void setLaunchStartTime(long l)
    {
        launchStartTime = l;
    }

    public void setPackageName(String s)
    {
        packageName = s;
    }

    public void setType(int i)
    {
        type = i;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(activity);
        parcel.writeLong(launchStartTime);
        parcel.writeLong(launchEndTime);
        parcel.writeLong(launchTime);
        parcel.writeInt(type);
        if(isColdStart)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public LaunchTimeRecord createFromParcel(Parcel parcel)
        {
            return new LaunchTimeRecord(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public LaunchTimeRecord[] newArray(int i)
        {
            return new LaunchTimeRecord[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final long serialVersionUID = 1L;
    String activity;
    boolean isColdStart;
    long launchEndTime;
    long launchStartTime;
    long launchTime;
    String packageName;
    int type;

}
