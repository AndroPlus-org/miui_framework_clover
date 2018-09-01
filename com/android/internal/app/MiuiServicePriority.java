// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class MiuiServicePriority
    implements Parcelable, Serializable
{

    public MiuiServicePriority()
    {
    }

    private MiuiServicePriority(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    MiuiServicePriority(Parcel parcel, MiuiServicePriority miuiservicepriority)
    {
        this(parcel);
    }

    public MiuiServicePriority(String s, String s1, int i, boolean flag, boolean flag1, long l)
    {
        packageName = s;
        serviceName = s1;
        priority = i;
        checkPriority = flag;
        inBlacklist = flag1;
        delayTime = l;
    }

    public int describeContents()
    {
        return 0;
    }

    public void readFromParcel(Parcel parcel)
    {
        boolean flag = true;
        packageName = parcel.readString();
        serviceName = parcel.readString();
        priority = parcel.readInt();
        boolean flag1;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        checkPriority = flag1;
        if(parcel.readInt() == 1)
            flag1 = flag;
        else
            flag1 = false;
        inBlacklist = flag1;
        delayTime = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeString(packageName);
        parcel.writeString(serviceName);
        parcel.writeInt(priority);
        if(checkPriority)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(inBlacklist)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeLong(delayTime);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public MiuiServicePriority createFromParcel(Parcel parcel)
        {
            return new MiuiServicePriority(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public MiuiServicePriority[] newArray(int i)
        {
            return new MiuiServicePriority[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int HIGH_PRIORITY = 10;
    public static final int LOW_PRIORITY = -10;
    public static final int NORMAL_PRIORITY = 0;
    private static final long serialVersionUID = 1L;
    public boolean checkPriority;
    public long delayTime;
    public boolean inBlacklist;
    public String packageName;
    public int priority;
    public String serviceName;

}
