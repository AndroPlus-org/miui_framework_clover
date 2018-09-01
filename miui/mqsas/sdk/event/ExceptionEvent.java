// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.mqsas.sdk.event;


public class ExceptionEvent
{

    public ExceptionEvent()
    {
        initType();
        mPid = -1;
        mProcessName = "";
        mPackageName = "";
        mIsSystem = false;
        mTimeStamp = -1L;
        mSummary = "";
        mDetails = "";
        mDigest = "";
        mLogName = "";
        mKeyWord = "";
        mIsUpload = false;
    }

    public String getDetails()
    {
        return mDetails;
    }

    public String getDigest()
    {
        return mDigest;
    }

    public int getEventSeverity()
    {
        return 2;
    }

    public String getKeyWord()
    {
        return mKeyWord;
    }

    public String getLogName()
    {
        return mLogName;
    }

    public String getPackageName()
    {
        return mPackageName;
    }

    public int getPid()
    {
        return mPid;
    }

    public String getProcessName()
    {
        return mProcessName;
    }

    public String getSummary()
    {
        return mSummary;
    }

    public long getTimeStamp()
    {
        return mTimeStamp;
    }

    public int getType()
    {
        return mType;
    }

    public void initType()
    {
        mType = -1;
    }

    public boolean isSystem()
    {
        return mIsSystem;
    }

    public boolean isUpload()
    {
        return mIsUpload;
    }

    public int judgeProcessLevel()
    {
        boolean flag = false;
        if(mProcessName == null || mPackageName == null)
            return -1;
        String as[] = mVerytImportantProcesses;
        int i = as.length;
        for(int j = 0; j < i; j++)
        {
            String s1 = as[j];
            if(s1.equals(mProcessName) || s1.equals(mPackageName))
                return 0;
        }

        String as1[] = mImportantProcesses;
        i = as1.length;
        for(int k = ((flag) ? 1 : 0); k < i; k++)
        {
            String s = as1[k];
            if(s.equals(mProcessName) || s.equals(mPackageName))
                return 1;
        }

        return 2;
    }

    public void setDetails(String s)
    {
        mDetails = s;
    }

    public void setDigest(String s)
    {
        mDigest = s;
    }

    public void setKeyWord(String s)
    {
        mKeyWord = s;
    }

    public void setLogName(String s)
    {
        mLogName = s;
    }

    public void setPackageName(String s)
    {
        mPackageName = s;
    }

    public void setPid(int i)
    {
        mPid = i;
    }

    public void setProcessName(String s)
    {
        mProcessName = s;
    }

    public void setSummary(String s)
    {
        mSummary = s;
    }

    public void setSystem(boolean flag)
    {
        mIsSystem = flag;
    }

    public void setTimeStamp(long l)
    {
        mTimeStamp = l;
    }

    public void setType(int i)
    {
        mType = i;
    }

    public void setUpload(boolean flag)
    {
        mIsUpload = flag;
    }

    public static final int IMPORTANT_PROCESS = 1;
    public static final int NORMAL_PROCESS = 2;
    public static final int SEVERITY_FATAL = 0;
    public static final int SEVERITY_MAJOR = 1;
    public static final int SEVERITY_MINOR = 3;
    public static final int SEVERITY_NORMAL = 2;
    public static final int UNKWOWN_PROCESS = -1;
    public static final int VERY_IMPORTANT_PROCESS = 0;
    protected String mDetails;
    protected String mDigest;
    public final String mImportantProcesses[] = {
        "com.android.systemui", "com.miui.home", "com.android.phone", "mediaserver"
    };
    protected boolean mIsSystem;
    protected boolean mIsUpload;
    protected String mKeyWord;
    protected String mLogName;
    protected String mPackageName;
    protected int mPid;
    protected String mProcessName;
    protected String mSummary;
    protected long mTimeStamp;
    protected int mType;
    public final String mVerytImportantProcesses[] = {
        "system_server", "zygote", "zygote64", "surfaceflinger"
    };
}
