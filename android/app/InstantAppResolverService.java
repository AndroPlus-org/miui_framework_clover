// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.util.Slog;
import com.android.internal.os.SomeArgs;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package android.app:
//            Service

public abstract class InstantAppResolverService extends Service
{
    public static final class InstantAppResolutionCallback
    {

        public void onInstantAppResolveInfo(List list)
        {
            Bundle bundle;
            bundle = new Bundle();
            bundle.putParcelableList("android.app.extra.RESOLVE_INFO", list);
            bundle.putInt("android.app.extra.SEQUENCE", mSequence);
            mCallback.sendResult(bundle);
_L2:
            return;
            list;
            if(true) goto _L2; else goto _L1
_L1:
        }

        private final IRemoteCallback mCallback;
        private final int mSequence;

        InstantAppResolutionCallback(int i, IRemoteCallback iremotecallback)
        {
            mCallback = iremotecallback;
            mSequence = i;
        }
    }

    private final class ServiceHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            int i = message.what;
            i;
            JVM INSTR tableswitch 1 2: default 28
        //                       1 55
        //                       2 115;
               goto _L1 _L2 _L3
_L1:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown message: ").append(i).toString());
_L2:
            Object obj = (SomeArgs)message.obj;
            IRemoteCallback iremotecallback = (IRemoteCallback)((SomeArgs) (obj)).arg1;
            int ai1[] = (int[])((SomeArgs) (obj)).arg2;
            obj = (String)((SomeArgs) (obj)).arg3;
            int j = message.arg1;
            _onGetInstantAppResolveInfo(ai1, ((String) (obj)), new InstantAppResolutionCallback(j, iremotecallback));
_L5:
            return;
_L3:
            Object obj1 = (SomeArgs)message.obj;
            message = (IRemoteCallback)((SomeArgs) (obj1)).arg1;
            int ai[] = (int[])((SomeArgs) (obj1)).arg2;
            String s = (String)((SomeArgs) (obj1)).arg3;
            obj1 = (String)((SomeArgs) (obj1)).arg4;
            _onGetInstantAppIntentFilter(ai, s, ((String) (obj1)), new InstantAppResolutionCallback(-1, message));
            if(true) goto _L5; else goto _L4
_L4:
        }

        public static final int MSG_GET_INSTANT_APP_INTENT_FILTER = 2;
        public static final int MSG_GET_INSTANT_APP_RESOLVE_INFO = 1;
        final InstantAppResolverService this$0;

        public ServiceHandler(Looper looper)
        {
            this$0 = InstantAppResolverService.this;
            super(looper, null, true);
        }
    }


    static boolean _2D_get0()
    {
        return DEBUG_EPHEMERAL;
    }

    public InstantAppResolverService()
    {
    }

    void _onGetInstantAppIntentFilter(int ai[], String s, String s1, InstantAppResolutionCallback instantappresolutioncallback)
    {
        if(DEBUG_EPHEMERAL)
            Slog.d("PackageManager", (new StringBuilder()).append("[").append(s).append("] Phase2 request;").append(" prefix: ").append(Arrays.toString(ai)).toString());
        onGetInstantAppIntentFilter(ai, s, instantappresolutioncallback);
    }

    void _onGetInstantAppResolveInfo(int ai[], String s, InstantAppResolutionCallback instantappresolutioncallback)
    {
        if(DEBUG_EPHEMERAL)
            Slog.d("PackageManager", (new StringBuilder()).append("[").append(s).append("] Phase1 request;").append(" prefix: ").append(Arrays.toString(ai)).toString());
        onGetInstantAppResolveInfo(ai, s, instantappresolutioncallback);
    }

    public final void attachBaseContext(Context context)
    {
        super.attachBaseContext(context);
        mHandler = new ServiceHandler(getLooper());
    }

    Looper getLooper()
    {
        return getBaseContext().getMainLooper();
    }

    public final IBinder onBind(Intent intent)
    {
        return new IInstantAppResolver.Stub() {

            public void getInstantAppIntentFilterList(int ai[], String s, String s1, IRemoteCallback iremotecallback)
            {
                if(InstantAppResolverService._2D_get0())
                    Slog.v("PackageManager", (new StringBuilder()).append("[").append(s).append("] Phase2 called; posting").toString());
                SomeArgs someargs = SomeArgs.obtain();
                someargs.arg1 = iremotecallback;
                someargs.arg2 = ai;
                someargs.arg3 = s;
                someargs.arg4 = s1;
                mHandler.obtainMessage(2, iremotecallback).sendToTarget();
            }

            public void getInstantAppResolveInfoList(int ai[], String s, int i, IRemoteCallback iremotecallback)
            {
                if(InstantAppResolverService._2D_get0())
                    Slog.v("PackageManager", (new StringBuilder()).append("[").append(s).append("] Phase1 called; posting").toString());
                SomeArgs someargs = SomeArgs.obtain();
                someargs.arg1 = iremotecallback;
                someargs.arg2 = ai;
                someargs.arg3 = s;
                mHandler.obtainMessage(1, i, 0, someargs).sendToTarget();
            }

            final InstantAppResolverService this$0;

            
            {
                this$0 = InstantAppResolverService.this;
                super();
            }
        }
;
    }

    public void onGetInstantAppIntentFilter(int ai[], String s, InstantAppResolutionCallback instantappresolutioncallback)
    {
        throw new IllegalStateException("Must define");
    }

    public void onGetInstantAppResolveInfo(int ai[], String s, InstantAppResolutionCallback instantappresolutioncallback)
    {
        throw new IllegalStateException("Must define");
    }

    private static final boolean DEBUG_EPHEMERAL;
    public static final String EXTRA_RESOLVE_INFO = "android.app.extra.RESOLVE_INFO";
    public static final String EXTRA_SEQUENCE = "android.app.extra.SEQUENCE";
    private static final String TAG = "PackageManager";
    Handler mHandler;

    static 
    {
        DEBUG_EPHEMERAL = Build.IS_DEBUGGABLE;
    }
}
