// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package com.android.ims:
//            ImsCallForwardInfo, ImsSsInfo

public class ImsSsData
    implements Parcelable
{

    public ImsSsData()
    {
    }

    public ImsSsData(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    private void readFromParcel(Parcel parcel)
    {
        mServiceType = parcel.readInt();
        mRequestType = parcel.readInt();
        mTeleserviceType = parcel.readInt();
        mServiceClass = parcel.readInt();
        mResult = parcel.readInt();
        mSsInfo = parcel.createIntArray();
        mCfInfo = (ImsCallForwardInfo[])parcel.readParcelableArray(getClass().getClassLoader());
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean isTypeBarring()
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(mServiceType == 13) goto _L2; else goto _L1
_L1:
        if(mServiceType != 14) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(mServiceType != 15)
        {
            flag1 = flag;
            if(mServiceType != 16)
            {
                flag1 = flag;
                if(mServiceType != 17)
                {
                    flag1 = flag;
                    if(mServiceType != 18)
                    {
                        flag1 = flag;
                        if(mServiceType != 19)
                        {
                            flag1 = flag;
                            if(mServiceType != 20)
                                flag1 = false;
                        }
                    }
                }
            }
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public boolean isTypeCF()
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(mServiceType == 0) goto _L2; else goto _L1
_L1:
        if(mServiceType != 1) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(mServiceType != 2)
        {
            flag1 = flag;
            if(mServiceType != 3)
            {
                flag1 = flag;
                if(mServiceType != 4)
                {
                    flag1 = flag;
                    if(mServiceType != 5)
                        flag1 = false;
                }
            }
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public boolean isTypeCW()
    {
        boolean flag;
        if(mServiceType == 12)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isTypeClip()
    {
        boolean flag;
        if(mServiceType == 7)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isTypeClir()
    {
        boolean flag;
        if(mServiceType == 8)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isTypeColp()
    {
        boolean flag;
        if(mServiceType == 9)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isTypeColr()
    {
        boolean flag;
        if(mServiceType == 10)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isTypeIcb()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(mServiceType != 21)
            if(mServiceType == 22)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public boolean isTypeInterrogation()
    {
        boolean flag;
        if(mRequestType == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isTypeUnConditional()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(mServiceType != 0)
            if(mServiceType == 4)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public String toString()
    {
        return (new StringBuilder()).append("[ImsSsData] ServiceType: ").append(mServiceType).append(" RequestType: ").append(mRequestType).append(" TeleserviceType: ").append(mTeleserviceType).append(" ServiceClass: ").append(mServiceClass).append(" Result: ").append(mResult).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mServiceType);
        parcel.writeInt(mRequestType);
        parcel.writeInt(mTeleserviceType);
        parcel.writeInt(mServiceClass);
        parcel.writeInt(mResult);
        parcel.writeIntArray(mSsInfo);
        parcel.writeParcelableArray(mCfInfo, 0);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ImsSsData createFromParcel(Parcel parcel)
        {
            return new ImsSsData(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ImsSsData[] newArray(int i)
        {
            return new ImsSsData[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int SS_ACTIVATION = 0;
    public static final int SS_ALL_BARRING = 18;
    public static final int SS_ALL_DATA_TELESERVICES = 3;
    public static final int SS_ALL_TELESERVICES_EXCEPT_SMS = 5;
    public static final int SS_ALL_TELESEVICES = 1;
    public static final int SS_ALL_TELE_AND_BEARER_SERVICES = 0;
    public static final int SS_BAIC = 16;
    public static final int SS_BAIC_ROAMING = 17;
    public static final int SS_BAOC = 13;
    public static final int SS_BAOIC = 14;
    public static final int SS_BAOIC_EXC_HOME = 15;
    public static final int SS_CFU = 0;
    public static final int SS_CFUT = 6;
    public static final int SS_CF_ALL = 4;
    public static final int SS_CF_ALL_CONDITIONAL = 5;
    public static final int SS_CF_BUSY = 1;
    public static final int SS_CF_NOT_REACHABLE = 3;
    public static final int SS_CF_NO_REPLY = 2;
    public static final int SS_CLIP = 7;
    public static final int SS_CLIR = 8;
    public static final int SS_CNAP = 11;
    public static final int SS_COLP = 9;
    public static final int SS_COLR = 10;
    public static final int SS_DEACTIVATION = 1;
    public static final int SS_ERASURE = 4;
    public static final int SS_INCOMING_BARRING = 20;
    public static final int SS_INCOMING_BARRING_ANONYMOUS = 22;
    public static final int SS_INCOMING_BARRING_DN = 21;
    public static final int SS_INTERROGATION = 2;
    public static final int SS_OUTGOING_BARRING = 19;
    public static final int SS_REGISTRATION = 3;
    public static final int SS_SMS_SERVICES = 4;
    public static final int SS_TELEPHONY = 2;
    public static final int SS_WAIT = 12;
    public ImsCallForwardInfo mCfInfo[];
    public ImsSsInfo mImsSsInfo[];
    public int mRequestType;
    public int mResult;
    public int mServiceClass;
    public int mServiceType;
    public int mSsInfo[];
    public int mTeleserviceType;

}
