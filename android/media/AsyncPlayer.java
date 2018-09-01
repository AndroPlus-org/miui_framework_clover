// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.Context;
import android.net.Uri;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import java.util.LinkedList;

// Referenced classes of package android.media:
//            MediaPlayer, PlayerBase, AudioAttributes

public class AsyncPlayer
{
    private static final class Command
    {

        public String toString()
        {
            return (new StringBuilder()).append("{ code=").append(code).append(" looping=").append(looping).append(" attr=").append(attributes).append(" uri=").append(uri).append(" }").toString();
        }

        AudioAttributes attributes;
        int code;
        Context context;
        boolean looping;
        long requestTime;
        Uri uri;

        private Command()
        {
        }

        Command(Command command)
        {
            this();
        }
    }

    private final class Thread extends java.lang.Thread
    {

        public void run()
        {
_L6:
            LinkedList linkedlist = AsyncPlayer._2D_get0(AsyncPlayer.this);
            linkedlist;
            JVM INSTR monitorenter ;
            Object obj = (Command)AsyncPlayer._2D_get0(AsyncPlayer.this).removeFirst();
            linkedlist;
            JVM INSTR monitorexit ;
            ((Command) (obj)).code;
            JVM INSTR tableswitch 1 2: default 52
        //                       1 99
        //                       2 110;
               goto _L1 _L2 _L3
_L1:
            obj = AsyncPlayer._2D_get0(AsyncPlayer.this);
            obj;
            JVM INSTR monitorenter ;
            if(AsyncPlayer._2D_get0(AsyncPlayer.this).size() != 0)
                break; /* Loop/switch isn't completed */
            AsyncPlayer._2D_set1(AsyncPlayer.this, null);
            AsyncPlayer._2D_wrap0(AsyncPlayer.this);
            obj;
            JVM INSTR monitorexit ;
            return;
            obj;
            throw obj;
_L2:
            AsyncPlayer._2D_wrap1(AsyncPlayer.this, ((Command) (obj)));
            continue; /* Loop/switch isn't completed */
_L3:
            if(AsyncPlayer._2D_get1(AsyncPlayer.this) != null)
            {
                long l = SystemClock.uptimeMillis() - ((Command) (obj)).requestTime;
                if(l > 1000L)
                    Log.w(AsyncPlayer._2D_get2(AsyncPlayer.this), (new StringBuilder()).append("Notification stop delayed by ").append(l).append("msecs").toString());
                AsyncPlayer._2D_get1(AsyncPlayer.this).stop();
                AsyncPlayer._2D_get1(AsyncPlayer.this).release();
                AsyncPlayer._2D_set0(AsyncPlayer.this, null);
            } else
            {
                Log.w(AsyncPlayer._2D_get2(AsyncPlayer.this), "STOP command without a player");
            }
            if(true) goto _L1; else goto _L4
_L4:
            if(true) goto _L6; else goto _L5
_L5:
            Exception exception;
            exception;
            throw exception;
        }

        final AsyncPlayer this$0;

        Thread()
        {
            this$0 = AsyncPlayer.this;
            super((new StringBuilder()).append("AsyncPlayer-").append(AsyncPlayer._2D_get2(AsyncPlayer.this)).toString());
        }
    }


    static LinkedList _2D_get0(AsyncPlayer asyncplayer)
    {
        return asyncplayer.mCmdQueue;
    }

    static MediaPlayer _2D_get1(AsyncPlayer asyncplayer)
    {
        return asyncplayer.mPlayer;
    }

    static String _2D_get2(AsyncPlayer asyncplayer)
    {
        return asyncplayer.mTag;
    }

    static MediaPlayer _2D_set0(AsyncPlayer asyncplayer, MediaPlayer mediaplayer)
    {
        asyncplayer.mPlayer = mediaplayer;
        return mediaplayer;
    }

    static Thread _2D_set1(AsyncPlayer asyncplayer, Thread thread)
    {
        asyncplayer.mThread = thread;
        return thread;
    }

    static void _2D_wrap0(AsyncPlayer asyncplayer)
    {
        asyncplayer.releaseWakeLock();
    }

    static void _2D_wrap1(AsyncPlayer asyncplayer, Command command)
    {
        asyncplayer.startSound(command);
    }

    public AsyncPlayer(String s)
    {
        mState = 2;
        if(s != null)
            mTag = s;
        else
            mTag = "AsyncPlayer";
    }

    private void acquireWakeLock()
    {
        if(mWakeLock != null)
            mWakeLock.acquire();
    }

    private void enqueueLocked(Command command)
    {
        mCmdQueue.add(command);
        if(mThread == null)
        {
            acquireWakeLock();
            mThread = new Thread();
            mThread.start();
        }
    }

    private void releaseWakeLock()
    {
        if(mWakeLock != null)
            mWakeLock.release();
    }

    private void startSound(Command command)
    {
        long l;
        MediaPlayer mediaplayer = JVM INSTR new #99  <Class MediaPlayer>;
        mediaplayer.MediaPlayer();
        mediaplayer.setAudioAttributes(command.attributes);
        mediaplayer.setDataSource(command.context, command.uri);
        mediaplayer.setLooping(command.looping);
        mediaplayer.prepare();
        mediaplayer.start();
        if(mPlayer != null)
            mPlayer.release();
        mPlayer = mediaplayer;
        l = SystemClock.uptimeMillis() - command.requestTime;
        if(l <= 1000L)
            break MISSING_BLOCK_LABEL_119;
        String s = mTag;
        StringBuilder stringbuilder = JVM INSTR new #146 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.w(s, stringbuilder.append("Notification sound delayed by ").append(l).append("msecs").toString());
_L1:
        return;
        Exception exception;
        exception;
        Log.w(mTag, (new StringBuilder()).append("error loading sound for ").append(command.uri).toString(), exception);
          goto _L1
    }

    public void play(Context context, Uri uri, boolean flag, int i)
    {
        PlayerBase.deprecateStreamTypeForPlayback(i, "AsyncPlayer", "play()");
        if(context == null || uri == null)
            return;
        AudioAttributes.Builder builder = JVM INSTR new #190 <Class AudioAttributes$Builder>;
        builder.AudioAttributes.Builder();
        play(context, uri, flag, builder.setInternalLegacyStreamType(i).build());
_L1:
        return;
        context;
        Log.e(mTag, "Call to deprecated AsyncPlayer.play() method caused:", context);
          goto _L1
    }

    public void play(Context context, Uri uri, boolean flag, AudioAttributes audioattributes)
        throws IllegalArgumentException
    {
        Command command;
        while(context == null || uri == null || audioattributes == null) 
            throw new IllegalArgumentException("Illegal null AsyncPlayer.play() argument");
        command = new Command(null);
        command.requestTime = SystemClock.uptimeMillis();
        command.code = 1;
        command.context = context;
        command.uri = uri;
        command.looping = flag;
        command.attributes = audioattributes;
        context = mCmdQueue;
        context;
        JVM INSTR monitorenter ;
        enqueueLocked(command);
        mState = 1;
        context;
        JVM INSTR monitorexit ;
        return;
        uri;
        throw uri;
    }

    public void setUsesWakeLock(Context context)
    {
        if(mWakeLock != null || mThread != null)
        {
            throw new RuntimeException((new StringBuilder()).append("assertion failed mWakeLock=").append(mWakeLock).append(" mThread=").append(mThread).toString());
        } else
        {
            mWakeLock = ((PowerManager)context.getSystemService("power")).newWakeLock(1, mTag);
            return;
        }
    }

    public void stop()
    {
        LinkedList linkedlist = mCmdQueue;
        linkedlist;
        JVM INSTR monitorenter ;
        if(mState != 2)
        {
            Command command = JVM INSTR new #6   <Class AsyncPlayer$Command>;
            command.Command(null);
            command.requestTime = SystemClock.uptimeMillis();
            command.code = 2;
            enqueueLocked(command);
            mState = 2;
        }
        linkedlist;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static final int PLAY = 1;
    private static final int STOP = 2;
    private static final boolean mDebug = false;
    private final LinkedList mCmdQueue = new LinkedList();
    private MediaPlayer mPlayer;
    private int mState;
    private String mTag;
    private Thread mThread;
    private android.os.PowerManager.WakeLock mWakeLock;
}
