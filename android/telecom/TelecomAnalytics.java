// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.telecom:
//            ParcelableCallAnalytics, TimedEvent

public final class TelecomAnalytics
    implements Parcelable
{
    public static final class SessionTiming extends TimedEvent
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public Integer getKey()
        {
            return Integer.valueOf(mId);
        }

        public volatile Object getKey()
        {
            return getKey();
        }

        public long getTime()
        {
            return mTime;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mId);
            parcel.writeLong(mTime);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SessionTiming createFromParcel(Parcel parcel)
            {
                return new SessionTiming(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SessionTiming[] newArray(int i)
            {
                return new SessionTiming[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final int CSW_ADD_CONFERENCE_CALL = 108;
        public static final int CSW_HANDLE_CREATE_CONNECTION_COMPLETE = 100;
        public static final int CSW_REMOVE_CALL = 106;
        public static final int CSW_SET_ACTIVE = 101;
        public static final int CSW_SET_DIALING = 103;
        public static final int CSW_SET_DISCONNECTED = 104;
        public static final int CSW_SET_IS_CONFERENCED = 107;
        public static final int CSW_SET_ON_HOLD = 105;
        public static final int CSW_SET_RINGING = 102;
        public static final int ICA_ANSWER_CALL = 1;
        public static final int ICA_CONFERENCE = 8;
        public static final int ICA_DISCONNECT_CALL = 3;
        public static final int ICA_HOLD_CALL = 4;
        public static final int ICA_MUTE = 6;
        public static final int ICA_REJECT_CALL = 2;
        public static final int ICA_SET_AUDIO_ROUTE = 7;
        public static final int ICA_UNHOLD_CALL = 5;
        private int mId;
        private long mTime;


        public SessionTiming(int i, long l)
        {
            mId = i;
            mTime = l;
        }

        private SessionTiming(Parcel parcel)
        {
            mId = parcel.readInt();
            mTime = parcel.readLong();
        }

        SessionTiming(Parcel parcel, SessionTiming sessiontiming)
        {
            this(parcel);
        }
    }


    private TelecomAnalytics(Parcel parcel)
    {
        mSessionTimings = new ArrayList();
        parcel.readTypedList(mSessionTimings, SessionTiming.CREATOR);
        mCallAnalytics = new ArrayList();
        parcel.readTypedList(mCallAnalytics, ParcelableCallAnalytics.CREATOR);
    }

    TelecomAnalytics(Parcel parcel, TelecomAnalytics telecomanalytics)
    {
        this(parcel);
    }

    public TelecomAnalytics(List list, List list1)
    {
        mSessionTimings = list;
        mCallAnalytics = list1;
    }

    public int describeContents()
    {
        return 0;
    }

    public List getCallAnalytics()
    {
        return mCallAnalytics;
    }

    public List getSessionTimings()
    {
        return mSessionTimings;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeTypedList(mSessionTimings);
        parcel.writeTypedList(mCallAnalytics);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public TelecomAnalytics createFromParcel(Parcel parcel)
        {
            return new TelecomAnalytics(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public TelecomAnalytics[] newArray(int i)
        {
            return new TelecomAnalytics[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private List mCallAnalytics;
    private List mSessionTimings;

}
