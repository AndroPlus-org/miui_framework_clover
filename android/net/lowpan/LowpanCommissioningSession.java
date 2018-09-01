// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;

import android.net.IpPrefix;
import android.os.*;

// Referenced classes of package android.net.lowpan:
//            ILowpanInterface, LowpanBeaconInfo, ILowpanInterfaceListener, LowpanIdentity

public class LowpanCommissioningSession
{
    public static abstract class Callback
    {

        public void onClosed()
        {
        }

        public void onReceiveFromCommissioner(byte abyte0[])
        {
        }

        public Callback()
        {
        }
    }

    private class InternalCallback extends ILowpanInterfaceListener.Stub
    {

        void lambda$_2D_android_net_lowpan_LowpanCommissioningSession$InternalCallback_2366(byte abyte0[])
        {
            LowpanCommissioningSession lowpancommissioningsession = LowpanCommissioningSession.this;
            lowpancommissioningsession;
            JVM INSTR monitorenter ;
            if(!LowpanCommissioningSession._2D_get2(LowpanCommissioningSession.this) && LowpanCommissioningSession._2D_get0(LowpanCommissioningSession.this) != null)
                LowpanCommissioningSession._2D_get0(LowpanCommissioningSession.this).onReceiveFromCommissioner(abyte0);
            lowpancommissioningsession;
            JVM INSTR monitorexit ;
            return;
            abyte0;
            throw abyte0;
        }

        public void onConnectedChanged(boolean flag)
        {
        }

        public void onEnabledChanged(boolean flag)
        {
        }

        public void onLinkAddressAdded(String s)
        {
        }

        public void onLinkAddressRemoved(String s)
        {
        }

        public void onLinkNetworkAdded(IpPrefix ipprefix)
        {
        }

        public void onLinkNetworkRemoved(IpPrefix ipprefix)
        {
        }

        public void onLowpanIdentityChanged(LowpanIdentity lowpanidentity)
        {
        }

        public void onReceiveFromCommissioner(byte abyte0[])
        {
            LowpanCommissioningSession._2D_get1(LowpanCommissioningSession.this).post(new _.Lambda.lq0tFj928XFoCdCDLCq_E_OIg9U((byte)0, this, abyte0));
        }

        public void onRoleChanged(String s)
        {
        }

        public void onStateChanged(String s)
        {
_L1:
            s = LowpanCommissioningSession.this;
            s;
            JVM INSTR monitorenter ;
            LowpanCommissioningSession._2D_wrap0(LowpanCommissioningSession.this);
            s;
            JVM INSTR monitorexit ;
_L2:
            return;
            if(LowpanCommissioningSession._2D_get2(LowpanCommissioningSession.this) || !s.equals("offline") && !s.equals("fault")) goto _L2; else goto _L1
            Exception exception;
            exception;
            throw exception;
        }

        public void onUpChanged(boolean flag)
        {
        }

        final LowpanCommissioningSession this$0;

        private InternalCallback()
        {
            this$0 = LowpanCommissioningSession.this;
            super();
        }

        InternalCallback(InternalCallback internalcallback)
        {
            this();
        }
    }


    static Callback _2D_get0(LowpanCommissioningSession lowpancommissioningsession)
    {
        return lowpancommissioningsession.mCallback;
    }

    static Handler _2D_get1(LowpanCommissioningSession lowpancommissioningsession)
    {
        return lowpancommissioningsession.mHandler;
    }

    static boolean _2D_get2(LowpanCommissioningSession lowpancommissioningsession)
    {
        return lowpancommissioningsession.mIsClosed;
    }

    static void _2D_wrap0(LowpanCommissioningSession lowpancommissioningsession)
    {
        lowpancommissioningsession.lockedCleanup();
    }

    LowpanCommissioningSession(ILowpanInterface ilowpaninterface, LowpanBeaconInfo lowpanbeaconinfo, Looper looper)
    {
        mCallback = null;
        mIsClosed = false;
        mBinder = ilowpaninterface;
        mBeaconInfo = lowpanbeaconinfo;
        mLooper = looper;
        if(mLooper != null)
            mHandler = new Handler(mLooper);
        else
            mHandler = new Handler();
        try
        {
            mBinder.addListener(mInternalCallback);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ILowpanInterface ilowpaninterface)
        {
            throw ilowpaninterface.rethrowAsRuntimeException();
        }
    }

    private void lockedCleanup()
    {
        if(!mIsClosed)
        {
            try
            {
                mBinder.removeListener(mInternalCallback);
            }
            catch(DeadObjectException deadobjectexception) { }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowAsRuntimeException();
            }
            if(mCallback != null)
                mHandler.post(new _.Lambda.QeWpJp8A7h1GVWRfnDNEd25gCZ8((byte)0, this));
        }
        mCallback = null;
        mIsClosed = true;
    }

    public void close()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mIsClosed;
        if(flag)
            break MISSING_BLOCK_LABEL_24;
        mBinder.closeCommissioningSession();
        lockedCleanup();
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        Object obj;
        obj;
        throw ((RemoteException) (obj)).rethrowAsRuntimeException();
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public LowpanBeaconInfo getBeaconInfo()
    {
        return mBeaconInfo;
    }

    void lambda$_2D_android_net_lowpan_LowpanCommissioningSession_4529()
    {
        mCallback.onClosed();
    }

    public void sendToCommissioner(byte abyte0[])
    {
        if(mIsClosed)
            break MISSING_BLOCK_LABEL_17;
        mBinder.sendToCommissioner(abyte0);
_L2:
        return;
        abyte0;
        throw abyte0.rethrowAsRuntimeException();
        abyte0;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setCallback(Callback callback, Handler handler)
    {
        this;
        JVM INSTR monitorenter ;
        if(mIsClosed) goto _L2; else goto _L1
_L1:
        if(handler == null) goto _L4; else goto _L3
_L3:
        mHandler = handler;
_L5:
        mCallback = callback;
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
_L4:
        if(mLooper == null)
            break MISSING_BLOCK_LABEL_58;
        handler = JVM INSTR new #62  <Class Handler>;
        handler.Handler(mLooper);
        mHandler = handler;
          goto _L5
        callback;
        throw callback;
        handler = JVM INSTR new #62  <Class Handler>;
        handler.Handler();
        mHandler = handler;
          goto _L5
    }

    private final LowpanBeaconInfo mBeaconInfo;
    private final ILowpanInterface mBinder;
    private Callback mCallback;
    private Handler mHandler;
    private final ILowpanInterfaceListener mInternalCallback = new InternalCallback(null);
    private volatile boolean mIsClosed;
    private final Looper mLooper;
}
