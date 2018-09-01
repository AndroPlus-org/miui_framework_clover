// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.session;

import android.app.PendingIntent;
import android.content.*;
import android.graphics.*;
import android.media.MediaMetadata;
import android.media.Rating;
import android.os.*;
import android.util.ArrayMap;
import android.util.Log;
import android.view.KeyEvent;

// Referenced classes of package android.media.session:
//            MediaSessionManager, MediaSession

public class MediaSessionLegacyHelper
{
    private static final class MediaButtonListener extends MediaSession.Callback
    {

        private void sendKeyEvent(int i)
        {
            KeyEvent keyevent = new KeyEvent(0, i);
            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
            intent.addFlags(0x10000000);
            intent.putExtra("android.intent.extra.KEY_EVENT", keyevent);
            MediaSessionLegacyHelper._2D_wrap0(mPendingIntent, mContext, intent);
            intent.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(1, i));
            MediaSessionLegacyHelper._2D_wrap0(mPendingIntent, mContext, intent);
            if(MediaSessionLegacyHelper._2D_get0())
                Log.d("MediaSessionHelper", (new StringBuilder()).append("Sent ").append(i).append(" to pending intent ").append(mPendingIntent).toString());
        }

        public void onFastForward()
        {
            sendKeyEvent(90);
        }

        public boolean onMediaButtonEvent(Intent intent)
        {
            MediaSessionLegacyHelper._2D_wrap0(mPendingIntent, mContext, intent);
            return true;
        }

        public void onPause()
        {
            sendKeyEvent(127);
        }

        public void onPlay()
        {
            sendKeyEvent(126);
        }

        public void onRewind()
        {
            sendKeyEvent(89);
        }

        public void onSkipToNext()
        {
            sendKeyEvent(87);
        }

        public void onSkipToPrevious()
        {
            sendKeyEvent(88);
        }

        public void onStop()
        {
            sendKeyEvent(86);
        }

        private final Context mContext;
        private final PendingIntent mPendingIntent;

        public MediaButtonListener(PendingIntent pendingintent, Context context)
        {
            mPendingIntent = pendingintent;
            mContext = context;
        }
    }

    private class SessionHolder
    {

        public void update()
        {
            if(mMediaButtonListener != null || mRccListener != null) goto _L2; else goto _L1
_L1:
            mSession.setCallback(null);
            mSession.release();
            mCb = null;
            MediaSessionLegacyHelper._2D_get1(MediaSessionLegacyHelper.this).remove(mPi);
_L4:
            return;
_L2:
            if(mCb == null)
            {
                mCb = new SessionCallback(null);
                Handler handler = new Handler(Looper.getMainLooper());
                mSession.setCallback(mCb, handler);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        public SessionCallback mCb;
        public int mFlags;
        public MediaButtonListener mMediaButtonListener;
        public final PendingIntent mPi;
        public MediaSession.Callback mRccListener;
        public final MediaSession mSession;
        final MediaSessionLegacyHelper this$0;

        public SessionHolder(MediaSession mediasession, PendingIntent pendingintent)
        {
            this$0 = MediaSessionLegacyHelper.this;
            super();
            mSession = mediasession;
            mPi = pendingintent;
        }
    }

    private class SessionHolder.SessionCallback extends MediaSession.Callback
    {

        public void onFastForward()
        {
            if(mMediaButtonListener != null)
                mMediaButtonListener.onFastForward();
        }

        public boolean onMediaButtonEvent(Intent intent)
        {
            if(mMediaButtonListener != null)
                mMediaButtonListener.onMediaButtonEvent(intent);
            return true;
        }

        public void onPause()
        {
            if(mMediaButtonListener != null)
                mMediaButtonListener.onPause();
        }

        public void onPlay()
        {
            if(mMediaButtonListener != null)
                mMediaButtonListener.onPlay();
        }

        public void onRewind()
        {
            if(mMediaButtonListener != null)
                mMediaButtonListener.onRewind();
        }

        public void onSeekTo(long l)
        {
            if(mRccListener != null)
                mRccListener.onSeekTo(l);
        }

        public void onSetRating(Rating rating)
        {
            if(mRccListener != null)
                mRccListener.onSetRating(rating);
        }

        public void onSkipToNext()
        {
            if(mMediaButtonListener != null)
                mMediaButtonListener.onSkipToNext();
        }

        public void onSkipToPrevious()
        {
            if(mMediaButtonListener != null)
                mMediaButtonListener.onSkipToPrevious();
        }

        public void onStop()
        {
            if(mMediaButtonListener != null)
                mMediaButtonListener.onStop();
        }

        final SessionHolder this$1;

        private SessionHolder.SessionCallback()
        {
            this$1 = SessionHolder.this;
            super();
        }

        SessionHolder.SessionCallback(SessionHolder.SessionCallback sessioncallback)
        {
            this();
        }
    }


    static boolean _2D_get0()
    {
        return DEBUG;
    }

    static ArrayMap _2D_get1(MediaSessionLegacyHelper mediasessionlegacyhelper)
    {
        return mediasessionlegacyhelper.mSessions;
    }

    static void _2D_wrap0(PendingIntent pendingintent, Context context, Intent intent)
    {
        sendKeyEvent(pendingintent, context, intent);
    }

    private MediaSessionLegacyHelper(Context context)
    {
        mHandler = new Handler(Looper.getMainLooper());
        mSessions = new ArrayMap();
        mContext = context;
        mSessionManager = (MediaSessionManager)context.getSystemService("media_session");
    }

    public static MediaSessionLegacyHelper getHelper(Context context)
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        if(sInstance == null)
        {
            MediaSessionLegacyHelper mediasessionlegacyhelper = JVM INSTR new #2   <Class MediaSessionLegacyHelper>;
            mediasessionlegacyhelper.MediaSessionLegacyHelper(context.getApplicationContext());
            sInstance = mediasessionlegacyhelper;
        }
        obj;
        JVM INSTR monitorexit ;
        return sInstance;
        context;
        throw context;
    }

    private SessionHolder getHolder(PendingIntent pendingintent, boolean flag)
    {
        SessionHolder sessionholder = (SessionHolder)mSessions.get(pendingintent);
        Object obj = sessionholder;
        if(sessionholder == null)
        {
            obj = sessionholder;
            if(flag)
            {
                obj = new MediaSession(mContext, (new StringBuilder()).append("MediaSessionHelper-").append(pendingintent.getCreatorPackage()).toString());
                ((MediaSession) (obj)).setActive(true);
                obj = new SessionHolder(((MediaSession) (obj)), pendingintent);
                mSessions.put(pendingintent, obj);
            }
        }
        return ((SessionHolder) (obj));
    }

    public static Bundle getOldMetadata(MediaMetadata mediametadata, int i, int j)
    {
        boolean flag;
        Bundle bundle;
        if(i != -1 && j != -1)
            flag = true;
        else
            flag = false;
        bundle = new Bundle();
        if(mediametadata.containsKey("android.media.metadata.ALBUM"))
            bundle.putString(String.valueOf(1), mediametadata.getString("android.media.metadata.ALBUM"));
        if(!flag || !mediametadata.containsKey("android.media.metadata.ART")) goto _L2; else goto _L1
_L1:
        bundle.putParcelable(String.valueOf(100), scaleBitmapIfTooBig(mediametadata.getBitmap("android.media.metadata.ART"), i, j));
_L4:
        if(mediametadata.containsKey("android.media.metadata.ALBUM_ARTIST"))
            bundle.putString(String.valueOf(13), mediametadata.getString("android.media.metadata.ALBUM_ARTIST"));
        if(mediametadata.containsKey("android.media.metadata.ARTIST"))
            bundle.putString(String.valueOf(2), mediametadata.getString("android.media.metadata.ARTIST"));
        if(mediametadata.containsKey("android.media.metadata.AUTHOR"))
            bundle.putString(String.valueOf(3), mediametadata.getString("android.media.metadata.AUTHOR"));
        if(mediametadata.containsKey("android.media.metadata.COMPILATION"))
            bundle.putString(String.valueOf(15), mediametadata.getString("android.media.metadata.COMPILATION"));
        if(mediametadata.containsKey("android.media.metadata.COMPOSER"))
            bundle.putString(String.valueOf(4), mediametadata.getString("android.media.metadata.COMPOSER"));
        if(mediametadata.containsKey("android.media.metadata.DATE"))
            bundle.putString(String.valueOf(5), mediametadata.getString("android.media.metadata.DATE"));
        if(mediametadata.containsKey("android.media.metadata.DISC_NUMBER"))
            bundle.putLong(String.valueOf(14), mediametadata.getLong("android.media.metadata.DISC_NUMBER"));
        if(mediametadata.containsKey("android.media.metadata.DURATION"))
            bundle.putLong(String.valueOf(9), mediametadata.getLong("android.media.metadata.DURATION"));
        if(mediametadata.containsKey("android.media.metadata.GENRE"))
            bundle.putString(String.valueOf(6), mediametadata.getString("android.media.metadata.GENRE"));
        if(mediametadata.containsKey("android.media.metadata.NUM_TRACKS"))
            bundle.putLong(String.valueOf(10), mediametadata.getLong("android.media.metadata.NUM_TRACKS"));
        if(mediametadata.containsKey("android.media.metadata.RATING"))
            bundle.putParcelable(String.valueOf(101), mediametadata.getRating("android.media.metadata.RATING"));
        if(mediametadata.containsKey("android.media.metadata.USER_RATING"))
            bundle.putParcelable(String.valueOf(0x10000001), mediametadata.getRating("android.media.metadata.USER_RATING"));
        if(mediametadata.containsKey("android.media.metadata.TITLE"))
            bundle.putString(String.valueOf(7), mediametadata.getString("android.media.metadata.TITLE"));
        if(mediametadata.containsKey("android.media.metadata.TRACK_NUMBER"))
            bundle.putLong(String.valueOf(0), mediametadata.getLong("android.media.metadata.TRACK_NUMBER"));
        if(mediametadata.containsKey("android.media.metadata.WRITER"))
            bundle.putString(String.valueOf(11), mediametadata.getString("android.media.metadata.WRITER"));
        if(mediametadata.containsKey("android.media.metadata.YEAR"))
            bundle.putLong(String.valueOf(8), mediametadata.getLong("android.media.metadata.YEAR"));
        if(mediametadata.containsKey("android.media.metadata.LYRIC"))
            bundle.putString(String.valueOf(1000), mediametadata.getString("android.media.metadata.LYRIC"));
        return bundle;
_L2:
        if(flag && mediametadata.containsKey("android.media.metadata.ALBUM_ART"))
            bundle.putParcelable(String.valueOf(100), scaleBitmapIfTooBig(mediametadata.getBitmap("android.media.metadata.ALBUM_ART"), i, j));
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static Bitmap scaleBitmapIfTooBig(Bitmap bitmap, int i, int j)
    {
        Object obj;
label0:
        {
            obj = bitmap;
            if(bitmap == null)
                break label0;
            int k = bitmap.getWidth();
            int l = bitmap.getHeight();
            if(k <= i)
            {
                obj = bitmap;
                if(l <= j)
                    break label0;
            }
            float f = Math.min((float)i / (float)k, (float)j / (float)l);
            i = Math.round((float)k * f);
            j = Math.round((float)l * f);
            Object obj1 = bitmap.getConfig();
            obj = obj1;
            if(obj1 == null)
                obj = android.graphics.Bitmap.Config.ARGB_8888;
            obj = Bitmap.createBitmap(i, j, ((android.graphics.Bitmap.Config) (obj)));
            obj1 = new Canvas(((Bitmap) (obj)));
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            ((Canvas) (obj1)).drawBitmap(bitmap, null, new RectF(0.0F, 0.0F, ((Bitmap) (obj)).getWidth(), ((Bitmap) (obj)).getHeight()), paint);
        }
        return ((Bitmap) (obj));
    }

    private static void sendKeyEvent(PendingIntent pendingintent, Context context, Intent intent)
    {
        try
        {
            pendingintent.send(context, 0, intent);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PendingIntent pendingintent)
        {
            Log.e("MediaSessionHelper", "Error sending media key down event:", pendingintent);
        }
    }

    public void addMediaButtonListener(PendingIntent pendingintent, ComponentName componentname, Context context)
    {
        if(pendingintent == null)
        {
            Log.w("MediaSessionHelper", "Pending intent was null, can't addMediaButtonListener.");
            return;
        }
        componentname = getHolder(pendingintent, true);
        if(componentname == null)
            return;
        if(((SessionHolder) (componentname)).mMediaButtonListener != null && DEBUG)
            Log.d("MediaSessionHelper", (new StringBuilder()).append("addMediaButtonListener already added ").append(pendingintent).toString());
        componentname.mMediaButtonListener = new MediaButtonListener(pendingintent, context);
        componentname.mFlags = ((SessionHolder) (componentname)).mFlags | 1;
        ((SessionHolder) (componentname)).mSession.setFlags(((SessionHolder) (componentname)).mFlags);
        ((SessionHolder) (componentname)).mSession.setMediaButtonReceiver(pendingintent);
        componentname.update();
        if(DEBUG)
            Log.d("MediaSessionHelper", (new StringBuilder()).append("addMediaButtonListener added ").append(pendingintent).toString());
    }

    public void addRccListener(PendingIntent pendingintent, MediaSession.Callback callback)
    {
        if(pendingintent == null)
        {
            Log.w("MediaSessionHelper", "Pending intent was null, can't add rcc listener.");
            return;
        }
        SessionHolder sessionholder = getHolder(pendingintent, true);
        if(sessionholder == null)
            return;
        if(sessionholder.mRccListener != null && sessionholder.mRccListener == callback)
        {
            if(DEBUG)
                Log.d("MediaSessionHelper", "addRccListener listener already added.");
            return;
        }
        sessionholder.mRccListener = callback;
        sessionholder.mFlags = sessionholder.mFlags | 2;
        sessionholder.mSession.setFlags(sessionholder.mFlags);
        sessionholder.update();
        if(DEBUG)
            Log.d("MediaSessionHelper", (new StringBuilder()).append("Added rcc listener for ").append(pendingintent).append(".").toString());
    }

    public MediaSession getSession(PendingIntent pendingintent)
    {
        Object obj = null;
        pendingintent = (SessionHolder)mSessions.get(pendingintent);
        if(pendingintent == null)
            pendingintent = obj;
        else
            pendingintent = ((SessionHolder) (pendingintent)).mSession;
        return pendingintent;
    }

    public boolean isGlobalPriorityActive()
    {
        return mSessionManager.isGlobalPriorityActive();
    }

    public void removeMediaButtonListener(PendingIntent pendingintent)
    {
        if(pendingintent == null)
            return;
        SessionHolder sessionholder = getHolder(pendingintent, false);
        if(sessionholder != null && sessionholder.mMediaButtonListener != null)
        {
            sessionholder.mFlags = sessionholder.mFlags & -2;
            sessionholder.mSession.setFlags(sessionholder.mFlags);
            sessionholder.mMediaButtonListener = null;
            sessionholder.update();
            if(DEBUG)
                Log.d("MediaSessionHelper", (new StringBuilder()).append("removeMediaButtonListener removed ").append(pendingintent).toString());
        }
    }

    public void removeRccListener(PendingIntent pendingintent)
    {
        if(pendingintent == null)
            return;
        SessionHolder sessionholder = getHolder(pendingintent, false);
        if(sessionholder != null && sessionholder.mRccListener != null)
        {
            sessionholder.mRccListener = null;
            sessionholder.mFlags = sessionholder.mFlags & -3;
            sessionholder.mSession.setFlags(sessionholder.mFlags);
            sessionholder.update();
            if(DEBUG)
                Log.d("MediaSessionHelper", (new StringBuilder()).append("Removed rcc listener for ").append(pendingintent).append(".").toString());
        }
    }

    public void sendAdjustVolumeBy(int i, int j, int k)
    {
        mSessionManager.dispatchAdjustVolume(i, j, k);
        if(DEBUG)
            Log.d("MediaSessionHelper", "dispatched volume adjustment");
    }

    public void sendMediaButtonEvent(KeyEvent keyevent, boolean flag)
    {
        if(keyevent == null)
        {
            Log.w("MediaSessionHelper", "Tried to send a null key event. Ignoring.");
            return;
        }
        mSessionManager.dispatchMediaKeyEvent(keyevent, flag);
        if(DEBUG)
            Log.d("MediaSessionHelper", (new StringBuilder()).append("dispatched media key ").append(keyevent).toString());
    }

    public void sendVolumeKeyEvent(KeyEvent keyevent, int i, boolean flag)
    {
        if(keyevent == null)
        {
            Log.w("MediaSessionHelper", "Tried to send a null key event. Ignoring.");
            return;
        } else
        {
            mSessionManager.dispatchVolumeKeyEvent(keyevent, i, flag);
            return;
        }
    }

    private static final boolean DEBUG = Log.isLoggable("MediaSessionHelper", 3);
    private static final String TAG = "MediaSessionHelper";
    private static MediaSessionLegacyHelper sInstance;
    private static final Object sLock = new Object();
    private Context mContext;
    private Handler mHandler;
    private MediaSessionManager mSessionManager;
    private ArrayMap mSessions;

}
