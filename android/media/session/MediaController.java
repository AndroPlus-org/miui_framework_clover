// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ParceledListSlice;
import android.media.*;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.media.session:
//            ISessionController, ParcelableVolumeInfo, PlaybackState

public final class MediaController
{
    public static abstract class Callback
    {

        public void onAudioInfoChanged(PlaybackInfo playbackinfo)
        {
        }

        public void onExtrasChanged(Bundle bundle)
        {
        }

        public void onMetadataChanged(MediaMetadata mediametadata)
        {
        }

        public void onPlaybackStateChanged(PlaybackState playbackstate)
        {
        }

        public void onQueueChanged(List list)
        {
        }

        public void onQueueTitleChanged(CharSequence charsequence)
        {
        }

        public void onSessionDestroyed()
        {
        }

        public void onSessionEvent(String s, Bundle bundle)
        {
        }

        public Callback()
        {
        }
    }

    private static final class CallbackStub extends ISessionControllerCallback.Stub
    {

        public void onEvent(String s, Bundle bundle)
        {
            MediaController mediacontroller = (MediaController)mController.get();
            if(mediacontroller != null)
                MediaController._2D_wrap0(mediacontroller, 1, s, bundle);
        }

        public void onExtrasChanged(Bundle bundle)
        {
            MediaController mediacontroller = (MediaController)mController.get();
            if(mediacontroller != null)
                MediaController._2D_wrap0(mediacontroller, 7, bundle, null);
        }

        public void onMetadataChanged(MediaMetadata mediametadata)
        {
            MediaController mediacontroller = (MediaController)mController.get();
            if(mediacontroller != null)
                MediaController._2D_wrap0(mediacontroller, 3, mediametadata, null);
        }

        public void onPlaybackStateChanged(PlaybackState playbackstate)
        {
            MediaController mediacontroller = (MediaController)mController.get();
            if(mediacontroller != null)
                MediaController._2D_wrap0(mediacontroller, 2, playbackstate, null);
        }

        public void onQueueChanged(ParceledListSlice parceledlistslice)
        {
            MediaController mediacontroller;
            if(parceledlistslice == null)
                parceledlistslice = null;
            else
                parceledlistslice = parceledlistslice.getList();
            mediacontroller = (MediaController)mController.get();
            if(mediacontroller != null)
                MediaController._2D_wrap0(mediacontroller, 5, parceledlistslice, null);
        }

        public void onQueueTitleChanged(CharSequence charsequence)
        {
            MediaController mediacontroller = (MediaController)mController.get();
            if(mediacontroller != null)
                MediaController._2D_wrap0(mediacontroller, 6, charsequence, null);
        }

        public void onSessionDestroyed()
        {
            MediaController mediacontroller = (MediaController)mController.get();
            if(mediacontroller != null)
                MediaController._2D_wrap0(mediacontroller, 8, null, null);
        }

        public void onVolumeInfoChanged(ParcelableVolumeInfo parcelablevolumeinfo)
        {
            MediaController mediacontroller = (MediaController)mController.get();
            if(mediacontroller != null)
                MediaController._2D_wrap0(mediacontroller, 4, new PlaybackInfo(parcelablevolumeinfo.volumeType, parcelablevolumeinfo.audioAttrs, parcelablevolumeinfo.controlType, parcelablevolumeinfo.maxVolume, parcelablevolumeinfo.currentVolume), null);
        }

        private final WeakReference mController;

        public CallbackStub(MediaController mediacontroller)
        {
            mController = new WeakReference(mediacontroller);
        }
    }

    private static final class MessageHandler extends Handler
    {

        static Callback _2D_get0(MessageHandler messagehandler)
        {
            return messagehandler.mCallback;
        }

        static boolean _2D_set0(MessageHandler messagehandler, boolean flag)
        {
            messagehandler.mRegistered = flag;
            return flag;
        }

        public void handleMessage(Message message)
        {
            if(!mRegistered)
                return;
            message.what;
            JVM INSTR tableswitch 1 8: default 60
        //                       1 61
        //                       2 82
        //                       3 99
        //                       4 167
        //                       5 116
        //                       6 133
        //                       7 150
        //                       8 184;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L1:
            return;
_L2:
            mCallback.onSessionEvent((String)message.obj, message.getData());
            continue; /* Loop/switch isn't completed */
_L3:
            mCallback.onPlaybackStateChanged((PlaybackState)message.obj);
            continue; /* Loop/switch isn't completed */
_L4:
            mCallback.onMetadataChanged((MediaMetadata)message.obj);
            continue; /* Loop/switch isn't completed */
_L6:
            mCallback.onQueueChanged((List)message.obj);
            continue; /* Loop/switch isn't completed */
_L7:
            mCallback.onQueueTitleChanged((CharSequence)message.obj);
            continue; /* Loop/switch isn't completed */
_L8:
            mCallback.onExtrasChanged((Bundle)message.obj);
            continue; /* Loop/switch isn't completed */
_L5:
            mCallback.onAudioInfoChanged((PlaybackInfo)message.obj);
            continue; /* Loop/switch isn't completed */
_L9:
            mCallback.onSessionDestroyed();
            if(true) goto _L1; else goto _L10
_L10:
        }

        public void post(int i, Object obj, Bundle bundle)
        {
            obj = obtainMessage(i, obj);
            ((Message) (obj)).setData(bundle);
            ((Message) (obj)).sendToTarget();
        }

        private final Callback mCallback;
        private boolean mRegistered;

        public MessageHandler(Looper looper, Callback callback)
        {
            super(looper, null, true);
            mRegistered = false;
            mCallback = callback;
        }
    }

    public static final class PlaybackInfo
    {

        public AudioAttributes getAudioAttributes()
        {
            return mAudioAttrs;
        }

        public int getCurrentVolume()
        {
            return mCurrentVolume;
        }

        public int getMaxVolume()
        {
            return mMaxVolume;
        }

        public int getPlaybackType()
        {
            return mVolumeType;
        }

        public int getVolumeControl()
        {
            return mVolumeControl;
        }

        public static final int PLAYBACK_TYPE_LOCAL = 1;
        public static final int PLAYBACK_TYPE_REMOTE = 2;
        private final AudioAttributes mAudioAttrs;
        private final int mCurrentVolume;
        private final int mMaxVolume;
        private final int mVolumeControl;
        private final int mVolumeType;

        public PlaybackInfo(int i, AudioAttributes audioattributes, int j, int k, int l)
        {
            mVolumeType = i;
            mAudioAttrs = audioattributes;
            mVolumeControl = j;
            mMaxVolume = k;
            mCurrentVolume = l;
        }
    }

    public final class TransportControls
    {

        public void fastForward()
        {
            MediaController._2D_get0(MediaController.this).fastForward();
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.wtf("TransportController", "Error calling fastForward.", remoteexception);
              goto _L1
        }

        public void pause()
        {
            MediaController._2D_get0(MediaController.this).pause();
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.wtf("TransportController", "Error calling pause.", remoteexception);
              goto _L1
        }

        public void play()
        {
            MediaController._2D_get0(MediaController.this).play();
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.wtf("TransportController", "Error calling play.", remoteexception);
              goto _L1
        }

        public void playFromMediaId(String s, Bundle bundle)
        {
            if(TextUtils.isEmpty(s))
                throw new IllegalArgumentException("You must specify a non-empty String for playFromMediaId.");
            MediaController._2D_get0(MediaController.this).playFromMediaId(s, bundle);
_L1:
            return;
            bundle;
            Log.wtf("TransportController", (new StringBuilder()).append("Error calling play(").append(s).append(").").toString(), bundle);
              goto _L1
        }

        public void playFromSearch(String s, Bundle bundle)
        {
            String s1;
            s1 = s;
            if(s == null)
                s1 = "";
            MediaController._2D_get0(MediaController.this).playFromSearch(s1, bundle);
_L1:
            return;
            s;
            Log.wtf("TransportController", (new StringBuilder()).append("Error calling play(").append(s1).append(").").toString(), s);
              goto _L1
        }

        public void playFromUri(Uri uri, Bundle bundle)
        {
            if(uri == null || Uri.EMPTY.equals(uri))
                throw new IllegalArgumentException("You must specify a non-empty Uri for playFromUri.");
            MediaController._2D_get0(MediaController.this).playFromUri(uri, bundle);
_L1:
            return;
            bundle;
            Log.wtf("TransportController", (new StringBuilder()).append("Error calling play(").append(uri).append(").").toString(), bundle);
              goto _L1
        }

        public void prepare()
        {
            MediaController._2D_get0(MediaController.this).prepare();
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.wtf("TransportController", "Error calling prepare.", remoteexception);
              goto _L1
        }

        public void prepareFromMediaId(String s, Bundle bundle)
        {
            if(TextUtils.isEmpty(s))
                throw new IllegalArgumentException("You must specify a non-empty String for prepareFromMediaId.");
            MediaController._2D_get0(MediaController.this).prepareFromMediaId(s, bundle);
_L1:
            return;
            bundle;
            Log.wtf("TransportController", (new StringBuilder()).append("Error calling prepare(").append(s).append(").").toString(), bundle);
              goto _L1
        }

        public void prepareFromSearch(String s, Bundle bundle)
        {
            String s1;
            s1 = s;
            if(s == null)
                s1 = "";
            MediaController._2D_get0(MediaController.this).prepareFromSearch(s1, bundle);
_L1:
            return;
            s;
            Log.wtf("TransportController", (new StringBuilder()).append("Error calling prepare(").append(s1).append(").").toString(), s);
              goto _L1
        }

        public void prepareFromUri(Uri uri, Bundle bundle)
        {
            if(uri == null || Uri.EMPTY.equals(uri))
                throw new IllegalArgumentException("You must specify a non-empty Uri for prepareFromUri.");
            MediaController._2D_get0(MediaController.this).prepareFromUri(uri, bundle);
_L1:
            return;
            bundle;
            Log.wtf("TransportController", (new StringBuilder()).append("Error calling prepare(").append(uri).append(").").toString(), bundle);
              goto _L1
        }

        public void rewind()
        {
            MediaController._2D_get0(MediaController.this).rewind();
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.wtf("TransportController", "Error calling rewind.", remoteexception);
              goto _L1
        }

        public void seekTo(long l)
        {
            MediaController._2D_get0(MediaController.this).seekTo(l);
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.wtf("TransportController", "Error calling seekTo.", remoteexception);
              goto _L1
        }

        public void sendCustomAction(PlaybackState.CustomAction customaction, Bundle bundle)
        {
            if(customaction == null)
            {
                throw new IllegalArgumentException("CustomAction cannot be null.");
            } else
            {
                sendCustomAction(customaction.getAction(), bundle);
                return;
            }
        }

        public void sendCustomAction(String s, Bundle bundle)
        {
            if(TextUtils.isEmpty(s))
                throw new IllegalArgumentException("CustomAction cannot be null.");
            MediaController._2D_get0(MediaController.this).sendCustomAction(s, bundle);
_L1:
            return;
            s;
            Log.d("TransportController", "Dead object in sendCustomAction.", s);
              goto _L1
        }

        public void setRating(Rating rating)
        {
            MediaController._2D_get0(MediaController.this).rate(rating);
_L1:
            return;
            rating;
            Log.wtf("TransportController", "Error calling rate.", rating);
              goto _L1
        }

        public void skipToNext()
        {
            MediaController._2D_get0(MediaController.this).next();
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.wtf("TransportController", "Error calling next.", remoteexception);
              goto _L1
        }

        public void skipToPrevious()
        {
            MediaController._2D_get0(MediaController.this).previous();
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.wtf("TransportController", "Error calling previous.", remoteexception);
              goto _L1
        }

        public void skipToQueueItem(long l)
        {
            MediaController._2D_get0(MediaController.this).skipToQueueItem(l);
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.wtf("TransportController", (new StringBuilder()).append("Error calling skipToItem(").append(l).append(").").toString(), remoteexception);
              goto _L1
        }

        public void stop()
        {
            MediaController._2D_get0(MediaController.this).stop();
_L1:
            return;
            RemoteException remoteexception;
            remoteexception;
            Log.wtf("TransportController", "Error calling stop.", remoteexception);
              goto _L1
        }

        private static final String TAG = "TransportController";
        final MediaController this$0;

        private TransportControls()
        {
            this$0 = MediaController.this;
            super();
        }

        TransportControls(TransportControls transportcontrols)
        {
            this();
        }
    }


    static ISessionController _2D_get0(MediaController mediacontroller)
    {
        return mediacontroller.mSessionBinder;
    }

    static void _2D_wrap0(MediaController mediacontroller, int i, Object obj, Bundle bundle)
    {
        mediacontroller.postMessage(i, obj, bundle);
    }

    public MediaController(Context context, ISessionController isessioncontroller)
    {
        mCbStub = new CallbackStub(this);
        mCallbacks = new ArrayList();
        mLock = new Object();
        mCbRegistered = false;
        if(isessioncontroller == null)
            throw new IllegalArgumentException("Session token cannot be null");
        if(context == null)
        {
            throw new IllegalArgumentException("Context cannot be null");
        } else
        {
            mSessionBinder = isessioncontroller;
            mTransportControls = new TransportControls(null);
            mToken = new MediaSession.Token(isessioncontroller);
            mContext = context;
            return;
        }
    }

    public MediaController(Context context, MediaSession.Token token)
    {
        this(context, token.getBinder());
    }

    private void addCallbackLocked(Callback callback, Handler handler)
    {
        if(getHandlerForCallbackLocked(callback) != null)
        {
            Log.w("MediaController", "Callback is already added, ignoring");
            return;
        }
        callback = new MessageHandler(handler.getLooper(), callback);
        mCallbacks.add(callback);
        MessageHandler._2D_set0(callback, true);
        if(mCbRegistered)
            break MISSING_BLOCK_LABEL_70;
        mSessionBinder.registerCallbackListener(mCbStub);
        mCbRegistered = true;
_L1:
        return;
        callback;
        Log.e("MediaController", "Dead object in registerCallback", callback);
          goto _L1
    }

    private MessageHandler getHandlerForCallbackLocked(Callback callback)
    {
        if(callback == null)
            throw new IllegalArgumentException("Callback cannot be null");
        for(int i = mCallbacks.size() - 1; i >= 0; i--)
        {
            MessageHandler messagehandler = (MessageHandler)mCallbacks.get(i);
            if(callback == MessageHandler._2D_get0(messagehandler))
                return messagehandler;
        }

        return null;
    }

    private final void postMessage(int i, Object obj, Bundle bundle)
    {
        Object obj1 = mLock;
        obj1;
        JVM INSTR monitorenter ;
        int j = mCallbacks.size() - 1;
_L2:
        if(j < 0)
            break; /* Loop/switch isn't completed */
        ((MessageHandler)mCallbacks.get(j)).post(i, obj, bundle);
        j--;
        if(true) goto _L2; else goto _L1
_L1:
        return;
        obj;
        throw obj;
    }

    private boolean removeCallbackLocked(Callback callback)
    {
        boolean flag = false;
        for(int i = mCallbacks.size() - 1; i >= 0; i--)
        {
            MessageHandler messagehandler = (MessageHandler)mCallbacks.get(i);
            if(callback == MessageHandler._2D_get0(messagehandler))
            {
                mCallbacks.remove(i);
                flag = true;
                MessageHandler._2D_set0(messagehandler, false);
            }
        }

        if(mCbRegistered && mCallbacks.size() == 0)
        {
            try
            {
                mSessionBinder.unregisterCallbackListener(mCbStub);
            }
            // Misplaced declaration of an exception variable
            catch(Callback callback)
            {
                Log.e("MediaController", "Dead object in removeCallbackLocked");
            }
            mCbRegistered = false;
        }
        return flag;
    }

    public void adjustVolume(int i, int j)
    {
        mSessionBinder.adjustVolume(i, j, mContext.getPackageName());
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.wtf("MediaController", "Error calling adjustVolumeBy.", remoteexception);
          goto _L1
    }

    public boolean controlsSameSession(MediaController mediacontroller)
    {
        boolean flag = false;
        if(mediacontroller == null)
            return false;
        if(mSessionBinder.asBinder() == mediacontroller.getSessionBinder().asBinder())
            flag = true;
        return flag;
    }

    public boolean dispatchMediaButtonEvent(KeyEvent keyevent)
    {
        if(keyevent == null)
            throw new IllegalArgumentException("KeyEvent may not be null");
        if(!KeyEvent.isMediaKey(keyevent.getKeyCode()))
            return false;
        boolean flag;
        try
        {
            flag = mSessionBinder.sendMediaButton(keyevent);
        }
        // Misplaced declaration of an exception variable
        catch(KeyEvent keyevent)
        {
            return false;
        }
        return flag;
    }

    public Bundle getExtras()
    {
        Bundle bundle;
        try
        {
            bundle = mSessionBinder.getExtras();
        }
        catch(RemoteException remoteexception)
        {
            Log.wtf("MediaController", "Error calling getExtras", remoteexception);
            return null;
        }
        return bundle;
    }

    public long getFlags()
    {
        long l;
        try
        {
            l = mSessionBinder.getFlags();
        }
        catch(RemoteException remoteexception)
        {
            Log.wtf("MediaController", "Error calling getFlags.", remoteexception);
            return 0L;
        }
        return l;
    }

    public MediaMetadata getMetadata()
    {
        MediaMetadata mediametadata;
        try
        {
            mediametadata = mSessionBinder.getMetadata();
        }
        catch(RemoteException remoteexception)
        {
            Log.wtf("MediaController", "Error calling getMetadata.", remoteexception);
            return null;
        }
        return mediametadata;
    }

    public String getPackageName()
    {
        if(mPackageName == null)
            try
            {
                mPackageName = mSessionBinder.getPackageName();
            }
            catch(RemoteException remoteexception)
            {
                Log.d("MediaController", "Dead object in getPackageName.", remoteexception);
            }
        return mPackageName;
    }

    public PlaybackInfo getPlaybackInfo()
    {
        Object obj;
        try
        {
            obj = mSessionBinder.getVolumeAttributes();
            obj = new PlaybackInfo(((ParcelableVolumeInfo) (obj)).volumeType, ((ParcelableVolumeInfo) (obj)).audioAttrs, ((ParcelableVolumeInfo) (obj)).controlType, ((ParcelableVolumeInfo) (obj)).maxVolume, ((ParcelableVolumeInfo) (obj)).currentVolume);
        }
        catch(RemoteException remoteexception)
        {
            Log.wtf("MediaController", "Error calling getAudioInfo.", remoteexception);
            return null;
        }
        return ((PlaybackInfo) (obj));
    }

    public PlaybackState getPlaybackState()
    {
        PlaybackState playbackstate;
        try
        {
            playbackstate = mSessionBinder.getPlaybackState();
        }
        catch(RemoteException remoteexception)
        {
            Log.wtf("MediaController", "Error calling getPlaybackState.", remoteexception);
            return null;
        }
        return playbackstate;
    }

    public List getQueue()
    {
        Object obj = mSessionBinder.getQueue();
        if(obj == null)
            break MISSING_BLOCK_LABEL_32;
        obj = ((ParceledListSlice) (obj)).getList();
        return ((List) (obj));
        RemoteException remoteexception;
        remoteexception;
        Log.wtf("MediaController", "Error calling getQueue.", remoteexception);
        return null;
    }

    public CharSequence getQueueTitle()
    {
        CharSequence charsequence;
        try
        {
            charsequence = mSessionBinder.getQueueTitle();
        }
        catch(RemoteException remoteexception)
        {
            Log.wtf("MediaController", "Error calling getQueueTitle", remoteexception);
            return null;
        }
        return charsequence;
    }

    public int getRatingType()
    {
        int i;
        try
        {
            i = mSessionBinder.getRatingType();
        }
        catch(RemoteException remoteexception)
        {
            Log.wtf("MediaController", "Error calling getRatingType.", remoteexception);
            return 0;
        }
        return i;
    }

    public PendingIntent getSessionActivity()
    {
        PendingIntent pendingintent;
        try
        {
            pendingintent = mSessionBinder.getLaunchPendingIntent();
        }
        catch(RemoteException remoteexception)
        {
            Log.wtf("MediaController", "Error calling getPendingIntent.", remoteexception);
            return null;
        }
        return pendingintent;
    }

    ISessionController getSessionBinder()
    {
        return mSessionBinder;
    }

    public MediaSession.Token getSessionToken()
    {
        return mToken;
    }

    public String getTag()
    {
        if(mTag == null)
            try
            {
                mTag = mSessionBinder.getTag();
            }
            catch(RemoteException remoteexception)
            {
                Log.d("MediaController", "Dead object in getTag.", remoteexception);
            }
        return mTag;
    }

    public TransportControls getTransportControls()
    {
        return mTransportControls;
    }

    public void registerCallback(Callback callback)
    {
        registerCallback(callback, null);
    }

    public void registerCallback(Callback callback, Handler handler)
    {
        Handler handler1;
        if(callback == null)
            throw new IllegalArgumentException("callback must not be null");
        handler1 = handler;
        if(handler == null)
            handler1 = new Handler();
        handler = ((Handler) (mLock));
        handler;
        JVM INSTR monitorenter ;
        addCallbackLocked(callback, handler1);
        handler;
        JVM INSTR monitorexit ;
        return;
        callback;
        throw callback;
    }

    public void sendCommand(String s, Bundle bundle, ResultReceiver resultreceiver)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("command cannot be null or empty");
        mSessionBinder.sendCommand(s, bundle, resultreceiver);
_L1:
        return;
        s;
        Log.d("MediaController", "Dead object in sendCommand.", s);
          goto _L1
    }

    public void setVolumeTo(int i, int j)
    {
        mSessionBinder.setVolumeTo(i, j, mContext.getPackageName());
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.wtf("MediaController", "Error calling setVolumeTo.", remoteexception);
          goto _L1
    }

    public void unregisterCallback(Callback callback)
    {
        if(callback == null)
            throw new IllegalArgumentException("callback must not be null");
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        removeCallbackLocked(callback);
        obj;
        JVM INSTR monitorexit ;
        return;
        callback;
        throw callback;
    }

    private static final int MSG_DESTROYED = 8;
    private static final int MSG_EVENT = 1;
    private static final int MSG_UPDATE_EXTRAS = 7;
    private static final int MSG_UPDATE_METADATA = 3;
    private static final int MSG_UPDATE_PLAYBACK_STATE = 2;
    private static final int MSG_UPDATE_QUEUE = 5;
    private static final int MSG_UPDATE_QUEUE_TITLE = 6;
    private static final int MSG_UPDATE_VOLUME = 4;
    private static final String TAG = "MediaController";
    private final ArrayList mCallbacks;
    private boolean mCbRegistered;
    private final CallbackStub mCbStub;
    private final Context mContext;
    private final Object mLock;
    private String mPackageName;
    private final ISessionController mSessionBinder;
    private String mTag;
    private final MediaSession.Token mToken;
    private final TransportControls mTransportControls;
}
