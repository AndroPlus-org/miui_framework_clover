// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.session;

import android.os.*;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

public final class PlaybackState
    implements Parcelable
{
    public static final class Builder
    {

        public Builder addCustomAction(CustomAction customaction)
        {
            if(customaction == null)
            {
                throw new IllegalArgumentException("You may not add a null CustomAction to PlaybackState.");
            } else
            {
                mCustomActions.add(customaction);
                return this;
            }
        }

        public Builder addCustomAction(String s, String s1, int i)
        {
            return addCustomAction(new CustomAction(s, s1, i, null, null));
        }

        public PlaybackState build()
        {
            return new PlaybackState(mState, mPosition, mUpdateTime, mSpeed, mBufferedPosition, mActions, mCustomActions, mActiveItemId, mErrorMessage, mExtras, null);
        }

        public Builder setActions(long l)
        {
            mActions = l;
            return this;
        }

        public Builder setActiveQueueItemId(long l)
        {
            mActiveItemId = l;
            return this;
        }

        public Builder setBufferedPosition(long l)
        {
            mBufferedPosition = l;
            return this;
        }

        public Builder setErrorMessage(CharSequence charsequence)
        {
            mErrorMessage = charsequence;
            return this;
        }

        public Builder setExtras(Bundle bundle)
        {
            mExtras = bundle;
            return this;
        }

        public Builder setState(int i, long l, float f)
        {
            return setState(i, l, f, SystemClock.elapsedRealtime());
        }

        public Builder setState(int i, long l, float f, long l1)
        {
            mState = i;
            mPosition = l;
            mUpdateTime = l1;
            mSpeed = f;
            return this;
        }

        private long mActions;
        private long mActiveItemId;
        private long mBufferedPosition;
        private final List mCustomActions;
        private CharSequence mErrorMessage;
        private Bundle mExtras;
        private long mPosition;
        private float mSpeed;
        private int mState;
        private long mUpdateTime;

        public Builder()
        {
            mCustomActions = new ArrayList();
            mActiveItemId = -1L;
        }

        public Builder(PlaybackState playbackstate)
        {
            mCustomActions = new ArrayList();
            mActiveItemId = -1L;
            if(playbackstate == null)
                return;
            mState = PlaybackState._2D_get8(playbackstate);
            mPosition = PlaybackState._2D_get6(playbackstate);
            mBufferedPosition = PlaybackState._2D_get2(playbackstate);
            mSpeed = PlaybackState._2D_get7(playbackstate);
            mActions = PlaybackState._2D_get0(playbackstate);
            if(PlaybackState._2D_get3(playbackstate) != null)
                mCustomActions.addAll(PlaybackState._2D_get3(playbackstate));
            mErrorMessage = PlaybackState._2D_get4(playbackstate);
            mUpdateTime = PlaybackState._2D_get9(playbackstate);
            mActiveItemId = PlaybackState._2D_get1(playbackstate);
            mExtras = PlaybackState._2D_get5(playbackstate);
        }
    }

    public static final class CustomAction
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public String getAction()
        {
            return mAction;
        }

        public Bundle getExtras()
        {
            return mExtras;
        }

        public int getIcon()
        {
            return mIcon;
        }

        public CharSequence getName()
        {
            return mName;
        }

        public String toString()
        {
            return (new StringBuilder()).append("Action:mName='").append(mName).append(", mIcon=").append(mIcon).append(", mExtras=").append(mExtras).toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(mAction);
            TextUtils.writeToParcel(mName, parcel, i);
            parcel.writeInt(mIcon);
            parcel.writeBundle(mExtras);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public CustomAction createFromParcel(Parcel parcel)
            {
                return new CustomAction(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public CustomAction[] newArray(int i)
            {
                return new CustomAction[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final String mAction;
        private final Bundle mExtras;
        private final int mIcon;
        private final CharSequence mName;


        private CustomAction(Parcel parcel)
        {
            mAction = parcel.readString();
            mName = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            mIcon = parcel.readInt();
            mExtras = parcel.readBundle();
        }

        CustomAction(Parcel parcel, CustomAction customaction)
        {
            this(parcel);
        }

        private CustomAction(String s, CharSequence charsequence, int i, Bundle bundle)
        {
            mAction = s;
            mName = charsequence;
            mIcon = i;
            mExtras = bundle;
        }

        CustomAction(String s, CharSequence charsequence, int i, Bundle bundle, CustomAction customaction)
        {
            this(s, charsequence, i, bundle);
        }
    }

    public static final class CustomAction.Builder
    {

        public CustomAction build()
        {
            return new CustomAction(mAction, mName, mIcon, mExtras, null);
        }

        public CustomAction.Builder setExtras(Bundle bundle)
        {
            mExtras = bundle;
            return this;
        }

        private final String mAction;
        private Bundle mExtras;
        private final int mIcon;
        private final CharSequence mName;

        public CustomAction.Builder(String s, CharSequence charsequence, int i)
        {
            if(TextUtils.isEmpty(s))
                throw new IllegalArgumentException("You must specify an action to build a CustomAction.");
            if(TextUtils.isEmpty(charsequence))
                throw new IllegalArgumentException("You must specify a name to build a CustomAction.");
            if(i == 0)
            {
                throw new IllegalArgumentException("You must specify an icon resource id to build a CustomAction.");
            } else
            {
                mAction = s;
                mName = charsequence;
                mIcon = i;
                return;
            }
        }
    }


    static long _2D_get0(PlaybackState playbackstate)
    {
        return playbackstate.mActions;
    }

    static long _2D_get1(PlaybackState playbackstate)
    {
        return playbackstate.mActiveItemId;
    }

    static long _2D_get2(PlaybackState playbackstate)
    {
        return playbackstate.mBufferedPosition;
    }

    static List _2D_get3(PlaybackState playbackstate)
    {
        return playbackstate.mCustomActions;
    }

    static CharSequence _2D_get4(PlaybackState playbackstate)
    {
        return playbackstate.mErrorMessage;
    }

    static Bundle _2D_get5(PlaybackState playbackstate)
    {
        return playbackstate.mExtras;
    }

    static long _2D_get6(PlaybackState playbackstate)
    {
        return playbackstate.mPosition;
    }

    static float _2D_get7(PlaybackState playbackstate)
    {
        return playbackstate.mSpeed;
    }

    static int _2D_get8(PlaybackState playbackstate)
    {
        return playbackstate.mState;
    }

    static long _2D_get9(PlaybackState playbackstate)
    {
        return playbackstate.mUpdateTime;
    }

    private PlaybackState(int i, long l, long l1, float f, long l2, long l3, List list, long l4, CharSequence charsequence, 
            Bundle bundle)
    {
        mState = i;
        mPosition = l;
        mSpeed = f;
        mUpdateTime = l1;
        mBufferedPosition = l2;
        mActions = l3;
        mCustomActions = new ArrayList(list);
        mActiveItemId = l4;
        mErrorMessage = charsequence;
        mExtras = bundle;
    }

    PlaybackState(int i, long l, long l1, float f, long l2, long l3, List list, long l4, CharSequence charsequence, 
            Bundle bundle, PlaybackState playbackstate)
    {
        this(i, l, l1, f, l2, l3, list, l4, charsequence, bundle);
    }

    private PlaybackState(Parcel parcel)
    {
        mState = parcel.readInt();
        mPosition = parcel.readLong();
        mSpeed = parcel.readFloat();
        mUpdateTime = parcel.readLong();
        mBufferedPosition = parcel.readLong();
        mActions = parcel.readLong();
        mCustomActions = parcel.createTypedArrayList(CustomAction.CREATOR);
        mActiveItemId = parcel.readLong();
        mErrorMessage = parcel.readCharSequence();
        mExtras = parcel.readBundle();
    }

    PlaybackState(Parcel parcel, PlaybackState playbackstate)
    {
        this(parcel);
    }

    private static long getActionForRccFlag(int i)
    {
        switch(i)
        {
        default:
            return 0L;

        case 1: // '\001'
            return 16L;

        case 2: // '\002'
            return 8L;

        case 4: // '\004'
            return 4L;

        case 8: // '\b'
            return 512L;

        case 16: // '\020'
            return 2L;

        case 32: // ' '
            return 1L;

        case 64: // '@'
            return 64L;

        case 128: 
            return 32L;

        case 256: 
            return 256L;

        case 512: 
            return 128L;
        }
    }

    public static long getActionsFromRccControlFlags(int i)
    {
        long l = 0L;
        for(long l1 = 1L; l1 <= (long)i;)
        {
            long l2 = l;
            if(((long)i & l1) != 0L)
                l2 = l | getActionForRccFlag((int)l1);
            l1 <<= 1;
            l = l2;
        }

        return l;
    }

    public static int getRccControlFlagsFromActions(long l)
    {
        int i = 0;
        for(long l1 = 1L; l1 <= l && l1 < 0x7fffffffL;)
        {
            int j = i;
            if((l1 & l) != 0L)
                j = i | getRccFlagForAction(l1);
            l1 <<= 1;
            i = j;
        }

        return i;
    }

    private static int getRccFlagForAction(long l)
    {
        int i;
        if(l < 0x7fffffffL)
            i = (int)l;
        else
            i = 0;
        switch(i)
        {
        default:
            return 0;

        case 16: // '\020'
            return 1;

        case 8: // '\b'
            return 2;

        case 4: // '\004'
            return 4;

        case 512: 
            return 8;

        case 2: // '\002'
            return 16;

        case 1: // '\001'
            return 32;

        case 64: // '@'
            return 64;

        case 32: // ' '
            return 128;

        case 256: 
            return 256;

        case 128: 
            return 512;
        }
    }

    public static int getRccStateFromState(int i)
    {
        switch(i)
        {
        case 8: // '\b'
        default:
            return -1;

        case 6: // '\006'
            return 8;

        case 7: // '\007'
            return 9;

        case 4: // '\004'
            return 4;

        case 0: // '\0'
            return 0;

        case 2: // '\002'
            return 2;

        case 3: // '\003'
            return 3;

        case 5: // '\005'
            return 5;

        case 9: // '\t'
            return 7;

        case 10: // '\n'
            return 6;

        case 1: // '\001'
            return 1;
        }
    }

    public static int getStateFromRccState(int i)
    {
        switch(i)
        {
        default:
            return -1;

        case 8: // '\b'
            return 6;

        case 9: // '\t'
            return 7;

        case 4: // '\004'
            return 4;

        case 0: // '\0'
            return 0;

        case 2: // '\002'
            return 2;

        case 3: // '\003'
            return 3;

        case 5: // '\005'
            return 5;

        case 7: // '\007'
            return 9;

        case 6: // '\006'
            return 10;

        case 1: // '\001'
            return 1;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public long getActions()
    {
        return mActions;
    }

    public long getActiveQueueItemId()
    {
        return mActiveItemId;
    }

    public long getBufferedPosition()
    {
        return mBufferedPosition;
    }

    public List getCustomActions()
    {
        return mCustomActions;
    }

    public CharSequence getErrorMessage()
    {
        return mErrorMessage;
    }

    public Bundle getExtras()
    {
        return mExtras;
    }

    public long getLastPositionUpdateTime()
    {
        return mUpdateTime;
    }

    public float getPlaybackSpeed()
    {
        return mSpeed;
    }

    public long getPosition()
    {
        return mPosition;
    }

    public int getState()
    {
        return mState;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("PlaybackState {");
        stringbuilder.append("state=").append(mState);
        stringbuilder.append(", position=").append(mPosition);
        stringbuilder.append(", buffered position=").append(mBufferedPosition);
        stringbuilder.append(", speed=").append(mSpeed);
        stringbuilder.append(", updated=").append(mUpdateTime);
        stringbuilder.append(", actions=").append(mActions);
        stringbuilder.append(", custom actions=").append(mCustomActions);
        stringbuilder.append(", active item id=").append(mActiveItemId);
        stringbuilder.append(", error=").append(mErrorMessage);
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mState);
        parcel.writeLong(mPosition);
        parcel.writeFloat(mSpeed);
        parcel.writeLong(mUpdateTime);
        parcel.writeLong(mBufferedPosition);
        parcel.writeLong(mActions);
        parcel.writeTypedList(mCustomActions);
        parcel.writeLong(mActiveItemId);
        parcel.writeCharSequence(mErrorMessage);
        parcel.writeBundle(mExtras);
    }

    public static final long ACTION_FAST_FORWARD = 64L;
    public static final long ACTION_PAUSE = 2L;
    public static final long ACTION_PLAY = 4L;
    public static final long ACTION_PLAY_FROM_MEDIA_ID = 1024L;
    public static final long ACTION_PLAY_FROM_SEARCH = 2048L;
    public static final long ACTION_PLAY_FROM_URI = 8192L;
    public static final long ACTION_PLAY_PAUSE = 512L;
    public static final long ACTION_PREPARE = 16384L;
    public static final long ACTION_PREPARE_FROM_MEDIA_ID = 32768L;
    public static final long ACTION_PREPARE_FROM_SEARCH = 0x10000L;
    public static final long ACTION_PREPARE_FROM_URI = 0x20000L;
    public static final long ACTION_REWIND = 8L;
    public static final long ACTION_SEEK_TO = 256L;
    public static final long ACTION_SET_RATING = 128L;
    public static final long ACTION_SKIP_TO_NEXT = 32L;
    public static final long ACTION_SKIP_TO_PREVIOUS = 16L;
    public static final long ACTION_SKIP_TO_QUEUE_ITEM = 4096L;
    public static final long ACTION_STOP = 1L;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PlaybackState createFromParcel(Parcel parcel)
        {
            return new PlaybackState(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PlaybackState[] newArray(int i)
        {
            return new PlaybackState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final long PLAYBACK_POSITION_UNKNOWN = -1L;
    public static final int STATE_BUFFERING = 6;
    public static final int STATE_CONNECTING = 8;
    public static final int STATE_ERROR = 7;
    public static final int STATE_FAST_FORWARDING = 4;
    public static final int STATE_NONE = 0;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_REWINDING = 5;
    public static final int STATE_SKIPPING_TO_NEXT = 10;
    public static final int STATE_SKIPPING_TO_PREVIOUS = 9;
    public static final int STATE_SKIPPING_TO_QUEUE_ITEM = 11;
    public static final int STATE_STOPPED = 1;
    private static final String TAG = "PlaybackState";
    private final long mActions;
    private final long mActiveItemId;
    private final long mBufferedPosition;
    private List mCustomActions;
    private final CharSequence mErrorMessage;
    private final Bundle mExtras;
    private final long mPosition;
    private final float mSpeed;
    private final int mState;
    private final long mUpdateTime;

}
