// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.accounts.Account;
import android.os.*;
import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package android.content:
//            Context, ContentProviderClient, SyncResult, SyncContext, 
//            ISyncContext, ContentResolver

public abstract class AbstractThreadedSyncAdapter
{
    private class ISyncAdapterImpl extends ISyncAdapter.Stub
    {

        public void cancelSync(ISyncContext isynccontext)
        {
            Object obj = null;
            Object obj1 = AbstractThreadedSyncAdapter._2D_get5(AbstractThreadedSyncAdapter.this);
            obj1;
            JVM INSTR monitorenter ;
            Iterator iterator = AbstractThreadedSyncAdapter._2D_get6(AbstractThreadedSyncAdapter.this).values().iterator();
_L2:
            SyncThread syncthread = obj;
            IBinder ibinder;
            IBinder ibinder1;
            if(!iterator.hasNext())
                break; /* Loop/switch isn't completed */
            syncthread = (SyncThread)iterator.next();
            ibinder = SyncThread._2D_get2(syncthread).getSyncContextBinder();
            ibinder1 = isynccontext.asBinder();
            if(ibinder != ibinder1) goto _L2; else goto _L1
_L1:
            obj1;
            JVM INSTR monitorexit ;
            if(syncthread == null) goto _L4; else goto _L3
_L3:
            if(AbstractThreadedSyncAdapter._2D_get0())
            {
                isynccontext = JVM INSTR new #78  <Class StringBuilder>;
                isynccontext.StringBuilder();
                Log.d("SyncAdapter", isynccontext.append("cancelSync() ").append(SyncThread._2D_get1(syncthread)).append(" ").append(SyncThread._2D_get0(syncthread)).toString());
            }
            if(!AbstractThreadedSyncAdapter._2D_get1(AbstractThreadedSyncAdapter.this)) goto _L6; else goto _L5
_L5:
            onSyncCanceled(syncthread);
_L7:
            if(AbstractThreadedSyncAdapter._2D_get0())
                Log.d("SyncAdapter", "cancelSync() finishing");
            return;
            isynccontext;
            obj1;
            JVM INSTR monitorexit ;
            throw isynccontext;
            isynccontext;
            if(AbstractThreadedSyncAdapter._2D_get0())
                Log.d("SyncAdapter", "cancelSync() caught exception", isynccontext);
            throw isynccontext;
            isynccontext;
            if(AbstractThreadedSyncAdapter._2D_get0())
                Log.d("SyncAdapter", "cancelSync() finishing");
            throw isynccontext;
_L6:
            onSyncCanceled();
              goto _L7
_L4:
            if(!AbstractThreadedSyncAdapter._2D_get0()) goto _L7; else goto _L8
_L8:
            Log.w("SyncAdapter", "cancelSync() unknown context");
              goto _L7
        }

        public void startSync(ISyncContext isynccontext, String s, Account account, Bundle bundle)
        {
            if(AbstractThreadedSyncAdapter._2D_get0())
            {
                if(bundle != null)
                    bundle.size();
                Log.d("SyncAdapter", (new StringBuilder()).append("startSync() start ").append(s).append(" ").append(account).append(" ").append(bundle).toString());
            }
            SyncContext synccontext;
            Account account1;
            synccontext = JVM INSTR new #64  <Class SyncContext>;
            synccontext.SyncContext(isynccontext);
            account1 = AbstractThreadedSyncAdapter._2D_wrap0(AbstractThreadedSyncAdapter.this, account);
            isynccontext = ((ISyncContext) (AbstractThreadedSyncAdapter._2D_get5(AbstractThreadedSyncAdapter.this)));
            isynccontext;
            JVM INSTR monitorenter ;
            if(AbstractThreadedSyncAdapter._2D_get6(AbstractThreadedSyncAdapter.this).containsKey(account1)) goto _L2; else goto _L1
_L1:
            if(!AbstractThreadedSyncAdapter._2D_get2(AbstractThreadedSyncAdapter.this) || bundle == null)
                break MISSING_BLOCK_LABEL_239;
            boolean flag = bundle.getBoolean("initialize", false);
            if(!flag)
                break MISSING_BLOCK_LABEL_239;
            if(ContentResolver.getIsSyncable(account, s) < 0)
                ContentResolver.setIsSyncable(account, s, 1);
            s = JVM INSTR new #170 <Class SyncResult>;
            s.SyncResult();
            synccontext.onFinished(s);
            isynccontext;
            JVM INSTR monitorexit ;
            if(AbstractThreadedSyncAdapter._2D_get0())
                Log.d("SyncAdapter", "startSync() finishing");
            return;
            s;
            account = JVM INSTR new #170 <Class SyncResult>;
            account.SyncResult();
            synccontext.onFinished(account);
            throw s;
            s;
            isynccontext;
            JVM INSTR monitorexit ;
            throw s;
            isynccontext;
            if(AbstractThreadedSyncAdapter._2D_get0())
                Log.d("SyncAdapter", "startSync() caught exception", isynccontext);
            throw isynccontext;
            isynccontext;
            if(AbstractThreadedSyncAdapter._2D_get0())
                Log.d("SyncAdapter", "startSync() finishing");
            throw isynccontext;
            SyncThread syncthread = JVM INSTR new #58  <Class AbstractThreadedSyncAdapter$SyncThread>;
            AbstractThreadedSyncAdapter abstractthreadedsyncadapter = AbstractThreadedSyncAdapter.this;
            StringBuilder stringbuilder = JVM INSTR new #78  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            syncthread.abstractthreadedsyncadapter. SyncThread(stringbuilder.append("SyncAdapterThread-").append(AbstractThreadedSyncAdapter._2D_get4(AbstractThreadedSyncAdapter.this).incrementAndGet()).toString(), synccontext, s, account, bundle, null);
            AbstractThreadedSyncAdapter._2D_get6(AbstractThreadedSyncAdapter.this).put(account1, syncthread);
            syncthread.start();
            boolean flag1 = false;
_L4:
            isynccontext;
            JVM INSTR monitorexit ;
            if(!flag1)
                break MISSING_BLOCK_LABEL_335;
            synccontext.onFinished(SyncResult.ALREADY_IN_PROGRESS);
            if(AbstractThreadedSyncAdapter._2D_get0())
                Log.d("SyncAdapter", "startSync() finishing");
            return;
_L2:
            if(AbstractThreadedSyncAdapter._2D_get0())
                Log.d("SyncAdapter", "  alreadyInProgress");
            flag1 = true;
            if(true) goto _L4; else goto _L3
_L3:
        }

        final AbstractThreadedSyncAdapter this$0;

        private ISyncAdapterImpl()
        {
            this$0 = AbstractThreadedSyncAdapter.this;
            super();
        }

        ISyncAdapterImpl(ISyncAdapterImpl isyncadapterimpl)
        {
            this();
        }
    }

    private class SyncThread extends Thread
    {

        static Account _2D_get0(SyncThread syncthread)
        {
            return syncthread.mAccount;
        }

        static String _2D_get1(SyncThread syncthread)
        {
            return syncthread.mAuthority;
        }

        static SyncContext _2D_get2(SyncThread syncthread)
        {
            return syncthread.mSyncContext;
        }

        private boolean isCanceled()
        {
            return Thread.currentThread().isInterrupted();
        }

        public void run()
        {
            SyncResult syncresult;
            Object obj;
            Object obj1;
            ContentProviderClient contentproviderclient;
            ContentProviderClient contentproviderclient1;
            Object obj3;
            Object obj4;
            Process.setThreadPriority(10);
            if(AbstractThreadedSyncAdapter._2D_get0())
                Log.d("SyncAdapter", "Thread started");
            Trace.traceBegin(128L, mAuthority);
            syncresult = new SyncResult();
            obj = null;
            obj1 = null;
            contentproviderclient = null;
            contentproviderclient1 = contentproviderclient;
            obj3 = obj;
            obj4 = obj1;
            if(!isCanceled())
                break MISSING_BLOCK_LABEL_169;
            contentproviderclient1 = contentproviderclient;
            obj3 = obj;
            obj4 = obj1;
            if(!AbstractThreadedSyncAdapter._2D_get0())
                break MISSING_BLOCK_LABEL_95;
            contentproviderclient1 = contentproviderclient;
            obj3 = obj;
            obj4 = obj1;
            Log.d("SyncAdapter", "Already canceled");
            Trace.traceEnd(128L);
            if(!isCanceled())
                mSyncContext.onFinished(syncresult);
            obj3 = AbstractThreadedSyncAdapter._2D_get5(AbstractThreadedSyncAdapter.this);
            obj3;
            JVM INSTR monitorenter ;
            AbstractThreadedSyncAdapter._2D_get6(AbstractThreadedSyncAdapter.this).remove(mThreadsKey);
            obj3;
            JVM INSTR monitorexit ;
            if(AbstractThreadedSyncAdapter._2D_get0())
                Log.d("SyncAdapter", "Thread finished");
            return;
            obj4;
            throw obj4;
            contentproviderclient1 = contentproviderclient;
            obj3 = obj;
            obj4 = obj1;
            if(!AbstractThreadedSyncAdapter._2D_get0())
                break MISSING_BLOCK_LABEL_203;
            contentproviderclient1 = contentproviderclient;
            obj3 = obj;
            obj4 = obj1;
            Log.d("SyncAdapter", "Calling onPerformSync...");
            contentproviderclient1 = contentproviderclient;
            obj3 = obj;
            obj4 = obj1;
            contentproviderclient = AbstractThreadedSyncAdapter._2D_get3(AbstractThreadedSyncAdapter.this).getContentResolver().acquireContentProviderClient(mAuthority);
            if(contentproviderclient == null) goto _L2; else goto _L1
_L1:
            contentproviderclient1 = contentproviderclient;
            obj3 = contentproviderclient;
            obj4 = contentproviderclient;
            onPerformSync(mAccount, mExtras, mAuthority, contentproviderclient, syncresult);
_L6:
            contentproviderclient1 = contentproviderclient;
            obj3 = contentproviderclient;
            obj4 = contentproviderclient;
            if(!AbstractThreadedSyncAdapter._2D_get0())
                break MISSING_BLOCK_LABEL_309;
            contentproviderclient1 = contentproviderclient;
            obj3 = contentproviderclient;
            obj4 = contentproviderclient;
            Log.d("SyncAdapter", "onPerformSync done");
            Trace.traceEnd(128L);
            if(contentproviderclient != null)
                contentproviderclient.release();
            if(!isCanceled())
                mSyncContext.onFinished(syncresult);
            obj4 = AbstractThreadedSyncAdapter._2D_get5(AbstractThreadedSyncAdapter.this);
            obj4;
            JVM INSTR monitorenter ;
            AbstractThreadedSyncAdapter._2D_get6(AbstractThreadedSyncAdapter.this).remove(mThreadsKey);
            obj4;
            JVM INSTR monitorexit ;
            if(AbstractThreadedSyncAdapter._2D_get0())
                Log.d("SyncAdapter", "Thread finished");
_L4:
            return;
_L2:
            contentproviderclient1 = contentproviderclient;
            obj3 = contentproviderclient;
            obj4 = contentproviderclient;
            syncresult.databaseError = true;
            continue; /* Loop/switch isn't completed */
            obj3;
            obj4 = contentproviderclient1;
            if(!AbstractThreadedSyncAdapter._2D_get0())
                break MISSING_BLOCK_LABEL_432;
            obj4 = contentproviderclient1;
            Log.d("SyncAdapter", "SecurityException", ((Throwable) (obj3)));
            obj4 = contentproviderclient1;
            onSecurityException(mAccount, mExtras, mAuthority, syncresult);
            obj4 = contentproviderclient1;
            syncresult.databaseError = true;
            Trace.traceEnd(128L);
            if(contentproviderclient1 != null)
                contentproviderclient1.release();
            if(!isCanceled())
                mSyncContext.onFinished(syncresult);
            obj4 = AbstractThreadedSyncAdapter._2D_get5(AbstractThreadedSyncAdapter.this);
            obj4;
            JVM INSTR monitorenter ;
            AbstractThreadedSyncAdapter._2D_get6(AbstractThreadedSyncAdapter.this).remove(mThreadsKey);
            obj4;
            JVM INSTR monitorexit ;
            if(AbstractThreadedSyncAdapter._2D_get0())
                Log.d("SyncAdapter", "Thread finished");
            if(true) goto _L4; else goto _L3
_L3:
            obj3;
            throw obj3;
            Object obj2;
            obj2;
            obj4 = obj3;
            if(!AbstractThreadedSyncAdapter._2D_get0())
                break MISSING_BLOCK_LABEL_578;
            obj4 = obj3;
            Log.d("SyncAdapter", "caught exception", ((Throwable) (obj2)));
            obj4 = obj3;
            throw obj2;
            Exception exception;
            exception;
            Trace.traceEnd(128L);
            if(obj4 != null)
                ((ContentProviderClient) (obj4)).release();
            if(!isCanceled())
                mSyncContext.onFinished(syncresult);
            obj4 = AbstractThreadedSyncAdapter._2D_get5(AbstractThreadedSyncAdapter.this);
            obj4;
            JVM INSTR monitorenter ;
            AbstractThreadedSyncAdapter._2D_get6(AbstractThreadedSyncAdapter.this).remove(mThreadsKey);
            obj4;
            JVM INSTR monitorexit ;
            if(AbstractThreadedSyncAdapter._2D_get0())
                Log.d("SyncAdapter", "Thread finished");
            throw exception;
            exception;
            throw exception;
            exception;
            throw exception;
            if(true) goto _L6; else goto _L5
_L5:
        }

        private final Account mAccount;
        private final String mAuthority;
        private final Bundle mExtras;
        private final SyncContext mSyncContext;
        private final Account mThreadsKey;
        final AbstractThreadedSyncAdapter this$0;

        private SyncThread(String s, SyncContext synccontext, String s1, Account account, Bundle bundle)
        {
            this$0 = AbstractThreadedSyncAdapter.this;
            super(s);
            mSyncContext = synccontext;
            mAuthority = s1;
            mAccount = account;
            mExtras = bundle;
            mThreadsKey = AbstractThreadedSyncAdapter._2D_wrap0(AbstractThreadedSyncAdapter.this, account);
        }

        SyncThread(String s, SyncContext synccontext, String s1, Account account, Bundle bundle, SyncThread syncthread)
        {
            this(s, synccontext, s1, account, bundle);
        }
    }


    static boolean _2D_get0()
    {
        return ENABLE_LOG;
    }

    static boolean _2D_get1(AbstractThreadedSyncAdapter abstractthreadedsyncadapter)
    {
        return abstractthreadedsyncadapter.mAllowParallelSyncs;
    }

    static boolean _2D_get2(AbstractThreadedSyncAdapter abstractthreadedsyncadapter)
    {
        return abstractthreadedsyncadapter.mAutoInitialize;
    }

    static Context _2D_get3(AbstractThreadedSyncAdapter abstractthreadedsyncadapter)
    {
        return abstractthreadedsyncadapter.mContext;
    }

    static AtomicInteger _2D_get4(AbstractThreadedSyncAdapter abstractthreadedsyncadapter)
    {
        return abstractthreadedsyncadapter.mNumSyncStarts;
    }

    static Object _2D_get5(AbstractThreadedSyncAdapter abstractthreadedsyncadapter)
    {
        return abstractthreadedsyncadapter.mSyncThreadLock;
    }

    static HashMap _2D_get6(AbstractThreadedSyncAdapter abstractthreadedsyncadapter)
    {
        return abstractthreadedsyncadapter.mSyncThreads;
    }

    static Account _2D_wrap0(AbstractThreadedSyncAdapter abstractthreadedsyncadapter, Account account)
    {
        return abstractthreadedsyncadapter.toSyncKey(account);
    }

    public AbstractThreadedSyncAdapter(Context context, boolean flag)
    {
        this(context, flag, false);
    }

    public AbstractThreadedSyncAdapter(Context context, boolean flag, boolean flag1)
    {
        mSyncThreads = new HashMap();
        mSyncThreadLock = new Object();
        mContext = context;
        mISyncAdapterImpl = new ISyncAdapterImpl(null);
        mNumSyncStarts = new AtomicInteger(0);
        mAutoInitialize = flag;
        mAllowParallelSyncs = flag1;
    }

    private Account toSyncKey(Account account)
    {
        if(mAllowParallelSyncs)
            return account;
        else
            return null;
    }

    public Context getContext()
    {
        return mContext;
    }

    public final IBinder getSyncAdapterBinder()
    {
        return mISyncAdapterImpl.asBinder();
    }

    public abstract void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentproviderclient, SyncResult syncresult);

    public void onSecurityException(Account account, Bundle bundle, String s, SyncResult syncresult)
    {
    }

    public void onSyncCanceled()
    {
        Object obj = mSyncThreadLock;
        obj;
        JVM INSTR monitorenter ;
        SyncThread syncthread = (SyncThread)mSyncThreads.get(null);
        obj;
        JVM INSTR monitorexit ;
        if(syncthread != null)
            syncthread.interrupt();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void onSyncCanceled(Thread thread)
    {
        thread.interrupt();
    }

    private static final boolean ENABLE_LOG;
    public static final int LOG_SYNC_DETAILS = 2743;
    private static final String TAG = "SyncAdapter";
    private boolean mAllowParallelSyncs;
    private final boolean mAutoInitialize;
    private final Context mContext;
    private final ISyncAdapterImpl mISyncAdapterImpl;
    private final AtomicInteger mNumSyncStarts;
    private final Object mSyncThreadLock;
    private final HashMap mSyncThreads;

    static 
    {
        boolean flag;
        if(Build.IS_DEBUGGABLE)
            flag = Log.isLoggable("SyncAdapter", 3);
        else
            flag = false;
        ENABLE_LOG = flag;
    }
}
