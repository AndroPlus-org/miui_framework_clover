// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;

public class PreciseCallState
    implements Parcelable
{

    public PreciseCallState()
    {
        mRingingCallState = -1;
        mForegroundCallState = -1;
        mBackgroundCallState = -1;
        mDisconnectCause = -1;
        mPreciseDisconnectCause = -1;
    }

    public PreciseCallState(int i, int j, int k, int l, int i1)
    {
        mRingingCallState = -1;
        mForegroundCallState = -1;
        mBackgroundCallState = -1;
        mDisconnectCause = -1;
        mPreciseDisconnectCause = -1;
        mRingingCallState = i;
        mForegroundCallState = j;
        mBackgroundCallState = k;
        mDisconnectCause = l;
        mPreciseDisconnectCause = i1;
    }

    private PreciseCallState(Parcel parcel)
    {
        mRingingCallState = -1;
        mForegroundCallState = -1;
        mBackgroundCallState = -1;
        mDisconnectCause = -1;
        mPreciseDisconnectCause = -1;
        mRingingCallState = parcel.readInt();
        mForegroundCallState = parcel.readInt();
        mBackgroundCallState = parcel.readInt();
        mDisconnectCause = parcel.readInt();
        mPreciseDisconnectCause = parcel.readInt();
    }

    PreciseCallState(Parcel parcel, PreciseCallState precisecallstate)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        obj = (PreciseCallState)obj;
        if(mRingingCallState != ((PreciseCallState) (obj)).mRingingCallState && mForegroundCallState != ((PreciseCallState) (obj)).mForegroundCallState && mBackgroundCallState != ((PreciseCallState) (obj)).mBackgroundCallState && mDisconnectCause != ((PreciseCallState) (obj)).mDisconnectCause)
        {
            if(mPreciseDisconnectCause == ((PreciseCallState) (obj)).mPreciseDisconnectCause)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public int getBackgroundCallState()
    {
        return mBackgroundCallState;
    }

    public int getDisconnectCause()
    {
        return mDisconnectCause;
    }

    public int getForegroundCallState()
    {
        return mForegroundCallState;
    }

    public int getPreciseDisconnectCause()
    {
        return mPreciseDisconnectCause;
    }

    public int getRingingCallState()
    {
        return mRingingCallState;
    }

    public int hashCode()
    {
        return ((((mRingingCallState + 31) * 31 + mForegroundCallState) * 31 + mBackgroundCallState) * 31 + mDisconnectCause) * 31 + mPreciseDisconnectCause;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("Ringing call state: ").append(mRingingCallState).toString());
        stringbuffer.append((new StringBuilder()).append(", Foreground call state: ").append(mForegroundCallState).toString());
        stringbuffer.append((new StringBuilder()).append(", Background call state: ").append(mBackgroundCallState).toString());
        stringbuffer.append((new StringBuilder()).append(", Disconnect cause: ").append(mDisconnectCause).toString());
        stringbuffer.append((new StringBuilder()).append(", Precise disconnect cause: ").append(mPreciseDisconnectCause).toString());
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mRingingCallState);
        parcel.writeInt(mForegroundCallState);
        parcel.writeInt(mBackgroundCallState);
        parcel.writeInt(mDisconnectCause);
        parcel.writeInt(mPreciseDisconnectCause);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PreciseCallState createFromParcel(Parcel parcel)
        {
            return new PreciseCallState(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PreciseCallState[] newArray(int i)
        {
            return new PreciseCallState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int PRECISE_CALL_STATE_ACTIVE = 1;
    public static final int PRECISE_CALL_STATE_ALERTING = 4;
    public static final int PRECISE_CALL_STATE_DIALING = 3;
    public static final int PRECISE_CALL_STATE_DISCONNECTED = 7;
    public static final int PRECISE_CALL_STATE_DISCONNECTING = 8;
    public static final int PRECISE_CALL_STATE_HOLDING = 2;
    public static final int PRECISE_CALL_STATE_IDLE = 0;
    public static final int PRECISE_CALL_STATE_INCOMING = 5;
    public static final int PRECISE_CALL_STATE_NOT_VALID = -1;
    public static final int PRECISE_CALL_STATE_WAITING = 6;
    private int mBackgroundCallState;
    private int mDisconnectCause;
    private int mForegroundCallState;
    private int mPreciseDisconnectCause;
    private int mRingingCallState;

}
