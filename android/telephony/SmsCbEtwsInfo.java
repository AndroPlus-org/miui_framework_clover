// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.Time;
import com.android.internal.telephony.uicc.IccUtils;
import java.util.Arrays;

public class SmsCbEtwsInfo
    implements Parcelable
{

    public SmsCbEtwsInfo(int i, boolean flag, boolean flag1, boolean flag2, byte abyte0[])
    {
        mWarningType = i;
        mEmergencyUserAlert = flag;
        mActivatePopup = flag1;
        mPrimary = flag2;
        mWarningSecurityInformation = abyte0;
    }

    SmsCbEtwsInfo(Parcel parcel)
    {
        boolean flag = true;
        super();
        mWarningType = parcel.readInt();
        boolean flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mEmergencyUserAlert = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mActivatePopup = flag1;
        if(parcel.readInt() != 0)
            flag1 = flag;
        else
            flag1 = false;
        mPrimary = flag1;
        mWarningSecurityInformation = parcel.createByteArray();
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
        if(obj == null || (obj instanceof SmsCbEtwsInfo) ^ true)
            return false;
        obj = (SmsCbEtwsInfo)obj;
        if(mWarningType == ((SmsCbEtwsInfo) (obj)).mWarningType && mEmergencyUserAlert == ((SmsCbEtwsInfo) (obj)).mEmergencyUserAlert)
        {
            if(mActivatePopup != ((SmsCbEtwsInfo) (obj)).mActivatePopup)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public byte[] getPrimaryNotificationSignature()
    {
        if(mWarningSecurityInformation == null || mWarningSecurityInformation.length < 50)
            return null;
        else
            return Arrays.copyOfRange(mWarningSecurityInformation, 7, 50);
    }

    public long getPrimaryNotificationTimestamp()
    {
        if(mWarningSecurityInformation == null || mWarningSecurityInformation.length < 7)
            return 0L;
        int i = IccUtils.gsmBcdByteToInt(mWarningSecurityInformation[0]);
        int j = IccUtils.gsmBcdByteToInt(mWarningSecurityInformation[1]);
        int k = IccUtils.gsmBcdByteToInt(mWarningSecurityInformation[2]);
        int l = IccUtils.gsmBcdByteToInt(mWarningSecurityInformation[3]);
        int i1 = IccUtils.gsmBcdByteToInt(mWarningSecurityInformation[4]);
        int j1 = IccUtils.gsmBcdByteToInt(mWarningSecurityInformation[5]);
        byte byte0 = mWarningSecurityInformation[6];
        int k1 = IccUtils.gsmBcdByteToInt((byte)(byte0 & -9));
        Time time;
        if((byte0 & 8) != 0)
            k1 = -k1;
        time = new Time("UTC");
        time.year = i + 2000;
        time.month = j - 1;
        time.monthDay = k;
        time.hour = l;
        time.minute = i1;
        time.second = j1;
        return time.toMillis(true) - (long)(k1 * 15 * 60 * 1000);
    }

    public int getWarningType()
    {
        return mWarningType;
    }

    public int hashCode()
    {
        int i = mWarningType * 4;
        int j;
        if(mEmergencyUserAlert)
            j = i * 8 + 7;
        else
            j = i * 6 + 5;
        i += j;
        if(mActivatePopup)
            j = i * 12 + 15;
        else
            j = i * 10 + 13;
        return i + j;
    }

    public boolean isEmergencyUserAlert()
    {
        return mEmergencyUserAlert;
    }

    public boolean isPopupAlert()
    {
        return mActivatePopup;
    }

    public boolean isPrimary()
    {
        return mPrimary;
    }

    public String toString()
    {
        return (new StringBuilder()).append("SmsCbEtwsInfo{warningType=").append(mWarningType).append(", emergencyUserAlert=").append(mEmergencyUserAlert).append(", activatePopup=").append(mActivatePopup).append('}').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(mWarningType);
        if(mEmergencyUserAlert)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mActivatePopup)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mPrimary)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeByteArray(mWarningSecurityInformation);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SmsCbEtwsInfo createFromParcel(Parcel parcel)
        {
            return new SmsCbEtwsInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SmsCbEtwsInfo[] newArray(int i)
        {
            return new SmsCbEtwsInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int ETWS_WARNING_TYPE_EARTHQUAKE = 0;
    public static final int ETWS_WARNING_TYPE_EARTHQUAKE_AND_TSUNAMI = 2;
    public static final int ETWS_WARNING_TYPE_OTHER_EMERGENCY = 4;
    public static final int ETWS_WARNING_TYPE_TEST_MESSAGE = 3;
    public static final int ETWS_WARNING_TYPE_TSUNAMI = 1;
    public static final int ETWS_WARNING_TYPE_UNKNOWN = -1;
    private final boolean mActivatePopup;
    private final boolean mEmergencyUserAlert;
    private final boolean mPrimary;
    private final byte mWarningSecurityInformation[];
    private final int mWarningType;

}
