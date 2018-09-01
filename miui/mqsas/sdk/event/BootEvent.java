// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.mqsas.sdk.event;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BootEvent
    implements Parcelable
{

    public BootEvent()
    {
        mType = -1;
        mTimeStamp = -1L;
        mMiuiVersion = android.os.Build.VERSION.INCREMENTAL;
        mBootType = -1;
        mPeriodSystemRun = -1L;
        mPeriodPmsScan = -1L;
        mPeriodDexopt = -1L;
        mPeriodAmsReady = -1L;
        mPeriodUIReady = -1L;
        mPeriodBootComplete = -1L;
        mDetailSystemRun = "";
        mDetailPmsScan = "";
        mDetailDexopt = "";
        mDetailAmsReady = "";
        mDetailUIReady = "";
        mDetailBootComplete = "";
    }

    private BootEvent(Parcel parcel)
    {
        mType = parcel.readInt();
        mTimeStamp = parcel.readLong();
        mBootType = parcel.readInt();
        mPeriodSystemRun = parcel.readLong();
        mPeriodPmsScan = parcel.readLong();
        mPeriodDexopt = parcel.readLong();
        mPeriodAmsReady = parcel.readLong();
        mPeriodUIReady = parcel.readLong();
        mPeriodBootComplete = parcel.readLong();
        mDetailSystemRun = parcel.readString();
        mDetailPmsScan = parcel.readString();
        mDetailDexopt = parcel.readString();
        mDetailAmsReady = parcel.readString();
        mDetailUIReady = parcel.readString();
        mDetailBootComplete = parcel.readString();
        mMiuiVersion = parcel.readString();
    }

    BootEvent(Parcel parcel, BootEvent bootevent)
    {
        this(parcel);
    }

    public static String bootTypeToString(int i)
    {
        switch(i)
        {
        default:
            return "Unknown";

        case 1: // '\001'
            return "NormalBoot";

        case 2: // '\002'
            return "FirstBoot";

        case 3: // '\003'
            return "OtaBoot";
        }
    }

    private String formatTime(long l)
    {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(l));
    }

    public int describeContents()
    {
        return 0;
    }

    public int getBootType()
    {
        return mBootType;
    }

    public String getDetailAmsReady()
    {
        return mDetailAmsReady;
    }

    public String getDetailBootComplete()
    {
        return mDetailBootComplete;
    }

    public String getDetailDexopt()
    {
        return mDetailDexopt;
    }

    public String getDetailPmsScan()
    {
        return mDetailPmsScan;
    }

    public String getDetailSystemRun()
    {
        return mDetailSystemRun;
    }

    public String getDetailUIReady()
    {
        return mDetailUIReady;
    }

    public String getMiuiVersion()
    {
        return mMiuiVersion;
    }

    public long getPeriodAmsReady()
    {
        return mPeriodAmsReady;
    }

    public long getPeriodBootComplete()
    {
        return mPeriodBootComplete;
    }

    public long getPeriodDexopt()
    {
        return mPeriodDexopt;
    }

    public long getPeriodPmsScan()
    {
        return mPeriodPmsScan;
    }

    public long getPeriodSystemRun()
    {
        return mPeriodSystemRun;
    }

    public long getPeriodUIReady()
    {
        return mPeriodUIReady;
    }

    public long getTimeStamp()
    {
        return mTimeStamp;
    }

    public int getType()
    {
        return mType;
    }

    public void setBootType(int i)
    {
        mBootType = i;
    }

    public void setDetailAmsReady(String s)
    {
        mDetailAmsReady = s;
    }

    public void setDetailBootComplete(String s)
    {
        mDetailBootComplete = s;
    }

    public void setDetailDexopt(String s)
    {
        mDetailDexopt = s;
    }

    public void setDetailPmsScan(String s)
    {
        mDetailPmsScan = s;
    }

    public void setDetailSystemRun(String s)
    {
        mDetailSystemRun = s;
    }

    public void setDetailUIReady(String s)
    {
        mDetailUIReady = s;
    }

    public void setMiuiVersion(String s)
    {
        mMiuiVersion = s;
    }

    public void setPeriodAmsReady(long l)
    {
        mPeriodAmsReady = l;
    }

    public void setPeriodBootComplete(long l)
    {
        mPeriodBootComplete = l;
    }

    public void setPeriodDexopt(long l)
    {
        mPeriodDexopt = l;
    }

    public void setPeriodPmsScan(long l)
    {
        mPeriodPmsScan = l;
    }

    public void setPeriodSystemRun(long l)
    {
        mPeriodSystemRun = l;
    }

    public void setPeriodUIReady(long l)
    {
        mPeriodUIReady = l;
    }

    public void setTimeStamp(long l)
    {
        mTimeStamp = l;
    }

    public void setType(int i)
    {
        mType = i;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("BootEvent {mTimeStamp=").append(formatTime(mTimeStamp)).append(",").append("mBootType=").append(bootTypeToString(mBootType)).append(",").append("mPeriod").append("SystemRun").append(":").append(mPeriodSystemRun).append(",").append("mPeriod").append("PmsScan").append(":").append(mPeriodPmsScan).append(",").append("mPeriod").append("Dexopt").append(":").append(mPeriodDexopt).append(",").append("mPeriod").append("AmsReady").append(":").append(mPeriodAmsReady).append(",").append("mPeriod").append("UIReady").append(":").append(mPeriodUIReady).append(",").append("mPeriod").append("BootComplete").append(":").append(mPeriodBootComplete).append(",").append("mDetail").append("SystemRun").append(":").append(mDetailSystemRun).append(",").append("mDetail").append("PmsScan").append(":").append(mDetailPmsScan).append(",").append("mDetail").append("Dexopt").append(":").append(mDetailDexopt).append(",").append("mDetail").append("AmsReady").append(":").append(mDetailAmsReady).append(",").append("mDetail").append("UIReady").append(":").append(mDetailUIReady).append(",").append("mDetail").append("BootComplete").append(":").append(mDetailBootComplete).append(",").append("mMiuiVersion").append(":").append(mMiuiVersion).append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mType);
        parcel.writeLong(mTimeStamp);
        parcel.writeInt(mBootType);
        parcel.writeLong(mPeriodSystemRun);
        parcel.writeLong(mPeriodPmsScan);
        parcel.writeLong(mPeriodDexopt);
        parcel.writeLong(mPeriodAmsReady);
        parcel.writeLong(mPeriodUIReady);
        parcel.writeLong(mPeriodBootComplete);
        parcel.writeString(mDetailSystemRun);
        parcel.writeString(mDetailPmsScan);
        parcel.writeString(mDetailDexopt);
        parcel.writeString(mDetailAmsReady);
        parcel.writeString(mDetailUIReady);
        parcel.writeString(mDetailBootComplete);
        parcel.writeString(mMiuiVersion);
    }

    public static final String ACTION_BOOT_AMS_READY = "AmsReady";
    public static final String ACTION_BOOT_BOOT_COMPLETE = "BootComplete";
    public static final String ACTION_BOOT_DEXOPT = "Dexopt";
    public static final String ACTION_BOOT_PMS_SCAN = "PmsScan";
    public static final String ACTION_BOOT_SYSTEM_RUN = "SystemRun";
    public static final String ACTION_BOOT_UI_READY = "UIReady";
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BootEvent createFromParcel(Parcel parcel)
        {
            return new BootEvent(parcel, null);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public BootEvent[] newArray(int i)
        {
            return new BootEvent[i];
        }

    }
;
    public static final int TYPE_BOOT_FIRST = 2;
    public static final int TYPE_BOOT_NORMAL = 1;
    public static final int TYPE_BOOT_OTA = 3;
    private int mBootType;
    private String mDetailAmsReady;
    private String mDetailBootComplete;
    private String mDetailDexopt;
    private String mDetailPmsScan;
    private String mDetailSystemRun;
    private String mDetailUIReady;
    private String mMiuiVersion;
    private long mPeriodAmsReady;
    private long mPeriodBootComplete;
    private long mPeriodDexopt;
    private long mPeriodPmsScan;
    private long mPeriodSystemRun;
    private long mPeriodUIReady;
    private long mTimeStamp;
    private int mType;

}
