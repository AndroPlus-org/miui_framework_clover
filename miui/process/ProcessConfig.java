// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.process;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import java.util.List;

public class ProcessConfig
    implements Parcelable
{

    public ProcessConfig(int i)
    {
        mUserId = -10000;
        mTaskId = -1;
        mUid = -1;
        mPolicy = i;
    }

    public ProcessConfig(int i, int j, ArrayMap arraymap)
    {
        this(i);
        mUserId = j;
        mKillingPackageMaps = arraymap;
    }

    public ProcessConfig(int i, int j, ArrayMap arraymap, String s)
    {
        this(i, j, arraymap);
        mReason = s;
    }

    public ProcessConfig(int i, String s, int j)
    {
        this(i);
        mKillingPackage = s;
        mUid = j;
    }

    public ProcessConfig(int i, String s, int j, int k)
    {
        this(i);
        mKillingPackage = s;
        mUserId = j;
        mTaskId = k;
    }

    private ProcessConfig(Parcel parcel)
    {
        mUserId = -10000;
        mTaskId = -1;
        mUid = -1;
        readFromParcel(parcel);
    }

    ProcessConfig(Parcel parcel, ProcessConfig processconfig)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public String getKillingPackage()
    {
        return mKillingPackage;
    }

    public ArrayMap getKillingPackageMaps()
    {
        return mKillingPackageMaps;
    }

    public int getPolicy()
    {
        return mPolicy;
    }

    public String getReason()
    {
        return mReason;
    }

    public List getRemovingTaskIdList()
    {
        return mRemovingTaskIdList;
    }

    public int getTaskId()
    {
        return mTaskId;
    }

    public int getUid()
    {
        return mUid;
    }

    public int getUserId()
    {
        return mUserId;
    }

    public List getWhiteList()
    {
        return mWhiteList;
    }

    public boolean isRemoveTaskNeeded()
    {
        return mRemoveTaskNeeded;
    }

    public boolean isTaskIdInvalid()
    {
        boolean flag;
        if(mTaskId == -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isUidInvalid()
    {
        boolean flag;
        if(mUid <= -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isUserIdInvalid()
    {
        boolean flag;
        if(mUserId == -10000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void readFromParcel(Parcel parcel)
    {
        boolean flag = false;
        mPolicy = parcel.readInt();
        mReason = parcel.readString();
        mKillingPackage = parcel.readString();
        mUserId = parcel.readInt();
        mTaskId = parcel.readInt();
        mUid = parcel.readInt();
        if(parcel.readInt() != 0)
            flag = true;
        mRemoveTaskNeeded = flag;
        if(parcel.readInt() != 0)
            mWhiteList = parcel.readArrayList(java/util/List.getClassLoader());
        if(parcel.readInt() != 0)
            mRemovingTaskIdList = parcel.readArrayList(java/util/List.getClassLoader());
        int i;
        java.util.ArrayList arraylist;
        for(; parcel.readInt() != 0; mKillingPackageMaps.put(Integer.valueOf(i), arraylist))
        {
            i = parcel.readInt();
            arraylist = parcel.readArrayList(java/util/List.getClassLoader());
            if(mKillingPackageMaps == null)
                mKillingPackageMaps = new ArrayMap();
        }

    }

    public void setKillingPackage(String s)
    {
        mKillingPackage = s;
    }

    public void setKillingPackageMaps(ArrayMap arraymap)
    {
        mKillingPackageMaps = arraymap;
    }

    public void setReason(String s)
    {
        mReason = s;
    }

    public void setRemoveTaskNeeded(boolean flag)
    {
        mRemoveTaskNeeded = flag;
    }

    public void setRemovingTaskIdList(List list)
    {
        mRemovingTaskIdList = list;
    }

    public void setTaskId(int i)
    {
        mTaskId = i;
    }

    public void setUid(int i)
    {
        mUid = i;
    }

    public void setUserId(int i)
    {
        mUserId = i;
    }

    public void setWhiteList(List list)
    {
        mWhiteList = list;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mPolicy);
        parcel.writeString(mReason);
        parcel.writeString(mKillingPackage);
        parcel.writeInt(mUserId);
        parcel.writeInt(mTaskId);
        parcel.writeInt(mUid);
        if(mRemoveTaskNeeded)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mWhiteList != null)
        {
            parcel.writeInt(1);
            parcel.writeList(mWhiteList);
        } else
        {
            parcel.writeInt(0);
        }
        if(mRemovingTaskIdList != null)
        {
            parcel.writeInt(1);
            parcel.writeList(mRemovingTaskIdList);
        } else
        {
            parcel.writeInt(0);
        }
        if(mKillingPackageMaps != null)
            for(i = 0; i < mKillingPackageMaps.size(); i++)
            {
                Integer integer = (Integer)mKillingPackageMaps.keyAt(i);
                List list = (List)mKillingPackageMaps.valueAt(i);
                if(list != null)
                {
                    parcel.writeInt(1);
                    parcel.writeInt(integer.intValue());
                    parcel.writeList(list);
                }
            }

        parcel.writeInt(0);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ProcessConfig createFromParcel(Parcel parcel)
        {
            return new ProcessConfig(parcel, null);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public ProcessConfig[] newArray(int i)
        {
            return new ProcessConfig[i];
        }

    }
;
    private static final int INVALID_TASK_ID = -1;
    private static final int INVALID_UID = -1;
    private static final int INVALID_USER_ID = -10000;
    public static final int KILL_LEVEL_FORCE_STOP = 104;
    public static final int KILL_LEVEL_KILL = 103;
    public static final int KILL_LEVEL_KILL_BACKGROUND = 102;
    public static final int KILL_LEVEL_TRIM_MEMORY = 101;
    public static final int KILL_LEVEL_UNKNOWN = 100;
    public static final int POLICY_AUTO_IDLE_KILL = 13;
    public static final int POLICY_AUTO_LOCK_OFF_CLEAN = 15;
    public static final int POLICY_AUTO_POWER_KILL = 11;
    public static final int POLICY_AUTO_SLEEP_CLEAN = 14;
    public static final int POLICY_AUTO_THERMAL_KILL = 12;
    public static final int POLICY_FORCE_CLEAN = 2;
    public static final int POLICY_GAME_CLEAN = 4;
    public static final int POLICY_GARBAGE_CLEAN = 6;
    public static final int POLICY_LOCK_SCREEN_CLEAN = 3;
    public static final int POLICY_ONE_KEY_CLEAN = 1;
    public static final int POLICY_OPTIMIZATION_CLEAN = 5;
    public static final int POLICY_SWIPE_UP_CLEAN = 7;
    public static final int POLICY_USER_DEFINED = 10;
    private String mKillingPackage;
    private ArrayMap mKillingPackageMaps;
    private int mPolicy;
    private String mReason;
    private boolean mRemoveTaskNeeded;
    private List mRemovingTaskIdList;
    private int mTaskId;
    private int mUid;
    private int mUserId;
    private List mWhiteList;

}
