// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.component;

import android.content.Intent;
import android.os.*;
import android.util.Slog;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.*;

// Referenced classes of package com.miui.whetstone.component:
//            WtComponentManager, PackageComponentPolicy

public class ComponentPolicyInfo
    implements Parcelable
{

    protected ComponentPolicyInfo(Parcel parcel)
    {
        boolean flag = true;
        super();
        mCheckIntents = new ArrayList();
        mCommonPolicyExceptPackage = new ArrayList();
        mPkg = parcel.readString();
        mUserId = parcel.readInt();
        mStartType = parcel.readInt();
        mSetter = parcel.readString();
        boolean flag1;
        if(parcel.readByte() != 0)
            flag1 = true;
        else
            flag1 = false;
        mDefaultResult = flag1;
        mMatchMethod = parcel.readInt();
        mCheckFlag = parcel.readInt();
        mPolicyModuleName = parcel.readString();
        mAvalidType = parcel.readInt();
        mCheckMethod = parcel.readInt();
        mProcessName = parcel.readString();
        if(parcel.readByte() != 0)
            flag1 = true;
        else
            flag1 = false;
        mEnable = flag1;
        if(parcel.readByte() != 0)
            flag1 = true;
        else
            flag1 = false;
        mIsRemovedWhenDisable = flag1;
        if(parcel.readByte() != 0)
            flag1 = flag;
        else
            flag1 = false;
        mIsCommonPolicy = flag1;
        mId = parcel.readInt();
        mTimeOutForBroadCast = parcel.readInt();
        mCheckIntents = parcel.createStringArrayList();
        mCommonPolicyExceptPackage = parcel.createStringArrayList();
    }

    public ComponentPolicyInfo(String s, int i, int j, int k)
    {
        mCheckIntents = new ArrayList();
        mCommonPolicyExceptPackage = new ArrayList();
        mPkg = s;
        mUserId = i;
        mStartType = j;
        mDefaultResult = true;
        mMatchMethod = 2;
        mSetter = "Unknown";
        mCheckFlag = k;
        mPolicyModuleName = "Unknown";
        mAvalidType = 1;
        mCheckMethod = 1;
        mProcessName = "";
        mEnable = true;
        mIsRemovedWhenDisable = false;
        mIsCommonPolicy = false;
        mId = -1;
        mTimeOutForBroadCast = 0;
    }

    public ComponentPolicyInfo(String s, int i, int j, boolean flag, int k, int l, int i1, 
            int j1, String s1, String s2, String s3)
    {
        mCheckIntents = new ArrayList();
        mCommonPolicyExceptPackage = new ArrayList();
        mPkg = s;
        mUserId = i;
        mStartType = j;
        mDefaultResult = flag;
        mMatchMethod = l;
        mSetter = s3;
        mCheckFlag = k;
        mPolicyModuleName = s2;
        mAvalidType = i1;
        mCheckMethod = j1;
        s = s1;
        if(s1 == null)
            s = "";
        mProcessName = s;
        mEnable = true;
        mIsRemovedWhenDisable = false;
        mIsCommonPolicy = false;
        mId = -1;
        mTimeOutForBroadCast = 0;
    }

    private boolean checkFlagResult(int i, int j)
    {
        boolean flag;
        boolean flag1;
        flag = mDefaultResult;
        flag1 = flag;
        mMatchMethod;
        JVM INSTR tableswitch 1 8: default 60
    //                   1 140
    //                   2 156
    //                   3 63
    //                   4 173
    //                   5 63
    //                   6 63
    //                   7 63
    //                   8 189;
           goto _L1 _L2 _L3 _L4 _L5 _L4 _L4 _L4 _L6
_L1:
        flag1 = flag;
_L4:
        if(DEBUG)
            Slog.i("WhetstoneComponentPolicy", (new StringBuilder()).append("check flag result: ").append(flag1).append(" matchFlag: ").append(i).append(" checkFlag:").append(j).append(" MatchMethod:").append(mMatchMethod).append(" DefaultResult:").append(mDefaultResult).toString());
        return flag1;
_L2:
        flag1 = flag;
        if(j != i)
            flag1 = flag ^ true;
        continue; /* Loop/switch isn't completed */
_L3:
        flag1 = flag;
        if((j & i) == 0)
            flag1 = flag ^ true;
        continue; /* Loop/switch isn't completed */
_L5:
        flag1 = flag;
        if(j == i)
            flag1 = flag ^ true;
        continue; /* Loop/switch isn't completed */
_L6:
        flag1 = flag;
        if((j & i) != 0)
            flag1 = flag ^ true;
        if(true) goto _L4; else goto _L7
_L7:
    }

    public boolean checkPolicyResult(String s, int i, int j, Intent intent, String s1)
    {
        Object obj;
        boolean flag1;
        int i1;
        int j1;
        int k1;
        if(DEBUG)
            Slog.i("WhetstoneComponentPolicy", (new StringBuilder()).append("Check Policy ID:").append(mId).toString());
        if(!mEnable)
        {
            if(DEBUG)
                Slog.i("WhetstoneComponentPolicy", (new StringBuilder()).append("Policy ID:").append(mId).append(" is not enable").toString());
            return true;
        }
        if(!mIsCommonPolicy && mUserId != i && mUserId != -1)
        {
            if(DEBUG)
                Slog.i("WhetstoneComponentPolicy", (new StringBuilder()).append("Check Policy ID:").append(mId).append(" not match").toString());
            return true;
        }
        obj = WtComponentManager.getInstance();
        if(mProcessName.length() > 0 && mProcessName.equals(s1) ^ true)
        {
            if(DEBUG)
                Slog.i("WhetstoneComponentPolicy", (new StringBuilder()).append("Check Policy ID:").append(mId).append(" process check error").toString());
            return true;
        }
        if(mCheckMethod == 2)
        {
            if(DEBUG)
                Slog.i("WhetstoneComponentPolicy", (new StringBuilder()).append("Check Policy ID:").append(mId).append(" allow policy").toString());
            return true;
        }
        int k = mCheckFlag;
        int l = j;
        boolean flag = false;
        flag1 = false;
        boolean flag2 = false;
        i = ((flag2) ? 1 : 0);
        i1 = k;
        j1 = l;
        k1 = ((flag) ? 1 : 0);
        if((0x8000 & j) != 0)
        {
            k ^= 0x8000;
            i = ((flag2) ? 1 : 0);
            i1 = k;
            j1 = l;
            k1 = ((flag) ? 1 : 0);
            if(mStartType == 4)
            {
                j1 = j ^ 0x8000;
                k1 = 32768;
                i = 1;
                i1 = k;
            }
        }
        if(i == 0) goto _L2; else goto _L1
_L1:
        if(intent == null || mCheckIntents.contains(intent.getAction()) ^ true)
            return true;
        if(DEBUG)
            Slog.i("WhetstoneComponentPolicy", (new StringBuilder()).append("Check Policy ID:").append(mId).append(" Intent:").append(intent.getAction()).toString());
        i = ((flag1) ? 1 : 0);
        if(obj == null) goto _L4; else goto _L3
_L3:
        obj = ((WtComponentManager) (obj)).getPackageComponentPolicy(s);
        i = ((flag1) ? 1 : 0);
        if(obj == null) goto _L4; else goto _L5
_L5:
        long l1;
        if(((PackageComponentPolicy) (obj)).isIntentWhiteList(intent))
        {
            if(DEBUG)
                Slog.i("WhetstoneComponentPolicy", (new StringBuilder()).append("Check Policy ID:").append(mId).append(" WhiteList Intent:").append(intent.getAction()).append(" for ").append(s).toString());
            return true;
        }
        l1 = ((PackageComponentPolicy) (obj)).getPackageStartTimeWithType(2);
        if(DEBUG)
            Slog.i("WhetstoneComponentPolicy", (new StringBuilder()).append("Pkg:").append(s).append(" Process:").append(s1).append(" broadcast start duration:").append(SystemClock.elapsedRealtime() - l1).toString());
        if(l1 <= 0L) goto _L7; else goto _L6
_L6:
        i = ((flag1) ? 1 : 0);
        if(SystemClock.elapsedRealtime() - l1 > (long)(mTimeOutForBroadCast * 60) * 1000L)
        {
            j = 32768;
            i = j;
            if(DEBUG)
            {
                Slog.i("WhetstoneComponentPolicy", (new StringBuilder()).append("Pkg:").append(s).append(" Process:").append(s1).append(" broadcast start time is enough").toString());
                i = j;
            }
        }
_L4:
        if(mStartType == 4)
            Slog.i("WhetstoneComponentPolicy", (new StringBuilder()).append("fontFlag:").append(i1).append(" inputfontFlag:").append(j1).append(" postFlag:").append(k1).append(" inputPostFlag:").append(i).toString());
        boolean flag3 = checkFlagResult(i1, j1) | checkFlagResult(k1, i);
        if(!flag3)
            Slog.e("WhetstoneComponentPolicy", (new StringBuilder()).append("Process: ").append(s1).append("start is deny by Whetstone, PolicyId: ").append(mId).toString());
        return flag3;
_L7:
        i = ((flag1) ? 1 : 0);
        if(l1 == 0L)
            i = 32768;
        continue; /* Loop/switch isn't completed */
_L2:
        i = 32768;
        if(true) goto _L4; else goto _L8
_L8:
    }

    public boolean checkPolicyResultWithStartType(String s, int i, int j, int k, Intent intent, String s1)
    {
        boolean flag;
        if(j == mStartType)
            flag = checkPolicyResult(s, i, k, intent, s1);
        else
            flag = true;
        return flag;
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.println(" ------ Base Info ------");
        printwriter.println(toString());
        if(!mCheckIntents.isEmpty())
        {
            printwriter.println(" ------ Intents Info ------");
            for(filedescriptor = mCheckIntents.iterator(); filedescriptor.hasNext(); printwriter.println((String)filedescriptor.next()));
        }
        if(isCommonPolicy() && mCommonPolicyExceptPackage.isEmpty() ^ true)
        {
            printwriter.println(" ------ Common Exception Packages ------");
            for(filedescriptor = mCommonPolicyExceptPackage.iterator(); filedescriptor.hasNext(); printwriter.println((String)filedescriptor.next()));
        }
    }

    public void getAllowIntents(List list)
    {
        Object obj = mMutex;
        obj;
        JVM INSTR monitorenter ;
        ArrayList arraylist = JVM INSTR new #70  <Class ArrayList>;
        arraylist.ArrayList(list);
        mCheckIntents = arraylist;
        obj;
        JVM INSTR monitorexit ;
        return;
        list;
        throw list;
    }

    public int getAvalidType()
    {
        return mAvalidType;
    }

    public int getCheckMethod()
    {
        return mCheckMethod;
    }

    public List getCommonPolicyExceptPackage()
    {
        return mCommonPolicyExceptPackage;
    }

    public boolean getDefaultResult()
    {
        return mDefaultResult;
    }

    public boolean getEnableStatus()
    {
        return mEnable;
    }

    public int getId()
    {
        return mId;
    }

    public int getMatchMethod()
    {
        return mMatchMethod;
    }

    public String getPkg()
    {
        return mPkg;
    }

    public String getPolicyModuleName()
    {
        return mPolicyModuleName;
    }

    public String getProcessName()
    {
        return mProcessName;
    }

    public String getSetter()
    {
        return mSetter;
    }

    public int getStartType()
    {
        return mStartType;
    }

    public int getTimeOutForBroadCast()
    {
        return mTimeOutForBroadCast;
    }

    public int getUserId()
    {
        return mUserId;
    }

    public int getmCheckFlag()
    {
        return mCheckFlag;
    }

    public boolean isCommonPolicy()
    {
        return mIsCommonPolicy;
    }

    public boolean isCommonPolicyIgnore(String s, String s1)
    {
        List list = mCommonPolicyExceptPackage;
        list;
        JVM INSTR monitorenter ;
        boolean flag;
        if(mCommonPolicyExceptPackage.contains(s))
            break MISSING_BLOCK_LABEL_37;
        flag = mCommonPolicyExceptPackage.contains(s1);
_L1:
        list;
        JVM INSTR monitorexit ;
        return flag;
        flag = true;
          goto _L1
        s;
        throw s;
    }

    public boolean isEnable()
    {
        return mEnable;
    }

    public boolean isRemovedWhenPolicyDisbale()
    {
        return mIsRemovedWhenDisable;
    }

    public void setAllowIntents(List list)
    {
        Object obj = mMutex;
        obj;
        JVM INSTR monitorenter ;
        mCheckIntents.addAll(list);
        obj;
        JVM INSTR monitorexit ;
        return;
        list;
        throw list;
    }

    public void setAvalidType(int i)
    {
        mAvalidType = i;
    }

    public void setCheckFlag(int i)
    {
        mCheckFlag = i;
    }

    public void setCheckMethod(int i)
    {
        mCheckMethod = i;
    }

    public void setCommonPolicy(boolean flag)
    {
        mIsCommonPolicy = flag;
    }

    public void setCommonPolicyExceptPackage(List list)
    {
        mCommonPolicyExceptPackage = list;
    }

    public void setDefaultResult(boolean flag)
    {
        mDefaultResult = flag;
    }

    public void setEnable(boolean flag)
    {
        mEnable = flag;
    }

    public void setEnableState(boolean flag)
    {
        mEnable = flag;
    }

    public void setId(int i)
    {
        mId = i;
    }

    public void setMatchMethod(int i)
    {
        mMatchMethod = i;
    }

    public void setPkg(String s)
    {
        mPkg = s;
    }

    public void setPolicyModuleName(String s)
    {
        mPolicyModuleName = s;
    }

    public void setProcessName(String s)
    {
        String s1 = s;
        if(s == null)
            s1 = "";
        mProcessName = s1;
    }

    public void setRemovedWhenPolicyDisbale(boolean flag)
    {
        mIsRemovedWhenDisable = flag;
    }

    public void setSetter(String s)
    {
        mSetter = s;
    }

    public void setStartType(int i)
    {
        mStartType = i;
    }

    public void setTimeOutForBroadCast(int i)
    {
        mTimeOutForBroadCast = i;
    }

    public void setUserId(int i)
    {
        mUserId = i;
    }

    public String toString()
    {
        return (new StringBuilder()).append("{ Package: ").append(mPkg).append(" UserId: ").append(mUserId).append(" Enable: ").append(mEnable).append(" id: ").append(mId).append(" StartType: ").append(mStartType).append(" DefaultResult: ").append(mDefaultResult).append(" CheckFlag: ").append(mCheckFlag).append(" ProcessName: ").append(mProcessName).append(" Setter: ").append(mSetter).append(" PolicyModuleName: ").append(mPolicyModuleName).append(" AvalidType: ").append(mAvalidType).append(" CheckMethod: ").append(mCheckMethod).append(" MatchMethod: ").append(mMatchMethod).append(" isRemovedWhenDisable: ").append(mIsRemovedWhenDisable).append(" isCommonPolicy: ").append(mIsCommonPolicy).append(" timeOutForBroadCast: ").append(mTimeOutForBroadCast).append(" }").toString();
    }

    public void updateCommonPolicyExceptPackage(String s, int i, boolean flag)
    {
        String s1 = (new StringBuilder()).append(i).append("#").append(s).toString();
        s = mCommonPolicyExceptPackage;
        s;
        JVM INSTR monitorenter ;
        if(!flag)
            break MISSING_BLOCK_LABEL_66;
        if(!mCommonPolicyExceptPackage.contains(s1))
            mCommonPolicyExceptPackage.add(s1);
_L1:
        s;
        JVM INSTR monitorexit ;
        return;
        mCommonPolicyExceptPackage.remove(s1);
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public void updatePolicyEnableStatus(String s, int i, int j)
    {
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = flag;
        if(mIsCommonPolicy) goto _L2; else goto _L1
_L1:
        flag1 = flag;
        if(!s.equals(mPkg)) goto _L2; else goto _L3
_L3:
        if(mUserId == -1) goto _L5; else goto _L4
_L4:
        flag1 = flag;
        if(mUserId != i) goto _L2; else goto _L5
_L5:
        flag1 = true;
          goto _L2
_L8:
        j;
        JVM INSTR tableswitch 1 1: default 80
    //                   1 150;
           goto _L6 _L7
_L6:
        if(DEBUG)
            Slog.i("WhetstoneComponentPolicy", (new StringBuilder()).append("Enable Check Policy ID: ").append(mId).append(" AvalidType:").append(mAvalidType).toString());
        return;
_L2:
        if(mAvalidType != 1 && flag1 && mAvalidType == 2)
            mEnable = false;
          goto _L8
_L7:
        if(mAvalidType == 4 || mAvalidType == 3 && flag1)
            mEnable = false;
          goto _L6
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeString(mPkg);
        parcel.writeInt(mUserId);
        parcel.writeInt(mStartType);
        parcel.writeString(mSetter);
        if(mDefaultResult)
            i = 1;
        else
            i = 0;
        parcel.writeByte((byte)i);
        parcel.writeInt(mMatchMethod);
        parcel.writeInt(mCheckFlag);
        parcel.writeString(mPolicyModuleName);
        parcel.writeInt(mAvalidType);
        parcel.writeInt(mCheckMethod);
        parcel.writeString(mProcessName);
        if(mEnable)
            i = 1;
        else
            i = 0;
        parcel.writeByte((byte)i);
        if(mIsRemovedWhenDisable)
            i = 1;
        else
            i = 0;
        parcel.writeByte((byte)i);
        if(mIsCommonPolicy)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeByte((byte)i);
        parcel.writeInt(mId);
        parcel.writeInt(mTimeOutForBroadCast);
        parcel.writeStringList(mCheckIntents);
        parcel.writeStringList(mCommonPolicyExceptPackage);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ComponentPolicyInfo createFromParcel(Parcel parcel)
        {
            return new ComponentPolicyInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ComponentPolicyInfo[] newArray(int i)
        {
            return new ComponentPolicyInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DEBUG = WtComponentManager.isDebug();
    public static final int POLICY_VALID_BEFORE_ANY_ACTIVITY_START = 4;
    public static final int POLICY_VALID_BEFORE_SELF_ACTIVITY_START = 3;
    public static final int POLICY_VALID_BEFORE_SELF_PROCESS_START = 2;
    public static final int POLICY_VALID_PERSIST = 1;
    private static final String TAG = "WhetstoneComponentPolicy";
    private static final Object mMutex = new Object();
    private int mAvalidType;
    private int mCheckFlag;
    private List mCheckIntents;
    private int mCheckMethod;
    private List mCommonPolicyExceptPackage;
    private boolean mDefaultResult;
    private boolean mEnable;
    private int mId;
    private boolean mIsCommonPolicy;
    private boolean mIsRemovedWhenDisable;
    private int mMatchMethod;
    private String mPkg;
    private String mPolicyModuleName;
    private String mProcessName;
    private String mSetter;
    private int mStartType;
    private int mTimeOutForBroadCast;
    private int mUserId;

}
