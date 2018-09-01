// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.ArrayMap;
import android.util.Slog;
import java.util.function.Consumer;

// Referenced classes of package android.os:
//            Debug, IInterface, IBinder, RemoteException

public class RemoteCallbackList
{
    private final class Callback
        implements IBinder.DeathRecipient
    {

        public void binderDied()
        {
            ArrayMap arraymap = mCallbacks;
            arraymap;
            JVM INSTR monitorenter ;
            mCallbacks.remove(mCallback.asBinder());
            arraymap;
            JVM INSTR monitorexit ;
            onCallbackDied(mCallback, mCookie);
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final IInterface mCallback;
        final Object mCookie;
        final RemoteCallbackList this$0;

        Callback(IInterface iinterface, Object obj)
        {
            this$0 = RemoteCallbackList.this;
            super();
            mCallback = iinterface;
            mCookie = obj;
        }
    }


    public RemoteCallbackList()
    {
        mCallbacks = new ArrayMap();
        mBroadcastCount = -1;
        mKilled = false;
    }

    private void logExcessiveCallbacks()
    {
        long l = mCallbacks.size();
        if(l >= 3000L)
        {
            if(l == 3000L && mRecentCallers == null)
                mRecentCallers = new StringBuilder();
            if(mRecentCallers != null && (long)mRecentCallers.length() < 1000L)
            {
                mRecentCallers.append(Debug.getCallers(5));
                mRecentCallers.append('\n');
                if((long)mRecentCallers.length() >= 1000L)
                {
                    Slog.wtf("RemoteCallbackList", (new StringBuilder()).append("More than 3000 remote callbacks registered. Recent callers:\n").append(mRecentCallers.toString()).toString());
                    mRecentCallers = null;
                }
            }
        }
    }

    public int beginBroadcast()
    {
        synchronized(mCallbacks)
        {
            if(mBroadcastCount > 0)
            {
                IllegalStateException illegalstateexception = JVM INSTR new #82  <Class IllegalStateException>;
                illegalstateexception.IllegalStateException("beginBroadcast() called while already in a broadcast");
                throw illegalstateexception;
            }
            break MISSING_BLOCK_LABEL_31;
        }
        int i;
        i = mCallbacks.size();
        mBroadcastCount = i;
        if(i > 0)
            break MISSING_BLOCK_LABEL_52;
        arraymap;
        JVM INSTR monitorexit ;
        return 0;
        Object aobj1[] = mActiveBroadcast;
        Object aobj[];
        if(aobj1 == null)
            break MISSING_BLOCK_LABEL_73;
        aobj = aobj1;
        if(aobj1.length >= i)
            break MISSING_BLOCK_LABEL_83;
        aobj = new Object[i];
        mActiveBroadcast = aobj;
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        aobj[j] = mCallbacks.valueAt(j);
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        return i;
    }

    public void broadcast(Consumer consumer)
    {
        int i;
        int j;
        i = beginBroadcast();
        j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        consumer.accept(getBroadcastItem(j));
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        finishBroadcast();
        return;
        consumer;
        finishBroadcast();
        throw consumer;
    }

    public void finishBroadcast()
    {
        synchronized(mCallbacks)
        {
            if(mBroadcastCount < 0)
            {
                IllegalStateException illegalstateexception = JVM INSTR new #82  <Class IllegalStateException>;
                illegalstateexception.IllegalStateException("finishBroadcast() called outside of a broadcast");
                throw illegalstateexception;
            }
            break MISSING_BLOCK_LABEL_31;
        }
        Object aobj[] = mActiveBroadcast;
        if(aobj == null)
            break MISSING_BLOCK_LABEL_65;
        int i = mBroadcastCount;
        for(int j = 0; j < i; j++)
            aobj[j] = null;

        mBroadcastCount = -1;
        arraymap;
        JVM INSTR monitorexit ;
    }

    public Object getBroadcastCookie(int i)
    {
        return ((Callback)mActiveBroadcast[i]).mCookie;
    }

    public IInterface getBroadcastItem(int i)
    {
        return ((Callback)mActiveBroadcast[i]).mCallback;
    }

    public Object getRegisteredCallbackCookie(int i)
    {
        ArrayMap arraymap = mCallbacks;
        arraymap;
        JVM INSTR monitorenter ;
        boolean flag = mKilled;
        if(!flag)
            break MISSING_BLOCK_LABEL_20;
        arraymap;
        JVM INSTR monitorexit ;
        return null;
        Object obj = ((Callback)mCallbacks.valueAt(i)).mCookie;
        arraymap;
        JVM INSTR monitorexit ;
        return obj;
        Exception exception;
        exception;
        throw exception;
    }

    public int getRegisteredCallbackCount()
    {
        ArrayMap arraymap = mCallbacks;
        arraymap;
        JVM INSTR monitorenter ;
        boolean flag = mKilled;
        if(!flag)
            break MISSING_BLOCK_LABEL_20;
        arraymap;
        JVM INSTR monitorexit ;
        return 0;
        int i = mCallbacks.size();
        arraymap;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public IInterface getRegisteredCallbackItem(int i)
    {
        ArrayMap arraymap = mCallbacks;
        arraymap;
        JVM INSTR monitorenter ;
        boolean flag = mKilled;
        if(!flag)
            break MISSING_BLOCK_LABEL_20;
        arraymap;
        JVM INSTR monitorexit ;
        return null;
        IInterface iinterface = ((Callback)mCallbacks.valueAt(i)).mCallback;
        arraymap;
        JVM INSTR monitorexit ;
        return iinterface;
        Exception exception;
        exception;
        throw exception;
    }

    public void kill()
    {
        ArrayMap arraymap = mCallbacks;
        arraymap;
        JVM INSTR monitorenter ;
        int i = mCallbacks.size() - 1;
_L2:
        if(i < 0)
            break; /* Loop/switch isn't completed */
        Callback callback = (Callback)mCallbacks.valueAt(i);
        callback.mCallback.asBinder().unlinkToDeath(callback, 0);
        i--;
        if(true) goto _L2; else goto _L1
_L1:
        mCallbacks.clear();
        mKilled = true;
        arraymap;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void onCallbackDied(IInterface iinterface)
    {
    }

    public void onCallbackDied(IInterface iinterface, Object obj)
    {
        onCallbackDied(iinterface);
    }

    public boolean register(IInterface iinterface)
    {
        return register(iinterface, null);
    }

    public boolean register(IInterface iinterface, Object obj)
    {
        ArrayMap arraymap = mCallbacks;
        arraymap;
        JVM INSTR monitorenter ;
        boolean flag = mKilled;
        if(!flag)
            break MISSING_BLOCK_LABEL_22;
        arraymap;
        JVM INSTR monitorexit ;
        return false;
        IBinder ibinder;
        logExcessiveCallbacks();
        ibinder = iinterface.asBinder();
        try
        {
            Callback callback = JVM INSTR new #7   <Class RemoteCallbackList$Callback>;
            callback.this. Callback(iinterface, obj);
            ibinder.linkToDeath(callback, 0);
            mCallbacks.put(ibinder, callback);
        }
        // Misplaced declaration of an exception variable
        catch(IInterface iinterface)
        {
            return false;
        }
        arraymap;
        JVM INSTR monitorexit ;
        return true;
        iinterface;
        throw iinterface;
    }

    public boolean unregister(IInterface iinterface)
    {
        ArrayMap arraymap = mCallbacks;
        arraymap;
        JVM INSTR monitorenter ;
        iinterface = (Callback)mCallbacks.remove(iinterface.asBinder());
        if(iinterface == null)
            break MISSING_BLOCK_LABEL_49;
        ((Callback) (iinterface)).mCallback.asBinder().unlinkToDeath(iinterface, 0);
        arraymap;
        JVM INSTR monitorexit ;
        return true;
        arraymap;
        JVM INSTR monitorexit ;
        return false;
        iinterface;
        throw iinterface;
    }

    private static final String TAG = "RemoteCallbackList";
    private Object mActiveBroadcast[];
    private int mBroadcastCount;
    ArrayMap mCallbacks;
    private boolean mKilled;
    private StringBuilder mRecentCallers;
}
