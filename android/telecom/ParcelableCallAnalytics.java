// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.*;

public class ParcelableCallAnalytics
    implements Parcelable
{
    public static final class AnalyticsEvent
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public int getEventName()
        {
            return mEventName;
        }

        public long getTimeSinceLastEvent()
        {
            return mTimeSinceLastEvent;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mEventName);
            parcel.writeLong(mTimeSinceLastEvent);
        }

        public static final int AUDIO_ROUTE_BT = 204;
        public static final int AUDIO_ROUTE_EARPIECE = 205;
        public static final int AUDIO_ROUTE_HEADSET = 206;
        public static final int AUDIO_ROUTE_SPEAKER = 207;
        public static final int BIND_CS = 5;
        public static final int BLOCK_CHECK_FINISHED = 105;
        public static final int BLOCK_CHECK_INITIATED = 104;
        public static final int CONFERENCE_WITH = 300;
        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public AnalyticsEvent createFromParcel(Parcel parcel)
            {
                return new AnalyticsEvent(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public AnalyticsEvent[] newArray(int i)
            {
                return new AnalyticsEvent[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final int CS_BOUND = 6;
        public static final int DIRECT_TO_VM_FINISHED = 103;
        public static final int DIRECT_TO_VM_INITIATED = 102;
        public static final int FILTERING_COMPLETED = 107;
        public static final int FILTERING_INITIATED = 106;
        public static final int FILTERING_TIMED_OUT = 108;
        public static final int MUTE = 202;
        public static final int REMOTELY_HELD = 402;
        public static final int REMOTELY_UNHELD = 403;
        public static final int REQUEST_ACCEPT = 7;
        public static final int REQUEST_HOLD = 400;
        public static final int REQUEST_PULL = 500;
        public static final int REQUEST_REJECT = 8;
        public static final int REQUEST_UNHOLD = 401;
        public static final int SCREENING_COMPLETED = 101;
        public static final int SCREENING_SENT = 100;
        public static final int SET_ACTIVE = 1;
        public static final int SET_DIALING = 4;
        public static final int SET_DISCONNECTED = 2;
        public static final int SET_HOLD = 404;
        public static final int SET_PARENT = 302;
        public static final int SET_SELECT_PHONE_ACCOUNT = 0;
        public static final int SILENCE = 201;
        public static final int SKIP_RINGING = 200;
        public static final int SPLIT_CONFERENCE = 301;
        public static final int START_CONNECTION = 3;
        public static final int SWAP = 405;
        public static final int UNMUTE = 203;
        private int mEventName;
        private long mTimeSinceLastEvent;


        public AnalyticsEvent(int i, long l)
        {
            mEventName = i;
            mTimeSinceLastEvent = l;
        }

        AnalyticsEvent(Parcel parcel)
        {
            mEventName = parcel.readInt();
            mTimeSinceLastEvent = parcel.readLong();
        }
    }

    public static final class EventTiming
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public int getName()
        {
            return mName;
        }

        public long getTime()
        {
            return mTime;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mName);
            parcel.writeLong(mTime);
        }

        public static final int ACCEPT_TIMING = 0;
        public static final int BIND_CS_TIMING = 6;
        public static final int BLOCK_CHECK_FINISHED_TIMING = 9;
        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public EventTiming createFromParcel(Parcel parcel)
            {
                return new EventTiming(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public EventTiming[] newArray(int i)
            {
                return new EventTiming[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final int DIRECT_TO_VM_FINISHED_TIMING = 8;
        public static final int DISCONNECT_TIMING = 2;
        public static final int FILTERING_COMPLETED_TIMING = 10;
        public static final int FILTERING_TIMED_OUT_TIMING = 11;
        public static final int HOLD_TIMING = 3;
        public static final int INVALID = 0xf423f;
        public static final int OUTGOING_TIME_TO_DIALING_TIMING = 5;
        public static final int REJECT_TIMING = 1;
        public static final int SCREENING_COMPLETED_TIMING = 7;
        public static final int UNHOLD_TIMING = 4;
        private int mName;
        private long mTime;


        public EventTiming(int i, long l)
        {
            mName = i;
            mTime = l;
        }

        private EventTiming(Parcel parcel)
        {
            mName = parcel.readInt();
            mTime = parcel.readLong();
        }

        EventTiming(Parcel parcel, EventTiming eventtiming)
        {
            this(parcel);
        }
    }

    public static final class VideoEvent
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public int getEventName()
        {
            return mEventName;
        }

        public long getTimeSinceLastEvent()
        {
            return mTimeSinceLastEvent;
        }

        public int getVideoState()
        {
            return mVideoState;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mEventName);
            parcel.writeLong(mTimeSinceLastEvent);
            parcel.writeInt(mVideoState);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public VideoEvent createFromParcel(Parcel parcel)
            {
                return new VideoEvent(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public VideoEvent[] newArray(int i)
            {
                return new VideoEvent[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final int RECEIVE_REMOTE_SESSION_MODIFY_REQUEST = 2;
        public static final int RECEIVE_REMOTE_SESSION_MODIFY_RESPONSE = 3;
        public static final int SEND_LOCAL_SESSION_MODIFY_REQUEST = 0;
        public static final int SEND_LOCAL_SESSION_MODIFY_RESPONSE = 1;
        private int mEventName;
        private long mTimeSinceLastEvent;
        private int mVideoState;


        public VideoEvent(int i, long l, int j)
        {
            mEventName = i;
            mTimeSinceLastEvent = l;
            mVideoState = j;
        }

        VideoEvent(Parcel parcel)
        {
            mEventName = parcel.readInt();
            mTimeSinceLastEvent = parcel.readLong();
            mVideoState = parcel.readInt();
        }
    }


    public ParcelableCallAnalytics(long l, long l1, int i, boolean flag, boolean flag1, 
            int j, int k, boolean flag2, String s, boolean flag3, List list, List list1)
    {
        isVideoCall = false;
        startTimeMillis = l;
        callDurationMillis = l1;
        callType = i;
        isAdditionalCall = flag;
        isInterrupted = flag1;
        callTechnologies = j;
        callTerminationCode = k;
        isEmergencyCall = flag2;
        connectionService = s;
        isCreatedFromExistingConnection = flag3;
        analyticsEvents = list;
        eventTimings = list1;
    }

    public ParcelableCallAnalytics(Parcel parcel)
    {
        isVideoCall = false;
        startTimeMillis = parcel.readLong();
        callDurationMillis = parcel.readLong();
        callType = parcel.readInt();
        isAdditionalCall = readByteAsBoolean(parcel);
        isInterrupted = readByteAsBoolean(parcel);
        callTechnologies = parcel.readInt();
        callTerminationCode = parcel.readInt();
        isEmergencyCall = readByteAsBoolean(parcel);
        connectionService = parcel.readString();
        isCreatedFromExistingConnection = readByteAsBoolean(parcel);
        analyticsEvents = new ArrayList();
        parcel.readTypedList(analyticsEvents, AnalyticsEvent.CREATOR);
        eventTimings = new ArrayList();
        parcel.readTypedList(eventTimings, EventTiming.CREATOR);
        isVideoCall = readByteAsBoolean(parcel);
        videoEvents = new LinkedList();
        parcel.readTypedList(videoEvents, VideoEvent.CREATOR);
    }

    private static boolean readByteAsBoolean(Parcel parcel)
    {
        boolean flag = true;
        if(parcel.readByte() != 1)
            flag = false;
        return flag;
    }

    private static void writeBooleanAsByte(Parcel parcel, boolean flag)
    {
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        parcel.writeByte((byte)i);
    }

    public List analyticsEvents()
    {
        return analyticsEvents;
    }

    public int describeContents()
    {
        return 0;
    }

    public long getCallDurationMillis()
    {
        return callDurationMillis;
    }

    public int getCallTechnologies()
    {
        return callTechnologies;
    }

    public int getCallTerminationCode()
    {
        return callTerminationCode;
    }

    public int getCallType()
    {
        return callType;
    }

    public String getConnectionService()
    {
        return connectionService;
    }

    public List getEventTimings()
    {
        return eventTimings;
    }

    public long getStartTimeMillis()
    {
        return startTimeMillis;
    }

    public List getVideoEvents()
    {
        return videoEvents;
    }

    public boolean isAdditionalCall()
    {
        return isAdditionalCall;
    }

    public boolean isCreatedFromExistingConnection()
    {
        return isCreatedFromExistingConnection;
    }

    public boolean isEmergencyCall()
    {
        return isEmergencyCall;
    }

    public boolean isInterrupted()
    {
        return isInterrupted;
    }

    public boolean isVideoCall()
    {
        return isVideoCall;
    }

    public void setIsVideoCall(boolean flag)
    {
        isVideoCall = flag;
    }

    public void setVideoEvents(List list)
    {
        videoEvents = list;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(startTimeMillis);
        parcel.writeLong(callDurationMillis);
        parcel.writeInt(callType);
        writeBooleanAsByte(parcel, isAdditionalCall);
        writeBooleanAsByte(parcel, isInterrupted);
        parcel.writeInt(callTechnologies);
        parcel.writeInt(callTerminationCode);
        writeBooleanAsByte(parcel, isEmergencyCall);
        parcel.writeString(connectionService);
        writeBooleanAsByte(parcel, isCreatedFromExistingConnection);
        parcel.writeTypedList(analyticsEvents);
        parcel.writeTypedList(eventTimings);
        writeBooleanAsByte(parcel, isVideoCall);
        parcel.writeTypedList(videoEvents);
    }

    public static final int CALLTYPE_INCOMING = 1;
    public static final int CALLTYPE_OUTGOING = 2;
    public static final int CALLTYPE_UNKNOWN = 0;
    public static final int CDMA_PHONE = 1;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ParcelableCallAnalytics createFromParcel(Parcel parcel)
        {
            return new ParcelableCallAnalytics(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ParcelableCallAnalytics[] newArray(int i)
        {
            return new ParcelableCallAnalytics[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int GSM_PHONE = 2;
    public static final int IMS_PHONE = 4;
    public static final long MILLIS_IN_1_SECOND = 1000L;
    public static final long MILLIS_IN_5_MINUTES = 0x493e0L;
    public static final int SIP_PHONE = 8;
    public static final int STILL_CONNECTED = -1;
    public static final int THIRD_PARTY_PHONE = 16;
    private final List analyticsEvents;
    private final long callDurationMillis;
    private final int callTechnologies;
    private final int callTerminationCode;
    private final int callType;
    private final String connectionService;
    private final List eventTimings;
    private final boolean isAdditionalCall;
    private final boolean isCreatedFromExistingConnection;
    private final boolean isEmergencyCall;
    private final boolean isInterrupted;
    private boolean isVideoCall;
    private final long startTimeMillis;
    private List videoEvents;

}
