// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.hdmi;

import android.os.Parcel;
import android.os.Parcelable;

public final class HdmiPortInfo
    implements Parcelable
{

    public HdmiPortInfo(int i, int j, int k, boolean flag, boolean flag1, boolean flag2)
    {
        mId = i;
        mType = j;
        mAddress = k;
        mCecSupported = flag;
        mArcSupported = flag2;
        mMhlSupported = flag1;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof HdmiPortInfo))
            return false;
        obj = (HdmiPortInfo)obj;
        boolean flag1 = flag;
        if(mId == ((HdmiPortInfo) (obj)).mId)
        {
            flag1 = flag;
            if(mType == ((HdmiPortInfo) (obj)).mType)
            {
                flag1 = flag;
                if(mAddress == ((HdmiPortInfo) (obj)).mAddress)
                {
                    flag1 = flag;
                    if(mCecSupported == ((HdmiPortInfo) (obj)).mCecSupported)
                    {
                        flag1 = flag;
                        if(mArcSupported == ((HdmiPortInfo) (obj)).mArcSupported)
                        {
                            flag1 = flag;
                            if(mMhlSupported == ((HdmiPortInfo) (obj)).mMhlSupported)
                                flag1 = true;
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public int getAddress()
    {
        return mAddress;
    }

    public int getId()
    {
        return mId;
    }

    public int getType()
    {
        return mType;
    }

    public boolean isArcSupported()
    {
        return mArcSupported;
    }

    public boolean isCecSupported()
    {
        return mCecSupported;
    }

    public boolean isMhlSupported()
    {
        return mMhlSupported;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("port_id: ").append(mId).append(", ");
        stringbuffer.append("address: ").append(String.format("0x%04x", new Object[] {
            Integer.valueOf(mAddress)
        })).append(", ");
        stringbuffer.append("cec: ").append(mCecSupported).append(", ");
        stringbuffer.append("arc: ").append(mArcSupported).append(", ");
        stringbuffer.append("mhl: ").append(mMhlSupported);
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(mId);
        parcel.writeInt(mType);
        parcel.writeInt(mAddress);
        if(mCecSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mArcSupported)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mMhlSupported)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public HdmiPortInfo createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            int j = parcel.readInt();
            int k = parcel.readInt();
            boolean flag;
            boolean flag1;
            boolean flag2;
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
            return new HdmiPortInfo(i, j, k, flag, flag2, flag1);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public HdmiPortInfo[] newArray(int i)
        {
            return new HdmiPortInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int PORT_INPUT = 0;
    public static final int PORT_OUTPUT = 1;
    private final int mAddress;
    private final boolean mArcSupported;
    private final boolean mCecSupported;
    private final int mId;
    private final boolean mMhlSupported;
    private final int mType;

}
