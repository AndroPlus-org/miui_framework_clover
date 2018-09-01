// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.app.ActivityThread;
import android.os.*;
import android.util.Log;
import com.android.internal.app.IAppOpsCallback;
import com.android.internal.app.IAppOpsService;
import java.lang.ref.WeakReference;
import java.util.Objects;

// Referenced classes of package android.media:
//            IAudioService, AudioAttributes, IPlayer

public abstract class PlayerBase
{
    private static class IAppOpsCallbackWrapper extends com.android.internal.app.IAppOpsCallback.Stub
    {

        public void opChanged(int i, int j, String s)
        {
            if(i == 28)
            {
                s = (PlayerBase)mWeakPB.get();
                if(s != null)
                    PlayerBase._2D_wrap0(s);
            }
        }

        private final WeakReference mWeakPB;

        public IAppOpsCallbackWrapper(PlayerBase playerbase)
        {
            mWeakPB = new WeakReference(playerbase);
        }
    }

    private static class IPlayerWrapper extends IPlayer.Stub
    {

        public void applyVolumeShaper(VolumeShaper.Configuration configuration, VolumeShaper.Operation operation)
        {
            PlayerBase playerbase = (PlayerBase)mWeakPB.get();
            if(playerbase != null)
                playerbase.playerApplyVolumeShaper(configuration, operation);
        }

        public void pause()
        {
            PlayerBase playerbase = (PlayerBase)mWeakPB.get();
            if(playerbase != null)
                playerbase.playerPause();
        }

        public void setPan(float f)
        {
            PlayerBase playerbase = (PlayerBase)mWeakPB.get();
            if(playerbase != null)
                playerbase.baseSetPan(f);
        }

        public void setStartDelayMs(int i)
        {
            PlayerBase playerbase = (PlayerBase)mWeakPB.get();
            if(playerbase != null)
                playerbase.baseSetStartDelayMs(i);
        }

        public void setVolume(float f)
        {
            PlayerBase playerbase = (PlayerBase)mWeakPB.get();
            if(playerbase != null)
                playerbase.baseSetVolume(f, f);
        }

        public void start()
        {
            PlayerBase playerbase = (PlayerBase)mWeakPB.get();
            if(playerbase != null)
                playerbase.playerStart();
        }

        public void stop()
        {
            PlayerBase playerbase = (PlayerBase)mWeakPB.get();
            if(playerbase != null)
                playerbase.playerStop();
        }

        private final WeakReference mWeakPB;

        public IPlayerWrapper(PlayerBase playerbase)
        {
            mWeakPB = new WeakReference(playerbase);
        }
    }

    public static class PlayerIdCard
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(this == obj)
                return true;
            if(obj == null || (obj instanceof PlayerIdCard) ^ true)
                return false;
            obj = (PlayerIdCard)obj;
            if(mPlayerType == ((PlayerIdCard) (obj)).mPlayerType)
                flag = mAttributes.equals(((PlayerIdCard) (obj)).mAttributes);
            return flag;
        }

        public int hashCode()
        {
            return Objects.hash(new Object[] {
                Integer.valueOf(mPlayerType)
            });
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            android.os.IBinder ibinder = null;
            parcel.writeInt(mPlayerType);
            mAttributes.writeToParcel(parcel, 0);
            if(mIPlayer != null)
                ibinder = mIPlayer.asBinder();
            parcel.writeStrongBinder(ibinder);
        }

        public static final int AUDIO_ATTRIBUTES_DEFINED = 1;
        public static final int AUDIO_ATTRIBUTES_NONE = 0;
        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public PlayerIdCard createFromParcel(Parcel parcel)
            {
                return new PlayerIdCard(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public PlayerIdCard[] newArray(int i)
            {
                return new PlayerIdCard[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public final AudioAttributes mAttributes;
        public final IPlayer mIPlayer;
        public final int mPlayerType;


        PlayerIdCard(int i, AudioAttributes audioattributes, IPlayer iplayer)
        {
            mPlayerType = i;
            mAttributes = audioattributes;
            mIPlayer = iplayer;
        }

        private PlayerIdCard(Parcel parcel)
        {
            mPlayerType = parcel.readInt();
            mAttributes = (AudioAttributes)AudioAttributes.CREATOR.createFromParcel(parcel);
            parcel = parcel.readStrongBinder();
            if(parcel == null)
                parcel = null;
            else
                parcel = IPlayer.Stub.asInterface(parcel);
            mIPlayer = parcel;
        }

        PlayerIdCard(Parcel parcel, PlayerIdCard playeridcard)
        {
            this(parcel);
        }
    }


    static void _2D_wrap0(PlayerBase playerbase)
    {
        playerbase.updateAppOpsPlayAudio();
    }

    PlayerBase(AudioAttributes audioattributes, int i)
    {
        mLeftVolume = 1.0F;
        mRightVolume = 1.0F;
        mAuxEffectSendLevel = 0.0F;
        mHasAppOpsPlayAudio = true;
        mStartDelayMs = 0;
        mPanMultiplierL = 1.0F;
        mPanMultiplierR = 1.0F;
        if(audioattributes == null)
        {
            throw new IllegalArgumentException("Illegal null AudioAttributes");
        } else
        {
            mAttributes = audioattributes;
            mImplType = i;
            mState = 1;
            return;
        }
    }

    public static void deprecateStreamTypeForPlayback(int i, String s, String s1)
        throws IllegalArgumentException
    {
        if(i == 10)
        {
            throw new IllegalArgumentException("Use of STREAM_ACCESSIBILITY is reserved for volume control");
        } else
        {
            Log.w(s, "Use of stream types is deprecated for operations other than volume control");
            Log.w(s, (new StringBuilder()).append("See the documentation of ").append(s1).append(" for what to use instead with ").append("android.media.AudioAttributes to qualify your playback use case").toString());
            return;
        }
    }

    private static IAudioService getService()
    {
        if(sService != null)
        {
            return sService;
        } else
        {
            sService = IAudioService.Stub.asInterface(ServiceManager.getService("audio"));
            return sService;
        }
    }

    private void updateAppOpsPlayAudio()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        updateAppOpsPlayAudio_sync(false);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void basePause()
    {
        Object obj = mStateLock;
        obj;
        JVM INSTR monitorenter ;
        mState = 3;
        getService().playerEvent(mPlayerIId, mState);
        obj;
        JVM INSTR monitorexit ;
_L1:
        return;
        Object obj1;
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
        obj1;
        Log.e("PlayerBase", "Error talking to audio service, PAUSED state will not be tracked", ((Throwable) (obj1)));
          goto _L1
    }

    protected void baseRegisterPlayer()
    {
        int i = -1;
        mAppOps = com.android.internal.app.IAppOpsService.Stub.asInterface(ServiceManager.getService("appops"));
        updateAppOpsPlayAudio();
        mAppOpsCallback = new IAppOpsCallbackWrapper(this);
        IAudioService iaudioservice;
        PlayerIdCard playeridcard;
        int j;
        AudioAttributes audioattributes;
        IPlayerWrapper iplayerwrapper;
        try
        {
            mAppOps.startWatchingMode(28, ActivityThread.currentPackageName(), mAppOpsCallback);
        }
        catch(RemoteException remoteexception)
        {
            mHasAppOpsPlayAudio = false;
        }
        iaudioservice = getService();
        playeridcard = JVM INSTR new #12  <Class PlayerBase$PlayerIdCard>;
        j = mImplType;
        audioattributes = mAttributes;
        iplayerwrapper = JVM INSTR new #9   <Class PlayerBase$IPlayerWrapper>;
        iplayerwrapper.IPlayerWrapper(this);
        playeridcard.PlayerIdCard(j, audioattributes, iplayerwrapper);
        j = iaudioservice.trackPlayer(playeridcard);
        i = j;
_L2:
        mPlayerIId = i;
        return;
        RemoteException remoteexception1;
        remoteexception1;
        Log.e("PlayerBase", "Error talking to audio service, player will not be tracked", remoteexception1);
        if(true) goto _L2; else goto _L1
_L1:
    }

    void baseRelease()
    {
        Object obj = mStateLock;
        obj;
        JVM INSTR monitorenter ;
        if(mState != 0)
        {
            getService().releasePlayer(mPlayerIId);
            mState = 0;
        }
        obj;
        JVM INSTR monitorexit ;
_L1:
        if(mAppOps != null)
            mAppOps.stopWatchingMode(mAppOpsCallback);
_L2:
        return;
        Object obj1;
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
        obj1;
        Log.e("PlayerBase", "Error talking to audio service, the player will still be tracked", ((Throwable) (obj1)));
          goto _L1
        obj1;
          goto _L2
    }

    int baseSetAuxEffectSendLevel(float f)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        mAuxEffectSendLevel = f;
        flag = isRestricted_sync();
        if(flag)
            return 0;
        obj;
        JVM INSTR monitorexit ;
        return playerSetAuxEffectSendLevel(false, f);
        Exception exception;
        exception;
        throw exception;
    }

    void baseSetPan(float f)
    {
        f = Math.min(Math.max(-1F, f), 1.0F);
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(f < 0.0F)
            break MISSING_BLOCK_LABEL_51;
        mPanMultiplierL = 1.0F - f;
        mPanMultiplierR = 1.0F;
_L1:
        obj;
        JVM INSTR monitorexit ;
        baseSetVolume(mLeftVolume, mRightVolume);
        return;
        mPanMultiplierL = 1.0F;
        mPanMultiplierR = 1.0F + f;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    void baseSetStartDelayMs(int i)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mStartDelayMs = Math.max(i, 0);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void baseSetVolume(float f, float f1)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        mLeftVolume = f;
        mRightVolume = f1;
        flag = isRestricted_sync();
        obj;
        JVM INSTR monitorexit ;
        playerSetVolume(flag, mPanMultiplierL * f, mPanMultiplierR * f1);
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void baseStart()
    {
        Object obj = mStateLock;
        obj;
        JVM INSTR monitorenter ;
        mState = 2;
        getService().playerEvent(mPlayerIId, mState);
        obj;
        JVM INSTR monitorexit ;
_L2:
        obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(isRestricted_sync())
            playerSetVolume(true, 0.0F, 0.0F);
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
        obj1;
        Log.e("PlayerBase", "Error talking to audio service, STARTED state will not be tracked", ((Throwable) (obj1)));
        if(true) goto _L2; else goto _L1
_L1:
        obj1;
        throw obj1;
    }

    void baseStop()
    {
        Object obj = mStateLock;
        obj;
        JVM INSTR monitorenter ;
        mState = 4;
        getService().playerEvent(mPlayerIId, mState);
        obj;
        JVM INSTR monitorexit ;
_L1:
        return;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        RemoteException remoteexception;
        remoteexception;
        Log.e("PlayerBase", "Error talking to audio service, STOPPED state will not be tracked", remoteexception);
          goto _L1
    }

    void baseUpdateAudioAttributes(AudioAttributes audioattributes)
    {
        Object obj;
        boolean flag;
        if(audioattributes == null)
            throw new IllegalArgumentException("Illegal null AudioAttributes");
        try
        {
            getService().playerAttributes(mPlayerIId, audioattributes);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            Log.e("PlayerBase", "Error talking to audio service, STARTED state will not be tracked", ((Throwable) (obj)));
        }
        obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mAttributes != audioattributes)
            flag = true;
        else
            flag = false;
        mAttributes = audioattributes;
        updateAppOpsPlayAudio_sync(flag);
        obj;
        JVM INSTR monitorexit ;
        return;
        audioattributes;
        throw audioattributes;
    }

    protected int getStartDelayMs()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i = mStartDelayMs;
        obj;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    boolean isRestricted_sync()
    {
        if(mHasAppOpsPlayAudio)
            return false;
        if((mAttributes.getAllFlags() & 0x40) != 0)
            return false;
        if((mAttributes.getAllFlags() & 1) != 0 && mAttributes.getUsage() == 13)
        {
            boolean flag = false;
            boolean flag1;
            try
            {
                flag1 = getService().isCameraSoundForced();
            }
            catch(RemoteException remoteexception)
            {
                Log.e("PlayerBase", "Cannot access AudioService in isRestricted_sync()");
                flag1 = flag;
            }
            catch(NullPointerException nullpointerexception)
            {
                Log.e("PlayerBase", "Null AudioService in isRestricted_sync()");
                flag1 = flag;
            }
            if(flag1)
                return false;
        }
        return true;
    }

    abstract int playerApplyVolumeShaper(VolumeShaper.Configuration configuration, VolumeShaper.Operation operation);

    abstract VolumeShaper.State playerGetVolumeShaperState(int i);

    abstract void playerPause();

    abstract int playerSetAuxEffectSendLevel(boolean flag, float f);

    abstract void playerSetVolume(boolean flag, float f, float f1);

    abstract void playerStart();

    abstract void playerStop();

    public void setStartDelayMs(int i)
    {
        baseSetStartDelayMs(i);
    }

    void updateAppOpsPlayAudio_sync(boolean flag)
    {
        boolean flag1;
        boolean flag2;
        int i;
        flag1 = true;
        flag2 = mHasAppOpsPlayAudio;
        i = 1;
        if(mAppOps != null)
            i = mAppOps.checkAudioOperation(28, mAttributes.getUsage(), Process.myUid(), ActivityThread.currentPackageName());
        if(i != 0)
            flag1 = false;
        try
        {
            mHasAppOpsPlayAudio = flag1;
        }
        catch(RemoteException remoteexception)
        {
            mHasAppOpsPlayAudio = false;
        }
        if(flag2 == mHasAppOpsPlayAudio && !flag)
            break MISSING_BLOCK_LABEL_121;
        getService().playerHasOpPlayAudio(mPlayerIId, mHasAppOpsPlayAudio);
        if(isRestricted_sync())
            break MISSING_BLOCK_LABEL_137;
        playerSetVolume(false, mLeftVolume * mPanMultiplierL, mRightVolume * mPanMultiplierR);
        playerSetAuxEffectSendLevel(false, mAuxEffectSendLevel);
_L1:
        return;
        try
        {
            playerSetVolume(true, 0.0F, 0.0F);
            playerSetAuxEffectSendLevel(true, 0.0F);
        }
        catch(Exception exception) { }
          goto _L1
    }

    private static final boolean DEBUG = false;
    private static final boolean DEBUG_APP_OPS = false;
    private static final String TAG = "PlayerBase";
    private static IAudioService sService;
    private IAppOpsService mAppOps;
    private IAppOpsCallback mAppOpsCallback;
    protected AudioAttributes mAttributes;
    protected float mAuxEffectSendLevel;
    private boolean mHasAppOpsPlayAudio;
    private final int mImplType;
    protected float mLeftVolume;
    private final Object mLock = new Object();
    private float mPanMultiplierL;
    private float mPanMultiplierR;
    private int mPlayerIId;
    protected float mRightVolume;
    private int mStartDelayMs;
    private int mState;
    private final Object mStateLock = new Object();
}
