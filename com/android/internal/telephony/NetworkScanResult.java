// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.*;

public final class NetworkScanResult
    implements Parcelable
{

    public NetworkScanResult(int i, int j, List list)
    {
        scanStatus = i;
        scanError = j;
        networkInfos = list;
    }

    private NetworkScanResult(Parcel parcel)
    {
        scanStatus = parcel.readInt();
        scanError = parcel.readInt();
        ArrayList arraylist = new ArrayList();
        parcel.readParcelableList(arraylist, java/lang/Object.getClassLoader());
        networkInfos = arraylist;
    }

    NetworkScanResult(Parcel parcel, NetworkScanResult networkscanresult)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        NetworkScanResult networkscanresult;
        try
        {
            networkscanresult = (NetworkScanResult)obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        if(obj == null)
            return false;
        boolean flag1 = flag;
        if(scanStatus == networkscanresult.scanStatus)
        {
            flag1 = flag;
            if(scanError == networkscanresult.scanError)
                flag1 = networkInfos.equals(networkscanresult.networkInfos);
        }
        return flag1;
    }

    public int hashCode()
    {
        return scanStatus * 31 + scanError * 23 + Objects.hashCode(networkInfos) * 37;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(scanStatus);
        parcel.writeInt(scanError);
        parcel.writeParcelableList(networkInfos, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NetworkScanResult createFromParcel(Parcel parcel)
        {
            return new NetworkScanResult(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NetworkScanResult[] newArray(int i)
        {
            return new NetworkScanResult[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int SCAN_STATUS_COMPLETE = 2;
    public static final int SCAN_STATUS_PARTIAL = 1;
    public List networkInfos;
    public int scanError;
    public int scanStatus;

}
