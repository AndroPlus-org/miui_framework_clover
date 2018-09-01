// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.app.*;
import android.os.*;
import android.util.Log;

// Referenced classes of package android.content:
//            Intent, Context

public abstract class BroadcastReceiver
{
    public static class PendingResult
    {

        public final void abortBroadcast()
        {
            checkSynchronousHint();
            mAbortBroadcast = true;
        }

        void checkSynchronousHint()
        {
            if(mOrderedHint || mInitialStickyHint)
            {
                return;
            } else
            {
                RuntimeException runtimeexception = new RuntimeException("BroadcastReceiver trying to return result during a non-ordered broadcast");
                runtimeexception.fillInStackTrace();
                Log.e("BroadcastReceiver", runtimeexception.getMessage(), runtimeexception);
                return;
            }
        }

        public final void clearAbortBroadcast()
        {
            mAbortBroadcast = false;
        }

        public final void finish()
        {
            if(mType != 0) goto _L2; else goto _L1
_L1:
            IActivityManager iactivitymanager = ActivityManager.getService();
            if(QueuedWork.hasPendingWork())
                QueuedWork.queue(iactivitymanager. new Runnable() {

                    public void run()
                    {
                        sendFinished(mgr);
                    }

                    final PendingResult this$1;
                    final IActivityManager val$mgr;

            
            {
                this$1 = final_pendingresult;
                mgr = IActivityManager.this;
                super();
            }
                }
, false);
            else
                sendFinished(iactivitymanager);
_L4:
            return;
_L2:
            if(mOrderedHint && mType != 2)
                sendFinished(ActivityManager.getService());
            if(true) goto _L4; else goto _L3
_L3:
        }

        public final boolean getAbortBroadcast()
        {
            return mAbortBroadcast;
        }

        public final int getResultCode()
        {
            return mResultCode;
        }

        public final String getResultData()
        {
            return mResultData;
        }

        public final Bundle getResultExtras(boolean flag)
        {
            Bundle bundle = mResultExtras;
            if(!flag)
                return bundle;
            Bundle bundle1 = bundle;
            if(bundle == null)
            {
                bundle1 = new Bundle();
                mResultExtras = bundle1;
            }
            return bundle1;
        }

        public int getSendingUserId()
        {
            return mSendingUser;
        }

        public void sendFinished(IActivityManager iactivitymanager)
        {
            this;
            JVM INSTR monitorenter ;
            if(mFinished)
            {
                iactivitymanager = JVM INSTR new #125 <Class IllegalStateException>;
                iactivitymanager.IllegalStateException("Broadcast already finished");
                throw iactivitymanager;
            }
            break MISSING_BLOCK_LABEL_26;
            iactivitymanager;
            this;
            JVM INSTR monitorexit ;
            throw iactivitymanager;
            mFinished = true;
            if(mResultExtras != null)
                mResultExtras.setAllowFds(false);
            if(!mOrderedHint)
                break MISSING_BLOCK_LABEL_87;
            iactivitymanager.finishReceiver(mToken, mResultCode, mResultData, mResultExtras, mAbortBroadcast, mFlags);
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
            try
            {
                iactivitymanager.finishReceiver(mToken, 0, null, null, false, mFlags);
            }
            // Misplaced declaration of an exception variable
            catch(IActivityManager iactivitymanager) { }
              goto _L1
        }

        public void setExtrasClassLoader(ClassLoader classloader)
        {
            if(mResultExtras != null)
                mResultExtras.setClassLoader(classloader);
        }

        public final void setResult(int i, String s, Bundle bundle)
        {
            checkSynchronousHint();
            mResultCode = i;
            mResultData = s;
            mResultExtras = bundle;
        }

        public final void setResultCode(int i)
        {
            checkSynchronousHint();
            mResultCode = i;
        }

        public final void setResultData(String s)
        {
            checkSynchronousHint();
            mResultData = s;
        }

        public final void setResultExtras(Bundle bundle)
        {
            checkSynchronousHint();
            mResultExtras = bundle;
        }

        public static final int TYPE_COMPONENT = 0;
        public static final int TYPE_REGISTERED = 1;
        public static final int TYPE_UNREGISTERED = 2;
        boolean mAbortBroadcast;
        boolean mFinished;
        final int mFlags;
        final boolean mInitialStickyHint;
        final boolean mOrderedHint;
        int mResultCode;
        String mResultData;
        Bundle mResultExtras;
        final int mSendingUser;
        final IBinder mToken;
        final int mType;

        public PendingResult(int i, String s, Bundle bundle, int j, boolean flag, boolean flag1, IBinder ibinder, 
                int k, int l)
        {
            mResultCode = i;
            mResultData = s;
            mResultExtras = bundle;
            mType = j;
            mOrderedHint = flag;
            mInitialStickyHint = flag1;
            mToken = ibinder;
            mSendingUser = k;
            mFlags = l;
        }
    }


    public BroadcastReceiver()
    {
    }

    public final void abortBroadcast()
    {
        checkSynchronousHint();
        mPendingResult.mAbortBroadcast = true;
    }

    void checkSynchronousHint()
    {
        if(mPendingResult == null)
            throw new IllegalStateException("Call while result is not pending");
        if(mPendingResult.mOrderedHint || mPendingResult.mInitialStickyHint)
        {
            return;
        } else
        {
            RuntimeException runtimeexception = new RuntimeException("BroadcastReceiver trying to return result during a non-ordered broadcast");
            runtimeexception.fillInStackTrace();
            Log.e("BroadcastReceiver", runtimeexception.getMessage(), runtimeexception);
            return;
        }
    }

    public final void clearAbortBroadcast()
    {
        if(mPendingResult != null)
            mPendingResult.mAbortBroadcast = false;
    }

    public final boolean getAbortBroadcast()
    {
        boolean flag;
        if(mPendingResult != null)
            flag = mPendingResult.mAbortBroadcast;
        else
            flag = false;
        return flag;
    }

    public final boolean getDebugUnregister()
    {
        return mDebugUnregister;
    }

    public final PendingResult getPendingResult()
    {
        return mPendingResult;
    }

    public final int getResultCode()
    {
        int i;
        if(mPendingResult != null)
            i = mPendingResult.mResultCode;
        else
            i = 0;
        return i;
    }

    public final String getResultData()
    {
        String s = null;
        if(mPendingResult != null)
            s = mPendingResult.mResultData;
        return s;
    }

    public final Bundle getResultExtras(boolean flag)
    {
        if(mPendingResult == null)
            return null;
        Bundle bundle = mPendingResult.mResultExtras;
        if(!flag)
            return bundle;
        Bundle bundle1 = bundle;
        if(bundle == null)
        {
            PendingResult pendingresult = mPendingResult;
            bundle1 = new Bundle();
            pendingresult.mResultExtras = bundle1;
        }
        return bundle1;
    }

    public int getSendingUserId()
    {
        return mPendingResult.mSendingUser;
    }

    public final PendingResult goAsync()
    {
        PendingResult pendingresult = mPendingResult;
        mPendingResult = null;
        return pendingresult;
    }

    public final boolean isInitialStickyBroadcast()
    {
        boolean flag;
        if(mPendingResult != null)
            flag = mPendingResult.mInitialStickyHint;
        else
            flag = false;
        return flag;
    }

    public final boolean isOrderedBroadcast()
    {
        boolean flag;
        if(mPendingResult != null)
            flag = mPendingResult.mOrderedHint;
        else
            flag = false;
        return flag;
    }

    public abstract void onReceive(Context context, Intent intent);

    public IBinder peekService(Context context, Intent intent)
    {
        IActivityManager iactivitymanager = ActivityManager.getService();
        Object obj = null;
        try
        {
            intent.prepareToLeaveProcess(context);
            context = iactivitymanager.peekService(intent, intent.resolveTypeIfNeeded(context.getContentResolver()), context.getOpPackageName());
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            context = obj;
        }
        return context;
    }

    public final void setDebugUnregister(boolean flag)
    {
        mDebugUnregister = flag;
    }

    public final void setOrderedHint(boolean flag)
    {
    }

    public final void setPendingResult(PendingResult pendingresult)
    {
        mPendingResult = pendingresult;
    }

    public final void setResult(int i, String s, Bundle bundle)
    {
        checkSynchronousHint();
        mPendingResult.mResultCode = i;
        mPendingResult.mResultData = s;
        mPendingResult.mResultExtras = bundle;
    }

    public final void setResultCode(int i)
    {
        checkSynchronousHint();
        mPendingResult.mResultCode = i;
    }

    public final void setResultData(String s)
    {
        checkSynchronousHint();
        mPendingResult.mResultData = s;
    }

    public final void setResultExtras(Bundle bundle)
    {
        checkSynchronousHint();
        mPendingResult.mResultExtras = bundle;
    }

    private boolean mDebugUnregister;
    private PendingResult mPendingResult;
}
