// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.media.session.MediaSession;
import android.media.session.MediaSessionLegacyHelper;
import android.media.session.PlaybackState;
import android.os.*;
import android.util.Log;

// Referenced classes of package android.media:
//            MediaMetadata, MediaMetadataEditor, Rating

public class RemoteControlClient
{
    public class MetadataEditor extends MediaMetadataEditor
    {

        public void apply()
        {
            this;
            JVM INSTR monitorenter ;
            if(!mApplied)
                break MISSING_BLOCK_LABEL_20;
            Log.e("RemoteControlClient", "Can't apply a previously applied MetadataEditor");
            this;
            JVM INSTR monitorexit ;
            return;
            Object obj = RemoteControlClient._2D_get0(RemoteControlClient.this);
            obj;
            JVM INSTR monitorenter ;
            RemoteControlClient remotecontrolclient = RemoteControlClient.this;
            Bundle bundle = JVM INSTR new #46  <Class Bundle>;
            bundle.Bundle(mEditorMetadata);
            RemoteControlClient._2D_set1(remotecontrolclient, bundle);
            RemoteControlClient._2D_get3(RemoteControlClient.this).putLong(String.valueOf(0x1fffffff), mEditableKeys);
            if(RemoteControlClient._2D_get4(RemoteControlClient.this) != null && RemoteControlClient._2D_get4(RemoteControlClient.this).equals(mEditorArtwork) ^ true)
                RemoteControlClient._2D_get4(RemoteControlClient.this).recycle();
            RemoteControlClient._2D_set2(RemoteControlClient.this, mEditorArtwork);
            mEditorArtwork = null;
            if(RemoteControlClient._2D_get5(RemoteControlClient.this) != null && mMetadataBuilder != null)
            {
                RemoteControlClient._2D_set0(RemoteControlClient.this, mMetadataBuilder.build());
                RemoteControlClient._2D_get5(RemoteControlClient.this).setMetadata(RemoteControlClient._2D_get2(RemoteControlClient.this));
            }
            mApplied = true;
            obj;
            JVM INSTR monitorexit ;
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

        public void clear()
        {
            this;
            JVM INSTR monitorenter ;
            super.clear();
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public Object clone()
            throws CloneNotSupportedException
        {
            throw new CloneNotSupportedException();
        }

        public volatile MediaMetadataEditor putBitmap(int i, Bitmap bitmap)
            throws IllegalArgumentException
        {
            return putBitmap(i, bitmap);
        }

        public MetadataEditor putBitmap(int i, Bitmap bitmap)
            throws IllegalArgumentException
        {
            this;
            JVM INSTR monitorenter ;
            String s;
            super.putBitmap(i, bitmap);
            if(mMetadataBuilder == null)
                break MISSING_BLOCK_LABEL_35;
            s = MediaMetadata.getKeyFromMetadataEditorKey(i);
            if(s == null)
                break MISSING_BLOCK_LABEL_35;
            mMetadataBuilder.putBitmap(s, bitmap);
            this;
            JVM INSTR monitorexit ;
            return this;
            bitmap;
            throw bitmap;
        }

        public volatile MediaMetadataEditor putLong(int i, long l)
            throws IllegalArgumentException
        {
            return putLong(i, l);
        }

        public MetadataEditor putLong(int i, long l)
            throws IllegalArgumentException
        {
            this;
            JVM INSTR monitorenter ;
            String s;
            super.putLong(i, l);
            if(mMetadataBuilder == null)
                break MISSING_BLOCK_LABEL_38;
            s = MediaMetadata.getKeyFromMetadataEditorKey(i);
            if(s == null)
                break MISSING_BLOCK_LABEL_38;
            mMetadataBuilder.putLong(s, l);
            this;
            JVM INSTR monitorexit ;
            return this;
            Exception exception;
            exception;
            throw exception;
        }

        public volatile MediaMetadataEditor putObject(int i, Object obj)
            throws IllegalArgumentException
        {
            return putObject(i, obj);
        }

        public MetadataEditor putObject(int i, Object obj)
            throws IllegalArgumentException
        {
            this;
            JVM INSTR monitorenter ;
            super.putObject(i, obj);
            if(mMetadataBuilder == null || i != 0x10000001 && i != 101)
                break MISSING_BLOCK_LABEL_50;
            String s = MediaMetadata.getKeyFromMetadataEditorKey(i);
            if(s == null)
                break MISSING_BLOCK_LABEL_50;
            mMetadataBuilder.putRating(s, (Rating)obj);
            this;
            JVM INSTR monitorexit ;
            return this;
            obj;
            throw obj;
        }

        public volatile MediaMetadataEditor putString(int i, String s)
            throws IllegalArgumentException
        {
            return putString(i, s);
        }

        public MetadataEditor putString(int i, String s)
            throws IllegalArgumentException
        {
            this;
            JVM INSTR monitorenter ;
            String s1;
            super.putString(i, s);
            if(mMetadataBuilder == null)
                break MISSING_BLOCK_LABEL_35;
            s1 = MediaMetadata.getKeyFromMetadataEditorKey(i);
            if(s1 == null)
                break MISSING_BLOCK_LABEL_35;
            mMetadataBuilder.putText(s1, s);
            this;
            JVM INSTR monitorexit ;
            return this;
            s;
            throw s;
        }

        public static final int BITMAP_KEY_ARTWORK = 100;
        public static final int METADATA_KEY_ARTWORK = 100;
        final RemoteControlClient this$0;

        private MetadataEditor()
        {
            this$0 = RemoteControlClient.this;
            super();
        }

        MetadataEditor(MetadataEditor metadataeditor)
        {
            this();
        }
    }

    public static interface OnGetPlaybackPositionListener
    {

        public abstract long onGetPlaybackPosition();
    }

    public static interface OnMetadataUpdateListener
    {

        public abstract void onMetadataUpdate(int i, Object obj);
    }

    public static interface OnPlaybackPositionUpdateListener
    {

        public abstract void onPlaybackPositionUpdate(long l);
    }


    static Object _2D_get0(RemoteControlClient remotecontrolclient)
    {
        return remotecontrolclient.mCacheLock;
    }

    static int _2D_get1(RemoteControlClient remotecontrolclient)
    {
        return remotecontrolclient.mCurrentClientGenId;
    }

    static MediaMetadata _2D_get2(RemoteControlClient remotecontrolclient)
    {
        return remotecontrolclient.mMediaMetadata;
    }

    static Bundle _2D_get3(RemoteControlClient remotecontrolclient)
    {
        return remotecontrolclient.mMetadata;
    }

    static Bitmap _2D_get4(RemoteControlClient remotecontrolclient)
    {
        return remotecontrolclient.mOriginalArtwork;
    }

    static MediaSession _2D_get5(RemoteControlClient remotecontrolclient)
    {
        return remotecontrolclient.mSession;
    }

    static int _2D_get6(RemoteControlClient remotecontrolclient)
    {
        return remotecontrolclient.mTransportControlFlags;
    }

    static MediaMetadata _2D_set0(RemoteControlClient remotecontrolclient, MediaMetadata mediametadata)
    {
        remotecontrolclient.mMediaMetadata = mediametadata;
        return mediametadata;
    }

    static Bundle _2D_set1(RemoteControlClient remotecontrolclient, Bundle bundle)
    {
        remotecontrolclient.mMetadata = bundle;
        return bundle;
    }

    static Bitmap _2D_set2(RemoteControlClient remotecontrolclient, Bitmap bitmap)
    {
        remotecontrolclient.mOriginalArtwork = bitmap;
        return bitmap;
    }

    static void _2D_wrap0(RemoteControlClient remotecontrolclient, int i, long l)
    {
        remotecontrolclient.onSeekTo(i, l);
    }

    static void _2D_wrap1(RemoteControlClient remotecontrolclient, int i, int j, Object obj)
    {
        remotecontrolclient.onUpdateMetadata(i, j, obj);
    }

    public RemoteControlClient(PendingIntent pendingintent)
    {
        mCacheLock = new Object();
        mPlaybackState = 0;
        mPlaybackStateChangeTimeMs = 0L;
        mPlaybackPositionMs = -1L;
        mPlaybackSpeed = 1.0F;
        mTransportControlFlags = 0;
        mMetadata = new Bundle();
        mCurrentClientGenId = -1;
        mNeedsPositionSync = false;
        mSessionPlaybackState = null;
        mRcMediaIntent = pendingintent;
    }

    public RemoteControlClient(PendingIntent pendingintent, Looper looper)
    {
        mCacheLock = new Object();
        mPlaybackState = 0;
        mPlaybackStateChangeTimeMs = 0L;
        mPlaybackPositionMs = -1L;
        mPlaybackSpeed = 1.0F;
        mTransportControlFlags = 0;
        mMetadata = new Bundle();
        mCurrentClientGenId = -1;
        mNeedsPositionSync = false;
        mSessionPlaybackState = null;
        mRcMediaIntent = pendingintent;
    }

    private static long getCheckPeriodFromSpeed(float f)
    {
        if(Math.abs(f) <= 1.0F)
            return 15000L;
        else
            return Math.max((long)(15000F / Math.abs(f)), 2000L);
    }

    private void onSeekTo(int i, long l)
    {
        Object obj = mCacheLock;
        obj;
        JVM INSTR monitorenter ;
        if(mCurrentClientGenId == i && mPositionUpdateListener != null)
            mPositionUpdateListener.onPlaybackPositionUpdate(l);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void onUpdateMetadata(int i, int j, Object obj)
    {
        Object obj1 = mCacheLock;
        obj1;
        JVM INSTR monitorenter ;
        if(mCurrentClientGenId == i && mMetadataUpdateListener != null)
            mMetadataUpdateListener.onMetadataUpdate(j, obj);
        obj1;
        JVM INSTR monitorexit ;
        return;
        obj;
        throw obj;
    }

    static boolean playbackPositionShouldMove(int i)
    {
        switch(i)
        {
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        default:
            return true;

        case 1: // '\001'
        case 2: // '\002'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
            return false;
        }
    }

    private void setPlaybackStateInt(int i, long l, float f, boolean flag)
    {
        Object obj = mCacheLock;
        obj;
        JVM INSTR monitorenter ;
        if(mPlaybackState == i && mPlaybackPositionMs == l) goto _L2; else goto _L1
_L1:
        mPlaybackState = i;
        if(!flag) goto _L4; else goto _L3
_L3:
        if(l >= 0L) goto _L6; else goto _L5
_L5:
        mPlaybackPositionMs = -1L;
_L9:
        mPlaybackSpeed = f;
        mPlaybackStateChangeTimeMs = SystemClock.elapsedRealtime();
        if(mSession == null) goto _L8; else goto _L7
_L7:
        i = PlaybackState.getStateFromRccState(i);
        if(!flag)
            break MISSING_BLOCK_LABEL_181;
        l = mPlaybackPositionMs;
_L10:
        android.media.session.PlaybackState.Builder builder = JVM INSTR new #257 <Class android.media.session.PlaybackState$Builder>;
        builder.android.media.session.PlaybackState.Builder(mSessionPlaybackState);
        builder.setState(i, l, f, SystemClock.elapsedRealtime());
        builder.setErrorMessage(null);
        mSessionPlaybackState = builder.build();
        mSession.setPlaybackState(mSessionPlaybackState);
_L8:
        obj;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(mPlaybackSpeed == f) goto _L8; else goto _L1
_L6:
        mPlaybackPositionMs = l;
          goto _L9
        Exception exception;
        exception;
        throw exception;
_L4:
        mPlaybackPositionMs = 0x8019771980198300L;
          goto _L9
        l = -1L;
          goto _L10
    }

    public MetadataEditor editMetadata(boolean flag)
    {
        MetadataEditor metadataeditor = new MetadataEditor(null);
        if(flag)
        {
            metadataeditor.mEditorMetadata = new Bundle();
            metadataeditor.mEditorArtwork = null;
            metadataeditor.mMetadataChanged = true;
            metadataeditor.mArtworkChanged = true;
            metadataeditor.mEditableKeys = 0L;
        } else
        {
            metadataeditor.mEditorMetadata = new Bundle(mMetadata);
            metadataeditor.mEditorArtwork = mOriginalArtwork;
            metadataeditor.mMetadataChanged = false;
            metadataeditor.mArtworkChanged = false;
        }
        if(flag || mMediaMetadata == null)
            metadataeditor.mMetadataBuilder = new MediaMetadata.Builder();
        else
            metadataeditor.mMetadataBuilder = new MediaMetadata.Builder(mMediaMetadata);
        return metadataeditor;
    }

    public MediaSession getMediaSession()
    {
        return mSession;
    }

    public PendingIntent getRcMediaIntent()
    {
        return mRcMediaIntent;
    }

    public void registerWithSession(MediaSessionLegacyHelper mediasessionlegacyhelper)
    {
        mediasessionlegacyhelper.addRccListener(mRcMediaIntent, mTransportListener);
        mSession = mediasessionlegacyhelper.getSession(mRcMediaIntent);
        setTransportControlFlags(mTransportControlFlags);
    }

    public void setMetadataUpdateListener(OnMetadataUpdateListener onmetadataupdatelistener)
    {
        Object obj = mCacheLock;
        obj;
        JVM INSTR monitorenter ;
        mMetadataUpdateListener = onmetadataupdatelistener;
        obj;
        JVM INSTR monitorexit ;
        return;
        onmetadataupdatelistener;
        throw onmetadataupdatelistener;
    }

    public void setOnGetPlaybackPositionListener(OnGetPlaybackPositionListener ongetplaybackpositionlistener)
    {
        Object obj = mCacheLock;
        obj;
        JVM INSTR monitorenter ;
        mPositionProvider = ongetplaybackpositionlistener;
        obj;
        JVM INSTR monitorexit ;
        return;
        ongetplaybackpositionlistener;
        throw ongetplaybackpositionlistener;
    }

    public void setPlaybackPositionUpdateListener(OnPlaybackPositionUpdateListener onplaybackpositionupdatelistener)
    {
        Object obj = mCacheLock;
        obj;
        JVM INSTR monitorenter ;
        mPositionUpdateListener = onplaybackpositionupdatelistener;
        obj;
        JVM INSTR monitorexit ;
        return;
        onplaybackpositionupdatelistener;
        throw onplaybackpositionupdatelistener;
    }

    public void setPlaybackState(int i)
    {
        setPlaybackStateInt(i, 0x8019771980198300L, 1.0F, false);
    }

    public void setPlaybackState(int i, long l, float f)
    {
        setPlaybackStateInt(i, l, f, true);
    }

    public void setTransportControlFlags(int i)
    {
        Object obj = mCacheLock;
        obj;
        JVM INSTR monitorenter ;
        mTransportControlFlags = i;
        if(mSession != null)
        {
            android.media.session.PlaybackState.Builder builder = JVM INSTR new #257 <Class android.media.session.PlaybackState$Builder>;
            builder.android.media.session.PlaybackState.Builder(mSessionPlaybackState);
            builder.setActions(PlaybackState.getActionsFromRccControlFlags(i));
            mSessionPlaybackState = builder.build();
            mSession.setPlaybackState(mSessionPlaybackState);
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void unregisterWithSession(MediaSessionLegacyHelper mediasessionlegacyhelper)
    {
        mediasessionlegacyhelper.removeRccListener(mRcMediaIntent);
        mSession = null;
    }

    private static final boolean DEBUG = false;
    public static final int DEFAULT_PLAYBACK_VOLUME = 15;
    public static final int DEFAULT_PLAYBACK_VOLUME_HANDLING = 1;
    public static final int FLAGS_KEY_MEDIA_NONE = 0;
    public static final int FLAG_INFORMATION_REQUEST_ALBUM_ART = 8;
    public static final int FLAG_INFORMATION_REQUEST_KEY_MEDIA = 2;
    public static final int FLAG_INFORMATION_REQUEST_METADATA = 1;
    public static final int FLAG_INFORMATION_REQUEST_PLAYSTATE = 4;
    public static final int FLAG_KEY_MEDIA_FAST_FORWARD = 64;
    public static final int FLAG_KEY_MEDIA_NEXT = 128;
    public static final int FLAG_KEY_MEDIA_PAUSE = 16;
    public static final int FLAG_KEY_MEDIA_PLAY = 4;
    public static final int FLAG_KEY_MEDIA_PLAY_PAUSE = 8;
    public static final int FLAG_KEY_MEDIA_POSITION_UPDATE = 256;
    public static final int FLAG_KEY_MEDIA_PREVIOUS = 1;
    public static final int FLAG_KEY_MEDIA_RATING = 512;
    public static final int FLAG_KEY_MEDIA_REWIND = 2;
    public static final int FLAG_KEY_MEDIA_STOP = 32;
    public static int MEDIA_POSITION_READABLE = 0;
    public static int MEDIA_POSITION_WRITABLE = 0;
    public static final int PLAYBACKINFO_INVALID_VALUE = 0x80000000;
    public static final int PLAYBACKINFO_PLAYBACK_TYPE = 1;
    public static final int PLAYBACKINFO_USES_STREAM = 5;
    public static final int PLAYBACKINFO_VOLUME = 2;
    public static final int PLAYBACKINFO_VOLUME_HANDLING = 4;
    public static final int PLAYBACKINFO_VOLUME_MAX = 3;
    public static final long PLAYBACK_POSITION_ALWAYS_UNKNOWN = 0x8019771980198300L;
    public static final long PLAYBACK_POSITION_INVALID = -1L;
    public static final float PLAYBACK_SPEED_1X = 1F;
    public static final int PLAYBACK_TYPE_LOCAL = 0;
    private static final int PLAYBACK_TYPE_MAX = 1;
    private static final int PLAYBACK_TYPE_MIN = 0;
    public static final int PLAYBACK_TYPE_REMOTE = 1;
    public static final int PLAYBACK_VOLUME_FIXED = 0;
    public static final int PLAYBACK_VOLUME_VARIABLE = 1;
    public static final int PLAYSTATE_BUFFERING = 8;
    public static final int PLAYSTATE_ERROR = 9;
    public static final int PLAYSTATE_FAST_FORWARDING = 4;
    public static final int PLAYSTATE_NONE = 0;
    public static final int PLAYSTATE_PAUSED = 2;
    public static final int PLAYSTATE_PLAYING = 3;
    public static final int PLAYSTATE_REWINDING = 5;
    public static final int PLAYSTATE_SKIPPING_BACKWARDS = 7;
    public static final int PLAYSTATE_SKIPPING_FORWARDS = 6;
    public static final int PLAYSTATE_STOPPED = 1;
    private static final long POSITION_DRIFT_MAX_MS = 500L;
    private static final long POSITION_REFRESH_PERIOD_MIN_MS = 2000L;
    private static final long POSITION_REFRESH_PERIOD_PLAYING_MS = 15000L;
    public static final int RCSE_ID_UNREGISTERED = -1;
    private static final String TAG = "RemoteControlClient";
    private final Object mCacheLock;
    private int mCurrentClientGenId;
    private MediaMetadata mMediaMetadata;
    private Bundle mMetadata;
    private OnMetadataUpdateListener mMetadataUpdateListener;
    private boolean mNeedsPositionSync;
    private Bitmap mOriginalArtwork;
    private long mPlaybackPositionMs;
    private float mPlaybackSpeed;
    private int mPlaybackState;
    private long mPlaybackStateChangeTimeMs;
    private OnGetPlaybackPositionListener mPositionProvider;
    private OnPlaybackPositionUpdateListener mPositionUpdateListener;
    private final PendingIntent mRcMediaIntent;
    private MediaSession mSession;
    private PlaybackState mSessionPlaybackState;
    private int mTransportControlFlags;
    private android.media.session.MediaSession.Callback mTransportListener = new android.media.session.MediaSession.Callback() {

        public void onSeekTo(long l)
        {
            RemoteControlClient._2D_wrap0(RemoteControlClient.this, RemoteControlClient._2D_get1(RemoteControlClient.this), l);
        }

        public void onSetRating(Rating rating)
        {
            if((RemoteControlClient._2D_get6(RemoteControlClient.this) & 0x200) != 0)
                RemoteControlClient._2D_wrap1(RemoteControlClient.this, RemoteControlClient._2D_get1(RemoteControlClient.this), 0x10000001, rating);
        }

        final RemoteControlClient this$0;

            
            {
                this$0 = RemoteControlClient.this;
                super();
            }
    }
;

    static 
    {
        MEDIA_POSITION_READABLE = 1;
        MEDIA_POSITION_WRITABLE = 2;
    }
}
