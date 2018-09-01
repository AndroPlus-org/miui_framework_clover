// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.location;

import android.os.Parcel;
import android.os.Parcelable;

public final class ProviderProperties
    implements Parcelable
{

    public ProviderProperties(boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4, boolean flag5, boolean flag6, 
            int i, int j)
    {
        mRequiresNetwork = flag;
        mRequiresSatellite = flag1;
        mRequiresCell = flag2;
        mHasMonetaryCost = flag3;
        mSupportsAltitude = flag4;
        mSupportsSpeed = flag5;
        mSupportsBearing = flag6;
        mPowerRequirement = i;
        mAccuracy = j;
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        if(mRequiresNetwork)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mRequiresSatellite)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mRequiresCell)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mHasMonetaryCost)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mSupportsAltitude)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mSupportsSpeed)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mSupportsBearing)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mPowerRequirement);
        parcel.writeInt(mAccuracy);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ProviderProperties createFromParcel(Parcel parcel)
        {
            boolean flag;
            boolean flag1;
            boolean flag2;
            boolean flag3;
            boolean flag4;
            boolean flag5;
            boolean flag6;
            if(parcel.readInt() == 1)
                flag = true;
            else
                flag = false;
            if(parcel.readInt() == 1)
                flag1 = true;
            else
                flag1 = false;
            if(parcel.readInt() == 1)
                flag2 = true;
            else
                flag2 = false;
            if(parcel.readInt() == 1)
                flag3 = true;
            else
                flag3 = false;
            if(parcel.readInt() == 1)
                flag4 = true;
            else
                flag4 = false;
            if(parcel.readInt() == 1)
                flag5 = true;
            else
                flag5 = false;
            if(parcel.readInt() == 1)
                flag6 = true;
            else
                flag6 = false;
            return new ProviderProperties(flag, flag1, flag2, flag3, flag4, flag5, flag6, parcel.readInt(), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ProviderProperties[] newArray(int i)
        {
            return new ProviderProperties[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final int mAccuracy;
    public final boolean mHasMonetaryCost;
    public final int mPowerRequirement;
    public final boolean mRequiresCell;
    public final boolean mRequiresNetwork;
    public final boolean mRequiresSatellite;
    public final boolean mSupportsAltitude;
    public final boolean mSupportsBearing;
    public final boolean mSupportsSpeed;

}
