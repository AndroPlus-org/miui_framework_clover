// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ParceledListSlice;
import android.content.res.Resources;
import android.media.*;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;

// Referenced classes of package android.media.session:
//            MediaSessionManager, ISession, MediaController, PlaybackState, 
//            ISessionController

public final class MediaSession
{
    public static abstract class Callback
    {

        static CallbackMessageHandler _2D_set0(Callback callback, CallbackMessageHandler callbackmessagehandler)
        {
            callback.mHandler = callbackmessagehandler;
            return callbackmessagehandler;
        }

        static MediaSession _2D_set1(Callback callback, MediaSession mediasession)
        {
            callback.mSession = mediasession;
            return mediasession;
        }

        static void _2D_wrap0(Callback callback)
        {
            callback.handleMediaPlayPauseKeySingleTapIfPending();
        }

        private void handleMediaPlayPauseKeySingleTapIfPending()
        {
            boolean flag;
            boolean flag1;
            if(!mMediaPlayPauseKeyPending)
                return;
            mMediaPlayPauseKeyPending = false;
            mHandler.removeMessages(23);
            PlaybackState playbackstate = MediaSession._2D_get1(mSession);
            long l;
            boolean flag2;
            if(playbackstate == null)
                l = 0L;
            else
                l = playbackstate.getActions();
            if(playbackstate != null)
            {
                if(playbackstate.getState() == 3)
                    flag = true;
                else
                    flag = false;
            } else
            {
                flag = false;
            }
            if((516L & l) != 0L)
                flag1 = true;
            else
                flag1 = false;
            if((514L & l) != 0L)
                flag2 = true;
            else
                flag2 = false;
            if(!flag || !flag2) goto _L2; else goto _L1
_L1:
            onPause();
_L4:
            return;
_L2:
            if(!flag && flag1)
                onPlay();
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void onCommand(String s, Bundle bundle, ResultReceiver resultreceiver)
        {
        }

        public void onCustomAction(String s, Bundle bundle)
        {
        }

        public void onFastForward()
        {
        }

        public boolean onMediaButtonEvent(Intent intent)
        {
            if(mSession == null || mHandler == null || !"android.intent.action.MEDIA_BUTTON".equals(intent.getAction())) goto _L2; else goto _L1
_L1:
            intent = (KeyEvent)intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            if(intent == null || intent.getAction() != 0) goto _L2; else goto _L3
_L3:
            long l;
            PlaybackState playbackstate = MediaSession._2D_get1(mSession);
            if(playbackstate == null)
                l = 0L;
            else
                l = playbackstate.getActions();
            intent.getKeyCode();
            JVM INSTR lookupswitch 2: default 92
        //                       79: 178
        //                       85: 178;
               goto _L4 _L5 _L5
_L4:
            handleMediaPlayPauseKeySingleTapIfPending();
            intent.getKeyCode();
            JVM INSTR lookupswitch 7: default 168
        //                       86: 315
        //                       87: 283
        //                       88: 299
        //                       89: 345
        //                       90: 329
        //                       126: 251
        //                       127: 267;
               goto _L2 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L2:
            return false;
_L5:
            if(intent.getRepeatCount() > 0)
                handleMediaPlayPauseKeySingleTapIfPending();
            else
            if(mMediaPlayPauseKeyPending)
            {
                mHandler.removeMessages(23);
                mMediaPlayPauseKeyPending = false;
                if((32L & l) != 0L)
                    onSkipToNext();
            } else
            {
                mMediaPlayPauseKeyPending = true;
                mHandler.sendEmptyMessageDelayed(23, ViewConfiguration.getDoubleTapTimeout());
            }
            return true;
_L11:
            if((4L & l) != 0L)
            {
                onPlay();
                return true;
            }
            continue; /* Loop/switch isn't completed */
_L12:
            if((2L & l) != 0L)
            {
                onPause();
                return true;
            }
            continue; /* Loop/switch isn't completed */
_L7:
            if((32L & l) != 0L)
            {
                onSkipToNext();
                return true;
            }
            continue; /* Loop/switch isn't completed */
_L8:
            if((16L & l) != 0L)
            {
                onSkipToPrevious();
                return true;
            }
            continue; /* Loop/switch isn't completed */
_L6:
            if((1L & l) != 0L)
            {
                onStop();
                return true;
            }
            continue; /* Loop/switch isn't completed */
_L10:
            if((64L & l) != 0L)
            {
                onFastForward();
                return true;
            }
            continue; /* Loop/switch isn't completed */
_L9:
            if((8L & l) != 0L)
            {
                onRewind();
                return true;
            }
            if(true) goto _L2; else goto _L13
_L13:
        }

        public void onPause()
        {
        }

        public void onPlay()
        {
        }

        public void onPlayFromMediaId(String s, Bundle bundle)
        {
        }

        public void onPlayFromSearch(String s, Bundle bundle)
        {
        }

        public void onPlayFromUri(Uri uri, Bundle bundle)
        {
        }

        public void onPrepare()
        {
        }

        public void onPrepareFromMediaId(String s, Bundle bundle)
        {
        }

        public void onPrepareFromSearch(String s, Bundle bundle)
        {
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle)
        {
        }

        public void onRewind()
        {
        }

        public void onSeekTo(long l)
        {
        }

        public void onSetRating(Rating rating)
        {
        }

        public void onSkipToNext()
        {
        }

        public void onSkipToPrevious()
        {
        }

        public void onSkipToQueueItem(long l)
        {
        }

        public void onStop()
        {
        }

        private CallbackMessageHandler mHandler;
        private boolean mMediaPlayPauseKeyPending;
        private MediaSession mSession;

        public Callback()
        {
        }
    }

    private class CallbackMessageHandler extends Handler
    {

        static Callback _2D_get0(CallbackMessageHandler callbackmessagehandler)
        {
            return callbackmessagehandler.mCallback;
        }

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 23: default 112
        //                       1 113
        //                       2 143
        //                       3 161
        //                       4 171
        //                       5 192
        //                       6 213
        //                       7 234
        //                       8 244
        //                       9 265
        //                       10 286
        //                       11 307
        //                       12 327
        //                       13 337
        //                       14 347
        //                       15 357
        //                       16 367
        //                       17 377
        //                       18 387
        //                       19 407
        //                       20 424
        //                       21 445
        //                       22 491
        //                       23 537;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24
_L1:
            return;
_L2:
            message = (Command)message.obj;
            mCallback.onCommand(((Command) (message)).command, ((Command) (message)).extras, ((Command) (message)).stub);
            continue; /* Loop/switch isn't completed */
_L3:
            mCallback.onMediaButtonEvent((Intent)message.obj);
            continue; /* Loop/switch isn't completed */
_L4:
            mCallback.onPrepare();
            continue; /* Loop/switch isn't completed */
_L5:
            mCallback.onPrepareFromMediaId((String)message.obj, message.getData());
            continue; /* Loop/switch isn't completed */
_L6:
            mCallback.onPrepareFromSearch((String)message.obj, message.getData());
            continue; /* Loop/switch isn't completed */
_L7:
            mCallback.onPrepareFromUri((Uri)message.obj, message.getData());
            continue; /* Loop/switch isn't completed */
_L8:
            mCallback.onPlay();
            continue; /* Loop/switch isn't completed */
_L9:
            mCallback.onPlayFromMediaId((String)message.obj, message.getData());
            continue; /* Loop/switch isn't completed */
_L10:
            mCallback.onPlayFromSearch((String)message.obj, message.getData());
            continue; /* Loop/switch isn't completed */
_L11:
            mCallback.onPlayFromUri((Uri)message.obj, message.getData());
            continue; /* Loop/switch isn't completed */
_L12:
            mCallback.onSkipToQueueItem(((Long)message.obj).longValue());
            continue; /* Loop/switch isn't completed */
_L13:
            mCallback.onPause();
            continue; /* Loop/switch isn't completed */
_L14:
            mCallback.onStop();
            continue; /* Loop/switch isn't completed */
_L15:
            mCallback.onSkipToNext();
            continue; /* Loop/switch isn't completed */
_L16:
            mCallback.onSkipToPrevious();
            continue; /* Loop/switch isn't completed */
_L17:
            mCallback.onFastForward();
            continue; /* Loop/switch isn't completed */
_L18:
            mCallback.onRewind();
            continue; /* Loop/switch isn't completed */
_L19:
            mCallback.onSeekTo(((Long)message.obj).longValue());
            continue; /* Loop/switch isn't completed */
_L20:
            mCallback.onSetRating((Rating)message.obj);
            continue; /* Loop/switch isn't completed */
_L21:
            mCallback.onCustomAction((String)message.obj, message.getData());
            continue; /* Loop/switch isn't completed */
_L22:
            Object obj = MediaSession._2D_get0(MediaSession.this);
            obj;
            JVM INSTR monitorenter ;
            VolumeProvider volumeprovider = MediaSession._2D_get2(MediaSession.this);
            obj;
            JVM INSTR monitorexit ;
            if(volumeprovider != null)
                volumeprovider.onAdjustVolume(((Integer)message.obj).intValue());
            continue; /* Loop/switch isn't completed */
            message;
            throw message;
_L23:
            obj = MediaSession._2D_get0(MediaSession.this);
            obj;
            JVM INSTR monitorenter ;
            volumeprovider = MediaSession._2D_get2(MediaSession.this);
            obj;
            JVM INSTR monitorexit ;
            if(volumeprovider != null)
                volumeprovider.onSetVolumeTo(((Integer)message.obj).intValue());
            continue; /* Loop/switch isn't completed */
            message;
            throw message;
_L24:
            Callback._2D_wrap0(mCallback);
            if(true) goto _L1; else goto _L25
_L25:
        }

        public void post(int i)
        {
            post(i, null);
        }

        public void post(int i, Object obj)
        {
            obtainMessage(i, obj).sendToTarget();
        }

        public void post(int i, Object obj, int j)
        {
            obtainMessage(i, j, 0, obj).sendToTarget();
        }

        public void post(int i, Object obj, Bundle bundle)
        {
            obj = obtainMessage(i, obj);
            ((Message) (obj)).setData(bundle);
            ((Message) (obj)).sendToTarget();
        }

        private static final int MSG_ADJUST_VOLUME = 21;
        private static final int MSG_COMMAND = 1;
        private static final int MSG_CUSTOM_ACTION = 20;
        private static final int MSG_FAST_FORWARD = 16;
        private static final int MSG_MEDIA_BUTTON = 2;
        private static final int MSG_NEXT = 14;
        private static final int MSG_PAUSE = 12;
        private static final int MSG_PLAY = 7;
        private static final int MSG_PLAY_MEDIA_ID = 8;
        private static final int MSG_PLAY_PAUSE_KEY_DOUBLE_TAP_TIMEOUT = 23;
        private static final int MSG_PLAY_SEARCH = 9;
        private static final int MSG_PLAY_URI = 10;
        private static final int MSG_PREPARE = 3;
        private static final int MSG_PREPARE_MEDIA_ID = 4;
        private static final int MSG_PREPARE_SEARCH = 5;
        private static final int MSG_PREPARE_URI = 6;
        private static final int MSG_PREVIOUS = 15;
        private static final int MSG_RATE = 19;
        private static final int MSG_REWIND = 17;
        private static final int MSG_SEEK_TO = 18;
        private static final int MSG_SET_VOLUME = 22;
        private static final int MSG_SKIP_TO_ITEM = 11;
        private static final int MSG_STOP = 13;
        private Callback mCallback;
        final MediaSession this$0;

        public CallbackMessageHandler(Looper looper, Callback callback)
        {
            this$0 = MediaSession.this;
            super(looper, null, true);
            mCallback = callback;
            Callback._2D_set0(mCallback, this);
        }
    }

    public static class CallbackStub extends ISessionCallback.Stub
    {

        public void onAdjustVolume(int i)
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap0(mediasession, i);
        }

        public void onCommand(String s, Bundle bundle, ResultReceiver resultreceiver)
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap21(mediasession, s, bundle, resultreceiver);
        }

        public void onCustomAction(String s, Bundle bundle)
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap1(mediasession, s, bundle);
        }

        public void onFastForward()
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap2(mediasession);
        }

        public void onMediaButton(Intent intent, int i, ResultReceiver resultreceiver)
        {
            MediaSession mediasession;
            mediasession = (MediaSession)mMediaSession.get();
            if(mediasession == null)
                break MISSING_BLOCK_LABEL_23;
            MediaSession._2D_wrap3(mediasession, intent);
            if(resultreceiver != null)
                resultreceiver.send(i, null);
            return;
            intent;
            if(resultreceiver != null)
                resultreceiver.send(i, null);
            throw intent;
        }

        public void onNext()
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap4(mediasession);
        }

        public void onPause()
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap5(mediasession);
        }

        public void onPlay()
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap9(mediasession);
        }

        public void onPlayFromMediaId(String s, Bundle bundle)
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap6(mediasession, s, bundle);
        }

        public void onPlayFromSearch(String s, Bundle bundle)
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap7(mediasession, s, bundle);
        }

        public void onPlayFromUri(Uri uri, Bundle bundle)
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap8(mediasession, uri, bundle);
        }

        public void onPrepare()
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap13(mediasession);
        }

        public void onPrepareFromMediaId(String s, Bundle bundle)
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap10(mediasession, s, bundle);
        }

        public void onPrepareFromSearch(String s, Bundle bundle)
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap11(mediasession, s, bundle);
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle)
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap12(mediasession, uri, bundle);
        }

        public void onPrevious()
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap14(mediasession);
        }

        public void onRate(Rating rating)
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap15(mediasession, rating);
        }

        public void onRewind()
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap16(mediasession);
        }

        public void onSeekTo(long l)
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap17(mediasession, l);
        }

        public void onSetVolumeTo(int i)
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap18(mediasession, i);
        }

        public void onSkipToTrack(long l)
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap19(mediasession, l);
        }

        public void onStop()
        {
            MediaSession mediasession = (MediaSession)mMediaSession.get();
            if(mediasession != null)
                MediaSession._2D_wrap20(mediasession);
        }

        private WeakReference mMediaSession;

        public CallbackStub(MediaSession mediasession)
        {
            mMediaSession = new WeakReference(mediasession);
        }
    }

    private static final class Command
    {

        public final String command;
        public final Bundle extras;
        public final ResultReceiver stub;

        public Command(String s, Bundle bundle, ResultReceiver resultreceiver)
        {
            command = s;
            extras = bundle;
            stub = resultreceiver;
        }
    }

    public static final class QueueItem
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(obj == null)
                return false;
            if(!(obj instanceof QueueItem))
                return false;
            obj = (QueueItem)obj;
            if(mId != ((QueueItem) (obj)).mId)
                return false;
            return Objects.equals(mDescription, ((QueueItem) (obj)).mDescription);
        }

        public MediaDescription getDescription()
        {
            return mDescription;
        }

        public long getQueueId()
        {
            return mId;
        }

        public String toString()
        {
            return (new StringBuilder()).append("MediaSession.QueueItem {Description=").append(mDescription).append(", Id=").append(mId).append(" }").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            mDescription.writeToParcel(parcel, i);
            parcel.writeLong(mId);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public QueueItem createFromParcel(Parcel parcel)
            {
                return new QueueItem(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public QueueItem[] newArray(int i)
            {
                return new QueueItem[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final int UNKNOWN_ID = -1;
        private final MediaDescription mDescription;
        private final long mId;


        public QueueItem(MediaDescription mediadescription, long l)
        {
            if(mediadescription == null)
                throw new IllegalArgumentException("Description cannot be null.");
            if(l == -1L)
            {
                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            } else
            {
                mDescription = mediadescription;
                mId = l;
                return;
            }
        }

        private QueueItem(Parcel parcel)
        {
            mDescription = (MediaDescription)MediaDescription.CREATOR.createFromParcel(parcel);
            mId = parcel.readLong();
        }

        QueueItem(Parcel parcel, QueueItem queueitem)
        {
            this(parcel);
        }
    }

    public static final class Token
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            obj = (Token)obj;
            if(mBinder == null)
            {
                if(((Token) (obj)).mBinder != null)
                    return false;
            } else
            if(!mBinder.asBinder().equals(((Token) (obj)).mBinder.asBinder()))
                return false;
            return true;
        }

        ISessionController getBinder()
        {
            return mBinder;
        }

        public int hashCode()
        {
            int i;
            if(mBinder == null)
                i = 0;
            else
                i = mBinder.asBinder().hashCode();
            return i + 31;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeStrongBinder(mBinder.asBinder());
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Token createFromParcel(Parcel parcel)
            {
                return new Token(ISessionController.Stub.asInterface(parcel.readStrongBinder()));
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Token[] newArray(int i)
            {
                return new Token[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private ISessionController mBinder;


        public Token(ISessionController isessioncontroller)
        {
            mBinder = isessioncontroller;
        }
    }


    static Object _2D_get0(MediaSession mediasession)
    {
        return mediasession.mLock;
    }

    static PlaybackState _2D_get1(MediaSession mediasession)
    {
        return mediasession.mPlaybackState;
    }

    static VolumeProvider _2D_get2(MediaSession mediasession)
    {
        return mediasession.mVolumeProvider;
    }

    static void _2D_wrap0(MediaSession mediasession, int i)
    {
        mediasession.dispatchAdjustVolume(i);
    }

    static void _2D_wrap1(MediaSession mediasession, String s, Bundle bundle)
    {
        mediasession.dispatchCustomAction(s, bundle);
    }

    static void _2D_wrap10(MediaSession mediasession, String s, Bundle bundle)
    {
        mediasession.dispatchPrepareFromMediaId(s, bundle);
    }

    static void _2D_wrap11(MediaSession mediasession, String s, Bundle bundle)
    {
        mediasession.dispatchPrepareFromSearch(s, bundle);
    }

    static void _2D_wrap12(MediaSession mediasession, Uri uri, Bundle bundle)
    {
        mediasession.dispatchPrepareFromUri(uri, bundle);
    }

    static void _2D_wrap13(MediaSession mediasession)
    {
        mediasession.dispatchPrepare();
    }

    static void _2D_wrap14(MediaSession mediasession)
    {
        mediasession.dispatchPrevious();
    }

    static void _2D_wrap15(MediaSession mediasession, Rating rating)
    {
        mediasession.dispatchRate(rating);
    }

    static void _2D_wrap16(MediaSession mediasession)
    {
        mediasession.dispatchRewind();
    }

    static void _2D_wrap17(MediaSession mediasession, long l)
    {
        mediasession.dispatchSeekTo(l);
    }

    static void _2D_wrap18(MediaSession mediasession, int i)
    {
        mediasession.dispatchSetVolumeTo(i);
    }

    static void _2D_wrap19(MediaSession mediasession, long l)
    {
        mediasession.dispatchSkipToItem(l);
    }

    static void _2D_wrap2(MediaSession mediasession)
    {
        mediasession.dispatchFastForward();
    }

    static void _2D_wrap20(MediaSession mediasession)
    {
        mediasession.dispatchStop();
    }

    static void _2D_wrap21(MediaSession mediasession, String s, Bundle bundle, ResultReceiver resultreceiver)
    {
        mediasession.postCommand(s, bundle, resultreceiver);
    }

    static void _2D_wrap3(MediaSession mediasession, Intent intent)
    {
        mediasession.dispatchMediaButton(intent);
    }

    static void _2D_wrap4(MediaSession mediasession)
    {
        mediasession.dispatchNext();
    }

    static void _2D_wrap5(MediaSession mediasession)
    {
        mediasession.dispatchPause();
    }

    static void _2D_wrap6(MediaSession mediasession, String s, Bundle bundle)
    {
        mediasession.dispatchPlayFromMediaId(s, bundle);
    }

    static void _2D_wrap7(MediaSession mediasession, String s, Bundle bundle)
    {
        mediasession.dispatchPlayFromSearch(s, bundle);
    }

    static void _2D_wrap8(MediaSession mediasession, Uri uri, Bundle bundle)
    {
        mediasession.dispatchPlayFromUri(uri, bundle);
    }

    static void _2D_wrap9(MediaSession mediasession)
    {
        mediasession.dispatchPlay();
    }

    public MediaSession(Context context, String s)
    {
        this(context, s, UserHandle.myUserId());
    }

    public MediaSession(Context context, String s, int i)
    {
        mLock = new Object();
        mActive = false;
        if(context == null)
            throw new IllegalArgumentException("context cannot be null.");
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("tag cannot be null or empty");
        mMaxBitmapSize = context.getResources().getDimensionPixelSize(0x1050038);
        mCbStub = new CallbackStub(this);
        MediaSessionManager mediasessionmanager = (MediaSessionManager)context.getSystemService("media_session");
        try
        {
            mBinder = mediasessionmanager.createSession(mCbStub, s, i);
            s = JVM INSTR new #25  <Class MediaSession$Token>;
            s.Token(mBinder.getController());
            mSessionToken = s;
            s = JVM INSTR new #254 <Class MediaController>;
            s.MediaController(context, mSessionToken);
            mController = s;
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new RuntimeException("Remote error creating session.", context);
        }
    }

    private void dispatchAdjustVolume(int i)
    {
        postToCallback(21, Integer.valueOf(i));
    }

    private void dispatchCustomAction(String s, Bundle bundle)
    {
        postToCallback(20, s, bundle);
    }

    private void dispatchFastForward()
    {
        postToCallback(16);
    }

    private void dispatchMediaButton(Intent intent)
    {
        postToCallback(2, intent);
    }

    private void dispatchNext()
    {
        postToCallback(14);
    }

    private void dispatchPause()
    {
        postToCallback(12);
    }

    private void dispatchPlay()
    {
        postToCallback(7);
    }

    private void dispatchPlayFromMediaId(String s, Bundle bundle)
    {
        postToCallback(8, s, bundle);
    }

    private void dispatchPlayFromSearch(String s, Bundle bundle)
    {
        postToCallback(9, s, bundle);
    }

    private void dispatchPlayFromUri(Uri uri, Bundle bundle)
    {
        postToCallback(10, uri, bundle);
    }

    private void dispatchPrepare()
    {
        postToCallback(3);
    }

    private void dispatchPrepareFromMediaId(String s, Bundle bundle)
    {
        postToCallback(4, s, bundle);
    }

    private void dispatchPrepareFromSearch(String s, Bundle bundle)
    {
        postToCallback(5, s, bundle);
    }

    private void dispatchPrepareFromUri(Uri uri, Bundle bundle)
    {
        postToCallback(6, uri, bundle);
    }

    private void dispatchPrevious()
    {
        postToCallback(15);
    }

    private void dispatchRate(Rating rating)
    {
        postToCallback(19, rating);
    }

    private void dispatchRewind()
    {
        postToCallback(17);
    }

    private void dispatchSeekTo(long l)
    {
        postToCallback(18, Long.valueOf(l));
    }

    private void dispatchSetVolumeTo(int i)
    {
        postToCallback(22, Integer.valueOf(i));
    }

    private void dispatchSkipToItem(long l)
    {
        postToCallback(11, Long.valueOf(l));
    }

    private void dispatchStop()
    {
        postToCallback(13);
    }

    public static boolean isActiveState(int i)
    {
        switch(i)
        {
        case 7: // '\007'
        default:
            return false;

        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
            return true;
        }
    }

    private void postCommand(String s, Bundle bundle, ResultReceiver resultreceiver)
    {
        postToCallback(1, new Command(s, bundle, resultreceiver));
    }

    private void postToCallback(int i)
    {
        postToCallback(i, null);
    }

    private void postToCallback(int i, Object obj)
    {
        postToCallback(i, obj, null);
    }

    private void postToCallback(int i, Object obj, Bundle bundle)
    {
        Object obj1 = mLock;
        obj1;
        JVM INSTR monitorenter ;
        if(mCallback != null)
            mCallback.post(i, obj, bundle);
        obj1;
        JVM INSTR monitorexit ;
        return;
        obj;
        throw obj;
    }

    public String getCallingPackage()
    {
        String s;
        try
        {
            s = mBinder.getCallingPackage();
        }
        catch(RemoteException remoteexception)
        {
            Log.wtf("MediaSession", "Dead object in getCallingPackage.", remoteexception);
            return null;
        }
        return s;
    }

    public MediaController getController()
    {
        return mController;
    }

    public Token getSessionToken()
    {
        return mSessionToken;
    }

    public boolean isActive()
    {
        return mActive;
    }

    public void notifyRemoteVolumeChanged(VolumeProvider volumeprovider)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(volumeprovider == null)
            break MISSING_BLOCK_LABEL_19;
        if(volumeprovider == mVolumeProvider)
            break MISSING_BLOCK_LABEL_31;
        Log.w("MediaSession", "Received update from stale volume provider");
        obj;
        JVM INSTR monitorexit ;
        return;
        obj;
        JVM INSTR monitorexit ;
        mBinder.setCurrentVolume(volumeprovider.getCurrentVolume());
_L1:
        return;
        volumeprovider;
        throw volumeprovider;
        volumeprovider;
        Log.e("MediaSession", "Error in notifyVolumeChanged", volumeprovider);
          goto _L1
    }

    public void release()
    {
        mBinder.destroy();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.wtf("MediaSession", "Error releasing session: ", remoteexception);
          goto _L1
    }

    public void sendSessionEvent(String s, Bundle bundle)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("event cannot be null or empty");
        mBinder.sendEvent(s, bundle);
_L1:
        return;
        s;
        Log.wtf("MediaSession", "Error sending event", s);
          goto _L1
    }

    public void setActive(boolean flag)
    {
        if(mActive == flag)
            return;
        mBinder.setActive(flag);
        mActive = flag;
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.wtf("MediaSession", "Failure in setActive.", remoteexception);
          goto _L1
    }

    public void setCallback(Callback callback)
    {
        setCallback(callback, null);
    }

    public void setCallback(Callback callback, Handler handler)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(callback != null)
            break MISSING_BLOCK_LABEL_38;
        if(mCallback != null)
            Callback._2D_set1(CallbackMessageHandler._2D_get0(mCallback), null);
        mCallback = null;
        obj;
        JVM INSTR monitorexit ;
        return;
        if(mCallback != null)
            Callback._2D_set1(CallbackMessageHandler._2D_get0(mCallback), null);
        Handler handler1;
        handler1 = handler;
        if(handler != null)
            break MISSING_BLOCK_LABEL_74;
        handler1 = JVM INSTR new #367 <Class Handler>;
        handler1.Handler();
        Callback._2D_set1(callback, this);
        handler = JVM INSTR new #11  <Class MediaSession$CallbackMessageHandler>;
        handler.this. CallbackMessageHandler(handler1.getLooper(), callback);
        mCallback = handler;
        obj;
        JVM INSTR monitorexit ;
        return;
        callback;
        throw callback;
    }

    public void setExtras(Bundle bundle)
    {
        mBinder.setExtras(bundle);
_L1:
        return;
        bundle;
        Log.wtf("Dead object in setExtras.", bundle);
          goto _L1
    }

    public void setFlags(int i)
    {
        mBinder.setFlags(i);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.wtf("MediaSession", "Failure in setFlags.", remoteexception);
          goto _L1
    }

    public void setMediaButtonReceiver(PendingIntent pendingintent)
    {
        mBinder.setMediaButtonReceiver(pendingintent);
_L1:
        return;
        pendingintent;
        Log.wtf("MediaSession", "Failure in setMediaButtonReceiver.", pendingintent);
          goto _L1
    }

    public void setMetadata(MediaMetadata mediametadata)
    {
        MediaMetadata mediametadata1;
        mediametadata1 = mediametadata;
        if(mediametadata != null)
            mediametadata1 = (new android.media.MediaMetadata.Builder(mediametadata, mMaxBitmapSize)).build();
        mBinder.setMetadata(mediametadata1);
_L1:
        return;
        mediametadata;
        Log.wtf("MediaSession", "Dead object in setPlaybackState.", mediametadata);
          goto _L1
    }

    public void setPlaybackState(PlaybackState playbackstate)
    {
        mPlaybackState = playbackstate;
        mBinder.setPlaybackState(playbackstate);
_L1:
        return;
        playbackstate;
        Log.wtf("MediaSession", "Dead object in setPlaybackState.", playbackstate);
          goto _L1
    }

    public void setPlaybackToLocal(AudioAttributes audioattributes)
    {
        if(audioattributes == null)
            throw new IllegalArgumentException("Attributes cannot be null for local playback.");
        mBinder.setPlaybackToLocal(audioattributes);
_L1:
        return;
        audioattributes;
        Log.wtf("MediaSession", "Failure in setPlaybackToLocal.", audioattributes);
          goto _L1
    }

    public void setPlaybackToRemote(VolumeProvider volumeprovider)
    {
        if(volumeprovider == null)
            throw new IllegalArgumentException("volumeProvider may not be null!");
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mVolumeProvider = volumeprovider;
        obj;
        JVM INSTR monitorexit ;
        volumeprovider.setCallback(new android.media.VolumeProvider.Callback() {

            public void onVolumeChanged(VolumeProvider volumeprovider1)
            {
                notifyRemoteVolumeChanged(volumeprovider1);
            }

            final MediaSession this$0;

            
            {
                this$0 = MediaSession.this;
                super();
            }
        }
);
        mBinder.setPlaybackToRemote(volumeprovider.getVolumeControl(), volumeprovider.getMaxVolume());
        mBinder.setCurrentVolume(volumeprovider.getCurrentVolume());
_L1:
        return;
        volumeprovider;
        throw volumeprovider;
        volumeprovider;
        Log.wtf("MediaSession", "Failure in setPlaybackToRemote.", volumeprovider);
          goto _L1
    }

    public void setQueue(List list)
    {
        Object obj = null;
        ISession isession = mBinder;
        if(list != null) goto _L2; else goto _L1
_L1:
        list = obj;
_L3:
        isession.setQueue(list);
_L4:
        return;
_L2:
        list = new ParceledListSlice(list);
          goto _L3
        list;
        Log.wtf("Dead object in setQueue.", list);
          goto _L4
    }

    public void setQueueTitle(CharSequence charsequence)
    {
        mBinder.setQueueTitle(charsequence);
_L1:
        return;
        charsequence;
        Log.wtf("Dead object in setQueueTitle.", charsequence);
          goto _L1
    }

    public void setRatingType(int i)
    {
        mBinder.setRatingType(i);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("MediaSession", "Error in setRatingType.", remoteexception);
          goto _L1
    }

    public void setSessionActivity(PendingIntent pendingintent)
    {
        mBinder.setLaunchPendingIntent(pendingintent);
_L1:
        return;
        pendingintent;
        Log.wtf("MediaSession", "Failure in setLaunchPendingIntent.", pendingintent);
          goto _L1
    }

    public static final int FLAG_EXCLUSIVE_GLOBAL_PRIORITY = 0x10000;
    public static final int FLAG_HANDLES_MEDIA_BUTTONS = 1;
    public static final int FLAG_HANDLES_TRANSPORT_CONTROLS = 2;
    private static final String TAG = "MediaSession";
    private boolean mActive;
    private final ISession mBinder;
    private CallbackMessageHandler mCallback;
    private final CallbackStub mCbStub;
    private final MediaController mController;
    private final Object mLock;
    private final int mMaxBitmapSize;
    private PlaybackState mPlaybackState;
    private final Token mSessionToken;
    private VolumeProvider mVolumeProvider;
}
