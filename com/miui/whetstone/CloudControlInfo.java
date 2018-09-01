// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

public class CloudControlInfo
    implements Parcelable
{

    public CloudControlInfo(Parcel parcel)
    {
        boolean flag = false;
        super();
        log((new StringBuilder()).append("Size of HttpRequest parcel:").append(parcel.dataSize()).toString());
        int i = parcel.readInt();
        if((i & 1) != 0)
            mPkgName = parcel.readString();
        if((i & 2) != 0)
            mClassName = parcel.readString();
        if((i & 4) != 0)
            mUrl = parcel.readString();
        if((i & 8) != 0)
            mParams = parcel.readString();
        if((i & 0x10) != 0)
            mHttpMethod = parcel.readString();
        if((i & 0x20) != 0)
            mTriggerDelayAtMin = parcel.readInt();
        if((i & 0x40) != 0)
            mIntervalAtMin = parcel.readInt();
        if((i & 0x80) != 0)
            mTimeout = parcel.readInt();
        if((i & 0x100) != 0)
        {
            if(parcel.readByte() != 0)
                flag = true;
            mIsIntentWithResponse = flag;
        }
        if((i & 0x200) != 0)
            mConfig = parcel.readString();
    }

    public CloudControlInfo(CloudControlInfo cloudcontrolinfo)
    {
        copyFrom(cloudcontrolinfo);
    }

    public CloudControlInfo(String s, String s1, String s2, String s3, String s4)
    {
        initialize(s, s1, s2, s3, s4, 0, 0, 0, false, null);
    }

    public CloudControlInfo(String s, String s1, String s2, String s3, String s4, int i, int j, 
            int k, boolean flag, String s5)
    {
        initialize(s, s1, s2, s3, s4, i, j, k, flag, s5);
    }

    private int buildFlagValue()
    {
        int i = 0;
        if(mPkgName != null)
            i = 1;
        int j = i;
        if(mClassName != null)
            j = i | 2;
        i = j;
        if(mUrl != null)
            i = j | 4;
        j = i;
        if(mParams != null)
            j = i | 8;
        i = j;
        if(mHttpMethod != null)
            i = j | 0x10;
        i = i | 0x20 | 0x40 | 0x80 | 0x100;
        j = i;
        if(mConfig != null)
            j = i | 0x200;
        return j;
    }

    private static void log(String s)
    {
        Log.w("CloudControlInfo", s);
    }

    protected void copyFrom(CloudControlInfo cloudcontrolinfo)
    {
        mPkgName = cloudcontrolinfo.mPkgName;
        mClassName = cloudcontrolinfo.mClassName;
        mUrl = cloudcontrolinfo.mUrl;
        mParams = cloudcontrolinfo.mParams;
        mHttpMethod = cloudcontrolinfo.mHttpMethod;
        mTriggerDelayAtMin = cloudcontrolinfo.mTriggerDelayAtMin;
        mIntervalAtMin = cloudcontrolinfo.mIntervalAtMin;
        mTimeout = cloudcontrolinfo.mTimeout;
        mIsIntentWithResponse = cloudcontrolinfo.mIsIntentWithResponse;
        mConfig = cloudcontrolinfo.mConfig;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        CloudControlInfo cloudcontrolinfo;
        try
        {
            cloudcontrolinfo = (CloudControlInfo)obj;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        if(obj == null)
            return false;
        boolean flag1 = flag;
        if(mPkgName.equals(cloudcontrolinfo.mPkgName))
        {
            flag1 = flag;
            if(mClassName.equals(cloudcontrolinfo.mClassName))
            {
                flag1 = flag;
                if(mUrl.equals(cloudcontrolinfo.mUrl))
                {
                    flag1 = flag;
                    if(mParams.equals(cloudcontrolinfo.mParams))
                    {
                        flag1 = flag;
                        if(mHttpMethod.equals(cloudcontrolinfo.mHttpMethod))
                        {
                            flag1 = flag;
                            if(mTriggerDelayAtMin == cloudcontrolinfo.mTriggerDelayAtMin)
                            {
                                flag1 = flag;
                                if(mIntervalAtMin == cloudcontrolinfo.mIntervalAtMin)
                                {
                                    flag1 = flag;
                                    if(mTimeout == cloudcontrolinfo.mTimeout)
                                    {
                                        flag1 = flag;
                                        if(mIsIntentWithResponse == cloudcontrolinfo.mIsIntentWithResponse)
                                            flag1 = mConfig.equals(cloudcontrolinfo.mConfig);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag1;
    }

    public String getComponentToShortString()
    {
        return (new StringBuilder()).append("{").append(mPkgName).append("/").append(mClassName).append("}").toString();
    }

    public void initialize(String s, String s1, String s2, String s3, String s4, int i, int j, 
            int k, boolean flag, String s5)
    {
        mPkgName = s;
        mClassName = s1;
        mUrl = s2;
        mParams = s3;
        mHttpMethod = s4;
        mTriggerDelayAtMin = i;
        mIntervalAtMin = j;
        mTimeout = k;
        mIsIntentWithResponse = flag;
        mConfig = s5;
    }

    public boolean isValid()
    {
        boolean flag;
        if(!TextUtils.isEmpty(mPkgName) && TextUtils.isEmpty(mClassName) ^ true && TextUtils.isEmpty(mUrl) ^ true && mParams != null)
            flag = TextUtils.isEmpty(mHttpMethod) ^ true;
        else
            flag = false;
        return flag;
    }

    public String toString()
    {
        return (new StringBuilder()).append("CloudControlInfo: ").append(mPkgName).append(" ").append(mClassName).append(" ").append(mUrl).append(" ").append(mParams).append(" ").append(mHttpMethod).append(" ").append(mTriggerDelayAtMin).append(" ").append(mIntervalAtMin).append(" ").append(mTimeout).append(" ").append(mIsIntentWithResponse).append(" ").append(mConfig).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(buildFlagValue());
        if(mPkgName != null)
            parcel.writeString(mPkgName);
        if(mClassName != null)
            parcel.writeString(mClassName);
        if(mUrl != null)
            parcel.writeString(mUrl);
        if(mParams != null)
            parcel.writeString(mParams);
        if(mHttpMethod != null)
            parcel.writeString(mHttpMethod);
        parcel.writeInt(mTriggerDelayAtMin);
        parcel.writeInt(mIntervalAtMin);
        parcel.writeInt(mTimeout);
        if(mIsIntentWithResponse)
            i = 1;
        else
            i = 0;
        parcel.writeByte((byte)i);
        if(mConfig != null)
            parcel.writeString(mConfig);
    }

    public static final int CONFIG_MASK_APPEND_TOKEN_TO_URL = 1;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CloudControlInfo createFromParcel(Parcel parcel)
        {
            return new CloudControlInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CloudControlInfo[] newArray(int i)
        {
            return new CloudControlInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DBG = true;
    private static final int HTTP_MASK_CLASS = 2;
    private static final int HTTP_MASK_CONFIG = 512;
    private static final int HTTP_MASK_HTTP_METHOD = 16;
    private static final int HTTP_MASK_INTENT_WITH_RESPONSE = 256;
    private static final int HTTP_MASK_INTERVAL_AT_MIN = 64;
    private static final int HTTP_MASK_PACKAGE = 1;
    private static final int HTTP_MASK_PARAMS = 8;
    private static final int HTTP_MASK_TIMEOUT = 128;
    private static final int HTTP_MASK_TRIGGER_DELAY_AT_MIN = 32;
    private static final int HTTP_MASK_URL = 4;
    private static final String LOG_TAG = "CloudControlInfo";
    public String mClassName;
    public String mConfig;
    public String mHttpMethod;
    public int mIntervalAtMin;
    public boolean mIsIntentWithResponse;
    public String mParams;
    public String mPkgName;
    public int mTimeout;
    public int mTriggerDelayAtMin;
    public String mUrl;

}
