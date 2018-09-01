// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.*;

// Referenced classes of package com.android.internal.telephony:
//            OperatorInfo

public class CellNetworkScanResult
    implements Parcelable
{

    public CellNetworkScanResult(int i, List list)
    {
        mStatus = i;
        mOperators = list;
    }

    private CellNetworkScanResult(Parcel parcel)
    {
        mStatus = parcel.readInt();
        int i = parcel.readInt();
        if(i > 0)
        {
            mOperators = new ArrayList();
            for(int j = 0; j < i; j++)
                mOperators.add((OperatorInfo)OperatorInfo.CREATOR.createFromParcel(parcel));

        } else
        {
            mOperators = null;
        }
    }

    CellNetworkScanResult(Parcel parcel, CellNetworkScanResult cellnetworkscanresult)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public List getOperators()
    {
        return mOperators;
    }

    public int getStatus()
    {
        return mStatus;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("CellNetworkScanResult: {");
        stringbuffer.append(" status:").append(mStatus);
        if(mOperators != null)
        {
            OperatorInfo operatorinfo;
            for(Iterator iterator = mOperators.iterator(); iterator.hasNext(); stringbuffer.append(" network:").append(operatorinfo))
                operatorinfo = (OperatorInfo)iterator.next();

        }
        stringbuffer.append("}");
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mStatus);
        if(mOperators != null && mOperators.size() > 0)
        {
            parcel.writeInt(mOperators.size());
            for(Iterator iterator = mOperators.iterator(); iterator.hasNext(); ((OperatorInfo)iterator.next()).writeToParcel(parcel, i));
        } else
        {
            parcel.writeInt(0);
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CellNetworkScanResult createFromParcel(Parcel parcel)
        {
            return new CellNetworkScanResult(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CellNetworkScanResult[] newArray(int i)
        {
            return new CellNetworkScanResult[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int STATUS_RADIO_GENERIC_FAILURE = 3;
    public static final int STATUS_RADIO_NOT_AVAILABLE = 2;
    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_UNKNOWN_ERROR = 4;
    private final List mOperators;
    private final int mStatus;

}
