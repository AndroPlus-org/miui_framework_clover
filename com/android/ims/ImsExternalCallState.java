// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.telecom.Log;
import android.telephony.Rlog;

public class ImsExternalCallState
    implements Parcelable
{

    public ImsExternalCallState()
    {
    }

    public ImsExternalCallState(int i, Uri uri, boolean flag, int j, int k, boolean flag1)
    {
        mCallId = i;
        mAddress = uri;
        mIsPullable = flag;
        mCallState = j;
        mCallType = k;
        mIsHeld = flag1;
        Rlog.d("ImsExternalCallState", (new StringBuilder()).append("ImsExternalCallState = ").append(this).toString());
    }

    public ImsExternalCallState(Parcel parcel)
    {
        boolean flag = true;
        super();
        mCallId = parcel.readInt();
        mAddress = (Uri)parcel.readParcelable(com/android/ims/ImsExternalCallState.getClassLoader());
        boolean flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mIsPullable = flag1;
        mCallState = parcel.readInt();
        mCallType = parcel.readInt();
        if(parcel.readInt() != 0)
            flag1 = flag;
        else
            flag1 = false;
        mIsHeld = flag1;
        Rlog.d("ImsExternalCallState", (new StringBuilder()).append("ImsExternalCallState const = ").append(this).toString());
    }

    public int describeContents()
    {
        return 0;
    }

    public Uri getAddress()
    {
        return mAddress;
    }

    public int getCallId()
    {
        return mCallId;
    }

    public int getCallState()
    {
        return mCallState;
    }

    public int getCallType()
    {
        return mCallType;
    }

    public boolean isCallHeld()
    {
        return mIsHeld;
    }

    public boolean isCallPullable()
    {
        return mIsPullable;
    }

    public String toString()
    {
        return (new StringBuilder()).append("ImsExternalCallState { mCallId = ").append(mCallId).append(", mAddress = ").append(Log.pii(mAddress)).append(", mIsPullable = ").append(mIsPullable).append(", mCallState = ").append(mCallState).append(", mCallType = ").append(mCallType).append(", mIsHeld = ").append(mIsHeld).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(mCallId);
        parcel.writeParcelable(mAddress, 0);
        if(mIsPullable)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mCallState);
        parcel.writeInt(mCallType);
        if(mIsHeld)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        Rlog.d("ImsExternalCallState", (new StringBuilder()).append("ImsExternalCallState writeToParcel = ").append(parcel.toString()).toString());
    }

    public static final int CALL_STATE_CONFIRMED = 1;
    public static final int CALL_STATE_TERMINATED = 2;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ImsExternalCallState createFromParcel(Parcel parcel)
        {
            return new ImsExternalCallState(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ImsExternalCallState[] newArray(int i)
        {
            return new ImsExternalCallState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "ImsExternalCallState";
    private Uri mAddress;
    private int mCallId;
    private int mCallState;
    private int mCallType;
    private boolean mIsHeld;
    private boolean mIsPullable;

}
