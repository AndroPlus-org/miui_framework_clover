// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.impl;

import android.os.Parcel;
import android.os.Parcelable;

public class CaptureResultExtras
    implements Parcelable
{

    public CaptureResultExtras(int i, int j, int k, int l, long l1, int i1, 
            int j1)
    {
        requestId = i;
        subsequenceId = j;
        afTriggerId = k;
        precaptureTriggerId = l;
        frameNumber = l1;
        partialResultCount = i1;
        errorStreamId = j1;
    }

    private CaptureResultExtras(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    CaptureResultExtras(Parcel parcel, CaptureResultExtras captureresultextras)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public int getAfTriggerId()
    {
        return afTriggerId;
    }

    public int getErrorStreamId()
    {
        return errorStreamId;
    }

    public long getFrameNumber()
    {
        return frameNumber;
    }

    public int getPartialResultCount()
    {
        return partialResultCount;
    }

    public int getPrecaptureTriggerId()
    {
        return precaptureTriggerId;
    }

    public int getRequestId()
    {
        return requestId;
    }

    public int getSubsequenceId()
    {
        return subsequenceId;
    }

    public void readFromParcel(Parcel parcel)
    {
        requestId = parcel.readInt();
        subsequenceId = parcel.readInt();
        afTriggerId = parcel.readInt();
        precaptureTriggerId = parcel.readInt();
        frameNumber = parcel.readLong();
        partialResultCount = parcel.readInt();
        errorStreamId = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(requestId);
        parcel.writeInt(subsequenceId);
        parcel.writeInt(afTriggerId);
        parcel.writeInt(precaptureTriggerId);
        parcel.writeLong(frameNumber);
        parcel.writeInt(partialResultCount);
        parcel.writeInt(errorStreamId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CaptureResultExtras createFromParcel(Parcel parcel)
        {
            return new CaptureResultExtras(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CaptureResultExtras[] newArray(int i)
        {
            return new CaptureResultExtras[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private int afTriggerId;
    private int errorStreamId;
    private long frameNumber;
    private int partialResultCount;
    private int precaptureTriggerId;
    private int requestId;
    private int subsequenceId;

}
