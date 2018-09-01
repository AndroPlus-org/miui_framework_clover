// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.trust;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.*;
import android.util.Log;
import android.util.Slog;
import java.util.List;

// Referenced classes of package android.service.trust:
//            ITrustAgentServiceCallback

public class TrustAgentService extends Service
{
    private static final class ConfigurationData
    {

        final List options;
        final IBinder token;

        ConfigurationData(List list, IBinder ibinder)
        {
            options = list;
            token = ibinder;
        }
    }

    private final class TrustAgentServiceWrapper extends ITrustAgentService.Stub
    {

        public void onConfigure(List list, IBinder ibinder)
        {
            TrustAgentService._2D_get1(TrustAgentService.this).obtainMessage(2, new ConfigurationData(list, ibinder)).sendToTarget();
        }

        public void onDeviceLocked()
            throws RemoteException
        {
            TrustAgentService._2D_get1(TrustAgentService.this).obtainMessage(4).sendToTarget();
        }

        public void onDeviceUnlocked()
            throws RemoteException
        {
            TrustAgentService._2D_get1(TrustAgentService.this).obtainMessage(5).sendToTarget();
        }

        public void onEscrowTokenAdded(byte abyte0[], long l, UserHandle userhandle)
        {
            Message message = TrustAgentService._2D_get1(TrustAgentService.this).obtainMessage(7);
            message.getData().putByteArray("token", abyte0);
            message.getData().putLong("token_handle", l);
            message.getData().putParcelable("user_handle", userhandle);
            message.sendToTarget();
        }

        public void onEscrowTokenRemoved(long l, boolean flag)
        {
            Message message = TrustAgentService._2D_get1(TrustAgentService.this).obtainMessage(9);
            message.getData().putLong("token_handle", l);
            message.getData().putBoolean("token_removed_result", flag);
            message.sendToTarget();
        }

        public void onTokenStateReceived(long l, int i)
        {
            Message message = TrustAgentService._2D_get1(TrustAgentService.this).obtainMessage(8);
            message.getData().putLong("token_handle", l);
            message.getData().putInt("token_state", i);
            message.sendToTarget();
        }

        public void onTrustTimeout()
        {
            TrustAgentService._2D_get1(TrustAgentService.this).sendEmptyMessage(3);
        }

        public void onUnlockAttempt(boolean flag)
        {
            Handler handler = TrustAgentService._2D_get1(TrustAgentService.this);
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            handler.obtainMessage(1, i, 0).sendToTarget();
        }

        public void onUnlockLockout(int i)
        {
            TrustAgentService._2D_get1(TrustAgentService.this).obtainMessage(6, i, 0).sendToTarget();
        }

        public void setCallback(ITrustAgentServiceCallback itrustagentservicecallback)
        {
            Object obj = TrustAgentService._2D_get2(TrustAgentService.this);
            obj;
            JVM INSTR monitorenter ;
            boolean flag;
            TrustAgentService._2D_set0(TrustAgentService.this, itrustagentservicecallback);
            flag = TrustAgentService._2D_get3(TrustAgentService.this);
            if(!flag)
                break MISSING_BLOCK_LABEL_50;
            TrustAgentService._2D_get0(TrustAgentService.this).setManagingTrust(TrustAgentService._2D_get3(TrustAgentService.this));
_L1:
            if(TrustAgentService._2D_get4(TrustAgentService.this) != null)
            {
                TrustAgentService._2D_get4(TrustAgentService.this).run();
                TrustAgentService._2D_set1(TrustAgentService.this, null);
            }
            obj;
            JVM INSTR monitorexit ;
            return;
            itrustagentservicecallback;
            TrustAgentService._2D_wrap0(TrustAgentService.this, "calling setManagingTrust()");
              goto _L1
            itrustagentservicecallback;
            throw itrustagentservicecallback;
        }

        final TrustAgentService this$0;

        private TrustAgentServiceWrapper()
        {
            this$0 = TrustAgentService.this;
            super();
        }

        TrustAgentServiceWrapper(TrustAgentServiceWrapper trustagentservicewrapper)
        {
            this();
        }
    }


    static ITrustAgentServiceCallback _2D_get0(TrustAgentService trustagentservice)
    {
        return trustagentservice.mCallback;
    }

    static Handler _2D_get1(TrustAgentService trustagentservice)
    {
        return trustagentservice.mHandler;
    }

    static Object _2D_get2(TrustAgentService trustagentservice)
    {
        return trustagentservice.mLock;
    }

    static boolean _2D_get3(TrustAgentService trustagentservice)
    {
        return trustagentservice.mManagingTrust;
    }

    static Runnable _2D_get4(TrustAgentService trustagentservice)
    {
        return trustagentservice.mPendingGrantTrustTask;
    }

    static ITrustAgentServiceCallback _2D_set0(TrustAgentService trustagentservice, ITrustAgentServiceCallback itrustagentservicecallback)
    {
        trustagentservice.mCallback = itrustagentservicecallback;
        return itrustagentservicecallback;
    }

    static Runnable _2D_set1(TrustAgentService trustagentservice, Runnable runnable)
    {
        trustagentservice.mPendingGrantTrustTask = runnable;
        return runnable;
    }

    static void _2D_wrap0(TrustAgentService trustagentservice, String s)
    {
        trustagentservice.onError(s);
    }

    public TrustAgentService()
    {
        mHandler = new Handler() {

            public void handleMessage(Message message)
            {
                boolean flag = false;
                message.what;
                JVM INSTR tableswitch 1 9: default 56
            //                           1 57
            //                           2 94
            //                           3 171
            //                           4 181
            //                           5 191
            //                           6 79
            //                           7 201
            //                           8 245
            //                           9 281;
                   goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
                return;
_L2:
                TrustAgentService trustagentservice = TrustAgentService.this;
                if(message.arg1 != 0)
                    flag = true;
                trustagentservice.onUnlockAttempt(flag);
                continue; /* Loop/switch isn't completed */
_L7:
                onDeviceUnlockLockout(message.arg1);
                continue; /* Loop/switch isn't completed */
_L3:
                ConfigurationData configurationdata;
                configurationdata = (ConfigurationData)message.obj;
                flag = onConfigure(configurationdata.options);
                if(configurationdata.token == null)
                    continue; /* Loop/switch isn't completed */
                message = ((Message) (TrustAgentService._2D_get2(TrustAgentService.this)));
                message;
                JVM INSTR monitorenter ;
                TrustAgentService._2D_get0(TrustAgentService.this).onConfigureCompleted(flag, configurationdata.token);
                message;
                JVM INSTR monitorexit ;
                continue; /* Loop/switch isn't completed */
                message;
                TrustAgentService._2D_wrap0(TrustAgentService.this, "calling onSetTrustAgentFeaturesEnabledCompleted()");
                continue; /* Loop/switch isn't completed */
                Exception exception;
                exception;
                message;
                JVM INSTR monitorexit ;
                throw exception;
_L4:
                onTrustTimeout();
                continue; /* Loop/switch isn't completed */
_L5:
                onDeviceLocked();
                continue; /* Loop/switch isn't completed */
_L6:
                onDeviceUnlocked();
                continue; /* Loop/switch isn't completed */
_L8:
                Object obj = message.getData();
                message = ((Bundle) (obj)).getByteArray("token");
                long l = ((Bundle) (obj)).getLong("token_handle");
                obj = (UserHandle)((Bundle) (obj)).getParcelable("user_handle");
                onEscrowTokenAdded(message, l, ((UserHandle) (obj)));
                continue; /* Loop/switch isn't completed */
_L9:
                message = message.getData();
                long l1 = message.getLong("token_handle");
                int i = message.getInt("token_state", 0);
                onEscrowTokenStateReceived(l1, i);
                continue; /* Loop/switch isn't completed */
_L10:
                message = message.getData();
                long l2 = message.getLong("token_handle");
                boolean flag1 = message.getBoolean("token_removed_result");
                onEscrowTokenRemoved(l2, flag1);
                if(true) goto _L1; else goto _L11
_L11:
            }

            final TrustAgentService this$0;

            
            {
                this$0 = TrustAgentService.this;
                super();
            }
        }
;
    }

    private void onError(String s)
    {
        Slog.v(TAG, (new StringBuilder()).append("Remote exception while ").append(s).toString());
    }

    public final void addEscrowToken(byte abyte0[], UserHandle userhandle)
    {
        synchronized(mLock)
        {
            if(mCallback == null)
            {
                Slog.w(TAG, "Cannot add escrow token if the agent is not connecting to framework");
                abyte0 = JVM INSTR new #158 <Class IllegalStateException>;
                abyte0.IllegalStateException("Trust agent is not connected");
                throw abyte0;
            }
            break MISSING_BLOCK_LABEL_41;
        }
        mCallback.addEscrowToken(abyte0, userhandle.getIdentifier());
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        abyte0;
        onError("calling addEscrowToken");
          goto _L1
    }

    public final void grantTrust(CharSequence charsequence, long l, int i)
    {
        synchronized(mLock)
        {
            if(!mManagingTrust)
            {
                charsequence = JVM INSTR new #158 <Class IllegalStateException>;
                charsequence.IllegalStateException("Cannot grant trust if agent is not managing trust. Call setManagingTrust(true) first.");
                throw charsequence;
            }
            break MISSING_BLOCK_LABEL_34;
        }
        ITrustAgentServiceCallback itrustagentservicecallback = mCallback;
        if(itrustagentservicecallback == null)
            break MISSING_BLOCK_LABEL_77;
        mCallback.grantTrust(charsequence.toString(), l, i);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        charsequence;
        onError("calling enableTrust()");
          goto _L1
        Runnable runnable = JVM INSTR new #8   <Class TrustAgentService$2>;
        runnable.this. _cls2();
        mPendingGrantTrustTask = runnable;
          goto _L1
    }

    public final void grantTrust(CharSequence charsequence, long l, boolean flag)
    {
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        grantTrust(charsequence, l, i);
    }

    public final void isEscrowTokenActive(long l, UserHandle userhandle)
    {
        synchronized(mLock)
        {
            if(mCallback == null)
            {
                Slog.w(TAG, "Cannot add escrow token if the agent is not connecting to framework");
                userhandle = JVM INSTR new #158 <Class IllegalStateException>;
                userhandle.IllegalStateException("Trust agent is not connected");
                throw userhandle;
            }
            break MISSING_BLOCK_LABEL_44;
        }
        mCallback.isEscrowTokenActive(l, userhandle.getIdentifier());
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        userhandle;
        onError("calling isEscrowTokenActive");
          goto _L1
    }

    public final IBinder onBind(Intent intent)
    {
        return new TrustAgentServiceWrapper(null);
    }

    public boolean onConfigure(List list)
    {
        return false;
    }

    public void onCreate()
    {
        super.onCreate();
        ComponentName componentname = new ComponentName(this, getClass());
        try
        {
            if(!"android.permission.BIND_TRUST_AGENT".equals(getPackageManager().getServiceInfo(componentname, 0).permission))
            {
                IllegalStateException illegalstateexception = JVM INSTR new #158 <Class IllegalStateException>;
                StringBuilder stringbuilder = JVM INSTR new #109 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                illegalstateexception.IllegalStateException(stringbuilder.append(componentname.flattenToShortString()).append(" is not declared with the permission ").append("\"").append("android.permission.BIND_TRUST_AGENT").append("\"").toString());
                throw illegalstateexception;
            }
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            Log.e(TAG, (new StringBuilder()).append("Can't get ServiceInfo for ").append(componentname.toShortString()).toString());
        }
    }

    public void onDeviceLocked()
    {
    }

    public void onDeviceUnlockLockout(long l)
    {
    }

    public void onDeviceUnlocked()
    {
    }

    public void onEscrowTokenAdded(byte abyte0[], long l, UserHandle userhandle)
    {
    }

    public void onEscrowTokenRemoved(long l, boolean flag)
    {
    }

    public void onEscrowTokenStateReceived(long l, int i)
    {
    }

    public void onTrustTimeout()
    {
    }

    public void onUnlockAttempt(boolean flag)
    {
    }

    public final void removeEscrowToken(long l, UserHandle userhandle)
    {
        synchronized(mLock)
        {
            if(mCallback == null)
            {
                Slog.w(TAG, "Cannot add escrow token if the agent is not connecting to framework");
                userhandle = JVM INSTR new #158 <Class IllegalStateException>;
                userhandle.IllegalStateException("Trust agent is not connected");
                throw userhandle;
            }
            break MISSING_BLOCK_LABEL_44;
        }
        mCallback.removeEscrowToken(l, userhandle.getIdentifier());
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        userhandle;
        onError("callling removeEscrowToken");
          goto _L1
    }

    public final void revokeTrust()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        ITrustAgentServiceCallback itrustagentservicecallback;
        if(mPendingGrantTrustTask != null)
            mPendingGrantTrustTask = null;
        itrustagentservicecallback = mCallback;
        if(itrustagentservicecallback == null)
            break MISSING_BLOCK_LABEL_37;
        mCallback.revokeTrust();
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        onError("calling revokeTrust()");
          goto _L1
        obj1;
        throw obj1;
    }

    public final void setManagingTrust(boolean flag)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        ITrustAgentServiceCallback itrustagentservicecallback;
        if(mManagingTrust == flag)
            break MISSING_BLOCK_LABEL_39;
        mManagingTrust = flag;
        itrustagentservicecallback = mCallback;
        if(itrustagentservicecallback == null)
            break MISSING_BLOCK_LABEL_39;
        mCallback.setManagingTrust(flag);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        obj1;
        onError("calling setManagingTrust()");
          goto _L1
        obj1;
        throw obj1;
    }

    public final void unlockUserWithToken(long l, byte abyte0[], UserHandle userhandle)
    {
label0:
        {
            if(((UserManager)getSystemService("user")).isUserUnlocked())
            {
                Slog.i(TAG, "User already unlocked");
                return;
            }
            synchronized(mLock)
            {
                if(mCallback == null)
                {
                    Slog.w(TAG, "Cannot add escrow token if the agent is not connecting to framework");
                    abyte0 = JVM INSTR new #158 <Class IllegalStateException>;
                    abyte0.IllegalStateException("Trust agent is not connected");
                    throw abyte0;
                }
                break label0;
            }
        }
        mCallback.unlockUserWithToken(l, abyte0, userhandle.getIdentifier());
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        abyte0;
        onError("calling unlockUserWithToken");
          goto _L1
    }

    private static final boolean DEBUG = false;
    private static final String EXTRA_TOKEN = "token";
    private static final String EXTRA_TOKEN_HANDLE = "token_handle";
    private static final String EXTRA_TOKEN_REMOVED_RESULT = "token_removed_result";
    private static final String EXTRA_TOKEN_STATE = "token_state";
    private static final String EXTRA_USER_HANDLE = "user_handle";
    public static final int FLAG_GRANT_TRUST_DISMISS_KEYGUARD = 2;
    public static final int FLAG_GRANT_TRUST_INITIATED_BY_USER = 1;
    private static final int MSG_CONFIGURE = 2;
    private static final int MSG_DEVICE_LOCKED = 4;
    private static final int MSG_DEVICE_UNLOCKED = 5;
    private static final int MSG_ESCROW_TOKEN_ADDED = 7;
    private static final int MSG_ESCROW_TOKEN_REMOVED = 9;
    private static final int MSG_ESCROW_TOKEN_STATE_RECEIVED = 8;
    private static final int MSG_TRUST_TIMEOUT = 3;
    private static final int MSG_UNLOCK_ATTEMPT = 1;
    private static final int MSG_UNLOCK_LOCKOUT = 6;
    public static final String SERVICE_INTERFACE = "android.service.trust.TrustAgentService";
    public static final int TOKEN_STATE_ACTIVE = 1;
    public static final int TOKEN_STATE_INACTIVE = 0;
    public static final String TRUST_AGENT_META_DATA = "android.service.trust.trustagent";
    private final String TAG = (new StringBuilder()).append(android/service/trust/TrustAgentService.getSimpleName()).append("[").append(getClass().getSimpleName()).append("]").toString();
    private ITrustAgentServiceCallback mCallback;
    private Handler mHandler;
    private final Object mLock = new Object();
    private boolean mManagingTrust;
    private Runnable mPendingGrantTrustTask;

    // Unreferenced inner class android/service/trust/TrustAgentService$2

/* anonymous class */
    class _cls2
        implements Runnable
    {

        public void run()
        {
            grantTrust(message, durationMs, flags);
        }

        final TrustAgentService this$0;
        final long val$durationMs;
        final int val$flags;
        final CharSequence val$message;

            
            {
                this$0 = TrustAgentService.this;
                message = charsequence;
                durationMs = l;
                flags = i;
                super();
            }
    }

}
