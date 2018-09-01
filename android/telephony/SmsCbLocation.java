// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import miui.telephony.PhoneDebug;

public class SmsCbLocation
    implements Parcelable
{

    public SmsCbLocation()
    {
        mPlmn = "";
        mLac = -1;
        mCid = -1;
    }

    public SmsCbLocation(Parcel parcel)
    {
        mPlmn = parcel.readString();
        mLac = parcel.readInt();
        mCid = parcel.readInt();
    }

    public SmsCbLocation(String s)
    {
        mPlmn = s;
        mLac = -1;
        mCid = -1;
    }

    public SmsCbLocation(String s, int i, int j)
    {
        mPlmn = s;
        mLac = i;
        mCid = j;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(obj == this)
            return true;
        if(obj == null || (obj instanceof SmsCbLocation) ^ true)
            return false;
        obj = (SmsCbLocation)obj;
        if(!mPlmn.equals(((SmsCbLocation) (obj)).mPlmn) || mLac != ((SmsCbLocation) (obj)).mLac || mCid != ((SmsCbLocation) (obj)).mCid)
            flag = false;
        return flag;
    }

    public int getCid()
    {
        return mCid;
    }

    public int getLac()
    {
        return mLac;
    }

    public String getPlmn()
    {
        return mPlmn;
    }

    public int hashCode()
    {
        return (mPlmn.hashCode() * 31 + mLac) * 31 + mCid;
    }

    public boolean isInLocationArea(SmsCbLocation smscblocation)
    {
        if(mCid != -1 && mCid != smscblocation.mCid)
            return false;
        if(mLac != -1 && mLac != smscblocation.mLac)
            return false;
        else
            return mPlmn.equals(smscblocation.mPlmn);
    }

    public boolean isInLocationArea(String s, int i, int j)
    {
        if(!mPlmn.equals(s))
            return false;
        if(mLac != -1 && mLac != i)
            return false;
        return mCid == -1 || mCid == j;
    }

    public String toString()
    {
        if(!PhoneDebug.VDBG)
            return (new StringBuilder()).append('[').append(mPlmn).append(']').toString();
        else
            return (new StringBuilder()).append('[').append(mPlmn).append(',').append(mLac).append(',').append(mCid).append(']').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mPlmn);
        parcel.writeInt(mLac);
        parcel.writeInt(mCid);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SmsCbLocation createFromParcel(Parcel parcel)
        {
            return new SmsCbLocation(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SmsCbLocation[] newArray(int i)
        {
            return new SmsCbLocation[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final int mCid;
    private final int mLac;
    private final String mPlmn;

}
