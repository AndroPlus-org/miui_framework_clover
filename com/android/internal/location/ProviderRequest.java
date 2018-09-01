// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.location;

import android.location.LocationRequest;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.TimeUtils;
import java.util.*;

public final class ProviderRequest
    implements Parcelable
{

    public ProviderRequest()
    {
        reportLocation = false;
        interval = 0x7fffffffffffffffL;
        locationRequests = new ArrayList();
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("ProviderRequest[");
        if(reportLocation)
        {
            stringbuilder.append("ON");
            stringbuilder.append(" interval=");
            TimeUtils.formatDuration(interval, stringbuilder);
        } else
        {
            stringbuilder.append("OFF");
        }
        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        int j;
        if(reportLocation)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeLong(interval);
        parcel.writeInt(locationRequests.size());
        for(Iterator iterator = locationRequests.iterator(); iterator.hasNext(); ((LocationRequest)iterator.next()).writeToParcel(parcel, i));
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ProviderRequest createFromParcel(Parcel parcel)
        {
            boolean flag = true;
            ProviderRequest providerrequest = new ProviderRequest();
            int i;
            if(parcel.readInt() != 1)
                flag = false;
            providerrequest.reportLocation = flag;
            providerrequest.interval = parcel.readLong();
            i = parcel.readInt();
            for(int j = 0; j < i; j++)
                providerrequest.locationRequests.add((LocationRequest)LocationRequest.CREATOR.createFromParcel(parcel));

            return providerrequest;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ProviderRequest[] newArray(int i)
        {
            return new ProviderRequest[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public long interval;
    public List locationRequests;
    public boolean reportLocation;

}
