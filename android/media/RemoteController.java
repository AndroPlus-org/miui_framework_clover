// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.session.MediaController;
import android.media.session.MediaSessionLegacyHelper;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.os.*;
import android.util.*;
import android.view.KeyEvent;
import java.util.List;

// Referenced classes of package android.media:
//            MediaMetadata, MediaMetadataEditor, Rating

public final class RemoteController
{
    private class EventHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            boolean flag = true;
            message.what;
            JVM INSTR tableswitch 0 2: default 32
        //                       0 61
        //                       1 87
        //                       2 104;
               goto _L1 _L2 _L3 _L4
_L1:
            Log.e("RemoteController", (new StringBuilder()).append("unknown event ").append(message.what).toString());
_L6:
            return;
_L2:
            RemoteController remotecontroller = RemoteController.this;
            if(message.arg2 != 1)
                flag = false;
            RemoteController._2D_wrap0(remotecontroller, flag);
            continue; /* Loop/switch isn't completed */
_L3:
            RemoteController._2D_wrap2(RemoteController.this, (PlaybackState)message.obj);
            continue; /* Loop/switch isn't completed */
_L4:
            RemoteController._2D_wrap1(RemoteController.this, (MediaMetadata)message.obj);
            if(true) goto _L6; else goto _L5
_L5:
        }

        final RemoteController this$0;

        public EventHandler(RemoteController remotecontroller1, Looper looper)
        {
            this$0 = RemoteController.this;
            super(looper);
        }
    }

    private class MediaControllerCallback extends android.media.session.MediaController.Callback
    {

        public void onMetadataChanged(MediaMetadata mediametadata)
        {
            RemoteController._2D_wrap1(RemoteController.this, mediametadata);
        }

        public void onPlaybackStateChanged(PlaybackState playbackstate)
        {
            RemoteController._2D_wrap2(RemoteController.this, playbackstate);
        }

        final RemoteController this$0;

        private MediaControllerCallback()
        {
            this$0 = RemoteController.this;
            super();
        }

        MediaControllerCallback(MediaControllerCallback mediacontrollercallback)
        {
            this();
        }
    }

    public class MetadataEditor extends MediaMetadataEditor
    {

        private void cleanupBitmapFromBundle(int i)
        {
            if(METADATA_KEYS_TYPE.get(i, -1) == 2)
                mEditorMetadata.remove(String.valueOf(i));
        }

        public void apply()
        {
            this;
            JVM INSTR monitorenter ;
            boolean flag = mMetadataChanged;
            if(flag)
                break MISSING_BLOCK_LABEL_14;
            this;
            JVM INSTR monitorexit ;
            return;
            Object obj = RemoteController._2D_get1();
            obj;
            JVM INSTR monitorenter ;
            Rating rating;
            if(RemoteController._2D_get0(RemoteController.this) == null || !mEditorMetadata.containsKey(String.valueOf(0x10000001)))
                break MISSING_BLOCK_LABEL_74;
            rating = (Rating)getObject(0x10000001, null);
            if(rating == null)
                break MISSING_BLOCK_LABEL_74;
            RemoteController._2D_get0(RemoteController.this).getTransportControls().setRating(rating);
            obj;
            JVM INSTR monitorexit ;
            mApplied = false;
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception1;
            exception1;
            obj;
            JVM INSTR monitorexit ;
            throw exception1;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        final RemoteController this$0;

        protected MetadataEditor()
        {
            this$0 = RemoteController.this;
            super();
        }

        protected MetadataEditor(Bundle bundle, long l)
        {
            this$0 = RemoteController.this;
            super();
            mEditorMetadata = bundle;
            mEditableKeys = l;
            mEditorArtwork = (Bitmap)bundle.getParcelable(String.valueOf(100));
            if(mEditorArtwork != null)
                cleanupBitmapFromBundle(100);
            mMetadataChanged = true;
            mArtworkChanged = true;
            mApplied = false;
        }
    }

    public static interface OnClientUpdateListener
    {

        public abstract void onClientChange(boolean flag);

        public abstract void onClientMetadataUpdate(MetadataEditor metadataeditor);

        public abstract void onClientPlaybackStateUpdate(int i);

        public abstract void onClientPlaybackStateUpdate(int i, long l, long l1, float f);

        public abstract void onClientTransportControlUpdate(int i);
    }

    private static class PlaybackInfo
    {

        long mCurrentPosMs;
        float mSpeed;
        int mState;
        long mStateChangeTimeMs;

        PlaybackInfo(int i, long l, long l1, float f)
        {
            mState = i;
            mStateChangeTimeMs = l;
            mCurrentPosMs = l1;
            mSpeed = f;
        }
    }

    private class TopTransportSessionListener
        implements android.media.session.MediaSessionManager.OnActiveSessionsChangedListener
    {

        public void onActiveSessionsChanged(List list)
        {
            int i = list.size();
            for(int j = 0; j < i; j++)
            {
                MediaController mediacontroller = (MediaController)list.get(j);
                if((2L & mediacontroller.getFlags()) != 0L)
                {
                    RemoteController._2D_wrap3(RemoteController.this, mediacontroller);
                    return;
                }
            }

            RemoteController._2D_wrap3(RemoteController.this, null);
        }

        final RemoteController this$0;

        private TopTransportSessionListener()
        {
            this$0 = RemoteController.this;
            super();
        }

        TopTransportSessionListener(TopTransportSessionListener toptransportsessionlistener)
        {
            this();
        }
    }


    static MediaController _2D_get0(RemoteController remotecontroller)
    {
        return remotecontroller.mCurrentSession;
    }

    static Object _2D_get1()
    {
        return mInfoLock;
    }

    static void _2D_wrap0(RemoteController remotecontroller, boolean flag)
    {
        remotecontroller.onClientChange(flag);
    }

    static void _2D_wrap1(RemoteController remotecontroller, MediaMetadata mediametadata)
    {
        remotecontroller.onNewMediaMetadata(mediametadata);
    }

    static void _2D_wrap2(RemoteController remotecontroller, PlaybackState playbackstate)
    {
        remotecontroller.onNewPlaybackState(playbackstate);
    }

    static void _2D_wrap3(RemoteController remotecontroller, MediaController mediacontroller)
    {
        remotecontroller.updateController(mediacontroller);
    }

    public RemoteController(Context context, OnClientUpdateListener onclientupdatelistener)
        throws IllegalArgumentException
    {
        this(context, onclientupdatelistener, null);
    }

    public RemoteController(Context context, OnClientUpdateListener onclientupdatelistener, Looper looper)
        throws IllegalArgumentException
    {
        mSessionCb = new MediaControllerCallback(null);
        mIsRegistered = false;
        mArtworkWidth = -1;
        mArtworkHeight = -1;
        mEnabled = true;
        if(context == null)
            throw new IllegalArgumentException("Invalid null Context");
        if(onclientupdatelistener == null)
            throw new IllegalArgumentException("Invalid null OnClientUpdateListener");
        if(looper != null)
        {
            mEventHandler = new EventHandler(this, looper);
        } else
        {
            looper = Looper.myLooper();
            if(looper != null)
                mEventHandler = new EventHandler(this, looper);
            else
                throw new IllegalArgumentException("Calling thread not associated with a looper");
        }
        mOnClientUpdateListener = onclientupdatelistener;
        mContext = context;
        mSessionManager = (MediaSessionManager)context.getSystemService("media_session");
        mSessionListener = new TopTransportSessionListener(null);
        if(ActivityManager.isLowRamDeviceStatic())
        {
            mMaxBitmapDimension = 512;
        } else
        {
            context = context.getResources().getDisplayMetrics();
            mMaxBitmapDimension = Math.max(((DisplayMetrics) (context)).widthPixels, ((DisplayMetrics) (context)).heightPixels);
        }
    }

    private void onClientChange(boolean flag)
    {
        Object obj = mInfoLock;
        obj;
        JVM INSTR monitorenter ;
        OnClientUpdateListener onclientupdatelistener;
        onclientupdatelistener = mOnClientUpdateListener;
        mMetadataEditor = null;
        obj;
        JVM INSTR monitorexit ;
        if(onclientupdatelistener != null)
            onclientupdatelistener.onClientChange(flag);
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void onNewMediaMetadata(MediaMetadata mediametadata)
    {
        int i;
        i = 0;
        if(mediametadata == null)
            return;
        Object obj = mInfoLock;
        obj;
        JVM INSTR monitorenter ;
        OnClientUpdateListener onclientupdatelistener = mOnClientUpdateListener;
        boolean flag;
        long l;
        Bundle bundle;
        if(mCurrentSession != null)
        {
            if(mCurrentSession.getRatingType() != 0)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        if(flag)
            i = 0x10000001;
        l = i;
        bundle = MediaSessionLegacyHelper.getOldMetadata(mediametadata, mArtworkWidth, mArtworkHeight);
        mediametadata = JVM INSTR new #12  <Class RemoteController$MetadataEditor>;
        mediametadata.this. MetadataEditor(bundle, l);
        mMetadataEditor = mediametadata;
        mediametadata = mMetadataEditor;
        obj;
        JVM INSTR monitorexit ;
        if(onclientupdatelistener != null)
            onclientupdatelistener.onClientMetadataUpdate(mediametadata);
        return;
        mediametadata;
        throw mediametadata;
    }

    private void onNewPlaybackState(PlaybackState playbackstate)
    {
        Object obj = mInfoLock;
        obj;
        JVM INSTR monitorenter ;
        OnClientUpdateListener onclientupdatelistener = mOnClientUpdateListener;
        obj;
        JVM INSTR monitorexit ;
        if(onclientupdatelistener != null)
        {
            int i;
            if(playbackstate == null)
                i = 0;
            else
                i = PlaybackState.getRccStateFromState(playbackstate.getState());
            if(playbackstate == null || playbackstate.getPosition() == -1L)
                onclientupdatelistener.onClientPlaybackStateUpdate(i);
            else
                onclientupdatelistener.onClientPlaybackStateUpdate(i, playbackstate.getLastPositionUpdateTime(), playbackstate.getPosition(), playbackstate.getPlaybackSpeed());
            if(playbackstate != null)
                onclientupdatelistener.onClientTransportControlUpdate(PlaybackState.getRccControlFlagsFromActions(playbackstate.getActions()));
        }
        return;
        playbackstate;
        throw playbackstate;
    }

    private static void sendMsg(Handler handler, int i, int j, int k, int l, Object obj, int i1)
    {
        if(handler == null)
        {
            Log.e("RemoteController", (new StringBuilder()).append("null event handler, will not deliver message ").append(i).toString());
            return;
        }
        if(j == 0)
            handler.removeMessages(i);
        else
        if(j == 1 && handler.hasMessages(i))
            return;
        handler.sendMessageDelayed(handler.obtainMessage(i, k, l, obj), i1);
    }

    private void updateController(MediaController mediacontroller)
    {
        Object obj = mInfoLock;
        obj;
        JVM INSTR monitorenter ;
        if(mediacontroller != null) goto _L2; else goto _L1
_L1:
        if(mCurrentSession != null)
        {
            mCurrentSession.unregisterCallback(mSessionCb);
            mCurrentSession = null;
            sendMsg(mEventHandler, 0, 0, 0, 1, null, 0);
        }
_L4:
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(mCurrentSession != null && !(mediacontroller.getSessionToken().equals(mCurrentSession.getSessionToken()) ^ true)) goto _L4; else goto _L3
_L3:
        if(mCurrentSession != null)
            mCurrentSession.unregisterCallback(mSessionCb);
        sendMsg(mEventHandler, 0, 0, 0, 0, null, 0);
        mCurrentSession = mediacontroller;
        mCurrentSession.registerCallback(mSessionCb, mEventHandler);
        PlaybackState playbackstate = mediacontroller.getPlaybackState();
        sendMsg(mEventHandler, 1, 0, 0, 0, playbackstate, 0);
        mediacontroller = mediacontroller.getMetadata();
        sendMsg(mEventHandler, 2, 0, 0, 0, mediacontroller, 0);
          goto _L4
        mediacontroller;
        throw mediacontroller;
    }

    public boolean clearArtworkConfiguration()
    {
        return setArtworkConfiguration(false, -1, -1);
    }

    public MetadataEditor editMetadata()
    {
        MetadataEditor metadataeditor = new MetadataEditor();
        metadataeditor.mEditorMetadata = new Bundle();
        metadataeditor.mEditorArtwork = null;
        metadataeditor.mMetadataChanged = true;
        metadataeditor.mArtworkChanged = true;
        metadataeditor.mEditableKeys = 0L;
        return metadataeditor;
    }

    public long getEstimatedMediaPosition()
    {
        Object obj = mInfoLock;
        obj;
        JVM INSTR monitorenter ;
        PlaybackState playbackstate;
        if(mCurrentSession == null)
            break MISSING_BLOCK_LABEL_34;
        playbackstate = mCurrentSession.getPlaybackState();
        if(playbackstate == null)
            break MISSING_BLOCK_LABEL_34;
        long l = playbackstate.getPosition();
        obj;
        JVM INSTR monitorexit ;
        return l;
        obj;
        JVM INSTR monitorexit ;
        return -1L;
        Exception exception;
        exception;
        throw exception;
    }

    public String getRemoteControlClientPackageName()
    {
        String s = null;
        Object obj = mInfoLock;
        obj;
        JVM INSTR monitorenter ;
        if(mCurrentSession != null)
            s = mCurrentSession.getPackageName();
        obj;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    OnClientUpdateListener getUpdateListener()
    {
        return mOnClientUpdateListener;
    }

    public boolean seekTo(long l)
        throws IllegalArgumentException
    {
        if(!mEnabled)
        {
            Log.e("RemoteController", "Cannot use seekTo() from a disabled RemoteController");
            return false;
        }
        if(l < 0L)
            throw new IllegalArgumentException("illegal negative time value");
        Object obj = mInfoLock;
        obj;
        JVM INSTR monitorenter ;
        if(mCurrentSession != null)
            mCurrentSession.getTransportControls().seekTo(l);
        obj;
        JVM INSTR monitorexit ;
        return true;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean sendMediaKeyEvent(KeyEvent keyevent)
        throws IllegalArgumentException
    {
        if(!KeyEvent.isMediaKey(keyevent.getKeyCode()))
            throw new IllegalArgumentException("not a media key event");
        Object obj = mInfoLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        if(mCurrentSession == null)
            break MISSING_BLOCK_LABEL_47;
        flag = mCurrentSession.dispatchMediaButtonEvent(keyevent);
        return flag;
        obj;
        JVM INSTR monitorexit ;
        return false;
        keyevent;
        throw keyevent;
    }

    public boolean setArtworkConfiguration(int i, int j)
        throws IllegalArgumentException
    {
        return setArtworkConfiguration(true, i, j);
    }

    public boolean setArtworkConfiguration(boolean flag, int i, int j)
        throws IllegalArgumentException
    {
        Object obj = mInfoLock;
        obj;
        JVM INSTR monitorenter ;
        if(!flag)
            break MISSING_BLOCK_LABEL_92;
        if(i <= 0 || j <= 0) goto _L2; else goto _L1
_L1:
        int k = i;
        if(i > mMaxBitmapDimension)
            k = mMaxBitmapDimension;
        i = j;
        if(j > mMaxBitmapDimension)
            i = mMaxBitmapDimension;
        mArtworkWidth = k;
        mArtworkHeight = i;
_L3:
        obj;
        JVM INSTR monitorexit ;
        return true;
_L2:
        IllegalArgumentException illegalargumentexception = JVM INSTR new #109 <Class IllegalArgumentException>;
        illegalargumentexception.IllegalArgumentException("Invalid dimensions");
        throw illegalargumentexception;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        mArtworkWidth = -1;
        mArtworkHeight = -1;
          goto _L3
    }

    public boolean setSynchronizationMode(int i)
        throws IllegalArgumentException
    {
        if(i != 0 && i != 1)
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown synchronization mode ").append(i).toString());
        if(!mIsRegistered)
        {
            Log.e("RemoteController", "Cannot set synchronization mode on an unregistered RemoteController");
            return false;
        } else
        {
            return true;
        }
    }

    void startListeningToSessions()
    {
        ComponentName componentname = new ComponentName(mContext, mOnClientUpdateListener.getClass());
        Handler handler = null;
        if(Looper.myLooper() == null)
            handler = new Handler(Looper.getMainLooper());
        mSessionManager.addOnActiveSessionsChangedListener(mSessionListener, componentname, UserHandle.myUserId(), handler);
        mSessionListener.onActiveSessionsChanged(mSessionManager.getActiveSessions(componentname));
    }

    void stopListeningToSessions()
    {
        mSessionManager.removeOnActiveSessionsChangedListener(mSessionListener);
    }

    private static final boolean DEBUG = false;
    private static final int MAX_BITMAP_DIMENSION = 512;
    private static final int MSG_CLIENT_CHANGE = 0;
    private static final int MSG_NEW_MEDIA_METADATA = 2;
    private static final int MSG_NEW_PLAYBACK_STATE = 1;
    public static final int POSITION_SYNCHRONIZATION_CHECK = 1;
    public static final int POSITION_SYNCHRONIZATION_NONE = 0;
    private static final int SENDMSG_NOOP = 1;
    private static final int SENDMSG_QUEUE = 2;
    private static final int SENDMSG_REPLACE = 0;
    private static final String TAG = "RemoteController";
    private static final Object mInfoLock = new Object();
    private int mArtworkHeight;
    private int mArtworkWidth;
    private final Context mContext;
    private MediaController mCurrentSession;
    private boolean mEnabled;
    private final EventHandler mEventHandler;
    private boolean mIsRegistered;
    private PlaybackInfo mLastPlaybackInfo;
    private final int mMaxBitmapDimension;
    private MetadataEditor mMetadataEditor;
    private OnClientUpdateListener mOnClientUpdateListener;
    private android.media.session.MediaController.Callback mSessionCb;
    private android.media.session.MediaSessionManager.OnActiveSessionsChangedListener mSessionListener;
    private MediaSessionManager mSessionManager;

}
