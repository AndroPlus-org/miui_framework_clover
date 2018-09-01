// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.os.Parcel;
import android.text.TextUtils;
import miui.os.ProcessUtils;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package android.os.statistics:
//            MacroscopicEvent, JniParcel, NativeBackTrace

public class Dex2oatEvent extends MacroscopicEvent
{

    public Dex2oatEvent()
    {
        this(0, "", 0, 0L, 0L, 0, 0, 0, 0L, 0L);
    }

    public Dex2oatEvent(int i, String s, int j, long l, long l1, 
            int k, int i1, int j1, long l2, long l3)
    {
        super(0x10003, EV_TYPE_NAME);
        mDexFiles = s;
        mThreadCount = j;
        mBeginUpTimeMills = l;
        mEndUpTimeMills = l1;
        mScheduler = k;
        mNice = i1;
        mCGroup = j1;
        mCPURunningTimeMills = l2;
        mCPURunnableTimeMills = l3;
        occurUptimeMillis = mEndUpTimeMills;
        pid = i;
        processName = ProcessUtils.getProcessNameByPid(pid);
        if(TextUtils.isEmpty(processName))
            processName = String.valueOf(pid);
    }

    void fillIn(JniParcel jniparcel, Object obj, NativeBackTrace nativebacktrace)
    {
    }

    public long getBeginUptimeMillis()
    {
        return mBeginUpTimeMills;
    }

    public long getEndUptimeMillis()
    {
        return mEndUpTimeMills;
    }

    boolean occursInCurrentProcess()
    {
        return false;
    }

    public void readFromJson(JSONObject jsonobject)
    {
        super.readFromJson(jsonobject);
        mDexFiles = jsonobject.optString("dexFiles");
        mThreadCount = jsonobject.optInt("threadCount");
        mBeginUpTimeMills = jsonobject.optLong("beginTime");
        mEndUpTimeMills = jsonobject.optLong("endTime");
        mScheduler = jsonobject.optInt("policy");
        mNice = jsonobject.optInt("priority");
        mCGroup = jsonobject.optInt("schedGroup");
        mCPURunningTimeMills = jsonobject.optLong("runningTime");
        mCPURunnableTimeMills = jsonobject.optLong("runnableTime");
    }

    public void readFromParcel(Parcel parcel)
    {
        super.readFromParcel(parcel);
        mDexFiles = parcel.readString();
        mThreadCount = parcel.readInt();
        mBeginUpTimeMills = parcel.readLong();
        mEndUpTimeMills = parcel.readLong();
        mScheduler = parcel.readInt();
        mNice = parcel.readInt();
        mCGroup = parcel.readInt();
        mCPURunningTimeMills = parcel.readLong();
        mCPURunnableTimeMills = parcel.readLong();
    }

    public void reset()
    {
        super.reset();
        mDexFiles = "";
        mThreadCount = 0;
        mBeginUpTimeMills = 0L;
        mEndUpTimeMills = 0L;
        mScheduler = 0;
        mNice = 0;
        mCGroup = 0;
        mCPURunningTimeMills = 0L;
        mCPURunnableTimeMills = 0L;
    }

    public void writeToJson(JSONObject jsonobject)
    {
        super.writeToJson(jsonobject);
        jsonobject.put("dexFiles", mDexFiles);
        jsonobject.put("threadCount", mThreadCount);
        jsonobject.put("beginTime", mBeginUpTimeMills);
        jsonobject.put("endTime", mEndUpTimeMills);
        jsonobject.put("policy", mScheduler);
        jsonobject.put("priority", mNice);
        jsonobject.put("schedGroup", mCGroup);
        jsonobject.put("runningTime", mCPURunningTimeMills);
        jsonobject.put("runnableTime", mCPURunnableTimeMills);
_L1:
        return;
        jsonobject;
        jsonobject.printStackTrace();
          goto _L1
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeString(mDexFiles);
        parcel.writeInt(mThreadCount);
        parcel.writeLong(mBeginUpTimeMills);
        parcel.writeLong(mEndUpTimeMills);
        parcel.writeInt(mScheduler);
        parcel.writeInt(mNice);
        parcel.writeInt(mCGroup);
        parcel.writeLong(mCPURunningTimeMills);
        parcel.writeLong(mCPURunnableTimeMills);
    }

    public static String EV_TYPE_NAME = "dex2oat";
    private long mBeginUpTimeMills;
    private int mCGroup;
    private long mCPURunnableTimeMills;
    private long mCPURunningTimeMills;
    private String mDexFiles;
    private long mEndUpTimeMills;
    private int mNice;
    private int mScheduler;
    private int mThreadCount;

}
