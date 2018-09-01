// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.voice;

import android.app.Service;
import android.content.*;
import android.hardware.soundtrigger.KeyphraseEnrollmentInfo;
import android.os.*;
import com.android.internal.app.IVoiceInteractionManagerService;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Locale;

// Referenced classes of package android.service.voice:
//            AlwaysOnHotwordDetector, IVoiceInteractionService

public class VoiceInteractionService extends Service
{
    class MyHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 4: default 36
        //                       1 42
        //                       2 52
        //                       3 62
        //                       4 72;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            super.handleMessage(message);
_L7:
            return;
_L2:
            onReady();
            continue; /* Loop/switch isn't completed */
_L3:
            VoiceInteractionService._2D_wrap0(VoiceInteractionService.this);
            continue; /* Loop/switch isn't completed */
_L4:
            VoiceInteractionService._2D_wrap1(VoiceInteractionService.this);
            continue; /* Loop/switch isn't completed */
_L5:
            onLaunchVoiceAssistFromKeyguard();
            if(true) goto _L7; else goto _L6
_L6:
        }

        final VoiceInteractionService this$0;

        MyHandler()
        {
            this$0 = VoiceInteractionService.this;
            super();
        }
    }


    static void _2D_wrap0(VoiceInteractionService voiceinteractionservice)
    {
        voiceinteractionservice.onShutdownInternal();
    }

    static void _2D_wrap1(VoiceInteractionService voiceinteractionservice)
    {
        voiceinteractionservice.onSoundModelsChangedInternal();
    }

    public VoiceInteractionService()
    {
        mInterface = new IVoiceInteractionService.Stub() {

            public void launchVoiceAssistFromKeyguard()
                throws RemoteException
            {
                mHandler.sendEmptyMessage(4);
            }

            public void ready()
            {
                mHandler.sendEmptyMessage(1);
            }

            public void shutdown()
            {
                mHandler.sendEmptyMessage(2);
            }

            public void soundModelsChanged()
            {
                mHandler.sendEmptyMessage(3);
            }

            final VoiceInteractionService this$0;

            
            {
                this$0 = VoiceInteractionService.this;
                super();
            }
        }
;
    }

    public static boolean isActiveService(Context context, ComponentName componentname)
    {
        context = android.provider.Settings.Secure.getString(context.getContentResolver(), "voice_interaction_service");
        if(context == null || context.isEmpty())
            return false;
        context = ComponentName.unflattenFromString(context);
        if(context == null)
            return false;
        else
            return context.equals(componentname);
    }

    private void onShutdownInternal()
    {
        onShutdown();
        safelyShutdownHotwordDetector();
    }

    private void onSoundModelsChangedInternal()
    {
        this;
        JVM INSTR monitorenter ;
        if(mHotwordDetector != null)
            mHotwordDetector.onSoundModelsChanged();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void safelyShutdownHotwordDetector()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mHotwordDetector != null)
        {
            mHotwordDetector.stopRecognition();
            mHotwordDetector.invalidate();
            mHotwordDetector = null;
        }
        obj;
        JVM INSTR monitorexit ;
_L2:
        return;
        Exception exception1;
        exception1;
        obj;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public final AlwaysOnHotwordDetector createAlwaysOnHotwordDetector(String s, Locale locale, AlwaysOnHotwordDetector.Callback callback)
    {
        if(mSystemService == null)
            throw new IllegalStateException("Not available until onReady() is called");
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        safelyShutdownHotwordDetector();
        AlwaysOnHotwordDetector alwaysonhotworddetector = JVM INSTR new #102 <Class AlwaysOnHotwordDetector>;
        alwaysonhotworddetector.AlwaysOnHotwordDetector(s, locale, callback, mKeyphraseEnrollmentInfo, mInterface, mSystemService);
        mHotwordDetector = alwaysonhotworddetector;
        obj;
        JVM INSTR monitorexit ;
        return mHotwordDetector;
        s;
        throw s;
    }

    protected void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.println("VOICE INTERACTION");
        filedescriptor = ((FileDescriptor) (mLock));
        filedescriptor;
        JVM INSTR monitorenter ;
        printwriter.println("  AlwaysOnHotwordDetector");
        if(mHotwordDetector != null)
            break MISSING_BLOCK_LABEL_35;
        printwriter.println("    NULL");
_L1:
        filedescriptor;
        JVM INSTR monitorexit ;
        return;
        mHotwordDetector.dump("    ", printwriter);
          goto _L1
        printwriter;
        throw printwriter;
    }

    public int getDisabledShowContext()
    {
        int i;
        try
        {
            i = mSystemService.getDisabledShowContext();
        }
        catch(RemoteException remoteexception)
        {
            return 0;
        }
        return i;
    }

    protected final KeyphraseEnrollmentInfo getKeyphraseEnrollmentInfo()
    {
        return mKeyphraseEnrollmentInfo;
    }

    public final boolean isKeyphraseAndLocaleSupportedForHotword(String s, Locale locale)
    {
        boolean flag = false;
        if(mKeyphraseEnrollmentInfo == null)
            return false;
        if(mKeyphraseEnrollmentInfo.getKeyphraseMetadata(s, locale) != null)
            flag = true;
        return flag;
    }

    public IBinder onBind(Intent intent)
    {
        if("android.service.voice.VoiceInteractionService".equals(intent.getAction()))
            return mInterface.asBinder();
        else
            return null;
    }

    public void onCreate()
    {
        super.onCreate();
        mHandler = new MyHandler();
    }

    public void onLaunchVoiceAssistFromKeyguard()
    {
    }

    public void onReady()
    {
        mSystemService = com.android.internal.app.IVoiceInteractionManagerService.Stub.asInterface(ServiceManager.getService("voiceinteraction"));
        mKeyphraseEnrollmentInfo = new KeyphraseEnrollmentInfo(getPackageManager());
    }

    public void onShutdown()
    {
    }

    public void setDisabledShowContext(int i)
    {
        mSystemService.setDisabledShowContext(i);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void showSession(Bundle bundle, int i)
    {
        if(mSystemService == null)
            throw new IllegalStateException("Not available until onReady() is called");
        mSystemService.showSession(mInterface, bundle, i);
_L2:
        return;
        bundle;
        if(true) goto _L2; else goto _L1
_L1:
    }

    static final int MSG_LAUNCH_VOICE_ASSIST_FROM_KEYGUARD = 4;
    static final int MSG_READY = 1;
    static final int MSG_SHUTDOWN = 2;
    static final int MSG_SOUND_MODELS_CHANGED = 3;
    public static final String SERVICE_INTERFACE = "android.service.voice.VoiceInteractionService";
    public static final String SERVICE_META_DATA = "android.voice_interaction";
    MyHandler mHandler;
    private AlwaysOnHotwordDetector mHotwordDetector;
    IVoiceInteractionService mInterface;
    private KeyphraseEnrollmentInfo mKeyphraseEnrollmentInfo;
    private final Object mLock = new Object();
    IVoiceInteractionManagerService mSystemService;
}
