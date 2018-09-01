// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.security;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Slog;

public class WakePathRuleInfo
    implements Parcelable
{
    public static class UserSettings
    {

        public static final int ACCEPT = 1;
        public static final int REJECT = 2;
        public static final int UNDEF = 0;

        public UserSettings()
        {
        }
    }


    private WakePathRuleInfo(Parcel parcel)
    {
        mActionExpress = parcel.readString();
        mActionExpressType = parcel.readInt();
        mClassNameExpress = parcel.readString();
        mClassNameExpressType = parcel.readInt();
        mCallerExpress = parcel.readString();
        mCallerExpressType = parcel.readInt();
        mCalleeExpress = parcel.readString();
        mCalleeExpressType = parcel.readInt();
        mWakeType = parcel.readInt();
        mUserSettings = parcel.readInt();
        mHashCode = parcel.readInt();
    }

    WakePathRuleInfo(Parcel parcel, WakePathRuleInfo wakepathruleinfo)
    {
        this(parcel);
    }

    public WakePathRuleInfo(String s, String s1, String s2, String s3, int i, int j)
        throws Exception
    {
        mActionExpress = s;
        mActionExpressType = getExpressType(mActionExpress);
        mClassNameExpress = s1;
        mClassNameExpressType = getExpressType(mClassNameExpress);
        mCallerExpress = s2;
        mCallerExpressType = getExpressType(mCallerExpress);
        mCalleeExpress = s3;
        mCalleeExpressType = getExpressType(mCalleeExpress);
        mWakeType = i;
        mUserSettings = j;
        if(mWakeType == 16)
            mHashCode = getHashCode(s, s1, s2, s3);
        else
            mHashCode = 0;
    }

    public static boolean checkCompatibility(String s, String s1, String s2, String s3, int i)
    {
        if(i == 17)
            return true;
        if(TextUtils.equals(s, "*") && TextUtils.equals(s1, "*") && TextUtils.equals(s2, "*") && TextUtils.equals(s3, "*"))
            return false;
        if(s.length() + s1.length() + s2.length() + s3.length() < 10)
            return false;
        if(TextUtils.equals(s2, "com.miui.home") || TextUtils.equals(s3, "com.miui.home"))
            return false;
        if(TextUtils.equals(s2, "android") || TextUtils.equals(s3, "android"))
        {
            if(TextUtils.equals(s3, "android"))
                return false;
            if(TextUtils.equals(s, "*") && TextUtils.equals(s1, "*"))
                return false;
        }
        return true;
    }

    private static boolean expressCompare(String s, int i, String s1)
    {
        i;
        JVM INSTR tableswitch 0 3: default 32
    //                   0 34
    //                   1 44
    //                   2 32
    //                   3 85;
           goto _L1 _L2 _L3 _L1 _L4
_L1:
        return true;
_L2:
        if(!TextUtils.equals(s, s1))
            return false;
        continue; /* Loop/switch isn't completed */
_L3:
        String s2 = s;
        if(s.length() >= 2)
            s2 = s.substring(0, s.length() - 2);
        if(TextUtils.isEmpty(s1) || s1.startsWith(s2) ^ true)
            return false;
        if(true) goto _L1; else goto _L4
_L4:
        if(TextUtils.isEmpty(s1) || TextUtils.isEmpty(s))
            return false;
        i = s.indexOf("*");
        if(i == -1)
            return false;
        String s3 = s.substring(0, i);
        s = s.substring(i + 1);
        if(TextUtils.isEmpty(s3) || TextUtils.isEmpty(s))
            return false;
        if(!s1.startsWith(s3))
            return false;
        if(!s1.endsWith(s))
            return false;
        if(true) goto _L1; else goto _L5
_L5:
    }

    private static int getExpressType(String s)
    {
        int i = 0;
        if(!TextUtils.isEmpty(s)) goto _L2; else goto _L1
_L1:
        i = 0;
_L4:
        return i;
_L2:
        if(s.equals("*"))
            i = 2;
        else
        if(s.endsWith("*"))
            i = 1;
        else
        if(s.contains("*"))
            i = 3;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static int getHashCode(String s, String s1, String s2, String s3)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(s);
        stringbuffer.append(s1);
        stringbuffer.append(s2);
        stringbuffer.append(s3);
        return stringbuffer.toString().hashCode();
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(int i)
    {
        boolean flag = false;
        if(mWakeType != 16 || i == 0)
        {
            Slog.w(TAG, (new StringBuilder()).append("MIUILOG-WAKEPATH equals: Invalid parameter!! mWakeType=").append(mWakeType).append(" hashCode=").append(i).toString());
            return false;
        }
        if(mHashCode == i)
            flag = true;
        return flag;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == null)
            return false;
        boolean flag1;
        try
        {
            obj = (WakePathRuleInfo)obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            ((ClassCastException) (obj)).printStackTrace();
            return false;
        }
        flag1 = flag;
        if(TextUtils.equals(mActionExpress, ((WakePathRuleInfo) (obj)).mActionExpress))
        {
            flag1 = flag;
            if(TextUtils.equals(mClassNameExpress, ((WakePathRuleInfo) (obj)).mClassNameExpress))
            {
                flag1 = flag;
                if(TextUtils.equals(mCallerExpress, ((WakePathRuleInfo) (obj)).mCallerExpress))
                {
                    flag1 = flag;
                    if(TextUtils.equals(mCalleeExpress, ((WakePathRuleInfo) (obj)).mCalleeExpress))
                    {
                        flag1 = flag;
                        if(mWakeType == ((WakePathRuleInfo) (obj)).mWakeType)
                            flag1 = true;
                    }
                }
            }
        }
        return flag1;
    }

    public boolean equals(String s, String s1, String s2, String s3, int i)
    {
        if((mWakeType & i) == 0)
            return false;
        if(!expressCompare(mActionExpress, mActionExpressType, s))
            return false;
        if(!expressCompare(mClassNameExpress, mClassNameExpressType, s1))
            return false;
        if(!expressCompare(mCallerExpress, mCallerExpressType, s2))
            return false;
        return expressCompare(mCalleeExpress, mCalleeExpressType, s3);
    }

    public String getCalleeExpress()
    {
        return mCalleeExpress;
    }

    public String getCallerExpress()
    {
        return mCallerExpress;
    }

    public int getUserSettings()
    {
        return mUserSettings;
    }

    public String toString()
    {
        return (new StringBuilder()).append("WakePathRuleInfo: mActionExpress=").append(mActionExpress).append(" mClassNameExpress=").append(mClassNameExpress).append(" mCallerExpress=").append(mCallerExpress).append(" mCalleeExpress= ").append(mCalleeExpress).append(" mWakeType=").append(mWakeType).append(" userSettings=").append(mUserSettings).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mActionExpress);
        parcel.writeInt(mActionExpressType);
        parcel.writeString(mClassNameExpress);
        parcel.writeInt(mClassNameExpressType);
        parcel.writeString(mCallerExpress);
        parcel.writeInt(mCallerExpressType);
        parcel.writeString(mCalleeExpress);
        parcel.writeInt(mCalleeExpressType);
        parcel.writeInt(mWakeType);
        parcel.writeInt(mUserSettings);
        parcel.writeInt(mHashCode);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WakePathRuleInfo createFromParcel(Parcel parcel)
        {
            return new WakePathRuleInfo(parcel, null);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public WakePathRuleInfo[] newArray(int i)
        {
            return new WakePathRuleInfo[i];
        }

    }
;
    private static final int EXPRESS_TYPE_WILDCARD_ALL = 2;
    private static final int EXPRESS_TYPE_WILDCARD_END = 1;
    private static final int EXPRESS_TYPE_WILDCARD_NONE = 0;
    private static final int EXPRESS_TYPE_WILDCARD_OTHER = 3;
    private static final String EXPRESS_WILDCARD = "*";
    private static final String TAG = miui/security/WakePathRuleInfo.getName();
    public static final int WAKE_TYPE_ALLOW_START_ACTIVITY = 17;
    public static final int WAKE_TYPE_CALL_LIST = 16;
    public static final int WAKE_TYPE_GET_CONTENT_PROVIDER = 4;
    public static final int WAKE_TYPE_SEND_BROADCAST = 2;
    public static final int WAKE_TYPE_START_ACTIVITY = 1;
    public static final int WAKE_TYPE_START_SERVICE = 8;
    public String mActionExpress;
    private int mActionExpressType;
    public String mCalleeExpress;
    private int mCalleeExpressType;
    public String mCallerExpress;
    private int mCallerExpressType;
    public String mClassNameExpress;
    private int mClassNameExpressType;
    public int mHashCode;
    public int mUserSettings;
    public int mWakeType;

}
