// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.os.Parcel;
import android.os.Parcelable;

public class OperatorInfo
    implements Parcelable
{
    public static final class State extends Enum
    {

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(com/android/internal/telephony/OperatorInfo$State, s);
        }

        public static State[] values()
        {
            return $VALUES;
        }

        private static final State $VALUES[];
        public static final State AVAILABLE;
        public static final State CURRENT;
        public static final State FORBIDDEN;
        public static final State UNKNOWN;

        static 
        {
            UNKNOWN = new State("UNKNOWN", 0);
            AVAILABLE = new State("AVAILABLE", 1);
            CURRENT = new State("CURRENT", 2);
            FORBIDDEN = new State("FORBIDDEN", 3);
            $VALUES = (new State[] {
                UNKNOWN, AVAILABLE, CURRENT, FORBIDDEN
            });
        }

        private State(String s, int i)
        {
            super(s, i);
        }
    }


    public OperatorInfo(String s, String s1, String s2)
    {
        this(s, s1, s2, State.UNKNOWN);
    }

    OperatorInfo(String s, String s1, String s2, State state)
    {
        mState = State.UNKNOWN;
        mOperatorAlphaLong = s;
        mOperatorAlphaShort = s1;
        mOperatorNumeric = s2;
        mRadioTech = "";
        if(s2 != null)
        {
            s = s2.split("\\+");
            mOperatorNumeric = s[0];
            if(s.length > 1)
                mRadioTech = s[1];
        }
        mState = state;
    }

    public OperatorInfo(String s, String s1, String s2, String s3)
    {
        this(s, s1, s2, rilStateToState(s3));
    }

    private static State rilStateToState(String s)
    {
        if(s.equals("unknown"))
            return State.UNKNOWN;
        if(s.equals("available"))
            return State.AVAILABLE;
        if(s.equals("current"))
            return State.CURRENT;
        if(s.equals("forbidden"))
            return State.FORBIDDEN;
        else
            throw new RuntimeException((new StringBuilder()).append("RIL impl error: Invalid network state '").append(s).append("'").toString());
    }

    public int describeContents()
    {
        return 0;
    }

    public String getOperatorAlphaLong()
    {
        return mOperatorAlphaLong;
    }

    public String getOperatorAlphaShort()
    {
        return mOperatorAlphaShort;
    }

    public String getOperatorNumeric()
    {
        return mOperatorNumeric;
    }

    public String getRadioTech()
    {
        return mRadioTech;
    }

    public State getState()
    {
        return mState;
    }

    public String toString()
    {
        return (new StringBuilder()).append("OperatorInfo ").append(mOperatorAlphaLong).append("/").append(mOperatorAlphaShort).append("/").append(mOperatorNumeric).append("/").append(mRadioTech).append("/").append(mState).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mOperatorAlphaLong);
        parcel.writeString(mOperatorAlphaShort);
        parcel.writeString(mOperatorNumeric);
        parcel.writeString((new StringBuilder()).append(mOperatorNumeric).append("+").append(mRadioTech).toString());
        parcel.writeSerializable(mState);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public OperatorInfo createFromParcel(Parcel parcel)
        {
            return new OperatorInfo(parcel.readString(), parcel.readString(), parcel.readString(), (State)parcel.readSerializable());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public OperatorInfo[] newArray(int i)
        {
            return new OperatorInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private String mOperatorAlphaLong;
    private String mOperatorAlphaShort;
    private String mOperatorNumeric;
    private String mRadioTech;
    private State mState;

}
