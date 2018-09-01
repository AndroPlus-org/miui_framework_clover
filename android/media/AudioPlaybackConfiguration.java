// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.*;
import android.util.Log;
import java.io.PrintWriter;
import java.util.Objects;

// Referenced classes of package android.media:
//            AudioAttributes, PlayerProxy, IPlayer

public final class AudioPlaybackConfiguration
    implements Parcelable
{
    static final class IPlayerShell
        implements android.os.IBinder.DeathRecipient
    {

        public void binderDied()
        {
            if(mMonitor != null)
                AudioPlaybackConfiguration._2D_wrap0(mMonitor);
        }

        IPlayer getIPlayer()
        {
            return mIPlayer;
        }

        void monitorDeath()
        {
            this;
            JVM INSTR monitorenter ;
            IPlayer iplayer = mIPlayer;
            if(iplayer != null)
                break MISSING_BLOCK_LABEL_14;
            this;
            JVM INSTR monitorexit ;
            return;
            mIPlayer.asBinder().linkToDeath(this, 0);
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
            RemoteException remoteexception;
            remoteexception;
            if(mMonitor == null)
                break MISSING_BLOCK_LABEL_86;
            String s = AudioPlaybackConfiguration._2D_get0();
            StringBuilder stringbuilder = JVM INSTR new #51  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.w(s, stringbuilder.append("Could not link to client death for piid=").append(AudioPlaybackConfiguration._2D_get1(mMonitor)).toString(), remoteexception);
              goto _L1
            Exception exception;
            exception;
            throw exception;
            Log.w(AudioPlaybackConfiguration._2D_get0(), "Could not link to client death", remoteexception);
              goto _L1
        }

        void release()
        {
            this;
            JVM INSTR monitorenter ;
            IPlayer iplayer = mIPlayer;
            if(iplayer != null)
                break MISSING_BLOCK_LABEL_14;
            this;
            JVM INSTR monitorexit ;
            return;
            mIPlayer.asBinder().unlinkToDeath(this, 0);
            mIPlayer = null;
            Binder.flushPendingCommands();
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private volatile IPlayer mIPlayer;
        final AudioPlaybackConfiguration mMonitor;

        IPlayerShell(AudioPlaybackConfiguration audioplaybackconfiguration, IPlayer iplayer)
        {
            mMonitor = audioplaybackconfiguration;
            mIPlayer = iplayer;
        }
    }

    public static interface PlayerDeathMonitor
    {

        public abstract void playerDeath(int i);
    }


    static String _2D_get0()
    {
        return TAG;
    }

    static int _2D_get1(AudioPlaybackConfiguration audioplaybackconfiguration)
    {
        return audioplaybackconfiguration.mPlayerIId;
    }

    static void _2D_wrap0(AudioPlaybackConfiguration audioplaybackconfiguration)
    {
        audioplaybackconfiguration.playerDied();
    }

    private AudioPlaybackConfiguration(int i)
    {
        mPlayerIId = i;
        mIPlayerShell = null;
    }

    public AudioPlaybackConfiguration(PlayerBase.PlayerIdCard playeridcard, int i, int j, int k)
    {
        mPlayerIId = i;
        mPlayerType = playeridcard.mPlayerType;
        mClientUid = j;
        mClientPid = k;
        mPlayerState = 1;
        mPlayerAttr = playeridcard.mAttributes;
        if(sPlayerDeathMonitor != null && playeridcard.mIPlayer != null)
            mIPlayerShell = new IPlayerShell(this, playeridcard.mIPlayer);
        else
            mIPlayerShell = null;
    }

    private AudioPlaybackConfiguration(Parcel parcel)
    {
        mPlayerIId = parcel.readInt();
        mPlayerType = parcel.readInt();
        mClientUid = parcel.readInt();
        mClientPid = parcel.readInt();
        mPlayerState = parcel.readInt();
        mPlayerAttr = (AudioAttributes)AudioAttributes.CREATOR.createFromParcel(parcel);
        parcel = IPlayer.Stub.asInterface(parcel.readStrongBinder());
        if(parcel == null)
            parcel = null;
        else
            parcel = new IPlayerShell(null, parcel);
        mIPlayerShell = parcel;
    }

    AudioPlaybackConfiguration(Parcel parcel, AudioPlaybackConfiguration audioplaybackconfiguration)
    {
        this(parcel);
    }

    public static AudioPlaybackConfiguration anonymizedCopy(AudioPlaybackConfiguration audioplaybackconfiguration)
    {
        AudioPlaybackConfiguration audioplaybackconfiguration1 = new AudioPlaybackConfiguration(audioplaybackconfiguration.mPlayerIId);
        audioplaybackconfiguration1.mPlayerState = audioplaybackconfiguration.mPlayerState;
        audioplaybackconfiguration1.mPlayerAttr = (new AudioAttributes.Builder()).setUsage(audioplaybackconfiguration.mPlayerAttr.getUsage()).setContentType(audioplaybackconfiguration.mPlayerAttr.getContentType()).setFlags(audioplaybackconfiguration.mPlayerAttr.getFlags()).build();
        audioplaybackconfiguration1.mPlayerType = -1;
        audioplaybackconfiguration1.mClientUid = -1;
        audioplaybackconfiguration1.mClientPid = -1;
        audioplaybackconfiguration1.mIPlayerShell = null;
        return audioplaybackconfiguration1;
    }

    private void playerDied()
    {
        if(sPlayerDeathMonitor != null)
            sPlayerDeathMonitor.playerDeath(mPlayerIId);
    }

    public static String toLogFriendlyPlayerState(int i)
    {
        switch(i)
        {
        default:
            return "unknown player state - FIXME";

        case -1: 
            return "unknown";

        case 0: // '\0'
            return "released";

        case 1: // '\001'
            return "idle";

        case 2: // '\002'
            return "started";

        case 3: // '\003'
            return "paused";

        case 4: // '\004'
            return "stopped";
        }
    }

    public static String toLogFriendlyPlayerType(int i)
    {
        switch(i)
        {
        case 0: // '\0'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        default:
            return (new StringBuilder()).append("unknown player type ").append(i).append(" - FIXME").toString();

        case -1: 
            return "unknown";

        case 1: // '\001'
            return "android.media.AudioTrack";

        case 2: // '\002'
            return "android.media.MediaPlayer";

        case 3: // '\003'
            return "android.media.SoundPool";

        case 11: // '\013'
            return "OpenSL ES AudioPlayer (Buffer Queue)";

        case 12: // '\f'
            return "OpenSL ES AudioPlayer (URI/FD)";

        case 13: // '\r'
            return "AAudio";

        case 14: // '\016'
            return "hardware source";

        case 15: // '\017'
            return "external proxy";
        }
    }

    public static String toLogFriendlyString(AudioPlaybackConfiguration audioplaybackconfiguration)
    {
        return new String((new StringBuilder()).append("ID:").append(audioplaybackconfiguration.mPlayerIId).append(" -- type:").append(toLogFriendlyPlayerType(audioplaybackconfiguration.mPlayerType)).append(" -- u/pid:").append(audioplaybackconfiguration.mClientUid).append("/").append(audioplaybackconfiguration.mClientPid).append(" -- state:").append(toLogFriendlyPlayerState(audioplaybackconfiguration.mPlayerState)).append(" -- attr:").append(audioplaybackconfiguration.mPlayerAttr).toString());
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(PrintWriter printwriter)
    {
        printwriter.println((new StringBuilder()).append("  ").append(toLogFriendlyString(this)).toString());
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(obj == null || (obj instanceof AudioPlaybackConfiguration) ^ true)
            return false;
        obj = (AudioPlaybackConfiguration)obj;
        if(mPlayerIId == ((AudioPlaybackConfiguration) (obj)).mPlayerIId && mPlayerType == ((AudioPlaybackConfiguration) (obj)).mPlayerType && mClientUid == ((AudioPlaybackConfiguration) (obj)).mClientUid)
        {
            if(mClientPid != ((AudioPlaybackConfiguration) (obj)).mClientPid)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public AudioAttributes getAudioAttributes()
    {
        return mPlayerAttr;
    }

    public int getClientPid()
    {
        return mClientPid;
    }

    public int getClientUid()
    {
        return mClientUid;
    }

    IPlayer getIPlayer()
    {
        Object obj = null;
        this;
        JVM INSTR monitorenter ;
        IPlayerShell iplayershell = mIPlayerShell;
        this;
        JVM INSTR monitorexit ;
        if(iplayershell != null)
            obj = iplayershell.getIPlayer();
        return ((IPlayer) (obj));
        obj;
        throw obj;
    }

    public int getPlayerInterfaceId()
    {
        return mPlayerIId;
    }

    public PlayerProxy getPlayerProxy()
    {
        Object obj = null;
        this;
        JVM INSTR monitorenter ;
        IPlayerShell iplayershell = mIPlayerShell;
        this;
        JVM INSTR monitorexit ;
        if(iplayershell != null)
            obj = new PlayerProxy(this);
        return ((PlayerProxy) (obj));
        obj;
        throw obj;
    }

    public int getPlayerState()
    {
        return mPlayerState;
    }

    public int getPlayerType()
    {
        switch(mPlayerType)
        {
        default:
            return mPlayerType;

        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
            return -1;
        }
    }

    public boolean handleAudioAttributesEvent(AudioAttributes audioattributes)
    {
        boolean flag = audioattributes.equals(mPlayerAttr);
        mPlayerAttr = audioattributes;
        return flag ^ true;
    }

    public boolean handleStateEvent(int i)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        if(mPlayerState != i)
            flag = true;
        else
            flag = false;
        mPlayerState = i;
        if(!flag || i != 0)
            break MISSING_BLOCK_LABEL_44;
        if(mIPlayerShell != null)
        {
            mIPlayerShell.release();
            mIPlayerShell = null;
        }
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(mPlayerIId), Integer.valueOf(mPlayerType), Integer.valueOf(mClientUid), Integer.valueOf(mClientPid)
        });
    }

    public void init()
    {
        this;
        JVM INSTR monitorenter ;
        if(mIPlayerShell != null)
            mIPlayerShell.monitorDeath();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isActive()
    {
        switch(mPlayerState)
        {
        default:
            return false;

        case 2: // '\002'
            return true;
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        Object obj;
        obj = null;
        parcel.writeInt(mPlayerIId);
        parcel.writeInt(mPlayerType);
        parcel.writeInt(mClientUid);
        parcel.writeInt(mClientPid);
        parcel.writeInt(mPlayerState);
        mPlayerAttr.writeToParcel(parcel, 0);
        this;
        JVM INSTR monitorenter ;
        IPlayerShell iplayershell = mIPlayerShell;
        this;
        JVM INSTR monitorexit ;
        if(iplayershell != null)
            obj = iplayershell.getIPlayer();
        parcel.writeStrongInterface(((android.os.IInterface) (obj)));
        return;
        parcel;
        throw parcel;
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AudioPlaybackConfiguration createFromParcel(Parcel parcel)
        {
            return new AudioPlaybackConfiguration(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AudioPlaybackConfiguration[] newArray(int i)
        {
            return new AudioPlaybackConfiguration[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DEBUG = false;
    public static final int PLAYER_PIID_INVALID = -1;
    public static final int PLAYER_STATE_IDLE = 1;
    public static final int PLAYER_STATE_PAUSED = 3;
    public static final int PLAYER_STATE_RELEASED = 0;
    public static final int PLAYER_STATE_STARTED = 2;
    public static final int PLAYER_STATE_STOPPED = 4;
    public static final int PLAYER_STATE_UNKNOWN = -1;
    public static final int PLAYER_TYPE_AAUDIO = 13;
    public static final int PLAYER_TYPE_EXTERNAL_PROXY = 15;
    public static final int PLAYER_TYPE_HW_SOURCE = 14;
    public static final int PLAYER_TYPE_JAM_AUDIOTRACK = 1;
    public static final int PLAYER_TYPE_JAM_MEDIAPLAYER = 2;
    public static final int PLAYER_TYPE_JAM_SOUNDPOOL = 3;
    public static final int PLAYER_TYPE_SLES_AUDIOPLAYER_BUFFERQUEUE = 11;
    public static final int PLAYER_TYPE_SLES_AUDIOPLAYER_URI_FD = 12;
    public static final int PLAYER_TYPE_UNKNOWN = -1;
    public static final int PLAYER_UPID_INVALID = -1;
    private static final String TAG = new String("AudioPlaybackConfiguration");
    public static PlayerDeathMonitor sPlayerDeathMonitor;
    private int mClientPid;
    private int mClientUid;
    private IPlayerShell mIPlayerShell;
    private AudioAttributes mPlayerAttr;
    private final int mPlayerIId;
    private int mPlayerState;
    private int mPlayerType;

}
