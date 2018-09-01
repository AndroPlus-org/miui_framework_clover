// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.voice;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.*;
import com.android.internal.app.IVoiceInteractionManagerService;
import com.android.internal.os.HandlerCaller;
import com.android.internal.os.SomeArgs;
import java.io.FileDescriptor;
import java.io.PrintWriter;

// Referenced classes of package android.service.voice:
//            VoiceInteractionSession, IVoiceInteractionSessionService

public abstract class VoiceInteractionSessionService extends Service
{

    public VoiceInteractionSessionService()
    {
        mInterface = new IVoiceInteractionSessionService.Stub() {

            public void newSession(IBinder ibinder, Bundle bundle, int i)
            {
                mHandlerCaller.sendMessage(mHandlerCaller.obtainMessageIOO(1, i, ibinder, bundle));
            }

            final VoiceInteractionSessionService this$0;

            
            {
                this$0 = VoiceInteractionSessionService.this;
                super();
            }
        }
;
    }

    void doNewSession(IBinder ibinder, Bundle bundle, int i)
    {
        if(mSession != null)
        {
            mSession.doDestroy();
            mSession = null;
        }
        mSession = onNewSession(bundle);
        mSystemService.deliverNewSession(ibinder, mSession.mSession, mSession.mInteractor);
        mSession.doCreate(mSystemService, ibinder);
_L2:
        return;
        ibinder;
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        if(mSession == null)
        {
            printwriter.println("(no active session)");
        } else
        {
            printwriter.println("VoiceInteractionSession:");
            mSession.dump("  ", filedescriptor, printwriter, as);
        }
    }

    public IBinder onBind(Intent intent)
    {
        return mInterface.asBinder();
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        if(mSession != null)
            mSession.onConfigurationChanged(configuration);
    }

    public void onCreate()
    {
        super.onCreate();
        mSystemService = com.android.internal.app.IVoiceInteractionManagerService.Stub.asInterface(ServiceManager.getService("voiceinteraction"));
        mHandlerCaller = new HandlerCaller(this, Looper.myLooper(), mHandlerCallerCallback, true);
    }

    public void onLowMemory()
    {
        super.onLowMemory();
        if(mSession != null)
            mSession.onLowMemory();
    }

    public abstract VoiceInteractionSession onNewSession(Bundle bundle);

    public void onTrimMemory(int i)
    {
        super.onTrimMemory(i);
        if(mSession != null)
            mSession.onTrimMemory(i);
    }

    static final int MSG_NEW_SESSION = 1;
    HandlerCaller mHandlerCaller;
    final com.android.internal.os.HandlerCaller.Callback mHandlerCallerCallback = new com.android.internal.os.HandlerCaller.Callback() {

        public void executeMessage(Message message)
        {
            SomeArgs someargs = (SomeArgs)message.obj;
            message.what;
            JVM INSTR tableswitch 1 1: default 32
        //                       1 33;
               goto _L1 _L2
_L1:
            return;
_L2:
            doNewSession((IBinder)someargs.arg1, (Bundle)someargs.arg2, someargs.argi1);
            if(true) goto _L1; else goto _L3
_L3:
        }

        final VoiceInteractionSessionService this$0;

            
            {
                this$0 = VoiceInteractionSessionService.this;
                super();
            }
    }
;
    IVoiceInteractionSessionService mInterface;
    VoiceInteractionSession mSession;
    IVoiceInteractionManagerService mSystemService;
}
