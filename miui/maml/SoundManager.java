// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.media.SoundPool;
import android.os.*;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.*;
import miui.content.res.ThemeResources;

// Referenced classes of package miui.maml:
//            ScreenContext, ResourceManager

public class SoundManager
    implements android.media.SoundPool.OnLoadCompleteListener
{
    public static final class Command extends Enum
    {

        public static Command parse(String s)
        {
            if("pause".equals(s))
                return Pause;
            if("resume".equals(s))
                return Resume;
            if("stop".equals(s))
                return Stop;
            else
                return Play;
        }

        public static Command valueOf(String s)
        {
            return (Command)Enum.valueOf(miui/maml/SoundManager$Command, s);
        }

        public static Command[] values()
        {
            return $VALUES;
        }

        private static final Command $VALUES[];
        public static final Command Pause;
        public static final Command Play;
        public static final Command Resume;
        public static final Command Stop;

        static 
        {
            Play = new Command("Play", 0);
            Pause = new Command("Pause", 1);
            Resume = new Command("Resume", 2);
            Stop = new Command("Stop", 3);
            $VALUES = (new Command[] {
                Play, Pause, Resume, Stop
            });
        }

        private Command(String s, int i)
        {
            super(s, i);
        }
    }

    public static class SoundOptions
    {

        public boolean mKeepCur;
        public boolean mLoop;
        public float mVolume;

        public SoundOptions(boolean flag, boolean flag1, float f)
        {
            mKeepCur = flag;
            mLoop = flag1;
            if(f < 0.0F)
                mVolume = 0.0F;
            else
            if(f > 1.0F)
                mVolume = 1.0F;
            else
                mVolume = f;
        }
    }


    static Object _2D_get0(SoundManager soundmanager)
    {
        return soundmanager.mInitSignal;
    }

    static SoundPool _2D_get1(SoundManager soundmanager)
    {
        return soundmanager.mSoundPool;
    }

    private static int[] _2D_getmiui_2D_maml_2D_SoundManager$CommandSwitchesValues()
    {
        if(_2D_miui_2D_maml_2D_SoundManager$CommandSwitchesValues != null)
            return _2D_miui_2D_maml_2D_SoundManager$CommandSwitchesValues;
        int ai[] = new int[Command.values().length];
        try
        {
            ai[Command.Pause.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[Command.Play.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[Command.Resume.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[Command.Stop.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_miui_2D_maml_2D_SoundManager$CommandSwitchesValues = ai;
        return ai;
    }

    static boolean _2D_set0(SoundManager soundmanager, boolean flag)
    {
        soundmanager.mInitialized = flag;
        return flag;
    }

    static SoundPool _2D_set1(SoundManager soundmanager, SoundPool soundpool)
    {
        soundmanager.mSoundPool = soundpool;
        return soundpool;
    }

    public SoundManager(ScreenContext screencontext)
    {
        mSoundPoolMap = new HashMap();
        mPendingSoundMap = new HashMap();
        mPlayingSoundMap = new ArrayList();
        mInitSignal = new Object();
        mResourceManager = screencontext.mResourceManager;
        mHandler = screencontext.getHandler();
    }

    private void init()
    {
        if(mInitialized)
            return;
        if(Thread.currentThread().getId() != mHandler.getLooper().getThread().getId()) goto _L2; else goto _L1
_L1:
        mSoundPool = new SoundPool(8, 3, 100);
        mSoundPool.setOnLoadCompleteListener(this);
        mInitialized = true;
_L5:
        return;
_L2:
        mHandler.post(new Runnable() {

            public void run()
            {
                SoundManager._2D_set1(SoundManager.this, new SoundPool(8, 3, 100));
                SoundManager._2D_get1(SoundManager.this).setOnLoadCompleteListener(SoundManager.this);
                Object obj1 = SoundManager._2D_get0(SoundManager.this);
                obj1;
                JVM INSTR monitorenter ;
                SoundManager._2D_set0(SoundManager.this, true);
                SoundManager._2D_get0(SoundManager.this).notify();
                obj1;
                JVM INSTR monitorexit ;
                return;
                Exception exception1;
                exception1;
                throw exception1;
            }

            final SoundManager this$0;

            
            {
                this$0 = SoundManager.this;
                super();
            }
        }
);
        Object obj = mInitSignal;
        obj;
        JVM INSTR monitorenter ;
_L3:
        boolean flag = mInitialized;
        if(!flag)
            break MISSING_BLOCK_LABEL_98;
        obj;
        JVM INSTR monitorexit ;
        continue; /* Loop/switch isn't completed */
        try
        {
            mInitSignal.wait();
        }
        catch(InterruptedException interruptedexception) { }
          goto _L3
        Exception exception;
        exception;
        throw exception;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private int playSoundImp(int i, SoundOptions soundoptions)
    {
        this;
        JVM INSTR monitorenter ;
        SoundPool soundpool = mSoundPool;
        if(soundpool != null)
            break MISSING_BLOCK_LABEL_15;
        this;
        JVM INSTR monitorexit ;
        return 0;
        if(!soundoptions.mKeepCur)
            stopAllPlaying();
        float f;
        float f1;
        soundpool = mSoundPool;
        f = soundoptions.mVolume;
        f1 = soundoptions.mVolume;
        byte byte0;
        if(soundoptions.mLoop)
            byte0 = -1;
        else
            byte0 = 0;
        i = soundpool.play(i, f, f1, 1, byte0, 1.0F);
        mPlayingSoundMap.add(Integer.valueOf(i));
        this;
        JVM INSTR monitorexit ;
        return i;
        soundoptions;
        Log.e("MamlSoundManager", soundoptions.toString());
        this;
        JVM INSTR monitorexit ;
        return 0;
        soundoptions;
        throw soundoptions;
    }

    public void onLoadComplete(SoundPool soundpool, int i, int j)
    {
        if(j == 0)
            playSoundImp(i, (SoundOptions)mPendingSoundMap.get(Integer.valueOf(i)));
        mPendingSoundMap.remove(Integer.valueOf(i));
    }

    public void pause()
    {
        stopAllPlaying();
    }

    public int playSound(String s, SoundOptions soundoptions)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj;
        if(!mInitialized)
            init();
        obj = mSoundPool;
        if(obj != null)
            break MISSING_BLOCK_LABEL_26;
        this;
        JVM INSTR monitorexit ;
        return 0;
        Object obj1 = (Integer)mSoundPoolMap.get(s);
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_457;
        if(android.os.Build.VERSION.SDK_INT >= 26) goto _L2; else goto _L1
_L1:
        Object obj2 = mResourceManager.getFile(s);
        if(obj2 != null)
            break MISSING_BLOCK_LABEL_98;
        soundoptions = JVM INSTR new #234 <Class StringBuilder>;
        soundoptions.StringBuilder();
        Log.e("MamlSoundManager", soundoptions.append("the sound does not exist: ").append(s).toString());
        this;
        JVM INSTR monitorexit ;
        return 0;
        if(((MemoryFile) (obj2)).length() <= 0x80000)
            break MISSING_BLOCK_LABEL_140;
        Log.w("MamlSoundManager", String.format("the sound file is larger than %d KB: %s", new Object[] {
            Integer.valueOf(512), s
        }));
        this;
        JVM INSTR monitorexit ;
        return 0;
        obj = obj1;
        obj1 = Integer.valueOf(mSoundPool.load(((MemoryFile) (obj2)).getFileDescriptor(), 0L, ((MemoryFile) (obj2)).length(), 1));
        obj = obj1;
        mSoundPoolMap.put(s, obj1);
        obj = obj1;
        ((MemoryFile) (obj2)).close();
        obj = obj1;
_L3:
        mPendingSoundMap.put(obj, soundoptions);
        this;
        JVM INSTR monitorexit ;
        return 0;
        s;
        obj1 = JVM INSTR new #234 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.e("MamlSoundManager", ((StringBuilder) (obj1)).append("fail to load sound. ").append(s.toString()).toString());
          goto _L3
        s;
        throw s;
_L2:
        obj2 = JVM INSTR new #278 <Class File>;
        obj = JVM INSTR new #234 <Class StringBuilder>;
        ((StringBuilder) (obj)).StringBuilder();
        ((File) (obj2)).File(((StringBuilder) (obj)).append(ThemeResources.THEME_MAGIC_PATH).append("lockscreen_audio/").append("advance/").append(s).toString());
        if(((File) (obj2)).exists())
            break MISSING_BLOCK_LABEL_331;
        soundoptions = JVM INSTR new #234 <Class StringBuilder>;
        soundoptions.StringBuilder();
        Log.e("MamlSoundManager", soundoptions.append("the sound does not exist: ").append(s).toString());
        this;
        JVM INSTR monitorexit ;
        return 0;
        if(((File) (obj2)).length() <= 0x80000L)
            break MISSING_BLOCK_LABEL_375;
        Log.w("MamlSoundManager", String.format("the sound file is larger than %d KB: %s", new Object[] {
            Integer.valueOf(512), s
        }));
        this;
        JVM INSTR monitorexit ;
        return 0;
        obj = obj1;
        ParcelFileDescriptor parcelfiledescriptor = ParcelFileDescriptor.open(((File) (obj2)), 0x10000000);
        obj = obj1;
        if(parcelfiledescriptor == null) goto _L3; else goto _L4
_L4:
        obj = obj1;
        obj1 = Integer.valueOf(mSoundPool.load(parcelfiledescriptor.getFileDescriptor(), 0L, ((File) (obj2)).length(), 1));
        obj = obj1;
        mSoundPoolMap.put(s, obj1);
        obj = obj1;
          goto _L3
        s;
        Log.e("MamlSoundManager", "fail to load sound. ", s);
          goto _L3
        int i = playSoundImp(((Integer) (obj1)).intValue(), soundoptions);
        this;
        JVM INSTR monitorexit ;
        return i;
    }

    public void playSound(int i, Command command)
    {
        this;
        JVM INSTR monitorenter ;
        SoundPool soundpool;
        if(!mInitialized)
            init();
        soundpool = mSoundPool;
        if(soundpool != null && i > 0)
            break MISSING_BLOCK_LABEL_29;
        this;
        JVM INSTR monitorexit ;
        return;
        int j = _2D_getmiui_2D_maml_2D_SoundManager$CommandSwitchesValues()[command.ordinal()];
        j;
        JVM INSTR tableswitch 1 4: default 72
    //                   1 75
    //                   2 72
    //                   3 91
    //                   4 102;
           goto _L1 _L2 _L1 _L3 _L4
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        mSoundPool.pause(i);
          goto _L1
        command;
        throw command;
_L3:
        mSoundPool.resume(i);
          goto _L1
_L4:
        mSoundPool.stop(i);
        mPlayingSoundMap.remove(Integer.valueOf(i));
          goto _L1
    }

    public void release()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mInitialized;
        if(flag)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        stopAllPlaying();
        if(mSoundPool != null)
        {
            mSoundPoolMap.clear();
            mSoundPool.setOnLoadCompleteListener(null);
            mSoundPool.release();
            mSoundPool = null;
        }
        mInitialized = false;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected void stopAllPlaying()
    {
        if(mPlayingSoundMap.isEmpty())
            return;
        if(mSoundPool != null)
        {
            Integer integer;
            for(Iterator iterator = mPlayingSoundMap.iterator(); iterator.hasNext(); mSoundPool.stop(integer.intValue()))
                integer = (Integer)iterator.next();

        }
        mPlayingSoundMap.clear();
    }

    private static final int _2D_miui_2D_maml_2D_SoundManager$CommandSwitchesValues[];
    private static final String ADVANCE = "advance/";
    private static final String LOCKSCREEN_AUDIO = "lockscreen_audio/";
    private static final String LOG_TAG = "MamlSoundManager";
    private static final int MAX_FILE_SIZE = 0x80000;
    private static final int MAX_STREAMS = 8;
    private Handler mHandler;
    private Object mInitSignal;
    private boolean mInitialized;
    private HashMap mPendingSoundMap;
    private ArrayList mPlayingSoundMap;
    private ResourceManager mResourceManager;
    private SoundPool mSoundPool;
    private HashMap mSoundPoolMap;
}
