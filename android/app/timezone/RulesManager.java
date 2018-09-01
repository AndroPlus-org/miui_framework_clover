// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.timezone;

import android.content.Context;
import android.os.*;
import java.io.IOException;
import java.util.Arrays;

// Referenced classes of package android.app.timezone:
//            IRulesManager, RulesState, Callback

public final class RulesManager
{
    private class CallbackWrapper extends ICallback.Stub
    {

        void lambda$_2D_android_app_timezone_RulesManager$CallbackWrapper_7810(int i)
        {
            mCallback.onFinished(i);
        }

        public void onFinished(int i)
        {
            RulesManager.logDebug((new StringBuilder()).append("mCallback.onFinished(status), status=").append(i).toString());
            mHandler.post(new _.Lambda.XGnGFnwDfPWgxse09CN983EaD_Q(i, this));
        }

        final Callback mCallback;
        final Handler mHandler;
        final RulesManager this$0;

        CallbackWrapper(Context context, Callback callback)
        {
            this$0 = RulesManager.this;
            super();
            mCallback = callback;
            mHandler = new Handler(context.getMainLooper());
        }
    }


    public RulesManager(Context context)
    {
        mContext = context;
    }

    static void logDebug(String s)
    {
    }

    public RulesState getRulesState()
    {
        RulesState rulesstate;
        try
        {
            logDebug("sIRulesManager.getRulesState()");
            rulesstate = mIRulesManager.getRulesState();
            StringBuilder stringbuilder = JVM INSTR new #64  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            logDebug(stringbuilder.append("sIRulesManager.getRulesState() returned ").append(rulesstate).toString());
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return rulesstate;
    }

    public int requestInstall(ParcelFileDescriptor parcelfiledescriptor, byte abyte0[], Callback callback)
        throws IOException
    {
        callback = new CallbackWrapper(mContext, callback);
        int i;
        try
        {
            logDebug("sIRulesManager.requestInstall()");
            i = mIRulesManager.requestInstall(parcelfiledescriptor, abyte0, callback);
        }
        // Misplaced declaration of an exception variable
        catch(ParcelFileDescriptor parcelfiledescriptor)
        {
            throw parcelfiledescriptor.rethrowFromSystemServer();
        }
        return i;
    }

    public void requestNothing(byte abyte0[], boolean flag)
    {
        try
        {
            StringBuilder stringbuilder = JVM INSTR new #64  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            logDebug(stringbuilder.append("sIRulesManager.requestNothing() with token=").append(Arrays.toString(abyte0)).toString());
            mIRulesManager.requestNothing(abyte0, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw abyte0.rethrowFromSystemServer();
        }
    }

    public int requestUninstall(byte abyte0[], Callback callback)
    {
        callback = new CallbackWrapper(mContext, callback);
        int i;
        try
        {
            logDebug("sIRulesManager.requestUninstall()");
            i = mIRulesManager.requestUninstall(abyte0, callback);
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            throw abyte0.rethrowFromSystemServer();
        }
        return i;
    }

    private static final boolean DEBUG = false;
    public static final int ERROR_OPERATION_IN_PROGRESS = 1;
    public static final int ERROR_UNKNOWN_FAILURE = 2;
    public static final int SUCCESS = 0;
    private static final String TAG = "timezone.RulesManager";
    private final Context mContext;
    private final IRulesManager mIRulesManager = IRulesManager.Stub.asInterface(ServiceManager.getService("timezone"));
}
