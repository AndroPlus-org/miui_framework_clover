// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package com.miui.whetstone:
//            WhetstoneManager

public class WhetstonePackageState
    implements Parcelable
{

    protected WhetstonePackageState(Parcel parcel)
    {
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
    }

    public static final int CLOUD_USERID = 0;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WhetstonePackageState createFromParcel(Parcel parcel)
        {
            return new WhetstonePackageState(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WhetstonePackageState[] newArray(int i)
        {
            return new WhetstonePackageState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final boolean DEBUG;
    public static final String SERVICE_RESTART = "Restart: AMS";
    public static final String TAG = "WhetstonePackageState";
    public static final int WPS_CHECK_FALSE = -1;
    public static final int WPS_CHECK_TRUE = 1;
    public static final int WPS_FEATURE_ACTIVITY = 1;
    public static final int WPS_FEATURE_ALL_OFF = 0;
    public static final int WPS_FEATURE_ALL_OPEN = 15;
    public static final int WPS_FEATURE_PROVIDER = 8;
    public static final int WPS_FEATURE_RECEIVER = 4;
    public static final int WPS_FEATURE_SERVICE = 2;
    public static final int WPS_START_BY_ACTIVITY = 1;
    public static final int WPS_START_BY_ALL = 15;
    public static final int WPS_START_BY_DEFAULT = 0;
    public static final int WPS_START_BY_PROVIDER = 8;
    public static final int WPS_START_BY_RECEIVER = 4;
    public static final int WPS_START_BY_SERVICE = 2;
    public static final int WPS_START_FORBIDDEN = 0;

    static 
    {
        DEBUG = WhetstoneManager.DEBUG;
    }
}
