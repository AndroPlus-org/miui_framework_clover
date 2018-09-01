// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

// Referenced classes of package android.telephony:
//            RadioAccessSpecifier

public final class NetworkScanRequest
    implements Parcelable
{

    public NetworkScanRequest(int i, RadioAccessSpecifier aradioaccessspecifier[])
    {
        scanType = i;
        specifiers = aradioaccessspecifier;
    }

    private NetworkScanRequest(Parcel parcel)
    {
        scanType = parcel.readInt();
        specifiers = (RadioAccessSpecifier[])parcel.readParcelableArray(java/lang/Object.getClassLoader(), android/telephony/RadioAccessSpecifier);
    }

    NetworkScanRequest(Parcel parcel, NetworkScanRequest networkscanrequest)
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
        NetworkScanRequest networkscanrequest;
        try
        {
            networkscanrequest = (NetworkScanRequest)obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        if(obj == null)
            return false;
        if(scanType == networkscanrequest.scanType)
            flag = Arrays.equals(specifiers, networkscanrequest.specifiers);
        return flag;
    }

    public int hashCode()
    {
        return scanType * 31 + Arrays.hashCode(specifiers) * 37;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(scanType);
        parcel.writeParcelableArray(specifiers, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NetworkScanRequest createFromParcel(Parcel parcel)
        {
            return new NetworkScanRequest(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NetworkScanRequest[] newArray(int i)
        {
            return new NetworkScanRequest[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int MAX_BANDS = 8;
    public static final int MAX_CHANNELS = 32;
    public static final int MAX_RADIO_ACCESS_NETWORKS = 8;
    public static final int SCAN_TYPE_ONE_SHOT = 0;
    public static final int SCAN_TYPE_PERIODIC = 1;
    public int scanType;
    public RadioAccessSpecifier specifiers[];

}
