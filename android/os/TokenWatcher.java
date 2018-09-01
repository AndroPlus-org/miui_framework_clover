// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.Log;
import java.io.PrintWriter;
import java.util.*;

// Referenced classes of package android.os:
//            IBinder, Handler, RemoteException

public abstract class TokenWatcher
{
    private class Death
        implements IBinder.DeathRecipient
    {

        public void binderDied()
        {
            cleanup(token, false);
        }

        protected void finalize()
            throws Throwable
        {
            if(token != null)
            {
                String s = TokenWatcher._2D_get1(TokenWatcher.this);
                StringBuilder stringbuilder = JVM INSTR new #41  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.w(s, stringbuilder.append("cleaning up leaked reference: ").append(tag).toString());
                release(token);
            }
            super.finalize();
            return;
            Exception exception;
            exception;
            super.finalize();
            throw exception;
        }

        String tag;
        final TokenWatcher this$0;
        IBinder token;

        Death(IBinder ibinder, String s)
        {
            this$0 = TokenWatcher.this;
            super();
            token = ibinder;
            tag = s;
        }
    }


    static int _2D_get0(TokenWatcher tokenwatcher)
    {
        return tokenwatcher.mNotificationQueue;
    }

    static String _2D_get1(TokenWatcher tokenwatcher)
    {
        return tokenwatcher.mTag;
    }

    static WeakHashMap _2D_get2(TokenWatcher tokenwatcher)
    {
        return tokenwatcher.mTokens;
    }

    static int _2D_set0(TokenWatcher tokenwatcher, int i)
    {
        tokenwatcher.mNotificationQueue = i;
        return i;
    }

    public TokenWatcher(Handler handler, String s)
    {
        mNotificationTask = new Runnable() {

            public void run()
            {
                WeakHashMap weakhashmap = TokenWatcher._2D_get2(TokenWatcher.this);
                weakhashmap;
                JVM INSTR monitorenter ;
                int i;
                i = TokenWatcher._2D_get0(TokenWatcher.this);
                TokenWatcher._2D_set0(TokenWatcher.this, -1);
                weakhashmap;
                JVM INSTR monitorexit ;
                if(i != 1) goto _L2; else goto _L1
_L1:
                acquired();
_L4:
                return;
                Exception exception;
                exception;
                throw exception;
_L2:
                if(i == 0)
                    released();
                if(true) goto _L4; else goto _L3
_L3:
            }

            final TokenWatcher this$0;

            
            {
                this$0 = TokenWatcher.this;
                super();
            }
        }
;
        mTokens = new WeakHashMap();
        mNotificationQueue = -1;
        mAcquired = false;
        mHandler = handler;
        if(s == null)
            s = "TokenWatcher";
        mTag = s;
    }

    private ArrayList dumpInternal()
    {
        ArrayList arraylist = new ArrayList();
        WeakHashMap weakhashmap = mTokens;
        weakhashmap;
        JVM INSTR monitorenter ;
        Object obj;
        obj = mTokens.keySet();
        StringBuilder stringbuilder = JVM INSTR new #67  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        arraylist.add(stringbuilder.append("Token count: ").append(mTokens.size()).toString());
        int i = 0;
        obj = ((Iterable) (obj)).iterator();
_L1:
        if(!((Iterator) (obj)).hasNext())
            break MISSING_BLOCK_LABEL_157;
        IBinder ibinder = (IBinder)((Iterator) (obj)).next();
        StringBuilder stringbuilder1 = JVM INSTR new #67  <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        arraylist.add(stringbuilder1.append("[").append(i).append("] ").append(((Death)mTokens.get(ibinder)).tag).append(" - ").append(ibinder).toString());
        i++;
          goto _L1
        weakhashmap;
        JVM INSTR monitorexit ;
        return arraylist;
        Exception exception;
        exception;
        throw exception;
    }

    private void sendNotificationLocked(boolean flag)
    {
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        if(mNotificationQueue != -1) goto _L2; else goto _L1
_L1:
        mNotificationQueue = i;
        mHandler.post(mNotificationTask);
_L4:
        return;
_L2:
        if(mNotificationQueue != i)
        {
            mNotificationQueue = -1;
            mHandler.removeCallbacks(mNotificationTask);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void acquire(IBinder ibinder, String s)
    {
        WeakHashMap weakhashmap = mTokens;
        weakhashmap;
        JVM INSTR monitorenter ;
        int i;
        Death death;
        i = mTokens.size();
        death = JVM INSTR new #8   <Class TokenWatcher$Death>;
        death.this. Death(ibinder, s);
        try
        {
            ibinder.linkToDeath(death, 0);
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            return;
        }
        mTokens.put(ibinder, death);
        if(i != 0)
            break MISSING_BLOCK_LABEL_73;
        if(mAcquired ^ true)
        {
            sendNotificationLocked(true);
            mAcquired = true;
        }
        weakhashmap;
        JVM INSTR monitorexit ;
        return;
        ibinder;
        throw ibinder;
    }

    public abstract void acquired();

    public void cleanup(IBinder ibinder, boolean flag)
    {
        WeakHashMap weakhashmap = mTokens;
        weakhashmap;
        JVM INSTR monitorenter ;
        ibinder = (Death)mTokens.remove(ibinder);
        if(!flag || ibinder == null)
            break MISSING_BLOCK_LABEL_44;
        ((Death) (ibinder)).token.unlinkToDeath(ibinder, 0);
        ibinder.token = null;
        if(mTokens.size() == 0 && mAcquired)
        {
            sendNotificationLocked(false);
            mAcquired = false;
        }
        weakhashmap;
        JVM INSTR monitorexit ;
        return;
        ibinder;
        throw ibinder;
    }

    public void dump()
    {
        String s;
        for(Iterator iterator = dumpInternal().iterator(); iterator.hasNext(); Log.i(mTag, s))
            s = (String)iterator.next();

    }

    public void dump(PrintWriter printwriter)
    {
        for(Iterator iterator = dumpInternal().iterator(); iterator.hasNext(); printwriter.println((String)iterator.next()));
    }

    public boolean isAcquired()
    {
        WeakHashMap weakhashmap = mTokens;
        weakhashmap;
        JVM INSTR monitorenter ;
        boolean flag = mAcquired;
        weakhashmap;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public void release(IBinder ibinder)
    {
        cleanup(ibinder, true);
    }

    public abstract void released();

    private volatile boolean mAcquired;
    private Handler mHandler;
    private int mNotificationQueue;
    private Runnable mNotificationTask;
    private String mTag;
    private WeakHashMap mTokens;
}
