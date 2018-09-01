// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.mqsas.sdk.event;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package miui.mqsas.sdk.event:
//            ExceptionEvent

public class AnrEvent extends ExceptionEvent
    implements Parcelable
{

    public AnrEvent()
    {
        mIsBgAnr = false;
        mReason = "";
        mTargetActivity = "";
        mParent = "";
        mCpuInfo = "";
    }

    private AnrEvent(Parcel parcel)
    {
        boolean flag = true;
        super();
        mType = parcel.readInt();
        mPid = parcel.readInt();
        mProcessName = parcel.readString();
        mPackageName = parcel.readString();
        boolean flag1;
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        mIsSystem = flag1;
        mTimeStamp = parcel.readLong();
        mSummary = parcel.readString();
        mDetails = parcel.readString();
        mDigest = parcel.readString();
        mLogName = parcel.readString();
        mKeyWord = parcel.readString();
        if(parcel.readInt() == 1)
            flag1 = true;
        else
            flag1 = false;
        mIsBgAnr = flag1;
        mReason = parcel.readString();
        mTargetActivity = parcel.readString();
        mParent = parcel.readString();
        mCpuInfo = parcel.readString();
        if(parcel.readInt() == 1)
            flag1 = flag;
        else
            flag1 = false;
        mIsUpload = flag1;
    }

    AnrEvent(Parcel parcel, AnrEvent anrevent)
    {
        this(parcel);
    }

    private String getAnrType()
    {
        if(isInputAnr())
            return "input";
        if(isServiceAnr())
            return "service";
        if(isBroadcastAnr())
            return "broadcast";
        if(isProviderAnr())
            return "provider";
        else
            return "unknown";
    }

    private String getBroadcastDetails()
    {
        StringBuilder stringbuilder = new StringBuilder();
        Object obj = PATTERN_ACTION.matcher(getReason());
        Matcher matcher = PATTERN_COMPONENT.matcher(getReason());
        StringBuilder stringbuilder1 = stringbuilder.append("Broadcast of Intent { ");
        if(((Matcher) (obj)).find())
            obj = (new StringBuilder()).append(((Matcher) (obj)).group()).append(" ").toString();
        else
            obj = "";
        stringbuilder1 = stringbuilder1.append(((String) (obj)));
        if(matcher.find())
            obj = (new StringBuilder()).append(matcher.group()).append(" ").toString();
        else
            obj = "";
        stringbuilder1.append(((String) (obj))).append("}");
        return stringbuilder.toString();
    }

    private String getFixedReason(String s)
    {
        if(TextUtils.isEmpty(s) || !s.startsWith("Input dispatching timed out"))
            break MISSING_BLOCK_LABEL_53;
        String s1 = s.replaceAll("\\d{1,}\\.\\d{1,}", "XX").replaceAll("\\d{1,}", "XX").replaceAll("touched|focused", "XX").replaceAll("NORMAL|BROKEN|ZOMBIE|UNKNOWN", "XX");
        return s1;
        Exception exception;
        exception;
        exception.printStackTrace();
        return s;
    }

    private String getInformation()
    {
        String s = getReason();
        String s1;
        if(isBroadcastAnr())
        {
            s1 = getBroadcastDetails();
        } else
        {
            s1 = s;
            if(isInputAnr())
            {
                s1 = s;
                if("system".equals(getProcessName()) ^ true)
                    return s.replaceAll("(?<=timed out \\().*?(?=Waiting)", "");
            }
        }
        return s1;
    }

    public String createDefaultDetails()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("bgAnr=").append(getBgAnr()).append("@@@");
        stringbuilder.append("type=").append(getAnrType());
        return stringbuilder.toString();
    }

    public String createDefaultDigest()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getProcessName());
        stringbuilder.append("--");
        if(!TextUtils.isEmpty(mTargetActivity))
        {
            stringbuilder.append(mTargetActivity);
            stringbuilder.append("--");
        }
        stringbuilder.append(getInformation());
        return stringbuilder.toString();
    }

    public String createDefaultSummary()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("ANR in ").append(getProcessName());
        stringbuilder.append("--");
        if(!TextUtils.isEmpty(mTargetActivity))
        {
            stringbuilder.append(mTargetActivity);
            stringbuilder.append("--");
        }
        stringbuilder.append(getInformation());
        return stringbuilder.toString();
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean getBgAnr()
    {
        return mIsBgAnr;
    }

    public String getCpuInfo()
    {
        return mCpuInfo;
    }

    public String getParent()
    {
        return mParent;
    }

    public String getReason()
    {
        return mReason;
    }

    public String getTargetActivity()
    {
        return mTargetActivity;
    }

    public void initType()
    {
        mType = 8;
    }

    public boolean isBroadcastAnr()
    {
        return !TextUtils.isEmpty(mReason) && mReason.contains("Broadcast of");
    }

    public boolean isInputAnr()
    {
        return !TextUtils.isEmpty(mReason) && mReason.contains("Input dispatching timed out");
    }

    public boolean isProviderAnr()
    {
        return !TextUtils.isEmpty(mReason) && mReason.contains("ContentProvider not responding");
    }

    public boolean isServiceAnr()
    {
        return !TextUtils.isEmpty(mReason) && mReason.contains("executing service");
    }

    public void setBgAnr(boolean flag)
    {
        mIsBgAnr = flag;
    }

    public void setCpuInfo(String s)
    {
        mCpuInfo = s;
    }

    public void setParent(String s)
    {
        mParent = s;
    }

    public void setReason(String s)
    {
        mReason = getFixedReason(s);
    }

    public void setTargetActivity(String s)
    {
        mTargetActivity = s;
    }

    public String toShortString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("AnrEvent { mType=").append(mType).append(" Pid=").append(mPid).append(" processName=").append(mProcessName).append(" mTimeStamp=").append(mTimeStamp).append(" mDigest=").append(mDigest).append(" mSummary=").append(mSummary).append(" mLogName=").append(mLogName).append(" mKeyWord=").append(mKeyWord).append(" mIsBgAnr=").append(mIsBgAnr).append(" mReason=").append(mReason).append(" mTargetActivity=").append(mTargetActivity).append(" mParent=").append(mParent).append(" mCpuinfo=").append(mCpuInfo);
        return stringbuilder.toString();
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("AnrEvent { mType=").append(mType).append(" Pid=").append(mPid).append(" processName=").append(mProcessName).append(" mTimeStamp=").append(mTimeStamp).append(" mSummary=").append(mSummary).append(" mDetails=").append(mDetails).append(" mDigest=").append(mDigest).append(" mDetails=").append(mDetails).append(" mSummary=").append(mSummary).append(" mLogName=").append(mLogName).append(" mKeyWord=").append(mKeyWord).append(" mIsBgAnr=").append(mIsBgAnr).append(" mReason=").append(mReason).append(" mTargetActivity=").append(mTargetActivity).append(" mParent=").append(mParent).append(" mCpuinfo=").append(mCpuInfo);
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(mType);
        parcel.writeInt(mPid);
        parcel.writeString(mProcessName);
        parcel.writeString(mPackageName);
        if(mIsSystem)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeLong(mTimeStamp);
        parcel.writeString(mSummary);
        parcel.writeString(mDetails);
        parcel.writeString(mDigest);
        parcel.writeString(mLogName);
        parcel.writeString(mKeyWord);
        if(mIsBgAnr)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeString(mReason);
        parcel.writeString(mTargetActivity);
        parcel.writeString(mParent);
        parcel.writeString(mCpuInfo);
        if(mIsUpload)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AnrEvent createFromParcel(Parcel parcel)
        {
            return new AnrEvent(parcel, null);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public AnrEvent[] newArray(int i)
        {
            return new AnrEvent[i];
        }

    }
;
    private static final Pattern PATTERN_ACTION = Pattern.compile("act=[^\\(\\)\\{\\}\\[\\]\\s]+");
    private static final Pattern PATTERN_COMPONENT = Pattern.compile("cmp=[^\\(\\)\\{\\}\\[\\]\\s]+");
    private static final String REGEX_DECIMAL = "\\d{1,}\\.\\d{1,}";
    private static final String REGEX_EXTRAS = "(?<=timed out \\().*?(?=Waiting)";
    private static final String REGEX_INT = "\\d{1,}";
    private static final String REGEX_STATUS = "NORMAL|BROKEN|ZOMBIE|UNKNOWN";
    private static final String REGEX_TARGET_TYPE = "touched|focused";
    private static final String REPLACEMENT = "XX";
    private static final String SPECIAL_CHARS = "\\(\\)\\{\\}\\[\\]\\s";
    private String mCpuInfo;
    private boolean mIsBgAnr;
    private String mParent;
    private String mReason;
    private String mTargetActivity;

}
