// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.strategy;

import android.os.*;

public class WhetstonePackageInfo
    implements Parcelable, Cloneable
{

    private WhetstonePackageInfo(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    WhetstonePackageInfo(Parcel parcel, WhetstonePackageInfo whetstonepackageinfo)
    {
        this(parcel);
    }

    public WhetstonePackageInfo(String s, String s1, int i, int j, int k, boolean flag1)
    {
        packageName = s;
        samePackageList = s1;
        capacity = i;
        uiMemoryThresold = j;
        nonUiMemoryThresold = k;
        mScreenOffClear = flag1;
        flag = 0;
        type = 0;
        mForeGroundStartCount = 0L;
        mForeGroundTime = 0L;
        mBackGroundTime = 0L;
        mTotalForeGroundTime = 0L;
        mHistoryOrder = 0;
        mLifeOrder = -1;
        mClearType = 0;
        mClearScore = 0;
        mPermission = -1;
        mPromoteLevel = -1;
    }

    public WhetstonePackageInfo(String s, boolean flag1)
    {
        packageName = s;
        int i;
        if(flag1)
            i = 0x80000000;
        else
            i = 0;
        flag = i;
        type = 0;
        mScreenOffClear = false;
        mForeGroundStartCount = 0L;
        mForeGroundTime = 0L;
        mBackGroundTime = 0L;
        mTotalForeGroundTime = 0L;
        mHistoryOrder = 0;
        mLifeOrder = -1;
        mClearType = 0;
        mClearScore = 0;
        mPermission = -1;
        mPromoteLevel = -1;
    }

    public void addFlag(int i)
    {
        flag = flag | i;
    }

    public void addForeGroundStartCount()
    {
        mForeGroundStartCount = mForeGroundStartCount + 1L;
    }

    public void addType(int i)
    {
        type = type | i;
    }

    public void clearBackGroundStartTime()
    {
        mBackGroundTime = 0L;
    }

    public void clearForeGroundStartTime()
    {
        mForeGroundTime = 0L;
    }

    public Object clone()
    {
        WhetstonePackageInfo whetstonepackageinfo = null;
        WhetstonePackageInfo whetstonepackageinfo1 = (WhetstonePackageInfo)super.clone();
        whetstonepackageinfo = whetstonepackageinfo1;
_L2:
        return whetstonepackageinfo;
        CloneNotSupportedException clonenotsupportedexception;
        clonenotsupportedexception;
        clonenotsupportedexception.printStackTrace();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int describeContents()
    {
        return 0;
    }

    public void endForeGround()
    {
        setBackGroundStartTime();
        mTotalForeGroundTime = mTotalForeGroundTime + (SystemClock.elapsedRealtime() - mForeGroundTime);
    }

    public long getForeGroundStartCount()
    {
        return mForeGroundStartCount;
    }

    public int getScore()
    {
        return mClearScore;
    }

    public int getUid()
    {
        return uid;
    }

    public boolean isDisableWakelock()
    {
        boolean flag1 = false;
        if((flag & 0x4000) != 0)
            flag1 = true;
        return flag1;
    }

    public boolean isEnable(int i)
    {
        boolean flag1 = false;
        if((flag & i) != 0)
            flag1 = true;
        return flag1;
    }

    public boolean isSystemApp()
    {
        boolean flag1 = false;
        if((flag & 0x80000000) != 0)
            flag1 = true;
        return flag1;
    }

    public boolean isType(int i)
    {
        boolean flag1 = false;
        if((type & i) != 0)
            flag1 = true;
        return flag1;
    }

    public void readFromParcel(Parcel parcel)
    {
        packageName = parcel.readString();
        flag = parcel.readInt();
        type = parcel.readInt();
        uid = parcel.readInt();
        mPermission = parcel.readInt();
        mPromoteLevel = parcel.readInt();
    }

    public void setBackGroundStartTime()
    {
        mBackGroundTime = SystemClock.elapsedRealtime();
    }

    public void setForeGroundStartCount(long l)
    {
        mForeGroundStartCount = l;
    }

    public void setForeGroundStartTime()
    {
        mForeGroundTime = SystemClock.elapsedRealtime();
    }

    public void setScore(int i)
    {
        mClearScore = i;
    }

    public void setUid(int i)
    {
        uid = i;
    }

    public void startForceGround()
    {
        clearBackGroundStartTime();
        setForeGroundStartTime();
        addForeGroundStartCount();
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("{WhetstonePackageInfo#PacakgeName:").append(packageName);
        stringbuilder.append(" uid:").append(uid);
        stringbuilder.append(" uiMemoryThresold:").append(uiMemoryThresold);
        stringbuilder.append(" nonUiMemoryThresold:").append(nonUiMemoryThresold);
        String s = Integer.toHexString(flag);
        stringbuilder.append(" Flag:").append(flag).append(",0x").append(s).append(" [");
        if((flag & 1) != 0)
            stringbuilder.append(",BitmapCache");
        if((flag & 4) != 0)
            stringbuilder.append(",DestoryActivity");
        if((flag & 2) != 0)
            stringbuilder.append(",isOPENGLDiable");
        if((flag & 0x40) != 0)
            stringbuilder.append(",TRIMHEAPS");
        if((flag & 0x200) != 0)
            stringbuilder.append(",TRIM_OPENGL");
        if((flag & 0x400) != 0)
            stringbuilder.append(",SOFT_RESET");
        if((flag & 0x80000000) != 0)
            stringbuilder.append(",APP_SYSTEM");
        if((flag & 0x80) != 0)
            stringbuilder.append(",ZRAM");
        if((flag & 0x800) != 0)
            stringbuilder.append(",TRIMPROCESS_BY_ACTIVITY");
        if((flag & 0x1000) != 0)
            stringbuilder.append(",FLAG_DEAL_SCHEDULE");
        if((flag & 0x2000) != 0)
            stringbuilder.append(",FLAG_TRIMHEAPSIZE");
        stringbuilder.append("] Type:").append(type).append("[");
        if((type & 1) != 0)
            stringbuilder.append(",IM_PUSH");
        if((type & 2) != 0)
            stringbuilder.append(",Game");
        if((type & 8) != 0)
            stringbuilder.append(",note");
        if((type & 0x10) != 0)
            stringbuilder.append(",notification");
        if((type & 0x40) != 0)
            stringbuilder.append(",AUTO_START");
        if((type & 0x80) != 0)
            stringbuilder.append(",TYPE_LARGE_MEMORY");
        stringbuilder.append("] }");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(packageName);
        parcel.writeInt(flag);
        parcel.writeInt(type);
        parcel.writeInt(uid);
        parcel.writeInt(mPermission);
        parcel.writeInt(mPromoteLevel);
    }

    public static final int CAP_LOWMEM_KILL = 1;
    public static final int CAP_LOWMEM_THRESOLD_KILL = 2;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WhetstonePackageInfo createFromParcel(Parcel parcel)
        {
            return new WhetstonePackageInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WhetstonePackageInfo[] newArray(int i)
        {
            return new WhetstonePackageInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FLAG_ANDROID_PERSISTENT_APP = 0x40000;
    public static final int FLAG_APP_STOP = 0x40000000;
    public static final int FLAG_APP_SYSTEM = 0x80000000;
    public static final int FLAG_BACKGROUND_START = 256;
    public static final int FLAG_BITMAPCACHE = 1;
    public static final int FLAG_DEAL_SCHEDULE = 4096;
    public static final int FLAG_DESTORYACTIVITY = 4;
    public static final int FLAG_DISABLEOPENGL = 2;
    public static final int FLAG_DISABLE_WAKELOCK = 16384;
    public static final int FLAG_ONEKEY_CLEAN_NO_UI_WHITE = 0x20000;
    public static final int FLAG_ONEKEY_CLEAN_WHITE = 0x10000;
    public static final int FLAG_SOFT_RESET = 1024;
    public static final int FLAG_TRIMBACKGOUNDAPPS = 32;
    public static final int FLAG_TRIMHEAPS = 64;
    public static final int FLAG_TRIMHEAPSIZE = 8192;
    public static final int FLAG_TRIMPROCESS_BY_ACTIVITY = 2048;
    public static final int FLAG_TRIMSERVICES = 16;
    public static final int FLAG_TRIM_OPENGL = 512;
    public static final int FLAG_UPDATESETTING = 8;
    public static final int FLAG_ZRAM = 128;
    public static final int TRIM_LEVEL_WHETSTONE_HEAP = 1001;
    public static final int TRIM_LEVLE_WHETSTONE_BITMAPCACHE = 1000;
    public static final int TRIM_LEVLE_WHETSTONE_DEFAULT = 1100;
    public static final int TRIM_LEVLE_WHETSTONE_NATIVE = 1002;
    public static final int TYPE_AUTO_START = 64;
    public static final int TYPE_GAME = 2;
    public static final int TYPE_IM_PUSH = 1;
    public static final int TYPE_INPUT_METHOD = 32;
    public static final int TYPE_LARGE_MEMORY = 128;
    public static final int TYPE_MUSIC = 4;
    public static final int TYPE_NOTE = 8;
    public static final int TYPE_NOTIFICATION = 16;
    public int capacity;
    public int flag;
    public long mBackGroundTime;
    public int mClearScore;
    public int mClearType;
    public int mExceptionAnrCount;
    public int mExceptionCrashCount;
    public int mExceptionTotalCount;
    public long mFirstExceptionTime;
    public long mForeGroundStartCount;
    public long mForeGroundTime;
    public int mHistoryOrder;
    public int mLifeOrder;
    public int mPermission;
    public int mPromoteLevel;
    public boolean mScreenOffClear;
    public long mStartTime;
    public long mTotalForeGroundTime;
    public int nonUiMemoryThresold;
    public String packageName;
    public String samePackageList;
    public int type;
    public int uiMemoryThresold;
    public int uid;

}
