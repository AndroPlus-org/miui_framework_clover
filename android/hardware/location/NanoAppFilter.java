// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.os.Parcel;

// Referenced classes of package android.hardware.location:
//            NanoAppInstanceInfo

public class NanoAppFilter
{

    public NanoAppFilter(long l, int i, int j, long l1)
    {
        mContextHubId = -1;
        mAppId = l;
        mAppVersion = i;
        mVersionRestrictionMask = j;
        mAppIdVendorMask = l1;
    }

    private NanoAppFilter(Parcel parcel)
    {
        mContextHubId = -1;
        mAppId = parcel.readLong();
        mAppVersion = parcel.readInt();
        mVersionRestrictionMask = parcel.readInt();
        mAppIdVendorMask = parcel.readInt();
    }

    NanoAppFilter(Parcel parcel, NanoAppFilter nanoappfilter)
    {
        this(parcel);
    }

    private boolean versionsMatch(int i, int j, int k)
    {
        return true;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean testMatch(NanoAppInstanceInfo nanoappinstanceinfo)
    {
        boolean flag;
        if((mContextHubId == -1 || nanoappinstanceinfo.getContexthubId() == mContextHubId) && (mAppId == -1L || nanoappinstanceinfo.getAppId() == mAppId))
            flag = versionsMatch(mVersionRestrictionMask, mAppVersion, nanoappinstanceinfo.getAppVersion());
        else
            flag = false;
        return flag;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(mAppId);
        parcel.writeInt(mAppVersion);
        parcel.writeInt(mVersionRestrictionMask);
        parcel.writeLong(mAppIdVendorMask);
    }

    public static final int APP_ANY = -1;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NanoAppFilter createFromParcel(Parcel parcel)
        {
            return new NanoAppFilter(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NanoAppFilter[] newArray(int i)
        {
            return new NanoAppFilter[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FLAGS_VERSION_ANY = -1;
    public static final int FLAGS_VERSION_GREAT_THAN = 2;
    public static final int FLAGS_VERSION_LESS_THAN = 4;
    public static final int FLAGS_VERSION_STRICTLY_EQUAL = 8;
    public static final int HUB_ANY = -1;
    private static final String TAG = "NanoAppFilter";
    public static final int VENDOR_ANY = -1;
    private long mAppId;
    private long mAppIdVendorMask;
    private int mAppVersion;
    private int mContextHubId;
    private int mVersionRestrictionMask;

}
