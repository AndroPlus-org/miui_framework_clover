// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.*;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;

// Referenced classes of package android.media:
//            AudioManager, MediaPlayer, IRingtonePlayer, RingtoneManager, 
//            AudioAttributes, PlayerBase

public class Ringtone
{
    class MyOnCompletionListener
        implements MediaPlayer.OnCompletionListener
    {

        public void onCompletion(MediaPlayer mediaplayer)
        {
            ArrayList arraylist = Ringtone._2D_get0();
            arraylist;
            JVM INSTR monitorenter ;
            Ringtone._2D_get0().remove(Ringtone.this);
            arraylist;
            JVM INSTR monitorexit ;
            mediaplayer.setOnCompletionListener(null);
            return;
            mediaplayer;
            throw mediaplayer;
        }

        final Ringtone this$0;

        MyOnCompletionListener()
        {
            this$0 = Ringtone.this;
            super();
        }
    }


    static ArrayList _2D_get0()
    {
        return sActiveRingtones;
    }

    public Ringtone(Context context, boolean flag)
    {
        Object obj = null;
        super();
        mAudioAttributes = (new AudioAttributes.Builder()).setUsage(6).setContentType(4).build();
        mIsLooping = false;
        mVolume = 1.0F;
        mContext = context;
        mAudioManager = (AudioManager)mContext.getSystemService("audio");
        mAllowRemote = flag;
        if(flag)
            context = mAudioManager.getRingtonePlayer();
        else
            context = null;
        mRemotePlayer = context;
        context = obj;
        if(flag)
            context = new Binder();
        mRemoteToken = context;
    }

    private void applyPlaybackProperties_sync()
    {
        if(mLocalPlayer != null)
        {
            mLocalPlayer.setVolume(mVolume);
            mLocalPlayer.setLooping(mIsLooping);
        } else
        if(mAllowRemote && mRemotePlayer != null)
            try
            {
                mRemotePlayer.setPlaybackProperties(mRemoteToken, mVolume, mIsLooping);
            }
            catch(RemoteException remoteexception)
            {
                Log.w("Ringtone", "Problem setting playback properties: ", remoteexception);
            }
        else
            Log.w("Ringtone", "Neither local nor remote player available when applying playback properties");
    }

    private void destroyLocalPlayer()
    {
        if(mLocalPlayer == null) goto _L2; else goto _L1
_L1:
        mLocalPlayer.setOnCompletionListener(null);
        mLocalPlayer.reset();
        mLocalPlayer.release();
        mLocalPlayer = null;
        ArrayList arraylist = sActiveRingtones;
        arraylist;
        JVM INSTR monitorenter ;
        sActiveRingtones.remove(this);
        arraylist;
        JVM INSTR monitorexit ;
_L2:
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static String getTitle(Context context, Uri uri, boolean flag, boolean flag1)
    {
        ContentResolver contentresolver;
        Object obj;
        Object obj1;
        Object obj2;
        contentresolver = context.getContentResolver();
        obj = null;
        obj1 = null;
        obj2 = null;
        if(uri == null) goto _L2; else goto _L1
_L1:
        Object obj3 = ContentProvider.getAuthorityWithoutUserId(uri.getAuthority());
        if(!"settings".equals(obj3)) goto _L4; else goto _L3
_L3:
        if(flag)
            obj2 = context.getString(0x1040582, new Object[] {
                getTitle(context, RingtoneManager.getActualDefaultRingtoneUri(context, RingtoneManager.getDefaultType(uri)), false, flag1)
            });
_L8:
        uri = ((Uri) (obj2));
        if(obj2 == null)
        {
            context = context.getString(0x1040587);
            uri = context;
            if(context == null)
                uri = "";
        }
        return uri;
_L4:
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        obj4 = null;
        obj5 = null;
        obj6 = null;
        obj2 = obj4;
        obj7 = obj5;
        if(!"media".equals(obj3))
            break MISSING_BLOCK_LABEL_242;
        if(flag1)
            obj3 = null;
        else
            obj3 = "mime_type LIKE 'audio/%' OR mime_type IN ('application/ogg', 'application/x-flac')";
        obj2 = obj4;
        obj7 = obj5;
        obj3 = contentresolver.query(uri, MEDIA_COLUMNS, ((String) (obj3)), null, null);
        obj6 = obj3;
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_242;
        obj6 = obj3;
        obj2 = obj3;
        obj7 = obj3;
        if(((Cursor) (obj3)).getCount() != 1)
            break MISSING_BLOCK_LABEL_242;
        obj2 = obj3;
        obj7 = obj3;
        ((Cursor) (obj3)).moveToFirst();
        obj2 = obj3;
        obj7 = obj3;
        obj6 = ((Cursor) (obj3)).getString(2);
        if(obj3 != null)
            ((Cursor) (obj3)).close();
        return ((String) (obj6));
        obj7 = obj;
        if(obj6 != null)
        {
            ((Cursor) (obj6)).close();
            obj7 = obj;
        }
_L6:
        obj2 = obj7;
        if(obj7 == null)
            obj2 = uri.getLastPathSegment();
        continue; /* Loop/switch isn't completed */
        obj7;
        obj6 = null;
        if(!flag1)
            break MISSING_BLOCK_LABEL_307;
        obj7 = obj2;
        obj6 = ((AudioManager)context.getSystemService("audio")).getRingtonePlayer();
        obj3 = obj1;
        if(obj6 == null)
            break MISSING_BLOCK_LABEL_330;
        obj7 = obj2;
        try
        {
            obj3 = ((IRingtonePlayer) (obj6)).getTitle(uri);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj7)
        {
            obj3 = obj1;
        }
        obj7 = obj3;
        if(obj2 != null)
        {
            ((Cursor) (obj2)).close();
            obj7 = obj3;
        }
        if(true) goto _L6; else goto _L5
_L5:
        context;
        if(obj7 != null)
            ((Cursor) (obj7)).close();
        throw context;
_L2:
        obj2 = context.getString(0x1040586);
        if(true) goto _L8; else goto _L7
_L7:
    }

    private boolean playFallbackRingtone()
    {
        if(mAudioManager.getStreamVolume(AudioAttributes.toLegacyStreamType(mAudioAttributes)) == 0) goto _L2; else goto _L1
_L1:
        int i = RingtoneManager.getDefaultType(mUri);
        if(i != -1 && RingtoneManager.getActualDefaultRingtoneUri(mContext, i) == null) goto _L4; else goto _L3
_L3:
        AssetFileDescriptor assetfiledescriptor = mContext.getResources().openRawResourceFd(0x1100005);
        if(assetfiledescriptor == null) goto _L6; else goto _L5
_L5:
        MediaPlayer mediaplayer = JVM INSTR new #130 <Class MediaPlayer>;
        mediaplayer.MediaPlayer();
        mLocalPlayer = mediaplayer;
        if(assetfiledescriptor.getDeclaredLength() >= 0L)
            break MISSING_BLOCK_LABEL_133;
        mLocalPlayer.setDataSource(assetfiledescriptor.getFileDescriptor());
_L7:
        mLocalPlayer.setAudioAttributes(mAudioAttributes);
        Object obj1 = mPlaybackSettingsLock;
        obj1;
        JVM INSTR monitorenter ;
        applyPlaybackProperties_sync();
        obj1;
        JVM INSTR monitorexit ;
        mLocalPlayer.prepare();
        startLocalPlayer();
        assetfiledescriptor.close();
        return true;
        Object obj;
        mLocalPlayer.setDataSource(assetfiledescriptor.getFileDescriptor(), assetfiledescriptor.getStartOffset(), assetfiledescriptor.getDeclaredLength());
          goto _L7
_L2:
        return false;
        obj;
        obj1;
        JVM INSTR monitorexit ;
        throw obj;
_L6:
        try
        {
            Log.e("Ringtone", "Could not load fallback ringtone");
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            destroyLocalPlayer();
            Log.e("Ringtone", "Failed to open fallback ringtone");
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.e("Ringtone", "Fallback ringtone does not exist");
        }
        continue; /* Loop/switch isn't completed */
_L4:
        Log.w("Ringtone", (new StringBuilder()).append("not playing fallback for ").append(mUri).toString());
        if(true) goto _L2; else goto _L8
_L8:
    }

    private void startLocalPlayer()
    {
        if(mLocalPlayer == null)
            return;
        ArrayList arraylist = sActiveRingtones;
        arraylist;
        JVM INSTR monitorenter ;
        sActiveRingtones.add(this);
        arraylist;
        JVM INSTR monitorexit ;
        mLocalPlayer.setOnCompletionListener(mCompletionListener);
        mLocalPlayer.start();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected void finalize()
    {
        if(mLocalPlayer != null)
            mLocalPlayer.release();
    }

    public AudioAttributes getAudioAttributes()
    {
        return mAudioAttributes;
    }

    public int getStreamType()
    {
        return AudioAttributes.toLegacyStreamType(mAudioAttributes);
    }

    public String getTitle(Context context)
    {
        if(mTitle != null)
        {
            return mTitle;
        } else
        {
            context = getTitle(context, mUri, true, mAllowRemote);
            mTitle = context;
            return context;
        }
    }

    public Uri getUri()
    {
        return mUri;
    }

    public boolean isPlaying()
    {
        if(mLocalPlayer != null)
            return mLocalPlayer.isPlaying();
        if(mAllowRemote && mRemotePlayer != null)
        {
            boolean flag;
            try
            {
                flag = mRemotePlayer.isPlaying(mRemoteToken);
            }
            catch(RemoteException remoteexception)
            {
                Log.w("Ringtone", (new StringBuilder()).append("Problem checking ringtone: ").append(remoteexception).toString());
                return false;
            }
            return flag;
        } else
        {
            Log.w("Ringtone", "Neither local nor remote playback available");
            return false;
        }
    }

    public void play()
    {
        if(mLocalPlayer == null) goto _L2; else goto _L1
_L1:
        if(mAudioManager.getStreamVolume(AudioAttributes.toLegacyStreamType(mAudioAttributes)) != 0)
            startLocalPlayer();
_L4:
        return;
_L2:
        Uri uri;
        if(!mAllowRemote || mRemotePlayer == null)
            break MISSING_BLOCK_LABEL_137;
        uri = mUri.getCanonicalUri();
        Object obj = mPlaybackSettingsLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        float f;
        flag = mIsLooping;
        f = mVolume;
        obj;
        JVM INSTR monitorexit ;
        try
        {
            mRemotePlayer.play(mRemoteToken, uri, mAudioAttributes, f, flag);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            if(!playFallbackRingtone())
                Log.w("Ringtone", (new StringBuilder()).append("Problem playing ringtone: ").append(obj).toString());
        }
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        throw exception;
        if(!playFallbackRingtone())
            Log.w("Ringtone", "Neither local nor remote playback available");
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setAudioAttributes(AudioAttributes audioattributes)
        throws IllegalArgumentException
    {
        if(audioattributes == null)
        {
            throw new IllegalArgumentException("Invalid null AudioAttributes for Ringtone");
        } else
        {
            mAudioAttributes = audioattributes;
            setUri(mUri);
            return;
        }
    }

    public void setLooping(boolean flag)
    {
        Object obj = mPlaybackSettingsLock;
        obj;
        JVM INSTR monitorenter ;
        mIsLooping = flag;
        applyPlaybackProperties_sync();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setStreamType(int i)
    {
        PlayerBase.deprecateStreamTypeForPlayback(i, "Ringtone", "setStreamType()");
        setAudioAttributes((new AudioAttributes.Builder()).setInternalLegacyStreamType(i).build());
    }

    void setTitle(String s)
    {
        mTitle = s;
    }

    public void setUri(Uri uri)
    {
        destroyLocalPlayer();
        mUri = uri;
        if(mUri == null)
            return;
        mLocalPlayer = new MediaPlayer();
        mLocalPlayer.setDataSource(mContext, mUri);
        mLocalPlayer.setAudioAttributes(mAudioAttributes);
        Object obj = mPlaybackSettingsLock;
        obj;
        JVM INSTR monitorenter ;
        applyPlaybackProperties_sync();
        obj;
        JVM INSTR monitorexit ;
        mLocalPlayer.prepare();
_L1:
        if(mLocalPlayer != null)
            Log.d("Ringtone", "Successfully created local player");
        else
            Log.d("Ringtone", "Problem opening; delegating to remote player");
        return;
        uri;
        obj;
        JVM INSTR monitorexit ;
        throw uri;
        uri;
        destroyLocalPlayer();
        if(!mAllowRemote)
            Log.w("Ringtone", (new StringBuilder()).append("Remote playback not allowed: ").append(uri).toString());
          goto _L1
    }

    public void setVolume(float f)
    {
        Object obj = mPlaybackSettingsLock;
        obj;
        JVM INSTR monitorenter ;
        float f1 = f;
        if(f < 0.0F)
            f1 = 0.0F;
        f = f1;
        if(f1 > 1.0F)
            f = 1.0F;
        mVolume = f;
        applyPlaybackProperties_sync();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void stop()
    {
        if(mLocalPlayer == null) goto _L2; else goto _L1
_L1:
        destroyLocalPlayer();
_L4:
        return;
_L2:
        if(mAllowRemote && mRemotePlayer != null)
            try
            {
                mRemotePlayer.stop(mRemoteToken);
            }
            catch(RemoteException remoteexception)
            {
                Log.w("Ringtone", (new StringBuilder()).append("Problem stopping ringtone: ").append(remoteexception).toString());
            }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final boolean LOGD = true;
    private static final String MEDIA_COLUMNS[] = {
        "_id", "_data", "title"
    };
    private static final String MEDIA_SELECTION = "mime_type LIKE 'audio/%' OR mime_type IN ('application/ogg', 'application/x-flac')";
    private static final String TAG = "Ringtone";
    private static final ArrayList sActiveRingtones = new ArrayList();
    private final boolean mAllowRemote;
    private AudioAttributes mAudioAttributes;
    private final AudioManager mAudioManager;
    private final MyOnCompletionListener mCompletionListener = new MyOnCompletionListener();
    private final Context mContext;
    private boolean mIsLooping;
    private MediaPlayer mLocalPlayer;
    private final Object mPlaybackSettingsLock = new Object();
    private final IRingtonePlayer mRemotePlayer;
    private final Binder mRemoteToken;
    private String mTitle;
    private Uri mUri;
    private float mVolume;

}
