// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.mqsas.sdk.event;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PackageEvent
    implements Parcelable
{

    public PackageEvent()
    {
        mType = -1;
        mTimeStamp = -1L;
        mAction = -1;
        mPackageName = "";
        mReturnCode = -1;
        mReturnMsg = "";
        mVersionCode = -1;
        mVersionName = "";
        mInstallerPkgName = "";
    }

    private PackageEvent(Parcel parcel)
    {
        mType = parcel.readInt();
        mTimeStamp = parcel.readLong();
        mAction = parcel.readInt();
        mPackageName = parcel.readString();
        mReturnCode = parcel.readInt();
        mReturnMsg = parcel.readString();
        mVersionCode = parcel.readInt();
        mVersionName = parcel.readString();
        mInstallerPkgName = parcel.readString();
    }

    PackageEvent(Parcel parcel, PackageEvent packageevent)
    {
        this(parcel);
    }

    public static String actionToString(int i)
    {
        switch(i)
        {
        default:
            return "Unknown";

        case 1: // '\001'
            return "Install";

        case 3: // '\003'
            return "Update";

        case 2: // '\002'
            return "Uninstall";
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

    public int getAction()
    {
        return mAction;
    }

    public String getInstallerPkgName()
    {
        return mInstallerPkgName;
    }

    public String getPackageName()
    {
        return mPackageName;
    }

    public int getReturnCode()
    {
        return mReturnCode;
    }

    public String getReturnMsg()
    {
        return mReturnMsg;
    }

    public long getTimeStamp()
    {
        return mTimeStamp;
    }

    public int getType()
    {
        return mType;
    }

    public int getVersionCode()
    {
        return mVersionCode;
    }

    public String getVersionName()
    {
        return mVersionName;
    }

    public void setAction(int i)
    {
        mAction = i;
    }

    public void setInstallerPkgName(String s)
    {
        mInstallerPkgName = s;
    }

    public void setPackageName(String s)
    {
        mPackageName = s;
    }

    public void setReturnCode(int i)
    {
        mReturnCode = i;
    }

    public void setReturnMsg(String s)
    {
        mReturnMsg = s;
    }

    public void setTimeStamp(long l)
    {
        mTimeStamp = l;
    }

    public void setType(int i)
    {
        mType = i;
    }

    public void setVersionCode(int i)
    {
        mVersionCode = i;
    }

    public void setVersionName(String s)
    {
        mVersionName = s;
    }

    public String toShortString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("PackageEvent { mAction=").append(actionToString(mAction)).append(" mPackageName=").append(mPackageName).append(" mReturnCode=").append(mReturnCode);
        return stringbuilder.toString();
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("PackageEvent { mTimeStamp=").append(formatTime(mTimeStamp)).append(" mAction=").append(actionToString(mAction)).append(" mPackageName=").append(mPackageName).append(" mReturnCode=").append(mReturnCode).append(" mVersionCode=").append(mVersionCode).append(" mVersionName=").append(mVersionName).append("mInstallerPkgName=").append(mInstallerPkgName);
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mType);
        parcel.writeLong(mTimeStamp);
        parcel.writeInt(mAction);
        parcel.writeString(mPackageName);
        parcel.writeInt(mReturnCode);
        parcel.writeString(mReturnMsg);
        parcel.writeInt(mVersionCode);
        parcel.writeString(mVersionName);
        parcel.writeString(mInstallerPkgName);
    }

    public static final int ACTION_PACKAGE_INSTALL = 1;
    public static final int ACTION_PACKAGE_UNINSTALL = 2;
    public static final int ACTION_PACKAGE_UPDATE = 3;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PackageEvent createFromParcel(Parcel parcel)
        {
            return new PackageEvent(parcel, null);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public PackageEvent[] newArray(int i)
        {
            return new PackageEvent[i];
        }

    }
;
    private int mAction;
    private String mInstallerPkgName;
    private String mPackageName;
    private int mReturnCode;
    private String mReturnMsg;
    private long mTimeStamp;
    private int mType;
    private int mVersionCode;
    private String mVersionName;

}
