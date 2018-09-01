// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import miui.telephony.PhoneDebug;

public class NeighboringCellInfo
    implements Parcelable
{

    public NeighboringCellInfo()
    {
        mRssi = 99;
        mLac = -1;
        mCid = -1;
        mPsc = -1;
        mNetworkType = 0;
    }

    public NeighboringCellInfo(int i, int j)
    {
        mRssi = i;
        mCid = j;
    }

    public NeighboringCellInfo(int i, String s, int j)
    {
        String s1;
        mRssi = i;
        mNetworkType = 0;
        mPsc = -1;
        mLac = -1;
        mCid = -1;
        int k = s.length();
        if(k > 8)
            return;
        s1 = s;
        if(k < 8)
        {
            i = 0;
            do
            {
                s1 = s;
                if(i >= 8 - k)
                    break;
                s = (new StringBuilder()).append("0").append(s).toString();
                i++;
            } while(true);
        }
        j;
        JVM INSTR tableswitch 1 10: default 148
    //                   1 149
    //                   2 149
    //                   3 222
    //                   4 148
    //                   5 148
    //                   6 148
    //                   7 148
    //                   8 222
    //                   9 222
    //                   10 222;
           goto _L1 _L2 _L2 _L3 _L1 _L1 _L1 _L1 _L3 _L3 _L3
_L1:
        return;
_L2:
        mNetworkType = j;
        if(!s1.equalsIgnoreCase("FFFFFFFF"))
        {
            mCid = Integer.parseInt(s1.substring(4), 16);
            mLac = Integer.parseInt(s1.substring(0, 4), 16);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        try
        {
            mNetworkType = j;
            mPsc = Integer.parseInt(s1, 16);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            mPsc = -1;
            mLac = -1;
            mCid = -1;
            mNetworkType = 0;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    public NeighboringCellInfo(Parcel parcel)
    {
        mRssi = parcel.readInt();
        mLac = parcel.readInt();
        mCid = parcel.readInt();
        mPsc = parcel.readInt();
        mNetworkType = parcel.readInt();
    }

    public int describeContents()
    {
        return 0;
    }

    public int getCid()
    {
        return mCid;
    }

    public int getLac()
    {
        return mLac;
    }

    public int getNetworkType()
    {
        return mNetworkType;
    }

    public int getPsc()
    {
        return mPsc;
    }

    public int getRssi()
    {
        return mRssi;
    }

    public void setCid(int i)
    {
        mCid = i;
    }

    public void setRssi(int i)
    {
        mRssi = i;
    }

    public String toString()
    {
        StringBuilder stringbuilder;
        if(!PhoneDebug.VDBG)
            return "NeighboringCellInfo:[...]";
        stringbuilder = new StringBuilder();
        stringbuilder.append("[");
        if(mPsc == -1) goto _L2; else goto _L1
_L1:
        StringBuilder stringbuilder1 = stringbuilder.append(Integer.toHexString(mPsc)).append("@");
        Object obj;
        if(mRssi == 99)
            obj = "-";
        else
            obj = Integer.valueOf(mRssi);
        stringbuilder1.append(obj);
_L4:
        stringbuilder.append("]");
        return stringbuilder.toString();
_L2:
        if(mLac != -1 && mCid != -1)
        {
            StringBuilder stringbuilder2 = stringbuilder.append(Integer.toHexString(mLac)).append(Integer.toHexString(mCid)).append("@");
            Object obj1;
            if(mRssi == 99)
                obj1 = "-";
            else
                obj1 = Integer.valueOf(mRssi);
            stringbuilder2.append(obj1);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mRssi);
        parcel.writeInt(mLac);
        parcel.writeInt(mCid);
        parcel.writeInt(mPsc);
        parcel.writeInt(mNetworkType);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NeighboringCellInfo createFromParcel(Parcel parcel)
        {
            return new NeighboringCellInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NeighboringCellInfo[] newArray(int i)
        {
            return new NeighboringCellInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int UNKNOWN_CID = -1;
    public static final int UNKNOWN_RSSI = 99;
    private int mCid;
    private int mLac;
    private int mNetworkType;
    private int mPsc;
    private int mRssi;

}
