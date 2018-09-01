// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.Parcel;
import android.os.Parcelable;

public class Criteria
    implements Parcelable
{

    static boolean _2D_set0(Criteria criteria, boolean flag)
    {
        criteria.mAltitudeRequired = flag;
        return flag;
    }

    static int _2D_set1(Criteria criteria, int i)
    {
        criteria.mBearingAccuracy = i;
        return i;
    }

    static boolean _2D_set2(Criteria criteria, boolean flag)
    {
        criteria.mBearingRequired = flag;
        return flag;
    }

    static boolean _2D_set3(Criteria criteria, boolean flag)
    {
        criteria.mCostAllowed = flag;
        return flag;
    }

    static int _2D_set4(Criteria criteria, int i)
    {
        criteria.mHorizontalAccuracy = i;
        return i;
    }

    static int _2D_set5(Criteria criteria, int i)
    {
        criteria.mPowerRequirement = i;
        return i;
    }

    static int _2D_set6(Criteria criteria, int i)
    {
        criteria.mSpeedAccuracy = i;
        return i;
    }

    static boolean _2D_set7(Criteria criteria, boolean flag)
    {
        criteria.mSpeedRequired = flag;
        return flag;
    }

    static int _2D_set8(Criteria criteria, int i)
    {
        criteria.mVerticalAccuracy = i;
        return i;
    }

    public Criteria()
    {
        mHorizontalAccuracy = 0;
        mVerticalAccuracy = 0;
        mSpeedAccuracy = 0;
        mBearingAccuracy = 0;
        mPowerRequirement = 0;
        mAltitudeRequired = false;
        mBearingRequired = false;
        mSpeedRequired = false;
        mCostAllowed = false;
    }

    public Criteria(Criteria criteria)
    {
        mHorizontalAccuracy = 0;
        mVerticalAccuracy = 0;
        mSpeedAccuracy = 0;
        mBearingAccuracy = 0;
        mPowerRequirement = 0;
        mAltitudeRequired = false;
        mBearingRequired = false;
        mSpeedRequired = false;
        mCostAllowed = false;
        mHorizontalAccuracy = criteria.mHorizontalAccuracy;
        mVerticalAccuracy = criteria.mVerticalAccuracy;
        mSpeedAccuracy = criteria.mSpeedAccuracy;
        mBearingAccuracy = criteria.mBearingAccuracy;
        mPowerRequirement = criteria.mPowerRequirement;
        mAltitudeRequired = criteria.mAltitudeRequired;
        mBearingRequired = criteria.mBearingRequired;
        mSpeedRequired = criteria.mSpeedRequired;
        mCostAllowed = criteria.mCostAllowed;
    }

    private static String accuracyToString(int i)
    {
        switch(i)
        {
        default:
            return "???";

        case 0: // '\0'
            return "---";

        case 3: // '\003'
            return "HIGH";

        case 2: // '\002'
            return "MEDIUM";

        case 1: // '\001'
            return "LOW";
        }
    }

    private static String powerToString(int i)
    {
        switch(i)
        {
        default:
            return "???";

        case 0: // '\0'
            return "NO_REQ";

        case 1: // '\001'
            return "LOW";

        case 2: // '\002'
            return "MEDIUM";

        case 3: // '\003'
            return "HIGH";
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public int getAccuracy()
    {
        return mHorizontalAccuracy < 3 ? 2 : 1;
    }

    public int getBearingAccuracy()
    {
        return mBearingAccuracy;
    }

    public int getHorizontalAccuracy()
    {
        return mHorizontalAccuracy;
    }

    public int getPowerRequirement()
    {
        return mPowerRequirement;
    }

    public int getSpeedAccuracy()
    {
        return mSpeedAccuracy;
    }

    public int getVerticalAccuracy()
    {
        return mVerticalAccuracy;
    }

    public boolean isAltitudeRequired()
    {
        return mAltitudeRequired;
    }

    public boolean isBearingRequired()
    {
        return mBearingRequired;
    }

    public boolean isCostAllowed()
    {
        return mCostAllowed;
    }

    public boolean isSpeedRequired()
    {
        return mSpeedRequired;
    }

    public void setAccuracy(int i)
    {
        if(i < 0 || i > 2)
            throw new IllegalArgumentException((new StringBuilder()).append("accuracy=").append(i).toString());
        if(i == 1)
            mHorizontalAccuracy = 3;
        else
            mHorizontalAccuracy = 1;
    }

    public void setAltitudeRequired(boolean flag)
    {
        mAltitudeRequired = flag;
    }

    public void setBearingAccuracy(int i)
    {
        if(i < 0 || i > 3)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("accuracy=").append(i).toString());
        } else
        {
            mBearingAccuracy = i;
            return;
        }
    }

    public void setBearingRequired(boolean flag)
    {
        mBearingRequired = flag;
    }

    public void setCostAllowed(boolean flag)
    {
        mCostAllowed = flag;
    }

    public void setHorizontalAccuracy(int i)
    {
        if(i < 0 || i > 3)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("accuracy=").append(i).toString());
        } else
        {
            mHorizontalAccuracy = i;
            return;
        }
    }

    public void setPowerRequirement(int i)
    {
        if(i < 0 || i > 3)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("level=").append(i).toString());
        } else
        {
            mPowerRequirement = i;
            return;
        }
    }

    public void setSpeedAccuracy(int i)
    {
        if(i < 0 || i > 3)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("accuracy=").append(i).toString());
        } else
        {
            mSpeedAccuracy = i;
            return;
        }
    }

    public void setSpeedRequired(boolean flag)
    {
        mSpeedRequired = flag;
    }

    public void setVerticalAccuracy(int i)
    {
        if(i < 0 || i > 3)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("accuracy=").append(i).toString());
        } else
        {
            mVerticalAccuracy = i;
            return;
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("Criteria[power=").append(powerToString(mPowerRequirement));
        stringbuilder.append(" acc=").append(accuracyToString(mHorizontalAccuracy));
        stringbuilder.append(']');
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(mHorizontalAccuracy);
        parcel.writeInt(mVerticalAccuracy);
        parcel.writeInt(mSpeedAccuracy);
        parcel.writeInt(mBearingAccuracy);
        parcel.writeInt(mPowerRequirement);
        if(mAltitudeRequired)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mBearingRequired)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mSpeedRequired)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mCostAllowed)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final int ACCURACY_COARSE = 2;
    public static final int ACCURACY_FINE = 1;
    public static final int ACCURACY_HIGH = 3;
    public static final int ACCURACY_LOW = 1;
    public static final int ACCURACY_MEDIUM = 2;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Criteria createFromParcel(Parcel parcel)
        {
            boolean flag = true;
            Criteria criteria = new Criteria();
            Criteria._2D_set4(criteria, parcel.readInt());
            Criteria._2D_set8(criteria, parcel.readInt());
            Criteria._2D_set6(criteria, parcel.readInt());
            Criteria._2D_set1(criteria, parcel.readInt());
            Criteria._2D_set5(criteria, parcel.readInt());
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            Criteria._2D_set0(criteria, flag1);
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            Criteria._2D_set2(criteria, flag1);
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            Criteria._2D_set7(criteria, flag1);
            if(parcel.readInt() != 0)
                flag1 = flag;
            else
                flag1 = false;
            Criteria._2D_set3(criteria, flag1);
            return criteria;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Criteria[] newArray(int i)
        {
            return new Criteria[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int NO_REQUIREMENT = 0;
    public static final int POWER_HIGH = 3;
    public static final int POWER_LOW = 1;
    public static final int POWER_MEDIUM = 2;
    private boolean mAltitudeRequired;
    private int mBearingAccuracy;
    private boolean mBearingRequired;
    private boolean mCostAllowed;
    private int mHorizontalAccuracy;
    private int mPowerRequirement;
    private int mSpeedAccuracy;
    private boolean mSpeedRequired;
    private int mVerticalAccuracy;

}
